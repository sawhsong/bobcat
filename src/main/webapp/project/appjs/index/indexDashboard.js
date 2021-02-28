/**
 * indexDashboard.js
 */
jsconfig.put("noWest", true);
$(function() {
	/*!
	 * event
	 */
	$("#tblGridAnnouncement .aEn").click(function(event) {
		goMenu('BBS', 'Bulletin Board', '#', 'BBS0202', 'Announcement', '/bbs/0202/getDefault.do');
	});

	$("#tblGridFreeBoard .aEn").click(function(event) {
		goMenu('BBS', 'Bulletin Board', '#', 'BBS0204', 'Free Board', '/bbs/0204/getDefault.do');
	});

	$("#tblGridIncome .aEn").click(function(event) {
		goMenu('RKM', 'Record Keeping', '#', 'RKM0202', 'Sales Income', '/rkm/0202/getDefault.do');
	});

	$("#tblGridExpense .aEn").click(function(event) {
		goMenu('RKM', 'Record Keeping', '#', 'RKM0402', 'General Expense', '/rkm/0402/getDefault.do');
	});
	/*!
	 * process
	 */
	goMenu = function(headerMenuId, headerMenuName, headerMenuUrl, leftMenuId, leftMenuName, leftMenuUrl) {
		$("#hdnHeaderMenuId").val(headerMenuId);
		$("#hdnHeaderMenuName").val(headerMenuName);
		$("#hdnHeaderMenuUrl").val(headerMenuUrl);
		$("#hdnLeftMenuId").val(leftMenuId);
		$("#hdnLeftMenuName").val(leftMenuName);
		$("#hdnLeftMenuUrl").val(leftMenuUrl);

		commonJs.doSubmit({form:$("form:eq(0)"), action:leftMenuUrl});
	};

	getAnnouncement = () => {
		commonJs.showProcMessageOnElement("sectionAnnouncement");
		commonJs.doSearch({
			url:"/index/getAnnouncementList.do",
			onSuccess:(result) => {
				var ds = result.dataSet;
				var html = "";

				$("#tbodyGridAnnouncement").html("");

				if (ds.getRowCnt() > 0) {
					for (var i=0; i<ds.getRowCnt(); i++) {
						var gridTr = new UiGridTr();

						gridTr.setClassName("noBorderAll");

						gridTr.addChild(new UiGridTd().addClassName("Lt").addChild(new UiAnchor().setText(ds.getValue(i, "ARTICLE_SUBJECT")).setScript("goDetail('Announcement', '"+ds.getValue(i, "ARTICLE_ID")+"')")));
						gridTr.addChild(new UiGridTd().addClassName("Lt").setText(ds.getValue(i, "WRITER_NAME")));
						gridTr.addChild(new UiGridTd().addClassName("Rt").setText(commonJs.nvl(ds.getValue(i, "UPDATE_DATE"), ds.getValue(i, "INSERT_DATE"))));

						html += gridTr.toHtmlString();
					}
				} else {
					var gridTr = new UiGridTr();

					gridTr.setClassName("noBorderAll noStripe");

					gridTr.addChild(new UiGridTd().addClassName("Ct").setAttribute("colspan:3").setText("No Announcement found."));
					html += gridTr.toHtmlString();
				}

				$("#tbodyGridAnnouncement").append($(html));

				commonJs.hideProcMessageOnElement("sectionAnnouncement");
			}
		});
	};
	/*!
	 * load event (document / window)
	 */
	$(window).load(function() {
		getAnnouncement();
	});
});