--liquibase formatted sql

--changeset knurbolat:1
create table users(
  id serial primary key,
  username varchar(128) not null unique,
  password varchar(128) not null
);