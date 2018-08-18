<!-- Keep the order of css files -->
<link type="text/css" rel="stylesheet" href="<tag:cp key="cssJqUi"/>/jquery-ui.css"/>
<link type="text/css" rel="stylesheet" href="<tag:cp key="cssBootstrap"/>/bootstrap.css"/>
<link type="text/css" rel="stylesheet" href="<tag:cp key="cssBootstrap"/>/font-awesome.css"/>
<link type="text/css" rel="stylesheet" href="<tag:cp key="cssTheme"/>/default.css"/>

<script type="text/javascript" src="<tag:cp key="jsJq"/>/jquery-2.1.4.js"></script>
<script type="text/javascript" src="<tag:cp key="jsJq"/>/jquery-ui.js"></script>
<script type="text/javascript" src="<tag:cp key="jsJq"/>/bootstrap.js"></script>
<script type="text/javascript" src="<tag:cp key="jsNg"/>/angular.js"></script>

<script type="text/javascript" src="<tag:cp key="jsMessage"/>/messages_${sessionScope.langCode}.js"></script>
<script type="text/javascript" src="<tag:cp key="jsMessage"/>/messages-framework_${sessionScope.langCode}.js"></script>

<script type="text/javascript" src="<tag:cp key="jsJqPlugin"/>/jquery.blockUI.js"></script>
<script type="text/javascript" src="<tag:cp key="jsJqPlugin"/>/jquery.corner.js"></script>
<script type="text/javascript" src="<tag:cp key="jsJqPlugin"/>/jquery.layout-latest.js"></script>
<script type="text/javascript" src="<tag:cp key="jsJqPlugin"/>/jquery.textshadow.js"></script>
<script type="text/javascript" src="<tag:cp key="jsJqPlugin"/>/jquery.maskedinput.js"></script>
<script type="text/javascript" src="<tag:cp key="jsJqPlugin"/>/moment.js"></script>
<script type="text/javascript" src="<tag:cp key="jsJqPlugin"/>/numeral.js"></script>
<script type="text/javascript" src="<tag:cp key="jsJqPlugin"/>/jquery.number.js"></script>
<script type="text/javascript" src="<tag:cp key="jsJqPlugin"/>/sortableTable.js"></script>
<script type="text/javascript" src="<tag:cp key="jsJqPlugin"/>/jquery.actual.js"></script>
<script type="text/javascript" src="<tag:cp key="jsJqPlugin"/>/superfish.js"></script>
<script type="text/javascript" src="<tag:cp key="jsJqPlugin"/>/bootstrap-select.js"></script>
<script type="text/javascript" src="<tag:cp key="jsJqPlugin"/>/bowser.js"></script>

<script type="text/javascript" src="<tag:cp key="ckEditor"/>/ckeditor.js"></script>
<script type="text/javascript" src="<tag:cp key="ckEditor"/>/adapters/jquery.js"></script>
<script type="text/javascript" src="<tag:cp key="slider"/>/js/jssor.core.js"></script>
<script type="text/javascript" src="<tag:cp key="slider"/>/js/jssor.slider.js"></script>
<script type="text/javascript" src="<tag:cp key="slider"/>/js/jssor.utils.js"></script>

<script type="text/javascript" src="<tag:cp key="jsCommon"/>/core.js"></script>
<script type="text/javascript" src="<tag:cp key="jsCommon"/>/popup.js"></script>
<script type="text/javascript" src="<tag:cp key="jsCommon"/>/calendar.js"></script>
<script type="text/javascript" src="<tag:cp key="jsCommon"/>/validator.js"></script>
<script type="text/javascript" src="<tag:cp key="jsCommon"/>/fixedHeaderTable.js"></script>
<script type="text/javascript" src="<tag:cp key="jsCommon"/>/ajax.js"></script>
<script type="text/javascript" src="<tag:cp key="jsCommon"/>/fileElement.js"></script>
<script type="text/javascript" src="<tag:cp key="jsCommon"/>/dataSet.js"></script>
<script type="text/javascript" src="<tag:cp key="jsCommon"/>/contextMenu.js"></script>
<script type="text/javascript" src="<tag:cp key="jsCommon"/>/commonJs.js"></script>

<script type="text/javascript">
/*!
 * session attributes
 */
globalMap.put("frameworkName", "${sessionScope.frameworkName}");
globalMap.put("projectName", "${sessionScope.projectName}");
globalMap.put("langCode", "${sessionScope.langCode}");
globalMap.put("themeId", "${sessionScope.themeId}");
globalMap.put("maxRowsPerPage", "${sessionScope.maxRowsPerPage}");
globalMap.put("pageNumsPerPage", "${sessionScope.pageNumsPerPage}");
/*!
 * paths
 */
globalMap.put("shareRoot", "<tag:cp key="sharedRoot"/>");
globalMap.put("sharePage", "<tag:cp key="sharedPage"/>");
globalMap.put("shareCss", "<tag:cp key="sharedCss"/>");
globalMap.put("shareImg", "<tag:cp key="sharedImg"/>");
globalMap.put("shareJs", "<tag:cp key="sharedJs"/>");
globalMap.put("cssJqUi", "<tag:cp key="cssJqUi"/>");
globalMap.put("imgIcon", "<tag:cp key="imgIcon"/>");
globalMap.put("imgPhoto", "<tag:cp key="path.image.photo"/>");
globalMap.put("imgSortableTable", "<tag:cp key="imgSortableTable"/>");
globalMap.put("jsCommon", "<tag:cp key="jsCommon"/>");
globalMap.put("jsJq", "<tag:cp key="jsJq"/>");
globalMap.put("jsNg", "<tag:cp key="jsNg"/>");
globalMap.put("jsJqPlugin", "<tag:cp key="jsJqPlugin"/>");
globalMap.put("jsMessage", "<tag:cp key="jsMessage"/>");
globalMap.put("cssTheme", "<tag:cp key="cssTheme"/>");
globalMap.put("imgTheme", "<tag:cp key="imgTheme"/>");
globalMap.put("imgThemeCom", "<tag:cp key="imgThemeCom"/>");
globalMap.put("imgThemeCal", "<tag:cp key="imgThemeCal"/>");
globalMap.put("imgThemePage", "<tag:cp key="imgThemePage"/>");
globalMap.put("ckEditor", "<tag:cp key="ckEditor"/>");
globalMap.put("slider", "<tag:cp key="slider"/>");
globalMap.put("bootstrap", "<tag:cp key="bootstrap"/>");
/*!
 * jQuery UI
 */
globalMap.put("useJqTooltip", false);
globalMap.put("useJqSelectmenu", true);
globalMap.put("useJqButton", true);
/*!
 * etc
 */
globalMap.put("basicDateTimeJs", "<tag:cp key="format.basic.dateTime.js"/>");
globalMap.put("dateTimeFormatJs", "<tag:cp key="format.dateTime.js"/>");
globalMap.put("dateFormatJs", "<tag:cp key="dateFormatJs"/>");
globalMap.put("dateFormatJs_en", "<tag:cp key="dateFormatJs_en"/>");
globalMap.put("dateFormatJs_ko", "<tag:cp key="dateFormatJs_ko"/>");
globalMap.put("submitEffect", "<tag:cp key="submitEffect"/>");
globalMap.put("customFileObject", "<tag:cp key="customFileObject"/>");
globalMap.put("recordDelimiter", "<tag:cp key="recordDelimiter"/>");
globalMap.put("dataDelimiter", "<tag:cp key="dataDelimiter"/>");
globalMap.put("dataSetHeaderDelimiter", "<tag:cp key="dataSetHeaderDelimiter"/>");
globalMap.put("effectDuration", 400);
globalMap.put("pagehandlerActionType", "<tag:cp key="pagehandlerActionType"/>");
globalMap.put("webServiceProviderUrl", "<tag:cp key="webService.provider.url"/>");
globalMap.put("maxRowsPerPageArray", "<tag:cp key="view.data.maxRowsPerPage"/>");
/*!
 * auto-setup search criteria
 */
globalMap.put("autoSetSearchCriteria", "<tag:cp key="autoSetSearchCriteria"/>");
globalMap.put("searchCriteriaElementSuffix", "<tag:cp key="searchCriteriaElementSuffix"/>");
globalMap.put("searchCriteriaDataSetString", "${pageScope.searchCriteriaDataSet.toStringForJs()}");
</script>
<script type="text/javascript" src="<tag:cp key="jsCommon"/>/contextMenuItems.js"></script>