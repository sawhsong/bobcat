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
	String delimiter = ConfigUtil.getProperty("dataDelimiter");
	String isActive = "", disableFlag = "";
	int masterRow = -1;

	if (resultDataSet.getRowCnt() > 0) {
		masterRow = resultDataSet.getRowIndex("COMMON_CODE", "0000000000");
		isActive = resultDataSet.getValue(masterRow, "USE_YN");
	}
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
<style type="text/css">
#liDummy {display:none;}
#divDataArea.areaContainerPopup {padding-top:0px;}
.dummyDetail {list-style:none;margin-top:4px;}
.dragHandler {cursor:move;}
.deleteButton {cursor:pointer;}
</style>
<script type="text/javascript" src="<mc:cp key="viewPageJsName"/>"></script>
<script type="text/javascript">
var codeType = "<%=resultDataSet.getValue(masterRow, "CODE_TYPE")%>";
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
			<th class="thEdit Rt mandatory"><mc:msg key="fwk.commoncode.header.codeType"/></th>
			<td class="tdEdit">
				<ui:text name="codeTypeMaster" id="codeTypeMaster" className="defClass" value="<%=resultDataSet.getValue(masterRow, \"CODE_TYPE\")%>" style="text-transform:uppercase;" checkName="fwk.commoncode.header.codeType" options="mandatory"/>
			</td>
			<th class="thEdit Rt mandatory"><mc:msg key="fwk.commoncode.header.useYn"/></th>
			<td class="tdEdit">
				<ui:ccradio name="useYnMaster" codeType="SIMPLE_YN" selectedValue="<%=isActive%>" source="framework"/>
			</td>
		</tr>
		<tr>
			<th class="thEdit Rt mandatory"><mc:msg key="fwk.commoncode.header.descriptionEn"/></th>
			<td class="tdEdit">
				<ui:text name="descriptionEnMaster" id="descriptionEnMaster" className="defClass" value="<%=resultDataSet.getValue(masterRow, \"DESCRIPTION_EN\")%>" checkName="fwk.commoncode.header.descriptionEn" options="mandatory"/>
			</td>
			<th class="thEdit Rt mandatory"><mc:msg key="fwk.commoncode.header.descriptionKo"/></th>
			<td class="tdEdit">
				<ui:text name="descriptionKoMaster" id="descriptionKoMaster" className="defClass" value="<%=resultDataSet.getValue(masterRow, \"DESCRIPTION_KO\")%>" checkName="fwk.commoncode.header.descriptionKo" options="mandatory"/>
			</td>
		</tr>
	</table>
</div>
<div class="breaker" style="height:5px;"></div>
<div class="divButtonArea areaContainerPopup">
	<div class="divButtonAreaLeft"></div>
	<div class="divButtonAreaRight">
		<ui:buttonGroup id="subButtonGroup">
			<ui:button id="btnAdd" caption="button.com.add" iconClass="fa-plus"/>
		</ui:buttonGroup>
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
	<ul id="ulCommonCodeDetailHolder">
<%
	if (resultDataSet.getRowCnt() > 0) {
		for (int i=0; i<resultDataSet.getRowCnt(); i++) {
			String idSuffix = delimiter+i;
			String rdoIsActiveName = "useYnDetail"+idSuffix;

			isActive = resultDataSet.getValue(i, "USE_YN");

			if (i == masterRow) {continue;}
%>
		<li id="liDummy<%=idSuffix%>" class="dummyDetail" index="<%=i%>">
			<table class="tblEdit">
				<colgroup>
					<col width="3%"/>
					<col width="13%"/>
					<col width="*"/>
					<col width="13%"/>
					<col width="15%"/>
					<col width="11%"/>
					<col width="10%"/>
				</colgroup>
				<tr>
					<th id="thDragHander<%=idSuffix%>" index="<%=i%>" class="thEdit Ct dragHandler" title="<mc:msg key="fwk.commoncode.msg.drag"/>"><i id="iDragHandler<%=idSuffix%>" index="<%=i%>" class="fa fa-lg fa-sort"></i></th>
					<th class="thEdit Rt mandatory"><mc:msg key="fwk.commoncode.header.commonCode"/></th>
					<td class="tdEdit">
						<input type="text" id="commonCodeDetail<%=idSuffix%>" name="commonCodeDetail<%=idSuffix%>" value="<%=resultDataSet.getValue(i, "COMMON_CODE")%>" class="txtEn" checkName="<mc:msg key="fwk.commoncode.header.commonCode"/>" mandatory/>
					</td>
					<th class="thEdit Rt mandatory"><mc:msg key="fwk.commoncode.header.useYn"/></th>
					<td class="tdEdit">
						<ui:ccradio name="<%=rdoIsActiveName%>" codeType="SIMPLE_YN" selectedValue="<%=isActive%>" source="framework"/>
					</td>
					<th class="thEdit Rt mandatory"><mc:msg key="fwk.commoncode.header.sortOrder"/></th>
					<td class="tdEdit">
						<input type="text" id="sortOrderDetail<%=idSuffix%>" name="sortOrderDetail<%=idSuffix%>" value="<%=resultDataSet.getValue(i, "SORT_ORDER")%>" class="txtEn" checkName="<mc:msg key="fwk.commoncode.header.sortOrder"/>" mandatory option="numeric"/>
					</td>
				</tr>
				<tr>
					<th id="thDeleteButton<%=idSuffix%>" index="<%=i%>" class="thEdit Ct deleteButton" title="<mc:msg key="fwk.commoncode.msg.delete"/>"><i id="iDeleteButton<%=idSuffix%>" index="<%=i%>" class="fa fa-lg fa-times"></i></th>
					<th class="thEdit Rt mandatory"><mc:msg key="fwk.commoncode.header.descriptionEn"/></th>
					<td class="tdEdit">
						<input type="text" id="descriptionEnDetail<%=idSuffix%>" name="descriptionEnDetail<%=idSuffix%>" value="<%=resultDataSet.getValue(i, "DESCRIPTION_EN")%>" class="txtEn" checkName="<mc:msg key="fwk.commoncode.header.descriptionEn"/>" mandatory/>
					</td>
					<th class="thEdit Rt mandatory"><mc:msg key="fwk.commoncode.header.descriptionKo"/></th>
					<td class="tdEdit" colspan="3">
						<input type="text" id="descriptionKoDetail<%=idSuffix%>" name="descriptionKoDetail<%=idSuffix%>" value="<%=resultDataSet.getValue(i, "DESCRIPTION_KO")%>" class="txtEn" checkName="<mc:msg key="fwk.commoncode.header.descriptionKo"/>" mandatory/>
					</td>
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
<li id="liDummy" class="dummyDetail">
	<table class="tblEdit">
		<colgroup>
			<col width="3%"/>
			<col width="13%"/>
			<col width="*"/>
			<col width="13%"/>
			<col width="15%"/>
			<col width="11%"/>
			<col width="10%"/>
		</colgroup>
		<tr>
			<th id="thDragHander" class="thEdit Ct dragHandler" title="<mc:msg key="fwk.commoncode.msg.drag"/>"><i id="iDragHandler" class="fa fa-lg fa-sort"></i></th>
			<th class="thEdit Rt mandatory"><mc:msg key="fwk.commoncode.header.commonCode"/></th>
			<td class="tdEdit">
				<input type="text" id="commonCodeDetail" name="commonCodeDetail" class="txtEn" checkName="<mc:msg key="fwk.commoncode.header.commonCode"/>" mandatory/>
			</td>
			<th class="thEdit Rt mandatory"><mc:msg key="fwk.commoncode.header.useYn"/></th>
			<td class="tdEdit">
				<ui:ccradio name="useYnDetail" codeType="SIMPLE_YN" selectedValue="Y" source="framework"/>
			</td>
			<th class="thEdit Rt mandatory"><mc:msg key="fwk.commoncode.header.sortOrder"/></th>
			<td class="tdEdit">
				<input type="text" id="sortOrderDetail" name="sortOrderDetail" class="txtEn" checkName="<mc:msg key="fwk.commoncode.header.sortOrder"/>" mandatory option="numeric"/>
			</td>
		</tr>
		<tr>
			<th id="thDeleteButton" class="thEdit Ct deleteButton" title="<mc:msg key="fwk.commoncode.msg.delete"/>"><i id="iDeleteButton" class="fa fa-lg fa-times"></i></th>
			<th class="thEdit Rt mandatory"><mc:msg key="fwk.commoncode.header.descriptionEn"/></th>
			<td class="tdEdit">
				<input type="text" id="descriptionEnDetail" name="descriptionEnDetail" class="txtEn" checkName="<mc:msg key="fwk.commoncode.header.descriptionEn"/>" mandatory/>
			</td>
			<th class="thEdit Rt mandatory"><mc:msg key="fwk.commoncode.header.descriptionKo"/></th>
			<td class="tdEdit" colspan="3">
				<input type="text" id="descriptionKoDetail" name="descriptionKoDetail" class="txtEn" checkName="<mc:msg key="fwk.commoncode.header.descriptionKo"/>" mandatory/>
			</td>
		</tr>
	</table>
</li>
</form>
<%/************************************************************************************************
* Additional Form
************************************************************************************************/%>
<form id="fmHidden" name="fmHidden" method="post" action=""></form>
</body>
</html>