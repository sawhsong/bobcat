/**
 * Table Name  : USR_BS_TRAN_ALLOC
 * Description : Bank statement transaction allocation - transaction reconciliation
 */
drop table usr_bs_tran_alloc cascade constraints;
purge recyclebin;

create table usr_bs_tran_alloc (
    bs_tran_alloc_id                varchar2(30)                                 not null,      -- Bank statement transaction allocation UID (PK)
    bank_statement_d_id             varchar2(30)                                 not null,      -- Bank statement detail ID
    bank_statement_id               varchar2(30)                                 not null,      -- Bank statement master ID
    bank_accnt_id                   varchar2(30)                                 not null,      -- Bank account UID
    user_id                         varchar2(30)                                 not null,      -- User UID
    row_index                       number                                       not null,      -- Row number of the file (For checking proc order)
    proc_date                       date                                         not null,      -- Date processed
    proc_amt                        number                                       not null,      -- Amount processed
    proc_description                varchar2(1000),                                             -- Process description
    balance                         number,                                                     -- Balance amount after processing
    user_description                varchar2(500),                                              -- Description for user
    main_category                   varchar2(30),                                               -- Allocation main category (SYS_ALLOCATION_TYPE.MAIN_CATEGORY)
    sub_category                    varchar2(30),                                               -- Allocation sub category (SYS_ALLOCATION_TYPE.SUB_CATEGORY)
    gst_amt                         number,                                                     -- GST Amount
    net_amt                         number,                                                     -- Net Amount
    status                          varchar2(30),                                               -- Allocation status (SYS_COMMON_CODE.BS_TRAN_ALLOC_STATUS)
    source_type                     varchar2(30),                                               -- Allocaion source (if linked with other - ex. invoice)
    source_id                       varchar2(30),                                               -- Allocaion source ID (if linked with other - ex. invoice)
    update_date                     date,                                                       -- Update Date
    insert_user_id                  varchar2(30),                                               -- Insert User UID
    insert_date                     date                default sysdate,                        -- Insert Date
    update_user_id                  varchar2(30),                                               -- Update User UID

    constraint pk_usr_bs_tran_alloc primary key(bs_tran_alloc_id)
    using index tablespace bobcat_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace bobcat_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  usr_bs_tran_alloc                                              is 'Bank statement transaction allocation - transaction reconciliation';
comment on column usr_bs_tran_alloc.bs_tran_alloc_id                             is 'Bank statement transaction allocation UID (PK)';
comment on column usr_bs_tran_alloc.bank_statement_d_id                          is 'Bank statement detail ID';
comment on column usr_bs_tran_alloc.bank_statement_id                            is 'Bank statement master ID';
comment on column usr_bs_tran_alloc.bank_accnt_id                                is 'Bank account UID';
comment on column usr_bs_tran_alloc.user_id                                      is 'User UID';
comment on column usr_bs_tran_alloc.row_index                                    is 'Row number of the file (For checking proc order)';
comment on column usr_bs_tran_alloc.proc_date                                    is 'Date processed';
comment on column usr_bs_tran_alloc.proc_amt                                     is 'Amount processed';
comment on column usr_bs_tran_alloc.proc_description                             is 'Process description';
comment on column usr_bs_tran_alloc.balance                                      is 'Balance amount after processing';
comment on column usr_bs_tran_alloc.user_description                             is 'Description for user';
comment on column usr_bs_tran_alloc.main_category                                is 'Allocation main category (SYS_ALLOCATION_TYPE.MAIN_CATEGORY)';
comment on column usr_bs_tran_alloc.sub_category                                 is 'Allocation sub category (SYS_ALLOCATION_TYPE.SUB_CATEGORY)';
comment on column usr_bs_tran_alloc.gst_amt                                      is 'GST Amount';
comment on column usr_bs_tran_alloc.net_amt                                      is 'Net Amount';
comment on column usr_bs_tran_alloc.status                                       is 'Allocation status (SYS_COMMON_CODE.BS_TRAN_ALLOC_STATUS)';
comment on column usr_bs_tran_alloc.source_type                                  is 'Allocaion source (if linked with other - ex. invoice)';
comment on column usr_bs_tran_alloc.source_id                                    is 'Allocaion source ID (if linked with other - ex. invoice)';
comment on column usr_bs_tran_alloc.update_date                                  is 'Update Date';
comment on column usr_bs_tran_alloc.insert_user_id                               is 'Insert User UID';
comment on column usr_bs_tran_alloc.insert_date                                  is 'Insert Date';
comment on column usr_bs_tran_alloc.update_user_id                               is 'Update User UID';


/**
 * Table Name  : USR_BS_TRAN_ALLOC
 * Data        : 
 */
