package br.com.studentmanager.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import br.com.studentmanager.model.Instructor;
import br.com.studentmanager.repository.InstructorRepository;

public class InstructorService {
	@Autowired
	private InstructorRepository instructorRepository;	
	
	public Instructor atualizar(Long id, Instructor instructor) {
		Instructor attInstructor = buscarPeloId(id);		
		BeanUtils.copyProperties(instructor, attInstructor, "id");
		return instructorRepository.save(attInstructor);
	}	

	public Instructor buscarPeloId(Long id) {
		Instructor attInstructor = instructorRepository.getOne(id);
		if (attInstructor == null) {
			throw new EmptyResultDataAccessException(1);
		}		
		return attInstructor;
	}
}
