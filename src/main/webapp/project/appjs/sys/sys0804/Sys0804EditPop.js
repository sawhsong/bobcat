/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sys0804EditPop.js
 *************************************************************************************************/
var dateFormat = jsconfig.get("dateFormatJs");
var dateTimeFormat = jsconfig.get("dateTimeFormatJs");
$(function() {
	/*!
	 * event
	 */
	$("#btnSave").click(function(event) {
		if ("disabled" == $(this).attr("disabled")) {
			return;
		}

		if (commonJs.doValidate("fmDefault")) {
			commonJs.doSave({
				url:"/sys/0804/doSave.do",
				onSuccess:function(result) {
					var ds = result.dataSet;
					$("#btnClose").trigger("click");
//					commonJs.showProcMessage(com.message.loading);
//
//					setTimeout(function() {
//						setCategoryInfo(ds);
//					}, 400);
				}
			});
		}
	});

	$("#btnClose").click(function(event) {
		parent.popup.close();
		parent.loadMainCategory();
		parent.doSearch();
	});

	$(document).keydown(function(event) {
		var code = event.keyCode || event.which, element = event.target;

		if (code == 13) {}

		if (code == 9) {}
	});

	/*!
	 * process
	 */
	loadMainCategory = function() {
		commonJs.showProcMessageOnElement("divScrollablePanel");

		commonJs.doSearch({
			url:"/sys/0804/getMainCategory.do",
			onSuccess:renderMainCategory
		});
	};

	renderMainCategory = function(result) {
		var ds = result.dataSet;

		$("#parentCategoryId option").each(function(index) {
			$(this).remove();
		});
		$("#parentCategoryId").append("<option value=\"\">==Select==</option>");

		for (var i=0; i<ds.getRowCnt(); i++) {
			$("#parentCategoryId").append(commonJs.getUiSelectOption({
				value:ds.getValue(i, "CATEGORY_ID"),
				text:ds.getValue(i, "CATEGORY_NAME")
			}));
		}

		$("#parentCategoryId").selectpicker("refresh");
	};

	loadData = function() {
		if (mode == "Update") {
			commonJs.showProcMessage(com.message.loading);

			setTimeout(function() {
				commonJs.doSimpleProcess({
					url:"/sys/0804/getCategory.do",
					noForm:true,
					data:{categoryId:categoryId},
					onSuccess:function(result) {
						var ds = result.dataSet;
						setCategoryInfo(ds);
					}
				});
			}, 400);
		}
	};

	setCategoryInfo = function(ds) {
		if (ds.getRowCnt() > 0) {
			$("#categoryId").val(ds.getValue(0, "CATEGORY_ID"));
			$("#parentCategoryId").val(ds.getValue(0, "PARENT_CATEGORY_ID"));
			commonJs.refreshBootstrapSelectbox("parentCategoryId");
			$("#categoryName").val(ds.getValue(0, "CATEGORY_NAME"));
			$("#accountCode").val(ds.getValue(0, "ACCOUNT_CODE"));
			$("#sortOrder").val(ds.getValue(0, "SORT_ORDER"));
			commonJs.setRadioValue("isApplyGst", ds.getValue(0, "IS_APPLY_GST"));
			$("#gstPercentage").val(commonJs.getNumberMask(ds.getValue(0, "GST_PERCENTAGE"), "#,##0.00"));
			$("#lastUpdatedBy").val(commonJs.nvl(ds.getValue(0, "UPDATE_USER_NAME"), ds.getValue(0, "INSERT_USER_NAME")));
			$("#lastUpdatedDate").val(commonJs.getDateTimeMask(commonJs.nvl(ds.getValue(0, "UPDATE_DATE"), ds.getValue(0, "INSERT_DATE")), dateTimeFormat));
		}

		commonJs.hideProcMessage();
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
		loadMainCategory();
		loadData();
	});
});