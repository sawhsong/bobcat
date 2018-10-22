/**
 * Table Name  : USR_EMPLOYEE_WAGE
 * Description : Employee Wage
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
    hourly_rate                     number(12,2),                                                   -- Hourly rate (coming from usr_employee.hourly_rate, user can change the value)
    hour_worked                     number(12,2),                                                   -- Number of Hours worked
    gross_wage                      number(12,2),                                                   -- Gross Wage
    tax                             number(12,2),                                                   -- Tax Amount (calculate based on sys_tax_master by usr_employee.visa_status)
    super_amt                       number(12,2),                                                   -- Super (calculate based on sys_common_code.super_type)
    net_wage                        number(12,2),                                                   -- Net Wage Amount (gross_wage - (tax + super))
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


/**
 * Table Name  : USR_EMPLOYEE_WAGE
 * Data        : 
 */
