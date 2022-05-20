/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Ads0202List.js
 *************************************************************************************************/
var popup, preview;
var dateTimeFormat = jsconfig.get("dateTimeFormatJs");
var dateFormat = jsconfig.get("dateFormatJs");

$(function() {
	/*!
	 * event
	 */
	$("#btnSearch").click(function() {
		doSearch();
	});

	$("#btnNew").click(function() {
		getEdit("");
	});

	$("#btnDelete").click(function(event) {
		doDelete();
	});

	$("#icnFromDate").click(function(event) {
		commonJs.openCalendar(event, "fromDate");
	});

	$("#icnToDate").click(function(event) {
		commonJs.openCalendar(event, "toDate");
	});

	$("#btnClear").click(function(event) {
		commonJs.clearSearchCriteria();
	});

	$("#icnCheck").click(function(event) {
		commonJs.toggleCheckboxes("chkForDel");
	});

	$(document).keydown(function(event) {
		var code = event.keyCode || event.which, element = event.target;
		if (code == 13) {
			if ($(element).attr("name") == "customerName") {
				doSearch();
			}
		}
		if (code == 9) {}
	});

	/*!
	 * process
	 */
	doSearch = function() {
		commonJs.showProcMessageOnElement("divScrollablePanel");

		commonJs.doSearch({
			url:"/ads/0202/getList.do",
			onSuccess:renderDataGrid
		});
	};

	renderDataGrid = function(result) {
		var ds = result.dataSet;
		var html = "";

		$("#tblGridBody").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr();

				var iconAction = new UiIcon();
				iconAction.setId("icnAction").setName("icnAction").addClassName("fa-ellipsis-h fa-lg").addAttribute("quotationId:"+ds.getValue(i, "QUOTATION_ID")).setScript("doAction(this)");
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(iconAction));

				var uiChk = new UiCheckbox();
				uiChk.setName("chkForDel").setValue(ds.getValue(i, "QUOTATION_ID"));
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiChk));

				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(commonJs.getDateTimeMask(ds.getValue(i, "ISSUE_DATE"), dateFormat)));

				var uiAnc = new UiAnchor();
				uiAnc.setText(ds.getValue(i, "QUOTATION_NUMBER")).setScript("getEdit('"+ds.getValue(i, "QUOTATION_ID")+"')");
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiAnc));

				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "NET_AMT"), "#,##0.00")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "GST_AMT"), "#,##0.00")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "TOTAL_AMT"), "#,##0.00")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "DETAIL_CNT"), "#,##0")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "CLIENT_NAME")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "CLIENT_EMAIL")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "DESCRIPTION")));

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:11").setText(com.message.I001));
			html += gridTr.toHtmlString();
		}

		$("#tblGridBody").append($(html));

		$("#tblGrid").freezeHeader({
			attachTo:$("#divDataArea"),
			pagingArea:$("#divPagingArea"),
			isPageable:true,
			totalResultRows:result.totalResultRows,
			script:"doSearch"
		});

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenu.quoteInvoiceAction);
		});

		commonJs.bindToggleTrBackgoundWithCheckbox($("[name=chkForDel]"));
		commonJs.hideProcMessageOnElement("divScrollablePanel");
	};

	doAction = function(img) {
		var quotationId = $(img).attr("quotationId");

		$("input:checkbox[name=chkForDel]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == quotationId) {
				if (!$(this).is(":checked")) {
					$(this).click();
				}
			} else {
				if ($(this).is(":checked")) {
					$(this).click();
				}
			}
		});

		ctxMenu.quoteInvoiceAction[0].fun = function() {getEdit(quotationId);};
		ctxMenu.quoteInvoiceAction[1].fun = function() {getPreview(quotationId);};
		ctxMenu.quoteInvoiceAction[2].fun = function() {doDelete();};

		$(img).contextMenu(ctxMenu.quoteInvoiceAction, {
			classPrefix:com.constants.ctxClassPrefixGrid,
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0,
			verAdjust:2
		});
	};

	getEdit = function(quotationId) {
		popup = commonJs.openPopup({
			popupId:"QuotationEdit",
			url:"/ads/0202/getEdit.do",
			data:{quotationId:quotationId},
			header:"Quotation Edit",
			width:1400,
			height:750
		});
	};

	getPreview = function(quotationId) {
		preview = commonJs.openPopup({
			popupId:"QuotationPreview",
			url:"/ads/0202/getPreview.do",
			data:{quotationId:quotationId},
			header:"Quotation Preview",
			width:800,
			height:750
		});
	};

	doDelete = function() {
		if (commonJs.getCountChecked("chkForDel") == 0) {
			commonJs.warn(com.message.I902);
			return;
		}

		commonJs.doDelete({
			url:"/ads/0202/doDelete.do",
			onSuccess:doSearch
		});
	};
	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		commonJs.setFieldDateMask("fromDate");
		commonJs.setFieldDateMask("toDate");

		doSearch();
	});
});