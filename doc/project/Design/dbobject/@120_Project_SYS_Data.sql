/**
 * Category    : SYS
 * Table ID    : SYS_COMMON_CODE
 * Table Name  : Common Lookup Code
 * Description : Import Excel file
 */
delete sys_common_code;

-- Entity Type
insert into sys_common_code
select 'ENTITY_TYPE' as code_type,
       '0000000000' as common_code,
       'Entity Type' as description_ko,
       'Entity Type' as description_en,
       'ENTITY_TYPE_0000000000' as program_constants,
       '000' as sort_order,
       'Y' as is_active,
       'Y' as is_default,
       '0' as insert_user,
       sysdate as insert_date,
       null as update_user,
       null as update_date
  from dual
union
select 'ENTITY_TYPE' as code_type,
       replace(upper(entity_type), ' ', '_') as common_code,
       entity_type as description_ko,
       entity_type as description_en,
       'ENTITY_TYPE_'||replace(upper(entity_type), ' ', '_') as program_constants,
       lpad(idx, 3, '0') as sort_order,
       'Y' as is_active,
       'Y' as is_default,
       '0' as insert_user,
       sysdate as insert_date,
       null as update_user,
       null as update_date
  from (select *
          from dm_entity_type
         order by idx
       )
 order by sort_order
;

-- Business Type
insert into sys_common_code
select 'BUSINESS_TYPE' as code_type,
       '0000000000' as common_code,
       'Business Type' as description_ko,
       'Business Type' as description_en,
       'BUSINESS_TYPE_0000000000' as program_constants,
       '000' as sort_order,
       'Y' as is_active,
       'Y' as is_default,
       '0' as insert_user,
       sysdate as insert_date,
       null as update_user,
       null as update_date
  from dual
union
select 'BUSINESS_TYPE' as code_type,
       replace(upper(business_type), ' ', '_') as common_code,
       business_type as description_ko,
       business_type as description_en,
       'BUSINESS_TYPE_'||replace(upper(business_type), ' ', '_') as program_constants,
       lpad(idx, 3, '0') as sort_order,
       'Y' as is_active,
       'Y' as is_default,
       '0' as insert_user,
       sysdate as insert_date,
       null as update_user,
       null as update_date
  from (select *
          from dm_business_type
         order by idx
       )
 order by sort_order
;

-- Template Type (Organisation Category)
insert into sys_common_code
select 'ORG_CATEGORY' as code_type,
       '0000000000' as common_code,
       'Organisation Category' as description_ko,
       'Organisation Category' as description_en,
       'ORG_CATEGORY_0000000000' as program_constants,
       '000' as sort_order,
       'Y' as is_active,
       'Y' as is_default,
       '0' as insert_user,
       sysdate as insert_date,
       null as update_user,
       null as update_date
  from dual
union
select 'ORG_CATEGORY' as code_type,
       replace(upper(template_type), ' ', '_') as common_code,
       template_type||' Type' as description_ko,
       template_type||' Type' as description_en,
       'ORG_CATEGORY_'||replace(upper(template_type), ' ', '_') as program_constants,
       lpad(idx, 3, '0') as sort_order,
       'Y' as is_active,
       'Y' as is_default,
       '0' as insert_user,
       sysdate as insert_date,
       null as update_user,
       null as update_date
  from (select *
          from dm_template_type
         order by idx
       )
 order by sort_order
;

-- Base Type
insert into sys_common_code
select 'BASE_TYPE' as code_type,
       '0000000000' as common_code,
       'Base Type' as description_ko,
       'Base Type' as description_en,
       'BASE_TYPE_0000000000' as program_constants,
       '000' as sort_order,
       'Y' as is_active,
       'Y' as is_default,
       '0' as insert_user,
       sysdate as insert_date,
       null as update_user,
       null as update_date
  from dual
union
select 'BASE_TYPE' as code_type,
       replace(upper(base_type), ' ', '_') as common_code,
       base_type as description_ko,
       base_type as description_en,
       'BASE_TYPE_'||replace(upper(base_type), ' ', '_') as program_constants,
       lpad(idx, 3, '0') as sort_order,
       'Y' as is_active,
       'Y' as is_default,
       '0' as insert_user,
       sysdate as insert_date,
       null as update_user,
       null as update_date
  from (select *
          from dm_base_type
         order by idx
       )
 order by sort_order
;

-- Wage Type
insert into sys_common_code
select 'WAGE_TYPE' as code_type,
       '0000000000' as common_code,
       'Wage Type' as description_ko,
       'Wage Type' as description_en,
       'WAGE_TYPE_0000000000' as program_constants,
       '000' as sort_order,
       'Y' as is_active,
       'Y' as is_default,
       '0' as insert_user,
       sysdate as insert_date,
       null as update_user,
       null as update_date
  from dual
union
select 'WAGE_TYPE' as code_type,
       replace(upper(wage_type), ' ', '_') as common_code,
       wage_type as description_ko,
       wage_type as description_en,
       'WAGE_TYPE_'||replace(upper(wage_type), ' ', '_') as program_constants,
       lpad(idx, 3, '0') as sort_order,
       'Y' as is_active,
       'Y' as is_default,
       '0' as insert_user,
       sysdate as insert_date,
       null as update_user,
       null as update_date
  from (select *
          from dm_wage_type
         order by idx
       )
 order by sort_order
;

-- Visa Type
insert into sys_common_code
select 'VISA_TYPE' as code_type,
       '0000000000' as common_code,
       'Visa Type' as description_ko,
       'Visa Type' as description_en,
       'VISA_TYPE_0000000000' as program_constants,
       '000' as sort_order,
       'Y' as is_active,
       'Y' as is_default,
       '0' as insert_user,
       sysdate as insert_date,
       null as update_user,
       null as update_date
  from dual
union
select 'VISA_TYPE' as code_type,
       replace(upper(visa_name), '-', '_') as common_code,
       visa_name as description_ko,
       visa_name as description_en,
       'VISA_TYPE_'||replace(upper(visa_name), '-', '_') as program_constants,
       lpad(idx, 3, '0') as sort_order,
       'Y' as is_active,
       'Y' as is_default,
       '0' as insert_user,
       sysdate as insert_date,
       null as update_user,
       null as update_date
  from (select *
          from dm_visa_type
         order by idx
       )
 order by sort_order
;

-- Record Keeping Type
insert into sys_common_code
select 'RECORD_KEEPING_TYPE' as code_type,
       '0000000000' as common_code,
       'Record Keeping Type' as description_ko,
       'Record Keeping Type' as description_en,
       'RECORD_KEEPING_TYPE_0000000000' as program_constants,
       '000' as sort_order,
       'Y' as is_active,
       'Y' as is_default,
       '0' as insert_user,
       sysdate as insert_date,
       null as update_user,
       null as update_date
  from dual
union
select 'RECORD_KEEPING_TYPE' as code_type,
       replace(upper(record_keeping_name), ' ', '_') as common_code,
       record_keeping_name as description_ko,
       record_keeping_name as description_en,
       'RECORD_KEEPING_TYPE_'||replace(upper(record_keeping_name), ' ', '_') as program_constants,
       lpad(idx, 3, '0') as sort_order,
       'Y' as is_active,
       'Y' as is_default,
       '0' as insert_user,
       sysdate as insert_date,
       null as update_user,
       null as update_date
  from (select *
          from dm_record_keeping_type
         order by idx
       )
 order by sort_order
;

-- Income Type
insert into sys_common_code values('INCOME_TYPE', '0000000000',     'Income Type',          'Income Type',           'INCOME_TYPE_0000000000',       '000',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('INCOME_TYPE', 'SALES',          'Sales Income',         'Sales Income',          'INCOME_TYPE_SALES',            '001',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('INCOME_TYPE', 'COMMISSION',     'Commission Received',  'Commission Received',   'INCOME_TYPE_COMMISSION',       '002',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('INCOME_TYPE', 'PROFESSIONAL',   'Professional Fees',    'Professional Fees',     'INCOME_TYPE_PROFESSIONAL',     '003',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('INCOME_TYPE', 'CONSULTING',     'Consulting Fees',      'Consulting Fees',       'INCOME_TYPE_CONSULTING',       '004',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('INCOME_TYPE', 'MANAGEMENT',     'Management Fees',      'Management Fees',       'INCOME_TYPE_MANAGEMENT',       '005',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('INCOME_TYPE', 'MIGRATION',      'Migration',            'Migration',             'INCOME_TYPE_MIGRATION',        '006',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('INCOME_TYPE', 'INSURANCE',      'Insurance',            'Insurance',             'INCOME_TYPE_INSURANCE',        '007',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('INCOME_TYPE', 'SUB_LEASE',      'Sub Lease',            'Sub Lease',             'INCOME_TYPE_SUB_LEASE',        '008',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('INCOME_TYPE', 'TRANSLATION',    'Translation',          'Translation',           'INCOME_TYPE_TRANSLATION',      '009',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('INCOME_TYPE', 'INTEREST',       'Interest',             'Interest',              'INCOME_TYPE_INTEREST',         '010',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('INCOME_TYPE', 'OTHER',          'Other Income',         'Other Income',          'INCOME_TYPE_OTHER',            '011',   'Y','Y','0',sysdate,'','');

-- Expense Main Type
insert into sys_common_code values('EXPENSE_MAIN_TYPE', '0000000000',     'Expense Main Type',          'Expense Main Type',           'EXPENSE_MAIN_TYPE_0000000000',       '000',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_MAIN_TYPE', 'PUR',            'Purchase',                   'Purchase',                    'EXPENSE_MAIN_TYPE_PUR',              '001',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_MAIN_TYPE', 'FU',             'Fees / Utility',             'Fees / Utility',              'EXPENSE_MAIN_TYPE_FU',               '002',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_MAIN_TYPE', 'MVT',            'Motor Vehicles / Travel',    'Motor Vehicles / Travel',     'EXPENSE_MAIN_TYPE_MVT',              '003',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_MAIN_TYPE', 'SRE',            'Shop Related Expenses',      'Shop Related Expenses',       'EXPENSE_MAIN_TYPE_SRE',              '004',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_MAIN_TYPE', 'BF',             'Banking / Finance',          'Banking / Finance',           'EXPENSE_MAIN_TYPE_BF',               '005',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_MAIN_TYPE', 'ERE',            'Employee Related Expenses',  'Employee Related Expenses',   'EXPENSE_MAIN_TYPE_ERE',              '006',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_MAIN_TYPE', 'MS',             'Materials / Supplies',       'Materials / Supplies',        'EXPENSE_MAIN_TYPE_MS',               '007',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_MAIN_TYPE', 'ORE',            'Office Related Expenses',    'Office Related Expenses',     'EXPENSE_MAIN_TYPE_ORE',              '008',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_MAIN_TYPE', 'CRE',            'Client Related Expenses',    'Client Related Expenses',     'EXPENSE_MAIN_TYPE_CRE',              '009',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_MAIN_TYPE', 'OTH',            'Other Expenses',             'Other Expenses',              'EXPENSE_MAIN_TYPE_OTH',              '010',   'Y','Y','0',sysdate,'','');

-- Expense Sub Type
insert into sys_common_code values('EXPENSE_SUB_TYPE', '0000000000',     'Expense Sub Type',             'Expense Sub Type',             'EXPENSE_SUB_TYPE_0000000000',    '000',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'BIZINSUR',       'Business Insurance',           'Business Insurance',           'EXPENSE_SUB_TYPE_BIZINSUR',      '001',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'COUNCILRA',      'Council rate',                 'Council rate',                 'EXPENSE_SUB_TYPE_COUNCILRA',     '002',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'ELECTRIC',       'Electricity',                  'Electricity',                  'EXPENSE_SUB_TYPE_ELECTRIC',      '003',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'GAS',            'Gas',                          'Gas',                          'EXPENSE_SUB_TYPE_GAS',           '004',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'INTERNETSE',     'Internet Services',            'Internet Services',            'EXPENSE_SUB_TYPE_INTERNETSE',    '005',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'PERMITLIC',      'Permit / Licenses',            'Permit / Licenses',            'EXPENSE_SUB_TYPE_PERMITLIC',     '006',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'REGISTRATION',   'Registration',                 'Registration',                 'EXPENSE_SUB_TYPE_REGISTRATION',  '007',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'TELEPHONE',      'Telephone',                    'Telephone',                    'EXPENSE_SUB_TYPE_TELEPHONE',     '008',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'WASTEDISPOS',    'Waste Disposal',               'Waste Disposal',               'EXPENSE_SUB_TYPE_WASTEDISPOS',   '009',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'WATER',          'Water',                        'Water',                        'EXPENSE_SUB_TYPE_WATER',         '010',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'PETROL',         'Petrol / Oil',                 'Petrol / Oil',                 'EXPENSE_SUB_TYPE_PETROL',        '011',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'REGINSURANCE',   'Registration / Insurance',     'Registration / Insurance',     'EXPENSE_SUB_TYPE_REGINSURANCE',  '012',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'MVREPMAIN',      'MV Reapir / Maintenance',      'MV Reapir / Maintenance',      'EXPENSE_SUB_TYPE_MVREPMAIN',     '013',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'PARKTOLL',       'Parking / Toll',               'Parking / Toll',               'EXPENSE_SUB_TYPE_PARKTOLL',      '014',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'ACCNTFEE',       'Accountancy Fees',             'Accountancy Fees',             'EXPENSE_SUB_TYPE_ACCNTFEE',      '015',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'ADVERTIS',       'Advertisement',                'Advertisement',                'EXPENSE_SUB_TYPE_ADVERTIS',      '016',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'ASICFEE',        'ASIC Fee',                     'ASIC Fee',                     'EXPENSE_SUB_TYPE_ASICFEE',       '017',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'CLOTHING',       'Clothing',                     'Clothing',                     'EXPENSE_SUB_TYPE_CLOTHING',      '018',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'DONATION',       'Donation',                     'Donation',                     'EXPENSE_SUB_TYPE_DONATION',      '019',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'FREIGHTFEE',     'Freight Fees',                 'Freight Fees',                 'EXPENSE_SUB_TYPE_FREIGHTFEE',    '020',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'LEGALFEE',       'Legal Fees',                   'Legal Fees',                   'EXPENSE_SUB_TYPE_LEGALFEE',      '021',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'POSTAGE',        'Postage',                      'Postage',                      'EXPENSE_SUB_TYPE_POSTAGE',       '022',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'REPLTOOL',       'Replace of Tools',             'Replace of Tools',             'EXPENSE_SUB_TYPE_REPLTOOL',      '023',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'SUBSCRIP',       'Subscription',                 'Subscription',                 'EXPENSE_SUB_TYPE_SUBSCRIP',      '024',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'TAXPAY',         'Tax Payment',                  'Tax Payment',                  'EXPENSE_SUB_TYPE_TAXPAY',        '025',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'RENT',           'Rent',                         'Rent',                         'EXPENSE_SUB_TYPE_RENT',          '026',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'REPMAINT',       'Repair / Maintenance',         'Repair / Maintenance',         'EXPENSE_SUB_TYPE_REPMAINT',      '027',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'SHOPSUP',        'Shop Supplies',                'Shop Supplies',                'EXPENSE_SUB_TYPE_SHOPSUP',       '028',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'STATIO',         'Stationery',                   'Stationery',                   'EXPENSE_SUB_TYPE_STATIO',        '029',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'BANKCHARG',      'Bank Charge',                  'Bank Charge',                  'EXPENSE_SUB_TYPE_BANKCHARG',     '030',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'MERCHANTFEE',    'Merchant Fees',                'Merchant Fees',                'EXPENSE_SUB_TYPE_MERCHANTFEE',   '031',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'INTERESTPM',     'Interest Payment',             'Interest Payment',             'EXPENSE_SUB_TYPE_INTERESTPM',    '032',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'LEASEPAY',       'Lease Payment',                'Lease Payment',                'EXPENSE_SUB_TYPE_LEASEPAY',      '033',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'HIREPEQ',        'Hire of Plant / Equipment',    'Hire of Plant / Equipment',    'EXPENSE_SUB_TYPE_HIREPEQ',       '034',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'STAFAMENI',      'Staff Amenities',              'Staff Amenities',              'EXPENSE_SUB_TYPE_STAFAMENI',     '035',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'STAFTRAIN',      'Staff Training',               'Staff Training',               'EXPENSE_SUB_TYPE_STAFTRAIN',     '036',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'SUBCONT',        'Subcontractors',               'Subcontractors',               'EXPENSE_SUB_TYPE_SUBCONT',       '037',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'SUPERANN',       'Superannuation',               'Superannuation',               'EXPENSE_SUB_TYPE_SUPERANN',      '038',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'SEMINARCON',     'Seminar or Conference',        'Seminar or Conference',        'EXPENSE_SUB_TYPE_SEMINARCON',    '039',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'WORKCOVER',      'Workcover',                    'Workcover',                    'EXPENSE_SUB_TYPE_WORKCOVER',     '040',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'PURCHASE',       'Purchase',                     'Purchase',                     'EXPENSE_SUB_TYPE_PURCHASE',      '041',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'TRAFEEOVERSEA',  'Travel Fees Overseas',         'Travel Fees Overseas',         'EXPENSE_SUB_TYPE_TRAFEEOVERSEA', '042',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'TRAACCOM',       'Travel Accommodation / Meals', 'Travel Accommodation / Meals', 'EXPENSE_SUB_TYPE_TRAACCOM',      '043',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'TRAEXPENSE',     'Travel Expenses Domestic',     'Travel Expenses Domestic',     'EXPENSE_SUB_TYPE_TRAEXPENSE',    '044',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'TRAOVERSEA',     'Travel Overseas',              'Travel Overseas',              'EXPENSE_SUB_TYPE_TRAOVERSEA',    '045',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'TRADOMESTIC',    'Travel Domestic',              'Travel Domestic',              'EXPENSE_SUB_TYPE_TRADOMESTIC',   '046',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'TRAINTERNAT',    'Travel International',         'Travel International',         'EXPENSE_SUB_TYPE_TRAINTERNAT',   '047',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'AGENCYCOM',      'Agency Commission',            'Agency Commission',            'EXPENSE_SUB_TYPE_AGENCYCOM',     '048',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'BIZCLOTH',       'Business Clothing',            'Business Clothing',            'EXPENSE_SUB_TYPE_BIZCLOTH',      '049',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'COMMISDIS',      'Commission Discount',          'Commission Discount',          'EXPENSE_SUB_TYPE_COMMISDIS',     '050',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'CONFERENC',      'Conference / Seminar',         'Conference / Seminar',         'EXPENSE_SUB_TYPE_CONFERENC',     '051',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'ENTERTAIN',      'Entertainment',                'Entertainment',                'EXPENSE_SUB_TYPE_ENTERTAIN',     '052',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'FINEPEANLT',     'Fines / Penalties',            'Fines / Penalties',            'EXPENSE_SUB_TYPE_FINEPEANLT',    '053',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'HOMEOFFICE',     'Home Office',                  'Home Office',                  'EXPENSE_SUB_TYPE_HOMEOFFICE',    '054',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'MATERIALSUP',    'Material / Supplies',          'Material / Supplies',          'EXPENSE_SUB_TYPE_MATERIALSUP',   '055',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'OFFICESUP',      'Office Supplies',              'Office Supplies',              'EXPENSE_SUB_TYPE_OFFICESUP',     '056',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'PICKUP',         'Pick-up',                      'Pick-up',                      'EXPENSE_SUB_TYPE_PICKUP',        '057',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'TRANSFEE',       'Translation Fees',             'Translation Fees',             'EXPENSE_SUB_TYPE_TRANSFEE',      '058',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('EXPENSE_SUB_TYPE', 'OTHERS',         'Others',                       'Others',                       'EXPENSE_SUB_TYPE_OTHERS',        '059',   'Y','Y','0',sysdate,'','');

-- Asset Type
insert into sys_common_code values('ASSET_TYPE', '0000000000',     'Asset Type',            'Asset Type',            'ASSET_TYPE_0000000000',       '000',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('ASSET_TYPE', 'EQUIP',          'Equipments',            'Equipments',            'ASSET_TYPE_EQUIP',            '001',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('ASSET_TYPE', 'MOTVEH',         'Motor Vehicle',         'Motor Vehicle',         'ASSET_TYPE_MOTVEH',           '002',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('ASSET_TYPE', 'FURNI',          'Furniture',             'Furniture',             'ASSET_TYPE_FURNI',            '003',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('ASSET_TYPE', 'FITREN',         'Fittings / Renovation', 'Fittings / Renovation', 'ASSET_TYPE_FITREN',           '004',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('ASSET_TYPE', 'PROPERTY',       'Properties',            'Properties',            'ASSET_TYPE_PROPERTY',         '005',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('ASSET_TYPE', 'OTHER',          'Others',                'Others',                'ASSET_TYPE_OTHER',            '006',   'Y','Y','0',sysdate,'','');

-- Lending Type
insert into sys_common_code values('LENDING_TYPE', '0000000000',   'Lending Type',          'Lending Type',          'LENDING_TYPE_0000000000',     '000',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('LENDING_TYPE', 'EQUIP',        'Equipments',            'Equipments',            'LENDING_TYPE_EQUIP',          '001',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('LENDING_TYPE', 'MOTVEH',       'Motor Vehicle',         'Motor Vehicle',         'LENDING_TYPE_MOTVEH',         '002',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('LENDING_TYPE', 'FURNI',        'Furniture',             'Furniture',             'LENDING_TYPE_FURNI',          '003',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('LENDING_TYPE', 'FITREN',       'Fittings / Renovation', 'Fittings / Renovation', 'LENDING_TYPE_FITREN',         '004',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('LENDING_TYPE', 'PROPERTY',     'Properties',            'Properties',            'LENDING_TYPE_PROPERTY',       '005',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('LENDING_TYPE', 'OTHER',        'Others',                'Others',                'LENDING_TYPE_OTHER',          '006',   'Y','Y','0',sysdate,'','');

-- Payment Type
insert into sys_common_code values('PAYMENT_TYPE', '0000000000',   'Payment Type',          'Payment Type',          'PAYMENT_TYPE_0000000000',     '000',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('PAYMENT_TYPE', 'EQUIP',        'Equipments',            'Equipments',            'PAYMENT_TYPE_EQUIP',          '001',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('PAYMENT_TYPE', 'MOTVEH',       'Motor Vehicle',         'Motor Vehicle',         'PAYMENT_TYPE_MOTVEH',         '002',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('PAYMENT_TYPE', 'FURNI',        'Furniture',             'Furniture',             'PAYMENT_TYPE_FURNI',          '003',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('PAYMENT_TYPE', 'FITREN',       'Fittings / Renovation', 'Fittings / Renovation', 'PAYMENT_TYPE_FITREN',         '004',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('PAYMENT_TYPE', 'PROPERTY',     'Properties',            'Properties',            'PAYMENT_TYPE_PROPERTY',       '005',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('PAYMENT_TYPE', 'OTHER',        'Others',                'Others',                'PAYMENT_TYPE_OTHER',          '006',   'Y','Y','0',sysdate,'','');

-- Others Type
insert into sys_common_code values('OTHER_TYPE',  '0000000000',    'Other Type',            'Other Type',            'OTHER_TYPE_0000000000',       '000',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('OTHER_TYPE',  'INTEREST',      'Interest Income',       'Interest Income',       'OTHER_TYPE_INTEREST',         '001',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('OTHER_TYPE',  'COMMISION',     'Commision',             'Commision',             'OTHER_TYPE_COMMISION',        '002',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('OTHER_TYPE',  'RENTAL',        'Rental',                'Rental',                'OTHER_TYPE_RENTAL',           '003',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('OTHER_TYPE',  'MOTVEH',        'MotorVehicle',          'MotorVehicle',          'OTHER_TYPE_MOTVEH',           '004',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('OTHER_TYPE',  'PROPERTY',      'Property',              'Property',              'OTHER_TYPE_PROPERTY',         '005',   'Y','Y','0',sysdate,'','');

-- Super Type
insert into sys_common_code values('SUPER_TYPE',  '0000000000',    'Super Type',            'Super Type',            'SUPER_TYPE_0000000000',       '000',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('SUPER_TYPE',  '2012',          '9',                     '9',                     'SUPER_TYPE_2012',             '001',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('SUPER_TYPE',  '2013',          '9',                     '9',                     'SUPER_TYPE_2013',             '002',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('SUPER_TYPE',  '2014',          '9.25',                  '9.25',                  'SUPER_TYPE_2014',             '003',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('SUPER_TYPE',  '2015',          '9.5',                   '9.5',                   'SUPER_TYPE_2015',             '004',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('SUPER_TYPE',  '2016',          '9.5',                   '9.5',                   'SUPER_TYPE_2016',             '005',   'Y','Y','0',sysdate,'','');
insert into sys_common_code values('SUPER_TYPE',  '2017',          '9.5',                   '9.5',                   'SUPER_TYPE_2017',             '006',   'Y','Y','0',sysdate,'','');

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