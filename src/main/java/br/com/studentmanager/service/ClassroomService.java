package br.com.studentmanager.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import br.com.studentmanager.model.Classroom;
import br.com.studentmanager.repository.ClassroomRepository;


public class ClassroomService {
	@Autowired
	private ClassroomRepository classroomRepository;	
	
	public Classroom atualizar(Long id, Classroom classroom) {
		Classroom attClassroom = buscarPeloId(id);		
		BeanUtils.copyProperties(classroom, attClassroom, "id");
		return classroomRepository.save(attClassroom);
	}	

	public Classroom buscarPeloId(Long id) {
		Classroom attClassroom = classroomRepository.getOne(id);
		if (attClassroom == null) {
			throw new EmptyResultDataAccessException(1);
		}		
		return attClassroom;
	}
}
