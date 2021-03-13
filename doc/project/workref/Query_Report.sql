with src as (
select cate.category_level,
       cate.category_id,
       cate.category_name,
       cate.account_code,
       bsta.proc_date,
       bsta.debit_amt,
       bsta.credit_amt,
       bsta.gst_amt,
       bsta.gross_amt,
       bsta.balance
  from ( select connect_by_root category_id as root,
                level as category_level,
                connect_by_isleaf as is_leaf,
                substr(sys_connect_by_path(category_id, '-'), 2) as path,
                sys_recon_category.*
           from sys_recon_category
        connect by prior category_id = parent_category_id
          start with parent_category_id is null
          order siblings by sort_order
       ) cate,
       (select ubta.main_category,
               ubta.sub_category as category_id,
               trunc(ubta.proc_date) as proc_date,
               case when sum(nvl(ubta.net_amt, 0)) < 0 then sum(nvl(ubta.net_amt, 0)) end as debit_amt,
               case when sum(nvl(ubta.net_amt, 0)) >= 0 then sum(nvl(ubta.net_amt, 0)) end as credit_amt,
               sum(nvl(ubta.gst_amt, 0)) as gst_amt,
               sum(nvl(ubta.proc_amt, 0)) as gross_amt,
               max(nvl(ubta.balance, 0)) as balance
          from usr_bs_tran_alloc ubta,
               (select period_year,
                       financial_year,
                       min(date_from) as date_from,
                       max(date_to) as date_to
                  from sys_financial_period
                 where period_year = '2021'
                 group by period_year, financial_year
               ) fy
         where user_id in (select user_id from sys_user where org_id = (select org_id from sys_user where user_id = '1'))
           and trunc(ubta.proc_date) between trunc(fy.date_from) and trunc(fy.date_to)
         group by ubta.main_category,
               ubta.sub_category,
               trunc(ubta.proc_date)
       ) bsta
 where cate.category_id = bsta.category_id(+)
)
select (grouping(src.account_code)+grouping(src.category_name)+grouping(src.proc_date)) as div,
       src.account_code,
       src.category_name,
       src.proc_date,
       round(sum(nvl(src.gst_amt, 0)), 2) as gst_amt,
       round(sum(nvl(src.gross_amt, 0)), 2) as gross_amt,
       round(sum(nvl(src.debit_amt, 0)), 2) as debit_amt,
       round(sum(nvl(src.credit_amt, 0)), 2) as credit_amt,
       round(max(nvl(src.balance, 0)), 2) as balance
  from src
 where src.proc_date is not null
 group by rollup(src.account_code, src.category_name, src.proc_date)
having (grouping(src.account_code)+grouping(src.category_name)+grouping(src.proc_date)) in ('0', '1', '3')
 order by src.account_code,
       div desc,
       src.proc_date
;