create database if not exists studentsmanager;
use studentsmanager;

create table students(
	id bigint auto_increment,
    nome varchar(100) not null,
    curso varchar(40) not null,
    
    primary key (id)
);


INSERT INTO students values (null, "Ojisvalnicley Soares Tavarez", "Curso Spring Boot");
INSERT INTO students values (null, "Matisltao Candido Souza", "Curso React JS");
INSERT INTO students values (null, "Wesdiney Otavio Tales", "Curso Javascript");
INSERT INTO students values (null, "Jienellen Machado Fuher", "Curso Html");
INSERT INTO students values (null, "Uwesleen Campos", "Curso Java");

select * from students;