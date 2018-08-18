<%/************************************************************************************************
* Description
* - 
************************************************************************************************/%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	SysUser sysUserHeaderPage = (SysUser)session.getAttribute("SysUser");
	SysOrg sysOrgHeaderPage = (SysOrg)session.getAttribute("SysOrg");
	String authGroupIdHeaderPage = sysUserHeaderPage.getAuthGroupId();
	String userNameHeaderPage = sysUserHeaderPage.getUserName();
	String userIdHeaderPage = sysUserHeaderPage.getUserId();
	String loginIdHeaderPage = sysUserHeaderPage.getLoginId();
	String languageCodeHeaderPage = CommonUtil.upperCase((String)session.getAttribute("langCode"));
	String selectedHeaderMenuHeaderPage = (String)session.getAttribute("headerMenuId");
	DataSet dsMenuHeaderPage = MenuManager.getMenu(authGroupIdHeaderPage, "", "1", "1");
	String userIdForAdminToolHeaderPage = (String)session.getAttribute("UserIdForAdminTool");
%>
<%/************************************************************************************************
* Stylesheet & Javascript
************************************************************************************************/%>
<style type="text/css">
</style>
<script type="text/javascript">
var popupUserProfile;

$(function() {
	$("#aLogo").click(function(event) {
		$("#hdnHeaderMenuId").val("");
		$("#hdnHeaderMenuName").val("");
		$("#hdnHeaderMenuUrl").val("");
		$("#hdnLeftMenuId").val("");
		$("#hdnLeftMenuName").val("");
		$("#hdnLeftMenuUrl").val("");

		commonJs.doSubmit({form:$("form:eq(0)"), action:"/index.do"});
	});

	$("#aFrameworkMenu").click(function() {
		$("#hdnHeaderMenuId").val("");
		$("#hdnHeaderMenuName").val("");
		$("#hdnHeaderMenuUrl").val("");
		$("#hdnLeftMenuId").val("");
		$("#hdnLeftMenuName").val("");
		$("#hdnLeftMenuUrl").val("");

		commonJs.doSubmit({form:$("form:eq(0)"), action:"/zebra/framework/index/getDefault.do"});
	});

	$("#aLoggedInUser").click(function() {
		$("#divLoggedInUser").addClass("selected");
		$("#divLoggedInUser").trigger("click");
	});

	doMainMenu = function(menuId, menuName, menuUrl) {
		if (menuUrl == "#") {
			menuUrl = "/index.do";
		}
		$("#hdnHeaderMenuId").val(menuId);
		$("#hdnHeaderMenuName").val(menuName);
		$("#hdnHeaderMenuUrl").val(menuUrl);
		$("#hdnLeftMenuId").val("");
		$("#hdnLeftMenuName").val("");
		$("#hdnLeftMenuUrl").val("");

		commonJs.doSubmit({form:$("form:eq(0)"), action:menuUrl});
	};

	setLoginUserContextMenu = function() {
		ctxMenu.loggedInUser[0].fun = function() {getMyProfile("<%=userIdHeaderPage%>");};
		ctxMenu.loggedInUser[1].fun = function() {logout();};
		$("#divLoggedInUser").contextMenu(ctxMenu.loggedInUser, {
			classPrefix:"header",
			effectDuration:300,
			effect:"slide",
			borderRadius:"bottom 3px",
			displayAround:"trigger",
			position:"bottom",
			verAdjust:0,
			onClose:function() {
				$("#divLoggedInUser").removeClass("selected");
			}
		});
	};

	getMyProfile = function(userId) {
		popupUserProfile = commonJs.openPopup({
			popupId:"UserProfile",
			url:"/login/getUserProfile.do",
			paramData:{
				userId:userId
			},
			header:"User Profile Detail",
			blind:true,
			width:720,
			height:340
		});
	};

	logout = function() {
		commonJs.doSubmit({form:$("form:eq(0)"), action:"/login/logout.do"});
	};

	$(window).load(function() {
		setLoginUserContextMenu();
	});
});
</script>
<%/************************************************************************************************
* Real Contents
************************************************************************************************/%>
<div id="divHeaderLeft"></div>
<div id="divHeaderCenter">
	<div id="divGlobalMenuHeaderGroup">
		<div id="divGlobalMenuLeft">
			<div id="divLogoArea">
				<a id="aLogo"></a>
			</div>
		</div>
		<div id="divGlobalMenuRight">
			<div id="divGblMenuArea">
<%
				if (CommonUtil.isNotBlank(userIdForAdminToolHeaderPage)) {
%>
				<div id="divUsingUserAs" class="headerGblMenus" style="color:#D92E24;cursor:default;">
					User Name As ${sessionScope.UserNameForAdminTool} / User Login ID As ${sessionScope.LoginIdForAdminTool} (${sessionScope.OrgIdForAdminTool} / ${sessionScope.OrgLegalNameForAdminTool} / ${sessionScope.OrgCategoryDescForAdminTool})
				</div>
				<div id="divUsingUserAsBreaker" class="divGblMenuBreak">&nbsp;</div>
<%
				} else {
%>
				<div id="divUsingUserAs" class="headerGblMenus" style="color:#D92E24;cursor:default;">
				</div>
<%
				}
%>
				<div id="divLoginUserInfo" class="headerGblMenus" style="cursor:default;">
					<%=sysOrgHeaderPage.getLegalName()%> (<%=CommonUtil.getFormatString(sysOrgHeaderPage.getAbn(), "?? ??? ??? ???")%>)
				</div>
<%
				if (CommonUtil.equals(loginIdHeaderPage, "dustin")) {
%>
				<div class="divGblMenuBreak">&nbsp;</div>
				<div id="divFrameworkMenu" class="headerGblMenus">
					<a id="aFrameworkMenu">Framework Menu</a>
				</div>
<%
				}
%>
			</div>
		</div>
	</div>
	<div id="divMainMenuHeaderGroup">
		<div id="divMainMenuAreaLeft">
<%
		for (int i=0; i<dsMenuHeaderPage.getRowCnt(); i++) {
			if (CommonUtil.equals(dsMenuHeaderPage.getValue(i, "LEVEL"), "1") && !CommonUtil.equals(dsMenuHeaderPage.getValue(i, "MENU_ID"), "QM")) {
				String menuId = dsMenuHeaderPage.getValue(i, "MENU_ID");
				String menuName = dsMenuHeaderPage.getValue(i, "MENU_NAME_"+languageCodeHeaderPage);
				String menuUrl = dsMenuHeaderPage.getValue(i, "MENU_URL");
				String icon = dsMenuHeaderPage.getValue(i, "MENU_ICON");
				String selected = (CommonUtil.equals(menuId, selectedHeaderMenuHeaderPage)) ? "Selected" : "";
%>
				<div id="div<%=menuId%>" class="headerMainMenus<%=selected%>" onclick="doMainMenu('<%=menuId%>', '<%=menuName%>', '<%=menuUrl%>')">
					<a style="background:url(<tag:cp key="imgThemeCom"/>/<%=icon%>_<tag:cp key="hearMainMenuIconColor"/>.png) no-repeat 0px 0px"><%=menuName%></a>
				</div>
<%
			}
		}
%>
		</div>
		<div id="divMainMenuAreaRight">
			<div id="divLoggedInUser" class="headerMainMenus">
				<a id="aLoggedInUser"><%=userNameHeaderPage%></a>
			</div>
		</div>
	</div>
</div>
<div id="divHeaderRight"></div>
<%/************************************************************************************************
* Additional Elements
************************************************************************************************/%>
<input type="hidden" id="hdnHeaderMenuId" name="hdnHeaderMenuId" value="${sessionScope.headerMenuId}"/>
<input type="hidden" id="hdnHeaderMenuName" name="hdnHeaderMenuName" value="${sessionScope.headerMenuName}"/>
<input type="hidden" id="hdnHeaderMenuUrl" name="hdnHeaderMenuUrl" value="${sessionScope.headerMenuUrl}"/>