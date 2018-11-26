/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sba0406List.js
 *************************************************************************************************/
jsconfig.put("useJqTooltip", false);
var popup = null;
var searchResultDataCount = 0;
var menu = [];

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

	$("#btnSetSort").click(function(event) {
		openPopup({mode:"SetSort"});
	});

	$("#orgCategory").change(function() {
		doSearch();
	});

	$(document).keypress(function(event) {
		if (event.which == 13) {
			var element = event.target;
		}
	});

	/*!
	 * context menus
	 */
	setPreviewContextMenu = function() {
		$("#btnPreview").contextMenu(menu, {
			classPrefix:com.constants.ctxClassPrefixButton,
			borderRadius : "4px",
			displayAround : "trigger",
			position : "bottom",
			horAdjust : 0,
			verAdjust : 0
		});
	};

	/*!
	 * process
	 */
	doSearch = function() {
		commonJs.showProcMessageOnElement("divScrollablePanel");

		if (commonJs.doValidate($("#fmDefault"))) {
			setTimeout(function() {
				commonJs.ajaxSubmit({
					url:"/sba/0406/getList.do",
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
		var html = "", delimiter = "_";
		var mainMenu = {}, subMenu = [];

		menu = [];
		searchResultDataCount = ds.getRowCnt();
		$("#tblGridBody").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var id = ds.getValue(i, "EXPENSE_TYPE_ID");
				var path = ds.getValue(i, "PATH");
				var orgCategory = ds.getValue(i, "ORG_CATEGORY");
				var expenseType = ds.getValue(i, "EXPENSE_TYPE");
				var parentExpenseType = ds.getValue(i, "PARENT_EXPENSE_TYPE");
				var space = "", style = "", paramValue = "";
				var iLevel = parseInt(ds.getValue(i, "LEVEL"));
				var isLeaf = parseInt(ds.getValue(i, "IS_LEAF"));
				var gridTr = new UiGridTr();

				var uiChk = new UiCheckbox();
				uiChk.setId("chkForDel").setName("chkForDel").setValue(path);
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiChk));

				if (isLeaf == 0) {
					var uiAnc = new UiAnchor();
					uiAnc.setText(ds.getValue(i, "DESCRIPTION")).setScript("getDetail('"+path+"')");
					gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAnc));

					gridTr.addChild(new UiGridTd().addClassName("Lt").setText(""));
				} else {
					gridTr.addChild(new UiGridTd().addClassName("Lt").setText(""));

					var uiAnc = new UiAnchor();
					uiAnc.setText(ds.getValue(i, "DESCRIPTION")).setScript("getDetail('"+path+"')");
					gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAnc));
				}

				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "IS_APPLY_GST")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "GST_PERCENTAGE"), "#,###.##")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "ACCOUNT_CODE")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "SORT_ORDER")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "INSERT_DATE")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "UPDATE_DATE")));

				var iconAction = new UiIcon();
				iconAction.setId("icnAction").setName("icnAction").addClassName("fa-tasks fa-lg").addAttribute("path:"+path).setScript("doAction(this)");
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(iconAction));

				html += gridTr.toHtmlString();

				// context menu
				if (iLevel == 1) {
					if (subMenu.length != 0) {
						menu[menu.length - 1].subMenu = subMenu;
						subMenu = [];
					}

					menu.push({
						name:ds.getValue(i, "DESCRIPTION"),
						userData:expenseType,
						fun:function() {
						}
					});
				} else {
					subMenu.push({
						name:ds.getValue(i, "DESCRIPTION"),
						userData:parentExpenseType+"_"+expenseType,
						fun:function() {
							var index = $(this).index();
							alert($(this).attr("userData"));
						}
					});
				}

				if (i == ds.getRowCnt()-1) {
					menu[menu.length - 1].subMenu = subMenu;
					subMenu = [];
				}
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
			isPageable:false,
			isFilter:false,
			filterColumn:[],
			totalResultRows:result.totalResultRows,
			script:"doSearch"
		});

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenu.commonAction);
		});

		commonJs.hideProcMessageOnElement("divScrollablePanel");

		setPreviewContextMenu();
	};

	getDetail = function(path) {
		openPopup({mode:"Detail", path:path});
	};

	openPopup = function(param) {
		var url = "", header = "";
		var height = 510;

		if (param.mode == "Detail") {
			url = "/sba/0406/getDetail.do";
			header = com.header.popHeaderDetail;
		} else if (param.mode == "New" || param.mode == "Reply") {
			url = "/sba/0406/getInsert.do";
			header = com.header.popHeaderEdit;
		} else if (param.mode == "Edit") {
			url = "/sba/0406/getUpdate.do";
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
						url:"/sba/0406/exeDelete.do",
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

		ctxMenu.commonAction[0].fun = function() {getDetail(articleId);};
		ctxMenu.commonAction[1].fun = function() {openPopup({mode:"Edit", articleId:articleId});};
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
						url:"/sba/0406/exeExport.do",
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
		setPreviewContextMenu();
		doSearch();
	});
});