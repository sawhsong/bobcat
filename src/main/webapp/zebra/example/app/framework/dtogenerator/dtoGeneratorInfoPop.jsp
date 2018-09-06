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
	String dataSource = requestDataSet.getValue("dataSource");
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
var dataSource = "<%=dataSource%>";

$(function() {
	/*!
	 * event
	 */
	$("#btnClose").click(function(event) {
		parent.popupInfo.close();
	});

	$("#btnGenerate").click(function(event) {
		var selectedSystem = commonJs.getCheckedValueFromRadio("system");

		if (getCheckedCount(selectedSystem) <= 0) {
			commonJs.warn("<mc:msg key="I902"/>");
			return;
		}

		commonJs.confirm({
			contents:"<mc:msg key="Q901"/>",
			width:300,
			height:150,
			buttons:[{
				caption:"Yes",
				callback:function() {
					exeGenerate($("#fmDefault").serializeArray());
				}
			}, {
				caption:"No",
				callback:function() {
				}
			}]
		});
	});

	$("[name=system]").click(function(event) {
		if ($(this).val() == "Framework") {
			$("#divSystemProject").stop().animate({opacity:"hide"}, "fast");
			$("#divSystemFramework").stop().delay(300).animate({opacity:"show"}, globalMap.get("effectDuration"));
		} else if ($(this).val() == "Project") {
			$("#divSystemFramework").stop().animate({opacity:"hide"}, "fast");
			$("#divSystemProject").stop().delay(300).animate({opacity:"show"}, globalMap.get("effectDuration"));
		}
	});

	/*!
	 * process
	 */
	getCheckedCount = function(selectedSystem) {
		var checkedCnt = 0;

		$("input:checkbox").each(function(index) {
			var name = $(this).attr("name");
			if (name.indexOf(selectedSystem) != -1 && $(this).is(":checked")) {
				checkedCnt++;
			}
		});
		return checkedCnt;
	};

	exeGenerate = function(paramDataArray) {
		var paramData = {};
		$.each(paramDataArray, function(i, param) {
			paramData[param.name] = param.value;
		});
		paramData.dataSource = dataSource;

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
						paramData.tableName = $this.val();

						commonJs.ajaxSubmit({
							url:"/zebra/framework/dtogenerator/exeGenerate.do",
							dataType:"json",
							data:paramData,
							blind:false,
							success:function(data, textStatus) {
								var result = commonJs.parseAjaxResult(data, textStatus, "json");

								if (result.isSuccess == true || result.isSuccess == "true") {
									popupProcess.addContents("<mc:msg key="I802"/> : "+paramData.tableName);

									if ((index+1) == parent.commonJs.getCountChecked("chkForGenerate")) {
										parent.commonJs.openDialog({
											type:"information",
											contents:"<mc:msg key="I801"/>",
											modal:true,
											width:300,
											buttons:[{
												caption:framework.messages.ok, callback:function() {
													try {
														parent.popupInfo.close();
														popupProcess.close();
													} catch(ex) {
													}
												}
											}]
										});
									}
								} else {
									popupProcess.addContents("<mc:msg key="E801"/> : "+paramData.tableName);
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
		</tag:buttonGroup>
	</div>
</div>
<div id="divSearchCriteriaArea"></div>
<div id="divInformArea" class="areaContainerPopup">
	<div class="panel panel-success">
		<div class="panel-heading"><h3 class="panel-title"><mc:msg key="fwk.dtogenerator.header.system"/></h3></div>
		<div class="panel-body">
			<table class="tblDefault withPadding">
				<colgroup>
					<col width="25%"/>
					<col width="75%"/>
				</colgroup>
				<tr>
					<td class="tdDefault">
						<label class="lblRadioEn"><input type="radio" name="system" class="rdoEn inEdit" value="Project" checked/><mc:msg key="fwk.dtogenerator.header.project"/></label>
						<label class="lblRadioEn"><input type="radio" name="system" class="rdoEn inEdit" value="Framework"/><mc:msg key="fwk.dtogenerator.header.framework"/></label>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
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
	<div id="divSystemProject">
		<table class="tblEdit">
			<caption class="captionEdit"><mc:msg key="fwk.dtogenerator.header.basicSource"/></caption>
			<colgroup>
				<col width="20%"/>
				<col width="4%"/>
				<col width="*"/>
			</colgroup>
			<tr>
				<th class="thEdit" rowspan="2"><mc:msg key="fwk.dtogenerator.header.dto"/></th>
				<td class="tdEditCt" colspan="2">
					<label class="lblCheckEn block">
						<input type="checkbox" name="dtoProject" class="chkEn inTbl" value="Y" checked/><mc:msg key="fwk.dtogenerator.header.dtoClass"/>
						&nbsp;&nbsp;[<%=paramEntity.getObject("dtoProjectPath")%>]
					</label>
				</td>
			</tr>
			<tr>
				<td class="tdEditCt"></td>
				<td class="tdEdit">
					<label class="lblCheckEn block">
						<input type="checkbox" name="hibernateDtoConfigProject" class="chkEn" value="Y" checked/><mc:msg key="fwk.dtogenerator.header.hibernateConfig"/>
					</label>
					<label class="lblCheckEn block">
						<input type="checkbox" name="mybatisDtoMapperConfigProject" class="chkEn" value="Y"/><mc:msg key="fwk.dtogenerator.header.mybatisDtoMapperConfig"/>
					</label>
				</td>
			</tr>
			<tr>
				<th class="thEdit" rowspan="3"><mc:msg key="fwk.dtogenerator.header.dao"/></th>
				<td class="tdEditCt" colspan="2">
					<label class="lblCheckEn block">
						<input type="checkbox" name="daoProject" class="chkEn inTbl" value="Y"/><mc:msg key="fwk.dtogenerator.header.daoClass"/>
						&nbsp;&nbsp;[<%=paramEntity.getObject("daoProjectPath")%>]
					</label>
				</td>
			</tr>
			<tr>
				<td class="tdEditCt"></td>
				<td class="tdEdit">
					<label class="lblCheckEn block">
						<input type="checkbox" name="hibernateDaoImplProject" class="chkEn" value="Y"/><mc:msg key="fwk.dtogenerator.header.hibernateDaoImpl"/>
					</label>
					<label class="lblCheckEn block">
						<input type="checkbox" name="mybatisDaoImplProject" class="chkEn" value="Y"/><mc:msg key="fwk.dtogenerator.header.mybatisDaoImpl"/>
					</label>
				</td>
			</tr>
			<tr>
				<td class="tdEdit" colspan="2">
					<label class="lblCheckEn block">
						<input type="checkbox" name="daoSpringConfigProject" class="chkEn" value="Y"/><mc:msg key="fwk.dtogenerator.header.daoSpringConfig"/>
					</label>
				</td>
			</tr>
		</table>
		<div class="verGap4"></div>
		<table class="tblEdit">
			<caption class="captionEdit"><mc:msg key="fwk.dtogenerator.header.queryXml"/></caption>
			<colgroup>
				<col width="20%"/>
				<col width="4%"/>
				<col width="*"/>
			</colgroup>
			<tr>
				<th class="thEdit"><mc:msg key="fwk.dtogenerator.header.hibernateQuery"/></th>
				<td class="tdEditCt"><input type="checkbox" name="hibernateQueryProject" class="chkEn inTbl" value="Y"/></td>
				<td class="tdEdit"><%=paramEntity.getObject("hibernateQueryProjectPath")%></td>
			</tr>
			<tr>
				<th class="thEdit"><mc:msg key="fwk.dtogenerator.header.mybatisQuery"/></th>
				<td class="tdEditCt"><input type="checkbox" name="mybatisQueryProject" class="chkEn inTbl" value="Y"/></td>
				<td class="tdEdit"><%=paramEntity.getObject("mybatisQueryProjectPath")%></td>
			</tr>
		</table>
	</div>
	<div id="divSystemFramework" style="display:none">
		<table class="tblEdit">
			<caption class="captionEdit"><mc:msg key="fwk.dtogenerator.header.basicSource"/></caption>
			<colgroup>
				<col width="20%"/>
				<col width="4%"/>
				<col width="*"/>
			</colgroup>
			<tr>
				<th class="thEdit" rowspan="2"><mc:msg key="fwk.dtogenerator.header.dto"/></th>
				<td class="tdEditCt" colspan="2">
					<label class="lblCheckEn block">
						<input type="checkbox" name="dtoFramework" class="chkEn inTbl" value="Y" checked/><mc:msg key="fwk.dtogenerator.header.dtoClass"/>
						&nbsp;&nbsp;[<%=paramEntity.getObject("dtoFrameworkPath")%>]
					</label>
				</td>
			</tr>
			<tr>
				<td class="tdEditCt"></td>
				<td class="tdEdit">
					<label class="lblCheckEn block">
						<input type="checkbox" name="hibernateDtoConfigFramework" class="chkEn" value="Y" checked/><mc:msg key="fwk.dtogenerator.header.hibernateConfig"/>
					</label>
					<label class="lblCheckEn block">
						<input type="checkbox" name="mybatisDtoMapperConfigFramework" class="chkEn" value="Y"/><mc:msg key="fwk.dtogenerator.header.mybatisDtoMapperConfig"/>
					</label>
				</td>
			</tr>
			<tr>
				<th class="thEdit" rowspan="3"><mc:msg key="fwk.dtogenerator.header.dao"/></th>
				<td class="tdEditCt" colspan="2">
					<label class="lblCheckEn block">
						<input type="checkbox" name="daoFramework" class="chkEn inTbl" value="Y"/><mc:msg key="fwk.dtogenerator.header.daoClass"/>
						&nbsp;&nbsp;[<%=paramEntity.getObject("daoFrameworkPath")%>]
					</label>
				</td>
			</tr>
			<tr>
				<td class="tdEditCt"></td>
				<td class="tdEdit">
					<label class="lblCheckEn block">
						<input type="checkbox" name="hibernateDaoImplFramework" class="chkEn" value="Y"/><mc:msg key="fwk.dtogenerator.header.hibernateDaoImpl"/>
					</label>
					<label class="lblCheckEn block">
						<input type="checkbox" name="mybatisDaoImplFramework" class="chkEn" value="Y"/><mc:msg key="fwk.dtogenerator.header.mybatisDaoImpl"/>
					</label>
				</td>
			</tr>
			<tr>
				<td class="tdEdit" colspan="2">
					<label class="lblCheckEn block">
						<input type="checkbox" name="daoSpringConfigFramework" class="chkEn" value="Y"/><mc:msg key="fwk.dtogenerator.header.daoSpringConfig"/>
					</label>
				</td>
			</tr>
		</table>
		<div class="verGap4"></div>
		<table class="tblEdit">
			<caption class="captionEdit"><mc:msg key="fwk.dtogenerator.header.queryXml"/></caption>
			<colgroup>
				<col width="20%"/>
				<col width="4%"/>
				<col width="*"/>
			</colgroup>
			<tr>
				<th class="thEdit"><mc:msg key="fwk.dtogenerator.header.hibernateQuery"/></th>
				<td class="tdEditCt"><input type="checkbox" name="hibernateQueryFramework" class="chkEn inTbl" value="Y"/></td>
				<td class="tdEdit"><%=paramEntity.getObject("hibernateQueryFrameworkPath")%></td>
			</tr>
			<tr>
				<th class="thEdit"><mc:msg key="fwk.dtogenerator.header.mybatisQuery"/></th>
				<td class="tdEditCt"><input type="checkbox" name="mybatisQueryFramework" class="chkEn inTbl" value="Y"/></td>
				<td class="tdEdit"><%=paramEntity.getObject("mybatisQueryFrameworkPath")%></td>
			</tr>
		</table>
	</div>
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