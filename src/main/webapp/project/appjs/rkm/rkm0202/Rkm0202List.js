/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Rkm0202List.js
 *************************************************************************************************/
jsconfig.put("useJqTooltip", false);
jsconfig.put("scrollablePanelHeightAdjust", 6);
var popup = null;

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

	$("#icnDataEntryDate").click(function(event) {
		commonJs.openCalendar(event, "deDate");
	});

	$("#financialYear").change(function() {
		doSearch();
	});

	$("#quarterName").change(function() {
		doSearch();
	});

	$("#recordKeepingType").change(function() {
		doSearch();
	});

	$(document).keypress(function(event) {
		var element = event.target;

		if (event.which == 13) {
		}

		onEditDataEntry($(element));
	});

	/*!
	 * context menus
	 */
	setDataEntryActionButtonContextMenu = function() {
		ctxMenu.dataEntryAction[0].fun = function() {};
		ctxMenu.dataEntryAction[1].fun = function() {};
		ctxMenu.dataEntryAction[2].fun = function() {};

		$("#icnDataEntryAction").contextMenu(ctxMenu.dataEntryAction, {
			classPrefix:com.constants.ctxClassPrefixGrid,
			displayAround:"trigger",
			position:"bottom"
		});
	};

	/*!
	 * process
	 */
	onEditDataEntry = function(jqObj) {
console.log($(jqObj).val());
	};

	doSearch = function() {
		commonJs.showProcMessageOnElement("divScrollablePanel");

		refreshDataEntry();

		setTimeout(function() {
			commonJs.ajaxSubmit({
				url:"/rkm/0202/getList.do",
				dataType:"json",
				formId:"fmDefault",
				success:function(data, textStatus) {
					var result = commonJs.parseAjaxResult(data, textStatus, "json");

					if (result.isSuccess == true || result.isSuccess == "true") {
						renderDataGridTable(result);
					}
				}
			});
		}, 200);

		setSummaryDataForAdminTool();
	};

	renderDataGridTable = function(result) {
		var ds = result.dataSet;
		var html = "", totHtml = "";
		var totNonCash = 0, totCash = 0, totGross = 0, totGstFree = 0, totGst = 0, totNet = 0;
		var totGridTr = new UiGridTr();

		searchResultDataCount = ds.getRowCnt();
		$("#tblGridBody").html("");
		$("#tblGridFoot").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr(),
					keyVal = ds.getValue(i, "INCOME_ID")+"_"+ds.getValue(i, "QUARTER_DATE");

				totNonCash += parseFloat(ds.getValue(i, "NON_CASH_AMT"));
				totCash += parseFloat(ds.getValue(i, "CASH_AMT"));
				totGross += parseFloat(ds.getValue(i, "GROSS_AMT"));
				totGstFree += parseFloat(ds.getValue(i, "GST_FREE_AMT"));
				totGst += parseFloat(ds.getValue(i, "GST_AMT"));
				totNet += parseFloat(ds.getValue(i, "NET_AMT"));

				var uiChk = new UiCheckbox();
				uiChk.setId("chkForDel").setName("chkForDel").setValue(keyVal);
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiChk));

				var uiAnc = new UiAnchor();
				uiAnc.setText(ds.getValue(i, "QUARTER_DATE")).setScript("getEdit('"+keyVal+"')");
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiAnc));

				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "NON_CASH_AMT"), "#,###.##")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "CASH_AMT"), "#,###.##")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "GROSS_AMT"), "#,###.##")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "GST_FREE_AMT"), "#,###.##")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "GST_AMT"), "#,###.##")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "NET_AMT"), "#,###.##")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "IS_COMPLETED")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "INSERT_DATE")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "UPDATE_DATE")));

				var iconAction = new UiIcon();
				iconAction.setId("icnAction").setName("icnAction").addClassName("fa-tasks fa-lg").addAttribute("incomeIdDate:"+keyVal).setScript("doAction(this)");
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(iconAction));

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:12").setText(com.message.I001));
			html += gridTr.toHtmlString();
		}

		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Ct").setText(com.caption.total));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(totNonCash, "#,###.##")));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(totCash, "#,###.##")));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(totGross, "#,###.##")));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(totGstFree, "#,###.##")));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(totGst, "#,###.##")));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(totNet, "#,###.##")));
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
			isPageable:false,
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

	getEdit = function(incomeIdDate) {
		commonJs.ajaxSubmit({
			url:"/rkm/0202/getEdit.do",
			dataType:"json",
			data:{
				incomeIdDate:incomeIdDate
			},
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");

				if (result.isSuccess == true || result.isSuccess == "true") {
					var ds = result.dataSet;

					refreshDataEntry();
					setDataEntryValues(ds);
					$("#deNonCash").focus();
				} else {
					commonJs.error(result.message);
				}
			}
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
						url:"/rkm/0202/exeDelete.do",
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
		var incomeIdDate = $(img).attr("incomeIdDate");

		$("input:checkbox[name=chkForDel]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == incomeIdDate) {
				$(this).prop("checked", true);
			} else {
				$(this).prop("checked", false);
			}
		});

		ctxMenu.dataEntryListAction[0].fun = function() {getEdit(incomeIdDate);};
		ctxMenu.dataEntryListAction[1].fun = function() {doDelete();};

		$(img).contextMenu(ctxMenu.dataEntryListAction, {
			classPrefix:com.constants.ctxClassPrefixGrid,
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0,
			verAdjust:2
		});
	};

	doDataEntryAction = function(img) {
		ctxMenu.dataEntryAction[0].fun = function() {alert("save");};
		ctxMenu.dataEntryAction[1].fun = function() {refreshDataEntry();};
		ctxMenu.dataEntryAction[2].fun = function() {alert("delete");};

		$(img).contextMenu(ctxMenu.dataEntryAction, {
			classPrefix:com.constants.ctxClassPrefixGrid,
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0,
			verAdjust:2
		});
	};

	refreshDataEntry = function() {
		$("#deIncomeId").val("");
		$("#deDate").val("");
		$("#deNonCash").val("");
		$("#deCash").val("");
		$("#deGrossSales").val("");
		$("#deGstFree").val("");
		$("#deGst").val("");
		$("#deNetSales").val("");
		$("#deRecordKeepingType").val("");
		commonJs.refreshBootstrapSelectbox("deRecordKeepingType");
		$("#deRemark").val("");
	};

	setDataEntryValues = function(dataSet) {
		$("#deIncomeId").val(commonJs.nvl(dataSet.getValue(0, "INCOME_ID"), ""));
		$("#deDate").val(commonJs.nvl(dataSet.getValue(0, "INCOME_DATE"), ""));
		$("#deNonCash").val(commonJs.nvl(commonJs.getNumberMask(dataSet.getValue(0, "NON_CASH_AMT"), "#,###.##"), "0.00"));
		$("#deCash").val(commonJs.nvl(commonJs.getNumberMask(dataSet.getValue(0, "CASH_AMT"), "#,###.##"), "0.00"));
		$("#deGrossSales").val(commonJs.nvl(commonJs.getNumberMask(dataSet.getValue(0, "GROSS_AMT"), "#,###.##"), "0.00"));
		$("#deGstFree").val(commonJs.nvl(commonJs.getNumberMask(dataSet.getValue(0, "GST_FREE_AMT"), "#,###.##"), "0.00"));
		$("#deGst").val(commonJs.nvl(commonJs.getNumberMask(dataSet.getValue(0, "GST_AMT"), "#,###.##"), "0.00"));
		$("#deNetSales").val(commonJs.nvl(commonJs.getNumberMask(dataSet.getValue(0, "NET_AMT"), "#,###.##"), "0.00"));
		$("#deRecordKeepingType").val(commonJs.nvl(dataSet.getValue(0, "RECORD_KEEPING_TYPE"), ""));
		commonJs.refreshBootstrapSelectbox("deRecordKeepingType");
		$("#deRemark").val(commonJs.nvl(dataSet.getValue(0, "DESCRIPTION"), ""));
	};
	/*!
	 * load event (document / window)
	 */
	$(document).ready(function() {
		$("input:text").focus(function() {
			if ($(this).hasClass("txtEn")) {
				$(this).select();
			}
		});
	});

	$(window).load(function() {
		setDataEntryActionButtonContextMenu();
		commonJs.setFieldDateMask("deDate");
		$(".numeric").number(true, 2);
		doSearch();
	});
});