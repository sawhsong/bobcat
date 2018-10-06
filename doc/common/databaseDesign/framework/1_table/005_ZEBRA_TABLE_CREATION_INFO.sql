/**
 * Table Name  : ZEBRA_TABLE_CREATION_INFO
 * Description : Table info to be created - For framework and Project
 * 	Table creation scripts is generated based on this table and ZEBRA_COLUMN_CREATION_INFO table
 */
drop table zebra_table_creation_info cascade constraints;
purge recyclebin;

create table zebra_table_creation_info (
    table_name                      varchar2(30)                                        not null,   -- Table Name
    description                     varchar2(1000),                                                 -- Table description
    sql_script                      clob                        default empty_clob(),               -- SQL Script for creation
    file_path_name                  varchar2(1000),                                                 -- File path and name for SQL Script saved
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_zebra_table_creation primary key(table_name)
    using index tablespace alpaca_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace alpaca_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table zebra_table_creation_info is 'Table info to be created';
comment on column zebra_table_creation_info.table_name      is 'Table Name';
comment on column zebra_table_creation_info.description     is 'Table description';
comment on column zebra_table_creation_info.sql_script      is 'SQL Script for creation';
comment on column zebra_table_creation_info.file_path_name  is 'File path and name for SQL Script saved';
comment on column zebra_table_creation_info.insert_user_id  is 'Insert User UID';
comment on column zebra_table_creation_info.insert_date     is 'Insert Date';
comment on column zebra_table_creation_info.update_user_id  is 'Update User UID';
comment on column zebra_table_creation_info.update_date     is 'Update Date';


/**
 * Table Name  : ZEBRA_TABLE_CREATION_INFO
 * Description : Table info to be created - For framework and Project
 * 	Table creation scripts is generated based on this table and ZEBRA_COLUMN_CREATION_INFO table
 */
