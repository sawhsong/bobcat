/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Rkm0802InsertPop.js
 *************************************************************************************************/
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
						commonJs.ajaxSubmit({
							url:"/rkm/0802/exeSave.do",
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