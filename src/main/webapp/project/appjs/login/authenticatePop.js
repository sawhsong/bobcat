/**
 * authenticatePop.js
 */
var popup = null;

$(function() {
	/*!
	 * event
	 */
	$("#btnSubmitCode").click(function(event) {
		if (!commonJs.doValidate("fmDefault")) {
			return;
		}

		if ("Y" == jsconfig.get("google2fa")) {
			doAuthentication("google2fa");
		} else if ("Y" == jsconfig.get("emailKey")) {
			doAuthentication("emailKey");
		}
	});

	$(document).keypress(function(event) {
		var code = event.keyCode || event.which, element = event.target;
		if (code == 13) {
			if ($(element).is("[name=authenticationCode]")) {
				$("#btnSubmitCode").trigger("click");
				event.preventDefault();
			}
		}
	});

	/*!
	 * process
	 */
	doAuthentication = function(mode) {
		commonJs.doSimpleProcess({
			url:"/login/doAuthentication.do",
			data:{
				mode:mode,
				inputCode:$("#authenticationCode").val()
			},
			noForm:true,
			onSuccess:function(result) {
				var ds = result.dataSet;
				isAuthenticated = commonJs.toBoolean(ds.getValue(0, "isAuthenticated"));

				if (!isAuthenticated) {
					commonJs.error("Authentication code is not correct!<br/>Please try again!");
					$("#authenticationCode").val("");
					$("#authenticationCode").focus();
					return;
				}

				commonJs.openDialog({
					type:com.message.I000,
					contents:com.message.I903+" "+userName+"!",
					blind:true,
					draggable:false,
					width:350,
					buttons:[{
						caption:com.caption.ok,
						callback:function() {
							parent.commonJs.doSubmit({
								formId:"fmDefault",
								action:defaultStartUrl
							});
						}
					}]
				});
			},
			onError:function(result) {
				commonJs.openDialog({
					type:com.message.E000,
					contents:result.message,
					blind:false,
					draggable:false,
					width:350,
					buttons:[{
						caption:com.caption.ok,
						callback:function() {
							return;
						}
					}]
				});
			}
		});
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		setTimeout(function() {
			$("[name=authenticationCode]").focus();
		}, 400);
	});
});