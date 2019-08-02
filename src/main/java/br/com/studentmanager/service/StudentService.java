package br.com.studentmanager.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import br.com.studentmanager.model.Student;
import br.com.studentmanager.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;		// Interface com os métodos SPRING BOOT
	
	public Student atualizar(Long id, Student student) {
		Student attStudent = buscarPeloId(id);
		
		/* Bean Utils - Copia as propriedades de um bean para outro
		 * 
		 */
		BeanUtils.copyProperties(student, attStudent, "id");
		
		// Método padrão do SPRING BOOT para alterar
		return studentRepository.save(attStudent);
	}
	
	/*
	 * Caso o id do estudante informado não for encontrado
	 * envia um throw exception assim cancelando a atualização
	 * 
	 */

	public Student buscarPeloId(Long id) {
		Student attStudent = studentRepository.getOne(id);
		if (attStudent == null) {
			throw new EmptyResultDataAccessException(1);
		}		
		return attStudent;
	}
	
}
