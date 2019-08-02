package br.com.studentmanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity 					// Define que é uma entidade do banco de dados
@Table(name= "Students")	// Define a qual tabela essa entidade pertence
public class Student {

	@Id						// Informa que esse campo é a PK da tabela
	@GeneratedValue			// Por se tratar de um elemento auto_increment é inserido a estratégia de criação
			(strategy = GenerationType.IDENTITY)	 
	private long id;
	
	@NotNull				// Não pode ser criado um estudante sem nome
	private String nome;
	
	private String curso;
	
	/*
		Criação dos GETTERS AND SETTERS	
	*/
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	

	/*
	 * 	
	 *  Gerador de unique key
	 *  
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
		
}
