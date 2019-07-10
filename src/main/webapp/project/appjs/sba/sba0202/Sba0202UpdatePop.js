/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sba0202UpdatePop.js
 *************************************************************************************************/
$(function() {
	/*!
	 * event
	 */
	$("#btnSave").click(function(event) {
		var fileValue = $("#logoPath").val();

		if (commonJs.doValidate("fmDefault")) {
			if (!commonJs.isEmpty(fileValue)) {
				fileValue = fileValue.substring(fileValue.lastIndexOf(".")+1);
				if (!(fileValue.toLowerCase() == "png" || fileValue.toLowerCase() == "jpg" || fileValue.toLowerCase() == "gif" || fileValue.toLowerCase() == "jpeg")) {
					commonJs.doValidatorMessage($("#logoPath"), "notUploadable");
					return;
				}
			}

			$("#fmDefault").attr("enctype", "multipart/form-data");

			if (commonJs.contains($("#legalName").val(), "&")) {
				var val = commonJs.replace($("#legalName").val(), "&", "||");
				$("#legalName").val(val);
			}

			commonJs.confirm({
				contents:com.message.Q001,
				buttons:[{
					caption:com.caption.yes,
					callback:function() {
						commonJs.doSubmit({
							form:"fmDefault",
							action:"/sba/0202/exeUpdate"
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