/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sba0406UpdatePop.js
 *************************************************************************************************/
jsconfig.put("jqSelectmenuInterval", 100);

$(function() {
	/*!
	 * event
	 */
	$("#btnSave").click(function(event) {
		if (commonJs.doValidate("fmDefault")) {
			commonJs.confirm({
				contents:com.message.Q001,
				buttons:[{
					caption:com.caption.yes,
					callback:function() {
						exeSave();
					}
				}, {
					caption:com.caption.no,
					callback:function() {
					}
				}]
			});
		}
	});

	$("#isApplyGst").change(function() {
		setGstPercentageStatus();
	});

	$("#btnClose").click(function(event) {
		parent.popup.close();
	});

	$(document).keypress(function(event) {
		if (event.which == 13) {
			var element = event.target;
		}
	});

	/*!
	 * process
	 */
	setGstPercentageStatus = function() {
		var val = $("#isApplyGst").val();
		if (val == "N") {
			$("#gstPercentage").prop("readonly", true).removeClass("txtEn").addClass("txtDis").val("0");
		} else {
			$("#gstPercentage").prop("readonly", false).removeClass("txtDis").addClass("txtEn");
		}
	};

	changeObjectStatus = function(status) {
		if (status == "enable") {
			$("#orgCategory").prop("disabled", false);
			$("#mainExpenseType").prop("disabled", false);
			$("#expenseType").prop("disabled", false);
		} else {
			$("#orgCategory").prop("disabled", true);
			$("#mainExpenseType").prop("disabled", true);
			$("#expenseType").prop("disabled", true);
		}
		$("#orgCategory").selectpicker("refresh");
		$("#mainExpenseType").selectpicker("refresh");
		$("#expenseType").selectpicker("refresh");
	};

	exeSave = function() {
		changeObjectStatus("enable");

		commonJs.ajaxSubmit({
			url:"/sba/0406/exeUpdate",
			dataType:"json",
			formId:"fmDefault",
			data:{
			},
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");

				if (result.isSuccess == true || result.isSuccess == "true") {
					commonJs.openDialog({
						type:com.message.I000,
						contents:result.message,
						blind:true,
						width:300,
						buttons:[{
							caption:com.caption.ok,
							callback:function() {
								parent.popup.close();
								parent.doSearch();
							}
						}]
					});
				} else {
					changeObjectStatus("disable");
					commonJs.error(result.message);
				}
			}
		});
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		$(".numeric").number(true, 2);
		setGstPercentageStatus();
	});
});