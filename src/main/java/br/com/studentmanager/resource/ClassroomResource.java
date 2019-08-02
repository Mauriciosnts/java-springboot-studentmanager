package br.com.studentmanager.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.ResponseEntity;

import br.com.studentmanager.event.RecursoCriadoEvent;
import br.com.studentmanager.model.Classroom;
import br.com.studentmanager.repository.ClassroomRepository;
import br.com.studentmanager.service.ClassroomService;

public class ClassroomResource {

	@Autowired
	private ClassroomRepository classroomRepository;
	
	@Autowired
	private ClassroomService classroomService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@GetMapping
	public List<Classroom> listar(){
		return classroomRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Classroom> buscarPorId(@PathVariable long id){
		Classroom classroom = classroomRepository.getOne(id);
		return classroom != null ? ResponseEntity.ok(classroom) : ResponseEntity.notFound().build();
	}	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable long id) {
		classroomRepository.deleteById(id);
	}
		
	@PutMapping
	public ResponseEntity<Classroom> atualizar(@PathVariable Long id, @Valid @RequestBody Classroom classroom) {
		Classroom attclassroom = classroomService.atualizar(id, classroom);
		return ResponseEntity.ok(attclassroom);
	}
	
	@PostMapping	
	public ResponseEntity<Classroom> criar(@Valid @RequestBody Classroom classroom, HttpServletResponse response) {
		Classroom newclassroom = classroomRepository.save(classroom);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, newclassroom.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(newclassroom);
	}
	
}
