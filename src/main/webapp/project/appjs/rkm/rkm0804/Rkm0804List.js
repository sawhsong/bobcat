/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Rkm0804List.js
 *************************************************************************************************/
jsconfig.put("useJqTooltip", false);
var numberFormat = "#,##0.00";
var widthEmpListDiv, widthWageListDiv, empListWidthAdjust, wageListWidthAdjust;

$(function() {
	/*!
	 * event
	 */
	$("#btnDelete").click(function(event) {
		doDelete();
	});

	$("#btnSearch").click(function(event) {
		doSearch();
	});

	$("#btnClear").click(function(event) {
		commonJs.clearSearchCriteria();
		refreshDataEntry();
	});

	$("#icnCheck").click(function(event) {
		commonJs.toggleCheckboxes("chkForDel");
	});

	$("#icnDeStartDate").click(function(event) {
		commonJs.openCalendar(event, "deStartDate");
	});

	$("#icnDeEndDate").click(function(event) {
		commonJs.openCalendar(event, "deEndDate");
	});

	$("#financialYear").change(function() {
		doSearch();
	});

	$("#quarterName").change(function() {
		doSearch();
	});

	$("#visaType").change(function() {
		doSearch();
	});

	$(document).keyup(function(event) {
		var element = event.target;
		if (event.which == 13) {
			if ($(element).attr("name") == "deRemark") {
				doSave();
			}
		}
		onEditDataEntry($(element));
	});

	$("input:text").focus(function() {
		if ($(this).hasClass("txtEn")) {
			$(this).select();
		}
	});

	setDataEntryActionButtonContextMenu = function() {
		ctxMenu.dataEntryAction[0].fun = function() {};
		ctxMenu.dataEntryAction[1].fun = function() {};
		ctxMenu.dataEntryAction[2].fun = function() {};

		$("#icnDataEntryAction").contextMenu(ctxMenu.dataEntryAction, {
			classPrefix:com.constants.ctxClassPrefixGrid,
			effectDuration:300,
			borderRadius:"bottom 4px",
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0
		});
	};

	/*!
	 * process
	 */
	doSearch = function() {
		commonJs.showProcMessageOnElement("tblEmpList");

		refreshDataEntry();

		setTimeout(function() {
			commonJs.ajaxSubmit({
				url:"/rkm/0804/getEmployeeList.do",
				dataType:"json",
				formId:"fmDefault",
				success:function(data, textStatus) {
					var result = commonJs.parseAjaxResult(data, textStatus, "json");

					if (result.isSuccess == true || result.isSuccess == "true") {
						renderEmpListGridTable(result);
					} else {
						commonJs.error(result.message);
					}
				}
			});
		}, 400);

		setSummaryDataForAdminTool();
	};

	renderEmpListGridTable = function(result) {
		var ds = result.dataSet;
		var html = "", totHtml = "", grossTotal = 0, taxTotal = 0, netTotal = 0, superTotal = 0;
		var totGridTr = new UiGridTr();

		searchResultDataCount = ds.getRowCnt();
		$("#tblEmpListBody").html("");
		$("#tblEmpListFoot").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr();

				grossTotal += parseFloat(ds.getValue(i, "GROSS_WAGE"));
				taxTotal += parseFloat(ds.getValue(i, "TAX"));
				netTotal += parseFloat(ds.getValue(i, "NET_WAGE"));
				superTotal += parseFloat(ds.getValue(i, "SUPER_AMT"));

				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(new UiRadio().setName("rdoEmployee").setValue(ds.getValue(i, "EMPLOYEE_ID")).setScript("getWageList('"+$("#financialYear").val()+"', '"+$("#quarterName").val()+"', '"+ds.getValue(i, "EMPLOYEE_ID")+"')")));

				var uiAncSurname = new UiAnchor();
				uiAncSurname.setText(ds.getValue(i, "SURNAME")).setScript("getWageList('"+$("#financialYear").val()+"', '"+$("#quarterName").val()+"', '"+ds.getValue(i, "EMPLOYEE_ID")+"')");
				gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAncSurname));

				var uiAncGivenName = new UiAnchor();
				uiAncGivenName.setText(ds.getValue(i, "GIVEN_NAME")).setScript("getWageList('"+$("#financialYear").val()+"', '"+$("#quarterName").val()+"', '"+ds.getValue(i, "EMPLOYEE_ID")+"')");
				gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAncGivenName));

				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "GROSS_WAGE"), numberFormat)));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "TAX"), numberFormat)));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "NET_WAGE"), numberFormat)));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "SUPER_AMT"), numberFormat)));

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:7").setText(com.message.I001));
			html += gridTr.toHtmlString();
		}

		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Ct").setText(com.caption.total));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(grossTotal, numberFormat)));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(taxTotal, numberFormat)));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(netTotal, numberFormat)));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(superTotal, numberFormat)));

		totHtml = totGridTr.toHtmlString();

		$("#tblEmpListBody").append($(html));
		$("#tblEmpListFoot").append($(totHtml));

		$("#tblEmpList").fixedHeaderTable({
			attachTo:$("#divEmpList"),
			pagingArea:$("#divEmpListPagingArea"),
			isPageable:false,
			isFilter:false,
			filterColumn:[],
			totalResultRows:result.totalResultRows,
			script:"doSearch"
		});

		commonJs.hideProcMessageOnElement("tblEmpList");
		getWageList("", "", "");
	};

	getWageList = function(financialYear, quarterName, employeeId) {
		commonJs.showProcMessageOnElement("tblWageList");

		$("input[name=rdoEmployee]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == employeeId) {
				$(this).prop("checked", true);
			} else {
				$(this).prop("checked", false);
			}
		});

		setTimeout(function() {
			commonJs.ajaxSubmit({
				url:"/rkm/0804/getWageList.do",
				dataType:"json",
				data:{
					financialYear:financialYear,
					quarterName:quarterName,
					employeeId:employeeId
				},
				success:function(data, textStatus) {
					var result = commonJs.parseAjaxResult(data, textStatus, "json");

					if (result.isSuccess == true || result.isSuccess == "true") {
						renderWageListGridTable(result);
					} else {
						commonJs.error(result.message);
					}
				}
			});
		}, 200);
	};

	renderWageListGridTable = function(result) {
		var ds = result.dataSet;
		var html = "", totHtml = "", hourWorked = 0, grossTotal = 0, taxTotal = 0, netTotal = 0, superTotal = 0;
		var totGridTr = new UiGridTr();

		$("#tblWageListBody").html("");
		$("#tblWageListFoot").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr();

				hourWorked += parseFloat(ds.getValue(i, "HOUR_WORKED"));
				grossTotal += parseFloat(ds.getValue(i, "GROSS_WAGE"));
				taxTotal += parseFloat(ds.getValue(i, "TAX"));
				netTotal += parseFloat(ds.getValue(i, "NET_WAGE"));
				superTotal += parseFloat(ds.getValue(i, "SUPER_AMT"));

				var uiChk = new UiCheckbox();
				uiChk.setId("chkForDel").setName("chkForDel").setValue(ds.getValue(i, "WAGE_ID"));
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiChk));

				var uiAncStartDate = new UiAnchor();
				uiAncStartDate.setText(ds.getValue(i, "START_DATE")).setScript("getEdit('"+ds.getValue(i, "WAGE_ID")+"')");
				gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAncStartDate));

				var uiAncEndDate = new UiAnchor();
				uiAncEndDate.setText(ds.getValue(i, "END_DATE")).setScript("getEdit('"+ds.getValue(i, "WAGE_ID")+"')");
				gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAncEndDate));

				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "HOURLY_RATE"), numberFormat)));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "HOUR_WORKED"), numberFormat)));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "GROSS_WAGE"), numberFormat)));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "TAX"), numberFormat)));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "NET_WAGE"), numberFormat)));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "SUPER_AMT"), numberFormat)));

				var iconAction = new UiIcon();
				iconAction.setId("icnAction").setName("icnAction").addClassName("fa-tasks fa-lg").addAttribute("wageId:"+ds.getValue(i, "WAGE_ID")).setScript("doAction(this)");
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(iconAction));

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:10").setText(com.message.I001));
			html += gridTr.toHtmlString();
		}

		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Ct").setText(com.caption.total));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(hourWorked, numberFormat)));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(grossTotal, numberFormat)));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(taxTotal, numberFormat)));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(netTotal, numberFormat)));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(superTotal, numberFormat)));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));

		totHtml = totGridTr.toHtmlString();

		$("#tblWageListBody").append($(html));
		$("#tblWageListFoot").append($(totHtml));

		$("#tblWageList").fixedHeaderTable({
			attachTo:$("#divWageList"),
			pagingArea:$("#divWageListPagingArea"),
			isPageable:false,
			isFilter:false,
			filterColumn:[],
			totalResultRows:result.totalResultRows,
			script:"doSearch"
		});

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenu.commonSimpleAction);
		});

		commonJs.hideProcMessageOnElement("tblWageList");
	};

	onEditDataEntry = function(jqObj) {
		var name = $(jqObj).attr("name");

		if (name == "deGrossWage" && $(jqObj).val() > 0) {
			var selectedEmployeeId = commonJs.getCheckedValueFromRadio("rdoEmployee");

			if (commonJs.isEmpty(selectedEmployeeId)) {
				commonJs.error(rkm.rkm0804.message.noEmployeeSelected);
				return;
			}

			commonJs.ajaxSubmit({
				url:"/rkm/0804/calculateDataEntry.do",
				dataType:"json",
				data:{
					financialYear:$("#financialYear").val(),
					employeeId:commonJs.getCheckedValueFromRadio("rdoEmployee"),
					hourlyRate:$("#deHourlyRate").val(),
					hourlyWorked:$("#deHoursWorked").val(),
					grossWage:$("#deGrossWage").val()
				},
				success:function(data, textStatus) {
					var result = commonJs.parseAjaxResult(data, textStatus, "json");

					if (result.isSuccess == true || result.isSuccess == "true") {
						var ds = result.dataSet;
						$("#deTax").val(ds.getValue(0, "tax"));
						$("#deNetWage").val(ds.getValue(0, "netWage"));
						$("#deSuper").val(ds.getValue(0, "super"));
					} else {
						commonJs.error(result.message);
					}
				}
			});
		}
	};

	getEdit = function(wageId) {
		$("input:checkbox[name=chkForDel]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == wageId) {
				$(this).prop("checked", true);
			} else {
				$(this).prop("checked", false);
			}
		});

		commonJs.ajaxSubmit({
			url:"/rkm/0804/getEdit.do",
			dataType:"json",
			data:{
				wageId:wageId
			},
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");

				if (result.isSuccess == true || result.isSuccess == "true") {
					var ds = result.dataSet;

					refreshDataEntry();
					setDataEntryValues(ds);
					$("#deGrossWage").focus();
				} else {
					commonJs.error(result.message);
				}
			}
		});
	};

	doSave = function() {
		var selectedEmployeeId = commonJs.getCheckedValueFromRadio("rdoEmployee");
		if (commonJs.isEmpty(selectedEmployeeId)) {
			commonJs.error(rmk.rkm0804.message.noEmployeeSelected);
			return;
		}

		if (!commonJs.doValidate("fmDefault")) {
			return;
		}

		commonJs.confirm({
			contents:com.message.Q001,
			buttons:[{
				caption:com.caption.yes,
				callback:function() {
					commonJs.ajaxSubmit({
						url:"/rkm/0804/exeSave.do",
						dataType:"json",
						formId:"fmDefault",
						success:function(data, textStatus) {
							var result = commonJs.parseAjaxResult(data, textStatus, "json");

							if (result.isSuccess == true || result.isSuccess == "true") {
								commonJs.openDialog({
									type:com.message.I000,
									contents:result.message,
									blind:true,
									width:300,
									buttons:[{
										caption:com.caption.ok,
										callback:function() {
											getWageList($("#financialYear").val(), $("#quarterName").val(), selectedEmployeeId);
										}
									}]
								});
							} else {
								commonJs.error(result.message);
							}
						}
					});
				}
			}, {
				caption:com.caption.no,
				callback:function() {
				}
			}]
		});
	};

	doDelete = function() {
		if (commonJs.getCountChecked("chkForDel") == 0) {
			commonJs.warn(com.message.I902);
			return;
		}

		commonJs.confirm({
			contents:com.message.Q002,
			buttons:[{
				caption:com.caption.yes,
				callback:function() {
					commonJs.ajaxSubmit({
						url:"/rkm/0804/exeDelete.do",
						dataType:"json",
						formId:"fmDefault",
						success:function(data, textStatus) {
							var result = commonJs.parseAjaxResult(data, textStatus, "json");

							if (result.isSuccess == true || result.isSuccess == "true") {
								commonJs.openDialog({
									type:com.message.I000,
									contents:result.message,
									blind:true,
									width:300,
									buttons:[{
										caption:com.caption.ok,
										callback:function() {
											getWageList($("#financialYear").val(), $("#quarterName").val(), commonJs.getCheckedValueFromRadio("rdoEmployee"));
										}
									}]
								});
							} else {
								commonJs.error(result.message);
							}
						}
					});
				}
			}, {
				caption:com.caption.no,
				callback:function() {
				}
			}],
			blind:true
		});
	};

	doAction = function(img) {
		var wageId = $(img).attr("wageId");

		$("input:checkbox[name=chkForDel]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == wageId) {
				$(this).prop("checked", true);
			} else {
				$(this).prop("checked", false);
			}
		});

		ctxMenu.commonSimpleAction[0].fun = function() {getEdit(wageId);};
		ctxMenu.commonSimpleAction[1].fun = function() {doDelete();};

		$(img).contextMenu(ctxMenu.commonSimpleAction, {
			classPrefix:com.constants.ctxClassPrefixGrid,
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0,
			verAdjust:2
		});
	};

	doDataEntryAction = function(img) {
		ctxMenu.dataEntryAction[0].fun = function() {doSave();};
		ctxMenu.dataEntryAction[1].fun = function() {refreshDataEntry();};
		ctxMenu.dataEntryAction[2].fun = function() {doDelete();};

		$(img).contextMenu(ctxMenu.dataEntryAction, {
			classPrefix:com.constants.ctxClassPrefixGrid,
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0,
			verAdjust:2
		});
	};

	refreshDataEntry = function() {
		$("#divInformArea").find(":input").each(function() {
			if ($(this).prop("type") == "checkbox" || $(this).prop("type") == "radio") {
				$(this).attr("checked", false);
			} else {
				$(this).val("");
			}
		});
	};

	setDataEntryValues = function(dataSet) {
		$("#deWageId").val(commonJs.nvl(dataSet.getValue(0, "WAGE_ID"), ""));
		$("#deStartDate").val(commonJs.nvl(dataSet.getValue(0, "START_DATE"), ""));
		$("#deEndDate").val(commonJs.nvl(dataSet.getValue(0, "END_DATE"), ""));
		$("#deHourlyRate").val(commonJs.getNumberMask(dataSet.getValue(0, "HOURLY_RATE"), numberFormat));
		$("#deHoursWorked").val(commonJs.getNumberMask(dataSet.getValue(0, "HOUR_WORKED"), numberFormat));
		$("#deGrossWage").val(commonJs.getNumberMask(dataSet.getValue(0, "GROSS_WAGE"), numberFormat));
		$("#deTax").val(commonJs.getNumberMask(dataSet.getValue(0, "TAX"), numberFormat));
		$("#deNetWage").val(commonJs.getNumberMask(dataSet.getValue(0, "NET_WAGE"), numberFormat));
		$("#deSuper").val(commonJs.getNumberMask(dataSet.getValue(0, "SUPER_AMT"), numberFormat));
		$("#deRemark").val(dataSet.getValue(0, "DESCRIPTION"));
	};

	exeExport = function(menuObject) {
		$("[name=fileType]").remove();
		$("[name=dataRange]").remove();

		if (searchResultDataCount <= 0) {
			commonJs.warn(com.message.I001);
			return;
		}

		commonJs.confirm({
			contents:com.message.Q003,
			buttons:[{
				caption:com.caption.yes,
				callback:function() {
					var param = commonJs.serialiseObject($("#divSearchCriteriaArea"));
					param.fileType = menuObject.fileType;
					param.dataRange = menuObject.dataRange;

					popup = commonJs.openPopup({
						popupId:"exportFile",
						url:"/rkm/0804/exeExport.do",
						paramData:param,
						header:"exportFile",
						blind:false,
						width:200,
						height:100
					});
				}
			}, {
				caption:com.caption.no,
				callback:function() {
				}
			}]
		});
	};

	setGridSize = function() {
		$("#divScrollablePanel").height($("#divScrollablePanel").outerHeight()-2);
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		setGridSize();
		commonJs.setExportButtonContextMenu($("#btnExport"));
		setDataEntryActionButtonContextMenu();
		commonJs.setAutoComplete($("#surname"), {
			method:"getEmployeeSurname",
			label:"surname",
			value:"surname",
			focus:function(event, ui) {
				$("#surname").val(ui.item.label);
				return false;
			},
			select:function(event, ui) {
				doSearch();
				return false;
			}
		});

		commonJs.setAutoComplete($("#givenName"), {
			method:"getEmployeeGivenName",
			label:"given_name",
			value:"given_name",
			focus:function(event, ui) {
				$("#givenName").val(ui.item.label);
				return false;
			},
			select:function(event, ui) {
				doSearch();
				return false;
			}
		});

		commonJs.setFieldDateMask("deStartDate");
		commonJs.setFieldDateMask("deEndDate");
		$(".numeric").number(true, 2);
		doSearch();
	});
});