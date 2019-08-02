package br.com.studentmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.studentmanager.model.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Long>{

}
