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
/*
select '0200' as category_id,
       'Expense' as category_name,
       '' as parent_category_id,
       'N' as is_apply_gst,
       0 as gst_percentage,
       '' as account_code,
       '0200' as sort_order,
       '1' as insert_user_id,
       sysdate as insert_date,
       null as update_user_id,
       null as update_date
  from dual
union
select '02'||lpad(to_char(rownum), 2, '0') as category_id,
       category_name,
       '0200' as parent_category_id,
       is_apply_gst,
       gst_percentage,
       account_code,
       '02'||lpad(to_char(rownum), 2, '0') as sort_order,
       insert_user_id,
       insert_date,
       update_user_id,
       update_date
  from (select to_char(expense_type_id) as category_id,
               description as category_name,
               '02' as parent_category_id,
               'N' as is_apply_gst,
               0 as gst_percentage,
               to_char(account_code) as account_code,
               '02'||substr(sort_order, 3) as sort_order,
               '1' as insert_user_id,
               sysdate as insert_date,
               null as update_user_id,
               null as update_date
          from temp_expense_type
         where org_category = 'A'
           and parent_expense_type is not null
         order by category_name
       )
;
*/