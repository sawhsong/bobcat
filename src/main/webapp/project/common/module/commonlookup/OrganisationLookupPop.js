/**
 * OrganisationLookupPop.js
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

	/*!
	 * process
	 */
	doSearch = function() {
		commonJs.showProcMessageOnElement("divScrollablePanelPopup");

		commonJs.doSearch({
			url:"/common/lookup/getOrganisationLookup.do",
			onSuccess:renderDataGridTable
		});
	};

	renderDataGridTable = function(result) {
		var ds = result.dataSet;
		var html = "";

		searchResultDataCount = ds.getRowCnt();
		$("#tblGridBody").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr();

				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "ORG_ID")));

				var uiAnc = new UiAnchor();
				uiAnc.setText(commonJs.abbreviate(ds.getValue(i, "LEGAL_NAME"), 60)).setScript("setValue('"+ds.getValue(i, "ORG_ID")+"', '"+ds.getValue(i, "LEGAL_NAME")+"')");
				gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAnc));

				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(commonJs.getFormatString(ds.getValue(i, "ABN"), "?? ??? ??? ???")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(commonJs.abbreviate(ds.getValue(i, "ADDRESS"), 60)));

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:4").setText(com.message.I001));
			html += gridTr.toHtmlString();
		}

		$("#tblGridBody").append($(html));

		$("#tblGrid").fixedHeaderTable({
			attachTo:$("#divDataArea"),
			pagingArea:$("#divPagingArea"),
			isPageable:true,
			isFilter:false,
			filterColumn:[],
			totalResultRows:result.totalResultRows,
			script:"doSearch"
		});

		commonJs.hideProcMessageOnElement("divScrollablePanelPopup");
	};

	setValue = function(id, name) {
		var keyField, valueField;

		if (popupToSetValueObject != null) {
			if (popupToSetValue == "parent") {
				parent.$("#"+keyFieldId).val(id);
				parent.$("#"+valueFieldId).val(name);

				if (commonJs.isNotBlank(callback)) {
					parent[callback]();
				}
			} else {
				var targetDocument = $(popupToSetValueObject.popupIframe).contents();

				keyField = $(targetDocument).find("#"+keyFieldId);
				valueField = $(targetDocument).find("#"+valueFieldId);

				$(keyField).val(id);
				$(valueField).val(name);

				if (commonJs.isNotBlank(callback)) {
					$(popupToSetValueObject.popupIframe).get(0).contentWindow[callback]();
				}
			}
		}

		popupObject.close();
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		$("#orgName").val(lookupValue);
		$("#orgName").focus();

		commonJs.setAutoComplete($("#orgName"), {
			method:"getOrgName",
			label:"org_name",
			value:"org_name",
			focus: function(event, ui) {
				$("#orgName").val(ui.item.label);
				return false;
			},
			select:function(event, ui) {
				doSearch();
			}
		});

		commonJs.setAutoComplete($("#abn"), {
			method:"getAbn",
			label:"abn",
			value:"abn",
			focus: function(event, ui) {
				$("#abn").val(ui.item.label);
				return false;
			},
			select:function(event, ui) {
				doSearch();
			}
		});

		doSearch();
	});
});