/**
 * Category    : SYS
 * Table ID    : SYS_COMMON_CODE
 * Table Name  : Common Lookup Code
 * Description : Import Excel file
 */
delete sys_common_code;

insert into sys_common_code values('SIMPLE_YN', '0000000000', 'Simple YN', 'Simple YN', 'Simple YN', 'SIMPLE_YN_0000000000', '000', 'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('SIMPLE_YN', 'Y',          'Yes',       'Yes',       'Yes',       'SIMPLE_YN_Y',          '001', 'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('SIMPLE_YN', 'N',          'No',        'No',        'No',        'SIMPLE_YN_N',          '002', 'Y', 'Y', '0', sysdate, null, null);

insert into sys_common_code values('USE_YN', '0000000000', 'Is Used',    '사용여부', 'Use or Not', 'USE_YN_0000000000', '000',   'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('USE_YN', 'Y',          'Use',        '사용',     'Use',        'USE_YN_Y',          '001',   'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('USE_YN', 'N',          'Not in Use', '미사용',   'Not Use',    'USE_YN_N',          '002',   'Y', 'Y', '0', sysdate, null, null);

insert into sys_common_code values('LANGUAGE_TYPE', '0000000000', 'Language Type', '언어타입',  'Language Type', 'LANGUAGE_TYPE_0000000000', '000', 'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('LANGUAGE_TYPE', 'EN',         'English',       '영어',      'English',       'LANGUAGE_TYPE_EN',         '001', 'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('LANGUAGE_TYPE', 'KO',         'Korean',        '한국어',    'Korean',        'LANGUAGE_TYPE_KO',         '002', 'Y', 'Y', '0', sysdate, null, null);

insert into sys_common_code values('BOARD_TYPE', '0000000000', 'Board Type',      '게시판구분', 'Board Type',      'BOARD_TYPE_0000000000', '000', 'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('BOARD_TYPE', 'FREE',       'Free Board',      '자유게시판', 'Free Board',      'BOARD_TYPE_FREE',       '001', 'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('BOARD_TYPE', 'NOTICE',     'Notice Board',    '공지사항',   'Notice Board',    'BOARD_TYPE_NOTICE',     '002', 'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('BOARD_TYPE', 'REPOSITORY', 'Data Repository', '자료실',     'Data Repository', 'BOARD_TYPE_REPOSITORY', '003', 'Y', 'Y', '0', sysdate, null, null);

insert into sys_common_code values('BOARD_SEARCH_TYPE', '0000000000', 'Board Search Criteria', '게시판검색형태', 'Board Search Criteria', 'BOARD_SEARCH_TYPE_0000000000', '000', 'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('BOARD_SEARCH_TYPE', 'SUBJECT',    'Subject',               '제목',            'Subject',               'BOARD_SEARCH_TYPE_SUBJECT',    '001', 'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('BOARD_SEARCH_TYPE', 'CONTENTS',   'Contents',              '내용',            'Contents',              'BOARD_SEARCH_TYPE_CONTENTS',   '002', 'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('BOARD_SEARCH_TYPE', 'WRITER',     'Writer',                '작성자',          'Writer',                'BOARD_SEARCH_TYPE_WRITER',     '003', 'Y', 'Y', '0', sysdate, null, null);

insert into sys_common_code values('USER_THEME_TYPE', '0000000000', 'User Theme Type', '사용자스킨구분', 'User Theme Type', 'USER_THEME_TYPE_0000000000', '000', 'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('USER_THEME_TYPE', 'THEME000',   'Bootstrap',       'Bootstrap',       'Bootstrap',       'USER_THEME_TYPE_000',        '001', 'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('USER_THEME_TYPE', 'THEME001',   'Smoothness',      'Smoothness',      'Smoothness',      'USER_THEME_TYPE_001',        '002', 'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('USER_THEME_TYPE', 'THEME002',   'Redmond',         'Redmond',         'Redmond',         'USER_THEME_TYPE_002',        '003', 'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('USER_THEME_TYPE', 'THEME003',   'Lightness',       'Lightness',       'Lightness',       'USER_THEME_TYPE_003',        '004', 'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('USER_THEME_TYPE', 'THEME004',   'Start',           'Start',           'Start',           'USER_THEME_TYPE_004',        '005', 'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('USER_THEME_TYPE', 'THEME005',   'Sunny',           'Sunny',           'Sunny',           'USER_THEME_TYPE_005',        '006', 'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('USER_THEME_TYPE', 'THEME006',   'Flick',           'Flick',           'Flick',           'USER_THEME_TYPE_006',        '007', 'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('USER_THEME_TYPE', 'THEME007',   'Flick',           'Pepper Grinder',  'Pepper Grinder',  'USER_THEME_TYPE_007',        '008', 'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('USER_THEME_TYPE', 'THEME008',   'Cupertino',       'Cupertino',       'Cupertino',       'USER_THEME_TYPE_008',        '009', 'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('USER_THEME_TYPE', 'THEME009',   'Cupertino',       'South Street',    'South Street',    'USER_THEME_TYPE_009',        '010', 'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('USER_THEME_TYPE', 'THEME010',   'Humanity',        'Humanity',        'Humanity',        'USER_THEME_TYPE_010',        '011', 'Y', 'Y', '0', sysdate, null, null);

insert into sys_common_code values('USER_TYPE', '0000000000', 'User Type',     'User Type',     'User Type',     'USER_TYPE_0000000000',    '000', 'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('USER_TYPE', 'INTERNAL',   'Internal User', 'Internal User', 'Internal User', 'USER_TYPE_TYPE_INTERNAL', '001', 'Y', 'Y', '0', sysdate, null, null);
insert into sys_common_code values('USER_TYPE', 'EXTERNAL',   'External User', 'External User', 'External User', 'USER_TYPE_TYPE_EXTERNAL', '002', 'Y', 'Y', '0', sysdate, null, null);

-- From PERCI
insert into sys_common_code
select lookup_type as code_type,
       '0000000000' as common_code,
       initcap(replace(lookup_type, '_', ' ')) as code_meaning,
       initcap(replace(lookup_type, '_', ' ')) as description_ko,
       initcap(replace(lookup_type, '_', ' ')) as description_en,
       lookup_type||'_'||'0000000000' as program_constants,
       '000' as sort_order,
       'Y' as is_active,
       'N' as is_default,
       '0' as insert_user_id,
       sysdate as insert_date,
       null as update_user_id,
       null as update_date
  from sys_common_lookups@perci
 where enabled_flag = 'Y'
   and lookup_type not in ('ORG_RELATIONSHIP', 'EXTENSION_CHANGES') -- Duplicated codes
union
select lookup_type as code_type,
       lookup_code as common_code,
       meaning as description_ko,
       description as description_ko,
       description as description_en,
       lookup_type||'_'||lookup_code as program_constants,
       lpad(to_char(row_number() over (partition by lookup_type order by lookup_type, lookup_code)), 3, '0') as sort_order,
       'Y' as is_active,
       'N' as is_default,
       '0' as insert_user_id,
       sysdate as insert_date,
       null as update_user_id,
       null as update_date
  from sys_common_lookups@perci
 where enabled_flag = 'Y'
   and lookup_type not in ('ORG_RELATIONSHIP', 'EXTENSION_CHANGES') -- Duplicated codes
 order by code_type,
       sort_order,
       common_code
;

-- Insert Duplicated data from PERCI
insert into sys_common_code
select distinct lookup_type as code_type,
       '0000000000' as common_code,
       initcap(replace(lookup_type, '_', ' ')) as code_meaning,
       initcap(replace(lookup_type, '_', ' ')) as description_ko,
       initcap(replace(lookup_type, '_', ' ')) as description_en,
       lookup_type||'_'||'0000000000' as program_constants,
       '000' as sort_order,
       'Y' as is_active,
       'N' as is_default,
       '0' as insert_user_id,
       sysdate as insert_date,
       null as update_user_id,
       null as update_date
  from sys_common_lookups@perci
 where enabled_flag = 'Y'
   and lookup_type in ('ORG_RELATIONSHIP', 'EXTENSION_CHANGES')
   and meaning not in ('EB Customer', 'EMA Prospect')
union
select distinct lookup_type as code_type,
       lookup_code as common_code,
       meaning as description_ko,
       description as description_ko,
       description as description_en,
       lookup_type||'_'||lookup_code as program_constants,
       lpad(to_char(row_number() over (partition by lookup_type order by lookup_type, lookup_code)), 3, '0') as sort_order,
       'Y' as is_active,
       'N' as is_default,
       '0' as insert_user_id,
       sysdate as insert_date,
       null as update_user_id,
       null as update_date
  from sys_common_lookups@perci
 where enabled_flag = 'Y'
   and lookup_type in ('ORG_RELATIONSHIP', 'EXTENSION_CHANGES')
   and meaning not in ('EB Customer', 'EMA Prospect')
 order by code_type,
       sort_order,
       common_code
;

/**
 * Category    : SYS
 * Table ID    : SYS_MENU
 * Table Name  : Menu Info
 * Description : Use Excel file to initialise data (@121_Project_Data_SYS_MENU.xlsx)
 */
delete sys_menu;
-- PERCI Menu
/*
 select connect_by_root sequence_number||'/'||sub_menu_id as my_root,
        substr(sys_connect_by_path(sequence_number||'/'||sub_menu_id, '^'), 2) as connect_path,
        level as my_level,
        connect_by_isleaf as is_leaf,
        menu_id,
        sub_menu_id,
        sequence_number,
        prompt,
        (select jsp_page
           from sys_user_function@perci
          where function_id = smd.function_id
        ) jsp_link
   from sys_menu_details@perci smd
connect by prior sub_menu_id = menu_id
  start with menu_id = (select menu_id
                          from sys_menus@perci
                         where user_menu_name = 'Entity_Responsibilities'
                       )
 order siblings by sequence_number
;
*/


/**
 * Category    : SYS
 * Table ID    : SYS_USER
 * Table Name  : System User Info
 * Description : Use Excel file to initialise data
 */
delete sys_user;

insert into sys_user values('0', 'Dustin', 'dustin', 'dustin', '0', '0', 'EN', 'THEME000', 'INTERNAL', 'dsa@entitysolutions.com.au', 50, 5, 'NU',
	'/shared/resource/image/photo/DefaultUser_128_Black.png', 'Y', 'System Admin - Dustin', 'Y', null, null, null, null, null, null, 'ipro-default', null, 'N', 'N', 'Y', '1',
	'0', sysdate, null, null
);
insert into sys_user values('1', 'Admin', 'admin', 'admin', '1', '1', 'EN', 'THEME000', 'INTERNAL', 'dsa@entitysolutions.com.au', 50, 5, 'NU',
	'/shared/resource/image/photo/DefaultUser_128_Black.png', 'Y', 'General Admin - Admin', 'Y', null, null, null, null, null, null, 'ipro-default', null, 'N', 'N', 'Y', '1',
	'0', sysdate, null, null
);

-- From PERCI
insert into sys_user
select user_id as user_id,
       user_name as user_name,
       user_name as login_id,
       password as login_password,
       person_id as person_id,
       'Z' as auth_group_id,
       'EN' as language,
       'THEME000' as theme_type,
       'INTERNAL' as user_type,
       email as email,
       50 as max_row_per_page,
       5 as page_num_per_page,
       'NU' as user_status,
       '/shared/resource/image/photo/DefaultUser_128_Black.png' as photo_path,
       decode(is_active, null, 'Y', is_active) as is_active,
       description as description,
       prop_to_portal as prop_to_portal,
       pin as pin,
       disabled_date as disabled_date,
       security_question_1 as security_question_1,
       security_question_answer_1 as security_question_answer_1,
       security_question_2 as security_question_2,
       security_question_answer_2 as security_question_answer_2,
       portal_skin as portal_skin,
       portal_security_role as portal_security_role,
       reset_password as reset_password,
       reset_term_condition as reset_term_condition,
       is_portal_user as is_portal_user,
       portal_org_profile_id as portal_org_profile_id,
       '0' as insert_user_id,
       sysdate as insert_date,
       null as update_user_id,
       null update_date
  from sys_users@perci
 where user_id not in ('0', '1')
 order by login_id, login_password
;


/**
 * Category    : SYS
 * Table ID    : SYS_AUTH_GROUP
 * Table Name  : Authority Type Info
 * Description : 
 */
delete sys_auth_group;

insert into sys_auth_group values('0', 'System Admin',         'System Administrator',          'Y',    '0',    sysdate,    null,     null);
insert into sys_auth_group values('1', 'General Admin',        'General Administrator',         'Y',    '0',    sysdate,    null,     null);
insert into sys_auth_group values('2', 'Dep Representative 1', 'Organisation Representative 1', 'Y',    '0',    sysdate,    null,     null);
insert into sys_auth_group values('3', 'Dep Representative 2', 'Organisation Representative 2', 'Y',    '0',    sysdate,    null,     null);
insert into sys_auth_group values('4', 'Dep Representative 3', 'Organisation Representative 3', 'Y',    '0',    sysdate,    null,     null);
insert into sys_auth_group values('5', 'General User 1',       'General User 1',                'Y',    '0',    sysdate,    null,     null);
insert into sys_auth_group values('6', 'General User 2',       'General User 2',                'Y',    '0',    sysdate,    null,     null);
insert into sys_auth_group values('7', 'General User 3',       'General User 3',                'Y',    '0',    sysdate,    null,     null);
insert into sys_auth_group values('Z', 'Not Selected',         'Not Selected',                  'Y',    '0',    sysdate,    null,     null);


/**
 * Category    : SYS
 * Table ID    : SYS_MENU_AUTH_LINK
 * Table Name  : Menu - Authority group mapping
 * Description : 
 */
delete sys_menu_auth_link;

insert into sys_menu_auth_link (
	select sys_auth_group.group_id,
	       sys_menu.menu_id,
	       0,
	       sysdate,
	       null,
	       null
	  from sys_auth_group,
	       sys_menu
	 where sys_auth_group.group_id = '0'
)
;


/**
 * Category    : SYS
 * Table ID    : SYS_COUNTRY_CURRENCY
 * Table Name  : Country & Currency code
 * Description : Use Excel file to initialise data (@122_Project_Data_SYS_COUNTRY_CURRENCY.xlsx)
 */
update sys_country_currency
   set country_name = initcap(country_name)
;


/**
 * Category    : SYS
 * Table ID    : SYS_BOARD
 * Table Name  : Bulletin board
 * Description : 
 */


/**
 * Category    : SYS
 * Table ID    : SYS_BOARD_FILE
 * Table Name  : Attached file for Bulletin board
 * Description : 
 */