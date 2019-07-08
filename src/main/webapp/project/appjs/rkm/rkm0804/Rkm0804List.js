/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Rkm0804List.js
 *************************************************************************************************/
jsconfig.put("useJqTooltip", false);
var popup = null;
var widthEmpListDiv, widthWageListDiv, empListWidthAdjust, wageListWidthAdjust;

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

	$("#icnDeStartDate").click(function(event) {
		commonJs.openCalendar(event, "deStartDate");
	});

	$("#icnDeEndDate").click(function(event) {
		commonJs.openCalendar(event, "deEndDate");
	});

	$("#financialYear").change(function() {
		doSearch();
	});

	$("#quarterName").change(function() {
		doSearch();
	});

	$("#visaType").change(function() {
		doSearch();
	});

	$(document).keypress(function(event) {
		if (event.which == 13) {
			var element = event.target;
		}
	});

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
		commonJs.showProcMessageOnElement("tblEmpList");

		setTimeout(function() {
			commonJs.ajaxSubmit({
				url:"/rkm/0804/getEmployeeList.do",
				dataType:"json",
				formId:"fmDefault",
				success:function(data, textStatus) {
					var result = commonJs.parseAjaxResult(data, textStatus, "json");

					if (result.isSuccess == true || result.isSuccess == "true") {
						renderEmpListGridTable(result);
					} else {
						commonJs.error(result.message);
					}
				}
			});
		}, 200);

		setSummaryDataForAdminTool();
	};

	renderEmpListGridTable = function(result) {
		var ds = result.dataSet;
		var html = "", totHtml = "", grossTotal = 0, taxTotal = 0, netTotal = 0, superTotal = 0;
		var totGridTr = new UiGridTr();

		searchResultDataCount = ds.getRowCnt();
		$("#tblEmpListBody").html("");
		$("#tblEmpListFoot").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr();

				grossTotal += parseFloat(ds.getValue(i, "GROSS_WAGE"));
				taxTotal += parseFloat(ds.getValue(i, "TAX"));
				netTotal += parseFloat(ds.getValue(i, "NET_WAGE"));
				superTotal += parseFloat(ds.getValue(i, "SUPER_AMT"));

				var uiAncSurname = new UiAnchor();
				uiAncSurname.setText(ds.getValue(i, "SURNAME")).setScript("getWageList('"+$("#financialYear").val()+"', '"+$("#quarterName").val()+"', '"+ds.getValue(i, "EMPLOYEE_ID")+"')");
				gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAncSurname));

				var uiAncGivenName = new UiAnchor();
				uiAncGivenName.setText(ds.getValue(i, "GIVEN_NAME")).setScript("getWageList('"+$("#financialYear").val()+"', '"+$("#quarterName").val()+"', '"+ds.getValue(i, "EMPLOYEE_ID")+"')");
				gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAncGivenName));

				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "GROSS_WAGE"), "#,###.##")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "TAX"), "#,###.##")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "NET_WAGE"), "#,###.##")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "SUPER_AMT"), "#,###.##")));

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:6").setText(com.message.I001));
			html += gridTr.toHtmlString();
		}

		totGridTr.addChild(new UiGridTd().addClassName("Ct").setText(com.caption.total));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(grossTotal, "#,###.##")));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(taxTotal, "#,###.##")));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(netTotal, "#,###.##")));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(superTotal, "#,###.##")));

		totHtml = totGridTr.toHtmlString();

		$("#tblEmpListBody").append($(html));
		$("#tblEmpListFoot").append($(totHtml));

		$("#tblEmpList").fixedHeaderTable({
			attachTo:$("#divEmpList"),
			pagingArea:$("#divEmpListPagingArea"),
			isPageable:false,
			isFilter:false,
			filterColumn:[],
			totalResultRows:result.totalResultRows,
			script:"doSearch"
		});

		commonJs.hideProcMessageOnElement("tblEmpList");
		getWageList("", "", "");
	};

	getWageList = function(financialYear, quarterName, employeeId) {
		commonJs.showProcMessageOnElement("tblWageList");

		setTimeout(function() {
			commonJs.ajaxSubmit({
				url:"/rkm/0804/getWageList.do",
				dataType:"json",
				data:{
					financialYear:financialYear,
					quarterName:quarterName,
					employeeId:employeeId
				},
				success:function(data, textStatus) {
					var result = commonJs.parseAjaxResult(data, textStatus, "json");

					if (result.isSuccess == true || result.isSuccess == "true") {
						renderWageListGridTable(result);
					} else {
						commonJs.error(result.message);
					}
				}
			});
		}, 200);
	};

	renderWageListGridTable = function(result) {
		var ds = result.dataSet;
		var html = "", totHtml = "", hourWorked = 0, grossTotal = 0, taxTotal = 0, netTotal = 0, superTotal = 0;
		var totGridTr = new UiGridTr();

		$("#tblWageListBody").html("");
		$("#tblWageListFoot").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr();

				hourWorked += parseFloat(ds.getValue(i, "HOUR_WORKED"));
				grossTotal += parseFloat(ds.getValue(i, "GROSS_WAGE"));
				taxTotal += parseFloat(ds.getValue(i, "TAX"));
				netTotal += parseFloat(ds.getValue(i, "NET_WAGE"));
				superTotal += parseFloat(ds.getValue(i, "SUPER_AMT"));

				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "START_DATE")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "END_DATE")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "HOURLY_RATE"), "#,###.##")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "HOUR_WORKED"), "#,###.##")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "GROSS_WAGE"), "#,###.##")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "TAX"), "#,###.##")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "NET_WAGE"), "#,###.##")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "SUPER_AMT"), "#,###.##")));

				var iconAction = new UiIcon();
				iconAction.setId("icnAction").setName("icnAction").addClassName("fa-tasks fa-lg").addAttribute("employeeId:"+ds.getValue(i, "EMPLOYEE_ID")).setScript("doAction(this)");
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(iconAction));

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:9").setText(com.message.I001));
			html += gridTr.toHtmlString();
		}

		totGridTr.addChild(new UiGridTd().addClassName("Ct").setText(com.caption.total));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(hourWorked, "#,###.##")));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(grossTotal, "#,###.##")));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(taxTotal, "#,###.##")));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(netTotal, "#,###.##")));
		totGridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(superTotal, "#,###.##")));
		totGridTr.addChild(new UiGridTd().addClassName("Ct"));

		totHtml = totGridTr.toHtmlString();

		$("#tblWageListBody").append($(html));
		$("#tblWageListFoot").append($(totHtml));

		$("#tblWageList").fixedHeaderTable({
			attachTo:$("#divWageList"),
			pagingArea:$("#divWageListPagingArea"),
			isPageable:false,
			isFilter:false,
			filterColumn:[],
			totalResultRows:result.totalResultRows,
			script:"doSearch"
		});

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenu.dataEntryListAction);
		});

		commonJs.hideProcMessageOnElement("tblWageList");
	};

	getDetail = function(articleId) {
		openPopup({mode:"Detail", articleId:articleId});
	};

	openPopup = function(param) {
		var url = "", header = "";
		var height = 510;

		if (param.mode == "Detail") {
			url = "/rkm/0804/getDetail.do";
			header = com.header.popHeaderDetail;
		} else if (param.mode == "New" || param.mode == "Reply") {
			url = "/rkm/0804/getInsert.do";
			header = com.header.popHeaderEdit;
		} else if (param.mode == "Edit") {
			url = "/rkm/0804/getUpdate.do";
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
						url:"/rkm/0804/exeDelete.do",
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

		ctxMenu.boardAction[0].fun = function() {getDetail(articleId);};
		ctxMenu.boardAction[1].fun = function() {openPopup({mode:"Edit", articleId:articleId});};
		ctxMenu.boardAction[2].fun = function() {openPopup({mode:"Reply", articleId:articleId});};
		ctxMenu.boardAction[3].fun = function() {doDelete();};

		$(img).contextMenu(ctxMenu.boardAction, {
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
						url:"/rkm/0804/exeExport.do",
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

	setGridSize = function() {
		$("#divScrollablePanel").height($("#divScrollablePanel").outerHeight()-2);
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		setGridSize();
		commonJs.setExportButtonContextMenu($("#btnExport"));
		setDataEntryActionButtonContextMenu();
		commonJs.setAutoComplete($("#surname"), {
			method:"getEmployeeSurname",
			label:"surname",
			value:"surname",
			focus:function(event, ui) {
				$("#surname").val(ui.item.label);
				return false;
			},
			select:function(event, ui) {
				doSearch();
			}
		});

		commonJs.setAutoComplete($("#givenName"), {
			method:"getEmployeeGivenName",
			label:"given_name",
			value:"given_name",
			focus:function(event, ui) {
				$("#givenName").val(ui.item.label);
				return false;
			},
			select:function(event, ui) {
				doSearch();
			}
		});

		commonJs.setFieldDateMask("deStartDate");
		commonJs.setFieldDateMask("deEndDate");
		$(".numeric").number(true, 2);
		doSearch();
	});
});