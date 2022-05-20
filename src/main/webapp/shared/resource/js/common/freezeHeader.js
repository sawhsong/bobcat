/**
 * tbody scrollable table with fixed header
 * Usage : in $(window).load() event
 * 		 : selector must have an ID
 * 			$("#tblFixedHeaderTable").freezeHeader({
 * 				attachTo:$("divDataArea"),				// [mandatory : parent jquery object which holds this table - usually this would be 'divDataArea']
 * 				pagingArea:$("#divPagingArea")			// [optional : pagination area jquery object]
 * 				isPageable:true/false					// [optional : true : displays paging area component, false : displays nothing]
 * 				pagingAlign:left						// [optional : left/center]
 * 				displayRowCount:true					// [optional : true : displays total row count even if isPageable is false, false : displays nothing]
 * 				attachToHeight:750,						// [optional : height of parent jquery object which holds this table]
 * 				isFilter:false,							// [optional : true : displays filter row]
 * 				filterColumn:[1, 2],					// [optional : if isFilter is true : sets the column index to display filter textbox]
 * 
 * 				formId:"formId"							// [optional : for pagination area component - form Id]
 * 				formAction:"formAction					// [optional : for pagination area component - form action]
 * 				script:"doSearch"						// [conditional mandatory : for pagination area component - if paging is attached this is mandatory]
 * 				totalResultRows:result.totalResultRows	// [conditional mandatory : for pagination area component - if paging is attached this is mandatory]
 * 			});
 */
(function($) {
	$.fn.freezeHeader = function(options) {
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

			var $scrollablePanel, attachToHeight = 0, isScrollbar = false, heightAdjustment = 0, hFixByTheme = 0;
			var isPopup = $.nony.isPopup(), isTabFrame = $.nony.isTabFrame(), tableId = $(this).attr("id");
			var systemGeneratedTableForFixedHeaderId = jsconfig.get("systemGeneratedTableForFixedHeaderId"+tableId) || "systemGeneratedTableForFixedHeader"+tableId;
			var divSystemGeneratedFixedTableHeaderWrapperId = jsconfig.get("divSystemGeneratedFixedTableHeaderWrapperId")+tableId;

			$(options.attachTo).css("overflow", "auto");

			jsconfig.put("systemGeneratedTableForFixedHeaderId", systemGeneratedTableForFixedHeaderId);

			if ($("#"+systemGeneratedTableForFixedHeaderId).length > 0) {
				$("#"+systemGeneratedTableForFixedHeaderId).remove();
			}

			/*!
			 * Rendering Paging Area
			 */
			$(options.pagingArea).html(paging.getPagingHtml(options));
			$("#selMaxRowsPerPageSelectForPagenation").selectpicker({width:"52px"});

			/*!
			 * Filter Row
			 */
			if (options.isFilter) {
				if ($(this).find("thead").find("#systemGeneratedFilterRow").length <= 0) {
					var html = "", filterColLength = 0;

					html += "<tr id=\"systemGeneratedFilterRow\">";

					if (options.filterColumn == null || options.filterColumn == "undefined") {
						$(this).find("thead th").each(function(index) {
							html += "<th class=\"thGrid Ct\">";
							html += "<input type=\"text\" class=\"txtEn Lt\" style=\"width:100%;font-weight:normal\" onkeyup=\"Table.filter(this, this)\"/>";
							html += "</th>"
						});
					} else {
						$(this).find("thead th").each(function(index) {
							html += "<th class=\"thGrid Ct\">";
							for (var i=0; i<options.filterColumn.length; i++) {
								if (options.filterColumn[i] == index) {
									html += "<input type=\"text\" class=\"txtEn Lt\" style=\"width:100%;font-weight:normal\" onkeyup=\"Table.filter(this, this)\"/>";
								}
							}
							html += "</th>"
						});
					}

					html += "</tr>";
					$(this).find("thead").append($(html));
				}
			}

			/*!
			 * Calculating height(pagingArea, attachTo)
			 */
			var pagingAreaHeight = 0;
			if ($(options.pagingArea).length > 0) {
				pagingAreaHeight = $(options.pagingArea).outerHeight();
			}

			if (isPopup) {
				$scrollablePanel = $("#divScrollablePanelPopup");

				if ($.nony.browser.Chrome) {heightAdjustment = 4;}
				else if ($.nony.browser.FireFox) {heightAdjustment = 4;}
				else {heightAdjustment = 4;}
			} else if (isTabFrame) {
				$scrollablePanel = $("#divScrollablePanelFrame");

				if ($.nony.browser.Chrome) {heightAdjustment = 4;}
				else if ($.nony.browser.FireFox) {heightAdjustment = 4;}
				else {heightAdjustment = 4;}
			} else {
				$scrollablePanel = $("#divScrollablePanel");

				if ($.nony.browser.Chrome) {heightAdjustment = 6;}
				else if ($.nony.browser.FireFox) {heightAdjustment = 6;}
				else {heightAdjustment = 6;}
			}

			/*!
			 * Return if the data table is smaller than attachTo height
			 */
//			attachToHeight = options.attachToHeight || $(options.attachTo).height();

			if (($scrollablePanel.height() - (pagingAreaHeight + heightAdjustment)) >= attachToHeight) {
//				$(options.attachTo).height($(this).height() + heightAdjustment);
			} else {
//				$(options.attachTo).height($scrollablePanel.height() - (pagingAreaHeight + heightAdjustment));
			}

			if (!$.nony.isEmpty(options.attachToHeight) || options.attachToHeight > 0) {
				$(options.attachTo).height(options.attachToHeight);
			} else {
				$(options.attachTo).height($scrollablePanel.height() - (pagingAreaHeight + heightAdjustment) - hFixByTheme);
			}
//			$(options.attachTo).height($scrollablePanel.height() - (pagingAreaHeight + heightAdjustment));

			if ($(this).height() <= $(options.attachTo).height()) {
				$(options.attachTo).height($(this).height() + (heightAdjustment - 2));
			}

			/*!
			 * Fixed header
			 */
			var $table = $(this), visibleHeight = 0;
			var $scrollablePanel = $.nony.isPopup() ? $("#divScrollablePanelPopup") : ($.nony.isTabFrame() ? $("#divScrollablePanelFrame") : $("#divScrollablePanel"));
			var $header = $table.find("thead").clone(true, true);
			var $fixedTable = $("<table id=\""+systemGeneratedTableForFixedHeaderId+"\"/>").prop("class", $table.prop("class"))
								.css({position:"fixed", overflow:"hidden", visibility:"hidden", "margin-left":"0px", "margin-top":"0px", "z-index":1000});

			if ($.nony.browser.Chrome) {$fixedTable.width(($table.width()));}
			else if ($.nony.browser.FireFox) {$fixedTable.width($table.width());}
			else {$fixedTable.width($table.width());}

			/*!
			 * If (table width != '100%' or table width > attachTo width)
			 */
//			if ($table.width() > $(options.attachTo).width()) {
//				return;
//			}

			$table.before($fixedTable);
			$fixedTable.append($header).css({visibility:"visible"});

			$table.find("th").each(function(index) {
				$($fixedTable.find("th")[index]).bind("click", function() {
					$($table.find("th")[index]).trigger("click");

					$table.find("th").each(function(inner_index) {
						$fixedTable.find("th").each(function() {
							$($fixedTable.find("th")[inner_index]).attr("class", $($table.find("th")[inner_index]).attr("class"));
						});
					});
				});

				$($fixedTable.find("th")[index]).width($(this).width());
			});

			if ($(this).find("thead").find("#systemGeneratedFilterRow").length > 0) {
				$("#systemGeneratedFilterRow").find("th input[type=text]").each(function(index) {
					$($fixedTable.find("th input[type=text]")[index]).bind("keyup", function() {
						var val = $($fixedTable.find("th input[type=text]")[index]).val();

						$($table.find("thead th input[type=text]")[index]).val(val);
						$($table.find("thead th input[type=text]")[index]).trigger("keyup");
					});
				});
			}

			visibleHeight = $.nony.toNumber($(options.attachTo).offset().top - $scrollablePanel.offset().top);

			/*!
			 * Check page scroll for hiding/displaying header according to the wapper
			 * 		options.scrollWrapper
			 */
			if (!$.nony.isEmpty(options.scrollWrapper)) {
				$scrollablePanel = $(options.scrollWrapper);

				$scrollablePanel.bind("scroll", function() {
					var tableWrapperId = jsconfig.get("divSystemGeneratedFixedTableHeaderWrapperId"+tableId);
					var $tableWrapper = $("#"+tableWrapperId);

					$tableWrapper.css("top", $(options.attachTo).offset().top);

					if (visibleHeight < $scrollablePanel.scrollTop()) {
						$fixedTable.hide();
					} else {
						$fixedTable.show();
					}
				});
			} else {
				$scrollablePanel.bind("scroll", function() {
					$fixedTable.css("top", $(options.attachTo).offset().top);

					if (visibleHeight < $scrollablePanel.scrollTop()) {
						$fixedTable.hide();
					} else {
						$fixedTable.show();
					}
				});
			}

			/*!
			 * If (table width != '100%' or table width > attachTo width)
			 * -> this case : another attachTo div should be created inside 'divDataArea'
			 */
			if ($table.width() > $(options.attachTo).width()) {
				var borderColor = $table.find("tbody>tr>td:first-child").css("border-color");
				var wrapperWidth = 0, leftPos = 0, scrollWidth = 0, scrollHeight = 0;

				if ($.nony.browser.Chrome) {
					scrollWidth = 18;
					scrollHeight = 18;
				} else if ($.nony.browser.FireFox) {
					scrollWidth = 19;
					scrollHeight = 19;
				} else {
					scrollWidth = 19;
					scrollHeight = 19;
				}

//				if ($("#"+divSystemGeneratedFixedTableHeaderWrapperId).length > 0) {
					$("#"+divSystemGeneratedFixedTableHeaderWrapperId).remove();
					if ($(options.attachTo).height() > $table.height()) {
						$(options.attachTo).height($(this).height() + (heightAdjustment - 4) + scrollHeight);
					}
//				}

				$(options.attachTo).css({"border-left":"0px solid", "border-color":borderColor});

				// is scroll bar displayed?
				if ($(options.attachTo).height() <= $table.height()) {
					wrapperWidth = $(options.attachTo).width()-scrollWidth;
				} else {
					wrapperWidth = $(options.attachTo).width();
				}

				if (isPopup) {
					if ($.nony.browser.Chrome) {leftPos = $fixedTable.offset().left+0;}
					else if ($.nony.browser.FireFox) {leftPos = $fixedTable.offset().left+0;}
					else {leftPos = $fixedTable.offset().left+0;}
				} else if (isTabFrame) {
					if ($.nony.browser.Chrome) {leftPos = $fixedTable.offset().left+0;}
					else if ($.nony.browser.FireFox) {leftPos = $fixedTable.offset().left+0;}
					else {leftPos = $fixedTable.offset().left+0;}
				} else {
					if ($.nony.browser.Chrome) {leftPos = $fixedTable.offset().left+0;}
					else if ($.nony.browser.FireFox) {leftPos = $fixedTable.offset().left+0;}
					else {leftPos = $fixedTable.offset().left+0;}
				}

				var $wrapper = $("<div id=\"divSystemGeneratedFixedTableHeaderWrapper"+tableId+"\"></div>").css({
					"width":wrapperWidth,
					"height":$header.height()+1,
					"left":leftPos,
					"top":$fixedTable.offset().top,
					"position":"fixed",
					"table-layout":"fixed",
					"margin-top":"0px",
//					"border":"1px solid red",
					"overflow":"hidden"
				});
				$fixedTable.css("table-layout", "").css("position", "relative");
				$fixedTable.detach().appendTo($wrapper);
				$table.before($wrapper);

				jsconfig.put("divSystemGeneratedFixedTableHeaderWrapperId"+tableId, "divSystemGeneratedFixedTableHeaderWrapper"+tableId);

				var left = $table.offset().left;
				$(options.attachTo).bind("scroll", function() {
					$wrapper.scrollLeft($(options.attachTo).scrollLeft());
//					$fixedTable.css("left", left - $(options.attachTo).scrollLeft());
				});

				$(options.attachTo).scrollLeft("0");
			}

			$(options.attachTo).scrollTop("0");
		});
	};
})(jQuery);