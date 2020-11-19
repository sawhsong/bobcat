/**
 * updateUserProfilePop.js
 */
$(function() {
	/*!
	 * event
	 */
	$("#btnSave").click(function(event) {
		var fileValue = $("#filePhotoPath").val();

		if (commonJs.doValidate("fmDefault")) {
			if (!commonJs.isEmpty(fileValue)) {
				if (!commonJs.isUploadableImageFile($("#filePhotoPath"), fileValue)) {
					return;
				}
			}

			commonJs.doSaveWithFileForPage({
				action:"/login/exeUpdate.do"
			});
		}
	});

	$("#btnBack").click(function(event) {
		parent.popupUserProfile.resizeTo(0, -124);
		history.go(-1);
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
	setButtonStatus = function() {
		commonJs.doSearch({
			url:"/login/hasAuthKey.do",
			noForm:true,
			onSuccess:function(result) {
				var ds = result.dataSet;
				var hasAuthKey = commonJs.toBoolean(ds.getValue(0, "hasAuthKey"));

				if (hasAuthKey) {
					$("#authenticationSecretKey").removeClass("txtEn").addClass("txtDis").attr("readonly", true);
					$("#btnGetAuthenticationSecretKey").attr("disabled", true);
				}
			}
		});
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		$("#maxRowsPerPage").selectpicker({width:"90px"}).selectpicker("refresh");
		setButtonStatus();
	});
});