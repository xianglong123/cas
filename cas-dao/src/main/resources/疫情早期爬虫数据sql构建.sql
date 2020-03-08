create table china_grippe_prov_info (
                                        uuid varchar(32) not null ,
                                        CONFIRMED_COUNT int(8) default 0,
                                        CURED_COUNT int(8) default 0,
                                        PROVINCE_SHORT_NAME varchar(8),
                                        COMMENT varchar(512),
                                        provinceName varchar(8),
                                        DEAD_COUNT varchar(8)default 0,
                                        SUSPECTED_COUNT int(8) default 0
);

alter table china_grippe_prov_info comment '新型流感数据统计表';
alter table china_grippe_prov_info modify column uuid int comment '主键';
alter table china_grippe_prov_info modify column CONFIRMED_COUNT int comment '确诊人数';
alter table china_grippe_prov_info modify column CURED_COUNT int comment '治愈人数';
alter table china_grippe_prov_info modify column PROVINCE_SHORT_NAME int comment '省简称';
alter table china_grippe_prov_info modify column COMMENT int comment '备注';
alter table china_grippe_prov_info modify column provinceName int comment '省全称';
alter table china_grippe_prov_info modify column DEAD_COUNT int comment '死亡人数';

insert china_grippe_prov_info values
(replace(uuid(), '-', ''),1,1,'未知','oooo','',0,0,sysdate(),sysdate());


-- 分割线


create table china_grippe_city_info (
                                        uuid varchar(32) not null ,
                                        CONFIRMED_COUNT int(8) default 0,
                                        CURED_COUNT int(8) default 0,
                                        CITY_NAME varchar(8),
                                        DEAD_COUNT varchar(8)default 0,
                                        SUSPECTED_COUNT varchar(8) default 0
);

alter table china_grippe_city_info comment '新型流感数据市统计表';
alter table china_grippe_city_info modify column uuid int comment '主键';
alter table china_grippe_city_info modify column CONFIRMED_COUNT int comment '确诊人数';
alter table china_grippe_city_info modify column CURED_COUNT int comment '治愈人数';
alter table china_grippe_city_info modify column CITY_NAME int comment '市全称';
alter table china_grippe_city_info modify column DEAD_COUNT int comment '死亡人数';

ALTER TABLE china_grippe_city_info
    ADD COLUMN PROVINCE_NAME varchar(8) DEFAULT '' comment '省全称' ;
