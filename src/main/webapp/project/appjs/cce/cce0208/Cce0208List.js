/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Cce0208List.js
 *************************************************************************************************/
var dateTimeFormat = jsconfig.get("dateTimeFormatJs");
var dateFormat = jsconfig.get("dateFormatJs");
var numberFormat = "#,##0.00";
var popup = null;
var categoryDataSet;

$(function() {
	/*!
	 * event
	 */
	$("#btnNew").click(function(event) {
		getEdit("");
	});

	$("#btnDelete").click(function(event) {
		doDelete();
	});

	$("#btnSearch").click(function(event) {
		doSearch();
	});

	$("#btnClear").click(function(event) {
		commonJs.clearSearchCriteria();
	});

	$("#icnFromDate").click(function(event) {
		commonJs.openCalendar(event, "fromDate");
	});

	$("#icnToDate").click(function(event) {
		commonJs.openCalendar(event, "toDate");
	});

	$("#icnCheck").click(function(event) {
		commonJs.toggleCheckboxes("chkForDel");
	});

	$(document).keydown(function(event) {
		var code = event.keyCode || event.which, element = event.target;
		if (code == 13) {}
		if (code == 9) {}
	});

	$(document).keyup(function(event) {
		var code = event.keyCode || event.which, element = event.target;
		if (code == 9) {}
		if (code == 13) {
			onEditAmts($(element));
		}
	});

	/*!
	 * process
	 */
	getEdit = function(cashExpenseId) {
		popup = commonJs.openPopup({
			popupId:"EditCashExpense",
			url:"/cce/0208/getEdit.do",
			data:{cashExpenseId:cashExpenseId},
			header:"New / Edit Cash Expense",
			width:450,
			height:450
		});
	};

	doSearch = function() {
		commonJs.showProcMessageOnElement("divScrollablePanel");

		commonJs.doSearch({
			url:"/cce/0208/getList.do",
			onSuccess:renderDataGrid
		});
	};

	renderDataGrid = function(result) {
		var ds = result.dataSet, html = "";
		var totGrossAmt = 0, totGstAmt = 0, totNetAmt = 0;

		$("#tblGridBody").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr();
				var cashExpenseId = ds.getValue(i, "CASH_EXPENSE_ID")+"";
				var grossAmt = ds.getValue(i, "PROC_AMT");
				var gstAmt = ds.getValue(i, "GST_AMT");
				var netAmt = ds.getValue(i, "NET_AMT");

				var grossAmtFmt = commonJs.getNumberMask(grossAmt, numberFormat);
				var gstAmtFmt = commonJs.getNumberMask(gstAmt, numberFormat);
				var netAmtFmt = commonJs.getNumberMask(netAmt, numberFormat);

				var categorySelectboxString = getCategorySelectbox({
					id:"selCategory_"+i,
					name:"selCategory_"+i,
					selectedValue:ds.getValue(i, "SUB_CATEGORY")+"",
					cashExpenseId:ds.getValue(i, "CASH_EXPENSE_ID")+""
				});
				var grossAmtBox = commonJs.getUiTextbox({id:"txtGrossAmt_"+i, name:"txtGrossAmt_"+i, className:"Rt", value:grossAmtFmt, attribute:"cashExpenseId:"+cashExpenseId});
				var gstAmtBox = commonJs.getUiTextbox({id:"txtGstAmt_"+i, name:"txtGstAmt_"+i, className:"Rt", value:gstAmtFmt, attribute:"cashExpenseId:"+cashExpenseId});
				var netAmtBox = commonJs.getUiTextbox({id:"txtNetAmt_"+i, name:"txtNetAmt_"+i, className:"Rt", value:netAmtFmt, status:"display", attribute:"cashExpenseId:"+cashExpenseId});
				var descBox = commonJs.getUiTextbox({id:"txtDesc_"+i, name:"txtDesc_"+i, className:"Lt", value:ds.getValue(i, "PROC_DESCRIPTION"), attribute:"cashExpenseId:"+cashExpenseId});

				var iconAction = new UiIcon();
				iconAction.setId("icnAction").setName("icnAction").addClassName("fa-ellipsis-h fa-lg").addAttribute("cashExpenseId:"+ds.getValue(i, "CASH_EXPENSE_ID")).setScript("doAction(this)");
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(iconAction));

				var uiChk = new UiCheckbox();
				uiChk.setName("chkForDel").setValue(ds.getValue(i, "CASH_EXPENSE_ID"));
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiChk));

				var uiAnc = new UiAnchor();
				uiAnc.setText(ds.getValue(i, "PROC_DATE_FORMAT")).setScript("getEdit('"+ds.getValue(i, "CASH_EXPENSE_ID")+"')");
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiAnc));

				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(categorySelectboxString));
//				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "CATEGORY_MEANING")));
//				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "PROC_AMT"), numberFormat)));
//				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "GST_AMT"), numberFormat)));
//				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "NET_AMT"), numberFormat)));
//				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "PROC_DESCRIPTION")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(grossAmtBox));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(gstAmtBox));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(netAmtBox));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(descBox));

				gridTr.setClassName("noStripe");

				totGrossAmt += commonJs.toNumber(grossAmt);
				totGstAmt += commonJs.toNumber(gstAmt);
				totNetAmt += commonJs.toNumber(netAmt);

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:8").setText(com.message.I001));
			html += gridTr.toHtmlString();
		}

		$("#tblGridBody").append($(html));
		setGridTable(result.totalResultRows);

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenu.commonSimpleAction);
		});

		commonJs.bindToggleTrBackgroundWithCheckbox($("[name=chkForDel]"));
		commonJs.hideProcMessageOnElement("divScrollablePanel");

		$("#totGrossAmt").val(totGrossAmt);
		$("#totGstAmt").val(totGstAmt);
		$("#totNetAmt").val(totNetAmt);

		$("#totGrossAmt").number(true, 2);
		$("#totGstAmt").number(true, 2);
		$("#totNetAmt").number(true, 2);

		initElements();
	};

	setGridTable = function(totalResultRows) {
		$("#tblGrid").freezeHeader({
			attachTo:$("#divDataArea"),
			pagingArea:$("#divPagingArea"),
			isPageable:true,
			totalResultRows:totalResultRows,
			script:"doSearch"
		});
	};

	initCategoryDataSet = () => {
		commonJs.doSearch({
			url:"/cce/0208/getReconCategoryDataSet.do",
			onSuccess:(result) => {
				categoryDataSet = result.dataSet;
			}
		});
	};

	doAction = function(img) {
		var cashExpenseId = $(img).attr("cashExpenseId");

		$("input:checkbox[name=chkForDel]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == cashExpenseId) {
				if (!$(this).is(":checked")) {
					$(this).click();
				}
			} else {
				if ($(this).is(":checked")) {
					$(this).click();
				}
			}
		});

		ctxMenu.commonSimpleAction[0].fun = function() {getEdit(cashExpenseId);};
		ctxMenu.commonSimpleAction[1].fun = function() {doDelete();};

		$(img).contextMenu(ctxMenu.commonSimpleAction, {
			classPrefix:com.constants.ctxClassPrefixGrid,
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0,
			verAdjust:2
		});
	};

	doDelete = function() {
		if (commonJs.getCountChecked("chkForDel") == 0) {
			commonJs.warn(com.message.I902);
			return;
		}

		commonJs.doDelete({
			url:"/cce/0208/doDelete.do",
			onSuccess:doSearch
		});
	};

	getCategorySelectbox = (params) => {
		var html = "<select id=\""+params.id+"\" name=\""+params.name+"\" cashExpenseId=\""+params.cashExpenseId+"\" class=\"bootstrapSelect\" data-width=\"100%\">";

		html += "<option value=\"\">==Select==</option>";

		for (var i=0; i<categoryDataSet.getRowCnt(); i++) {
			var selected = "";

			if (categoryDataSet.getValue(i, "CATEGORY_ID") == params.selectedValue) {selected = " selected";}

			html += "<option value=\""+categoryDataSet.getValue(i, "CATEGORY_ID")+"\""+selected+">"+categoryDataSet.getValue(i, "CATEGORY_NAME")+"</option>";
		}

		html += "</select>";

		return html;
	};

	initElements = () => {
		var options = {};
		$("#divDataArea").find(".bootstrapSelect").each(function(index) {
			options.container = "body";
			options.style = $(this).attr("class");
			$(this).selectpicker(options);

			commonJs.setEvent("change", [$(this)], onEditAmts);
		});

		$("#divDataArea").find(".txtEn").each((index, obj) => {
			commonJs.setEvent("blur", [$(obj)], onEditAmts);
		});
	};

	onEditAmts = function(jqObj) {
		var name = $(jqObj).attr("name");
		var index = name.split("_")[1];
		var cashExpenseId = $(jqObj).attr("cashExpenseId");
		var categoryId = $("#selCategory_"+index).val();
		var grossAmt = commonJs.toNumber($("#txtGrossAmt_"+index).val());
		var gstAmt = commonJs.toNumber($("#txtGstAmt_"+index).val());
		var desc = $("#txtDesc_"+index).val();

		commonJs.doSimpleProcess({
			url:"/cce/0208/doSaveOnEdit.do",
			data:{
				cashExpenseId:commonJs.nvl(cashExpenseId, ""),
				categoryId:categoryId,
				grossAmt:grossAmt,
				gstAmt:gstAmt,
				desc:desc
			},
			noForm:true,
			onSuccess:function(result) {
				var ds = result.dataSet;

				$("#txtNetAmt_"+index).val(ds.getValue(0, "netAmt"));

				setTotAmt();
			}
		});

		$("#txtGrossAmt_"+index).number(true, 2);
		$("#txtGstAmt_"+index).number(true, 2);
		$("#txtNetAmt_"+index).number(true, 2);
	};

	setTotAmt = () => {
		var grossAmt = 0, gstAmt = 0;

		$("#tblGridBody").find(".txtEn").each((index, obj) => {
			if (commonJs.containsIgnoreCase($(obj).attr("id"), "txtGrossAmt")) {
				grossAmt += commonJs.toNumber($(obj).val());
			} else if (commonJs.containsIgnoreCase($(obj).attr("id"), "txtGstAmt")) {
				gstAmt += commonJs.toNumber($(obj).val());
			}
		});

		$("#totGrossAmt").val(grossAmt);
		$("#totGstAmt").val(gstAmt);
		$("#totNetAmt").val(grossAmt - gstAmt);

		$("#totGrossAmt").number(true, 2);
		$("#totGstAmt").number(true, 2);
		$("#totNetAmt").number(true, 2);
	};
	/*!
	 * load event (document / window)
	 */
	$(document).click(function(event) {
		var obj = event.target;

		if ($(obj).is($("input:text")) && $(obj).hasClass("txtEn")) {
			$(obj).select();
		}
	});

	$(window).load(function() {
		commonJs.setFieldDateMask("fromDate");
		commonJs.setFieldDateMask("toDate");
		$(".numeric").number(true, 2);
		initCategoryDataSet();

		setTimeout(function() {
			doSearch();
		}, 400);
	});
});