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
<link rel="icon" type="image/png" href="<mc:cp key="imgIcon"/>/favicon.png">
<title><mc:msg key="main.system.title"/></title>
<%/************************************************************************************************
* Stylesheet & Javascript
************************************************************************************************/%>
<%@ include file="/shared/page/incCssJs.jsp"%>
<style type="text/css">
body {background:#FFFFFF;}
.logoImage {margin:0px auto;width:100%;text-align:center;}
.logoImage img {margin-top:10%;}
.loginPanel {margin:0px auto;width:100%;text-align:center;}
.panelLogin {margin-top:10px;display:inline-block;width:360px;border:1px solid #D1D1D1;box-shadow:0px 0px 10px rgba(0, 0, 0, .2);}
.panel-title {padding-top:4px;padding-left:36px;text-align:left;font-size:14px;height:23px;}
.loginBoxtTitle {background:url(<mc:cp key="imgIcon"/>/login.png) no-repeat 0px 0px;}
.panel-body {padding:25px 25px 20px 25px;}
.addonIcon {width:16px;}
.input-group {padding-bottom:8px;}
.buttonDiv {padding-top:16px;padding-bottom:0px;}
.additionalLink {padding-top:20px;font-size:13px;}
.passwordLink {float:left;width:50%;text-align:left;}
.registerLink {float:right;width:50%;text-align:right;}
</style>
<script type="text/javascript">
jsconfig.put("noLayoutWindow", true);
var popup = null;

$(function() {
	/*!
	 * event
	 */
	$(document).keypress(function(event) {
		if (event.which == 13) {
			var element = event.target;

			if ($(element).is("[name=loginId]") || $(element).is("[name=password]")) {
				doProcess({mode:"login"});
			}
		}
	});

	$("#btnLogin").click(function(event) {
		doProcess({mode:"login"});
	});

	$("#aResetPassword").click(function(event) {
		doProcess({mode:"resetPassword"});
	});

	$("#aRequestRegister").click(function(event) {
		doProcess({mode:"requestRegister"});
	});

	/*!
	 * process
	 */
	doProcess = function(params) {
		if (params.mode == "login") {
			if (!commonJs.doValidate("fmDefault")) {
				return;
			}

			commonJs.ajaxSubmit({
				url:"/login/login.do",
				dataType:"json",
				formId:"fmDefault",
				success:function(data, textStatus) {
					var result = commonJs.parseAjaxResult(data, textStatus, "json");
					if (result.isSuccess == true || result.isSuccess == "true") {
						var dataSet = result.dataSet;
						var actionString = "/index/dashboard.do";

						commonJs.openDialog({
							type:"information",
							contents:result.message+" "+dataSet.getValue(0, "USER_NAME")+"!",
							blind:true,
							draggable:false,
							width:350,
							buttons:[{
								caption:"Ok",
								callback:function() {
									commonJs.doSubmit({
										formId:"fmDefault",
										action:actionString
									});
								}
							}]
						});
					} else {
						commonJs.error(result.message);
					}
				}
			});
		} else {
			if (params.mode == "resetPassword") {
				params = {
					popupId:"ResetPassword",
					url:"/login/resetPassword.do",
					paramData:{},
					header:"<mc:msg key="login.header.resetPassword"/>",
					blind:false,
					draggable:false,
					width:400,
					height:266
				};
			} else if (params.mode == "requestRegister") {
				params = {
					popupId:"Request Register",
					url:"/login/requestRegister.do",
					paramData:{},
					header:"<mc:msg key="login.header.requestRegister"/>",
					blind:false,
					draggable:false,
					width:400,
					height:406
				};
			}

			popup = commonJs.openPopup(params);
		}
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		$("[name=loginId]").focus();
	});
});
</script>
</head>
<%/************************************************************************************************
* Page & Header
************************************************************************************************/%>
<body>
<form id="fmDefault" name="fmDefault" method="post" action="">
<div id="divLogo" class="logoImage">
	<ui:img id="imgLogo" src="<mc:cp key=imgIcon/>/logoPerci.png" style="width:100px;height:50px;"/>
</div>
<div id="divLoginPanel" class="loginPanel">
	<div class="panel panel-default panelLogin">
		<div class="panel-heading">
			<h3 class="panel-title loginBoxtTitle"><mc:msg key="login.header.main"/></h3>
		</div>
		<div class="panel-body">
			<div class="input-group">
				<div class="input-group-addon"><ui:icon className="fa-user fa-lg addonIcon"/></div>
				<ui:text id="loginId" name="loginId" value="dustin" className="form-control" style="padding:6px 12px" placeHolder="login.header.loginId" checkName="login.header.loginId" options="mandatory"/>
			</div>
			<div class="input-group">
				<div class="input-group-addon"><ui:icon className="fa-lock fa-lg addonIcon"/></div>
				<ui:password id="password" name="password" value="dustin" className="form-control" style="padding:6px 12px" placeHolder="login.header.password" checkName="login.header.password" options="mandatory"/>
			</div>
			<div class="buttonDiv">
				<ui:button id="btnLogin" type="success" caption="login.button.login" iconClass="fa-key" buttonStyle="padding-top:8px;width:100%;height:40px;font-size:14px;"/>
			</div>
			<div class="additionalLink">
				<div class="passwordLink">
					<ui:anchor id="aResetPassword" caption="login.button.resetPassword" className="aEn"/>
				</div>
				<div class="registerLink">
					<ui:anchor id="aRequestRegister" caption="login.button.requestRegister" className="aEn"/>
				</div>
			</div>
		</div>
	</div>
</div>
</form>
</body>
</html>