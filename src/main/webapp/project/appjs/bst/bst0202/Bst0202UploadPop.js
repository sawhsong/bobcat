/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Bst0202EditPop.js
 *************************************************************************************************/
$(function() {
	/*!
	 * event
	 */
	$("#btnUpload").click(function(event) {
		if ("disabled" == $(this).attr("disabled")) {
			return;
		}

		if (commonJs.doValidate("fmDefault")) {
			var bankCode = $("#bankAccntId option:selected").attr("bankCode");

			commonJs.doProcessWithFile({
				url:"/bst/0202/doUpload.do",
				confirmMessage:"Are you sure to upload the file?",
				data:{bankCode:bankCode},
				onSuccess:function(result) {
					var ds = result.dataSet;

					commonJs.showProcMessageOnElement("divScrollablePanelPopup");

					setTableDisplay(bankCode);
					setTimeout(function() {
						renderDataGrid(ds, bankCode);
					}, 400);
				}
			});
		}
	});

	/*
	 * ToDo
	 */
	$("#btnDiscard").click(function() {
		commonJs.doProcess({
			url:"/bst/0202/discardBankStatement.do",
			noForm:true,
			onSuccess:function(result) {
				var ds = result.dataSet;
			}
		});
	});

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
	setTableDisplay = function(bankCode) {
		$("#divDataArea").find("div").each(function() {
			var id = $(this).attr("id");
			if (commonJs.contains(id, bankCode)) {
				$(this).show();
			} else {
				$(this).hide();
			}
		});
	};

	getTableByBankCode = function(bankCode) {
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

	getTBodyByBankCode = function(bankCode) {
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

	renderDataGrid = function(ds, bankCode) {
		var html = "";
		var table = getTableByBankCode(bankCode);
		var tbody = getTBodyByBankCode(bankCode);

		$(tbody).html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr();

				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "BANK_NAME")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "BSB")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "ACCNT_NUMBER")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "ACCNT_NAME")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "ROW_INDEX")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "PROC_DATE")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "PROC_AMOUNT"), "#,##0.00")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "BALANCE"), "#,##0.00")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "DESCRIPTION")));

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:9").setText(com.message.I001));
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
	/*
	 * ToDo
	 */
	$(window).unload(function() {
		commonJs.doSimpleProcess({
			url:"/bst/0202/discardBankStatement.do",
			noForm:true,
			onSuccess:function(result) {
			}
		});
	});

	$(window).load(function() {
	});
});