/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Bst0202EditPop.js
 *************************************************************************************************/
jsconfig.put("scrollablePanelHeightAdjust", 2);
var discardable = false;

$(function() {
	/*!
	 * event
	 */
	$("#bankAccntId").change(function() {
		loadBankInfo();
	});

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
				showPostMessage:false,
				onSuccess:function(result) {
					var ds = result.dataSet;
					var msgCode = result.messageCode;
					var msg = result.message;

					if (msgCode == "E999") {
						commonJs.error(msg);
					} else {
						commonJs.showProcMessageOnElement("divScrollablePanelPopup");

						setTableDisplay(bankCode);
						setTimeout(function() {
							renderDataGrid(ds, bankCode);
						}, 400);

						discardable = true;
					}
				}
			});
		}
	});

	$("#btnDiscard").click(function() {
		if (!discardable) {
			commonJs.warn("There is no data to discard.");
			return;
		}

		commonJs.doProcess({
			url:"/bst/0202/discardBankStatement.do",
			confirmMessage:"Are you sure to discard data uploaded?",
			noForm:true,
			onSuccess:function(result) {
				var ds = result.dataSet;
				setTableDisplay("");
				toggleUploadButton(0);
				discardable = false;
			}
		});
	});

	$("#btnSave").click(function() {
		if ("disabled" == $(this).attr("disabled")) {
			return;
		}

		commonJs.doSave({
			url:"/bst/0202/doSave.do",
			showPostMessage:false,
			onSuccess:function(result) {
				var ds = result.dataSet;
				commonJs.confirm({
					contents:"Would you like to go to Bank Statement Allocation screen?",
					buttons:[{
						caption:com.caption.yes,
						callback:function() {
							parent.$("#divBSA").trigger("click");
						}
					}, {
						caption:com.caption.no,
						callback:function() {
							$("#btnClose").trigger("click");
						}
					}]
				});
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
	loadBankInfo = function() {
		var bankAccntId = $("#bankAccntId").val();

		commonJs.showProcMessageOnElement("divInformArea");
		commonJs.doSearch({
			url:"/bst/0202/getBankAccountInfo.do",
			noForm:true,
			data:{bankAccntId:bankAccntId},
			onSuccess:setBankAccountInfo
		});
	};

	setBankAccountInfo = function(result) {
		var ds = result.dataSet;

		$("#tdBank").html(ds.getValue(0, "BANK_NAME"));
		$("#tdBsb").html(commonJs.getFormatString(ds.getValue(0, "BSB"), "??? ???"));
		$("#tdAccntNumber").html(ds.getValue(0, "ACCNT_NUMBER"));
		$("#tdAccntName").html(ds.getValue(0, "ACCNT_NAME"));
		$("#tdBalance").html(commonJs.getNumberMask(ds.getValue(0, "BALANCE"), "#,##0.00"));
		$("#tdDescription").html(ds.getValue(0, "DESCRIPTION"));

		commonJs.hideProcMessageOnElement("divInformArea");
	};

	setTableDisplay = function(bankCode) {
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

	toggleUploadButton = function(numberOfRows) {
		if (numberOfRows > 0) {
			$("#btnUpload").attr("disabled", true);
		} else {
			$("#btnUpload").attr("disabled", false);
		}
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
		var rowCnt = ds.getRowCnt();
		var attachTo;

		$(tbody).html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr();

				if (commonJs.isIn(bankCode, ["CBA", "ANZ", "NAB"])) {
					gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "ROW_INDEX")));
					gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "PROC_DATE")));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "PROC_AMOUNT"), "#,##0.00")));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "BALANCE"), "#,##0.00")));
					gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "DESCRIPTION")));
				} else if (commonJs.isIn(bankCode, ["WESTPAC"])) {
					gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "ROW_INDEX")));
					gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "BANK_ACCOUNT")));
					gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "PROC_DATE")));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "DEBIT_AMOUNT"), "#,##0.00")));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "CREDIT_AMOUNT"), "#,##0.00")));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "BALANCE"), "#,##0.00")));
					gridTr.addChild(new UiGridTd().addClassName("Lt").setText(commonJs.abbreviate(ds.getValue(i, "DESCRIPTION"), 66)));
					gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "CATEGORIES")));
					gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "SERIAL")));
				}

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			if (commonJs.isIn(bankCode, ["CBA", "ANZ", "NAB"])) {
				gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:5").setText(com.message.I001));
			} else if (commonJs.isIn(bankCode, ["WESTPAC"])) {
				gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:9").setText(com.message.I001));
			}

			html += gridTr.toHtmlString();
		}

		$(tbody).append($(html));

		if (commonJs.isIn(bankCode, ["CBA", "ANZ", "NAB"])) {
			attachTo = $("#divDataArea");
		} else if (commonJs.isIn(bankCode, ["WESTPAC"])) {
			attachTo = $("#divGridWrapper_WESTPAC");
		}

		$(table).fixedHeaderTable({
			attachTo:attachTo,
			pagingArea:$("#divPagingArea"),
			isPageable:false,
			totalResultRows:ds.getRowCnt()
		});

		toggleUploadButton(rowCnt);

		commonJs.hideProcMessageOnElement("divScrollablePanelPopup");
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		setTimeout(function() {
			loadBankInfo();
		}, 200);
	});
});