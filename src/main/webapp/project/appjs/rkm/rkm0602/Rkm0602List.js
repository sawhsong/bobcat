/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Rkm0602List.js
 *************************************************************************************************/
jsconfig.put("useJqTooltip", false);
var popup = null;
var searchResultDataCount = 0;
var repaymentTypeMenu = [];

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

	$("#icnCheck").click(function(event) {
		commonJs.toggleCheckboxes("chkForDel");
	});

	$("#icnDataEntryDate").click(function(event) {
		commonJs.openCalendar(event, "deDate");
	});

	$("#financialYear").change(function() {
		doSearch();
	});

	$("#quarterName").change(function() {
		doSearch();
	});

	$("#repaymentType").change(function() {
		doSearch();
	});

	$(document).keypress(function(event) {
		if (event.which == 13) {
			var element = event.target;
		}
	});

	setDeRepaymentTypeContextMenu = function() {
		commonJs.ajaxSubmit({
			url:"/common/entryTypeSupporter/getRepaymentTypeForContextMenu.do",
			dataType:"json",
			data:{},
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");
				if (result.isSuccess == true || result.isSuccess == "true") {
					var ds = result.dataSet;

					if (ds.getRowCnt() > 0) {
						for (var i=0; i<ds.getRowCnt(); i++) {
							var repaymentType = ds.getValue(i, "REPAYMENT_TYPE");

							repaymentTypeMenu.push({
								name:ds.getValue(i, "DESCRIPTION"),
								userData:repaymentType,
								fun:function() {
									setDeRepaymentTypeSelectbox($(this).attr("userData"));
								}
							});
						}
					}
				} else {
					commonJs.error(result.message);
				}
			}
		});

		$("#btnDeRepaymentType").contextMenu(repaymentTypeMenu, {
			borderRadius : "4px",
			displayAround : "trigger",
			position : "bottom",
			horAdjust : 0,
			verAdjust : 0
		});
	};

	setDataEntryActionButtonContextMenu = function() {
		ctxMenu.dataEntryAction[0].fun = function() {};
		ctxMenu.dataEntryAction[1].fun = function() {};
		ctxMenu.dataEntryAction[2].fun = function() {};

		$("#icnDataEntryAction").contextMenu(ctxMenu.dataEntryAction, {
			classPrefix:com.constants.ctxClassPrefixGrid,
			effectDuration:300,
			borderRadius:"bottom 4px",
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0
		});
	};
	/*!
	 * process
	 */
	doSearch = function() {
		commonJs.showProcMessageOnElement("divScrollablePanel");

		setTimeout(function() {
			commonJs.ajaxSubmit({
				url:"/rkm/0602/getList.do",
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

		setSummaryDataForAdminTool();
	};

	renderDataGridTable = function(result) {
		var ds = result.dataSet;
		var html = "", totHtml = "", totAmt = 0;
		var totGridTr = new UiGridTr();

		searchResultDataCount = ds.getRowCnt();
		$("#tblGridBody").html("");
		$("#tblGridFoot").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr();
				totAmt += parseFloat(ds.getValue(i, "REPAYMENT_AMT"));

				var uiChk = new UiCheckbox();
				uiChk.setId("chkForDel").setName("chkForDel").setValue(ds.getValue(i, "FINANCE_ID"));
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiChk));

				var uiAnc = new UiAnchor();
				uiAnc.setText(ds.getValue(i, "FINANCE_DATE")).setScript("getDetail('"+ds.getValue(i, "FINANCE_ID")+"')");
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiAnc));

				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(commonJs.abbreviate(ds.getValue(i, "FINANCE_TYPE_DESC"), 60)));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "REPAYMENT_AMT"), "#,###.##")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(commonJs.abbreviate(ds.getValue(i, "DESCRIPTION"), 60)));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "IS_COMPLETED")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "ENTRY_DATE")));

				var iconAction = new UiIcon();
				iconAction.setId("icnAction").setName("icnAction").addClassName("fa-tasks fa-lg").addAttribute("financeId:"+ds.getValue(i, "FINANCE_ID")).setScript("doAction(this)");
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(iconAction));

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:8").setText(com.message.I001));
			html += gridTr.toHtmlString();
		}

		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Ct").setText(com.caption.total));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(totAmt, "#,###.##")));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));

		totHtml = totGridTr.toHtmlString();

		$("#tblGridBody").append($(html));
		$("#tblGridFoot").append($(totHtml));

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
			$(this).contextMenu(ctxMenu.dataEntryListAction);
		});

		commonJs.hideProcMessageOnElement("divScrollablePanel");
	};

	getDetail = function(articleId) {
		openPopup({mode:"Detail", articleId:articleId});
	};

	openPopup = function(param) {
		var url = "", header = "";
		var height = 510;

		if (param.mode == "Detail") {
			url = "/rkm/0602/getDetail.do";
			header = com.header.popHeaderDetail;
		} else if (param.mode == "New" || param.mode == "Reply") {
			url = "/rkm/0602/getInsert.do";
			header = com.header.popHeaderEdit;
		} else if (param.mode == "Edit") {
			url = "/rkm/0602/getUpdate.do";
			header = com.header.popHeaderEdit;
			height = 634;
		}

		var popParam = {
			popupId:"notice"+param.mode,
			url:url,
			paramData:{
				mode:param.mode,
				articleId:commonJs.nvl(param.articleId, "")
			},
			header:header,
			blind:true,
			width:800,
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
						url:"/rkm/0602/exeDelete.do",
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
		var articleId = $(img).attr("articleId");

		$("input:checkbox[name=chkForDel]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == articleId) {
				$(this).prop("checked", true);
			} else {
				$(this).prop("checked", false);
			}
		});

		ctxMenu.dataEntryListAction[0].fun = function() {openPopup({mode:"Edit", articleId:articleId});};
		ctxMenu.dataEntryListAction[1].fun = function() {doDelete();};

		$(img).contextMenu(ctxMenu.dataEntryListAction, {
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
						url:"/rkm/0602/exeExport.do",
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

	setDeRepaymentTypeSelectbox = function(type) {
		$("#deRepaymentType").val(type);
		$("#deRepaymentType").selectpicker("val", type);
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		commonJs.setExportButtonContextMenu($("#btnExport"));
		setDeRepaymentTypeContextMenu();
		setDataEntryActionButtonContextMenu();
		commonJs.setFieldDateMask("deDate");
		$(".numeric").number(true, 2);
		doSearch();
	});
});