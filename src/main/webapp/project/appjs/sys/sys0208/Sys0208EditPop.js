/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sys0208EditPop.js
 *************************************************************************************************/
var delimiter = jsconfig.get("dataDelimiter");
var isBankAccntLoaded = false;
jsconfig.put("useJqSelectmenu", false);

$(function() {
	/*!
	 * event
	 */
	$("#btnSaveUserDetail").click(function(event) {
		if ("disabled" == $(this).attr("disabled")) {
			return;
		}

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
				url:"/sys/0208/saveUserDetail.do",
				preProcess:function() {
					enableUserDetailFields();
				},
				onSuccess:function(result) {
					var ds = result.dataSet;
					setUserDetailInfo(ds);
				},
				onCancel:function() {
					disableUserDetailFields();
				}
			});
		}
	});

	$("#btnSaveBankAccnt").click(function(event) {
		var isValid = true;
		var elementsToCheck = [];
		var detailLength = $("#ulDetailHolder .dummyDetail").length;

		if ("disabled" == $(this).attr("disabled")) {
			return;
		}

		if (detailLength <= 0) {
			commonJs.alert("There is no data to save.");
			return;
		}

		$("#ulDetailHolder").find(".dummyDetail").each(function(groupIndex) {
			$(this).find(":input").each(function(index) {
				var id = $(this).attr("id"), name = $(this).attr("name");

				if (!commonJs.isEmpty(id)) {id = (id.indexOf(delimiter) != -1) ? id.substring(0, id.indexOf(delimiter)) : id;}
				else {id = "";}

				if (!commonJs.isEmpty(name)) {name = (name.indexOf(delimiter) != -1) ? name.substring(0, name.indexOf(delimiter)) : name;}
				else {name = "";}

				$(this).attr("id", id+delimiter+groupIndex).attr("name", name+delimiter+groupIndex);

				elementsToCheck.push($(this).attr("id"));
			});
		});

		if (!commonJs.doValidate(elementsToCheck)) {
			isValid = false;
			return;
		}

		if (isValid) {
			var detailLength = $("#ulDetailHolder .dummyDetail").length;

			commonJs.doSave({
				url:"/sys/0208/saveBankAccnts.do",
				data:{detailLength:detailLength},
				onSuccess:function(result) {
					parent.popup.close();
					parent.doSearch();
				}
			});
		}
	});

	$("#icnOrgSearch").click(function(event) {
		parent.popupLookup = parent.commonJs.openPopup({
			popupId:"OrganisationLookup",
			url:"/common/lookup/getDefault.do",
			data:{
				lookupType:"organisationName",
				keyFieldId:"orgId",
				valueFieldId:"orgName",
				popupToSetValue:"parent.popup",
				popupName:"parent.popupLookup",
				lookupValue:$("#orgName").val()
			},
			header:sys.sys0208.header.popOrgLookup,
			width:880,
			height:680
		});
	});

	$("#btnGetAuthenticationSecretKey").click(function(event) {
		if ("disabled" == $(this).attr("disabled")) {
			return;
		}

		commonJs.doSearch({
			url:"/login/getAuthenticationSecretKey.do",
			noForm:true,
			onSuccess:function(result) {
				var ds = result.dataSet;
				$("#authenticationSecretKey").val(ds.getValue(0, "authenticationSecretKey"));
			}
		});
	});

	$("#btnAddBankAccnt").click(function(event) {
		var elem = $("#liDummy").clone(), elemId = $(elem).attr("id");

		$(elem).css("display", "block").appendTo($("#ulDetailHolder"));

		$("#ulDetailHolder").find(".dummyDetail").each(function(groupIndex) {
			$(this).attr("index", groupIndex).attr("id", elemId+delimiter+groupIndex);

			$(this).find("i").each(function(index) {
				var id = $(this).attr("id"), id = (id.indexOf(delimiter) != -1) ? id.substring(0, id.indexOf(delimiter)) : id;
				$(this).attr("index", groupIndex).attr("id", id+delimiter+groupIndex);
			});

			$(this).find(".deleteButton").each(function(index) {
				var id = $(this).attr("id"), id = (id.indexOf(delimiter) != -1) ? id.substring(0, id.indexOf(delimiter)) : id;
				$(this).attr("index", groupIndex).attr("id", id+delimiter+groupIndex);
			});

			$(this).find("input, select").each(function(index) {
				var id = $(this).attr("id"), name = $(this).attr("name");

				if (!commonJs.isEmpty(id)) {id = (id.indexOf(delimiter) != -1) ? id.substring(0, id.indexOf(delimiter)) : id;}
				else {id = "";}

				if (!commonJs.isEmpty(name)) {name = (name.indexOf(delimiter) != -1) ? name.substring(0, name.indexOf(delimiter)) : name;}
				else {name = "";}

				$(this).attr("id", id+delimiter+groupIndex).attr("name", name+delimiter+groupIndex);

				if ($(this).is("select")) {
					setSelectboxForBankAccountTab($(this));
				}

				if (commonJs.contains(id, "bsb")) {
					commonJs.setFieldNumberMask(id+delimiter+groupIndex, "999 999");
				}

				if (commonJs.contains(id, "balance")) {
					$(".numeric").number(true, 2);
				}
			});
		});

		setGridHeader();
	});

	$(document).keydown(function(event) {
		var code = event.keyCode || event.which, element = event.target;

		if (code == 13) {}
		if (code == 9) {}
	});

	/*!
	 * process
	 */
	closeWindow = function() {
		parent.popup.close();
		parent.doSearch();
	};

	setGridHeader = function() {
		$("#tblGrid").fixedHeaderTable({
			attachTo:$("#divGridWrapper"),
			attachToHeight:446
		});
	};

	toggleTabStatus = function() {
		if (commonJs.isBlank($("#userId").val())) {
			$("#tabCategory li").each(function(index) {
				if (index == 1) {
					$(this).removeClass("active").addClass("disabled").find("a").unbind("click");
				}
			});
		} else {
			$("#tabCategory li").each(function(index) {
				if (index == 1) {
					$(this).removeClass("disabled").find("a").bind("click");
				}
			});
		}
	};

	setSelectboxForUserDetailTab = function() {
		var options = {};
		$("#div0 select.bootstrapSelect").each(function(index) {
			options.container = "body";
			options.style = $(this).attr("class");
			$(this).selectpicker(options);
		});
	};

	setSelectboxForBankAccountTab = function(jqObj) {
		$(jqObj).selectpicker({
			width:"auto",
			container:"body",
			style:$(jqObj).attr("class")
		});
	};

	loadUserDetail = function() {
		if (!commonJs.isBlank(userId)) {
			commonJs.doSimpleProcess({
				url:"/sys/0208/getUserDetail.do",
				noForm:true,
				data:{userId:userId},
				onSuccess:function(result) {
					var ds = result.dataSet;
					setUserDetailInfo(ds);
				}
			});
		}
	};

	loadBankAccounts = function() {
		if (!commonJs.isBlank(userId) && !isBankAccntLoaded) {
			commonJs.doSimpleProcess({
				url:"/sys/0208/getBankAccounts.do",
				noForm:true,
				data:{userId:userId},
				onSuccess:function(result) {
					var ds = result.dataSet;
					setBankAccountsInfo(ds);
					isBankAccntLoaded = true;
				}
			});
		}
	};

	enableUserDetailFields = function() {
		$("#language").attr("disabled", false);
		commonJs.refreshBootstrapSelectbox("language");
		$("#themeType").attr("disabled", false);
		commonJs.refreshBootstrapSelectbox("themeType");
		$("#pageNumsPerPage").attr("disabled", false);
		commonJs.refreshBootstrapSelectbox("pageNumsPerPage");
		$("#userStatus").attr("disabled", false);
		commonJs.refreshBootstrapSelectbox("userStatus");
	};

	disableUserDetailFields = function() {
		$("#language").attr("disabled", true);
		commonJs.refreshBootstrapSelectbox("language");
		$("#themeType").attr("disabled", true);
		commonJs.refreshBootstrapSelectbox("themeType");
		$("#pageNumsPerPage").attr("disabled", true);
		commonJs.refreshBootstrapSelectbox("pageNumsPerPage");
		$("#userStatus").attr("disabled", true);
		commonJs.refreshBootstrapSelectbox("userStatus");
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
		$("#authGroup").val(ds.getValue(0, "AUTH_GROUP_ID"));
		commonJs.refreshBootstrapSelectbox("authGroup");
		$("#language").val(ds.getValue(0, "LANGUAGE"));
		commonJs.refreshBootstrapSelectbox("language");
		$("#themeType").val(ds.getValue(0, "THEME_TYPE"));
		commonJs.refreshBootstrapSelectbox("themeType");
		$("#email").val(ds.getValue(0, "EMAIL"));
		$("#defaultStartUrl").val(ds.getValue(0, "DEFAULT_START_URL"));
		$("#maxRowsPerPage").val(ds.getValue(0, "MAX_ROW_PER_PAGE"));
		commonJs.refreshBootstrapSelectbox("maxRowsPerPage");
		$("#pageNumsPerPage").val(ds.getValue(0, "PAGE_NUM_PER_PAGE"));
		commonJs.refreshBootstrapSelectbox("pageNumsPerPage");
		$("#userStatus").val(ds.getValue(0, "USER_STATUS"));
		commonJs.refreshBootstrapSelectbox("userStatus");
		$("#isActive").val(ds.getValue(0, "IS_ACTIVE"));
		commonJs.refreshBootstrapSelectbox("isActive");
		$("#authenticationSecretKey").val(ds.getValue(0, "AUTHENTICATION_SECRET_KEY"));
		$("#lastUpdatedBy").val(commonJs.nvl(ds.getValue(0, "UPDATE_USER_NAME"), ds.getValue(0, "INSERT_USER_NAME")));
		$("#lastUpdatedDate").val(commonJs.getDateTimeMask(commonJs.nvl(ds.getValue(0, "UPDATE_DATE"), ds.getValue(0, "INSERT_DATE")), jsconfig.get("dateTimeFormatJs")));

		if (!commonJs.isBlank($("#authenticationSecretKey").val())) {
			$("#btnGetAuthenticationSecretKey").attr("disabled", true);
		}

		disableUserDetailFields();
		toggleTabStatus();
	};

	setBankAccountsInfo = function(ds) {
		for (var i=0; i<ds.getRowCnt(); i++) {
			var rowIdx = delimiter+i;

			$("#btnAddBankAccnt").trigger("click");

			$("[name=bankAccntId"+rowIdx+"]").val(ds.getValue(i, "BANK_ACCNT_ID"));
			$("[name=bankCode"+rowIdx+"]").selectpicker("val", ds.getValue(i, "BANK_CODE"));
			$("[name=bsb"+rowIdx+"]").val(ds.getValue(i, "BSB"));
			commonJs.setFieldNumberMask("bsb"+rowIdx, "999 999");
			$("[name=accntNumber"+rowIdx+"]").val(ds.getValue(i, "ACCNT_NUMBER"));
			$("[name=accntName"+rowIdx+"]").val(ds.getValue(i, "ACCNT_NAME"));
			$("[name=balance"+rowIdx+"]").val(ds.getValue(i, "BALANCE"));
			$("[name=description"+rowIdx+"]").val(ds.getValue(i, "DESCRIPTION"));
		}
	};

	/*!
	 * load event (document / window)
	 */
	$(document).click(function(event) {
		var obj = event.target;

		if ($(obj).hasClass("deleteButton") || ($(obj).is("i") && $(obj).parent("th").hasClass("deleteButton"))) {
			$("#ulDetailHolder").find(".dummyDetail").each(function(index) {
				if ($(this).attr("index") == $(obj).attr("index")) {
					$(this).remove();
				}

				setGridHeader();
			});
		}

		if ($(obj).is($("#tabCategory li:eq(1) a"))) {
			setTimeout(function() {
				loadBankAccounts();
			}, 400);
		}

		if ($(obj).is($("input:text")) && $(obj).hasClass("txtEn")) {
			$(obj).select();
		}
	});

	$(window).load(function() {
		commonJs.setEvent("click", [$("#btnCloseUserDetail"), $("#btnCloseBankAccnt")], closeWindow);

		commonJs.setAutoComplete($("#orgName"), {
			method:"getOrgId",
			label:"legal_name",
			value:"org_id",
			focus:function(event, ui) {
				$("#orgName").val(ui.item.label);
				return false;
			},
			select:function(event, ui) {
				$("#orgId").val(ui.item.value);
				$("#orgName").val(ui.item.label);
				return false;
			}
		});

		toggleTabStatus();
		setSelectboxForUserDetailTab();
		loadUserDetail();
	});
});