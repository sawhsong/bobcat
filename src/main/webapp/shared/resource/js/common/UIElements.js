/**
* UIElements JavaScript
* 	- Returns string to render ui elements
* 	- Same as taglib
*/
var uiElements = {
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
	}
};