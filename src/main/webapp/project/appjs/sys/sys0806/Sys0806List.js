/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sys0806List.js
 *************************************************************************************************/
jsconfig.put("scrollablePanelHeightAdjust", 6);
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
			displayAround : "trigger",
			position : "bottom",
			horAdjust : 0,
			verAdjust : 0
		});
	};

	/*!
	 * process
	 */
	setCheckbox = function(obj) {
		try {
			var $obj = obj, val = $obj.val(), delimiter = "/", isClickedObjChecked = $obj.prop("checked");
			var clickedVals = val.split(delimiter), clickedNewVal = clickedVals[1]+delimiter+clickedVals[2];

			if (clickedVals.length == 3) {
				$("[name=chkForDel]").each(function(index) {
					var thisVal = $(this).val();
					var thisVals = thisVal.split(delimiter), thisNewVal = thisVals[1]+delimiter+thisVals[2];

					if (clickedNewVal == thisNewVal) {
						$(this).prop("checked", isClickedObjChecked);
					}
				});
			}
		} catch(e) {
		}
	};

	doSearch = function() {
		commonJs.showProcMessageOnElement("divScrollablePanel");

		if (commonJs.doValidate($("#fmDefault"))) {
			setTimeout(function() {
				commonJs.ajaxSubmit({
					url:"/sys/0806/getList.do",
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
			}, 500);
		}
	};

	renderDataGridTable = function(result) {
		var ds = result.dataSet;
		var html = "", delimiter = "_";
		var subMenu = [];

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
				var usedCount = ds.getValue(i, "USED_COUNT");
				var gridTr = new UiGridTr();
				var checkString = "", className = "chkEn", disabledStr = "";

				if (isLeaf == "0" || usedCount > 0) {
					className = "chkDis";
					disabledStr = "disabled";
				}

				var uiChk = new UiCheckbox();
				uiChk.setId("chkForDel").setName("chkForDel").setClassName(className+" inTblGrid").setOptions(disabledStr).setValue(path);
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiChk));

				if (isLeaf == 0) {
					var uiAnc = new UiAnchor();
					uiAnc.setText(ds.getValue(i, "DESCRIPTION")).setScript("getUpdate('"+path+"', '"+disabledStr+"')");
					gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAnc));

					gridTr.addChild(new UiGridTd().addClassName("Lt").setText(""));
				} else {
					gridTr.addChild(new UiGridTd().addClassName("Lt").setText(""));

					var uiAnc = new UiAnchor();
					uiAnc.setText(ds.getValue(i, "DESCRIPTION")).setScript("getUpdate('"+path+"', '"+disabledStr+"')");
					gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAnc));
				}

				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "IS_APPLY_GST")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "GST_PERCENTAGE"), "#,##0.00")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "ACCOUNT_CODE")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "SORT_ORDER")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "INSERT_DATE")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "UPDATE_DATE")));

				var iconAction = new UiIcon();
				iconAction.setId("icnAction").setName("icnAction").addClassName("fa-tasks fa-lg").addAttribute("path:"+path).addAttribute("disabledStr:"+disabledStr).setScript("doAction(this)");
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

		$("[name=chkForDel]").bind("click", function() {
			setCheckbox($(this));
		});

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenu.commonSimpleAction);
		});

		commonJs.hideProcMessageOnElement("divScrollablePanel");

		setPreviewContextMenu();
	};

	getUpdate = function(path, disabledStr) {
		openPopup({mode:"Edit", path:path, disabledStr:disabledStr});
	};

	openPopup = function(param) {
		var url = "", header = com.header.popHeaderEdit;
		var width = 900, height = 300;

		if (param.mode == "New") {
			url = "/sys/0806/getInsert.do";
			height = 500;
		} else if (param.mode == "Edit") {
			url = "/sys/0806/getUpdate.do";
		} else if (param.mode == "SetSort") {
			url = "/sys/0806/getUpdateSortOrder.do";
			header = sba.sys0806.header.popHeaderSort;
			width = 850, height = 700;
		}

		var popParam = {
			popupId:"expenseType"+param.mode,
			url:url,
			data:{
				mode:param.mode,
				path:param.path,
				disabledStr:param.disabledStr,
				orgCategory:$("#orgCategory").val()
			},
			header:header,
			blind:true,
			width:width,
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
						url:"/sys/0806/exeDelete.do",
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
		var path = $(img).attr("path"), disabledStr = $(img).attr("disabledStr");
		var $checkbox;

		$("input:checkbox[name=chkForDel]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == path) {
				$(this).prop("checked", true);
				$checkbox = $(this);
			} else {
				$(this).prop("checked", false);
			}
		});

		if (disabledStr == "disabled") {
			ctxMenu.commonSimpleAction[1].disable = true;
		} else {
			ctxMenu.commonSimpleAction[1].disable = false;
		}

		setCheckbox($checkbox);

		ctxMenu.commonSimpleAction[0].fun = function() {getUpdate(path, disabledStr);};
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

		commonJs.confirm({
			contents:com.message.Q003,
			buttons:[{
				caption:com.caption.yes,
				callback:function() {
					var param = commonJs.serialiseObject($("#divSearchCriteriaArea"));
					param.fileType = menuObject.fileType;
					param.dataRange = menuObject.dataRange;

					popup = commonJs.openPopup({
						popupId:"exportFile",
						url:"/sys/0806/exeExport.do",
						paramData:param,
						header:"exportFile",
						blind:false,
						width:200,
						height:100
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

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		commonJs.setExportButtonContextMenu($("#btnExport"));
		doSearch();
	});
});