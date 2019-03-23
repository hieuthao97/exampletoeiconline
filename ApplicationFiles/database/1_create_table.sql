use toeiconline;

create table userEntity(
#khóa chính userid tự tăng bigint(nhiều) tự tăng theo hệ thống
#nên viết thường tên biến
userid bigint not null primary key auto_increment,
name varchar(255) null,
password varchar(255) null,
fullname varchar(300) null,
createdated timestamp null


);
create table roleEntity(
roleid bigint not null primary key,
name varchar(100) null
);
