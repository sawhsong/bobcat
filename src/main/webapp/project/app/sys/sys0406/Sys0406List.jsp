<%/************************************************************************************************
* Description - Sys0408 - Menu - Authority Group
*   - Generated by Source Generator
************************************************************************************************/%>
<%@ include file="/webapp/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity paramEntity = (ParamEntity)request.getAttribute("paramEntity");
	DataSet resultDataSet = (DataSet)paramEntity.getObject("resultDataSet");
	DataSet authGroupDataSet = (DataSet)paramEntity.getObject("authGroupDataSet");
	String langCode = CommonUtil.upperCase((String)session.getAttribute("langCode"));
	String delimiter = ConfigUtil.getProperty("delimiter.data");
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
</style>
<script type="text/javascript">
var delimiter = "<%=delimiter%>";

$(function() {
	/*!
	 * event
	 */
	$("#btnSave").click(function(event) {
		if (!commonJs.doValidate("fmDefault")) {
			return;
		}

		commonJs.confirm({
			contents:"<tag:msg key="Q001"/>",
			buttons:[{
				caption:"Yes",
				callback:function() {
					commonJs.ajaxSubmit({
						url:"/sys/0406/exeInsert.do",
						dataType:"json",
						formId:"fmDefault",
						data:{
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
											commonJs.doSubmit({
												formId:"fmDefault",
												action:"/sys/0406/getList.do"
											});
										}
									}]
								});
							} else {
								commonJs.error(result.message);
							}
						}
					});
				}
			}, {
				caption:"No",
				callback:function() {
				}
			}]
		});
	});

	$("#icnCheck").click(function(event) {
		commonJs.toggleCheckboxes("chkToAssign");
	});

	$("[name=chkToAssign]").click(function(event) {
		var thisChecked = $(this).prop("checked"), thisValue = $(this).val();
		var thisValueItems = thisValue.split(delimiter);
		var thisLevel = thisValueItems[0];
		var thisPaths = thisValueItems[1].split("/");
		var thisMenuId = thisValueItems[2];

		$("[name=chkToAssign]").each(function(index) {
			var val = $(this).val();
			var valItems = val.split(delimiter);
			var level = valItems[0];
			var paths = valItems[1].split("/");

			if (thisValue != val) {
				if (thisLevel == "1") {
					if (level > thisLevel && thisMenuId == paths[0]) {
						$(this).prop("checked", thisChecked);
					}
				}

				if (thisLevel == "2") {
					if (thisChecked) {
						if ((level > thisLevel && thisMenuId == paths[1]) || (level < thisLevel && thisPaths[0] == paths[0])) {
							$(this).prop("checked", thisChecked);
						}
					} else {
						if (level > thisLevel && thisMenuId == paths[1]) {
							$(this).prop("checked", thisChecked);
						}
					}
				}

				if (thisLevel == "3") {
					if (thisChecked) {
						if ((level == "1" && thisPaths[0] == paths[0]) || (level == "2" && thisPaths[1] == paths[1])) {
							$(this).prop("checked", thisChecked);
						}
					}
				}
			}
		});

		if (!thisChecked && thisLevel == "2") {
			if (!hasChildChecked(thisLevel, thisPaths[0])) {
				$("[name=chkToAssign]").each(function(index) {
					var val = $(this).val();
					var valItems = val.split(delimiter);
					var level = valItems[0];
					var paths = valItems[1].split("/");

					if (paths[0] == thisPaths[0]) {
						$(this).prop("checked", false);
						return false;
					}
				});
			}
		}

		if (!thisChecked && thisLevel == "3") {
			if (!hasChildChecked(thisLevel, thisPaths[1])) {
				$("[name=chkToAssign]").each(function(index) {
					var val = $(this).val();
					var valItems = val.split(delimiter);
					var level = valItems[0];
					var paths = valItems[1].split("/");

					if (paths[1] == thisPaths[1]) {
						$(this).prop("checked", false);
						return false;
					}
				});
			}

			if (!hasChildChecked(2, thisPaths[0])) {
				$("[name=chkToAssign]").each(function(index) {
					var val = $(this).val();
					var valItems = val.split(delimiter);
					var level = valItems[0];
					var paths = valItems[1].split("/");

					if (paths[0] == thisPaths[0]) {
						$(this).prop("checked", false);
						return false;
					}
				});
			}
		}
	});

	$("#authGroup").change(function() {
		$("#authGroupDesc").val($("#authGroup option:selected").attr("desc"));
		setCheckbox();
	});

	$(document).keypress(function(event) {
		if (event.which == 13) {
			var element = event.target;
		}
	});

	/*!
	 * process
	 */
	setCheckbox = function() {
		var selectedAuthGroup = $("#authGroup").val(), groupId = "";

		if (commonJs.isEmpty(selectedAuthGroup)) {
			return;
		}

		$("[name=chkToAssign]").each(function(index) {
			groupId = $(this).attr("groupId");

			if (!commonJs.isEmpty(groupId) && groupId.indexOf(selectedAuthGroup) != -1) {
				$(this).prop("checked", true);
			} else {
				$(this).prop("checked", false);
			}
		});
	};

	hasChildChecked = function(paramLevel, paramMenuId) {
		var rtn = false;

		$("[name=chkToAssign]:checked").each(function(index) {
			var val = $(this).val();
			var valItems = val.split(delimiter);
			var level = valItems[0];
			var paths = valItems[1].split("/");

			if (paramLevel == "2") {
				if (level > paramLevel && paths[0] == paramMenuId || level == paramLevel && paths[0] == paramMenuId) {
					rtn = true;
				}
			}

			if (paramLevel == "3") {
				if (level == paramLevel && paths[1] == paramMenuId) {
					rtn = true;
				}
			}
		});
		return rtn;
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		$("#tblGrid").fixedHeaderTable({
			baseDivElement:"divScrollablePanel",
			widthAdjust:22
		});

		setCheckbox();
	});
});
</script>
</head>
<%/************************************************************************************************
* Page & Header
************************************************************************************************/%>
<body>
<form id="fmDefault" name="fmDefault" method="post" action="">
<div id="divHeaderHolder" class="ui-layout-north"><%@ include file="/webapp/project/common/include/header.jsp"%></div>
<div id="divBodyHolder" class="ui-layout-center">
<div id="divBodyLeft" class="ui-layout-west"><%@ include file="/webapp/project/common/include/bodyLeft.jsp"%></div>
<div id="divBodyCenter" class="ui-layout-center">
<div id="divFixedPanel">
<div id="divLocationPathArea"><%@ include file="/webapp/project/common/include/bodyLocationPathArea.jsp"%></div>
<%/************************************************************************************************
* Real Contents - fixed panel(tab, button, search, information)
************************************************************************************************/%>
<div id="divTabArea"></div>
<div id="divButtonArea" class="areaContainer">
	<div id="divButtonAreaLeft"></div>
	<div id="divButtonAreaRight">
		<tag:buttonGroup id="buttonGroup">
			<tag:button id="btnSave" caption="button.com.save" iconClass="fa-save"/>
		</tag:buttonGroup>
	</div>
</div>
<div id="divSearchCriteriaArea" class="areaContainer">
	<table class="tblSearch">
		<caption><tag:msg key="page.com.searchCriteria"/></caption>
		<tr>
			<td class="tdSearch">
				<label for="authGroup" class="lblEn hor mandatory"><tag:msg key="sys0406.search.authGroup"/></label>
				<div style="float:left;padding-right:4px;">
					<select id="authGroup" name="authGroup" class="bootstrapSelect" checkName="<tag:msg key="sys0406.search.authGroup"/>" mandatory>
						<option value="">==Select==</option>
<%
					for (int i=0; i<authGroupDataSet.getRowCnt(); i++) {
%>
						<option value="<%=authGroupDataSet.getValue(i, "GROUP_ID")%>" desc="<%=authGroupDataSet.getValue(i, "DESCRIPTION")%>"><%=authGroupDataSet.getValue(i, "GROUP_NAME")%></option>
<%
					}
%>
					</select>
				</div>
				<input type="text" id="authGroupDesc" name="authGroupDesc" class="txtDpl hor" style="width:500px" readonly/>
			</td>
		</tr>
	</table>
</div>
<div id="divInformArea"></div>
<%/************************************************************************************************
* End of fixed panel
************************************************************************************************/%>
<div class="breaker"></div>
</div>
<div id="divScrollablePanel">
<%/************************************************************************************************
* Real Contents - scrollable panel(data, paging)
************************************************************************************************/%>
<div id="divDataArea" class="areaContainer">
	<table id="tblGrid" class="tblGrid sort autosort">
		<colgroup>
			<col width="3%"/>
			<col width="10%"/>
			<col width="20%"/>
			<col width="15%"/>
			<col width="7%"/>
			<col width="*"/>
			<col width="5%"/>
		</colgroup>
		<thead>
			<tr>
				<th class="thGrid"><i id="icnCheck" class="fa fa-check-square-o fa-lg icnEn" title="<tag:msg key="sys0406.grid.selectToAssign"/>"></i></th>
				<th class="thGrid"><tag:msg key="sys0406.grid.menuId"/></th>
				<th class="thGrid"><tag:msg key="sys0406.grid.menuName"/></th>
				<th class="thGrid"><tag:msg key="sys0406.grid.menuUrl"/></th>
				<th class="thGrid"><tag:msg key="sys0406.grid.sortOrder"/></th>
				<th class="thGrid"><tag:msg key="sys0406.grid.menuDesc"/></th>
				<th class="thGrid"><tag:msg key="sys0406.grid.isActive"/></th>
			</tr>
		</thead>
		<tbody>
<%
		if (resultDataSet.getRowCnt() > 0) {
			for (int i=0; i<resultDataSet.getRowCnt(); i++) {
				String menuPath = resultDataSet.getValue(i, "PATH");
				String menuId = resultDataSet.getValue(i, "MENU_ID");
				String menuName = resultDataSet.getValue(i, "MENU_NAME_"+langCode);
				String groupId = resultDataSet.getValue(i, "GROUP_ID");
				String space = "", style = "", paramValue = "";
				int iLevel = CommonUtil.toInt(resultDataSet.getValue(i, "LEVEL")) - 1;

				style = (iLevel == 0 || iLevel == 1) ? "font-weight:bold;" : "";

				for (int j=0; j<iLevel; j++) {
					space += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				}

				paramValue = resultDataSet.getValue(i, "LEVEL")+delimiter+menuPath+delimiter+menuId;
%>
			<tr>
				<td class="tdGridCt"><input type="checkbox" id="chkToAssign" name="chkToAssign" class="inTblGrid chkEn" value="<%=paramValue%>" groupId="<%=groupId%>"/></td>
				<td class="tdGrid" style="<%=style%>"><%=space%><%=menuId%></td>
				<td class="tdGrid" style="<%=style%>"><%=menuName%></td>
				<td class="tdGrid"><%=resultDataSet.getValue(i, "MENU_URL")%></td>
				<td class="tdGridCt"><%=resultDataSet.getValue(i, "SORT_ORDER")%></td>
				<td class="tdGrid"><%=resultDataSet.getValue(i, "DESCRIPTION")%></td>
				<td class="tdGridCt"><%=resultDataSet.getValue(i, "IS_ACTIVE")%></td>
			</tr>
<%
			}
		} else {
%>
			<tr>
				<td class="tdGridCt" colspan="7"></td>
			</tr>
<%
		}
%>
		</tbody>
	</table>
</div>
<div id="divPagingArea"></div>
<%/************************************************************************************************
* Right & Footer
************************************************************************************************/%>
</div>
</div>
<div id="divBodyRight" class="ui-layout-east"><%@ include file="/webapp/project/common/include/bodyRight.jsp"%></div>
</div>
<div id="divFooterHolder" class="ui-layout-south"><%@ include file="/webapp/project/common/include/footer.jsp"%></div>
<%/************************************************************************************************
* Additional Elements
************************************************************************************************/%>
</form>
<%/************************************************************************************************
* Additional Form
************************************************************************************************/%>
<form id="fmHidden" name="fmHidden" method="post" action=""></form>
</body>
</html>