<%/************************************************************************************************
* Description
* - 
************************************************************************************************/%>
<div id="divFooterLeft"></div>
<div id="divFooterCenter">
	<div id="divCopyRight">
		<table class="tblDefault" style="width:100%;">
			<colgroup>
				<col width="33%"/>
				<col width="34%"/>
				<col width="33%"/>
			</colgroup>
			<tr>
				<td class="tdDefault Lt">
					(<mc:msg key="login.header.tel"/>)&nbsp;<mc:msg key="login.header.telValue"/>&nbsp;/
					&nbsp;(<mc:msg key="login.header.fax"/>)&nbsp;<mc:msg key="login.header.faxValue"/>&nbsp;/
					&nbsp;<a href="mailto:<mc:msg key="login.header.emailValue"/>" style="cursor:pointer;"><mc:msg key="login.header.emailValue"/></a>
				</td>
				<td class="tdDefault Ct">&copy; <mc:msg key="main.copyright"/> / <%=CommonUtil.getSysdate("yyyy")%></td>
				<td class="tdDefault Rt"><mc:msg key="I990"/></td>
			</tr>
		</table>
	</div>
</div>
<div id="divFooterRight"></div>