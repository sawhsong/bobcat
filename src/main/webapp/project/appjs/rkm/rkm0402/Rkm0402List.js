/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Rkm0402List.js
 *************************************************************************************************/
jsconfig.put("useJqTooltip", false);
var searchResultDataCount = 0;
var numberFormat = "#,##0.00";
var expenseTypeMenu = [];

$(function() {
	/*!
	 * event
	 */
	$("#btnComplete").click(function(event) {
		doComplete();
	});

	$("#btnDelete").click(function(event) {
		doDelete();
	});

	$("#btnSearch").click(function(event) {
		doSearch();
	});

	$("#btnClear").click(function(event) {
		commonJs.clearSearchCriteria();
		flushSubType("expenseSubType");
		refreshDataEntry();
	});

	$("#icnCheck").click(function(event) {
		commonJs.toggleCheckboxes("chkForDel");
	});

	$("#icnDataEntryDate").click(function(event) {
		commonJs.openCalendar(event, "deDate");
	});

	$("#financialYear").change(function() {
		doSearch();
	});

	$("#quarterName").change(function() {
		doSearch();
	});

	$("#expenseMainType").change(function() {
		setExpenseSubType();
	});

	$("#expenseSubType").change(function() {
		doSearch();
	});

	$("#deExpenseMainType").change(function() {
		setDeExpenseSubType();
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

	flushSubType = function(subTypeSelectId) {
		$("#"+subTypeSelectId+" option").each(function(index) {
			$(this).remove();
		});
		$("#"+subTypeSelectId).append("<option value=\"\">==Select==</option>");

		$("#"+subTypeSelectId).selectpicker("refresh");
	};

	setDeTypesContextMenu = function() {
		var subMenu = [];

		commonJs.ajaxSubmit({
			url:"/common/entryTypeSupporter/getExpenseTypesForContextMenu",
			dataType:"json",
			data:{},
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");
				if (result.isSuccess == true || result.isSuccess == "true") {
					var ds = result.dataSet;

					if (ds.getRowCnt() > 0) {
						for (var i=0; i<ds.getRowCnt(); i++) {
							var expenseType = ds.getValue(i, "EXPENSE_TYPE");
							var parentExpenseType = ds.getValue(i, "PARENT_EXPENSE_TYPE");
							var iLevel = parseInt(ds.getValue(i, "LEVEL"));
							var isLeaf = parseInt(ds.getValue(i, "IS_LEAF"));

							if (iLevel == 1) {
								if (subMenu.length != 0) {
									expenseTypeMenu[expenseTypeMenu.length - 1].subMenu = subMenu;
									subMenu = [];
								}

								expenseTypeMenu.push({
									name:ds.getValue(i, "DESCRIPTION"),
									userData:expenseType,
									fun:function() {
									}
								});
							} else {
								subMenu.push({
									name:ds.getValue(i, "DESCRIPTION"),
									userData:parentExpenseType+"_"+expenseType,
									fun:function() {
										setDeTypeSelectboxes($(this).attr("userData"));
									}
								});
							}

							if (i == ds.getRowCnt()-1) {
								expenseTypeMenu[expenseTypeMenu.length - 1].subMenu = subMenu;
								subMenu = [];
							}
						}
					}
				} else {
					commonJs.error(result.message);
				}
			}
		});

		$("#btnDeTypes").contextMenu(expenseTypeMenu, {
			borderRadius : "4px",
			displayAround : "trigger",
			position : "bottom",
			horAdjust : 0,
			verAdjust : 0
		});
	};

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
		commonJs.showProcMessageOnElement("divScrollablePanel");

		refreshDataEntry();

		setTimeout(function() {
			commonJs.ajaxSubmit({
				url:"/rkm/0402/getList",
				dataType:"json",
				formId:"fmDefault",
				success:function(data, textStatus) {
					var result = commonJs.parseAjaxResult(data, textStatus, "json");

					if (result.isSuccess == true || result.isSuccess == "true") {
						renderDataGridTable(result);
					} else {
						commonJs.error(result.message);
					}
				}
			});
		}, 400);

		setSummaryDataForAdminTool();
	};

	renderDataGridTable = function(result) {
		var ds = result.dataSet;
		var html = "", totHtml = "", totGross = 0, totGst = 0, totNet = 0;
		var totGridTr = new UiGridTr();

		searchResultDataCount = ds.getRowCnt();
		$("#tblGridBody").html("");
		$("#tblGridFoot").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr();
				totGross += parseFloat(ds.getValue(i, "GROSS_AMT"));
				totGst += parseFloat(ds.getValue(i, "GST_AMT"));
				totNet += parseFloat(ds.getValue(i, "NET_AMT"));

				var uiChk = new UiCheckbox();
				uiChk.setId("chkForDel").setName("chkForDel").setValue(ds.getValue(i, "EXPENSE_ID"));
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiChk));

				var uiAnc = new UiAnchor();
				uiAnc.setText(ds.getValue(i, "EXPENSE_DATE")).setScript("getEdit('"+ds.getValue(i, "EXPENSE_ID")+"')");
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiAnc));

				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(commonJs.abbreviate(ds.getValue(i, "PARENT_EXPENSE_TYPE_DESC"), 60)));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(commonJs.abbreviate(ds.getValue(i, "EXPENSE_TYPE_DESC"), 60)));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "ACCOUNT_CODE")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "GROSS_AMT"), numberFormat)));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "GST_AMT"), numberFormat)));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "NET_AMT"), numberFormat)));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(commonJs.abbreviate(ds.getValue(i, "DESCRIPTION"), 40)));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "IS_COMPLETED")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "ENTRY_DATE")));

				var iconAction = new UiIcon();
				iconAction.setId("icnAction").setName("icnAction").addClassName("fa-tasks fa-lg").addAttribute("expenseId:"+ds.getValue(i, "EXPENSE_ID")).setScript("doAction(this)");
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(iconAction));

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:12").setText(com.message.I001));
			html += gridTr.toHtmlString();
		}

		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Ct").setText(com.caption.total));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(totGross, numberFormat)));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(totGst, numberFormat)));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(totNet, numberFormat)));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));

		totHtml = totGridTr.toHtmlString();

		$("#tblGridBody").append($(html));
		$("#tblGridFoot").append($(totHtml));

		$("#tblGrid").fixedHeaderTable({
			attachTo:$("#divDataArea"),
			pagingArea:$("#divPagingArea"),
			isPageable:true,
			isFilter:false,
			filterColumn:[],
			totalResultRows:result.totalResultRows,
			script:"doSearch"
		});

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenu.dataEntryListAction);
		});

		commonJs.hideProcMessageOnElement("divScrollablePanel");
	};

	onEditDataEntry = function(jqObj) {
		var name = $(jqObj).attr("name");
		if (name == "deGrossExpense" || name == "deGst") {
			commonJs.ajaxSubmit({
				url:"/rkm/0402/calculateDataEntry",
				dataType:"json",
				data:{
					grossExpense:$("#deGrossExpense").val(),
					gst:$("#deGst").val()
				},
				success:function(data, textStatus) {
					var result = commonJs.parseAjaxResult(data, textStatus, "json");

					if (result.isSuccess == true || result.isSuccess == "true") {
						var ds = result.dataSet;
						$("#deNetExpense").val(ds.getValue(0, "netExpense"));
					} else {
						commonJs.error(result.message);
					}
				}
			});
		}
	};

	getEdit = function(expenseId) {
		$("input:checkbox[name=chkForDel]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == expenseId) {
				$(this).prop("checked", true);
			} else {
				$(this).prop("checked", false);
			}
		});

		commonJs.ajaxSubmit({
			url:"/rkm/0402/getEdit",
			dataType:"json",
			data:{
				expenseId:expenseId
			},
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");

				if (result.isSuccess == true || result.isSuccess == "true") {
					var ds = result.dataSet;

					refreshDataEntry();
					setDataEntryValues(ds);
					$("#deGrossExpense").focus();
				} else {
					commonJs.error(result.message);
				}
			}
		});
	};

	doSave = function() {
		if (!commonJs.doValidate("fmDefault")) {
			return;
		}

		commonJs.confirm({
			contents:com.message.Q001,
			buttons:[{
				caption:com.caption.yes,
				callback:function() {
					commonJs.ajaxSubmit({
						url:"/rkm/0402/exeSave",
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
											doSearch();
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

	doComplete = function() {
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
						url:"/rkm/0402/exeComplete",
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
											doSearch();
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
						url:"/rkm/0402/exeDelete",
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
											doSearch();
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
		var expenseId = $(img).attr("expenseId");

		$("input:checkbox[name=chkForDel]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == expenseId) {
				$(this).prop("checked", true);
			} else {
				$(this).prop("checked", false);
			}
		});

		ctxMenu.dataEntryListAction[0].fun = function() {getEdit(expenseId);};
		ctxMenu.dataEntryListAction[1].fun = function() {doDelete();};
		ctxMenu.dataEntryListAction[2].fun = function() {doComplete();};

		$(img).contextMenu(ctxMenu.dataEntryListAction, {
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
		flushSubType("deExpenseSubType");
	};

	setDataEntryValues = function(dataSet) {
		$("#deExpenseId").val(commonJs.nvl(dataSet.getValue(0, "EXPENSE_ID"), ""));
		$("#deExpenseTypeId").val(commonJs.nvl(dataSet.getValue(0, "EXPENSE_TYPE_ID"), ""));
		$("#deDate").val(commonJs.nvl(dataSet.getValue(0, "EXPENSE_DATE"), ""));

		$("#deGrossExpense").val(commonJs.getNumberMask(dataSet.getValue(0, "GROSS_AMT"), numberFormat));
		$("#deGst").val(commonJs.getNumberMask(dataSet.getValue(0, "GST_AMT"), numberFormat));
		$("#deNetExpense").val(commonJs.getNumberMask(dataSet.getValue(0, "NET_AMT"), numberFormat));
		$("#deRemark").val(commonJs.nvl(dataSet.getValue(0, "DESCRIPTION"), ""));

		$("#deExpenseMainType").val(commonJs.nvl(dataSet.getValue(0, "PARENT_EXPENSE_TYPE"), ""));
		commonJs.refreshBootstrapSelectbox("deExpenseMainType");

		setDeExpenseSubType();
		$("#deExpenseSubType").val(commonJs.nvl(dataSet.getValue(0, "EXPENSE_TYPE_CODE"), ""));
		commonJs.refreshBootstrapSelectbox("deExpenseSubType");
	};

	setExpenseSubType = function() {
		drawExpenseSubTypeSelectbox();
		commonJs.refreshBootstrapSelectbox("expenseSubType");
	};

	drawExpenseSubTypeSelectbox = function() {
		$("#expenseSubType option").each(function(index) {
			$(this).remove();
		});

		commonJs.ajaxSubmit({
			url:"/common/entryTypeSupporter/getExpenseSubTypeForSelectbox",
			dataType:"json",
			data:{expenseMainType:$("#expenseMainType").val()},
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");
				if (result.isSuccess == true || result.isSuccess == "true") {
					var ds = result.dataSet;

					$("#expenseSubType").append("<option value=\"\">==Select==</option>");
					if (ds.getRowCnt() > 0) {
						for (var i=0; i<ds.getRowCnt(); i++) {
							$("#expenseSubType").append("<option value=\""+ds.getValue(i, "EXPENSE_TYPE")+"\">"+ds.getValue(i, "DESCRIPTION")+"</option>");
						}
					}
				} else {
					commonJs.error(result.message);
				}
			}
		});
	};

	setDeExpenseSubType = function() {
		drawDeExpenseSubTypeSelectbox();
		$("#deExpenseSubType").selectpicker("refresh");
	};

	drawDeExpenseSubTypeSelectbox = function() {
		$("#deExpenseSubType option").each(function(index) {
			$(this).remove();
		});

		commonJs.ajaxSubmit({
			url:"/common/entryTypeSupporter/getExpenseSubTypeForSelectbox",
			dataType:"json",
			data:{
				expenseMainType:$("#deExpenseMainType").val()
			},
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");
				if (result.isSuccess == true || result.isSuccess == "true") {
					var ds = result.dataSet;

					$("#deExpenseSubType").append("<option value=\"\">==Select==</option>");
					if (ds.getRowCnt() > 0) {
						for (var i=0; i<ds.getRowCnt(); i++) {
							$("#deExpenseSubType").append("<option value=\""+ds.getValue(i, "EXPENSE_TYPE")+"\">"+ds.getValue(i, "DESCRIPTION")+"</option>");
						}
					}
				} else {
					commonJs.error(result.message);
				}
			}
		});
	};

	setDeTypeSelectboxes = function(types) {
		var mainType = types.split("_")[0];
		var subType = types.split("_")[1];

		$("#deExpenseMainType").val(mainType);
		commonJs.refreshBootstrapSelectbox("deExpenseMainType");
		setTimeout(function() {
			setDeExpenseSubType();

			$("#deExpenseSubType").val(subType);
			commonJs.refreshBootstrapSelectbox("deExpenseSubType");
		}, 10);
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
						url:"/rkm/0402/exeExport",
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
			}],
			blind:true
		});
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		commonJs.setExportButtonContextMenu($("#btnExport"));
		commonJs.setFieldDateMask("deDate");
		$(".numeric").number(true, 2);

		setDeTypesContextMenu();
		setDataEntryActionButtonContextMenu();
		setExpenseSubType();
		setDeExpenseSubType();

		doSearch();
	});
});