<%/************************************************************************************************
* Description
* - 
************************************************************************************************/%>
<%@ include file="/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity paramEntity = (ParamEntity)request.getAttribute("paramEntity");
	DataSet datasourceDataSet = (DataSet)paramEntity.getObject("datasourceDataSet");
	DataSet requestDataSet = (DataSet)paramEntity.getRequestDataSet();
	DataSet resultDataSet = (DataSet)paramEntity.getObject("resultDataSet");
	String messageCode = "I001";
%>
<%/************************************************************************************************
* HTML
************************************************************************************************/%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title><mc:msg key="fwk.main.system.title"/></title>
<%/************************************************************************************************
* Stylesheet & Javascript
************************************************************************************************/%>
<%@ include file="/shared/page/incCssJs.jsp"%>
<style type="text/css">
</style>
<script type="text/javascript">
var popupDetail = null;
var popupInfo = null;

$(function() {
	/*!
	 * event
	 */
	$("#btnGenerate").click(function(event) {
		if (commonJs.getCountChecked("chkForGenerate") == 0) {
			commonJs.warn("<mc:msg key="I902"/>");
			return;
		}

		popupInfo = commonJs.openPopup({
			popupId:"DTOGeneratorInfo",
			url:"/zebra/framework/dtogenerator/getGeneratorInfo.do",
			header:"<mc:msg key="fwk.dtogenerator.title.generatorPopupHeader"/>",
			paramData:{dataSource:$("#dataSource").val()},
			blind:false,
			width:800,
			height:520
		});
	});

	$("#btnSearch").click(function(event) {
		exeSearch();
	});

	$("#dataSource").change(function(event) {
		exeSearch();
	});

	$("#btnClear").click(function(event) {
		commonJs.clearSearchCriteria();
	});

	$("#icnCheck").click(function(event) {
		commonJs.toggleCheckboxes("chkForGenerate");
	});

	$(document).keypress(function(event) {
		if (event.which == 13) {
			var element = event.target;

			if ($(element).is("[name=tableName]")) {
				exeSearch();
			}
		}
	});

	/*!
	 * process
	 */
	exeSearch = function() {
		if (commonJs.doValidate($("#fmDefault"))) {
			commonJs.doSubmit({
				form:"fmDefault",
				action:"/zebra/framework/dtogenerator/getList.do"
			});
		}
	};

	getDetail = function(tableName) {
		popupDetail = commonJs.openPopup({
			popupId:"TableDetail",
			url:"/zebra/framework/dtogenerator/getDetail.do",
			paramData:{
				tableName:tableName,
				dataSource:$("#dataSource").val()
			},
			header:"<mc:msg key="fwk.dtogenerator.title.detailPopupHeader"/>",
			width:1000,
			height:650
		});
	};

	doAction = function(img) {
		var tableName = $(img).attr("tableName");
		var dataSource = $("#dataSource").val();

		$("input:checkbox[name=chkForGenerate]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == tableName) {
				$(this).prop("checked", true);
			} else {
				$(this).prop("checked", false);
			}
		});

		ctxMenu.dtoGeneratorAction[0].fun = function() {getDetail(tableName);};
		ctxMenu.dtoGeneratorAction[1].fun = function() {$("#btnGenerate").trigger("click");};

		$(img).contextMenu(ctxMenu.dtoGeneratorAction, {
			classPrefix:"actionInGrid",
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0,
			verAdjust:2,
			containment:$("#divScrollablePanel")
		});
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		$("#tblFixedHeaderTable").fixedHeaderTable({
			baseDivElement:"divScrollablePanel",
			widthAdjust:26
		});

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenu.dtoGeneratorAction);
		});

		$("#tableName").focus();
	});
});
</script>
</head>
<%/************************************************************************************************
* Page & Header
************************************************************************************************/%>
<body>
<form id="fmDefault" name="fmDefault" method="post" action="">
<div id="divHeaderHolder" class="ui-layout-north"><%@ include file="/zebra/example/common/include/header.jsp"%></div>
<div id="divBodyHolder" class="ui-layout-center">
<div id="divBodyLeft" class="ui-layout-west"><%@ include file="/zebra/example/common/include/bodyLeft.jsp"%></div>
<div id="divBodyCenter" class="ui-layout-center">
<div id="divFixedPanel">
<div id="divLocationPathArea"><%@ include file="/zebra/example/common/include/bodyLocationPathArea.jsp"%></div>
<%/************************************************************************************************
* Real Contents - fixed panel(tab, button, search, information)
************************************************************************************************/%>
<div id="divTabArea"></div>
<div id="divButtonArea" class="areaContainer">
	<div id="divButtonAreaLeft"></div>
	<div id="divButtonAreaRight">
		<ui:buttonGroup id="buttonGroup">
			<ui:button id="btnGenerate" caption="button.com.generate" iconClass="fa-gears"/>
			<ui:button id="btnSearch" caption="button.com.search" iconClass="fa-search"/>
			<ui:button id="btnClear" caption="button.com.clear" iconClass="fa-refresh"/>
		</tag:buttonGroup>
	</div>
</div>
<div id="divSearchCriteriaArea" class="areaContainer">
	<div class="panel panel-default">
		<div class="panel-body">
			<table class="tblDefault">
				<colgroup>
					<col width="50%"/>
					<col width="50%"/>
				</colgroup>
				<tr>
					<td class="tdDefault">
						<label for="dataSource" class="lblEn hor"><mc:msg key="fwk.dtogenerator.dataSource"/></label>
						<select id="dataSource" name="dataSource" class="bootstrapSelect default">
<%
						for (int i=0; i<datasourceDataSet.getRowCnt(); i++) {
							String selected = (CommonUtil.equalsIgnoreCase(datasourceDataSet.getValue(i, "VALUE"), "hkaccount")) ? "selected" : "";
%>
							<option value="<%=datasourceDataSet.getValue(i, "VALUE")%>" <%=selected%>><%=datasourceDataSet.getValue(i, "NAME")%></option>
<%
						}
%>
						</select>
					</td>
					<td class="tdDefault">
						<label for="tableName" class="lblEn hor"><mc:msg key="fwk.dtogenerator.tableName"/></label>
						<input type="text" id="tableName" name="tableName" class="txtEn hor" style="width:280px"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
<div id="divInformArea"></div>
<%/************************************************************************************************
* End of fixed panel
************************************************************************************************/%>
<div class="breaker"></div>
</div>
<div id="divScrollablePanel">
<%/************************************************************************************************
* Real Contents - scrollable panel(data, paging)
************************************************************************************************/%>
<div id="divDataArea" class="areaContainer">
	<table id="tblFixedHeaderTable" class="tblGrid sort autosort">
		<colgroup>
			<col width="4%"/>
			<col width="32%"/>
			<col width="59%"/>
			<col width="5%"/>
		</colgroup>
		<thead>
			<tr>
				<th class="thGrid">
					<i id="icnCheck" class="fa fa-check-square-o fa-lg icnEn" title="<mc:msg key="fwk.dtogenerator.title.selectToGenerate"/>"></i>
				</th>
				<th class="thGrid sortable:string"><mc:msg key="fwk.dtogenerator.dataGridHeader.tableName"/></th>
				<th class="thGrid sortable:string"><mc:msg key="fwk.dtogenerator.dataGridHeader.tableDesc"/></th>
				<th class="thGrid"><mc:msg key="page.com.action"/></th>
			</tr>
		</thead>
		<tbody>
<%
		if (resultDataSet.getRowCnt() > 0) {
			for (int i=0; i<resultDataSet.getRowCnt(); i++) {
%>
			<tr>
				<td class="tdGridCt">
					<input type="checkbox" id="chkForGenerate" name="chkForGenerate" class="chkEn inTblGrid" value="<%=resultDataSet.getValue(i, "TABLE_NAME")%>"/>
				</td>
				<td class="tdGridLt">
					<a class="aEn" onclick="getDetail('<%=resultDataSet.getValue(i, "TABLE_NAME")%>')" class="aNormal"><%=resultDataSet.getValue(i, "TABLE_NAME")%></a>
				</td>
				<td class="tdGridLt"><%=resultDataSet.getValue(i, "COMMENTS")%></td>
				<td class="tdGridCt">
					<i name="icnAction" class="fa fa-tasks fa-lg icnEn" tableName="<%=resultDataSet.getValue(i, "TABLE_NAME")%>" onclick="doAction(this)" title="<mc:msg key="page.com.action"/>"></i>
				</td>
			</tr>
<%
			}
		} else {
%>
			<tr>
				<td class="tdGridCt" colspan="4"><mc:msg key="<%=messageCode%>"/></td>
			</tr>
<%
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
<div id="divBodyRight" class="ui-layout-east"><%@ include file="/zebra/example/common/include/bodyRight.jsp"%></div>
</div>
<div id="divFooterHolder" class="ui-layout-south"><%@ include file="/zebra/example/common/include/footer.jsp"%></div>
<%/************************************************************************************************
* Additional Elements
************************************************************************************************/%>
</form>
<%/************************************************************************************************
* Additional Form
************************************************************************************************/%>
<form id="fmHidden" name="fmHidden" method="post" action="">
</form>
</body>
</html>