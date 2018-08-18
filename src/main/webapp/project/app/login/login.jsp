<%/************************************************************************************************
* Description
* - 
************************************************************************************************/%>
<%@ include file="/webapp/shared/page/incCommon.jsp"%>
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
<link rel="icon" type="image/png" href="<tag:cp key="imgIcon"/>/favicon.png">
<title><tag:msg key="main.system.title"/></title>
<%/************************************************************************************************
* Stylesheet & Javascript
************************************************************************************************/%>
<%@ include file="/webapp/shared/page/incCssJs.jsp"%>
<style type="text/css">
body {background:#FFFFFF;}
.logoImage {margin:0px auto;width:100%;text-align:center;}
.logoImage img {margin-top:5%;}
.loginPanel {margin:0px auto;width:100%;text-align:center;}
.panelLogin {margin-top:16px;display:inline-block;width:360px;border:1px solid #D1D1D1;box-shadow:0px 0px 10px rgba(0, 0, 0, .2);}
.panel-title {padding-top:4px;padding-left:36px;text-align:left;font-size:14px;height:23px;}
.loginBoxtTitle {background:url(<tag:cp key="imgIcon"/>/login.png) no-repeat 0px 0px;}
.panel-body {padding:25px 25px 20px 25px;}
.addonIcon {width:16px;}
.input-group {padding-bottom:4px;}
.buttonDiv {padding-top:18px;padding-bottom:0px;}
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
.loginDescription {margin:30px 10px 30px 10px;display:inline-block;width:560px;border-top:1px solid #D1D1D1;box-shadow:0px 0px 20px rgba(0, 0, 0, .2);}
.loginDescriptionArea .panel-body {padding:15px 15px 15px 15px;text-align:left;}
.descContents {line-height:180%;}
</style>
<script type="text/javascript">
globalMap.put("noLayoutWindow", true);
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
					header:"<tag:msg key="login.header.resetPassword"/>",
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
					header:"<tag:msg key="login.header.requestRegister"/>",
					blind:false,
					draggable:false,
					width:400,
					height:402
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
	<img id="imgLogo" src="<tag:cp key="imgIcon"/>/logoHKAccount.png" class="" style="width:130px;height:24px;"/>
</div>
<div id="divLoginPanel" class="loginPanel">
	<div class="panel panel-default panelLogin">
		<div class="panel-heading">
			<h3 class="panel-title loginBoxtTitle"><tag:msg key="login.header.main"/></h3>
		</div>
		<div class="panel-body">
			<div class="input-group">
				<div class="input-group-addon"><i class="fa fa-lg fa-user addonIcon"></i></div>
				<input type="text" id="loginId" name="loginId" value="" class="form-control" placeholder="LOGIN ID" checkName="<tag:msg key="login.header.loginId"/>" mandatory/>
			</div>
			<div class="input-group">
				<div class="input-group-addon"><i class="fa fa-lg fa-lock addonIcon"></i></div>
				<input type="password" id="password" name="password" value="" class="form-control" placeholder="PASSWORD" checkName="<tag:msg key="login.header.password"/>" mandatory/>
			</div>
			<div class="buttonDiv">
				<tag:button id="btnLogin" type="success" caption="login.button.login" iconClass="fa-key" buttonStyle="padding-top:8px;width:100%;height:40px;font-size:14px;"/>
			</div>
			<div class="additionalLink">
				<div class="passwordLink">
					<a id="aResetPassword" class="aNormal aEn"><tag:msg key="login.button.resetPassword"/></a>
				</div>
				<div class="registerLink">
					<a id="aRequestRegister" class="aNormal aEn"><tag:msg key="login.button.requestRegister"/></a>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="divDescriptionArea" class="loginDescriptionArea">
	<div class="panel panel-default loginDescription">
		<div class="panel-heading">
			<h3 class="panel-title loginDescriptionTitle"><tag:msg key="login.message.descHeader"/></h3>
		</div>
		<div class="panel-body descContents">
			<table class="tblDefault withPadding" style="width:100%;">
				<colgroup>
					<col width="100%"/>
				</colgroup>
				<tr>
					<td class="tdDefault"><tag:msg key="login.message.description"/></td>
				</tr>
			</table>
			<div class="verGap10"></div>
			<table class="tblDefault withPadding" style="width:100%;">
				<colgroup>
					<col width="25%"/>
					<col width="75%"/>
				</colgroup>
				<tr>
					<th class="thDefault"><tag:msg key="login.header.accountant"/></th>
					<td class="tdDefault"><tag:msg key="login.header.accountantName"/></td>
				</tr>
				<tr>
					<th class="thDefault"><tag:msg key="login.header.email"/></th>
					<td class="tdDefault"><tag:msg key="login.header.emailValue"/></td>
				</tr>
				<tr>
					<th class="thDefault"><tag:msg key="login.header.tel"/></th>
					<td class="tdDefault"><tag:msg key="login.header.telValue"/></td>
				</tr>
				<tr>
					<th class="thDefault"><tag:msg key="login.header.fax"/></th>
					<td class="tdDefault"><tag:msg key="login.header.faxValue"/></td>
				</tr>
			</table>
			<div class="verGap10"></div>
			<table class="tblDefault withPadding" style="width:100%;">
				<colgroup>
					<col width="100%"/>
				</colgroup>
				<tr>
					<td class="tdDefault"><tag:msg key="I990"/></td>
				</tr>
			</table>
		</div>
	</div>
</div>
</form>
</body>
</html>