/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Bbs0202UpdatePop.js
 *************************************************************************************************/
$(function() {
	/*!
	 * event
	 */
	$("#btnSave").click(function(event) {
		if (commonJs.doValidate("fmDefault")) {
			$("#fmDefault").attr("enctype", "multipart/form-data");

			commonJs.confirm({
				contents:com.message.Q001,
				buttons:[{
					caption:com.caption.yes,
					callback:function() {
						commonJs.doSubmit({
							form:"fmDefault",
							action:"/bbs/0202/exeUpdate.do",
							data:{
								articleId:"<%=sysBoard.getArticleId()%>"
							}
						});
					}
				}, {
					caption:com.caption.no,
					callback:function() {
					}
				}]
			});
		}
	});

	$("#btnClose").click(function(event) {
		parent.popup.close();
	});

	$("#btnAddFile").click(function(event) {
		commonJs.addFileSelectObject({
			appendToId:"divAttachedFile",
			rowBreak:false
		});
	});

	/*!
	 * process
	 */

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		parent.popup.setHeader(com.header.popHeaderEdit);
	});
});