/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sba0202List.js
 *************************************************************************************************/
jsconfig.put("useJqTooltip", false);
jsconfig.put("scrollablePanelHeightAdjust", 8);
var popup = null;
var searchResultDataCount = 0;

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

	$("#entityType").change(function() {
		doSearch();
	});

	$("#businessType").change(function() {
		doSearch();
	});

	$("#orgCategory").change(function() {
		doSearch();
	});

	$("#wageType").change(function() {
		doSearch();
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

		setTimeout(function() {
			commonJs.ajaxSubmit({
				url:"/sba/0202/getList.do",
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
				uiChk.setId("chkForDel").setName("chkForDel").setValue(ds.getValue(i, "ORG_ID"));
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiChk));

				var uiAnc = new UiAnchor();
				uiAnc.setText(commonJs.abbreviate(ds.getValue(i, "LEGAL_NAME"), 60)).setScript("getDetail('"+ds.getValue(i, "ORG_ID")+"')");
				gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAnc));

				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(commonJs.abbreviate(ds.getValue(i, "TRADING_NAME"), 50)));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(commonJs.getFormatString(ds.getValue(i, "ABN"), "?? ??? ??? ???")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "USER_CNT"), "#,###")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "ENTITY_TYPE_NAME")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "BUSINESS_TYPE_NAME")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "ORG_CATEGORY_NAME")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "WAGE_TYPE_NAME")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "REVENUE_RANGE_FROM"), "#,###")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "REVENUE_RANGE_TO"), "#,###")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "REGISTERED_DATE")));

				var iconAction = new UiIcon();
				iconAction.setId("icnAction").setName("icnAction").addClassName("fa-tasks fa-lg").addAttribute("orgId:"+ds.getValue(i, "ORG_ID")).setScript("doAction(this)");
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(iconAction));

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

	getDetail = function(orgId) {
		openPopup({mode:"Detail", orgId:orgId});
	};

	openPopup = function(param) {
		var url = "", header = "";
		var height = 304;

		if (param.mode == "Detail") {
			url = "/sba/0202/getDetail.do";
			header = com.header.popHeaderDetail;
		} else if (param.mode == "New") {
			url = "/sba/0202/getInsert.do";
			header = com.header.popHeaderEdit;
			height = 416;
		} else if (param.mode == "Edit") {
			url = "/sba/0202/getUpdate.do";
			header = com.header.popHeaderEdit;
			height = 416;
		}

		var popParam = {
			popupId:"orgInfo"+param.mode,
			url:url,
			paramData:{
				mode:param.mode,
				orgId:commonJs.nvl(param.orgId, "")
			},
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

		commonJs.confirm({
			contents:com.message.Q002,
			buttons:[{
				caption:com.caption.yes,
				callback:function() {
					commonJs.ajaxSubmit({
						url:"/sba/0202/exeDelete.do",
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
		var orgId = $(img).attr("orgId");

		$("input:checkbox[name=chkForDel]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == orgId) {
				$(this).prop("checked", true);
			} else {
				$(this).prop("checked", false);
			}
		});

		ctxMenu.commonAction[0].fun = function() {getDetail(orgId);};
		ctxMenu.commonAction[1].fun = function() {openPopup({mode:"Edit", orgId:orgId});};
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
					var fileType = menuObject.fileType, tm = 3000;
					popup = commonJs.openPopup({
						popupId:"exportFile",
						url:"/sba/0202/exeExport.do",
						paramData:{
							fileType:menuObject.fileType,
							dataRange:menuObject.dataRange
						},
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