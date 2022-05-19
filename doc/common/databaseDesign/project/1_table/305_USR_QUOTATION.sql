/**
 * Table Name  : USR_QUOTATION
 * Description : Quotation info for additioanl user service
 */
drop table usr_quotation cascade constraints;
purge recyclebin;

create table usr_quotation (
    quotation_id                    varchar2(30)                                 not null,      -- Quotation UID (PK)
    quotation_number                varchar2(30)                                 not null,      -- Quotation Number - for display
    issue_date                      date                                         not null,      -- Date issued
    user_id                         varchar2(30)                                 not null,      -- User UID - Logged In User Id (sys_user.user_id)
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
    net_amt                         number,                                                     -- Quotation net amount (exclude gst)
    gst_amt                         number,                                                     -- GST amount
    total_amt                       number,                                                     -- Qutation total amount (include gst)
    description                     varchar2(1000),                                             -- Description - for user to set brief comments
    additional_remark               varchar2(4000),                                             -- Additional remark
    update_date                     date,                                                       -- Update Date
    insert_user_id                  varchar2(30),                                               -- Insert User UID
    insert_date                     date                default sysdate,                        -- Insert Date
    update_user_id                  varchar2(30),                                               -- Update User UID

    constraint fk_27627836020300 foreign key(user_id) references sys_user(user_id),
    constraint pk_usr_quotation primary key(quotation_id)
    using index tablespace bobcat_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace bobcat_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  usr_quotation                                                  is 'Quotation info for additioanl user service';
comment on column usr_quotation.quotation_id                                     is 'Quotation UID (PK)';
comment on column usr_quotation.quotation_number                                 is 'Quotation Number - for display';
comment on column usr_quotation.issue_date                                       is 'Date issued';
comment on column usr_quotation.user_id                                          is 'User UID - Logged In User Id (sys_user.user_id)';
comment on column usr_quotation.provider_org_id                                  is 'Provider Org UID if applicable';
comment on column usr_quotation.provider_logo_path                               is 'Provider logo image path';
comment on column usr_quotation.provider_name                                    is 'Provider name - user name | org name | editable';
comment on column usr_quotation.provider_telephone                               is 'Provider land line number';
comment on column usr_quotation.provider_mobile                                  is 'Provider mobile number';
comment on column usr_quotation.provider_email                                   is 'Provider email - user emal | org email | editable';
comment on column usr_quotation.provider_address                                 is 'Provider address';
comment on column usr_quotation.provider_abn                                     is 'Provider ABN';
comment on column usr_quotation.provider_acn                                     is 'Provider ACN';
comment on column usr_quotation.client_org_id                                    is 'Client Org UID if applicable';
comment on column usr_quotation.client_user_id                                   is 'Client User UID if applicable';
comment on column usr_quotation.client_name                                      is 'Client name - user name | org name | editable';
comment on column usr_quotation.client_telephone                                 is 'Client land line number';
comment on column usr_quotation.client_mobile                                    is 'Client mobile number';
comment on column usr_quotation.client_email                                     is 'Client email - user emal | org email | editable';
comment on column usr_quotation.client_address                                   is 'Client address';
comment on column usr_quotation.net_amt                                          is 'Quotation net amount (exclude gst)';
comment on column usr_quotation.gst_amt                                          is 'GST amount';
comment on column usr_quotation.total_amt                                        is 'Qutation total amount (include gst)';
comment on column usr_quotation.description                                      is 'Description - for user to set brief comments';
comment on column usr_quotation.additional_remark                                is 'Additional remark';
comment on column usr_quotation.update_date                                      is 'Update Date';
comment on column usr_quotation.insert_user_id                                   is 'Insert User UID';
comment on column usr_quotation.insert_date                                      is 'Insert Date';
comment on column usr_quotation.update_user_id                                   is 'Update User UID';


/**
 * Table Name  : USR_QUOTATION
 * Data        : 
 */
