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
	String langCode = CommonUtil.upperCase((String)session.getAttribute("langCode"));
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
var popup;
var searchResultDataCount = 0;
var langCode = jsconfig.get("langCode");
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
			commonJs.warn(com.message.I902);
			return;
		}

		popup = commonJs.openPopup({
			popupId:"SourceGeneratorInfo",
			url:"/zebra/framework/sourcegenerator/getGeneratorInfo.do",
			header:"<mc:msg key="fwk.sourcegenerator.title.generatorPopupHeader"/>",
			paramData:{},
			blind:false,
			width:760,
			height:570
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
		commonJs.showProcMessageOnElement("divScrollablePanel");

		if (commonJs.doValidate($("#fmDefault"))) {
			setTimeout(function() {
				commonJs.ajaxSubmit({
					url:"/zebra/framework/sourcegenerator/getList.do",
					dataType:"json",
					formId:"fmDefault",
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
				var gridTr = new UiGridTr();
				var space = "", style = "", menuId = "";
				var delimiter = jsconfig.get("dataDelimiter");
				var isLeaf = commonJs.toNumber(dataSet.getValue(i, "IS_LEAF"));
				var iLevel = commonJs.toNumber(dataSet.getValue(i, "MENU_LEVEL")) - 1;
				var isActive = commonJs.toBoolean(dataSet.getValue(i, "IS_ACTIVE"));

				for (var j=0; j<iLevel; j++) {
					space += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				}

				menuId = dataSet.getValue(i, "ROOT")+delimiter+dataSet.getValue(i, "MENU_ID");
				style += (isLeaf != 1) ? "font-weight:bold;" : "";

				var tdSelect = new UiGridTd();
				tdSelect.addClassName("Ct");
				if (isActive) {
					var uiChk = new UiCheckbox();
					uiChk.setId("chkForGenerate").setName("chkForGenerate").setValue(menuId).addAttribute("menuName:"+dataSet.getValue(i, "MENU_NAME_"+langCode));
					tdSelect.addChild(uiChk);
				}
				gridTr.addChild(tdSelect);

				gridTr.addChild(new UiGridTd().addClassName("Lt").setStyle(style).addTextBeforeChild(space).setText(dataSet.getValue(i, "MENU_ID")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setStyle(style).setText(dataSet.getValue(i, "MENU_NAME_"+langCode)));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(dataSet.getValue(i, "MENU_URL")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(dataSet.getValue(i, "DESCRIPTION")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(dataSet.getValue(i, "CREATION_DATE")));

				var tdAction = new UiGridTd();
				tdAction.addClassName("Ct");
				if (isActive) {
					var iconAction = new UiIcon();
					iconAction.setId("icnAction").setName("icnAction").addClassName("fa-tasks fa-lg").addAttribute("menuId:"+menuId)
					.setScript("doAction(this)").addAttribute("title:"+com.header.action);
					tdAction.addChild(iconAction);
				}
				gridTr.addChild(tdAction);

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:7").setText(com.message.I001));
			html += gridTr.toHtmlString();
		}

		$("#tblGridBody").append($(html));

		$("#tblGrid").fixedHeaderTable({
			attachTo:$("#divDataArea"),
			pagingArea:$("#divPagingArea"),
			isPageable:false,
			totalResultRows:result.totalResultRows,
			script:"exeSearch"
		});

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(contextMenu);
		});

		commonJs.hideProcMessageOnElement("divScrollablePanel");
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
			classPrefix:com.constants.ctxClassPrefixGrid,
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0,
			verAdjust:2
		});
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		commonJs.getBootstrapSelectbox("searchMenu").focus();

		exeSearch();
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
					<col width="100%"/>
				</colgroup>
				<tr>
					<td class="tdDefault">
						<label for="searchMenu" class="lblEn hor"><mc:msg key="fwk.sourcegenerator.searchMenu"/></label>
						<select id="searchMenu" name="searchMenu" class="bootstrapSelect">
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
	<table id="tblGrid" class="tblGrid sort autosort">
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
				<th class="thGrid"><ui:icon id="icnCheck" className="fa-check-square-o fa-lg icnEn" title="fwk.sourcegenerator.title.selectToGenerate"/></th>
				<th class="thGrid"><mc:msg key="fwk.sourcegenerator.dataGridHeader.menuId"/></th>
				<th class="thGrid"><mc:msg key="fwk.sourcegenerator.dataGridHeader.menuName"/></th>
				<th class="thGrid"><mc:msg key="fwk.sourcegenerator.dataGridHeader.menuUrl"/></th>
				<th class="thGrid"><mc:msg key="fwk.sourcegenerator.dataGridHeader.menuDesc"/></th>
				<th class="thGrid"><mc:msg key="fwk.sourcegenerator.dataGridHeader.creationDate"/></th>
				<th class="thGrid"><mc:msg key="page.com.action"/></th>
			</tr>
		</thead>
		<tbody id="tblGridBody">
			<tr>
				<td class="tdGrid Ct" colspan="7"><mc:msg key="I002"/></td>
			</tr>
		</tbody>
	</table>
</div>
<div id="divPagingArea" class="areaContainer"></div>
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