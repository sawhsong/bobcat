/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sba0204List.js
 *************************************************************************************************/
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

	$("#icnFromDate").click(function(event) {
		commonJs.openCalendar(event, "fromDate");
	});

	$("#icnToDate").click(function(event) {
		commonJs.openCalendar(event, "toDate");
	});

	$("#icnCheck").click(function(event) {
		commonJs.toggleCheckboxes("chkForDel");
	});

	$(document).keypress(function(event) {
		if (event.which == 13) {
			var element = event.target;

			if ($(element).is("[name=searchWord]") || $(element).is("[name=fromDate]") || $(element).is("[name=toDate]")) {
				doSearch();
			}
		}
	});

	/*!
	 * context menus
	 */
	setExportButtonContextMenu = function() {
		ctxMenu.commonExport[0].fun = function() {exeExport(ctxMenu.commonExport[0]);};
		ctxMenu.commonExport[1].fun = function() {exeExport(ctxMenu.commonExport[1]);};
		ctxMenu.commonExport[2].fun = function() {exeExport(ctxMenu.commonExport[2]);};
		ctxMenu.commonExport[3].fun = function() {exeExport(ctxMenu.commonExport[3]);};
		ctxMenu.commonExport[4].fun = function() {exeExport(ctxMenu.commonExport[4]);};
		ctxMenu.commonExport[5].fun = function() {exeExport(ctxMenu.commonExport[5]);};

		$("#btnExport").contextMenu(ctxMenu.commonExport, {
			classPrefix:com.constants.ctxClassPrefixButton,
			effectDuration:300,
			effect:"slide",
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

		if (commonJs.doValidate($("#fmDefault"))) {
			setTimeout(function() {
				commonJs.ajaxSubmit({
					url:"/sba/0204/getList.do",
					dataType:"json",
					formId:"fmDefault",
					success:function(data, textStatus) {
						var result = commonJs.parseAjaxResult(data, textStatus, "json");

						if (result.isSuccess == true || result.isSuccess == "true") {
							renderDataGridTable(result);
						}
					}
				});
			}, 100);
		}
	};

	renderDataGridTable = function(result) {
		var dataSet = result.dataSet;
		var html = "";

		searchResultDataCount = dataSet.getRowCnt();
		$("#tblGridBody").html("");

		if (dataSet.getRowCnt() > 0) {
			for (var i=0; i<dataSet.getRowCnt(); i++) {
				var space = "", iLength = 200;
				var iLevel = parseInt(dataSet.getValue(i, "LEVEL")) - 1;
				var gridTr = new UiGridTr();

				gridTr.setClassName("noBorderHor noStripe");

				var uiChk = new UiCheckbox();
				uiChk.setId("chkForDel").setName("chkForDel").setValue(dataSet.getValue(i, "ARTICLE_ID"));
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiChk));

				if (iLevel > 0) {
					for (var j=0; j<iLevel; j++) {
						space += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
						iLength -= - 2;
					}
					space += "<i class=\"fa fa-comments\"></i>";
				} else {
					space += "<i class=\"fa fa-comment\"></i>";
				}

				var uiAnc = new UiAnchor();
				uiAnc.setText(commonJs.abbreviate(dataSet.getValue(i, "ARTICLE_SUBJECT"), iLength)).setScript("getDetail('"+dataSet.getValue(i, "ARTICLE_ID")+"')");
				gridTr.addChild(new UiGridTd().addClassName("Lt").addTextBeforeChild(space+"&nbsp;&nbsp;").addChild(uiAnc).addAttribute("title:"+commonJs.htmlToString(dataSet.getValue(i, "ARTICLE_SUBJECT"))));

				var gridTd = new UiGridTd();
				gridTd.addClassName("Ct");
				if (dataSet.getValue(i, "FILE_CNT") > 0) {
					var iconAttachFile = new UiIcon();
					iconAttachFile.setId("icnAttachedFile").setName("icnAttachedFile").addClassName("glyphicon-paperclip").addAttribute("articleId:"+dataSet.getValue(i, "ARTICLE_ID"));
					gridTd.addChild(iconAttachFile);
				}
				gridTr.addChild(gridTd);

				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(dataSet.getValue(i, "WRITER_NAME")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(dataSet.getValue(i, "CREATED_DATE")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(dataSet.getValue(i, "VISIT_CNT"), "#,###")));

				var iconAction = new UiIcon();
				iconAction.setId("icnAction").setName("icnAction").addClassName("fa-tasks fa-lg").addAttribute("articleId:"+dataSet.getValue(i, "ARTICLE_ID"))
					.setScript("doAction(this)").addAttribute("title:"+com.header.action);
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(iconAction));

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:7").setText(com.message.I001));
			html += gridTr.toHtmlString();
		}

		$("#tblGridBody").append($(html));

		$("#tblGrid").fixedHeaderTable({
			attachTo:$("#divDataArea"),
			pagingArea:$("#divPagingArea"),
			isPageable:true,
			isFilter:false,
			filterColumn:[1, 3],
			totalResultRows:result.totalResultRows,
			script:"doSearch"
		});

		$("[name=icnAttachedFile]").each(function(index) {
			$(this).contextMenu(attchedFileContextMenu);
		});

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenu.boardAction);
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
			url = "/sba/0204/getDetail.do";
			header = com.header.popHeaderDetail;
		} else if (param.mode == "New" || param.mode == "Reply") {
			url = "/sba/0204/getInsert.do";
			header = com.header.popHeaderEdit;
		} else if (param.mode == "Edit") {
			url = "/sba/0204/getUpdate.do";
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
						url:"/sba/0204/exeDelete.do",
						dataType:"json",
						formId:"fmDefault",
						success:function(data, textStatus) {
							var result = commonJs.parseAjaxResult(data, textStatus, "json");

							if (result.isSuccess == true || result.isSuccess == "true") {
								commonJs.openDialog({
									type:com.message.I000,
									contents:result.message,
									blind:true,
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
						url:"/sba/0204/exeExport.do",
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
		commonJs.setFieldDateMask("fromDate");
		commonJs.setFieldDateMask("toDate");
		setExportButtonContextMenu();
		$("#searchWord").focus();
		doSearch();
	});
});