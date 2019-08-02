package br.com.studentmanager.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.studentmanager.event.RecursoCriadoEvent;
import br.com.studentmanager.model.Student;
import br.com.studentmanager.repository.StudentRepository;
import br.com.studentmanager.service.StudentService;

@RestController
@RequestMapping("/students")					// Para mapear em qual endereço que pode se chamar os métodos
public class StudentResource {
	
	/*
	 * 		Nessa classe é onde é chamados os métodos de acordo com a requisição HTTP
	 */
	
	@Autowired
	private StudentRepository studentRepository; // Instanciando a interface com os métodos criados
	
	@Autowired
	private StudentService studentService;		 // Instanciando a class StudentService 
	
	@Autowired
	private ApplicationEventPublisher publisher; // É responsável por publicar eventos de aplicação para o Spring	
	
	@GetMapping									 // Ao fazer uma requisição GET sem nenhum parametro irá soliciar o retorno do método listarEstudantes()
	public List<Student> listarEstudantes(){	
		return studentRepository.findAll();      // Select * from esudantes  - Método padrão do SPRING BOOT para retornar todos os estudantes
	}
	
	@GetMapping("/{id}")						 // Ao fazer uma requisição GET com um parametro (id) * localhost:8080/students/3 * irá retornar somente o estudante com o id informado
	public ResponseEntity<Student> buscarPorId(@PathVariable long id){ 	
		// ReponseEntity é utilizado para manipular o HTTP reponse
		// PathVariable é para receber o parametro informado na requisição		
		Student student = studentRepository.getOne(id);
		
		// Verifica se foi encontrado, caso não encontrado retornamos o HTTP NOT FOUND
		return student != null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();	
	}
	
	@DeleteMapping("/{id}")						// Ao receber uma requisição DELETE com um paramêtro irá remover o estudante 
	@ResponseStatus(HttpStatus.NO_CONTENT)		// Retorna a requisição HTTP como NO_CONTENT
	public void remover(@PathVariable long id){	
		studentRepository.deleteById(id);		// Método padrão do SPRING BOOT para deletar algum item		
	}
	
	@PutMapping("/{id}")						// Ao fazer uma requisição POST com um paramêtro (id) irá atualizar os dados do estudante
	public ResponseEntity<Student> atualizar(@PathVariable Long id, @Valid @RequestBody Student student) {
		Student attStudent = studentService.atualizar(id, student);
		
		/*
		 * Caso ocorra tudo certo
		 * retorna o Http Request como 200 - OK
		 * 
		 */
		return ResponseEntity.ok(attStudent);
	}
	
	
	/*
	 *  ---- Método para criação de novos estudantes
	 *  
	 *  @Valid - Valida os campos informados, caso os dados forem inválidos retorna um HTTP 
	 *  @RequestBody - 
	 *  
	 */	
	@PostMapping	
	public ResponseEntity<Student> criar(@Valid @RequestBody Student student, HttpServletResponse response) {
		Student newStudent = studentRepository.save(student);
		
		// publisher - Publica o usuário no sistema  -- (?)
		publisher.publishEvent(new RecursoCriadoEvent(this, response, newStudent.getId()));		
		
		/*
		 * Retorna o HTTP request como 201 CREATED
		 */
		return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
	}

}
