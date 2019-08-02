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
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.studentmanager.event.RecursoCriadoEvent;
import br.com.studentmanager.model.Instructor;
import br.com.studentmanager.repository.InstructorRepository;
import br.com.studentmanager.service.InstructorService;



public class InstructorResource {
	
	@Autowired
	private InstructorRepository instructorRepository;
	
	@Autowired
	private InstructorService instructorService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@GetMapping
	public List<Instructor> listar(){
		return instructorRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Instructor> buscarPorId(@PathVariable long id){
		Instructor instructor = instructorRepository.getOne(id);
		return instructor != null ? ResponseEntity.ok(instructor) : ResponseEntity.notFound().build();
	}	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable long id) {
		instructorRepository.deleteById(id);
	}
		
	@PutMapping
	public ResponseEntity<Instructor> atualizar(@PathVariable Long id, @Valid @RequestBody Instructor instructor) {
		Instructor attinstructor = instructorService.atualizar(id, instructor);
		return ResponseEntity.ok(attinstructor);
	}
	
	@PostMapping	
	public ResponseEntity<Instructor> criar(@Valid @RequestBody Instructor instructor, HttpServletResponse response) {
		Instructor newinstructor = instructorRepository.save(instructor);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, newinstructor.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(newinstructor);
	}
}
