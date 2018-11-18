package project.common.module.admintoolentrysummary;

import zebra.data.ParamEntity;

public interface EntrySummaryBiz {
	public ParamEntity getIncomeSummary(ParamEntity paramEntity) throws Exception;
	public ParamEntity getExpenseSummary(ParamEntity paramEntity) throws Exception;
	public ParamEntity getAssetSummary(ParamEntity paramEntity) throws Exception;
	public ParamEntity getRepaymentSummary(ParamEntity paramEntity) throws Exception;
	public ParamEntity getBorrowingSummary(ParamEntity paramEntity) throws Exception;
	public ParamEntity getLendingSummary(ParamEntity paramEntity) throws Exception;
	public ParamEntity getEmployeeSummary(ParamEntity paramEntity) throws Exception;
}