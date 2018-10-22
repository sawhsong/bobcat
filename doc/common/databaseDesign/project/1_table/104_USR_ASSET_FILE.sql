/**
 * Table Name  : USR_ASSET_FILE
 * Description : Attached file for USR_ASSET table
 */
drop table usr_asset_file cascade constraints;
purge recyclebin;

create table usr_asset_file (
    file_id                         varchar2(30)                                        not null,   -- File item UID (PK)
    asset_id                        varchar2(30)                                        not null,   -- Asset UID (usr_asset.asset_id)
    original_name                   varchar2(1000)                                      not null,   -- Real file name
    new_name                        varchar2(1000)                                      not null,   -- System defined file name
    file_type                       varchar2(300),                                                  -- File type
    file_icon                       varchar2(1000),                                                 -- File Icon path and name
    file_size                       number(12,2),                                                   -- File size (KB)
    repository_path                 varchar2(1000)                                      not null,   -- Saved file path
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint fk_usr_asset_file foreign key(asset_id) references usr_asset(asset_id),
    constraint pk_usr_asset_file primary key(file_id)

    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  usr_asset_file                    is 'Attached file for Bulletin board';
comment on column usr_asset_file.file_id            is 'File item UID (PK)';
comment on column usr_asset_file.asset_id           is 'Asset UID (usr_asset.asset_id)';
comment on column usr_asset_file.original_name      is 'Real file name';
comment on column usr_asset_file.new_name           is 'System defined file name';
comment on column usr_asset_file.file_type          is 'File type';
comment on column usr_asset_file.file_icon          is 'File Icon path and name';
comment on column usr_asset_file.file_size          is 'File size (KB)';
comment on column usr_asset_file.repository_path    is 'Saved file path';
comment on column usr_asset_file.insert_user_id     is 'Insert User UID';
comment on column usr_asset_file.insert_date        is 'Insert Date';
comment on column usr_asset_file.update_user_id     is 'Update User UID';
comment on column usr_asset_file.update_date        is 'Update Date';


/**
 * Table Name  : USR_ASSET_FILE
 * Data        : 
 */
