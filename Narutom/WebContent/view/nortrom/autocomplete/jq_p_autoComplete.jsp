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
	<title>jQuery UI Autocomplete - Default functionality</title>
	<link rel="stylesheet" href="<%=basePath%>/resource/thirdpart/jquery_autocomplete/jquery.ui.all.css">
	<script src="<%=basePath%>/resource/thirdpart/jquery-1.8.3.js"></script>
	<script src="<%=basePath%>/resource/thirdpart/jquery_autocomplete/jquery.ui.core.js"></script>
	<script src="<%=basePath%>/resource/thirdpart/jquery_autocomplete/jquery.ui.widget.js"></script>
	<script src="<%=basePath%>/resource/thirdpart/jquery_autocomplete/jquery.ui.position.js"></script>
	<script src="<%=basePath%>/resource/thirdpart/jquery_autocomplete/jquery.ui.menu.js"></script>
	<script src="<%=basePath%>/resource/thirdpart/jquery_autocomplete/jquery.ui.autocomplete.js"></script>
	<link rel="stylesheet" href="<%=basePath%>/resource/thirdpart/jquery_autocomplete/demos.css">
	<script>
	
	$(function() {
		var availableTags = [
			"ActionScript",
			"AppleScript",
			"Asp",
			"BASIC",
			"C",
			"C++",
			"Clojure",
			"COBOL",
			"ColdFusion",
			"Erlang",
			"Fortran",
			"Groovy",
			"Haskell",
			"Java",
			"JavaScript",
			"Lisp",
			"Perl",
			"PHP",
			"Python",
			"Ruby",
			"Scala",
			"Scheme"
		];
		$( "#tags" ).autocomplete({
			source: availableTags
		});
	});
	</script>
</head>
<body>

<div class="ui-widget">
	<label for="tags">Tags: </label>
	<input id="tags">
</div>

<div class="demo-description">
<p>The Autocomplete widgets provides suggestions while you type into the field. Here the suggestions are tags for programming languages, give "ja" (for Java or JavaScript) a try.</p>
<p>The datasource is a simple JavaScript array, provided to the widget using the source-option.</p>
</div>
</body>
</html>
