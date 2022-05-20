/**
 * tbody default html table - no fixed header | paging area
 * Usage : in $(window).load() event
 * 		 : selector must have an ID
 * 			$("#tblFixedHeaderTable").setGridTable({
 * 				attachTo:$("divDataArea"),				// [mandatory : parent jquery object which holds this table - usually this would be 'divDataArea']
 * 				pagingArea:$("#divPagingArea")			// [optional : pagination area jquery object]
 * 				isPageable:true/false					// [optional : true : displays paging area component, false : displays nothing]
 * 				pagingAlign:left						// [optional : left/center]
 * 				displayRowCount:true					// [optional : true : displays total row count even if isPageable is false, false : displays nothing]
 * 				attachToHeight:750,						// [optional : height of parent jquery object which holds this table]
 * 
 * 				formId:"formId"							// [optional : for pagination area component - form Id]
 * 				formAction:"formAction					// [optional : for pagination area component - form action]
 * 				script:"doSearch"						// [conditional mandatory : for pagination area component - if paging is attached this is mandatory]
 * 				totalResultRows:result.totalResultRows	// [conditional mandatory : for pagination area component - if paging is attached this is mandatory]
 * 			});
 */
(function($) {
	$.fn.setGridTable = function(options) {
		return this.each(function() {
			if ($(options.attachTo).length <= 0) {
				throw new Error("AttachTo" + com.message.mandatory);
				return;
			}

			if (options.isPageable || options.displayRowCount) {
				if ($(options.pagingArea).length <= 0) {
					throw new Error("PagingArea" + com.message.mandatory);
					return;
				}
			}

			var $scrollablePanel, attachToHeight = 0, heightAdjustment = 0, hFixByTheme = 0;
			var isPopup = $.nony.isPopup(), isTabFrame = $.nony.isTabFrame();

			$(options.attachTo).css("overflow", "auto");

			/*!
			 * Rendering Paging Area
			 */
			$(options.pagingArea).html(paging.getPagingHtml(options));
			$("#selMaxRowsPerPageSelectForPagenation").selectpicker({width:"52px"});

			/*!
			 * Calculating height(pagingArea, attachTo)
			 */
			var pagingAreaHeight = 0;
			if ($(options.pagingArea).length > 0) {
				pagingAreaHeight = $(options.pagingArea).outerHeight();
			}

			if (isPopup) {
				$scrollablePanel = $("#divScrollablePanelPopup");

				if ($.nony.browser.Chrome) {heightAdjustment = 0;}
				else if ($.nony.browser.FireFox) {heightAdjustment = 0;}
				else {heightAdjustment = 0;}
			} else if (isTabFrame) {
				$scrollablePanel = $("#divScrollablePanelFrame");

				if ($.nony.browser.Chrome) {heightAdjustment = 0;}
				else if ($.nony.browser.FireFox) {heightAdjustment = 0;}
				else {heightAdjustment = 0;}
			} else {
				$scrollablePanel = $("#divScrollablePanel");

				if ($.nony.browser.Chrome) {heightAdjustment = 4;}
				else if ($.nony.browser.FireFox) {heightAdjustment = 4;}
				else {heightAdjustment = 4;}
			}

			if (!$.nony.isEmpty(options.attachToHeight) || options.attachToHeight > 0) {
				$(options.attachTo).height(options.attachToHeight);
			} else {
				$(options.attachTo).height($scrollablePanel.height() - (pagingAreaHeight + heightAdjustment) - hFixByTheme);
			}

			if ($(this).height() <= $(options.attachTo).height()) {
				$(options.attachTo).height($(this).height() + (heightAdjustment - 4));
			}
		});
	};
})(jQuery);