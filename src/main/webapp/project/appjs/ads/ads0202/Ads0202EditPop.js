/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Ads0202EditPop.js
 *************************************************************************************************/
jsconfig.put("useJqSelectmenu", false);
var delimiter = jsconfig.get("dataDelimiter");
var dateTimeFormat = jsconfig.get("dateTimeFormatJs");
var dateFormat = jsconfig.get("dateFormatJs");

$(function() {
	/*!
	 * event
	 */
	$("#btnClose").click(function() {
		parent.popup.close();
//		parent.doSearch();
	});

	$("#btnBringMyInfo").click(function() {
		commonJs.showProcMessageOnElement("divFixedPanelPopup");

		setTimeout(function() {
			commonJs.doSimpleProcess({
				url:"/ads/0202/getMyInfo.do",
				noForm:true,
				data:{quotationId:quotationId},
				onSuccess:function(result) {
					var ds = result.dataSet;
					setMyInfo(ds);
				}
			});
		}, 400);
	});

	$("#btnBringOrgInfo").click(function() {
		commonJs.showProcMessageOnElement("divFixedPanelPopup");

		setTimeout(function() {
			commonJs.doSimpleProcess({
				url:"/ads/0202/getOrgInfo.do",
				noForm:true,
				data:{quotationId:quotationId},
				onSuccess:function(result) {
					var ds = result.dataSet;
					setOrgInfo(ds);
				}
			});
		}, 400);
	});

	$("#btnDiscardBasicInfo").click(function() {
		$("#providerOrgId").val("");
		$("#providerOrgName").val("");
		$("#providerName").val("");
		$("#providerAbn").val("");
		$("#providerAcn").val("");
		$("#providerEmail").val("");
		$("#providerTelephone").val("");
		$("#providerMobile").val("");
		$("#providerAddress").val("");
	});

	$("#icnIssueDate").click(function(event) {
		commonJs.openCalendar(event, "issueDate");
	});

	clearValueOnBlur = function(jqObj) {
		if (jqObj == null) {return;}

		var id = $(jqObj).attr("id");

		if ($.nony.isEmpty($(jqObj).val())) {
			$(jqObj).val("");
		}

		if (id == "providerOrgName") {
			$("#"+id).val("");
		}
	};

	$(document).keydown(function(event) {
		var code = event.keyCode || event.which, element = event.target;
		if (code == 13) {}
		if (code == 9) {}
	});
	/*!
	 * process
	 */
	setFieldFormat = function() {
		commonJs.setFieldDateMask("issueDate");
		commonJs.setFieldNumberMask("providerAbn", "99 999 999 999");
		commonJs.setFieldNumberMask("providerAcn", "999 999 999");
		commonJs.setFieldNumberMask("providerTelephone", "99 9999 9999");
		commonJs.setFieldNumberMask("providerMobile", "9999 999 999");
		commonJs.setFieldNumberMask("clientTelephone", "99 9999 9999");
		commonJs.setFieldNumberMask("clientMobile", "9999 999 999");
		$("#netAmt").number(true, 2);
		$("#gstAmt").number(true, 2);
		$("#totalAmt").number(true, 2);
	};

	setMyInfo = function(ds) {
		$("#providerName").val(ds.getValue(0, "USER_NAME"));
		$("#providerEmail").val(ds.getValue(0, "EMAIL"));
		$("#providerTelephone").val(commonJs.getFormatString(ds.getValue(0, "TEL_NUMBER"), "?? ???? ????"));
		$("#providerMobile").val(commonJs.getFormatString(ds.getValue(0, "MOBILE_NUMBER"), "???? ??? ???"));

		commonJs.hideProcMessageOnElement("divFixedPanelPopup");
	};

	setOrgInfo = function(ds) {
		$("#providerOrgId").val(ds.getValue(0, "ORG_ID"));
		$("#providerOrgName").val(ds.getValue(0, "LEGAL_NAME"));
		$("#providerName").val(ds.getValue(0, "LEGAL_NAME"));
		$("#providerAbn").val(commonJs.getFormatString(ds.getValue(0, "ABN"), "?? ??? ??? ???"));
		$("#providerAcn").val(commonJs.getFormatString(ds.getValue(0, "ACN"), "??? ??? ???"));
		$("#providerEmail").val(ds.getValue(0, "EMAIL"));
		$("#providerTelephone").val(ds.getValue(0, "TEL_NUMBER"));
		$("#providerMobile").val(ds.getValue(0, "MOBILE_NUMBER"));
		$("#providerAddress").val(ds.getValue(0, "ADDRESS"));

		commonJs.hideProcMessageOnElement("divFixedPanelPopup");
	};

	loadQuotationNumber = function() {
		if (commonJs.isBlank(quotationId)) {
			commonJs.doSimpleProcess({
				url:"/ads/0202/getQuotationNumber.do",
				noForm:true,
				data:{quotationId:quotationId},
				onSuccess:function(result) {
					var ds = result.dataSet;
					$("#quotationNumber").val(ds.getValue(0, "Number"));
				}
			});
		}
	};

	loadMasterInfo = function() {
		if (!commonJs.isBlank(quotationId)) {
			commonJs.showProcMessageOnElement("divFixedPanelPopup");

			setTimeout(function() {
				commonJs.doSimpleProcess({
					url:"/ads/0202/getQuotationMasterInfo.do",
					noForm:true,
					data:{quotationId:quotationId},
					onSuccess:function(result) {
						var ds = result.dataSet;
						setQuotationMasterInfo(ds);
					}
				});
			}, 300);
		}
	};

	setQuotationMasterInfo = function (ds) {
		
		commonJs.hideProcMessageOnElement("divFixedPanelPopup");
	};

	loadDetailInfo = function() {
		if (!commonJs.isBlank(quotationId)) {
			commonJs.showProcMessageOnElement("divDataArea");

			setTimeout(function() {
				commonJs.doSimpleProcess({
					url:"/ads/0202/getQuotationDetailInfo.do",
					noForm:true,
					data:{quotationId:quotationId},
					onSuccess:function(result) {
						var ds = result.dataSet;
						setQuotationDetailInfo(ds);
					}
				});
			}, 300);
		}
	};

	setQuotationDetailInfo = function (ds) {
		commonJs.hideProcMessageOnElement("divDataArea");
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
		commonJs.setEvent("blur", [$("#providerOrgName")], clearValueOnBlur);
		$("#btnBringMyInfo").trigger("click");
		setFieldFormat();
		setTimeout(function() {
			loadQuotationNumber();
			loadMasterInfo();
			loadDetailInfo();
		}, 200);
	});
});