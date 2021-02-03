/**
* Framework Core JavaScript
* 	- library & plugin required : jQuery.js
* Caution : The methods which start with '_' are all private methods. Do not use these methods in projects.
*/

/**
 * execute constructor
 */
var Class = {
	create : function() {
		return function() {
			this.initialize.apply(this, arguments);
		};
	}
};
/**
 * global js variables
 */
var jsconfig = {
	map : {},
	isExist : function(name) {return !!this.map[name];},
	put : function(name, value) {this.map[name] = value;},
	get : function(name) {return (!!this.map[name]) ? this.map[name] : null;},
	remove : function(name) {delete this.map[name];},
	getMap : function() {return this.map;},
	toString : function() {
		var items = [];
		for (var keys in this.map) {
			items[keys] = this.map[keys];
			console.log("jsconfig : "+keys+" => "+items[keys]);
		}
	}
};

var chartColor = {
	background0:"rgba(255, 99, 132, 0.2)",
	border0:"rgba(255, 99, 132, 1)",
	background1:"rgba(54, 162, 235, 0.2)",
	border1:"rgba(54, 162, 235, 1)",
	background2:"rgba(255, 159, 64, 0.2)",
	border2:"rgba(255, 159, 64, 1)",
	background3:"rgba(75, 192, 192, 0.2)",
	border3:"rgba(75, 192, 192, 1)",
	background4:"rgba(255, 205, 86, 0.2)",
	border4:"rgba(255, 205, 86, 1)",
	background5:"rgba(153, 102, 255, 0.2)",
	border5:"rgba(153, 102, 255, 1)",
	background6:"rgba(201, 203, 207, 0.2)",
	border6:"rgba(201, 203, 207, 1)",
	background7:"rgba(194, 24, 91, 0.2)",
	border7:"rgba(194, 24, 91, 1)",
	background8:"rgba(85, 139, 47, 0.2)",
	border8:"rgba(85, 139, 47, 1)",
	background9:"rgba(245, 127, 23, 0.2)",
	border9:"rgba(245, 127, 23, 1)",
	background10:"rgba(121, 85, 72, 0.2)",
	border10:"rgba(121, 85, 72, 1)",
	background11:"rgba(0, 0, 0, 0.2)",
	border11:"rgba(0, 0, 0, 1)",
	background12:"rgba(221, 44, 0, 0.2)",
	border12:"rgba(221, 44, 0, 1)",
	background13:"rgba(251, 192, 45, 0.2)",
	border13:"rgba(251, 192, 45, 1)",
	background14:"rgba(174, 234, 0, 0.2)",
	border14:"rgba(174, 234, 0, 1)",
	background15:"rgba(0, 131, 143, 0.2)",
	border15:"rgba(0, 131, 143, 1)"
};

/**
 * declare nony
 */
var nony = {
	/*!
	 * information
	 */
	version : "0.1.0",
	browser : {
		IE : (JSON.parse(JSON.stringify(bowser, null))).name.indexOf("Chrome") == -1 && (JSON.parse(JSON.stringify(bowser, null))).name.indexOf("Firefox") == -1,
		Chrome : (JSON.parse(JSON.stringify(bowser, null))).name.indexOf("Chrome") != -1,
		FireFox : (JSON.parse(JSON.stringify(bowser, null))).name.indexOf("Firefox") != -1,
		userAgent : navigator.userAgent,
		version : bowser.version
	},
	isPopup : function() {
		return (jsconfig.get("noLayoutWindow") || jsconfig.get("isPopup") || $("#divPopupWindowHolder").length > 0);
	},
	isTabFrame : function() {
		return ($("#divFrameWindowHolder").length > 0);
	},
	isNormalPage : function() {
		return !$.nony.isPopup();
	},
	/*!
	 * operating
	 * 		general form submit :
	 * 			submit : no effect
	 * 			doSubmit : with effect
	 * 		ajax submit :
	 * 			doAjax : $.Ajax
	 */
	submit : function(params) {
		var formId = ($.nony.isEmpty(params.form)) ? params.formId : params.form;
		var action = ($.nony.isEmpty(params.action)) ? params.url : params.action;
		var target = params.target;

		if ($.nony.isEmpty(formId)) {
			formId = $("form:eq(0)");
			params.form = $("form:eq(0)");
		}

		if (typeof(formId) == "object") {formId = $(formId).prop("id");}

		if ("Y" == jsconfig.get("autoSetSearchCriteria")) {
			$.nony._appendAutoSearchCriteria(formId);
		}

		if (params.data) {
			for (var keys in params.data) {
				var items = [];
				items[keys] = params.data[keys];

				hiddenElem = $("<input type='hidden' id='"+keys+"' name='"+keys+"' value='"+items[keys]+"'/>");
				hiddenElem.appendTo($("#" + formId));
			}
		}

		setTimeout(function() {
			$("#" + formId).attr("action", action).attr("target", target).submit();
		}, 50);
//		$("#" + formId).attr("action", action).attr("target", target).submit();
	},
	doSubmit : function(params) {
		var effect = params.effect;
		var formId = ($.nony.isEmpty(params.form)) ? params.formId : params.form;

		if ($.nony.isEmpty(formId)) {
			formId = $("form:eq(0)");
			params.form = $("form:eq(0)");
		}

		if (typeof(formId) == "object") {formId = $(formId).prop("id");}

		if ($.nony.isPopup()) {
			if ("1" == jsconfig.get("submitEffect") || "1" == effect || "2" == jsconfig.get("submitEffect") || "2" == effect || "3" == jsconfig.get("submitEffect") || "3" == effect) {
				// some actions do not refresh the page - download
				if (params.action.indexOf("download") == -1) {
					$.nony.showProcMessage(com.message.loading);
				}

				setTimeout(function() {
					$.nony.submit(params);
				}, 10);
			} else {
				$.nony.submit(params);
			}
		} else {
			// some actions do not refresh the page - download
			if (params.action.indexOf("download") == -1) {
				if ("1" == jsconfig.get("submitEffect") || "1" == effect) {
					$.nony.showProcMessage(com.message.loading);
					setTimeout(function() {
						$.nony.submit(params);
					}, 10);
				} else if ("2" == jsconfig.get("submitEffect") || "2" == effect) {
					$("#divBodyHolder").slideUp("slow", function() {
						setTimeout(function() {
							$.nony.submit(params);
						}, 10);
					});
				} else if ("3" == jsconfig.get("submitEffect") || "3" == effect) {
					$.nony.showProcMessage(com.message.loading);
					$("#divBodyHolder").slideUp("slow", function() {
						setTimeout(function() {
							$.nony.submit(params);
						}, 10);
					});
				} else {
					$.nony.submit(params);
				}
			} else {
				$.nony.submit(params);
			}
		}
	},
	serialiseForm : function(jqForm) {
		if (typeof(jqForm) != "object") {
			throw new Error("Form " + com.message.invalid);
			return;
		}

		var arrTemp = $(jqForm).serializeArray(),
			paramData = {};

		$.each(arrTemp, function(i, data) {
			paramData[data.name] = data.value;
		});

		return paramData;
	},
	serialiseObject : function(jqObject) {
		if (typeof(jqObject) != "object") {
			throw new Error("Object " + com.message.invalid);
			return;
		}

		var paramData = {};

		$(jqObject).find("input, select").each(function(index) {
			paramData[$(this).attr("name")] = $(this).val();
		});

		return paramData;
	},
	/*!
	 * General form submission process wrappers
	 */
	doSimpleProcessForPage : function(params) { // no confirm message, no post message (ex, getEdit, getInsert, getUpdate etc)
		var formId = ($.nony.isEmpty(params.form)) ? params.formId : params.form;

		if ($.nony.isEmpty(formId)) {
			formId = $("form:eq(0)");
			params.form = $("form:eq(0)");
		}

		if (typeof(formId) == "object") {formId = $(formId).prop("id");}

		commonJs.doSubmit({
			action:params.action,
			data:params.data || {}
		});
	},
	doProcessForPage : function(params) { // confirm message, post message (ex, not save/delete)
		var formId = ($.nony.isEmpty(params.form)) ? params.formId : params.form;

		if ($.nony.isEmpty(formId)) {
			formId = $("form:eq(0)");
			params.form = $("form:eq(0)");
		}

		if (typeof(formId) == "object") {formId = $(formId).prop("id");}

		commonJs.confirm({
			contents:params.confirmMessage,
			buttons:[{
				caption:com.caption.yes,
				callback:function() {
					if (!$.nony.isEmpty(params.preProcess)) {
						params.preProcess();
					}

					commonJs.doSubmit({
						formId:formId,
						action:params.action,
						data:params.data || {}
					});
				}
			}, {
				caption:com.caption.no,
				callback:function() {
					if (!$.nony.isEmpty(params.onCancel)) {
						params.onCancel();
					}
				}
			}]
		});
	},
	doProcessWithFileForPage : function(params) {
		var formId = ($.nony.isEmpty(params.form)) ? params.formId : params.form;

		if ($.nony.isEmpty(formId)) {
			formId = $("form:eq(0)");
			params.form = $("form:eq(0)");
		}

		if (typeof(formId) == "object") {formId = $(formId).prop("id");}

		$("#"+formId).attr("enctype", "multipart/form-data");

		commonJs.confirm({
			contents:params.confirmMessage,
			buttons:[{
				caption:com.caption.yes,
				callback:function() {
					if (!$.nony.isEmpty(params.preProcess)) {
						params.preProcess();
					}

					commonJs.doSubmit({
						formId:formId,
						action:params.action,
						data:params.data || {}
					});
				}
			}, {
				caption:com.caption.no,
				callback:function() {
					if (!$.nony.isEmpty(params.onCancel)) {
						params.onCancel();
					}
				}
			}]
		});
	},
	doSaveForPage : function(params) {
		var formId = ($.nony.isEmpty(params.form)) ? params.formId : params.form;

		if ($.nony.isEmpty(formId)) {
			formId = $("form:eq(0)");
			params.form = $("form:eq(0)");
		}

		if (typeof(formId) == "object") {formId = $(formId).prop("id");}

		commonJs.confirm({
			contents:com.message.Q001,
			buttons:[{
				caption:com.caption.yes,
				callback:function() {
					if (!$.nony.isEmpty(params.preProcess)) {
						params.preProcess();
					}

					commonJs.doSubmit({
						formId:formId,
						action:params.action,
						data:params.data || {}
					});
				}
			}, {
				caption:com.caption.no,
				callback:function() {
					if (!$.nony.isEmpty(params.onCancel)) {
						params.onCancel();
					}
				}
			}]
		});
	},
	doSaveWithFileForPage : function(params) {
		var formId = ($.nony.isEmpty(params.form)) ? params.formId : params.form;

		if ($.nony.isEmpty(formId)) {
			formId = $("form:eq(0)");
			params.form = $("form:eq(0)");
		}

		if (typeof(formId) == "object") {formId = $(formId).prop("id");}

		$("#"+formId).attr("enctype", "multipart/form-data");

		commonJs.confirm({
			contents:com.message.Q001,
			buttons:[{
				caption:com.caption.yes,
				callback:function() {
					if (!$.nony.isEmpty(params.preProcess)) {
						params.preProcess();
					}

					commonJs.doSubmit({
						formId:formId,
						action:params.action,
						data:params.data || {}
					});
				}
			}, {
				caption:com.caption.no,
				callback:function() {
					if (!$.nony.isEmpty(params.onCancel)) {
						params.onCancel();
					}
				}
			}]
		});
	},
	doDeleteForPage : function(params) {
		var formId = ($.nony.isEmpty(params.form)) ? params.formId : params.form;

		if ($.nony.isEmpty(formId)) {
			formId = $("form:eq(0)");
			params.form = $("form:eq(0)");
		}

		if (typeof(formId) == "object") {formId = $(formId).prop("id");}

		commonJs.confirm({
			contents:com.message.Q002,
			buttons:[{
				caption:com.caption.yes,
				callback:function() {
					if (!$.nony.isEmpty(params.preProcess)) {
						params.preProcess();
					}

					commonJs.doSubmit({
						formId:formId,
						action:params.action,
						data:params.data || {}
					});
				}
			}, {
				caption:com.caption.no,
				callback:function() {
					if (!$.nony.isEmpty(params.onCancel)) {
						params.onCancel();
					}
				}
			}]
		});
	},
	/*!
	 * Ajax process wrappers
	 */
	doSearch : function(params) {
		setTimeout(function() {
			commonJs.ajaxSubmit({
				url:params.url,
				dataType:params.dataType || "json",
				formId:(params.noForm == true) ? "" : params.formId || "fmDefault",
				data:params.data || {},
				success:function(data, textStatus) {
					var result = commonJs.parseAjaxResult(data, textStatus, params.dataType||"json");

					if (result.isSuccess == true || result.isSuccess == "true") {
						$.nony._doAjaxSimpleSuccessProc(params, result);
					} else {
						$.nony._doAjaxErrorProc(params, data, result);
					}
				}
			});
		}, 300);
	},
	doSimpleProcess : function(params) { // no confirm message, no post message (ex, getEdit, getInsert, getUpdate etc)
		commonJs.ajaxSubmit({
			url:params.url,
			dataType:params.dataType || "json",
			formId:(params.noForm == true) ? "" : params.formId || "fmDefault",
			data:params.data || {},
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, params.dataType||"json");

				if (result.isSuccess == true || result.isSuccess == "true") {
					$.nony._doAjaxSimpleSuccessProc(params, result);
				} else {
					$.nony._doAjaxErrorProc(params, data, result);
				}
			}
		});
	},
	doProcess : function(params) { // confirm message, post message (ex, not save/delete)
		commonJs.confirm({
			contents:params.confirmMessage,
			buttons:[{
				caption:com.caption.yes,
				callback:function() {
					if (!$.nony.isEmpty(params.preProcess)) {
						params.preProcess();
					}

					commonJs.ajaxSubmit({
						url:params.url,
						dataType:params.dataType || "json",
						formId:(params.noForm == true) ? "" : params.formId || "fmDefault",
						data:params.data || {},
						success:function(data, textStatus) {
							var result = commonJs.parseAjaxResult(data, textStatus, params.dataType||"json");

							if (result.isSuccess == true || result.isSuccess == "true") {
								$.nony._doAjaxSuccessProc(params, result);
							} else {
								$.nony._doAjaxErrorProc(params, data, result);
							}
						}
					});
				}
			}, {
				caption:com.caption.no,
				callback:function() {
					if (!$.nony.isEmpty(params.onCancel)) {
						params.onCancel();
					}
				}
			}]
		});
	},
	doProcessWithFile : function(params) {
		commonJs.confirm({
			contents:params.confirmMessage,
			buttons:[{
				caption:com.caption.yes,
				callback:function() {
					if (!$.nony.isEmpty(params.preProcess)) {
						params.preProcess();
					}

					commonJs.ajaxSubmitMultipart({
						url:params.url,
						dataType:params.dataType || "json",
						formId:(params.noForm == true) ? "" : params.formId || "fmDefault",
						data:params.data || {},
						success:function(data, textStatus) {
							var result = commonJs.parseAjaxResult(data, textStatus, params.dataType||"json");

							if (result.isSuccess == true || result.isSuccess == "true") {
								$.nony._doAjaxSuccessProc(params, result);
							} else {
								$.nony._doAjaxErrorProc(params, data, result);
							}
						}
					});
				}
			}, {
				caption:com.caption.no,
				callback:function() {
					if (!$.nony.isEmpty(params.onCancel)) {
						params.onCancel();
					}
				}
			}]
		});
	},
	doSave : function(params) {
		commonJs.confirm({
			contents:com.message.Q001,
			buttons:[{
				caption:com.caption.yes,
				callback:function() {
					if (!$.nony.isEmpty(params.preProcess)) {
						params.preProcess();
					}

					commonJs.ajaxSubmit({
						url:params.url,
						dataType:params.dataType || "json",
						formId:(params.noForm == true) ? "" : params.formId || "fmDefault",
						data:params.data || {},
						success:function(data, textStatus) {
							var result = commonJs.parseAjaxResult(data, textStatus, params.dataType||"json");

							if (result.isSuccess == true || result.isSuccess == "true") {
								$.nony._doAjaxSuccessProc(params, result);
							} else {
								$.nony._doAjaxErrorProc(params, data, result);
							}
						}
					});
				}
			}, {
				caption:com.caption.no,
				callback:function() {
					if (!$.nony.isEmpty(params.onCancel)) {
						params.onCancel();
					}
				}
			}]
		});
	},
	doSaveWithFile : function(params) {
		commonJs.confirm({
			contents:com.message.Q001,
			buttons:[{
				caption:com.caption.yes,
				callback:function() {
					if (!$.nony.isEmpty(params.preProcess)) {
						params.preProcess();
					}

					commonJs.ajaxSubmitMultipart({
						url:params.url,
						dataType:params.dataType || "json",
						formId:(params.noForm == true) ? "" : params.formId || "fmDefault",
						data:params.data || {},
						success:function(data, textStatus) {
							var result = commonJs.parseAjaxResult(data, textStatus, params.dataType||"json");

							if (result.isSuccess == true || result.isSuccess == "true") {
								$.nony._doAjaxSuccessProc(params, result);
							} else {
								$.nony._doAjaxErrorProc(params, data, result);
							}
						}
					});
				}
			}, {
				caption:com.caption.no,
				callback:function() {
					if (!$.nony.isEmpty(params.onCancel)) {
						params.onCancel();
					}
				}
			}]
		});
	},
	doDelete : function(params) {
		commonJs.confirm({
			contents:com.message.Q002,
			buttons:[{
				caption:com.caption.yes,
				callback:function() {
					if (!$.nony.isEmpty(params.preProcess)) {
						params.preProcess();
					}

					commonJs.ajaxSubmit({
						url:params.url,
						dataType:params.dataType || "json",
						formId:(params.noForm == true) ? "" : params.formId || "fmDefault",
						data:params.data || {},
						success:function(data, textStatus) {
							var result = commonJs.parseAjaxResult(data, textStatus, params.dataType||"json");

							if (result.isSuccess == true || result.isSuccess == "true") {
								$.nony._doAjaxSuccessProc(params, result);
							} else {
								$.nony._doAjaxErrorProc(params, data, result);
							}
						}
					});
				}
			}, {
				caption:com.caption.no,
				callback:function() {
					if (!$.nony.isEmpty(params.onCancel)) {
						params.onCancel();
					}
				}
			}]
		});
	},
	doExport : function(params) {
		$("[name=fileType]").remove();
		$("[name=dataRange]").remove();

		if (!$.nony.isEmpty(params.menuObject)) {
			params.data.fileType = params.menuObject.fileType;
			params.data.dataRange = params.menuObject.dataRange;
		}

		commonJs.confirm({
			contents:com.message.Q003,
			buttons:[{
				caption:com.caption.yes,
				callback:function() {
					if (!$.nony.isEmpty(params.preProcess)) {
						params.preProcess();
					}

					popup = commonJs.openPopup({
						popupId:"exportFile",
						url:params.url,
						paramData:params.data,
						header:"exportFile",
						blind:false,
						width:200,
						height:100
					});
				}
			}, {
				caption:com.caption.no,
				callback:function() {
				}
			}]
		});
	},
	/*!
	 * number utilities
	 */
	isNumber : function(val) {
		var pattern = /^[-+0-9,.]+$/;
		return (pattern.test(val)) ? true : false;
	},
	toNumber : function(val) {
		if ($.nony.isEmpty(val)) {return 0;}
		if ($.nony.isNumber(val)) {return parseFloat((""+val).replace(/\,/g, ""));}
		else {return 0;}
	},
	ceil : function(number, scale) {
		var val = Math.pow(10, scale);
		return Math.ceil($.nony.toNumber(number) * val) / val;
	},
	round : function(number, scale) {
		var val = Math.pow(10, scale);
		return Math.round($.nony.toNumber(number) * val) / val;
	},
	floor : function(number, scale) {
		var val = Math.pow(10, scale);
		return Math.floor($.nony.toNumber(number) * val) / val;
	},
	getNumberMask : function(number, format) {
		return numeral(number).format(format);
	},
	// return number only from css attribute of jQuery object
	getCssAttributeNumber : function(jqObject, cssAttributeName) {
		if ($.nony.isEmpty(cssAttributeName)) {
			return 0;
		} else if (cssAttributeName.indexOf("margin") != -1 || cssAttributeName.indexOf("padding") != -1 || cssAttributeName.indexOf("left") != -1 || cssAttributeName.indexOf("top") != -1) {
			return $.nony.toNumber($.nony.replace($(jqObject).css(cssAttributeName), "px", ""));
		} else if (cssAttributeName.indexOf("border") != -1) {
			var temp = $(jqObject).css(cssAttributeName);

			if (!$.nony.isEmpty(temp)) {
				temp = temp.split(" ");
				temp = temp[0];

				if (cssAttributeName.indexOf("-top") != -1 || cssAttributeName.indexOf("-bottom") != -1 || cssAttributeName.indexOf("-left") != -1 || cssAttributeName.indexOf("-right") != -1) {
					return $.nony.toNumber($.nony.replace(temp, "px", ""));
				} else {
					return ($.nony.toNumber($.nony.replace(temp, "px", "")) * 2);
				}
			} else {
				return 0;
			}
		}
	},
	/*!
	 * string utilities
	 */
	isEmpty : function(val) {
		if (null == val || "" == val || "undefined" == val || undefined == val) {return true;}
		else {return false;}
	},
	isBlank : function(val) {
		if (null == val || "" == val || "undefined" == val || undefined == val) {return true;}
		else {return false;}
	},
	isNotEmpty : function(val) {
		if (null == val || "" == val || "undefined" == val || undefined == val) {return false;}
		else {return true;}
	},
	isNotBlank : function(val) {
		if (null == val || "" == val || "undefined" == val || undefined == val) {return false;}
		else {return true;}
	},
	isIn : function(val, vals) {
		for (var i=0; i<vals.length; i++) {
			if (val == vals[i]) {return true;}
		}
		return false;
	},
	isInIgnoreCase : function(val, vals) {
		for (var i=0; i<vals.length; i++) {
			if ($.nony.lowerCase(val) == $.nony.lowerCase(vals[i])) {return true;}
		}
		return false;
	},
	nvl : function(val, defaultVal) {
		if ($.nony.isEmpty(val)) {
			if ($.nony.isEmpty(defaultVal)) {
				return "";
			} else {
				return defaultVal;
			}
		} else {
			return val;
		}
	},
	trim : function(txt) {
		while (txt.indexOf(" ") != -1) {
			txt = txt.replace(" ", "");
		}
		return txt;
	},
	upperCase : function(val) {
		if ($.nony.isEmpty(val)) {
			return "";
		} else {
			return val.toUpperCase();
		}
	},
	lowerCase : function(val) {
		if ($.nony.isEmpty(val)) {
			return "";
		} else {
			return val.toLowerCase();
		}
	},
	replace : function(src, from, to) {
		var newStr = src;
		var iOffset = 0;

		if (!$.nony.isEmpty(newStr)) {
			try {
				while ((iOffset = newStr.indexOf(from, iOffset)) > -1) {
					var temp = newStr.substring(0, iOffset);
					temp += to;
					temp += newStr.substring(iOffset+from.length);
					newStr = temp;
					iOffset++;
				}
			} catch(e) {
			}
		}
		return newStr;
	},
	removeString : function(src, val) {
		var str = "";

		if ($.nony.isEmpty(src) || val == null) {
			return src;
		}

		str = src.trim();
		for (var i=0; i<val.length; i++) {
			str = $.nony.replace(str, val[i], "");
		}
		return str;
	},
	lpad : function(src, len, pad) {
		var result = ""+src;
		var templen = len - result.length;

		for (var i=0; i<templen; i++) {
			result = pad + result;
		}

		return result;
	},
	rpad : function(src, len, pad) {
		var result = ""+src;
		var templen = len - result.length;

		for (var i=0; i<templen; i++) {
			result = result + pad;
		}

		return result;
	},
	startsWith : function(src, prefix) {
		return src.indexOf(prefix) === 0;
	},
	endsWith : function(src, suffix) {
		return src.match(suffix+"$") == suffix;
	},
	contains : function(src, val) {
		if (src == null) {return false;}
		src = $.nony.nvl(src);
		return src.indexOf(val) != -1;
	},
	containsIgnoreCase : function(src, val) {
		if (src == null) {return false;}
		src = $.nony.lowerCase($.nony.nvl(src));
		return src.indexOf($.nony.lowerCase(val)) != -1;
	},
	// getFormatString("1234567890", "????-???-???") => "1234-567-890"
	getFormatString : function(source, format) {
		var sRtn = "";
		var iStart = 0, iLen = 0, iTCount = 0, iFCount = 0;

		iTCount = source.length;
		iFCount = format.length;

		if ($.nony.isEmpty(source)) {return sRtn;}
		if ($.nony.isEmpty(format)) {return source;}

		for (var i=0; i<iTCount; i++) {if (!(source.substring(i, i+1) == "-")) {sRtn = sRtn + source.substring(i, i+1);}}

		source = sRtn;
		iTCount = source.length;

		for (var i=0; i<iFCount; i++) {if (format.substring(i, i+1) == "?") {iLen++;}}

		if (iTCount < iLen) {
			for (var i=0; i<(iLen - iTCount); i++) {source = "0" + source;}
			iTCount = iLen;
		}

		sRtn = "";
		for (var i=0; i<iTCount; i++) {
			for (var j=iStart; j<iFCount; j++) {
				if (format.substring(j, j+1) == "?") {
					sRtn = sRtn + source.substring(i, i+1);
					iStart = iStart + 1;
					break;
				} else {
					sRtn = sRtn + format.substring(j, j+1);
					iStart = iStart + 1;
				}
			}
		}
		return sRtn + format.substring(iStart);
	},
	getUid : function() {
		return ""+$.nony.getTimeStamp();
	},
	abbreviate : function(val, length) {
		if (val.length < length) {return val;}
		return val.substring(0, length)+"...";
	},
	toBoolean : function(val) {
		if ($.nony.isEmpty(val)) {return false;}
		return (val == true || val.toLowerCase() == "true" || val.toLowerCase() == "yes" || val.toLowerCase() == "y");
	},
	htmlToString : function(val) {
		if ($.nony.isEmpty(val)) {
			val = "";
		} else {
			val = $.nony.replace(val, "&amp;", "&");
//			val = $.nony.replace(val, "&#35;", "#");
//			val = $.nony.replace(val, "&lt;", "<");
//			val = $.nony.replace(val, "&gt;", ">");
//			val = $.nony.replace(val, "&#37;", "%");
			val = $.nony.replace(val, "&quot;", "\"");
			val = $.nony.replace(val, "&#39;", "'");
//			val = $.nony.replace(val, "&nbsp;", " ");
			val = $.nony.replace(val, "<br/>", "\n");
			val = $.nony.replace(val, "<br>", "\n");
		}
		return val;
	},
	stringToHtml : function(val) {
		if ($.nony.isEmpty(val)) {
			val = "";
		} else {
			val = $.nony.replace(val, "&", "&amp;");
//			val = $.nony.replace(val, "#", "&#35;");
//			val = $.nony.replace(val, "<", "&lt;");
//			val = $.nony.replace(val, ">", "&gt;");
//			val = $.nony.replace(val, "%", "&#37;");
			val = $.nony.replace(val, "\"", "&quot;");
			val = $.nony.replace(val, "'", "&#39;");
//			val = $.nony.replace(val, " ", "&nbsp;");
			val = $.nony.replace(val, "\n", "<br/>");
			val = $.nony.replace(val, "\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
//			val = $.nony.replace(val, "<br>", "\n");
		}
		return val;
	},
	/*!
	 * date utilities(use moment.js)
	 */
	getSysdate : function(format) {
		format = format || "YYYYMMDD HHmmss";
		return moment().format(format);
	},
	getFormatDateTime : function(value, format) {
		if (value == null || value == "") {return "";}

		format = format || "YYYYMMDD HHmmss";

		if (typeof value == "string") {
			if (!moment(value, format).isValid()) {return "";}
			return moment(value, format).format(format);
		}
		// Date object
		if (typeof value == "object") {
			return moment(value).format(format);
		}
	},
	getDateTimeMask : function(value, format) {
		var basicDateTimeJs = jsconfig.get("basicDateTimeJs");

		if (value == null || value == "") {return "";}

		return moment(value, basicDateTimeJs).format(format);
	},
	getTimeStamp : function() {
		return moment.unix(moment());
	},
	/*!
	 * Bind event and function
	 */
	setEvent : function(eventName, objArr, callback) {
		if ($.nony.isBlank(eventName) || objArr == null) {return;}

		for (var index in objArr) {
			$(objArr[index]).bind(eventName, function() {
				callback($(this));
			});
		}
	},
	clearValueOnBlur : function(jqObj) {
		if (jqObj == null) {return;}

		if ($.nony.isEmpty($(jqObj).val())) {
			$(jqObj).val("");
		}
	},
	/*!
	 * utilities etc
	 */
	// used by validator
	getElement : function(any) {
		var obj = null;

		if (typeof(any) == "string") {obj = (document.getElementById(any) || document.getElementsByName(any));}
		else if (typeof(any) == "object") {obj = any;}

		if ($(obj).length > 0) {
			if (obj.type == "radio") {return document.getElementsByName(obj.name);}
			else {return obj;}
		} else {return null;}
	},
	removeArrayItem : function(array, name) {
		if (array == null) {return null;}

		for (var i=0; i<array.length; i++) {
			if (array[i] == name) {array.splice(i, 1);}
		}
		return array;
	},
	getIconNameByFileType : function(fileType) {
		if (fileType.indexOf("pdf") != -1) {
			return "icnPdf.png";
		} else if (fileType.indexOf("ms-excel") != -1 || fileType.indexOf("officedocument.spreadsheetml.sheet") != -1) {
			return "icnExcel.png";
		} else if (fileType.indexOf("msword") != -1 || fileType.indexOf("officedocument.wordprocessingml.document") != -1) {
			return "icnWord.png";
		} else if (fileType.indexOf("ms-powerpoint") != -1 || fileType.indexOf("officedocument.presentationml.presentation") != -1) {
			return "icnPowerpoint.png";
		} else if (fileType.indexOf("image/") != -1) {
			return "icnImage.png";
		} else {
			return "icnDocument.png";
		}
	},
	setFieldDateMask : function(elementId) {
		if (typeof(elementId) != "string") {
			throw new Error("The parameter" + com.message.invalid);
			return;
		}

		var dateFormat = (jsconfig.get("langCode") == "ko") ? "YYYY-MM-DD" : jsconfig.get("dateFormatJs");
		var formatPlaceHolder = dateFormat.toLowerCase();

		dateFormat = $.nony.replace(dateFormat.toUpperCase(), "DD", "99");
		dateFormat = $.nony.replace(dateFormat.toUpperCase(), "MM", "99");
		dateFormat = $.nony.replace(dateFormat.toUpperCase(), "YYYY", "9999");

		$("#"+elementId).mask(
			dateFormat,
			{
				placeholder:formatPlaceHolder,
				completed:function() {
					$.nony.validator.isValidDate($("#"+elementId));
				}
			}
		);
	},
	setFieldNumberMask : function(elementId, format) {
		if (typeof(elementId) != "string") {
			throw new Error("The parameter" + com.message.invalid);
			return;
		}

		$("#"+elementId).mask(
			format,
			{
				placeholder:"#",
				completed:function() {
					$.nony.validator.isValidNumeric($("#"+elementId));
				}
			}
		);
	},
	toggleCheckboxes : function(elementName) {
		var checkFlag = $.nony.nvl(jsconfig.get("toggleCheckboxes"));

		if (typeof(elementName) != "string") {
			throw new Error("The parameter" + com.message.invalid);
			return;
		}

		$("input:checkbox[name="+elementName+"]").each(function(index) {
			if (!$(this).is(":disabled")) {
				$(this).prop("checked", !checkFlag);

				if ($(this).is(":checked")) {
					$(this).parents("tr").addClass("checkedTr");
				} else {
					$(this).parents("tr").removeClass("checkedTr");
				}
			}
		});

		jsconfig.put("toggleCheckboxes", !checkFlag);
	},
	bindToggleTrBackgoundWithCheckbox : function(jqObject) {
		$(jqObject).each(function(index) {
			$(this).bind("click", function() {
				if ($(this).is(":checked")) {
					$(this).parents("tr").addClass("checkedTr");
				} else {
					$(this).parents("tr").removeClass("checkedTr");
				}
			});
		});
	},
	bindToggleTrBackgoundWithRadiobox : function(jqObject) {
		$(jqObject).each(function(index) {
			$(this).bind("click", function() {
				$(this).parents("tbody").find("tr").each(function(rowIdx) {
					if (index == rowIdx) {
						$(this).addClass("checkedTr");
					} else {
						$(this).removeClass("checkedTr");
					}
				});
			});
		});
	},
	getCountChecked : function (checkboxName) {
		var cnt = 0;

		if (typeof(checkboxName) != "string") {
			throw new Error("The parameter" + com.message.invalid);
			return;
		}

		$("input:checkbox[name="+checkboxName+"]").each(function(index) {
			if ($(this).is(":checked")) {
				cnt += 1;
			}
		});

		return cnt;
	},
	setCheckboxValue : function(checkboxName, value) {
		$("[name = "+checkboxName+"]").each(function() {
			if ($(this).val() == value) {
				$(this).prop("checked", true);
			}
		});
	},
	clearSearchCriteria : function() {
		$(document).find("#divSearchCriteriaArea").find(":input").each(function() {
			if ($(this).prop("type") == "checkbox" || $(this).prop("type") == "radio") {
				$(this).attr("checked", false);
			} else {
				$(this).val("");
			}
		});

		$("select.bootstrapSelect").each(function(index) {
			$(this).selectpicker("refresh");
		});
	},
	isSearchCriteriaSelected : function() {
		var isSelected = false;
		$(document).find("#divSearchCriteriaArea").find(":input").each(function() {
			if ($(this).prop("type") == "checkbox" || $(this).prop("type") == "radio") {
				if ($(this).is(":checked")) {
					isSelected = true;
				}
			} else {
				if (!$.nony.isEmpty($(this).val())) {
					isSelected = true;
				}
			}
		});
		return isSelected;
	},
	clearPaginationValue : function() {
		$("#txtCurrentPageForPagination").val("");
		$("#selMaxRowsPerPageSelectForPagenation").val("");
	},
	getCheckedValueFromRadio : function(radioName) {
		var checkValue;
		$("[name="+radioName+"]").each(function(index) {
			if ($(this).is(":checked")) {
				checkValue = $(this).val();
			}
		});
		return checkValue;
	},
	getCheckedValueFromCheckbox : function(jqObject) {
		var delimiter = jsconfig.get("recordDelimiter"), values = "";
		$(jqObject).each(function(index) {
			if ($(this).is(":checked")) {
				values += commonJs.isBlank(values) ? $(this).val() : delimiter+$(this).val();
			}
		});
		return values;
	},
	setRadioValue : function(radioName, value) {
		$("[name="+radioName+"]").filter("[value="+value+"]").attr("checked", true);
	},
	setExportButtonContextMenu : function(jqObjectButton) {
		if ($(jqObjectButton).length <= 0) {return;}

		ctxMenu.commonExport[0].fun = function() {exeExport(ctxMenu.commonExport[0]);};
		ctxMenu.commonExport[1].fun = function() {exeExport(ctxMenu.commonExport[1]);};
		ctxMenu.commonExport[2].fun = function() {exeExport(ctxMenu.commonExport[2]);};
		ctxMenu.commonExport[3].fun = function() {exeExport(ctxMenu.commonExport[3]);};
		ctxMenu.commonExport[4].fun = function() {exeExport(ctxMenu.commonExport[4]);};
		ctxMenu.commonExport[5].fun = function() {exeExport(ctxMenu.commonExport[5]);};

		$(jqObjectButton).contextMenu(ctxMenu.commonExport, {
			classPrefix:com.constants.ctxClassPrefixButton,
			effectDuration:300,
			effect:"slide",
			borderRadius:"bottom 4px",
			displayAround:"trigger",
			position:"bottom",
			horAdjust:0
		});
	},
	copyToClipboard : function(value) {
		var $temp = $("<input>");
		$("body").append($temp);
		$temp.val(value).select();
		document.execCommand("copy");
		$temp.remove();
	},
	downloadContentAsFile : function(fileName, value) {
		var element = document.createElement("a");

		element.setAttribute("href", "data:text/plain;charset=utf-8," + encodeURIComponent(value));
		element.setAttribute("download", fileName);
		element.style.display = "none";
		document.body.appendChild(element);
		element.click();
		document.body.removeChild(element);
	},
	/*!
	 * jQuery UI
	 */
	setAccordion : function(params) {
		var containerString;

		// Accordion container - Id or Class name
		if ($.nony.isEmpty(params) || ($.nony.isEmpty(params.containerId) && $.nony.isEmpty(params.containerClass))) {
			throw new Error("The parameter" + com.message.invalid);
			return;
		}

		containerString = (!$.nony.isEmpty(params.containerClass)) ? "."+params.containerClass : "#"+params.containerId;
		params.multipleExpand = (params.multipleExpand == false) ? false : true;

		$(containerString).accordion({
			event:params.event || "click",
			header:params.header || "> div > h3",
			collapsible:params.collapsible || true,
			heightStyle:params.heightStyle || "content",
			active:params.active || 0,
			icons:params.icons,
			activate:function(event, ui) {
				if (!$.nony.isEmpty(params.activate) && typeof params.activate == "function") {
					params.activate(event, ui);
				}
			},
			beforeActivate:function(event, ui) {
				if (!$.nony.isEmpty(params.beforeActivate) && typeof params.beforeActivate == "function") {
					params.beforeActivate(event, ui);
				}

				if (params.multipleExpand) {
					var currHeader, currContent, isPanelSelected;

					if (ui.newHeader[0]) {
						currHeader = ui.newHeader;
						currContent = currHeader.next(".ui-accordion-content");
					} else {
						currHeader = ui.oldHeader;
						currContent = currHeader.next(".ui-accordion-content");
					}

					isPanelSelected = currHeader.attr("aria-selected") == "true";

					currHeader.toggleClass("ui-corner-all", isPanelSelected).toggleClass("accordion-header-active ui-state-active ui-corner-top", !isPanelSelected).attr("aria-selected", ((!isPanelSelected).toString()));
					currHeader.children(".ui-icon").toggleClass("ui-icon-triangle-1-e", isPanelSelected).toggleClass("ui-icon-triangle-1-s", !isPanelSelected);
					currContent.toggleClass("accordion-content-active", !isPanelSelected);

					if (isPanelSelected) {currContent.slideUp();}
					else {currContent.slideDown();}

					return false;
				}
			}
		});

		if (params.sortable) {
			$(containerString).sortable({
				axis:params.axis || "y",
				handle:params.handle || "h3",
				stop:function(event, ui) {
					var handler = params.handle || "h3";
					if (!$.nony.isEmpty(params.stop) && typeof params.stop == "function") {
						params.stop(event, ui);
					}
					ui.item.children(handler).triggerHandler("focusout");
					$(this).accordion("refresh");
				}
			});
		}
//		.sortable({
//			axis:"y",
//			handle:"h3",
//			stop:function(event, ui) {
//				if (!$.nony.isEmpty(params.stop) && typeof params.stop == "function") {
//					params.stop(event, ui);
//				}
//				ui.item.children("h3").triggerHandler("focusout");
//				$(this).accordion("refresh");
//			}
//		});

		if (params.expandAll) {$.nony.expandAllAccordion(params);}
	},
	expandAllAccordion : function(params) {
		var containerString;

		// Accordion container - Id or Class name
		if ($.nony.isEmpty(params) || ($.nony.isEmpty(params.containerId) && $.nony.isEmpty(params.containerClass))) {
			throw new Error("The parameter" + com.message.invalid);
			return;
		}

		containerString = (!$.nony.isEmpty(params.containerClass)) ? "."+params.containerClass : "#"+params.containerId;

		$(containerString+" .ui-accordion-header").each(function(index) {
			if ($(this).attr("class").indexOf("-active") == -1) {
				$(this).trigger("click");
			}
		});
	},
	_jqTooltip : function() {
		$.widget.bridge("jqUiTooltip", $.ui.tooltip);
		$(document).jqUiTooltip({
			track:true,
			show:{effect:"fade", duration:20},
			position:{my:"left+13 top+10", at:"right bottom"}
		});
	},
	_jqSelectmenu : function() {
		var options = {};

		$("select.bootstrapSelect").each(function(index) {
//			options.width = "auto";
			options.container = "body";
			options.style = $(this).attr("class");
			$(this).selectpicker(options);
		});
	},
	getBootstrapSelectbox : function(id) {
		var element;
		$("button").each(function() {
			if ($(this).attr("data-id") == id) {
				element = $(this);
			}
		});
		return element;
	},
	refreshBootstrapSelectbox : function(id) {
		if ($.nony.isEmpty(id)) {
			$("select.bootstrapSelect").each(function(index) {
				$(this).selectpicker("refresh");
				$(this).selectpicker("render");
			});
		} else {
			$("#"+id).selectpicker("refresh");
			$("#"+id).selectpicker("render");
		}
	},
	setSelectpickerValue : function(elementId, selectedValue) {
		$("#"+elementId).selectpicker("val", selectedValue);
		$("#"+elementId).selectpicker("refresh");
	},
	/*!
	 * Auto Completion
	 */
	setAutoComplete : function(jqObject, param) {
		if (param == null || $.nony.isEmpty(param.method) || $.nony.isEmpty(param.label) || $.nony.isEmpty(param.value)) {
			throw new Error("The parameter" + com.message.invalid);
			return;
		}

		var paramUrl = ($.nony.isEmpty(param.url)) ? "/common/autoCompletion/" : param.url;
		var dataSource = [], option;
		var additionalObject = param.addValElementNames;
		var data = {};
		var opt = {
			source:dataSource,
			minLength:1,
			search:function(event, ui) {
				data.inputValue = $(this).val();
				if (additionalObject) {
					for (var i=0; i<additionalObject.length; i++) {
						data[additionalObject[i]] = $("#"+additionalObject[i]).val();
					}
				}

				$.nony.ajax.ajaxSubmit({
					url:paramUrl+param.method+".do",
					dataType:"json",
					data:data,
					blind:false,
					success:function(data, textStatus) {
						var result = commonJs.parseAjaxResult(data, textStatus, "json");
						if (result.isSuccess == true || result.isSuccess == "true") {
							var ds = result.dataSet;

							dataSource = [];
							for (var i=0; i<ds.getRowCnt(); i++) {
								var item = {};

								item.label = ds.getValue(i, param.label.toUpperCase());
								item.value = ds.getValue(i, param.value.toUpperCase());

								dataSource.push(item);
							}

							$(jqObject).autocomplete("option", "source", dataSource);
						} else {
							$.nony._doAjaxErrorProc(param, data, result);
//							throw new Error(com.message.error);
//							return;
						}
					}
				});
			},
			open:function() {
				$(this).autocomplete("widget").css("z-index", 1000);
				return false;
			}
		};

//		$.nony.ajax.ajaxSubmit({
//			url:"/common/autoCompletion/"+param.method+".do",
//			dataType:"json",
//			data:param.data,
//			blind:false,
//			success:function(data, textStatus) {
//				var result = commonJs.parseAjaxResult(data, textStatus, "json");
//
//				if (result.isSuccess == true || result.isSuccess == "true") {
//					var ds = result.dataSet;
//					for (var i=0; i<ds.getRowCnt(); i++) {
//						dataSource.push(ds.getValue(i, 0));
//					}
//				} else {
//					throw new Error("The parameter" + com.message.invalid);
//					return;
//				}
//			}
//		});

		option = $.extend({}, opt, param);
		$(jqObject).attr("placeholder", "Auto Complete");
		$(jqObject).autocomplete(option);
	},
	/*!
	 * popup / dialog / calendar
	 */
	openWindow : function(params) {
		var url = params.url;
		var name = params.name || "Window";
		var width = params.width || "350";
		var height = params.height || "200";
		var opt = params.options;
		var xpos = (screen.width/2) - (width/2);
		var ypos = (screen.height/2) - (height/2);
		var attr = opt + ",width=" + width + ",height=" + height + ",left=" + xpos + ",top=" + ypos;
		return window.open(url, name, attr);
	},
	openPopup : function(params) {
		return $.nony.popup.openPopup(params);
	},
	openDialog : function(params) {
		return $.nony.popup.openDialog(params);
	},
	alert : function(msg) {
		var params = {};

		params.type = com.message.I000;
		params.contents = msg;

		return $.nony.popup.openDialog(params);
	},
	confirm : function(params) {
		if (typeof params != "object") {
			throw new Error("Parameter" + com.message.required);
			return;
		}

		params.type = com.message.Q000;

		return $.nony.popup.openDialog(params);
	},
	warn : function(msg) {
		var params = {};

		params.type = com.message.W000;
		params.contents = msg;

		return $.nony.popup.openDialog(params);
	},
	error : function(msg) {
		var params = {};

		params.type = com.message.E000;
		params.contents = msg;

		return $.nony.popup.openDialog(params);
	},
	openCalendar : function(event, txtObjectId, options) {
		return $.nony.calendar.openCalendar(event, txtObjectId, options);
	},
	/*!
	 * screen blind
	 */
	showProcMessage : function(msg) {
		$.blockUI({
			message:"<img src='" + jsconfig.get("imgThemeCom") + "/waiting1.gif' style='vertical-align:middle'/>&nbsp;&nbsp;&nbsp;" + msg,
			css:{
				width:"100%",
//				width:"250px",
				left:"0px",
//				left:"50%",
				top:"50%",
				border:"0px",
				background:"transparent",
//				background:"#FFFFFF",
//				opacity:"1.0",
				cursor:"wait",
				fadeIn:100,
				fadeOut:100,
				"font-weight":"bold",
				"font-size":"1.0em"
			},
			overlayCSS:{
				cursor:"wait",
				backgroundColor:"#888888",
				opacity:"0.1"
			}
		});
	},
	hideProcMessage : function() {
		$.unblockUI();
	},
	showProcMessageOnElement : function(blockElementId, msg) {
		var message = msg || com.message.loading;
		$("#"+blockElementId).block({
			message:"<img src='" + jsconfig.get("imgThemeCom") + "/waiting2.gif'/>&nbsp;" + message,
			css:{
				border:"0px",
				background:"transparent",
				cursor:"wait",
				fadeIn:100,
				fadeOut:100,
				"font-weight":"bold",
				"font-size":"1.0em"
			},
			overlayCSS:{
				cursor:"wait",
				backgroundColor:"#888888",
				opacity:"0.1"
			}
		});
		jsconfig.put("showProcMessageOnElement", blockElementId);
	},
	hideProcMessageOnElement : function(blockElementId) {
		$("#"+blockElementId).unblock();
		jsconfig.remove("showProcMessageOnElement");
	},
	/*!
	 * controlling tab - relevant elements should be div which has the same index of the tab as an id
	 */
	changeTabSelection : function(object) {
		var $parentUl = $(object).closest("ul");
		var $parentLi = $(object).closest("li");

		if ($parentLi.attr("class").indexOf("disabled") != -1) {return;}

		$parentUl.find("li").each(function(index) {
			var thisSelected = $(this).has($(object)).length;

			if (thisSelected <= 0) {
				if ($(this).attr("class").indexOf("disabled") != -1) {
				} else {
					$(this).removeClass("active");
					if ($("#div"+index).length > 0) {
						$("#div"+index).stop().animate({opacity:"hide"}, jsconfig.get("effectDuration"));
					}
				}
			}

			if (thisSelected > 0) {
				if ($(this).attr("class").indexOf("disabled") != -1) {
				} else {
					$(this).addClass("active");
					if ($("#div"+index).length > 0) {
						$("#div"+index).stop().delay(300).animate({opacity:"show"}, jsconfig.get("effectDuration"));
					}
				}
			}
		});
	},
	getSelectedTabIndex : function(object) {
		var $object, selectedIndex = -1;

		if (typeof(object) == "string") {$object = $("#"+object);}
		else {$object = $(object);}

		$object.find("li").each(function(index) {
			if ($(this).hasClass("active")) {
				selectedIndex = index;
			}
		});

		return selectedIndex;
	},
	/*!
	 * Automated Pagination
	 */
	onKeyPressFortxtCurrentPageForPagination : function(event, formId, formAction, script, textObject) {
		if (!event.which && ((event.charCode || event.charCode === 0) ? event.charCode: event.keyCode)) {
			event.which = event.charCode || event.keyCode;
		}

		if (event.which == "13") {
			$.nony.goPageForPagination(formId, formAction, script, textObject.value);
		}
	},
	goPageForPagination : function(formId, formAction, script, pageNumber) {
		$("#txtCurrentPageForPagination").val(pageNumber);

		if (!$.nony.isEmpty(script)) {
			var func = eval(script);
			func();
		} else {
			if ($.nony.isEmpty(formAction)) {
				throw new Error("The parameter " + com.message.invalid);
			}

			$.nony.doSubmit({
				form:(formId||document.forms[0]),
				action:formAction
			});
		}
	},
	/*!
	 * Create DataSet from java DataSet
	 */
	getDataSetFromJavaDataSet : function(dataSetString) {
		var strArray, header, values;
		var dataSet = new DataSet();
		var dataSetHeaderDelimiter = jsconfig.get("dataSetHeaderDelimiter");
		var recordDelimiter = jsconfig.get("recordDelimiter");
		var dataDelimiter = jsconfig.get("dataDelimiter");

		if (!$.nony.isEmpty(dataSetString) && dataSetHeaderDelimiter != dataSetString) {
			strArray = dataSetString.split(dataSetHeaderDelimiter);
			header = strArray[0].split(dataDelimiter);
			values = strArray[1].split(recordDelimiter);

			dataSet.addName(header);
			for (var i=0; i<values.length; i++) {
				var value = values[i].split(dataDelimiter);

				dataSet.addRow();
				for (var j=0; j<value.length; j++) {
					dataSet.setValue(dataSet.getRowCnt()-1, j, value[j]);
				}
			}
		}
		return dataSet;
	},
	/*!
	 * screen layout / look and feel / page loading
	 */
	_doResizeScrollablePanel : function() {
		var isPopup = $.nony.isPopup();
		var isTabFrame = $.nony.isTabFrame();
		var bodyLayout = jsconfig.get("defaultOuterLayoutOption");
		var heightWindow = $(window).innerHeight();
		var mainDivId;
		var heightSum = 0, heightHeader = 0, heightFooter = 0, heightCorrection = 0;
//commonJs.printLog({message:"heightWindow in core.js : "+heightWindow});
//commonJs.printLog({message:"divFrameWindowHolder in core.js : "+$("#divFrameWindowHolder").outerHeight(true)});
		if (isPopup) {
			if ($("#divScrollablePanelPopup").length <= 0) {return;}
		} else if (isTabFrame) {
			if ($("#divScrollablePanelFrame").length <= 0) {return;}
		} else {
			if ($("#divScrollablePanel").length <= 0) {return;}
		}

		if (isPopup) {
			mainDivId = "divPopupWindowHolder";
		} else if (isTabFrame) {
			mainDivId = "divFrameWindowHolder";
		} else {
			mainDivId = "divBodyCenter";
			heightHeader = $("#divHeaderHolder").outerHeight() || 0;
			heightFooter = $("#divFooterHolder").outerHeight() || 0;
		}

		$("#"+mainDivId+" > div").each(function(index) {
			if (isPopup) {
				if (($(this).css("display") != "none") && ($(this).attr("id") != "divScrollablePanelPopup")) {
					heightSum += $(this).outerHeight();
				}
			} else if (isTabFrame) {
				if (($(this).css("display") != "none") && ($(this).attr("id") != "divScrollablePanelFrame")) {
					heightSum += $(this).outerHeight();
				}
			} else {
				if (($(this).css("display") != "none") && ($(this).attr("id") != "divScrollablePanel")) {
					heightSum += $(this).outerHeight();
				}
			}
		});

//		heightSum += ($("#divScrollablePanel").outerHeight() - $("#divScrollablePanel").height());

		if (bodyLayout != null) {
			if (bodyLayout.state.north.isClosed) {
				heightHeader = 0;
			}

			if (bodyLayout.state.south.isClosed) {
				heightFooter = 0;
			}
		}

		var fixedScrollablePanelHeight = $.nony.nvl(jsconfig.get("fixedScrollablePanelHeight"), 0);
		var hFix = ($.nony.isInIgnoreCase(jsconfig.get("themeId"), ["theme000", "theme008"])) ? 0 : 6;
		if (isPopup) {
			if (fixedScrollablePanelHeight > 0) {
				$("#divScrollablePanelPopup").height(fixedScrollablePanelHeight);
			} else {
				heightCorrection = jsconfig.get("scrollablePanelHeightAdjust") || 2;
				$("#divScrollablePanelPopup").height((heightWindow - (heightHeader + heightFooter + heightSum + heightCorrection))+"px");
			}
		} else if (isTabFrame) {
			if (fixedScrollablePanelHeight > 0) {
				$("#divScrollablePanelFrame").height(fixedScrollablePanelHeight);
			} else {
				heightWindow = $(parent.document).find(".frameContainer").height();
				heightCorrection = jsconfig.get("scrollablePanelHeightAdjust") || 2;
				$("#divScrollablePanelFrame").height((heightWindow - (heightHeader + heightFooter + heightSum + heightCorrection))+"px");
//commonJs.printLog({message:"heightWindow(core.js) : "+heightWindow});
			}
		} else {
			if (fixedScrollablePanelHeight > 0) {
				$("#divScrollablePanel").height(fixedScrollablePanelHeight);
			} else {
				heightCorrection = jsconfig.get("scrollablePanelHeightAdjust") || 2;
				$("#divScrollablePanel").height((heightWindow - (heightHeader + heightFooter + heightSum + heightCorrection))+"px");
			}
		}
//commonJs.printLog({message:"isTabFrame(core.js) : "+isTabFrame});
//commonJs.printLog({message:"$(#divScrollablePanelFrame).height(core.js) : "+$("#divScrollablePanelFrame").height()});
		if (!jsconfig.get("isResizeScrollablePanelFuntionRegisteredInResizeEvent")) {
			$(window).resize(function() {
				setTimeout(function() {
					if (!isTabFrame) {
						$.nony._doResizeScrollablePanel();
					}
				}, 10);
			});
		}

		jsconfig.put("isResizeScrollablePanelFuntionRegisteredInResizeEvent", true);
		if (isPopup) {
			jsconfig.put("divScrollablePanelPopupWidth", $("#divScrollablePanelPopup").width());
			jsconfig.put("divScrollablePanelPopupHeight", $("#divScrollablePanelPopup").height());
		} else if (isTabFrame) {
			jsconfig.put("divScrollablePanelFrameWidth", $("#divScrollablePanelFrame").width());
			jsconfig.put("divScrollablePanelFrameHeight", $("#divScrollablePanelFrame").height());
		} else {
			jsconfig.put("divScrollablePanelWidth", $("#divScrollablePanel").width());
			jsconfig.put("divScrollablePanelHeight", $("#divScrollablePanel").height());
		}
	},
	_doPageLayout : function(options) {
		if ($.nony.isEmpty(options)) {return;}

		if ($.nony.isIn($("#hdnHeaderMenuId").val(), ["SYS", "BAU", "M100", "M200", "M300"])) {
			options[1].togglerLength_open = 40;
			options[1].togglerLength_closed = 40;
			options[1].spacing_open = 0,
			options[1].spacing_closed = 0,
			options[1].west__size = 250;
		} else {
			options[1].togglerLength_open = 0;
			options[1].togglerLength_closed = 0;
			options[1].spacing_open = 0,
			options[1].spacing_closed = 0,
			options[1].west__size = 0;

			$("#divBodyCenter").css("border-left", "0px solid #D1D1D1");
		}

		var defaultOuterLayoutOption = $("body").layout(options[0]);
		var defaultInnerLayoutOption = $("#divBodyHolder").layout(options[1]);

		jsconfig.put("defaultOuterLayoutOption", defaultOuterLayoutOption);
		jsconfig.put("defaultInnerLayoutOption", defaultInnerLayoutOption);

		if ("1" == jsconfig.get("submitEffect")) {
			$.nony.showProcMessage(com.message.loading);
		} else if ("2" == jsconfig.get("submitEffect")) {
			$("#divBodyHolder").hide();
		} else if ("3" == jsconfig.get("submitEffect")) {
			$("#divBodyHolder").hide();
			$.nony.showProcMessage(com.message.loading);
		}
	},
	_doPageLoadEffect : function() {
		if ("1" == jsconfig.get("submitEffect")) {
			$.nony.hideProcMessage();
		} else if ("2" == jsconfig.get("submitEffect")) {
			$("#divBodyHolder").slideDown(jsconfig.get("effectDuration"));
		} else if ("3" == jsconfig.get("submitEffect")) {
			$("#divBodyHolder").slideDown(jsconfig.get("effectDuration"));
			$.nony.hideProcMessage();
		}
	},
	_appendAutoSearchCriteria : function(formId) {
		var $form = $("#"+formId);
		var elemNameSuffix = jsconfig.get("searchCriteriaElementSuffix");
		var elemArray = $form.find("#divSearchCriteriaArea").find(":input").serializeArray();

		$.each(elemArray, function(i, elem) {
			var hiddenElem = null;
			var elemName = elem.name + elemNameSuffix;
			var elemValue = elem.value;

			hiddenElem = $("<input type='hidden' id='"+elemName+"' name='"+elemName+"' value='"+elemValue+"'/>");
			hiddenElem.appendTo($form);
		});
	},
	_setAutoSearchCriteria : function(searchCriteriaDataSetString) {
		var strArray, header, value;
		var dataSet = new DataSet();
		var elemNameSuffix = jsconfig.get("searchCriteriaElementSuffix");
		var dataSetHeaderDelimiter = jsconfig.get("dataSetHeaderDelimiter");
		var dataDelimiter = jsconfig.get("dataDelimiter");

		if (!$.nony.isEmpty(searchCriteriaDataSetString) && dataSetHeaderDelimiter != searchCriteriaDataSetString) {
			strArray = searchCriteriaDataSetString.split(dataSetHeaderDelimiter);
			header = strArray[0].split(dataDelimiter);
			value = strArray[1].split(dataDelimiter);

			dataSet.addName(header);
			dataSet.addRow(header.length);
			for (var i=0; i<header.length; i++) {
				dataSet.setValue(0, header[i], value[i]);
			}

			for (var i=0; i<dataSet.getColumnCnt(); i++) {
				var name = dataSet.getName(i);
				var elemName = $.nony.replace(name, elemNameSuffix, "");
				var $elem = $("[name = '"+elemName+"']");

				// For Page Handler Process
//				if (name == "processMessageForFramework_AutoSearchCriteria") {
//					$.nony.printLog({
//						message:dataSet.getValue(0, name)
//					});
//					continue;
//				}

				if ($elem.length > 0) {
					var elemtype = $elem.prop("type");
					var val = dataSet.getValue(0, name);

					if ("checkbox" == elemtype.toLowerCase() || "radio" == elemtype.toLowerCase()) {
						var valArr;
						var dataDelimiter = jsconfig.get("dataDelimiter");

						for (var j=0; j<$elem.length; j++) {
							if (val.indexOf(dataDelimiter) != -1) {
								valArr = val.split(dataDelimiter);

								for (var k=0; k<valArr.length; k++) {
									if ($elem[j].value == valArr[k]) {$elem[j].checked = true;}
								}
							} else {
								if ($elem[j].value == val) {$elem[j].checked = true;}
							}
						}
					} else {
						$("[name = '"+elemName+"']").val(dataSet.getValue(0, name));
					}
				}
			}
		}
	},
	_doAjaxSimpleSuccessProc : function(params, result) {
		if (typeof params.callback == "function") {
			params.callback(result);
		} else if (typeof params.onSuccess == "function") {
			params.onSuccess(result);
		}
	},
	_doAjaxSuccessProc : function(params, result) {
		var msgHandleType = jsconfig.get("pagehandlerActionType");

		if (params.showPostMessage == false) {
			setTimeout(function() {
				if (typeof params.callback == "function") {
					params.callback(result);
				} else if (typeof params.onSuccess == "function") {
					params.onSuccess(result);
				}
			}, 400);
		} else {
			if (msgHandleType == "message") {
				setTimeout(function() {
					$.nony.printProcMessage({
						type:"Success",
						message:result.message,
						onClose:function() {
						}
					});

					if (typeof params.callback == "function") {
						params.callback(result);
					} else if (typeof params.onSuccess == "function") {
						params.onSuccess(result);
					}
				}, 400);
			} else if (msgHandleType == "popup") {
				commonJs.openDialog({
					type:com.message.I000,
					contents:result.message,
					blind:true,
					buttons:[{
						caption:com.caption.ok,
						callback:function() {
							setTimeout(function() {
								if (typeof params.callback == "function") {
									params.callback(result);
								} else if (typeof params.onSuccess == "function") {
									params.onSuccess(result);
								}
							}, 400);
						}
					}]
				});
			}
		}
	},
	_doAjaxErrorProc : function(params, data, result) {
		var msgHandleType = jsconfig.get("pagehandlerActionType");

		if ("SessionTimedOut" == data) {
			if (msgHandleType == "message") {
				$.nony.printProcMessage({
					type:"Error",
					message:com.message.sessionTimeOut,
					onClose:function() {
						try {
							location.replace("/index/index.do");
						} catch(e) {}
					}
				});
			} else if (msgHandleType == "popup") {
				commonJs.openDialog({
					contents:com.message.sessionTimeOut,
					buttons:[{
						caption:com.caption.ok,
						callback:function() {
							location.replace("/index/index.do");
						}
					}],
					width:340,
					blind:true
				});
			}
		} else {
			if (typeof params.onError == "function") {
				params.onError(result);
			} else {
				if (msgHandleType == "message") {
					$.nony.printProcMessage({
						type:"Error",
						message:result.message,
						onClose:function() {
							try {
								$.nony.hideProcMessageOnElement(jsconfig.get("showProcMessageOnElement"));
								$.nony.hideProcMessage();
							} catch(e) {}
						}
					});
				} else if (msgHandleType == "popup") {
					commonJs.openDialog({
						type:com.message.E000,
						contents:result.message
					});

					try {
						$.nony.hideProcMessageOnElement(jsconfig.get("showProcMessageOnElement"));
						$.nony.hideProcMessage();
					} catch(e) {}
				}
			}
		}
	},
	/*!
	 * Logging on console
	 * 		option
	 * 			resetFlag : true / false
	 * 			message : message to display
	 * 			onClose : onclose function
	 */
	printLog : function(params) {
		if ($("#textAreaForLog").length) {
			if (params.resetFlag) {this.debugArea.value = "";}

			this.debugArea.value += (this.debugArea.value == "") ? "[" + $.nony.getSysdate("YYYY-MM-DD HH:mm:ss.SSS") + " DEBUG] " + params.message : "\n" + "[" + $.nony.getSysdate("YYYY-MM-DD HH:mm:ss.SSS") + " DEBUG] " + params.message;
			this.debugArea.scrollTop = this.debugArea.scrollHeight;
		} else {
			var divLogPanelHolder = $("<div id='divLogPanelHolder'></div>");
			var textAreaForLog = document.createElement("textarea");
			var divLogPanelCloser = $("<div id='divLogPanelCloser'></div>");
			var imgFolder = $("<i id='imgLogPanelCloserFolder' class='fa fa-chevron-circle-down fa-lg icnEn' flag='Fold'></i>");
			var imgClose = $("<i id='imgLogPanelColserClose' class='fa fa-times-circle fa-lg icnEn'></i>");
			var holderTop = ($(window).innerHeight() + $(window).scrollTop());

			$(divLogPanelHolder).append(textAreaForLog);
			$(divLogPanelCloser).append(imgFolder);
			$(divLogPanelCloser).append(imgClose);
			$(divLogPanelHolder).append(divLogPanelCloser);
			$("body").append(divLogPanelHolder);

			$(textAreaForLog).attr("id", "textAreaForLog").css({
				"width":($(window).outerWidth() - 34) + "px"
			});

			$("#divLogPanelHolder").css("top", holderTop + "px");
			$("#divLogPanelHolder").animate({
				marginTop:"-" + ($("#divLogPanelHolder").height() + 16) + "px"
			}, 1000);

			// be careful of order
			this.debugArea = textAreaForLog;
			this.printLog(params);

			$(window).scroll(this._reposLogPanel);
			$(window).resize(this._reposLogPanel);

			$(imgFolder).bind("click", function() {
				if ("Fold" == $("#imgLogPanelCloserFolder").attr("flag")) {
					$("#divLogPanelHolder").animate({
						marginTop:"+=142px",
						height:"36px"
					}, 1000, function() {
						$("#imgLogPanelCloserFolder").removeClass("fa-chevron-circle-down").addClass("fa-chevron-circle-up").attr("flag", "Expand");
					});
				} else {
					$("#divLogPanelHolder").animate({
						marginTop:"-=142px",
						height:"180px"
					}, 1000, function() {
						$("#imgLogPanelCloserFolder").removeClass("fa-chevron-circle-up").addClass("fa-chevron-circle-down").attr("flag", "Fold");
					});
				}
			});

			$(imgClose).bind("click", function(event) {
				$("#divLogPanelHolder").stop().animate({
					marginTop:"200px"
				}, 500, function() {
					$("#textAreaForLog").remove();
					$("#divLogPanelHolder").remove();
				});

				if (typeof params.onClose == "function") {
					params.onClose();
				}
			});
		}
	},
	/*!
	 * Logging Process Message
	 * 		option
	 * 			resetFlag : true / false
	 * 			type : Confirm / Confirmation / Error / Information(Default) / Question / Warning / Success
	 * 			message : message to display
	 * 			onClose : onclose function
	 */
	printProcMessage : function(params) {
		if ($("#divMessageAreaForProcMessage").length) {
			if (params.resetFlag) {$(this.procMessageArea).html("");}

			var html = "", str = $(this.procMessageArea).html();
			if (str == "") {
				html += "<table><tr>";
				html += "<td style='vertical-align:top;padding-right:4px;'><img src='"+jsconfig.get("imgThemeCom")+"/"+params.type+"_Small.png"+"'/></td>";
				html += "<td style='padding:2px 4px;line-height:14px;white-space:nowrap;'>"+params.message+"</td>";
				html += "</tr></table>";
			} else {
				html += "<div class='verGap2'></div>";
				html += "<table><tr>";
				html += "<td style='vertical-align:top;padding-right:4px;'><img src='"+jsconfig.get("imgThemeCom")+"/"+params.type+"_Small.png"+"'/></td>";
				html += "<td style='padding:2px 4px;line-height:14px;white-space:nowrap;'>"+params.message+"</td>";
				html += "</tr></table>";
			}
			str += html;

			$(this.procMessageArea).html(str);

			$(this.procMessageArea).animate({scrollTop:$(this.procMessageArea).prop("scrollHeight")}, 1000);
		} else {
			var divLogPanelHolder = $("<div id='divLogPanelHolder'></div>");
			var divMessageAreaForProcMessage = $("<div id='divMessageAreaForProcMessage' style='float:left;width:98%'></div>");
			var divLogPanelCloser = $("<div id='divLogPanelCloser' style='float:right;width:1%'></div>");
			var imgFolder = $("<i id='imgLogPanelCloserFolder' class='fa fa-chevron-circle-down fa-lg icnEn' flag='Fold'></i>");
			var imgClose = $("<i id='imgLogPanelColserClose' class='fa fa-times-circle fa-lg icnEn'></i>");
			var holderTop = ($(window).innerHeight() + $(window).scrollTop());

			$(divLogPanelHolder).append(divMessageAreaForProcMessage);
			$(divLogPanelCloser).append(imgFolder);
			$(divLogPanelCloser).append(imgClose);
			$(divLogPanelHolder).append(divLogPanelCloser);
			$("body").append(divLogPanelHolder);

			$(divMessageAreaForProcMessage).css({
				"width":($(window).outerWidth() - 34) + "px"
			});

			$("#divLogPanelHolder").css("top", holderTop + "px");
			$("#divLogPanelHolder").animate({
				marginTop:"-" + ($("#divLogPanelHolder").height() + 16) + "px"
			}, 1000);

			// be careful of order
			this.procMessageArea = divMessageAreaForProcMessage;
			this.printProcMessage(params);

			$(window).scroll(this._reposProcMessage);
			$(window).resize(this._reposProcMessage);

			$(imgFolder).bind("click", function() {
				if ("Fold" == $("#imgLogPanelCloserFolder").attr("flag")) {
					$("#divLogPanelHolder").animate({
						marginTop:"+=70px",
						height:"34px"
					}, 800, function() {
						$("#imgLogPanelCloserFolder").removeClass("fa-chevron-circle-down").addClass("fa-chevron-circle-up").attr("flag", "Expand");
					});
				} else {
					$("#divLogPanelHolder").animate({
						marginTop:"-=70px",
						height:"180px"
					}, 800, function() {
						$("#imgLogPanelCloserFolder").removeClass("fa-chevron-circle-up").addClass("fa-chevron-circle-down").attr("flag", "Fold");
					});
				}
			});

			$(imgClose).bind("click", function(event) {
				$("#divLogPanelHolder").stop().animate({
					marginTop:"200px"
				}, 500, function() {
					$("#divMessageAreaForProcMessage").remove();
					$("#divLogPanelHolder").remove();
				});

				if (typeof params.onClose == "function") {
					params.onClose();
				}
			});
		}
	},
	/*!
	 * private methods
	 */
	_reposLogPanel : function() {
		if (nony.debugArea) {
			$("#textAreaForLog").css("width", ($(window).width() - 34) + "px");
			$("#divLogPanelHolder").css("top", ($(window).innerHeight() - ($("#divLogPanelHolder").height() + 16)) + "px");
			$("#divLogPanelHolder").stop().animate({"margin-top":$(window).scrollTop() + "px"}, "slow", "swing");
			$("#textAreaForLog").css("width", ($(window).width() - 34) + "px");
			$("#divLogPanelHolder").css("top", ($(window).innerHeight() - ($("#divLogPanelHolder").height() + 16)) + "px");
		}
	},
	_reposProcMessage : function() {
		if (nony.procMessageArea) {
			$("#divMessageAreaForProcMessage").css("width", ($(window).width() - 34) + "px");
			$("#divLogPanelHolder").css("top", ($(window).innerHeight() - ($("#divLogPanelHolder").height() + 16)) + "px");
			$("#divLogPanelHolder").stop().animate({"margin-top":$(window).scrollTop() + "px"}, "slow", "swing");
			$("#divMessageAreaForProcMessage").css("width", ($(window).width() - 34) + "px");
			$("#divLogPanelHolder").css("top", ($(window).innerHeight() - ($("#divLogPanelHolder").height() + 16)) + "px");
		}
	}
};

/**
 * plugin(define nony)
 */
(function($) {
	$.nony = nony;
})(jQuery);