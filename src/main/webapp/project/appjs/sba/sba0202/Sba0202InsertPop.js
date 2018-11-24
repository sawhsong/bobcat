/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sba0202InsertPop.js
 *************************************************************************************************/
$(function() {
	/*!
	 * event
	 */
	$("#btnSave").click(function(event) {
		if (commonJs.doValidate("fmDefault")) {
			if (commonJs.contains($("#legalName").val(), "&")) {
				var val = commonJs.replace($("#legalName").val(), "&", "||");
				$("#legalName").val(val);
			}

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

	$("#btnClose").click(function(event) {
		parent.popup.close();
	});

	$("#icnRegisteredDate").click(function(event) {
		commonJs.openCalendar(event, "registeredDate", {
			adjustX:254,
			adjustY:-62
		});
	});

	$(document).keypress(function(event) {
		if (event.which == 13) {
			var element = event.target;
		}
	});

	/*!
	 * process
	 */
	exeSave = function() {
		commonJs.ajaxSubmit({
			url:"/sba/0202/exeInsert.do",
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
	};

	setAbnFieldMask = function() {
		$("#abn").mask(
			"99 999 999 999",
			{
				placeholder:"_"
			}
		);
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		setTimeout(function() {
			commonJs.setFieldDateMask("registeredDate");
			setAbnFieldMask();
			$(".numeric").number(true, 0);
			$("#abn").focus();
		}, 100);
	});
});