<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>自动补全</title>
<script type="text/javascript" src="<%=basePath%>/resource/thirdpart/jquery-1.7.2.min.js"></script>
<script type="text/javascript">

	var hightlight = -1;
	var oInputField; //考虑到很多函数中都要使用
	var oPopDiv; //因此采用全局变量的形式
	var oconUl;
	//初始化变量
	function initVars() {
		oInputField = $("#txt1");
		oPopDiv = $("#popup");
		oconUl = $("#con_ul");
	}

	//清除提示内容
	function clearcon() {
		if (oconUl != null){
			oconUl.empty();
		}
		if (oPopDiv != null){
			oPopDiv.removeClass("show");
		}
	}

	function setcon(the_con) {
		//显示提示框，传入的参数即为匹配出来的结果组成的数组
		clearcon(); //每输入一个字母就先清除原先的提示，再继续
		oPopDiv.addClass("show");
		var reg = /[\",\[,\]]/g;
		for ( var i = 0; i < the_con.length; i++) {
			//将匹配的提示结果逐一显示给用户
			oconUl.append($("<li>" + the_con[i].replace(reg, "") + "</li>"));
			oconUl.find("li").click(function() {
				oInputField.val($(this).text());
				clearcon();
			}).hover(function() {
				$(this).addClass("mouseOver");
			}, function() {
				$(this).removeClass("mouseOver");
			});
		}
	}

	function findcon(event) {
		initVars();  //初始化变量
		var myEvent = event || window.event;
		var keycode = myEvent.keyCode; //获取键盘键值
		if ((keycode >= 65 && keycode <= 90) || keycode == 8 || keycode == 46) {
			if (oInputField.val().replace(/(^\s*)|(\s*$)/g, '') == "") {
				return;
			}//值为空，退出
			if (jQuery.trim(oInputField.val()).length > 0) {
				//获取异步数据

				$.post('autoComplete.do', {
					dirName : oInputField.val()
				}, function(data) {
					var aResult = new Array();
					if (data.length > 0) {
						aResult = data.split(",");
						setcon(aResult); //显示服务器结果
					} else {
						clearcon();
					}
				});
			} else {
				clearcon(); //无输入时清除提示框（例如用户按del键）
				hightlight = -1
			}
		} else if (keycode == 38 || keycode == 40) { //如果输入的是向上向下
			if (keycode == 38) {
				//向上
				var autoNodes = oconUl.find("li");
				if (hightlight != -1) {
					//把高亮节点恢复
					autoNodes.eq(hightlight).removeClass("mouseOver");
					hightlight--;
				} else {
					hightlight = autoNodes.length - 1
				}
				if (hightlight == -1) {
					//如果到顶 把高亮移动到最后
					hightlight = autoNodes.length - 1;
				}
				;
				autoNodes.eq(hightlight).addClass("mouseOver");
				//把值放入文本框中
				var context = oconUl.find("li").eq(hightlight).text();
				oInputField.val(context);
			}
			if (keycode == 40) {
				//向下
				var autoNodes = oconUl.find("li");
				if (hightlight != -1) {
					//把高亮节点恢复
					autoNodes.eq(hightlight).removeClass("mouseOver");
				}
				hightlight++;
				if (hightlight == autoNodes.length) {
					//如果到顶 把高亮移动到最后
					hightlight = 0;
				}
				autoNodes.eq(hightlight).addClass("mouseOver");
				//把值放入文本框中
				var context = oconUl.find("li").eq(hightlight).text();
				oInputField.val(context);
			}
		} else if (keycode == 13) {
			//如果输入的是回车
			if (hightlight != -1) {
				//取出节点的内容
				var context = oconUl.find("li").eq(hightlight).text();
				clearcon();
				hightlight = -1;
				oInputField.val(context);
			}
		}
	}
</script>
<style type="text/css">
body {
	font-family: Arial;
	font-size: 14px;
	padding: 0px;
	margin: 10px;
}

.txt1 { /* 用户输入框的样式 */
	width: 200px;
}

#popup { /* 提示框div块的样式 */
	position: absolute;
	left: 80px;
	top: 32px;
	width: 204px;
	/*border:solid 1px black;*/
	color: #004a7e;
}

#popup.show { /* 显示提示框的边框 */
	border: 1px solid #004a7e;
}

ul {
	list-style: none;
	margin: 0px;
	padding: 0px;
	color: #004a7e;
}

li.mouseOver {
	background-color: #004a7e;
	color: #FFFFFF;
}
</style>
</head>

<body onclick="clearcon()">
	<div id="con">
		自动补全：<input id="txt1" type="text" class="txt1" onkeyup="findcon();" />
	</div>
	<div id="popup">
		<ul id="con_ul"></ul>
	</div>
</body>
</html>