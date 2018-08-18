<%/************************************************************************************************
* Description
* - 
************************************************************************************************/%>
<%@ include file="/webapp/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity paramEntity = (ParamEntity)request.getAttribute("paramEntity");
	DataSet requestDataSet = (DataSet)paramEntity.getRequestDataSet();
	DataSet resultDataSet = (DataSet)paramEntity.getObject("resultDataSet");
%>
<%/************************************************************************************************
* HTML
************************************************************************************************/%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><tag:msg key="fwk.main.system.title"/></title>
<%/************************************************************************************************
* Stylesheet & Javascript
************************************************************************************************/%>
<%@ include file="/webapp/shared/page/incCssJs.jsp"%>
<script type="text/javascript">
$(function() {
	/*!
	 * event
	 */
	$("#btnClose").click(function(event) {
		parent.popup.close();
	});

	/*!
	 * process
	 */

	/*!
	 * load event (document / window)
	 */
	$(window).ready(function() {
		setTimeout(function() {
			$("#tblFixedHeaderTable").fixedHeaderTable({
				baseDivElement:"divScrollablePanelPopup",
				widthAdjust:-6
			});
		}, 1000);
	});
});
</script>
</head>
<%/************************************************************************************************
* Page & Header
************************************************************************************************/%>
<body>
<form id="fmDefault" name="fmDefault" method="post" action="">
<div id="divPopupWindowHolder">
<div id="divFixedPanelPopup">
<div id="divLocationPathArea"><%@ include file="/webapp/zebra/example/common/include/bodyLocationPathArea.jsp"%></div>
<%/************************************************************************************************
* Real Contents - fixed panel(tab, button, search, information)
************************************************************************************************/%>
<div id="divTabArea"></div>
<div id="divButtonArea" class="areaContainerPopup">
	<div id="divButtonAreaLeft"></div>
	<div id="divButtonAreaRight">
		<tag:button id="btnClose" caption="button.com.close" iconClass="fa-times"/>
	</div>
</div>
<div id="divSearchCriteriaArea"></div>
<div id="divInformArea" class="areaContainerPopup">
	<table class="tblEdit">
		<colgroup>
			<col width="15%"/>
			<col width="35%"/>
			<col width="15%"/>
			<col width="35%"/>
		</colgroup>
		<tr>
			<th class="thEditRt"><tag:msg key="fwk.dtogenerator.dataGridHeader.tableName"/></th>
			<td class="tdEdit"><%=requestDataSet.getValue("tableName")%></td>
			<th class="thEditRt"><tag:msg key="fwk.dtogenerator.dataGridHeader.tableDesc"/></th>
			<td class="tdEdit"><%=CommonUtil.abbreviate(resultDataSet.getValue("TABLE_DESCRIPTION"), 50)%></td>
		</tr>
	</table>
</div>
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
	<table id="tblFixedHeaderTable" class="tblGrid sort">
		<colgroup>
			<col width="25%"/>
			<col width="10%"/>
			<col width="10%"/>
			<col width="7%"/>
			<col width="8%"/>
			<col width="8%"/>
			<col width="*"/>
		</colgroup>
		<thead>
			<tr>
				<th class="thGrid sortable:string"><tag:msg key="fwk.dtogenerator.header.columnName"/></th>
				<th class="thGrid sortable:string"><tag:msg key="fwk.dtogenerator.header.dataType"/></th>
				<th class="thGrid sortable:string"><tag:msg key="fwk.dtogenerator.header.defaultValue"/></th>
				<th class="thGrid sortable:numeric"><tag:msg key="fwk.dtogenerator.header.length"/></th>
				<th class="thGrid sortable:string"><tag:msg key="fwk.dtogenerator.header.nullable"/></th>
				<th class="thGrid sortable:string"><tag:msg key="fwk.dtogenerator.header.keyType"/></th>
				<th class="thGrid sortable:string"><tag:msg key="fwk.dtogenerator.header.description"/></th>
			</tr>
		</thead>
		<tbody>
<%
		if (resultDataSet.getRowCnt() > 0) {
			for (int i=0; i<resultDataSet.getRowCnt(); i++) {
%>
			<tr>
				<td class="tdGrid"><%=resultDataSet.getValue(i, "COLUMN_NAME")%></td>
				<td class="tdGridCt"><%=resultDataSet.getValue(i, "DATA_TYPE")%></td>
				<td class="tdGridCt"><%=resultDataSet.getValue(i, "DATA_DEFAULT")%></td>
				<td class="tdGridCt"><%=resultDataSet.getValue(i, "DATA_LENGTH")%></td>
				<td class="tdGridCt"><%=resultDataSet.getValue(i, "NULLABLE")%></td>
				<td class="tdGridCt"><%=resultDataSet.getValue(i, "CONSTRAINT_TYPE")%></td>
				<td class="tdGrid"><%=CommonUtil.abbreviate(resultDataSet.getValue(i, "COMMENTS"), 35)%></td>
			</tr>
<%
			}
		}
%>
		</tbody>
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