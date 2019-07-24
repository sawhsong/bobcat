<%/************************************************************************************************
* Description - Sba0416 - Tax Table Mgmt.
*	- Generated by Source Generator
************************************************************************************************/%>
<%@ include file="/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity paramEntity = (ParamEntity)request.getAttribute("paramEntity");
	DataSet requestDataSet = (DataSet)paramEntity.getRequestDataSet();
	SysTaxMaster sysTaxMaster = (SysTaxMaster)paramEntity.getObject("sysTaxMaster");
	int currentYear = CommonUtil.toInt(CommonUtil.getSysdate("YYYY"));
	String numberFormat = "#,##0.00";
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
			<ui:button id="btnDelete" caption="button.com.delete" iconClass="fa-trash"/>
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
	<table class="tblEdit">
		<colgroup>
			<col width="17%"/>
			<col width="33%"/>
			<col width="17%"/>
			<col width="33%"/>
		</colgroup>
		<tr>
			<th class="thEdit Rt mandatory"><mc:msg key="sba0416.header.taxMasterId"/></th>
			<td class="tdEdit"><ui:text name="taxMasterId" value="<%=sysTaxMaster.getTaxMasterId()%>" status="display" checkName="sba0416.header.taxMasterId" options="mandatory"/></td>
			<th class="thEdit Rt mandatory"><mc:msg key="sba0416.header.taxYear"/></th>
			<td class="tdEdit">
				<ui:select name="taxYear" options="mandatory">
<%
				for (int i=-5; i<6; i++) {
					String seleted = (CommonUtil.equals(CommonUtil.toString(currentYear - i), sysTaxMaster.getTaxYear())) ? "selected" : "";
%>
					<option value="<%=currentYear - i%>" <%=seleted%>><%=currentYear - i%></option>
<%
				}
%>
				</ui:select>
			</td>
		</tr>
		<tr>
			<th class="thEdit Rt mandatory"><mc:msg key="sba0416.header.wageType"/></th>
			<td class="tdEdit"><ui:ccselect name="wageType" codeType="WAGE_TYPE" options="mandatory" selectedValue="<%=sysTaxMaster.getWageType()%>"/></td>
			<th class="thEdit Rt mandatory"><mc:msg key="sba0416.header.grossIncome"/></th>
			<td class="tdEdit"><ui:text name="grossIncome" value="<%=CommonUtil.toString(sysTaxMaster.getGross(), numberFormat)%>" className="numeric" checkName="sba0416.header.grossIncome" options="mandatory"/></td>
		</tr>
		<tr>
			<th class="thEdit Rt"><mc:msg key="sba0416.header.resident"/></th>
			<td class="tdEdit"><ui:text name="resident" value="<%=CommonUtil.toString(sysTaxMaster.getResident(), numberFormat)%>" className="numeric" checkName="sba0416.header.resident"/></td>
			<th class="thEdit Rt"><mc:msg key="sba0416.header.nonResident"/></th>
			<td class="tdEdit"><ui:text name="nonResident" value="<%=CommonUtil.toString(sysTaxMaster.getNonResident(), numberFormat)%>" className="numeric" checkName="sba0416.header.nonResident"/></td>
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