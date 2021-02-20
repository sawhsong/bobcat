/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Ads0204PreviewPop.js
 *************************************************************************************************/
var dateTimeFormat = jsconfig.get("dateTimeFormatJs");
var dateFormat = jsconfig.get("dateFormatJs");

$(function() {
	/*!
	 * event
	 */
	$("#btnClose").click(function() {
		parent.preview.close();
	});

	$("#btnPrint").click(function() {
		$("#divDataArea").printThis({
			importCSS:true
		});
	});

	$("#btnExport").click(function() {
		commonJs.doExport({
			url:"/ads/0204/doExport.do",
			data:{quotationId:quotationId}
		});
	});

	$(document).keydown(function(event) {
		var code = event.keyCode || event.which, element = event.target;
		if (code == 13) {}
		if (code == 9) {}
	});
	/*!
	 * process
	 */
	displayMessage = () => {
		commonJs.showProcMessage(com.message.loading);
		setTimeout(() => commonJs.hideProcMessage(), 400);
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		displayMessage();
	});
});