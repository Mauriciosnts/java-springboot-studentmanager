package br.com.studentmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.studentmanager.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	/*
	 * 	Interface que contém todos os métodos padrões do SpringBoot
	 * 	Como por exemplo:
	 * 		findall()
	 * 		deletebyid() 
	 * 
	 *  Também é permitido criar novos métodos, basta incluir nessa interface.
	 */
	
	
}
