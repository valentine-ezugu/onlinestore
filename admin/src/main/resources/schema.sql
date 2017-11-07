
DROP TABLE IF EXISTS billing_address;

CREATE TABLE billing_address (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  billingAddressCity varchar(255),
  billingAddressCountry varchar(255),
  billingAddressName varchar(255),
  billingAddressState varchar(255),
  billingAddressStreet1 varchar(255),
  billingAddressStreet2 varchar(255),
  billingAddressZipCode varchar(255),
  order_id bigint  (20),
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS Book;
DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `description` text,
  `format` varchar(255) DEFAULT NULL,
  `in_stock_number` int(11) NOT NULL,
  `isbn` int(11) NOT NULL,
  `language` varchar(255) DEFAULT NULL,
  `list_price` double NOT NULL,
  `number_of_pages` varchar(255) DEFAULT NULL,
  `our_price` double NOT NULL,
  `publication_date` varchar(255) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `shipping_weight` double NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `book_to_cart_item`;

CREATE TABLE `book_to_cart_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `book_id` bigint(20) DEFAULT NULL,
  `cart_item_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS cart_item;

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `qty` int(11) NOT NULL,
  `sub_total` decimal(19,2) DEFAULT NULL,
  `book_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `shopping_cart_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `password_reset_token`;

CREATE TABLE `password_reset_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `expiry_date` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_name` varchar(255) DEFAULT NULL,
  `card_number` varchar(255) DEFAULT NULL,
  `cvc` int(11) NOT NULL,
  `expiry_month` int(11) NOT NULL,
  `expiry_year` int(11) NOT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `roleid` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`roleid`)
);
DROP TABLE IF EXISTS `shipping_address`;

CREATE TABLE `shipping_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `shipping_address_city` varchar(255) DEFAULT NULL,
  `shipping_address_country` varchar(255) DEFAULT NULL,
  `shipping_address_name` varchar(255) DEFAULT NULL,
  `shipping_address_state` varchar(255) DEFAULT NULL,
  `shipping_address_street1` varchar(255) DEFAULT NULL,
  `shipping_address_street2` varchar(255) DEFAULT NULL,
  `shipping_address_zip_code` varchar(255) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;
  DROP TABLE IF EXISTS `shopping_cart`;

 CREATE TABLE `shopping_cart` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `grand_total` decimal(19,2) DEFAULT NULL,
    `user_id` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
 );




DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
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
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_name` varchar(255) DEFAULT NULL,
  `card_number` varchar(255) DEFAULT NULL,
  `cvc` int(11) NOT NULL,
  `default_payment` bit(1) NOT NULL,
  `expiry_month` int(11) NOT NULL,
  `expiry_year` int(11) NOT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_role_id`)
);

DROP TABLE IF EXISTS `user_shipping`;

CREATE TABLE `user_shipping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_shipping_city` varchar(255) DEFAULT NULL,
  `user_shipping_country` varchar(255) DEFAULT NULL,
  `user_shipping_default` bit(1) NOT NULL,
  `user_shipping_name` varchar(255) DEFAULT NULL,
  `user_shipping_state` varchar(255) DEFAULT NULL,
  `user_shipping_street1` varchar(255) DEFAULT NULL,
  `user_shipping_street2` varchar(255) DEFAULT NULL,
  `user_shipping_zip_code` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);



