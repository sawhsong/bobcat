/**
 * login.js
 */
jsconfig.put("noLayoutWindow", true);
var popup = null;

$(function() {
	/*!
	 * event
	 */
	$(document).keypress(function(event) {
		if (event.which == 13) {
			var element = event.target;

			if ($(element).is("[name=loginId]") || $(element).is("[name=password]")) {
				doProcess({mode:"login"});
			}
		}
	});

	$("#btnLogin").click(function(event) {
		doProcess({mode:"login"});
	});

	$("#aResetPassword").click(function(event) {
		doProcess({mode:"resetPassword"});
	});

	$("#aRequestRegister").click(function(event) {
		doProcess({mode:"requestRegister"});
	});

	/*!
	 * process
	 */
	doProcess = function(params) {
		if (params.mode == "login") {
			if (!commonJs.doValidate("fmDefault")) {
				return;
			}

			commonJs.doSimpleProcess({
				url:"/login/login.do",
				onSuccess:function(result) {
					var dataSet = result.dataSet;
					var actionString = dataSet.getValue(0, "DEFAULT_START_URL");

					if ("Y" == jsconfig.get("google2fa") || "Y" == jsconfig.get("emailKey")) {
						if ("Y" == jsconfig.get("google2fa")) {
							var key = dataSet.getValue(0, "AUTHENTICATION_SECRET_KEY");
							if (commonJs.isBlank(key)) {
								commonJs.openDialog({
									type:com.message.E000,
									contents:com.message.E913,
									blind:true,
									draggable:false,
									buttons:[{
										caption:com.caption.ok,
										callback:function() {
											return;
										}
									}]
								});
								return;
							}
						}

						commonJs.openPopup({
							popupId:"2-Factor Authentication",
							url:"/login/getAuthentication.do",
							data:{},
							header:"Authentication",
							blind:true,
							draggable:false,
							width:400,
							height:240
						});
					} else {
						var message = result.message+" "+dataSet.getValue(0, "USER_NAME")+"!";
						commonJs.doSimpleProcess({
							url:"/login/doAuthentication.do",
							data:{mode:"noAuth"},
							noForm:true,
							onSuccess:function(result) {
								var ds = result.dataSet;
								isAuthenticated = commonJs.toBoolean(ds.getValue(0, "isAuthenticated"));

								commonJs.openDialog({
									type:com.message.I000,
									contents:message,
									blind:true,
									draggable:false,
									buttons:[{
										caption:com.caption.ok,
										callback:function() {
											commonJs.doSubmit({
												formId:"fmDefault",
												action:actionString
											});
										}
									}]
								});
							}
						});
					}
				},
				onError:function(result) {
					commonJs.openDialog({
						type:com.message.E000,
						contents:result.message
					});
				}
			});
		} else {
			if (params.mode == "resetPassword") {
				params = {
					popupId:"ResetPassword",
					url:"/login/resetPassword.do",
					data:{},
					header:login.header.resetPassword,
					blind:false,
					draggable:false,
					width:400,
					height:266
				};
			} else if (params.mode == "requestRegister") {
				params = {
					popupId:"Request Register",
					url:"/login/requestRegister.do",
					data:{},
					header:login.header.requestRegister,
					blind:false,
					draggable:false,
					width:400,
					height:410
				};
			}

			popup = commonJs.openPopup(params);
		}
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		$("[name=loginId]").focus();
	});
});