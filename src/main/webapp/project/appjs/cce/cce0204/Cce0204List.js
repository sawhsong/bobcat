/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Cce0204List.js
 *************************************************************************************************/
var dateTimeFormat = jsconfig.get("dateTimeFormatJs");
var dateFormat = jsconfig.get("dateFormatJs");
var numberFormat = "#,##0.00";
var popup;
var categoryDataSet;

$(function() {
	/*!
	 * event
	 */
	$("#btnSearch").click(function(event) {
		doSearch();
	});

	$("#btnClear").click(function(event) {
		commonJs.clearSearchCriteria();
	});

	$("#bankAccntId").change(function(event) {
		doSearch();
	});

	$("#allocationStatus").change(function(event) {
		doSearch();
	});

	$("#icnTransactionDateFrom").click(function(event) {
		commonJs.openCalendar(event, "transactionDateFrom");
	});

	$("#icnTransactionDateTo").click(function(event) {
		commonJs.openCalendar(event, "transactionDateTo");
	});

	$("#icnUpdatedDateFrom").click(function(event) {
		commonJs.openCalendar(event, "updatedDateFrom");
	});

	$("#icnUpdatedDateTo").click(function(event) {
		commonJs.openCalendar(event, "updatedDateTo");
	});

	$("#icnCheck").click(function(event) {
		commonJs.toggleCheckboxes("chkForEdit");
	});

	$("#btnBatch").click(function() {
		if (commonJs.getCountChecked("chkForEdit") == 0) {
			commonJs.warn(com.message.I902);
			return;
		}

		popup = commonJs.openPopup({
			popupId:"BatchApplication",
			url:"/cce/0204/getBatchApplication.do",
			data:{},
			header:"Batch Application",
			width:840,
			height:400
		});
	});

	$(document).keyup(function(event) {
		var code = event.keyCode || event.which, element = event.target;
		if (code == 9) {}
		if (code == 13) {
			onEditGstAmt($(element));
		}
	});

	/*!
	 * process
	 */
	initElements = () => {
		var options = {};
		$("#divDataArea").find(".bootstrapSelect").each(function(index) {
			options.container = "body";
			options.style = $(this).attr("class");
			$(this).selectpicker(options);

			commonJs.setEvent("change", [$(this)], onEditGstAmt);
		});

		$("#divDataArea").find(".txtEn").each((index, obj) => {
			commonJs.setEvent("blur", [$(obj)], onEditGstAmt);
		});
	};

	onEditGstAmt = function(jqObj) {
		var name = $(jqObj).attr("name");
		var index = name.split("_")[1];
		var ccAllocId = $(jqObj).attr("ccAllocId");
		var categoryId = $("#selCategory_"+index).val();
		var procAmt = $("#txtGstAmt_"+index).attr("procAmt");
		var gstAmt = $("#txtGstAmt_"+index).val();

		commonJs.doSimpleProcess({
			url:"/cce/0204/doSave.do",
			data:{
				ccAllocId:commonJs.nvl(ccAllocId, ""),
				categoryId:categoryId,
				procAmt:procAmt,
				gstAmt:gstAmt
			},
			noForm:true,
			onSuccess:function(result) {
				var ds = result.dataSet;

				$("#txtNetAmt_"+index).val(ds.getValue(0, "netAmt"));
			}
		});

		$("#txtGstAmt_"+index).number(true, 2);
		$("#txtNetAmt_"+index).number(true, 2);
	};

	initCategoryDataSet = () => {
		commonJs.doSearch({
			url:"/cce/0204/getReconCategoryDataSet.do",
			onSuccess:(result) => {
				categoryDataSet = result.dataSet;
			}
		});
	};

	getCategorySelectbox = (params) => {
		var html = "<select id=\""+params.id+"\" name=\""+params.name+"\" ccAllocId=\""+params.ccAllocId+"\" class=\"bootstrapSelect\" data-width=\"100%\">";

		html += "<option value=\"\">==Select==</option>";

		for (var i=0; i<categoryDataSet.getRowCnt(); i++) {
			var level = categoryDataSet.getValue(i, "CATEGORY_LEVEL");
			var selected = "";

			if (categoryDataSet.getValue(i, "PATH") == params.selectedValue) {selected = " selected";}

			if (level == "1") {
				if (i != 0) {
					html += "</optgroup>";
				}
				html += "<optgroup label=\""+categoryDataSet.getValue(i, "CATEGORY_NAME")+"\">";
			} else {
				html += "<option value=\""+categoryDataSet.getValue(i, "PATH")+"\""+selected+">"+categoryDataSet.getValue(i, "CATEGORY_NAME")+"</option>";
			}
		}

		html += "</select>";

		return html;
	};

	getGstAmtTextbox = (params) => {
		var html = "<input type=\"text\" id=\""+params.id+"\" name=\""+params.name+"\" ccAllocId=\""+params.ccAllocId+"\" class=\"txtEn Rt numeric\" option=\"numeric\" ";
		html += "value=\""+commonJs.getNumberMask(params.value, "#,##0.00")+"\" ";
		html += "procAmt=\""+params.procAmt+"\"";
		html += "/>";
		return html;
	};

	getNetAmtTextbox = (params) => {
		var html = "<input type=\"text\" id=\""+params.id+"\" name=\""+params.name+"\" class=\"txtDpl Rt numeric\" option=\"numeric\" readonly ";
		html += "value=\""+commonJs.getNumberMask(params.value, "#,##0.00")+"\" ";
		html += "/>";
		return html;
	};

	setBankAccntId = function() {
		if (!commonJs.isBlank(sbaId)) {
			$("#bankAccntId").val(sbaId);
			commonJs.refreshBootstrapSelectbox("bankAccntId");
		}
	};

	doSearch = function() {
		commonJs.showProcMessageOnElement("divScrollablePanel");

		commonJs.doSearch({
			url:"/cce/0204/getList.do",
			onSuccess:renderGridData
		});
	};

	renderGridData = function(result) {
		var ds = result.dataSet, html = "";

		$("#tblGridBody").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr();
				var categorySelectboxString = getCategorySelectbox({
					id:"selCategory_"+i,
					name:"selCategory_"+i,
					selectedValue:ds.getValue(i, "MAIN_CATEGORY")+"-"+ds.getValue(i, "SUB_CATEGORY")+"",
					ccAllocId:ds.getValue(i, "CC_ALLOC_ID")+""
				});
				var gstBoxString = getGstAmtTextbox({
					id:"txtGstAmt_"+i,
					name:"txtGstAmt_"+i,
					value:ds.getValue(i, "GST_AMT"),
					ccAllocId:ds.getValue(i, "CC_ALLOC_ID")+"",
					procAmt:ds.getValue(i, "PROC_AMT")
				});
				var netAmtBoxString = getNetAmtTextbox({
					id:"txtNetAmt_"+i,
					name:"txtNetAmt_"+i,
					value:ds.getValue(i, "NET_AMT")
				});
				var debitAmt = commonJs.isNotBlank(ds.getValue(i, "DEBIT_AMT")) ? commonJs.getNumberMask(ds.getValue(i, "DEBIT_AMT"), "#,##0.00") : "";
				var creditAmt = commonJs.isNotBlank(ds.getValue(i, "CREDIT_AMT")) ? commonJs.getNumberMask(ds.getValue(i, "CREDIT_AMT"), "#,##0.00") : "";

				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(new UiCheckbox().setName("chkForEdit").setValue(ds.getValue(i, "CC_ALLOC_ID"))));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(commonJs.getDateTimeMask(ds.getValue(i, "PROC_DATE"), dateFormat)));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(categorySelectboxString));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(debitAmt));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(creditAmt));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(gstBoxString));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(netAmtBoxString));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "PROC_DESCRIPTION")));

				gridTr.setClassName("noStripe");

				if (commonJs.isNotBlank(ds.getValue(i, "MAIN_CATEGORY"))) {
					gridTr.addClassName("success");
				}

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:8").setText(com.message.I001));
			html += gridTr.toHtmlString();
		}

		$("#tblGridBody").append($(html));
		setGridTable(result.totalResultRows);

		commonJs.bindToggleTrBackgoundWithCheckbox($("[name=chkForEdit]"));
		commonJs.hideProcMessageOnElement("divScrollablePanel");

		initElements();
	};

	setGridTable = function(totalResultRows) {
		$("#tblGrid").fixedHeaderTable({
			attachTo:$("#divDataArea"),
			pagingArea:$("#divPagingArea"),
			isPageable:true,
			totalResultRows:totalResultRows,
			script:"doSearch"
		});
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
		commonJs.setFieldDateMask("transactionDateFrom");
		commonJs.setFieldDateMask("transactionDateTo");
		commonJs.setFieldDateMask("updatedDateFrom");
		commonJs.setFieldDateMask("updatedDateTo");
		$(".numeric").number(true, 2);
		initCategoryDataSet();

		setTimeout(function() {
			setBankAccntId();
			doSearch();
		}, 200);
	});
});