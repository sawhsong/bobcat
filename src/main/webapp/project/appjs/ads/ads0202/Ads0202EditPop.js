/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Ads0202EditPop.js
 *************************************************************************************************/
jsconfig.put("useJqSelectmenu", false);
var delimiter = jsconfig.get("dataDelimiter");
var dateTimeFormat = jsconfig.get("dateTimeFormatJs");
var dateFormat = jsconfig.get("dateFormatJs");

$(function() {
	/*!
	 * event
	 */
	$("#btnClose").click(function() {
		parent.popup.close();
		parent.doSearch();
	});

	$("#btnSave").click(function(event) {
		var isValid = true;
		var elementsToCheck = [];
		var detailLength = $("#ulDetailHolder .dummyDetail").length;
		var fileValue = $("#providerLogoPath").val();

		if ("disabled" == $(this).attr("disabled")) {
			return;
		}

		$("#liDummy").find(":input").each(function(index) {
			$(this).removeAttr("mandatory");
			$(this).removeAttr("option");
		});

		if (!commonJs.isEmpty(fileValue)) {
			if (!commonJs.isUploadableImageFile($("#providerLogoPath"), fileValue)) {
				return;
			}
		}

		$("#ulDetailHolder").find(".dummyDetail").each(function(groupIndex) {
			$(this).find(":input").each(function(index) {
				var id = $(this).attr("id"), name = $(this).attr("name");

				if (!commonJs.isEmpty(id)) {id = (id.indexOf(delimiter) != -1) ? id.substring(0, id.indexOf(delimiter)) : id;}
				else {id = "";}

				if (!commonJs.isEmpty(name)) {name = (name.indexOf(delimiter) != -1) ? name.substring(0, name.indexOf(delimiter)) : name;}
				else {name = "";}

				$(this).attr("id", id+delimiter+groupIndex).attr("name", name+delimiter+groupIndex);

				elementsToCheck.push($(this).attr("id"));
			});
		});

		if (!commonJs.doValidate("fmDefault")) {
			isValid = false;
			return;
		}

		if (elementsToCheck != null && elementsToCheck.length > 0 && !commonJs.doValidate(elementsToCheck)) {
			isValid = false;
			return;
		}

		if (isValid) {
			commonJs.doSaveWithFile({
				url:"/ads/0202/doSave.do",
				data:{detailLength:detailLength},
				onSuccess:function(result) {
					var ds = result.dataSet;

					quotationId = ds.getValue(0, "QUOTATION_ID");

					loadMasterInfo();
					loadDetailInfo();
				}
			});
		}
	});

	$("#btnBringMyInfo").click(function() {
		commonJs.showProcMessageOnElement("divFixedPanelPopup");

		setTimeout(function() {
			commonJs.doSimpleProcess({
				url:"/ads/0202/getMyInfo.do",
				noForm:true,
				data:{quotationId:quotationId},
				onSuccess:function(result) {
					var ds = result.dataSet;
					setMyInfo(ds);
				}
			});
		}, 400);
	});

	$("#btnBringOrgInfo").click(function() {
		commonJs.showProcMessageOnElement("divFixedPanelPopup");

		setTimeout(function() {
			commonJs.doSimpleProcess({
				url:"/ads/0202/getOrgInfo.do",
				noForm:true,
				data:{quotationId:quotationId},
				onSuccess:function(result) {
					var ds = result.dataSet;
					setOrgInfo(ds);
				}
			});
		}, 400);
	});

	$("#btnDiscardBasicInfo").click(function() {
		$("#providerOrgId").val("");
		$("#providerOrgName").val("");
		$("#providerName").val("");
		$("#providerAbn").val("");
		$("#providerAcn").val("");
		$("#providerEmail").val("");
		$("#providerTelephone").val("");
		$("#providerMobile").val("");
		$("#providerAddress").val("");
		if ($("#imgLogo").length > 0) {
			$("#imgLogo").remove();
		}
	});

	$("#btnRemoveLogo").click(function() {
		if ($("#imgLogo").length > 0) {
			$("#imgLogo").remove();
		}

		if (!commonJs.isBlank(quotationId)) {
			commonJs.doSimpleProcess({
				url:"/ads/0202/doRemoveLogo.do",
				noForm:true,
				data:{quotationId:quotationId},
				onSuccess:function(result) {
					var ds = result.dataSet;
				}
			});
		}
	});

	$("#icnIssueDate").click(function(event) {
		commonJs.openCalendar(event, "issueDate");
	});

	clearValueOnBlur = function(jqObj) {
		if (jqObj == null) {return;}

		var id = $(jqObj).attr("id");

		if ($.nony.isEmpty($(jqObj).val())) {
			$(jqObj).val("");
		}

		if (id == "providerOrgName") {
			$("#"+id).val("");
		}
	};

	$("#btnAdd").click(function(event) {
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
					setSelectboxForBankAccountTab($(this));
				}

				if (commonJs.contains(name, "rowIndex")) {
					$(this).val(groupIndex + 1);
				}

				if (commonJs.contains(id, "unit") || commonJs.contains(id, "price") || commonJs.contains(id, "amount")) {
					$(this).number(true, 2);
				}
			});
		});

		setGridHeader();
	});

	setGridHeader = function() {
		$("#tblGrid").fixedHeaderTable({
			attachTo:$("#divGridWrapper")
		});
	};

	$(document).keydown(function(event) {
		var code = event.keyCode || event.which, element = event.target;
		if (code == 13) {}
		if (code == 9) {}
	});

	$(document).keyup(function(event) {
		var code = event.keyCode || event.which, element = event.target;
		if (code == 9) {}
		if (code == 13) {}
		onEditDataEntry($(element));
	});
	/*!
	 * process
	 */
	setFieldFormat = function() {
		commonJs.setFieldDateMask("issueDate");
		commonJs.setFieldNumberMask("providerAbn", "99 999 999 999");
		commonJs.setFieldNumberMask("providerAcn", "999 999 999");
		commonJs.setFieldNumberMask("providerTelephone", "99 9999 9999");
		commonJs.setFieldNumberMask("providerMobile", "9999 999 999");
		commonJs.setFieldNumberMask("clientTelephone", "99 9999 9999");
		commonJs.setFieldNumberMask("clientMobile", "9999 999 999");
		$("#netAmt").number(true, 2);
		$("#gstAmt").number(true, 2);
		$("#totalAmt").number(true, 2);
		$("#unit").number(true, 2);
		$("#price").number(true, 2);
		$("#amount").number(true, 2);
	};

	loadDefaultMyInfo = function() {
		if (commonJs.isBlank(quotationId)) {
			$("#btnBringMyInfo").trigger("click");
		}
	};

	setMyInfo = function(ds) {
		$("#providerName").val(ds.getValue(0, "USER_NAME"));
		$("#providerEmail").val(ds.getValue(0, "EMAIL"));
		$("#providerTelephone").val(commonJs.getFormatString(ds.getValue(0, "TEL_NUMBER"), "?? ???? ????"));
		$("#providerMobile").val(commonJs.getFormatString(ds.getValue(0, "MOBILE_NUMBER"), "???? ??? ???"));

		if ($("#imgLogo").length > 0) {
			$("#imgLogo").remove();
		}

		commonJs.hideProcMessageOnElement("divFixedPanelPopup");
	};

	setOrgInfo = function(ds) {
		var logoPath = ds.getValue(0, "LOGO_PATH");

		$("#providerOrgId").val(ds.getValue(0, "ORG_ID"));
		$("#providerOrgName").val(ds.getValue(0, "LEGAL_NAME"));
		$("#providerName").val(ds.getValue(0, "LEGAL_NAME"));
		$("#providerAbn").val(commonJs.getFormatString(ds.getValue(0, "ABN"), "?? ??? ??? ???"));
		$("#providerAcn").val(commonJs.getFormatString(ds.getValue(0, "ACN"), "??? ??? ???"));
		$("#providerEmail").val(ds.getValue(0, "EMAIL"));
		$("#providerTelephone").val(commonJs.getFormatString(ds.getValue(0, "TEL_NUMBER"), "?? ???? ????"));
		$("#providerMobile").val(commonJs.getFormatString(ds.getValue(0, "MOBILE_NUMBER"), "???? ??? ???"));
		$("#providerAddress").val(ds.getValue(0, "ADDRESS"));

		if (!commonJs.contains(logoPath, "/DefaultLogo.png")) {
			if ($("#imgLogo").length > 0) {
				$("#imgLogo").remove();
			}
			$("#tdLogo").append(commonJs.getUiImage({
				id:"imgLogo",
				src:logoPath,
				idDisabled:true,
				style:"width:250px;height:80px;"
			}));
		}

		commonJs.hideProcMessageOnElement("divFixedPanelPopup");
	};

	loadQuotationNumber = function() {
		if (commonJs.isBlank(quotationId)) {
			commonJs.doSimpleProcess({
				url:"/ads/0202/getQuotationNumber.do",
				noForm:true,
				data:{quotationId:quotationId},
				onSuccess:function(result) {
					var ds = result.dataSet;
					$("#quotationNumber").val(ds.getValue(0, "Number"));
				}
			});
		}
	};

	loadMasterInfo = function() {
		if (!commonJs.isBlank(quotationId)) {
			commonJs.showProcMessageOnElement("divFixedPanelPopup");

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
			}, 300);
		}
	};

	setQuotationMasterInfo = function (ds) {
		var logoPath = "";

		for (var i=0; i<ds.getColumnCnt(); i++) {
			var eleId = commonJs.toCamelCase(ds.getName(i));

			if (commonJs.isIn(eleId, ["providerLogoPath"])) {
				logoPath = ds.getValue(0, "PROVIDER_LOGO_PATH");
				continue;
			}

			if (commonJs.isIn(eleId, ["description"])) {
				$("#descriptionM").val(ds.getValue(0, ds.getName(i)));
				continue;
			}

			if (commonJs.isIn(eleId, ["issueDate"])) {
				$("#issueDate").val(commonJs.getDateTimeMask(ds.getValue(0, ds.getName(i)), dateFormat));
				continue;
			}

			$("#"+eleId).val(ds.getValue(0, ds.getName(i)));
		}

		if (commonJs.isNotBlank(logoPath)) {
			if ($("#imgLogo").length > 0) {
				$("#imgLogo").remove();
			}

			$("#tdLogo").append(commonJs.getUiImage({
				id:"imgLogo",
				src:logoPath,
				idDisabled:true,
				style:"width:250px;height:80px;"
			}));
		}

		setFieldFormat();

		commonJs.hideProcMessageOnElement("divFixedPanelPopup");
	};

	loadDetailInfo = function() {
		if (!commonJs.isBlank(quotationId)) {
			commonJs.showProcMessageOnElement("divDataArea");

			setTimeout(function() {
				commonJs.doSimpleProcess({
					url:"/ads/0202/getQuotationDetailInfo.do",
					noForm:true,
					data:{quotationId:quotationId},
					onSuccess:function(result) {
						var ds = result.dataSet;
						setQuotationDetailInfo(ds);
					}
				});
			}, 300);
		}
	};

	setQuotationDetailInfo = function (ds) {
		$("#ulDetailHolder").html("");

		for (var i=0; i<ds.getRowCnt(); i++) {
			var rowIdx = delimiter+i;

			$("#btnAdd").trigger("click");

			$("[name=quotationDId"+rowIdx+"]").val(ds.getValue(i, "QUOTATION_D_ID"));
			$("[name=rowIndex"+rowIdx+"]").val(ds.getValue(i, "ROW_INDEX"));
			$("[name=unit"+rowIdx+"]").val(ds.getValue(i, "UNIT"));
			$("[name=price"+rowIdx+"]").val(ds.getValue(i, "AMT_PER_UNIT"));
			$("[name=amount"+rowIdx+"]").val(ds.getValue(i, "ITEM_AMT"));
			$("[name=descriptionD"+rowIdx+"]").val(ds.getValue(i, "DESCRIPTION"));
		}

		commonJs.hideProcMessageOnElement("divDataArea");
	};

	setSortable = function() {
		$("#ulDetailHolder").sortable({
			axis:"y",
			handle:".dragHandler",
			stop:function() {
				$("#ulDetailHolder").find(".dummyDetail").each(function(groupIndex) {
					$(this).find("input").each(function(index) {
						var id = $(this).attr("id"), name = $(this).attr("name");

						$(this).attr("id", id+delimiter+groupIndex).attr("name", name+delimiter+groupIndex);

						if (commonJs.contains(name, "rowIndex")) {
							$(this).val(groupIndex + 1);
						}
					});
				});
			}
		});

		$("#ulDetailHolder").disableSelection();
	};

	onEditDataEntry = function(jqObj) {
		var name = $(jqObj).attr("name");

		if (commonJs.contains(name, "unit") || commonJs.contains(name, "price")) {
			var suffix = name.substring(name.indexOf(delimiter));
			var unit = commonJs.toNumber($("#unit"+suffix).val());
			var price = commonJs.toNumber($("#price"+suffix).val());
			var netAmt = 0;

			$("#amount"+suffix).val(unit * price);

			$("#ulDetailHolder").find(".dummyDetail").each(function(groupIndex) {
				$(this).find("input").each(function(index) {
					var id = $(this).attr("id"), name = $(this).attr("name");
					if (commonJs.contains(name, "amount")) {
						netAmt += commonJs.toNumber($(this).val());
					}
				});
			});

			$("#netAmt").val(netAmt);
			$("#totalAmt").val(commonJs.toNumber($("#netAmt").val()) + commonJs.toNumber($("#gstAmt").val()));
		}

		if (commonJs.contains(name, "netAmt") || commonJs.contains(name, "gstAmt")) {
			var netAmt = commonJs.toNumber($("#netAmt").val());
			var gstAmt = commonJs.toNumber($("#gstAmt").val());
			$("#totalAmt").val(netAmt + gstAmt);
		}
	};
	/*!
	 * load event (document / window)
	 */
	
	$(document).click(function(event) {
		var obj = event.target;

		if ($(obj).is("i") && $(obj).parent("th").hasClass("deleteButton")) {
			$("#ulDetailHolder").find(".dummyDetail").each(function(index) {
				if ($(this).attr("index") == $(obj).attr("index")) {
					$(this).remove();
				}

				setGridHeader();
			});
		}

		if ($(obj).is($("input:text")) && $(obj).hasClass("txtEn")) {
			$(obj).select();
		}
	});

	$(window).load(function() {
		commonJs.setEvent("blur", [$("#providerOrgName")], clearValueOnBlur);
		setFieldFormat();
		setSortable();
		setTimeout(function() {
			loadQuotationNumber();
			loadDefaultMyInfo();
			loadMasterInfo();
			loadDetailInfo();
		}, 200);
	});
});