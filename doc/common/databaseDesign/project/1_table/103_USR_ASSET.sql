/**
 * Table Name  : USR_ASSET
 * Description : Asset Entry - Asset data entry for all organisation category
 */
drop table usr_asset cascade constraints;
purge recyclebin;

create table usr_asset (
    asset_id                        varchar2(30)                                        not null,   -- Asset Entry UID (PK)
    asset_year                      varchar2(4)                                         not null,   -- Year (ex. 2017)
    quarter_name                    varchar2(30)                                        not null,   -- Quarter Name (sys_common_code.quarter_name)
    org_id                          varchar2(30)                                        not null,   -- Organisation UID (sys_org.org_id)
    asset_type_id                   varchar2(30)                                        not null,   -- Asset Type UID (sys_asset_type.asset_type_id)
    asset_date                      date                                                not null,   -- Date of Asset data entry (can be set the last date of the quarter if user does not input)
    gross_amt                       number(12,2),                                                   -- Amount of gross
    gst_amt                         number(12,2),                                                   -- GST amount (calculate besed on applied_gst but can be changed by user)
    applied_gst                     number(12,2),                                                   -- Applied GST percentage (coming from sys_asset_type.gst_percentage, gst_amt can be changed by user)
    net_amt                         number(12,2),                                                   -- Net Asset Amt (gross_amt - gst_amt)
    is_completed                    varchar2(30)                                        not null,   -- Is Completed (sys_common_code.simple_yn, user can not update if completed)
    description                     varchar2(1000),                                                 -- Description (Particular)
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_usr_asset primary key(asset_id)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  usr_asset                     is 'Asset Entry (Asset data entry for all organisation category)';
comment on column usr_asset.asset_id            is 'Asset Entry UID (PK)';
comment on column usr_asset.asset_year          is 'Year (ex. 2017)';
comment on column usr_asset.quarter_name        is 'Quarter Name (sys_common_code.quarter_name)';
comment on column usr_asset.org_id              is 'Organisation UID (sys_org.org_id)';
comment on column usr_asset.asset_type_id       is 'Asset Type UID (sys_asset_type.asset_type_id)';
comment on column usr_asset.asset_date          is 'Date of Asset data entry (can be set the last date of the quarter if user does not input)';
comment on column usr_asset.gross_amt           is 'Amount of gross';
comment on column usr_asset.gst_amt             is 'GST amount (calculate besed on applied_gst but can be changed by user)';
comment on column usr_asset.applied_gst         is 'Applied GST percentage (coming from sys_expense_type.gst_percentage, gst_amt can be changed by user)';
comment on column usr_asset.net_amt             is 'Net Asset Amt (gross_amt - gst_amt)';
comment on column usr_asset.is_completed        is 'Is Completed (sys_common_code.simple_yn, user can not update if completed)';
comment on column usr_asset.description         is 'Description (Particular)';
comment on column usr_asset.insert_user_id      is 'Insert User UID';
comment on column usr_asset.insert_date         is 'Insert Date';
comment on column usr_asset.update_user_id      is 'Update User UID';
comment on column usr_asset.update_date         is 'Update Date';


/**
 * Table Name  : USR_ASSET
 * Data        : 
 */
