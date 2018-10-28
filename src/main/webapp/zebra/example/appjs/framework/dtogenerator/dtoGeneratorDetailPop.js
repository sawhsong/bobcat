/**
 * dtoGeneratorDetailPop.js
 */
$(function() {
	/*!
	 * event
	 */
	$("#btnClose").click(function(event) {
		parent.popupDetail.close();
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
				attachTo:$("#divDataArea"),
				pagingArea:$("#divPagingArea")
			});
		}, 500);
	});
});