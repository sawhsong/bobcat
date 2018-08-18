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
	String langCode = CommonUtil.upperCase((String)session.getAttribute("langCode"));
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
var langCode = commonJs.upperCase(globalMap.get("langCode"));
var toDateTimeFormat = globalMap.get("dateFormatJs");

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

	$("#codeCategory").change(function(event) {
		doSearch();
	});

	$("#btnClear").click(function(event) {
		commonJs.clearSearchCriteria();
	});

	$("#icnCheck").click(function(event) {
		commonJs.toggleCheckboxes("chkForDel");
	});

	$("#commonCodeType").change(function(event) {
		doSearch();
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
		commonJs.ajaxSubmit({
			url:"/sys/0202/getList.do",
			dataType:"json",
			formId:"fmDefault",
			blockElementId:"tblGrid",
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");

				if (result.isSuccess == true || result.isSuccess == "true") {
					setTimeout(function() {
						renderDataGridTable(result);
					}, 100);
				}
			}
		});
	};

	renderDataGridTable = function(result) {
		var dataSet = result.dataSet;
		var html = "";

		searchResultDataCount = dataSet.getRowCnt();
		$("#gridBody").html("");

		if (dataSet.getRowCnt() > 0) {
			for (var i=0; i<dataSet.getRowCnt(); i++) {
				var isDefault = dataSet.getValue(i, "IS_DEFAULT");
				var className = "chkEn", disabledStr = "";

				if (isDefault == "Y") {
					className = "chkDis";
					disabledStr = "disabled";
				}

				html += "<tr>";
				html += "<td class=\"tdGridCt\"><input type=\"checkbox\" id=\"chkForDel\" name=\"chkForDel\" class=\""+className+" inTblGrid\" value=\""+dataSet.getValue(i, "CODE_TYPE")+"\" "+disabledStr+"/></td>";
				html += "<td class=\"tdGrid\"><a onclick=\"getDetail('"+dataSet.getValue(i, "CODE_TYPE")+"')\" class=\"aEn\">"+dataSet.getValue(i, "CODE_TYPE")+"</a></td>";
				html += "<td class=\"tdGrid\">"+dataSet.getValue(i, "DESCRIPTION_"+langCode)+"</td>";
				html += "<td class=\"tdGrid\">"+dataSet.getValue(i, "PROGRAM_CONSTANTS")+"</td>";
				html += "<td class=\"tdGridCt\">"+dataSet.getValue(i, "IS_ACTIVE")+"</td>";
				html += "<td class=\"tdGridCt\">"+isDefault+"</td>";
				html += "<td class=\"tdGridCt\">"+commonJs.getDateTimeMask(dataSet.getValue(i, "INSERT_DATE"), toDateTimeFormat)+"</td>";
				html += "<td class=\"tdGridCt\">"+commonJs.getDateTimeMask(dataSet.getValue(i, "UPDATE_DATE"), toDateTimeFormat)+"</td>";
				html += "<td class=\"tdGridCt\"><i id=\"icnAction\" name=\"icnAction\" class=\"fa fa-tasks fa-lg icnEn\" codeType=\""+dataSet.getValue(i, "CODE_TYPE")+"\" isDefault=\""+isDefault+"\" onclick=\"doAction(this)\"></i></td>";
				html += "</tr>";
			}
		} else {
			html += "<tr>";
			html += "<td class=\"tdGridCt\" colspan=\"9\"><tag:msg key="I001"/></td>";
			html += "</tr>";
		}

		$("#gridBody").append($(html));
		if (commonJs.browser.FireFox) {
			gridWidthAdjust = 40;
		} else {
			gridWidthAdjust = 42;
		}

		$("#tblGrid").fixedHeaderTable({
			baseDivElement:"divScrollablePanel",
			attachedPagingArea:true,
			blockElementId:"tblGrid",
			pagingAreaId:"divPagingArea",
			totalResultRows:result.totalResultRows,
			script:"doSearch",
			widthAdjust:gridWidthAdjust
		});

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenu.boardAction);
		});

		commonJs.hideProcMessageOnElement("tblGrid");
	};

	getDetail = function(codeType) {
		openPopup({mode:"Detail", codeType:codeType});
	};

	openPopup = function(param) {
		var url = "", header = "";
		var height = 754;

		if (param.mode == "Detail") {
			url = "/sys/0202/getDetail.do";
			header = "<tag:msg key="sys0202.header.popupHeaderDetail"/>";
		} else if (param.mode == "New") {
			url = "/sys/0202/getInsert.do";
			header = "<tag:msg key="sys0202.header.popupHeaderEdit"/>";
		} else if (param.mode == "Edit") {
			url = "/sys/0202/getUpdate.do";
			header = "<tag:msg key="sys0202.header.popupHeaderEdit"/>";
			height = 754;
		}

		var popParam = {
			popupId:"commonCode"+param.mode,
			url:url,
			paramData:{
				mode:param.mode,
				codeType:commonJs.nvl(param.codeType, "")
			},
			header:header,
			blind:true,
			width:1000,
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
					exeDelete();
				}
			}, {
				caption:"No",
				callback:function() {
				}
			}],
			blind:true
		});
	};

	exeDelete = function() {
		commonJs.ajaxSubmit({
			url:"/sys/0202/exeDelete.do",
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
	};

	doAction = function(img) {
		var codeType = $(img).attr("codeType");
		var isDefault = $(img).attr("isDefault");

		$("input:checkbox[name=chkForDel]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == codeType) {
				$(this).prop("checked", true);
			} else {
				$(this).prop("checked", false);
			}
		});

		if (isDefault == "Y") {
			ctxMenu.commonAction[2].disable = true;
		} else {
			ctxMenu.commonAction[2].disable = false;
		}

		ctxMenu.commonAction[0].fun = function() {getDetail(codeType);};
		ctxMenu.commonAction[1].fun = function() {openPopup({mode:"Edit", codeType:codeType});};
		ctxMenu.commonAction[2].fun = function() {doDelete();};

		$(img).contextMenu(ctxMenu.commonAction, {
			classPrefix:"actionInGrid",
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0,
			verAdjust:2,
			containment:$("#divScrollablePanel")
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
						url:"/sys/0202/exeExport.do",
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
		commonJs.getBootstrapSelectbox("codeCategory").focus();
		setExportButtonContextMenu();
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
		<tr>
			<td class="tdSearch">
				<label for="codeCategory" class="lblEn hor"><tag:msg key="sys0202.searchHeader.codeCategory"/></label>
				<tag:select id="codeCategory" name="codeCategory" codeType="CODE_CATEGORY" caption="==Select==" className="default" selectedValue="BIZ"/>
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
	<table id="tblGrid" class="tblGrid sort autosort">
		<colgroup>
			<col width="3%"/>
			<col width="15%"/>
			<col width="*"/>
			<col width="18%"/>
			<col width="7%"/>
			<col width="7%"/>
			<col width="9%"/>
			<col width="9%"/>
			<col width="4%"/>
		</colgroup>
		<thead>
			<tr>
				<th class="thGrid"><i id="icnCheck" class="fa fa-check-square-o fa-lg icnEn" title="<tag:msg key="sys0202.dataGridHeader.selectToDelete"/>"></i></th>
				<th class="thGrid sortable:alphanumeric"><tag:msg key="sys0202.dataGridHeader.codeType"/></th>
				<th class="thGrid sortable:alphanumeric"><tag:msg key="sys0202.dataGridHeader.description"/></th>
				<th class="thGrid sortable:alphanumeric"><tag:msg key="sys0202.dataGridHeader.programConstants"/></th>
				<th class="thGrid sortable:number"><tag:msg key="sys0202.dataGridHeader.isActive"/></th>
				<th class="thGrid sortable:alphanumeric"><tag:msg key="sys0202.dataGridHeader.isDefault"/></th>
				<th class="thGrid sortable:date"><tag:msg key="sys0202.dataGridHeader.insertDate"/></th>
				<th class="thGrid sortable:date"><tag:msg key="sys0202.dataGridHeader.updateDate"/></th>
				<th class="thGrid"><tag:msg key="page.com.action"/></th>
			</tr>
		</thead>
		<tbody id="gridBody">
			<tr>
				<td class="tdGridCt" colspan="9"><tag:msg key="I002"/></td>
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