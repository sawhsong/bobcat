package project.app.sys.sys0406;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseBiz;
import project.conf.resource.ormapper.dao.SysAuthGroup.SysAuthGroupDao;
import project.conf.resource.ormapper.dao.SysMenuAuthLink.SysMenuAuthLinkDao;
import project.conf.resource.ormapper.dto.oracle.SysMenuAuthLink;
import zebra.config.MemoryBean;
import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.data.QueryAdvisor;
import zebra.exception.FrameworkException;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class Sys0406BizImpl extends BaseBiz implements Sys0406Biz {
	@Autowired
	private SysMenuAuthLinkDao sysMenuAuthLinkDao;
	@Autowired
	private SysAuthGroupDao sysAuthGroupDao;

	public ParamEntity getDefault(ParamEntity paramEntity) throws Exception {
		try {
			setAuthorityGroup(paramEntity);
			paramEntity.setObject("resultDataSet", MemoryBean.get("menuDataSet"));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getList(ParamEntity paramEntity) throws Exception {
		try {
			setAuthorityGroup(paramEntity);
			paramEntity.setObject("resultDataSet", MemoryBean.get("menuDataSet"));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity exeInsert(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		HttpSession session = paramEntity.getSession();
		String authGroupId = requestDataSet.getValue("authGroup");
		String chkToAssign = requestDataSet.getValue("chkToAssign");
		String[] chkToAssignArray = CommonUtil.splitWithTrim(chkToAssign, ConfigUtil.getProperty("delimiter.record"));
		String dataDelimiter = ConfigUtil.getProperty("delimiter.data");
		SysMenuAuthLink sysMenuAuthLink = new SysMenuAuthLink();
		int resultDelete = 0, resultInsert = 0;

		try {
			resultDelete = sysMenuAuthLinkDao.deleteByAuthGroupId(authGroupId);

			if (CommonUtil.isNotBlank(chkToAssign)) {
				for (int i=0; i<chkToAssignArray.length; i++) {
					String[] chkValues = CommonUtil.split(chkToAssignArray[i], dataDelimiter);
					String menuId = chkValues[2];

					sysMenuAuthLink.setGroupId(authGroupId);
					sysMenuAuthLink.setMenuId(menuId);
					sysMenuAuthLink.setInsertUserId((String)session.getAttribute("UserId"));
					sysMenuAuthLink.setInsertDate(CommonUtil.toDate(CommonUtil.getSysdate()));

					if (resultDelete > 0) {
						sysMenuAuthLink.setUpdateUserId((String)session.getAttribute("UserId"));
						sysMenuAuthLink.setUpdateDate(CommonUtil.toDate(CommonUtil.getSysdate()));
					}

					resultInsert += sysMenuAuthLinkDao.insert(sysMenuAuthLink);
				}

				if (resultInsert <= 0) {
					throw new FrameworkException("E801", getMessage("E801", paramEntity));
				}
			}

			paramEntity.setSuccess(true);
			paramEntity.setMessage("I801", getMessage("I801", paramEntity));
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	private void setAuthorityGroup(ParamEntity paramEntity) throws Exception {
		QueryAdvisor qaAuth = paramEntity.getQueryAdvisor();
		qaAuth.addOrderByClause("group_id");
		paramEntity.setObject("authGroupDataSet", sysAuthGroupDao.getAllAuthGroupDataSet(qaAuth));
	}
}