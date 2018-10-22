/**
 * Table Name  : SYS_ORG
 * Description : Organisation Info
 */
drop table sys_org cascade constraints;
purge recyclebin;

create table sys_org (
    org_id                          varchar2(30)                                        not null,   -- Organisation UID (PK)
    legal_name                      varchar2(300)                                       not null,   -- Org legal name
    trading_name                    varchar2(300),                                                  -- Org trading name
    abn                             varchar2(30),                                                   -- Org ABN
    email                           varchar2(100),                                                  -- Org Email
    postal_address                  varchar2(500),                                                  -- Org Postal Address
    registered_date                 date,                                                           -- Registered Date
    entity_type                     varchar2(30)                                        not null,   -- Entity Type - sys_common_code.entity_type
    business_type                   varchar2(30)                                        not null,   -- Business Type - sys_common_code.business_type
    org_category                    varchar2(30)                                        not null,   -- Organisation Catetory - sys_common_code.org_category
    base_type                       varchar2(30)                                        not null,   -- Base Type - sys_common_code.base_type
    wage_type                       varchar2(30)                                        not null,   -- Wage Type - sys_common_code.wage_type
    revenue_range_from              number(12,2),                                                   -- Revenue Range From
    revenue_range_to                number(12,2),                                                   -- Revenue Range To
    is_active                       varchar2(1),                                                    -- Is Active
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_org primary key(org_id)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_org                    is 'Organisation Info';
comment on column sys_org.org_id             is 'Organisation UID (PK)';
comment on column sys_org.legal_name         is 'Org legal name';
comment on column sys_org.trading_name       is 'Org trading name';
comment on column sys_org.abn                is 'Org ABN';
comment on column sys_org.email              is 'Org Email';
comment on column sys_org.postal_address     is 'Org Postal Address';
comment on column sys_org.registered_date    is 'Registered Date';
comment on column sys_org.entity_type        is 'Entity Type - sys_common_code.entity_type';
comment on column sys_org.business_type      is 'Business Type - sys_common_code.business_type';
comment on column sys_org.org_category       is 'Organisation Catetory - sys_common_code.org_category';
comment on column sys_org.base_type          is 'Base Type - sys_common_code.base_type';
comment on column sys_org.wage_type          is 'Wage Type - sys_common_code.wage_type';
comment on column sys_org.revenue_range_from is 'Revenue Range From';
comment on column sys_org.revenue_range_to   is 'Revenue Range To';
comment on column sys_org.is_active          is 'Is active?';
comment on column sys_org.insert_user_id     is 'Insert User UID';
comment on column sys_org.insert_date        is 'Insert Date';
comment on column sys_org.update_user_id     is 'Update User UID';
comment on column sys_org.update_date        is 'Update Date';


/**
 * Table Name  : SYS_ORG
 * Data        : 
 */
