-- Asset - All Type
select typ.asset_type_id,
       typ.asset_type,
       typ.description,
       nvl(asset.asset_entry_count, 0) as asset_entry_count
  from sys_asset_type typ,
       (select asset_type_id,
               count(*) as asset_entry_count
          from usr_asset
         where org_id = '439'
           and asset_year = '2017'
           and quarter_name = 'SEP'
         group by asset_type_id
       ) asset
 where typ.org_category = 'A'
   and typ.asset_type_id = asset.asset_type_id(+)
 order by sort_order
;