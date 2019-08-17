/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Rkm0802UpdatePop.js
 *************************************************************************************************/
$(function() {
	/*!
	 * event
	 */
	$("#btnSave").click(function(event) {
		if (commonJs.doValidate("fmDefault")) {
			doProcess({mode:"Save"});
		}
	});

	$("#btnDelete").click(function(event) {
		doDelete();
	});

	$("#btnClose").click(function(event) {
		parent.popup.close();
	});

	$("#icnDob").click(function(event) {
		commonJs.openCalendar(event, "dob");
	});

	$("#icnStartDate").click(function(event) {
		commonJs.openCalendar(event, "startDate");
	});

	$("#icnEndDate").click(function(event) {
		commonJs.openCalendar(event, "endDate");
	});

	$(document).keypress(function(event) {
		if (event.which == 13) {
			var element = event.target;
		}
	});

	/*!
	 * process
	 */
	doDelete = function() {
		doProcess({mode:"Delete"});
	};

	doProcess = function(param) {
		var confirmMessage = "", url = "";

		if (param.mode == "Save") {
			confirmMessage = com.message.Q001;
			url = "exeSave.do";
		} else if (param.mode == "Delete") {
			confirmMessage = com.message.Q002;
			url = "exeDelete.do";
		}

		commonJs.confirm({
			contents:confirmMessage,
			buttons:[{
				caption:com.caption.ok,
				callback:function() {
					commonJs.ajaxSubmit({
						url:"/rkm/0802/"+url,
						dataType:"json",
						formId:"fmDefault",
						data:{},
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
								commonJs.error(result.message);
							}
						}
					});
				}
			}, {
				caption:com.caption.no,
				callback:function() {
				}
			}]
		});
	}
	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		commonJs.setFieldDateMask("dob");
		commonJs.setFieldDateMask("startDate");
		commonJs.setFieldDateMask("endDate");
		$("#tfn").mask("999 999 999", {placeholder:"_"});
	});
});