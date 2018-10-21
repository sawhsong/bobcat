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
	String defaultDatasource = CommonUtil.split(ConfigUtil.getProperty("jdbc.multipleDatasource"), ConfigUtil.getProperty("delimiter.data"))[0];
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
</style>
<script type="text/javascript">
var popupDetail = null;
var popupInfo = null;
var searchResultDataCount = 0;

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
			blind:true,
			width:1000,
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
				event.preventDefault();
			}
		}
	});

	/*!
	 * process
	 */
	exeSearch = function() {
		commonJs.showProcMessageOnElement("divScrollablePanel");

		if (commonJs.doValidate($("#fmDefault"))) {
			setTimeout(function() {
				commonJs.ajaxSubmit({
					formId:"fmDefault",
					url:"/zebra/framework/dtogenerator/getList.do",
					dataType:"json",
					success:function(data, textStatus) {
						var result = commonJs.parseAjaxResult(data, textStatus, "json");

						if (result.isSuccess == true || result.isSuccess == "true") {
							renderDataGridTable(result);
						}
					}
				});
			}, 100);
		}
	};

	renderDataGridTable = function(result) {
		var dataSet = result.dataSet;
		var html = "";

		searchResultDataCount = dataSet.getRowCnt();

		$("#tblGridBody").html("");

		if (dataSet.getRowCnt() > 0) {
			for (var i=0; i<dataSet.getRowCnt(); i++) {
				var uiGridTr = new UiGridTr();

				var uiTd0 = new UiGridTd(), uiChk = new UiCheckbox();
				uiChk.setId("chkForGenerate").setName("chkForGenerate").setValue(dataSet.getValue(i, "TABLE_NAME"));
				uiTd0.addClassName("Ct").addChild(uiChk);
				uiGridTr.addChild(uiTd0);

				var uiTd1 = new UiGridTd(), uiAnc = new UiAnchor();
				uiAnc.setText(dataSet.getValue(i, "TABLE_NAME")).setScript("getDetail('"+dataSet.getValue(i, "TABLE_NAME")+"')");
				uiTd1.addClassName("Lt").addChild(uiAnc);
				uiGridTr.addChild(uiTd1);

				var uiTd2 = new UiGridTd();
				uiTd2.addClassName("Lt").setText(dataSet.getValue(i, "COMMENTS"));
				uiGridTr.addChild(uiTd2);

				var uiTd3 = new UiGridTd(), uiIcon = new UiIcon();
				uiIcon.setId("icnAction").setName("icnAction").addClassName("fa-tasks fa-lg").addAttribute("tableName:"+dataSet.getValue(i, "TABLE_NAME"))
					.addAttribute("title:<mc:msg key="page.com.action"/>").setScript("doAction(this)");
				uiTd3.addClassName("Ct").addChild(uiIcon);
				uiGridTr.addChild(uiTd3);

				html += uiGridTr.toHtmlString();
			}
		} else {
			var uiGridTr = new UiGridTr();

			var uiTd0 = new UiGridTd();
			uiTd0.addClassName("Ct").setAttribute("colspan:4").setText("<mc:msg key="I001"/>");
			uiGridTr.addChild(uiTd0);

			html += uiGridTr.toHtmlString();
		}

		$("#tblGridBody").append($(html));

		$("#tblGrid").fixedHeaderTable({
			attachTo:$("#divDataArea"),
			pagingArea:$("#divPagingArea"),
			isPageable:false,
			displayRowCount:false,
			isFilter:false,
			filterColumn:[1, 2],
			totalResultRows:result.totalResultRows,
			script:"exeSearch"
		});

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenu.dtoGeneratorAction);
		});

		commonJs.hideProcMessageOnElement("divScrollablePanel");
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
			width:1200,
			height:700
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
		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenu.dtoGeneratorAction);
		});
		exeSearch();
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
		</ui:buttonGroup>
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
						<ui:select id="dataSource" name="dataSource">
<%
						for (int i=0; i<datasourceDataSet.getRowCnt(); i++) {
							String selected = (CommonUtil.equalsIgnoreCase(datasourceDataSet.getValue(i, "VALUE"), defaultDatasource)) ? "selected" : "";
%>
							<option value="<%=datasourceDataSet.getValue(i, "VALUE")%>" <%=selected%>><%=datasourceDataSet.getValue(i, "NAME")%></option>
<%
						}
%>
						</ui:select>
					</td>
					<td class="tdDefault">
						<label for="tableName" class="lblEn hor"><mc:msg key="fwk.dtogenerator.tableName"/></label>
						<ui:text id="tableName" name="tableName" className="defClass hor" style="width:280px"/>
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
	<table id="tblGrid" class="tblGrid sort autosort">
		<colgroup>
			<col width="4%"/>
			<col width="32%"/>
			<col width="59%"/>
			<col width="5%"/>
		</colgroup>
		<thead>
			<tr>
				<th class="thGrid"><ui:icon id="icnCheck" className="fa-check-square-o fa-lg icnEn" title="fwk.dtogenerator.title.selectToGenerate"/></th>
				<th class="thGrid sortable:alphanumeric"><mc:msg key="fwk.dtogenerator.dataGridHeader.tableName"/></th>
				<th class="thGrid sortable:alphanumeric"><mc:msg key="fwk.dtogenerator.dataGridHeader.tableDesc"/></th>
				<th class="thGrid"><mc:msg key="page.com.action"/></th>
			</tr>
		</thead>
		<tbody id="tblGridBody">
			<tr>
				<td class="tdGrid Ct" colspan="4"><mc:msg key="I002"/></td>
			</tr>
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