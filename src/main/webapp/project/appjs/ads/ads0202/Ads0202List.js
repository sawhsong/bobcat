/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Ads0202List.js
 *************************************************************************************************/
var popup;
var dateTimeFormat = jsconfig.get("dateTimeFormatJs");
var dateFormat = jsconfig.get("dateFormatJs");

$(function() {
	/*!
	 * event
	 */
	$("#btnNew").click(function() {
		getEdit("");
	});

	$(document).keydown(function(event) {
		var code = event.keyCode || event.which, element = event.target;

		if (code == 13) {}

		if (code == 9) {}
	});

	/*!
	 * process
	 */
	getEdit = function(quotationId) {
		popup = commonJs.openPopup({
			popupId:"QuotationEdit",
			url:"/ads/0202/getEdit.do",
			data:{quotationId:quotationId},
			header:"Quotation Edit",
			width:1400,
			height:900
		});
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
	});
});