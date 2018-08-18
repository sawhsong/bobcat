/**
 * tbody scrollable table with fixed header
 * Usage : in $(window).load() event
 * 		 : selector must have an ID
 * 			$("#tblFixedHeaderTable").fixedHeaderTable({
 * 				baseDivElementId:"divScrollablePanel",				// [mandatory : parent div id or object which holds this table - usually this would be 'divScrollablePanel']
 * 				baseWidth:1010,										// [optional : base width] - display:none -> jquery doesn't know height & width
 * 				baseHeight:400,										// [optional : base height] - display:none -> jquery doesn't know height & width
 * 				headerWidth:["20%", "20%", "20%", "20%", "20%"]		// [optional : table header width] - display:none -> jquery doesn't know height & width
 * 				headerHeight:22										// [optional : table header height(default:22)] - display:none -> jquery doesn't know height & width
 * 				footerHeight:22,									// [optional : table tooter height(default:22)] - display:none -> jquery doesn't know height & width
 * 				widthAdjust:10										// [optional : width correction value] - because of a padding or margin, width or height might not be accurate
 * 				pagingArea:true/false								// [optional : if pagination area should be considered(default:true)]
 * 				attachedPagingArea:true/false						// [optional : if pagination area is attached with the list table - this is for Ajax function (default:false)]
 * 
 * 				pagingAreaId:"divPagingArea"						// [optional : when pagingArea is attached to the table pagingAreaId -> this means ajax call]
 * 				formId:"formId"										// [optional : form Id]
 * 				formAction:"formAction								// [optional : form action]
 * 				script:"doSearch"									// [conditional mandatory : if paging is attached this is mandatory]
 * 				setDummyPaging:true/false							// [optional : make the paging area dummy area(shows only total number of rows)]
 * 			});
 */
(function($) {
	$.fn.fixedHeaderTable = function(options) {
		return this.each(function() {
			var baseDivElementId, thisId, $baseElement, $closestElement;
			var baseWidth = 0, baseHeight = 0, thisHeight = 0, tHeaderHeight = 0, tFooterHeight = 0, tBodyHeight = 0, pagingAreaHeight = 0, captionHeight = 0;
			var originalHeight = 0, colWidthSum = 0, zeroWidthColIndex = -1, tempForWidth = 0;
			var headerColumnWidth = new Array();
			var scrollBarWidth = 0, widthAdjust = 0, compWidth = 0, heightAdjust = 0, heightSum = 0, bodyTrHeightSum = 0;

			/*!
			 * set variables
			 */
			$(this).addClass("fixedHeaderTable");

			if ($.nony.isEmpty(options.baseDivElement)) {return;}

			if (typeof(options.baseDivElement) == "string") {baseDivElementId = options.baseDivElement;}
			else {baseDivElementId = $(options.baseDivElement).attr("id");}

			if ($.nony.isEmpty(baseDivElementId)) {return;}

			/**
			 * Paging Area
			 */
			if (options.attachedPagingArea == true && !$.nony.isEmpty(options.pagingAreaId)) {
				var className = "", readOnly = "", event = "", jsParamString = "", html = "";
				var arrMaxRowsPerPageSelect = globalMap.get("maxRowsPerPageArray").split(globalMap.get("dataDelimiter"));
				var maxRowsPerPage = $.nony.toNumber($("#selMaxRowsPerPageSelectForPagenation").val());
				var pageNumsPerPage = ($.nony.isPopup()) ? 5 : parseInt(globalMap.get("pageNumsPerPage"));

				if (maxRowsPerPage <= 0) {
					maxRowsPerPage = parseInt(globalMap.get("maxRowsPerPage"));
				}

				var viewPageCount = 1;
				var totalPages = 0;
				var totalPos = 0, currentPos = 0, prevPage = 0, nextPage = 0, temp = 0;

				totalPages = parseInt((($.nony.toNumber(options.totalResultRows) - 1) / maxRowsPerPage) + 1);
				totalPages = parseInt((totalPages == 0) ? 1 : totalPages);

				currentPage = ($.nony.isEmpty($("#txtCurrentPageForPagination").val()) || $("#txtCurrentPageForPagination").val() == 0) ? 1 : $("#txtCurrentPageForPagination").val();
				if (currentPage <= 0) {
					currentPage = 1;
				}
				currentPage = parseInt((currentPage > totalPages) ? totalPages : currentPage);
				totalPos = parseInt(((totalPages - 1) / pageNumsPerPage) + 1);
				currentPos = parseInt(((currentPage - 1) / pageNumsPerPage) + 1);

				if (currentPos == totalPos) {
					viewPageCount = ((totalPages % pageNumsPerPage) == 0) ? pageNumsPerPage : (totalPages % pageNumsPerPage);
				} else {
					viewPageCount = pageNumsPerPage;
				}
				viewPageCount = parseInt(viewPageCount);

				jsParamString = "'"+options.formId+"', '"+options.formAction+"', '"+options.script+"'";

				if ($(this).find("tfoot tr").length > 0) {
					html += "<div id=\"\" class=\"verGap2\"></div>";
				}

				if (options.setDummyPaging) {
					html += "<div id=\"divPagination\">";
					html += "<table id=\"tblPagination\">";
					html += "<tr>";
					if ($.nony.isPopup()) {
						html += "<td class=\"tdPaginationLeft\">";
					} else {
						html += "<td class=\"tdPaginationLeft\"></td>";
						html += "<td class=\"tdPaginationCenter\">";
					}
					html += "<ul id=\"ulPagination\">";
					html += "</ul>";
					html += "</td>";
					html += "<td class=\"tdPaginationRight\" style=\"text-align:right;\">";
//					html += $.nony.getNumberMask(options.totalResultRows, "#,##0")+" Rows";
					html += "</td>";
					html += "</tr>";
					html += "</table>";
					html += "</div>";

					$("#"+options.pagingAreaId).html(html);
				} else {
					html += "<div id=\"divPagination\">";
					html += "<table id=\"tblPagination\">";
					html += "<tr>";
					if ($.nony.isPopup()) {
						html += "<td class=\"tdPaginationLeft\">";
					} else {
						html += "<td class=\"tdPaginationLeft\"></td>";
						html += "<td class=\"tdPaginationCenter\">";
					}
					html += "<ul id=\"ulPagination\">";
					if (currentPage > 1) {
						html += "<li class=\"liPaginationButton\">";
						html += "<a class=\"aPaginationButtonEn\" onclick=\"$.nony.goPageForPagination("+jsParamString+", '1')\" title=\"First Page\">";
						html += "<i class=\"fa fa-angle-double-left\"></i>";
						html += "</a>";
						html += "</li>";
					} else {
						html += "<li class=\"liPaginationButton disabled\">";
						html += "<a class=\"aPaginationButtonDis\" title=\"First Page\">";
						html += "<i class=\"fa fa-angle-double-left\"></i>";
						html += "</a>";
						html += "</li>";
					}

					prevPage = currentPage - 1;

					if (prevPage >= 1) {
						html += "<li class=\"liPaginationButton\">";
						html += "<a class=\"aPaginationButtonEn\" onclick=\"$.nony.goPageForPagination("+jsParamString+", '"+prevPage+"')\" title=\"Previous Page\">";
						html += "<i class=\"fa fa-angle-left\"></i>";
						html += "</a>";
						html += "</li>";
					} else {
						html += "<li class=\"liPaginationButton disabled\">";
						html += "<a class=\"aPaginationButtonDis\" title=\"Previous Page\">";
						html += "<i class=\"fa fa-angle-left\"></i>";
						html += "</a>";
						html += "</li>";
					}

					for (var i=1; i<=viewPageCount && (currentPos - 1) * pageNumsPerPage + i <= totalPages; i++) {
						temp = parseInt((currentPos - 1) * pageNumsPerPage + i);

						if (((currentPos - 1) * pageNumsPerPage + i) == currentPage) {
							html += "<li class=\"liPaginationButton active\">";
							html += "<a class=\"aPaginationButtonCurr\">";
							html += temp;
							html += "</a>";
							html += "</li>";
						} else {
							html += "<li class=\"liPaginationButton\">";
							html += "<a class=\"aPaginationButtonEn\" onclick=\"$.nony.goPageForPagination("+jsParamString+", '"+temp+"')\">";
							html += temp;
							html += "</a>";
							html += "</li>";
						}
					}

					nextPage = parseInt(currentPage + 1);

					if (nextPage <= totalPages) {
						html += "<li class=\"liPaginationButton\">";
						html += "<a class=\"aPaginationButtonEn\" onclick=\"$.nony.goPageForPagination("+jsParamString+", '"+nextPage+"')\" title=\"Next Page\">";
						html += "<i class=\"fa fa-angle-right\"></i>";
						html += "</a>";
						html += "</li>";
					} else {
						html += "<li class=\"liPaginationButton disabled\">";
						html += "<a class=\"aPaginationButtonDis\" title=\"Next Page\">";
						html += "<i class=\"fa fa-angle-right\"></i>";
						html += "</a>";
						html += "</li>";
					}

					if (currentPage < totalPages) {
						html += "<li class=\"liPaginationButton\">";
						html += "<a class=\"aPaginationButtonEn\" onclick=\"$.nony.goPageForPagination("+jsParamString+", '"+totalPages+"')\" title=\"Last Page\">";
						html += "<i class=\"fa fa-angle-double-right\"></i>";
						html += "</a>";
						html += "</li>";
					} else {
						html += "<li class=\"liPaginationButton disabled\">";
						html += "<a class=\"aPaginationButtonDis\" title=\"Last Page\">";
						html += "<i class=\"fa fa-angle-double-right\"></i>";
						html += "</a>";
						html += "</li>";
					}

					var rowCountForDisplay = parseInt(currentPage * maxRowsPerPage);

					html += "</ul>";
					html += "</td>";
					html += "<td class=\"tdPaginationRight\">";

					if (options.totalResultRows <= 0) {
						readOnly = "readonly=\"readonly\"";
						event = "";
					} else {
						readOnly = "";
						event = "onkeypress=\"$.nony.onKeyPressFortxtCurrentPageForPagination(event, "+jsParamString+", this)";
					}
					html += "<div id=\"divPaginationDescriptor\">";
					html += "<div class=\"\">";
					html += "<input type=\"text\" id=\"txtCurrentPageForPagination\" name=\"txtCurrentPageForPagination\" class=\"txtPagination\" value=\""+currentPage+"\" "+readOnly+" "+event+"\"/>";
					html += "</div>";
					html += "<div class=\"paginationDescriptor\">";
//					html += "&nbsp; / "+$.nony.getNumberMask(totalPages, "#,###")+" Pages";
					html += "&nbsp; / "+$.nony.getNumberMask(totalPages, "#,###");
					html += "<span class=\"spanPaginationBreaker\"></span>";

					if (options.totalResultRows <= 0) {
						className = "disabled";
						readOnly = "disabled";
						event = "";
					} else {
						className = "";
						readOnly = "";
						event = "onchange=\"$.nony.goPageForPagination("+jsParamString+", '"+currentPage+"')\"";
					}
					html += "</div>";
					html += "<div style=\"\">";
					html += "<select id=\"selMaxRowsPerPageSelectForPagenation\" name=\"selMaxRowsPerPageSelectForPagenation\" class=\"selectpicker bootstrapSelect "+className+"\" "+readOnly+" "+event+">";

					for (var i=0; i<arrMaxRowsPerPageSelect.length; i++) {
						var selected = (maxRowsPerPage == parseInt(arrMaxRowsPerPageSelect[i])) ? "selected" : "";
						html += "<option value=\""+arrMaxRowsPerPageSelect[i]+"\" "+selected+">"+arrMaxRowsPerPageSelect[i]+"</option>";
					}
					html += "</select>";
					html += "</div>";
//					html += "<div class=\"paginationDescriptor\">&nbsp;Rows";
					html += "<div class=\"paginationDescriptor\">";
					html += "<span class=\"spanPaginationBreaker\"></span>";

					if (options.totalResultRows <= 0) {
						html += $.nony.getNumberMask(0, "#,##0")+" - ";
					} else {
						html += $.nony.getNumberMask((((currentPage - 1) * maxRowsPerPage) + 1), "#,##0")+" - ";
					}
//					html += $.nony.getNumberMask(((rowCountForDisplay > options.totalResultRows) ? options.totalResultRows : rowCountForDisplay), "#,##0")+" / "+$.nony.getNumberMask(options.totalResultRows, "#,##0")+" Items";
					html += $.nony.getNumberMask(((rowCountForDisplay > options.totalResultRows) ? options.totalResultRows : rowCountForDisplay), "#,##0")+" / "+$.nony.getNumberMask(options.totalResultRows, "#,##0");
					html += "</div>";
					html += "</div>";
					html += "</td>";
					html += "</tr>";
					html += "</table>";
					html += "</div>";

					$("#"+options.pagingAreaId).html(html);

					$("#selMaxRowsPerPageSelectForPagenation").selectpicker({width:"52px"});
				}
			} // Paging Area End

			$baseElement = $("#"+baseDivElementId);
			$closestElement = $(this).closest("div");

			thisId = $(this).attr("id");
			baseWidth = options.baseWidth || $baseElement.outerWidth();
			baseHeight = options.baseHeight || $baseElement.outerHeight();
			pagingAreaHeight = ($.nony.isEmpty(options.pagingArea) || options.pagingArea == true) ? (pagingAreaHeight = $("#divPagingArea").actual("height") + 0) : 0;
//$baseElement.css("border","1px solid red");
//$.nony.printLog({message:"baseWidth : "+baseWidth});
//$.nony.printLog({message:"baseHeight : "+baseHeight});
//$.nony.printLog({message:"pagingAreaHeight : "+pagingAreaHeight});
			$(this).find("tbody tr").each(function(index) {
				if (commonJs.browser.FireFox) {
					bodyTrHeightSum += $(this).outerHeight()+1;
				} else {
					bodyTrHeightSum += $(this).outerHeight()+2;
				}
			});

			thisWidth = $(this).outerWidth();
			// Differenciate Ajax call to Normal page table
			if (options.attachedPagingArea == true && !$.nony.isEmpty(options.pagingAreaId)) {
//$.nony.printLog({message:"bodyTrHeightSum : "+bodyTrHeightSum});
//$.nony.printLog({message:"$(this).outerHeight() : "+$(this).outerHeight()});
//$.nony.printLog({message:"$(this).actual() : "+$(this).actual("height")});
				thisHeight = bodyTrHeightSum;
			} else {
//$.nony.printLog({message:"bodyTrHeightSum : "+bodyTrHeightSum});
//$.nony.printLog({message:"$(this).outerHeight() : "+$(this).outerHeight()});
//$.nony.printLog({message:"$(this).actual() : "+$(this).actual("height")});
				thisHeight = $(this).outerHeight();
			}

			// width or height of the base element less than 0 means it is invisible
			baseWidth = (baseWidth <= 0) ? $baseElement.actual("width") : baseWidth;
			baseHeight = (baseHeight <= 0) ? $baseElement.actual("height") : baseHeight;

			// if the element is not visible width=100 and height=0(ie) or -4(ff)
			if (!$(this).is(":visible")) {
				thisWidth = $(this).actual("width");
				thisHeight = $(this).actual("height");
			}

			baseWidth = (baseWidth - ($.nony.getCssAttributeNumber($baseElement, "margin-left") + $.nony.getCssAttributeNumber($baseElement, "margin-right") +
				$.nony.getCssAttributeNumber($baseElement, "padding-left") + $.nony.getCssAttributeNumber($baseElement, "padding-right") +
				$.nony.getCssAttributeNumber($baseElement, "border-left") + $.nony.getCssAttributeNumber($baseElement, "border-right")
			));

			baseWidth = (baseWidth - ($.nony.getCssAttributeNumber($closestElement, "margin-left") + $.nony.getCssAttributeNumber($closestElement, "margin-right") +
				$.nony.getCssAttributeNumber($closestElement, "padding-left") + $.nony.getCssAttributeNumber($closestElement, "padding-right") +
				$.nony.getCssAttributeNumber($closestElement, "border-left") + $.nony.getCssAttributeNumber($closestElement, "border-right")
			));

			baseHeight = (baseHeight - ($.nony.getCssAttributeNumber($baseElement, "margin-top") + $.nony.getCssAttributeNumber($baseElement, "margin-bottom") +
				$.nony.getCssAttributeNumber($baseElement, "padding-top") + $.nony.getCssAttributeNumber($baseElement, "padding-bottom") +
				$.nony.getCssAttributeNumber($baseElement, "border-top") + $.nony.getCssAttributeNumber($baseElement, "border-bottom")
			));

			baseHeight = (baseHeight - ($.nony.getCssAttributeNumber($closestElement, "margin-top") + $.nony.getCssAttributeNumber($closestElement, "margin-bottom") +
				$.nony.getCssAttributeNumber($closestElement, "padding-top") + $.nony.getCssAttributeNumber($closestElement, "padding-bottom") +
				$.nony.getCssAttributeNumber($closestElement, "border-top") + $.nony.getCssAttributeNumber($closestElement, "border-bottom")
			));

			/*!
			 * height & scrollbar - width is defined by scrollbar width
			 */
			$(this).find("thead").find("tr").each(function(index) {
				var height = ($(this).outerHeight() > 0) ? $(this).outerHeight() : $(this).actual("height");
				tHeaderHeight += (options.headerHeight || height);
			});

			$(this).find("tfoot").find("tr").each(function(index) {
				var height = ($(this).outerHeight() > 0) ? $(this).outerHeight() : $(this).actual("height");
				tFooterHeight += options.footerHeight || height;
			});

			captionHeight = $(this).find("caption").outerHeight();

			$("#"+baseDivElementId+" > div").each(function(index) {
				if (($(this).css("display") != "none") && ($(this).attr("id") != "divDataArea")) {
					heightSum += $(this).outerHeight();
				}
			});
//$.nony.printLog({message:"thisHeight : "+thisHeight});
//$.nony.printLog({message:"baseHeight : "+baseHeight});
//$.nony.printLog({message:"bodyTrHeightSum : "+bodyTrHeightSum});

			// height adjustment
			if (commonJs.browser.FireFox) {
				if ($.nony.isEmpty(options.attachedPagingArea) || options.attachedPagingArea == false) {
					if (baseHeight > 749) {
						baseHeight = (baseHeight - 7);
						heightAdjust = 4;
					} else {
						baseHeight = (baseHeight - 7);
						heightAdjust = 0;
					}
				} else {
					if (baseHeight > 749) {
						baseHeight = (baseHeight - 13);
						heightAdjust = 0;
					} else {
						baseHeight = (baseHeight - 12);
						heightAdjust = 0;
					}
				}
			} else {
				if ($.nony.isEmpty(options.attachedPagingArea) || options.attachedPagingArea == false) {
					if (baseHeight > 785) {
						baseHeight = (baseHeight - 3);
						heightAdjust = 6;
					} else {
						baseHeight = (baseHeight - 3);
						heightAdjust = 4;
					}
				} else {
					if (baseHeight > 785) {
						baseHeight = (baseHeight - 12);
						heightAdjust = 0;
					} else {
						baseHeight = (baseHeight - 10);
						heightAdjust = 0;
					}
				}
			}

			if ($.nony.isEmpty(options.attachedPagingArea) || options.attachedPagingArea == false) {
				tBodyHeight = (baseHeight - (tHeaderHeight + tFooterHeight + captionHeight + heightSum + heightAdjust));
			} else {
				tBodyHeight = (baseHeight - (tHeaderHeight + tFooterHeight + captionHeight + heightSum + 26 + heightAdjust));
			}

			if (options.attachedPagingArea == true && !$.nony.isEmpty(options.pagingAreaId)) {
				originalHeight = thisHeight;
			} else {
				originalHeight = globalMap.get("originalTableHeight"+thisId) || thisHeight;
			}

			/*!
			 * width - which scroll bar appears
			 */
			var compWidthForScrollbar = 0;
			/*!
			 * Sets compensation value by browser
			 */
			if ($.nony.browser.Chrome) {
				compWidthForScrollbar = 30;
			} else if ($.nony.browser.FireFox) {
				compWidthForScrollbar = 29;
			} else {
				if ($.nony.browser.version <= 11) {
					compWidthForScrollbar = 28;
				} else {
					compWidthForScrollbar = 25;
				}
			}
//alert(baseHeight);
			scrollBarWidth = (baseHeight > (originalHeight + pagingAreaHeight)) ? 0 : compWidthForScrollbar;
//			if (commonJs.browser.FireFox) {
//				scrollBarWidth = (baseHeight > (originalHeight + pagingAreaHeight)) ? 0 : compWidthForScrollbar;
//			} else {
//				scrollBarWidth = (baseHeight > (originalHeight + pagingAreaHeight)) ? 0 : compWidthForScrollbar;
//			}

			if (scrollBarWidth <= 0) {
				$(this).find("tbody").height(thisHeight);
				$(this).removeClass("fixedHeaderTable");
				return;
			}
			// if the window is popup the width should be smaller
			if (scrollBarWidth != 0 && $.nony.isPopup()) {
				baseWidth = parseInt(baseWidth - compWidthForScrollbar);
			}

			// apply width correction value
			widthAdjust = ($.nony.isEmpty(options.widthAdjust)) ? 22 : options.widthAdjust;
			if ($.nony.browser.Chrome) {
				widthAdjust = (widthAdjust + 6);
			} else if ($.nony.browser.FireFox) {
				widthAdjust = (widthAdjust + 1);
			} else {
				if ($.nony.browser.version <= 11) {
					widthAdjust = (widthAdjust + 4);
				} else {
					widthAdjust = (widthAdjust + 5);
				}
			}

			if (scrollBarWidth > 0) {
				baseWidth = (baseWidth - scrollBarWidth - widthAdjust);
			}

			/*!
			 * column header width
			 */
			$(this).find("colgroup").find("col").each(function(index) {
				var headerWidth = 0;
				if ($.nony.isEmpty(options.headerWidth)) {
					headerWidth = $(this).width();
				} else {
					headerWidth = $.nony.replace(options.headerWidth[index], "%", "");
				}

				headerColumnWidth[index] = Math.round(headerWidth * baseWidth / 100);
				colWidthSum += headerColumnWidth[index];

				if (headerColumnWidth[index] == 0) {
					zeroWidthColIndex = index;
				}
			});

			if (zeroWidthColIndex != -1) {
				headerColumnWidth[zeroWidthColIndex] = (baseWidth - colWidthSum - scrollBarWidth);
				colWidthSum += headerColumnWidth[zeroWidthColIndex];
			}

			/*!
			 * set width
			 */
			if (scrollBarWidth > 0) {
				$(this).find("tbody").height(tBodyHeight+"px");
				compWidth = 0;
			} else {
				var tBodyHeight = $(this).find("tbody").outerHeight();

				if ((baseHeight - (tHeaderHeight + tFooterHeight + pagingAreaHeight)) >= tBodyHeight) {
					$(this).find("tbody").height("100%");
				}
				compWidth = 7;
			}

			// thead colspan
			tempForWidth = $(this).find("thead > tr").length;
			if (tempForWidth != 1) {
				$(this).find("thead").find("tr").each(function(index) {
					if ($(this).find("th").length == headerColumnWidth.length) {
						tempForWidth = index;
						return false;
					}
				});
			} else {
				tempForWidth = 0;
			}

			$(this).find("thead").find("tr:eq("+tempForWidth+")").find("th").each(function(index) {
				$(this).width((headerColumnWidth[index] - compWidth)+"px");
			});

			// tfoot colspan
			tempForWidth = $(this).find("tfoot > tr").length;
			if (tempForWidth != 1) {
				$(this).find("tfoot").find("tr").each(function(index) {
					if ($(this).find("td").length == headerColumnWidth.length) {
						tempForWidth = index;
						return false;
					}
				});
			} else {
				tempForWidth = 0;
			}

			$(this).find("tfoot").find("tr:eq("+tempForWidth+")").find("td").each(function(index) {
				$(this).width((headerColumnWidth[index] - compWidth)+"px");
			});

			$(this).find("tbody").find("tr:first").find("td").each(function(index) {
				if ($.nony.isEmpty($(this).attr("colspan"))) {
					$(this).width((headerColumnWidth[index] - compWidth)+"px");
				} else {
					$(this).width(baseWidth+"px");
				}
			});

			// caption
			if (scrollBarWidth > 0) {
				var $caption = $(this).find("caption"), widthForCaption = 0;

				if ($caption.length > 0) {
					widthForCaption = $.nony.getCssAttributeNumber($caption, "padding-left") + $.nony.getCssAttributeNumber($caption, "padding-right") +
									  $.nony.getCssAttributeNumber($caption, "border-left") + $.nony.getCssAttributeNumber($caption, "border-right");
					$(this).find("caption").width((baseWidth + scrollBarWidth - widthForCaption)+"px");
				}
			}

			/*
			 * set variables into global map
			 */
			globalMap.put("headerColumnWidth"+thisId, headerColumnWidth);
			globalMap.put("originalTableHeight"+thisId, originalHeight);

			if (!globalMap.get("isfixedHeaderTableFuntionRegisteredInResizeEvent"+thisId)) {
				$(window).resize(function() {
					setTimeout(function() {
						$("#"+thisId).fixedHeaderTable(options);
					}, 10);
				});
			}

			globalMap.put("isfixedHeaderTableFuntionRegisteredInResizeEvent"+thisId, true);
		});
	};
})(jQuery);