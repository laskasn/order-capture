
create database ordercapture encoding 'UTF8';
\connect ordercapture;
CREATE EXTENSION "uuid-ossp";

CREATE TYPE statuses AS ENUM ('NEW', 'FAILED', 'COMPLETED');

create table "product" (id uuid, name varchar(500) not null, sku varchar(200) not null, description text, primary key(id));
create table "customer" (id uuid, firstname varchar(300) not null, lastname varchar(300) not null, address varchar(500), primary key(id));
create table "order" (id uuid, customerid uuid not null references "customer"(id), orderdate date not null, lastupdatedate date not null, orderstatus statuses, primary key(id));
create table "order_product" (orderid uuid not null references "order"(id), productid uuid not null references "product"(id), primary key(orderid, productid));

CREATE INDEX title_idx ON "order" (orderstatus);
