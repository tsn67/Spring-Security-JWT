create table table_name
(
    id       bigint       not null
        primary key,
    name     varchar(30)  not null,
    email    varchar(255) null,
    password varchar(100) not null
);

