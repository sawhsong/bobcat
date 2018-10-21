/**
 * Source -> src, webapp, message
 */

/**
 * Category    : SYS
 * Table ID    : SYS_EXPENSE_TYPE
 * Table Name  : Expenditure Entry Type
 * Description : 
 */
drop table sys_expense_type cascade constraints;
purge recyclebin;

create table sys_expense_type (
    expense_type_id                 varchar2(30)                                        not null,   -- Expense type UID (PK)
    org_category                    varchar2(30)                                        not null,   -- Organisation Category (sys_common_code.org_category)
    expense_main_type               varchar2(30)                                        not null,   -- Expense Main Type (sys_common_code.expense_main_type)
    expense_sub_type                varchar2(30),                                                   -- Expense Sub Type (sys_common_code.expense_sub_type)
    is_apply_gst                    varchar2(30)                                        not null,   -- Is Apply GST (sys_common_code.simple_yn)
    gst_percentage                  number(12, 0),                                                  -- GST Percentage
    account_code                    varchar2(30),                                                   -- Account Code
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_expense_type primary key(expense_type_id),
    constraint uk_sys_expense_type unique(org_category, expense_main_type, expense_sub_type)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_expense_type                       is 'Income Entry Type';
comment on column sys_expense_type.expense_type_id       is 'Expense type UID (PK)';
comment on column sys_expense_type.org_category          is 'Organisation Category (sys_common_code.org_category)';
comment on column sys_expense_type.expense_main_type     is 'Expense Main Type (sys_common_code.expense_main_type)';
comment on column sys_expense_type.expense_sub_type      is 'Expense Sub Type (sys_common_code.expense_sub_type)';
comment on column sys_expense_type.is_apply_gst          is 'Is Apply GST (sys_common_code.simple_yn)';
comment on column sys_expense_type.gst_percentage        is 'GST Percentage';
comment on column sys_expense_type.account_code          is 'Account Code';
comment on column sys_expense_type.insert_user_id        is 'Insert User UID';
comment on column sys_expense_type.insert_date           is 'Insert Date';
comment on column sys_expense_type.update_user_id        is 'Update User UID';
comment on column sys_expense_type.update_date           is 'Update Date';