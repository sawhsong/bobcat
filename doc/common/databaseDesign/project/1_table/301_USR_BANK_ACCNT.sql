/**
 * Table Name  : USR_BANK_ACCNT
 * Description : Bank account info by user
 */
drop table usr_bank_accnt cascade constraints;
purge recyclebin;

create table usr_bank_accnt (
    bank_accnt_id                   varchar2(30)                                 not null,      -- Bank account UID (PK)
    user_id                         varchar2(30)                                 not null,      -- User UID (SYS_USER.USER_ID)
    bank_code                       varchar2(30)                                 not null,      -- Bank code (SYS_COMMON_CODE.COMMON_CODE)
    bsb                             varchar2(10)                                 not null,      -- Bank BSB Number
    accnt_number                    varchar2(30)                                 not null,      -- Bank account Number
    accnt_name                      varchar2(50),                                               -- Bank account Name
    balance                         number,                                                     -- Account balance amount
    description                     varchar2(100),                                              -- Bacnk account description
    insert_user_id                  varchar2(30),                                               -- Insert User UID
    insert_date                     date                default sysdate,                        -- Insert Date
    update_user_id                  varchar2(30),                                               -- Update User UID
    update_date                     date,                                                       -- Update Date

    constraint pk_usr_bank_accnt primary key(bank_accnt_id),
    constraint uk_13468612426000 unique(user_id, bank_code, bsb, accnt_number)
    using index tablespace bobcat_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace bobcat_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  usr_bank_accnt                                                 is 'Bank account info by user';
comment on column usr_bank_accnt.bank_accnt_id                                   is 'Bank account UID (PK)';
comment on column usr_bank_accnt.user_id                                         is 'User UID (SYS_USER.USER_ID)';
comment on column usr_bank_accnt.bank_code                                       is 'Bank code (SYS_COMMON_CODE.COMMON_CODE)';
comment on column usr_bank_accnt.bsb                                             is 'Bank BSB Number';
comment on column usr_bank_accnt.accnt_number                                    is 'Bank account Number';
comment on column usr_bank_accnt.accnt_name                                      is 'Bank account Name';
comment on column usr_bank_accnt.balance                                         is 'Account balance amount';
comment on column usr_bank_accnt.description                                     is 'Bacnk account description';
comment on column usr_bank_accnt.insert_user_id                                  is 'Insert User UID';
comment on column usr_bank_accnt.insert_date                                     is 'Insert Date';
comment on column usr_bank_accnt.update_user_id                                  is 'Update User UID';
comment on column usr_bank_accnt.update_date                                     is 'Update Date';


/**
 * Table Name  : USR_BANK_ACCNT
 * Data        : 
 */
