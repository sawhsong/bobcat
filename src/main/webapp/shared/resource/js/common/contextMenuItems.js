/**
 * Predefined context menu items
 */
var ctxMenu = {
	// Common action context menu
	commonAction : [{
		name:framework.caption.ctxActionViewDetail,
		img:"fa-list-alt",
		fun:function() {}
	}, {
		name:framework.caption.ctxActionEdit,
		img:"fa-edit",
		fun:function() {}
	}, {
		name:framework.caption.ctxActionDelete,
		img:"fa-times",
		fun:function() {}
	}],

	// Export context menu
	commonExport : [{
		name:framework.caption.exportToExcelAll,
		fileType:"Excel",
		dataRange:"All",
		img:"fa-file-excel-o",
		fun:function() {}
	}, {
		name:framework.caption.exportToExcelCurrentPage,
		fileType:"Excel",
		dataRange:"Current",
		img:"fa-file-excel-o",
		fun:function() {}
	}, {
		name:framework.caption.exportToPdfAll,
		fileType:"PDF",
		dataRange:"All",
		img:"fa-file-pdf-o",
		fun:function() {}
	}, {
		name:framework.caption.exportToPdfCurrentPage,
		fileType:"PDF",
		dataRange:"Current",
		img:"fa-file-pdf-o",
		fun:function() {}
	}, {
		name:framework.caption.exportToHtmlAll,
		fileType:"HTML",
		dataRange:"All",
		img:"fa-file-code-o",
		fun:function() {}
	}, {
		name:framework.caption.exportToHtmlCurrentPage,
		fileType:"HTML",
		dataRange:"Current",
		img:"fa-file-code-o",
		fun:function() {}
	}],

	// LoggedIn global menu
	loggedInUser : [{
		name:framework.caption.ctxMyProfile,
		img:jsconfig.get("imgThemeCom")+"/icnUser-MyProfile_Black.png",
//		title:"My Profile",
		fun:function() {}
	}, {
		name:framework.caption.ctxLogOut,
		img:jsconfig.get("imgThemeCom")+"/icnUser-LogOut_Black.png",
//		title:"Log Out",
		fun:function() {}
	}],

	// Board(Notice, BBS) Action context menu
	boardAction : [{
		name:framework.caption.ctxActionViewDetail,
		img:"fa-list-alt",
		fun:function() {}
	}, {
		name:framework.caption.ctxActionEdit,
		img:"fa-edit",
		fun:function() {}
	}, {
		name:framework.caption.ctxActionReply,
		img:"fa-reply-all",
		fun:function() {}
	}, {
		name:framework.caption.ctxActionDelete,
		img:"fa-times",
		fun:function() {}
	}],

	// DTOGenerator Action context menu
	dtoGeneratorAction : [{
		name:framework.caption.ctxActionViewDetail,
		img:"fa-list-alt",
		fun:function() {}
	}, {
		name:framework.caption.ctxActionGenerate,
		img:"fa-gears",
		fun:function() {}
	}],

	// Data Entry Action context menu
	dataEntrySalesAction : [{
		name:messages.caption.editItem,
		img:"fa-edit",
		fun:function() {}
	}, {
		name:messages.caption.deleteItem,
		img:"fa-trash",
		fun:function() {}
	}],

	// Data Entry Action context menu
	dataEntryAction : [{
		name:messages.caption.saveItem,
		img:"fa-save",
		fun:function() {}
	}, {
		name:messages.caption.cancelItem,
		img:"fa-refresh",
		fun:function() {}
	}, {
		name:messages.caption.deleteItem,
		img:"fa-trash",
		fun:function() {}
	}]
};