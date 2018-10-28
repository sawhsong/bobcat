<%/************************************************************************************************
* Description
* - 
************************************************************************************************/%>
<%@ include file="/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity pe = (ParamEntity)request.getAttribute("paramEntity");
	DataSet dsRequest = (DataSet)pe.getRequestDataSet();
	DataSet resultDataSet = (DataSet)pe.getObject("resultDataSet");
	String langCode = CommonUtil.upperCase((String)session.getAttribute("langCode"));
	String isActive = "", isDefault = "", disableFlag = "";
	int masterRow = -1;

	if (resultDataSet.getRowCnt() > 0) {
		masterRow = resultDataSet.getRowIndex("COMMON_CODE", "0000000000");
		isActive = resultDataSet.getValue(masterRow, "USE_YN");
		isDefault = resultDataSet.getValue(masterRow, "DEFAULT_YN");
		if (CommonUtil.equals(isDefault, "Y")) {disableFlag = "disabled";}
	}
%>
<%/************************************************************************************************
* HTML
************************************************************************************************/%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="icon" type="image/png" href="<mc:cp key="imgIcon"/>/zebraFavicon.png">
<title><mc:msg key="fwk.main.system.title"/></title>
<%/************************************************************************************************
* Stylesheet & Javascript
************************************************************************************************/%>
<%@ include file="/shared/page/incCssJs.jsp"%>
<style type="text/css">
.codeDetail {list-style:none;margin-top:4px;}
</style>
<script type="text/javascript" src="<mc:cp key="viewPageJsName"/>"></script>
<script type="text/javascript">
var codeType = "<%=resultDataSet.getValue(masterRow, "CODE_TYPE")%>";
</script>
</head>
<%/************************************************************************************************
* Page & Header
************************************************************************************************/%>
<body>
<form id="fmDefault" name="fmDefault" method="post" action="">
<div id="divPopupWindowHolder">
<div id="divFixedPanelPopup">
<div id="divLocationPathArea"><%@ include file="/zebra/example/common/include/bodyLocationPathArea.jsp"%></div>
<%/************************************************************************************************
* Real Contents - fixed panel(tab, button, search, information)
************************************************************************************************/%>
<div id="divTabArea"></div>
<div id="divButtonArea" class="areaContainerPopup">
	<div id="divButtonAreaLeft"></div>
	<div id="divButtonAreaRight">
		<ui:buttonGroup id="buttonGroup">
			<ui:button id="btnEdit" caption="button.com.edit" iconClass="fa-edit"/>
			<ui:button id="btnDelete" caption="button.com.delete" iconClass="fa-save" status="<%=disableFlag%>"/>
			<ui:button id="btnClose" caption="button.com.close" iconClass="fa-times"/>
		</ui:buttonGroup>
	</div>
</div>
<div id="divSearchCriteriaArea"></div>
<div id="divInformArea" class="areaContainerPopup">
	<table class="tblEdit">
		<caption class="captionEdit"><mc:msg key="fwk.commoncode.searchHeader.codeType"/></caption>
		<colgroup>
			<col width="15%"/>
			<col width="35%"/>
			<col width="15%"/>
			<col width="35%"/>
		</colgroup>
		<tr>
			<th class="thEdit Rt"><mc:msg key="fwk.commoncode.header.codeType"/></th>
			<td class="tdEdit"><%=resultDataSet.getValue(masterRow, "CODE_TYPE")%></td>
			<th class="thEdit Rt"><mc:msg key="fwk.commoncode.header.useYn"/></th>
			<td class="tdEdit"><ui:ccradio name="useYnMaster" codeType="SIMPLE_YN" selectedValue="<%=isActive%>" status="disabled" source="framework"/></td>
		</tr>
		<tr>
			<th class="thEdit Rt"><mc:msg key="fwk.commoncode.header.descriptionEn"/></th>
			<td class="tdEdit"><%=resultDataSet.getValue(masterRow, "DESCRIPTION_EN")%></td>
			<th class="thEdit Rt"><mc:msg key="fwk.commoncode.header.descriptionKo"/></th>
			<td class="tdEdit"><%=resultDataSet.getValue(masterRow, "DESCRIPTION_KO")%></td>
		</tr>
	</table>
</div>
<%/************************************************************************************************
* End of fixed panel
************************************************************************************************/%>
<div class="breaker" style="height:5px;"></div>
</div>
<div id="divScrollablePanelPopup">
<%/************************************************************************************************
* Real Contents - scrollable panel(data, paging)
************************************************************************************************/%>
<div id="divDataArea" class="areaContainerPopup">
	<ul>
<%
	if (resultDataSet.getRowCnt() > 0) {
		for (int i=0; i<resultDataSet.getRowCnt(); i++) {
			String rdoIsActiveName = "useYnDetail_"+i;

			isActive = resultDataSet.getValue(i, "USE_YN");

			if (i == masterRow) {continue;}
%>
		<li class="codeDetail">
			<table class="tblEdit">
				<colgroup>
					<col width="13%"/>
					<col width="*"/>
					<col width="13%"/>
					<col width="15%"/>
					<col width="11%"/>
					<col width="10%"/>
				</colgroup>
				<tr>
					<th class="thEdit Rt"><mc:msg key="fwk.commoncode.header.commonCode"/></th>
					<td class="tdEdit"><%=resultDataSet.getValue(i, "COMMON_CODE")%></td>
					<th class="thEdit Rt"><mc:msg key="fwk.commoncode.header.useYn"/></th>
					<td class="tdEdit"><ui:ccradio name="<%=rdoIsActiveName%>" codeType="SIMPLE_YN" selectedValue="<%=isActive%>" status="disabled" source="framework"/></td>
					<th class="thEdit Rt"><mc:msg key="fwk.commoncode.header.sortOrder"/></th>
					<td class="tdEdit"><%=resultDataSet.getValue(i, "SORT_ORDER")%></td>
				</tr>
				<tr>
					<th class="thEdit Rt"><mc:msg key="fwk.commoncode.header.descriptionEn"/></th>
					<td class="tdEdit"><%=resultDataSet.getValue(i, "DESCRIPTION_EN")%></td>
					<th class="thEdit Rt"><mc:msg key="fwk.commoncode.header.descriptionKo"/></th>
					<td class="tdEdit" colspan="3"><%=resultDataSet.getValue(i, "DESCRIPTION_KO")%></td>
				</tr>
			</table>
		</li>
<%
		}
	}
%>
	</ul>
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