/**
 * dataMigrationDetailPop.js
 */
$(function() {
	/*!
	 * event
	 */
	$("#btnClose").click(function(event) {
		parent.popup.close();
	});

	/*!
	 * process
	 */

	/*!
	 * load event (document / window)
	 */
	$(window).ready(function() {
		setTimeout(function() {
			$("#tblGrid").fixedHeaderTable({
				attachTo:$("#divDataArea")
			});
		}, 500);
	});
});