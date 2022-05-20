/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sys0204List.js
 *************************************************************************************************/
var popup = null;
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
	 * process
	 */
	doSearch = function() {
		commonJs.showProcMessageOnElement("divScrollablePanel");

		commonJs.doSearch({
			url:"/sys/0204/getList.do",
			onSuccess:renderDataGridTable
		});
	};

	renderDataGridTable = function(result) {
		var ds = result.dataSet;
		var html = "";

		$("#tblGridBody").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr();

				var iconAction = new UiIcon();
				iconAction.setId("icnAction").setName("icnAction").addClassName("fa-ellipsis-h fa-lg").addAttribute("articleId:"+ds.getValue(i, "ARTICLE_ID"))
					.setScript("doAction(this)").addAttribute("title:"+com.header.action);
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(iconAction));

				var uiChk = new UiCheckbox();
				uiChk.setId("chkForDel").setName("chkForDel").setValue(ds.getValue(i, "ARTICLE_ID"));
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiChk));

				var uiAnc = new UiAnchor();
				uiAnc.setText(ds.getValue(i, "ARTICLE_SUBJECT")).setScript("getDetail('"+ds.getValue(i, "ARTICLE_ID")+"')");
				gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAnc));

				var gridTd = new UiGridTd();
				gridTd.addClassName("Ct");
				if (ds.getValue(i, "FILE_CNT") > 0) {
					var iconAttachFile = new UiIcon();
					iconAttachFile.setId("icnAttachedFile").setName("icnAttachedFile").addClassName("glyphicon-paperclip").addAttribute("articleId:"+ds.getValue(i, "ARTICLE_ID")).setScript("getAttachedFile(this)");
					gridTd.addChild(iconAttachFile);
				}
				gridTr.addChild(gridTd);

				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "WRITER_NAME")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(commonJs.nvl(ds.getValue(i, "UPDATE_DATE"), ds.getValue(i, "INSERT_DATE"))));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "HIT_CNT"), "#,###")));

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
			isPageable:true,
			totalResultRows:result.totalResultRows,
			script:"doSearch"
		});

		$("[name=icnAttachedFile]").each(function(index) {
			$(this).contextMenu(attchedFileContextMenu);
		});

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenu.commonAction);
		});

		commonJs.bindToggleTrBackgoundWithCheckbox($("[name=chkForDel]"));
		commonJs.hideProcMessageOnElement("divScrollablePanel");
	};

	getDetail = function(articleId) {
		openPopup({mode:"Detail", articleId:articleId});
	};

	openPopup = function(param) {
		var url = "", header = "";
		var width = 1080, height = 700;

		if (param.mode == "Detail") {
			url = "/sys/0204/getDetail.do";
			header = com.header.popHeaderDetail;
		} else if (param.mode == "New") {
			url = "/sys/0204/getInsert.do";
			header = com.header.popHeaderEdit;
		} else if (param.mode == "Edit") {
			url = "/sys/0204/getUpdate.do";
			header = com.header.popHeaderEdit;
			height = 824;
		}

		var popParam = {
			popupId:"notice"+param.mode,
			url:url,
			data:{
				mode:param.mode,
				articleId:commonJs.nvl(param.articleId, "")
			},
			header:header,
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

		commonJs.doDelete({
			url:"/sys/0204/exeDelete.do",
			onSuccess:doSearch
		});
	};

	doAction = function(img) {
		var articleId = $(img).attr("articleId");

		$("input:checkbox[name=chkForDel]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == articleId) {
				if (!$(this).is(":checked")) {
					$(this).click();
				}
			} else {
				if ($(this).is(":checked")) {
					$(this).click();
				}
			}
		});

		ctxMenu.commonAction[0].fun = function() {openPopup({mode:"Detail", articleId:articleId});};
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

	getAttachedFile = function(img) {
		commonJs.doSimpleProcess({
			url:"/sys/0204/getAttachedFile.do",
			data:{articleId:$(img).attr("articleId")},
			onSuccess:(result) => {
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

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		commonJs.setFieldDateMask("fromDate");
		commonJs.setFieldDateMask("toDate");
//		commonJs.setExportButtonContextMenu($("#btnExport"));
		doSearch();
	});
});