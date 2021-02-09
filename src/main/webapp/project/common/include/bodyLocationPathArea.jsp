<%/************************************************************************************************
* Description
* - Location Path Area
************************************************************************************************/%>
<%
	SysUser sysUserLocationPathArea = (SysUser)session.getAttribute("SysUser");
	String authGroupIdLocationPath = sysUserLocationPathArea.getAuthGroupId();
	String headerMenuIdLocationPath = (String)session.getAttribute("headerMenuId");
%>

<script type="text/javascript">
$(function() {
	goMenu = function(headerMenuId, headerMenuName, headerMenuUrl, leftMenuId, leftMenuName, leftMenuUrl) {
		$("#hdnHeaderMenuId").val(headerMenuId);
		$("#hdnHeaderMenuName").val(headerMenuName);
		$("#hdnHeaderMenuUrl").val(headerMenuUrl);
		$("#hdnLeftMenuId").val(leftMenuId);
		$("#hdnLeftMenuName").val(leftMenuName);
		$("#hdnLeftMenuUrl").val(leftMenuUrl);

		commonJs.doSubmit({form:$("form:eq(0)"), action:leftMenuUrl});
	};
});
</script>

<c:if test="${!(sessionScope.headerMenuId == null || sessionScope.headerMenuId == '')}">
<div id="divLocationPathContainer" class="locationPathContainer">
	<span class="fa fa-home fa-lg locationPathContainerIcon"></span>&nbsp;
	<a onclick="doMainMenu('${sessionScope.headerMenuId}', '${sessionScope.headerMenuName}', '${sessionScope.headerMenuUrl}')">${sessionScope.headerMenuName}</a>

	<c:if test="${!(sessionScope.leftMenuId == null || sessionScope.leftMenuId == '')}">
		&nbsp;<span class="fa fa-angle-right fa-lg locationPathContainerIcon"></span>&nbsp;
		<a onclick="doLeftMenu('${sessionScope.leftMenuId}', '${sessionScope.leftMenuName}', '${sessionScope.leftMenuUrl}')">${sessionScope.leftMenuName}</a>
	</c:if>
</div>
</c:if>