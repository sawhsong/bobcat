/**
 * Table Name  : USR_CC_STATEMENT_D
 * Description : Credit card statement details
 */
drop table usr_cc_statement_d cascade constraints;
purge recyclebin;

create table usr_cc_statement_d (
    cc_statement_d_id               varchar2(30)                                 not null,      -- Credit card statement detail UID (PK)
    cc_statement_id                 varchar2(30)                                 not null,      -- Credit card statement master ID
    row_index                       number                                       not null,      -- Row number of the file (For checking proc order)
    proc_date                       date                                         not null,      -- Date processed
    proc_amt                        number                                       not null,      -- Amount processed
    proc_description                varchar2(1000),                                             -- Process description
    user_description                varchar2(500),                                              -- Description for user
    bank_account                    varchar2(30),                                               -- Bank account (westpac)
    debit_amt                       number,                                                     -- Debit amount (westpac)
    credit_amt                      number,                                                     -- Credit amount (westpac)
    category                        varchar2(50),                                               -- Categories (westpac)
    serial                          varchar2(50),                                               -- Serial (westpac)
    insert_user_id                  varchar2(30),                                               -- Insert User UID
    insert_date                     date                default sysdate,                        -- Insert Date
    update_user_id                  varchar2(30),                                               -- Update User UID
    update_date                     date,                                                       -- Update Date

    constraint fk_11694491827400 foreign key(cc_statement_id) references usr_cc_statement(cc_statement_id),
    constraint pk_usr_cc_statement_d primary key(cc_statement_d_id)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  usr_cc_statement_d                                             is 'Credit card statement details';
comment on column usr_cc_statement_d.cc_statement_d_id                           is 'Credit card statement detail UID (PK)';
comment on column usr_cc_statement_d.cc_statement_id                             is 'Credit card statement master ID';
comment on column usr_cc_statement_d.row_index                                   is 'Row number of the file (For checking proc order)';
comment on column usr_cc_statement_d.proc_date                                   is 'Date processed';
comment on column usr_cc_statement_d.proc_amt                                    is 'Amount processed';
comment on column usr_cc_statement_d.proc_description                            is 'Process description';
comment on column usr_cc_statement_d.user_description                            is 'Description for user';
comment on column usr_cc_statement_d.bank_account                                is 'Bank account (westpac)';
comment on column usr_cc_statement_d.debit_amt                                   is 'Debit amount (westpac)';
comment on column usr_cc_statement_d.credit_amt                                  is 'Credit amount (westpac)';
comment on column usr_cc_statement_d.category                                    is 'Categories (westpac)';
comment on column usr_cc_statement_d.serial                                      is 'Serial (westpac)';
comment on column usr_cc_statement_d.insert_user_id                              is 'Insert User UID';
comment on column usr_cc_statement_d.insert_date                                 is 'Insert Date';
comment on column usr_cc_statement_d.update_user_id                              is 'Update User UID';
comment on column usr_cc_statement_d.update_date                                 is 'Update Date';


/**
 * Table Name  : USR_CC_STATEMENT_D
 * Data        : 
 */
