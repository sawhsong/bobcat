/**
 * Table Name  : USR_CASH_EXPENSE
 * Description : Cash expenses
 */
drop table usr_cash_expense cascade constraints;
purge recyclebin;

create table usr_cash_expense (
    cash_expense_id                 varchar2(30)                                 not null,      -- Cash expense UID (PK)
    user_id                         varchar2(30)                                 not null,      -- User UID
    proc_date                       date                                         not null,      -- Expense date
    main_category                   varchar2(30)                                 not null,      -- Main category (SYS_ALLOCATION_TYPE.MAIN_CATEGORY)
    sub_category                    varchar2(30)                                 not null,      -- Sub category (SYS_ALLOCATION_TYPE.SUB_CATEGORY)
    proc_amt                        number,                                                     -- Expense Smount
    gst_amt                         number,                                                     -- GST Amount
    net_amt                         number,                                                     -- Net Amount
    proc_description                varchar2(1000),                                             -- Process description
    insert_user_id                  varchar2(30),                                               -- Insert User UID
    insert_date                     date                default sysdate,                        -- Insert Date
    update_user_id                  varchar2(30),                                               -- Update User UID
    update_date                     date,                                                       -- Update Date

    constraint pk_usr_cash_expense primary key(cash_expense_id)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  usr_cash_expense                                               is 'Cash expenses';
comment on column usr_cash_expense.cash_expense_id                               is 'Cash expense UID (PK)';
comment on column usr_cash_expense.user_id                                       is 'User UID';
comment on column usr_cash_expense.proc_date                                     is 'Expense date';
comment on column usr_cash_expense.main_category                                 is 'Main category (SYS_ALLOCATION_TYPE.MAIN_CATEGORY)';
comment on column usr_cash_expense.sub_category                                  is 'Sub category (SYS_ALLOCATION_TYPE.SUB_CATEGORY)';
comment on column usr_cash_expense.proc_amt                                      is 'Expense Smount';
comment on column usr_cash_expense.gst_amt                                       is 'GST Amount';
comment on column usr_cash_expense.net_amt                                       is 'Net Amount';
comment on column usr_cash_expense.proc_description                              is 'Process description';
comment on column usr_cash_expense.insert_user_id                                is 'Insert User UID';
comment on column usr_cash_expense.insert_date                                   is 'Insert Date';
comment on column usr_cash_expense.update_user_id                                is 'Update User UID';
comment on column usr_cash_expense.update_date                                   is 'Update Date';


/**
 * Table Name  : USR_CASH_EXPENSE
 * Data        : 
 */
