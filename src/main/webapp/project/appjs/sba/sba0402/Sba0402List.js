/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sba0402List.js
 *************************************************************************************************/
jsconfig.put("useJqTooltip", false);
var popup = null;
var searchResultDataCount = 0;
var attchedFileContextMenu = [];

$(function() {
	/*!
	 * event
	 */
	$("#btnNew").click(function(event) {
		openPopup({mode:"New"});
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

	$(document).keypress(function(event) {
		if (event.which == 13) {
			var element = event.target;
		}
	});

	/*!
	 * process
	 */
	doSearch = function() {
		commonJs.showProcMessageOnElement("divScrollablePanel");

		if (commonJs.doValidate($("#fmDefault"))) {
			setTimeout(function() {
				commonJs.ajaxSubmit({
					url:"/sba/0402/getList",
					dataType:"json",
					formId:"fmDefault",
					success:function(data, textStatus) {
						var result = commonJs.parseAjaxResult(data, textStatus, "json");

						if (result.isSuccess == true || result.isSuccess == "true") {
							renderDataGridTable(result);
						}
					}
				});
			}, 200);
		}
	};

	renderDataGridTable = function(result) {
		var ds = result.dataSet;
		var html = "";

		searchResultDataCount = ds.getRowCnt();
		$("#tblGridBody").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr();

				var uiChk = new UiCheckbox();
				uiChk.setId("chkForDel").setName("chkForDel").setValue(ds.getValue(i, "PERIOD_YEAR")+"_"+ds.getValue(i, "QUARTER_CODE"));
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiChk));

				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "PERIOD_YEAR")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "FINANCIAL_YEAR")));

				var uiAnc = new UiAnchor();
				uiAnc.setText(ds.getValue(i, "QUARTER_CODE_NAME")).setScript("getDetail('"+ds.getValue(i, "PERIOD_YEAR")+"', '"+ds.getValue(i, "QUARTER_CODE")+"')");
				gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAnc));

				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "QUARTER_NAME_DESC")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "DATE_FROM")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "DATE_TO")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "INSERT_DATE")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "UPDATE_DATE")));

				var iconAction = new UiIcon();
				iconAction.setId("icnAction").setName("icnAction").addClassName("fa-tasks fa-lg").addAttribute("periodYear:"+ds.getValue(i, "PERIOD_YEAR")).addAttribute("quarterCode:"+ds.getValue(i, "QUARTER_CODE"))
					.setScript("doAction(this)");
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(iconAction));

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:10").setText(com.message.I001));
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

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenu.commonAction);
		});

		commonJs.hideProcMessageOnElement("divScrollablePanel");
	};

	getDetail = function(periodYear, quarterCode) {
		openPopup({
			mode:"Detail",
			periodYear:periodYear,
			quarterCode:quarterCode
		});
	};

	openPopup = function(param) {
		var url = "", header = "";
		var height = 170;

		if (param.mode == "Detail") {
			url = "/sba/0402/getDetail";
			header = com.header.popHeaderDetail;
		} else if (param.mode == "New") {
			url = "/sba/0402/getInsert";
			header = com.header.popHeaderEdit;
		} else if (param.mode == "Edit") {
			url = "/sba/0402/getUpdate";
			header = com.header.popHeaderEdit;
			height = 250;
		}

		var popParam = {
			popupId:"financialPeriod"+param.mode,
			url:url,
			paramData:{
				mode:param.mode,
				periodYear:param.periodYear,
				quarterCode:param.quarterCode
			},
			header:header,
			blind:true,
			width:700,
			height:height
		};

		popup = commonJs.openPopup(popParam);
	};

	doDelete = function() {
		if (commonJs.getCountChecked("chkForDel") == 0) {
			commonJs.warn(com.message.I902);
			return;
		}

		commonJs.confirm({
			contents:com.message.Q002,
			buttons:[{
				caption:com.caption.yes,
				callback:function() {
					commonJs.ajaxSubmit({
						url:"/sba/0402/exeDelete",
						dataType:"json",
						formId:"fmDefault",
						success:function(data, textStatus) {
							var result = commonJs.parseAjaxResult(data, textStatus, "json");

							if (result.isSuccess == true || result.isSuccess == "true") {
								commonJs.openDialog({
									type:com.message.I000,
									contents:result.message,
									blind:true,
									width:300,
									buttons:[{
										caption:com.caption.ok,
										callback:function() {
											doSearch();
										}
									}]
								});
							} else {
								commonJs.error(result.message);
							}
						}
					});
				}
			}, {
				caption:com.caption.no,
				callback:function() {
				}
			}],
			blind:true
		});
	};

	doAction = function(img) {
		var periodYear = $(img).attr("periodYear");
		var quarterCode = $(img).attr("quarterCode");

		$("input:checkbox[name=chkForDel]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == periodYear+"_"+quarterCode) {
				$(this).prop("checked", true);
			} else {
				$(this).prop("checked", false);
			}
		});

		ctxMenu.commonAction[0].fun = function() {getDetail(periodYear, quarterCode);};
		ctxMenu.commonAction[1].fun = function() {openPopup({mode:"Edit", periodYear:periodYear, quarterCode:quarterCode});};
		ctxMenu.commonAction[2].fun = function() {doDelete();};

		$(img).contextMenu(ctxMenu.commonAction, {
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

		commonJs.confirm({
			contents:com.message.Q003,
			buttons:[{
				caption:com.caption.yes,
				callback:function() {
					popup = commonJs.openPopup({
						popupId:"exportFile",
						url:"/sba/0402/exeExport",
						paramData:{
							fileType:menuObject.fileType,
							dataRange:menuObject.dataRange
						},
						header:"exportFile",
						blind:false,
						width:200,
						height:100
					});
					setTimeout(function() {popup.close();}, 3000);
				}
			}, {
				caption:com.caption.no,
				callback:function() {
				}
			}],
			blind:true
		});
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		commonJs.setExportButtonContextMenu($("#btnExport"));
		$("#periodYear").focus();
		doSearch();
	});
});