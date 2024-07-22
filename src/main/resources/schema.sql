

create table product
(
    id        bigint generated by default as identity,
    category_id bigint not null,
    name      varchar(15)  not null,
    price     integer      not null,
    image_url varchar(255) not null,
    primary key (id)
);

create table member
(
    id       bigint generated by default as identity,
    email    varchar(255) not null unique,
    password varchar(255) not null,
    role     varchar(64) not null,
    primary key (id)
);

create table wish
(
    id         bigint generated by default as identity,
    member_id  bigint not null,
    product_id bigint not null,
    primary key (id)
);

create table category
(
    id        bigint generated by default as identity,
    name      varchar(30)  not null unique,
    color     varchar(30)  not null,
    image_url varchar(255) not null,
    description varchar(255) not null,
    primary key (id)
);

create table options
(
    id         bigint generated by default as identity,
    product_id bigint not null,
    name       varchar(50)  not null unique,
    quantity   bigint not null,
    primary key (id)
);