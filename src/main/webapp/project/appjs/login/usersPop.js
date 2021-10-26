/**
 * usersPop.js
 */
$(function() {
	var ctxMenuUser = [{
		name:"Login As This User",
		img:"fa-users",
		fun:function() {}
	}];
	/*!
	 * event
	 */
	$("#btnBackToMyAccnt").click(function(event) {
		commonJs.doSimpleProcess({
			url:"/login/removeSessionValuesForAdminTool.do",
			noForm:true,
			data:{},
			onSuccess:function(result) {
				var ds = result.dataSet;

				parent.$("#divUsingUserAs").html("");
				parent.$("#divUsingUserAsBreaker").hide();
				$("#btnClose").trigger("click");
			}
		});
	});

	$("#btnLoginUserAs").click(function(event) {
		loginAs(commonJs.getCheckedValueFromRadio("rdoForSave"));
	});

	$("#btnSearch").click(function(event) {
		doSearch();
	});

	$("#btnClear").click(function(event) {
		commonJs.clearSearchCriteria();
	});

	$("#btnClose").click(function(event) {
		parent.popupUsers.close();
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
	/*!
	 * process
	 */
	doSearch = function() {
		commonJs.showProcMessageOnElement("divScrollablePanelPopup");

		commonJs.doSearch({
			url:"/login/getUserList.do",
			onSuccess:renderDataGrid
		});
	};

	renderDataGrid = function(result) {
		var ds = result.dataSet;
		var html = "";

		$("#tblGridBody").html("");

		if (ds.getRowCnt() > 0) {
			for (var i=0; i<ds.getRowCnt(); i++) {
				var gridTr = new UiGridTr();

				var iconAction = new UiIcon();
				iconAction.setId("icnAction").setName("icnAction").addClassName("fa-ellipsis-h fa-lg").addAttribute("userId:"+ds.getValue(i, "USER_ID")).setScript("doAction(this)");
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(iconAction));
				gridTr.addChild(new UiGridTd().addClassName("Ct").addChild(new UiRadio().setName("rdoForSave").setValue(ds.getValue(i, "USER_ID"))));
				var uiAnc = new UiAnchor();
				uiAnc.setText(ds.getValue(i, "USER_NAME")).setScript("loginAs('"+ds.getValue(i, "USER_ID")+"')");
				gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(uiAnc));
				gridTr.addChild(new UiGridTd().addClassName("Ct").setText(ds.getValue(i, "LOGIN_ID")));
				gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "ORG_NAME")));

				html += gridTr.toHtmlString();
			}
		} else {
			var gridTr = new UiGridTr();

			gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:5").setText(com.message.I001));
			html += gridTr.toHtmlString();
		}

		$("#tblGridBody").append($(html));

		$("#tblGrid").fixedHeaderTable({
			attachTo:$("#divDataArea"),
			pagingArea:$("#divPagingArea"),
			isPageable:true,
			totalResultRows:result.totalResultRows,
			script:"doSearch"
		});

		$("[name=icnAction]").each(function(index) {
			$(this).contextMenu(ctxMenuUser);
		});

		commonJs.bindToggleTrBackgoundWithRadiobox($("[name=rdoForSave]"));
		commonJs.hideProcMessageOnElement("divScrollablePanelPopup");
	};

	loginAs = function(userId) {
		if (commonJs.isBlank(userId)) {
			commonJs.warn("Please select a User.");
			return;
		}

		commonJs.doSimpleProcess({
			url:"/login/setSessionValuesForAdminTool.do",
			noForm:true,
			data:{userId:userId},
			onSuccess:function(result) {
				var ds = result.dataSet;
				var userInfo = "";

				userInfo += "User Name : "+ds.getValue(0, "user_name")+" | ";
				userInfo += "User Login ID : "+ds.getValue(0, "login_id")+" ";
				userInfo += "("+ds.getValue(0, "org_id")+" | ";
				userInfo += ds.getValue(0, "org_name")+" | ";
				userInfo += ds.getValue(0, "abn")+")";

				parent.$("#divUsingUserAs").html(userInfo);
				parent.$("#divUsingUserAsBreaker").show();
				$("#btnClose").trigger("click");
			}
		});
	};

	doAction = function(img) {
		var userId = $(img).attr("userId");

		$("input:radio[name=rdoForSave]").each(function(index) {
			if (!$(this).is(":disabled") && $(this).val() == userId) {
				$(this).click();
			} else {
				if ($(this).is(":checked")) {
					$(this).click();
				}
			}
		});

		ctxMenuUser[0].fun = function() {loginAs(userId);};

		$(img).contextMenu(ctxMenuUser, {
			classPrefix:com.constants.ctxClassPrefixGrid,
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0,
			verAdjust:2
		});
	};
	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
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
			position:{
				my:"right top",
				at:"right bottom",
				collision:"fit flip"
			},
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

		setTimeout(() => {
			doSearch();
		}, 400);
	});
});