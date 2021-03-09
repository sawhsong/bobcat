/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Rpu0202List.js
 *************************************************************************************************/
var dateTimeFormat = jsconfig.get("dateTimeFormatJs");
var dateFormat = jsconfig.get("dateFormatJs");
var searchResultDataCount = 0;
var popup;

$(function() {
	/*!
	 * event
	 */
	$("#btnSearch").click(function(event) {
		doSearch();
	});

	$("#btnClear").click(function(event) {
		commonJs.clearSearchCriteria();
	});

	$("#btnExport").click(function(event) {
		if (searchResultDataCount <= 0) {
			commonJs.alert("There is no data to export.");
			return;
		}

		commonJs.doExport({
			url:"/rpu/0202/doExport.do",
			data:commonJs.serialiseObject($("#divSearchCriteriaArea"))
		});

		setTimeout(() => popup.close(), 5000);
	});

	$("#icnFromDate").click(function(event) {
		commonJs.openCalendar(event, "fromDate");
	});

	$("#icnToDate").click(function(event) {
		commonJs.openCalendar(event, "toDate");
	});

	$(document).keydown(function(event) {
		var code = event.keyCode || event.which, element = event.target;
		var name = $(element).attr("name");
		if (code == 13) {
			if (commonJs.isIn(name, ["fromDate", "toDate"])) {
				doSearch();
			}
		}
		if (code == 9) {}
	});

	/*!
	 * process
	 */
	doSearch = function() {
		commonJs.showProcMessageOnElement("divScrollablePanel");

		commonJs.doSearch({
			url:"/rpu/0202/getList.do",
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

				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getAccountingFormat(ds.getValue(i, "LAST_YEAR"))));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "ACCOUNT_CODE")));
				if (i == ds.getRowCnt()-1) {
					gridTr.addChild(new UiGridTd().addClassName("Lt").setText("Total"));
				} else {
					gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "DESCRIPTION")));
				}
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getAccountingFormat(ds.getValue(i, "DEBIT"))));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getAccountingFormat(ds.getValue(i, "CREDIT"))));

				if (i == ds.getRowCnt()-1) {
					gridTr.addClassName("noStripe").setStyle("font-weight:bold;border-top:2px solid #cccccc;border-bottom:2px solid #cccccc;background:#f5f5f5;");
				}

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:5").setText(com.message.I001));
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

		commonJs.hideProcMessageOnElement("divScrollablePanel");
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		commonJs.setFieldDateMask("fromDate");
		commonJs.setFieldDateMask("toDate");
	});
});