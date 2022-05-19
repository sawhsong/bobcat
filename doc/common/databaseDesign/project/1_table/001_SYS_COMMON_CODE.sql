/**
 * Table Name  : SYS_COMMON_CODE
 * Description : Common Lookup Code - Import initial data(SYS_COMMON_CODE.xlsx)
 */
drop table sys_common_code cascade constraints;
purge recyclebin;

create table sys_common_code (
    code_type                       varchar2(30)                                        not null,   -- Code type (PK)
    common_code                     varchar2(30)                                        not null,   -- Common code (PK)
    code_category                   varchar2(30)                                        not null,   -- Code usage category (sys_common_code.code_category)
    description_ko                  varchar2(1000),                                                 -- Code Description (Korean)
    description_en                  varchar2(1000),                                                 -- Code Description (English)
    program_constants               varchar2(100)                                       not null,   -- Constants value for the common code to be used in program source code
    sort_order                      varchar2(3),                                                    -- Sort Order
    is_active                       varchar2(1)                 default 'Y',                        -- Is active?
    is_default                      varchar2(1)                 default 'N',                        -- Is default code item? (default code item should not be deleted)
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_common_code primary key(code_type, common_code),
    constraint uk_sys_common_code unique(program_constants)
    using index tablespace bobcat_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace bobcat_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_common_code                   is 'Common Lookup Code';
comment on column sys_common_code.code_type         is 'Code type (PK)';
comment on column sys_common_code.common_code       is 'Common code (PK)';
comment on column sys_common_code.code_category     is 'Code usage category (sys_common_code.code_category)';
comment on column sys_common_code.description_ko    is 'Code Description (Korean)';
comment on column sys_common_code.description_en    is 'Code Description (English)';
comment on column sys_common_code.program_constants is 'Constants value for the common code to be used in program';
comment on column sys_common_code.sort_order        is 'Sort order';
comment on column sys_common_code.is_active         is 'Is active?';
comment on column sys_common_code.is_default        is 'Is default code item? (default code item should not be deleted)';
comment on column sys_common_code.insert_user_id    is 'Insert User UID';
comment on column sys_common_code.insert_date       is 'Insert Date';
comment on column sys_common_code.update_user_id    is 'Update User UID';
comment on column sys_common_code.update_date       is 'Update Date';


/**
 * Table Name  : SYS_COMMON_CODE
 * Data        : 
 */
