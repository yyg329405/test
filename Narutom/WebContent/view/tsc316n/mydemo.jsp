<%@ page contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<HEAD>
	<TITLE>TESO 探索者 指纹仪OCX控件测试 - 天诚盛业</TITLE>
	<META NAME="Generator" CONTENT="EditPlus">
	<META NAME="Author" CONTENT="">
	<META NAME="Keywords" CONTENT="">
	<META NAME="Description" CONTENT="">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
</HEAD>
<base href="<%=basePath%>">
<%@ include file="/view/common/include.jsp"%>

<script>
document.write("<scr"+"ipt type='text/javascript' src='<%=path%>/view/tsc316n/opertion.js?a="+Math.random()+"'></scr"+"ipt>");
</script>
<style type="text/css">
<!--
body,p,form,table,td,img,div{margin:0;padding:0;border:0;font-family:"宋体";}

body,td,p,li,select,input,textarea,div{font-size:12px;}
-->
</style>


<SCRIPT LANGUAGE="JavaScript" src="./opertion.js?a="+Math.random()> </SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
//<!--

/* 提示错误信息 */
function ShowErr(nErr, sCmd)
{
	if(nErr)
	{
		alert(sCmd + "失败：" + nErr);
	}
	else
	{
		alert(sCmd + "成功！");
	}
}

function GetFingerprintString () {

	if (typeof(ObjFinger) == "undefined") {
		alert("控件未找到，请检查HTMl代码里的OBJECT");
		return -1;
	}

	// 0x30 拆分格式
	ObjFinger.nSucStrFmtTyp = 1;
	// 商行协议
	ObjFinger.nComProtocol = 2;
	// USB协议
	ObjFinger.nUsbProtocol = 0;
	// 是否检测抬起
	ObjFinger.nRegChkToLv = 1;
	// 是否显示 注册指纹模板时的对话框
	ObjFinger.nRegShowDlg = 1;
	// 是否显示 验证指纹特征时的对话框
	ObjFinger.nVerShowDlg = 1;
	// 是否禁用自动提速  0-启用自动提速， 4= 固定9600波特率
	ObjFinger.nNotSpeedUp = 4;
	ObjFinger.nComShwOnOcx = 10;
	// 注册模板，Base64
	nRet = ObjFinger.FPIGetTemplate(0, 18000);
	if (nRet) {
		alert("注册指纹模板失败。错误码:[" + nRet + "]");
		return -1;
	} else {
		return  ObjFinger.FPIGetFingerInfo();
	}
}


/* 命令执行 */
function DoTesoCmd(nCmd)
{

	if(typeof(FpDemo) =="undefined")
	{
		alert("表单域未定义！");
		return;
	}
	
	/*获取界面上的设置属性*/
	var nPortNo = FpDemo.Comm.value;		// 端口SLCT
	var dwWaitTime = FpDemo.TimeOut.value;	// 超时EDIT
	var nLevel = FpDemo.Level.value;		// 安全级SLCT
		
	var nRet = -1; var sDvSn = "";
	var objReg = FpDemo.Reg;				// 模板EDIT
	var objVer = FpDemo.Ver;				// 特征EDIT
	if (typeof(ObjFinger) == "undefined") {
		alert("控件未找到，请检查HTMl代码里的OBJECT");
		return -1;
	}

	/*------设备驱动配置信息，除非您清楚，不建议随意改动------*/	
	// 0x30 拆分格式
	ObjFinger.nSucStrFmtTyp = 0;
	// 商行协议
	ObjFinger.nComProtocol = 2;
	// USB协议
	ObjFinger.nUsbProtocol = 0;
	// 是否检测抬起
	ObjFinger.nRegChkToLv = 1;
	// 是否显示 注册指纹模板时的对话框
	ObjFinger.nRegShowDlg = 1;
	// 是否显示 验证指纹特征时的对话框
	ObjFinger.nVerShowDlg = 1;
	// 是否禁用自动提速  0-启用自动提速， 4= 固定9600波特率
	ObjFinger.nNotSpeedUp = 4;
	ObjFinger.nComShwOnOcx = 10;


	/*------调用具体的各种方法------*/

	// 通用方法
	switch(nCmd)
	{
	case 4:
		nRet = ObjFinger.StartWatching(nPortNo, dwWaitTime);
			if (nRet) {
				objReg.value = nRet;
				alert("注册指纹模板失败。错误码:[" + nRet + "]");
			} else {
				objReg.value = nRet;
			}
		break;
	case 5:
		nRet = ObjFinger.StopWatching(nPortNo);
			if (nRet) {
				objReg.value = nRet;
				alert("stop监听:[" + nRet + "]");
			} else {
				objReg.value = nRet;
			}
		break;
		
	case 0: // 注册模板，Base64
			nRet = ObjFinger.FPIGetTemplate(nPortNo, dwWaitTime);
			if (nRet) {
				objReg.value = nRet;
				alert("注册指纹模板失败。错误码:[" + nRet + "]");
			} else {
				objReg.value = ObjFinger.FPIGetFingerInfo();
			}		
		break;
		
	case 1: // 验证特征，Base64		
		nRet = ObjFinger.FPIGetFeature(nPortNo, dwWaitTime);
		if (nRet) {
			objVer.value = nRet;
			alert("验证指纹特征失败。错误码:[" + nRet + "]");
		} else {
			objVer.value = ObjFinger.FPIGetFingerInfo();
		}
		break;
		
	
	case 2: // 比对，Base64
		nRet = ObjFinger.FPIFpMatch(objReg.value, objVer.value, nLevel);
		ShowErr(nRet, "比对");
		break;
	
	case 3:
		//取出刚才注册指纹的3枚图像
		//FPIGetImageData(nImageIndex) 
		//nImageIndex：图像索引1~3
		//返回值：base64编码之后的图像

		//SaveBmpB64(chSaveFile, chImageData, nDecodeType)
		//chSaveFile 保存路径
		//chImageData 图像内容，等于FPIGetImageData的返回值
		//nDecodeType 编码格式 传入 2 即可 

		sImgVal1 = ObjFinger.FPIGetImageData(1);
		ObjFinger.SaveBmpB64('c:\\Teso01.bmp', sImgVal1, 2);
		sImgVal2 = ObjFinger.FPIGetImageData(2);
		ObjFinger.SaveBmpB64('c:\\Teso02.bmp', sImgVal2, 2);
		sImgVal3 = ObjFinger.FPIGetImageData(3);
		ObjFinger.SaveBmpB64('c:\\Teso03.bmp', sImgVal3, 2);

		break;

	case 20: // 发现设备
		/* function TcWhereAreu(nBgn:I4; nEnd:I4; nRidx:I4; nMask:I4; chFix:BSTR): I4; */
		/* nBgn和nEnd	要搜索的目标端口区间[]，0为USB，>=1为COM		*/
		/* nRidx		串口波特率索引号[0, 7]，对应为[1200, 115200]	*/
		/* nMask		附加功能的掩码：Bit0为在搜索COM前，是否先搜USB	*/
		/*				Bit1-7为x0.1s超时(0默认0.6s)，Bit8-15为分隔符	*/
		/* chFix		BP盒的前后缀字串儿，可为NULL，默认0用'|'分隔	*/
		/* 返回值		<0失败，>=0发现有设备连接：0为USB，>=1为COM		*/
		nRet = ObjFinger.TcWhereAreu(0, 16, 3, 0 , "");
		if (nRet>=0) {
			FpDemo.Comm.value = nRet;
			alert("发现设备：" + nRet);
		} else {
			alert("没有发现设备");
		}
		break;

	case 10:// 验证控件是否符合规范
		var v1 = typeof (ObjFinger.FPIFpMatch);
		var v2 = typeof (ObjFinger.FPIGetFeature);
		var v3 = typeof (ObjFinger.FPIGetTemplate);
		var eMsg="";
		if ( v1== "undefined" ) eMsg += "没有 FPIFpMatch 方法\n"; 
		if ( v2== "undefined" ) eMsg += "没有 FPIGetFeature 方法\n"; 
		if ( v3== "undefined" ) eMsg += "没有 FPIGetTemplate 方法\n"; 
		if (eMsg != "") {
		    alert( " 错误：\n\n");
		} else {
			alert("标准调用方法都存在！请分别测试 注册、验证、比对");
		}

		var v4 = ObjFinger.FPIGetDeviceSN(0, 18000);
		alert("Len=" + v4.length + ", SN=" + v4);

		break;

	default:
		alert("错误的命令");
	}
}

//-->
</SCRIPT>

<SCRIPT  for="ObjFinger"  LANGUAGE="JavaScript" event="TheEndOfWatching(nRet,chMut);">
<!--
	
	var bStop = false;
	if (FpDemo.stopListen.checked == true)  bStop = true;

	//如果成功采集到了一枚指纹，则nRet>0 
	if (nRet>0) {
		FpDemo.Reg.value = chMut;
		//ajax 递交
		//递交完成之后，需要继续调用 StartWatching(0, 30000);
		//ajax 向后台递交请求，在后台比对指纹，并返回结果
		//for 
		//处理成功后，继续启动监听
		if (bStop == false) {
			ObjFinger.StartWatching(0, 10000);
		}
	} else {
		//没有采集到指纹，如果不是设备连接错，则继续
		FpDemo.Ver.value += nRet;
		if (nRet != -15) {
			if (bStop == false) {
				ObjFinger.StartWatching(0, 10000);
			}
		}
	}
//-->
</SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
<!--
	function startFPI () {
		// 0x30 拆分格式
		ObjFinger.nSucStrFmtTyp = 1;
		// 商行协议
		ObjFinger.nComProtocol = 2;
		// USB协议
		ObjFinger.nUsbProtocol = 0;
		// 是否检测抬起
		ObjFinger.nRegChkToLv = 1;
		// 是否显示 注册指纹模板时的对话框
		ObjFinger.nRegShowDlg = 1;
		// 是否显示 验证指纹特征时的对话框
		ObjFinger.nVerShowDlg = 1;

		ObjFinger.StartWatching(0, 10000);
	}

//-->
</SCRIPT>
<!-- 
<BODY bgcolor="#f7f7f7" leftmargin="0" topmargin="100" marginwidth="0" marginheight="0" onload="javascript:startFPI()">
-->

<BODY bgcolor="#f7f7f7" leftmargin="0" topmargin="100" marginwidth="0" marginheight="0" >
<br>
<TABLE align=center>
<TR>
<TD>

	<!-- TESO -->
	<OBJECT CLASSID="CLSID:94793CDE-C768-449B-BE87-40147B56032D" codebase="libFPDev_TESO.ocx" id="ObjFinger" width=152 height=200 border=1>
	</OBJECT>
	
	

	<br><br>
	
	<FORM Name="FpDemo" METHOD=POST ACTION="" OnSubmit="javascript:return false;">
		<INPUT TYPE="button" value="控件验证" onclick="javascript:DoTesoCmd(10);"> &nbsp;&nbsp;
		<INPUT TYPE="button" value="注册模板" onclick="javascript:DoTesoCmd(0);"> &nbsp;&nbsp;
		<INPUT TYPE="button" value="验证特征" onclick="javascript:DoTesoCmd(1);"> &nbsp;&nbsp;
		<INPUT TYPE="button" value="特征比对" onclick="javascript:DoTesoCmd(2);"> &nbsp;&nbsp;
		<INPUT TYPE="button" value="保存图像" onclick="javascript:DoTesoCmd(3);"> &nbsp;&nbsp;
		<INPUT TYPE="button" value="发现设备" onclick="javascript:DoTesoCmd(20);"> &nbsp;&nbsp;

		<INPUT TYPE="button" value="启动监听" onclick="javascript:DoTesoCmd(4);"> &nbsp;&nbsp;

		<INPUT TYPE="button" value="停止监听" onclick="javascript:DoTesoCmd(5);"> &nbsp;&nbsp;

		<br><br>端口：<SELECT NAME="Comm" Width=60>
			<OPTION VALUE="0" SELECTED>USB</OPTION>
			<OPTION VALUE="1" >Com1</OPTION>
			<OPTION VALUE="2">Com2</OPTION>
			<OPTION VALUE="3">Com3</OPTION>
			<OPTION VALUE="4">Com4</OPTION>
			<OPTION VALUE="5">Com5</OPTION>
			<OPTION VALUE="6">Com6</OPTION>
			<OPTION VALUE="7">Com7</OPTION>
			<OPTION VALUE="8">Com8</OPTION>
			<OPTION VALUE="9">Com9</OPTION>
			<OPTION VALUE="10">Com10</OPTION>
			<OPTION VALUE="11">Com11</OPTION>
			<OPTION VALUE="12">Com12</OPTION>
			<OPTION VALUE="13">Com13</OPTION>
			<OPTION VALUE="14">Com14</OPTION>
			<OPTION VALUE="15">Com15</OPTION>
			<OPTION VALUE="16">Com16</OPTION>
		</SELECT>
		<INPUT TYPE="checkbox" NAME="stopListen" >停止监听
		
		<br>级别：<SELECT NAME="Level" Width=160>
			<OPTION VALUE="1">1－低</OPTION>
			<OPTION VALUE="2">2－稍低</OPTION>
			<OPTION VALUE="3" SELECTED>3－中</OPTION>
			<OPTION VALUE="4">4－稍高</OPTION>
			<OPTION VALUE="5">5－高</OPTION>
		</SELECT>
		
		&nbsp;&nbsp;&nbsp;&nbsp;超时：<INPUT TYPE="text" NAME="TimeOut" Value="18000" COLS=3> 毫秒<br>
		
		<br>注册指纹模板：<br>
		<TEXTAREA NAME="Reg" ROWS="5"  COLS="60"> </TEXTAREA>
		<br>验证指纹特征：<br>
		<TEXTAREA NAME="Ver" ROWS="5"  COLS="60"> </TEXTAREA>
	</FORM>
</TD>
</TR>
</TABLE>
<center>
	<p>北京天诚盛业科技有限公司</p>
	<hr/>
	<INPUT TYPE="button" value="采集" id="regId"/> &nbsp;&nbsp;
	<INPUT TYPE="button" value="验证" id="validId"/> &nbsp;&nbsp;
</center>

</BODY>
</HTML>
