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
	ZebraDomainDictionary zebraDomainDictionary = (ZebraDomainDictionary)pe.getObject("zebraDomainDictionary");
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
		var domainId = "<%=zebraDomainDictionary.getDomainId()%>";
		var actionString = "";
		var params = {};

		if (param.mode == "Update") {
			actionString = "/zebra/framework/domaindictionary/getUpdate.do";
		} else if (param.mode == "Delete") {
			actionString = "/zebra/framework/domaindictionary/exeDelete.do";
		}

		params = {
			form:"fmDefault",
			action:actionString,
			data:{
				mode:param.mode,
				domainId:domainId
			}
		};

		if (param.mode == "Update") {
			parent.popup.resizeTo(0, 80);
		}

		if (param.mode == "Delete") {
			commonJs.confirm({
				contents:"<mc:msg key="Q002"/>",
				buttons:[{
					caption:"Yes",
					callback:function() {
						exeDelete(params);
					}
				}, {
					caption:"No",
					callback:function() {
					}
				}]
			});
		} else {
			commonJs.doSubmit(params);
		}
	};

	exeDelete = function(params) {
		commonJs.ajaxSubmit({
			url:"/zebra/framework/domaindictionary/exeDelete.do",
			dataType:"json",
			formId:"fmDefault",
			data:{
				domainId:params.data.domainId
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
			<ui:button id="btnEdit" caption="button.com.edit" iconClass="fa-edit"/>
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
			<th class="thEdit Rt"><mc:msg key="fwk.domaindictionary.header.name"/></th>
			<td class="tdEdit"><%=zebraDomainDictionary.getDomainName()%></td>
			<th class="thEdit Rt"><mc:msg key="fwk.domaindictionary.header.nameAbbrev"/></th>
			<td class="tdEdit"><%=zebraDomainDictionary.getNameAbbreviation()%></td>
		</tr>
		<tr>
			<th class="thEdit Rt"><mc:msg key="fwk.domaindictionary.header.dataType"/></th>
			<td class="tdEdit"><%=zebraDomainDictionary.getDataType()%></td>
			<th class="thEdit Rt"><mc:msg key="fwk.domaindictionary.header.dataLength"/></th>
			<td class="tdEdit"><%=CommonUtil.getNumberMask(zebraDomainDictionary.getDataLength())%></td>
		</tr>
		<tr>
			<th class="thEdit Rt"><mc:msg key="fwk.domaindictionary.header.dataPrecision"/></th>
			<td class="tdEdit"><%=zebraDomainDictionary.getDataPrecision()%></td>
			<th class="thEdit Rt"><mc:msg key="fwk.domaindictionary.header.dataScale"/></th>
			<td class="tdEdit"><%=zebraDomainDictionary.getDataScale()%></td>
		</tr>
		<tr>
			<th class="thEdit Rt"><mc:msg key="fwk.domaindictionary.header.desc"/></th>
			<td class="tdEdit" colspan="3" style="height:200px;vertical-align:top">
				<ui:txa name="description" id="description" value="<%=HtmlUtil.htmlToString(zebraDomainDictionary.getDescription())%>" className="defClass" style="height:200px;" status="display"/>
			</td>
		</tr>
		<tr>
			<th class="thEdit Rt"><mc:msg key="fwk.domaindictionary.header.insertUser"/></th>
			<td class="tdEdit"><%=zebraDomainDictionary.getInsertUserName()%></td>
			<th class="thEdit Rt"><mc:msg key="fwk.domaindictionary.header.insertDate"/></th>
			<td class="tdEdit"><%=CommonUtil.toViewDateString(zebraDomainDictionary.getInsertDate())%></td>
		</tr>
		<tr>
			<th class="thEdit Rt"><mc:msg key="fwk.domaindictionary.header.updateUser"/></th>
			<td class="tdEdit"><%=zebraDomainDictionary.getUpdateUserName()%></td>
			<th class="thEdit Rt"><mc:msg key="fwk.domaindictionary.header.updateDate"/></th>
			<td class="tdEdit"><%=CommonUtil.toViewDateString(zebraDomainDictionary.getUpdateDate())%></td>
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