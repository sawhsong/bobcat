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
		this.className = "";
		this.style = "";
		this.value = ""
	},
	/**
	 * Setter / Getter
	 */
	setId : function(id) {
		this.id = id;
	},
	setName : function(name) {
		this.name = name;
	},
	setClassName : function(className) {
		this.className = className;
	},
	setStyle : function(style) {
		this.style = style;
	},
	setValue : function(value) {
		this.value = value;
	},
	/**
	 * Method
	 */
	/**
	 * toString
	 */
	toHtmlString : function() {
		var str = "";

		str += "<td class=\""+this.className+"\"";
		
		str += ">";
		str += this.value;
		str += "</td>";

		return str;
	}
};