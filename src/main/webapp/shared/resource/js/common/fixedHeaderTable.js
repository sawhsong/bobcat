/**
 * tbody scrollable table with fixed header
 * Usage : in $(window).load() event
 * 		 : selector must have an ID
 * 			$("#tblFixedHeaderTable").fixedHeaderTable({
 * 				attachTo:$("divDataArea"),				// [mandatory : parent jquery object which holds this table - usually this would be 'divDataArea']
 * 				pagingArea:$("#divPagingArea")			// [mandatory : pagination area jquery object]
 * 				isPageable:true/false					// [optional : true : displays paging area component, false : displays nothing]
 * 				displayRowCount:true					// [optional : true : displays total row count even if isPageable is false, false : displays nothing]
 * 				attachToHeight:750,						// [optional : height of parent jquery object which holds this table]
 * 
 * 				formId:"formId"							// [optional : for pagination area component - form Id]
 * 				formAction:"formAction					// [optional : for pagination area component - form action]
 * 				script:"doSearch"						// [conditional mandatory : for pagination area component - if paging is attached this is mandatory]
 * 				totalResultRows:result.totalResultRows	// [conditional mandatory : for pagination area component - if paging is attached this is mandatory]
 * 			});
 */
/*
(function ($) {
    $.fn.fixHeader = function () {
        return this.each(function () {
            var $table = $(this);
            var $sp = $table.scrollParent();
            var tableOffset = $table.position().top;
            var $tableFixed = $("<table />")
                .prop('class', $table.prop('class'))
                .css({ position: "fixed", "table-layout": "fixed", display: "none", "margin-top": "0px" });
            $table.before($tableFixed);
            $tableFixed.append($table.find("thead").clone());

            $sp.bind("scroll", function () {
                var offset = $(this).scrollTop();

                if (offset > tableOffset && $tableFixed.is(":hidden")) {
                    $tableFixed.show();
                    var p = $table.position();
                    var offset = $sp.offset();

                    //Set the left and width to match the source table and the top to match the scroll parent
                    $tableFixed.css({ top: (offset ? offset.top : 0) + "px", }).width($table.width());

                    //Set the width of each column to match the source table
                    $.each($table.find('th, td'), function (i, th) {
                        $($tableFixed.find('th, td')[i]).width($(th).width());
                    });

                }
                else if (offset <= tableOffset && !$tableFixed.is(":hidden")) {
                    $tableFixed.hide();
                }
            });
        });
    };
})(jQuery);
 */
(function($) {
	$.fn.fixedHeaderTable = function(options) {
		return this.each(function() {
			if ($(options.attachTo).length <= 0) {
				throw new Error("AttachTo" + framework.messages.mandatory);
				return;
			}

			if ($(options.pagingArea).length <= 0) {
				throw new Error("PagingArea" + framework.messages.mandatory);
				return;
			}

			var $scrollablePanel, attachToHeight = 0, isScrollbar = false, heightAdjustment = 0;
				isPopup = $.nony.isPopup();

			$(options.attachTo).css("overflow", "auto");

			/**
			 * Paging Area
			 */
			if (options.isPageable) {
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

				$(options.pagingArea).html(html);

				$("#selMaxRowsPerPageSelectForPagenation").selectpicker({width:"52px"});
			} else {
				var html = "";

				if (options.displayRowCount) {
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
					html += "<td class=\"tdPaginationRight\" style=\"text-align:right;padding:4px 0px 2px 0px;\">";
					html += $.nony.getNumberMask(options.totalResultRows, "#,##0")+" Rows Displayed";
					html += "</td>";
					html += "</tr>";
					html += "</table>";
					html += "</div>";

					$(options.pagingArea).html(html);
				}
			} // Paging Area End

			pagingAreaHeight = $(options.pagingArea).outerHeight();

			if (isPopup) {
				$scrollablePanel = $("#divScrollablePanelPopup");
			} else {
				$scrollablePanel = $("#divScrollablePanel");
			}

			attachToHeight = options.attachToHeight || $scrollablePanel.height();

			if ($.nony.browser.Chrome) {heightAdjustment = 6;}
			else if ($.nony.browser.FireFox) {heightAdjustment = 6;}
			else {heightAdjustment = 6;}
			$(options.attachTo).height(attachToHeight - (pagingAreaHeight + heightAdjustment));
//console.log("$scrollablePanel.height() : "+$scrollablePanel.height());
//console.log("attachToHeight : "+attachToHeight);
//console.log("pagingAreaHeight : "+pagingAreaHeight);
//console.log("$(options.attachTo).height() : "+$(options.attachTo).height());
//console.log("$(this).height() : "+$(this).height());



//			if () {
//				isScrollbar = true;
//			}
		});
	};
})(jQuery);