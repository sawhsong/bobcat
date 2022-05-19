/**
 * Table Name  : USR_INVOICE
 * Description : Invoice info for additioanl user service
 */
drop table usr_invoice cascade constraints;
purge recyclebin;

create table usr_invoice (
    invoice_id                      varchar2(30)                                 not null,      -- Invoice UID (PK)
    invoice_number                  varchar2(30)                                 not null,      -- Invoice Number - for display
    issue_date                      date                                         not null,      -- Date issued
    user_id                         varchar2(30)                                 not null,      -- User UID - Logged In User Id (sys_user.user_id)
    quotation_id                    varchar2(30),                                               -- Quotation UID - if the invoice linked with quotation
    status                          varchar2(30),                                               -- Invoice status (sys_common_code.invoice_status)
    provider_org_id                 varchar2(30),                                               -- Provider Org UID if applicable
    provider_logo_path              varchar2(1000),                                             -- Provider logo image path
    provider_name                   varchar2(50),                                               -- Provider name - user name | org name | editable
    provider_telephone              varchar2(15),                                               -- Provider land line number
    provider_mobile                 varchar2(15),                                               -- Provider mobile number
    provider_email                  varchar2(100),                                              -- Provider email - user emal | org email | editable
    provider_address                varchar2(300),                                              -- Provider address
    provider_abn                    varchar2(15),                                               -- Provider ABN
    provider_acn                    varchar2(15),                                               -- Provider ACN
    client_org_id                   varchar2(30),                                               -- Client Org UID if applicable
    client_user_id                  varchar2(30),                                               -- Client User UID if applicable
    client_name                     varchar2(50),                                               -- Client name - user name | org name | editable
    client_telephone                varchar2(15),                                               -- Client land line number
    client_mobile                   varchar2(15),                                               -- Client mobile number
    client_email                    varchar2(100),                                              -- Client email - user emal | org email | editable
    client_address                  varchar2(300),                                              -- Client address
    net_amt                         number,                                                     -- Invoice net amount (exclude gst)
    gst_amt                         number,                                                     -- GST amount
    total_amt                       number,                                                     -- Invoice total amount (include gst)
    description                     varchar2(1000),                                             -- Description - for user to set brief comments
    additional_remark               varchar2(4000),                                             -- Additional remark
    payment_due_date                date,                                                       -- Payment due date
    payment_method                  varchar2(30),                                               -- Payment method - sys_common_code.invoice_payment_method
    bank_accnt_id                   varchar2(30),                                               -- Bank account UID - if the user has bank account registered on the system and select it
    bank_code                       varchar2(30),                                               -- Bank - sys_common_code.bank_type
    bsb                             varchar2(10),                                               -- Bank BSB number
    bank_accnt_number               varchar2(30),                                               -- Bank account number
    bank_accnt_name                 varchar2(50),                                               -- Bank account name
    ref_number                      varchar2(30),                                               -- Reference number - to display on the bank account description
    update_date                     date,                                                       -- Update Date
    insert_user_id                  varchar2(30),                                               -- Insert User UID
    insert_date                     date                default sysdate,                        -- Insert Date
    update_user_id                  varchar2(30),                                               -- Update User UID

    constraint fk_52464305443800 foreign key(user_id) references sys_user(user_id),
    constraint pk_usr_invoice primary key(invoice_id)
    using index tablespace bobcat_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace bobcat_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  usr_invoice                                                    is 'Invoice info for additioanl user service';
comment on column usr_invoice.invoice_id                                         is 'Invoice UID (PK)';
comment on column usr_invoice.invoice_number                                     is 'Invoice Number - for display';
comment on column usr_invoice.issue_date                                         is 'Date issued';
comment on column usr_invoice.user_id                                            is 'User UID - Logged In User Id (sys_user.user_id)';
comment on column usr_invoice.quotation_id                                       is 'Quotation UID - if the invoice linked with quotation';
comment on column usr_invoice.status                                             is 'Invoice status (sys_common_code.invoice_status)';
comment on column usr_invoice.provider_org_id                                    is 'Provider Org UID if applicable';
comment on column usr_invoice.provider_logo_path                                 is 'Provider logo image path';
comment on column usr_invoice.provider_name                                      is 'Provider name - user name | org name | editable';
comment on column usr_invoice.provider_telephone                                 is 'Provider land line number';
comment on column usr_invoice.provider_mobile                                    is 'Provider mobile number';
comment on column usr_invoice.provider_email                                     is 'Provider email - user emal | org email | editable';
comment on column usr_invoice.provider_address                                   is 'Provider address';
comment on column usr_invoice.provider_abn                                       is 'Provider ABN';
comment on column usr_invoice.provider_acn                                       is 'Provider ACN';
comment on column usr_invoice.client_org_id                                      is 'Client Org UID if applicable';
comment on column usr_invoice.client_user_id                                     is 'Client User UID if applicable';
comment on column usr_invoice.client_name                                        is 'Client name - user name | org name | editable';
comment on column usr_invoice.client_telephone                                   is 'Client land line number';
comment on column usr_invoice.client_mobile                                      is 'Client mobile number';
comment on column usr_invoice.client_email                                       is 'Client email - user emal | org email | editable';
comment on column usr_invoice.client_address                                     is 'Client address';
comment on column usr_invoice.net_amt                                            is 'Invoice net amount (exclude gst)';
comment on column usr_invoice.gst_amt                                            is 'GST amount';
comment on column usr_invoice.total_amt                                          is 'Invoice total amount (include gst)';
comment on column usr_invoice.description                                        is 'Description - for user to set brief comments';
comment on column usr_invoice.additional_remark                                  is 'Additional remark';
comment on column usr_invoice.payment_due_date                                   is 'Payment due date';
comment on column usr_invoice.payment_method                                     is 'Payment method - sys_common_code.invoice_payment_method';
comment on column usr_invoice.bank_accnt_id                                      is 'Bank account UID - if the user has bank account registered on the system and select it';
comment on column usr_invoice.bank_code                                          is 'Bank - sys_common_code.bank_type';
comment on column usr_invoice.bsb                                                is 'Bank BSB number';
comment on column usr_invoice.bank_accnt_number                                  is 'Bank account number';
comment on column usr_invoice.bank_accnt_name                                    is 'Bank account name';
comment on column usr_invoice.ref_number                                         is 'Reference number - to display on the bank account description';
comment on column usr_invoice.update_date                                        is 'Update Date';
comment on column usr_invoice.insert_user_id                                     is 'Insert User UID';
comment on column usr_invoice.insert_date                                        is 'Insert Date';
comment on column usr_invoice.update_user_id                                     is 'Update User UID';


/**
 * Table Name  : USR_INVOICE
 * Data        : 
 */
