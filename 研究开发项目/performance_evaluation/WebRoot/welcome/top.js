var $ = function(id) {return document.getElementById(id);};
var differentMillisec = 0;

function init() {
	getServerDate();
	//ȡ����ʾʱ��
	showtime();
	//ȡ����Ϣ���ʼ����������˵�����
	getEventCount();
	openNewUrl();
}

//ȡ����ʾʱ��
function showtime(){
	now = new Date();
	now.setTime(differentMillisec + now.getTime());
	var year = now.getYear();
	if(year < 1900) year += 1900;
	var str = year + "��";
	var temp = now.getMonth() + 1;
	if (temp < 10) str += "0";
	str += temp +  "��";
	temp = now.getDate();
	if (temp < 10) str += "0";
	str += temp +  "��";
	var today = new Array("������", "����һ", "���ڶ�", "������", "������", "������", "������");
	str += " " + today[now.getDay()] + " ";
	temp = now.getHours();
	if (temp < 10) str += "0";
	str += temp +  ":";
	temp = now.getMinutes();
	if (temp < 10) str += "0";
	str += temp +  ":";
	temp = now.getSeconds();
	if (temp < 10) str += "0";
	str += temp;
	document.getElementById("Head1Right_Time").innerHTML = str;
	ctroltime=setTimeout("showtime()", 1000);
}

//�ӷ�����ȡʱ��
function getServerDate() {
	begin = new Date();
	millisecbeg = begin.getTime();
	try {
		var buffalo = new Buffalo(endPointTop);
	        buffalo.remoteCall("desktopService.getServerDate", [], function(reply) {
	            var serverMillisec = reply.getResult();
				end = new Date();
				millisecend = end.getTime();
				differentMillisec = serverMillisec - new Date() + (millisecend - millisecbeg)/2;
	        });
	} catch (ex) {
	}	
}

//ȡ����Ϣ���ʼ����������������
function getEventCount() {
	try {
		var buffalo = new Buffalo(endPointTop);
	        buffalo.remoteCall("desktopService.getInfoCount", [userId, deptId], function(reply) {
	            var str = reply.getResult();
	            var ary = str.split("|");
				document.getElementById("msg").innerHTML = "(" + ary[0] + ")";
				document.getElementById("mail").innerHTML = "(" + ary[1] + ")";
				document.getElementById("wait").innerHTML = "(" + ary[2] + ")";
	        });
	} catch (ex) {
	}
	setTimeout("getEventCount()", 10 * 60 * 1000);
}

//ȡ���Ƿ�������Ϣ
function getNewMsg() {
	try {
		var buffalo = new Buffalo(endPointTop);
	        buffalo.remoteCall("desktopService.getNewMsgAry", [userId, awoke], function(reply) {
	            if (reply.getResult() > 0) {
				    var varOption = "toolbar=no,location=no,status=yes,menubar=no,resizable=yes,scrollbars=yes,width=800, height=600,left=0,top=0";
				    window.open(document.all.msgLink.href, 'autoOpenWin', varOption);
	            }
	        });
	} catch (ex) {
	}
    setTimeout("getNewMsg()", refreshSecond * 1000);
}

function openNewUrl() {
	if (document.homeForm.newUrl.value != "") {
		location.replace(document.homeForm.newUrl.value);
	}
}

function submitList() {
	if (document.all.topForm.funcList == null) return;
	var n = document.all.topForm.funcList.selectedIndex;
	if (n == -1 || n == 0) return;
	var urlname = document.all.topForm.funcList.options[n].text;
	var urlvalue = document.all.topForm.funcList.options[n].value;
	top.desktop.menuForm.url.value = urlvalue ;
	document.all.topForm.url.value = urlvalue;
	document.all.topForm.urlName.value = urlname;
	top.desktop.menuForm.submit();
	document.all.topForm.submit();
}

