create database file_hider;
use file_hider;

create table users(id int primary key auto_increment,name varchar(50) not null,email varchar(50) unique,password varchar(50) not null unique);

create table data(id int primary key auto_increment,file_name varchar(50),path varchar(100),email varchar(50) not null,bin_data blob);
