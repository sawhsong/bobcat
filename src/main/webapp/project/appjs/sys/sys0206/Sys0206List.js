/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sys0206List.js
 *************************************************************************************************/
jsconfig.put("scrollablePanelHeightAdjust", -2);
var popup = null;
var searchResultDataCount = 0;

$(function() {
	/*!
	 * event
	 */
	$("#btnNew").click(function(event) {
		openPopup({mode:"Insert"});
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

	$("#icnCheck").click(function(event) {
		commonJs.toggleCheckboxes("chkForDel");
	});

	$(document).keydown(function(event) {
		var code = event.keyCode || event.which, element = event.target;

		if (code == 13) {
			if ($(element).is("[name=orgName]") || $(element).is("[name=abn]")) {
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
			url:"/sys/0206/getList.do",
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
				var userCnt = ds.getValue(i, "USER_CNT");
				var className = "chkEn", disabledStr = "";

				if (userCnt > 0) {
					className = "chkDis";
					disabledStr = "disabled";
				}

				var iconAction = new UiIcon();
				iconAction.setId("icnAction").setName("icnAction").addClassName("fa-ellipsis-h fa-lg").addAttribute("userCnt:"+userCnt).addAttribute("orgId:"+ds.getValue(i, "ORG_ID")).setScript("doAction(this)");
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(iconAction));

				var uiChk = new UiCheckbox();
				uiChk.setId("chkForDel").setName("chkForDel").setClassName(className+" inTblGrid").setValue(ds.getValue(i, "ORG_ID")).addOptions(disabledStr);
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiChk));

				var uiAnc = new UiAnchor();
				uiAnc.setText(ds.getValue(i, "LEGAL_NAME")).setScript("getEdit('"+ds.getValue(i, "ORG_ID")+"')");
				gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAnc));

				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "TRADING_NAME")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(commonJs.getFormatString(ds.getValue(i, "ABN"), "?? ??? ??? ???")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(commonJs.getFormatString(ds.getValue(i, "TEL_NUMBER"), "?? ???? ????")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(commonJs.getFormatString(ds.getValue(i, "MOBILE_NUMBER"), "???? ??? ???")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "EMAIL")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "USER_CNT"), "#,###")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "ENTITY_TYPE_NAME")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "BUSINESS_TYPE_NAME")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "REGISTERED_DATE")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "IS_ACTIVE")));

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:13").setText(com.message.I001));
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

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenu.commonSimpleAction);
		});

		commonJs.bindToggleTrBackgoundWithCheckbox($("[name=chkForDel]"));
		commonJs.hideProcMessageOnElement("divScrollablePanel");
	};

	getEdit = function(orgId) {
		openPopup({
			mode:"Update",
			orgId:orgId
		});
	};

	openPopup = function(param) {
		var url = "", header = "", width = 0, height = 0;

		if (param.mode == "Insert" || param.mode == "Update") {
			url = "/sys/0206/getEdit.do";
			header = com.header.popHeaderEdit;
			width = 1000; height = 600;
		}

		var popParam = {
			popupId:"orgInfo"+param.mode,
			url:url,
			data:param,
			header:header,
			blind:true,
			width:1000,
			height:height
		};

		popup = commonJs.openPopup(popParam);
	};

	doDelete = function() {
		if (commonJs.getCountChecked("chkForDel") == 0) {
			commonJs.warn(com.message.I902);
			return;
		}

		commonJs.doDelete({
			url:"/sys/0206/exeDelete.do",
			callback:doSearch
		});
	};

	doAction = function(img) {
		var orgId = $(img).attr("orgId"), userCnt = $(img).attr("userCnt");

		$("input:checkbox[name=chkForDel]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == orgId) {
				$(this).click();
			} else {
				if ($(this).is(":checked")) {
					$(this).click();
				}
			}
		});

		if (userCnt > 0) {
			ctxMenu.commonSimpleAction[1].disable = true;
		} else {
			ctxMenu.commonSimpleAction[1].disable = false;
		}

		ctxMenu.commonSimpleAction[0].fun = function() {openPopup({mode:"Update", orgId:orgId});};
		ctxMenu.commonSimpleAction[1].fun = function() {doDelete();};

		$(img).contextMenu(ctxMenu.commonSimpleAction, {
			classPrefix:com.constants.ctxClassPrefixGrid,
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0,
			verAdjust:2
		});
	};

	exeExport = function(menuObject) {
		$("[name=fileType]").remove();
		$("[name=dataRange]").remove();

		if (searchResultDataCount <= 0) {
			commonJs.warn(com.message.I001);
			return;
		}

		commonJs.doExport({
			url:"/sys/0206/exeExport.do",
			data:commonJs.serialiseObject($("#divSearchCriteriaArea")),
			menuObject:menuObject
		});
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
//		commonJs.setExportButtonContextMenu($("#btnExport"));
		commonJs.setEvent("change", [$("#entityType"), $("#businessType"), $("#orgCategory")], doSearch);

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