--liquibase formatted sql

-- changeset novokren:1
alter table product
    add column image varchar;
