/**
 * Table.tr
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
	setClassName : function(className) {
		this.className = className;
	},
	setStyle : function(style) {
		this.style = style;
	},
	addChild : function(obj) {
		this.childList.push(obj);
	},
	/**
	 * Method
	 */
	/**
	 * toString
	 */
	toHtmlString : function() {
		var str = "";

		str += "<tr class=\""+this.className+"\">";
		if (this.childList != null && this.childList.length > 0) {
			for (var i=0; i<this.childList.length; i++) {
				str += this.childList[i].toHtmlString();
			}
		}
		str += "</tr>";

		return str;
	}
};