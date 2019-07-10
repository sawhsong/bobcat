/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Bbs0202List.js
 *************************************************************************************************/
jsconfig.put("useJqTooltip", false);

$(function() {
	/*!
	 * event
	 */
	$("#financialYear").change(function() {
		doSearch();
	});

	$("#quarterName").change(function() {
		doSearch();
	});

	$("#btnSearch").click(function(event) {
		doSearch();
	});

	$("#btnClear").click(function(event) {
		commonJs.clearSearchCriteria();
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

		if (commonJs.doValidate($("#fmDefault"))) {
			setTimeout(function() {
				commonJs.ajaxSubmit({
					url:"/cst/0202/getList",
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

//			setTimeout(function() {
//				commonJs.ajaxSubmit({
//					url:"/cst/0202/getChart",
//					dataType:"json",
//					formId:"fmDefault",
//					success:function(data, textStatus) {
//						var result = commonJs.parseAjaxResult(data, textStatus, "json");
//
//						if (result.isSuccess == true || result.isSuccess == "true") {
//							var ds = result.dataSet;
//
//							$("#imgChart").attr("src", ds.getValue(0, "chartFilePath"));
//						} else {
//							commonJs.error(result.message);
//						}
//					}
//				});
//			}, 400);
		}
	};

	renderDataGridTable = function(result) {
		var ds = result.dataSet;
		var html = "", totHtml = "", totGross = 0, totGst = 0, totNet = 0;
		var totGridTr = new UiGridTr();

		$("#tblGridBody").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr();

				if (i == (ds.getRowCnt() - 1)) {
					gridTr.addChild(new UiGridTd().addAttribute("colspan:13").addClassName("Rt").setText("Total Profit / Loss"));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(ds.getValue(i, "TOT")));
					gridTr.setStyle("font-weight:bold;background:#f6f6f6;");
				} else {
					gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "TYPE_NAME")));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(ds.getValue(i, "JUL")));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(ds.getValue(i, "AUG")));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(ds.getValue(i, "SEP")));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(ds.getValue(i, "OCT")));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(ds.getValue(i, "NOV")));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(ds.getValue(i, "DEC")));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(ds.getValue(i, "JAN")));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(ds.getValue(i, "FEB")));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(ds.getValue(i, "MAR")));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(ds.getValue(i, "APR")));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(ds.getValue(i, "MAY")));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(ds.getValue(i, "JUN")));
					gridTr.addChild(new UiGridTd().addClassName("Rt").setText(ds.getValue(i, "TOT")));

					if (ds.getValue(i, "TYPE_NAME") == "Income Total" || ds.getValue(i, "TYPE_NAME") == "Expense Total") {
						gridTr.setStyle("font-weight:bold;background:#f6f6f6;");
					}
				}
				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:14").setText(com.message.I001));
			html += gridTr.toHtmlString();
		}

		$("#tblGridBody").append($(html));

//		$("#tblGrid").fixedHeaderTable({
//			attachTo:$("#divDataArea"),
//			pagingArea:$("#divPagingArea"),
//			isPageable:false,
//			isFilter:false,
//			filterColumn:[],
//			totalResultRows:result.totalResultRows,
//			script:"doSearch"
//		});

		renderChart(ds);
		commonJs.hideProcMessageOnElement("divScrollablePanel");
	};

	renderChart = function(ds) {
		var ctx = $("#cvChart")[0].getContext("2d");
		var maxAmt = 0;
		var dataLabels = new Array();
		for (var i=2; i<ds.getColumnCnt()-1; i++) {
			dataLabels.push(ds.getName(i));
		}

		var dataset = new Array();
		for (var i=0; i<ds.getRowCnt()-1; i++) {
			var dataitems = {}, data = new Array(), color = new Array();
			dataitems.label = ds.getValue(i, "TYPE_NAME");
			dataitems.fill = false;
			for (var j=2; j<ds.getColumnCnt()-1; j++) {
				var val = commonJs.replace(ds.getValue(i, j), ",", "");
				data.push(val);
				color.push(chartColor["border"+(i)]);
				if (maxAmt < val) {
					maxAmt = Math.ceil(val);
				}
			}
			dataitems.data = data;
			dataitems.borderColor = color;
			dataitems.borderWidth = 1;

			dataset.push(dataitems);
		}

		var myChart = new Chart(ctx, {
			type:"line",
			data:{
				labels:dataLabels,
				datasets:dataset
			},
			options:{
				title:{
					display:true,
					text:"Annual Performance ("+$("#financialYear option:selected").text()+")"
				},
				scales:{
					xAxes:[{
						display:true,
						scaleLabel:{
							display:true,
							labelString:"Month"
						}
					}],
					yAxes:[{
						display:true,
						scaleLabel:{
							display:true,
							labelString:"Amount"
						},
						ticks:{
							min:0,
							max:maxAmt,
							stepSize:10000
						}
					}]
				}
			}
		});

	}
	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		doSearch();
	});
});