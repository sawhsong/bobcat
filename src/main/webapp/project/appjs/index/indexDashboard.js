/**
 * indexDashboard.js
 */
jsconfig.put("noWest", true);
var attchedFileContextMenu = [];
var dateTimeFormat = jsconfig.get("dateTimeFormatJs");
var dateFormat = jsconfig.get("dateFormatJs");
var monthDataSet;
var monthLabel = [], monthNumber = [];

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
		if (id == "icnRefreshIncomeChart") {renderIncomeChart();}
		if (id == "icnRefreshExpenseChart") {renderExpenseChart();}
		if (id == "icnRefreshOtherChart") {renderOtherChart();}

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
		goMenu("BAU", "Announcement", "#", "BAU0204", "Announcement", "/bau/0204/getDefault.do");
	};

	goBankStatementAllocation = () => {
		goMenu("BSM", "Bank Statement Allocation", "#", "BSM0204", "Bank Statement Allocation", "/bsm/0204/getDefault.do");
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

	getMonthsForChart = () => {
		commonJs.doSearch({
			url:"/index/getMonthForChart.do",
			onSuccess:(result) => {
				monthDataSet = result.dataSet;
				for (var i=0; i<monthDataSet.getRowCnt(); i++) {
					monthLabel.push(monthDataSet.getValue(i, "MON_CHAR"));
					monthNumber.push(monthDataSet.getValue(i, "MON_NUM"));
				}
			}
		});
	};

	renderIncomeChart = () => {
		commonJs.showProcMessageOnElement("sectionIncomeChart");

		$("#incomeChart").html("");

		commonJs.doSearch({
			url:"/index/getIncomeChartData.do",
			onSuccess:(result) => {
				var ds = result.dataSet;
console.log("ds.toString()");
console.log(ds.toString());
				if (ds.getRowCnt() > 0) {
					var dataTot = {}, dataGst = {}, dataNet = {};
					var totAmt = [], gstAmt = [], netAmt = [];
					var datasets = [];
					var chartData = {};
					$("#incomeChart").html("<canvas id=\"cvIncomeChart\" style=\"width:100%;height:210px;\"></canvas>");

					for (var i=0; i<monthNumber.length; i++) {
						var mon = monthNumber[i].split("-")[0];

						totAmt.push(ds.getValue(0, "TOTAL_AMT_"+mon));
						gstAmt.push(ds.getValue(0, "GST_AMT_"+mon));
						netAmt.push(ds.getValue(0, "NET_AMT_"+mon));
					}

					dataGst.type = "bar";
					dataGst.label = "GST";
					dataGst.backgroundColor = chartColor.background0;
					dataGst.borderColor = chartColor.border0;
					dataGst.borderWidth = 1,
					dataGst.data = gstAmt;

					dataNet.type = "bar";
					dataNet.label = "Net";
					dataNet.backgroundColor = chartColor.background3;
					dataNet.borderColor = chartColor.border3;
					dataNet.borderWidth = 1,
					dataNet.data = netAmt;

					dataTot.type = "bar";
					dataTot.label = "Total";
					dataTot.backgroundColor = chartColor.background5;
					dataTot.borderColor = chartColor.border5;
					dataTot.borderWidth = 1,
					dataTot.data = totAmt;

					datasets.push(dataGst);
					datasets.push(dataNet);
					datasets.push(dataTot);

					chartData.labels = monthLabel;
					chartData.datasets = datasets;

					setTimeout(() => {
						var ctx = $("#cvIncomeChart")[0].getContext("2d");
						incomeChart = new Chart(ctx, {
							type:"bar",
							data:chartData,
							options:{
								responsive:true,
								defaultFontFamily:"Verdana",
								title:{
									display:false,
									text: "Income Status"
								},
								tooltips:{
									mode:"index",
									intersect:true,
									callbacks:{
										label:(tooltipItem, data) => {
											var label = data.datasets[tooltipItem.datasetIndex].label || "";

											if (label) {label += ": ";}
											label += commonJs.getNumberMask(tooltipItem.yLabel, "#,##0.00");

											return label;
										}
									}
								},
								scales:{
									yAxes:[{
										ticks:{
											callback:(value, index, values) => {
												return commonJs.getNumberMask(value, "#,##0");
											}
										}
									}]
								}
							}
						});

						commonJs.hideProcMessageOnElement("sectionIncomeChart");
					}, 500);
				} else {
					$("#incomeChart").html("No Data found.");
					commonJs.hideProcMessageOnElement("sectionIncomeChart");
				}
			}
		});
	};

	renderExpenseChart = () => {
		commonJs.showProcMessageOnElement("sectionExpenseChart");

		$("#expenseChart").html("");

		commonJs.doSearch({
			url:"/index/getExpenseChartData.do",
			onSuccess:(result) => {
				var ds = result.dataSet;

				if (ds.getRowCnt() > 0) {
					var dataTot = {}, dataGst = {}, dataNet = {};
					var totAmt = [], gstAmt = [], netAmt = [];
					var datasets = [];
					var chartData = {};

					$("#expenseChart").html("<canvas id=\"cvExpenseChart\" style=\"width:100%;height:210px;\"></canvas>");

					for (var i=0; i<monthNumber.length; i++) {
						var mon = monthNumber[i].split("-")[0];

						totAmt.push(ds.getValue(0, "TOTAL_AMT_"+mon));
						gstAmt.push(ds.getValue(0, "GST_AMT_"+mon));
						netAmt.push(ds.getValue(0, "NET_AMT_"+mon));
					}

					dataGst.type = "bar";
					dataGst.label = "GST";
					dataGst.backgroundColor = chartColor.background0;
					dataGst.borderColor = chartColor.border0;
					dataGst.borderWidth = 1,
					dataGst.data = gstAmt;

					dataNet.type = "bar";
					dataNet.label = "Net";
					dataNet.backgroundColor = chartColor.background3;
					dataNet.borderColor = chartColor.border3;
					dataNet.borderWidth = 1,
					dataNet.data = netAmt;

					dataTot.type = "bar";
					dataTot.label = "Total";
					dataTot.backgroundColor = chartColor.background5;
					dataTot.borderColor = chartColor.border5;
					dataTot.borderWidth = 1,
					dataTot.data = totAmt;

					datasets.push(dataGst);
					datasets.push(dataNet);
					datasets.push(dataTot);

					chartData.labels = monthLabel;
					chartData.datasets = datasets;

					setTimeout(() => {
						var ctx = $("#cvExpenseChart")[0].getContext("2d");
						incomeChart = new Chart(ctx, {
							type:"bar",
							data:chartData,
							options:{
								responsive:true,
								defaultFontFamily:"Verdana",
								title:{
									display:false,
									text: "Expense Status"
								},
								tooltips:{
									mode:"index",
									intersect:true,
									callbacks:{
										label:(tooltipItem, data) => {
											var label = data.datasets[tooltipItem.datasetIndex].label || "";

											if (label) {label += ": ";}
											label += commonJs.getNumberMask(tooltipItem.yLabel, "#,##0.00");

											return label;
										}
									}
								},
								scales:{
									yAxes:[{
										ticks:{
											callback:(value, index, values) => {
												return commonJs.getNumberMask(value, "#,##0");
											}
										}
									}]
								}
							}
						});

						commonJs.hideProcMessageOnElement("sectionExpenseChart");
					}, 500);
				} else {
					$("#expenseChart").html("No Data found.");
					commonJs.hideProcMessageOnElement("sectionExpenseChart");
				}
			}
		});
	};

	renderOtherChart = () => {
		commonJs.showProcMessageOnElement("sectionOtherChart");

		$("#otherChart").html("");

		commonJs.doSearch({
			url:"/index/getOtherChartData.do",
			onSuccess:(result) => {
				var ds = result.dataSet;

				if (ds.getRowCnt() > 0) {
					var data0 = {}, data1 = {};
					var totAmt0 = [], totAmt1 = [];
					var amt = [], datasets = [];
					var chartData = {};

					$("#otherChart").html("<canvas id=\"cvOtherChart\" style=\"width:100%;height:210px;\"></canvas>");

					for (var i=0; i<ds.getRowCnt(); i++) {
						var monAmt = [];

						for (var j=0; j<monthNumber.length; j++) {
							var mon = monthNumber[j].split("-")[0];

							monAmt.push(ds.getValue(i, "TOTAL_AMT_"+mon));
						}
						amt.push(monAmt);
					}

					for (var i=0; i<ds.getRowCnt(); i++) {
						var data = [];
						var colorName = "chartColor.border"+((i+1)*3)+"";

						data["type"] = "line";
						data["label"] = ds.getValue(i, "MAIN_CATEGORY_NAME");
						data["borderColor"] = eval(colorName);
						data["borderWidth"] = 2;
						data["data"] = amt[i];

						datasets.push(data);
					}

					chartData.labels = monthLabel;
					chartData.datasets = datasets;

					setTimeout(() => {
						var ctx = $("#cvOtherChart")[0].getContext("2d");
						incomeChart = new Chart(ctx, {
							type:"line",
							data:chartData,
							options:{
								responsive:true,
								defaultFontFamily:"Verdana",
								title:{
									display:false,
									text: "Other Status"
								},
								tooltips:{
									mode:"index",
									intersect:true,
									callbacks:{
										label:(tooltipItem, data) => {
											var label = data.datasets[tooltipItem.datasetIndex].label || "";

											if (label) {label += ": ";}
											label += commonJs.getNumberMask(tooltipItem.yLabel, "#,##0.00");

											return label;
										}
									}
								},
								scales:{
									yAxes:[{
										ticks:{
											callback:(value, index, values) => {
												return commonJs.getNumberMask(value, "#,##0");
											}
										}
									}]
								}
							}
						});

						commonJs.hideProcMessageOnElement("sectionOtherChart");
					}, 500);
				} else {
					$("#otherChart").html("No Data found.");
					commonJs.hideProcMessageOnElement("sectionOtherChart");
				}
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

		commonJs.setAccordion({
			containerClass:"accordionStatus",
			multipleExpand:true,
			expandAll:true
		});

		getMonthsForChart();

		getAnnouncement();
		getBankStatementAllocationStatus();
		getQuotationData();
		getInvoiceData();

		renderIncomeChart();
		renderExpenseChart();
		renderOtherChart();
	});
});