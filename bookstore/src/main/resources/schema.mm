DROP TABLE IF EXISTS billing_address;

CREATE TABLE billing_address (
  id                    BIGINT (20) NOT NULL AUTO_INCREMENT,
  billingAddressCity    VARCHAR(255),
  billingAddressCountry VARCHAR(255),
  billingAddressName    VARCHAR(255),
  billingAddressState   VARCHAR(255),
  billingAddressStreet1 VARCHAR(255),
  billingAddressStreet2 VARCHAR(255),
  billingAddressZipCode VARCHAR(255),
  order_id              BIGINT (20),
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS Book;
DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id`               BIGINT (20) NOT NULL AUTO_INCREMENT,
  `active`           BIT (1) NOT NULL,
  `author`           VARCHAR(255) DEFAULT NULL,
  `category`         VARCHAR(255) DEFAULT NULL,
  `description`      TEXT,
  `format`           VARCHAR(255) DEFAULT NULL,
  `in_stock_number`  INT(11) NOT NULL,
  `isbn`             INT(11) NOT NULL,
  `language`         VARCHAR(255) DEFAULT NULL,
  `list_price`       DOUBLE  NOT NULL,
  `number_of_pages`  VARCHAR(255) DEFAULT NULL,
  `our_price`        DOUBLE  NOT NULL,
  `publication_date` VARCHAR(255) DEFAULT NULL,
  `publisher`        VARCHAR(255) DEFAULT NULL,
  `shipping_weight`  DOUBLE  NOT NULL,
  `title`            VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE =InnoDB DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `book_to_cart_item`;

CREATE TABLE `book_to_cart_item` (
  `id`           BIGINT (20) NOT NULL AUTO_INCREMENT,
  `book_id`      BIGINT (20) DEFAULT NULL,
  `cart_item_id` BIGINT (20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS cart_item;

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart_item` (
  `id`               BIGINT (20) NOT NULL AUTO_INCREMENT,
  `qty`              INT(11) NOT NULL,
  `sub_total`        DECIMAL(19, 2) DEFAULT NULL,
  `book_id`          BIGINT (20) DEFAULT NULL,
  `order_id`         BIGINT (20) DEFAULT NULL,
  `shopping_cart_id` BIGINT (20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `password_reset_token`;

CREATE TABLE `password_reset_token` (
  `id`          BIGINT (20) NOT NULL AUTO_INCREMENT,
  `expiry_date` DATETIME     DEFAULT NULL,
  `token`       VARCHAR(255) DEFAULT NULL,
  `user_id`     BIGINT (20) NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `id`           BIGINT (20) NOT NULL AUTO_INCREMENT,
  `card_name`    VARCHAR(255) DEFAULT NULL,
  `card_number`  VARCHAR(255) DEFAULT NULL,
  `cvc`          INT(11) NOT NULL,
  `expiry_month` INT(11) NOT NULL,
  `expiry_year`  INT(11) NOT NULL,
  `holder_name`  VARCHAR(255) DEFAULT NULL,
  `type`         VARCHAR(255) DEFAULT NULL,
  `order_id`     BIGINT (20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `roleid`  INT(11) NOT NULL,
  `name`    VARCHAR(255) DEFAULT NULL,
  `role_id` INT(11) NOT NULL,
  PRIMARY KEY (`roleid`)
);
DROP TABLE IF EXISTS `shipping_address`;

CREATE TABLE `shipping_address` (
  `id`                        BIGINT (20) NOT NULL AUTO_INCREMENT,
  `shipping_address_city`     VARCHAR(255) DEFAULT NULL,
  `shipping_address_country`  VARCHAR(255) DEFAULT NULL,
  `shipping_address_name`     VARCHAR(255) DEFAULT NULL,
  `shipping_address_state`    VARCHAR(255) DEFAULT NULL,
  `shipping_address_street1`  VARCHAR(255) DEFAULT NULL,
  `shipping_address_street2`  VARCHAR(255) DEFAULT NULL,
  `shipping_address_zip_code` VARCHAR(255) DEFAULT NULL,
  `order_id`                  BIGINT (20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS `shopping_cart`;

CREATE TABLE `shopping_cart` (
  `id`          BIGINT (20) NOT NULL AUTO_INCREMENT,
  `grand_total` DECIMAL(19, 2) DEFAULT NULL,
  `user_id`     BIGINT (20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id`        BIGINT (20) NOT NULL AUTO_INCREMENT,
  `email`     VARCHAR(255) NOT NULL,
  `enabled`   BIT (1) NOT NULL,
  `firstname` VARCHAR(255) DEFAULT NULL,
  `lastname`  VARCHAR(255) DEFAULT NULL,
  `password`  VARCHAR(255) DEFAULT NULL,
  `phone`     VARCHAR(255) DEFAULT NULL,
  `username`  VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `user_billing`;
CREATE TABLE `user_billing` (
  `id`                    BIGINT (20) NOT NULL AUTO_INCREMENT,
  `user_billing_city`     VARCHAR(255) DEFAULT NULL,
  `user_billing_country`  VARCHAR(255) DEFAULT NULL,
  `user_billing_name`     VARCHAR(255) DEFAULT NULL,
  `user_billing_state`    VARCHAR(255) DEFAULT NULL,
  `user_billing_street1`  VARCHAR(255) DEFAULT NULL,
  `user_billing_street2`  VARCHAR(255) DEFAULT NULL,
  `user_billing_zip_code` VARCHAR(255) DEFAULT NULL,
  `user_payment_id`       BIGINT (20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `user_id`;

CREATE TABLE `user_id` (
  `id`                  BIGINT (20) NOT NULL AUTO_INCREMENT,
  `order_date`          DATETIME       DEFAULT NULL,
  `order_status`        VARCHAR(255)   DEFAULT NULL,
  `order_total`         DECIMAL(19, 2) DEFAULT NULL,
  `shipping_date`       DATETIME       DEFAULT NULL,
  `shipping_method`     VARCHAR(255)   DEFAULT NULL,
  `billing_address_id`  BIGINT (20) DEFAULT NULL,
  `payment_id`          BIGINT (20) DEFAULT NULL,
  `shipping_address_id` BIGINT (20) DEFAULT NULL,
  `user_id`             BIGINT (20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `user_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_payment` (
  `id`              BIGINT (20) NOT NULL AUTO_INCREMENT,
  `card_name`       VARCHAR(255) DEFAULT NULL,
  `card_number`     VARCHAR(255) DEFAULT NULL,
  `cvc`             INT(11) NOT NULL,
  `default_payment` BIT (1) NOT NULL,
  `expiry_month`    INT(11) NOT NULL,
  `expiry_year`     INT(11) NOT NULL,
  `holder_name`     VARCHAR(255) DEFAULT NULL,
  `type`            VARCHAR(255) DEFAULT NULL,
  `user_id`         BIGINT (20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_role_id` BIGINT (20) NOT NULL AUTO_INCREMENT,
  `role_id`      INT(11) DEFAULT NULL,
  `user_id`      BIGINT (20) DEFAULT NULL,
  PRIMARY KEY (`user_role_id`)
);

DROP TABLE IF EXISTS `user_shipping`;

CREATE TABLE `user_shipping` (
  `id`                     BIGINT (20) NOT NULL AUTO_INCREMENT,
  `user_shipping_city`     VARCHAR(255) DEFAULT NULL,
  `user_shipping_country`  VARCHAR(255) DEFAULT NULL,
  `user_shipping_default`  BIT (1) NOT NULL,
  `user_shipping_name`     VARCHAR(255) DEFAULT NULL,
  `user_shipping_state`    VARCHAR(255) DEFAULT NULL,
  `user_shipping_street1`  VARCHAR(255) DEFAULT NULL,
  `user_shipping_street2`  VARCHAR(255) DEFAULT NULL,
  `user_shipping_zip_code` VARCHAR(255) DEFAULT NULL,
  `user_id`                BIGINT (20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


ALTER TABLE billing_address
  DROP CONSTRAINT IF EXISTS FKn9o6nq40aqjyebaofkolmgv69;
ALTER TABLE billing_address
  ADD CONSTRAINT FKn9o6nq40aqjyebaofkolmgv69 FOREIGN KEY (order_id) REFERENCES user_id;


ALTER TABLE book_to_cart_item
  DROP CONSTRAINT IF EXISTS FKbdyqr108hc7c06xtem0dhv9mk;
ALTER TABLE book_to_cart_item
  ADD CONSTRAINT FKbdyqr108hc7c06xtem0dhv9mk FOREIGN KEY (cart_item_id) REFERENCES cart_item;


ALTER TABLE book_to_cart_item
  DROP CONSTRAINT IF EXISTS FK254kg9aacrs8uqa93ijc3garu;
ALTER TABLE book_to_cart_item
  ADD CONSTRAINT FK254kg9aacrs8uqa93ijc3garu FOREIGN KEY (book_id) REFERENCES book (id);

ALTER TABLE cart_item
  DROP CONSTRAINT IF EXISTS FKbfad2pd4ooykdqmav0oaynd1b;
ALTER TABLE cart_item
  ADD CONSTRAINT FKbfad2pd4ooykdqmav0oaynd1b FOREIGN KEY (order_id) REFERENCES user_id;

ALTER TABLE cart_item
  DROP CONSTRAINT IF EXISTS FKe89gjdx91fxnmkkssyoim8xfu;
ALTER TABLE cart_item
  ADD CONSTRAINT FKe89gjdx91fxnmkkssyoim8xfu FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart;

ALTER TABLE cart_item
  DROP CONSTRAINT IF EXISTS FKis5hg85qbs5d91etr4mvd4tx6;
ALTER TABLE cart_item
  ADD CONSTRAINT FKis5hg85qbs5d91etr4mvd4tx6 FOREIGN KEY (book_id) REFERENCES book;


ALTER TABLE password_reset_token
  DROP CONSTRAINT IF EXISTS FK5lwtbncug84d4ero33v3cfxvl;
ALTER TABLE password_reset_token
  ADD CONSTRAINT FK5lwtbncug84d4ero33v3cfxvl FOREIGN KEY (user_id) REFERENCES user;


ALTER TABLE payment
  DROP CONSTRAINT IF EXISTS FK919xn6cn5gio3mf7a7imk0aep;
ALTER TABLE payment
  ADD CONSTRAINT FK919xn6cn5gio3mf7a7imk0aep FOREIGN KEY (order_id) REFERENCES user_id;


ALTER TABLE shipping_address
  DROP CONSTRAINT IF EXISTS FK13e0im0d0g0etv74kpk1lhks6;
ALTER TABLE shipping_address
  ADD CONSTRAINT FK13e0im0d0g0etv74kpk1lhks6 FOREIGN KEY (order_id) REFERENCES user_id;

ALTER TABLE shopping_cart
  DROP CONSTRAINT IF EXISTS FK254qp5akhuaaj9n5co4jww3fk;
ALTER TABLE shopping_cart
  ADD CONSTRAINT FK254qp5akhuaaj9n5co4jww3fk FOREIGN KEY (user_id) REFERENCES user (id);


ALTER TABLE user_billing
  DROP CONSTRAINT IF EXISTS FK3v6hd7snyc3g9s72u41k1fydu;
ALTER TABLE user_billing
  ADD CONSTRAINT FK3v6hd7snyc3g9s72u41k1fydu FOREIGN KEY (user_payment_id) REFERENCES user_payment;


ALTER TABLE user_id
  DROP CONSTRAINT IF EXISTS FK7stlup30t5kexwbo4tlss2k4x;
ALTER TABLE user_id
  ADD CONSTRAINT FK7stlup30t5kexwbo4tlss2k4x FOREIGN KEY (shipping_address_id) REFERENCES shipping_address;


ALTER TABLE user_id
  DROP CONSTRAINT IF EXISTS FKj1qsusvrhf359t8cgg9fm5r8o;
ALTER TABLE user_id
  ADD CONSTRAINT FKj1qsusvrhf359t8cgg9fm5r8o FOREIGN KEY (user_id) REFERENCES user;

ALTER TABLE user_id
  DROP CONSTRAINT IF EXISTS FKlvpbjicc57x0f2j2d1o0tj9e5;
ALTER TABLE user_id
  ADD CONSTRAINT FKlvpbjicc57x0f2j2d1o0tj9e5 FOREIGN KEY (billing_address_id) REFERENCES billing_address;

ALTER TABLE user_id
  DROP CONSTRAINT IF EXISTS FKqb8pss8wf8dop8m3fdwl1rot5;
ALTER TABLE user_id
  ADD CONSTRAINT FKqb8pss8wf8dop8m3fdwl1rot5 FOREIGN KEY (payment_id) REFERENCES payment;


ALTER TABLE user_payment
  DROP CONSTRAINT IF EXISTS FK8fb9fr82lb1qk2cw55ito9rk6;
ALTER TABLE user_payment
  ADD CONSTRAINT FK8fb9fr82lb1qk2cw55ito9rk6 FOREIGN KEY (user_id) REFERENCES user;


ALTER TABLE user_role
  DROP CONSTRAINT IF EXISTS FK859n2jvi8ivhui0rl0esws6o;
ALTER TABLE user_role
  ADD CONSTRAINT FK859n2jvi8ivhui0rl0esws6o FOREIGN KEY (user_id) REFERENCES user;

ALTER TABLE user_role
  DROP CONSTRAINT IF EXISTS FKa68196081fvovjhkek5m97n3y;
ALTER TABLE user_role
  ADD CONSTRAINT FKa68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES role (roleid);


ALTER TABLE user_shipping
  DROP CONSTRAINT IF EXISTS FK9hidca5hndj9y0b5jb0xtpn9u;
ALTER TABLE user_shipping
  ADD CONSTRAINT FK9hidca5hndj9y0b5jb0xtpn9u FOREIGN KEY (user_id) REFERENCES user;

