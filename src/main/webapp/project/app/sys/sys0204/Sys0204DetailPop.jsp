<%/************************************************************************************************
* Description - Sys0204 - Annoucement
*	- Generated by Source Generator
************************************************************************************************/%>
<%@ include file="/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity paramEntity = (ParamEntity)request.getAttribute("paramEntity");
	DataSet requestDataSet = (DataSet)paramEntity.getRequestDataSet();
	SysBoard sysBoard = (SysBoard)paramEntity.getObject("sysBoard");
	DataSet fileDataSet = (DataSet)paramEntity.getObject("fileDataSet");
%>
<%/************************************************************************************************
* HTML
************************************************************************************************/%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="icon" type="image/png" href="<mc:cp key="imgIcon"/>/faviconBobcat.png">
<title><mc:msg key="main.system.title"/></title>
<%/************************************************************************************************
* Stylesheet & Javascript
************************************************************************************************/%>
<%@ include file="/shared/page/incCssJs.jsp"%>
<style type="text/css">
</style>
<script type="text/javascript" src="<mc:cp key="viewPageJsName"/>"></script>
<script type="text/javascript">
var articleId = "<%=sysBoard.getArticleId()%>";
</script>
</head>
<%/************************************************************************************************
* Page & Header
************************************************************************************************/%>
<body>
<form id="fmDefault" name="fmDefault" method="post" action="">
<div id="divPopupWindowHolder">
<div id="divFixedPanelPopup">
<div id="divLocationPathArea"><%@ include file="/project/common/include/bodyLocationPathArea.jsp"%></div>
<%/************************************************************************************************
* Real Contents - fixed panel(tab, button, search, information)
************************************************************************************************/%>
<div id="divTabArea"></div>
<div id="divButtonArea" class="areaContainerPopup">
	<div id="divButtonAreaLeft"></div>
	<div id="divButtonAreaRight">
		<ui:buttonGroup id="buttonGroup">
			<ui:button id="btnEdit" caption="button.com.edit" iconClass="fa-edit"/>
<%-- 			<ui:button id="btnReply" caption="button.com.reply" iconClass="fa-reply-all"/> --%>
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
<div class="breaker"></div>
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
			<th class="thEdit Rt"><mc:msg key="sys0204.header.writerName"/></th>
			<td class="tdEdit"><%=sysBoard.getWriterName()%></td>
			<th class="thEdit Rt"><mc:msg key="sys0204.header.writerEmail"/></th>
			<td class="tdEdit"><%=sysBoard.getWriterEmail()%></td>
		</tr>
		<tr>
			<th class="thEdit Rt"><mc:msg key="sys0204.header.updateDate"/></th>
			<td class="tdEdit"><%=CommonUtil.toViewDateString(sysBoard.getUpdateDate())%></td>
			<th class="thEdit Rt"><mc:msg key="sys0204.header.hitCount"/></th>
			<td class="tdEdit"><%=CommonUtil.getNumberMask(sysBoard.getHitCnt())%></td>
		</tr>
		<tr>
			<th class="thEdit Rt"><mc:msg key="sys0204.header.articleSubject"/></th>
			<td class="tdEdit" colspan="3"><%=sysBoard.getArticleSubject()%></td>
		</tr>
		<tr>
			<th class="thEdit Rt"><mc:msg key="sys0204.header.articleContents"/></th>
			<td class="tdEdit" colspan="3" style="height:380px;vertical-align:top">
				<ui:txa style="height:372px;padding:0px 4px 0px 0px" value="<%=sysBoard.getArticleContents()%>" status="display"/>
			</td>
		</tr>
		<tr>
			<th class="thEdit Rt"><mc:msg key="sys0204.header.attachedFile"/></th>
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
		<tr>
			<th class="thEdit Rt">Send Email</th>
			<td class="tdEdit" colspan="3"><ui:ccradio name="sendEmail" codeType="SIMPLE_YN" selectedValue="<%=sysBoard.getSendEmail()%>" isCustomised="true"/></td>
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
<form id="fmHidden" name="fmHidden" method="post" action=""></form>
</body>
</html>