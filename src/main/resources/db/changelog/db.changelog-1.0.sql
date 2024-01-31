--liquibase formatted sql

--changeset novokren:1
create table if not exists public.users
(
    id               bigserial
    primary key,
    birth_date       date,
    email            varchar(255) not null
    constraint uk_6dotkott2kjsp8vw4d0m25fb7
    unique,
    first_name       varchar(255),
    gender           varchar(255)
    constraint users_gender_check
    check ((gender)::text = ANY ((ARRAY ['MALE'::character varying, 'FEMALE'::character varying])::text[])),
    last_name        varchar(255),
    password         varchar(255),
    role             varchar(255)
    constraint users_role_check
    check ((role)::text = ANY ((ARRAY ['USER'::character varying, 'ADMIN'::character varying])::text[])),
    created_at       timestamp,
    modified_at      timestamp,
    last_modified_at timestamp(6) with time zone
                                      );

--changeset novokren:2
create table if not exists public.users_aud
(
    id         bigint  not null,
    rev        integer not null
    constraint fkmrjb3nxent1mi8jjld588s7u6
    references public.revision,
    revtype    smallint,
    birth_date date,
    email      varchar(255),
    first_name varchar(255),
    gender     varchar(255)
    constraint users_aud_gender_check
    check ((gender)::text = ANY ((ARRAY ['MALE'::character varying, 'FEMALE'::character varying])::text[])),
    last_name  varchar(255),
    password   varchar(255),
    role       varchar(255)
    constraint users_aud_role_check
    check ((role)::text = ANY ((ARRAY ['USER'::character varying, 'ADMIN'::character varying])::text[])),
    primary key (rev, id)
    );

--changeset novokren:3
create table if not exists public.product
(
    id           serial
    primary key,
    description  varchar(255),
    name         varchar(255),
    price        numeric(38, 2),
    product_type varchar(255)
    constraint product_product_type_check
    check ((product_type)::text = ANY
((ARRAY ['FIRST_TYPE'::character varying, 'SECOND_TYPE'::character varying, 'THIRD_TYPE'::character varying])::text[]))
    );

--changeset novokren:4
create table if not exists public.shopping_cart
(
    id      bigserial
    primary key,
    user_id bigint
    constraint uk_qx5dh8nhlnh77h8im91vlqwdv
    unique
    constraint fkr1irjigmqcpfrvggavnr7vjyv
    references public.users
);

--changeset novokren:5
create table if not exists public.shopping_cart_products
(
    shopping_cart_id bigint  not null
    constraint fkb0wl9vd38umuh226jsjqx3grq
    references public.shopping_cart,
    quantity         integer,
    product_id       integer not null
    constraint fk1ska6634dwt9j5esgp6u9yfji
    references public.product,
    primary key (shopping_cart_id, product_id)
    );

--changeset novokren:6
create table if not exists public.orders
(
    id               bigserial
    primary key,
    delivery_method  varchar(255)
    constraint orders_delivery_method_check
    check ((delivery_method)::text = ANY
((ARRAY ['PICKUP'::character varying, 'POST_OFFICE'::character varying, 'COURIER'::character varying])::text[])),
    time_of_creation timestamp(6),
    user_id          bigint
    constraint fk32ql8ubntj5uh44ph9659tiih
    references public.users
    );

--changeset novokren:7
create table if not exists public.order_products_mapping
(
    order_id   bigint  not null
    constraint fki6f9ubq625rak7acllrcsefbg
    references public.orders,
    quantity   integer,
    product_id integer not null
    constraint fk3hguj2chdl3vbws02evgbyq0s
    references public.product,
    primary key (order_id, product_id)
    );

--changeset novokren:8
create table if not exists public.revision
(
    id        serial
    primary key,
    timestamp bigint
);
