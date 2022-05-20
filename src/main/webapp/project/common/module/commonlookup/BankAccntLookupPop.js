/**
 * BankAccntLookupPop.js
 */
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

	$("#btnClose").click(function(event) {
		parent.popupLookup.close();
	});

	$("#bankCode").change(function(event) {
		doSearch();
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
		commonJs.showProcMessageOnElement("divScrollablePanelPopup");

		commonJs.doSearch({
			url:"/common/lookup/getBankAccntLookup.do",
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

				var uiAnc = new UiAnchor();
				uiAnc.setText(ds.getValue(i, "BANK_NAME")).setScript("setValue('"+ds.getValue(i, "BANK_ACCNT_ID")+"', '"+ds.getValue(i, "ACCNT_NAME")+"')");
				gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAnc));

				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(commonJs.getFormatString(ds.getValue(i, "BSB"), "??? ???")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "ACCNT_NUMBER")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "ACCNT_NAME")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "BALANCE"), "#,##0.00")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "DESCRIPTION")));

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:6").setText(com.message.I001));
			html += gridTr.toHtmlString();
		}

		$("#tblGridBody").append($(html));

		$("#tblGrid").freezeHeader({
			attachTo:$("#divDataArea"),
			pagingArea:$("#divPagingArea"),
			isPageable:false,
			totalResultRows:result.totalResultRows,
			script:"doSearch"
		});

		commonJs.hideProcMessageOnElement("divScrollablePanelPopup");
	};

	setValue = function(id, name) {
		var targetDocument, keyField, valueField;

		if (popupToSetValue != null) {
			targetDocument = $(popupToSetValue.popupIframe).contents();
			keyField = $(targetDocument).find("#"+keyFieldId);
			valueField = $(targetDocument).find("#"+valueFieldId);

			$(keyField).val(id);
			$(valueField).val(name);

			if (commonJs.isNotBlank(callback)) {
				$(popupToSetValue.popupIframe).get(0).contentWindow[callback]();
			}
		}

		popupObject.close();
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		setTimeout(() => doSearch(), 400);
	});
});