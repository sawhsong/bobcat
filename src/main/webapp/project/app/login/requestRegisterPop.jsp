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
.panelHolder {margin:0px auto;width:100%;text-align:center;}
.panel {margin-top:20px;display:inline-block;width:360px;}
.panel-title {padding-top:4px;padding-left:36px;text-align:left;font-size:14px;height:23px;background:url(<tag:cp key="imgIcon"/>/login.png) no-repeat 0px 0px;}
.panel-body {padding:25px 25px 20px 25px;}
.addonIcon {width:16px;}
.input-group {padding-bottom:4px;}
.buttonDiv {padding-top:18px;padding-bottom:0px;}
.lblCheckEn {margin-top:6px;margin-left:12px;font-size:13px;}
</style>
<script type="text/javascript">
var popup = null;

$(function() {
	/*!
	 * event
	 */
	$(document).keypress(function(event) {
		if (event.which == 13) {
		}
	});

	$("#btnRequest").click(function() {
		doProcess();
	});

	/*!
	 * process
	 */
	doProcess = function() {
		if (!commonJs.doValidate("fmDefault")) {
			return;
		}

		if ($("#password").val() != $("#passwordConfirm").val()) {
			commonJs.error("<tag:msg key="login.message.confirmPassword"/>");
		} else {
			commonJs.ajaxSubmit({
				url:"/login/exeRequestRegister.do",
				dataType:"json",
				formId:"fmDefault",
				success:function(data, textStatus) {
					var result = commonJs.parseAjaxResult(data, textStatus, "json");
					if (result.isSuccess == true || result.isSuccess == "true") {
						popup = commonJs.openDialog({
							type:"information",
							contents:result.message,
							blind:true,
							width:300,
							buttons:[{
								caption:"Ok",
								callback:function() {
									parent.popup.close();
								}
							}]
						});
					} else {
						commonJs.error(result.message);
					}
				}
			});
		}
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		$("[name=userName]").focus();
	});
});
</script>
</head>
<%/************************************************************************************************
* Page & Header
************************************************************************************************/%>
<body>
<form id="fmDefault" name="fmDefault" method="post" action="">
<div id="divPopupWindowHolder" class="panelHolder">
	<div class="panel panel-default">
		<div class="panel-body">
			<div class="input-group">
				<div class="input-group-addon"><i class="fa fa-lg fa-credit-card addonIcon"></i></div>
				<input type="text" id="userName" name="userName" class="form-control" placeholder="USER NAME" checkName="<tag:msg key="login.header.userName"/>" mandatory/>
			</div>
			<div class="input-group">
				<div class="input-group-addon"><i class="fa fa-lg fa-user addonIcon"></i></div>
				<input type="text" id="loginId" name="loginId" class="form-control" placeholder="LOGIN ID" checkName="<tag:msg key="login.header.loginId"/>" mandatory/>
			</div>
			<div class="input-group">
				<div class="input-group-addon"><i class="fa fa-lg fa-lock addonIcon"></i></div>
				<input type="password" id="password" name="password" class="form-control" placeholder="PASSWORD" checkName="<tag:msg key="login.header.password"/>" mandatory/>
			</div>
			<div class="input-group">
				<div class="input-group-addon"><i class="fa fa-lg fa-lock addonIcon"></i></div>
				<input type="password" id="passwordConfirm" name="passwordConfirm" class="form-control" placeholder="CONFIRM PASSWORD" checkName="<tag:msg key="login.header.password"/>" mandatory/>
			</div>
			<div class="input-group">
				<div class="input-group-addon"><i class="fa fa-lg fa-envelope addonIcon"></i></div>
				<input type="text" id="email" name="email" class="form-control" placeholder="EMAIL" checkName="<tag:msg key="login.header.email"/>" mandatory option="email"/>
			</div>
			<label class="lblCheckEn block"><input type="checkbox" name="sendEmail" class="chkEn" value="Y" checked/><tag:msg key="login.label.sendMail"/></label>
			<div class="buttonDiv">
				<tag:button id="btnRequest" type="primary" caption="login.button.requestRegister" iconClass="fa-bullhorn" buttonStyle="padding-top:8px;width:100%;height:40px;font-size:14px;"/>
			</div>
		</div>
	</div>
</div>
</form>
</body>
</html>