package project.app.index;

import zebra.data.ParamEntity;

public interface IndexBiz {
	public ParamEntity index(ParamEntity paramEntity) throws Exception;
	public ParamEntity dashboard(ParamEntity paramEntity) throws Exception;
	public ParamEntity getAnnouncementList(ParamEntity paramEntity) throws Exception;
	public ParamEntity getAttachedFile(ParamEntity paramEntity) throws Exception;
	public ParamEntity getBankStatementAllocationStatus(ParamEntity paramEntity) throws Exception;
	public ParamEntity getQuotationBadge(ParamEntity paramEntity) throws Exception;
	public ParamEntity getQuotationData(ParamEntity paramEntity) throws Exception;
	public ParamEntity getInvoiceBadge(ParamEntity paramEntity) throws Exception;
	public ParamEntity getInvoiceData(ParamEntity paramEntity) throws Exception;
	public ParamEntity getMonthForChart(ParamEntity paramEntity) throws Exception;
	public ParamEntity getIncomeChartData(ParamEntity paramEntity) throws Exception;
	public ParamEntity getExpenseChartData(ParamEntity paramEntity) throws Exception;
	public ParamEntity getOtherChartData(ParamEntity paramEntity) throws Exception;
}