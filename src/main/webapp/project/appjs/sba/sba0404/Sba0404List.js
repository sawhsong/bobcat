/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sba0404List.js
 *************************************************************************************************/
jsconfig.put("useJqTooltip", false);
var popup = null;

$(function() {
	/*!
	 * event
	 */
	$("#icnCheck").click(function(event) {
		commonJs.toggleCheckboxes("chkToSave");
	});

	$("#orgCategory").change(function(event) {
		doSearch();
	});

	$("#btnSave").click(function() {
		commonJs.confirm({
			contents:com.message.Q001,
			buttons:[{
				caption:com.caption.yes,
				callback:function() {
					commonJs.ajaxSubmit({
						url:"/sba/0404/exeSave.do",
						dataType:"json",
						formId:"fmDefault",
						data:{
						},
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
			}]
		});
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
					url:"/sba/0404/getList.do",
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
		var ds = result.dataSet, html = "";

		$("#tblGridBody").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr();
				var checkString = "", className = "chkEn", disabledStr = "";

				if (!commonJs.isEmpty(ds.getValue(i, "INCOME_TYPE_ID"))) {
					checkString = "checked";
				}

				if (ds.getValue(i, "USED_COUNT") > 0) {
					className = "chkDis";
					disabledStr = "disabled";
				}

				var uiChk = new UiCheckbox();
				uiChk.setName("chkToSave").setClassName(className+" inTblGrid").setOptions(checkString+" "+disabledStr).setValue(ds.getValue(i, "INCOME_TYPE_ID")+"_"+ds.getValue(i, "INCOME_TYPE_CODE"));
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiChk));

				if (!commonJs.isEmpty(ds.getValue(i, "INCOME_TYPE_ID"))) {
					var uiAnc = new UiAnchor();
					uiAnc.setText(ds.getValue(i, "INCOME_TYPE_NAME")).setScript("getUpdate('"+ds.getValue(i, "INCOME_TYPE_ID")+"', '"+ds.getValue(i, "INCOME_TYPE_CODE")+"')");
					gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAnc));
				} else {
					gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "INCOME_TYPE_NAME")));
				}

				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "IS_APPLY_GST")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.getNumberMask(ds.getValue(i, "GST_PERCENTAGE"), "#,##0.00")));
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

	getUpdate = function(incomeTypeId, incomeTypeCode) {
		popup = commonJs.openPopup({
			popupId:"IncomeTypeUpdate",
			url:"/sba/0404/getUpdate.do",
			paramData:{
				mode:"Update",
				incomeTypeId:incomeTypeId,
				incomeTypeCode:incomeTypeCode
			},
			header:com.header.popHeaderEdit,
			blind:true,
			width:900,
			height:300
		});
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		doSearch();
	});
});