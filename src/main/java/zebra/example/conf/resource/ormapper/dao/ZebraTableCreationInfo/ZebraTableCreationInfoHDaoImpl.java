/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - ZEBRA_TABLE_CREATION_INFO - Table info to be created
 *************************************************************************************************/
package zebra.example.conf.resource.ormapper.dao.ZebraTableCreationInfo;

import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.example.common.extend.BaseHDao;
import zebra.example.conf.resource.ormapper.dto.oracle.ZebraTableCreationInfo;

public class ZebraTableCreationInfoHDaoImpl extends BaseHDao implements ZebraTableCreationInfoDao {
	public DataSet getAllLikeTableNameAsDataSet(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String tableName = requestDataSet.getValue("tableName");

		queryAdvisor.addAutoFillCriteria(tableName, "upper(table_name) like upper('%"+tableName+"%')");

		return selectAllAsDataSet(queryAdvisor, new ZebraTableCreationInfo());
	}
}