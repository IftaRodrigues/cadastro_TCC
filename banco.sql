--drop table pessoa cascade;
--drop table aluno cascade;
--drop table professor cascade;
--drop table curso cascade;
--drop table tipoProjeto cascade;
--drop table areaPesquisa cascade;
--drop table situacao cascade;
--drop table resultado cascade;
--drop table avaliacao cascade;
--drop table projeto cascade;
--drop table banca_avaliadora cascade;


create table pessoa
(
 id_pessoa serial,
 nome varchar (120),
 cpf varchar (120),
 rg varchar (120),
 email varchar (120),
 constraint pessoa_pk primary key (id_pessoa)
);

create table professor
(
 id_professor serial,
 registro integer,
 salario numeric (10,2), 
 constraint professor_pk primary key (id_professor)
)inherits (pessoa);

create table aluno
(
 id_aluno serial ,
 matricula integer ,
 constraint aluno_pk primary key (id_aluno)
)inherits (pessoa);

create table curso
(
 id_curso serial ,
 nome varchar (120) ,
 cargaHoraria integer ,
 constraint curso_pk primary key (id_curso)
);

create table tipoProjeto
(
 id_tipoProjeto serial ,
 descricao varchar (120) ,
 constraint tipoProjeto_pk primary key (id_tipoProjeto)
);

create table areaPesquisa
(
 id_areaPesquisa serial ,
 nome varchar (120) ,
 constraint areaPesquisa_pk primary key (id_areaPesquisa)
);

create table situacao
(
 id_situacao serial,
 descricao varchar (120) ,
 constraint situacao_pk primary key (id_situacao)
);

create table resultado
(
 id_resultado serial ,
 descricao varchar (120) ,
 constraint resultado_pk primary key (id_resultado)
);

create table avaliacao
(
 id_avaliacao serial ,
 notaAvaliador1 numeric (10,2) ,
 notaAvaliador2 numeric (10,2) ,
 notaOrientador numeric (10,2) ,
 dataRealizacao date ,
 resultado integer ,
 constraint avaliacao_pk primary key (id_avaliacao),
 constraint resultado_projeto_fk foreign key (resultado)
      references resultado (id_resultado) 
);



create table projeto
(
  id_projeto serial ,
  titulo varchar (120) ,
  aluno integer ,
  curso integer ,
  tipoProjeto integer ,
  areaPesquisa integer ,
  orientador integer ,
  constraint projeto_pkey primary key (id_projeto),
  constraint aluno_projeto_fk foreign key (aluno)
      references aluno (id_aluno),
  constraint curso_projeto_fk foreign key (curso)
      references curso (id_curso),
  constraint tipoProjeto_projeto_fk foreign key (tipoProjeto)
      references tipoProjeto (id_tipoProjeto),
  constraint areaPesquisa_projeto_fk foreign key (areaPesquisa)
      references areaPesquisa (id_areaPesquisa),
  constraint orientador_projeto_fk foreign key (orientador)
      references professor (id_professor)     
);

create table banca_avaliadora
(
  id_banca serial ,
  projeto integer ,
  avaliador1 integer ,
  avaliador2 integer ,
  situacao integer ,
  avaliacao integer ,
  constraint banca_avaliadora_pkey primary key (id_banca),
  constraint projeto_projeto_fk foreign key (projeto)
      references projeto (id_projeto),
  constraint avaliador1_projeto_fk foreign key (avaliador1)
      references professor (id_professor),
  constraint avaliador2_projeto_fk foreign key (avaliador2)
      references professor (id_professor),
  constraint situacao_projeto_fk foreign key (situacao)
      references situacao (id_situacao),
  constraint avaliacao_projeto_fk foreign key (avaliacao)
      references avaliacao (id_avaliacao)
);

/*
select pj.id_projeto, pj.titulo, a.id_aluno, a.nome as aluno, a.cpf as cpf_aluno, a.rg as rg_aluno, a.email as email_aluno, a.matricula, 
  c.id_curso, c.nome as curso, c.cargaHoraria, t.id_tipoProjeto, t.descricao as tipoProjeto, r.id_areaPesquisa, r.nome as areaPesquisa, 
  o.id_professor, o.nome as orientador, o.cpf as cpf_orientador, a.rg as rg_orientador, o.email as email_orientador, o.registro, o.salario
from projeto pj 
inner join aluno a on (a.id_aluno = pj.aluno) 
inner join curso c on (c.id_curso=pj.curso)
inner join tipoProjeto t on (t.id_tipoProjeto=pj.tipoProjeto)
inner join areaPesquisa r on (r.id_areaPesquisa=pj.areaPesquisa)
inner join professor o on (o.id_professor=pj.orientador);

select *,
  p1.id_professor as id_avaliador1, p1.nome as avaliador1, p1.cpf as cpf_avaliador1, a.rg as rg_avaliador1, p1.email as email_avaliador1, p1.registro as registro_avaliador1, p1.salario as salario_avaliador1,
  p2.id_professor as id_avaliador2, p2.nome as avaliador2, p2.cpf as cpf_avaliador2, a.rg as rg_avaliador2, p2.email as email_avaliador2, p2.registro as registro_avaliador2, p2.salario as salario_avaliador2,
  s.id_situacao, s.descricao as situacao,
  pj.id_projeto, pj.titulo,
  a.id_aluno, a.nome as aluno, a.cpf as cpf_aluno, a.rg as rg_aluno, a.email as email_aluno, a.matricula, 
  c.id_curso, c.nome as curso, c.cargaHoraria, 
  t.id_tipoProjeto, t.descricao as tipoProjeto, 
  r.id_areaPesquisa, r.nome as areaPesquisa, 
  o.id_professor as id_orientador, o.nome as orientador, o.cpf as cpf_orientador, a.rg as rg_orientador, o.email as email_orientador, o.registro as registro_orientador, o.salario as salario_orientador
from banca_avaliadora b
inner join professor p1 on (p1.id_professor= b.avaliador1)
inner join professor p2 on (p2.id_professor= b.avaliador2)
inner join situacao s on (s.id_situacao= b.situacao)
inner join projeto pj on (pj.id_projeto= b.projeto) 
inner join aluno a on (a.id_aluno = pj.aluno) 
inner join curso c on (c.id_curso=pj.curso)
inner join tipoProjeto t on (t.id_tipoProjeto=pj.tipoProjeto)
inner join areaPesquisa r on (r.id_areaPesquisa=pj.areaPesquisa)
inner join professor o on (o.id_professor=pj.orientador);
*/


