package br.com.studentmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.studentmanager.model.Instructor;


public interface InstructorRepository extends JpaRepository<Instructor, Long>{

}
