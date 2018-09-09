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
	DataSet resultDataSet = (DataSet)paramEntity.getObject("resultDataSet");
	String viewMode = CommonUtil.nvl((String)paramEntity.getObject("viewMode"));
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
var popupNotice = null;
var attchedFileContextMenu = [];

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

	$("#icnFromDate").click(function(event) {
		commonJs.openCalendar(event, "fromDate");
	});

	$("#icnToDate").click(function(event) {
		commonJs.openCalendar(event, "toDate");
	});

	$("#icnCheck").click(function(event) {
		commonJs.toggleCheckboxes("chkForDel");
	});

	$(document).keypress(function(event) {
		if (event.which == 13) {
			var element = event.target;

			if ($(element).is("[name=searchWord]") || $(element).is("[name=fromDate]") || $(element).is("[name=toDate]")) {
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
			action:"/zebra/sample/multidatasource/getList.do"
		});
	};

	getDetail = function(articleId) {
		openPopup({mode:"Detail", articleId:articleId});
	};

	openPopup = function(param) {
		var url = "", header = "";
		var height = 510;

		if (param.mode == "Detail") {
			url = "/zebra/sample/multidatasource/getDetail.do";
			header = "<mc:msg key="fwk.notice.title.popupTitleDetail"/>";
		} else if (param.mode == "New" || param.mode == "Reply") {
			url = "/zebra/sample/multidatasource/getInsert.do";
			header = "<mc:msg key="fwk.notice.title.popupTitleEdit"/>";
		} else if (param.mode == "Edit") {
			url = "/zebra/sample/multidatasource/getUpdate.do";
			header = "<mc:msg key="fwk.notice.title.popupTitleEdit"/>";
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

		popupNotice = commonJs.openPopup(popParam);
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
					commonJs.doSubmit({
						form:"fmDefault",
						action:"/zebra/sample/multidatasource/exeDelete.do"
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
		var articleId = $(img).attr("articleId");

		$("input:checkbox[name=chkForDel]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == articleId) {
				$(this).prop("checked", true);
			} else {
				$(this).prop("checked", false);
			}
		});

		ctxMenu.boardAction[0].fun = function() {getDetail(articleId);};
		ctxMenu.boardAction[1].fun = function() {openPopup({mode:"Edit", articleId:articleId});};
		ctxMenu.boardAction[2].fun = function() {openPopup({mode:"Reply", articleId:articleId});};
		ctxMenu.boardAction[3].fun = function() {doDelete();};

		$(img).contextMenu(ctxMenu.boardAction, {
			classPrefix:"actionInGrid",
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0,
			verAdjust:2,
			containment:$("#divScrollablePanel")
		});
	};

	getAttachedFile = function(img) {
		commonJs.ajaxSubmit({
			url:"/zebra/sample/multidatasource/getAttachedFile.do",
			dataType:"json",
			data:{
				articleId:$(img).attr("articleId")
			},
			blind:false,
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");

				if (result.isSuccess == true || result.isSuccess == "true") {
					var dataSet = result.dataSet;
					attchedFileContextMenu = [];

					for (var i=0; i<dataSet.getRowCnt(); i++) {
						var repositoryPath = dataSet.getValue(i, "REPOSITORY_PATH");
						var originalName = dataSet.getValue(i, "ORIGINAL_NAME");
						var newName = dataSet.getValue(i, "NEW_NAME");
						var fileIcon = dataSet.getValue(i, "FILE_ICON");
						var fileSize = dataSet.getValue(i, "FILE_SIZE")/1024;

						attchedFileContextMenu.push({
							name:originalName+" ("+commonJs.getNumberMask(fileSize, "0,0")+") KB",
							title:originalName,
							img:fileIcon,
							repositoryPath:repositoryPath,
							originalName:originalName,
							newName:newName,
							fun:function() {
								var index = $(this).index();

								downloadFile({
									repositoryPath:attchedFileContextMenu[index].repositoryPath,
									originalName:attchedFileContextMenu[index].originalName,
									newName:attchedFileContextMenu[index].newName
								});
							}
						});
					}

					$(img).contextMenu(attchedFileContextMenu, {
						classPrefix:"actionInGrid",
						displayAround:"trigger",
						position:"bottom",
						horAdjust:0,
						verAdjust:2,
						containment:$("#divScrollablePanel")
					});
				}
			}
		});
	};

	downloadFile = function(param) {
		commonJs.submit({
			form:"fmDefault",
			action:"/download.do",
			data:{
				repositoryPath:param.repositoryPath,
				originalName:param.originalName,
				newName:param.newName
			}
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
					popupNotice = commonJs.openPopup({
						popupId:"exportFile",
						url:"/zebra/sample/multidatasource/exeExport.do",
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
					setTimeout(function() {popupNotice.close();}, 2000);
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
		$("#tblFixedHeaderTable").fixedHeaderTable({
			baseDivElement:"divScrollablePanel",
			widthAdjust:28
		});

		commonJs.setFieldDateMask("fromDate");
		commonJs.setFieldDateMask("toDate");

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenu.boardAction);
		});

		$("[name=icnAttachedFile]").each(function(index) {
			$(this).contextMenu(attchedFileContextMenu);
		});

		$("#searchWord").focus();

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
			<ui:button id="btnNew" caption="button.com.new" iconClass="fa-comment"/>
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
			<table class="tblDefault">
				<colgroup>
					<col width="50%"/>
					<col width="50%"/>
				</colgroup>
				<tr>
					<td class="tdDefault">
						<label for="searchType" class="lblEn hor"><mc:msg key="fwk.notice.searchHeader.searchType"/></label>
						<div style="float:left;padding-right:4px;"><ui:ccselect id="searchType" name="searchType" codeType="BOARD_SEARCH_TYPE" caption="==Select==" className="default" options="checkName='Search Type'" source="framework"/></div>
						<input type="text" id="searchWord" name="searchWord" class="txtEn hor" style="width:280px"/>
					</td>
					<td class="tdDefault">
						<label for="fromDate" class="lblEn hor"><mc:msg key="fwk.notice.searchHeader.searchPeriod"/></label>
						<input type="text" id="fromDate" name="fromDate" class="txtEnCt hor" style="width:100px" checkName="From Date" option="date"/>
						<i id="icnFromDate" class="fa fa-calendar icnEn hor" title="From Date"></i>
						<div class="horGap20" style="padding:6px 8px 6px 0px;">-</div>
						<input type="text" id="toDate" name="toDate" class="txtEnCt hor" style="width:100px" checkName="To Date" option="date"/>
						<i id="icnToDate" class="fa fa-calendar icnEn hor" title="To Date"></i>
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
			<col width="*"/>
			<col width="5%"/>
			<col width="15%"/>
			<col width="10%"/>
			<col width="8%"/>
			<col width="5%"/>
		</colgroup>
		<thead>
			<tr class="noBorderHor">
				<th class="thGrid">
					<i id="icnCheck" class="fa fa-check-square-o fa-lg icnEn" title="<mc:msg key="fwk.notice.title.selectToDelete"/>"></i>
				</th>
				<th class="thGrid sortable:alphanumeric"><mc:msg key="fwk.notice.dataGridHeader.subject"/></th>
				<th class="thGrid"><mc:msg key="fwk.notice.dataGridHeader.file"/></th>
				<th class="thGrid sortable:alphanumeric"><mc:msg key="fwk.notice.dataGridHeader.writer"/></th>
				<th class="thGrid sortable:date"><mc:msg key="fwk.notice.dataGridHeader.createdDate"/></th>
				<th class="thGrid sortable:numeric"><mc:msg key="fwk.notice.dataGridHeader.visitCount"/></th>
				<th class="thGrid"><mc:msg key="page.com.action"/></th>
			</tr>
		</thead>
		<tbody>
<%
		if (resultDataSet.getRowCnt() > 0) {
			for (int i=0; i<resultDataSet.getRowCnt(); i++) {
%>
			<tr class="noBorderHor noStripe">
				<td class="tdGridCt">
					<input type="checkbox" id="chkForDel" name="chkForDel" class="chkEn inTblGrid" value="<%=resultDataSet.getValue(i, "ARTICLE_ID")%>"/>
				</td>
				<td class="tdGrid" title="<%=resultDataSet.getValue(i, "ARTICLE_SUBJECT")%>">
<%
					String space = "";
					int iLevel = CommonUtil.toInt(resultDataSet.getValue(i, "LEVEL")) - 1;
					int iLength = 200;

					if (iLevel > 0) {
						for (int j=0; j<iLevel; j++) {
							space += "&nbsp;&nbsp;&nbsp;&nbsp;";
							iLength = iLength - 2;
						}
						space += "<i class=\"fa fa-comments\"></i>";
					} else {
						space += "<i class=\"fa fa-comment\"></i>";
					}
%>
					<%=space%><a onclick="getDetail('<%=resultDataSet.getValue(i, "ARTICLE_ID")%>')" class="aEn">
						<%=CommonUtil.abbreviate(resultDataSet.getValue(i, "ARTICLE_SUBJECT"), iLength)%>
					</a>
				</td>
				<td class="tdGridCt">
<%
				if (CommonUtil.toInt(resultDataSet.getValue(i, "FILE_CNT")) > 0) {
%>
					<i id="icnAttachedFile" name="icnAttachedFile" class="glyphicon glyphicon-paperclip icnEn" articleId="<%=resultDataSet.getValue(i, "ARTICLE_ID")%>" onclick="getAttachedFile(this)"></i>
<%
				}
%>
				</td>
				<td class="tdGrid"><%=resultDataSet.getValue(i, "WRITER_NAME")%></td>
				<td class="tdGridCt"><%=resultDataSet.getValue(i, "CREATED_DATE")%></td>
				<td class="tdGridRt"><%=CommonUtil.getNumberMask(resultDataSet.getValue(i, "VISIT_CNT"))%></td>
				<td class="tdGridCt">
					<i id="icnAction" name="icnAction" class="fa fa-tasks fa-lg icnEn" articleId="<%=resultDataSet.getValue(i, "ARTICLE_ID")%>" onclick="doAction(this)" title="<mc:msg key="page.com.action"/>"></i>
				</td>
			</tr>
<%
			}
		} else {
%>
			<tr class="noBorderHor noStripe">
				<td class="tdGridCt" colspan="7"><mc:msg key="<%=msgCode%>"/></td>
			</tr>
<%
		}
%>
		</tbody>
	</table>
</div>
<div id="divPagingArea" class="areaContainer"><ui:pagination totalRows="<%=paramEntity.getTotalResultRows()%>" script="doSearch"/></div>
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