<%/************************************************************************************************
* Description
* - Layout order(Normal page)
*	#divHeaderHolder
*	#divBodyHolder
*		#divBodyLeft
*		#divBodyCenter - Each 'Area' in Body Center must set this class('areaContainer') when it has its contents
*			#divFixedPanel
*				#divLocationPathArea
*				#divTabArea
*				#divButtonArea(Left & Right)
*				#divSearchCriteriaArea
*				#divInformArea
*				#breaker
*			#divScrollablePanel
*				#divDataArea
*				#divPagingArea
*		#divBodyRight
*	#divFooterHolder
************************************************************************************************/%>
<%@ include file="/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity paramEntity = (ParamEntity)request.getAttribute("paramEntity");
	DataSet resultDataSet = (DataSet)paramEntity.getObject("resultDataSet");
%>
<%/************************************************************************************************
* HTML
************************************************************************************************/%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="icon" type="image/png" href="<mc:cp key="imgIcon"/>/faviconHKAccount.png">
<title><mc:msg key="main.system.title"/></title>
<%/************************************************************************************************
* Stylesheet & Javascript
************************************************************************************************/%>
<%@ include file="/shared/page/incCssJs.jsp"%>
<style type="text/css">
#divWrapper {padding:0px 5px;}
#tblGridAnnouncement {}
#tblGridAnnouncement th, #tblGridAnnouncement td {border:0px;}
#tblBankStatement {}
#tblGridBankStatement th, #tblGridAnnouncement td {}

.sectionHolder {margin-bottom:20px;}
.sectionHeader {padding:0px 4px 4px 0px;border-bottom:1px solid #cccccc;}
.badge {padding:4px 6px 4px 6px;margin-top:-6px;font-size:11px;font-weight:normal;border-radius:3px;background:#094369;cursor:default;}

/* #hNotice.ui-state-default {background-color:#DFF0D8;padding-top:10px;padding-bottom:10px;} */
/* #hNotice.ui-accordion-header.ui-state-active {background:#DFF0D8;padding-top:10px;padding-bottom:10px;} */
/* #hFreeBoard.ui-state-default {background-color:#DFF0D8;padding-top:10px;padding-bottom:10px;} */
/* #hFreeBoard.ui-accordion-header.ui-state-active {background:#DFF0D8;padding-top:10px;padding-bottom:10px;} */
/* #hIncome.ui-state-default {background-color:#5BC0DE;padding-top:10px;padding-bottom:10px;color:#ffffff;} */
/* #hIncome.ui-accordion-header.ui-state-active {background:#5BC0DE;padding-top:10px;padding-bottom:10px;color:#ffffff;} */
/* #hExpense.ui-state-default {background-color:#5BC0DE;padding-top:10px;padding-bottom:10px;color:#ffffff;} */
/* #hExpense.ui-accordion-header.ui-state-active {background:#5BC0DE;padding-top:10px;padding-bottom:10px;color:#ffffff;} */
</style>
<script type="text/javascript" src="<mc:cp key="viewPageJsName"/>"></script>
<script type="text/javascript">
</script>
</head>
<%/************************************************************************************************
* Page & Header
************************************************************************************************/%>
<body>
<form id="fmDefault" name="fmDefault" method="post" action="">
<div id="divHeaderHolder" class="ui-layout-north"><%@ include file="/project/common/include/header.jsp"%></div>
<div id="divBodyHolder" class="ui-layout-center">
<div id="divBodyLeft" class="ui-layout-west"><%@ include file="/project/common/include/bodyLeft.jsp"%></div>
<div id="divBodyCenter" class="ui-layout-center">
<div id="divFixedPanel">
<div id="divLocationPathArea"><%@ include file="/project/common/include/bodyLocationPathArea.jsp"%></div>
<%/************************************************************************************************
* Real Contents - fixed panel(tab, button, search, information)
************************************************************************************************/%>
<div id="divTabArea"></div>
<div id="divButtonArea">
	<div id="divButtonAreaLeft"></div>
	<div id="divButtonAreaRight"></div>
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
<div id="divWrapper">
	<div id="divLeft" style="width:45%;float:left">
		<div class="sectionHolder">
			<h4 class="sectionHeader">Announcement
				<span style="float:right;">
					<ui:icon id="icnRefreshAnnouncement" className="fa-refresh" style="font-size:15px;margin-left:14px;" title="Refresh"/>
				</span>
			</h4>
			<div id="sectionAnnouncement" class="sectionContents">
				<table id="tblGridAnnouncement" class="tblGrid">
					<colgroup>
						<col width="*"/>
						<col width="5%"/>
						<col width="12%"/>
						<col width="6%"/>
					</colgroup>
					<tbody id="tbodyGridAnnouncement">
					</tbody>
				</table>
			</div>
		</div>
		<div class="sectionHolder">
			<h4 class="sectionHeader">Bank Statements and Allocation Status
				<span style="float:right;">
					<ui:icon id="icnRefreshBankStatement" className="fa-refresh" style="font-size:15px;margin-left:14px;" title="Refresh"/>
				</span>
			</h4>
			<div id="sectionBankStatement" class="sectionContents">
				<table id="tblGridBankStatement" class="tblGrid">
					<colgroup>
						<col width="*"/>
						<col width="6%"/>
						<col width="10%"/>
						<col width="10%"/>
					</colgroup>
					<thead>
						<tr class="noBorderAll">
							<th class="thGrid">Bank Account</th>
							<th class="thGrid Rt">BS</th>
							<th class="thGrid Rt">Uploaded</th>
							<th class="thGrid Rt">Allocated</th>
						</tr>
					</thead>
					<tbody id="tbodyGridBankStatement">
					</tbody>
				</table>
			</div>
		</div>
		<h4 class="sectionHeader">Quotation | Invoice</h4>
<!--
		<div class="divTabArea" class="areaContainer">
			<ui:tab id="tabQuotationInvoice">
				<ui:tabList caption="Quotation" isActive="true" iconClass="fa-user" iconPosition="left"/>
				<ui:tabList caption="Invoice" iconClass="fa-university" iconPosition="left"/>
			</ui:tab>
		</div>
		<div id="div0" style="">
			<h3>Quotation
				<span style="float:right;">
					<span id="spnBadgeQuotationCnt" class="badge"></span>
					<span id="spnBadgeQuotationAmt" class="badge"></span>
					<ui:icon id="icnRefreshQuotation" className="fa-refresh" style="font-size:15px;margin-left:14px;" title="Refresh"/>
				</span>
			</h3>
			<div id="sectionQuotation" class="sectionContents">
				<table id="tblGridQuotation" class="tblGrid">
					<colgroup>
						<col width="*"/>
						<col width="12%"/>
						<col width="12%"/>
					</colgroup>
					<thead>
						<tr class="noBorderAll">
							<th class="thGrid Lt">Customer</th>
							<th class="thGrid Rt">Total Amount</th>
							<th class="thGrid Rt">Date Issued</th>
						</tr>
					</thead>
					<tbody id="tbodyGridQuotation">
					</tbody>
				</table>
			</div>
		</div>
		<div id="div1" style="display:none">
			<h3>Invoice
				<span style="float:right;">
					<span id="spnBadgeInvoiceCnt" class="badge"></span>
					<span id="spnBadgeInvoiceAmt" class="badge"></span>
					<ui:icon id="icnRefreshInvoice" className="fa-refresh" style="font-size:15px;margin-left:14px;" title="Refresh"/>
				</span>
			</h3>
			<div id="sectionInvoice" class="sectionContents">
				<table id="tblGridInvoice" class="tblGrid">
					<colgroup>
						<col width="*"/>
						<col width="12%"/>
						<col width="12%"/>
						<col width="12%"/>
					</colgroup>
					<thead>
						<tr class="noBorderAll">
							<th class="thGrid Lt">Customer</th>
							<th class="thGrid Lt">Status</th>
							<th class="thGrid Rt">Total Amount</th>
							<th class="thGrid Rt">Date Issued</th>
						</tr>
					</thead>
					<tbody id="tbodyGridInvoice">
					</tbody>
				</table>
			</div>
		</div>
-->
		<div class="accordionQuotationInvoice">
			<div class="accordionGroup">
				<h3>Quotation
					<span style="float:right;">
						<span id="spnBadgeQuotationCnt" class="badge"></span>
						<span id="spnBadgeQuotationAmt" class="badge"></span>
						<ui:icon id="icnRefreshQuotation" className="fa-refresh" style="font-size:15px;margin-left:14px;" title="Refresh"/>
					</span>
				</h3>
				<div id="sectionQuotation" class="sectionContents">
					<table id="tblGridQuotation" class="tblGrid">
						<colgroup>
							<col width="*"/>
							<col width="12%"/>
							<col width="12%"/>
						</colgroup>
						<thead>
							<tr class="noBorderAll">
								<th class="thGrid Lt">Customer</th>
								<th class="thGrid Rt">Total Amount</th>
								<th class="thGrid Rt">Date Issued</th>
							</tr>
						</thead>
						<tbody id="tbodyGridQuotation">
						</tbody>
					</table>
				</div>
			</div>
			<div class="accordionGroup">
				<h3>Invoice
					<span style="float:right;">
						<span id="spnBadgeInvoiceCnt" class="badge"></span>
						<span id="spnBadgeInvoiceAmt" class="badge"></span>
						<ui:icon id="icnRefreshInvoice" className="fa-refresh" style="font-size:15px;margin-left:14px;" title="Refresh"/>
					</span>
				</h3>
				<div id="sectionInvoice" class="sectionContents">
					<table id="tblGridInvoice" class="tblGrid">
						<colgroup>
							<col width="*"/>
							<col width="12%"/>
							<col width="12%"/>
							<col width="12%"/>
						</colgroup>
						<thead>
							<tr class="noBorderAll">
								<th class="thGrid Lt">Customer</th>
								<th class="thGrid Lt">Status</th>
								<th class="thGrid Rt">Total Amount</th>
								<th class="thGrid Rt">Date Issued</th>
							</tr>
						</thead>
						<tbody id="tbodyGridInvoice">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div id="divRight" style="width:53%;float:right">
		<div class="sectionHolder">
			<h4 class="sectionHeader">Allocation Status</h4>
		</div>


	</div>
</div>
</div>
<div id="divPagingArea"></div>
<%/************************************************************************************************
* Right & Footer
************************************************************************************************/%>
</div>
</div>
<div id="divBodyRight" class="ui-layout-east"><%@ include file="/project/common/include/bodyRight.jsp"%></div>
</div>
<div id="divFooterHolder" class="ui-layout-south"><%@ include file="/project/common/include/footer.jsp"%></div>
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