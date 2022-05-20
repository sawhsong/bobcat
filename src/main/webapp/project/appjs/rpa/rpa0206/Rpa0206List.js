/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Rpa0206List.js
 *************************************************************************************************/
var dateTimeFormat = jsconfig.get("dateTimeFormatJs");
var dateFormat = jsconfig.get("dateFormatJs");
var searchResultDataCount = 0;
var popup, popupLookup;
var exportCtxMenu = [{
		name:"Export as PDF",
		fileType:"PDF",
		img:"fa-file-pdf-o",
		fun:function() {}
	},{
		name:"Export as Excel",
		fileType:"Excel",
		img:"fa-file-excel-o",
		fun:function() {}
	}];

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

	$("#icnFromDate").click(function(event) {
		commonJs.openCalendar(event, "fromDate");
	});

	$("#icnToDate").click(function(event) {
		commonJs.openCalendar(event, "toDate");
	});

	$("#icnOrgSearch").click(function(event) {
		popupLookup = commonJs.openPopup({
			popupId:"OrganisationLookup",
			url:"/common/lookup/getDefault.do",
			data:{
				lookupType:"organisationName",
				keyFieldId:"orgId",
				valueFieldId:"orgName",
				popupToSetValue:"parent",
				popupName:"parent.popupLookup",
				lookupValue:$("#orgName").val(),
				callback:"doSearch"
			},
			header:"Organisation Lookup",
			width:880,
			height:680
		});
	});

	$("#orgName").blur(function() {
		if (commonJs.isBlank($(this).val())) {
			$("#orgId").val("");
		}
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
	setExportButtonContextMenu = () => {
		exportCtxMenu[0].fun = function() {doExport(exportCtxMenu[0]);};
		exportCtxMenu[1].fun = function() {doExport(exportCtxMenu[1]);};

		$("#btnExport").contextMenu(exportCtxMenu, {
			classPrefix:com.constants.ctxClassPrefixButton,
			effectDuration:300,
			effect:"slide",
			borderRadius:"bottom 4px",
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0
		});
	}

	doSearch = function() {
		if (commonJs.doValidate($("#fmDefault"))) {
			commonJs.showProcMessageOnElement("divScrollablePanel");
			commonJs.doSearch({
				url:"/rpa/0206/getList.do",
				onSuccess:renderDataGrid
			});
		}
	};

	renderDataGrid = function(result) {
		var ds = result.dataSet;
		var html = "";

		searchResultDataCount = ds.getRowCnt();
		$("#tblGridBody").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr();
				var parentCategoryId = ds.getValue(i, "PARENT_CATEGORY_ID");

				if (commonJs.isNotBlank(parentCategoryId)) {
					gridTr.addChild(new UiGridTd().addClassName("Lt").setText("&nbsp;&nbsp;&nbsp;&nbsp;"+commonJs.nvl(ds.getValue(i, "CATEGORY_NAME"), "&nbsp;")));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getAccountingFormat(ds.getValue(i, "PROC_AMT_SEP"))));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getAccountingFormat(ds.getValue(i, "PROC_AMT_DEC"))));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getAccountingFormat(ds.getValue(i, "PROC_AMT_MAR"))));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getAccountingFormat(ds.getValue(i, "PROC_AMT_JUN"))));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getAccountingFormat(ds.getValue(i, "THIS_YEAR_PROC_AMT"))).setStyle("background:#f5f5f5;"));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getAccountingFormat(ds.getValue(i, "LAST_YEAR_PROC_AMT"))).setStyle("background:#f5f5f5;"));
				} else {
					var div = ds.getValue(i, "DIV");

					if (div == "0") {
						gridTr.addChild(new UiGridTd().addClassName("Lt").setText(commonJs.nvl(ds.getValue(i, "CATEGORY_NAME"), "&nbsp;")).setStyle("font-weight:bold;"));
						gridTr.addChild(new UiGridTd().addClassName("Rt").setText(""));
						gridTr.addChild(new UiGridTd().addClassName("Rt").setText(""));
						gridTr.addChild(new UiGridTd().addClassName("Rt").setText(""));
						gridTr.addChild(new UiGridTd().addClassName("Rt").setText(""));
						gridTr.addChild(new UiGridTd().addClassName("Rt").setText("").setStyle("background:#f5f5f5;"));
						gridTr.addChild(new UiGridTd().addClassName("Rt").setText("").setStyle("background:#f5f5f5;"));
					} else {
						if (i != ds.getRowCnt()-1) {
							gridTr.addChild(new UiGridTd().addClassName("Lt").setText(commonJs.nvl(ds.getValue(i, "CATEGORY_NAME"), "&nbsp;")));
							gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getAccountingFormat(ds.getValue(i, "PROC_AMT_SEP"))));
							gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getAccountingFormat(ds.getValue(i, "PROC_AMT_DEC"))));
							gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getAccountingFormat(ds.getValue(i, "PROC_AMT_MAR"))));
							gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getAccountingFormat(ds.getValue(i, "PROC_AMT_JUN"))));
							gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getAccountingFormat(ds.getValue(i, "THIS_YEAR_PROC_AMT"))));
							gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getAccountingFormat(ds.getValue(i, "LAST_YEAR_PROC_AMT"))));
						} else {
							gridTr.addChild(new UiGridTd().addClassName("Lt").setText(commonJs.nvl(ds.getValue(i, "CATEGORY_NAME"), "&nbsp;")));
							gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getParenthesisFormat(ds.getValue(i, "PROC_AMT_SEP"))));
							gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getParenthesisFormat(ds.getValue(i, "PROC_AMT_DEC"))));
							gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getParenthesisFormat(ds.getValue(i, "PROC_AMT_MAR"))));
							gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getParenthesisFormat(ds.getValue(i, "PROC_AMT_JUN"))));
							gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getParenthesisFormat(ds.getValue(i, "THIS_YEAR_PROC_AMT"))));
							gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getParenthesisFormat(ds.getValue(i, "LAST_YEAR_PROC_AMT"))));
						}

						if (commonJs.isNotBlank(div)) {
							gridTr.setStyle("font-weight:bold;background:#fffef4;");
						}
					}
				}

				if (i == ds.getRowCnt()-1) {
					gridTr.setStyle("font-weight:bold;border-top:2px solid #cccccc;border-bottom:2px solid #cccccc;background:#f5f5f5;");
				}

				gridTr.setClassName("noStripe");

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:7").setText(com.message.I001));
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

		commonJs.hideProcMessageOnElement("divScrollablePanel");
	};

	doExport = (menuObject) => {
		if (searchResultDataCount <= 0) {
			commonJs.alert("There is no data to export.");
			return;
		}

		commonJs.doExport({
			url:"/rpa/0206/doExport.do",
			data:commonJs.serialiseObject($("#divSearchCriteriaArea")),
			menuObject:menuObject
		});

//		setTimeout(() => popup.close(), 5000);
	};
	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		setExportButtonContextMenu();
		commonJs.setFieldDateMask("fromDate");
		commonJs.setFieldDateMask("toDate");

		commonJs.setAutoComplete($("#orgName"), {
			method:"getOrgId",
			label:"legal_name",
			value:"org_id",
			focus: function(event, ui) {
				$("#orgId").val(ui.item.value);
				$("#orgName").val(ui.item.label);
				return false;
			},
			change:function(event, ui) {
				if (commonJs.isEmpty($("#orgName").val())) {
					$("#orgId").val("");
					$("#orgName").val("");
				}
			},
			select:function(event, ui) {
				$("#orgId").val(ui.item.value);
				$("#orgName").val(ui.item.label);
				doSearch();
				return false;
			}
		});
	});
});