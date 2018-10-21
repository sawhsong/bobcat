-- Finance - Repayment - All Type
select 'Repayment' as finance_entry_type,
       typ.repayment_type_id,
       typ.description,
       nvl(fin.finance_entry_count, 0) as finance_entry_count
  from sys_repayment_type typ,
       (select finance_type_id,
               count(*) as finance_entry_count
          from usr_finance
         where org_id = '427'
           and finance_year = '2014'
           and quarter_name = 'SEP'
           and finance_entry_type = 'REP'
         group by finance_type_id
       ) fin
 where typ.org_category = 'A'
   and typ.repayment_type_id = fin.finance_type_id(+)
 order by typ.sort_order
;

-- Finance - Borrowing - All Type
select 'Borrowing' as finance_entry_type,
       typ.borrowing_type_id,
       typ.description,
       nvl(fin.finance_entry_count, 0) as finance_entry_count
  from sys_borrowing_type typ,
       (select finance_type_id,
               count(*) as finance_entry_count
          from usr_finance
         where org_id = '439'
           and finance_year = '2017'
           and quarter_name = 'SEP'
           and finance_entry_type = 'BOR'
         group by finance_type_id
       ) fin
 where typ.org_category = 'A'
   and typ.borrowing_type_id = fin.finance_type_id(+)
 order by typ.sort_order
;

-- Finance - Lending - All Type
select 'Lending' as finance_entry_type,
       typ.lending_type_id,
       typ.description,
       nvl(fin.finance_entry_count, 0) as finance_entry_count
  from sys_lending_type typ,
       (select finance_type_id,
               count(*) as finance_entry_count
          from usr_finance
         where org_id = '427'
           and finance_year = '2014'
           and quarter_name = 'SEP'
           and finance_entry_type = 'LEN'
         group by finance_type_id
       ) fin
 where typ.org_category = 'A'
   and typ.lending_type_id = fin.finance_type_id(+)
 order by typ.sort_order
;