<%/************************************************************************************************
* Description - Sba0204 - User Management
*	- Generated by Source Generator
************************************************************************************************/%>
<%@ include file="/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity paramEntity = (ParamEntity)request.getAttribute("paramEntity");
	DataSet requestDataSet = (DataSet)paramEntity.getRequestDataSet();
	DataSet authGroupDataSet = (DataSet)paramEntity.getObject("authGroupDataSet");
	String dateFormat = ConfigUtil.getProperty("format.date.java");
	String maxRowPerPage[] = (String[])paramEntity.getObject("maxRowPerPage");
	String pageNumPerPage[] = (String[])paramEntity.getObject("pageNumPerPage");
	String photoPath = (String)paramEntity.getObject("photoPath");
	String defaultAuthGroup = (String)paramEntity.getObject("defaultAuthGroup");
	String defaultUserStatus = CommonCodeManager.getCodeByConstants("USER_STATUS_NU");
%>
<%/************************************************************************************************
* HTML
************************************************************************************************/%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="icon" type="image/png" href="<mc:cp key="imgIcon"/>/faviconHKAccount.png">
<title><mc:msg key="main.system.title"/></title>
<%/************************************************************************************************
* Stylesheet & Javascript
************************************************************************************************/%>
<%@ include file="/shared/page/incCssJs.jsp"%>
<style type="text/css">
</style>
<script type="text/javascript" src="<mc:cp key="viewPageJsName"/>"></script>
<script type="text/javascript">
</script>
</head>
<%/************************************************************************************************
* Page & Header
************************************************************************************************/%>
<body>
<form id="fmDefault" name="fmDefault" method="post" action="">
<div id="divPopupWindowHolder">
<div id="divFixedPanelPopup">
<div id="divLocationPathArea"><%@ include file="/project/common/include/bodyLocationPathArea.jsp"%></div>
<%/************************************************************************************************
* Real Contents - fixed panel(tab, button, search, information)
************************************************************************************************/%>
<div id="divTabArea"></div>
<div id="divButtonArea" class="areaContainerPopup">
	<div id="divButtonAreaLeft"></div>
	<div id="divButtonAreaRight">
		<ui:buttonGroup id="buttonGroup">
			<ui:button id="btnSave" caption="button.com.save" iconClass="fa-save"/>
			<ui:button id="btnClose" caption="button.com.close" iconClass="fa-times"/>
		</ui:buttonGroup>
	</div>
</div>
<div id="divSearchCriteriaArea"></div>
<div id="divInformArea"></div>
<%/************************************************************************************************
* End of fixed panel
************************************************************************************************/%>
<div class="breaker"></div>
</div>
<div id="divScrollablePanelPopup">
<%/************************************************************************************************
* Real Contents - scrollable panel(data, paging)
************************************************************************************************/%>
<div id="divDataArea" class="areaContainerPopup">
	<div class="panel panel-default" style="width:120px;height:110px;">
		<div class="panel-body">
			<table class="tblDefault">
				<tr>
					<td class="tdDefaultCt">
						<img id="imgUserPhoto" src="<%=photoPath%>" class="imgDis" style="width:90px;height:90px;"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<table class="tblEdit">
		<colgroup>
			<col width="18%"/>
			<col width="32%"/>
			<col width="18%"/>
			<col width="32%"/>
		</colgroup>
		<tr>
			<th class="thEdit rt"><mc:msg key="sba0204.header.changePhoto"/></th>
			<td class="tdEdit" colspan="3"><ui:file name="photoPath" style="width:540px;" value=" " checkName="sba0204.header.changePhoto"/></td>
		</tr>
		<tr>
			<th class="thEdit rt"><mc:msg key="sba0204.header.userId"/></th>
			<td class="tdEdit"><ui:text name="userId" status="display"/></td>
			<th class="thEdit rt mandatory"><mc:msg key="sba0204.header.userName"/></th>
			<td class="tdEdit"><ui:text name="userName" checkName="sba0204.header.userName" options="mandatory"/></td>
		</tr>
		<tr>
			<th class="thEdit rt mandatory"><mc:msg key="sba0204.header.loginId"/></th>
			<td class="tdEdit"><ui:text name="loginId" checkName="sba0204.header.loginId" options="mandatory"/></td>
			<th class="thEdit rt mandatory"><mc:msg key="sba0204.header.password"/></th>
			<td class="tdEdit"><ui:text name="password" checkName="sba0204.header.password" options="mandatory"/></td>
		</tr>
		<tr>
			<th class="thEdit rt mandatory"><mc:msg key="sba0204.header.org"/></th>
			<td class="tdEdit">
				<ui:hidden name="orgId" checkName="sba0204.header.org"/>
				<ui:text name="orgName" className="hor" style="width:250px" checkName="sba0204.header.org" options="mandatory"/>
				<ui:icon id="icnOrgSearch" className="fa-search hor"/>
			</td>
			<th class="thEdit rt mandatory"><mc:msg key="sba0204.header.authGroup"/></th>
			<td class="tdEdit">
				<ui:select name="authGroup" checkName="sba0204.header.authGroup" options="mandatory">
<%
				for (int i=0; i<authGroupDataSet.getRowCnt(); i++) {
					String selected = (CommonUtil.equals(authGroupDataSet.getValue(i, "GROUP_ID"), defaultAuthGroup)) ? "selected" : "";
%>
					<option value="<%=authGroupDataSet.getValue(i, "GROUP_ID")%>" <%=selected%>><%=authGroupDataSet.getValue(i, "GROUP_NAME")%></option>
<%
				}
%>
				</ui:select>
			</td>
		</tr>
		<tr>
			<th class="thEdit rt mandatory"><mc:msg key="sba0204.header.language"/></th>
			<td class="tdEdit"><ui:ccselect name="language" codeType="LANGUAGE_TYPE" options="mandatory"/></td>
			<th class="thEdit rt mandatory"><mc:msg key="sba0204.header.themeType"/></th>
			<td class="tdEdit"><ui:ccselect name="themeType" codeType="USER_THEME_TYPE" options="mandatory"/></td>
		</tr>
		<tr>
			<th class="thEdit rt mandatory"><mc:msg key="sba0204.header.type"/></th>
			<td class="tdEdit"><ui:ccselect name="userType" codeType="USER_TYPE" options="mandatory"/></td>
			<th class="thEdit rt mandatory"><mc:msg key="sba0204.header.email"/></th>
			<td class="tdEdit"><ui:text name="email" checkName="sba0204.header.email" options="mandatory" option="email"/></td>
		</tr>
		<tr>
			<th class="thEdit rt mandatory"><mc:msg key="sba0204.header.maxRowsPerPage"/></th>
			<td class="tdEdit">
				<ui:select name="maxRowsPerPage" checkName="sba0204.header.maxRowsPerPage" options="mandatory">
<%
				for (int i=0; i<maxRowPerPage.length; i++) {
%>
					<option value="<%=maxRowPerPage[i]%>"><%=maxRowPerPage[i]%></option>
<%
				}
%>
				</ui:select>
			</td>
			<th class="thEdit rt mandatory"><mc:msg key="sba0204.header.pageNumsPerPage"/></th>
			<td class="tdEdit">
				<ui:select name="pageNumsPerPage" checkName="sba0204.header.pageNumsPerPage" options="mandatory">
<%
				for (int i=0; i<pageNumPerPage.length; i++) {
%>
					<option value="<%=pageNumPerPage[i]%>"><%=pageNumPerPage[i]%></option>
<%
				}
%>
				</ui:select>
			</td>
		</tr>
		<tr>
			<th class="thEdit rt mandatory"><mc:msg key="sba0204.header.status"/></th>
			<td class="tdEdit"><ui:ccselect name="userStatus" codeType="USER_STATUS" selectedValue="<%=defaultUserStatus%>" options="mandatory"/></td>
			<th class="thEdit rt mandatory"><mc:msg key="sba0204.header.active"/></th>
			<td class="tdEdit"><ui:ccselect name="isActive" codeType="IS_ACTIVE" options="mandatory"/></td>
		</tr>
		<tr>
			<th class="thEdit rt"><mc:msg key="sba0204.header.insertUser"/></th>
			<td class="tdEdit"><ui:text name="insertUser" status="display"/></td>
			<th class="thEdit rt"><mc:msg key="sba0204.header.insertDate"/></th>
			<td class="tdEdit"><ui:text name="insertDate" status="display"/></td>
		</tr>
		<tr>
			<th class="thEdit rt"><mc:msg key="sba0204.header.updateUser"/></th>
			<td class="tdEdit"><ui:text name="updateUser" status="display"/></td>
			<th class="thEdit rt"><mc:msg key="sba0204.header.updateDate"/></th>
			<td class="tdEdit"><ui:text name="updateDate" status="display"/></td>
		</tr>
	</table>
</div>
<div id="divPagingArea"></div>
<%/************************************************************************************************
* Right & Footer
************************************************************************************************/%>
</div>
</div>
<%/************************************************************************************************
* Additional Elements
************************************************************************************************/%>
</form>
<%/************************************************************************************************
* Additional Form
************************************************************************************************/%>
<form id="fmHidden" name="fmHidden" method="post" action=""></form>
</body>
</html>