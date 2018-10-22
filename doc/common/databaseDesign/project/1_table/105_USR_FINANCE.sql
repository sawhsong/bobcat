/**
 * Table Name  : USR_FINANCE
 * Description : Financial Loan Related Data Entry - Repayment / Borrowing / Lending
 */
drop table usr_finance cascade constraints;
purge recyclebin;

create table usr_finance (
    finance_id                      varchar2(30)                                        not null,   -- Finance Entry UID (PK)
    finance_year                    varchar2(4)                                         not null,   -- Year (ex. 2017)
    quarter_name                    varchar2(30)                                        not null,   -- Quarter Name (sys_common_code.quarter_name)
    org_id                          varchar2(30)                                        not null,   -- Organisation UID (sys_org.org_id)
    finance_entry_type              varchar2(30)                                        not null,   -- Finance Entry Type Code (sys_common_code.finance_entry_type)
    finance_type_id                 varchar2(30)                                        not null,   -- Type UID by Finance Entry Type (sys_repayment_type.repayment_type_id or sys_borrowing_type.borrowing_type_id or sys_lending_type.lending_type_id)
    finance_date                    date                                                not null,   -- Date of Repayment/Borrowing/Lending (can be set the last date of the quarter if user does not input)
    repayment_amt                   number(12,2),                                                   -- Repayment Amount (only for repayment_type)
    principal_amt                   number(12,2),                                                   -- Principal Amount (for borrowing and lending types)
    interest_percentage             number(12,2),                                                   -- Interest Rate Percentage (%) (for borrowing and lending types)
    start_date                      date,                                                           -- Start date of borrowing/lending
    end_date                        date,                                                           -- End date of borrowing/lending
    is_completed                    varchar2(30)                                        not null,   -- Is Completed (sys_common_code.simple_yn, user can not update if completed)
    description                     varchar2(1000),                                                 -- Description (Particular)
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_usr_finance primary key(finance_id)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  usr_finance                     is 'Financial Loan Related Data Entry (Repayment / Borrowing / Lending)';
comment on column usr_finance.finance_id          is 'Finance Entry UID (PK)';
comment on column usr_finance.finance_year        is 'Year (ex. 2017)';
comment on column usr_finance.quarter_name        is 'Quarter Name (sys_common_code.quarter_name)';
comment on column usr_finance.org_id              is 'Organisation UID (sys_org.org_id)';
comment on column usr_finance.finance_entry_type  is 'Finance Entry Type Code (sys_common_code.finance_entry_type)';
comment on column usr_finance.finance_type_id     is 'Type UID by Finance Entry Type (sys_repayment_type.repayment_type_id or sys_borrowing_type.borrowing_type_id or sys_lending_type.lending_type_id)';
comment on column usr_finance.finance_date        is 'Date of Repayment/Borrowing/Lending (can be set the last date of the quarter if user does not input)';
comment on column usr_finance.repayment_amt       is 'Repayment Amount (only for repayment_type)';
comment on column usr_finance.principal_amt       is 'Principal Amount (for borrowing and lending types)';
comment on column usr_finance.interest_percentage is 'Interest Rate Percentage (%) (for borrowing and lending types)';
comment on column usr_finance.start_date          is 'Start date of borrowing/lending';
comment on column usr_finance.end_date            is 'End date of borrowing/lending';
comment on column usr_finance.is_completed        is 'Is Completed (sys_common_code.simple_yn, user can not update if completed)';
comment on column usr_finance.description         is 'Description (Particular)';
comment on column usr_finance.insert_user_id      is 'Insert User UID';
comment on column usr_finance.insert_date         is 'Insert Date';
comment on column usr_finance.update_user_id      is 'Update User UID';
comment on column usr_finance.update_date         is 'Update Date';


/**
 * Table Name  : USR_FINANCE
 * Data        : 
 */
