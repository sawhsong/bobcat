/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sba0412List.js
 *************************************************************************************************/
jsconfig.put("useJqTooltip", false);
var popup = null;
var searchResultDataCount = 0;

$(function() {
	/*!
	 * event
	 */
	$("#icnCheck").click(function(event) {
		commonJs.toggleCheckboxes("chkToAssign");
	});

	$("#orgCategory").change(function(event) {
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

		if (commonJs.doValidate($("#fmDefault"))) {
			setTimeout(function() {
				commonJs.ajaxSubmit({
					url:"/sba/0412/getList.do",
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
		}
	};

	renderDataGridTable = function(result) {
		var ds = result.dataSet;
		var html = "";

		searchResultDataCount = ds.getRowCnt();
		$("#tblGridBody").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr();
				var checkString = "";

				if (!commonJs.isEmpty(ds.getValue(i, "BORROWING_TYPE_ID"))) {
					checkString = "checked";
				}

				var uiChk = new UiCheckbox();
				uiChk.setId("chkToAssign").setName("chkToAssign").addAttribute(checkString).setValue(ds.getValue(i, "BORROWING_TYPE_ID")+"_"+ds.getValue(i, "BORROWING_TYPE_CODE"));
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiChk));

				var uiAnc = new UiAnchor();
				uiAnc.setText(ds.getValue(i, "BORROWING_TYPE_NAME")).setScript("getDetail('"+ds.getValue(i, "BORROWING_TYPE_ID")+"', '"+ds.getValue(i, "BORROWING_TYPE_CODE")+"')");
				gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAnc));

				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "IS_APPLY_GST")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "GST_PERCENTAGE"), "#,###.##")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "ACCOUNT_CODE")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "INSERT_DATE")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "UPDATE_DATE")));

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
			isPageable:false,
			isFilter:false,
			filterColumn:[],
			totalResultRows:result.totalResultRows,
			script:"doSearch"
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
			url = "/sba/0412/getDetail.do";
			header = com.header.popHeaderDetail;
		} else if (param.mode == "New" || param.mode == "Reply") {
			url = "/sba/0412/getInsert.do";
			header = com.header.popHeaderEdit;
		} else if (param.mode == "Edit") {
			url = "/sba/0412/getUpdate.do";
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

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		doSearch();
	});
});