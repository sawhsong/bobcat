/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Bsa0202EditPop.js
 *************************************************************************************************/
var delimiter = jsconfig.get("dataDelimiter");

$(function() {
	/*!
	 * event
	 */
	$("#btnSave").click(function(event) {
		doSave();
	});

	$("#btnClose").click(function(event) {
		parent.popup.close();
		parent.doSearch();
	});

	$("#mainReconCategory").change(function() {
		setSubReconCategory();
	});

	$(document).keydown(function(event) {
		var code = event.keyCode || event.which, element = event.target;
		if (code == 13) {}
		if (code == 9) {}
	});

	/*!
	 * process
	 */
	doSave = function() {
		var chkForEdit = "";

		if (!commonJs.doValidate("fmDefault")) {
			return;
		}

		parent.$("input[name=chkForEdit]:checked").each(function() {
			chkForEdit += commonJs.isBlank(chkForEdit) ? $(this).val() : delimiter + $(this).val();
		});

		commonJs.doSave({
			url:"/bsa/0202/doBatchApplication.do",
			data:{
				chkForEdit:chkForEdit
			},
			onSuccess:function(result) {
				var ds = result.dataSet;
				$("#btnClose").trigger("click");
			}
		});
	};

	setSubReconCategory = function() {
		commonJs.doSearch({
			url:"/bsa/0202/getSubReconCategory.do",
			noForm:true,
			data:{mainReconCategoryId:$("#mainReconCategory").val()},
			onSuccess:function(result) {
				var ds = result.dataSet;

				$("#subReconCategory option").each(function(index) {
					$(this).remove();
				});

				$("#subReconCategory").append(commonJs.getUiSelectOption({
					value:"",
					text:"==Select=="
				}));

				if (ds.getRowCnt() > 0) {
					for (var i=0; i<ds.getRowCnt(); i++) {
						$("#subReconCategory").append(commonJs.getUiSelectOption({
							value:ds.getValue(i, "CATEGORY_ID"),
							text:ds.getValue(i, "CATEGORY_NAME")
						}));
					}
				}

				$("#subReconCategory").selectpicker("refresh");
			}
		});
	};

	/*!
	 * load event (document / window)
	 */
	$(document).click(function(event) {
		var obj = event.target;

		if ($(obj).is($("input:text")) && $(obj).hasClass("txtEn")) {
			$(obj).select();
		}
	});

	$(window).load(function() {
		$(".numeric").number(true, 2);
	});
});