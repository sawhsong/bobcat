/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Pro0202List.js
 *************************************************************************************************/
var numberFormat = "#,##0.00";
var dateTimeFormat = jsconfig.get("dateTimeFormatJs");

$(function() {
	/*!
	 * event
	 */
	$("#btnSearch").click(function(event) {
		doSearch();
	});

	$("#btnSave").click(function(event) {
		doSave();
	});

	$("#financialYear").change(function() {
		doSearch();
	});

	$(document).keydown(function(event) {
		var code = event.keyCode || event.which, element = event.target;
		if (code == 13) {}
		if (code == 9) {}
	});

	/*!
	 * process
	 */
	doSave = function() {
		if (!commonJs.doValidate("fmDefault")) {
			return;
		}

		commonJs.doSave({
			url:"/pro/0202/doSave.do",
			onSuccess:function(result) {
				var ds = result.dataSet;
				doSearch();
			}
		});
	};

	doSearch = function() {
		commonJs.showProcMessageOnElement("divPayrollSummary");

		commonJs.doSearch({
			url:"/pro/0202/getList.do",
			onSuccess:renderGridData
		});
	};

	renderGridData = function(result) {
		var ds = result.dataSet, html = "";

		$("#tblGridBody").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr();

				var noOfEmployee = commonJs.getNumberMask(ds.getValue(i, "NUMBER_OF_EMPLOYEE"), numberFormat);
				var grossPay = commonJs.getNumberMask(ds.getValue(i, "GROSS_PAY_AMT"), numberFormat);
				var tax = commonJs.getNumberMask(ds.getValue(i, "TAX"), numberFormat);
				var netPay = commonJs.getNumberMask(ds.getValue(i, "NET_PAY_AMT"), numberFormat);
				var superAmt = commonJs.getNumberMask(ds.getValue(i, "SUPER_AMT"), numberFormat);
				var updateDate = commonJs.getDateTimeMask(commonJs.nvl(ds.getValue(i, "UPDATE_DATE"), ds.getValue(i, "INSERT_DATE")), dateTimeFormat);

				if (i != ds.getRowCnt()-1) {
					var monthNumBox = commonJs.getUiHidden({id:"txtMonthNum_"+i, name:"txtMonthNum_"+i, value:ds.getValue(i, "MONTH_NUM")});
					var noOfEmployeeBox = commonJs.getUiTextbox({id:"txtNoOfEmployee_"+i, name:"txtNoOfEmployee_"+i, className:"Rt", value:noOfEmployee});
					var grossPayBox = commonJs.getUiTextbox({id:"txtGrossPayAmt_"+i, name:"txtGrossPayAmt_"+i, className:"Rt", value:grossPay});
					var taxBox = commonJs.getUiTextbox({id:"txtTax_"+i, name:"txtTax_"+i, className:"Rt", value:tax});
					var netPayBox = commonJs.getUiTextbox({id:"txtNetPayAmt_"+i, name:"txtNetPayAmt_"+i, className:"Rt", value:netPay});
					var superAmtBox = commonJs.getUiTextbox({id:"txtSuperAmt_"+i, name:"txtSuperAmt_"+i, className:"Rt", value:superAmt});

					gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "MONTH_ALPHA")).addTextBeforeChild(monthNumBox));
					gridTr.addChild(new UiGridTd().addClassName("Ct").setText(noOfEmployeeBox));
					gridTr.addChild(new UiGridTd().addClassName("Ct").setText(grossPayBox));
					gridTr.addChild(new UiGridTd().addClassName("Ct").setText(taxBox));
					gridTr.addChild(new UiGridTd().addClassName("Ct").setText(netPayBox));
					gridTr.addChild(new UiGridTd().addClassName("Ct").setText(superAmtBox));
					gridTr.addChild(new UiGridTd().addClassName("Ct").setText(updateDate));
				} else {
					var totNoOfEmployeeBox = commonJs.getUiTextbox({id:"txtNoOfEmployeeTot", name:"txtNoOfEmployeeTot", className:"Rt", value:noOfEmployee, status:"display"});
					var totGrossPayBox = commonJs.getUiTextbox({id:"txtGrossPayAmtTot", name:"txtGrossPayAmtTot", className:"Rt", value:grossPay, status:"display"});
					var totTaxBox = commonJs.getUiTextbox({id:"txtTaxTot", name:"txtTaxTot", className:"Rt", value:tax, status:"display"});
					var totNetPayBox = commonJs.getUiTextbox({id:"txtNetPayAmtTot", name:"txtNetPayAmtTot", className:"Rt", value:netPay, status:"display"});
					var totSuperAmtBox = commonJs.getUiTextbox({id:"txtSuperAmtTot", name:"txtSuperAmtTot", className:"Rt", value:superAmt, status:"display"});

					gridTr.addChild(new UiGridTd().addClassName("Ct tdTotal").setText(ds.getValue(i, "MONTH_ALPHA")));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(totNoOfEmployeeBox));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(totGrossPayBox));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(totTaxBox));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(totNetPayBox));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(totSuperAmtBox));
					gridTr.addChild(new UiGridTd().addClassName("Ct").setText(updateDate));

					gridTr.setStyle("font-weight:bold;border-top:2px solid #cccccc;border-bottom:2px solid #cccccc;background:#f5f5f5;");
				}

				gridTr.setClassName("noStripe");

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:7").setText(com.message.I001));
			html += gridTr.toHtmlString();
		}

		$("#tblGridBody").append($(html));

		commonJs.hideProcMessageOnElement("divPayrollSummary");

		initElements();
	};

	initElements = () => {
		var options = {};
		$("#divDataArea").find(".txtEn").each((index, obj) => {
			commonJs.setEvent("blur", [$(obj)], onEditText);
		});
	};

	onEditText = function(jqObj) {
		var objName = $(jqObj).attr("name");
		var name = objName.split("_")[0];
		var index = objName.split("_")[1];
		var tot = 0;

		for (var i=0; i<12; i++) {
			tot += commonJs.toNumber($("#"+name+"_"+i).val());
		}
		$("#"+name+"Tot").val(commonJs.getNumberMask(tot, numberFormat));

		if (commonJs.isBlank($(jqObj).val())) {
			$(jqObj).val("0.00");
		}

		$(jqObj).number(true, 2);
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
		doSearch();
	});
});