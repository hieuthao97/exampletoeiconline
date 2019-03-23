use toeiconline;

create table listenguidelineEntity(
  listenguidelineid bigint not null primary key auto_increment,
  title varchar(512) null ,
  image varchar(255) null ,
  content text null ,
  createdated timestamp null ,
  modifieddate timestamp null
)
 #table trung gian
  create table comment (
    commentid bigint not null primary key auto_increment,
    content text null,
    #2 khóa ngoại
    userid bigint null ,
    listenguidelineid bigint null ,
    createdate timestamp null
  )