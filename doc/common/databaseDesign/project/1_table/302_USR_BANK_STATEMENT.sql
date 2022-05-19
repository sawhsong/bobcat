/**
 * Table Name  : USR_BANK_STATEMENT
 * Description : Bank statement master info which is uploaded by user
 */
drop table usr_bank_statement cascade constraints;
purge recyclebin;

create table usr_bank_statement (
    bank_statement_id               varchar2(30)                                 not null,      -- Bank statement master UID (PK)
    bank_accnt_id                   varchar2(30)                                 not null,      -- Bank account UID
    original_file_name              varchar2(500)                                not null,      -- File real name
    new_name                        varchar2(1000)                               not null,      -- File name defined by system
    file_type                       varchar2(100),                                              -- File type
    file_icon                       varchar2(500),                                              -- File icon path and name
    file_size                       number,                                                     -- File size (KB)
    repository_path                 varchar2(1000)                               not null,      -- Saved file path
    description                     varchar2(500),                                              -- File description written by user
    insert_user_id                  varchar2(30),                                               -- Insert User UID
    insert_date                     date                default sysdate,                        -- Insert Date
    update_user_id                  varchar2(30),                                               -- Update User UID
    update_date                     date,                                                       -- Update Date

    constraint fk_13247395166800 foreign key(bank_accnt_id) references usr_bank_accnt(bank_accnt_id),
    constraint pk_usr_bank_statement primary key(bank_statement_id)
    using index tablespace bobcat_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace bobcat_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  usr_bank_statement                                             is 'Bank statement master info which is uploaded by user';
comment on column usr_bank_statement.bank_statement_id                           is 'Bank statement master UID (PK)';
comment on column usr_bank_statement.bank_accnt_id                               is 'Bank account UID';
comment on column usr_bank_statement.original_file_name                          is 'File real name';
comment on column usr_bank_statement.new_name                                    is 'File name defined by system';
comment on column usr_bank_statement.file_type                                   is 'File type';
comment on column usr_bank_statement.file_icon                                   is 'File icon path and name';
comment on column usr_bank_statement.file_size                                   is 'File size (KB)';
comment on column usr_bank_statement.repository_path                             is 'Saved file path';
comment on column usr_bank_statement.description                                 is 'File description written by user';
comment on column usr_bank_statement.insert_user_id                              is 'Insert User UID';
comment on column usr_bank_statement.insert_date                                 is 'Insert Date';
comment on column usr_bank_statement.update_user_id                              is 'Update User UID';
comment on column usr_bank_statement.update_date                                 is 'Update Date';


/**
 * Table Name  : USR_BANK_STATEMENT
 * Data        : 
 */
