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
	DataSet resultDataSet = (DataSet)paramEntity.getRequestDataSet();
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
		if (!commonJs.doValidate("fmDefault")) {
			return;
		}

		commonJs.confirm({
			contents:"<tag:msg key="Q001"/>",
			buttons:[{
				caption:"Yes",
				callback:function() {
					commonJs.ajaxSubmit({
						url:"/sys/0204/exeInsert.do",
						dataType:"json",
						formId:"fmDefault",
						data:{
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
	});

	$("#btnClose").click(function(event) {
		parent.popup.close();
	});

	/*!
	 * process
	 */

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		$("#currencyName").focus();
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
		<caption class="captionEdit"><tag:msg key="sys0204.caption.currency"/></caption>
		<colgroup>
			<col width="15%"/>
			<col width="35%"/>
			<col width="15%"/>
			<col width="35%"/>
		</colgroup>
		<tr>
			<th class="thEdit mandatory"><tag:msg key="sys0204.header.currencyName"/></th>
			<td class="tdEdit">
				<input type="text" id="currencyName" name="currencyName" class="txtEn" value="" checkName="<tag:msg key="sys0204.header.currencyName"/>" mandatory/>
			</td>
			<th class="thEdit"><tag:msg key="sys0204.header.currencySymbol"/></th>
			<td class="tdEdit">
				<input type="text" id="currencySymbol" name="currencySymbol" class="txtEn" value="" maxlength="10" checkName="<tag:msg key="sys0204.header.currencySymbol"/>"/>
			</td>
		</tr>
		<tr>
			<th class="thEdit mandatory"><tag:msg key="sys0204.header.currencyAlphaCode"/></th>
			<td class="tdEdit">
				<input type="text" id="currencyAlphabeticCode" name="currencyAlphabeticCode" class="txtEn" maxlength="5" style="text-transform:uppercase;" value="" checkName="<tag:msg key="sys0204.header.currencyAlphaCode"/>" mandatory/>
			</td>
			<th class="thEdit"><tag:msg key="sys0204.header.currencyNumCode"/></th>
			<td class="tdEdit">
				<input type="text" id="currencyNumericCode" name="currencyNumericCode" class="txtEn" value="" maxlength="5" checkName="<tag:msg key="sys0204.header.currencyNumCode"/>"/>
			</td>
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
			<th class="thEdit mandatory"><tag:msg key="sys0204.header.countryName"/></th>
			<td class="tdEdit">
				<input type="text" id="countryName" name="countryName" class="txtEn" value="" checkName="<tag:msg key="sys0204.header.countryName"/>" mandatory/>
			</td>
			<th class="thEdit"><tag:msg key="sys0204.header.countryLangCode"/></th>
			<td class="tdEdit">
				<input type="text" id="countryLanguageCode" name="countryLanguageCode" class="txtEn" value="" maxlength="5" checkName="<tag:msg key="sys0204.header.countryLangCode"/>"/>
			</td>
		</tr>
		<tr>
			<th class="thEdit"><tag:msg key="sys0204.header.countryCode2"/></th>
			<td class="tdEdit">
				<input type="text" id="countryCode2" name="countryCode2" class="txtEn" style="text-transform:uppercase;" value="" maxlength="5" checkName="<tag:msg key="sys0204.header.countryCode2"/>"/>
			</td>
			<th class="thEdit"><tag:msg key="sys0204.header.countryCode3"/></th>
			<td class="tdEdit">
				<input type="text" id="countryCode3" name="countryCode3" class="txtEn" style="text-transform:uppercase;" value="" maxlength="5" checkName="<tag:msg key="sys0204.header.countryCode3"/>"/>
			</td>
		</tr>
		<tr>
			<th class="thEdit"><tag:msg key="sys0204.header.countryNumCode"/></th>
			<td class="tdEdit">
				<input type="text" id="countryNumericCode" name="countryNumericCode" class="txtEn" value="" maxlength="5" checkName="<tag:msg key="sys0204.header.countryCode2"/>"/>
			</td>
			<th class="thEdit"></th>
			<td class="tdEdit"></td>
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