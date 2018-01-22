 drop table if exists billing_address;
 create table billing_address (id bigint not null auto_increment, billing_address_city varchar(255), billing_address_country varchar(255), billing_address_name varchar(255), billing_address_state varchar(255), billing_address_street1 varchar(255), billing_address_street2 varchar(255), billing_address_zip_code varchar(255), order_id bigint, primary key (id));

 drop table if exists book;
 create table book (id bigint not null auto_increment, active bit not null, author varchar(255), category varchar(255), description text, format varchar(255), in_stock_number integer not null, isbn integer not null, language varchar(255), list_price double precision not null, number_of_pages varchar(255), our_price double precision not null, publication_date varchar(255), publisher varchar(255), shipping_weight double precision not null, title varchar(255), primary key (id));

 drop table if exists book_to_cart_item;
 create table book_to_cart_item (id bigint not null auto_increment, book_id bigint, cart_item_id bigint, primary key (id));

 drop table if exists cart_item;
 create table cart_item (id bigint not null auto_increment, qty integer not null, sub_total decimal(19,2), book_id bigint, order_id bigint, shopping_cart_id bigint, primary key (id));

 drop table if exists password_reset_token;
 create table password_reset_token (id bigint not null auto_increment, expiry_date datetime, token varchar(255), user_id bigint not null, primary key (id));

 drop table if exists payment;
 create table payment (id bigint not null auto_increment, card_name varchar(255), card_number varchar(255), cvc integer not null, expiry_month integer not null, expiry_year integer not null, holder_name varchar(255), type varchar(255), order_id bigint, primary key (id));

 drop table if exists role;
 create table role (role_id bigint not null auto_increment, name varchar(255) not null, primary key (role_id));

 drop table if exists shipping_address;
 create table shipping_address (id bigint not null auto_increment, shipping_address_city varchar(255), shipping_address_country varchar(255), shipping_address_name varchar(255), shipping_address_state varchar(255), shipping_address_street1 varchar(255), shipping_address_street2 varchar(255), shipping_address_zip_code varchar(255), order_id bigint, primary key (id));

 drop table if exists shopping_cart;
 create table shopping_cart (id bigint not null auto_increment, grand_total decimal(19,2), user_id bigint, primary key (id));

 drop table if exists user;
 create table user (id bigint not null auto_increment, email varchar(255) not null, enabled bit not null, first_name varchar(255), last_name varchar(255), password varchar(255), phone varchar(255), username varchar(255), primary key (id));


 drop table if exists user_id;
 create table user_id (id bigint not null auto_increment, order_date datetime, order_status varchar(255), order_total decimal(19,2), shipping_date datetime, shipping_method varchar(255), billing_address_id bigint, payment_id bigint, shipping_address_id bigint, user_id bigint, primary key (id));

 drop table if exists user_role;
 create table user_role (user_id bigint not null, role_id bigint not null, primary key (user_id, role_id));


 drop table if exists user_billing;
 create table user_billing (id bigint not null auto_increment, user_billing_city varchar(255), user_billing_country varchar(255), user_billing_name varchar(255), user_billing_state varchar(255), user_billing_street1 varchar(255), user_billing_street2 varchar(255), user_billing_zip_code varchar(255), user_payment_id bigint, primary key (id));

 drop table if exists user_payment;
 create table user_payment (id bigint not null auto_increment, card_name varchar(255), card_number varchar(255), cvc integer not null, default_payment bit not null, expiry_month integer not null, expiry_year integer not null, holder_name varchar(255), type varchar(255), user_id bigint, primary key (id));

 drop table if exists user_shipping;
 create table user_shipping (id bigint not null auto_increment, user_shipping_city varchar(255), user_shipping_country varchar(255), user_shipping_default bit not null, user_shipping_name varchar(255), user_shipping_state varchar(255), user_shipping_street1 varchar(255), user_shipping_street2 varchar(255), user_shipping_zip_code varchar(255), user_id bigint, primary key (id));

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

  alter table billing_address add constraint FKn9o6nq40aqjyebaofkolmgv69 foreign key (order_id) references user_id (id);


  alter table book_to_cart_item add constraint FK254kg9aacrs8uqa93ijc3garu foreign key (book_id) references book (id);
  alter table book_to_cart_item add constraint FKbdyqr108hc7c06xtem0dhv9mk foreign key (cart_item_id) references cart_item (id);


  alter table cart_item add constraint FKis5hg85qbs5d91etr4mvd4tx6 foreign key (book_id) references book (id);
  alter table cart_item add constraint FKbfad2pd4ooykdqmav0oaynd1b foreign key (order_id) references user_id (id);
  alter table cart_item add constraint FKe89gjdx91fxnmkkssyoim8xfu foreign key (shopping_cart_id) references shopping_cart (id);


  alter table password_reset_token add constraint FK5lwtbncug84d4ero33v3cfxvl foreign key (user_id) references user (id);

  alter table payment add constraint FK919xn6cn5gio3mf7a7imk0aep foreign key (order_id) references user_id (id);

  alter table shipping_address add constraint FK13e0im0d0g0etv74kpk1lhks6 foreign key (order_id) references user_id (id);

  alter table shopping_cart add constraint FK254qp5akhuaaj9n5co4jww3fk foreign key (user_id) references user (id);


  alter table user_id add constraint FKlvpbjicc57x0f2j2d1o0tj9e5 foreign key (billing_address_id) references billing_address (id);
  alter table user_id add constraint FKqb8pss8wf8dop8m3fdwl1rot5 foreign key (payment_id) references payment (id);
  alter table user_id add constraint FK7stlup30t5kexwbo4tlss2k4x foreign key (shipping_address_id) references shipping_address (id);
  alter table user_id add constraint FKj1qsusvrhf359t8cgg9fm5r8o foreign key (user_id) references user (id);

 alter table user_role add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role (role_id);
  alter table user_role add constraint FK859n2jvi8ivhui0rl0esws6o foreign key (user_id) references user (id);


  alter table user_billing add constraint FK3v6hd7snyc3g9s72u41k1fydu foreign key (user_payment_id) references user_payment (id);

  alter table user_payment add constraint FK8fb9fr82lb1qk2cw55ito9rk6 foreign key (user_id) references user (id);

  alter table user_shipping add constraint FK9hidca5hndj9y0b5jb0xtpn9u foreign key (user_id) references user (id);