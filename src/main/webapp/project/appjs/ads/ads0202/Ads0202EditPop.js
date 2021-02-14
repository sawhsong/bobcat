/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Ads0202EditPop.js
 *************************************************************************************************/
$(function() {
	/*!
	 * event
	 */
	$("#btnClose").click(function() {
		parent.popup.close();
//		parent.doSearch();
	});

	$(document).keydown(function(event) {
		var code = event.keyCode || event.which, element = event.target;
		if (code == 13) {}
		if (code == 9) {}
	});

	/*!
	 * process
	 */
	setFieldFormat = function() {
//		commonJs.setFieldNumberMask("bsb", "999 999");
//		$("#balance").number(true, 2);
	};

	loadData = function() {
		if (!commonJs.isBlank(quotationId)) {
			commonJs.showProcMessage(com.message.loading);

			setTimeout(function() {
				commonJs.doSimpleProcess({
					url:"/ads/0202/getQuotationMasterInfo.do",
					noForm:true,
					data:{quotationId:quotationId},
					onSuccess:function(result) {
						var ds = result.dataSet;
						setQuotationMasterInfo(ds);
					}
				});
			}, 400);
		}
	};

	setQuotationMasterInfo = function (ds) {
		
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		setFieldFormat();
		loadData();
	});
});