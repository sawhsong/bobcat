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
%>
<%/************************************************************************************************
* HTML
************************************************************************************************/%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
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
	$("#btnSave").click(function(event) {
		if (!commonJs.doValidate("fmDefault")) {
			return;
		}

		if ($("#dataType").val() == "VARCHAR2" && commonJs.isEmpty($("#dataLength").val())) {
			commonJs.openDialog({
				type:"warning",
				contents:"Data Length" + framework.messages.mandatory,
				buttons:[{
					caption:"Ok",
					callback:function() {
						commonJs.getBootstrapSelectbox("dataLength").addClass("error");
						return;
					}
				}]
			});
		} else {
			commonJs.confirm({
				contents:"<mc:msg key="Q001"/>",
				buttons:[{
					caption:"Yes",
					callback:function() {
						exeSave();
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

	/*!
	 * process
	 */
	exeSave = function() {
		commonJs.ajaxSubmit({
			url:"/zebra/framework/domaindictionary/exeInsert.do",
			dataType:"json",
			formId:"fmDefault",
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
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		$("#domainName").focus();
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
		</tag:buttonGroup>
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
			<th class="thEdit mandatory"><mc:msg key="fwk.domaindictionary.header.name"/></th>
			<td class="tdEdit">
				<input type="text" id="domainName" name="domainName" class="txtEn" checkName="<mc:msg key="fwk.domaindictionary.header.name"/>" mandatory/>
			</td>
			<th class="thEdit mandatory"><mc:msg key="fwk.domaindictionary.header.nameAbbrev"/></th>
			<td class="tdEdit">
				<input type="text" id="nameAbbreviation" name="nameAbbreviation" class="txtEn" checkName="<mc:msg key="fwk.domaindictionary.header.nameAbbrev"/>" mandatory/>
			</td>
		</tr>
		<tr>
			<th class="thEdit mandatory"><mc:msg key="fwk.domaindictionary.header.dataType"/></th>
			<td class="tdEdit">
				<ui:select id="dataType" name="dataType" codeType="DOMAIN_DATA_TYPE" options="mandatory" source="framework"/>
			</td>
			<th class="thEdit"><mc:msg key="fwk.domaindictionary.header.dataLength"/></th>
			<td class="tdEdit">
				<ui:select id="dataLength" name="dataLength" codeType="DOMAIN_DATA_LENGTH" caption="==Select==" source="framework"/>
			</td>
		</tr>
		<tr>
			<th class="thEdit"><mc:msg key="fwk.domaindictionary.header.dataPrecision"/></th>
			<td class="tdEdit">
				<ui:select id="dataPrecision" name="dataPrecision" codeType="DOMAIN_DATA_PRECISION" caption="==Select==" source="framework"/>
			</td>
			<th class="thEdit"><mc:msg key="fwk.domaindictionary.header.dataScale"/></th>
			<td class="tdEdit">
				<ui:select id="dataScale" name="dataScale" codeType="DOMAIN_DATA_SCALE" caption="==Select==" source="framework"/>
			</td>
		</tr>
		<tr>
			<th class="thEditRt"><mc:msg key="fwk.domaindictionary.header.desc"/></th>
			<td class="tdEdit" colspan="3" style="height:200px;vertical-align:top">
				<textarea id="description" name="description" class="txaEn" style="height:200px;"></textarea>
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