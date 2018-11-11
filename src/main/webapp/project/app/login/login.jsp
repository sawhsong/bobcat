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
%>
<%/************************************************************************************************
* HTML
************************************************************************************************/%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="icon" type="image/png" href="<mc:cp key="imgIcon"/>/faviconHKAccount.png">
<title><mc:msg key="main.system.title"/></title>
<%/************************************************************************************************
* Stylesheet & Javascript
************************************************************************************************/%>
<%@ include file="/shared/page/incCssJs.jsp"%>
<style type="text/css">
body {background:#FFFFFF;}
text, password {padding:6px 12px;}
.logoImage {margin:0px auto;width:100%;text-align:center;}
.logoImage img {margin-top:5%;}
.loginPanel {margin:0px auto;width:100%;text-align:center;}
.panelLogin {margin-top:10px;display:inline-block;width:360px;border:1px solid #D1D1D1;box-shadow:0px 0px 10px rgba(0, 0, 0, .2);}
.panel-title {padding-top:4px;padding-left:36px;text-align:left;font-size:14px;height:23px;}
.loginBoxtTitle {background:url(<mc:cp key="imgIcon"/>/login.png) no-repeat 0px 0px;}
.panel-body {padding:25px 25px 20px 25px;}
.addonIcon {width:16px;}
.input-group {padding-bottom:4px;}
.buttonDiv {padding-top:16px;padding-bottom:0px;}
.additionalLink {padding-top:20px;font-size:13px;}
.passwordLink {float:left;width:50%;text-align:left;}
.registerLink {float:right;width:50%;text-align:right;}

.loginDescriptionArea {margin:0px auto;width:700px;text-align:center;color:#555555;font-size:12px;
/* 	border-radius:6px;box-shadow:0px 10px 20px rgba(0, 0, 0, .2); */
/* 	background:url(<tag:cp key="imgIcon"/>/loginBack.png) no-repeat 50% 0%; */
/* 	background-size:700px 500px; */
/* 	background-blend-mode:overlay; */
}
.loginDescriptionArea .panel-heading {background-color:#ffffff;}
.loginDescriptionTitle {padding-top:4px;padding-left:4px;text-align:left;font-size:14px;height:26px;font-weight:bold;}
.loginDescription {margin:30px 10px 30px 10px;display:inline-block;width:560px;border:1px solid #D1D1D1;border-radius:10px;box-shadow:0px 0px 20px rgba(0, 0, 0, .2);}
.loginDescriptionArea .panel-body {padding:15px 15px 15px 15px;text-align:left;}
.descContents {line-height:180%;}
</style>
<script type="text/javascript" src="<mc:cp key="viewPageJsName"/>"></script>
<script type="text/javascript">
</script>
</head>
<%/************************************************************************************************
* Page & Header
************************************************************************************************/%>
<body>
<form id="fmDefault" name="fmDefault" method="post" action="">
<div id="divLogo" class="logoImage">
	<ui:img id="imgLogo" src="<mc:cp key=imgIcon/>/logoHKAccount.png" style="width:130px;height:28px;"/>
</div>
<div id="divLoginPanel" class="loginPanel">
	<div class="panel panel-default panelLogin">
		<div class="panel-heading">
			<h3 class="panel-title loginBoxtTitle"><mc:msg key="login.header.main"/></h3>
		</div>
		<div class="panel-body">
			<div class="input-group">
				<div class="input-group-addon"><ui:icon className="fa-user fa-lg addonIcon"/></div>
				<ui:text name="loginId" value="dustin" className="form-control" placeHolder="login.header.loginId" checkName="login.header.loginId" options="mandatory"/>
			</div>
			<div class="input-group">
				<div class="input-group-addon"><ui:icon className="fa-lock fa-lg addonIcon"/></div>
				<ui:password name="password" value="dustin" className="form-control" placeHolder="login.header.password" checkName="login.header.password" options="mandatory"/>
			</div>
			<div class="buttonDiv">
				<ui:button id="btnLogin" type="success" caption="login.button.login" iconClass="fa-key" buttonStyle="padding-top:8px;width:100%;height:40px;font-size:14px;"/>
			</div>
			<div class="additionalLink">
				<div class="passwordLink">
					<ui:anchor id="aResetPassword" caption="login.button.resetPassword"/>
				</div>
				<div class="registerLink">
					<ui:anchor id="aRequestRegister" caption="login.button.requestRegister"/>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="divDescriptionArea" class="loginDescriptionArea">
	<div class="panel panel-default loginDescription">
		<div class="panel-heading">
			<h3 class="panel-title loginDescriptionTitle"><mc:msg key="login.message.descHeader"/></h3>
		</div>
		<div class="panel-body descContents">
			<table class="tblDefault withPadding" style="width:100%;">
				<colgroup>
					<col width="100%"/>
				</colgroup>
				<tr>
					<td class="tdDefault"><mc:msg key="login.message.description"/></td>
				</tr>
			</table>
			<div class="verGap10"></div>
			<table class="tblDefault withPadding" style="width:100%;">
				<colgroup>
					<col width="20%"/>
					<col width="*"/>
				</colgroup>
				<tr>
					<th class="thDefault"><mc:msg key="login.header.accountant"/></th>
					<td class="tdDefault"><mc:msg key="login.header.accountantName"/></td>
				</tr>
				<tr>
					<th class="thDefault"><mc:msg key="login.header.email"/></th>
					<td class="tdDefault"><mc:msg key="login.header.emailValue"/></td>
				</tr>
				<tr>
					<th class="thDefault"><mc:msg key="login.header.tel"/></th>
					<td class="tdDefault"><mc:msg key="login.header.telValue"/></td>
				</tr>
				<tr>
					<th class="thDefault"><mc:msg key="login.header.fax"/></th>
					<td class="tdDefault"><mc:msg key="login.header.faxValue"/></td>
				</tr>
			</table>
			<div class="verGap10"></div>
			<table class="tblDefault withPadding" style="width:100%;">
				<colgroup>
					<col width="100%"/>
				</colgroup>
				<tr>
					<td class="tdDefault"><mc:msg key="I990"/></td>
				</tr>
			</table>
		</div>
	</div>
</div>
</form>
</body>
</html>