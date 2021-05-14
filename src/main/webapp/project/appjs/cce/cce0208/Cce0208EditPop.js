/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Cce0208EditPop.js
 *************************************************************************************************/
var dateTimeFormat = jsconfig.get("dateTimeFormatJs");
var dateFormat = jsconfig.get("dateFormatJs");

$(function() {
	/*!
	 * event
	 */
	$("#btnClose").click(function() {
		parent.popup.close();
		parent.doSearch();
	});

	$("#btnSave").click(function(event) {
		if (commonJs.doValidate("fmDefault")) {
			commonJs.doSave({
				url:"/cce/0208/doSave.do",
				data:{cashExpenseId:cashExpenseId},
				onSuccess:function(result) {
					var ds = result.dataSet;
					$("#btnClose").trigger("click");
				}
			});
		}
	});

	$("#icnProcDate").click(function(event) {
		commonJs.openCalendar(event, "procDate");
	});

	$(document).keydown(function(event) {
		var code = event.keyCode || event.which, element = event.target;
		if (code == 13) {}
		if (code == 9) {}
	});

	$(document).keyup(function(event) {
		var code = event.keyCode || event.which, element = event.target;
		if (code == 9) {}
		if (code == 13) {}
		onEdit($(element));
	});
	/*!
	 * process
	 */
	onEdit = function(jqObj) {
		var name = $(jqObj).attr("name");

		if (commonJs.contains(name, "grossAmt") || commonJs.contains(name, "gstAmt")) {
			var netAmt = commonJs.toNumber($("#grossAmt").val());
			var gstAmt = commonJs.toNumber($("#gstAmt").val());
			$("#netAmt").val(netAmt + gstAmt);
		}
	};

	loadData = () => {
		setTimeout(() => {
			if (!commonJs.isBlank(cashExpenseId)) {
				commonJs.showProcMessageOnElement("divScrollablePanelPopup");

				setTimeout(function() {
					commonJs.doSimpleProcess({
						url:"/cce/0208/getDataForEdit.do",
						noForm:true,
						data:{cashExpenseId:cashExpenseId},
						onSuccess:function(result) {
							var ds = result.dataSet;

							$("#procDate").val(commonJs.getDateTimeMask(ds.getValue(0, "PROC_DATE"), dateFormat));
							$("#category").val(ds.getValue(0, "SUB_CATEGORY"));
							commonJs.refreshBootstrapSelectbox("category");
							$("#grossAmt").val(ds.getValue(0, "PROC_AMT"));
							$("#gstAmt").val(ds.getValue(0, "GST_AMT"));
							$("#netAmt").val(ds.getValue(0, "NET_AMT"));
							$("#procDescription").val(ds.getValue(0, "PROC_DESCRIPTION"));

							commonJs.hideProcMessageOnElement("divScrollablePanelPopup");
						}
					});
				}, 400);
			}
		}, 400);
	};
	/*!
	 * load event (document / window)
	 */
	$(document).click(function(event) {
		var obj = event.target;
		var amt = 0;

		if ($(obj).is($("input:text")) && $(obj).hasClass("txtEn")) {
			$(obj).select();
		}
	});

	$(window).load(function() {
		commonJs.setFieldDateMask("procDate");
		$(".numeric").number(true, 2);
		loadData();
	});
});