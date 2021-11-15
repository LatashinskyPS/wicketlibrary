create table author
(
    id         uuid         not null
        constraint author_pkey
            primary key,
    date_born  timestamp    not null,
    date_death timestamp,
    name       varchar(255) not null,
    surname    varchar(255) not null
);

create table publisher
(
    id      uuid         not null
        constraint publisher_pkey
            primary key,
    address varchar(255) not null,
    name    varchar(255) not null,
    number  varchar(255) not null
);

create table book
(
    id           uuid         not null
        constraint book_pkey
            primary key,
    description  varchar(255),
    name         varchar(255) not null,
    publisher_id uuid
        constraint fkgtvt7p649s4x80y6f4842pnfq
            references publisher
);

create table author_to_book
(
    book_id   uuid not null
        constraint fksh9c4nxabn353n7sd4miv5yv3
            references book,
    author_id uuid not null
        constraint fk2xxuj4m0iu8qv9jux3xnm3etd
            references author
);

