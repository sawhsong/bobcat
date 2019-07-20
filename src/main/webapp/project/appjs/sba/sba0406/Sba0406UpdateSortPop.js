/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sba0406UpdateSortPop.js
 *************************************************************************************************/
var delimiter = jsconfig.get("dataDelimiter");

$(function() {
	/*!
	 * event
	 */
	$("#expenseTypeLevel").change(function(event) {
		var value = $(this).val();

		if (value == "Main") {
			$("#divExpenseMainType").css("display", "none");
		} else {
			$("#divExpenseMainType").css("display", "block");
		}

		refreshDataArea();
	});

	$("#mainType").change(function(event) {
		refreshDataArea();
	});

	$("#btnSave").click(function(event) {
		commonJs.confirm({
			contents:com.message.Q001,
			buttons:[{
				caption:com.caption.yes,
				callback:function() {
					commonJs.ajaxSubmit({
						url:"/sba/0406/exeUpdateSortOrder",
						dataType:"json",
						formId:"fmDefault",
						data:{
							dataLength:$("#ulMenuHolder .dummyMenu").length,
							orgCategory:orgCategory
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
											parent.popup.close();
											parent.doSearch();
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

	$("#btnClose").click(function(event) {
		parent.popup.close();
	});

	$(document).keypress(function(event) {
		if (event.which == 13) {
			var element = event.target;
		}
	});

	/*!
	 * process
	 */
	refreshDataArea = function() {
		var typeLevel = $("#expenseTypeLevel").val();
		var mainType = $("#mainType").val();

		$("#ulMenuHolder").empty();

		for (var i=0; i<dsExpenseType.getRowCnt(); i++) {
			if (typeLevel == "Main") {
				if (commonJs.isEmpty(dsExpenseType.getValue(i, "PARENT_EXPENSE_TYPE"))) {
					renderElement(i);
				}
			} else {
				if (dsExpenseType.getValue(i, "PARENT_EXPENSE_TYPE") == mainType) {
					renderElement(i);
				}
			}
		}

		$("#ulMenuHolder").find(".dummyMenu").each(function(groupIndex) {
			$(this).find("input[type=text]").each(function(index) {
				var id = $(this).attr("id"), name = $(this).attr("name");

				if (!commonJs.isEmpty(id)) {id = (id.indexOf(delimiter) != -1) ? id.substring(0, id.indexOf(delimiter)) : id;}
				else {id = "";}

				if (!commonJs.isEmpty(name)) {name = (name.indexOf(delimiter) != -1) ? name.substring(0, name.indexOf(delimiter)) : name;}
				else {name = "";}

				$(this).attr("id", id+delimiter+groupIndex).attr("name", name+delimiter+groupIndex);
			});
		});
	};

	renderElement = function(dsIndex) {
		var elem = $("#liDummy").clone();

		$(elem).css("display", "block").appendTo($("#ulMenuHolder"));
		$(elem).find("input[type=text]").each(function(index) {
			if ($(this).attr("id") == "expenseTypeId") {
				$(this).val(dsExpenseType.getValue(dsIndex, "EXPENSE_TYPE_ID"));
			}

			if ($(this).attr("id") == "expenseType") {
				$(this).val(dsExpenseType.getValue(dsIndex, "EXPENSE_TYPE"));
			}

			if ($(this).attr("id") == "description") {
				$(this).val(dsExpenseType.getValue(dsIndex, "DESCRIPTION"));
			}

			if ($(this).attr("id") == "sortOrder") {
				$(this).val(dsExpenseType.getValue(dsIndex, "SORT_ORDER"));
			}
		});
	};

	setSortable = function() {
		$("#ulMenuHolder").sortable({
			axis:"y",
			handle:".dragHandler",
			stop:function() {
				var typeLevel = $("#expenseTypeLevel").val();
				var mainType = $("#mainType").val();

				$("#ulMenuHolder").find(".dummyMenu").each(function(groupIndex) {
					$(this).find("input[type=text]").each(function(index) {
						var id = $(this).attr("id"), name = $(this).attr("name");

						if (!commonJs.isEmpty(id)) {id = (id.indexOf(delimiter) != -1) ? id.substring(0, id.indexOf(delimiter)) : id;}
						else {id = "";}

						if (!commonJs.isEmpty(name)) {name = (name.indexOf(delimiter) != -1) ? name.substring(0, name.indexOf(delimiter)) : name;}
						else {name = "";}

						$(this).attr("id", id+delimiter+groupIndex).attr("name", name+delimiter+groupIndex);

						if (name.indexOf("sortOrder") != -1) {
							var orgCategoryPrefix = getOrgCategoryPrefix();
							if (typeLevel == "Main") {
								$(this).val(orgCategoryPrefix + commonJs.lpad((groupIndex+1), 2, "0")+"00");
							} else {
								var mainSort = commonJs.nvl(dsExpenseType.getValue(dsExpenseType.getRowIndex("EXPENSE_TYPE", mainType), "SORT_ORDER"), "000000");

								mainSort = mainSort.substring(2, 4);
								$(this).val(orgCategoryPrefix + mainSort + commonJs.lpad((groupIndex+1), 2, "0"));
							}
						}
					});
				});
			}
		});

		$("#ulMenuHolder").disableSelection();
	};

	getOrgCategoryPrefix = function() {
		if (orgCategory == "A") {return "01";}
		else if (orgCategory == "B") {return "02";}
		else if (orgCategory == "C") {return "03";}
		else {return "04";}
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		refreshDataArea();
		setSortable();
	});
});