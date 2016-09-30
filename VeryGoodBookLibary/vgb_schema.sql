DROP DATABASE IF EXISTS vgb;
CREATE DATABASE vgb DEFAULT CHARSET=utf8;

USE vgb;

DROP TABLE IF EXISTS customers;
CREATE TABLE  customers (
  id char(10) NOT NULL,
  password varchar(20) NOT NULL,
  name varchar(20) NOT NULL,
  email varchar(30) NOT NULL,
  gender char(1) NOT NULL,
  birthday date DEFAULT NULL,
  phone varchar(20) DEFAULT NULL,
  address varchar(100) DEFAULT NULL,
  married tinyint(1) NOT NULL DEFAULT '0',
  blood_type varchar(2) DEFAULT NULL,
  type varchar(10) DEFAULT 'Customer',
  discount int(10) unsigned DEFAULT 0,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO customers (id, password, name, email, gender)
VALUES('A123456789','123456','張三','sammul.chang.tw@gmail.com','M');

INSERT INTO customers 
(id, password, name, email, gender,birthday,phone,address,married,blood_type,type,discount)
VALUES('A223456781','123456','林梅莉','marie.lin.tw@gmail.com','F',
    '1996-9-20','0225149191','台北市復興北路99號14F',true,'B','VIP',15);

DROP TABLE IF EXISTS publishers;
CREATE TABLE  publishers (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  /*contactor varchar(20) NOT NULL,*/
  address varchar(100) NOT NULL,
  phone varchar(20) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO publishers(id, name, address, phone)
VALUES(1, '悅知文化','台北市松山區復興北路99號12樓','0227198811');

INSERT INTO publishers(id, name, address, phone)
VALUES(2, '博碩文化','新北市汐止區新台五路一段112號10樓A棟','0226962869');

INSERT INTO publishers(id,name, address, phone)
VALUES(3, '碁峰資訊','台北市南港區三重路66號7樓之6','0227882408');

DROP TABLE IF EXISTS books;
CREATE TABLE  books (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  name varchar(40) NOT NULL,
  subtitle varchar(100) DEFAULT NULL,
  auther_name varchar(20) NOT NULL,
  publisher_id int(10) unsigned NOT NULL,
  publish_date date NOT NULL,
  list_price double unsigned NOT NULL,
  discount int(10) unsigned NOT NULL DEFAULT '10',
  stock int(10) unsigned NOT NULL,
  photo blob,
  photo_url varchar(120) DEFAULT NULL,
  isbn varchar(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK_books_publishers (publisher_id),
  CONSTRAINT FK_books_publishers FOREIGN KEY (publisher_id) REFERENCES publishers (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

