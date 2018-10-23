<%/************************************************************************************************
* Description
* - Admin Tool Area - for User Menus
************************************************************************************************/%>
<%
	SysUser sysUserAdminToolArea = (SysUser)session.getAttribute("SysUser");
	String authGroupIdAdminToolArea = sysUserAdminToolArea.getAuthGroupId();
	boolean isVisibleAdminToolAdminToolArea = CommonUtil.toBoolean((String)session.getAttribute("isVisibleAdminTool"));
%>

<style type="text/css">
</style>
<script type="text/javascript">
$(function() {
	var leftMenuId = "${sessionScope.leftMenuId}";
	var divAdminToolContainerHeight = 0;
	if (commonJs.browser.FireFox) {
		divAdminToolContainerHeight = 223;
	} else {
		divAdminToolContainerHeight = 220;
	}

	$("#divAdminToolContainer").css("height", divAdminToolContainerHeight);

	var isVisibleAdminToolAdminToolArea = commonJs.toBoolean("<%=isVisibleAdminToolAdminToolArea%>");

	$("#btnReloadUserAdminTool").click(function() {
		setSessionValuesForAdminTool();
	});

	$("#btnReturnUserAdminTool").click(function() {
		removeSessionValuesForAdminTool();
	});

	setSummaryDataForAdminTool = function() {
		if (isVisibleAdminToolAdminToolArea) {
			setSummaryIncomeForAdminTool();
			setSummaryExpenseForAdminTool();
			setSummaryAssetForAdminTool();
			setSummaryRepaymentForAdminTool();
			setSummaryBorrowingForAdminTool();
			setSummaryLendingForAdminTool();
			setSummaryEmployeeForAdminTool();
		}
	}

	setMainScreenSearchCriteriaForAdminTool = function() {
		if ($("#incomeType").length > 0) {
			drawIncomeTypeSelectboxForAdminTool();
		}

		if ($("#expenseMainType").length > 0) {
			drawExpenseMainTypeSelectboxForAdminTool();
			setExpenseSubType();
			drawDeExpenseMainTypeSelectboxForAdminTool();
			setDeExpenseSubType();
		}

		if ($("#assetType").length > 0) {
			drawAssetTypeSelectboxForAdminTool();
			drawDeAssetTypeSelectboxForAdminTool();
		}

		if ($("#repaymentType").length > 0) {
			drawRepaymentTypeSelectboxForAdminTool();
			drawDeRepaymentTypeSelectboxForAdminTool();
		}

		if ($("#borrowingType").length > 0) {
			drawBorrowingTypeSelectboxForAdminTool();
			drawDeBorrowingTypeSelectboxForAdminTool();
		}

		if ($("#lendingType").length > 0) {
			drawLendingTypeSelectboxForAdminTool();
			drawDeLendingTypeSelectboxForAdminTool();
		}
	}

	drawIncomeTypeSelectboxForAdminTool = function() {
		$("#incomeType option").each(function(index) {
			$(this).remove();
		});
		$("#incomeType").selectpicker("refresh");

		commonJs.ajaxSubmit({
			url:"/common/entryTypeSupporter/getIncomeTypeForSelectbox.do",
			dataType:"json",
			data:{},
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");
				if (result.isSuccess == true || result.isSuccess == "true") {
					var ds = result.dataSet;

					for (var i=0; i<ds.getRowCnt(); i++) {
						$("#incomeType").append("<option value=\""+ds.getValue(i, "INCOME_TYPE_ID")+"\">"+ds.getValue(i, "DESCRIPTION")+"</option>");
					}
					$("#incomeType").selectpicker("refresh");
				} else {
					commonJs.error(result.message);
				}
			}
		});
	};

	drawExpenseMainTypeSelectboxForAdminTool = function() {
		$("#expenseMainType option").each(function(index) {
			$(this).remove();
		});
		$("#expenseMainType").selectpicker("refresh");

		commonJs.ajaxSubmit({
			url:"/common/entryTypeSupporter/getExpenseMainTypeForSelectbox.do",
			dataType:"json",
			data:{},
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");
				if (result.isSuccess == true || result.isSuccess == "true") {
					var ds = result.dataSet;

					if (ds.getRowCnt() > 0) {
						$("#expenseMainType").append("<option value=\"\">==Select==</option>");
						for (var i=0; i<ds.getRowCnt(); i++) {
							$("#expenseMainType").append("<option value=\""+ds.getValue(i, "EXPENSE_TYPE")+"\">"+ds.getValue(i, "DESCRIPTION")+"</option>");
						}
						$("#expenseMainType").selectpicker("refresh");
						expenseTypeMenu = [];
						setDeTypesContextMenu();
					}
				} else {
					commonJs.error(result.message);
				}
			}
		});
	};

	drawDeExpenseMainTypeSelectboxForAdminTool = function() {
		$("#deExpenseMainType option").each(function(index) {
			$(this).remove();
		});
		$("#deExpenseMainType").selectpicker("refresh");

		commonJs.ajaxSubmit({
			url:"/common/entryTypeSupporter/getExpenseMainTypeForSelectbox.do",
			dataType:"json",
			data:{},
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");
				if (result.isSuccess == true || result.isSuccess == "true") {
					var ds = result.dataSet;

					if (ds.getRowCnt() > 0) {
						$("#deExpenseMainType").append("<option value=\"\">==Select==</option>");
						for (var i=0; i<ds.getRowCnt(); i++) {
							$("#deExpenseMainType").append("<option value=\""+ds.getValue(i, "EXPENSE_TYPE")+"\">"+ds.getValue(i, "DESCRIPTION")+"</option>");
						}
						$("#deExpenseMainType").selectpicker("refresh");
					}
				} else {
					commonJs.error(result.message);
				}
			}
		});
	};

	drawAssetTypeSelectboxForAdminTool = function() {
		$("#assetType option").each(function(index) {
			$(this).remove();
		});
		$("#assetType").selectpicker("refresh");

		commonJs.ajaxSubmit({
			url:"/common/entryTypeSupporter/getAssetTypeForSelectbox.do",
			dataType:"json",
			data:{},
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");
				if (result.isSuccess == true || result.isSuccess == "true") {
					var ds = result.dataSet;

					if (ds.getRowCnt() > 0) {
						$("#assetType").append("<option value=\"\">==Select==</option>");
						for (var i=0; i<ds.getRowCnt(); i++) {
							$("#assetType").append("<option value=\""+ds.getValue(i, "ASSET_TYPE")+"\">"+ds.getValue(i, "DESCRIPTION")+"</option>");
						}
						$("#assetType").selectpicker("refresh");
						assetTypeMenu = [];
						setDeAssetTypeContextMenu();
					}
				} else {
					commonJs.error(result.message);
				}
			}
		});
	};

	drawDeAssetTypeSelectboxForAdminTool = function() {
		$("#deAssetType option").each(function(index) {
			$(this).remove();
		});
		$("#deAssetType").selectpicker("refresh");

		commonJs.ajaxSubmit({
			url:"/common/entryTypeSupporter/getAssetTypeForSelectbox.do",
			dataType:"json",
			data:{},
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");
				if (result.isSuccess == true || result.isSuccess == "true") {
					var ds = result.dataSet;

					if (ds.getRowCnt() > 0) {
						$("#deAssetType").append("<option value=\"\">==Select==</option>");
						for (var i=0; i<ds.getRowCnt(); i++) {
							$("#deAssetType").append("<option value=\""+ds.getValue(i, "ASSET_TYPE")+"\">"+ds.getValue(i, "DESCRIPTION")+"</option>");
						}
						$("#deAssetType").selectpicker("refresh");
					}
				} else {
					commonJs.error(result.message);
				}
			}
		});
	};

	drawRepaymentTypeSelectboxForAdminTool = function() {
		$("#repaymentType option").each(function(index) {
			$(this).remove();
		});
		$("#repaymentType").selectpicker("refresh");

		commonJs.ajaxSubmit({
			url:"/common/entryTypeSupporter/getRepaymentTypeForSelectbox.do",
			dataType:"json",
			data:{},
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");
				if (result.isSuccess == true || result.isSuccess == "true") {
					var ds = result.dataSet;

					if (ds.getRowCnt() > 0) {
						$("#repaymentType").append("<option value=\"\">==Select==</option>");
						for (var i=0; i<ds.getRowCnt(); i++) {
							$("#repaymentType").append("<option value=\""+ds.getValue(i, "REPAYMENT_TYPE")+"\">"+ds.getValue(i, "DESCRIPTION")+"</option>");
						}
						$("#repaymentType").selectpicker("refresh");
						repaymentTypeMenu = [];
						setDeRepaymentTypeContextMenu();
					}
				} else {
					commonJs.error(result.message);
				}
			}
		});
	};

	drawDeRepaymentTypeSelectboxForAdminTool = function() {
		$("#deRepaymentType option").each(function(index) {
			$(this).remove();
		});
		$("#deRepaymentType").selectpicker("refresh");

		commonJs.ajaxSubmit({
			url:"/common/entryTypeSupporter/getRepaymentTypeForSelectbox.do",
			dataType:"json",
			data:{},
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");
				if (result.isSuccess == true || result.isSuccess == "true") {
					var ds = result.dataSet;

					if (ds.getRowCnt() > 0) {
						$("#deRepaymentType").append("<option value=\"\">==Select==</option>");
						for (var i=0; i<ds.getRowCnt(); i++) {
							$("#deRepaymentType").append("<option value=\""+ds.getValue(i, "REPAYMENT_TYPE")+"\">"+ds.getValue(i, "DESCRIPTION")+"</option>");
						}
						$("#deRepaymentType").selectpicker("refresh");
					}
				} else {
					commonJs.error(result.message);
				}
			}
		});
	};

	drawBorrowingTypeSelectboxForAdminTool = function() {
		$("#borrowingType option").each(function(index) {
			$(this).remove();
		});
		$("#borrowingType").selectpicker("refresh");

		commonJs.ajaxSubmit({
			url:"/common/entryTypeSupporter/getBorrowingTypeForSelectbox.do",
			dataType:"json",
			data:{},
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");
				if (result.isSuccess == true || result.isSuccess == "true") {
					var ds = result.dataSet;

					if (ds.getRowCnt() > 0) {
						$("#borrowingType").append("<option value=\"\">==Select==</option>");
						for (var i=0; i<ds.getRowCnt(); i++) {
							$("#borrowingType").append("<option value=\""+ds.getValue(i, "BORROWING_TYPE")+"\">"+ds.getValue(i, "DESCRIPTION")+"</option>");
						}
						$("#borrowingType").selectpicker("refresh");
						borrowingTypeMenu = [];
						setDeBorrowingTypeContextMenu();
					}
				} else {
					commonJs.error(result.message);
				}
			}
		});
	};

	drawDeBorrowingTypeSelectboxForAdminTool = function() {
		$("#deBorrowingType option").each(function(index) {
			$(this).remove();
		});
		$("#deBorrowingType").selectpicker("refresh");

		commonJs.ajaxSubmit({
			url:"/common/entryTypeSupporter/getBorrowingTypeForSelectbox.do",
			dataType:"json",
			data:{},
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");
				if (result.isSuccess == true || result.isSuccess == "true") {
					var ds = result.dataSet;

					if (ds.getRowCnt() > 0) {
						$("#deBorrowingType").append("<option value=\"\">==Select==</option>");
						for (var i=0; i<ds.getRowCnt(); i++) {
							$("#deBorrowingType").append("<option value=\""+ds.getValue(i, "BORROWING_TYPE")+"\">"+ds.getValue(i, "DESCRIPTION")+"</option>");
						}
						$("#deBorrowingType").selectpicker("refresh");
					}
				} else {
					commonJs.error(result.message);
				}
			}
		});
	};

	drawLendingTypeSelectboxForAdminTool = function() {
		$("#lendingType option").each(function(index) {
			$(this).remove();
		});
		$("#lendingType").selectpicker("refresh");

		commonJs.ajaxSubmit({
			url:"/common/entryTypeSupporter/getLendingTypeTypeForSelectbox.do",
			dataType:"json",
			data:{},
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");
				if (result.isSuccess == true || result.isSuccess == "true") {
					var ds = result.dataSet;

					if (ds.getRowCnt() > 0) {
						$("#lendingType").append("<option value=\"\">==Select==</option>");
						for (var i=0; i<ds.getRowCnt(); i++) {
							$("#lendingType").append("<option value=\""+ds.getValue(i, "LENDING_TYPE")+"\">"+ds.getValue(i, "DESCRIPTION")+"</option>");
						}
						$("#lendingType").selectpicker("refresh");
						lendingTypeMenu = [];
						setDeLendingTypeContextMenu();
					}
				} else {
					commonJs.error(result.message);
				}
			}
		});
	};

	drawDeLendingTypeSelectboxForAdminTool = function() {
		$("#deLendingType option").each(function(index) {
			$(this).remove();
		});
		$("#deLendingType").selectpicker("refresh");

		commonJs.ajaxSubmit({
			url:"/common/entryTypeSupporter/getLendingTypeForSelectbox.do",
			dataType:"json",
			data:{},
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");
				if (result.isSuccess == true || result.isSuccess == "true") {
					var ds = result.dataSet;

					if (ds.getRowCnt() > 0) {
						$("#deLendingType").append("<option value=\"\">==Select==</option>");
						for (var i=0; i<ds.getRowCnt(); i++) {
							$("#deLendingType").append("<option value=\""+ds.getValue(i, "LENDING_TYPE")+"\">"+ds.getValue(i, "DESCRIPTION")+"</option>");
						}
						$("#deLendingType").selectpicker("refresh");
					}
				} else {
					commonJs.error(result.message);
				}
			}
		});
	};

	setSummaryIncomeForAdminTool = function() {
		commonJs.showProcMessageOnElement("tblIncomeEntrySummaryForAdminTool");

		setTimeout(function() {
			commonJs.ajaxSubmit({
				url:"/common/entrySummary/getIncomeSummary.do",
				dataType:"json",
				formId:"fmDefault",
				success:function(data, textStatus) {
					var result = commonJs.parseAjaxResult(data, textStatus, "json");
					if (result.isSuccess == true || result.isSuccess == "true") {
						var ds = result.dataSet;
						var html = "";

						$("#tblIncomeEntrySummaryForAdminTool").html("");
						for (var i=0; i<ds.getRowCnt(); i++) {
							html += "<tr>";
							html += "<td class=\"tdDefault\" style=\"padding:1px 1px\">"+ds.getValue(i, "DESCRIPTION")+"</td>";
							html += "<td class=\"tdDefaultRt\" style=\"padding:1px 1px\">"+commonJs.getNumberMask(ds.getValue(i, "INCOME_ENTRY_COUNT"), "#,###")+"</td>";
							html += "</tr>";
						}
						$("#tblIncomeEntrySummaryForAdminTool").append($(html));

						commonJs.hideProcMessageOnElement("tblIncomeEntrySummaryForAdminTool");
					} else {
						commonJs.error(result.message);
					}
				}
			});
		}, 50);
	};

	setSummaryExpenseForAdminTool = function() {
		commonJs.showProcMessageOnElement("tblExpenseEntrySummaryForAdminTool");

		setTimeout(function() {
			commonJs.ajaxSubmit({
				url:"/common/entrySummary/getExpenseSummary.do",
				dataType:"json",
				formId:"fmDefault",
				success:function(data, textStatus) {
					var result = commonJs.parseAjaxResult(data, textStatus, "json");
					if (result.isSuccess == true || result.isSuccess == "true") {
						var ds = result.dataSet;
						var html = "";

						$("#tblExpenseEntrySummaryForAdminTool").html("");
						for (var i=0; i<ds.getRowCnt(); i++) {
							html += "<tr>";
							html += "<td class=\"tdDefault\" style=\"padding:1px 1px\">"+ds.getValue(i, "DESCRIPTION")+"</td>";
							html += "<td class=\"tdDefaultRt\" style=\"padding:1px 1px\">"+commonJs.getNumberMask(ds.getValue(i, "EXPENSE_ENTRY_COUNT"), "#,###")+"</td>";
							html += "</tr>";
						}
						$("#tblExpenseEntrySummaryForAdminTool").append($(html));

						commonJs.hideProcMessageOnElement("tblExpenseEntrySummaryForAdminTool");
					} else {
						commonJs.error(result.message);
					}
				}
			});
		}, 50);
	};

	setSummaryAssetForAdminTool = function() {
		commonJs.showProcMessageOnElement("tblAssetEntrySummaryForAdminTool");

		setTimeout(function() {
			commonJs.ajaxSubmit({
				url:"/common/entrySummary/getAssetSummary.do",
				dataType:"json",
				formId:"fmDefault",
				success:function(data, textStatus) {
					var result = commonJs.parseAjaxResult(data, textStatus, "json");
					if (result.isSuccess == true || result.isSuccess == "true") {
						var ds = result.dataSet;
						var html = "";

						$("#tblAssetEntrySummaryForAdminTool").html("");
						for (var i=0; i<ds.getRowCnt(); i++) {
							html += "<tr>";
							html += "<td class=\"tdDefault\" style=\"padding:1px 1px\">"+ds.getValue(i, "DESCRIPTION")+"</td>";
							html += "<td class=\"tdDefaultRt\" style=\"padding:1px 1px\">"+commonJs.getNumberMask(ds.getValue(i, "ASSET_ENTRY_COUNT"), "#,###")+"</td>";
							html += "</tr>";
						}
						$("#tblAssetEntrySummaryForAdminTool").append($(html));

						commonJs.hideProcMessageOnElement("tblAssetEntrySummaryForAdminTool");
					} else {
						commonJs.error(result.message);
					}
				}
			});
		}, 50);
	};

	setSummaryRepaymentForAdminTool = function() {
		commonJs.showProcMessageOnElement("tblRepaymentEntrySummaryForAdminTool");

		setTimeout(function() {
			commonJs.ajaxSubmit({
				url:"/common/entrySummary/getRepaymentSummary.do",
				dataType:"json",
				formId:"fmDefault",
				success:function(data, textStatus) {
					var result = commonJs.parseAjaxResult(data, textStatus, "json");
					if (result.isSuccess == true || result.isSuccess == "true") {
						var ds = result.dataSet;
						var html = "";

						$("#tblRepaymentEntrySummaryForAdminTool").html("");
						for (var i=0; i<ds.getRowCnt(); i++) {
							html += "<tr>";
							html += "<td class=\"tdDefault\" style=\"padding:1px 1px\">"+ds.getValue(i, "DESCRIPTION")+"</td>";
							html += "<td class=\"tdDefaultRt\" style=\"padding:1px 1px\">"+commonJs.getNumberMask(ds.getValue(i, "FINANCE_ENTRY_COUNT"), "#,###")+"</td>";
							html += "</tr>";
						}
						$("#tblRepaymentEntrySummaryForAdminTool").append($(html));

						commonJs.hideProcMessageOnElement("tblRepaymentEntrySummaryForAdminTool");
					} else {
						commonJs.error(result.message);
					}
				}
			});
		}, 50);
	};

	setSummaryBorrowingForAdminTool = function() {
		commonJs.showProcMessageOnElement("tblBorrowingEntrySummaryForAdminTool");

		setTimeout(function() {
			commonJs.ajaxSubmit({
				url:"/common/entrySummary/getBorrowingSummary.do",
				dataType:"json",
				formId:"fmDefault",
				success:function(data, textStatus) {
					var result = commonJs.parseAjaxResult(data, textStatus, "json");
					if (result.isSuccess == true || result.isSuccess == "true") {
						var ds = result.dataSet;
						var html = "";

						$("#tblBorrowingEntrySummaryForAdminTool").html("");
						for (var i=0; i<ds.getRowCnt(); i++) {
							html += "<tr>";
							html += "<td class=\"tdDefault\" style=\"padding:1px 1px\">"+ds.getValue(i, "DESCRIPTION")+"</td>";
							html += "<td class=\"tdDefaultRt\" style=\"padding:1px 1px\">"+commonJs.getNumberMask(ds.getValue(i, "FINANCE_ENTRY_COUNT"), "#,###")+"</td>";
							html += "</tr>";
						}
						$("#tblBorrowingEntrySummaryForAdminTool").append($(html));

						commonJs.hideProcMessageOnElement("tblBorrowingEntrySummaryForAdminTool");
					} else {
						commonJs.error(result.message);
					}
				}
			});
		}, 50);
	};

	setSummaryLendingForAdminTool = function() {
		commonJs.showProcMessageOnElement("tblLendingEntrySummaryForAdminTool");

		setTimeout(function() {
			commonJs.ajaxSubmit({
				url:"/common/entrySummary/getLendingSummary.do",
				dataType:"json",
				formId:"fmDefault",
				success:function(data, textStatus) {
					var result = commonJs.parseAjaxResult(data, textStatus, "json");
					if (result.isSuccess == true || result.isSuccess == "true") {
						var ds = result.dataSet;
						var html = "";

						$("#tblLendingEntrySummaryForAdminTool").html("");
						for (var i=0; i<ds.getRowCnt(); i++) {
							html += "<tr>";
							html += "<td class=\"tdDefault\" style=\"padding:1px 1px\">"+ds.getValue(i, "DESCRIPTION")+"</td>";
							html += "<td class=\"tdDefaultRt\" style=\"padding:1px 1px\">"+commonJs.getNumberMask(ds.getValue(i, "FINANCE_ENTRY_COUNT"), "#,###")+"</td>";
							html += "</tr>";
						}
						$("#tblLendingEntrySummaryForAdminTool").append($(html));

						commonJs.hideProcMessageOnElement("tblLendingEntrySummaryForAdminTool");
					} else {
						commonJs.error(result.message);
					}
				}
			});
		}, 50);
	};

	setSummaryEmployeeForAdminTool = function() {
		commonJs.showProcMessageOnElement("tblEmployeeEntrySummaryBodyForAdminTool");

		setTimeout(function() {
			commonJs.ajaxSubmit({
				url:"/common/entrySummary/getEmployeeSummary.do",
				dataType:"json",
				formId:"fmDefault",
				success:function(data, textStatus) {
					var result = commonJs.parseAjaxResult(data, textStatus, "json");
					if (result.isSuccess == true || result.isSuccess == "true") {
						var ds = result.dataSet;
						var html = "";

						$("#tblEmployeeEntrySummaryBodyForAdminTool").html("");
						for (var i=0; i<ds.getRowCnt(); i++) {
							html += "<tr>";
							html += "<td class=\"tdAdminToolGrid\">"+ds.getValue(i, "DESCRIPTION")+"</td>";
							html += "<td class=\"tdAdminToolGridRt\">"+commonJs.getNumberMask(ds.getValue(i, "EMP_ENTRY_COUNT"), "#,###")+"</td>";
							html += "<td class=\"tdAdminToolGridRt\">"+commonJs.getNumberMask(ds.getValue(i, "EMP_WAGE_ENTRY_COUNT"), "#,###")+"</td>";
							html += "<td class=\"tdAdminToolGridRt\">"+commonJs.getNumberMask(ds.getValue(i, "TOT_GROSS_WAGE"), "#,###")+"</td>";
							html += "<td class=\"tdAdminToolGridRt\">"+commonJs.getNumberMask(ds.getValue(i, "TOT_TAX"), "#,###")+"</td>";
							html += "<td class=\"tdAdminToolGridRt\">"+commonJs.getNumberMask(ds.getValue(i, "TOT_SUPER_AMT"), "#,###")+"</td>";
							html += "<td class=\"tdAdminToolGridRt\">"+commonJs.getNumberMask(ds.getValue(i, "TOT_NET_WAGE"), "#,###")+"</td>";
							html += "</tr>";
						}
						$("#tblEmployeeEntrySummaryBodyForAdminTool").append($(html));

						commonJs.hideProcMessageOnElement("tblEmployeeEntrySummaryBodyForAdminTool");
					} else {
						commonJs.error(result.message);
					}
				}
			});
		}, 50);
	};

	setSessionValuesForAdminTool = function() {
		if (commonJs.isEmpty($("#userIdAdminTool").val())) {return;}

		commonJs.ajaxSubmit({
			url:"/login/setSessionValuesForAdminTool.do",
			dataType:"json",
			data:{userId:$("#userIdAdminTool").val()},
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");
				if (result.isSuccess == true || result.isSuccess == "true") {
					var ds = result.dataSet;

					$("#userNameAsAdminTool").val(ds.getValue(0, "user_name"));
					$("#loginIdAsAdminTool").val(ds.getValue(0, "login_id"));
					$("#userIdAdminTool").val(ds.getValue(0, "user_id"));
					$("#orgIdForAdminTool").val(ds.getValue(0, "org_id"));
					$("#orgLegalNameForAdminTool").val(ds.getValue(0, "org_name"));
					$("#orgCategoryDescForAdminTool").val(ds.getValue(0, "org_category_desc"));

					$("#divUsingUserAs").html("User Name As "+ds.getValue(0, "user_name")+" / User Login ID As "+ds.getValue(0, "login_id")+" ("+ds.getValue(0, "org_id")+" / "+ds.getValue(0, "org_name")+" / "+ds.getValue(0, "org_category_desc")+")");
					if ($("#divUsingUserAsBreaker").length <= 0) {
						$("<div id=\"divUsingUserAsBreaker\" class=\"divGblMenuBreak\">&nbsp;</div>").insertAfter($("#divUsingUserAs"));
					}

					setSummaryDataForAdminTool();
					setMainScreenSearchCriteriaForAdminTool();

					try {
						doSearch();
					} catch(e) {}

//					goMenu('${sessionScope.headerMenuId}', '${sessionScope.headerMenuName}', '${sessionScope.headerMenuUrl}', '${sessionScope.leftMenuId}', '${sessionScope.leftMenuName}', '${sessionScope.leftMenuUrl}');
				} else {
					commonJs.error(result.message);
				}
			}
		});
	};

	removeSessionValuesForAdminTool = function() {
		commonJs.ajaxSubmit({
			url:"/login/removeSessionValuesForAdminTool.do",
			dataType:"json",
			data:{userId:""},
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");
				if (result.isSuccess == true || result.isSuccess == "true") {
					$("#userNameAsAdminTool").val("");
					$("#loginIdAsAdminTool").val("");
					$("#userIdAdminTool").val("");
					$("#orgIdForAdminTool").val("");
					$("#orgLegalNameForAdminTool").val("");
					$("#orgCategoryDescForAdminTool").val("");

					$("#divUsingUserAs").html("");
					$("#divUsingUserAsBreaker").remove();

					setSummaryDataForAdminTool();
					setMainScreenSearchCriteriaForAdminTool();

					try {
						doSearch();
					} catch(e) {}

//					goMenu('${sessionScope.headerMenuId}', '${sessionScope.headerMenuName}', '${sessionScope.headerMenuUrl}', '${sessionScope.leftMenuId}', '${sessionScope.leftMenuName}', '${sessionScope.leftMenuUrl}');
				} else {
					commonJs.error(result.message);
				}
			}
		});
	};

	$(window).load(function() {
		commonJs.setAutoComplete($("#userNameAsAdminTool"), {
			method:"getUserName",
			label:"user_name",
			value:"user_id",
			focus:function(event, ui) {
				$("#userNameAsAdminTool").val(ui.item.label);
				return false;
			},
			change:function(event, ui) {
				if (commonJs.isEmpty($("#userNameAsAdminTool").val()) && commonJs.isEmpty($("#loginIdAsAdminTool").val())) {
					$("#userIdAdminTool").val("");
					$("#orgIdForAdminTool").val("");
					$("#orgLegalNameForAdminTool").val("");
					$("#orgCategoryDescForAdminTool").val("");
				}
			},
			select:function(event, ui) {
				$("#userIdAdminTool").val(ui.item.value);
				$("#userNameAsAdminTool").val(ui.item.label);
				setSessionValuesForAdminTool();
				return false;
			}
		});

		commonJs.setAutoComplete($("#loginIdAsAdminTool"), {
			method:"getLoginId",
			label:"login_id",
			value:"user_id",
			focus:function(event, ui) {
				$("#loginIdAsAdminTool").val(ui.item.label);
				return false;
			},
			change:function(event, ui) {
				if (commonJs.isEmpty($("#userNameAsAdminTool").val()) && commonJs.isEmpty($("#loginIdAsAdminTool").val())) {
					$("#userIdAdminTool").val("");
					$("#orgIdForAdminTool").val("");
					$("#orgLegalNameForAdminTool").val("");
					$("#orgCategoryDescForAdminTool").val("");
				}
			},
			select:function(event, ui) {
				$("#userIdAdminTool").val(ui.item.value);
				$("#loginIdAsAdminTool").val(ui.item.label);
				setSessionValuesForAdminTool();
				return false;
			}
		});
	});
});
</script>

<%
if (CommonUtil.equalsIgnoreCase(authGroupIdAdminToolArea, "0") && isVisibleAdminToolLocationPath) {
%>
<div id="divAdminToolContainer" class="adminToolContainer" style="overflow-y:hidden;">
	<table class="tblAdminTool">
		<caption><mc:msg key="page.com.adminTool"/></caption>
		<colgroup>
			<col width="20%"/>
			<col width="37%"/>
			<col width="*"/>
		</colgroup>
		<tr>
			<td class="tdAdminToolRt" style="vertical-align:top;">
				<table class="tblDefault">
					<colgroup>
						<col width="37%"/>
						<col width="*"/>
					</colgroup>
					<tr>
						<td class="tdDefaultRt" style="padding:0px 0px 2px 0px;" colspan="2">
							<tag:buttonGroup id="buttonGroup">
								<tag:button id="btnReloadUserAdminTool" caption="button.com.reload" iconClass="fa-refresh"/>
								<tag:button id="btnReturnUserAdminTool" caption="button.com.return" iconClass="fa-history"/>
							</tag:buttonGroup>
						</td>
					</tr>
					<tr>
						<th class="thDefault" style="padding:2px 2px 1px 0px;"><mc:msg key="page.com.loginUserNameAs"/></th>
						<td class="tdDefault" style="padding:2px 0px 1px 2px;">
							<input type="text" id="userNameAsAdminTool" name="userNameAsAdminTool" value="${sessionScope.UserNameForAdminTool}" class="txtEn"/>
						</td>
					</tr>
					<tr>
						<th class="thDefault" style="padding:1px 2px 1px 0px;"><mc:msg key="page.com.loginUserLoginIdAs"/></th>
						<td class="tdDefault" style="padding:1px 0px 1px 2px;">
							<input type="text" id="loginIdAsAdminTool" name="loginIdAsAdminTool" value="${sessionScope.LoginIdForAdminTool}" class="txtEn"/>
						</td>
					</tr>
					<tr>
						<td class="tdDefault" style="padding:4px 0px 0px 0px;" colspan="2">
							<input type="hidden" id="userIdAdminTool" name="loginIdAdminTool" value="${sessionScope.UserIdForAdminTool}" class="txtDpl"/>
							<input type="text" id="orgIdForAdminTool" name="orgIdForAdminTool" class="txtDplRt" value="${sessionScope.OrgIdForAdminTool}" disabled style="padding-top:1px;padding-bottom:1px;color:red;font-weight:bold;"/>
							<input type="text" id="orgLegalNameForAdminTool" name="orgLegalNameForAdminTool" class="txtDplRt" value="${sessionScope.OrgLegalNameForAdminTool}" disabled style="padding-top:1px;padding-bottom:1px;color:red;font-weight:bold;"/>
							<input type="text" id="orgCategoryDescForAdminTool" name="orgCategoryDescForAdminTool" class="txtDplRt" value="${sessionScope.OrgCategoryDescForAdminTool}" disabled style="padding-top:1px;padding-bottom:1px;color:red;font-weight:bold;"/>
						</td>
					</tr>
				</table>
			</td>
			<td class="tdAdminTool" style="vertical-align:top;">
				<table class="tblAdminToolGrid">
					<colgroup>
						<col width="32%"/>
						<col width="*"/>
						<col width="32%"/>
					</colgroup>
					<thead>
						<tr>
							<th class="thAdminToolGrid"><mc:msg key="page.com.headerIncome"/></th>
							<th class="thAdminToolGrid"><mc:msg key="page.com.headerExpense"/></th>
							<th class="thAdminToolGrid"><mc:msg key="page.com.headerAsset"/></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="tdAdminToolGrid">
								<table id="tblIncomeEntrySummaryForAdminTool" class="tblDefault">
								</table>
							</td>
							<td class="tdAdminToolGrid">
								<table id="tblExpenseEntrySummaryForAdminTool" class="tblDefault">
								</table>
							</td>
							<td class="tdAdminToolGrid">
								<table id="tblAssetEntrySummaryForAdminTool" class="tblDefault">
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</td>
			<td class="tdAdminTool" style="vertical-align:top;">
				<table class="tblAdminToolGrid" style="border-bottom:0px;">
					<colgroup>
						<col width="33%"/>
						<col width="34%"/>
						<col width="33%"/>
					</colgroup>
					<thead>
						<tr>
							<th class="thAdminToolGrid"><mc:msg key="page.com.headerRepayment"/></th>
							<th class="thAdminToolGrid"><mc:msg key="page.com.headerBorrowing"/></th>
							<th class="thAdminToolGrid"><mc:msg key="page.com.headerLending"/></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="tdAdminToolGrid">
								<table id="tblRepaymentEntrySummaryForAdminTool" class="tblDefault">
								</table>
							</td>
							<td class="tdAdminToolGrid">
								<table id="tblBorrowingEntrySummaryForAdminTool" class="tblDefault">
								</table>
							</td>
							<td class="tdAdminToolGrid">
								<table id="tblLendingEntrySummaryForAdminTool" class="tblDefault">
								</table>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="verGap2"></div>
				<table class="tblAdminToolGrid">
					<colgroup>
						<col width="*"/>
						<col width="13%"/>
						<col width="13%"/>
						<col width="15%"/>
						<col width="13%"/>
						<col width="13%"/>
						<col width="15%"/>
					</colgroup>
					<thead>
						<tr>
							<th class="thAdminToolGrid"><mc:msg key="page.com.headerVisaStatus"/></th>
							<th class="thAdminToolGrid"><mc:msg key="page.com.headerEmpCnt"/></th>
							<th class="thAdminToolGrid"><mc:msg key="page.com.headerWageCnt"/></th>
							<th class="thAdminToolGrid"><mc:msg key="page.com.headerGrossWage"/></th>
							<th class="thAdminToolGrid"><mc:msg key="page.com.headerTax"/></th>
							<th class="thAdminToolGrid"><mc:msg key="page.com.headerSuper"/></th>
							<th class="thAdminToolGrid"><mc:msg key="page.com.headerNetWage"/></th>
						</tr>
					</thead>
					<tbody id="tblEmployeeEntrySummaryBodyForAdminTool">
					</tbody>
				</table>
			</td>
		</tr>
	</table>
</div>
<%
}
%>