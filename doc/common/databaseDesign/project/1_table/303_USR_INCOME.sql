/**
 * Table Name  : USR_INCOME
 * Description : Income Entry - Sales Income and Other Income for Org Category A, Other Income for Org Category B/C/D
 */
drop table usr_income cascade constraints;
purge recyclebin;

create table usr_income (
    income_id                       varchar2(30)                                        not null,   -- Income UID (PK)
    income_year                     varchar2(4)                                         not null,   -- Year (ex. 2017)
    quarter_name                    varchar2(30)                                        not null,   -- Quarter Name (sys_common_code.quarter_name)
    org_id                          varchar2(30)                                        not null,   -- Organisation UID (sys_org.org_id)
    income_entry_type               varchar2(30)                                        not null,   -- Income Data Entry Type (sys_common_code.income_entry_type)
    record_keeping_type             varchar2(30),                                                   -- Record Keeping Type (sys_common_code.record_keeping_type)
    income_type_id                  varchar2(30),                                                   -- Income Type UID (sys_income_type.income_type_id, for Org Category B/C/D)
    income_date                     date                                                not null,   -- Date of Income data entry (can be set the last date of the quarter if user does not input)
    non_cash_amt                    number(12,2),                                                   -- Amount of non-cash(Card, Cheque, Deposit etc)
    cash_amt                        number(12,2),                                                   -- Amount of cash
    gross_amt                       number(12,2),                                                   -- Amount of gross (non_cash_amt + cash_amt)
    gst_free_amt                    number(12,2),                                                   -- GST Free amount
    gst_amt                         number(12,2),                                                   -- GST amount (calculate besed on applied_gst but can be changed by user)
    applied_gst                     number(12,2),                                                   -- Applied GST percentage (coming from sys_income_type.gst_percentage, gst_amt can be changed by user)
    net_amt                         number(12,2),                                                   -- Net Sales Amt ((non_cash_amt + cash_amt) - gst_amt + gst_free_amt)
    is_completed                    varchar2(30)                                        not null,   -- Is Completed (sys_common_code.simple_yn, user can not update if completed)
    description                     varchar2(1000),                                                 -- Description (Particular)
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_usr_income primary key(income_id)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  usr_income                     is 'Income Entry (Sales Income and Other Income for Org Category A, Other Income for Org Category B/C/D)';
comment on column usr_income.income_id           is 'Income UID (PK)';
comment on column usr_income.income_year         is 'Year (ex. 2017)';
comment on column usr_income.quarter_name        is 'Quarter Name (sys_common_code.quarter_name)';
comment on column usr_income.org_id              is 'Organisation UID (sys_org.org_id)';
comment on column usr_income.income_entry_type   is 'Income Data Entry Type (sys_common_code.income_entry_type)';
comment on column usr_income.record_keeping_type is 'Record Keeping Type (sys_common_code.record_keeping_type)';
comment on column usr_income.income_type_id      is 'Income Type UID (sys_income_type.income_type_id)';
comment on column usr_income.income_date         is 'Date of Income data entry (can be set the last date of the quarter if user does not input)';
comment on column usr_income.non_cash_amt        is 'Amount of non-cash(Card, Cheque, Deposit etc)';
comment on column usr_income.cash_amt            is 'Amount of cash';
comment on column usr_income.gross_amt           is 'Amount of gross (non_cash_amt + cash_amt)';
comment on column usr_income.gst_free_amt        is 'GST Free amount';
comment on column usr_income.gst_amt             is 'GST amount (calculate besed on applied_gst but can be changed by user)';
comment on column usr_income.applied_gst         is 'Applied GST percentage (coming from sys_income_type.gst_percentage, gst_amt can be changed by user)';
comment on column usr_income.net_amt             is 'Net Sales Amt ((non_cash_amt + cash_amt) - gst_amt + gst_free_amt)';
comment on column usr_income.is_completed        is 'Is Completed (sys_common_code.simple_yn, user can not update if completed)';
comment on column usr_income.description         is 'Description (Particular)';
comment on column usr_income.insert_user_id      is 'Insert User UID';
comment on column usr_income.insert_date         is 'Insert Date';
comment on column usr_income.update_user_id      is 'Update User UID';
comment on column usr_income.update_date         is 'Update Date';


/**
 * Table Name  : USR_INCOME
 * Data        : 
 */
