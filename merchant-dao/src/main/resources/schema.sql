
/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/3/16 21:41:46                           */
/*==============================================================*/


drop table if exists burse;

drop table if exists cash_recode;

drop table if exists merchant_handle_recode;

drop table if exists merchant_info;

drop table if exists news;

drop table if exists trade;

drop table if exists wx_user;

/*==============================================================*/
/* Table: burse                                                 */
/*==============================================================*/
create table burse
(
   merchant_id          varchar(32) not null,
   total_top_up         decimal,
   balance              decimal,
   total_red_packet     decimal,
   primary key (merchant_id)
);


/*==============================================================*/
/* Table: cash_recode                                           */
/*==============================================================*/
create table cash_recode
(
   id                   varchar(32) not null,
   merchant_id          varchar(32),
   recode_type          varchar(32),
   money                decimal,
   msg                  varchar(1024),
   create_time          DATETIME,
   state                tinyint,
   union_id             varchar(128),
   open_id              varchar(128),
   wx_error_code        varchar(32),
   primary key (id)
);


/*==============================================================*/
/* Table: merchant_handle_recode                                */
/*==============================================================*/
create table merchant_handle_recode
(
   id                   varchar(32) not null,
   merchant_id          varchar(32),
   handle_type          varchar(32),
   msg                  varchar(32),
   balance              decimal,
   number               int,
   primary key (id)
);


/*==============================================================*/
/* Table: merchant_info                                         */
/*==============================================================*/
create table merchant_info
(
   id                   varchar(32) not null,
   merchant_name        varchar(32),
   password             varchar(255),
   phone_number         varchar(11),
   login_time           DATETIME,
   create_time          DATETIME,
   purchase_time        DATETIME,
   due_time             DATETIME,
   open_id              varchar(128),
   union_id             varchar(128),
   user_name            varchar(32),
   state                tinyint,
   pay_password         varchar(255),
   primary key (id)
);


/*==============================================================*/
/* Table: news                                                  */
/*==============================================================*/
create table news
(
   id                   varchar(32) not null,
   merchant_id          varchar(32),
   create_time          DATETIME,
   msg                  varchar(1024),
   news_type            tinyint,
   trade_id             varchar(32),
   primary key (id)
);


/*==============================================================*/
/* Table: trade                                                 */
/*==============================================================*/
create table trade
(
   id                   varchar(32) not null,
   merchant             varchar(32),
   balance              decimal,
   trade_state          tinyint,
   trade_type           varchar(32),
   user_id              varchar(32),
   user_name            varchar(32),
   create_time          DATETIME,
   wx_error_code        int,
   response_time        DATETIME,
   open_id              varchar(32),
   union_id             varchar(32),
   primary key (id)
);


/*==============================================================*/
/* Table: wx_user                                               */
/*==============================================================*/
create table wx_user
(
   id                   varchar(32) not null,
   user_name            varchar(32),
   attention_time       varchar(32),
   open_id              varchar(128),
   union_id             varchar(128),
   phone_number         varchar(11),
   state                tinyint,
   total_get_banlance   decimal,
   primary key (id)
);


