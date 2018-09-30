
CREATE TABLE User_ (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) DEFAULT NULL,
  password VARCHAR(255) DEFAULT NULL
);

CREATE TABLE Category (
  id SERIAL PRIMARY KEY,
  name varchar(255) DEFAULT NULL
);

CREATE TABLE Property (
  id SERIAL PRIMARY KEY,
  cid INT DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  CONSTRAINT fk_property_category FOREIGN KEY (cid) REFERENCES category (id)
);

CREATE TABLE Product (
  id SERIAL PRIMARY KEY,
  name varchar(255) DEFAULT NULL,
  originalPrice float DEFAULT NULL,
  promotePrice float DEFAULT NULL,
  stock INT DEFAULT NULL,
  cid INT DEFAULT NULL,
  createDate DATE DEFAULT NULL,
  CONSTRAINT fk_product_category FOREIGN KEY (cid) REFERENCES category (id)
);

CREATE TABLE PropertyValue (
  id SERIAL PRIMARY KEY,
  pid INT DEFAULT NULL,
  ptid INT DEFAULT NULL,
  value varchar(255) DEFAULT NULL,
  CONSTRAINT fk_propertyvalue_property FOREIGN KEY (ptid) REFERENCES property (id),
  CONSTRAINT fk_propertyvalue_product FOREIGN KEY (pid) REFERENCES product (id)
);

CREATE TABLE ProductImage (
  id SERIAL PRIMARY KEY,
  pid INT DEFAULT NULL,
  type varchar(255) DEFAULT NULL,
  CONSTRAINT fk_productimage_product FOREIGN KEY (pid) REFERENCES product (id)
);

CREATE TABLE Order_ (
  id SERIAL PRIMARY KEY,
  orderCode varchar(255) DEFAULT NULL,
  address varchar(255) DEFAULT NULL,
  receiver varchar(255) DEFAULT NULL,
  phone varchar(255) DEFAULT NULL,
  createDate DATE DEFAULT NULL,
  payDate DATE DEFAULT NULL,
  uid INT DEFAULT NULL,
  status varchar(255) DEFAULT NULL,
  CONSTRAINT fk_order_user FOREIGN KEY (uid) REFERENCES user_ (id)
);

CREATE TABLE OrderItem (
  id SERIAL PRIMARY KEY,
  pid INT DEFAULT NULL,
  oid INT DEFAULT NULL,
  uid INT DEFAULT NULL,
  number INT DEFAULT NULL,
  CONSTRAINT fk_orderitem_user FOREIGN KEY (uid) REFERENCES user_ (id),
  CONSTRAINT fk_orderitem_product FOREIGN KEY (pid) REFERENCES product (id)
);
