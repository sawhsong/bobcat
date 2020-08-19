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
	}
};