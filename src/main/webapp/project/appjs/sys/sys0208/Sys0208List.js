/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sys0208List.js
 *************************************************************************************************/
var popup = null;
var searchResultDataCount = 0;

$(function() {
	/*!
	 * event
	 */
	$("#btnNew").click(function(event) {
		openPopup({mode:"Insert"});
	});

	$("#btnDelete").click(function(event) {
		doDelete();
	});

	$("#btnClear").click(function(event) {
		commonJs.clearSearchCriteria();
	});

	$("#icnCheck").click(function(event) {
		commonJs.toggleCheckboxes("chkForDel");
	});

	$("#orgName").blur(function() {
		if (commonJs.isEmpty($(this).val())) {
			$("#orgId").val("");
		}
	});

	$(document).keydown(function(event) {
		var code = event.keyCode || event.which, element = event.target;

		if (code == 13) {
			if ($(element).is("[name=userName]") || $(element).is("[name=loginId]") || $(element).is("[name=orgName]")) {
				doSearch();
			}
		}

		if (code == 9) {}
	});

	setActionButtonContextMenu = function() {
		var ctxMenu = [{
			name:sys.sys0208.caption.auth,
			img:"fa-sitemap",
			fun:function() {openPopup({mode:"UpdateAuthGroup"});}
		}, {
			name:sys.sys0208.caption.status,
			img:"fa-sliders",
			fun:function() {openPopup({mode:"UpdateUserStatus"});}
		}, {
			name:sys.sys0208.caption.active,
			img:"fa-adjust",
			fun:function() {openPopup({mode:"UpdateActiveStatus"});}
		}];

		$("#btnAction").contextMenu(ctxMenu, {
			classPrefix:com.constants.ctxClassPrefixButton,
			effectDuration:300,
			effect:"slide",
			borderRadius:"bottom 4px",
			displayAround:"trigger",
			position:"bottom",
			horAdjust:-140
		});
	};

	/*!
	 * process
	 */
	doSearch = function() {
		commonJs.showProcMessageOnElement("divScrollablePanel");

		commonJs.doSearch({
			url:"/sys/0208/getList.do",
			onSuccess:renderDataGridTable
		});
	};

	renderDataGridTable = function(result) {
		var ds = result.dataSet;
		var html = "";

		searchResultDataCount = ds.getRowCnt();
		$("#tblGridBody").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr();

				var uiChk = new UiCheckbox();
				uiChk.setId("chkForDel").setName("chkForDel").setValue(ds.getValue(i, "USER_ID"));
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(uiChk));

				var uiAnc = new UiAnchor();
				uiAnc.setText(ds.getValue(i, "USER_NAME")).setScript("getEdit('"+ds.getValue(i, "USER_ID")+"')");
				gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAnc));

				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "LOGIN_ID")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "ORG_NAME")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "EMAIL")));
				gridTr.addChild(new UiGridTd().addClassName("Rt").setText(ds.getValue(i, "BANK_ACCNT_CNT")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "AUTH_GROUP_NAME")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "IS_ACTIVE")));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(commonJs.nvl(ds.getValue(i, "UPDATE_DATE"), ds.getValue(i, "INSERT_DATE"))));

				var iconAction = new UiIcon();
				iconAction.setId("icnAction").setName("icnAction").addClassName("fa-tasks fa-lg").addAttribute("userId:"+ds.getValue(i, "USER_ID")).setScript("doAction(this)");
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(iconAction));

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:10").setText(com.message.I001));
			html += gridTr.toHtmlString();
		}

		$("#tblGridBody").append($(html));

		$("#tblGrid").fixedHeaderTable({
			attachTo:$("#divDataArea"),
			pagingArea:$("#divPagingArea"),
			isPageable:true,
			isFilter:false,
			totalResultRows:result.totalResultRows,
			script:"doSearch"
		});

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenu.commonSimpleAction);
		});

		commonJs.bindToggleTrBackgoundWithCheckbox($("[name=chkForDel]"));
		commonJs.hideProcMessageOnElement("divScrollablePanel");
	};

	getEdit = function(userId) {
		openPopup({
			mode:"Update",
			userId:userId
		});
	};

	openPopup = function(param) {
		var url = "", header = "", width = 0, height = 0;

		if (param.mode == "Insert" || param.mode == "Update") {
			url = "/sys/0208/getEdit.do";
			header = com.header.popHeaderEdit;
			width = 1100, height = 590;
		} else if (param.mode == "UpdateAuthGroup") {
			url = "/sys/0208/getActionContextMenu.do";
			header = sys.sys0208.caption.auth;
			width = 340; height = 366;
		} else if (param.mode == "UpdateUserStatus") {
			url = "/sys/0208/getActionContextMenu.do";
			header = sys.sys0208.caption.status;
			width = 320; height = 220;
		} else if (param.mode == "UpdateActiveStatus") {
			url = "/sys/0208/getActionContextMenu.do";
			header = sys.sys0208.caption.active;
			width = 320; height = 180;
		}

		if (url.indexOf("getActionContextMenu") != -1) {
			if (commonJs.getCountChecked("chkForDel") == 0) {
				commonJs.warn(com.message.I902);
				return;
			}
		}

		var popParam = {
			popupId:"user"+param.mode,
			url:url,
			data:param,
			header:header,
			blind:true,
			width:width,
			height:height
		};

		popup = commonJs.openPopup(popParam);
	};

	doDelete = function() {
		if (commonJs.getCountChecked("chkForDel") == 0) {
			commonJs.warn(com.message.I902);
			return;
		}

		commonJs.doDelete({
			url:"/sys/0208/exeDelete.do",
			callback:doSearch
		});
	};

	doAction = function(img) {
		var userId = $(img).attr("userId");

		$("input:checkbox[name=chkForDel]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == userId) {
				$(this).prop("checked", true);
				$(this).parents("tr").addClass("checkedTr");
			} else {
				$(this).prop("checked", false);
				$(this).parents("tr").removeClass("checkedTr");
			}
		});

		ctxMenu.commonSimpleAction[0].fun = function() {openPopup({mode:"Update", userId:userId});};
		ctxMenu.commonSimpleAction[1].fun = function() {doDelete();};

		$(img).contextMenu(ctxMenu.commonSimpleAction, {
			classPrefix:com.constants.ctxClassPrefixGrid,
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0,
			verAdjust:2
		});
	};

	exeExport = function(menuObject) {
		$("[name=fileType]").remove();
		$("[name=dataRange]").remove();

		if (searchResultDataCount <= 0) {
			commonJs.warn(com.message.I001);
			return;
		}

		commonJs.doExport({
			url:"/sys/0208/exeExport.do",
			data:commonJs.serialiseObject($("#divSearchCriteriaArea")),
			menuObject:menuObject
		});
	};

	exeActionContextMenu = function(param) {
		commonJs.doSimpleProcess({
			url:"/sys/0208/exeActionContextMenu.do",
			data:param,
			onSuccess:doSearch
		});
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		setActionButtonContextMenu();
//		commonJs.setExportButtonContextMenu($("#btnExport"));

		commonJs.setEvent("click", [$("#btnSearch")], doSearch);
		commonJs.setEvent("change", [$("#authGroup"), $("#userStatus"), $("#isActive")], doSearch);

		commonJs.setAutoComplete($("#userName"), {
			method:"getUserName",
			label:"user_name",
			value:"user_name",
			focus: function(event, ui) {
				$("#userName").val(ui.item.label);
				return false;
			},
			select:function(event, ui) {
				doSearch();
			}
		});

		commonJs.setAutoComplete($("#loginId"), {
			method:"getLoginId",
			label:"login_id",
			value:"login_id",
			focus: function(event, ui) {
				$("#loginId").val(ui.item.label);
				return false;
			},
			select:function(event, ui) {
				doSearch();
			}
		});

		commonJs.setAutoComplete($("#orgName"), {
			method:"getOrgByIdOrName",
			label:"legal_name_abn",
			value:"org_id",
			minLength:2,
			focus: function(event, ui) {
				$("#orgId").val(ui.item.value);
				$("#orgName").val(ui.item.label);
				return false;
			},
			change:function(event, ui) {
				if (commonJs.isEmpty($("#orgName").val())) {
					$("#orgId").val("");
					$("#orgName").val("");
				}
			},
			select:function(event, ui) {
				$("#orgId").val(ui.item.value);
				$("#orgName").val(ui.item.label);
				doSearch();
				return false;
			}
		});

		doSearch();
	});
});