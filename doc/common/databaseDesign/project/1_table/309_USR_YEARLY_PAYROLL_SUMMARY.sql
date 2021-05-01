/**
 * Table Name  : USR_YEARLY_PAYROLL_SUMMARY
 * Description : Yerarly payroll summary
 */
drop table usr_yearly_payroll_summary cascade constraints;
purge recyclebin;

create table usr_yearly_payroll_summary (
    org_id                          varchar2(30)                                 not null,      -- Org UID
    payroll_month                   varchar2(10)                                 not null,      -- Payroll month(YYYYMM)
    number_of_employee              number,                                                     -- Number of employee
    gross_pay_amt                   number,                                                     -- Gross payment
    tax                             number,                                                     -- Tax
    net_pay_amt                     number,                                                     -- Net Payment
    super_amt                       number,                                                     -- Superannuation
    update_date                     date,                                                       -- Update Date
    insert_user_id                  varchar2(30),                                               -- Insert User UID
    insert_date                     date                default sysdate,                        -- Insert Date
    update_user_id                  varchar2(30),                                               -- Update User UID

    constraint pk_usr_yearly_payroll_summary primary key(org_id, payroll_month)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  usr_yearly_payroll_summary                                     is 'Yerarly payroll summary';
comment on column usr_yearly_payroll_summary.org_id                              is 'Org UID';
comment on column usr_yearly_payroll_summary.payroll_month                       is 'Payroll month(YYYYMM)';
comment on column usr_yearly_payroll_summary.number_of_employee                  is 'Number of employee';
comment on column usr_yearly_payroll_summary.gross_pay_amt                       is 'Gross payment';
comment on column usr_yearly_payroll_summary.tax                                 is 'Tax';
comment on column usr_yearly_payroll_summary.net_pay_amt                         is 'Net Payment';
comment on column usr_yearly_payroll_summary.super_amt                           is 'Superannuation';
comment on column usr_yearly_payroll_summary.update_date                         is 'Update Date';
comment on column usr_yearly_payroll_summary.insert_user_id                      is 'Insert User UID';
comment on column usr_yearly_payroll_summary.insert_date                         is 'Insert Date';
comment on column usr_yearly_payroll_summary.update_user_id                      is 'Update User UID';


/**
 * Table Name  : USR_YEARLY_PAYROLL_SUMMARY
 * Data        : 
 */
