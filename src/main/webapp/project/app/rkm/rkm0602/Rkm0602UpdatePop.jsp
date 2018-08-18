<%/************************************************************************************************
* Description - Rkm0602 - Repayment
*   - Generated by Source Generator
************************************************************************************************/%>
<%@ include file="/webapp/shared/page/incCommon.jsp"%>
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
<link rel="icon" type="image/png" href="<tag:cp key="imgIcon"/>/favicon.png">
<title><tag:msg key="main.system.title"/></title>
<%/************************************************************************************************
* Stylesheet & Javascript
************************************************************************************************/%>
<%@ include file="/webapp/shared/page/incCssJs.jsp"%>
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
				contents:"<tag:msg key="Q001"/>",
				buttons:[{
					caption:"Yes",
					callback:function() {
						commonJs.doSubmit({
							form:"fmDefault",
							action:"/rkm/0602/exeUpdate.do",
							data:{
								articleId:"<%=sysBoard.getArticleId()%>"
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
		parent.popup.setHeader("<tag:msg key="rkm0602.title.popupTitleEdit"/>");
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
<div id="divLocationPathArea"><%@ include file="/webapp/project/common/include/bodyLocationPathArea.jsp"%></div>
<%/************************************************************************************************
* Real Contents - fixed panel(tab, button, search, information)
************************************************************************************************/%>
<div id="divTabArea"></div>
<div id="divButtonArea" class="areaContainerPopup">
	<div id="divButtonAreaLeft"></div>
	<div id="divButtonAreaRight">
		<tag:buttonGroup id="buttonGroup">
			<tag:button id="btnSave" caption="button.com.save" iconClass="fa-save"/>
			<tag:button id="btnClose" caption="button.com.close" iconClass="fa-times"/>
		</tag:buttonGroup>
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
			<th class="thEditRt mandatory"><tag:msg key="rkm0602.header.writerName"/></th>
			<td class="tdEdit">
				<input type="text" id="writerName" name="writerName" class="txtEn" value="<%=sysBoard.getWriterName()%>" checkName="<tag:msg key="rkm0602.header.writerName"/>" mandatory/>
			</td>
			<th class="thEditRt mandatory"><tag:msg key="rkm0602.header.writerEmail"/></th>
			<td class="tdEdit">
				<input type="text" id="writerEmail" name="writerEmail" class="txtEn" value="<%=sysBoard.getWriterEmail()%>" checkName="<tag:msg key="rkm0602.header.writerEmail"/>" option="email" mandatory/>
			</td>
		</tr>
		<tr>
			<th class="thEditRt mandatory"><tag:msg key="rkm0602.header.articleSubject"/></th>
			<td class="tdEdit" colspan="3">
				<input type="text" id="articleSubject" name="articleSubject" class="txtEn" value="<%=sysBoard.getArticleSubject()%>" checkName="<tag:msg key="rkm0602.header.articleSubject"/>" mandatory/>
			</td>
		</tr>
		<tr>
			<th class="thEditRt"><tag:msg key="rkm0602.header.articleContents"/></th>
			<td class="tdEdit" colspan="3">
				<textarea id="articleContents" name="articleContents" class="txaEn" style="height:224px;"><%=CommonUtil.replace(sysBoard.getArticleContents(), "<br/>", "\n")%></textarea>
			</td>
		</tr>
		<tr>
			<th class="thEditRt">
				<tag:msg key="rkm0602.header.attachedFile"/><br/>
			</th>
			<td class="tdEdit" colspan="3">
				<div id="divAttachedFileList" style="width:100%;height:100px;overflow-y:auto;">
					<table class="tblDefault withPadding">
<%
					if (fileDataSet.getRowCnt() > 0) {
						for (int i=0; i<fileDataSet.getRowCnt(); i++) {
							double fileSize = CommonUtil.toDouble(fileDataSet.getValue(i, "FILE_SIZE")) / 1024;
%>
						<tr>
							<td class="tdDefault">
								<label class="lblCheckEn">
									<input type="checkbox" id="chkForDel_<%=i%>" name="chkForDel" class="chkEn" value="<%=fileDataSet.getValue(i, "FILE_ID")%>" title="Select to Delete"/>
									<img src="<%=fileDataSet.getValue(i, "FILE_ICON")%>" style="margin-top:-4px;"/>
									<%=fileDataSet.getValue(i, "ORIGINAL_NAME")%> (<%=CommonUtil.getNumberMask(fileSize)%> KB)
								</label>
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
			<th class="thEditRt">
				<tag:msg key="rkm0602.header.attachedFile"/><br/>
				<div id="divButtonAreaRight">
					<tag:button id="btnAddFile" caption="button.com.add" iconClass="fa-plus"/>
				</div>
			</th>
			<td class="tdEdit" colspan="3">
				<div id="divAttachedFile" style="width:100%;height:100px;overflow-y:auto;">
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