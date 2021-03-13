package project.conf.resource.ormapper.dao.Report;

import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface ReportDao extends IDao {
	public DataSet getTrialBalance(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getGeneralLedger(QueryAdvisor queryAdvisor) throws Exception;
}