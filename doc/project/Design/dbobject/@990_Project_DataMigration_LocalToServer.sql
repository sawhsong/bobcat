delete sys_asset_type@hkaccount_aws;
insert into sys_asset_type@HKACCOUNT_AWS
select * from sys_asset_type;

delete sys_auth_group@hkaccount_aws;
insert into sys_auth_group@HKACCOUNT_AWS
select * from sys_auth_group;

delete sys_board@hkaccount_aws;
insert into sys_board@hkaccount_aws
select * from sys_board;

delete sys_board_file@hkaccount_aws;
insert into sys_board_file@hkaccount_aws
select * from sys_board_file;

delete sys_borrowing_type@hkaccount_aws;
insert into sys_borrowing_type@hkaccount_aws
select * from sys_borrowing_type;

delete sys_common_code@hkaccount_aws;
insert into sys_common_code@hkaccount_aws
select * from sys_common_code;

delete sys_country_currency@hkaccount_aws;
insert into sys_country_currency@hkaccount_aws
select * from sys_country_currency;

delete sys_expense_type@hkaccount_aws;
insert into sys_expense_type@hkaccount_aws
select * from sys_expense_type;

delete sys_financial_period@hkaccount_aws;
insert into sys_financial_period@hkaccount_aws
select * from sys_financial_period;

delete sys_income_type@hkaccount_aws;
insert into sys_income_type@hkaccount_aws
select * from sys_income_type;

delete sys_lending_type@hkaccount_aws;
insert into sys_lending_type@hkaccount_aws
select * from sys_lending_type;

delete sys_menu@hkaccount_aws;
insert into sys_menu@hkaccount_aws
select * from sys_menu;

delete sys_menu_auth_link@hkaccount_aws;
insert into sys_menu_auth_link@hkaccount_aws
select * from sys_menu_auth_link;

delete sys_org@hkaccount_aws;
insert into sys_org@hkaccount_aws
select * from sys_org;

delete sys_repayment_type@hkaccount_aws;
insert into sys_repayment_type@hkaccount_aws
select * from sys_repayment_type;

delete sys_tax_master@hkaccount_aws;
insert into sys_tax_master@hkaccount_aws
select * from sys_tax_master;

delete sys_user@hkaccount_aws;
insert into sys_user@hkaccount_aws
select * from sys_user;

delete usr_asset@hkaccount_aws;
insert into usr_asset@hkaccount_aws
select * from usr_asset;

delete usr_asset_file@hkaccount_aws;
insert into usr_asset_file@hkaccount_aws
select * from usr_asset_file;

delete usr_employee@hkaccount_aws;
insert into usr_employee@hkaccount_aws
select * from usr_employee;

delete usr_employee_wage@hkaccount_aws;
insert into usr_employee_wage@hkaccount_aws
select * from usr_employee_wage;

delete usr_expense@hkaccount_aws;
insert into usr_expense@hkaccount_aws
select * from usr_expense;

delete usr_finance@hkaccount_aws;
insert into usr_finance@hkaccount_aws
select * from usr_finance;

delete usr_finance_file@hkaccount_aws;
insert into usr_finance_file@hkaccount_aws
select * from usr_finance_file;
/*to do*/
delete usr_income@hkaccount_aws;
insert into usr_income@hkaccount_aws
select * from usr_income;

delete zebra_board@hkaccount_aws;
insert into zebra_board@hkaccount_aws
select * from zebra_board;

delete zebra_board_file@hkaccount_aws;
insert into zebra_board_file@hkaccount_aws
select * from zebra_board_file;

delete zebra_common_code@hkaccount_aws;
insert into zebra_common_code@hkaccount_aws
select * from zebra_common_code;