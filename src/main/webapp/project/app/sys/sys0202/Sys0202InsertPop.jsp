<%/************************************************************************************************
* Description
* - 
************************************************************************************************/%>
<%@ include file="/webapp/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity paramEntity = (ParamEntity)request.getAttribute("paramEntity");
	DataSet requestDataSet = (DataSet)paramEntity.getRequestDataSet();
%>
<%/************************************************************************************************
* HTML
************************************************************************************************/%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="icon" type="image/png" href="<tag:cp key="imgIcon"/>/favicon.png">
<title><tag:msg key="main.system.title"/></title>
<%/************************************************************************************************
* Stylesheet & Javascript
************************************************************************************************/%>
<%@ include file="/webapp/shared/page/incCssJs.jsp"%>
<style type="text/css">
#liDummy {display:none;}
#divDataArea.areaContainerPopup {padding-top:0px;}
.dummyDetail {list-style:none;margin-top:4px;}
.dragHandler {cursor:move;}
.deleteButton {cursor:pointer;}
</style>
<script type="text/javascript">
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

		$("#ulCommonCodeDetailHolder").find(".dummyDetail").each(function(groupIndex) {
			var delimiter = globalMap.get("dataDelimiter");

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
			contents:"<tag:msg key="Q001"/>",
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
		var elem = $("#liDummy").clone(), delimiter = globalMap.get("dataDelimiter"), elemId = $(elem).attr("id");

		$(elem).css("display", "block").appendTo($("#ulCommonCodeDetailHolder"));

		$("#ulCommonCodeDetailHolder").find(".dummyDetail").each(function(groupIndex) {
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

				if (groupIndex == ($("#ulCommonCodeDetailHolder .dummyDetail").length - 1)) {
					if (name.indexOf("isActiveDetail") != -1) {
						if ($(this).val() == "Y") {$(this).prop("checked", true);}
					}

					if (name.indexOf("sortOrderDetail") != -1) {
						$(this).val(commonJs.lpad((groupIndex+1), 3, "0"));
					}
				}
			});
		});
	});

	/*!
	 * process
	 */
	exeSave = function() {
		var detailLength = $("#ulCommonCodeDetailHolder .dummyDetail").length;

		commonJs.ajaxSubmit({
			url:"/sys/0202/exeInsert.do",
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
		$("#ulCommonCodeDetailHolder").sortable({
			axis:"y",
			handle:".dragHandler",
			stop:function() {
				$("#ulCommonCodeDetailHolder").find(".dummyDetail").each(function(groupIndex) {
					$(this).find("input").each(function(index) {
						var id = $(this).attr("id"), name = $(this).attr("name"), delimiter = globalMap.get("dataDelimiter");

						$(this).attr("id", id+delimiter+groupIndex).attr("name", name+delimiter+groupIndex);

						if (name.indexOf("sortOrderDetail") != -1) {
							$(this).val(commonJs.lpad((groupIndex+1), 3, "0"));
						}
					});
				});
			}
		});

		$("#ulCommonCodeDetailHolder").disableSelection();
	};

	/*!
	 * load event (document / window)
	 */
	$(document).click(function(event) {
		var obj = event.target;

		if ($(obj).hasClass("deleteButton") || ($(obj).is("i") && $(obj).parent("th").hasClass("deleteButton"))) {
			$("#ulCommonCodeDetailHolder").find(".dummyDetail").each(function(index) {
				if ($(this).attr("index") == $(obj).attr("index")) {
					$(this).remove();
				}
			});

			$("#ulCommonCodeDetailHolder").find(".dummyDetail").each(function(groupIndex) {
				$(this).find("input[type=text]").each(function(index) {
					var name = $(this).attr("name");
					if (name.indexOf("sortOrderDetail") != -1) {
						$(this).val(commonJs.lpad((groupIndex+1), 3, "0"));
					}
				});
			});
		}
	});

	$(window).load(function() {
		$("#codeTypeMaster").focus();
		setSortable();
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
<div id="divLocationPathArea"><%@ include file="/webapp/project/common/include/bodyLocationPathArea.jsp"%></div>
<%/************************************************************************************************
* Real Contents - fixed panel(tab, button, search, information)
************************************************************************************************/%>
<div id="divTabArea"></div>
<div id="divButtonArea" class="areaContainerPopup">
	<div id="divButtonAreaLeft"></div>
	<div id="divButtonAreaRight">
		<tag:buttonGroup id="buttonGroup">
			<tag:button id="btnSave" caption="button.com.save" iconClass="fa-save"/>
			<tag:button id="btnClose" caption="button.com.close" iconClass="fa-times"/>
		</tag:buttonGroup>
	</div>
</div>
<div id="divSearchCriteriaArea"></div>
<div id="divInformArea" class="areaContainerPopup">
	<table class="tblEdit">
		<caption class="captionEdit"><tag:msg key="sys0202.searchHeader.codeType"/></caption>
		<colgroup>
			<col width="15%"/>
			<col width="35%"/>
			<col width="15%"/>
			<col width="35%"/>
		</colgroup>
		<tr>
			<th class="thEdit mandatory"><tag:msg key="sys0202.header.codeType"/></th>
			<td class="tdEdit">
				<input type="text" id="codeTypeMaster" name="codeTypeMaster" class="txtEn" style="text-transform:uppercase;" checkName="<tag:msg key="sys0202.header.codeType"/>" mandatory/>
			</td>
			<th class="thEdit mandatory"><tag:msg key="sys0202.header.isActive"/></th>
			<td class="tdEdit">
				<tag:radio name="isActiveMaster" codeType="SIMPLE_YN" selectedValue="Y"/>
			</td>
		</tr>
		<tr>
			<th class="thEdit mandatory"><tag:msg key="sys0202.header.descriptionEn"/></th>
			<td class="tdEdit">
				<input type="text" id="descriptionEnMaster" name="descriptionEnMaster" class="txtEn" checkName="<tag:msg key="sys0202.header.descriptionEn"/>" mandatory/>
			</td>
			<th class="thEdit mandatory"><tag:msg key="sys0202.header.descriptionKo"/></th>
			<td class="tdEdit">
				<input type="text" id="descriptionKoMaster" name="descriptionKoMaster" class="txtEn" checkName="<tag:msg key="sys0202.header.descriptionKo"/>" mandatory/>
			</td>
		</tr>
	</table>
</div>
<div class="breaker" style="height:5px;"></div>
<div class="divButtonArea areaContainerPopup">
	<div class="divButtonAreaLeft"></div>
	<div class="divButtonAreaRight">
		<tag:buttonGroup id="subButtonGroup">
			<tag:button id="btnAdd" caption="button.com.add" iconClass="fa-plus"/>
		</tag:buttonGroup>
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
	<ul id="ulCommonCodeDetailHolder"></ul>
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
	<table class="tblEdit">
		<colgroup>
			<col width="3%"/>
			<col width="13%"/>
			<col width="*"/>
			<col width="13%"/>
			<col width="15%"/>
			<col width="11%"/>
			<col width="10%"/>
		</colgroup>
		<tr>
			<th id="thDragHander" class="thEditCt dragHandler" title="<tag:msg key="sys0202.msg.drag"/>"><i id="iDragHandler" class="fa fa-lg fa-sort"></i></th>
			<th class="thEdit mandatory"><tag:msg key="sys0202.header.commonCode"/></th>
			<td class="tdEdit">
				<input type="text" id="commonCodeDetail" name="commonCodeDetail" class="txtEn" checkName="<tag:msg key="sys0202.header.commonCode"/>" mandatory/>
			</td>
			<th class="thEdit mandatory"><tag:msg key="sys0202.header.isActive"/></th>
			<td class="tdEdit">
				<tag:radio name="isActiveDetail" codeType="SIMPLE_YN" selectedValue="Y"/>
			</td>
			<th class="thEdit mandatory"><tag:msg key="sys0202.header.sortOrder"/></th>
			<td class="tdEdit">
				<input type="text" id="sortOrderDetail" name="sortOrderDetail" class="txtEn" checkName="<tag:msg key="sys0202.header.sortOrder"/>" mandatory option="numeric"/>
			</td>
		</tr>
		<tr>
			<th id="thDeleteButton" class="thEditCt deleteButton" title="<tag:msg key="sys0202.msg.delete"/>"><i id="iDeleteButton" class="fa fa-lg fa-times"></i></th>
			<th class="thEdit mandatory"><tag:msg key="sys0202.header.descriptionEn"/></th>
			<td class="tdEdit">
				<input type="text" id="descriptionEnDetail" name="descriptionEnDetail" class="txtEn" checkName="<tag:msg key="sys0202.header.descriptionEn"/>" mandatory/>
			</td>
			<th class="thEdit mandatory"><tag:msg key="sys0202.header.descriptionKo"/></th>
			<td class="tdEdit" colspan="3">
				<input type="text" id="descriptionKoDetail" name="descriptionKoDetail" class="txtEn" checkName="<tag:msg key="sys0202.header.descriptionKo"/>" mandatory/>
			</td>
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