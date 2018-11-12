/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sys0406List.js
 *************************************************************************************************/
var popup = null;
var searchResultDataCount = 0;
var langCode = commonJs.upperCase(jsconfig.get("langCode"));
var delimiter = jsconfig.get("dataDelimiter");

$(function() {
	/*!
	 * event
	 */
	$("#btnSave").click(function(event) {
		if (!commonJs.doValidate("fmDefault")) {
			return;
		}

		commonJs.confirm({
			contents:com.message.Q001,
			buttons:[{
				caption:com.caption.yes,
				callback:function() {
					commonJs.ajaxSubmit({
						url:"/sys/0406/exeInsert.do",
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

	$("#icnCheck").click(function(event) {
		commonJs.toggleCheckboxes("chkToAssign");
	});

	$("[name=chkToAssign]").click(function(event) {
		var thisChecked = $(this).prop("checked"), thisValue = $(this).attr("paramValue");
		var thisMenuId = $(this).val();
		var thisValueItems = thisValue.split(delimiter);
		var thisLevel = thisValueItems[0];
		var thisPaths = thisValueItems[1].split("/");
console.log("thisMenuId : "+thisMenuId);
console.log("thisValue : "+thisValue);
		$("[name=chkToAssign]").each(function(index) {
			var menuId = $(this).val(), paramVal = $(this).attr("paramValue");
			var valItems = paramVal.split(delimiter);
			var level = valItems[0];
			var paths = valItems[1].split("/");

			if (thisValue != paramVal) {
				if (thisLevel == "1") {
					if (level > thisLevel && thisMenuId == paths[0]) {
						$(this).prop("checked", thisChecked);
					}
				}

				if (thisLevel == "2") {
					if (thisChecked) {
						if ((level > thisLevel && thisMenuId == paths[1]) || (level < thisLevel && thisPaths[0] == paths[0])) {
							$(this).prop("checked", thisChecked);
						}
					} else {
						if (level > thisLevel && thisMenuId == paths[1]) {
							$(this).prop("checked", thisChecked);
						}
					}
				}

				if (thisLevel == "3") {
					if (thisChecked) {
						if ((level == "1" && thisPaths[0] == paths[0]) || (level == "2" && thisPaths[1] == paths[1])) {
							$(this).prop("checked", thisChecked);
						}
					}
				}
			}
		});

		if (!thisChecked && thisLevel == "2") {
			if (!hasChildChecked(thisLevel, thisPaths[0])) {
				$("[name=chkToAssign]").each(function(index) {
					var val = $(this).attr("paramValue");
					var valItems = val.split(delimiter);
					var level = valItems[0];
					var paths = valItems[1].split("/");

					if (paths[0] == thisPaths[0]) {
						$(this).prop("checked", false);
						return false;
					}
				});
			}
		}

		if (!thisChecked && thisLevel == "3") {
			if (!hasChildChecked(thisLevel, thisPaths[1])) {
				$("[name=chkToAssign]").each(function(index) {
					var val = $(this).attr("paramValue");
					var valItems = val.split(delimiter);
					var level = valItems[0];
					var paths = valItems[1].split("/");

					if (paths[1] == thisPaths[1]) {
						$(this).prop("checked", false);
						return false;
					}
				});
			}

			if (!hasChildChecked(2, thisPaths[0])) {
				$("[name=chkToAssign]").each(function(index) {
					var val = $(this).attr("paramValue");
					var valItems = val.split(delimiter);
					var level = valItems[0];
					var paths = valItems[1].split("/");

					if (paths[0] == thisPaths[0]) {
						$(this).prop("checked", false);
						return false;
					}
				});
			}
		}
	});

	$("#authGroup").change(function() {
		$("#authGroupDesc").val($("#authGroup option:selected").attr("desc"));
		setCheckbox();
	});

	$(document).keypress(function(event) {
		if (event.which == 13) {
			var element = event.target;
		}
	});

	/*!
	 * process
	 */
	setCheckbox = function() {
		var selectedAuthGroup = $("#authGroup").val(), groupId = "";

		if (commonJs.isEmpty(selectedAuthGroup)) {
			return;
		}

		$("[name=chkToAssign]").each(function(index) {
			groupId = $(this).attr("groupId");

			if (!commonJs.isEmpty(groupId) && groupId.indexOf(selectedAuthGroup) != -1) {
				$(this).prop("checked", true);
			} else {
				$(this).prop("checked", false);
			}
		});
	};

	hasChildChecked = function(paramLevel, paramMenuId) {
		var rtn = false;

		$("[name=chkToAssign]:checked").each(function(index) {
			var val = $(this).val();
			var valItems = val.split(delimiter);
			var level = valItems[0];
			var paths = valItems[1].split("/");

			if (paramLevel == "2") {
				if (level > paramLevel && paths[0] == paramMenuId || level == paramLevel && paths[0] == paramMenuId) {
					rtn = true;
				}
			}

			if (paramLevel == "3") {
				if (level == paramLevel && paths[1] == paramMenuId) {
					rtn = true;
				}
			}
		});
		return rtn;
	};

	doSearch = function() {
		commonJs.showProcMessageOnElement("divScrollablePanel");

		setTimeout(function() {
			commonJs.ajaxSubmit({
				url:"/sys/0406/getList.do",
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

		setTimeout(function() {
			setCheckbox();
			$("[name=chkToAssign]").bind("click", function(event) {
				clickCheckToAssign(event);
			});
		}, 200);
	};

	renderDataGridTable = function(result) {
		var dataSet = result.dataSet;
		var html = "";

		searchResultDataCount = dataSet.getRowCnt();
		$("#tblGridBody").html("");

		if (dataSet.getRowCnt() > 0) {
			for (var i=0; i<dataSet.getRowCnt(); i++) {
				var menuPath = dataSet.getValue(i, "PATH"), menuId = dataSet.getValue(i, "MENU_ID"), menuName = dataSet.getValue(i, "MENU_NAME_"+langCode);
				var groupId = dataSet.getValue(i, "GROUP_ID"), space = "", style = "", paramValue = "";
				var iLevel = commonJs.toNumber(dataSet.getValue(i, "LEVEL")) - 1;
				var gridTr = new UiGridTr();

				style = (iLevel == 0 || iLevel == 1) ? "font-weight:bold;" : "";
				for (var j=0; j<iLevel; j++) {
					space += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				}

				paramValue = dataSet.getValue(i, "LEVEL")+delimiter+menuPath;

				var uiChk = new UiCheckbox();
				uiChk.setId("chkToAssign"+i).setName("chkToAssign").setValue(menuId).addAttribute("paramValue:"+paramValue).addAttribute("groupId:"+groupId);
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiChk));

				gridTr.addChild(new UiGridTd().addClassName("Lt").setStyle(style).setText(space+menuId));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setStyle(style).setText(menuName));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(dataSet.getValue(i, "MENU_URL")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(dataSet.getValue(i, "SORT_ORDER")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(dataSet.getValue(i, "DESCRIPTION")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(dataSet.getValue(i, "IS_ACTIVE")));

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

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		doSearch();
	});
});