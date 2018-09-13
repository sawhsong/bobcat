/**
 * Table.td - For Data Grid only
 */
UiGridTd = Class.create();
UiGridTd.prototype = {
	initialize : function() {
		this.className = "";
		this.tdAttribute = "";
		this.value = "",
		this.childList= new Array();
	},
	setClassName : function(className) {
		this.className = className;
	},
	setValue : function(value) {
		this.value = value;
	},
	setTdAttribute : function(attributes) {
		this.tdAttribute = attributes;
	},
	toHtmlString : function() {
		var str = "";
		var attrArray = null;
		var key = "", val = "";

		str += "<td class=\""+this.className+"\"";
		if (!$.nony.isEmpty(this.tdAttribute)) {
			attrArray = this.tdAttribute.split(";");
			for (var i=0; i<attrArray.length; i++) {
				var keyVal = attrArray[i].split(":");
				key = keyVal[0];
				val = keyVal[1];

				str += " "+key+"=\""+val+"\"";
			}
		}
		str += ">";
		str += this.value;
		str += "</td>";

		return str;
	}
};