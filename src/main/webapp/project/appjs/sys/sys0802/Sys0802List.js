/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sys0802List.js
 *************************************************************************************************/
var popup = null;
var dateTimeFormat = jsconfig.get("dateTimeFormatJs");
var searchResultDataCount = 0;

$(function() {
	/*!
	 * event
	 */
	$("#btnNew").click(function(event) {
		getEdit("", "");
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

	$("#periodYear").change(function(event) {
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
	doSearch = function() {
		commonJs.showProcMessageOnElement("divScrollablePanel");

		commonJs.doSearch({
			url:"/sys/0802/getList.do",
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

				var iconAction = new UiIcon();
				iconAction.setId("icnAction").setName("icnAction").addClassName("fa-ellipsis-h fa-lg").addAttribute("periodYear:"+ds.getValue(i, "PERIOD_YEAR")).addAttribute("quarterCode:"+ds.getValue(i, "QUARTER_CODE"))
					.setScript("doAction(this)");
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(iconAction));

				var uiChk = new UiCheckbox();
				uiChk.setId("chkForDel").setName("chkForDel").setValue(ds.getValue(i, "PERIOD_YEAR")+"_"+ds.getValue(i, "QUARTER_CODE"));
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiChk));

				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "PERIOD_YEAR")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "FINANCIAL_YEAR")));

				var uiAnc = new UiAnchor();
				uiAnc.setText(ds.getValue(i, "QUARTER_CODE_NAME")).setScript("getEdit('"+ds.getValue(i, "PERIOD_YEAR")+"', '"+ds.getValue(i, "QUARTER_CODE")+"')");
				gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAnc));

				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "QUARTER_NAME_DESC")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "DATE_FROM")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "DATE_TO")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(commonJs.getDateTimeMask(ds.getValue(i, "LAST_UPDATED_DATE"), dateTimeFormat)));

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:9").setText(com.message.I001));
			html += gridTr.toHtmlString();
		}

		$("#tblGridBody").append($(html));

		$("#tblGrid").freezeHeader({
			attachTo:$("#divDataArea"),
			pagingArea:$("#divPagingArea"),
			isPageable:true,
			totalResultRows:result.totalResultRows,
			script:"doSearch"
		});

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenu.commonSimpleAction);
		});

		commonJs.bindToggleTrBackgoundWithCheckbox($("[name=chkForDel]"));
		commonJs.hideProcMessageOnElement("divScrollablePanel");
	};

	getEdit = function(periodYear, quarterCode) {
		popup = commonJs.openPopup({
			popupId:"FinancialPeriodEdit",
			url:"/sys/0802/getEdit.do",
			data:{
				periodYear:periodYear,
				quarterCode:quarterCode
			},
			header:"Financial Period Edit",
			width:700,
			height:350
		});
	};

	doDelete = function() {
		if (commonJs.getCountChecked("chkForDel") == 0) {
			commonJs.warn(com.message.I902);
			return;
		}

		commonJs.doDelete({
			url:"/sys/0802/doDelete.do",
			onSuccess:doSearch
		});
	};

	doAction = function(img) {
		var periodYear = $(img).attr("periodYear"), quarterCode = $(img).attr("quarterCode");

		$("input:checkbox[name=chkForDel]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == periodYear+"_"+quarterCode) {
				if (!$(this).is(":checked")) {
					$(this).click();
				}
			} else {
				if ($(this).is(":checked")) {
					$(this).click();
				}
			}
		});

		ctxMenu.commonSimpleAction[0].fun = function() {getEdit(periodYear, quarterCode);};
		ctxMenu.commonSimpleAction[1].fun = function() {doDelete();};

		$(img).contextMenu(ctxMenu.commonSimpleAction, {
			classPrefix:com.constants.ctxClassPrefixGrid,
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0,
			verAdjust:2
		});
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		doSearch();
	});
});