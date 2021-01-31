/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sys0208InsertPop.js
 *************************************************************************************************/
var delimiter = jsconfig.get("dataDelimiter");
jsconfig.put("useJqSelectmenu", false);

$(function() {
	/*!
	 * event
	 */
	$("#btnSave").click(function(event) {
		var selectedTabIndex = commonJs.getSelectedTabIndex($("#tabCategory"));

		if (selectedTabIndex == 0) {
			saveUserDetail();
		} else if (selectedTabIndex == 1) {
			saveBankAccount();
		}

//		var fileValue = $("#photoPath").val();

//		if (commonJs.doValidate("fmDefault")) {
//			if (!commonJs.isEmpty(fileValue)) {
//				fileValue = fileValue.substring(fileValue.lastIndexOf(".")+1);
//				if (!(fileValue.toLowerCase() == "png" || fileValue.toLowerCase() == "jpg" || fileValue.toLowerCase() == "gif" || fileValue.toLowerCase() == "jpeg")) {
//					commonJs.doValidatorMessage($("#photoPath"), "notUploadable");
//					return;
//				}
//			}
//
//			$("#fmDefault").attr("enctype", "multipart/form-data");
//
//			commonJs.confirm({
//				contents:com.message.Q001,
//				buttons:[{
//					caption:com.caption.yes,
//					callback:function() {
//						commonJs.doSubmit({
//							form:"fmDefault",
//							action:"/sys/0208/exeInsert.do"
//						});
//					}
//				}, {
//					caption:com.caption.no,
//					callback:function() {
//					}
//				}]
//			});
//		}
	});

	$("#icnOrgSearch").click(function(event) {
		parent.popupLookup = parent.commonJs.openPopup({
			popupId:"OrganisationLookup",
			url:"/common/lookup/getDefault.do",
			data:{
				lookupType:"organisationName",
				keyFieldId:"orgId",
				valueFieldId:"orgName",
				popupToSetValue:"parent.popup",
				popupName:"parent.popupLookup",
				lookupValue:$("#orgName").val()
			},
			header:sys.sys0208.header.popOrgLookup,
			width:880,
			height:680
		});
	});

	$("#btnAddBankAccnt").click(function(event) {
		var elem = $("#liDummy").clone(), elemId = $(elem).attr("id");

		$(elem).css("display", "block").appendTo($("#ulDetailHolder"));

		$("#ulDetailHolder").find(".dummyDetail").each(function(groupIndex) {
			$(this).attr("index", groupIndex).attr("id", elemId+delimiter+groupIndex);

			$(this).find("i").each(function(index) {
				var id = $(this).attr("id"), id = (id.indexOf(delimiter) != -1) ? id.substring(0, id.indexOf(delimiter)) : id;
				$(this).attr("index", groupIndex).attr("id", id+delimiter+groupIndex);
			});

			$(this).find(".deleteButton").each(function(index) {
				var id = $(this).attr("id"), id = (id.indexOf(delimiter) != -1) ? id.substring(0, id.indexOf(delimiter)) : id;
				$(this).attr("index", groupIndex).attr("id", id+delimiter+groupIndex);
			});

			$(this).find("input, select").each(function(index) {
				var id = $(this).attr("id"), name = $(this).attr("name");

				if (!commonJs.isEmpty(id)) {id = (id.indexOf(delimiter) != -1) ? id.substring(0, id.indexOf(delimiter)) : id;}
				else {id = "";}

				if (!commonJs.isEmpty(name)) {name = (name.indexOf(delimiter) != -1) ? name.substring(0, name.indexOf(delimiter)) : name;}
				else {name = "";}

				$(this).attr("id", id+delimiter+groupIndex).attr("name", name+delimiter+groupIndex);

				if ($(this).is("select")) {
					setSelectBoxes($(this));
				}
			});
		});
	});

	$(document).keydown(function(event) {
		var code = event.keyCode || event.which, element = event.target;

		if (code == 13) {
		}

		if (code == 9) {
		}
	});

	/*!
	 * process
	 */
	closeWindow = function() {
		parent.popup.close();
	};

	toggleTabStatus = function() {
		if (commonJs.isBlank($("#userId").val())) {
			$("#tabCategory li").each(function(index) {
				if (index == 1) {
					$(this).removeClass("active").addClass("disabled").find("a").unbind("click");
				}
			});
		} else {
			$("#tabCategory li").each(function(index) {
				if (index == 1) {
					$(this).removeClass("disabled").find("a").bind("click");
				}
			});
		}
	};

	setSelectboxForUserDetailTab = function() {
		var options = {};
		$("#div0 select.bootstrapSelect").each(function(index) {
			options.container = "body";
			options.style = $(this).attr("class");
			$(this).selectpicker(options);
		});
	};

	setSelectBoxes = function(jqObj) {
		$(jqObj).selectpicker({
			width:"auto",
			container:"body",
			style:$(jqObj).attr("class")
		});
	};

	/*!
	 * load event (document / window)
	 */
	$(document).click(function(event) {
		var obj = event.target;

		if ($(obj).hasClass("deleteButton") || ($(obj).is("i") && $(obj).parent("th").hasClass("deleteButton"))) {
			$("#ulDetailHolder").find(".dummyDetail").each(function(index) {
				if ($(this).attr("index") == $(obj).attr("index")) {
					$(this).remove();
				}
			});
		}
	});

	$(window).load(function() {
		commonJs.setEvent("click", [$("#btnCloseUserDetail"), $("#btnCloseBankAccnt")], closeWindow);
		toggleTabStatus();
		setSelectboxForUserDetailTab();

		commonJs.setAutoComplete($("#orgName"), {
			method:"getOrgId",
			label:"legal_name",
			value:"org_id",
			focus:function(event, ui) {
				$("#orgName").val(ui.item.label);
				return false;
			},
			select:function(event, ui) {
				$("#orgId").val(ui.item.value);
				$("#orgName").val(ui.item.label);
				return false;
			}
		});
	});
});