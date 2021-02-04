/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Bst0202EditPop.js
 *************************************************************************************************/
$(function() {
	/*!
	 * event
	 */
	$("#btnUpload").click(function(event) {
		if ("disabled" == $(this).attr("disabled")) {
			return;
		}

		if (commonJs.doValidate("fmDefault")) {
			commonJs.doSaveWithFile({
				url:"/bst/0202/doUpload.do",
				data:{bankCode:$("#bankAccntId option:selected").attr("bankCode")},
				onSuccess:function(result) {
					var ds = result.dataSet;
				}
			});
		}
	});

	$("#btnClose").click(function() {
		parent.popup.close();
		parent.doSearch();
	});

	$(document).keydown(function(event) {
		var code = event.keyCode || event.which, element = event.target;

		if (code == 13) {}

		if (code == 9) {}
	});

	/*!
	 * process
	 */

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
	});
});