ALTER TABLE `t_stock_plan` ADD cur_price decimal(18,2) NOT NULL;

#增加开户行字段
alter table t_user_info add column deposit_bank varchar(255);

alter table t_user_info_check add column deposit_bank varchar(255);