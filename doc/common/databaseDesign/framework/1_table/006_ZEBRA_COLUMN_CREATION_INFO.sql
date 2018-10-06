/**
 * Table Name  : ZEBRA_COLUMN_CREATION_INFO
 * Description : Column info to be created - For framework and Project
 * 	Table creation scripts is generated based on this table and ZEBRA_TABLE_CREATION_INFO table
 */
drop table zebra_column_creation_info cascade constraints;
purge recyclebin;

create table zebra_column_creation_info (
    table_name                      varchar2(30)                                        not null,   -- Table Name ([ZEBRA_TABLE_CREATION_INFO.TABLE_NAME])
    column_name                     varchar2(30)                                        not null,   -- Column Name
    data_type                       varchar2(30)                                        not null,   -- Column Data Type
    data_length                     number,                                                         -- Data Length
    default_value                   varchar2(100),                                                  -- Default Value
    nullable                        varchar2(1)                 default 'Y',                        -- Accept null : Y, Not Accept null : N
    key_type                        varchar2(3),                                                    -- Constraint Type (PK / UK / FK)
    fk_table_column                 varchar2(100),                                                  -- If Constraint is FK - Reference table.column
    description                     varchar2(1000)                                      not null,   -- Column comment - not null

    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint fk_zebra_column_creation foreign key(table_name) references zebra_table_creation_info(table_name),
    constraint pk_zebra_column_creation primary key(table_name, column_name)
    using index tablespace alpaca_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace alpaca_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table zebra_column_creation_info is 'Column info to be created';
comment on column zebra_column_creation_info.table_name      is 'Table Name ([ZEBRA_TABLE_CREATION_INFO.TABLE_NAME])';
comment on column zebra_column_creation_info.column_name     is 'Column Name';
comment on column zebra_column_creation_info.data_type       is 'Column Data Type';
comment on column zebra_column_creation_info.data_length     is 'Data Length';
comment on column zebra_column_creation_info.default_value   is 'Default Value';
comment on column zebra_column_creation_info.nullable        is 'Accept null : Y, Not Accept null : N';
comment on column zebra_column_creation_info.key_type        is 'Constraint Type (PK / UK / FK)';
comment on column zebra_column_creation_info.fk_table_column is 'If Constraint is FK - Reference table.column';
comment on column zebra_column_creation_info.description     is 'Column comment - not null';
comment on column zebra_common_code.insert_user_id           is 'Insert User UID';
comment on column zebra_common_code.insert_date              is 'Insert Date';
comment on column zebra_common_code.update_user_id           is 'Update User UID';
comment on column zebra_common_code.update_date              is 'Update Date';


/**
 * Table Name  : ZEBRA_COLUMN_CREATION_INFO
 * Description : Column info to be created - For framework and Project
 * 	Table creation scripts is generated based on this table and ZEBRA_TABLE_CREATION_INFO table
 */
