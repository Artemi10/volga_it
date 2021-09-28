create table documents
(
    path varchar(256) not null,
    id   bigint auto_increment,
    constraint documents_id_uindex
        unique (id),
    constraint documents_path_uindex
        unique (path)
);

alter table documents
    add primary key (id);

create table statistics
(
    id          bigint auto_increment,
    word        varchar(256) not null,
    amount      int          not null,
    document_id bigint       not null,
    constraint statistic_id_uindex
        unique (id),
    constraint statistics_documents_id_fk
        foreign key (document_id) references documents (id)
            on delete cascade
);

alter table statistics
    add primary key (id);

