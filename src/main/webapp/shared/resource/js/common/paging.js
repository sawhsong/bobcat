/**
* Pagination JavaScript
* 	- Returns string to render pagination elements
*/
var paging = {
	getPagingHtml : (params) => {
		var html = "";

		if (params.isPageable) {
			var className = "", readOnly = "", event = "", jsParamString = "";
			var arrMaxRowsPerPageSelect = jsconfig.get("maxRowsPerPageArray").split(jsconfig.get("dataDelimiter"));
			var maxRowsPerPage = $.nony.toNumber($("#selMaxRowsPerPageSelectForPagenation").val());
			var pageNumsPerPage = ($.nony.isPopup()) ? 5 : parseInt(jsconfig.get("pageNumsPerPage"));

			if (maxRowsPerPage <= 0) {
				maxRowsPerPage = parseInt(jsconfig.get("maxRowsPerPage"));
			}

			var viewPageCount = 1;
			var totalPages = 0;
			var totalPos = 0, currentPos = 0, prevPage = 0, nextPage = 0, temp = 0;

			totalPages = parseInt((($.nony.toNumber(params.totalResultRows) - 1) / maxRowsPerPage) + 1);
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

			jsParamString = "'"+params.formId+"', '"+params.formAction+"', '"+params.script+"'";

			html += "<div id=\"divPagination\">";
			html += "<table id=\"tblPagination\">";
			html += "<tr>";

			if (!$.nony.isEmpty(params.pagingAlign)) {
				if ("left" == $.nony.lowerCase(params.pagingAlign)) {
					html += "<td class=\"tdPaginationLeft\">";
				} else if ("center" == $.nony.lowerCase(params.pagingAlign)) {
					html += "<td class=\"tdPaginationLeft\"></td>";
					html += "<td class=\"tdPaginationCenter\">";
				} else {
					if ($.nony.isPopup()) {
						html += "<td class=\"tdPaginationLeft\">";
					} else if ($.nony.isTabFrame()) {
						html += "<td class=\"tdPaginationLeft\"></td>";
						html += "<td class=\"tdPaginationCenter\">";
					} else {
						html += "<td class=\"tdPaginationLeft\"></td>";
						html += "<td class=\"tdPaginationCenter\">";
					}
				}
			} else {
				if ($.nony.isPopup()) {
					html += "<td class=\"tdPaginationLeft\">";
				} else if ($.nony.isTabFrame()) {
					html += "<td class=\"tdPaginationLeft\"></td>";
					html += "<td class=\"tdPaginationCenter\">";
				} else {
					html += "<td class=\"tdPaginationLeft\"></td>";
					html += "<td class=\"tdPaginationCenter\">";
				}
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

			if (params.totalResultRows <= 0) {
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
//				html += "&nbsp; / "+$.nony.getNumberMask(totalPages, "#,###")+" Pages";
			html += "&nbsp; / "+$.nony.getNumberMask(totalPages, "#,###");
			html += "<span class=\"spanPaginationBreaker\"></span>";

			if (params.totalResultRows <= 0) {
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
//				html += "<div class=\"paginationDescriptor\">&nbsp;Rows";
			html += "<div class=\"paginationDescriptor\">";
			html += "<span class=\"spanPaginationBreaker\"></span>";

			if (params.totalResultRows <= 0) {
				html += $.nony.getNumberMask(0, "#,##0")+" - ";
			} else {
				html += $.nony.getNumberMask((((currentPage - 1) * maxRowsPerPage) + 1), "#,##0")+" - ";
			}
//				html += $.nony.getNumberMask(((rowCountForDisplay > params.totalResultRows) ? params.totalResultRows : rowCountForDisplay), "#,##0")+" / "+$.nony.getNumberMask(params.totalResultRows, "#,##0")+" Items";
			html += $.nony.getNumberMask(((rowCountForDisplay > params.totalResultRows) ? params.totalResultRows : rowCountForDisplay), "#,##0")+" / "+$.nony.getNumberMask(params.totalResultRows, "#,##0");
			html += "</div>";
			html += "</div>";
			html += "</td>";
			html += "</tr>";
			html += "</table>";
			html += "</div>";
		} else {
			if (params.displayRowCount) {
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
				html += "<td class=\"tdPaginationRight\" style=\"color:#337AB7;padding:6px 0px 2px 0px;text-align:right;font-weight:bold;\">";
				html += $.nony.getNumberMask(params.totalResultRows, "#,##0")+" Rows Displayed";
				html += "</td>";
				html += "</tr>";
				html += "</table>";
				html += "</div>";
			}
		}

		return html;
	}
};