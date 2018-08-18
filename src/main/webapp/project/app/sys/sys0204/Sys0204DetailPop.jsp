<%/************************************************************************************************
* Description - Sys0204 - Country / Currency
*   - Generated by Source Generator
************************************************************************************************/%>
<%@ include file="/webapp/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity paramEntity = (ParamEntity)request.getAttribute("paramEntity");
	DataSet resultDataSet = (DataSet)paramEntity.getObject("resultDataSet");
	SysCountryCurrency sysCountryCurrency = (SysCountryCurrency)paramEntity.getObject("sysCountryCurrency");
	String dateFormat = ConfigUtil.getProperty("format.date.java");
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
	$("#btnEdit").click(function(event) {
		doProcessByButton({mode:"Update"});
	});

	$("#btnDelete").click(function(event) {
		doProcessByButton({mode:"Delete"});
	});

	$("#btnClose").click(function(event) {
		parent.popup.close();
	});

	/*!
	 * process
	 */
	doProcessByButton = function(param) {
		var countryCurrencyId = "<%=sysCountryCurrency.getCountryCurrencyId()%>";
		var action = "";

		if (param.mode == "Update") {
			action = "/sys/0204/getUpdate.do";
			parent.popup.resizeTo(0, -40);
		} else if (param.mode == "Delete") {
			action = "/sys/0204/exeDelete.do";
		}

		if (param.mode == "Delete") {
			commonJs.confirm({
				contents:"<tag:msg key="Q002"/>",
				buttons:[{
					caption:"Yes",
					callback:function() {
						commonJs.ajaxSubmit({
							url:action,
							dataType:"json",
							formId:"fmDefault",
							data:{
								countryCurrencyId:countryCurrencyId
							},
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
												parent.popup.close();
												parent.doSearch();
											}
										}]
									});
								} else {
									commonJs.error(result.message);
								}
							}
						});
					}
				}, {
					caption:"No",
					callback:function() {
					}
				}]
			});
		} else {
			commonJs.doSubmit({
				form:"fmDefault",
				action:action,
				data:{
					mode:param.mode,
					countryCurrencyId:countryCurrencyId
				}
			});
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
<div id="divLocationPathArea"><%@ include file="/webapp/project/common/include/bodyLocationPathArea.jsp"%></div>
<%/************************************************************************************************
* Real Contents - fixed panel(tab, button, search, information)
************************************************************************************************/%>
<div id="divTabArea"></div>
<div id="divButtonArea" class="areaContainerPopup">
	<div id="divButtonAreaLeft"></div>
	<div id="divButtonAreaRight">
		<tag:buttonGroup id="buttonGroup">
			<tag:button id="btnEdit" caption="button.com.edit" iconClass="fa-edit"/>
			<tag:button id="btnDelete" caption="button.com.delete" iconClass="fa-save"/>
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
		<caption class="captionEdit"><tag:msg key="sys0204.caption.currency"/></caption>
		<colgroup>
			<col width="15%"/>
			<col width="35%"/>
			<col width="15%"/>
			<col width="35%"/>
		</colgroup>
		<tr>
			<th class="thEdit"><tag:msg key="sys0204.header.currencyName"/></th>
			<td class="tdEdit"><%=sysCountryCurrency.getCurrencyName()%></td>
			<th class="thEdit"><tag:msg key="sys0204.header.currencySymbol"/></th>
			<td class="tdEdit"><%=sysCountryCurrency.getCurrencySymbol()%></td>
		</tr>
		<tr>
			<th class="thEdit"><tag:msg key="sys0204.header.currencyAlphaCode"/></th>
			<td class="tdEdit"><%=sysCountryCurrency.getCurrencyAlphabeticCode()%></td>
			<th class="thEdit"><tag:msg key="sys0204.header.currencyNumCode"/></th>
			<td class="tdEdit"><%=sysCountryCurrency.getCurrencyNumericCode()%></td>
		</tr>
	</table>
	<div class="horGap10"></div>
	<table class="tblEdit">
		<caption class="captionEdit"><tag:msg key="sys0204.caption.country"/></caption>
		<colgroup>
			<col width="15%"/>
			<col width="35%"/>
			<col width="15%"/>
			<col width="35%"/>
		</colgroup>
		<tr>
			<th class="thEdit"><tag:msg key="sys0204.header.countryName"/></th>
			<td class="tdEdit"><%=sysCountryCurrency.getCountryName()%></td>
			<th class="thEdit"><tag:msg key="sys0204.header.countryLangCode"/></th>
			<td class="tdEdit"><%=sysCountryCurrency.getCountryLanguageCode()%></td>
		</tr>
		<tr>
			<th class="thEdit"><tag:msg key="sys0204.header.countryCode2"/></th>
			<td class="tdEdit"><%=sysCountryCurrency.getCountryCode2()%></td>
			<th class="thEdit"><tag:msg key="sys0204.header.countryCode3"/></th>
			<td class="tdEdit"><%=sysCountryCurrency.getCountryCode3()%></td>
		</tr>
		<tr>
			<th class="thEdit"><tag:msg key="sys0204.header.countryNumCode"/></th>
			<td class="tdEdit" colspan="3"><%=sysCountryCurrency.getCountryNumericCode()%></td>
		</tr>
	</table>
	<div class="horGap10"></div>
	<table class="tblEdit">
		<caption class="captionEdit"><tag:msg key="sys0204.caption.basic"/></caption>
		<colgroup>
			<col width="15%"/>
			<col width="35%"/>
			<col width="15%"/>
			<col width="35%"/>
		</colgroup>
		<tr>
			<th class="thEdit"><tag:msg key="sys0204.header.insertUser"/></th>
			<td class="tdEdit"><%=sysCountryCurrency.getInsertUserName()%></td>
			<th class="thEdit"><tag:msg key="sys0204.header.insertDate"/></th>
			<td class="tdEdit"><%=CommonUtil.toString(sysCountryCurrency.getInsertDate(), dateFormat)%></td>
		</tr>
		<tr>
			<th class="thEdit"><tag:msg key="sys0204.header.updateUser"/></th>
			<td class="tdEdit"><%=sysCountryCurrency.getUpdateUserName()%></td>
			<th class="thEdit"><tag:msg key="sys0204.header.updateDate"/></th>
			<td class="tdEdit"><%=CommonUtil.toString(sysCountryCurrency.getUpdateDate(), dateFormat)%></td>
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