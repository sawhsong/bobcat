/**
 * Table Name  : USR_QUOTATION_D
 * Description : Quotation detail info
 */
drop table usr_quotation_d cascade constraints;
purge recyclebin;

create table usr_quotation_d (
    quotation_d_id                  varchar2(30)                                 not null,      -- Quotation detail UID (PK)
    quotation_id                    varchar2(30)                                 not null,      -- Quotation UID
    row_index                       number                                       not null,      -- Detail row index - just for sort order
    unit                            number,                                                     -- Unit - how many | how much
    amt_per_unit                    number,                                                     -- Price per unit
    item_amt                        number,                                                     -- Amount (exclude gst)
    description                     varchar2(1000),                                             -- Description
    insert_user_id                  varchar2(30),                                               -- Insert User UID
    insert_date                     date                default sysdate,                        -- Insert Date
    update_user_id                  varchar2(30),                                               -- Update User UID
    update_date                     date,                                                       -- Update Date

    constraint fk_29601650533400 foreign key(quotation_id) references usr_quotation(quotation_id),
    constraint pk_usr_quotation_d primary key(quotation_d_id)
    using index tablespace hkaccount_idx storage(initial 50k next 50k pctincrease 0)
)
pctfree 20 pctused 80 tablespace hkaccount_data storage(initial 100k next 100k maxextents 2000 pctincrease 0);

comment on table  usr_quotation_d                                                is 'Quotation detail info';
comment on column usr_quotation_d.quotation_d_id                                 is 'Quotation detail UID (PK)';
comment on column usr_quotation_d.quotation_id                                   is 'Quotation UID';
comment on column usr_quotation_d.row_index                                      is 'Detail row index - just for sort order';
comment on column usr_quotation_d.unit                                           is 'Unit - how many | how much';
comment on column usr_quotation_d.amt_per_unit                                   is 'Price per unit';
comment on column usr_quotation_d.item_amt                                       is 'Amount (exclude gst)';
comment on column usr_quotation_d.description                                    is 'Description';
comment on column usr_quotation_d.insert_user_id                                 is 'Insert User UID';
comment on column usr_quotation_d.insert_date                                    is 'Insert Date';
comment on column usr_quotation_d.update_user_id                                 is 'Update User UID';
comment on column usr_quotation_d.update_date                                    is 'Update Date';


/**
 * Table Name  : USR_QUOTATION_D
 * Data        : 
 */
