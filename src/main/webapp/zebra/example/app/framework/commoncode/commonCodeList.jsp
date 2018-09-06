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
	DataSet dsCodeType = (DataSet)pe.getObject("codeTypeDataSet");
	String viewMode = CommonUtil.nvl((String)pe.getObject("viewMode"));
	String langCode = CommonUtil.upperCase((String)session.getAttribute("langCode"));
	String toDateFormat = ConfigUtil.getProperty("format.date.java");
	String msgCode = CommonUtil.isEmpty(viewMode) ? "I001" : "I002";
%>
<%/************************************************************************************************
* HTML
************************************************************************************************/%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><mc:msg key="fwk.main.system.title"/></title>
<%/************************************************************************************************
* Stylesheet & Javascript
************************************************************************************************/%>
<%@ include file="/shared/page/incCssJs.jsp"%>
<style type="text/css">
</style>
<script type="text/javascript">
var popup = null;

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
		commonJs.doSubmit({
			formId:"fmDefault",
			action:"/zebra/framework/commoncode/getList.do"
		});
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
			commonJs.warn("<mc:msg key="I902"/>");
			return;
		}

		commonJs.confirm({
			contents:"<mc:msg key="Q002"/>",
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
			url:"/zebra/framework/commoncode/exeDelete.do",
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
			classPrefix:"actionInGrid",
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0,
			verAdjust:2,
			containment:$("#divScrollablePanel")
		});
	};

	exeExport = function(menuObject) {
		var rowCnt = <%=resultDataSet.getRowCnt()%>;
		$("[name=fileType]").remove();
		$("[name=dataRange]").remove();

		if (rowCnt <= 0) {
			commonJs.warn("<mc:msg key="I001"/>");
			return;
		}

		commonJs.confirm({
			contents:"<mc:msg key="Q003"/>",
			buttons:[{
				caption:"Yes",
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
		$("#tblGrid").fixedHeaderTable({
			baseDivElement:"divScrollablePanel",
			widthAdjust:50
		});

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenu.commonAction);
		});

		commonJs.getBootstrapSelectbox("commonCodeType").focus();
		setExportButtonContextMenu();
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
			<select id="commonCodeType" name="commonCodeType" class="bootstrapSelect default">
				<option value="">==Select==</option>
<%
			for (int i=0; i<dsCodeType.getRowCnt(); i++) {
%>
				<option value="<%=dsCodeType.getValue(i, "CODE_TYPE")%>"><%=dsCodeType.getValue(i, "DESCRIPTION_"+langCode)%></option>
<%
			}
%>
			</select>
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
				<th class="thGrid"><i id="icnCheck" class="fa fa-check-square-o fa-lg icnEn" title="<mc:msg key="fwk.commoncode.dataGridHeader.selectToDelete"/>"></i></th>
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
		<tbody>
<%
		if (resultDataSet.getRowCnt() > 0) {
			for (int i=0; i<resultDataSet.getRowCnt(); i++) {
				String defaultYn = resultDataSet.getValue(i, "DEFAULT_YN");
				String className = "chkEn", disabledStr = "";

				if (CommonUtil.equals(defaultYn, "Y")) {
					className = "chkDis";
					disabledStr = "disabled";
				}
%>
			<tr>
				<td class="tdGridCt">
					<input type="checkbox" id="chkForDel" name="chkForDel" class="<%=className%> inTblGrid" value="<%=resultDataSet.getValue(i, "CODE_TYPE")%>" <%=disabledStr%>/>
				</td>
				<td class="tdGrid">
					<a onclick="getDetail('<%=resultDataSet.getValue(i, "CODE_TYPE")%>')" class="aEn"><%=resultDataSet.getValue(i, "CODE_TYPE")%></a>
				</td>
				<td class="tdGrid"><%=resultDataSet.getValue(i, "DESCRIPTION_"+langCode)%></td>
				<td class="tdGrid"><%=resultDataSet.getValue(i, "PROGRAM_CONSTANTS")%></td>
				<td class="tdGridCt"><%=resultDataSet.getValue(i, "USE_YN")%></td>
				<td class="tdGridCt"><%=defaultYn%></td>
				<td class="tdGridCt"><%=CommonUtil.getDateTimeMask(resultDataSet.getValue(i, "INSERT_DATE"), toDateFormat)%></td>
				<td class="tdGridCt"><%=CommonUtil.getDateTimeMask(resultDataSet.getValue(i, "UPDATE_DATE"), toDateFormat)%></td>
				<td class="tdGridCt">
					<i id="icnAction" name="icnAction" class="fa fa-tasks fa-lg icnEn" codeType="<%=resultDataSet.getValue(i, "CODE_TYPE")%>" defaultYn="<%=defaultYn%>" onclick="doAction(this)"></i>
				</td>
			</tr>
<%
			}
		} else {
%>
			<tr>
				<td class="tdGridCt" colspan="9"><mc:msg key="<%=msgCode%>"/></td>
			</tr>
<%
		}
%>
		</tbody>
	</table>
</div>
<div id="divPagingArea" class="areaContainer"><ui:pagination totalRows="<%=pe.getTotalResultRows()%>" script="doSearch"/></div>
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