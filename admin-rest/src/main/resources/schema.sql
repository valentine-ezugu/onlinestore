DROP TABLE IF EXISTS billing_address;
CREATE TABLE billing_address (
  id                       BIGINT NOT NULL AUTO_INCREMENT,
  billing_address_city     VARCHAR(255),
  billing_address_country  VARCHAR(255),
  billing_address_name     VARCHAR(255),
  billing_address_state    VARCHAR(255),
  billing_address_street1  VARCHAR(255),
  billing_address_street2  VARCHAR(255),
  billing_address_zip_code VARCHAR(255),
  order_id                 BIGINT,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS book;
CREATE TABLE book (
  id               BIGINT           NOT NULL AUTO_INCREMENT,
  active           BIT              NOT NULL,
  author           VARCHAR(255),
  category         VARCHAR(255),
  description      TEXT,
  format           VARCHAR(255),
  in_stock_number  INTEGER          NOT NULL,
  isbn             INTEGER          NOT NULL,
  language         VARCHAR(255),
  list_price       DOUBLE PRECISION NOT NULL,
  number_of_pages  VARCHAR(255),
  our_price        DOUBLE PRECISION NOT NULL,
  publication_date VARCHAR(255),
  publisher        VARCHAR(255),
  shipping_weight  DOUBLE PRECISION NOT NULL,
  title            VARCHAR(255),
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS book_to_cart_item;
CREATE TABLE book_to_cart_item (
  id           BIGINT NOT NULL AUTO_INCREMENT,
  book_id      BIGINT,
  cart_item_id BIGINT,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS cart_item;
CREATE TABLE cart_item (
  id               BIGINT  NOT NULL AUTO_INCREMENT,
  qty              INTEGER NOT NULL,
  sub_total        DECIMAL(19, 2),
  book_id          BIGINT,
  order_id         BIGINT,
  shopping_cart_id BIGINT,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS password_reset_token;
CREATE TABLE password_reset_token (
  id          BIGINT NOT NULL AUTO_INCREMENT,
  expiry_date DATETIME,
  token       VARCHAR(255),
  user_id     BIGINT NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS payment;
CREATE TABLE payment (
  id           BIGINT  NOT NULL AUTO_INCREMENT,
  card_name    VARCHAR(255),
  card_number  VARCHAR(255),
  cvc          INTEGER NOT NULL,
  expiry_month INTEGER NOT NULL,
  expiry_year  INTEGER NOT NULL,
  holder_name  VARCHAR(255),
  type         VARCHAR(255),
  order_id     BIGINT,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS role;
CREATE TABLE role (
  role_id BIGINT       NOT NULL AUTO_INCREMENT,
  name    VARCHAR(255) NOT NULL,
  PRIMARY KEY (role_id)
);

DROP TABLE IF EXISTS shipping_address;
CREATE TABLE shipping_address (
  id                        BIGINT NOT NULL AUTO_INCREMENT,
  shipping_address_city     VARCHAR(255),
  shipping_address_country  VARCHAR(255),
  shipping_address_name     VARCHAR(255),
  shipping_address_state    VARCHAR(255),
  shipping_address_street1  VARCHAR(255),
  shipping_address_street2  VARCHAR(255),
  shipping_address_zip_code VARCHAR(255),
  order_id                  BIGINT,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS shopping_cart;
CREATE TABLE shopping_cart (
  id          BIGINT NOT NULL AUTO_INCREMENT,
  grand_total DECIMAL(19, 2),
  user_id     BIGINT,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id         BIGINT       NOT NULL AUTO_INCREMENT,
  email      VARCHAR(255) NOT NULL,
  enabled    BIT          NOT NULL,
  first_name VARCHAR(255),
  last_name  VARCHAR(255),
  password   VARCHAR(255),
  phone      VARCHAR(255),
  username   VARCHAR(255),
  PRIMARY KEY (id)
);


DROP TABLE IF EXISTS user_id;
CREATE TABLE user_id (
  id                  BIGINT NOT NULL AUTO_INCREMENT,
  order_date          DATETIME,
  order_status        VARCHAR(255),
  order_total         DECIMAL(19, 2),
  shipping_date       DATETIME,
  shipping_method     VARCHAR(255),
  billing_address_id  BIGINT,
  payment_id          BIGINT,
  shipping_address_id BIGINT,
  user_id             BIGINT,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role (
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  PRIMARY KEY (user_id, role_id)
);


DROP TABLE IF EXISTS user_billing;
CREATE TABLE user_billing (
  id                    BIGINT NOT NULL AUTO_INCREMENT,
  user_billing_city     VARCHAR(255),
  user_billing_country  VARCHAR(255),
  user_billing_name     VARCHAR(255),
  user_billing_state    VARCHAR(255),
  user_billing_street1  VARCHAR(255),
  user_billing_street2  VARCHAR(255),
  user_billing_zip_code VARCHAR(255),
  user_payment_id       BIGINT,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS user_payment;
CREATE TABLE user_payment (
  id              BIGINT  NOT NULL AUTO_INCREMENT,
  card_name       VARCHAR(255),
  card_number     VARCHAR(255),
  cvc             INTEGER NOT NULL,
  default_payment BIT     NOT NULL,
  expiry_month    INTEGER NOT NULL,
  expiry_year     INTEGER NOT NULL,
  holder_name     VARCHAR(255),
  type            VARCHAR(255),
  user_id         BIGINT,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS user_shipping;
CREATE TABLE user_shipping (
  id                     BIGINT NOT NULL AUTO_INCREMENT,
  user_shipping_city     VARCHAR(255),
  user_shipping_country  VARCHAR(255),
  user_shipping_default  BIT    NOT NULL,
  user_shipping_name     VARCHAR(255),
  user_shipping_state    VARCHAR(255),
  user_shipping_street1  VARCHAR(255),
  user_shipping_street2  VARCHAR(255),
  user_shipping_zip_code VARCHAR(255),
  user_id                BIGINT,
  PRIMARY KEY (id)
);

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

ALTER TABLE billing_address
  ADD CONSTRAINT FKn9o6nq40aqjyebaofkolmgv69 FOREIGN KEY (order_id) REFERENCES user_id (id);


ALTER TABLE book_to_cart_item
  ADD CONSTRAINT FK254kg9aacrs8uqa93ijc3garu FOREIGN KEY (book_id) REFERENCES book (id);
ALTER TABLE book_to_cart_item
  ADD CONSTRAINT FKbdyqr108hc7c06xtem0dhv9mk FOREIGN KEY (cart_item_id) REFERENCES cart_item (id);


ALTER TABLE cart_item
  ADD CONSTRAINT FKis5hg85qbs5d91etr4mvd4tx6 FOREIGN KEY (book_id) REFERENCES book (id);
ALTER TABLE cart_item
  ADD CONSTRAINT FKbfad2pd4ooykdqmav0oaynd1b FOREIGN KEY (order_id) REFERENCES user_id (id);
ALTER TABLE cart_item
  ADD CONSTRAINT FKe89gjdx91fxnmkkssyoim8xfu FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart (id);


ALTER TABLE password_reset_token
  ADD CONSTRAINT FK5lwtbncug84d4ero33v3cfxvl FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE payment
  ADD CONSTRAINT FK919xn6cn5gio3mf7a7imk0aep FOREIGN KEY (order_id) REFERENCES user_id (id);

ALTER TABLE shipping_address
  ADD CONSTRAINT FK13e0im0d0g0etv74kpk1lhks6 FOREIGN KEY (order_id) REFERENCES user_id (id);

ALTER TABLE shopping_cart
  ADD CONSTRAINT FK254qp5akhuaaj9n5co4jww3fk FOREIGN KEY (user_id) REFERENCES user (id);


ALTER TABLE user_id
  ADD CONSTRAINT FKlvpbjicc57x0f2j2d1o0tj9e5 FOREIGN KEY (billing_address_id) REFERENCES billing_address (id);
ALTER TABLE user_id
  ADD CONSTRAINT FKqb8pss8wf8dop8m3fdwl1rot5 FOREIGN KEY (payment_id) REFERENCES payment (id);
ALTER TABLE user_id
  ADD CONSTRAINT FK7stlup30t5kexwbo4tlss2k4x FOREIGN KEY (shipping_address_id) REFERENCES shipping_address (id);
ALTER TABLE user_id
  ADD CONSTRAINT FKj1qsusvrhf359t8cgg9fm5r8o FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user_role
  ADD CONSTRAINT FKa68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES role (role_id);
ALTER TABLE user_role
  ADD CONSTRAINT FK859n2jvi8ivhui0rl0esws6o FOREIGN KEY (user_id) REFERENCES user (id);


ALTER TABLE user_billing
  ADD CONSTRAINT FK3v6hd7snyc3g9s72u41k1fydu FOREIGN KEY (user_payment_id) REFERENCES user_payment (id);

ALTER TABLE user_payment
  ADD CONSTRAINT FK8fb9fr82lb1qk2cw55ito9rk6 FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user_shipping
  ADD CONSTRAINT FK9hidca5hndj9y0b5jb0xtpn9u FOREIGN KEY (user_id) REFERENCES user (id);