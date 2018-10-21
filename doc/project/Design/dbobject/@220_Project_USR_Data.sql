/**
 * Category    : USR
 * Table ID    : USR_INCOME
 * Table Name  : Income Entry
 * Description : Sales Income and Other Income for Org Category A, Other Income for Org Category B/C/D
 */
delete usr_income;

-- Sales Income
insert into usr_income
select idx as income_id,
       year as income_year,
       case quarter
            when '1' then 'SEP'
            when '2' then 'DEC'
            when '3' then 'MAR'
            when '4' then 'JUN'
       end as quarter_name,
       user_id as org_id,
       'SALES' as income_entry_type,
       case record_keeping_type
            when '1' then 'RKPOS'
            when '2' then 'RKBNKDEP'
            when '3' then 'RKINVC'
       end as record_keeping_type,
       null as income_type_id,
       template_sales_date as income_date,
       card_sales as non_cash_amt,
       sash_sales as cash_amt,
       (card_sales + sash_sales) as gross_amt,
       gst_free_sales as gst_free_amt,
       trunc(((card_sales + sash_sales) / 11), 2) as gst_amt,
       null as applied_gst,
       trunc((card_sales + sash_sales) - trunc(((card_sales + sash_sales) / 11), 2), 2) as net_amt,
       case complete_flag
            when '1' then 'Y'
            else 'N'
       end as is_completed,
       'Migrated by new system '||to_char(sysdate, 'yyyy-mm-dd') as description,
       '0' as insert_user,
       sysdate as insert_date,
       null as update_user,
       null as update_date
  from dm_template_sales
 order by template_sales_date
;

-- Other Income
insert into usr_income
select (idx + 103508) as income_id,  -- caution : idx
       year as income_year,
       case quarter
            when 1 then 'SEP'
            when 2 then 'DEC'
            when 3 then 'MAR'
            when 4 then 'JUN'
       end as quarter_name,
       user_id as org_id,
       case (select org_category from sys_org where org_id = dm_template_other.user_id)
            when 'A' then 'OTHTA'
            else 'OTHETC'
       end as income_entry_type,
       null as record_keeping_type,
       income_type_id as income_type_id,
       template_other_date as income_date,
       null as non_cash_amt,
       null as cash_amt,
       gross as gross_amt,
       null as gst_free_amt,
       gst as gst_amt,
       null as applied_gst,
       net as net_amt,
       case complete_flag
            when '1' then 'Y'
            else 'N'
       end as is_completed,
       description||' - Migrated by new system '||to_char(sysdate, 'yyyy-mm-dd') as description,
       '0' as insert_user,
       sysdate as insert_date,
       null as update_user,
       null as update_date
  from dm_template_other
 order by template_other_date
;

/**
 * Category    : USR
 * Table ID    : USR_EXPENSE
 * Table Name  : Expense Entry
 * Description : Expenditure data entry for all organisation category
 */
delete usr_expense;
insert into usr_expense
select idx as expense_id,
       year as expense_year,
       case quarter
            when '1' then 'SEP'
            when '2' then 'DEC'
            when '3' then 'MAR'
            when '4' then 'JUN'
       end as quarter_name,
       user_id as org_id,
       sub_type_id as expense_type_id, --???
/*
main_type_id as main_type_id,
(select main_type_name from dm_main_type where idx = dm_expense.main_type_id) as main_type_name_old,
case main_type_id
     when 1 then 'EMPUR'
     when 2 then 'EMFUT'
     when 3 then 'EMMVT'
     when 4 then 'EMOTH'
     when 5 then 'EMSRE'
     when 6 then 'EMBFC'
     when 7 then 'EMERE'
     when 8 then null
     when 9 then 'EMMAS'
     when 10 then 'EMFUT'
     when 11 then 'EMMVT'
     when 12 then 'EMORE'
     when 13 then 'EMOTH'
     when 14 then 'EMBFC'
     when 15 then 'EMERE'
     when 16 then null
     when 17 then null
     when 18 then 'EMFUT'
     when 19 then 'EMMVT'
     when 20 then 'EMOTH'
     when 21 then 'EMORE'
     when 22 then 'EMBFC'
     when 23 then 'EMERE'
     when 24 then 'EMCRE'
end as main_type_code_new,
case main_type_id
     when 1 then 'Purchase'
     when 2 then 'Fees / Utility'
     when 3 then 'Motor Vehicles / Travel'
     when 4 then 'Other Expenses'
     when 5 then 'Shop Related Expenses'
     when 6 then 'Banking / Finance'
     when 7 then 'Employee Related Expenses'
     when 8 then null
     when 9 then 'Materials / Supplies'
     when 10 then 'Fees / Utility'
     when 11 then 'Motor Vehicles / Travel'
     when 12 then 'Office Related Expenses'
     when 13 then 'Other Expenses'
     when 14 then 'Banking / Finance'
     when 15 then 'Employee Related Expenses'
     when 16 then null
     when 17 then null
     when 18 then 'Fees / Utility'
     when 19 then 'Motor Vehicles / Travel'
     when 20 then 'Other Expenses'
     when 21 then 'Office Related Expenses'
     when 22 then 'Banking / Finance'
     when 23 then 'Employee Related Expenses'
     when 24 then 'Client Related Expenses'
end as main_type_name_new,
sub_type_id as sub_type_id,
(select sub_type_name from dm_sub_type where idx = dm_expense.sub_type_id) as sub_type_name_old,
(select expense_type_id from sys_expense_type where expense_type_id = dm_expense.sub_type_id) as sub_type_id_new,
(select description from sys_expense_type where expense_type_id = dm_expense.sub_type_id) as sub_type_name_new,
(select parent_expense_type from sys_expense_type where expense_type_id = dm_expense.sub_type_id) as parent_type_new,
*/
       expense_date as expense_date,
       gross as gross_amt,
       gst as gst_amt,
       null as applied_gst,
       (gross - gst) as net_amt,
       case complete_flag
            when '1' then 'Y'
            else 'N'
       end as is_completed,
       description||' - Migrated by new system '||to_char(sysdate, 'yyyy-mm-dd') as description,
       '0' as insert_user,
       sysdate as insert_date,
       null as update_user,
       null as update_date
  from dm_expense
 order by expense_date
;

/**
 * Category    : USR
 * Table ID    : USR_ASSET
 * Table Name  : Asset Entry
 * Description : Asset data entry for all organisation category
 */
delete usr_asset;
insert into usr_asset
select idx as asset_id,
       year as asset_year,
       case quarter
            when '1' then 'SEP'
            when '2' then 'DEC'
            when '3' then 'MAR'
            when '4' then 'JUN'
       end as quarter_name,
       user_id as org_id,
       main_type_id as asset_type_id,
       asset_date as asset_date,
       product_gross as gross_amt,
       gst as gst_amt,
       null as applied_gst,
       (product_gross - gst) as net_amt,
       case complete_flag
            when '1' then 'Y'
            else 'N'
       end as is_completed,
       description||' - Migrated by new system '||to_char(sysdate, 'yyyy-mm-dd') as description,
       '0' as insert_user,
       sysdate as insert_date,
       null as update_user,
       null as update_date
  from dm_asset
 order by asset_date
;

/**
 * Category    : USR
 * Table ID    : USR_FINANCE
 * Table Name  : Financial Loan Related Data Entry
 * Description : Repayment / Borrowing / Lending
 */
delete usr_finance;

-- Borrowing
insert into usr_finance
select idx as finance_id,
       year as finance_year,
       case quarter
            when '1' then 'SEP'
            when '2' then 'DEC'
            when '3' then 'MAR'
            when '4' then 'JUN'
       end as quarter_name,
       user_id as org_id,
       'BOR' as finance_entry_type,
       main_type_id as finance_type_id, --???
       loan_lending_date as finance_date,
       null as repayment_amt,
       principal as principal_amt,
       interest_rate as interest_percentage,
       start_date as start_date,
       end_date as end_date,
       case complete_flag
            when '1' then 'Y'
            else 'N'
       end as is_completed,
       description||' - Migrated by new system '||to_char(sysdate, 'yyyy-mm-dd') as description,
       '0' as insert_user,
       sysdate as insert_date,
       null as update_user,
       null as update_date
  from dm_loan_lending
 where main_type_id is not null
 order by loan_lending_date
;

-- Repayment
insert into usr_finance
select (idx + 138) as finance_id, -- caution : idx
       year as finance_year,
       case quarter
            when '1' then 'SEP'
            when '2' then 'DEC'
            when '3' then 'MAR'
            when '4' then 'JUN'
       end as quarter_name,
       user_id as org_id,
       'REP' as finance_entry_type,
       main_type_id as finance_type_id, --???
       loan_payment_date as finance_date,
       amount as repayment_amt,
       null as principal_amt,
       null as interest_percentage,
       null as start_date,
       null as end_date,
       case complete_flag
            when '1' then 'Y'
            else 'N'
       end as is_completed,
       description||' - Migrated by new system '||to_char(sysdate, 'yyyy-mm-dd') as description,
       '0' as insert_user,
       sysdate as insert_date,
       null as update_user,
       null as update_date
  from dm_loan_payment
 where main_type_id is not null
 order by loan_payment_date
;

/**
 * Category    : USR
 * Table ID    : USR_EMPLOYEE
 * Table Name  : Employee Master
 * Description : 
 */
delete usr_employee;
insert into usr_employee
select idx as employee_id,
       user_id as org_id,
       case visa_type
            when 1 then 'VTNRES'
            when 2 then 'VTRES'
       end as visa_type,
       case wage_type
            when 1 then 'WTFORTN'
            when 2 then 'WTWEEK'
       end as wage_type,
       null as hourly_rate,
       first_name as surname,
       nvl(last_name, 'Migrated by new system') as given_name,
       tfn as tfn,
       dob as date_of_birth,
       address as address,
       start_date as start_date,
       end_date as end_date,
       memo||' - Migrated by new system '||to_char(sysdate, 'yyyy-mm-dd') as description,
       case when end_date is null then 'Y'
            else 'N'
       end as is_active,
       '0' as insert_user,
       sysdate as insert_date,
       null as update_user,
       null as update_date
  from dm_employee
;

/**
 * Category    : USR
 * Table ID    : USR_EMPLOYEE_WAGE
 * Table Name  : Employee Wage
 * Description : 
 */
insert into usr_employee_wage
select idx as wage_id,
       year as wage_year,
       case quarter
            when '1' then 'SEP'
            when '2' then 'DEC'
            when '3' then 'MAR'
            when '4' then 'JUN'
       end as quarter_name,
       user_id as org_id,
       employee_id as employee_id,
       start_date as period_start_date,
       end_date as period_end_date,
       wage_per_hour as hourly_rate,
       working_hour as hour_worked,
       gross_wage as gross_wage,
       tax as tax,
       super as super_amt,
       net_wage as net_wage,
       'Migrated by new system '||to_char(sysdate, 'yyyy-mm-dd') as description,
       case complete_flag
            when '1' then 'Y'
            else 'N'
       end as is_completed,
       '0' as insert_user,
       sysdate as insert_date,
       null as update_user,
       null as update_date
  from dm_wage
;
