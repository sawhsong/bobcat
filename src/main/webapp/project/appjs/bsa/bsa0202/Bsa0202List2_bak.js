/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Bsa0202List.js
 *************************************************************************************************/
var dateTimeFormat = jsconfig.get("dateTimeFormatJs");
var dateFormat = jsconfig.get("dateFormatJs");
var numberFormat = "#,##0.00";
var reconCategoryMenu = [];
var popup;

$(function() {
	/*!
	 * event
	 */
	$("#btnSearch").click(function(event) {
		refreshDataEntry();
		doSearch();
	});

	$("#btnClear").click(function(event) {
		commonJs.clearSearchCriteria();
		refreshDataEntry();
	});

	$("#bankAccntId").change(function(event) {
		refreshDataEntry();
		doSearch();
	});

	$("#allocationStatus").change(function(event) {
		doSearch();
		refreshDataEntry();
	});

	$("#icnTransactionDateFrom").click(function(event) {
		commonJs.openCalendar(event, "transactionDateFrom");
	});

	$("#icnTransactionDateTo").click(function(event) {
		commonJs.openCalendar(event, "transactionDateTo");
	});

	$("#icnUpdatedDateFrom").click(function(event) {
		commonJs.openCalendar(event, "updatedDateFrom");
	});

	$("#icnUpdatedDateTo").click(function(event) {
		commonJs.openCalendar(event, "updatedDateTo");
	});

	$("#icnCheck").click(function(event) {
		commonJs.toggleCheckboxes("chkForEdit");
	});

	$("#deMainReconCategory").change(function() {
		setSubReconCategory();
	});

	$("#deSubReconCategory").change(function() {
		if (!commonJs.isBlank($("#deSubReconCategory").val())) {
//			doSave();
		}
	});

	$("#btnBatch").click(function() {
		if (commonJs.getCountChecked("chkForEdit") == 0) {
			commonJs.warn(com.message.I902);
			return;
		}

		popup = commonJs.openPopup({
			popupId:"BatchApplication",
			url:"/bsa/0202/getBatchApplication.do",
			data:{},
			header:"Batch Application",
			width:840,
			height:400
		});
	});

	$(document).keyup(function(event) {
		var code = event.keyCode || event.which, element = event.target;
		if (code == 9) {}
		if (code == 13) {
			if ($(element).attr("name") == "deGstAmount") {
//				doSave();
			}
		}
		onEditDataEntry($(element));
	});

	/*!
	 * process
	 */
	doSave = function() {
		if (commonJs.isBlank($("#deBsTranAllocId").val())) {
			commonJs.alert("There is no data selected.");
			return;
		}

		if (!commonJs.doValidate("fmDefault")) {
			return;
		}

		commonJs.doSimpleProcess({
			url:"/bsa/0202/doSave.do",
			onSuccess:function(result) {
				var ds = result.dataSet;
				doSearch();
			}
		});
	};

	doDelete = function() {
		if (commonJs.isBlank($("#deBsTranAllocId").val())) {
			commonJs.alert("There is no data selected.");
			return;
		}

		commonJs.doSimpleProcess({
			url:"/bsa/0202/doDelete.do",
			onSuccess:function(result) {
				var ds = result.dataSet;
				doSearch();
			}
		});
	};

	onEditDataEntry = function(jqObj) {
		var name = $(jqObj).attr("name");
		if (name == "deGstAmount") {
			var amt = $("#deAmount").val();
			var gst = $("#deGstAmount").val();

			$("#deNetAmount").val(amt - gst);
		}
	};

	setBankAccntId = function() {
		if (!commonJs.isBlank(sbaId)) {
			$("#bankAccntId").val(sbaId);
			commonJs.refreshBootstrapSelectbox("bankAccntId");
		}
	};

	flushReconCategory = function() {
		$("#deMainReconCategory").val("");
		$("#deMainReconCategory").selectpicker("refresh");

		$("#deSubReconCategory option").each(function(index) {
			$(this).remove();
		});

		$("#deSubReconCategory").append(commonJs.getUiSelectOption({
			value:"",
			text:"==Select=="
		}));

		$("#deSubReconCategory").selectpicker("refresh");
	};

	refreshDataEntry = function() {
		$("#divInformArea").find(":input").each(function() {
			if ($(this).prop("type") == "checkbox" || $(this).prop("type") == "radio") {
				$(this).attr("checked", false);
			} else {
				$(this).val("");
			}
		});
		flushReconCategory();
	};

	setSubReconCategory = function() {
		commonJs.doSearch({
			url:"/bsa/0202/getSubReconCategory.do",
			noForm:true,
			data:{mainReconCategoryId:$("#deMainReconCategory").val()},
			onSuccess:function(result) {
				var ds = result.dataSet;

				$("#deSubReconCategory option").each(function(index) {
					$(this).remove();
				});

				$("#deSubReconCategory").append(commonJs.getUiSelectOption({
					value:"",
					text:"==Select=="
				}));

				if (ds.getRowCnt() > 0) {
					for (var i=0; i<ds.getRowCnt(); i++) {
						$("#deSubReconCategory").append(commonJs.getUiSelectOption({
							value:ds.getValue(i, "CATEGORY_ID"),
							text:ds.getValue(i, "CATEGORY_NAME")
						}));
					}
				}

				$("#deSubReconCategory").selectpicker("refresh");
			}
		});
	};

	getEdit = function(bsTranAllocId) {
		$("input:checkbox[name=chkForEdit]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == bsTranAllocId) {
				$(this).prop("checked", true);
				$(this).parents("tr").addClass("checkedTr");
			} else {
				$(this).prop("checked", false);
				$(this).parents("tr").removeClass("checkedTr");
			}
		});

		commonJs.showProcMessageOnElement("divInformArea");

		commonJs.doSearch({
			url:"/bsa/0202/getEdit.do",
			noForm:true,
			data:{bsTranAllocId:bsTranAllocId},
			onSuccess:function(result) {
				var ds = result.dataSet;

				refreshDataEntry();
				setDataEntryValues(ds);
				$("#deGstAmount").select();
			}
		});
	};

	setDataEntryValues = function(ds) {
		$("#deBsTranAllocId").val(ds.getValue(0, "BS_TRAN_ALLOC_ID"));
		$("#deDate").val(commonJs.getDateTimeMask(ds.getValue(0, "PROC_DATE"), dateFormat));

		$("#deMainReconCategory").val(ds.getValue(0, "MAIN_CATEGORY"));
		commonJs.refreshBootstrapSelectbox("deMainReconCategory");

		setSubReconCategory();

		$("#deAmount").val(commonJs.getNumberMask(ds.getValue(0, "PROC_AMT"), numberFormat));
		$("#deGstAmount").val(commonJs.getNumberMask(ds.getValue(0, "GST_AMT"), numberFormat));
		$("#deNetAmount").val(commonJs.getNumberMask(ds.getValue(0, "NET_AMT"), numberFormat));
		$("#deDescription").val(ds.getValue(0, "PROC_DESCRIPTION"));
		$("#deBalance").val(commonJs.getNumberMask(ds.getValue(0, "BALANCE"), numberFormat));

		setTimeout(function() {
			$("#deSubReconCategory").val(ds.getValue(0, "SUB_CATEGORY"));
			commonJs.refreshBootstrapSelectbox("deSubReconCategory");
		}, 300);

		commonJs.hideProcMessageOnElement("divInformArea");
	};

	doSearch = function() {
		commonJs.showProcMessageOnElement("divScrollablePanel");

		commonJs.doSearch({
			url:"/bsa/0202/getList.do",
			onSuccess:renderGridData
		});
	};

	renderGridData = function(result) {
		var ds = result.dataSet, html = "";

		$("#tblGridBody").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr();

				var uiChk = new UiCheckbox();
				uiChk.setName("chkForEdit").setValue(ds.getValue(i, "BS_TRAN_ALLOC_ID"));
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiChk));

				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(new UiAnchor().setText(commonJs.getDateTimeMask(ds.getValue(i, "PROC_DATE"), dateFormat)).setScript("getEdit('"+ds.getValue(i, "BS_TRAN_ALLOC_ID")+"')")));

				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "MAIN_CATEGORY_DESC")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "SUB_CATEGORY_DESC")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "PROC_AMT"), "#,##0.00")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "GST_AMT"), "#,##0.00")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "NET_AMT"), "#,##0.00")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "PROC_DESCRIPTION")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "BALANCE"), "#,##0.00")));

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:9").setText(com.message.I001));
			html += gridTr.toHtmlString();
		}

		$("#tblGridBody").append($(html));
		setGridTable(result.totalResultRows);

		commonJs.bindToggleTrBackgoundWithCheckbox($("[name=chkForEdit]"));
		commonJs.hideProcMessageOnElement("divScrollablePanel");
	};

	setGridTable = function(totalResultRows) {
		$("#tblGrid").fixedHeaderTable({
			attachTo:$("#divDataArea"),
			pagingArea:$("#divPagingArea"),
			isPageable:true,
			totalResultRows:totalResultRows,
			script:"doSearch"
		});
	};

	setDataEntryActionButtonContextMenu = function() {
		ctxMenu.dataEntryAction[0].fun = function() {};
		ctxMenu.dataEntryAction[1].fun = function() {};
		ctxMenu.dataEntryAction[2].fun = function() {};

		$("#icnDeAction").contextMenu(ctxMenu.dataEntryAction, {
			classPrefix:com.constants.ctxClassPrefixGrid,
			effectDuration:300,
			borderRadius:"bottom 4px",
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0
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

	setDeCategoriesContextMenu = function() {
		var subMenu = [];

		commonJs.doSearch({
			url:"/common/entryTypeSupporter/getRconCategoryForContextMenu.do",
			onSuccess:function(result) {
				var ds = result.dataSet;

				if (ds.getRowCnt() > 0) {
					for (var i=0; i<ds.getRowCnt(); i++) {
						var categoryId = ds.getValue(i, "CATEGORY_ID");
						var parentCategoryId = ds.getValue(i, "PARENT_CATEGORY_ID");
						var iLevel = parseInt(ds.getValue(i, "CATEGORY_LEVEL"));

						if (iLevel == 1) {
							if (subMenu.length != 0) {
								reconCategoryMenu[reconCategoryMenu.length - 1].subMenu = subMenu;
								subMenu = [];
							}

							reconCategoryMenu.push({
								name:ds.getValue(i, "CATEGORY_NAME"),
								userData:categoryId,
								fun:function() {
								}
							});
						} else {
							subMenu.push({
								name:ds.getValue(i, "CATEGORY_NAME"),
								userData:parentCategoryId+"_"+categoryId,
								fun:function() {
									setDeCategorySelectboxes($(this).attr("userData"));
								}
							});
						}

						if (i == ds.getRowCnt()-1) {
							reconCategoryMenu[reconCategoryMenu.length - 1].subMenu = subMenu;
							subMenu = [];
						}
					}
				}

				$("#btnDeCategories").contextMenu(reconCategoryMenu, {
					borderRadius : "4px",
					displayAround : "trigger",
					position : "bottom",
					horAdjust : 0,
					verAdjust : 0
				});
			}
		});
	};

	setDeCategorySelectboxes = function(categoryIds) {
		var mainCategoryId = categoryIds.split("_")[0];
		var subCategoryId = categoryIds.split("_")[1];

		$("#deMainReconCategory").val(mainCategoryId);
		commonJs.refreshBootstrapSelectbox("deMainReconCategory");

		setSubReconCategory();
		setTimeout(function() {
			$("#deSubReconCategory").val(subCategoryId);
			commonJs.refreshBootstrapSelectbox("deSubReconCategory");

//			doSave();
		}, 400);
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
		commonJs.setFieldDateMask("transactionDateFrom");
		commonJs.setFieldDateMask("transactionDateTo");
		commonJs.setFieldDateMask("updatedDateFrom");
		commonJs.setFieldDateMask("updatedDateTo");
		$(".numeric").number(true, 2);

		setDeCategoriesContextMenu();
		setDataEntryActionButtonContextMenu();

		setTimeout(function() {
			setBankAccntId();
			doSearch();
		}, 200);
	});
});