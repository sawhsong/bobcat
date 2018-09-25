/**
 * jssor image slider
 * Usage : in $(window).load() event
 * 		 : selector must have an ID
 * 			$("#slider").imageSlider({
 * 				width:1000,								// [mandatory : slider div width]
 * 				height:500,								// [mandatory : slider div height]
 * 				cursor:pointer,							// [optional : cursor]
 * 				thumbnail:true							// [optional : true : displays thumbnail, [default:false]]
 * 				thumbnailHeight:100,					// [conditional mandatory : height of thumbnail if thumbnail is true]
 * 				thumbnailTheme:1,						// [optional : thumbnail theme number (1, 2, 3, 4, 5, 6)]
 * 				thumbType:thumb							// [optional : thumbnail type ([thumb] / bullet)]
 * 				arrow:true,								// [optional : true : displays left/right arrows, [default:true]]
 * 				arrowTheme:1,							// [optional : arrow theme number (1, 2, 3, 4, 5, 6, 7, 8, 9, 10)]
 * 			});
 */

/*
		var sliderTransitions = [
			{$Duration:1200,x:0.3,$During:{$Left:[0.3,0.7]},$Easing:{$Left:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
			{$Duration:1200,x:-0.3,$SlideOut:true,$Easing:{$Left:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
			{$Duration:1200,x:-0.3,$During:{$Left:[0.3,0.7]},$Easing:{$Left:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
			{$Duration:1200,x:0.3,$SlideOut:true,$Easing:{$Left:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
			{$Duration:1200,y:0.3,$During:{$Top:[0.3,0.7]},$Easing:{$Top:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
			{$Duration:1200,y:-0.3,$SlideOut:true,$Easing:{$Top:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
			{$Duration:1200,y:-0.3,$During:{$Top:[0.3,0.7]},$Easing:{$Top:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
			{$Duration:1200,y:0.3,$SlideOut:true,$Easing:{$Top:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
			{$Duration:1200,x:0.3,$Cols:2,$During:{$Left:[0.3,0.7]},$ChessMode:{$Column:3},$Easing:{$Left:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
			{$Duration:1200,x:0.3,$Cols:2,$SlideOut:true,$ChessMode:{$Column:3},$Easing:{$Left:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
			{$Duration:1200,y:0.3,$Rows:2,$During:{$Top:[0.3,0.7]},$ChessMode:{$Row:12},$Easing:{$Top:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
			{$Duration:1200,y:0.3,$Rows:2,$SlideOut:true,$ChessMode:{$Row:12},$Easing:{$Top:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
			{$Duration:1200,y:0.3,$Cols:2,$During:{$Top:[0.3,0.7]},$ChessMode:{$Column:12},$Easing:{$Top:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
			{$Duration:1200,y:-0.3,$Cols:2,$SlideOut:true,$ChessMode:{$Column:12},$Easing:{$Top:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
			{$Duration:1200,x:0.3,$Rows:2,$During:{$Left:[0.3,0.7]},$ChessMode:{$Row:3},$Easing:{$Left:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
			{$Duration:1200,x:-0.3,$Rows:2,$SlideOut:true,$ChessMode:{$Row:3},$Easing:{$Left:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
			{$Duration:1200,x:0.3,y:0.3,$Cols:2,$Rows:2,$During:{$Left:[0.3,0.7],$Top:[0.3,0.7]},$ChessMode:{$Column:3,$Row:12},$Easing:{$Left:$Jease$.$InCubic,$Top:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
			{$Duration:1200,x:0.3,y:0.3,$Cols:2,$Rows:2,$During:{$Left:[0.3,0.7],$Top:[0.3,0.7]},$SlideOut:true,$ChessMode:{$Column:3,$Row:12},$Easing:{$Left:$Jease$.$InCubic,$Top:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
			{$Duration:1200,$Delay:20,$Clip:3,$Assembly:260,$Easing:{$Clip:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
			{$Duration:1200,$Delay:20,$Clip:3,$SlideOut:true,$Assembly:260,$Easing:{$Clip:$Jease$.$OutCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
			{$Duration:1200,$Delay:20,$Clip:12,$Assembly:260,$Easing:{$Clip:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
			{$Duration:1200,$Delay:20,$Clip:12,$SlideOut:true,$Assembly:260,$Easing:{$Clip:$Jease$.$OutCubic,$Opacity:$Jease$.$Linear},$Opacity:2}
		];

		var sliderOptions = {
			$AutoPlay: 1,
			$SlideshowOptions: {
				$Class: $JssorSlideshowRunner$,
				$Transitions: sliderTransitions,
				$TransitionsOrder: 1
			},
			$ArrowNavigatorOptions: {
				$Class: $JssorArrowNavigator$
			},
			$ThumbnailNavigatorOptions: {
				$Class: $JssorThumbnailNavigator$,
				$SpacingX: 5,
				$SpacingY: 5
			}
		};

		var jssor_1_slider = new $JssorSlider$("slider", sliderOptions);

		var maxWidth = 980;
		function ScaleSlider() {
			var containerElement = jssor_1_slider.$Elmt.parentNode;
			var containerWidth = containerElement.clientWidth;

			if (containerWidth) {
				var expectedWidth = Math.min(maxWidth || containerWidth, containerWidth);

				jssor_1_slider.$ScaleWidth(expectedWidth);
			} else {
				window.setTimeout(ScaleSlider, 30);
			}
		}

		ScaleSlider();

		$(window).bind("load", ScaleSlider);
		$(window).bind("resize", ScaleSlider);
		$(window).bind("orientationchange", ScaleSlider);
*/
(function($) {
	$.fn.imageSlider = function(options) {
		return this.each(function() {
			if (options.width == null || options.width <= 0) {throw new Error("Width" + framework.messages.mandatory); return;}
			if (options.height == null || options.height <= 0) {throw new Error("Height" + framework.messages.mandatory); return;}

			var maxWidth = 1000;
			var html = "", sliderAreaHtml = "";
			var cursor = options.cursor || "move", thumbnail = options.thumbnail || false, arrow = options.arrow || false;
			var thumbnailHeight = 0, thumbType = "", thumbnailTheme = "", arrowTheme = "";
			var containerHeight = 0;
			var sliderTransitions = [];

			if (thumbnail) {
				thumbnailHeight = options.thumbnailHeight || 100;
			} else {
				thumbnailHeight = 0;
			}
			thumbType = options.thumbType || "thumb";
			thumbnailTheme = options.thumbnailTheme || "1";
			arrowTheme = options.arrowTheme || "1";

			containerHeight = $.nony.toNumber(options.height - thumbnailHeight);

			sliderAreaHtml = $(this).html();
			html += "<div data-u=\"slides\" style=\"cursor:"+cursor+";position:relative;top:0px;left:0px;width:"+options.width+"px;height:"+containerHeight+"px;overflow:hidden;\">";
			html += $(this).html();
			html += "</div>";

			$(this).css({
				position:"relative",
				margin:"0 auto",
				top:"0px",
				left:"0px",
				width:""+options.width+"px",
				height:""+options.height+"px",
				overflow:"hidden",
				visibility:"hidden"
			});

			$(this).html(html);




			var sliderTransitions = [
				{$Duration:1200,x:0.3,$During:{$Left:[0.3,0.7]},$Easing:{$Left:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
				{$Duration:1200,x:-0.3,$SlideOut:true,$Easing:{$Left:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
				{$Duration:1200,x:-0.3,$During:{$Left:[0.3,0.7]},$Easing:{$Left:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
				{$Duration:1200,x:0.3,$SlideOut:true,$Easing:{$Left:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
				{$Duration:1200,y:0.3,$During:{$Top:[0.3,0.7]},$Easing:{$Top:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
				{$Duration:1200,y:-0.3,$SlideOut:true,$Easing:{$Top:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
				{$Duration:1200,y:-0.3,$During:{$Top:[0.3,0.7]},$Easing:{$Top:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
				{$Duration:1200,y:0.3,$SlideOut:true,$Easing:{$Top:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
				{$Duration:1200,x:0.3,$Cols:2,$During:{$Left:[0.3,0.7]},$ChessMode:{$Column:3},$Easing:{$Left:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
				{$Duration:1200,x:0.3,$Cols:2,$SlideOut:true,$ChessMode:{$Column:3},$Easing:{$Left:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
				{$Duration:1200,y:0.3,$Rows:2,$During:{$Top:[0.3,0.7]},$ChessMode:{$Row:12},$Easing:{$Top:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
				{$Duration:1200,y:0.3,$Rows:2,$SlideOut:true,$ChessMode:{$Row:12},$Easing:{$Top:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
				{$Duration:1200,y:0.3,$Cols:2,$During:{$Top:[0.3,0.7]},$ChessMode:{$Column:12},$Easing:{$Top:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
				{$Duration:1200,y:-0.3,$Cols:2,$SlideOut:true,$ChessMode:{$Column:12},$Easing:{$Top:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
				{$Duration:1200,x:0.3,$Rows:2,$During:{$Left:[0.3,0.7]},$ChessMode:{$Row:3},$Easing:{$Left:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
				{$Duration:1200,x:-0.3,$Rows:2,$SlideOut:true,$ChessMode:{$Row:3},$Easing:{$Left:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
				{$Duration:1200,x:0.3,y:0.3,$Cols:2,$Rows:2,$During:{$Left:[0.3,0.7],$Top:[0.3,0.7]},$ChessMode:{$Column:3,$Row:12},$Easing:{$Left:$Jease$.$InCubic,$Top:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
				{$Duration:1200,x:0.3,y:0.3,$Cols:2,$Rows:2,$During:{$Left:[0.3,0.7],$Top:[0.3,0.7]},$SlideOut:true,$ChessMode:{$Column:3,$Row:12},$Easing:{$Left:$Jease$.$InCubic,$Top:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
				{$Duration:1200,$Delay:20,$Clip:3,$Assembly:260,$Easing:{$Clip:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
				{$Duration:1200,$Delay:20,$Clip:3,$SlideOut:true,$Assembly:260,$Easing:{$Clip:$Jease$.$OutCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
				{$Duration:1200,$Delay:20,$Clip:12,$Assembly:260,$Easing:{$Clip:$Jease$.$InCubic,$Opacity:$Jease$.$Linear},$Opacity:2},
				{$Duration:1200,$Delay:20,$Clip:12,$SlideOut:true,$Assembly:260,$Easing:{$Clip:$Jease$.$OutCubic,$Opacity:$Jease$.$Linear},$Opacity:2}
			];
			var sliderOptions = {
				$AutoPlay:1,
				$SlideshowOptions: {
					$Class: $JssorSlideshowRunner$,
					$Transitions: sliderTransitions,
					$TransitionsOrder: 1
				},
				$ArrowNavigatorOptions: {
					$Class: $JssorArrowNavigator$
				},
				$ThumbnailNavigatorOptions: {
					$Class: $JssorThumbnailNavigator$,
					$SpacingX: 5,
					$SpacingY: 5
				}
			};
		
			var jssorSlider = new $JssorSlider$("slider", sliderOptions);
		

			function ScaleSlider() {
				var containerElement = jssorSlider.$Elmt.parentNode;
				var containerWidth = containerElement.clientWidth;
		
				if (containerWidth) {
					var expectedWidth = Math.min(maxWidth || containerWidth, containerWidth);
		
					jssorSlider.$ScaleWidth(expectedWidth);
				} else {
					window.setTimeout(ScaleSlider, 30);
				}
			}

			ScaleSlider();

//			$(window).bind("load", ScaleSlider);
			$(window).bind("resize", ScaleSlider);
			$(window).bind("orientationchange", ScaleSlider);

		});
	};
})(jQuery);