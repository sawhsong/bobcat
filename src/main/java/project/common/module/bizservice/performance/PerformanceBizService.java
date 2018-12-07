package project.common.module.bizservice.performance;

import zebra.data.DataSet;

public interface PerformanceBizService {
	public DataSet getPerformanceDataSet(String orgCategory, String orgId, String financialYear, String quarterName) throws Exception;
}