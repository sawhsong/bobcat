/** * Table Name  : MY_TEST_TABLE * Description : test table */drop table my_test_table cascade constraints;
purge recyclebin;

create table my_test_table (
                                    ()                  default                  not null,                                          ()                  default                  not null,                                          ()                  default                  not null,                                          ()                  default                  not null,                                          ()                  default                  not null,                                          ()                  default                  not null,      )
pctfree 20 pctused 80 tablespace alpaca_data alpaca_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  my_test_table                                                  is 'test table';
comment on column my_test_table.                                                 is '';
comment on column my_test_table.                                                 is '';
comment on column my_test_table.                                                 is '';
comment on column my_test_table.                                                 is '';
comment on column my_test_table.                                                 is '';
comment on column my_test_table.                                                 is '';
