<%/************************************************************************************************
* Description - Bbs0204 - Free Board
*   - Generated by Source Generator
************************************************************************************************/%>
<%@ include file="/webapp/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity paramEntity = (ParamEntity)request.getAttribute("paramEntity");
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
							action:"/bbs/0204/exeUpdate.do",
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

	$("#btnBack").click(function(event) {
		history.go(-1);
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
	setFCKEditor = function() {
		$("#articleContents").ckeditor({
			height:340,
			toolbar:"frameworkBasic"
		});
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		setFCKEditor();
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
			<tag:button id="btnSave" caption="button.com.save" iconClass="fa-save"/>
			<tag:button id="btnBack" caption="button.com.back" iconClass="fa-arrow-left"/>
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
<div id="divScrollablePanel">
<%/************************************************************************************************
* Real Contents - scrollable panel(data, paging)
************************************************************************************************/%>
<div id="divDataArea" class="areaContainer">
	<table class="tblEdit">
		<colgroup>
			<col width="12%"/>
			<col width="37%"/>
			<col width="12%"/>
			<col width="37%"/>
		</colgroup>
		<tr>
			<th class="thEdit mandatory"><tag:msg key="bbs0204.header.writerName"/></th>
			<td class="tdEdit">
				<input type="text" id="writerName" name="writerName" class="txtEn" value="<%=sysBoard.getWriterName()%>" checkName="<tag:msg key="bbs0204.header.writerName"/>" mandatory/>
			</td>
			<th class="thEdit mandatory"><tag:msg key="bbs0204.header.writerEmail"/></th>
			<td class="tdEdit">
				<input type="text" id="writerEmail" name="writerEmail" class="txtEn" value="<%=sysBoard.getWriterEmail()%>" checkName="<tag:msg key="bbs0204.header.writerEmail"/>" option="email" mandatory/>
			</td>
		</tr>
		<tr>
			<th class="thEdit mandatory"><tag:msg key="bbs0204.header.articleSubject"/></th>
			<td class="tdEdit" colspan="3">
				<input type="text" id="articleSubject" name="articleSubject" class="txtEn" value="<%=sysBoard.getArticleSubject()%>" checkName="<tag:msg key="bbs0204.header.articleSubject"/>" mandatory/>
			</td>
		</tr>
		<tr>
			<th class="thEdit">
				<tag:msg key="bbs0204.header.attachedFile"/><br/>
			</th>
			<td class="tdEdit" colspan="3">
				<div id="divAttachedFileList" style="width:100%;height:80px;overflow-y:auto;">
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
			<th class="thEdit"><tag:msg key="bbs0204.header.articleContents"/></th>
			<td class="tdEdit" colspan="3">
				<textarea id="articleContents" name="articleContents" class="txaEn"><%=sysBoard.getArticleContents()%></textarea>
			</td>
		</tr>
		<tr>
			<th class="thEdit">
				<tag:msg key="bbs0204.header.attachedFile"/><br/>
				<div id="divButtonAreaRight">
					<tag:button id="btnAddFile" caption="button.com.add" iconClass="fa-plus"/>
				</div>
			</th>
			<td class="tdEdit" colspan="3">
				<div id="divAttachedFile" style="width:100%;height:72px;overflow-y:auto;">
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