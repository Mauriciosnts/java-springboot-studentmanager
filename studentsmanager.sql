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

create table classrooms(
	id bigint auto_increment,
    name varchar(70) not null,
    floor varchar(30) not null,
    primary key(id)
);

INSERT INTO classrooms VALUES (null, "Administração", "2 ANDAR");
INSERT INTO classrooms VALUES (null, "Laboratório Robotica", "6 ANDAR");
INSERT INTO classrooms VALUES (null, "Laboratório Química", "1 ANDAR");
INSERT INTO classrooms VALUES (null, "Matemática Aplicada", "8 ANDAR");
INSERT INTO classrooms VALUES (null, "Engenharia Mecatrônica", "8 ANDAR");
INSERT INTO classrooms VALUES (null, "Biblioteca", "3 ANDAR");

select * from classrooms;

create table instructors(
	id bigint auto_increment,
    name varchar(40) not null,
    email varchar(60) not null,
    certificates varchar(200),
    primary key(id)
);

INSERT INTO instructors VALUES (NULL, "Igor Severino Assis"			, "igor.severino@studentsmanager.com.br"	, "Information Technology Infrastructure Library");
INSERT INTO instructors VALUES (NULL, "Julio Renato Assis"			, "julio.renato@studentsmanager.com.br"		, "Certified Information Systems Security Professional");
INSERT INTO instructors VALUES (NULL, "Isabel Amanda"				, "isabel.amanda@studentsmanager.com.br"	, "Oracle Certified Professional Advanced PL/SQL");
INSERT INTO instructors VALUES (NULL, "Lucas Fernando Campos"		, "lucas.fernando@studentsmanager.com.br"	, "Profissional Técnico Certificado em Gerenciamento de Projetos");
INSERT INTO instructors VALUES (NULL, "Joana Fátima Ramos"			, "joana.fatima@studentsmanager.com.br"		, "Profissional de Gerenciamento de Programas ");
INSERT INTO instructors VALUES (NULL, "Vitória Andreia da Rocha"	, "vitoria.andreia@studentsmanager.com.br"	, "Animal Breeding and Genetics");
INSERT INTO instructors VALUES (NULL, "Luís Geraldo Ribeiro"		, "luis.geraldo@studentsmanager.com.br"		, "");
INSERT INTO instructors VALUES (NULL, "Martin Julio Silveira"		, "martin.julio@studentsmanager.com.br"		, "");
INSERT INTO instructors VALUES (NULL, "Helena Elisa Farias"			, "helena.elisa@studentsmanager.com.br"		, "");

