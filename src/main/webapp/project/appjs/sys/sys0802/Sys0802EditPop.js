/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sys0802EditPop.js
 *************************************************************************************************/
var dateTimeFormat = jsconfig.get("dateTimeFormatJs");

$(function() {
	/*!
	 * event
	 */
	$("#btnSave").click(function(event) {
		if ("disabled" == $(this).attr("disabled")) {
			return;
		}

		if (commonJs.doValidate("fmDefault")) {
			commonJs.doSave({
				url:"/sys/0802/doSave.do",
				onSuccess:function(result) {
					var ds = result.dataSet;

					commonJs.showProcMessage(com.message.loading);
					setFinancialPeriod(ds);
				}
			});
		}
	});

	$("#btnClose").click(function(event) {
		parent.popup.close();
	});

	$(document).keydown(function(event) {
		var code = event.keyCode || event.which, element = event.target;
		if (code == 13) {}
		if (code == 9) {}
	});

	/*!
	 * process
	 */
	loadData = function() {
		commonJs.showProcMessage(com.message.loading);

		setTimeout(function() {
			commonJs.doSimpleProcess({
				url:"/sys/0802/getFinancialPeriod.do",
				noForm:true,
				data:{
					periodYear:periodYear,
					quarterCode:quarterCode
				},
				onSuccess:function(result) {
					var ds = result.dataSet;
					setFinancialPeriod(ds);
				}
			});
		}, 400);
	};

	setFinancialPeriod = function(ds) {
		if (ds.getRowCnt() > 0) {
			var financialYear = ds.getValue(i, "FINANCIAL_YEAR").split("-");

			$("#periodYear").val(ds.getValue(i, "PERIOD_YEAR"));
			commonJs.refreshBootstrapSelectbox("periodYear");
			$("#quarterCode").val(ds.getValue(i, "QUARTER_CODE"));
			commonJs.refreshBootstrapSelectbox("quarterCode");
			$("#financialYearFrom").val(financialYear[0]);
			commonJs.refreshBootstrapSelectbox("financialYearFrom");
			$("#financialYearTo").val(financialYear[1]);
			commonJs.refreshBootstrapSelectbox("financialYearTo");
			$("#quarterName").val(ds.getValue(i, "QUARTER_NAME"));
			commonJs.refreshBootstrapSelectbox("quarterName");
			$("#dateFrom").val(ds.getValue(0, "DATE_FROM"));
			$("#dateTo").val(ds.getValue(0, "DATE_TO"));
			$("#lastUpdatedBy").val(commonJs.nvl(ds.getValue(0, "UPDATE_USER_NAME"), ds.getValue(0, "INSERT_USER_NAME")));
			$("#lastUpdatedDate").val(commonJs.getDateTimeMask(commonJs.nvl(ds.getValue(0, "UPDATE_DATE"), ds.getValue(0, "INSERT_DATE")), dateTimeFormat));
		}

		commonJs.hideProcMessage();
	};
	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		commonJs.setFieldDateMask("dateFrom");
		commonJs.setFieldDateMask("dateTo");
		loadData();
	});
});