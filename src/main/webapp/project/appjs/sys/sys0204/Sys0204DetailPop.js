/**************************************************************************************************
 * Framework Generated Javascript Source
 * - Sys0204DetailPop.js
 *************************************************************************************************/
$(function() {
	/*!
	 * event
	 */
	$("#btnEdit").click(function(event) {
		doProcessByButton({mode:"Update"});
	});

	$("#btnReply").click(function(event) {
		doProcessByButton({mode:"Reply"});
	});

	$("#btnDelete").click(function(event) {
		doProcessByButton({mode:"Delete"});
	});

	$("#btnClose").click(function(event) {
		parent.popup.close();
	});

	$(document).keypress(function(event) {
		if (event.which == 13) {
			var element = event.target;
		}
	});

	/*!
	 * process
	 */
	exeDownload = function(repositoryPath, originalName, newName) {
		commonJs.doSubmit({
			form:"fmDefault",
			action:"/download.do",
			data:{
				repositoryPath:repositoryPath,
				originalName:originalName,
				newName:newName
			}
		});
	};

	doProcessByButton = function(param) {
		var actionString = "";
		var params = {};

		if (param.mode == "Update") {
			actionString = "/sys/0204/getUpdate.do";
		} else if (param.mode == "Reply") {
			actionString = "/sys/0204/getInsert.do";
		} else if (param.mode == "Delete") {
			actionString = "/sys/0204/exeDelete.do";
		}

		params = {
			form:"fmDefault",
			action:actionString,
			data:{
				mode:param.mode,
				articleId:articleId
			}
		};

		if (param.mode == "Update") {
			parent.popup.resizeTo(0, 124);
		}

		if (param.mode == "Delete") {
			commonJs.doDelete({
				url:actionString,
				data:{articleId:articleId},
				onSuccess:function() {
					parent.popup.close();
					parent.doSearch();
				}
			});
		} else {
			commonJs.doSubmit(params);
		}
	};

	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
	});
});