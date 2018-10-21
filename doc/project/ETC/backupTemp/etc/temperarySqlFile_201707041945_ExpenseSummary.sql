-- Expense - All Type
with src_data as (
select src.parent_expense_type as expense_type,
       sum(src.expense_entry_count) as expense_entry_count
  from (select type.expense_type_id,
               type.expense_type,
               type.parent_expense_type,
               type.description,
               nvl(expense.expense_count, 0) as expense_entry_count
          from sys_expense_type type,
               (select expense_type_id,
                       count(*) as expense_count
                  from usr_expense
                 where org_id = '475'
                   and expense_year = '2017'
                   and quarter_name = 'SEP'
                 group by expense_type_id
               ) expense
         where type.org_category = 'C'
           and type.expense_type_id = expense.expense_type_id(+)
       ) src
 group by src.parent_expense_type
having src.parent_expense_type is not null
)
select src.expense_type,
       typ.description,
       src.expense_entry_count
  from sys_expense_type typ,
       src_data src
 where typ.expense_type = src.expense_type
   and org_category = 'C'
 order by typ.sort_order
;