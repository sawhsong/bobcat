/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Bst0202EditPop.js
 *************************************************************************************************/
var dateFormat = jsconfig.get("dateFormatJs");
var dateTimeFormat = jsconfig.get("dateTimeFormatJs");

$(function() {
	/*!
	 * event
	 */
	$("#btnClose").click(function() {
		parent.popup.close();
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
	setTableDisplay = function() {
		if (commonJs.isBlank(bankCode)) {
			$("#divDataArea").find("div").each(function() {
				$(this).hide();
			});
		} else {
			$("#divDataArea").find("div").each(function() {
				var id = $(this).attr("id");
				if (commonJs.contains(id, bankCode)) {
					$(this).show();
				} else {
					$(this).hide();
				}
			});
		}
	};

	getTableByBankCode = function() {
		var obj;
		$("#divDataArea").find("div table").each(function() {
			var id = $(this).attr("id");
			if (commonJs.contains(id, bankCode)) {
				obj =  $(this);
				return true;
			}
		});
		return obj;
	};

	getTBodyByBankCode = function() {
		var obj;
		$("#divDataArea").find("div table tbody").each(function() {
			var id = $(this).attr("id");
			if (commonJs.contains(id, bankCode)) {
				obj =  $(this);
				return true;
			}
		});
		return obj;
	};

	loadInfoData = function() {
		commonJs.showProcMessageOnElement("divInformArea");

		commonJs.doSearch({
			url:"/bst/0202/getInfoDataForDetail.do",
			noForm:true,
			data:{bankStatementId:bankStatementId},
			onSuccess:setInfoData
		});
	};

	setInfoData = function(result) {
		var ds = result.dataSet;

		$("#tdBank").html(ds.getValue(0, "BANK_NAME"));
		$("#tdBsb").html(commonJs.getFormatString(ds.getValue(0, "BSB"), "??? ???"));
		$("#tdAccntNumber").html(ds.getValue(0, "ACCNT_NUMBER"));
		$("#tdAccntName").html(ds.getValue(0, "ACCNT_NAME"));
		$("#tdBalance").html(commonJs.getNumberMask(ds.getValue(0, "BALANCE"), "#,##0.00"));
		$("#tdDescription").html(ds.getValue(0, "BANK_ACCNT_DESCRIPTION"));

		$("#tdFileName").html(ds.getValue(0, "ORIGINAL_FILE_NAME"));
		$("#tdDateFrom").html(commonJs.getDateTimeMask(ds.getValue(0, "MIN_PROC_DATE"), dateFormat));
		$("#tdDateTo").html(commonJs.getDateTimeMask(ds.getValue(0, "MAX_PROC_DATE"), dateFormat));
		$("#tdLastBalance").html(commonJs.getNumberMask(ds.getValue(0, "LAST_BALANCE_AMT"), "#,##0.00"));
		$("#tdFileRows").html(commonJs.getNumberMask(ds.getValue(0, "DETAIL_CNT"), "#,##0"));
		$("#tdLastUpdateDate").html(commonJs.getDateTimeMask(ds.getValue(0, "LAST_UPDATE_DATE"), dateTimeFormat));

		commonJs.hideProcMessageOnElement("divInformArea");
	};

	loadDetailData = function() {
		commonJs.showProcMessageOnElement("divScrollablePanelPopup");

		setTableDisplay();

		commonJs.doSearch({
			url:"/bst/0202/getBankStatementDetail.do",
			noForm:true,
			data:{bankStatementId:bankStatementId},
			onSuccess:renderDataGrid
		});
	};

	renderDataGrid = function(result) {
		var ds = result.dataSet;
		var html = "";
		var table = getTableByBankCode(bankCode);
		var tbody = getTBodyByBankCode(bankCode);
		var rowCnt = ds.getRowCnt();

		$(tbody).html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr();

				if (commonJs.isIn(bankCode, ["CBA", "ANZ"])) {
					gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "ROW_INDEX")));
					gridTr.addChild(new UiGridTd().addClassName("Ct").setText(commonJs.getDateTimeMask(ds.getValue(i, "PROC_DATE"), dateFormat)));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "PROC_AMT"), "#,##0.00")));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "BALANCE"), "#,##0.00")));
					gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "PROC_DESCRIPTION")));
				}

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			if (commonJs.isIn(bankCode, ["CBA", "ANZ"])) {
				gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:5").setText(com.message.I001));
			}

			html += gridTr.toHtmlString();
		}

		$(tbody).append($(html));

		$(table).fixedHeaderTable({
			attachTo:$("#divDataArea"),
			pagingArea:$("#divPagingArea"),
			isPageable:false,
			totalResultRows:ds.getRowCnt()
		});

		commonJs.hideProcMessageOnElement("divScrollablePanelPopup");
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		setTimeout(function() {
			loadInfoData();
		}, 200);

		setTimeout(function() {
			loadDetailData();
		}, 500);
	});
});