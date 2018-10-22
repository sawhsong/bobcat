/**
 * Table Name  : USR_EMPLOYEE
 * Description : Employee Master
 */
drop table usr_employee cascade constraints;
purge recyclebin;

create table usr_employee (
    employee_id                     varchar2(30)                                        not null,   -- Employee UID (PK)
    org_id                          varchar2(30)                                        not null,   -- Organisation UID (sys_org.org_id)
    visa_type                       varchar2(30)                                        not null,   -- Visa Type (sys_common_code.visa_type)
    wage_type                       varchar2(30)                                        not null,   -- Wage Type (sys_common_code.wage_type)
    hourly_rate                     number(12,2),                                                   -- Wage Hourly rate
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
 * Table Name  : USR_EMPLOYEE
 * Data        : 
 */
