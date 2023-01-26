DROP  TABLE  IF EXISTS diary ;

CREATE TABLE diary (
                       id serial constraint table_name_pk primary key,
                       users text ,
                         times             time,           -- время
                         cases         	text,           -- дела
                         dates          date   ,           -- дата
                       FOREIGN KEY  (users)  REFERENCES Users (User_id)
    );
CREATE TABLE Users (
                       User_id serial constraint table_name_pk primary key,
                       FullName            text,
                       role             text,           -- роль
                       login         	text,           -- логин
                         password          text              -- пароль
);