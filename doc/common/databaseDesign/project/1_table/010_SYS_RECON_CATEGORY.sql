/**
 * Table Name  : SYS_RECON_CATEGORY
 * Description : Bank transaction reconciliation category
 */
drop table sys_recon_category cascade constraints;
purge recyclebin;

create table sys_recon_category (
    category_id                     varchar2(30)                                 not null,      -- Reconciliation category UID (PK)
    category_name                   varchar2(100)                                not null,      -- Reconciliation category name
    parent_category_id              varchar2(30),                                               -- Parent category ID
    is_apply_gst                    varchar2(1),                                                -- Is Apply GST (sys_common_code.simple_yn)
    gst_percentage                  number,                                                     -- GST Percentage
    account_code                    varchar2(30),                                               -- Account Code
    sort_order                      varchar2(30),                                               -- Sort Order
    insert_user_id                  varchar2(30),                                               -- Insert User UID
    insert_date                     date                default sysdate,                        -- Insert Date
    update_user_id                  varchar2(30),                                               -- Update User UID
    update_date                     date,                                                       -- Update Date

    constraint pk_sys_recon_category primary key(category_id),
    constraint uk_4913649645400 unique(category_name)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_recon_category                                             is 'Bank transaction reconciliation category';
comment on column sys_recon_category.category_id                                 is 'Reconciliation category UID (PK)';
comment on column sys_recon_category.category_name                               is 'Reconciliation category name';
comment on column sys_recon_category.parent_category_id                          is 'Parent category ID';
comment on column sys_recon_category.is_apply_gst                                is 'Is Apply GST (sys_common_code.simple_yn)';
comment on column sys_recon_category.gst_percentage                              is 'GST Percentage';
comment on column sys_recon_category.account_code                                is 'Account Code';
comment on column sys_recon_category.sort_order                                  is 'Sort Order';
comment on column sys_recon_category.insert_user_id                              is 'Insert User UID';
comment on column sys_recon_category.insert_date                                 is 'Insert Date';
comment on column sys_recon_category.update_user_id                              is 'Update User UID';
comment on column sys_recon_category.update_date                                 is 'Update Date';


/**
 * Table Name  : SYS_RECON_CATEGORY
 * Data        : 
 */
