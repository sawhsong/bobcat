/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Bst0202List.js
 *************************************************************************************************/
var popup;
var dateTimeFormat = jsconfig.get("dateTimeFormatJs");
var dateFormat = jsconfig.get("dateFormatJs");
var searchResultDataCount = 0;

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

	$("#btnUpload").click(function(event) {
		popup = commonJs.openPopup({
			popupId:"UploadBankStatement",
			url:"/bst/0202/getUpload.do",
			data:{},
			header:"Upload Bank Statement",
			width:1500,
			height:800
		});
	});

	$("#icnFromDate").click(function(event) {
		commonJs.openCalendar(event, "fromDate");
	});

	$("#icnToDate").click(function(event) {
		commonJs.openCalendar(event, "toDate");
	});

	$("#bankAccntId").change(function(event) {
		doSearch();
	});

	$(document).keydown(function(event) {
		var code = event.keyCode || event.which, element = event.target;

		if (code == 13) {
			if ($(element).is("#toDate")) {
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
			url:"/bst/0202/getList.do",
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

				var uiChk = new UiCheckbox();
				uiChk.setId("chkForDel").setName("chkForDel").setValue(ds.getValue(i, "BANK_STATEMENT_ID"));
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiChk));

				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "BANK_NAME")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(commonJs.getFormatString(ds.getValue(i, "BSB"), "??? ???")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "ACCNT_NUMBER")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "ACCNT_NAME")));

				var uiAnc = new UiAnchor();
				uiAnc.setText(ds.getValue(i, "ORIGINAL_FILE_NAME")).setScript("getDetail('"+ds.getValue(i, "BANK_STATEMENT_ID")+"')");
				gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAnc));

				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "DETAIL_CNT"), "#,##0")));

				var gridTd = new UiGridTd();
				gridTd.addClassName("Ct");
				var iconFile = new UiIcon();
				iconFile.setId("iconFile").setName("iconFile").addClassName("glyphicon-paperclip").addAttribute("bankStatementId:"+ds.getValue(i, "BANK_STATEMENT_ID")).setScript("getFile(this)");
				gridTd.addChild(iconFile);
				gridTr.addChild(gridTd);

				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(commonJs.getDateTimeMask(ds.getValue(i, "MIN_PROC_DATE"), dateFormat)));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(commonJs.getDateTimeMask(ds.getValue(i, "MAX_PROC_DATE"), dateFormat)));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "LAST_BALANCE_AMT"), "#,##0.00")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(commonJs.getDateTimeMask(ds.getValue(i, "LAST_UPDATE_DATE"), dateTimeFormat)));

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:12").setText(com.message.I001));
			html += gridTr.toHtmlString();
		}

		$("#tblGridBody").append($(html));

		$("#tblGrid").fixedHeaderTable({
			attachTo:$("#divDataArea"),
			pagingArea:$("#divPagingArea"),
			isPageable:true,
			totalResultRows:result.totalResultRows,
			script:"doSearch"
		});

		commonJs.bindToggleTrBackgoundWithCheckbox($("[name=chkForDel]"));
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