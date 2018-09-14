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
		this.className = "fa fa-tasks fa-lg icnEn";
		this.style = "";
		this.script = "";
		this.attribute = "";
	},
	/**
	 * Setter / Getter
	 */
	setId : function(id) {this.id = id; return this;},
	setName : function(name) {this.name = name; return this;},
	setClassName : function(className) {this.className = className; return this;},
	setStyle : function(style) {this.style = style; return this;},
	setScript : function(script) {this.script = script; return this;},
	setAttribute : function(attributes) {this.attribute = attributes; return this;},
	/**
	 * Method
	 */
	addClassName : function(className) {this.className += ($.nony.isEmpty(this.className)) ? className : " "+className; return this;},
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

		str += "<i id=\""+this.id+"\" name=\""+this.name+"\"";
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