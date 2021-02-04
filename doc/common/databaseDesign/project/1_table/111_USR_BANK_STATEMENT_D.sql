/**
 * Table Name  : USR_BANK_STATEMENT_D
 * Description : Bank statement details
 */
drop table usr_bank_statement_d cascade constraints;
purge recyclebin;

create table usr_bank_statement_d (
    bank_statement_d_id             varchar2(30)                                 not null,      -- Bank statement detail UID (PK)
    bank_statement_id               varchar2(30)                                 not null,      -- Bank statement master ID
    row_index                       number                                       not null,      -- Row number of the file (For checking proc order)
    proc_date                       date                                         not null,      -- Date processed
    proc_amt                        number                                       not null,      -- Amount processed
    proc_description                varchar2(1000),                                             -- Process description
    balance                         number,                                                     -- Balance amount after processing
    user_description                varchar2(500),                                              -- Description for user
    insert_user_id                  varchar2(30),                                               -- Insert User UID
    insert_date                     date                default sysdate,                        -- Insert Date
    update_user_id                  varchar2(30),                                               -- Update User UID
    update_date                     date,                                                       -- Update Date

    constraint fk_34330454163900 foreign key(bank_statement_id) references usr_bank_statement(bank_statement_id),
    constraint pk_usr_bank_statement_d primary key(bank_statement_d_id)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  usr_bank_statement_d                                           is 'Bank statement details';
comment on column usr_bank_statement_d.bank_statement_d_id                       is 'Bank statement detail UID (PK)';
comment on column usr_bank_statement_d.bank_statement_id                         is 'Bank statement master ID';
comment on column usr_bank_statement_d.row_index                                 is 'Row number of the file (For checking proc order)';
comment on column usr_bank_statement_d.proc_date                                 is 'Date processed';
comment on column usr_bank_statement_d.proc_amt                                  is 'Amount processed';
comment on column usr_bank_statement_d.proc_description                          is 'Process description';
comment on column usr_bank_statement_d.balance                                   is 'Balance amount after processing';
comment on column usr_bank_statement_d.user_description                          is 'Description for user';
comment on column usr_bank_statement_d.insert_user_id                            is 'Insert User UID';
comment on column usr_bank_statement_d.insert_date                               is 'Insert Date';
comment on column usr_bank_statement_d.update_user_id                            is 'Update User UID';
comment on column usr_bank_statement_d.update_date                               is 'Update Date';


/**
 * Table Name  : USR_BANK_STATEMENT_D
 * Data        : 
 */
