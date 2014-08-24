/* 提示错误信息 */
function ShowErr(nErr, sCmd) {
	if (nErr) {
		alert(sCmd + "失败：" + nErr);
	} else {
		alert(sCmd + "成功！");
	}
}

function GetFingerprintString() {

	if (typeof (ObjFinger) == "undefined") {
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
	// 是否禁用自动提速 0-启用自动提速， 4= 固定9600波特率
	ObjFinger.nNotSpeedUp = 4;
	ObjFinger.nComShwOnOcx = 10;
	// 注册模板，Base64
	nRet = ObjFinger.FPIGetTemplate(0, 18000);
	if (nRet) {
		alert("注册指纹模板失败。错误码:[" + nRet + "]");
		return -1;
	} else {
		return ObjFinger.FPIGetFingerInfo();
	}
}

function init() {

}


$(document).ready(function(){
	$('#regId').click(function(event){
		regFinger();
	});
});	
/**
 * 采集指纹到数据库
 */
function regFinger() {


/*
	if (typeof (FpDemo) == "undefined") {
		alert("表单域未定义！");
	}

	 获取界面上的设置属性 
	var nPortNo = FpDemo.Comm.value; // 端口SLCT
	var dwWaitTime = FpDemo.TimeOut.value; // 超时EDIT
	var nLevel = FpDemo.Level.value; // 安全级SLCT

	var nRet = -1;
	var sDvSn = "";
	var objReg = FpDemo.Reg; // 模板EDIT
	var objVer = FpDemo.Ver; // 特征EDIT
	if (typeof (ObjFinger) == "undefined") {
		alert("控件未找到，请检查HTMl代码里的OBJECT");
		return -1;
	}

	------设备驱动配置信息，除非您清楚，不建议随意改动------
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
	// 是否禁用自动提速 0-启用自动提速， 4= 固定9600波特率
	ObjFinger.nNotSpeedUp = 4;
	ObjFinger.nComShwOnOcx = 10;

	nRet = ObjFinger.FPIGetTemplate(nPortNo, dwWaitTime);
	if (nRet) {
		objReg.value = nRet;
		alert("注册指纹模板失败。错误码:[" + nRet + "]");
	} else {
		objReg.value = ObjFinger.FPIGetFingerInfo();

		// 采集成功后将指纹数据写入数据库
		$.ajax({
			type: "post",
            url: "",
            data: {fingervalue: objReg.value},
            dataType: "json",
            success: function(data){
                alert(data);
             }
		});
		  
	}
*/
	$.ajax({
		type: "post",
        url: "regfinger.do",
        data: {fingervalue: 'asd'},
        dataType: "json",
        success: function(data){
            alert(data);
         }
	});
	
}


/**
 * 验证指纹信息
 */
function matchFinger() {

}
