drop table if exists member; 
drop table if exists post; 


create table member(
id integer primary key autoincrement,
name text
);
create table post(
id integer primary key autoincrement,
createTime datetime,
memberId integer,
title text,
content text
);