# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table responsavel (
  id                            bigint not null,
  cpf                           varchar(255),
  nome                          varchar(255),
  email                         varchar(255),
  data_nascimento               timestamptz,
  constraint pk_responsavel primary key (id)
);
create sequence responsavel_seq;


# --- !Downs

drop table if exists responsavel cascade;
drop sequence if exists responsavel_seq;

