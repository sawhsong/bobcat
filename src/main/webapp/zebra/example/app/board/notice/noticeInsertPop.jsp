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
	SysUser sysUser = (SysUser)session.getAttribute("SysUser");
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
$(function() {
	/*!
	 * event
	 */
	$("#btnSave").click(function(event) {
		if (commonJs.doValidate("fmDefault")) {
			$("#fmDefault").attr("enctype", "multipart/form-data");

			commonJs.confirm({
				contents:"<mc:msg key="Q001"/>",
				buttons:[{
					caption:"Yes",
					callback:function() {
						commonJs.doSubmit({
							form:"fmDefault",
							action:"/zebra/board/notice/exeInsert.do",
							data:{
								articleId:"<%=requestDataSet.getValue("articleId")%>"
							}
						});
					}
				}, {
					caption:"No",
					callback:function() {
					}
				}]
			});
		}
	});

	$("#btnClose").click(function(event) {
		parent.popup.close();
	});

	$("#btnAddFile").click(function(event) {
		commonJs.addFileSelectObject({
			appendToId:"divAttachedFile",
			rowBreak:false
		});
	});

	/*!
	 * process
	 */

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		$("#writerName").focus();
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
			<ui:button id="btnSave" caption="button.com.save" iconClass="fa-save"/>
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
			<th class="thEdit mandatory"><mc:msg key="fwk.notice.header.writerName"/></th>
			<td class="tdEdit">
				<input type="text" id="writerName" name="writerName" class="txtEn" value="<%=sysUser.getUserName()%>" checkName="<mc:msg key="fwk.notice.header.writerName"/>" mandatory/>
			</td>
			<th class="thEdit mandatory"><mc:msg key="fwk.notice.header.writerEmail"/></th>
			<td class="tdEdit">
				<input type="text" id="writerEmail" name="writerEmail" class="txtEn" value="<%=sysUser.getEmail()%>" checkName="<mc:msg key="fwk.notice.header.writerEmail"/>" option="email" mandatory/>
			</td>
		</tr>
		<tr>
			<th class="thEditRt mandatory"><mc:msg key="fwk.notice.header.articleSubject"/></th>
			<td class="tdEdit" colspan="3">
				<input type="text" id="articleSubject" name="articleSubject" class="txtEn" checkName="<mc:msg key="fwk.notice.header.articleSubject"/>" mandatory/>
			</td>
		</tr>
		<tr>
			<th class="thEditRt"><mc:msg key="fwk.notice.header.articleContents"/></th>
			<td class="tdEdit" colspan="3">
				<textarea id="articleContents" name="articleContents" class="txaEn" style="height:224px;"></textarea>
			</td>
		</tr>
		<tr>
			<th class="thEditRt">
				<mc:msg key="fwk.notice.header.attachedFile"/><br/>
				<div id="divButtonAreaRight">
					<ui:button id="btnAddFile" caption="button.com.add" iconClass="fa-plus"/>
				</div>
			</th>
			<td class="tdEdit" colspan="3">
				<div id="divAttachedFile" style="width:100%;height:88px;overflow-y:auto;">
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
<form id="fmHidden" name="fmHidden" method="post" action=""></form>
</body>
</html>