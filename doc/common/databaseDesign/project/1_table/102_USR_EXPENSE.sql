/**
 * Table Name  : USR_EXPENSE
 * Description : Expense Entry - Expenditure data entry for all organisation category
 */
drop table usr_expense cascade constraints;
purge recyclebin;

create table usr_expense (
    expense_id                      varchar2(30)                                        not null,   -- Expense UID (PK)
    expense_year                    varchar2(4)                                         not null,   -- Year (ex. 2017)
    quarter_name                    varchar2(30)                                        not null,   -- Quarter Name (sys_common_code.quarter_name)
    org_id                          varchar2(30)                                        not null,   -- Organisation UID (sys_org.org_id)
    expense_type_id                 varchar2(30)                                        not null,   -- Expense Type UID (sys_expense_type.expense_type_id)
    expense_date                    date                                                not null,   -- Date of Expense data entry (can be set the last date of the quarter if user does not input)
    gross_amt                       number(12,2),                                                   -- Amount of gross
    gst_amt                         number(12,2),                                                   -- GST amount (calculate besed on applied_gst but can be changed by user)
    applied_gst                     number(12,2),                                                   -- Applied GST percentage (coming from sys_expense_type.gst_percentage, gst_amt can be changed by user)
    net_amt                         number(12,2),                                                   -- Net Expenditure Amt (gross_amt - gst_amt)
    is_completed                    varchar2(30)                                        not null,   -- Is Completed (sys_common_code.simple_yn, user can not update if completed)
    description                     varchar2(1000),                                                 -- Description (Particular)
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_usr_expense primary key(expense_id)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  usr_expense                     is 'Expense Entry (Expenditure data entry for all organisation category)';
comment on column usr_expense.expense_id          is 'Expense UID (PK)';
comment on column usr_expense.expense_year        is 'Year (ex. 2017)';
comment on column usr_expense.quarter_name        is 'Quarter Name (sys_common_code.quarter_name)';
comment on column usr_expense.org_id              is 'Organisation UID (sys_org.org_id)';
comment on column usr_expense.expense_type_id     is 'Expense Type UID (sys_expense_type.expense_type_id)';
comment on column usr_expense.expense_date        is 'Date of Expense data entry (can be set the last date of the quarter if user does not input)';
comment on column usr_expense.gross_amt           is 'Amount of gross';
comment on column usr_expense.gst_amt             is 'GST amount (calculate besed on applied_gst but can be changed by user)';
comment on column usr_expense.applied_gst         is 'Applied GST percentage (coming from sys_expense_type.gst_percentage, gst_amt can be changed by user)';
comment on column usr_expense.net_amt             is 'Net Expenditure Amt (gross_amt - gst_amt)';
comment on column usr_expense.is_completed        is 'Is Completed (sys_common_code.simple_yn, user can not update if completed)';
comment on column usr_expense.description         is 'Description (Particular)';
comment on column usr_expense.insert_user_id      is 'Insert User UID';
comment on column usr_expense.insert_date         is 'Insert Date';
comment on column usr_expense.update_user_id      is 'Update User UID';
comment on column usr_expense.update_date         is 'Update Date';


/**
 * Table Name  : USR_EXPENSE
 * Data        : 
 */
