/**
 * Table Name  : SYS_INCOME_TYPE
 * Description : Income Entry Type
 */
drop table sys_income_type cascade constraints;
purge recyclebin;

create table sys_income_type (
    income_type_id                  varchar2(30)                                        not null,   -- Income type UID (PK)
    org_category                    varchar2(30)                                        not null,   -- Organisation Category (UK) (sys_common_code.org_category)
    income_type                     varchar2(30)                                        not null,   -- Income Type (UK) (sys_common_code.income_main_type or sys_common_code.income_sub_type)
    parent_income_type              varchar2(30),                                                   -- Parent Income Type (always sys_common_code.income_main_type)
    description                     varchar2(100)                                       not null,   -- Income Type Description (comes from sys_common_code.income_type.description_en, user can change)
    is_apply_gst                    varchar2(30),                                                   -- Is Apply GST (sys_common_code.simple_yn)
    gst_percentage                  number(12,0),                                                   -- GST Percentage
    account_code                    varchar2(30),                                                   -- Account Code
    sort_order                      varchar2(30),                                                   -- Sort Order
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_income_type primary key(income_type_id),
    constraint uk_sys_income_type unique(org_category, income_type)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_income_type                       is 'Income Entry Type';
comment on column sys_income_type.income_type_id        is 'Income type UID (PK)';
comment on column sys_income_type.org_category          is 'Organisation Category (UK) (sys_common_code.org_category)';
comment on column sys_income_type.income_type           is 'Income Type (UK) (sys_common_code.income_main_type or sys_common_code.income_sub_type)';
comment on column sys_income_type.parent_income_type    is 'Parent Income Type (always sys_common_code.income_main_type)';
comment on column sys_income_type.description           is 'Income Type Description (comes from sys_common_code.income_type.description_en, user can change)';
comment on column sys_income_type.is_apply_gst          is 'Is Apply GST (sys_common_code.simple_yn)';
comment on column sys_income_type.gst_percentage        is 'GST Percentage';
comment on column sys_income_type.account_code          is 'Account Code';
comment on column sys_income_type.sort_order            is 'Sort Order';
comment on column sys_income_type.insert_user_id        is 'Insert User UID';
comment on column sys_income_type.insert_date           is 'Insert Date';
comment on column sys_income_type.update_user_id        is 'Update User UID';
comment on column sys_income_type.update_date           is 'Update Date';


/**
 * Table Name  : SYS_INCOME_TYPE
 * Data        : 
 */
