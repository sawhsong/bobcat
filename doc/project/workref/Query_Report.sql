select cate.category_level,
       cate.category_id,
       cate.category_name,
       cate.account_code,
       bsta.proc_date,
       bsta.debit_amt,
       bsta.credit_amt,
       bsta.gst_amt,
       bsta.net_amt,
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
               case when sum(nvl(ubta.proc_amt, 0)) < 0 then sum(nvl(ubta.proc_amt, 0)) end as debit_amt,
               case when sum(nvl(ubta.proc_amt, 0)) >= 0 then sum(nvl(ubta.proc_amt, 0)) end as credit_amt,
               sum(nvl(ubta.gst_amt, 0)) as gst_amt,
               sum(nvl(ubta.net_amt, 0)) as net_amt,
               sum(nvl(ubta.balance, 0)) as balance
          from usr_bs_tran_alloc ubta,
               (select period_year,
                       financial_year,
                       min(date_from) as date_from,
                       max(date_to) as date_to
                  from sys_financial_period
                 where period_year = '2021'
                 group by period_year, financial_year
               ) fy
         where user_id = '1'
           and trunc(ubta.proc_date) between trunc(fy.date_from) and trunc(fy.date_to)
         group by ubta.main_category,
               ubta.sub_category,
               trunc(ubta.proc_date)
       ) bsta
 where cate.category_id = bsta.category_id(+)
 order by cate.sort_order, bsta.proc_date
;