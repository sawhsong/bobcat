/**
 * Checkbox - For Data Grid only
 */
UiCheckbox = Class.create();
UiCheckbox.prototype = {
	/**
	 * Constructor
	 */
	initialize : function() {
		this.id = "";
		this.name = "";
		this.className = "chkEn inTblGrid";
		this.style = "";
		this.script = "";
		this.value = ""
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

		str += "<input type=\"checkbox\" id=\""+this.id+"\" name=\""+this.name+"\"";
		if (!$.nony.isEmpty(this.className)) {str += " class=\""+this.className+"\"";}
		if (!$.nony.isEmpty(this.style)) {str += " style=\""+this.style+"\"";}
		if (!$.nony.isEmpty(this.script)) {str += " onclick=\""+this.script+"\"";}
		if (!$.nony.isEmpty(this.value)) {str += "value=\""+this.value+"\"";}
		str += "/>";

		return str;
	}
};