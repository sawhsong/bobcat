<%/************************************************************************************************
* Description - Sba0406 - Expenditure Type
*   - Generated by Source Generator
************************************************************************************************/%>
<%@ include file="/webapp/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity paramEntity = (ParamEntity)request.getAttribute("paramEntity");
%>
<%/************************************************************************************************
* HTML
************************************************************************************************/%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="icon" type="image/png" href="<tag:cp key="imgIcon"/>/favicon.png">
<title><tag:msg key="main.system.title"/></title>
<%/************************************************************************************************
* Stylesheet & Javascript
************************************************************************************************/%>
<%@ include file="/webapp/shared/page/incCssJs.jsp"%>
<style type="text/css">
</style>
<script type="text/javascript">
var popup = null;
var widthMainTypeDiv, widthSubTypeDiv, mainTypeGridWidthAdjust, subTypeGridWidthAdjust;

$(function() {
	/*!
	 * event
	 */
	$("#icnCheckMainType").click(function(event) {
		commonJs.toggleCheckboxes("chkMainType");
	});

	$("#icnCheckSubType").click(function(event) {
		commonJs.toggleCheckboxes("chkSubType");
	});

	$("#orgCategory").change(function(event) {
		initSubTypeTable();
		doSearchMainType();
	});

	$("#btnSaveMainType").click(function() {
		var msg = "";

		msg += "Selected Organisation Category : "+$("#orgCategory").val()+"</br>";
		msg += "Number of Main Type Selected : "+commonJs.getCountChecked("chkMainType")+"</br>";

		commonJs.confirm({
			contents:msg,
			width:300,
			height:150,
			buttons:[{
				caption:"Yes",
				callback:function() {
					alert("Yes");
				}
			}, {
				caption:"No",
				callback:function() {
				}
			}]
		});
	});

	$(document).keypress(function(event) {
		if (event.which == 13) {
			var element = event.target;
		}
	});

	/*!
	 * context menus
	 */

	/*!
	 * process
	 */
	doSearchMainType = function() {
		commonJs.showProcMessageOnElement("tblMainType");

		setTimeout(function() {
			commonJs.ajaxSubmit({
				url:"/sba/0406/getMainType.do",
				dataType:"json",
				formId:"fmDefault",
				success:function(data, textStatus) {
					var result = commonJs.parseAjaxResult(data, textStatus, "json");

					if (result.isSuccess == true || result.isSuccess == "true") {
						renderMainTypeTable(result);
					}
				}
			});
		}, 100);
	};

	renderMainTypeTable = function(result) {
		var ds = result.dataSet;
		var html = "";

		searchResultDataCount = ds.getRowCnt();
		$("#tblMainTypeBody").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var checkString = "";

				if (!commonJs.isEmpty(ds.getValue(i, "ORG_CATEGORY"))) {
					checkString = "checked";
				}
				
				html += "<tr>";
				html += "<td class=\"tdGridCt\"><input type=\"checkbox\" id=\"chkMainType\" name=\"chkMainType\" class=\"chkEn inTblGrid\" "+checkString+" value=\""+ds.getValue(i, "EXPENSE_MAIN_TYPE_CODE")+"\" /></td>";
				html += "<td class=\"tdGrid\"><a onclick=\"getSubType('"+ds.getValue(i, "ORG_CATEGORY")+"', '"+ds.getValue(i, "EXPENSE_MAIN_TYPE_CODE")+"', '"+ds.getValue(i, "EXPENSE_TYPE_NAME")+"')\" class=\"aEn\">"+commonJs.abbreviate(ds.getValue(i, "EXPENSE_TYPE_NAME"), 60)+"</a></td>";
				html += "</tr>";
			}
		} else {
			html += "<tr>";
			html += "<td class=\"tdGridCt\" colspan=\"2\"><tag:msg key="I001"/></td>";
			html += "</tr>";
		}

		$("#tblMainTypeBody").append($(html));

// 		$("#tblMainType").fixedHeaderTable({
// 			baseDivElement:"divMainType",
// 			blockElementId:"tblMainType",
// 			baseWidth:widthMainTypeDiv,
// 			widthAdjust:mainTypeGridWidthAdjust
// 		});

		commonJs.hideProcMessageOnElement("tblMainType");
	};

	getSubType = function(orgCategory, mainTypeCode, mainTypeName) {
		$("#selectedMainTypeCode").val(mainTypeCode);
		$("#selectedMainTypeName").val(mainTypeName);

		commonJs.showProcMessageOnElement("tblSubType");

		setTimeout(function() {
			commonJs.ajaxSubmit({
				url:"/sba/0406/getSubType.do",
				dataType:"json",
				data:{
					orgCategory:orgCategory,
					mainType:mainTypeCode
				},
				success:function(data, textStatus) {
					var result = commonJs.parseAjaxResult(data, textStatus, "json");

					if (result.isSuccess == true || result.isSuccess == "true") {
						renderSubTypeTable(result);
					}
				}
			});
		}, 100);
	};

	renderSubTypeTable = function(result) {
		var ds = result.dataSet;
		var html = "";

		searchResultDataCount = ds.getRowCnt();
		$("#tblSubTypeBody").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var checkString = "";
				var keyValues = ds.getValue(i, "EXPENSE_TYPE_ID")+"_"+ds.getValue(i, "ORG_CATEGORY")+"_"+ds.getValue(i, "EXPENSE_TYPE_CODE");

				if (!commonJs.isEmpty(ds.getValue(i, "EXPENSE_TYPE_ID"))) {
					checkString = "checked";
				}

				html += "<tr>";
				html += "<td class=\"tdGridCt\"><input type=\"checkbox\" id=\"chkSubType\" name=\"chkSubType\" class=\"chkEn inTblGrid\" "+checkString+" value=\""+keyValues+"\" /></td>";
				html += "<td class=\"tdGrid\"><a onclick=\"getDetail('"+ds.getValue(i, "ORG_CATEGORY")+"')\" class=\"aEn\">"+commonJs.abbreviate(ds.getValue(i, "EXPENSE_TYPE_NAME"), 60)+"</a></td>";
				html += "<td class=\"tdGridCt\">"+ds.getValue(i, "IS_APPLY_GST")+"</td>";
				html += "<td class=\"tdGridRt\">"+commonJs.getNumberMask(ds.getValue(i, "GST_PERCENTAGE"), "#,###.##")+"</td>";
				html += "<td class=\"tdGridCt\">"+ds.getValue(i, "ACCOUNT_CODE")+"</td>";
				html += "<td class=\"tdGridCt\">"+ds.getValue(i, "INSERT_DATE")+"</td>";
				html += "<td class=\"tdGridCt\">"+ds.getValue(i, "UPDATE_DATE")+"</td>";
				html += "</tr>";
			}
		} else {
			html += "<tr>";
			html += "<td class=\"tdGridCt\" colspan=\"7\"><tag:msg key="I001"/></td>";
			html += "</tr>";
		}

		$("#tblSubTypeBody").append($(html));

		$("#tblSubType").fixedHeaderTable({
			baseDivElement:"divSubType",
			blockElementId:"tblSubType",
			baseWidth:widthSubTypeDiv,
			widthAdjust:subTypeGridWidthAdjust
		});

		commonJs.hideProcMessageOnElement("tblSubType");
	};

	openPopup = function(param) {
		var url = "", header = "";
		var height = 510;

		if (param.mode == "Detail") {
			url = "/sba/0406/getDetail.do";
			header = "<tag:msg key="sba0406.header.popupHeaderDetail"/>";
		} else if (param.mode == "New" || param.mode == "Reply") {
			url = "/sba/0406/getInsert.do";
			header = "<tag:msg key="sba0406.header.popupHeaderEdit"/>";
		} else if (param.mode == "Edit") {
			url = "/sba/0406/getUpdate.do";
			header = "<tag:msg key="sba0406.header.popupHeaderEdit"/>";
			height = 634;
		}

		var popParam = {
			popupId:"notice"+param.mode,
			url:url,
			paramData:{
				mode:param.mode,
				articleId:commonJs.nvl(param.articleId, "")
			},
			header:header,
			blind:true,
			width:800,
			height:height
		};

		popup = commonJs.openPopup(popParam);
	};

	initSubTypeTable = function() {
		$("#selectedMainTypeCode").val("");
		$("#selectedMainTypeName").val("");
		$("#tblSubTypeBody").html("");
	}
	/*!
	 * load event (document / window)
	 */
	setGridSize = function() {
		$("#divMainType").css("height", ($("#divScrollablePanel").height()-36));
		$("#divSubType").css("height", ($("#divScrollablePanel").height()-36));
		widthMainTypeDiv = $("#divMainType").width();
		widthSubTypeDiv = $("#divSubType").width();
	};

	setGridWidthAdjust = function() {
		if (commonJs.browser.FireFox) {
			mainTypeGridWidthAdjust = -23;
			subTypeGridWidthAdjust = 24;
		} else {
			mainTypeGridWidthAdjust = -27;
			subTypeGridWidthAdjust = 23;
		}
	};

	$(window).load(function() {
		setGridSize();
		setGridWidthAdjust();
		setTimeout(function() {
			doSearchMainType();
		}, 400);
	});
});
</script>
</head>
<%/************************************************************************************************
* Page & Header
************************************************************************************************/%>
<body>
<form id="fmDefault" name="fmDefault" method="post" action="">
<div id="divHeaderHolder" class="ui-layout-north"><%@ include file="/webapp/project/common/include/header.jsp"%></div>
<div id="divBodyHolder" class="ui-layout-center">
<div id="divBodyLeft" class="ui-layout-west"><%@ include file="/webapp/project/common/include/bodyLeft.jsp"%></div>
<div id="divBodyCenter" class="ui-layout-center">
<div id="divFixedPanel">
<div id="divLocationPathArea"><%@ include file="/webapp/project/common/include/bodyLocationPathArea.jsp"%></div>
<%/************************************************************************************************
* Real Contents - fixed panel(tab, button, search, information)
************************************************************************************************/%>
<div id="divTabArea"></div>
<div id="divButtonArea">
	<div id="divButtonAreaLeft"></div>
	<div id="divButtonAreaRight"></div>
</div>
<div id="divSearchCriteriaArea" class="areaContainer">
	<table class="tblSearch">
		<caption><tag:msg key="page.com.searchCriteria"/></caption>
		<tr>
			<td class="tdSearch">
				<label for="orgCategory" class="lblEn hor mandatory"><tag:msg key="sba0406.search.orgCategory"/></label>
				<div style="float:left;padding-right:4px;">
					<tag:select id="orgCategory" name="orgCategory" codeType="ORG_CATEGORY" caption=""/>
				</div>
			</td>
		</tr>
	</table>
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
	<div id="divMainType" style="float:left;width:27%;">
		<table class="tblDefault">
			<tr><td class="tdDefaultRt"><tag:button id="btnSaveMainType" caption="sba0406.button.saveMainType" iconClass="fa-save"/></td></tr>
		</table>
		<div class="verGap2"></div>
		<table id="tblMainType" class="tblGrid sort autosort">
			<colgroup>
				<col width="10%"/>
				<col width="*"/>
			</colgroup>
			<thead>
				<tr>
					<th class="thGrid"><i id="icnCheckMainType" class="fa fa-check-square-o fa-lg icnEn"></i></th>
					<th class="thGrid sortable:alphanumeric"><tag:msg key="sba0406.grid.mainTypeName"/></th>
				</tr>
			</thead>
			<tbody id="tblMainTypeBody">
				<tr>
					<td class="tdGridCt" colspan="2"><tag:msg key="I002"/></td>
				</tr>
			</tbody>
		</table>
		<div id="divMainTypePagingArea"></div>
	</div>
	<div id="divSubType" style="float:right;width:72%;">
		<table class="tblDefault">
			<colgroup>
				<col width="11%"/>
				<col width="39%"/>
				<col width="50%"/>
			</colgroup>
			<tr>
				<th class="thDefaultLt">Selected Main Type : </th>
				<td class="tdDefaultLt">
					<input type="hidden" id="selectedMainTypeCode" name="selectedMainTypeCode" class="txtDpl"/>
					<input type="text" id="selectedMainTypeName" name="selectedMainTypeName" class="txtDpl" style="font-weight:bold;color:Red;" disabled/>
				</td>
				<td class="tdDefaultRt"><tag:button id="btnSaveSubType" caption="sba0406.button.saveSubType" iconClass="fa-save"/></td>
			</tr>
		</table>
		<div class="verGap2"></div>
		<table id="tblSubType" class="tblGrid sort autosort">
			<colgroup>
				<col width="4%"/>
				<col width="*"/>
				<col width="15%"/>
				<col width="15%"/>
				<col width="15%"/>
				<col width="10%"/>
				<col width="10%"/>
			</colgroup>
			<thead>
				<tr>
					<th class="thGrid"><i id="icnCheckSubType" class="fa fa-check-square-o fa-lg icnEn"></i></th>
					<th class="thGrid sortable:alphanumeric"><tag:msg key="sba0406.grid.subTypeName"/></th>
				<th class="thGrid"><tag:msg key="sba0406.grid.isApplyGst"/></th>
				<th class="thGrid"><tag:msg key="sba0406.grid.gstPercentage"/></th>
				<th class="thGrid"><tag:msg key="sba0406.grid.accountCode"/></th>
				<th class="thGrid"><tag:msg key="sba0406.grid.insertDate"/></th>
				<th class="thGrid"><tag:msg key="sba0406.grid.updateDate"/></th>
				</tr>
			</thead>
			<tbody id="tblSubTypeBody">
				<tr>
					<td class="tdGridCt" colspan="7"><tag:msg key="sba0406.msg.selectMainType"/></td>
				</tr>
			</tbody>
		</table>
		<div id="divSubTypePagingArea"></div>
	</div>
</div>
<%/************************************************************************************************
* Right & Footer
************************************************************************************************/%>
</div>
</div>
<div id="divBodyRight" class="ui-layout-east"><%@ include file="/webapp/project/common/include/bodyRight.jsp"%></div>
</div>
<div id="divFooterHolder" class="ui-layout-south"><%@ include file="/webapp/project/common/include/footer.jsp"%></div>
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