/**
 * Table Name  : SYS_LENDING_TYPE
 * Description : Lending Entry Type
 */
drop table sys_lending_type cascade constraints;
purge recyclebin;

create table sys_lending_type (
    lending_type_id                 varchar2(30)                                        not null,   -- Lending Type UID (PK)
    org_category                    varchar2(30)                                        not null,   -- Organisation Category (UK) (sys_common_code.org_category)
    lending_type                    varchar2(30)                                        not null,   -- Lending Type (UK) (sys_common_code.lending_main_type or sys_common_code.lending_sub_type)
    parent_lending_type             varchar2(30),                                                   -- Parent Lending Type (always sys_common_code.lending_main_type)
    description                     varchar2(100)                                       not null,   -- Lending Type Description (comes from sys_common_code.lending_main_type.description_en, user can change)
    is_apply_gst                    varchar2(30),                                                   -- Is Apply GST (sys_common_code.simple_yn)
    gst_percentage                  number(12,0),                                                   -- GST Percentage
    account_code                    varchar2(30),                                                   -- Account Code
    sort_order                      varchar2(30),                                                   -- Sort Order
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_lending_type primary key(lending_type_id),
    constraint uk_sys_lending_type unique(org_category, lending_type)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_lending_type                       is 'Lending Entry Type';
comment on column sys_lending_type.lending_type_id       is 'Lending Type UID (PK)';
comment on column sys_lending_type.org_category          is 'Organisation Category (UK) (sys_common_code.org_category)';
comment on column sys_lending_type.lending_type          is 'Lending Type (UK) (sys_common_code.lending_main_type or sys_common_code.lending_sub_type)';
comment on column sys_lending_type.parent_lending_type   is 'Parent lending Type (always sys_common_code.lending_main_type)';
comment on column sys_lending_type.description           is 'Lending Type Description (comes from sys_common_code.lending_main_type.description_en, user can change)';
comment on column sys_lending_type.is_apply_gst          is 'Is Apply GST (sys_common_code.simple_yn)';
comment on column sys_lending_type.gst_percentage        is 'GST Percentage';
comment on column sys_lending_type.account_code          is 'Account Code';
comment on column sys_lending_type.sort_order            is 'Sort Order';
comment on column sys_lending_type.insert_user_id        is 'Insert User UID';
comment on column sys_lending_type.insert_date           is 'Insert Date';
comment on column sys_lending_type.update_user_id        is 'Update User UID';
comment on column sys_lending_type.update_date           is 'Update Date';


/**
 * Table Name  : SYS_LENDING_TYPE
 * Data        : 
 */
