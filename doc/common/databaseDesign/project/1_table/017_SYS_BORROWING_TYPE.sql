/**
 * Table Name  : SYS_BORROWING_TYPE
 * Description : Loan Borrowing type
 */
drop table sys_borrowing_type cascade constraints;
purge recyclebin;

create table sys_borrowing_type (
    borrowing_type_id               varchar2(30)                                        not null,   -- Borrowing Type UID (PK)
    org_category                    varchar2(30)                                        not null,   -- Organisation Category (UK) (sys_common_code.org_category)
    borrowing_type                  varchar2(30)                                        not null,   -- Borrowing Type (UK) (sys_common_code.borrowing_main_type or sys_common_code.borrowing_sub_type)
    parent_borrowing_type           varchar2(30),                                                   -- Parent Borrowing Type (always sys_common_code.borrowing_main_type)
    description                     varchar2(100)                                       not null,   -- Borrowing Type Description (comes from sys_common_code.borrowing_main_type.description_en, user can change)
    is_apply_gst                    varchar2(30),                                                   -- Is Apply GST (sys_common_code.simple_yn)
    gst_percentage                  number(12,0),                                                   -- GST Percentage
    account_code                    varchar2(30),                                                   -- Account Code
    sort_order                      varchar2(30),                                                   -- Sort Order
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_borrowing_type primary key(borrowing_type_id),
    constraint uk_sys_borrowing_type unique(org_category, borrowing_type)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_borrowing_type                       is 'Borrowing Entry Type';
comment on column sys_borrowing_type.borrowing_type_id     is 'Borrowing Type UID (PK)';
comment on column sys_borrowing_type.org_category          is 'Organisation Category (UK) (sys_common_code.org_category)';
comment on column sys_borrowing_type.borrowing_type        is 'Borrowing Type (UK) (sys_common_code.borrowing_main_type or sys_common_code.borrowing_sub_type)';
comment on column sys_borrowing_type.parent_borrowing_type is 'Parent Borrowing Type (always sys_common_code.borrowing_main_type)';
comment on column sys_borrowing_type.description           is 'Borrowing Type Description (comes from sys_common_code.borrowing_main_type.description_en, user can change)';
comment on column sys_borrowing_type.is_apply_gst          is 'Is Apply GST (sys_common_code.simple_yn)';
comment on column sys_borrowing_type.gst_percentage        is 'GST Percentage';
comment on column sys_borrowing_type.account_code          is 'Account Code';
comment on column sys_borrowing_type.sort_order            is 'Sort Order';
comment on column sys_borrowing_type.insert_user_id        is 'Insert User UID';
comment on column sys_borrowing_type.insert_date           is 'Insert Date';
comment on column sys_borrowing_type.update_user_id        is 'Update User UID';
comment on column sys_borrowing_type.update_date           is 'Update Date';


/**
 * Table Name  : SYS_BORROWING_TYPE
 * Data        : 
 */
