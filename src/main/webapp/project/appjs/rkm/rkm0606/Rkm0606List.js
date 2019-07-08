/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Rkm0606List.js
 *************************************************************************************************/
jsconfig.put("useJqTooltip", false);
var popup = null;
var searchResultDataCount = 0;
var lendingTypeMenu = [];

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

	$("#icnDataEntryStartDate").click(function(event) {
		commonJs.openCalendar(event, "deStartDate");
	});

	$("#icnDataEntryEndDate").click(function(event) {
		commonJs.openCalendar(event, "deEndDate");
	});

	$("#financialYear").change(function() {
		doSearch();
	});

	$("#quarterName").change(function() {
		doSearch();
	});

	$("#lendingType").change(function() {
		doSearch();
	});

	$(document).keypress(function(event) {
		if (event.which == 13) {
			var element = event.target;
		}
	});

	setDeLendingTypeContextMenu = function() {
		commonJs.ajaxSubmit({
			url:"/common/entryTypeSupporter/getLendingTypeForContextMenu.do",
			dataType:"json",
			data:{},
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");
				if (result.isSuccess == true || result.isSuccess == "true") {
					var ds = result.dataSet;

					if (ds.getRowCnt() > 0) {
						for (var i=0; i<ds.getRowCnt(); i++) {
							var lendingType = ds.getValue(i, "LENDING_TYPE");

							lendingTypeMenu.push({
								name:ds.getValue(i, "DESCRIPTION"),
								userData:lendingType,
								fun:function() {
									setDeLendingTypeSelectbox($(this).attr("userData"));
								}
							});
						}
					}
				} else {
					commonJs.error(result.message);
				}
			}
		});

		$("#btnDeLendingType").contextMenu(lendingTypeMenu, {
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
				url:"/rkm/0606/getList.do",
				dataType:"json",
				formId:"fmDefault",
				success:function(data, textStatus) {
					var result = commonJs.parseAjaxResult(data, textStatus, "json");

					if (result.isSuccess == true || result.isSuccess == "true") {
						renderDataGridTable(result);
					} else {
						commonJs.error(result.message);
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
				totAmt += parseFloat(ds.getValue(i, "PRINCIPAL_AMT"));

				var uiChk = new UiCheckbox();
				uiChk.setId("chkForDel").setName("chkForDel").setValue(ds.getValue(i, "FINANCE_ID"));
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiChk));

				var uiAnc = new UiAnchor();
				uiAnc.setText(ds.getValue(i, "FINANCE_DATE")).setScript("getDetail('"+ds.getValue(i, "FINANCE_ID")+"')");
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiAnc));

				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(commonJs.abbreviate(ds.getValue(i, "FINANCE_TYPE_DESC"), 60)));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "PRINCIPAL_AMT"), "#,###.##")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "INTEREST_PERCENTAGE"), "#,###.##")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "START_DATE")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "END_DATE")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "FINANCE_FILE_CNT"), "#,###")));
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

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:12").setText(com.message.I001));
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
			url = "/rkm/0606/getDetail.do";
			header = com.header.popHeaderDetail;
		} else if (param.mode == "New" || param.mode == "Reply") {
			url = "/rkm/0606/getInsert.do";
			header = com.header.popHeaderEdit;
		} else if (param.mode == "Edit") {
			url = "/rkm/0606/getUpdate.do";
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
						url:"/rkm/0606/exeDelete.do",
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

	getAttachedFile = function(img) {
		commonJs.ajaxSubmit({
			url:"/rkm/0606/getAttachedFile.do",
			dataType:"json",
			data:{
				articleId:$(img).attr("articleId")
			},
			blind:false,
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");

				if (result.isSuccess == true || result.isSuccess == "true") {
					var dataSet = result.dataSet;
					attchedFileContextMenu = [];

					for (var i=0; i<dataSet.getRowCnt(); i++) {
						var repositoryPath = dataSet.getValue(i, "REPOSITORY_PATH");
						var originalName = dataSet.getValue(i, "ORIGINAL_NAME");
						var newName = dataSet.getValue(i, "NEW_NAME");
						var fileIcon = dataSet.getValue(i, "FILE_ICON");
						var fileSize = dataSet.getValue(i, "FILE_SIZE")/1024;

						attchedFileContextMenu.push({
							name:originalName+" ("+commonJs.getNumberMask(fileSize, "0,0")+") KB",
							title:originalName,
							img:fileIcon,
							repositoryPath:repositoryPath,
							originalName:originalName,
							newName:newName,
							fun:function() {
								var index = $(this).index();

								downloadFile({
									repositoryPath:attchedFileContextMenu[index].repositoryPath,
									originalName:attchedFileContextMenu[index].originalName,
									newName:attchedFileContextMenu[index].newName
								});
							}
						});
					}

					$(img).contextMenu(attchedFileContextMenu, {
						classPrefix:com.constants.ctxClassPrefixGrid,
						displayAround:"trigger",
						position:"bottom",
						horAdjust:0,
						verAdjust:2
					});
				} else {
					commonJs.error(result.message);
				}
			}
		});
	};

	downloadFile = function(param) {
		commonJs.doSubmit({
			form:"fmDefault",
			action:"/download.do",
			data:{
				repositoryPath:param.repositoryPath,
				originalName:param.originalName,
				newName:param.newName
			}
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
						url:"/rkm/0606/exeExport.do",
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

	setDeLendingTypeSelectbox = function(type) {
		$("#deLendingType").val(type);
		$("#deLendingType").selectpicker("val", type);
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		commonJs.setExportButtonContextMenu($("#btnExport"));
		setDeLendingTypeContextMenu();
		setDataEntryActionButtonContextMenu();
		commonJs.setFieldDateMask("deDate");
		commonJs.setFieldDateMask("deStartDate");
		commonJs.setFieldDateMask("deEndDate");
		$(".numeric").number(true, 2);
		doSearch();
	});
});