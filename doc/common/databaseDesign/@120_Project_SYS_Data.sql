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
 order by code_type,
       common_code
;

/**
 * Category    : SYS
 * Table ID    : SYS_MENU
 * Table Name  : Menu Info
 * Description : Use Excel file to initialise data
 */
delete sys_menu;


/**
 * Category    : SYS
 * Table ID    : SYS_USER
 * Table Name  : System User Info
 * Description : Use Excel file to initialise data
 */
delete sys_user;


/**
 * Category    : SYS
 * Table ID    : SYS_AUTH_GROUP
 * Table Name  : Authority Type Info
 * Description : 
 */
delete sys_auth_group;

insert into sys_auth_group values('0', 'System Admin',         'System Administrator',                                  'Y',    '0',    sysdate,    '',     '');
insert into sys_auth_group values('1', 'General Admin',        'General Administrator',                                 'Y',    '0',    sysdate,    '',     '');
insert into sys_auth_group values('2', 'Org Representative A', 'Organisation Representative (Organisation Category A)', 'Y',    '0',    sysdate,    '',     '');
insert into sys_auth_group values('3', 'Org Representative B', 'Organisation Representative (Organisation Category B)', 'Y',    '0',    sysdate,    '',     '');
insert into sys_auth_group values('4', 'Org Representative C', 'Organisation Representative (Organisation Category C)', 'Y',    '0',    sysdate,    '',     '');
insert into sys_auth_group values('5', 'Org Representative D', 'Organisation Representative (Organisation Category D)', 'Y',    '0',    sysdate,    '',     '');
insert into sys_auth_group values('6', 'General User A',       'General User (Organisation Category A)',                'Y',    '0',    sysdate,    '',     '');
insert into sys_auth_group values('7', 'General User B',       'General User (Organisation Category B)',                'Y',    '0',    sysdate,    '',     '');
insert into sys_auth_group values('8', 'General User C',       'General User (Organisation Category C)',                'Y',    '0',    sysdate,    '',     '');
insert into sys_auth_group values('9', 'General User D',       'General User (Organisation Category D)',                'Y',    '0',    sysdate,    '',     '');
insert into sys_auth_group values('Z', 'Not Selected',         'Not Selected',                                          'Y',    '0',    sysdate,    '',     '');


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
 * Table ID    : SYS_ORG
 * Table Name  : Organisation Info
 * Description : 
 */
--delete sys_org;
insert into sys_org
select '0' as org_id,
       'HK Accounting' as legal_name,
       'HK Accounting' as trading_name,
       '13152584837' as abn,
       'jin@hkaccounting.com.au' as email,
       '7 Jeffcott Street, West Melbourne, VIC, 3003' as postal_address,
       to_date('2017-05-23', 'yyyy-mm-dd') as registred_date,
       'COMP' as entity_type,
       'HKST' as business_type,
       'A' as org_category,
       'YEAR' as base_type,
       'WTFORTN' as wage_type,
       null as revenue_range_from,
       null as revenue_range_to,
       'Y' as is_active,
       '0' as insert_user_id,
       sysdate as insert_date,
       null as update_user_id,
       null as update_date
  from dual
union
select to_char(id) as org_id,
       legal_name as legal_name,
       trading_name as trading_name,
       abn as abn,
       email as email,
       null as postal_address,
       registerdate as registred_date,
       case entity_type
            when 1 then 'SLTR'
            when 2 then 'COMP'
            when 3 then 'TRUST'
            when 4 then 'PRTN'
            when 5 then 'SUFN'
       end as entity_type,
       case business_type
            when 1 then 'GRSP'
            when 2 then 'WHLS'
            when 3 then 'REST'
            when 4 then 'HOSP'
            when 5 then 'CLNG'
            when 6 then 'HKST'
            when 7 then 'CONS'
            when 8 then 'MGSV'
            when 9 then 'BESL'
            when 10 then 'BAKE'
            when 11 then 'RESA'
            when 12 then 'SRVC'
            when 13 then 'NIL'
       end as business_type,
       case template_type
            when 1 then 'A'
            when 2 then 'B'
            when 3 then 'C'
            when 4 then 'D'
       end as org_category,
       case base_type
            when 1 then 'YEAR'
            when 2 then 'QUAR'
       end as base_type,
       case wage_type
            when 1 then 'WTFORTN'
            when 2 then 'WTWEEK'
       end as wage_type,
       to_number(revenue_range_from) as revenue_range_from,
       to_number(revenue_range_to) as revenue_range_to,
       'Y' as is_active,
       '0' as insert_user_id,
       sysdate as insert_date,
       null as update_user_id,
       null as update_date
  from dm_users
 where legal_name is not null
;


/**
 * Category    : SYS
 * Table ID    : SYS_FINANCIAL_PERIOD
 * Table Name  : Quarter type by financial year
 * Description : 
 */
delete sys_financial_period;
insert into sys_financial_period
select account_year as period_year,
       'Q'||quarter as quarter_code,
       period_name as financial_year,
       case when quarter = '3' then 'MAR'
            when quarter = '4' then 'JUN'
            when quarter = '1' then 'SEP'
            when quarter = '2' then 'DEC'
       end as quarter_name,
       case quarter
            when '3' then to_date(account_year||'0101', 'yyyymmdd')
            when '4' then to_date(account_year||'0401', 'yyyymmdd')
            when '1' then to_date(account_year-1||'0701', 'yyyymmdd')
            when '2' then to_date(account_year-1||'1001', 'yyyymmdd')
       end as date_from,
       case quarter
            when '3' then to_date(account_year||'0331', 'yyyymmdd')
            when '4' then to_date(account_year||'0630', 'yyyymmdd')
            when '1' then to_date(account_year-1||'0930', 'yyyymmdd')
            when '2' then to_date(account_year-1||'1231', 'yyyymmdd')
       end as date_to,
       '0' as insert_user_id,
       sysdate as insert_date,
       null as update_user_id,
       null as update_date
  from dm_period
 order by period_year desc,
       quarter_code desc
;


/**
 * Category    : SYS
 * Table ID    : SYS_USER
 * Table Name  : User Info
 * Description : Use Excel file to initialise data
 */
insert into sys_user
select '0' as user_id,
       'Administrator' as user_name,
       'admin' as login_id,
       'admin' as login_password,
       '0' as org_id,
       '0' as auth_group_id,
       'en' as language,
       'theme000' as theme_type,
       'INTERNAL' as user_type,
       'jin@hkaccounting.com.au' as email,
       50 as max_row_per_page,
       5 as page_num_per_page,
       'NU' as user_status,
       '/webapp/shared/resource/image/photo/DefaultUser_128_Black.png' as photo_path,
       'Y' as is_active,
       '0' as insert_user_id,
       sysdate as insert_date,
       null as update_user_id,
       null as update_date
  from dual
union
select '1' as user_id,
       'Developer' as user_name,
       'dustin' as login_id,
       'dustin' as login_password,
       '0' as org_id,
       '0' as auth_group_id,
       'en' as language,
       'theme000' as theme_type,
       'INTERNAL' as user_type,
       'jin@hkaccounting.com.au' as email,
       50 as max_row_per_page,
       5 as page_num_per_page,
       'NU' as user_status,
       '/webapp/shared/resource/image/photo/DefaultUser_128_Black.png' as photo_path,
       'Y' as is_active,
       '0' as insert_user_id,
       sysdate as insert_date,
       null as update_user_id,
       null as update_date
  from dual
union
select to_char(id) as user_id,
       name as user_name,
       username as login_id,
       case when password_text is null then 'Password_01'
       else password_text
       end as login_password,
       to_char(id) as org_id,
       'Z' as auth_group_id,
       'en' as language,
       'theme000' as theme_type,
       'EXTERNAL' as user_type,
       email as email,
       50 as max_row_per_page,
       5 as page_num_per_page,
       'NU' as user_status,
       '/webapp/shared/resource/image/photo/DefaultUser_128_Black.png' as photo_path,
       'Y' as is_active,
       '0' as insert_user_id,
       sysdate as insert_date,
       null as update_user_id,
       null as update_date
  from dm_users
 where legal_name is not null
;


/**
 * Category    : SYS
 * Table ID    : SYS_TAX_MASTER
 * Table Name  : Tax Master
 * Description : 
 */
insert into sys_tax_master
select idx as tax_master_id,
       year as tax_year,
       quarter as quarter_code,
       case wage_type
            when 1 then 'FORTNIGHTLY'
            when 2 then 'WEEKLY'
       end as wage_type,
       gross as gross,
       resident as resident,
       nonresident as non_resident,
       '0' as insert_user_id,
       sysdate as insert_date,
       null as update_user_id,
       null as update_date
  from dm_tax
;


/**
 * Category    : SYS
 * Table ID    : SYS_INCOME_TYPE
 * Table Name  : Income Entry Type
 * Description : 
 */
--delete sys_income_type;
insert into sys_income_type
select idx as income_type_id,
       case template_type
            when 1 then 'A'
            when 2 then 'B'
            when 3 then 'C'
            when 4 then 'D'
       end as org_category,
       case trim(income_type)
            when 'Other' then 'IMOTI' -- idx 1
            when 'Commission' then 'IMCOM' -- idx 2, 10
            when 'Commission Received' then 'COMMISSION' -- idx 2, 10
            when 'Migration' then 'IMMGR' -- idx 3
            when 'Insurance' then 'IMINS' -- idx 4
            when 'Sub Lease' then 'IMSLS' -- idx 5
            when 'Translation' then 'IMTRN' -- idx 6
            when 'Interest' then 'IMINTR' -- idx 7
            when 'Sales' then 'IMSAL' -- idx 9
            when 'Professional Fees' then 'IMPRF' -- idx 11
            when 'Consulting Fees' then 'IMCNS' -- idx 12
            when 'Management Fees' then 'IMMGT' -- idx 13
            when 'Others' then 'IMOTS' -- idx 8, 14
       end as income_type,
       null as parent_income_type,
       trim(income_type) as description,
       'Y' as is_apply_gst,
       10 as gst_percentage,
       null as account_code,
       case template_type
            when 1 then '01'||lpad(to_char(idx), 2, '0')||'00'
            when 2 then '02'||lpad(to_char(idx), 2, '0')||'00'
            when 3 then '03'||lpad(to_char(idx), 2, '0')||'00'
            when 4 then '04'||lpad(to_char(idx), 2, '0')||'00'
       end as sort_order,
       '0' as insert_user_id,
       sysdate as insert_date,
       null as update_user_id,
       null as update_date
  from dm_income_type
 where income_type not like 'Shop%'
;


/**
 * Category    : SYS
 * Table ID    : SYS_EXPENSE_TYPE
 * Table Name  : Expenditure Entry Type
 * Description : import excel file
 */


/**
 * Category    : SYS
 * Table ID    : SYS_ASSET_TYPE
 * Table Name  : Asset Entry Type
 * Description : 
 */
--delete sys_asset_type;
insert into sys_asset_type
select idx as asset_type_id,
       case template_id
            when 1 then 'A'
            when 2 then 'B'
            when 3 then 'C'
            when 4 then 'D'
       end as org_category,
       case translate(main_type_name, '&', '/')
            when 'Equipments' then 'AMEQP'
            when 'Equipment' then 'AMEQP'
            when 'Motor Vehicle' then 'AMMV'
            when 'Furniture' then 'AMFURN'
            when 'Fittings / Renovation' then 'AMFITRN'
            when 'Fitting / Renovation' then 'AMFITRN'
            when 'Properties' then 'AMPROPT'
            when 'Others' then 'AMOTHR'
       end as asset_type,
       null as parent_asset_type,
       translate(main_type_name, '&', '/') as description,
       'N' as is_apply_gst,
       0 as gst_percentage,
       null as account_code,
       case template_id
            when 1 then '01'||lpad(to_char(idx), 2, '0')||'00'
            when 2 then '02'||lpad(to_char(idx), 2, '0')||'00'
            when 3 then '03'||lpad(to_char(idx), 2, '0')||'00'
            when 4 then '04'||lpad(to_char(idx), 2, '0')||'00'
       end as sort_order,
       '0' as insert_user_id,
       sysdate as insert_date,
       null as update_user_id,
       null as update_date
  from dm_asset_main_type
;


/**
 * Category    : SYS
 * Table ID    : SYS_REPAYMENT_TYPE
 * Table Name  : Repayment Entry Type
 * Description : 
 */
delete sys_repayment_type;
insert into sys_repayment_type
select idx as repayment_type_id,
       'A' as org_category,
       case translate(main_type_name, '&', '/')
            when 'Equipments' then 'RMEQP'
            when 'Equipment' then 'RMEQP'
            when 'Motor Vehicle' then 'RMMV'
            when 'Furniture' then 'RMFURN'
            when 'Fittings / Renovation' then 'RMFITRN'
            when 'Properties' then 'RMPROPT'
            when 'Others' then 'RMOTHR'
       end as repayment_type,
       null as parent_repayment_type,
       translate(main_type_name, '&', '/') as description,
       'N' as is_apply_gst,
       0 as gst_percentage,
       null as account_code,
       '01'||lpad(to_char(idx), 2, '0')||'00' as org_category,
       '0' as insert_user_id,
       sysdate as insert_date,
       null as update_user_id,
       null as update_date
  from dm_payment_main_type
union
select idx+6 as repayment_type_id,
       'B' as org_category,
       case translate(main_type_name, '&', '/')
            when 'Equipments' then 'RMEQP'
            when 'Equipment' then 'RMEQP'
            when 'Motor Vehicle' then 'RMMV'
            when 'Furniture' then 'RMFURN'
            when 'Fittings / Renovation' then 'RMFITRN'
            when 'Properties' then 'RMPROPT'
            when 'Others' then 'RMOTHR'
       end as repayment_type,
       null as parent_repayment_type,
       translate(main_type_name, '&', '/') as description,
       'N' as is_apply_gst,
       0 as gst_percentage,
       null as account_code,
       '02'||lpad(to_char(idx), 2, '0')||'00' as org_category,
       '0' as insert_user_id,
       sysdate as insert_date,
       null as update_user_id,
       null as update_date
  from dm_payment_main_type
union
select idx+12 as repayment_type_id,
       'C' as org_category,
       case translate(main_type_name, '&', '/')
            when 'Equipments' then 'RMEQP'
            when 'Equipment' then 'RMEQP'
            when 'Motor Vehicle' then 'RMMV'
            when 'Furniture' then 'RMFURN'
            when 'Fittings / Renovation' then 'RMFITRN'
            when 'Properties' then 'RMPROPT'
            when 'Others' then 'RMOTHR'
       end as repayment_type,
       null as parent_repayment_type,
       translate(main_type_name, '&', '/') as description,
       'N' as is_apply_gst,
       0 as gst_percentage,
       null as account_code,
       '03'||lpad(to_char(idx), 2, '0')||'00' as org_category,
       '0' as insert_user_id,
       sysdate as insert_date,
       null as update_user_id,
       null as update_date
  from dm_payment_main_type
;


/**
 * Category    : SYS
 * Table ID    : SYS_BORROWING_TYPE
 * Table Name  : Borrowing Entry Type
 * Description : Loan Borrowing type
 */
delete sys_borrowing_type;
insert into sys_borrowing_type
select idx as borrowing_type_id,
       'A' as org_category,
       case translate(main_type_name, '&', '/')
            when 'Equipments' then 'BMEQP'
            when 'Equipment' then 'BMEQP'
            when 'Motor Vehicle' then 'BMMV'
            when 'Furniture' then 'BMFURN'
            when 'Fittings / Renovation' then 'BMFITRN'
            when 'Properties' then 'BMPROPT'
            when 'Others' then 'BMOTHR'
       end as borrowing_type,
       null as parent_borrowing_type,
       translate(main_type_name, '&', '/') as description,
       'N' as is_apply_gst,
       0 as gst_percentage,
       null as account_code,
       '01'||lpad(to_char(idx), 2, '0')||'00' as org_category,
       '0' as insert_user_id,
       sysdate as insert_date,
       null as update_user_id,
       null as update_date
  from dm_lending_main_type
union
select idx+6 as borrowing_type_id,
       'B' as org_category,
       case translate(main_type_name, '&', '/')
            when 'Equipments' then 'BMEQP'
            when 'Equipment' then 'BMEQP'
            when 'Motor Vehicle' then 'BMMV'
            when 'Furniture' then 'BMFURN'
            when 'Fittings / Renovation' then 'BMFITRN'
            when 'Properties' then 'BMPROPT'
            when 'Others' then 'BMOTHR'
       end as borrowing_type,
       null as parent_borrowing_type,
       translate(main_type_name, '&', '/') as description,
       'N' as is_apply_gst,
       0 as gst_percentage,
       null as account_code,
       '02'||lpad(to_char(idx), 2, '0')||'00' as org_category,
       '0' as insert_user_id,
       sysdate as insert_date,
       null as update_user_id,
       null as update_date
  from dm_lending_main_type
union
select idx+12 as borrowing_type_id,
       'C' as org_category,
       case translate(main_type_name, '&', '/')
            when 'Equipments' then 'BMEQP'
            when 'Equipment' then 'BMEQP'
            when 'Motor Vehicle' then 'BMMV'
            when 'Furniture' then 'BMFURN'
            when 'Fittings / Renovation' then 'BMFITRN'
            when 'Properties' then 'BMPROPT'
            when 'Others' then 'BMOTHR'
       end as borrowing_type,
       null as parent_borrowing_type,
       translate(main_type_name, '&', '/') as description,
       'N' as is_apply_gst,
       0 as gst_percentage,
       null as account_code,
       '03'||lpad(to_char(idx), 2, '0')||'00' as org_category,
       '0' as insert_user_id,
       sysdate as insert_date,
       null as update_user_id,
       null as update_date
  from dm_lending_main_type
;



/**
 * Category    : SYS
 * Table ID    : SYS_LENDING_TYPE
 * Table Name  : Lending Entry Type
 * Description : 
 */
delete sys_lending_type;
insert into sys_lending_type
select idx as lending_type_id,
       'A' as org_category,
       case translate(main_type_name, '&', '/')
            when 'Equipments' then 'LMEQP'
            when 'Equipment' then 'LMEQP'
            when 'Motor Vehicle' then 'LMMV'
            when 'Furniture' then 'LMFURN'
            when 'Fittings / Renovation' then 'LMFITRN'
            when 'Properties' then 'LMPROPT'
            when 'Others' then 'LMOTHR'
       end as lending_type,
       null as parent_lending_type,
       translate(main_type_name, '&', '/') as description,
       'N' as is_apply_gst,
       0 as gst_percentage,
       null as account_code,
       '01'||lpad(to_char(idx), 2, '0')||'00' as org_category,
       '0' as insert_user_id,
       sysdate as insert_date,
       null as update_user_id,
       null as update_date
  from dm_lending_main_type
union
select idx+6 as lending_type_id,
       'B' as org_category,
       case translate(main_type_name, '&', '/')
            when 'Equipments' then 'LMEQP'
            when 'Equipment' then 'LMEQP'
            when 'Motor Vehicle' then 'LMMV'
            when 'Furniture' then 'LMFURN'
            when 'Fittings / Renovation' then 'LMFITRN'
            when 'Properties' then 'LMPROPT'
            when 'Others' then 'LMOTHR'
       end as lending_type,
       null as parent_lending_type,
       translate(main_type_name, '&', '/') as description,
       'N' as is_apply_gst,
       0 as gst_percentage,
       null as account_code,
       '02'||lpad(to_char(idx), 2, '0')||'00' as org_category,
       '0' as insert_user_id,
       sysdate as insert_date,
       null as update_user_id,
       null as update_date
  from dm_lending_main_type
union
select idx+12 as lending_type_id,
       'C' as org_category,
       case translate(main_type_name, '&', '/')
            when 'Equipments' then 'LMEQP'
            when 'Equipment' then 'LMEQP'
            when 'Motor Vehicle' then 'LMMV'
            when 'Furniture' then 'LMFURN'
            when 'Fittings / Renovation' then 'LMFITRN'
            when 'Properties' then 'LMPROPT'
            when 'Others' then 'LMOTHR'
       end as lending_type,
       null as parent_lending_type,
       translate(main_type_name, '&', '/') as description,
       'N' as is_apply_gst,
       0 as gst_percentage,
       null as account_code,
       '03'||lpad(to_char(idx), 2, '0')||'00' as org_category,
       '0' as insert_user_id,
       sysdate as insert_date,
       null as update_user_id,
       null as update_date
  from dm_lending_main_type
;