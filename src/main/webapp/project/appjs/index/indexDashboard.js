/**
 * indexDashboard.js
 */
jsconfig.put("noWest", true);
var attchedFileContextMenu = [];
var dateTimeFormat = jsconfig.get("dateTimeFormatJs");
var dateFormat = jsconfig.get("dateFormatJs");

$(function() {
	/*!
	 * event
	 */
	$("h4 span .icnEn").click(function(event) {
		var id = $(this).attr("id");

		if (id == "icnRefreshAnnouncement") {getAnnouncement();}
		if (id == "icnRefreshBankStatement") {getBankStatementAllocationStatus();}

		event.preventDefault();
		event.stopPropagation();
	});

	$("h3 span .icnEn").click(function(event) {
		var id = $(this).attr("id");

		if (id == "icnRefreshQuotation") {getQuotationData();}
		if (id == "icnRefreshInvoice") {getInvoiceData();}

		event.preventDefault();
		event.stopPropagation();
	});

	$("h3 span .badge").click(function(event) {
		event.preventDefault();
		event.stopPropagation();
	});
	/*!
	 * process
	 */
	goMenu = (headerMenuId, headerMenuName, headerMenuUrl, leftMenuId, leftMenuName, leftMenuUrl) => {
		$("#hdnHeaderMenuId").val(headerMenuId);
		$("#hdnHeaderMenuName").val(headerMenuName);
		$("#hdnHeaderMenuUrl").val(headerMenuUrl);
		$("#hdnLeftMenuId").val(leftMenuId);
		$("#hdnLeftMenuName").val(leftMenuName);
		$("#hdnLeftMenuUrl").val(leftMenuUrl);

		commonJs.doSubmit({form:$("form:eq(0)"), action:leftMenuUrl});
	};

	goAnnouncement = () => {
		goMenu("BBS", "Announcement", "#", "BBS0202", "Announcement", "/bbs/0202/getDefault.do");
	};

	goBankStatementAllocation = () => {
		goMenu("BSA", "Bank Statement Allocation", "#", "BSA0202", "Bank Statement Allocation", "/bsa/0202/getDefault.do");
	};

	goQuotation = () => {
		goMenu("ADS", "Quotation Management", "#", "ADS0202", "Quotation Management", "/ads/0202/getDefault.do");
	};

	goInvoice = () => {
		goMenu("ADS", "Invoice Management", "#", "ADS0204", "Invoice Management", "/ads/0204/getDefault.do");
	};

	getAnnouncement = () => {
		commonJs.showProcMessageOnElement("sectionAnnouncement");
		commonJs.doSearch({
			url:"/index/getAnnouncementList.do",
			onSuccess:(result) => {
				var ds = result.dataSet;
				var html = "";

				$("#tbodyGridAnnouncement").html("");

				if (ds.getRowCnt() > 0) {
					for (var i=0; i<ds.getRowCnt(); i++) {
						var gridTr = new UiGridTr();

						gridTr.setClassName("noBorderAll noStripe");

						gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(new UiAnchor().setText(ds.getValue(i, "ARTICLE_SUBJECT")).setScript("goAnnouncement()")));

						var gridTd = new UiGridTd();
						gridTd.addClassName("Lt");
						if (ds.getValue(i, "FILE_CNT") > 0) {
							var iconAttachFile = new UiIcon();
							iconAttachFile.setId("icnAttachedFile").setName("icnAttachedFile").addClassName("glyphicon-paperclip").setStyle("font-size:12px").addAttribute("articleId:"+ds.getValue(i, "ARTICLE_ID")).setScript("getAttachedFile(this)");
							gridTd.addChild(iconAttachFile);
						}
						gridTr.addChild(gridTd);

						gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "WRITER_NAME")));
						gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.nvl(ds.getValue(i, "UPDATE_DATE"), ds.getValue(i, "INSERT_DATE"))));

						html += gridTr.toHtmlString();
					}
				} else {
					var gridTr = new UiGridTr();

					gridTr.setClassName("noBorderAll noStripe");

					gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:4").setText("No Announcement found."));
					html += gridTr.toHtmlString();
				}

				$("#tbodyGridAnnouncement").append($(html));

				$("[name=icnAttachedFile]").each(function(index) {
					$(this).contextMenu(attchedFileContextMenu);
				});

				commonJs.hideProcMessageOnElement("sectionAnnouncement");
			}
		});
	};

	getAttachedFile = function(img) {
		commonJs.ajaxSubmit({
			url:"/index/getAttachedFile.do",
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

	getBankStatementAllocationStatus = () => {
		commonJs.showProcMessageOnElement("sectionBankStatement");
		commonJs.doSearch({
			url:"/index/getBankStatementAllocationStatus.do",
			onSuccess:(result) => {
				var ds = result.dataSet;
				var html = "";

				$("#tbodyGridBankStatement").html("");

				if (ds.getRowCnt() > 0) {
					for (var i=0; i<ds.getRowCnt(); i++) {
						var gridTr = new UiGridTr();
						var totTranCnt = commonJs.getNumberMask(ds.getValue(i, "TOTAL_TRAN_CNT"), "#,##0");
						var allocatedTranCnt = commonJs.getNumberMask(ds.getValue(i, "ALLOCATED_TRAN_CNT"), "#,##0");
						var uploadedTranCnt = commonJs.getNumberMask(ds.getValue(i, "UPLOADED_TRAN_CNT"), "#,##0");

						gridTr.setClassName("noBorderAll noStripe");

						gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(new UiAnchor().setText(ds.getValue(i, "DESCRIPTION")).setScript("goBankStatementAllocation()")));
						gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "BS_CNT"), "#,##0")));
						gridTr.addChild(new UiGridTd().addClassName("Rt").setText(uploadedTranCnt+" / "+totTranCnt));
						gridTr.addChild(new UiGridTd().addClassName("Rt").setText(allocatedTranCnt+" / "+totTranCnt));

						html += gridTr.toHtmlString();
					}
				} else {
					var gridTr = new UiGridTr();

					gridTr.setClassName("noBorderAll noStripe");

					gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:4").setText("No Bank Account data found."));
					html += gridTr.toHtmlString();
				}

				$("#tbodyGridBankStatement").append($(html));

				commonJs.hideProcMessageOnElement("sectionBankStatement");
			}
		});
	};

	getQuotationData = () => {
		commonJs.showProcMessageOnElement("sectionQuotation");

		commonJs.doSearch({
			url:"/index/getQuotationBadge.do",
			onSuccess:(result) => {
				var ds = result.dataSet;
				var html = "";

				$("#spnBadgeQuotationCnt").html(commonJs.getNumberMask(ds.getValue(0, "QUOTATION_CNT"), "#,##0"));
				$("#spnBadgeQuotationAmt").html(commonJs.getNumberMask(ds.getValue(0, "QUOTATION_TOT_AMT"), "#,##0.00"));
			}
		});

		commonJs.doSearch({
			url:"/index/getQuotationData.do",
			onSuccess:(result) => {
				var ds = result.dataSet;
				var html = "";

				$("#tbodyGridQuotation").html("");

				if (ds.getRowCnt() > 0) {
					for (var i=0; i<ds.getRowCnt(); i++) {
						var gridTr = new UiGridTr();

						gridTr.setClassName("noBorderAll noStripe");

						gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(new UiAnchor().setText(ds.getValue(i, "CLIENT_NAME")).setScript("goQuotation()")));
						gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "TOTAL_AMT"), "#,##0.00")));
						gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getDateTimeMask(ds.getValue(i, "ISSUE_DATE"), dateFormat)));

						html += gridTr.toHtmlString();
					}
				} else {
					var gridTr = new UiGridTr();

					gridTr.setClassName("noBorderAll noStripe");

					gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:3").setText("No Quotation data found."));
					html += gridTr.toHtmlString();
				}

				$("#tbodyGridQuotation").append($(html));

				commonJs.hideProcMessageOnElement("sectionQuotation");
			}
		});
	};

	getInvoiceData = () => {
		commonJs.showProcMessageOnElement("sectionInvoice");

		commonJs.doSearch({
			url:"/index/getInvoiceBadge.do",
			onSuccess:(result) => {
				var ds = result.dataSet;
				var html = "";

				$("#spnBadgeInvoiceCnt").html(commonJs.getNumberMask(ds.getValue(0, "INVOICE_CNT"), "#,##0"));
				$("#spnBadgeInvoiceAmt").html(commonJs.getNumberMask(ds.getValue(0, "INVOICE_TOT_AMT"), "#,##0.00"));
			}
		});

		commonJs.doSearch({
			url:"/index/getInvoiceData.do",
			onSuccess:(result) => {
				var ds = result.dataSet;
				var html = "";

				$("#tbodyGridInvoice").html("");

				if (ds.getRowCnt() > 0) {
					for (var i=0; i<ds.getRowCnt(); i++) {
						var gridTr = new UiGridTr();

						gridTr.setClassName("noBorderAll noStripe");

						gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(new UiAnchor().setText(ds.getValue(i, "CLIENT_NAME")).setScript("goInvoice()")));
						gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "STATUS_MEANING")));
						gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "TOTAL_AMT"), "#,##0.00")));
						gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getDateTimeMask(ds.getValue(i, "ISSUE_DATE"), dateFormat)));

						html += gridTr.toHtmlString();
					}
				} else {
					var gridTr = new UiGridTr();

					gridTr.setClassName("noBorderAll noStripe");

					gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:4").setText("No Invoice data found."));
					html += gridTr.toHtmlString();
				}

				$("#tbodyGridInvoice").append($(html));

				commonJs.hideProcMessageOnElement("sectionInvoice");
			}
		});
	};
	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		commonJs.setAccordion({
			containerClass:"accordionQuotationInvoice",
			multipleExpand:true,
			expandAll:true
		});

		getAnnouncement();
		getBankStatementAllocationStatus();
		getQuotationData();
		getInvoiceData();
	});
});