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
	DataSet dsRequest = (DataSet)pe.getRequestDataSet();
	ZebraBoard noticeBoard = (ZebraBoard)pe.getObject("noticeBoard");
	DataSet fileDataSet = (DataSet)pe.getObject("fileDataSet");
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
<script type="text/javascript">
$(function() {
	/*!
	 * event
	 */
	$("#btnEdit").click(function(event) {
		doProcessByButton({mode:"Update"});
	});

	$("#btnReply").click(function(event) {
		doProcessByButton({mode:"Reply"});
	});

	$("#btnDelete").click(function(event) {
		doProcessByButton({mode:"Delete"});
	});

	$("#btnClose").click(function(event) {
		parent.popupNotice.close();
	});

	/*!
	 * process
	 */
	exeDownload = function(repositoryPath, originalName, newName) {
		var popup = commonJs.openPopup({
			popupId:"downloadFile",
			url:"/zebra/sample/soapwebservice/exeDownload.do",
			paramData:{
				repositoryPath:repositoryPath,
				originalName:originalName,
				newName:newName
			},
			header:"downloadFile",
			blind:false,
			width:300,
			height:150
		});
		popup.close();
	};

	doProcessByButton = function(param) {
		var articleId = "<%=noticeBoard.getArticleId()%>";
		var actionString = "";
		var params = {};

		if (param.mode == "Update") {
			actionString = "/zebra/sample/soapwebservice/getUpdate.do";
		} else if (param.mode == "Reply") {
			actionString = "/zebra/sample/soapwebservice/getInsert.do";
		} else if (param.mode == "Delete") {
			actionString = "/zebra/sample/soapwebservice/exeDelete.do";
		}

		params = {
			form:"fmDefault",
			action:actionString,
			data:{
				mode:param.mode,
				articleId:articleId
			}
		};

		if (param.mode == "Update") {
			parent.popupNotice.resizeTo(0, 124);
		}

		if (param.mode == "Delete") {
			commonJs.confirm({
				contents:"<mc:msg key="Q002"/>",
				buttons:[{
					caption:"Yes",
					callback:function() {
						commonJs.doSubmit(params);
					}
				}, {
					caption:"No",
					callback:function() {
					}
				}]
			});
		} else {
			commonJs.doSubmit(params);
		}
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
	});
});
</script>
</head>
<%/************************************************************************************************
* Page & Header
************************************************************************************************/%>
<body>
<form id="fmDefault" name="fmDefault" method="post" action="">
<div id="divPopupWindowHolder">
<div id="divFixedPanelPopup">
<div id="divLocationPathArea"><%@ include file="/zebra/example/common/include/bodyLocationPathArea.jsp"%></div>
<%/************************************************************************************************
* Real Contents - fixed panel(tab, button, search, information)
************************************************************************************************/%>
<div id="divTabArea"></div>
<div id="divButtonArea" class="areaContainerPopup">
	<div id="divButtonAreaLeft"></div>
	<div id="divButtonAreaRight">
		<ui:buttonGroup id="buttonGroup">
			<ui:button id="btnEdit" caption="button.com.edit" iconClass="fa-edit"/>
			<ui:button id="btnReply" caption="button.com.reply" iconClass="fa-reply-all"/>
			<ui:button id="btnDelete" caption="button.com.delete" iconClass="fa-save"/>
			<ui:button id="btnClose" caption="button.com.close" iconClass="fa-times"/>
		</ui:buttonGroup>
	</div>
</div>
<div id="divSearchCriteriaArea"></div>
<div id="divInformArea"></div>
<%/************************************************************************************************
* End of fixed panel
************************************************************************************************/%>
<div class="breaker" style="height:1px;"></div>
</div>
<div id="divScrollablePanelPopup">
<%/************************************************************************************************
* Real Contents - scrollable panel(data, paging)
************************************************************************************************/%>
<div id="divDataArea" class="areaContainerPopup">
	<table class="tblEdit">
		<colgroup>
			<col width="15%"/>
			<col width="35%"/>
			<col width="15%"/>
			<col width="35%"/>
		</colgroup>
		<tr>
			<th class="thEditRt"><mc:msg key="fwk.notice.header.writerName"/></th>
			<td class="tdEdit"><%=noticeBoard.getWriterName()%>(<%=noticeBoard.getWriterId()%>)</td>
			<th class="thEditRt"><mc:msg key="fwk.notice.header.writerEmail"/></th>
			<td class="tdEdit"><%=noticeBoard.getWriterEmail()%></td>
		</tr>
		<tr>
			<th class="thEditRt"><mc:msg key="fwk.notice.header.updateDate"/></th>
			<td class="tdEdit"><%=CommonUtil.toViewDateString(noticeBoard.getUpdateDate())%></td>
			<th class="thEditRt"><mc:msg key="fwk.notice.header.visitCount"/></th>
			<td class="tdEdit"><%=CommonUtil.getNumberMask(noticeBoard.getVisitCnt())%></td>
		</tr>
		<tr>
			<th class="thEditRt"><mc:msg key="fwk.notice.header.articleSubject"/></th>
			<td class="tdEdit" colspan="3"><%=noticeBoard.getArticleSubject()%></td>
		</tr>
		<tr>
			<th class="thEditRt"><mc:msg key="fwk.notice.header.articleContents"/></th>
			<td class="tdEdit" colspan="3" style="height:226px;vertical-align:top">
				<%=noticeBoard.getArticleContents()%>
			</td>
		</tr>
		<tr>
			<th class="thEditRt"><mc:msg key="fwk.notice.header.attachedFile"/></th>
			<td class="tdEdit" colspan="3">
				<div id="divAttachedFile" style="width:100%;height:100px;overflow-y:auto;">
					<table class="tblDefault withPadding">
<%
					if (fileDataSet.getRowCnt() > 0) {
						for (int i=0; i<fileDataSet.getRowCnt(); i++) {
							String repositoryPath = fileDataSet.getValue(i, "REPOSITORY_PATH");
							String originalName = fileDataSet.getValue(i, "ORIGINAL_NAME");
							String newName = fileDataSet.getValue(i, "NEW_NAME");
							String icon = fileDataSet.getValue(i, "FILE_ICON");
							double fileSize = CommonUtil.toDouble(fileDataSet.getValue(i, "FILE_SIZE")) / 1024;
%>
						<tr>
							<td class="tdDefault">
								<img src="<%=icon%>" style="margin-top:-4px;"/>
								<a class="aEn" onclick="exeDownload('<%=repositoryPath%>', '<%=originalName%>', '<%=newName%>')">
									<%=fileDataSet.getValue(i, "ORIGINAL_NAME")%> (<%=CommonUtil.getNumberMask(fileSize)%> KB)
								</a>
							</td>
						</tr>
<%
						}
					}
%>
					</table>
				</div>
			</td>
		</tr>
	</table>
</div>
<div id="divPagingArea"></div>
<%/************************************************************************************************
* Right & Footer
************************************************************************************************/%>
</div>
</div>
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