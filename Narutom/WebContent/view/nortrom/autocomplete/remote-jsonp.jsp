<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>jQuery UI Autocomplete - Remote JSONP datasource</title>
	<link rel="stylesheet" href="<%=basePath%>/resource/thirdpart/jquery_autocomplete/jquery.ui.all.css">
	<script src="<%=basePath%>/resource/thirdpart/jquery-1.8.3.js"></script>
	<script src="<%=basePath%>/resource/thirdpart/jquery_autocomplete/jquery.ui.core.js"></script>
	<script src="<%=basePath%>/resource/thirdpart/jquery_autocomplete/jquery.ui.widget.js"></script>
	<script src="<%=basePath%>/resource/thirdpart/jquery_autocomplete/jquery.ui.position.js"></script>
	<script src="<%=basePath%>/resource/thirdpart/jquery_autocomplete/jquery.ui.menu.js"></script>
	<script src="<%=basePath%>/resource/thirdpart/jquery_autocomplete/jquery.ui.autocomplete.js"></script>
	<link rel="stylesheet" href="<%=basePath%>/resource/thirdpart/jquery_autocomplete/demos.css">
	<style>
	.ui-autocomplete-loading {
		background: white url('images/ui-anim_basic_16x16.gif') right center no-repeat;
	}
	#city { width: 25em; }
	</style>
	<script>
	$(function() {
		function log( message ) {
			$( "<div>" ).text( message ).prependTo( "#log" );
			$( "#log" ).scrollTop( 0 );
		}

		$( "#city" ).autocomplete({
			source: function( request, response ) {
				$.ajax({
					url: "autoComplete.do",
					data: {
						featureClass: "P",
					},
					success: function( data ) {
						response( $.map( data.geonames, function( item ) {
							return {
								label: item.name ,
								value: item.value
							}
						}));
					}
				});
			},
			minLength: 2,
			select: function( event, ui ) {
				log( ui.item ?
					"Selected: " + ui.item.label :
					"Nothing selected, input was " + this.value);
			},
			open: function() {
				$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
			},
			close: function() {
				$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
			}
		});
	});
	</script>
</head>
<body>

<div class="ui-widget">
	<label for="city">Your city: </label>
	<input id="city">
	Powered by <a href="http://geonames.org">geonames.org</a>
</div>

<div class="ui-widget" style="margin-top:2em; font-family:Arial">
	Result:
	<div id="log" style="height: 200px; width: 300px; overflow: auto;" class="ui-widget-content"></div>
</div>

<div class="demo-description">
<p>The Autocomplete widgets provides suggestions while you type into the field. Here the suggestions are cities, displayed when at least two characters are entered into the field.</p>
<p>In this case, the datasource is the <a href="http://geonames.org">geonames.org webservice</a>. While only the city name itself ends up in the input after selecting an element, more info is displayed in the suggestions to help find the right entry. That data is also available in callbacks, as illustrated by the Result area below the input.</p>
</div>
</body>
</html>
