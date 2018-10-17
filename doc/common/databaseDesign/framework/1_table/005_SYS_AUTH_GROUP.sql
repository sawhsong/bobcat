/**
 * Table Name  : SYS_AUTH_GROUP
 * Description : Menu Authority Info
 */
drop table sys_auth_group cascade constraints;
purge recyclebin;

create table sys_auth_group (
    group_id                        varchar2(30)                                 not null,      -- Authority group UID (PK)
    group_name                      varchar2(50)                                 not null,      -- Authority group name
    description                     varchar2(1000),                                             -- Description
    is_active                       varchar2(1)         default y,                              -- Is active ?
    num_col_1                       number(12,2),                                               -- num 1
    num_col_2                       number,                                                     -- num 2
    fk_col                          varchar2(30)                                 not null,      -- fk col
    uk_col                          date                default sysdate                         -- uk col
                         not null,      -- uk col
    insert_user_id                  varchar2(30),                                               -- Insert User UID
    insert_date                     date                default sysdate,                        -- Insert Date
    update_user_id                  varchar2(30),                                               -- Update User UID
    update_date                     date,                                                       -- Update Date
constraint fk_15433062886369 foreign key(fk_col) references sys_menu(menu_id),
    constraint pk_sys_auth_group primary key(group_id),
    constraint uk_15433093409788 unique(uk_col)
    using index tablespace alpaca_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace alpaca_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_auth_group                                                 is 'Menu Authority Info';
comment on column sys_auth_group.group_id                                        is 'Authority group UID (PK)';
comment on column sys_auth_group.group_name                                      is 'Authority group name';
comment on column sys_auth_group.description                                     is 'Description';
comment on column sys_auth_group.is_active                                       is 'Is active ?';
comment on column sys_auth_group.num_col_1                                       is 'num 1';
comment on column sys_auth_group.num_col_2                                       is 'num 2';
comment on column sys_auth_group.fk_col                                          is 'fk col';
comment on column sys_auth_group.uk_col                                          is 'uk col';
comment on column sys_auth_group.INSERT_USER_ID                                  is 'Insert User UID';
comment on column sys_auth_group.INSERT_DATE                                     is 'Insert Date';
comment on column sys_auth_group.UPDATE_USER_ID                                  is 'Update User UID';
comment on column sys_auth_group.UPDATE_DATE                                     is 'Update Date';


/**
 * Table Name  : SYS_AUTH_GROUP
 * Data        : 
 */
