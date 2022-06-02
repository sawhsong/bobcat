/**
* UIElements JavaScript
* 	- Returns string to render ui elements
* 	- Same as taglib
*/
var uiElements = {
	getUiHidden : (params) => {
		/*
		 * id
		 * name
		 * className
		 * value
		 * style
		 * checkName
		 * attribute
		 */
		var html = "", attrStr = "", attrs = new Array(), attr = new Array();

		if ($.nony.isNotBlank(params.attribute)) {
			attrs = params.attribute.split(";");
			for (var i=0; i<attrs.length; i++) {
				attr = attrs[i].split(":");
				attrStr += " "+attr[0]+"=\""+attr[1]+"\"";
			}
		}

		html += "<input type=\"hidden\"";
		html += " id=\""+$.nony.nvl(params.id, params.name)+"\"";
		html += " name=\""+params.name+"\"";

		if ($.nony.isNotBlank(params.className)) {html += " class=\""+params.className+"\"";}
		if ($.nony.isNotBlank(params.value)) {html += " value=\""+params.value+"\"";}
		if ($.nony.isNotBlank(params.style)) {html += " style=\""+params.style+"\"";}
		if ($.nony.isNotBlank(params.checkName)) {html += " checkName=\""+params.checkName+"\"";}
		if ($.nony.isNotBlank(attrStr)) {html += " "+attrStr+"";}

		html += "/>";

		return html;
	},
	getUiTextbox : (params) => {
		/*
		 * id
		 * name
		 * className
		 * value
		 * style
		 * script
		 * title
		 * placeHolder
		 * checkName
		 * maxlength
		 * minlength
		 * checkFlag
		 * option
		 * options
		 * attribute
		 * status
		 */
		var html = "", className = ""; classNamePrefix = "", scriptStr = "", attrStr = "", options = "";
		var scripts = new Array(), eventFunc = new Array(), attrs = new Array(), attr = new Array();

		if ($.nony.containsIgnoreCase(params.status, "disabled")) {
			options += ($.nony.isBlank(params.options)) ? "readonly" : " readonly";
			classNamePrefix = "txtDis";
		} else if ($.nony.containsIgnoreCase(params.status, "display")) {
			options += ($.nony.isBlank(params.options)) ? "readonly" : " readonly";
			classNamePrefix = "txtDpl";
		} else if ($.nony.containsIgnoreCase(params.status, "spinner")) {
			classNamePrefix = "txtSpinner";
		} else {
			classNamePrefix = "txtEn";
		}
		className = ($.nony.isBlank(params.className)) ? classNamePrefix : classNamePrefix+" "+params.className;

		if ($.nony.isNotBlank(params.script)) {
			scripts = params.script.split(";");
			for (var i=0; i<scripts.length; i++) {
				eventFunc = scripts[i].split(":");
				scriptStr += " "+eventFunc[0]+"=\""+eventFunc[1]+"\"";
			}
		}

		if ($.nony.isNotBlank(params.attribute)) {
			attrs = params.attribute.split(";");
			for (var i=0; i<attrs.length; i++) {
				attr = attrs[i].split(":");
				attrStr += " "+attr[0]+"=\""+attr[1]+"\"";
			}
		}

		html += "<input type=\"text\"";
		html += " id=\""+$.nony.nvl(params.id, params.name)+"\"";
		html += " name=\""+params.name+"\"";

		if ($.nony.isNotBlank(className)) {html += " class=\""+className+"\"";}
		if ($.nony.isNotBlank(params.value)) {html += " value=\""+params.value+"\"";}
		if ($.nony.isNotBlank(params.style)) {html += " style=\""+params.style+"\"";}
		if ($.nony.isNotBlank(scriptStr)) {html += " "+scriptStr+"";}
		if ($.nony.isNotBlank(params.title)) {html += " title=\""+params.title+"\"";}
		if ($.nony.isNotBlank(params.placeHolder)) {html += " placeholder=\""+params.placeHolder+"\"";}
		if ($.nony.isNotBlank(params.checkName)) {html += " checkName=\""+params.checkName+"\"";}
		if ($.nony.isNotBlank(params.maxlength)) {html += " maxlength=\""+params.maxlength+"\"";}
		if ($.nony.isNotBlank(params.minlength)) {html += " minlength=\""+params.minlength+"\"";}
		if ($.nony.isNotBlank(params.checkFlag)) {html += " checkFlag=\""+params.checkFlag+"\"";}
		if ($.nony.isNotBlank(params.option)) {html += " option=\""+params.option+"\"";}
		if ($.nony.isNotBlank(attrStr)) {html += " "+attrStr+"";}
		if ($.nony.isNotBlank(params.options)) {html += " "+params.options;}

		html += "/>";

		return html;
	},
	getUiRadio : function(params) {
		/*
		 * name
		 * value
		 * text
		 * id
		 * isSelected
		 * isDisabled
		 * script
		 * labelClassName
		 * inputClassName
		 * labelStyle
		 * inputStyle
		 * displayType
		 * isBootstrap
		 * isCustomised
		 * status
		 * options
		 * attribute
		 */

		params.isBootstrap = (params.isBootstrap == false) ? false : true;

		var html = "", langCode = jsconfig.get("langCode"), disabledString = "", classSuffix = "", attrStr = "", classNameCustomised = "", attrs = new Array(), attr = new Array();

		if ($.nony.toBoolean(params.isCustomised)) {
			isBootstrap = "true";
			classNameCustomised = "-custom";
			text = "<span>"+params.text+"</span>";
			inputClassName = ($.nony.isBlank(params.inputClassName)) ? "custom" : "custom"+" "+params.inputClassName;
		}

		if ($.nony.isNotBlank(params.attribute)) {
			attrs = params.attribute.split(";");
			for (var i=0; i<attrs.length; i++) {
				attr = attrs[i].split(":");
				attrStr += " "+attr[0]+"=\""+attr[1]+"\"";
			}
		}

		if ($.nony.toBoolean(params.isBootstrap)) {
			if ($.nony.toBoolean(params.isDisabled) || "disabled" == $.nony.lowerCase(params.status)) {
				disabledString = " disabled";
			}

			if ("block" == $.nony.lowerCase(params.displayType)) {
				html += "<div class=\"radio"+classNameCustomised+disabledString+"\"><label";

				if ($.nony.isNotBlank(params.labelClassName)) {html += " class=\""+params.labelClassName+"\"";}
				if ($.nony.isNotBlank(params.labelStyle)) {html += " style=\""+params.labelStyle+"\"";}
				html += "><input type=\"radio\" name=\""+params.name+"\" value=\""+params.value+"\"";
				if ($.nony.isNotBlank(params.id)) {html += " id=\""+params.id+"\"";}
				if ($.nony.isNotBlank(disabledString)) {html += " "+disabledString;}
				if ($.nony.isNotBlank(inputClassName)) {html += " class=\""+inputClassName+"\"";}
				if ($.nony.isNotBlank(params.inputStyle)) {html += " style=\""+params.inputStyle+"\"";}
				if ($.nony.toBoolean(params.isSelected)) {html += " checked";}
				if ($.nony.isNotBlank(params.options)) {html += " "+params.options;}
				if ($.nony.isNotBlank(params.script)) {html += " onclick=\""+params.script+"\"";}
				if ($.nony.isNotBlank(attrStr)) {html += " "+attrStr+"";}

				html += "/>"+text+"</label></div>";
			} else {
				html += "<label class=\"radio-inline"+classNameCustomised+disabledString;
				if ($.nony.isNotBlank(params.labelClassName)) {html += " "+params.labelClassName+"";}
				html += "\"";
				if ($.nony.isNotBlank(params.labelStyle)) {html += " style=\""+params.labelStyle+"\"";}
				html += "><input type=\"radio\" name=\""+params.name+"\" value=\""+params.value+"\"";
				if ($.nony.isNotBlank(params.id)) {html += " id=\""+params.id+"\"";}
				if ($.nony.isNotBlank(disabledString)) {html += " "+disabledString;}
				if ($.nony.isNotBlank(inputClassName)) {html += " class=\""+inputClassName+"\"";}
				if ($.nony.isNotBlank(params.inputStyle)) {html += " style=\""+params.inputStyle+"\"";}
				if ($.nony.toBoolean(params.isSelected)) {html += " checked";}
				if ($.nony.isNotBlank(params.options)) {html += " "+params.options;}
				if ($.nony.isNotBlank(params.script)) {html += " onclick=\""+params.script+"\"";}
				if ($.nony.isNotBlank(attrStr)) {html += " "+attrStr+"";}

				html += "/>"+text+"</label>";
			}
		} else {
			if ($.nony.toBoolean(params.isDisabled) || "disabled" == $.nony.lowerCase(params.status)) {
				classSuffix = "Dis";
				disabledString = " disabled";
			} else {
				classSuffix = "En";
			}

			if ("block" == $.nony.lowerCase(params.displayType)) {
				html += "<label class=\"lblRadio"+classSuffix;
				html += " block";
				if ($.nony.isNotBlank(params.labelClassName)) {html += " "+params.labelClassName+"";}
				html += "\"";
				if ($.nony.isNotBlank(params.labelStyle)) {html += " style=\""+params.labelStyle+"\"";}
				html += "><input type=\"radio\" name=\""+params.name+"\" value=\""+params.value+"\" class=\"rdo"+classSuffix;
				if ($.nony.isNotBlank(inputClassName)) {html += " "+inputClassName+"";}
				html += "\"";
				if ($.nony.isNotBlank(params.id)) {html += " id=\""+params.id+"\"";}
				if ($.nony.isNotBlank(params.inputStyle)) {html += " style=\""+params.inputStyle+"\"";}
				if ($.nony.isNotBlank(disabledString)) {html += " "+disabledString;}
				if ($.nony.toBoolean(params.isSelected)) {html += " checked";}
				if ($.nony.isNotBlank(params.options)) {html += " "+params.options;}
				if ($.nony.isNotBlank(params.script)) {html += " onclick=\""+params.script+"\"";}
				if ($.nony.isNotBlank(attrStr)) {html += " "+attrStr+"";}

				html += "/>"+text+"</label>";
			} else {
				html += "<label class=\"lblRadio"+classSuffix;
				if ($.nony.isNotBlank(labelClassName)) {html += " "+labelClassName+"";}
				html += "\"";
				if ($.nony.isNotBlank(params.labelStyle)) {html += " style=\""+params.labelStyle+"\"";}
				html += "><input type=\"radio\" name=\""+params.name+"\" value=\""+params.value+"\" class=\"rdo"+classSuffix;
				if ($.nony.isNotBlank(inputClassName)) {html += " "+inputClassName+"";}
				html += "\"";
				if ($.nony.isNotBlank(params.id)) {html += " id=\""+params.id+"\"";}
				if ($.nony.isNotBlank(params.inputStyle)) {html += " style=\""+params.inputStyle+"\"";}
				if ($.nony.isNotBlank(disabledString)) {html += " "+disabledString;}
				if ($.nony.toBoolean(params.isSelected)) {html += " checked";}
				if ($.nony.isNotBlank(params.options)) {html += " "+params.options;}
				if ($.nony.isNotBlank(params.script)) {html += " onclick=\""+params.script+"\"";}
				if ($.nony.isNotBlank(attrStr)) {html += " "+attrStr+"";}

				html += "/>"+text+"</label>";
			}
		}

		return html;
	},
	getUiSelectOption : function(params) {
		/*
		 * value
		 * text
		 * isSelected
		 * isDisabled
		 * attribute
		 */

		var html = "", attrString = "", attrs = new Array(), attr = new Array();

		if ($.nony.isNotBlank(params.attribute)) {
			attrs = params.attribute.split(";");
			for (var i=0; i<attrs.length; i++) {
				attr = attrs[i].split(":");
				attrString += " "+attr[0]+"=\""+attr[1]+"\"";
			}
		}

		html += "<option value=\""+params.value+"\"";

		if ($.nony.isNotBlank(attrString)) {html += " "+attrString+"";}
		if ($.nony.toBoolean(params.isSelected)) {html += " selected";}
		if ($.nony.toBoolean(params.isDisabled)) {html += " disabled";}

		html += ">"+params.text+"";
		html += "</option>\n";

		return html;
	},
	getUiSelectOptionWithCommonCode : function(params) {
		/*
		 * selectboxId
		 * codeType
		 */

		var id = params.selectboxId;

		$.nony.doSearch({
			url:"/common/lookup/getCommonCodeForSelectbox.do",
			noForm:true,
			data:params,
			onSuccess:function(result) {
				var ds = result.dataSet;
				for (var i=0; i<ds.getRowCnt(); i++) {
					$("#"+id).append(uiElements.getUiSelectOption({
						value:ds.getValue(i, "COMMON_CODE"),
						text:ds.getValue(i, "CODE_MEANING")
					}));
				}

				$("#"+id).selectpicker("refresh");
				$("#"+id).selectpicker("render");
			}
		});
	},
	getUiImage : function(params) {
		/*
		 * id
		 * src
		 * className
		 * isDisabled
		 * attribute
		 * style
		 */

		var html = "", attrString = "", attrs = new Array(), attr = new Array();
		var className = (params.isDisabled == true) ? "imgDis" : "imgEn";

		if ($.nony.isNotBlank(params.attribute)) {
			attrs = params.attribute.split(";");
			for (var i=0; i<attrs.length; i++) {
				attr = attrs[i].split(":");
				attrString += " "+attr[0]+"=\""+attr[1]+"\"";
			}
		}

		className = $.nony.isNotBlank(params.className) ? className+" "+params.className : className;

		html += "<img"+" "+className;

		if ($.nony.isNotBlank(params.id)) {html += " id=\""+params.id+"\"";}
		if ($.nony.isNotBlank(params.src)) {html += " src=\""+params.src+"\"";}
		if ($.nony.isNotBlank(params.style)) {html += " style=\""+params.style+"\"";}
		if ($.nony.isNotBlank(attrString)) {html += " "+attrString+"";}
		if ($.nony.toBoolean(params.isDisabled)) {html += " disabled";}

		html += "/>";

		return html;
	},
	getUiIcon : (params) => {
		/*
		 * id
		 * title
		 * className
		 * style
		 * attribute
		 * script
		 * status
		 */

		var html = "", attrString = "", classNamePrefix = "", className = "", attrs = new Array(), attr = new Array();

		if ($.nony.containsIgnoreCase(params.status, "display")) {
		} else if ($.nony.containsIgnoreCase(params.status, "disabled")) {
			classNamePrefix = "icnDis";
		} else {
			classNamePrefix = "icnEn";
		}

		if ($.nony.isNotBlank(useFor)) {
			if ($.nony.lowerCase(useFor) == "checkgrid") {params.className = params.className+" "+"fa-check-square-o fa-lg";}
			else if ($.nony.lowerCase(useFor) == "radiogrid") {params.className = params.className+" "+"fa-dot-circle-o fa-lg";}
			else if ($.nony.lowerCase(useFor) == "action") {params.className = params.className+" "+"fa-ellipsis-h fa-lg";}
			else if ($.nony.lowerCase(useFor) == "calendar") {params.className = params.className+" "+"fa-calendar";}
			else if ($.nony.lowerCase(useFor) == "refresh") {params.className = params.className+" "+"fa-refresh fa-lg";}
			else if ($.nony.lowerCase(useFor) == "delete") {params.className = params.className+" "+"fa-times fa-lg";}
			else if ($.nony.lowerCase(useFor) == "lookup") {params.className = params.className+" "+"fa-search";}
			else if ($.nony.lowerCase(useFor) == "sort") {params.className = params.className+" "+"fa-sort fa-lg";}
		}

		if ($.nony.containsIgnoreCase(params.className, "fa-")) {
			classNamePrefix += " fa";
		} else if ($.nony.containsIgnoreCase(params.className, "glyphicon-")) {
			classNamePrefix += " glyphicon";
		}
		className = ($.nony.isBlank(params.className)) ? classNamePrefix : classNamePrefix+" "+params.className;

		if ($.nony.isNotBlank(params.attribute)) {
			attrs = params.attribute.split(";");
			for (var i=0; i<attrs.length; i++) {
				attr = attrs[i].split(":");
				attrString += " "+attr[0]+"=\""+attr[1]+"\"";
			}
		}

		html += "<i";

		if ($.nony.isNotBlank(params.id)) {html += " id=\""+params.id+"\"";}
		if ($.nony.isNotBlank(params.title)) {html += " id=\""+params.title+"\"";}
		if ($.nony.isNotBlank(className)) {html += " class=\""+className+"\"";}
		if ($.nony.isNotBlank(params.style)) {html += " style=\""+params.style+"\"";}
		if ($.nony.isNotBlank(params.script)) {html += " onclick=\""+params.script+"\"";}
		if ($.nony.isNotBlank(attrString)) {html += " "+attrString+"";}

		html += "></i>";

		return html;
	}
};