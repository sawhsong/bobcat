/**
 * Table Name  : SYS_MENU
 * Description : Menu Info - Use Excel file to initialise data (SYS_MENU.xlsx)
 */
drop table sys_menu cascade constraints;
purge recyclebin;

create table sys_menu (
    menu_id                         varchar2(30)                                        not null,   -- Menu UID (PK)
    parent_menu_id                  varchar2(30),                                                   -- Parent menu UID
    menu_name_ko                    varchar2(500)                                       not null,   -- Menu name (Korean)
    menu_name_en                    varchar2(500)                                       not null,   -- Menu name (English)
    menu_url                        varchar2(500),                                                  -- Menu URL
    menu_icon                       varchar2(100),                                                  -- Menu Icon
    sort_order                      varchar2(10),                                                   -- Sort order
    description                     varchar2(1000),                                                 -- Description
    is_active                       varchar2(1),                                                    -- Is active ?
    insert_user_id                  varchar2(30),                                                   -- Insert User UID
    insert_date                     date                        default sysdate,                    -- Insert Date
    update_user_id                  varchar2(30),                                                   -- Update User UID
    update_date                     date,                                                           -- Update Date

    constraint pk_sys_menu primary key(menu_id)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  sys_menu                 is 'Menu Info';
comment on column sys_menu.menu_id         is 'Menu UID (PK)';
comment on column sys_menu.parent_menu_id  is 'Parent menu UID';
comment on column sys_menu.menu_name_ko    is 'Menu name (Korean)';
comment on column sys_menu.menu_name_en    is 'Menu name (English)';
comment on column sys_menu.menu_url        is 'Menu URL';
comment on column sys_menu.menu_icon       is 'Menu Icon';
comment on column sys_menu.sort_order      is 'Sort order';
comment on column sys_menu.description     is 'Description';
comment on column sys_menu.is_active       is 'Is active?';
comment on column sys_menu.insert_user_id  is 'Insert User UID';
comment on column sys_menu.insert_date     is 'Insert Date';
comment on column sys_menu.update_user_id  is 'Update User UID';
comment on column sys_menu.update_date     is 'Update Date';


/**
 * Table Name  : SYS_MENU
 * Data        : Menu Info - Use Excel file to initialise data (SYS_MENU.xlsx)
 */
