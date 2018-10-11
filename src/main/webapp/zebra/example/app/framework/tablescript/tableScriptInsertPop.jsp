<%/************************************************************************************************
* Description
* - 
************************************************************************************************/%>
<%@ include file="/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity pe = (ParamEntity)request.getAttribute("paramEntity");
	DataSet dsRequest = (DataSet)pe.getRequestDataSet();
%>
<%/************************************************************************************************
* HTML
************************************************************************************************/%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="icon" type="image/png" href="<mc:cp key="imgIcon"/>/zebraFavicon.png">
<title><mc:msg key="fwk.main.system.title"/></title>
<%/************************************************************************************************
* Stylesheet & Javascript
************************************************************************************************/%>
<%@ include file="/shared/page/incCssJs.jsp"%>
<style type="text/css">
/* #tblGridBody tr td:hover {background:#ffffff;} */
#liDummy {display:none;}
#divDataArea.areaContainerPopup {padding-top:0px;}
.dummyDetail {list-style:none;}
.dragHandler {cursor:move;}
.deleteButton {cursor:pointer;}
</style>
<script type="text/javascript">
jsconfig.put("useJqSelectmenu", false);

$(function() {
	/*!
	 * event
	 */
	$("#btnSave").click(function(event) {
		$("#liDummy").find(":input").each(function(index) {
			$(this).removeAttr("mandatory");
			$(this).removeAttr("option");
		});

		if (!commonJs.doValidate("fmDefault")) {
			return;
		}

		$("#ulColumnDetailHolder").find(".dummyDetail").each(function(groupIndex) {
			var delimiter = jsconfig.get("dataDelimiter");

			$(this).find(":input").each(function(index) {
				var id = $(this).attr("id"), name = $(this).attr("name");

				if (!commonJs.isEmpty(id)) {id = (id.indexOf(delimiter) != -1) ? id.substring(0, id.indexOf(delimiter)) : id;}
				else {id = "";}

				if (!commonJs.isEmpty(name)) {name = (name.indexOf(delimiter) != -1) ? name.substring(0, name.indexOf(delimiter)) : name;}
				else {name = "";}

				$(this).attr("id", id+delimiter+groupIndex).attr("name", name+delimiter+groupIndex);
			});
		});

		commonJs.confirm({
			contents:"<mc:msg key="Q001"/>",
			buttons:[{
				caption:"Yes",
				callback:function() {
					exeSave();
				}
			}, {
				caption:"No",
				callback:function() {
				}
			}]
		});
	});

	$("#btnClose").click(function(event) {
		parent.popup.close();
	});

	$("#btnAdd").click(function(event) {
		var elem = $("#liDummy").clone(), delimiter = jsconfig.get("dataDelimiter"), elemId = $(elem).attr("id");

		$(elem).css("display", "block").appendTo($("#ulColumnDetailHolder"));

		$("#ulColumnDetailHolder").find(".dummyDetail").each(function(groupIndex) {
			$(this).attr("index", groupIndex).attr("id", elemId+delimiter+groupIndex);

			$(this).find("i").each(function(index) {
				var id = $(this).attr("id"), id = (id.indexOf(delimiter) != -1) ? id.substring(0, id.indexOf(delimiter)) : id;
				$(this).attr("index", groupIndex).attr("id", id+delimiter+groupIndex);
			});

			$(this).find(".dragHandler").each(function(index) {
				var id = $(this).attr("id"), id = (id.indexOf(delimiter) != -1) ? id.substring(0, id.indexOf(delimiter)) : id;
				$(this).attr("index", groupIndex).attr("id", id+delimiter+groupIndex);
			});

			$(this).find(".deleteButton").each(function(index) {
				var id = $(this).attr("id"), id = (id.indexOf(delimiter) != -1) ? id.substring(0, id.indexOf(delimiter)) : id;
				$(this).attr("index", groupIndex).attr("id", id+delimiter+groupIndex);
			});

			$(this).find("input").each(function(index) {
				var id = $(this).attr("id"), name = $(this).attr("name");

				if (!commonJs.isEmpty(id)) {id = (id.indexOf(delimiter) != -1) ? id.substring(0, id.indexOf(delimiter)) : id;}
				else {id = "";}

				if (!commonJs.isEmpty(name)) {name = (name.indexOf(delimiter) != -1) ? name.substring(0, name.indexOf(delimiter)) : name;}
				else {name = "";}

				$(this).attr("id", id+delimiter+groupIndex).attr("name", name+delimiter+groupIndex);

				if (groupIndex == ($("#ulColumnDetailHolder .dummyDetail").length - 1)) {
					if (name.indexOf("useYnDetail") != -1) {
						if ($(this).val() == "Y") {$(this).prop("checked", true);}
					}

					if (name.indexOf("sortOrderDetail") != -1) {
						$(this).val(commonJs.lpad((groupIndex+1), 3, "0"));
					}
				}
			});
		});

		$("#tblGrid").fixedHeaderTable({
			attachTo:$("#divDataArea")
		});
		setSelectBoxes();
	});

	/*!
	 * process
	 */
	exeSave = function() {
		var detailLength = $("#ulColumnDetailHolder .dummyDetail").length;

		commonJs.ajaxSubmit({
			url:"/zebra/framework/commoncode/exeInsert.do",
			dataType:"json",
			formId:"fmDefault",
			data:{
				detailLength:detailLength
			},
			success:function(data, textStatus) {
				var result = commonJs.parseAjaxResult(data, textStatus, "json");

				if (result.isSuccess == true || result.isSuccess == "true") {
					commonJs.openDialog({
						type:"information",
						contents:result.message,
						blind:true,
						buttons:[{
							caption:"Ok",
							callback:function() {
								parent.popup.close();
								parent.doSearch();
							}
						}]
					});
				} else {
					commonJs.error(result.message);
				}
			}
		});
	};

	setSortable = function() {
		$("#ulColumnDetailHolder").sortable({
			axis:"y",
			handle:".dragHandler",
			stop:function() {
				$("#ulColumnDetailHolder").find(".dummyDetail").each(function(groupIndex) {
					$(this).find("input").each(function(index) {
						var id = $(this).attr("id"), name = $(this).attr("name"), delimiter = jsconfig.get("dataDelimiter");

						$(this).attr("id", id+delimiter+groupIndex).attr("name", name+delimiter+groupIndex);

						if (name.indexOf("sortOrderDetail") != -1) {
							$(this).val(commonJs.lpad((groupIndex+1), 3, "0"));
						}
					});
				});
			}
		});

		$("#ulColumnDetailHolder").disableSelection();
	};

	/*!
	 * load event (document / window)
	 */
	$(document).click(function(event) {
		var obj = event.target;

		if ($(obj).hasClass("deleteButton") || ($(obj).is("i") && $(obj).parent("th").hasClass("deleteButton"))) {
			$("#ulColumnDetailHolder").find(".dummyDetail").each(function(index) {
				if ($(this).attr("index") == $(obj).attr("index")) {
					$(this).remove();

					$("#tblGrid").fixedHeaderTable({
						attachTo:$("#divDataArea")
					});
				}
			});

			$("#ulColumnDetailHolder").find(".dummyDetail").each(function(groupIndex) {
				$(this).find("input[type=text]").each(function(index) {
					var name = $(this).attr("name");
					if (name.indexOf("sortOrderDetail") != -1) {
						$(this).val(commonJs.lpad((groupIndex+1), 3, "0"));
					}
				});
			});
		}
	});

	setSelectBoxes = function() {
		var options = {};

		$("select.bootstrapSelect").each(function(index) {
			options.width = "auto";
			options.size = 5;
			options.container = "body";
			options.style = $(this).attr("class");

			$(this).selectpicker(options);
		});
	};

	$(window).load(function() {
		$("#tableName").focus();

		setSortable();

		setTimeout(function() {
			$("#tblGrid").fixedHeaderTable({
				attachTo:$("#divDataArea")
			});
		}, 500);
	});
});
</script>
</head>
<%/************************************************************************************************
* Page & Header
************************************************************************************************/%>
<body>
<form id="fmDefault" name="fmDefault" method="post" action="">
<div id="divPopupWindowHolder">
<div id="divFixedPanelPopup">
<div id="divLocationPathArea"><%@ include file="/zebra/example/common/include/bodyLocationPathArea.jsp"%></div>
<%/************************************************************************************************
* Real Contents - fixed panel(tab, button, search, information)
************************************************************************************************/%>
<div id="divTabArea"></div>
<div id="divButtonArea" class="areaContainerPopup">
	<div id="divButtonAreaLeft"></div>
	<div id="divButtonAreaRight">
		<ui:buttonGroup id="buttonGroup">
			<ui:button id="btnSave" caption="button.com.save" iconClass="fa-save"/>
			<ui:button id="btnClose" caption="button.com.close" iconClass="fa-times"/>
		</ui:buttonGroup>
	</div>
</div>
<div id="divSearchCriteriaArea"></div>
<div id="divInformArea" class="areaContainerPopup">
	<table class="tblEdit">
		<colgroup>
			<col width="10%"/>
			<col width="25%"/>
			<col width="10%"/>
			<col width="*"/>
		</colgroup>
		<tr>
			<th class="thEdit Rt mandatory"><mc:msg key="fwk.tablescript.header.tableName"/></th>
			<td class="tdEdit">
				<ui:text name="tableName" id="tableName" className="defClass" style="text-transform:uppercase" checkName="fwk.tablescript.header.tableName" options="mandatory" maxbyte="30"/>
			</td>
			<th class="thEdit Rt mandatory"><mc:msg key="fwk.tablescript.header.tableDesc"/></th>
			<td class="tdEdit">
				<ui:text name="tableDescription" id="tableDescription" className="defClass" checkName="fwk.tablescript.header.tableDesc" options="mandatory"/>
			</td>
		</tr>
	</table>
</div>
<div class="breaker" style="height:5px;"></div>
<div class="divButtonArea areaContainerPopup">
	<div class="divButtonAreaLeft"></div>
	<div class="divButtonAreaRight">
		<ui:buttonGroup id="subButtonGroup">
			<ui:button id="btnAdd" caption="button.com.add" iconClass="fa-plus"/>
		</ui:buttonGroup>
	</div>
</div>
<%/************************************************************************************************
* End of fixed panel
************************************************************************************************/%>
<div class="breaker"></div>
</div>
<div id="divScrollablePanelPopup">
<%/************************************************************************************************
* Real Contents - scrollable panel(data, paging)
************************************************************************************************/%>
<div id="divDataArea" class="areaContainerPopup">
	<table id="tblGrid" class="tblGrid">
		<colgroup>
			<col width="2%"/>
			<col width="2%"/>
			<col width="17%"/>
			<col width="7%"/>
			<col width="5%"/>
			<col width="7%"/>
			<col width="5%"/>
			<col width="5%"/>
			<col width="18%"/>
			<col width="*"/>
		</colgroup>
		<thead>
			<tr>
				<th class="thGrid"></th>
				<th class="thGrid"></th>
				<th class="thGrid"><mc:msg key="fwk.tablescript.header.colName"/></th>
				<th class="thGrid"><mc:msg key="fwk.tablescript.header.dataType"/></th>
				<th class="thGrid"><mc:msg key="fwk.tablescript.header.length"/></th>
				<th class="thGrid"><mc:msg key="fwk.tablescript.header.defaultValue"/></th>
				<th class="thGrid"><mc:msg key="fwk.tablescript.header.nullable"/></th>
				<th class="thGrid"><mc:msg key="fwk.tablescript.header.keyType"/></th>
				<th class="thGrid"><mc:msg key="fwk.tablescript.header.fkRef"/></th>
				<th class="thGrid"><mc:msg key="fwk.tablescript.header.description"/></th>
			</tr>
		</thead>
		<tbody id="tblGridBody">
			<tr>
				<td colspan="10" style="padding:0px"><ul id="ulColumnDetailHolder"></ul></td>
			</tr>
		</tbody>
	</table>
</div>
<div id="divPagingArea"></div>
<%/************************************************************************************************
* Right & Footer
************************************************************************************************/%>
</div>
</div>
<%/************************************************************************************************
* Additional Elements
************************************************************************************************/%>
<li id="liDummy" class="dummyDetail">
	<table class="tblGrid">
		<colgroup>
			<col width="2%"/>
			<col width="2%"/>
			<col width="17%"/>
			<col width="7%"/>
			<col width="5%"/>
			<col width="7%"/>
			<col width="5%"/>
			<col width="5%"/>
			<col width="18%"/>
			<col width="*"/>
		</colgroup>
		<tr class="noBorderAll">
			<th id="thDragHander" class="thEdit Ct dragHandler" title="<mc:msg key="fwk.commoncode.msg.drag"/>"><i id="iDragHandler" class="fa fa-lg fa-sort"></i></th>
			<th id="thDeleteButton" class="thEdit Ct deleteButton" title="<mc:msg key="fwk.commoncode.msg.delete"/>"><i id="iDeleteButton" class="fa fa-lg fa-times"></i></th>
			<td class="tdEdit Ct"><ui:text id="columnName" name="columnName" className="defClass" style="text-transform:uppercase" checkName="fwk.tablescript.header.colName" options="mandatory"/></td>
			<td class="tdEdit Ct"><ui:ccselect id="dataType" name="dataType" codeType="DOMAIN_DATA_TYPE" options="mandatory" source="framework"/></td>
			<td class="tdEdit Ct"><ui:ccselect id="dataLength" name="dataLength" codeType="DOMAIN_DATA_LENGTH" caption="==Select==" source="framework"/></td>
			<td class="tdEdit Ct"><ui:text id="defaultValue" name="defaultValue" className="defClass" checkName="fwk.tablescript.header.defaultValue"/></td>
			<td class="tdEdit Ct"><ui:ccradio name="Nullable" codeType="SIMPLE_YN" selectedValue="Y" source="framework"/></td>
			<td class="tdEdit Ct"><ui:ccselect id="keyType" name="keyType" codeType="CONSTRAINT_TYPE" caption="==Select==" source="framework"/></td>
			<td class="tdEdit Ct"><ui:text id="fkRef" name="fkRef" className="defClass" checkName="fwk.tablescript.header.fkRef" status="disabled"/></td>
			<td class="tdEdit Ct"><ui:text id="description" name="description" className="defClass" checkName="fwk.tablescript.header.description" options="mandatory"/></td>
		</tr>
	</table>
</li>
</form>
<%/************************************************************************************************
* Additional Form
************************************************************************************************/%>
<form id="fmHidden" name="fmHidden" method="post" action=""></form>
</body>
</html>