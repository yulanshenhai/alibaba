use alibaba;

# table

-- auto-generated definition
create table if not exists alibaba.persistent_logins
(
    username  varchar(64) not null,
    series    varchar(64) not null primary key,
    token     varchar(64) not null,
    last_used timestamp   not null
);
