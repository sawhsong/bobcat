/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sys0206InsertPop.js
 *************************************************************************************************/
$(function() {
	/*!
	 * event
	 */
	$("#btnSave").click(function(event) {
		var fileValue = $("#logoPath").val();

		if (commonJs.doValidate("fmDefault")) {
			if (!commonJs.isEmpty(fileValue)) {
				if (!commonJs.isUploadableImageFile($("#filePhotoPath"), fileValue)) {
					return;
				}
			}

			if (commonJs.contains($("#legalName").val(), "&")) {
				var val = commonJs.replace($("#legalName").val(), "&", "||");
				$("#legalName").val(val);
			}

			if (commonJs.contains($("#tradingName").val(), "&")) {
				var val = commonJs.replace($("#tradingName").val(), "&", "||");
				$("#tradingName").val(val);
			}

			commonJs.doSaveWithFile({
				url:"/sys/0206/saveOrgDetail.do",
				onSuccess:function(result) {
					var ds = result.dataSet;
					setOrgDetailInfo(ds);
				}
			});
		}
	});

	$("#btnClose").click(function(event) {
		parent.popup.close();
		parent.doSearch();
	});

	$("#icnRegisteredDate").click(function(event) {
		commonJs.openCalendar(event, "registeredDate", {
			adjustX:254,
			adjustY:-180
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
	loadData = function() {
		if (!commonJs.isBlank(orgId)) {
			commonJs.doSimpleProcess({
				url:"/sys/0206/getOrgDetail.do",
				noForm:true,
				data:{orgId:orgId},
				onSuccess:function(result) {
					var ds = result.dataSet;
					setTimeout(function() {
						setOrgDetailInfo(ds);
					}, 400);
				}
			});
		}
	};

	setOrgDetailInfo = function(ds) {
		var logoPath = ds.getValue(0, "LOGO_PATH");

		if (!commonJs.contains(logoPath, "DefaultLogo.png")) {
			$("#tdOrgLogo").append("<img id=\"imgOrgLogo\" src=\""+ds.getValue(0, "LOGO_PATH")+"\" class=\"imgDis\" style=\"width:250px;height:80px;\"/>");
		}

		$("#logoPath").val("");
		$("#orgId").val(ds.getValue(0, "ORG_ID"));
		$("#abn").val(ds.getValue(0, "ABN"));
		commonJs.setFieldNumberMask("abn", "99 999 999 999");
		$("#legalName").val(ds.getValue(0, "LEGAL_NAME"));
		$("#tradingName").val(ds.getValue(0, "TRADING_NAME"));
		$("#email").val(ds.getValue(0, "EMAIL"));
		$("#businessType").val(ds.getValue(0, "BUSINESS_TYPE"));
		commonJs.refreshBootstrapSelectbox("businessType");
		$("#entityType").val(ds.getValue(0, "ENTITY_TYPE"));
		commonJs.refreshBootstrapSelectbox("entityType");
		$("#orgCategory").val(ds.getValue(0, "ORG_CATEGORY"));
		commonJs.refreshBootstrapSelectbox("orgCategory");
		$("#baseType").val(ds.getValue(0, "BASE_TYPE"));
		commonJs.refreshBootstrapSelectbox("baseType");
		$("#wageType").val(ds.getValue(0, "WAGE_TYPE"));
		commonJs.refreshBootstrapSelectbox("wageType");
		$("#postalAddress").val(ds.getValue(0, "POSTAL_ADDRESS"));
		$("#registeredDate").val(commonJs.getDateTimeMask(ds.getValue(0, "REGISTERED_DATE"), jsconfig.get("dateFormatJs")));
		$("#isActive").val(ds.getValue(0, "IS_ACTIVE"));
		commonJs.refreshBootstrapSelectbox("isActive");
		$("#rRangeFrom").val(ds.getValue(0, "REVENUE_RANGE_FROM"));
		$("#rRangeTo").val(ds.getValue(0, "REVENUE_RANGE_TO"));
		$("#lastUpdatedBy").val(commonJs.nvl(ds.getValue(0, "UPDATE_USER_NAME"), ds.getValue(0, "INSERT_USER_NAME")));
		$("#lastUpdatedDate").val(commonJs.getDateTimeMask(commonJs.nvl(ds.getValue(0, "UPDATE_DATE"), ds.getValue(0, "INSERT_DATE")), jsconfig.get("dateTimeFormatJs")));
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
		commonJs.setFieldDateMask("registeredDate");
		commonJs.setFieldNumberMask("abn", "99 999 999 999");
		$(".numeric").number(true, 0);

		loadData();
	});
});