<!-- Keep the order of css files -->
<link type="text/css" rel="stylesheet" href="<mc:cp key="cssJqUi"/>/jquery-ui.css"/>
<link type="text/css" rel="stylesheet" href="<mc:cp key="cssBootstrap"/>/bootstrap.css"/>
<link type="text/css" rel="stylesheet" href="<mc:cp key="cssBootstrap"/>/font-awesome.css"/>
<link type="text/css" rel="stylesheet" href="<mc:cp key="cssTheme"/>/default.css"/>

<script type="text/javascript" src="<mc:cp key="jsJq"/>/jquery-2.1.4.js"></script>
<script type="text/javascript" src="<mc:cp key="jsJq"/>/jquery-ui.js"></script>
<script type="text/javascript" src="<mc:cp key="jsJq"/>/bootstrap.js"></script>
<script type="text/javascript" src="<mc:cp key="jsNg"/>/angular.js"></script>

<script type="text/javascript" src="<mc:cp key="jsMessage"/>/messages_${sessionScope.langCode}.js"></script>
<script type="text/javascript" src="<mc:cp key="jsMessage"/>/messages-framework_${sessionScope.langCode}.js"></script>

<script type="text/javascript" src="<mc:cp key="jsJqPlugin"/>/jquery.blockUI.js"></script>
<script type="text/javascript" src="<mc:cp key="jsJqPlugin"/>/jquery.corner.js"></script>
<script type="text/javascript" src="<mc:cp key="jsJqPlugin"/>/jquery.layout-latest.js"></script>
<script type="text/javascript" src="<mc:cp key="jsJqPlugin"/>/jquery.textshadow.js"></script>
<script type="text/javascript" src="<mc:cp key="jsJqPlugin"/>/jquery.maskedinput.js"></script>
<script type="text/javascript" src="<mc:cp key="jsJqPlugin"/>/moment.js"></script>
<script type="text/javascript" src="<mc:cp key="jsJqPlugin"/>/numeral.js"></script>
<script type="text/javascript" src="<mc:cp key="jsJqPlugin"/>/jquery.number.js"></script>
<script type="text/javascript" src="<mc:cp key="jsJqPlugin"/>/sortableTable.js"></script>
<script type="text/javascript" src="<mc:cp key="jsJqPlugin"/>/jquery.actual.js"></script>
<script type="text/javascript" src="<mc:cp key="jsJqPlugin"/>/superfish.js"></script>
<script type="text/javascript" src="<mc:cp key="jsJqPlugin"/>/bootstrap-select.js"></script>
<script type="text/javascript" src="<mc:cp key="jsJqPlugin"/>/bowser.js"></script>

<script type="text/javascript" src="<mc:cp key="ckEditor"/>/ckeditor.js"></script>
<script type="text/javascript" src="<mc:cp key="ckEditor"/>/adapters/jquery.js"></script>
<script type="text/javascript" src="<mc:cp key="slider"/>/js/jssor.slider.min.js"></script>

<script type="text/javascript" src="<mc:cp key="jsCommon"/>/core.js"></script>
<script type="text/javascript" src="<mc:cp key="jsCommon"/>/popup.js"></script>
<script type="text/javascript" src="<mc:cp key="jsCommon"/>/calendar.js"></script>
<script type="text/javascript" src="<mc:cp key="jsCommon"/>/validator.js"></script>
<script type="text/javascript" src="<mc:cp key="jsCommon"/>/fixedHeaderTable.js"></script>
<script type="text/javascript" src="<mc:cp key="jsCommon"/>/ajax.js"></script>
<script type="text/javascript" src="<mc:cp key="jsCommon"/>/fileElement.js"></script>
<script type="text/javascript" src="<mc:cp key="jsCommon"/>/dataSet.js"></script>
<script type="text/javascript" src="<mc:cp key="jsCommon"/>/imageSlider.js"></script>

<script type="text/javascript" src="<mc:cp key="jsCommon"/>/UiAnchor.js"></script>
<script type="text/javascript" src="<mc:cp key="jsCommon"/>/UiCheckbox.js"></script>
<script type="text/javascript" src="<mc:cp key="jsCommon"/>/UiGridTd.js"></script>
<script type="text/javascript" src="<mc:cp key="jsCommon"/>/UiGridTr.js"></script>
<script type="text/javascript" src="<mc:cp key="jsCommon"/>/UiIcon.js"></script>

<script type="text/javascript" src="<mc:cp key="jsCommon"/>/contextMenu.js"></script>
<script type="text/javascript" src="<mc:cp key="jsCommon"/>/commonJs.js"></script>

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
globalMap.put("shareRoot", "<mc:cp key="sharedRoot"/>");
globalMap.put("sharePage", "<mc:cp key="sharedPage"/>");
globalMap.put("shareCss", "<mc:cp key="sharedCss"/>");
globalMap.put("shareImg", "<mc:cp key="sharedImg"/>");
globalMap.put("shareJs", "<mc:cp key="sharedJs"/>");
globalMap.put("cssJqUi", "<mc:cp key="cssJqUi"/>");
globalMap.put("imgIcon", "<mc:cp key="imgIcon"/>");
globalMap.put("imgPhoto", "<mc:cp key="path.image.photo"/>");
globalMap.put("imgSortableTable", "<mc:cp key="imgSortableTable"/>");
globalMap.put("jsCommon", "<mc:cp key="jsCommon"/>");
globalMap.put("jsJq", "<mc:cp key="jsJq"/>");
globalMap.put("jsNg", "<mc:cp key="jsNg"/>");
globalMap.put("jsJqPlugin", "<mc:cp key="jsJqPlugin"/>");
globalMap.put("jsMessage", "<mc:cp key="jsMessage"/>");
globalMap.put("cssTheme", "<mc:cp key="cssTheme"/>");
globalMap.put("imgTheme", "<mc:cp key="imgTheme"/>");
globalMap.put("imgThemeCom", "<mc:cp key="imgThemeCom"/>");
globalMap.put("imgThemeCal", "<mc:cp key="imgThemeCal"/>");
globalMap.put("imgThemePage", "<mc:cp key="imgThemePage"/>");
globalMap.put("ckEditor", "<mc:cp key="ckEditor"/>");
globalMap.put("slider", "<mc:cp key="slider"/>");
globalMap.put("bootstrap", "<mc:cp key="bootstrap"/>");
/*!
 * jQuery UI
 */
globalMap.put("useJqTooltip", true);
globalMap.put("useJqSelectmenu", true);
globalMap.put("useJqButton", true);
/*!
 * etc
 */
globalMap.put("basicDateTimeJs", "<mc:cp key="format.basic.dateTime.js"/>");
globalMap.put("dateTimeFormatJs", "<mc:cp key="format.dateTime.js"/>");
globalMap.put("dateFormatJs", "<mc:cp key="dateFormatJs"/>");
globalMap.put("dateFormatJs_en", "<mc:cp key="dateFormatJs_en"/>");
globalMap.put("dateFormatJs_ko", "<mc:cp key="dateFormatJs_ko"/>");
globalMap.put("submitEffect", "<mc:cp key="submitEffect"/>");
globalMap.put("customFileObject", "<mc:cp key="customFileObject"/>");
globalMap.put("recordDelimiter", "<mc:cp key="recordDelimiter"/>");
globalMap.put("dataDelimiter", "<mc:cp key="dataDelimiter"/>");
globalMap.put("dataSetHeaderDelimiter", "<mc:cp key="dataSetHeaderDelimiter"/>");
globalMap.put("effectDuration", 400);
globalMap.put("pagehandlerActionType", "<mc:cp key="pagehandlerActionType"/>");
globalMap.put("webServiceProviderUrl", "<mc:cp key="webService.provider.url"/>");
globalMap.put("maxRowsPerPageArray", "<mc:cp key="view.data.maxRowsPerPage"/>");
/*!
 * auto-setup search criteria
 */
globalMap.put("autoSetSearchCriteria", "<mc:cp key="autoSetSearchCriteria"/>");
globalMap.put("searchCriteriaElementSuffix", "<mc:cp key="searchCriteriaElementSuffix"/>");
globalMap.put("searchCriteriaDataSetString", "${pageScope.searchCriteriaDataSet.toStringForJs()}");
</script>
<script type="text/javascript" src="<mc:cp key="jsCommon"/>/contextMenuItems.js"></script>