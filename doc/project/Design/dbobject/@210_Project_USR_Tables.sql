/**
 * Category    : USR
 * Table ID    : USR_INCOME
 * Table Name  : Income Entry
 * Description : Sales Income and Other Income for Org Category A, Other Income for Org Category B/C/D
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
    non_cash_amt                    number(12, 2),                                                  -- Amount of non-cash(Card, Cheque, Deposit etc)
    cash_amt                        number(12, 2),                                                  -- Amount of cash
    gross_amt                       number(12, 2),                                                  -- Amount of gross (non_cash_amt + cash_amt)
    gst_free_amt                    number(12, 2),                                                  -- GST Free amount
    gst_amt                         number(12, 2),                                                  -- GST amount (calculate besed on applied_gst but can be changed by user)
    applied_gst                     number(12, 2),                                                  -- Applied GST percentage (coming from sys_income_type.gst_percentage, gst_amt can be changed by user)
    net_amt                         number(12, 2),                                                  -- Net Sales Amt ((non_cash_amt + cash_amt) - gst_amt + gst_free_amt)
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
 * Category    : USR
 * Table ID    : USR_EXPENSE
 * Table Name  : Expense Entry
 * Description : Expenditure data entry for all organisation category
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
    gross_amt                       number(12, 2),                                                  -- Amount of gross
    gst_amt                         number(12, 2),                                                  -- GST amount (calculate besed on applied_gst but can be changed by user)
    applied_gst                     number(12, 2),                                                  -- Applied GST percentage (coming from sys_expense_type.gst_percentage, gst_amt can be changed by user)
    net_amt                         number(12, 2),                                                  -- Net Expenditure Amt (gross_amt - gst_amt)
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
 * Category    : USR
 * Table ID    : USR_ASSET
 * Table Name  : Asset Entry
 * Description : Asset data entry for all organisation category
 */
drop table usr_asset cascade constraints;
purge recyclebin;

create table usr_asset (
    asset_id                        varchar2(30)                                        not null,   -- Asset Entry UID (PK)
    asset_year                      varchar2(4)                                         not null,   -- Year (ex. 2017)
    quarter_name                    varchar2(30)                                        not null,   -- Quarter Name (sys_common_code.quarter_name)
    org_id                          varchar2(30)                                        not null,   -- Organisation UID (sys_org.org_id)
    asset_type_id                   varchar2(30)                                        not null,   -- Asset Type UID (sys_asset_type.asset_type_id)
    asset_date                      date                                                not null,   -- Date of Asset data entry (can be set the last date of the quarter if user does not input)
    gross_amt                       number(12, 2),                                                  -- Amount of gross
    gst_amt                         number(12, 2),                                                  -- GST amount (calculate besed on applied_gst but can be changed by user)
    applied_gst                     number(12, 2),                                                  -- Applied GST percentage (coming from sys_asset_type.gst_percentage, gst_amt can be changed by user)
    net_amt                         number(12, 2),                                                  -- Net Asset Amt (gross_amt - gst_amt)
    is_completed                    varchar2(30)                                        not null,   -- Is Completed (sys_common_code.simple_yn, user can not update if completed)
    description                     varchar2(1000),                                                 -- Description (Particular)
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_usr_asset primary key(asset_id)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  usr_asset                     is 'Asset Entry (Asset data entry for all organisation category)';
comment on column usr_asset.asset_id            is 'Asset Entry UID (PK)';
comment on column usr_asset.asset_year          is 'Year (ex. 2017)';
comment on column usr_asset.quarter_name        is 'Quarter Name (sys_common_code.quarter_name)';
comment on column usr_asset.org_id              is 'Organisation UID (sys_org.org_id)';
comment on column usr_asset.asset_type_id       is 'Asset Type UID (sys_asset_type.asset_type_id)';
comment on column usr_asset.asset_date          is 'Date of Asset data entry (can be set the last date of the quarter if user does not input)';
comment on column usr_asset.gross_amt           is 'Amount of gross';
comment on column usr_asset.gst_amt             is 'GST amount (calculate besed on applied_gst but can be changed by user)';
comment on column usr_asset.applied_gst         is 'Applied GST percentage (coming from sys_expense_type.gst_percentage, gst_amt can be changed by user)';
comment on column usr_asset.net_amt             is 'Net Asset Amt (gross_amt - gst_amt)';
comment on column usr_asset.is_completed        is 'Is Completed (sys_common_code.simple_yn, user can not update if completed)';
comment on column usr_asset.description         is 'Description (Particular)';
comment on column usr_asset.insert_user_id      is 'Insert User UID';
comment on column usr_asset.insert_date         is 'Insert Date';
comment on column usr_asset.update_user_id      is 'Update User UID';
comment on column usr_asset.update_date         is 'Update Date';


/**
 * Category    : USR
 * Table ID    : USR_ASSET_FILE
 * Table Name  : Attached file for USR_ASSET table
 * Description : 
 */
drop table usr_asset_file cascade constraints;
purge recyclebin;

create table usr_asset_file (
    file_id                         varchar2(30)                                        not null,   -- File item UID (PK)
    asset_id                        varchar2(30)                                        not null,   -- Asset UID (usr_asset.asset_id)
    original_name                   varchar2(1000)                                      not null,   -- Real file name
    new_name                        varchar2(1000)                                      not null,   -- System defined file name
    file_type                       varchar2(300),                                                  -- File type
    file_icon                       varchar2(1000),                                                 -- File Icon path and name
    file_size                       number(12,2),                                                   -- File size (KB)
    repository_path                 varchar2(1000)                                      not null,   -- Saved file path
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint fk_usr_asset_file foreign key(asset_id) references usr_asset(asset_id),
    constraint pk_usr_asset_file primary key(file_id)

    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  usr_asset_file                    is 'Attached file for Bulletin board';
comment on column usr_asset_file.file_id            is 'File item UID (PK)';
comment on column usr_asset_file.asset_id           is 'Asset UID (usr_asset.asset_id)';
comment on column usr_asset_file.original_name      is 'Real file name';
comment on column usr_asset_file.new_name           is 'System defined file name';
comment on column usr_asset_file.file_type          is 'File type';
comment on column usr_asset_file.file_icon          is 'File Icon path and name';
comment on column usr_asset_file.file_size          is 'File size (KB)';
comment on column usr_asset_file.repository_path    is 'Saved file path';
comment on column usr_asset_file.insert_user_id     is 'Insert User UID';
comment on column usr_asset_file.insert_date        is 'Insert Date';
comment on column usr_asset_file.update_user_id     is 'Update User UID';
comment on column usr_asset_file.update_date        is 'Update Date';


/**
 * Category    : USR
 * Table ID    : USR_FINANCE
 * Table Name  : Financial Loan Related Data Entry
 * Description : Repayment / Borrowing / Lending
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
    repayment_amt                   number(12, 2),                                                  -- Repayment Amount (only for repayment_type)
    principal_amt                   number(12, 2),                                                  -- Principal Amount (for borrowing and lending types)
    interest_percentage             number(12, 2),                                                  -- Interest Rate Percentage (%) (for borrowing and lending types)
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
 * Category    : USR
 * Table ID    : USR_FINANCE_FILE
 * Table Name  : Attached file for usr_finance table
 * Description : 
 */
drop table usr_finance_file cascade constraints;
purge recyclebin;

create table usr_finance_file (
    file_id                         varchar2(30)                                        not null,   -- File item UID (PK)
    finance_id                      varchar2(30)                                        not null,   -- Finance Entry UID (usr_finance.finance_id)
    original_name                   varchar2(1000)                                      not null,   -- Real file name
    new_name                        varchar2(1000)                                      not null,   -- System defined file name
    file_type                       varchar2(300),                                                  -- File type
    file_icon                       varchar2(1000),                                                 -- File Icon path and name
    file_size                       number(12,2),                                                   -- File size (KB)
    repository_path                 varchar2(1000)                                      not null,   -- Saved file path
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint fk_usr_finance_file foreign key(finance_id) references usr_finance(finance_id),
    constraint pk_usr_finance_file primary key(file_id)

    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  usr_finance_file                    is 'Attached file for usr_finance table';
comment on column usr_finance_file.file_id            is 'File item UID (PK)';
comment on column usr_finance_file.finance_id         is 'Finance Entry UID (usr_finance.finance_id)';
comment on column usr_finance_file.original_name      is 'Real file name';
comment on column usr_finance_file.new_name           is 'System defined file name';
comment on column usr_finance_file.file_type          is 'File type';
comment on column usr_finance_file.file_icon          is 'File Icon path and name';
comment on column usr_finance_file.file_size          is 'File size (KB)';
comment on column usr_finance_file.repository_path    is 'Saved file path';
comment on column usr_finance_file.insert_user_id     is 'Insert User UID';
comment on column usr_finance_file.insert_date        is 'Insert Date';
comment on column usr_finance_file.update_user_id     is 'Update User UID';
comment on column usr_finance_file.update_date        is 'Update Date';


/**
 * Category    : USR
 * Table ID    : USR_EMPLOYEE
 * Table Name  : Employee Master
 * Description : 
 */
drop table usr_employee cascade constraints;
purge recyclebin;

create table usr_employee (
    employee_id                     varchar2(30)                                        not null,   -- Employee UID (PK)
    org_id                          varchar2(30)                                        not null,   -- Organisation UID (sys_org.org_id)
    visa_type                       varchar2(30)                                        not null,   -- Visa Type (sys_common_code.visa_type)
    wage_type                       varchar2(30)                                        not null,   -- Wage Type (sys_common_code.wage_type)
    hourly_rate                     number(12, 2),                                                  -- Wage Hourly rate
    surname                         varchar2(30)                                        not null,   -- Surname
    given_name                      varchar2(30)                                        not null,   -- Given Name
    tfn                             varchar2(30),                                                   -- TFN
    date_of_birth                   date,                                                           -- Date of Birth
    address                         varchar2(1000),                                                 -- Address
    start_date                      date,                                                           -- Start Date
    end_date                        date,                                                           -- End Date
    description                     varchar2(1000),                                                 -- Description
    is_active                       varchar2(30)                                        not null,   -- Is Active (set to N if user delete the employee)
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_usr_employee primary key(employee_id)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  usr_employee                     is 'Employee Master';
comment on column usr_employee.employee_id         is 'Employee UID (PK)';
comment on column usr_employee.org_id              is 'Organisation UID (sys_org.org_id)';
comment on column usr_employee.visa_type           is 'Visa Type (sys_common_code.visa_type)';
comment on column usr_employee.wage_type           is 'Wage Type (sys_common_code.wage_type)';
comment on column usr_employee.hourly_rate         is 'Wage Hourly rate';
comment on column usr_employee.surname             is 'Surname';
comment on column usr_employee.given_name          is 'Given Name';
comment on column usr_employee.tfn                 is 'TFN';
comment on column usr_employee.date_of_birth       is 'Date of Birth';
comment on column usr_employee.address             is 'Address';
comment on column usr_employee.start_date          is 'Start Date';
comment on column usr_employee.end_date            is 'End Date';
comment on column usr_employee.description         is 'Description';
comment on column usr_employee.is_active           is 'Is Active (set to N if user delete the employee)';
comment on column usr_employee.insert_user_id      is 'Insert User UID';
comment on column usr_employee.insert_date         is 'Insert Date';
comment on column usr_employee.update_user_id      is 'Update User UID';
comment on column usr_employee.update_date         is 'Update Date';


/**
 * Category    : USR
 * Table ID    : USR_EMPLOYEE_WAGE
 * Table Name  : Employee Wage
 * Description : 
 */
drop table usr_employee_wage cascade constraints;
purge recyclebin;

create table usr_employee_wage (
    wage_id                         varchar2(30)                                        not null,   -- Employee Wage UID (PK)
    wage_year                       varchar2(4)                                         not null,   -- Year (ex. 2017)
    quarter_name                    varchar2(30)                                        not null,   -- Quarter Name (sys_common_code.quarter_name)
    org_id                          varchar2(30)                                        not null,   -- Organisation UID (sys_org.org_id)
    employee_id                     varchar2(30)                                        not null,   -- Employee UID (usr_employee.employee_id)
    period_start_date               date,                                                           -- Work Period Start Date
    period_end_date                 date,                                                           -- Work Period End Date
    hourly_rate                     number(12, 2),                                                  -- Hourly rate (coming from usr_employee.hourly_rate, user can change the value)
    hour_worked                     number(12, 2),                                                  -- Number of Hours worked
    gross_wage                      number(12, 2),                                                  -- Gross Wage
    tax                             number(12, 2),                                                  -- Tax Amount (calculate based on sys_tax_master by usr_employee.visa_status)
    super_amt                       number(12, 2),                                                  -- Super (calculate based on sys_common_code.super_type)
    net_wage                        number(12, 2),                                                  -- Net Wage Amount (gross_wage - (tax + super))
    description                     varchar2(1000),                                                 -- Description
    is_completed                    varchar2(30)                                        not null,   -- Is Completed (sys_common_code.simple_yn, user can not update if completed)
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_usr_employee_wage primary key(wage_id)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  usr_employee_wage                     is 'Employee Wage';
comment on column usr_employee_wage.wage_id             is 'Employee Wage UID (PK)';
comment on column usr_employee_wage.wage_year           is 'Year (ex. 2017)';
comment on column usr_employee_wage.quarter_name        is 'Quarter Name (sys_common_code.quarter_name)';
comment on column usr_employee_wage.org_id              is 'Organisation UID (sys_org.org_id)';
comment on column usr_employee_wage.employee_id         is 'Employee UID (usr_employee.employee_id)';
comment on column usr_employee_wage.period_start_date   is 'Work Period Start Date';
comment on column usr_employee_wage.period_end_date     is 'Work Period End Date';
comment on column usr_employee_wage.hourly_rate         is 'Hourly rate (coming from usr_employee.hourly_rate, user can change the value)';
comment on column usr_employee_wage.hour_worked         is 'Number of Hours worked';
comment on column usr_employee_wage.gross_wage          is 'Gross Wage';
comment on column usr_employee_wage.tax                 is 'Tax Amount (calculate based on sys_tax_master by usr_employee.visa_status)';
comment on column usr_employee_wage.super_amt           is 'Super (calculate based on sys_common_code.super_type)';
comment on column usr_employee_wage.net_wage            is 'Net Wage Amount (gross_wage - (tax + super))';
comment on column usr_employee_wage.description         is 'Description';
comment on column usr_employee_wage.is_completed        is 'Is Completed (sys_common_code.simple_yn, user can not update if completed)';
comment on column usr_employee_wage.insert_user_id      is 'Insert User UID';
comment on column usr_employee_wage.insert_date         is 'Insert Date';
comment on column usr_employee_wage.update_user_id      is 'Update User UID';
comment on column usr_employee_wage.update_date         is 'Update Date';