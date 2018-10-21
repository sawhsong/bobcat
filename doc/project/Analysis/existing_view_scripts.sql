-- asset_view
select `asset`.`date` as `date`,
      (`asset`.`product_gross` - `asset`.`gst`) as `net`,
      `asset`.`user_id` as `user_id`,
      `asset`.`year` as `year`,
      `asset`.`quarter` as `quarter`,
      `asset`.`complete_flag` as `complete_flag`
 from `asset`;

-- cash_sales_view
select `template_sales`.`sash_sales` as `cash`,
      (`template_sales`.`sash_sales` / 11) as `gst`,
      (`template_sales`.`sash_sales` - (`template_sales`.`sash_sales` / 11)) as `net`,
      `template_sales`.`user_id` as `user_id`,
      `template_sales`.`year` as `year`,
      `template_sales`.`quarter` as `quarter`,
      `template_sales`.`complete_flag` as `complete_flag`,
      `template_sales`.`date` as `date`
 from `template_sales`;

-- expense_view
select `expense`.`main_type_id` as `main_type_id`,
       `expense`.`sub_type_id` as `sub_type_id`,
      (`expense`.`gross` - `expense`.`gst`) as `net`,
       `expense`.`date` as `date`,
       `expense`.`user_id` as `user_id`,
       `expense`.`year` as `year`,
       `expense`.`quarter` as `quarter`,
       `expense`.`complete_flag` as `complete_flag`
 from `expense`;

-- others_view
select (`template_other`.`gross` - `template_other`.`gst`) as `net`,
       `template_other`.`user_id` as `user_id`,
       `template_other`.`year` as `year`,
       `template_other`.`quarter` as `quarter`,
       `template_other`.`complete_flag` as `complete_flag`,
       `template_other`.`date` as `date`,
       `template_other`.`income_type_id` as `income_type_id`
 from `template_other`;

-- sales_view
select (`template_sales`.`card_sales` + `template_sales`.`sash_sales`) as `gross_sales`,
       if(((((`template_sales`.`card_sales` + `template_sales`.`sash_sales`) - `template_sales`.`gst_free_sales`) / 11) > 0),(((`template_sales`.`card_sales` + `template_sales`.`sash_sales`) - `template_sales`.`gst_free_sales`) / 11),0) as `gst`,
       if(((`template_sales`.`card_sales` + `template_sales`.`sash_sales`) > 0),((`template_sales`.`card_sales` + `template_sales`.`sash_sales`) - truncate((((`template_sales`.`card_sales` + `template_sales`.`sash_sales`) - `template_sales`.`gst_free_sales`) / 11),2)),if((`template_sales`.`gst_free_sales` > 0),`template_sales`.`gst_free_sales`,0)) as `net`,
       `template_sales`.`user_id` as `user_id`,
       `template_sales`.`year` as `year`,
       `template_sales`.`quarter` as `quarter`,
       `template_sales`.`complete_flag` as `complete_flag`,
       `template_sales`.`date` as `date`,
       `template_sales`.`record_keeping_type` as `record_keeping_type`
 from `template_sales`;