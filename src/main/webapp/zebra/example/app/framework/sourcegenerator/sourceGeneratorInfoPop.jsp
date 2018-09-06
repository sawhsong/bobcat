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
	DataSet requestDataSet = paramEntity.getRequestDataSet();
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
	$("#btnClose").click(function(event) {
		parent.popup.close();
	});

	$("#btnGenerate").click(function(event) {
		commonJs.confirm({
			contents:"<mc:msg key="Q901"/>",
			width:300,
			height:150,
			buttons:[{
				caption:"Yes",
				callback:function() {
					$("input[type=checkbox]").each(function(index) {
						$(this).prop("disabled", false);
					});
					exeGenerate($("#fmDefault").serializeArray());
				}
			}, {
				caption:"No",
				callback:function() {
				}
			}]
		});
	});

	/*!
	 * process
	 */
	exeGenerate = function(paramDataArray) {
		var paramData = {};
		$.each(paramDataArray, function(i, param) {
			paramData[param.name] = param.value;
		});

		popupProcess = parent.commonJs.openPopup({
			popupId:"ProcessInformation",
			header:"Process Result",
			width:600,
			height:400,
			blind:false,
			onLoad:function() {
				parent.$("input[name=chkForGenerate]:checked").each(function(index) {
					var $this = $(this);

					setTimeout(function() {
						paramData.menuId = $this.val();
						paramData.menuName = $this.attr("menuName");

						commonJs.ajaxSubmit({
							url:"/zebra/framework/sourcegenerator/exeGenerate.do",
							dataType:"json",
							data:paramData,
							blind:false,
							success:function(data, textStatus) {
								var result = commonJs.parseAjaxResult(data, textStatus, "json");

								if (result.isSuccess == true || result.isSuccess == "true") {
									var dataDelimiter = globalMap.get("dataDelimiter");
									var menuIdArray = paramData.menuId.split(dataDelimiter);
									var menuId = menuIdArray[1];
									popupProcess.addContents("<mc:msg key="I802"/> : "+menuId);

									if ((index+1) == parent.commonJs.getCountChecked("chkForGenerate")) {
										parent.commonJs.openDialog({
											type:"information",
											contents:"<mc:msg key="I801"/>",
											modal:true,
											width:300,
											buttons:[{
												caption:framework.messages.ok, callback:function() {
													try {
														parent.popup.close();
														popupProcess.close();
													} catch(ex) {
													}
												}
											}]
										});
									}
								} else {
									popupProcess.addContents("<mc:msg key="E801"/> : "+paramData.menuId);
								}
							}
						});
					}, index * 100);
				});
			}
		});
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
			<ui:button id="btnGenerate" caption="button.com.generate" iconClass="fa-gears"/>
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
		<caption class="captionEdit"><mc:msg key="fwk.sourcegenerator.header.classSource"/></caption>
		<colgroup>
			<col width="20%"/>
			<col width="20%"/>
			<col width="20%"/>
			<col width="*"/>
		</colgroup>
		<tr>
			<th class="thEditRt"><mc:msg key="fwk.sourcegenerator.header.srcPath"/></th>
			<td class="tdEdit" colspan="3">
				<input type="text" id="javaSourcePath" name="javaSourcePath" class="txtDpl" value="<%=paramEntity.getObject("javaPath")%>" readonly/>
			</td>
		</tr>
		<tr>
			<th class="thEditRt"><mc:msg key="fwk.sourcegenerator.header.actionClass"/></th>
			<td class="tdEdit">
				<label class="lblCheckDis"><input type="checkbox" name="javaCreateAction" class="chkDis" value="Y" checked disabled/><mc:msg key="fwk.sourcegenerator.header.generate"/></label>
			</td>
			<th class="thEditRt"><mc:msg key="fwk.sourcegenerator.header.cudPageHandler"/></th>
			<td class="tdEdit">
				<label class="lblRadioEn"><input type="radio" name="actionHandlerType" class="rdoEn" value="A" checked/><mc:msg key="fwk.sourcegenerator.header.ajax"/></label>
				<label class="lblRadioEn"><input type="radio" name="actionHandlerType" class="rdoEn" value="P"/><mc:msg key="fwk.sourcegenerator.header.pageHandler"/></label>
			</td>
		</tr>
		<tr>
			<th class="thEditRt"><mc:msg key="fwk.sourcegenerator.header.bizClass"/></th>
			<td class="tdEdit" colspan="3">
				<label class="lblCheckDis"><input type="checkbox" name="javaCreateBiz" class="chkDis" value="Y" checked disabled/><mc:msg key="fwk.sourcegenerator.header.generate"/></label>
			</td>
		</tr>
	</table>
	<div class="verGap10"></div>
	<table class="tblEdit">
		<caption class="captionEdit"><mc:msg key="fwk.sourcegenerator.header.viewSource"/></caption>
		<colgroup>
			<col width="20%"/>
			<col width="20%"/>
			<col width="20%"/>
			<col width="*"/>
		</colgroup>
		<tr>
			<th class="thEditRt"><mc:msg key="fwk.sourcegenerator.header.srcPath"/></th>
			<td class="tdEdit" colspan="3">
				<input type="text" id="jspSourcePath" name="jspSourcePath" class="txtDpl" value="<%=paramEntity.getObject("jspPath")%>" readonly/>
			</td>
		</tr>
		<tr>
			<th class="thEditRt"><mc:msg key="fwk.sourcegenerator.header.list"/></th>
			<td class="tdEdit">
				<label class="lblCheckDis"><input type="checkbox" name="jspCreateList" class="chkDis" value="Y" checked disabled/><mc:msg key="fwk.sourcegenerator.header.generate"/></label>
			</td>
			<th class="thEditRt"><mc:msg key="fwk.sourcegenerator.header.pageType"/></th>
			<td class="tdEdit">
				<label class="lblRadioEn"><input type="radio" name="jspSubPageType" class="rdoEn" value="Popup" checked/><mc:msg key="fwk.sourcegenerator.header.pop"/></label>
				<label class="lblRadioEn"><input type="radio" name="jspSubPageType" class="rdoEn" value="Page"/><mc:msg key="fwk.sourcegenerator.header.page"/></label>
			</td>
		</tr>
		<tr>
			<th class="thEditRt"><mc:msg key="fwk.sourcegenerator.header.detail"/></th>
			<td class="tdEdit" colspan="3">
				<label class="lblCheckEn"><input type="checkbox" name="jspCreateDetail" class="chkEn" value="Y" checked/><mc:msg key="fwk.sourcegenerator.header.generate"/></label>
			</td>
		</tr>
		<tr>
			<th class="thEditRt"><mc:msg key="fwk.sourcegenerator.header.insert"/></th>
			<td class="tdEdit" colspan="3">
				<label class="lblCheckEn"><input type="checkbox" name="jspCreateInsert" class="chkEn" value="Y" checked/><mc:msg key="fwk.sourcegenerator.header.generate"/></label>
			</td>
		</tr>
		<tr>
			<th class="thEditRt"><mc:msg key="fwk.sourcegenerator.header.update"/></th>
			<td class="tdEdit" colspan="3">
				<label class="lblCheckEn"><input type="checkbox" name="jspCreateUpdate" class="chkEn" value="Y" checked/><mc:msg key="fwk.sourcegenerator.header.generate"/></label>
			</td>
		</tr>
	</table>
	<div class="verGap10"></div>
	<table class="tblEdit">
		<caption class="captionEdit"><mc:msg key="fwk.sourcegenerator.header.configETC"/></caption>
		<colgroup>
			<col width="20%"/>
			<col width="*"/>
			<col width="14%"/>
		</colgroup>
		<tr>
			<th class="thEditRt"><mc:msg key="fwk.sourcegenerator.header.spring"/></th>
			<td class="tdEdit">
				<input type="text" id="springConfigPath" name="springConfigPath" class="txtDpl" value="<%=paramEntity.getObject("springPath")%>" readonly/>
			</td>
			<td class="tdEdit">
				<label class="lblCheckEn"><input type="checkbox" name="createSpring" class="chkEn" value="Y" checked/><mc:msg key="fwk.sourcegenerator.header.generate"/></label>
			</td>
		</tr>
		<tr>
			<th class="thEditRt"><mc:msg key="fwk.sourcegenerator.header.struts"/></th>
			<td class="tdEdit">
				<input type="text" id="strutsConfigPath" name="strutsConfigPath" class="txtDpl" value="<%=paramEntity.getObject("strutsPath")%>" readonly/>
			</td>
			<td class="tdEdit">
				<label class="lblCheckEn"><input type="checkbox" name="createStruts" class="chkEn" value="Y" checked/><mc:msg key="fwk.sourcegenerator.header.generate"/></label>
			</td>
		</tr>
		<tr>
			<th class="thEditRt"><mc:msg key="fwk.sourcegenerator.header.message"/></th>
			<td class="tdEdit">
				<input type="text" id="messageConfigPath" name="messageConfigPath" class="txtDpl" value="<%=paramEntity.getObject("messagePath")%>" readonly/>
			</td>
			<td class="tdEdit">
				<label class="lblCheckEn"><input type="checkbox" name="createMessage" class="chkEn" value="Y" checked/><mc:msg key="fwk.sourcegenerator.header.generate"/></label>
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