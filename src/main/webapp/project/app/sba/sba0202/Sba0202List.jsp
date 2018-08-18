<%/************************************************************************************************
* Description - Sba0202 - Organisation Profile
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
var searchResultDataCount = 0;
var toDateFormat = globalMap.get("dateFormatJs");

$(function() {
	/*!
	 * event
	 */
	$("#btnNew").click(function(event) {
		openPopup({mode:"New"});
	});

	$("#btnDelete").click(function(event) {
		doDelete();
	});

	$("#btnSearch").click(function(event) {
		doSearch();
	});

	$("#btnClear").click(function(event) {
		commonJs.clearSearchCriteria();
	});

	$("#icnCheck").click(function(event) {
		commonJs.toggleCheckboxes("chkForDel");
	});

	$("#entityType").change(function() {
		doSearch();
	});

	$("#businessType").change(function() {
		doSearch();
	});

	$("#orgCategory").change(function() {
		doSearch();
	});

	$("#wageType").change(function() {
		doSearch();
	});

	$(document).keypress(function(event) {
		if (event.which == 13) {
			var element = event.target;
		}
	});

	/*!
	 * context menus
	 */
	setExportButtonContextMenu = function() {
		ctxMenu.commonExport[0].fun = function() {exeExport(ctxMenu.commonExport[0]);};
		ctxMenu.commonExport[1].fun = function() {exeExport(ctxMenu.commonExport[1]);};
		ctxMenu.commonExport[2].fun = function() {exeExport(ctxMenu.commonExport[2]);};
		ctxMenu.commonExport[3].fun = function() {exeExport(ctxMenu.commonExport[3]);};
		ctxMenu.commonExport[4].fun = function() {exeExport(ctxMenu.commonExport[4]);};
		ctxMenu.commonExport[5].fun = function() {exeExport(ctxMenu.commonExport[5]);};

		$("#btnExport").contextMenu(ctxMenu.commonExport, {
			classPrefix:"actionButton",
			effectDuration:300,
			effect:"slide",
			borderRadius:"bottom 4px",
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0
		});
	};

	/*!
	 * process
	 */
	doSearch = function() {
		commonJs.showProcMessageOnElement("tblGrid");

		setTimeout(function() {
			commonJs.ajaxSubmit({
				url:"/sba/0202/getList.do",
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
	};

	renderDataGridTable = function(result) {
		var ds = result.dataSet;
		var html = "";

		searchResultDataCount = ds.getRowCnt();
		$("#tblGridBody").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				html += "<tr>";
				html += "<td class=\"tdGridCt\"><input type=\"checkbox\" id=\"chkForDel\" name=\"chkForDel\" class=\"chkEn inTblGrid\" value=\""+ds.getValue(i, "ORG_ID")+"\"/></td>";
				html += "<td class=\"tdGrid\"><a onclick=\"getDetail('"+ds.getValue(i, "ORG_ID")+"')\" class=\"aEn\">"+commonJs.abbreviate(ds.getValue(i, "LEGAL_NAME"), 60)+"</a></td>";
				html += "<td class=\"tdGrid\">"+commonJs.abbreviate(ds.getValue(i, "TRADING_NAME"), 50)+"</td>";
				html += "<td class=\"tdGridCt\">"+commonJs.getFormatString(ds.getValue(i, "ABN"), "?? ??? ??? ???")+"</td>";
				html += "<td class=\"tdGridCt\">"+ds.getValue(i, "USER_CNT")+"</td>";
				html += "<td class=\"tdGrid\">"+ds.getValue(i, "ENTITY_TYPE_NAME")+"</td>";
				html += "<td class=\"tdGrid\">"+ds.getValue(i, "BUSINESS_TYPE_NAME")+"</td>";
				html += "<td class=\"tdGridCt\">"+ds.getValue(i, "ORG_CATEGORY_NAME")+"</td>";
				html += "<td class=\"tdGrid\">"+ds.getValue(i, "WAGE_TYPE_NAME")+"</td>";
				html += "<td class=\"tdGridRt\">"+commonJs.getNumberMask(ds.getValue(i, "REVENUE_RANGE_FROM"), "#,###")+"</td>";
				html += "<td class=\"tdGridRt\">"+commonJs.getNumberMask(ds.getValue(i, "REVENUE_RANGE_TO"), "#,###")+"</td>";
				html += "<td class=\"tdGridCt\">"+ds.getValue(i, "REGISTERED_DATE")+"</td>";
				html += "<td class=\"tdGridCt\"><i id=\"icnAction\" name=\"icnAction\" class=\"fa fa-tasks fa-lg icnEn\" orgId=\""+ds.getValue(i, "ORG_ID")+"\" onclick=\"doAction(this)\"></i></td>";
				html += "</tr>";
			}
		} else {
			html += "<tr>";
			html += "<td class=\"tdGridCt\" colspan=\"13\"><tag:msg key="I001"/></td>";
			html += "</tr>";
		}

		$("#tblGridBody").append($(html));
		if (commonJs.browser.FireFox) {
			gridWidthAdjust = 76;
			baseHeight = $("#divScrollablePanel").height() - 10;
		} else {
			gridWidthAdjust = 82;
			baseHeight = $("#divScrollablePanel").height() - 10;
		}

		$("#tblGrid").fixedHeaderTable({
			baseDivElement:"divScrollablePanel",
			attachedPagingArea:true,
			blockElementId:"tblGrid",
			pagingAreaId:"divPagingArea",
			totalResultRows:result.totalResultRows,
			script:"doSearch",
			baseHeight:baseHeight,
			widthAdjust:gridWidthAdjust
		});

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenu.boardAction);
		});

		commonJs.hideProcMessageOnElement("tblGrid");
	};

	getDetail = function(orgId) {
		openPopup({mode:"Detail", orgId:orgId});
	};

	openPopup = function(param) {
		var url = "", header = "";
		var height = 510;

		if (param.mode == "Detail") {
			url = "/sba/0202/getDetail.do";
			header = "<tag:msg key="sba0202.header.popDetail"/>";
		} else if (param.mode == "New") {
			url = "/sba/0202/getInsert.do";
			header = "<tag:msg key="sba0202.header.popNew"/>";
		} else if (param.mode == "Edit") {
			url = "/sba/0202/getUpdate.do";
			header = "<tag:msg key="sba0202.header.popEdit"/>";
			height = 634;
		}

		var popParam = {
			popupId:param.mode,
			url:url,
			paramData:{
				mode:param.mode,
				orgId:orgId
			},
			header:header,
			blind:true,
			width:800,
			height:height
		};

		popup = commonJs.openPopup(popParam);
	};

	doDelete = function() {
		if (commonJs.getCountChecked("chkForDel") == 0) {
			commonJs.warn("<tag:msg key="I902"/>");
			return;
		}

		commonJs.confirm({
			contents:"<tag:msg key="Q002"/>",
			buttons:[{
				caption:"Yes",
				callback:function() {
					commonJs.ajaxSubmit({
						url:"/sba/0202/exeDelete.do",
						dataType:"json",
						formId:"fmDefault",
						success:function(data, textStatus) {
							var result = commonJs.parseAjaxResult(data, textStatus, "json");

							if (result.isSuccess == true || result.isSuccess == "true") {
								commonJs.openDialog({
									type:"information",
									contents:result.message,
									blind:true,
									buttons:[{
										caption:"Ok",
										callback:function() {
											doSearch();
										}
									}]
								});
							} else {
								commonJs.error(result.message);
							}
						}
					});
				}
			}, {
				caption:"No",
				callback:function() {
				}
			}],
			blind:true
		});
	};

	doAction = function(img) {
		var orgId = $(img).attr("orgId");

		$("input:checkbox[name=chkForDel]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == orgId) {
				$(this).prop("checked", true);
			} else {
				$(this).prop("checked", false);
			}
		});

		ctxMenu.commonAction[0].fun = function() {getDetail(orgId);};
		ctxMenu.commonAction[1].fun = function() {openPopup({mode:"Edit", orgId:orgId});};
		ctxMenu.commonAction[2].fun = function() {doDelete();};

		$(img).contextMenu(ctxMenu.commonAction, {
			classPrefix:"actionInGrid",
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0,
			verAdjust:2
		});
	};

	exeExport = function(menuObject) {
		$("[name=fileType]").remove();
		$("[name=dataRange]").remove();

		if (searchResultDataCount <= 0) {
			commonJs.warn("<tag:msg key="I001"/>");
			return;
		}

		commonJs.confirm({
			contents:"<tag:msg key="Q003"/>",
			buttons:[{
				caption:"Yes",
				callback:function() {
					popup = commonJs.openPopup({
						popupId:"exportFile",
						url:"/sba/0202/exeExport.do",
						paramData:{
							fileType:menuObject.fileType,
							dataRange:menuObject.dataRange
						},
						header:"exportFile",
						blind:false,
						width:200,
						height:100
					});
					setTimeout(function() {popup.close();}, 3000);
				}
			}, {
				caption:"No",
				callback:function() {
				}
			}],
			blind:true
		});
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		setExportButtonContextMenu();

		$("#orgName").focus();

		commonJs.setAutoComplete($("#orgName"), {
			method:"getOrgName",
			label:"org_name",
			value:"org_name",
			focus: function(event, ui) {
				$("#orgName").val(ui.item.label);
				return false;
			},
			select:function(event, ui) {
				doSearch();
			}
		});

		commonJs.setAutoComplete($("#abn"), {
			method:"getAbn",
			label:"abn",
			value:"abn",
			focus: function(event, ui) {
				$("#abn").val(ui.item.label);
				return false;
			},
			select:function(event, ui) {
				doSearch();
			}
		});

		setTimeout(function() {
			doSearch();
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
<div id="divButtonArea" class="areaContainer">
	<div id="divButtonAreaLeft"></div>
	<div id="divButtonAreaRight">
		<tag:buttonGroup id="buttonGroup">
			<tag:button id="btnNew" caption="button.com.new" iconClass="fa-plus-square"/>
			<tag:button id="btnDelete" caption="button.com.delete" iconClass="fa-trash"/>
			<tag:button id="btnSearch" caption="button.com.search" iconClass="fa-search"/>
			<tag:button id="btnClear" caption="button.com.clear" iconClass="fa-refresh"/>
			<tag:button id="btnExport" caption="button.com.export" iconClass="fa-download"/>
		</tag:buttonGroup>
	</div>
</div>
<div id="divSearchCriteriaArea" class="areaContainer">
	<table class="tblSearch">
		<caption><tag:msg key="page.com.searchCriteria"/></caption>
		<colgroup>
			<col width="10%"/>
			<col width="23%"/>
			<col width="10%"/>
			<col width="23%"/>
			<col width="10%"/>
			<col width="24%"/>
		</colgroup>
		<tr>
			<th class="thSearch"><tag:msg key="sba0202.search.orgName"/></th>
			<td class="tdSearch"><input type="text" id="orgName" name="orgName" class="txtEn"/></td>
			<th class="thSearch"><tag:msg key="sba0202.search.abn"/></th>
			<td class="tdSearch"><input type="text" id="abn" name="abn" class="txtEn"/></td>
			<th class="thSearch"><tag:msg key="sba0202.search.entityType"/></th>
			<td class="tdSearch"><tag:select id="entityType" name="entityType" codeType="ENTITY_TYPE" caption="==Select==" className="default"/></td>
		</tr>
		<tr>
			<th class="thSearch"><tag:msg key="sba0202.search.bizType"/></th>
			<td class="tdSearch"><tag:select id="businessType" name="businessType" codeType="BUSINESS_TYPE" caption="==Select==" className="default"/></td>
			<th class="thSearch"><tag:msg key="sba0202.search.orgCategory"/></th>
			<td class="tdSearch"><tag:select id="orgCategory" name="orgCategory" codeType="ORG_CATEGORY" caption="==Select==" className="default"/></td>
			<th class="thSearch"><tag:msg key="sba0202.search.wageType"/></th>
			<td class="tdSearch"><tag:select id="wageType" name="wageType" codeType="WAGE_TYPE" caption="==Select==" className="default"/></td>
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
	<table id="tblGrid" class="tblGrid sort autosort">
		<colgroup>
			<col width="2%"/>
			<col width="*"/>
			<col width="12%"/>
			<col width="7%"/>
			<col width="3%"/>
			<col width="9%"/>
			<col width="9%"/>
			<col width="7%"/>
			<col width="7%"/>
			<col width="7%"/>
			<col width="7%"/>
			<col width="7%"/>
			<col width="3%"/>
		</colgroup>
		<thead>
			<tr>
				<th class="thGrid"><i id="icnCheck" class="fa fa-check-square-o fa-lg icnEn" title="<tag:msg key="page.com.selectToDelete"/>"></i></th>
				<th class="thGrid sortable:alphanumeric"><tag:msg key="sba0202.grid.legalName"/></th>
				<th class="thGrid sortable:alphanumeric"><tag:msg key="sba0202.grid.tradingName"/></th>
				<th class="thGrid"><tag:msg key="sba0202.grid.abn"/></th>
				<th class="thGrid"><tag:msg key="sba0202.grid.users"/></th>
				<th class="thGrid sortable:alphanumeric"><tag:msg key="sba0202.grid.entityType"/></th>
				<th class="thGrid sortable:alphanumeric"><tag:msg key="sba0202.grid.bizType"/></th>
				<th class="thGrid sortable:alphanumeric"><tag:msg key="sba0202.grid.orgCategory"/></th>
				<th class="thGrid sortable:alphanumeric"><tag:msg key="sba0202.grid.wageType"/></th>
				<th class="thGrid"><tag:msg key="sba0202.grid.revRangeFrom"/></th>
				<th class="thGrid"><tag:msg key="sba0202.grid.revRangeTo"/></th>
				<th class="thGrid sortable:date"><tag:msg key="sba0202.grid.regDate"/></th>
				<th class="thGrid"><tag:msg key="page.com.action"/></th>
			</tr>
		</thead>
		<tbody id="tblGridBody">
			<tr>
				<td class="tdGridCt" colspan="13"><tag:msg key="I002"/></td>
			</tr>
		</tbody>
	</table>
	<div id="divPagingArea" class="areaContainer"></div>
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