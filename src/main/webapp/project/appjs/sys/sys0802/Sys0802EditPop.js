/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sys0802EditPop.js
 *************************************************************************************************/
var dateFormat = jsconfig.get("dateFormatJs");
var dateTimeFormat = jsconfig.get("dateTimeFormatJs");
jsconfig.put("scrollablePanelHeightAdjust", 4);

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

	$("#btnAutoGenerate").click(function() {
		if ("disabled" == $(this).attr("disabled")) {
			return;
		}

		commonJs.doSave({
			url:"/sys/0802/doAutoGenerate.do",
			onSuccess:function(result) {
				var ds = result.dataSet;
				$("#btnClose").trigger("click");
			}
		});
	});

	$("#btnClose").click(function(event) {
		parent.popup.close();
		parent.doSearch();
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
		if (!(commonJs.isBlank(periodYear) && commonJs.isBlank(quarterCode))) {
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
		}
	};

	setFinancialPeriod = function(ds) {
		if (ds.getRowCnt() > 0) {
			var financialYear = ds.getValue(0, "FINANCIAL_YEAR").split("-");

			$("#periodYear").val(ds.getValue(0, "PERIOD_YEAR"));
			commonJs.refreshBootstrapSelectbox("periodYear");
			$("#quarterCode").val(ds.getValue(0, "QUARTER_CODE"));
			commonJs.refreshBootstrapSelectbox("quarterCode");
			$("#financialYearFrom").val(financialYear[0]);
			commonJs.refreshBootstrapSelectbox("financialYearFrom");
			$("#financialYearTo").val(financialYear[1]);
			commonJs.refreshBootstrapSelectbox("financialYearTo");
			$("#quarterName").val(ds.getValue(0, "QUARTER_NAME"));
			commonJs.refreshBootstrapSelectbox("quarterName");
			$("#dateFrom").val(commonJs.getDateTimeMask(ds.getValue(0, "DATE_FROM"), dateFormat));
			$("#dateTo").val(commonJs.getDateTimeMask(ds.getValue(0, "DATE_TO"), dateFormat));
			$("#lastUpdatedBy").val(commonJs.nvl(ds.getValue(0, "UPDATE_USER_NAME"), ds.getValue(0, "INSERT_USER_NAME")));
			$("#lastUpdatedDate").val(commonJs.getDateTimeMask(commonJs.nvl(ds.getValue(0, "UPDATE_DATE"), ds.getValue(0, "INSERT_DATE")), dateTimeFormat));
		}

		commonJs.hideProcMessage();
	};

	setStatusForAutoGen = function() {
		if (!(commonJs.isBlank(periodYear) && commonJs.isBlank(quarterCode))) {
			$("#btnAutoGenerate").attr("disabled", true);
			$("#periodYearForAutoGen").attr("disabled", true);
			commonJs.refreshBootstrapSelectbox("periodYearForAutoGen");
			$("#quarterCodeForAutoGen").attr("disabled", true);
			commonJs.refreshBootstrapSelectbox("quarterCodeForAutoGen");
		}
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
		commonJs.setFieldDateMask("dateFrom");
		commonJs.setFieldDateMask("dateTo");
		setStatusForAutoGen();
		loadData();
	});
});