/**
 * userProfilePop.js
 */
$(function() {
	/*!
	 * event
	 */
	$("#btnSave").click(function(event) {
		if ("disabled" == $(this).attr("disabled")) {return;}

		var fileValue = $("#photoPath").val();
		var elementsToCheck = [];

		elementsToCheck.push("userName");
		elementsToCheck.push("loginId");
		elementsToCheck.push("password");
		elementsToCheck.push("orgId");
		elementsToCheck.push("orgName");
		elementsToCheck.push("email");

		if (commonJs.doValidate(elementsToCheck)) {
			if (!commonJs.isEmpty(fileValue)) {
				if (!commonJs.isUploadableImageFile($("#filePhotoPath"), fileValue)) {
					return;
				}
			}

			commonJs.doSaveWithFile({
				url:"/login/saveUserDetail.do",
				onSuccess:function(result) {
					var ds = result.dataSet;
					var isLoginIdPasswordChanged = ds.getValue(0, "isLoginIdPasswordChanged");

					if (isLoginIdPasswordChanged == "Y") {
						commonJs.openDialog({
							contents:"Login information has been changed.</br>It is required to login again.",
							buttons:[{
								caption:com.caption.ok,
								callback:function() {
									commonJs.doSubmit({form:$("form:eq(0)"), action:"/login/logout.do"});
								}
							}],
							draggable:false,
							blind:true
						});
					} else {
						parent.popupUserProfile.close();
					}
				}
			});
		}
	});

	$("#btnClose").click(function(event) {
		parent.popupUserProfile.close();
	});

	$("#btnGetAuthenticationSecretKey").click(function(event) {
		if ("disabled" != $(this).attr("disabled")) {
			commonJs.doSearch({
				url:"/login/getAuthenticationSecretKey.do",
				noForm:true,
				onSuccess:function(result) {
					var ds = result.dataSet;
					$("#authenticationSecretKey").val(ds.getValue(0, "authenticationSecretKey"));
				}
			});
		}
	});

	/*!
	 * process
	 */
	loadData = function() {
		if (!commonJs.isBlank(userId)) {
			commonJs.doSimpleProcess({
				url:"/login/getUserDetail.do",
				noForm:true,
				data:{userId:userId},
				onSuccess:function(result) {
					var ds = result.dataSet;
					setUserDetailInfo(ds);
				}
			});
		}
	};

	setUserDetailInfo = function(ds) {
		$("#imgUserPhoto").attr("src", ds.getValue(0, "PHOTO_PATH"));
		$("#photoPath").val("");
		$("#userId").val(ds.getValue(0, "USER_ID"));
		$("#userName").val(ds.getValue(0, "USER_NAME"));
		$("#loginId").val(ds.getValue(0, "LOGIN_ID"));
		$("#password").val(ds.getValue(0, "LOGIN_PASSWORD"));
		$("#orgId").val(ds.getValue(0, "ORG_ID"));
		$("#orgName").val(ds.getValue(0, "ORG_NAME"));
		$("#email").val(ds.getValue(0, "EMAIL"));
		$("#authenticationSecretKey").val(ds.getValue(0, "AUTHENTICATION_SECRET_KEY"));

		if (!commonJs.isBlank($("#authenticationSecretKey").val())) {
			$("#btnGetAuthenticationSecretKey").attr("disabled", true);
		}
	};

	/*!
	 * load event (document / window)
	 */
	$(document).click(function(event) {
		var obj = event.target;

		if ($(obj).is($("input:text")) && $(obj).hasClass("txtEn")) {
			$(obj).select();
		}
	});

	$(window).load(function() {
		loadData();
	});
});