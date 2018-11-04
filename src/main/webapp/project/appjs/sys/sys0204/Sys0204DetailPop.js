/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sys0204DetailPop.js
 *************************************************************************************************/
$(function() {
	/*!
	 * event
	 */
	$("#btnEdit").click(function(event) {
		doProcessByButton({mode:"Update"});
	});

	$("#btnDelete").click(function(event) {
		doProcessByButton({mode:"Delete"});
	});

	$("#btnClose").click(function(event) {
		parent.popup.close();
	});

	/*!
	 * process
	 */
	doProcessByButton = function(param) {
		var action = "";

		if (param.mode == "Update") {
			action = "/sys/0204/getUpdate.do";
			parent.popup.resizeTo(0, -40);
		} else if (param.mode == "Delete") {
			action = "/sys/0204/exeDelete.do";
		}

		if (param.mode == "Delete") {
			commonJs.confirm({
				contents:com.message.Q002,
				buttons:[{
					caption:com.caption.yes,
					callback:function() {
						commonJs.ajaxSubmit({
							url:action,
							dataType:"json",
							formId:"fmDefault",
							data:{
								countryCurrencyId:countryCurrencyId
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
											caption:"Ok",
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
		} else {
			commonJs.doSubmit({
				form:"fmDefault",
				action:action,
				data:{
					mode:param.mode,
					countryCurrencyId:countryCurrencyId
				}
			});
		}
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
	});
});