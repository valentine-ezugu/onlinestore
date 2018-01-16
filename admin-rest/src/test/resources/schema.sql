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
DROP TABLE IF EXISTS book;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE book (
  id               BIGINT (20) NOT NULL AUTO_INCREMENT,
  active           BIT (1) NOT NULL,
  author           VARCHAR(255) DEFAULT NULL,
  category         VARCHAR(255) DEFAULT NULL,
  description      TEXT,
  format           VARCHAR(255) DEFAULT NULL,
  in_stock_number  INT(11) NOT NULL,
  isbn             INT(11) NOT NULL,
  language         VARCHAR(255) DEFAULT NULL,
  list_price       DOUBLE  NOT NULL,
  number_of_pages  VARCHAR(255) DEFAULT NULL,
  our_price        DOUBLE  NOT NULL,
  publication_date VARCHAR(255) DEFAULT NULL,
  publisher        VARCHAR(255) DEFAULT NULL,
  shipping_weight  DOUBLE  NOT NULL,
  title            VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE =InnoDB DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS book_to_cart_item;

CREATE TABLE book_to_cart_item (
  id           BIGINT (20) NOT NULL AUTO_INCREMENT,
  book_id      BIGINT (20) DEFAULT NULL,
  cart_item_id BIGINT (20) DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS cart_item;

DROP TABLE IF EXISTS cart_item;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE cart_item (
  id               BIGINT (20) NOT NULL AUTO_INCREMENT,
  qty              INT(11) NOT NULL,
  sub_total        DECIMAL(19, 2) DEFAULT NULL,
  book_id          BIGINT (20) DEFAULT NULL,
  order_id         BIGINT (20) DEFAULT NULL,
  shopping_cart_id BIGINT (20) DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS password_reset_token;

CREATE TABLE password_reset_token (
  id          BIGINT (20) NOT NULL AUTO_INCREMENT,
  expiry_date DATETIME     DEFAULT NULL,
  token       VARCHAR(255) DEFAULT NULL,
  user_id     BIGINT (20) NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS payment;

CREATE TABLE payment (
  id           BIGINT (20) NOT NULL AUTO_INCREMENT,
  card_name    VARCHAR(255) DEFAULT NULL,
  card_number  VARCHAR(255) DEFAULT NULL,
  cvc          INT(11) NOT NULL,
  expiry_month INT(11) NOT NULL,
  expiry_year  INT(11) NOT NULL,
  holder_name  VARCHAR(255) DEFAULT NULL,
  type         VARCHAR(255) DEFAULT NULL,
  order_id     BIGINT (20) DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS role;

CREATE TABLE role (
  roleid  INT(11) NOT NULL,
  name    VARCHAR(255) DEFAULT NULL,
  role_id INT(11) NOT NULL,
  PRIMARY KEY (roleid)
);
DROP TABLE IF EXISTS shipping_address;

CREATE TABLE shipping_address (
  id                        BIGINT (20) NOT NULL AUTO_INCREMENT,
  shipping_address_city     VARCHAR(255) DEFAULT NULL,
  shipping_address_country  VARCHAR(255) DEFAULT NULL,
  shipping_address_name     VARCHAR(255) DEFAULT NULL,
  shipping_address_state    VARCHAR(255) DEFAULT NULL,
  shipping_address_street1  VARCHAR(255) DEFAULT NULL,
  shipping_address_street2  VARCHAR(255) DEFAULT NULL,
  shipping_address_zip_code VARCHAR(255) DEFAULT NULL,
  order_id                  BIGINT (20) DEFAULT NULL,
  PRIMARY KEY (id)
);
DROP TABLE IF EXISTS shopping_cart;

CREATE TABLE shopping_cart (
  id          BIGINT (20) NOT NULL AUTO_INCREMENT,
  grand_total DECIMAL(19, 2) DEFAULT NULL,
  user_id     BIGINT (20) DEFAULT NULL,
  PRIMARY KEY (id)
);


DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id        BIGINT (20) NOT NULL AUTO_INCREMENT,
  email     VARCHAR(255) NOT NULL,
  enabled   BIT (1) NOT NULL,
  firstname VARCHAR(255) DEFAULT NULL,
  lastname  VARCHAR(255) DEFAULT NULL,
  password  VARCHAR(255) DEFAULT NULL,
  phone     VARCHAR(255) DEFAULT NULL,
  username  VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id)
);


DROP TABLE IF EXISTS user_billing;
CREATE TABLE user_billing (
  id                    BIGINT (20) NOT NULL AUTO_INCREMENT,
  user_billing_city     VARCHAR(255) DEFAULT NULL,
  user_billing_country  VARCHAR(255) DEFAULT NULL,
  user_billing_name     VARCHAR(255) DEFAULT NULL,
  user_billing_state    VARCHAR(255) DEFAULT NULL,
  user_billing_street1  VARCHAR(255) DEFAULT NULL,
  user_billing_street2  VARCHAR(255) DEFAULT NULL,
  user_billing_zip_code VARCHAR(255) DEFAULT NULL,
  user_payment_id       BIGINT (20) DEFAULT NULL,
  PRIMARY KEY (id)
);


DROP TABLE IF EXISTS user_id;

CREATE TABLE user_id (
  id                  BIGINT (20) NOT NULL AUTO_INCREMENT,
  order_date          DATETIME       DEFAULT NULL,
  order_status        VARCHAR(255)   DEFAULT NULL,
  order_total         DECIMAL(19, 2) DEFAULT NULL,
  shipping_date       DATETIME       DEFAULT NULL,
  shipping_method     VARCHAR(255)   DEFAULT NULL,
  billing_address_id  BIGINT (20) DEFAULT NULL,
  payment_id          BIGINT (20) DEFAULT NULL,
  shipping_address_id BIGINT (20) DEFAULT NULL,
  user_id             BIGINT (20) DEFAULT NULL,
  PRIMARY KEY (id)
);


DROP TABLE IF EXISTS user_payment;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE user_payment (
  id              BIGINT (20) NOT NULL AUTO_INCREMENT,
  card_name       VARCHAR(255) DEFAULT NULL,
  card_number     VARCHAR(255) DEFAULT NULL,
  cvc             INT(11) NOT NULL,
  default_payment BIT (1) NOT NULL,
  expiry_month    INT(11) NOT NULL,
  expiry_year     INT(11) NOT NULL,
  holder_name     VARCHAR(255) DEFAULT NULL,
  type            VARCHAR(255) DEFAULT NULL,
  user_id         BIGINT (20) DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS user_role;

CREATE TABLE user_role (
  user_role_id BIGINT (20) NOT NULL AUTO_INCREMENT,
  role_id      INT(11) DEFAULT NULL,
  user_id      BIGINT (20) DEFAULT NULL,
  PRIMARY KEY (user_role_id)
);

DROP TABLE IF EXISTS user_shipping;

CREATE TABLE user_shipping (
  id                     BIGINT (20) NOT NULL AUTO_INCREMENT,
  user_shipping_city     VARCHAR(255) DEFAULT NULL,
  user_shipping_country  VARCHAR(255) DEFAULT NULL,
  user_shipping_default  BIT (1) NOT NULL,
  user_shipping_name     VARCHAR(255) DEFAULT NULL,
  user_shipping_state    VARCHAR(255) DEFAULT NULL,
  user_shipping_street1  VARCHAR(255) DEFAULT NULL,
  user_shipping_street2  VARCHAR(255) DEFAULT NULL,
  user_shipping_zip_code VARCHAR(255) DEFAULT NULL,
  user_id                BIGINT (20) DEFAULT NULL,
  PRIMARY KEY (id)
);



