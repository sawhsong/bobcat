/**************************************************************************************************
 * Framework Generated DAO Source
 * - SYS_BOARD - Bulletin Board
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysBoard;

import project.conf.resource.ormapper.dto.oracle.SysBoard;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface SysBoardDao extends IDao {
	public int insert(SysBoard sysBoard) throws Exception;
	public int insert(SysBoard sysBoard, DataSet fileDataSet, String isSaveFileFlag) throws Exception;
	public int update(SysBoard sysBoard, DataSet fileDataSet, String isSaveFileFlag, String[] fileIds) throws Exception;
	public int delete(String[] articleIds) throws Exception;
	public int delete(String articleId) throws Exception;
	public int updateVisitCountByArticleId(String articleId) throws Exception;

	public SysBoard getBoardByArticleId(String articleId) throws Exception;
	public DataSet getNoticeBoardDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getAnnouncementDataSetForDashboard(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getFreeBoardDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
}