<%/************************************************************************************************
* Description - Sys0208 - User Management
*	- Generated by Source Generator
************************************************************************************************/%>
<%@ include file="/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity paramEntity = (ParamEntity)request.getAttribute("paramEntity");
	DataSet requestDataSet = (DataSet)paramEntity.getRequestDataSet();
	String mode = requestDataSet.getValue("mode");
	String userId = requestDataSet.getValue("userId");
	DataSet authGroupDataSet = (DataSet)paramEntity.getObject("authGroupDataSet");
	String maxRowPerPage[] = (String[])paramEntity.getObject("maxRowPerPage");
	String pageNumPerPage[] = (String[])paramEntity.getObject("pageNumPerPage");
	String defaultPhotoPath = (String)paramEntity.getObject("defaultPhotoPath");
	String defaultAuthGroup = (String)paramEntity.getObject("defaultAuthGroup");
	String defaultUserStatus = (String)paramEntity.getObject("defaultUserStatus");
	String dateFormat = ConfigUtil.getProperty("format.date.java");
%>
<%/************************************************************************************************
* HTML
************************************************************************************************/%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="icon" type="image/png" href="<mc:cp key="imgIcon"/>/faviconBobcat.png">
<title><mc:msg key="main.system.title"/></title>
<%/************************************************************************************************
* Stylesheet & Javascript
************************************************************************************************/%>
<%@ include file="/shared/page/incCssJs.jsp"%>
<style type="text/css">
.thGrid {border-bottom:0px;}
.tblGrid tr:not(.default):not(.active):not(.info):not(.success):not(.warning):not(.danger):hover td {background:#FFFFFF;}
#liDummy {display:none;}
#divDataArea.areaContainerPopup {padding-top:0px;}
.dummyDetail {list-style:none;}
.deleteButton {cursor:pointer;}
</style>
<script type="text/javascript" src="<mc:cp key="viewPageJsName"/>"></script>
<script type="text/javascript">
var userId = "<%=userId%>";
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
<div id="divTabArea" class="areaContainerPopup">
	<ui:tab id="tabCategory">
		<ui:tabList caption="User Detail" isActive="true" iconClass="fa-user" iconPosition="left"/>
		<ui:tabList caption="Bank Account" iconClass="fa-university" iconPosition="left"/>
	</ui:tab>
</div>
<div id="divButtonArea" class="areaContainerPopup">
	<div id="divButtonAreaLeft"></div>
	<div id="divButtonAreaRight">
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
	<div id="div0" style="">
		<div class="divButtonArea">
			<div class="divButtonAreaRight">
				<ui:buttonGroup id="subButtonGroup">
					<ui:button id="btnSaveUserDetail" caption="button.com.save" iconClass="fa-save"/>
					<ui:button id="btnCloseUserDetail" caption="button.com.close" iconClass="fa-times"/>
				</ui:buttonGroup>
			</div>
		</div>
		<div class="verGap4"></div>
		<table class="tblEdit">
			<colgroup>
				<col width="*"/>
				<col width="13%"/>
				<col width="30%"/>
				<col width="15%"/>
				<col width="30%"/>
			</colgroup>
			<tr>
				<td class="tdEdit Ct" rowspan="3">
					<img id="imgUserPhoto" src="<%=defaultPhotoPath%>" class="imgDis" style="width:100%;height:110px;"/>
				</td>
				<th class="thEdit rt"><mc:msg key="sys0208.header.changePhoto"/></th>
				<td class="tdEdit" colspan="3"><ui:file name="photoPath" style="width:380px;"/></td>
			</tr>
			<tr>
				<th class="thEdit rt"><mc:msg key="sys0208.header.userId"/></th>
				<td class="tdEdit"><ui:text name="userId" status="display"/></td>
				<th class="thEdit rt mandatory"><mc:msg key="sys0208.header.userName"/></th>
				<td class="tdEdit"><ui:text name="userName" checkName="sys0208.header.userName" options="mandatory"/></td>
			</tr>
			<tr>
				<th class="thEdit rt mandatory"><mc:msg key="sys0208.header.loginId"/></th>
				<td class="tdEdit"><ui:text name="loginId" checkName="sys0208.header.loginId" options="mandatory"/></td>
				<th class="thEdit rt mandatory"><mc:msg key="sys0208.header.password"/></th>
				<td class="tdEdit"><ui:password name="password" checkName="sys0208.header.password" options="mandatory"/></td>
			</tr>
		</table>
		<div class="verGap6"></div>
		<table class="tblEdit">
			<colgroup>
				<col width="17%"/>
				<col width="33%"/>
				<col width="17%"/>
				<col width="33%"/>
			</colgroup>
			<tr>
				<th class="thEdit rt mandatory"><mc:msg key="sys0208.header.org"/></th>
				<td class="tdEdit">
					<ui:hidden name="orgId" checkName="sys0208.header.org"/>
					<ui:text name="orgName" className="hor" style="width:300px" checkName="sys0208.header.org" options="mandatory"/>
					<ui:icon id="icnOrgSearch" className="fa-search hor"/>
				</td>
				<th class="thEdit rt mandatory"><mc:msg key="sys0208.header.authGroup"/></th>
				<td class="tdEdit">
					<%=authGroupDataSet.getAsHtmlStringForSelectbox("GROUP_ID", "GROUP_NAME", defaultAuthGroup, "", "id:authGroup;name:authGroup;class:bootstrapSelect;options:mandatory")%>
				</td>
			</tr>
			<tr>
				<th class="thEdit rt mandatory"><mc:msg key="sys0208.header.language"/></th>
				<td class="tdEdit"><ui:ccselect name="language" codeType="LANGUAGE_TYPE" options="mandatory" status="disabled"/></td>
				<th class="thEdit rt mandatory"><mc:msg key="sys0208.header.themeType"/></th>
				<td class="tdEdit"><ui:ccselect name="themeType" codeType="USER_THEME_TYPE" options="mandatory" status="disabled"/></td>
			</tr>
			<tr>
				<th class="thEdit rt">Telephone</th>
				<td class="tdEdit"><ui:text name="telNumber" option="numeric"/></td>
				<th class="thEdit rt">Mobile</th>
				<td class="tdEdit"><ui:text name="mobileNumber" option="numeric"/></td>
			</tr>
			<tr>
				<th class="thEdit rt mandatory"><mc:msg key="sys0208.header.email"/></th>
				<td class="tdEdit"><ui:text name="email" checkName="sys0208.header.email" options="mandatory" option="email"/></td>
				<th class="thEdit rt mandatory">Default Start URL</th>
				<td class="tdEdit"><ui:text name="defaultStartUrl" value="/index/dashboard.do" checkName="Default Start URL" options="mandatory" status="disabled"/></td>
			</tr>
			<tr>
				<th class="thEdit rt mandatory"><mc:msg key="sys0208.header.maxRowsPerPage"/></th>
				<td class="tdEdit">
					<ui:select name="maxRowsPerPage" checkName="sys0208.header.maxRowsPerPage" options="mandatory">
<%
					for (int i=0; i<maxRowPerPage.length; i++) {
%>
						<option value="<%=maxRowPerPage[i]%>"><%=maxRowPerPage[i]%></option>
<%
					}
%>
					</ui:select>
				</td>
				<th class="thEdit rt mandatory"><mc:msg key="sys0208.header.pageNumsPerPage"/></th>
				<td class="tdEdit">
					<ui:select name="pageNumsPerPage" checkName="sys0208.header.pageNumsPerPage" options="mandatory" status="disabled">
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
				<th class="thEdit rt mandatory"><mc:msg key="sys0208.header.status"/></th>
				<td class="tdEdit"><ui:ccselect name="userStatus" codeType="USER_STATUS" selectedValue="<%=defaultUserStatus%>" options="mandatory" status="disabled"/></td>
				<th class="thEdit rt mandatory"><mc:msg key="sys0208.header.active"/></th>
				<td class="tdEdit"><ui:ccselect name="isActive" codeType="IS_ACTIVE" options="mandatory"/></td>
			</tr>
			<tr>
				<th class="thEdit rt">Authentication Key</th>
				<td class="tdEdit" colspan="3">
					<ui:text name="authenticationSecretKey" value="" checkName="Authentication Key" className="hor" status="disabled" style="width:40%;"/>
					<ui:button id="btnGetAuthenticationSecretKey" caption="Generate Key" iconClass="fa-key"/>
				</td>
			</tr>
			<tr>
				<th class="thEdit rt">Last Updated By</th>
				<td class="tdEdit"><ui:text name="lastUpdatedBy" status="display"/></td>
				<th class="thEdit rt">Last Updated Date</th>
				<td class="tdEdit"><ui:text name="lastUpdatedDate" status="display"/></td>
			</tr>
		</table>
	</div>
	<div id="div1" style="display:none">
		<div class="divButtonArea">
			<div class="divButtonAreaRight">
				<ui:buttonGroup id="subButtonGroup">
				<ui:button id="btnAddBankAccnt" caption="Add Bank Account" iconClass="fa-plus"/>
				<ui:button id="btnSaveBankAccnt" caption="button.com.save" iconClass="fa-save"/>
				<ui:button id="btnCloseBankAccnt" caption="button.com.close" iconClass="fa-times"/>
				</ui:buttonGroup>
			</div>
		</div>
		<div class="verGap4"></div>
		<div id="divGridWrapper">
			<table id="tblGrid" class="tblGrid">
				<colgroup>
					<col width="3%"/>
					<col width="23%"/>
					<col width="7%"/>
					<col width="13%"/>
					<col width="17%"/>
					<col width="10%"/>
					<col width="*"/>
				</colgroup>
				<thead>
					<tr>
						<th class="thGrid"></th>
						<th class="thGrid mandatory">Bank</th>
						<th class="thGrid mandatory">BSB</th>
						<th class="thGrid mandatory">Account Number</th>
						<th class="thGrid mandatory">Account Name</th>
						<th class="thGrid">Balance</th>
						<th class="thGrid">Description</th>
					</tr>
				</thead>
				<tbody id="tblGridBody">
					<tr class="noStripe">
						<td colspan="7" style="padding:0px;border-top:0px"><ul id="ulDetailHolder"></ul></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
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
<li id="liDummy" class="dummyDetail">
	<table class="tblGrid" style="border:0px">
		<colgroup>
			<col width="3%"/>
			<col width="23%"/>
			<col width="7%"/>
			<col width="13%"/>
			<col width="17%"/>
			<col width="10%"/>
			<col width="*"/>
		</colgroup>
		<tr class="noBorderAll">
			<th id="thDeleteButton" class="thGrid deleteButton"><i id="iDeleteButton" class="fa fa-lg fa-times"></i></th>
			<td class="tdGrid Lt"><ui:ccselect name="bankCode" checkName="Bank" codeType="BANK_TYPE" attribute="data-width:100%;" options="mandatory"/></td>
			<td class="tdGrid Ct">
				<ui:hidden name="bankAccntId"/>
				<ui:text name="bsb" className="Ct" checkName="BSB" options="mandatory" option="numeric"/>
			</td>
			<td class="tdGrid Ct"><ui:text name="accntNumber" className="Lt" checkName="Account Number" options="mandatory" option="numeric"/></td>
			<td class="tdGrid Ct"><ui:text name="accntName" className="Lt" checkName="Account Name" options="mandatory"/></td>
			<td class="tdGrid Ct"><ui:text name="balance" className="Rt numeric" option="numeric"/></td>
			<td class="tdGrid Ct"><ui:text name="description" className="Lt"/></td>
		</tr>
	</table>
</li>
</form>
<%/************************************************************************************************
* Additional Form
************************************************************************************************/%>
<form id="fmHidden" name="fmHidden" method="post" action=""></form>
</body>
</html>