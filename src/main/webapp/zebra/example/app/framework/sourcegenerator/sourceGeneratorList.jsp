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
	DataSet requestDataSet = (DataSet)paramEntity.getRequestDataSet();
	DataSet searchMenuDataSet = (DataSet)paramEntity.getObject("searchMenu");
	DataSet resultDataSet = (DataSet)paramEntity.getObject("resultDataSet");
	String langCode = CommonUtil.upperCase((String)session.getAttribute("langCode"));
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
var popup;
var contextMenu = [{
	name:"Generate",
	img:"fa-gears",
	fun:function() {}
}];
$(function() {
	/*!
	 * event
	 */
	$("#btnGenerate").click(function(event) {
		if (commonJs.getCountChecked("chkForGenerate") == 0) {
			commonJs.warn("<mc:msg key="I902"/>");
			return;
		}

		popup = commonJs.openPopup({
			popupId:"SourceGeneratorInfo",
			url:"/zebra/framework/sourcegenerator/getGeneratorInfo.do",
			header:"<mc:msg key="fwk.sourcegenerator.title.generatorPopupHeader"/>",
			paramData:{},
			blind:false,
			width:680,
			height:580
		});
	});

	$("#btnSearch").click(function(event) {
		exeSearch();
	});

	$("#btnClear").click(function(event) {
		commonJs.clearSearchCriteria();
	});

	$("#searchMenu").change(function() {
		exeSearch();
	});

	$("#icnCheck").click(function(event) {
		commonJs.toggleCheckboxes("chkForGenerate");
	});

	$(document).keypress(function(event) {
		if (event.which == 13) {
		}
	});

	/*!
	 * process
	 */
	exeSearch = function() {
		if (commonJs.doValidate($("#fmDefault"))) {
			commonJs.doSubmit({
				form:"fmDefault",
				data:{
				},
				action:"/zebra/framework/sourcegenerator/getList.do"
			});
		}
	};

	doAction = function(img) {
		var menuId = $(img).attr("menuId");

		$("input:checkbox[name=chkForGenerate]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == menuId) {
				$(this).prop("checked", true);
			} else {
				$(this).prop("checked", false);
			}
		});

		contextMenu[0].fun = function() {$("#btnGenerate").trigger("click");};

		$(img).contextMenu(contextMenu, {
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
			widthAdjust:22
		});

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(contextMenu);
		});

		commonJs.getBootstrapSelectbox("searchMenu").focus();
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
					<col width="100%"/>
				</colgroup>
				<tr>
					<td class="tdDefault">
						<label for="searchMenu" class="lblEn hor"><mc:msg key="fwk.sourcegenerator.searchMenu"/></label>
						<select id="searchMenu" name="searchMenu" class="bootstrapSelect default">
							<option value="">==Select==</option>
<%
						for (int i=0; i<searchMenuDataSet.getRowCnt(); i++) {
%>
							<option value="<%=searchMenuDataSet.getValue(i, "MENU_ID")%>"><%=searchMenuDataSet.getValue(i, "MENU_NAME_"+langCode)%>(<%=searchMenuDataSet.getValue(i, "MENU_ID")%>)</option>
<%
						}
%>
						</select>
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
			<col width="3%"/>
			<col width="10%"/>
			<col width="25%"/>
			<col width="15%"/>
			<col width="*"/>
			<col width="8%"/>
			<col width="4%"/>
		</colgroup>
		<thead>
			<tr>
				<th class="thGrid"><i id="icnCheck" class="fa fa-check-square-o fa-lg icnEn" title="<mc:msg key="fwk.sourcegenerator.title.selectToGenerate"/>"></i></th>
				<th class="thGrid"><mc:msg key="fwk.sourcegenerator.dataGridHeader.menuId"/></th>
				<th class="thGrid"><mc:msg key="fwk.sourcegenerator.dataGridHeader.menuName"/></th>
				<th class="thGrid"><mc:msg key="fwk.sourcegenerator.dataGridHeader.menuUrl"/></th>
				<th class="thGrid"><mc:msg key="fwk.sourcegenerator.dataGridHeader.menuDesc"/></th>
				<th class="thGrid"><mc:msg key="fwk.sourcegenerator.dataGridHeader.creationDate"/></th>
				<th class="thGrid"><mc:msg key="page.com.action"/></th>
			</tr>
		</thead>
		<tbody>
<%
		if (resultDataSet.getRowCnt() > 0) {
			for (int i=0; i<resultDataSet.getRowCnt(); i++) {
				String space = "", style = "", menuId = "";
				String delimiter = ConfigUtil.getProperty("delimiter.data");
				boolean activate = false;
				int isLeaf = CommonUtil.toInt(resultDataSet.getValue(i, "IS_LEAF"));
				int iLevel = CommonUtil.toInt(resultDataSet.getValue(i, "MENU_LEVEL")) - 1;

				for (int j=0; j<iLevel; j++) {
					space += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				}

				if (isLeaf == 1 && !BeanHelper.containsBean(CommonUtil.toCamelCaseStartLowerCase(resultDataSet.getValue(i, "MENU_ID"))+"Action")) {
					activate = true;
				}

				menuId = resultDataSet.getValue(i, "ROOT")+delimiter+resultDataSet.getValue(i, "MENU_ID");
				style += (isLeaf != 1) ? "font-weight:bold;" : "";
%>
			<tr>
				<td class="tdGridCt">
<%
				if (activate) {
%>
					<input type="checkbox" id="chkForGenerate" name="chkForGenerate" class="chkEn inTblGrid" value="<%=menuId%>" menuName="<%=resultDataSet.getValue(i, "MENU_NAME_"+langCode)%>"/>
<%
				}
%>
				</td>
				<td class="tdGrid" style="<%=style%>"><%=space%><%=resultDataSet.getValue(i, "MENU_ID")%></td>
				<td class="tdGrid" style="<%=style%>"><%=resultDataSet.getValue(i, "MENU_NAME_"+langCode)%></td>
				<td class="tdGrid"><%=resultDataSet.getValue(i, "MENU_URL")%></td>
				<td class="tdGrid"><%=resultDataSet.getValue(i, "DESCRIPTION")%></td>
				<td class="tdGridCt"><%=resultDataSet.getValue(i, "CREATION_DATE")%></td>
				<td class="tdGridCt">
<%
				if (activate) {
%>
					<i name="icnAction" class="fa fa-tasks fa-lg icnEn" menuId="<%=menuId%>" onclick="doAction(this)" title="<mc:msg key="page.com.action"/>"></i>
<%
				}
%>
				</td>
			</tr>
<%
			}
		} else {
%>
			<tr>
				<td class="tdGridCt" colspan="7"><mc:msg key="<%=messageCode%>"/></td>
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
<form id="fmHidden" name="fmHidden" method="post" action=""></form>
</body>
</html>