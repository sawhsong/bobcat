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
				<td class="tdDefault">
					(<tag:msg key="login.header.tel"/>)&nbsp;<tag:msg key="login.header.telValue"/>&nbsp;/
					&nbsp;(<tag:msg key="login.header.fax"/>)&nbsp;<tag:msg key="login.header.faxValue"/>&nbsp;/
					&nbsp;&nbsp;<a href="mailto:<tag:msg key="login.header.emailValue"/>" style="cursor:pointer;"><tag:msg key="login.header.emailValue"/></a>
				</td>
				<td class="tdDefaultCt">&copy; HKAccounting.com.au / <%=CommonUtil.getSysdate("yyyy")%></td>
				<td class="tdDefaultRt"><tag:msg key="I990"/></td>
			</tr>
		</table>
	</div>
</div>
<div id="divFooterRight"></div>