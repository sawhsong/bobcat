/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Rkm0202List.js
 *************************************************************************************************/
jsconfig.put("useJqTooltip", false);
jsconfig.put("scrollablePanelHeightAdjust", 6);
var searchResultDataCount = 0;
var numberFormat = "#,##0.00";

$(function() {
	/*!
	 * event
	 */
	$("#btnComplete").click(function(event) {
		doComplete();
	});

	$("#btnDelete").click(function(event) {
		doDelete();
	});

	$("#btnSearch").click(function(event) {
		doSearch();
	});

	$("#btnClear").click(function(event) {
		commonJs.clearSearchCriteria();
		refreshDataEntry();
	});

	$("#icnCheck").click(function(event) {
		commonJs.toggleCheckboxes("chkForDel");
	});

	$("#icnDataEntryDate").click(function(event) {
		commonJs.openCalendar(event, "deDate");
	});

	$("#financialYear").change(function() {
		doSearch();
	});

	$("#quarterName").change(function() {
		doSearch();
	});

	$("#recordKeepingType").change(function() {
		doSearch();
	});

	$(document).keyup(function(event) {
		var element = event.target;
		if (event.which == 13) {
			if ($(element).attr("name") == "deRemark") {
				doSave();
			}
		}

		onEditDataEntry($(element));
	});

	$("input:text").focus(function() {
		if ($(this).hasClass("txtEn")) {
			$(this).select();
		}
	});

	/*!
	 * context menus
	 */
	setDataEntryActionButtonContextMenu = function() {
		ctxMenu.dataEntryAction[0].fun = function() {};
		ctxMenu.dataEntryAction[1].fun = function() {};
		ctxMenu.dataEntryAction[2].fun = function() {};

		$("#icnDataEntryAction").contextMenu(ctxMenu.dataEntryAction, {
			classPrefix:com.constants.ctxClassPrefixGrid,
			displayAround:"trigger",
			position:"bottom"
		});
	};

	/*!
	 * process
	 */
	doSearch = function() {
		commonJs.showProcMessageOnElement("divScrollablePanel");

		refreshDataEntry();

		commonJs.doSearch({
			url:"/rkm/0202/getList",
			callback:renderDataGridTable
		});

		setSummaryDataForAdminTool();
	};

	renderDataGridTable = function(result) {
		var ds = result.dataSet;
		var html = "", totHtml = "";
		var totNonCash = 0, totCash = 0, totGross = 0, totGstFree = 0, totGst = 0, totNet = 0;
		var totGridTr = new UiGridTr();

		searchResultDataCount = ds.getRowCnt();
		$("#tblGridBody").html("");
		$("#tblGridFoot").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr(),
					keyVal = ds.getValue(i, "INCOME_ID")+"_"+ds.getValue(i, "QUARTER_DATE");

				totNonCash += parseFloat(ds.getValue(i, "NON_CASH_AMT"));
				totCash += parseFloat(ds.getValue(i, "CASH_AMT"));
				totGross += parseFloat(ds.getValue(i, "GROSS_AMT"));
				totGstFree += parseFloat(ds.getValue(i, "GST_FREE_AMT"));
				totGst += parseFloat(ds.getValue(i, "GST_AMT"));
				totNet += parseFloat(ds.getValue(i, "NET_AMT"));

				var uiChk = new UiCheckbox();
				uiChk.setId("chkForDel").setName("chkForDel").setValue(keyVal);
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiChk));

				var uiAnc = new UiAnchor();
				uiAnc.setText(ds.getValue(i, "QUARTER_DATE")).setScript("getEdit('"+keyVal+"')");
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiAnc));

				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "NON_CASH_AMT"), numberFormat)));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "CASH_AMT"), numberFormat)));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "GROSS_AMT"), numberFormat)));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "GST_FREE_AMT"), numberFormat)));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "GST_AMT"), numberFormat)));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "NET_AMT"), numberFormat)));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "RECORD_KEEPING_TYPE_DESC")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "IS_COMPLETED")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(commonJs.abbreviate(ds.getValue(i, "DESCRIPTION"), 40)));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "INSERT_DATE")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "UPDATE_DATE")));

				var iconAction = new UiIcon();
				iconAction.setId("icnAction").setName("icnAction").addClassName("fa-tasks fa-lg").addAttribute("incomeIdDate:"+keyVal).setScript("doAction(this)");
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(iconAction));

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:14").setText(com.message.I001));
			html += gridTr.toHtmlString();
		}

		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Ct").setText(com.caption.total));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(totNonCash, numberFormat)));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(totCash, numberFormat)));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(totGross, numberFormat)));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(totGstFree, numberFormat)));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(totGst, numberFormat)));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(totNet, numberFormat)));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));

		totHtml = totGridTr.toHtmlString();

		$("#tblGridBody").append($(html));
		$("#tblGridFoot").append($(totHtml));

		$("#tblGrid").fixedHeaderTable({
			attachTo:$("#divDataArea"),
			pagingArea:$("#divPagingArea"),
			isPageable:false,
			isFilter:false,
			filterColumn:[],
			totalResultRows:result.totalResultRows,
			script:"doSearch"
		});

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenu.dataEntryListAction);
		});

		commonJs.hideProcMessageOnElement("divScrollablePanel");
	};

	onEditDataEntry = function(jqObj) {
		var name = $(jqObj).attr("name");
		if (name == "deNonCash" || name == "deCash" || name == "deGstFree") {
			commonJs.doSimpleProcessNoForm({
				url:"/rkm/0202/calculateDataEntry",
				data:{
					nonCash:$("#deNonCash").val(),
					cash:$("#deCash").val(),
					gstFree:$("#deGstFree").val()
				},
				callback:editDataEntryCallback
			});
		}
	};

	editDataEntryCallback = function(result) {
		var ds = result.dataSet;
		$("#deGrossSales").val(ds.getValue(0, "grossSales"));
		$("#deGst").val(ds.getValue(0, "gst"));
		$("#deNetSales").val(ds.getValue(0, "netSales"));
	};

	getEdit = function(incomeIdDate) {
		$("input:checkbox[name=chkForDel]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == incomeIdDate) {
				$(this).prop("checked", true);
			} else {
				$(this).prop("checked", false);
			}
		});

		commonJs.doSimpleProcess({
			url:"/rkm/0202/getEdit",
			data:{incomeIdDate:incomeIdDate},
			callback:editCallback
		});
	};

	editCallback = function(result) {
		var ds = result.dataSet;

		refreshDataEntry();
		setDataEntryValues(ds);
		$("#deNonCash").focus();
	};

	doSave = function() {
		if (!commonJs.doValidate("fmDefault")) {
			return;
		}

		commonJs.doSave({
			url:"/rkm/0202/exeSave",
			showPostMessage:true,
			callback:doSearch
		});
	};

	doComplete = function() {
		if (commonJs.getCountChecked("chkForDel") == 0) {
			commonJs.warn(com.message.I902);
			return;
		}

		commonJs.doProcess({
			url:"/rkm/0202/exeComplete",
			confirmMessage:com.message.Q004,
			showPostMessage:true,
			callback:doSearch
		});
	};

	doDelete = function() {
		if (commonJs.getCountChecked("chkForDel") == 0) {
			commonJs.warn(com.message.I902);
			return;
		}

		commonJs.doSave({
			url:"/rkm/0202/exeDelete",
			showPostMessage:true,
			callback:doSearch
		});
	};

	doAction = function(img) {
		var incomeIdDate = $(img).attr("incomeIdDate");

		$("input:checkbox[name=chkForDel]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == incomeIdDate) {
				$(this).prop("checked", true);
			} else {
				$(this).prop("checked", false);
			}
		});

		ctxMenu.dataEntryListAction[0].fun = function() {getEdit(incomeIdDate);};
		ctxMenu.dataEntryListAction[1].fun = function() {doDelete();};
		ctxMenu.dataEntryListAction[2].fun = function() {doComplete();};

		$(img).contextMenu(ctxMenu.dataEntryListAction, {
			classPrefix:com.constants.ctxClassPrefixGrid,
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0,
			verAdjust:2
		});
	};

	doDataEntryAction = function(img) {
		ctxMenu.dataEntryAction[0].fun = function() {doSave();};
		ctxMenu.dataEntryAction[1].fun = function() {refreshDataEntry();};
		ctxMenu.dataEntryAction[2].fun = function() {doDelete();};

		$(img).contextMenu(ctxMenu.dataEntryAction, {
			classPrefix:com.constants.ctxClassPrefixGrid,
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0,
			verAdjust:2
		});
	};

	refreshDataEntry = function() {
		$("#divInformArea").find(":input").each(function() {
			if ($(this).prop("type") == "checkbox" || $(this).prop("type") == "radio") {
				$(this).attr("checked", false);
			} else {
				$(this).val("");
			}
		});

		commonJs.refreshBootstrapSelectbox("deRecordKeepingType");
	};

	setDataEntryValues = function(dataSet) {
		$("#deIncomeId").val(commonJs.nvl(dataSet.getValue(0, "INCOME_ID"), ""));
		$("#deDate").val(commonJs.nvl(dataSet.getValue(0, "INCOME_DATE"), ""));
		$("#deNonCash").val(commonJs.getNumberMask(dataSet.getValue(0, "NON_CASH_AMT"), numberFormat));
		$("#deCash").val(commonJs.getNumberMask(dataSet.getValue(0, "CASH_AMT"), numberFormat));
		$("#deGrossSales").val(commonJs.getNumberMask(dataSet.getValue(0, "GROSS_AMT"), numberFormat));
		$("#deGstFree").val(commonJs.getNumberMask(dataSet.getValue(0, "GST_FREE_AMT"), numberFormat));
		$("#deGst").val(commonJs.getNumberMask(dataSet.getValue(0, "GST_AMT"), numberFormat));
		$("#deNetSales").val(commonJs.getNumberMask(dataSet.getValue(0, "NET_AMT"), numberFormat));
		$("#deRecordKeepingType").val(commonJs.nvl(dataSet.getValue(0, "RECORD_KEEPING_TYPE"), ""));
		commonJs.refreshBootstrapSelectbox("deRecordKeepingType");
		$("#deRemark").val(commonJs.nvl(dataSet.getValue(0, "DESCRIPTION"), ""));
	};

	exeExport = function(menuObject) {
		if (searchResultDataCount <= 0) {
			commonJs.warn(com.message.I001);
			return;
		}

		commonJs.doExport({
			url:"/rkm/0202/exeExport",
			data:commonJs.serialiseObject($("#divSearchCriteriaArea")),
			menuObject:menuObject
		});
	};
	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		commonJs.setExportButtonContextMenu($("#btnExport"));
		setDataEntryActionButtonContextMenu();
		commonJs.setFieldDateMask("deDate");
		$(".numeric").number(true, 2);
		doSearch();
	});
});