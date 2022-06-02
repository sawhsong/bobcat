/**
* UIElementsForGrid JavaScript - Used only for grid table
* 	- Returns string to render ui elements
* UiGridTr
* UiGridTd
* UiAnchor
* UiCheckbox
* UiIcon
* UiRadio
*/

/**
 * Table.tr - For Data Grid only
 */
UiGridTr = Class.create();
UiGridTr.prototype = {
	/**
	 * Constructor
	 */
	initialize : function() {
		this.className = "";
		this.style = "";
		this.childList= new Array();
	},
	/**
	 * Setter / Getter
	 */
	setClassName : function(className) {this.className = className; return this;},
	setStyle : function(style) {this.style = style; return this;},
	/**
	 * Method
	 */
	addClassName : function(className) {this.className += ($.nony.isEmpty(this.className)) ? className : " "+className; return this;},
	removeClassName : function(className) {
		if (!$.nony.isEmpty(this.className)) {this.className.replace(className, "");}
		return this;
	},
	addChild : function(obj) {this.childList.push(obj); return this;},
	/**
	 * toString
	 */
	toHtmlString : function() {
		var str = "";

		str += "<tr";
		if (!$.nony.isEmpty(this.className)) {str += " class=\""+this.className+"\"";}
		if (!$.nony.isEmpty(this.style)) {str += " style=\""+this.style+"\"";}
		str += ">";
		if (this.childList != null && this.childList.length > 0) {
			for (var i=0; i<this.childList.length; i++) {
				str += this.childList[i].toHtmlString();
			}
		}
		str += "</tr>";

		return str;
	}
};

/**
 * Table.td - For Data Grid only
 */
UiGridTd = Class.create();
UiGridTd.prototype = {
	/**
	 * Constructor
	 */
	initialize : function() {
		this.className = "tdGrid";
		this.style = "";
		this.attribute = "";
		this.text = "";
		this.textBeforeChild = "";
		this.textAfterChild = "";
		this.script = "";
		this.childList= new Array();
	},
	/**
	 * Setter / Getter
	 */
	setClassName : function(className) {this.className = className; return this;},
	setStyle : function(style) {this.style = style; return this;},
	setText : function(text) {this.text = text; return this;},
	setAttribute : function(attributes) {this.attribute = attributes; return this;},
	setScript : function(script) {this.script = script; return this;},
	/**
	 * Method
	 */
	addClassName : function(className) {this.className += ($.nony.isEmpty(this.className)) ? className : " "+className; return this;},
	removeClassName : function(className) {
		if (!$.nony.isEmpty(this.className)) {this.className.replace(className, "");}
		return this;
	},
	addAttribute : function(attribute) {this.attribute += ($.nony.isEmpty(this.attribute)) ? attribute : ";"+attribute; return this;},
	addChild : function(obj) {this.childList.push(obj); return this;},
	addTextBeforeChild : function(text) {this.textBeforeChild = text; return this},
	addTextAfterChild : function(text) {this.textAfterChild = text; return this},
	/**
	 * toString
	 */
	toHtmlString : function() {
		var str = "", attrArray = null, key = "", val = "";

		str += "<td";
		if (!$.nony.isEmpty(this.className)) {str += " class=\""+this.className+"\"";}
		if (!$.nony.isEmpty(this.style)) {str += " style=\""+this.style+"\"";}
		if (!$.nony.isEmpty(this.script)) {str += " onclick=\""+this.script+"\"";}
		if (!$.nony.isEmpty(this.attribute)) {
			attrArray = this.attribute.split(";");
			for (var i=0; i<attrArray.length; i++) {
				var keyVal = attrArray[i].split(":");
				key = keyVal[0];
				val = keyVal[1];

				str += " "+key+"=\""+val+"\"";
			}
		}
		str += ">";
		if (!$.nony.isEmpty(this.textBeforeChild)) {str += this.textBeforeChild;}
		if (this.childList != null && this.childList.length > 0) {
			for (var i=0; i<this.childList.length; i++) {
				str += this.childList[i].toHtmlString();
			}
		}
		if (!$.nony.isEmpty(this.text)) {str += this.text;}
		if (!$.nony.isEmpty(this.textAfterChild)) {str += this.textAfterChild;}
		str += "</td>";

		return str;
	}
};

/**
 * Anchor - For Data Grid only
 */
UiAnchor = Class.create();
UiAnchor.prototype = {
	/**
	 * Constructor
	 */
	initialize : function() {
		this.id = "";
		this.className = "aEn";
		this.style = "";
		this.script = "";
		this.text = ""
	},
	/**
	 * Setter / Getter
	 */
	setId : function(id) {this.id = id; return this;},
	setClassName : function(className) {this.className = className; return this;},
	setStyle : function(style) {this.style = style; return this;},
	setScript : function(script) {this.script = script; return this;},
	setText : function(text) {this.text = text; return this;},
	/**
	 * Method
	 */
	addClassName : function(className) {this.className += ($.nony.isEmpty(this.className)) ? className : " "+className; return this;},
	removeClassName : function(className) {
		if (!$.nony.isEmpty(this.className)) {this.className.replace(className, "");}
		return this;
	},
	/**
	 * toString
	 */
	toHtmlString : function() {
		var str = "";

		str += "<a";
		if (!$.nony.isEmpty(this.id)) {str += " id=\""+this.id+"\"";}
		if (!$.nony.isEmpty(this.className)) {str += " class=\""+this.className+"\"";}
		if (!$.nony.isEmpty(this.style)) {str += " style=\""+this.style+"\"";}
		if (!$.nony.isEmpty(this.script)) {str += " onclick=\""+this.script+"\"";}
		str += ">";
		if (!$.nony.isEmpty(this.text)) {str += this.text;}
		str += "</a>";

		return str;
	}
};

/**
 * Checkbox - For Data Grid only
 */
UiCheckbox = Class.create();
UiCheckbox.prototype = {
	/**
	 * Constructor
	 * 		attribute : [attrA:attrVal;attrB:attrVal;...]
	 * 		options : [optionA optionB optionC...]
	 */
	initialize : function() {
		this.id = "";
		this.name = "";
		this.className = "chkEn inTblGrid";
		this.style = "";
		this.script = "";
		this.value = "";
		this.attribute = "";
		this.options = "";
	},
	/**
	 * Setter / Getter
	 */
	setId : function(id) {this.id = id; return this;},
	setName : function(name) {this.name = name; return this;},
	setClassName : function(className) {this.className = className; return this;},
	setStyle : function(style) {this.style = style; return this;},
	setScript : function(script) {this.script = script; return this;},
	setValue : function(value) {this.value = value; return this;},
	setAttribute : function(attributes) {this.attribute = attributes; return this;},
	setOptions : function(options) {this.options = options; return this;},
	/**
	 * Method
	 */
	addClassName : function(className) {this.className += ($.nony.isEmpty(this.className)) ? className : " "+className; return this;},
	removeClassName : function(className) {
		if (!$.nony.isEmpty(this.className)) {this.className.replace(className, "");}
		return this;
	},
	addAttribute : function(attribute) {this.attribute += ($.nony.isEmpty(this.attribute)) ? attribute : ";"+attribute; return this;},
	addOptions : function(options) {this.options += ($.nony.isEmpty(this.options)) ? options : " "+options; return this;},
	/**
	 * toString
	 */
	toHtmlString : function() {
		var str = "";

		str += "<input type=\"checkbox\"";
		if (!$.nony.isEmpty(this.id)) {str += " id=\""+this.id+"\"";}
		if (!$.nony.isEmpty(this.name)) {str += " name=\""+this.name+"\"";}
		if (!$.nony.isEmpty(this.className)) {str += " class=\""+this.className+"\"";}
		if (!$.nony.isEmpty(this.style)) {str += " style=\""+this.style+"\"";}
		if (!$.nony.isEmpty(this.script)) {str += " onclick=\""+this.script+"\"";}
		if (!$.nony.isEmpty(this.value)) {str += " value=\""+this.value+"\"";}
		if (!$.nony.isEmpty(this.attribute)) {
			attrArray = this.attribute.split(";");
			for (var i=0; i<attrArray.length; i++) {
				var keyVal = attrArray[i].split(":");
				key = keyVal[0];
				val = keyVal[1];

				str += " "+key+"=\""+val+"\"";
			}
		}
		if (!$.nony.isEmpty(this.options)) {str += " "+this.options+"";}
		str += "/>";

		return str;
	}
};

/**
 * Icon - For Data Grid only
 */
UiIcon = Class.create();
UiIcon.prototype = {
	/**
	 * Constructor
	 */
	initialize : function() {
		this.id = "";
		this.name = "";
		this.className = "icnEn";
		this.style = "";
		this.script = "";
		this.attribute = "";
	},
	/**
	 * Setter / Getter
	 */
	setId : function(id) {this.id = id; return this;},
	setName : function(name) {this.name = name; return this;},
	setClassName : function(className) {
		var classNamePrefix = "";

		if ($.nony.startsWith(className, "fa-")) {
			classNamePrefix = "fa";
		} else if ($.nony.startsWith(className, "glyphicon-")) {
			classNamePrefix = "glyphicon";
		}
		this.className += ($.nony.isEmpty(this.className)) ? classNamePrefix+" "+className : " "+classNamePrefix+" "+className;

		return this;
	},
	setStyle : function(style) {this.style = style; return this;},
	setScript : function(script) {this.script = script; return this;},
	setAttribute : function(attributes) {this.attribute = attributes; return this;},
	setUseFor : function(useFor) {
		if ($.nony.isNotBlank(useFor)) {
			var classNamePrefix = "", cName = "";

			if ($.nony.lowerCase(useFor) == "checkgrid") {cName = "fa-check-square-o fa-lg";}
			else if ($.nony.lowerCase(useFor) == "radiogrid") {cName = "fa-dot-circle-o fa-lg";}
			else if ($.nony.lowerCase(useFor) == "action") {cName = "fa-ellipsis-h fa-lg";}
			else if ($.nony.lowerCase(useFor) == "calendar") {cName = "fa-calendar";}
			else if ($.nony.lowerCase(useFor) == "refresh") {cName = "fa-refresh fa-lg";}
			else if ($.nony.lowerCase(useFor) == "delete") {cName = "fa-times fa-lg";}
			else if ($.nony.lowerCase(useFor) == "lookup") {cName = "fa-search";}
			else if ($.nony.lowerCase(useFor) == "sort") {cName = "fa-sort fa-lg";}

			if ($.nony.startsWith(cName, "fa-")) {
				classNamePrefix = "fa";
			} else if ($.nony.startsWith(cName, "glyphicon-")) {
				classNamePrefix = "glyphicon";
			}
			this.className += ($.nony.isEmpty(this.className)) ? classNamePrefix+" "+cName : " "+classNamePrefix+" "+cName;
		}

		return this;
	},
	/**
	 * Method
	 */
	addClassName : function(className) {
		var classNamePrefix = "";

		if ($.nony.startsWith(className, "fa-")) {
			classNamePrefix = "fa";
		} else if ($.nony.startsWith(className, "glyphicon-")) {
			classNamePrefix = "glyphicon";
		}
		this.className += ($.nony.isEmpty(this.className)) ? classNamePrefix+" "+className : " "+classNamePrefix+" "+className;
		return this;
	},
	removeClassName : function(className) {
		if (!$.nony.isEmpty(this.className)) {this.className.replace(className, "");}
		return this;
	},
	addAttribute : function(attribute) {this.attribute += ($.nony.isEmpty(this.attribute)) ? attribute : ";"+attribute; return this;},
	/**
	 * toString
	 */
	toHtmlString : function() {
		var str = "";

		str += "<i";
		if (!$.nony.isEmpty(this.id)) {str += " id=\""+this.id+"\"";}
		if (!$.nony.isEmpty(this.name)) {str += " name=\""+this.name+"\"";}
		if (!$.nony.isEmpty(this.className)) {str += " class=\""+this.className+"\"";}
		if (!$.nony.isEmpty(this.style)) {str += " style=\""+this.style+"\"";}
		if (!$.nony.isEmpty(this.script)) {str += " onclick=\""+this.script+"\"";}
		if (!$.nony.isEmpty(this.attribute)) {
			attrArray = this.attribute.split(";");
			for (var i=0; i<attrArray.length; i++) {
				var keyVal = attrArray[i].split(":");
				key = keyVal[0];
				val = keyVal[1];

				str += " "+key+"=\""+val+"\"";
			}
		}
		str += ">";
		str += "</i>";

		return str;
	}
};

/**
 * Radio button - For Data Grid only
 */
UiRadio = Class.create();
UiRadio.prototype = {
	/**
	 * Constructor
	 * 		attribute : [attrA:attrVal;attrB:attrVal;...]
	 * 		options : [optionA optionB optionC...]
	 */
	initialize : function() {
		this.id = "";
		this.name = "";
		this.className = "rdoEn inTblGrid";
		this.style = "";
		this.script = "";
		this.value = "";
		this.attribute = "";
		this.options = "";
	},
	/**
	 * Setter / Getter
	 */
	setId : function(id) {this.id = id; return this;},
	setName : function(name) {this.name = name; return this;},
	setClassName : function(className) {this.className = className; return this;},
	setStyle : function(style) {this.style = style; return this;},
	setScript : function(script) {this.script = script; return this;},
	setValue : function(value) {this.value = value; return this;},
	setAttribute : function(attributes) {this.attribute = attributes; return this;},
	setOptions : function(options) {this.options = options; return this;},
	/**
	 * Method
	 */
	addClassName : function(className) {this.className += ($.nony.isEmpty(this.className)) ? className : " "+className; return this;},
	removeClassName : function(className) {
		if (!$.nony.isEmpty(this.className)) {this.className.replace(className, "");}
		return this;
	},
	addAttribute : function(attribute) {this.attribute += ($.nony.isEmpty(this.attribute)) ? attribute : ";"+attribute; return this;},
	addOptions : function(options) {this.options += ($.nony.isEmpty(this.options)) ? options : " "+options; return this;},
	/**
	 * toString
	 */
	toHtmlString : function() {
		var str = "";

		str += "<input type=\"radio\"";
		if (!$.nony.isEmpty(this.id)) {str += " id=\""+this.id+"\"";}
		if (!$.nony.isEmpty(this.name)) {str += " name=\""+this.name+"\"";}
		if (!$.nony.isEmpty(this.className)) {str += " class=\""+this.className+"\"";}
		if (!$.nony.isEmpty(this.style)) {str += " style=\""+this.style+"\"";}
		if (!$.nony.isEmpty(this.script)) {str += " onclick=\""+this.script+"\"";}
		if (!$.nony.isEmpty(this.value)) {str += " value=\""+this.value+"\"";}
		if (!$.nony.isEmpty(this.attribute)) {
			attrArray = this.attribute.split(";");
			for (var i=0; i<attrArray.length; i++) {
				var keyVal = attrArray[i].split(":");
				key = keyVal[0];
				val = keyVal[1];

				str += " "+key+"=\""+val+"\"";
			}
		}
		if (!$.nony.isEmpty(this.options)) {str += " "+this.options+"";}
		str += "/>";

		return str;
	}
};