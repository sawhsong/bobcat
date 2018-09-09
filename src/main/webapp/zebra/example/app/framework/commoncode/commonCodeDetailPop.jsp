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
	DataSet resultDataSet = (DataSet)pe.getObject("resultDataSet");
	String langCode = CommonUtil.upperCase((String)session.getAttribute("langCode"));
	String isActive = "", isDefault = "", disableFlag = "";
	int masterRow = -1;

	if (resultDataSet.getRowCnt() > 0) {
		masterRow = resultDataSet.getRowIndex("COMMON_CODE", "0000000000");
		isActive = resultDataSet.getValue(masterRow, "USE_YN");
		isDefault = resultDataSet.getValue(masterRow, "DEFAULT_YN");
		if (CommonUtil.equals(isDefault, "Y")) {disableFlag = "disabled";}
	}
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
<style type="text/css">
.codeDetail {list-style:none;margin-top:4px;}
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
		if ($("#btnDelete").attr("disabled") == "disabled") {return;}
		doProcessByButton({mode:"Delete"});
	});

	$("#btnClose").click(function(event) {
		parent.popup.close();
	});

	/*!
	 * process
	 */
	doProcessByButton = function(param) {
		var codeType = "<%=resultDataSet.getValue(masterRow, "CODE_TYPE")%>";
		var actionString = "";
		var params = {};

		if (param.mode == "Update") {
			actionString = "/zebra/framework/commoncode/getUpdate.do";
		} else if (param.mode == "Delete") {
			actionString = "/zebra/framework/commoncode/exeDelete.do";
		}

		params = {
			form:"fmDefault",
			action:actionString,
			data:{
				mode:param.mode,
				codeType:codeType
			}
		};

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
			url:"/zebra/framework/commoncode/exeDelete.do",
			dataType:"json",
			formId:"fmDefault",
			data:{
				codeType:params.data.codeType
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
			<ui:button id="btnDelete" caption="button.com.delete" iconClass="fa-save" status="<%=disableFlag%>"/>
			<ui:button id="btnClose" caption="button.com.close" iconClass="fa-times"/>
		</ui:buttonGroup>
	</div>
</div>
<div id="divSearchCriteriaArea"></div>
<div id="divInformArea" class="areaContainerPopup">
	<table class="tblEdit">
		<caption class="captionEdit"><mc:msg key="fwk.commoncode.searchHeader.codeType"/></caption>
		<colgroup>
			<col width="15%"/>
			<col width="35%"/>
			<col width="15%"/>
			<col width="35%"/>
		</colgroup>
		<tr>
			<th class="thEdit"><mc:msg key="fwk.commoncode.header.codeType"/></th>
			<td class="tdEdit"><%=resultDataSet.getValue(masterRow, "CODE_TYPE")%></td>
			<th class="thEdit"><mc:msg key="fwk.commoncode.header.useYn"/></th>
			<td class="tdEdit"><ui:ccradio name="useYnMaster" codeType="SIMPLE_YN" selectedValue="<%=isActive%>" status="disabled" source="framework"/></td>
		</tr>
		<tr>
			<th class="thEdit"><mc:msg key="fwk.commoncode.header.descriptionEn"/></th>
			<td class="tdEdit"><%=resultDataSet.getValue(masterRow, "DESCRIPTION_EN")%></td>
			<th class="thEdit"><mc:msg key="fwk.commoncode.header.descriptionKo"/></th>
			<td class="tdEdit"><%=resultDataSet.getValue(masterRow, "DESCRIPTION_KO")%></td>
		</tr>
	</table>
</div>
<%/************************************************************************************************
* End of fixed panel
************************************************************************************************/%>
<div class="breaker" style="height:5px;"></div>
</div>
<div id="divScrollablePanelPopup">
<%/************************************************************************************************
* Real Contents - scrollable panel(data, paging)
************************************************************************************************/%>
<div id="divDataArea" class="areaContainerPopup">
	<ul>
<%
	if (resultDataSet.getRowCnt() > 0) {
		for (int i=0; i<resultDataSet.getRowCnt(); i++) {
			String rdoIsActiveName = "useYnDetail_"+i;

			isActive = resultDataSet.getValue(i, "USE_YN");

			if (i == masterRow) {continue;}
%>
		<li class="codeDetail">
			<table class="tblEdit">
				<colgroup>
					<col width="13%"/>
					<col width="*"/>
					<col width="13%"/>
					<col width="15%"/>
					<col width="11%"/>
					<col width="10%"/>
				</colgroup>
				<tr>
					<th class="thEdit"><mc:msg key="fwk.commoncode.header.commonCode"/></th>
					<td class="tdEdit"><%=resultDataSet.getValue(i, "COMMON_CODE")%></td>
					<th class="thEdit"><mc:msg key="fwk.commoncode.header.useYn"/></th>
					<td class="tdEdit"><ui:ccradio name="<%=rdoIsActiveName%>" codeType="SIMPLE_YN" selectedValue="<%=isActive%>" status="disabled" source="framework"/></td>
					<th class="thEdit"><mc:msg key="fwk.commoncode.header.sortOrder"/></th>
					<td class="tdEdit"><%=resultDataSet.getValue(i, "SORT_ORDER")%></td>
				</tr>
				<tr>
					<th class="thEdit"><mc:msg key="fwk.commoncode.header.descriptionEn"/></th>
					<td class="tdEdit"><%=resultDataSet.getValue(i, "DESCRIPTION_EN")%></td>
					<th class="thEdit"><mc:msg key="fwk.commoncode.header.descriptionKo"/></th>
					<td class="tdEdit" colspan="3"><%=resultDataSet.getValue(i, "DESCRIPTION_KO")%></td>
				</tr>
			</table>
		</li>
<%
		}
	}
%>
	</ul>
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