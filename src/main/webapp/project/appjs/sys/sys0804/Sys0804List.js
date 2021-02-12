/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sys0804List.js
 *************************************************************************************************/
var dateTimeFormat = jsconfig.get("dateTimeFormatJs");
jsconfig.put("scrollablePanelHeightAdjust", 14);

$(function() {
	/*!
	 * event
	 */
	$("#btnNew").click(function(event) {
		getEdit("");
	});

	$("#btnDelete").click(function(event) {
		doDelete();
	});

	$("#btnSearch").click(function(event) {
		doSearch();
	});

	$("#btnClear").click(function(event) {
		commonJs.clearSearchCriteria();
	});

	$("#mainCategory").change(function(event) {
		doSearch();
	});

	$("#icnCheck").click(function(event) {
		commonJs.toggleCheckboxes("chkForDel");
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

		$("#mainCategory option").each(function(index) {
			$(this).remove();
		});
		$("#mainCategory").append("<option value=\"\">==Select==</option>");

		for (var i=0; i<ds.getRowCnt(); i++) {
			$("#mainCategory").append(commonJs.getUiSelectOption({
				value:ds.getValue(i, "CATEGORY_ID"),
				text:ds.getValue(i, "CATEGORY_NAME")
			}));
		}

		$("#mainCategory").selectpicker("refresh");
	};

	doSearch = function() {
		commonJs.showProcMessageOnElement("divScrollablePanel");

		commonJs.doSearch({
			url:"/sys/0804/getList.do",
			onSuccess:renderDataGrid
		});
	};

	renderDataGrid = function(result) {
		var ds = result.dataSet;
		var html = "";

		searchResultDataCount = ds.getRowCnt();
		$("#tblGridBody").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr();
				var categoryLevel = ds.getValue(i, "CATEGORY_LEVEL");
				var usedCnt = ds.getValue(i, "USED_CNT");
				var className = "chkEn", disabledStr = "";

				if (categoryLevel == 1 || usedCnt > 0) {
					className = "chkDis";
					disabledStr = "disabled";
				}

				var iconAction = new UiIcon();
				iconAction.setId("icnAction").setName("icnAction").addClassName("fa-ellipsis-h fa-lg").addAttribute("categoryId:"+ds.getValue(i, "CATEGORY_ID"))
				.addAttribute("usedCnt:"+usedCnt).addAttribute("categoryLevel:"+categoryLevel).setScript("doAction(this)");
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(iconAction));

				var uiChk = new UiCheckbox();
				uiChk.setName("chkForDel").setClassName(className+" inTblGrid").addOptions(disabledStr).setValue(ds.getValue(i, "CATEGORY_ID"));
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiChk));

				if (categoryLevel == 1) {
					var uiAnc = new UiAnchor();
					uiAnc.setText(ds.getValue(i, "CATEGORY_NAME")).setScript("getEdit('"+ds.getValue(i, "CATEGORY_ID")+"')");
					gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAnc));

					gridTr.addChild(new UiGridTd().addClassName("Lt").setText());
				} else {
					gridTr.addChild(new UiGridTd().addClassName("Lt").setText());

					var uiAnc = new UiAnchor();
					uiAnc.setText(ds.getValue(i, "CATEGORY_NAME")).setScript("getEdit('"+ds.getValue(i, "CATEGORY_ID")+"')");
					gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAnc));
				}

				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "ACCOUNT_CODE")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "IS_APPLY_GST")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(commonJs.getNumberMask(ds.getValue(i, "GST_PERCENTAGE"), "#,##0.00")+" %"));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "SORT_ORDER")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(commonJs.getDateTimeMask(ds.getValue(i, "LAST_UPDATED_DATE"), dateTimeFormat)));

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:9").setText(com.message.I001));
			html += gridTr.toHtmlString();
		}

		$("#tblGridBody").append($(html));

		$("#tblGrid").fixedHeaderTable({
			attachTo:$("#divDataArea"),
			pagingArea:$("#divPagingArea"),
			isPageable:false,
			totalResultRows:result.totalResultRows,
			script:"doSearch"
		});

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenu.commonSimpleAction);
		});

		commonJs.bindToggleTrBackgoundWithCheckbox($("[name=chkForDel]"));
		commonJs.hideProcMessageOnElement("divScrollablePanel");
	};

	doAction = function(img) {
		var categoryId = $(img).attr("categoryId"), usedCnt = $(img).attr("usedCnt"), categoryLevel = $(img).attr("categoryLevel");

		$("input:checkbox[name=chkForDel]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == categoryId) {
				$(this).prop("checked", true);
				$(this).parents("tr").addClass("checkedTr");
			} else {
				$(this).prop("checked", false);
				$(this).parents("tr").removeClass("checkedTr");
			}
		});

		if (categoryLevel == 1 || usedCnt > 0) {
			ctxMenu.commonSimpleAction[1].disable = true;
		} else {
			ctxMenu.commonSimpleAction[1].disable = false;
		}

		ctxMenu.commonSimpleAction[0].fun = function() {getEdit(categoryId);};
		ctxMenu.commonSimpleAction[1].fun = function() {doDelete();};

		$(img).contextMenu(ctxMenu.commonSimpleAction, {
			classPrefix:com.constants.ctxClassPrefixGrid,
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0,
			verAdjust:2
		});
	};

	getEdit = function(categoryId) {
		var mode = commonJs.isBlank(categoryId) ? "Insert" : "Update";

		popup = commonJs.openPopup({
			popupId:"CategoryEdit",
			url:"/sys/0804/getEdit.do",
			data:{
				mode:mode,
				categoryId:categoryId
			},
			header:"Bank Statement Transaction Reconciliation Category Edit",
			width:700,
			height:320
		});
	};

	doDelete = function() {
		if (commonJs.getCountChecked("chkForDel") == 0) {
			commonJs.warn(com.message.I902);
			return;
		}

		commonJs.doDelete({
			url:"/sys/0804/doDelete.do",
			onSuccess:doSearch
		});
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		loadMainCategory();
		doSearch();
	});
});