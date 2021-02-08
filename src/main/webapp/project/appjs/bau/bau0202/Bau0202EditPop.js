/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Bau0202InsertPop.js
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
				url:"/bau/0202/doSave.do",
				onSuccess:function(result) {
					var ds = result.dataSet;
					$("#btnClose").trigger("click");
				}
			});
		}
	});

	$("#btnClose").click(function() {
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
	setFieldFormat = function() {
		commonJs.setFieldNumberMask("bsb", "999 999");
		$("#balance").number(true, 2);
	};

	loadData = function() {
		if (!commonJs.isBlank(bankAccntId)) {
			commonJs.showProcMessage(com.message.loading);

			setTimeout(function() {
				commonJs.doSimpleProcess({
					url:"/bau/0202/getBankAccountInfo.do",
					noForm:true,
					data:{bankAccntId:bankAccntId},
					onSuccess:function(result) {
						var ds = result.dataSet;
						setBankAccountInfo(ds);
					}
				});
			}, 500);
		}
	};

	setBankAccountInfo = function(ds) {
		for (var i=0; i<ds.getRowCnt(); i++) {
			$("#bankAccntId").val(ds.getValue(i, "BANK_ACCNT_ID"));
			$("#bankCode").val(ds.getValue(i, "BANK_CODE"));
			commonJs.refreshBootstrapSelectbox("bankCode");
			$("#bsb").val(commonJs.getFormatString(ds.getValue(i, "BSB"), "??? ???"));
			$("#accntNumber").val(ds.getValue(i, "ACCNT_NUMBER"));
			$("#accntName").val(ds.getValue(i, "ACCNT_NAME"));
			$("#balance").val(ds.getValue(i, "BALANCE"));
			$("#description").val(ds.getValue(i, "DESCRIPTION"));
			$("#lastUpdatedBy").val(commonJs.nvl(ds.getValue(0, "UPDATE_USER_NAME"), ds.getValue(0, "INSERT_USER_NAME")));
			$("#lastUpdatedDate").val(commonJs.getDateTimeMask(commonJs.nvl(ds.getValue(0, "UPDATE_DATE"), ds.getValue(0, "INSERT_DATE")), dateTimeFormat));
		}

		commonJs.hideProcMessage();
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		setFieldFormat();
		loadData();
	});
});