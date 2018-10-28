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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="icon" type="image/png" href="<mc:cp key="imgIcon"/>/zebraFavicon.png">
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
				caption:com.caption.yes,
				callback:function() {
					exeGenerate($("#fmDefault").serializeArray());
				}
			}, {
				caption:com.caption.no,
				callback:function() {
				}
			}]
		});
	});

	$("[name=system]").click(function(event) {
		if ($(this).val() == "Framework") {
			$("#divSystemProject").stop().animate({opacity:"hide"}, "fast");
			$("#divSystemFramework").stop().delay(300).animate({opacity:"show"}, jsconfig.get("effectDuration"));
		} else if ($(this).val() == "Project") {
			$("#divSystemFramework").stop().animate({opacity:"hide"}, "fast");
			$("#divSystemProject").stop().delay(300).animate({opacity:"show"}, jsconfig.get("effectDuration"));
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

								if (commonJs.toBoolean(result.isSuccess)) {
									popupProcess.addContents("<mc:msg key="I802"/> : "+paramData.tableName);

									if ((index+1) == parent.commonJs.getCountChecked("chkForGenerate")) {
										parent.commonJs.openDialog({
											type:com.message.I000,
											contents:"<mc:msg key="I801"/>",
											modal:true,
											width:300,
											buttons:[{
												caption:com.caption.ok, callback:function() {
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
									popupProcess.addContents("[Error Message : "+data.message+"]");
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
<div id="divInformArea" class="areaContainerPopup">
	<div class="panel panel-success">
		<div class="panel-heading"><h3 class="panel-title"><mc:msg key="fwk.dtogenerator.header.system"/></h3></div>
		<div class="panel-body">
			<table class="tblDefault withPadding">
				<tr>
					<td class="tdDefault">
						<ui:radio name="system" inputClassName="inTbl" value="Project" text="fwk.dtogenerator.header.project" isSelected="true"/>
						<ui:radio name="system" inputClassName="inTbl" value="Framework" text="fwk.dtogenerator.header.framework"/>
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
				<th class="thEdit Rt" rowspan="2"><mc:msg key="fwk.dtogenerator.header.dto"/></th>
				<td class="tdEdit Ct" colspan="2">
					<label class="lblCheckEn block">
						<input type="checkbox" name="dtoProject" class="chkEn inTbl" value="Y" checked/><mc:msg key="fwk.dtogenerator.header.dtoClass"/>
						&nbsp;&nbsp;[<%=paramEntity.getObject("dtoProjectPath")%>]
					</label>
				</td>
			</tr>
			<tr>
				<td class="tdEdit Ct" style="border-right:0px"></td>
				<td class="tdEdit" style="border-left:0px">
					<ui:check name="hibernateDtoConfigProject" value="Y" text="fwk.dtogenerator.header.hibernateConfig" isChecked="true" displayType="block"/>
					<ui:check name="mybatisDtoMapperConfigProject" value="Y" text="fwk.dtogenerator.header.mybatisDtoMapperConfig" displayType="block"/>
				</td>
			</tr>
			<tr>
				<th class="thEdit Rt" rowspan="3"><mc:msg key="fwk.dtogenerator.header.dao"/></th>
				<td class="tdEditCt" colspan="2">
					<label class="lblCheckEn block">
						<input type="checkbox" name="daoProject" class="chkEn inTbl" value="Y"/><mc:msg key="fwk.dtogenerator.header.daoClass"/>
						&nbsp;&nbsp;[<%=paramEntity.getObject("daoProjectPath")%>]
					</label>
				</td>
			</tr>
			<tr>
				<td class="tdEdit Ct" style="border-right:0px"></td>
				<td class="tdEdit" style="border-left:0px">
					<ui:check name="hibernateDaoImplProject" value="Y" text="fwk.dtogenerator.header.hibernateDaoImpl" displayType="block"/>
					<ui:check name="mybatisDaoImplProject" value="Y" text="fwk.dtogenerator.header.mybatisDaoImpl" displayType="block"/>
				</td>
			</tr>
			<tr>
				<td class="tdEdit" colspan="2">
					<ui:check name="daoSpringConfigProject" value="Y" text="fwk.dtogenerator.header.daoSpringConfig"/>
				</td>
			</tr>
		</table>
		<div class="verGap4"></div>
		<table class="tblEdit">
			<caption class="captionEdit"><mc:msg key="fwk.dtogenerator.header.queryXml"/></caption>
			<colgroup>
				<col width="20%"/>
				<col width="*"/>
			</colgroup>
			<tr>
				<th class="thEdit Rt"><mc:msg key="fwk.dtogenerator.header.hibernateQuery"/></th>
				<td class="tdEdit">
					<label class="lblCheckEn block">
						<input type="checkbox" name="hibernateQueryProject" class="chkEn inTbl" value="Y"/><%=paramEntity.getObject("hibernateQueryProjectPath")%>
					</label>
				</td>
			</tr>
			<tr>
				<th class="thEdit Rt"><mc:msg key="fwk.dtogenerator.header.mybatisQuery"/></th>
				<td class="tdEditCt">
					<label class="lblCheckEn block">
						<input type="checkbox" name="mybatisQueryProject" class="chkEn inTbl" value="Y"/><%=paramEntity.getObject("mybatisQueryProjectPath")%>
					</label>
				</td>
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
				<th class="thEdit Rt" rowspan="2"><mc:msg key="fwk.dtogenerator.header.dto"/></th>
				<td class="tdEdit" colspan="2">
					<label class="lblCheckEn block">
						<input type="checkbox" name="dtoFramework" class="chkEn inTbl" value="Y" checked/><mc:msg key="fwk.dtogenerator.header.dtoClass"/>
						&nbsp;&nbsp;[<%=paramEntity.getObject("dtoFrameworkPath")%>]
					</label>
				</td>
			</tr>
			<tr>
				<td class="tdEdit Rt" style="border-right:0px"></td>
				<td class="tdEdit" style="border-left:0px">
					<ui:check name="hibernateDtoConfigFramework" value="Y" text="fwk.dtogenerator.header.hibernateConfig" isChecked="true" displayType="block"/>
					<ui:check name="mybatisDtoMapperConfigFramework" value="Y" text="fwk.dtogenerator.header.mybatisDtoMapperConfig" displayType="block"/>
				</td>
			</tr>
			<tr>
				<th class="thEdit Rt" rowspan="3"><mc:msg key="fwk.dtogenerator.header.dao"/></th>
				<td class="tdEdit" colspan="2">
					<label class="lblCheckEn block">
						<input type="checkbox" name="daoFramework" class="chkEn inTbl" value="Y"/><mc:msg key="fwk.dtogenerator.header.daoClass"/>
						&nbsp;&nbsp;[<%=paramEntity.getObject("daoFrameworkPath")%>]
					</label>
				</td>
			</tr>
			<tr>
				<td class="tdEdit Rt" style="border-right:0px"></td>
				<td class="tdEdit" style="border-left:0px">
					<ui:check name="hibernateDaoImplFramework" value="Y" text="fwk.dtogenerator.header.hibernateDaoImpl" displayType="block"/>
					<ui:check name="mybatisDaoImplFramework" value="Y" text="fwk.dtogenerator.header.mybatisDaoImpl" displayType="block"/>
				</td>
			</tr>
			<tr>
				<td class="tdEdit" colspan="2">
					<ui:check name="daoSpringConfigFramework" value="Y" text="fwk.dtogenerator.header.daoSpringConfig"/>
				</td>
			</tr>
		</table>
		<div class="verGap4"></div>
		<table class="tblEdit">
			<caption class="captionEdit"><mc:msg key="fwk.dtogenerator.header.queryXml"/></caption>
			<colgroup>
				<col width="20%"/>
				<col width="*"/>
			</colgroup>
			<tr>
				<th class="thEdit Rt"><mc:msg key="fwk.dtogenerator.header.hibernateQuery"/></th>
				<td class="tdEdit">
					<label class="lblCheckEn block">
						<input type="checkbox" name="hibernateQueryFramework" class="chkEn inTbl" value="Y"/><%=paramEntity.getObject("hibernateQueryFrameworkPath")%>
					</label>
				</td>
			</tr>
			<tr>
				<th class="thEdit Rt"><mc:msg key="fwk.dtogenerator.header.mybatisQuery"/></th>
				<td class="tdEdit">
					<label class="lblCheckEn block">
						<input type="checkbox" name="mybatisQueryFramework" class="chkEn inTbl" value="Y"/><%=paramEntity.getObject("mybatisQueryFrameworkPath")%>
					</label>
				</td>
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