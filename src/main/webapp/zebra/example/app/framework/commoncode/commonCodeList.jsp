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
	DataSet resultDataSet = (DataSet)pe.getObject("resultDataSet");
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
var popup = null;
var searchResultDataCount = 0;
var dateFormat = jsconfig.get("dateFormatJs");
var langCode = jsconfig.get("langCode").toUpperCase();

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

	$(document).keypress(function(event) {
		if (event.which == 13) {
			var element = event.target;

			if ($(element).is("[name=searchWord]")) {
				doSearch();
			}
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
			classPrefix:com.constants.ctxClassPrefixButton,
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
		commonJs.showProcMessageOnElement("divScrollablePanel");

		if (commonJs.doValidate($("#fmDefault"))) {
			setTimeout(function() {
				commonJs.ajaxSubmit({
					formId:"fmDefault",
					url:"/zebra/framework/commoncode/getList.do",
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
				var defaultYn = dataSet.getValue(i, "DEFAULT_YN");
				var className = "chkEn", disabledStr = "";
				var uiGridTr = new UiGridTr();

				if ("Y" == defaultYn) {
					className = "chkDis";
					disabledStr = "disabled";
				}

				var uiChk = new UiCheckbox();
				uiChk.setId("chkForDel").setName("chkForDel").removeClassName("chkEn").addClassName(className).setValue(dataSet.getValue(i, "CODE_TYPE")).setOptions(disabledStr);
				uiGridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiChk));

				var uiAnc = new UiAnchor();
				uiAnc.setText(dataSet.getValue(i, "CODE_TYPE")).setScript("getDetail('"+dataSet.getValue(i, "CODE_TYPE")+"')");
				uiGridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAnc));

				uiGridTr.addChild(new UiGridTd().addClassName("Lt").setText(dataSet.getValue(i, "DESCRIPTION_"+langCode)));
				uiGridTr.addChild(new UiGridTd().addClassName("Lt").setText(dataSet.getValue(i, "PROGRAM_CONSTANTS")));
				uiGridTr.addChild(new UiGridTd().addClassName("Ct").setText(dataSet.getValue(i, "USE_YN")));
				uiGridTr.addChild(new UiGridTd().addClassName("Ct").setText(defaultYn));
				uiGridTr.addChild(new UiGridTd().addClassName("Ct").setText(commonJs.getDateTimeMask(dataSet.getValue(i, "INSERT_DATE"), dateFormat)));
				uiGridTr.addChild(new UiGridTd().addClassName("Ct").setText(commonJs.getDateTimeMask(dataSet.getValue(i, "UPDATE_DATE"), dateFormat)));

				var uiTd3 = new UiGridTd(), uiIcon = new UiIcon();
				uiIcon.setId("icnAction").setName("icnAction").addClassName("fa-tasks fa-lg").addAttribute("codeType:"+dataSet.getValue(i, "CODE_TYPE"))
					.addAttribute("defaultYn:"+defaultYn).setScript("doAction(this)");
				uiGridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiIcon));

				html += uiGridTr.toHtmlString();
			}
		} else {
			var uiGridTr = new UiGridTr();

			uiGridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:9").setText(com.message.I001));
			html += uiGridTr.toHtmlString();
		}

		$("#tblGridBody").append($(html));

		$("#tblGrid").fixedHeaderTable({
			attachTo:$("#divDataArea"),
			pagingArea:$("#divPagingArea"),
			isPageable:true,
			isFilter:false,
			filterColumn:[1, 2, 3],
			totalResultRows:result.totalResultRows,
			script:"doSearch"
		});

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenu.commonAction);
		});

		commonJs.hideProcMessageOnElement("divScrollablePanel");
	};

	getDetail = function(codeType) {
		openPopup({mode:"Detail", codeType:codeType});
	};

	openPopup = function(param) {
		var url = "", header = "";
		var height = 754;

		if (param.mode == "Detail") {
			url = "/zebra/framework/commoncode/getDetail.do";
			header = "<mc:msg key="fwk.commoncode.header.popupHeaderDetail"/>";
		} else if (param.mode == "New") {
			url = "/zebra/framework/commoncode/getInsert.do";
			header = "<mc:msg key="fwk.commoncode.header.popupHeaderEdit"/>";
		} else if (param.mode == "Edit") {
			url = "/zebra/framework/commoncode/getUpdate.do";
			header = "<mc:msg key="fwk.commoncode.header.popupHeaderEdit"/>";
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
			commonJs.warn(com.message.I902);
			return;
		}

		commonJs.confirm({
			contents:com.message.Q002,
			buttons:[{
				caption:com.caption.yes,
				callback:function() {
					exeDelete();
				}
			}, {
				caption:com.caption.no,
				callback:function() {
				}
			}],
			blind:true
		});
	};

	exeDelete = function() {
		commonJs.ajaxSubmit({
			url:"/zebra/framework/commoncode/exeDelete.do",
			dataType:"json",
			formId:"fmDefault",
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");

				if (result.isSuccess == true || result.isSuccess == "true") {
					commonJs.openDialog({
						type:com.message.I000,
						contents:result.message,
						blind:true,
						buttons:[{
							caption:com.caption.ok,
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
		var defaultYn = $(img).attr("defaultYn");

		$("input:checkbox[name=chkForDel]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == codeType) {
				$(this).prop("checked", true);
			} else {
				$(this).prop("checked", false);
			}
		});

		if (defaultYn == "Y") {
			ctxMenu.commonAction[2].disable = true;
		} else {
			ctxMenu.commonAction[2].disable = false;
		}

		ctxMenu.commonAction[0].fun = function() {getDetail(codeType);};
		ctxMenu.commonAction[1].fun = function() {openPopup({mode:"Edit", codeType:codeType});};
		ctxMenu.commonAction[2].fun = function() {doDelete();};

		$(img).contextMenu(ctxMenu.commonAction, {
			classPrefix:com.constants.ctxClassPrefixGrid,
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
			commonJs.warn(com.message.I001);
			return;
		}

		commonJs.confirm({
			contents:com.message.Q003,
			buttons:[{
				caption:com.caption.yes,
				callback:function() {
					popup = commonJs.openPopup({
						popupId:"exportFile",
						url:"/zebra/framework/commoncode/exeExport.do",
						paramData:{
							fileType:menuObject.fileType,
							dataRange:menuObject.dataRange
						},
						header:"exportFile",
						blind:false,
						width:200,
						height:100
					});
					// needs delayed time - sometimes causing the error [getOutputStream() has already been called for this response]
					setTimeout(function() {popup.close();}, 3000);
				}
			}, {
				caption:com.caption.no,
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
		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenu.commonAction);
		});

		commonJs.setAutoComplete($("#commonCodeType"), {
			url:"/zebra/common/autoCompletion/",
			method:"getCommonCodeType",
			label:"description"+langCode,
			value:"code_type",
			select:function(event, ui) {
				doSearch();
			}
		});

		$("#commonCodeType").focus();

		setExportButtonContextMenu();

		doSearch();
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
			<ui:button id="btnNew" caption="button.com.new" iconClass="fa-plus-square"/>
			<ui:button id="btnDelete" caption="button.com.delete" iconClass="fa-trash"/>
			<ui:button id="btnSearch" caption="button.com.search" iconClass="fa-search"/>
			<ui:button id="btnClear" caption="button.com.clear" iconClass="fa-refresh"/>
			<ui:button id="btnExport" caption="button.com.export" iconClass="fa-download"/>
		</ui:buttonGroup>
	</div>
</div>
<div id="divSearchCriteriaArea" class="areaContainer">
	<div class="panel panel-default">
		<div class="panel-body">
			<label for="commonCodeType" class="lblEn hor"><mc:msg key="fwk.commoncode.searchHeader.codeType"/></label>
			<ui:text name="commonCodeType" id="commonCodeType" className="defClass hor" style="width:280px"/>
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
				<th class="thGrid"><ui:icon id="icnCheck" className="fa-check-square-o fa-lg icnEn" title="fwk.commoncode.dataGridHeader.selectToDelete"/></th>
				<th class="thGrid sortable:alphanumeric"><mc:msg key="fwk.commoncode.dataGridHeader.codeType"/></th>
				<th class="thGrid sortable:alphanumeric"><mc:msg key="fwk.commoncode.dataGridHeader.description"/></th>
				<th class="thGrid sortable:alphanumeric"><mc:msg key="fwk.commoncode.dataGridHeader.programConstants"/></th>
				<th class="thGrid sortable:number"><mc:msg key="fwk.commoncode.dataGridHeader.useYn"/></th>
				<th class="thGrid sortable:alphanumeric"><mc:msg key="fwk.commoncode.dataGridHeader.defaultYn"/></th>
				<th class="thGrid sortable:date"><mc:msg key="fwk.commoncode.dataGridHeader.insertDate"/></th>
				<th class="thGrid sortable:date"><mc:msg key="fwk.commoncode.dataGridHeader.updateDate"/></th>
				<th class="thGrid"><mc:msg key="page.com.action"/></th>
			</tr>
		</thead>
		<tbody id="tblGridBody">
			<tr>
				<td class="tdGrid Ct" colspan="9"><mc:msg key="I002"/></td>
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