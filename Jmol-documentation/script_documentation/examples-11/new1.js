//examples.js by Bob Hanson hansonr@stolaf.edu 6:17 AM 6/14/2004
isxhtmltest=0/1
isinitialized=0
MAXMSG=100000
msglog=""
height=350
width=350

//height=width=1600
eval("a=function(j,c,z) {alert(j)}")

loadstructcallback="loadStructCallback"
animcallback="animFrameCallback"
pickcallback="showpick"
hovercallback="showmsg"
msgcallback="showmsg"
//msgcallback="ignoreit"
errorcallback = "errorCallback"
measurecallback = "measureCallback"

function measureCallback(app, strMeasure,intInfo, strStatus) {
showmsg(app, strStatus + ":" + strMeasure, "", 0)
}

function loadStructCallback(fullPathName, fileName, modelName, errorMsg, ptLoad) {
 alert([fullPathName, fileName, modelName, errorMsg, ptLoad])
}

function errorCallback(app,errorType,errorMessage, errorMessageUntranslated, errorObject) {
 alert("Applet " + app + " reports an error of type " + errorType + ":\n\n" + errorMessageUntranslated + "\n\nobject:\n\n" + errorObject)
}

datadir = "data"

logLevel = 4

function resize(n)
{
 document.getElementById("jmolApplet0").width = n
 document.getElementById("jmolApplet0").height = n
}

function resizeCSS(n)
{
 document.getElementById("jmolApplet0").style.width = n+"px"
 document.getElementById("jmolApplet0").style.height = n+"px"
}

function newAppletWindow() {
 var sm=""+Math.random()
 sm=sm.substring(2,10)
 var newwin=open("JmolPopup.htm","jmol_"+sm,woptions)
}


/// this next code is all you need in 11.1.17 to open a new resizable applet window

woptions="menubar=yes,resizable=1,scrollbars,alwaysRaised,width=600,height=600,left=50"

function dowritenew(s){
 var sm=""+Math.random()
 sm=sm.substring(2,10)
 var newwin=open("","jmol_"+sm,woptions)
 newwin.document.write(s)
 newwin.document.close()
}

function jmolGetLogLevelRadios() {
  var S = [["set logLevel 0","0",0,"radio0","Java console silent"]
	,["set logLevel 1","1",0,"radio1","only fatal errors to the Java Console"]
	,["set logLevel 2","2",0,"radio2","all errors to Java Console"]
	,["set logLevel 3","3",0,"radio3","errors and warnings to Java Console"]
	,["set debugscript off;set logLevel 4","4",1,"radio4","information, warnings, and errors to Java Console"]
	,["set debugscript on ;set logLevel 5","5",0,"radio5","full debug mode"]
	]
  var s = jmolRadioGroup(S)
  return s
}

////////////////////////////////////////////////////////


addRCSBlink=true

force_useHtml4Object=0
force_useIEObject=0
defaultloadscript=""

TitleInfo=new Array()

function setLogLevel(n){
 setTimeout("jmolScript('set logLevel " + n+"')",100)
}

function fixlinks(){
 if(!document.getElementsByTagName) return;
 var anchors = document.getElementsByTagName("a")
 for(var i=0; i<anchors.length; i++){
	var anchor = anchors[i]
	var h=anchor.getAttribute("href")
	if(h && anchor.getAttribute("rel"))anchor.target = anchor.getAttribute("rel")
	if(h && h.indexOf("personal_disclaimer")>=0)anchor.innerHTML=""
 }
}

function idof(s){
	s=s.replace(/\(\#\)/,"")
	return unescape(s).replace(/(\.|\(|\))/g,"").replace(/\s+/g,"")
}


usejmoljs=1

//nstart=(location.search+"?").split("?")[1]
td2width=250
ncolsfortextarea=48
ntd=2
thecaption=""
showappcode=false&&true
docbase="./index.htm"
codebase="."
archive="jmolAppletSigned.jar"
thiscommand=""

function ignoreit() {}

echoformat="font echo 14"
echoformat2="font echo 16"
title="example form"
Scripts=new Array("reset")
ref=""
remark=""
FTREF250="<a class=\"ftnote\" href=\"javascript:showref(250)\"><sup>*</sup></a>"

loadscript=";"
docsearch = document.location.search.substring(1)
iscript = (docsearch.indexOf("scriptno=")>=0 ? parseInt(docsearch.split("scriptno=")[1].split("&")[0]) : 0)
listScripts = (docsearch.indexOf("LISTONLY")>=0)
listHeadings = (docsearch.indexOf("HEADINGSONLY")>=0)
useSigned = (docsearch.indexOf("SIGNED")>=0)
language = (docsearch.indexOf("language=")>=0 ? docsearch.split("language=")[1].split("&")[0] : 0)
thistopic = (docsearch.indexOf("topic=")>=0 ? docsearch.split("topic=")[1].split("&")[0] : 0)

function checkScroll() {
 if (thistopic == 0)return
 divScrollTo("aframe","topic" + thistopic)
}

function divScrollTo(fname, name){
	var d=document.getElementById(fname)
	if(!d)return
	var pos=0
	if(name!="top"){
		var d2=document.getElementById(name)
		if(!d2) {
			return
		}
		pos=d2.offsetTop
	}
	d.scrollTop=pos
}

function showref(n){
 if(n==250)alert("Integer distances in Jmol indicate Rasmol units (0.004 Angstrom), now deprecated.")
}


function getapplet(name, model, codebase, height, width, script) {
  if (!isinitialized && useSigned) {
	jmolInitialize(".", useSigned) //signed
	isinitialized = true
  }

jmolInitialize(".","JmolApplet0.jar")

//   jmolSetCallback("menuFile", "data/myfix.mnu")

  if(force_useHtml4Object)_jmol.useHtml4Object=1
  if(force_useIEObject)_jmol.useIEObject=1

  if (!isinitialized) jmolInitialize(".")
  isinitialized = 1
  jmolSetDocument(0)

	       jmolSetAppletColor("#000033")

//  jmolSetTranslation(true)

 // jmolSetLogLevel(logLevel);

  var s = "set defaultDirectory \""+datadir+"\";" 
  if (model)s += (model.indexOf("load") == 0 ? "" : "load ") + model + ";"
  script = s + script;
//script = "load " + model;
  script = script.replace(/load \;/,";")
  if (defaultloadscript != "")script = "set defaultLoadScript \""+defaultloadscript+"\";"+script
  jmolSetCallback("ScriptCallback","scriptCallback")
  if (msgcallback)jmolSetCallback("MessageCallback",msgcallback)
  if (measurecallback)jmolSetCallback("MeasureCallback",measurecallback)
  if (errorcallback)jmolSetCallback("ErrorCallback",errorcallback)
  if (animcallback)jmolSetCallback("AnimFrameCallback",animcallback)
  if (pickcallback)jmolSetCallback("PickCallback",pickcallback)
  if (hovercallback)jmolSetCallback("HoverCallback",hovercallback)
  if (loadstructcallback)jmolSetCallback("LoadStructCallback",loadstructcallback)

//jmolSetCallback("loglevel", "6")

  //jmolSetCallback("debug", true)
  if (language)jmolSetCallback("language",language)

  var s = jmolApplet([width,height], script, "myname")
// alert("not allowing scripting");s=s.replace(/mayscript/,"maynotscript")
 alert(s)
  return s
}

function getinfo(){
 var script=getscriptlink(0,false)
 var s="Click on a link below to see what it does. You can also type a command in the box below the model to see its effect."

theref = (model.length==8 && model.indexOf(".pdb")==4?"<a target=_blank href=http://www.rcsb.org/pdb/files/"+model+">["+model.substring(0,4)+"]</a>":
model.indexOf('"')<0?"<a target=_blank href="+model.split(";")[0]+">"+model+"</a>":model)

 if(model)s+=" The script run in this case was <b>"+(model.indexOf(";") == 0 ? "" : "load ")+theref+"</b>."
 if(defaultloadscript != "")s+=" The default load script used here is \""+defaultloadscript+"\"."
 return "<p>"+s+"</p><table><tr height=1000><td></td></tr></table>"
}

function getremark(){
 return (remark?"<blockquote><p>"+remark+"</p></blockquote>":"")
}

function getscriptlink(i,isul,addNumber){
 if(!Scripts[i])return ""
 var S=Scripts[i].split(" ~~ ")
 var s=(S.length>1?(isul && false?"</ul>":"")+"<table><tbody><tr>":"")
 //2> means colspan=2
 for(var j=0;j<S.length;j++){
	if(S.length>1)s+="\n<td"+(td2width && S.length<=ntd?" width='"+td2width+"'":"")+" valign='top'"+(isNaN(parseInt(S[j]))?">":" colspan='"+parseInt(S[j])+"'")+(isul?"<ul>":"")
	if(S[j].indexOf("###")>=0){
		nTopics--;
		S[j]=S[j].replace(/\#\#\#/,"<h3><span>" + (nTopics <= nFirst ? "<a name=\"topic"+nTopics+"\" id=\"topic"+nTopics+"\">"+ nTopics + ".</a> " : "")).replace(/\#\#\#/,"</span></h3>")
		if(S[j].indexOf("<br>")>=0)
			S[j]="<table width=450><tr><td>"+S[j].split("<br>")[0]+"</td><td width=210 align=right>"+S[j].split("<br>")[1]+"</td></tr></table>"
		s+="<br>"
	} else if(S[j].length == 1){
		s+="<br>"
	} else {
		if (S[j].indexOf("#")==0)isul=false
		s+=(isul?"\n<li>":"")
		if (addNumber)s+=i+":"
	}
	if(S[j].indexOf("#")==0)
		S[j]= "<span><i>"+(S[j].length==1?"&nbsp;":S[j])+"</i></span>"
        var st = S[j]
	var isLoad = (st.indexOf("load ")>=0 || st.indexOf("zap") >= 0)
	st=(st.indexOf("<span")>=0||st.indexOf("<a href")>=0?st:"<a href=\"javascript:showscript("+i+","+j+")\">"
		+(isLoad?"<font color=red>":"")
		+st.replace(/\</g,isxhtmltest?"&amp;lt;":"&lt;")
		+(isLoad?"</font>":"")
		+"</a>")
        if (S[j].indexOf("#")>0)
		st = st.substring(0, st.indexOf("#")) + "<font color=black>" + st.substring(st.indexOf("#") + (st.indexOf("#") == 9 ? 1 : 0)) + "</font>"
	s+=st + (isul?"</li>":"")
	if (S[j].indexOf("<span")<0 && S[j].indexOf("<a href")<0 
		&& S[j].indexOf("quit")<0 && S[j].indexOf("loop")<0 
		&& S[j].indexOf("exit")<0 && S[j].indexOf("pause")<0
		&& S[j].indexOf("exit")<0 && S[j].indexOf("resume")<0
	        )scriptList+=S[j]+"\n"
	if(S.length>1)s+=(isul?"\n</ul>":"")+"\n</td>"
 }
 if(S.length>1)s+="\n</tr></tbody></table>"+(isul && false?"\n<ul>":"")
 //if (s.indexOf("id=")>=0)alert(s)
 return s
}

nTopics=0;
nFirst=0;
nSkip = 2;
scriptList = ""
headingList =""
TopicScripts = {}

function getscripts(addNumber){
 nTopics = 1;
 for (var i=1;i<Scripts.length;i++){
	if(Scripts[i].indexOf("###")>=0){
  	  nTopics++;
          iTopic = -nTopics
	  if (listHeadings)headingList += Scripts[i].replace(/\#\#\#/g,"")+"\n"
        }
 }
 nFirst=nTopics-nSkip;
 
 var s=""
 var isul=true
 var iCount = 10;
 for (var i=1;i<Scripts.length;i++){
	var sc = Scripts[i]
	if(sc.charAt(0)==" "){
		s+="</ul><p>"+sc+"</p><ul>"
	}else if(sc.charAt(0)=="*"){
		if(sc=="*NOUL")isul=false
		if(sc=="*UL")isul=true
	}else{
		if(sc.indexOf("###")>=0)iCount = -1
	  	if (++iCount == 2) {
			if (sc.indexOf("load") == 0)
				TopicScripts["" + (nTopics)] = sc
		}
		s+=getscriptlink(i,isul,addNumber)
	}
 }
 s=s.replace(/\<\/tbody\>\<\/table\>\<\/ul\>\<ul\>\<table\>\<tbody\>/g,"")
 if(s)s+="</ul>"
 return s.substring(5,s.length)//.replace(/\</g,"&lt;")
}

function gettitleinfo(){
 var s=""
 var l=window.location+""
 for(var t in TitleInfo){
	s+="\n<option "+(l.indexOf(t+".htm")>=0?"selected=\"selected\"":"")+" value=\""+t+"\">"+TitleInfo[t]+"</option>"
 }
 s="<select onchange=\"location=this.value+'.htm'\">"+s+"</select>"
 return s
}

function showfunction(i){
 if(!i)return
 var d = document.getElementById("msg");
 if (!d)return
 d.value=window[i].toString()
}

function getfunctions(){
 return ""

 if((navigator.appName+navigator.appVersion).indexOf("afari")>=0)return ""
 var S=new Array()
 for(var i in window){
	if(typeof(window[i])=="function" && window[i].toString().indexOf("native")<0)S[S.length]="<option value=\""+i+"\">"+i+"()</option>"
 }
 if(!S)return ""
 return "functions on this page:<select name=\"myfunctions\" onchange=\"showfunction(this.value)\"><option value=\"0\"></option>"+S.sort().join("")+"</select>"
}

function gettitle(){
 return ""
 return s
}

function showcmd(){
 var d = document.getElementById("msg");
 if (!d)return
 if(d.value.length>MAXMSG)document.getElementById("msg").value=document.getElementById("msg").value.substring(0,MAXMSG/2)
 showscript(-1)
}

function showOrientation(){
 thiscommand="show orientation"
 jmolScriptWait(thiscommand)
}

function showjsoninfo(){
 var  what= _jmolEnumerateObject(_jmolEvalJSON(WHAT),"")
 showmsg("",what,"")
}

function animFrameCallback(app, frameNo, fileNo, modelNo, firstNo, lastNo, 
isAnimationRunning, animationDirection, currentDirection) {
 showmsg("","animation running, range = " + isAnimationRunning + " " + firstNo + " " + lastNo
	+"\ncurrent direction = " + currentDirection
	+"\ncurrent frame,file,model = " + frameNo + " " + fileNo + " " + modelNo
	)

}

function scriptCallback(app, status, message, millisec, errorUntranslated) {
  // this filters out the "script completed" messages and only passes the real messages along
  millisec = parseInt("" + millisec)
  errorUntranslated = "" + errorUntranslated
  if (errorUntranslated != "null" && errorUntranslated != "undefined" /*safari*/)
	alert(errorUntranslated + "\n\n" + message)
  if (millisec == 0) showmsg(app, status, message, 0)
}

ttest2=""
function showmsg(n,objwhat,moreinfo,moreinfo2){
ttest2 += "<br>showmsg:" + objwhat
//alert("showmsg" + objwhat)
 var d = document.getElementById("msg");
 if (!d)return
 var nmore = parseInt(moreinfo);
 var what="" + objwhat//+(moreinfo?"\n"+moreinfo:"")+(moreinfo2?"\n"+moreinfo2:"")
 if (what.indexOf("{") == 0) {
   WHAT = what.replace(/\\\"/g,"~")
   //need a new thread here in case of an error
   setTimeout("showjsoninfo()",100)
 }
 msglog+="\n"+what
 var s=d.value
 if(s.length>MAXMSG) s=s.substring(0,MAXMSG/2)
 d.value=what
}

function showpick(n,objwhat,moreinfo){
 showmsg(n,objwhat,moreinfo)
 return
 var nmore = parseInt(moreinfo);
 if (nmore == 1)moreinfo="";
 var what=objwhat+(moreinfo?"\n"+moreinfo:"")
 alert(what);
}

function showfile(s){
 if(s.length<100)return
 thiscommand=""
 var s="<pre>"+s.replace(/\</g,"&lt;")+"</pre>"
 dowritenew(s)
}

function showmsgbox(){
 var s="<pre>"+msglog.replace(/\</g,"&lt;")+"</pre>"
 dowritenew(s)
}

function showoutput(s){ 
 if(!s) s=document.getElementById("output").value
 dowritenew("<pre>"+s.replace(/\</g,"&lt;")+"</pre>") 
}

function showthefile(){
 thiscommand="show file"
 jmolScript(thiscommand)
}

function showpage(){
 var s=getpage()
 document.write(s)
}


function getRCSBfile(){
  _jmol.RCSBserver = serverURL = "http://pdb202.sdsc.edu" //temporarily only 4/17/06
 jmolLoadAjax_STOLAF_RCSB("CIF",0,defaultloadscript)
}

function getMSAfile(){
 jmolLoadAjax_MSA(0,0,'load "" {1 1 1};'+defaultloadscript)
}

function getANYfile(){
 jmolLoadAjax_STOLAF_ANY(0,0,defaultloadscript)
}


function winHeight(){
  var myWidth = 0, myHeight = 500;
  if( typeof( window.innerWidth ) == 'number' ) {
    //Non-IE
    myWidth = window.innerWidth;
    myHeight = window.innerHeight;
  } else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) {
    //IE 6+ in 'standards compliant mode'
    myWidth = document.documentElement.clientWidth;
    myHeight = document.documentElement.clientHeight;
  } else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) {
    //IE 4 compatible
    myWidth = document.body.clientWidth;
    myHeight = document.body.clientHeight;
  }
  return myHeight
}

function getpage(){
 var s=gettitle()+getremark()+getinfo()

 s+='\n<div id="aframe" style = "position:absolute;top:50px;left:10px;width:530px;overflow:auto;height:'+(winHeight()-75)+'px">'
+'<table id="atable" style="position:absolute;top:0px;left:10px;"><tbody><tr><td>'
+getscripts()

 if(thistopic && TopicScripts[thistopic])
   model = TopicScripts[thistopic]

 s+='\n</td></tr></tbody></table></div>'
+'\n<div id="bframe" style = "position:absolute;top:50px;left:550px;height:500px;width:450px;overflow:auto;height:'+(winHeight()-75)+'px">'
	+'\n<span id="jmolApplet">'
	+getapplet("jmol",model,codebase,height,width,(iscript>0?Scripts[iscript]:loadscript+";"+Scripts[0]))
	+'\n</span>'
	+'\n<p><a target=_blank href=http://en.wikipedia.org/wiki/Ajax_%28programming%29>AJAX</a>: '
	+'<a href=javascript:getRCSBfile() title="any CIF from RCSB via St. Olaf AJAX server"><u>STOLAF-RCSB/CIF</u></a> &nbsp;&nbsp;&nbsp; '
	+'<a href=javascript:getMSAfile() title="a mineral from the Mineralogical Society of America via MSA AJAX server"><u>MSA/CIF</u></a> &nbsp;&nbsp;&nbsp; '
	+'<a href=javascript:getANYfile() title="any URL on the WEB via St. Olaf AJAX server"><u>STOLAF-ANY</u></a> &nbsp;&nbsp;&nbsp; '
	+'<br />show <a href=javascript:showOrientation()>orientation</a> '
	+'<a href="javascript:void(open(\'getimage.htm\',\'_blank\'))">image</a> '
	+'<a href="javascript:void(open(\'getstereo.htm\',\'_blank\'))">stereo</a> '
	+'(images not available with MSIE)<br />'
	+'<a href="javascript:jmolScript(\'save state\')">save</a>/<a href="javascript:jmolScript(\'console;show state\')">show</a>/<a href="javascript:jmolScript(\'restore state\')">restore</a> state'
	+' <a href="javascript:newAppletWindow()">new resizable Window</a>'
	+' antialias <a href="javascript:jmolScript(\'antialiasDisplay = true\')">ON</a> '
	+'<a href="javascript:jmolScript(\'antialiasDisplay = false\')">OFF</a> '
	+'\n<form action="javascript:showcmd()">'
	+'\n<br /><a href="javascript:jmolScript(\'console\')" title="Jmol popup console">cmd</a>: <input autocomplete="off" id="cmd" type="text" size="50" value="" />'
	+'\n<a href="javascript:showmsgbox()">popup</a>'
 	+'\n<br /><textarea autocomplete="off" id="msg" cols="'+ncolsfortextarea+'" rows="6" wrap="off">'
	+'\n</textarea>'
	+'\n<center>Java Console Log level: '+jmolGetLogLevelRadios()+'</center>'
	+'\n<br />'+getfunctions()
	+'\n</p></form>'
	+'\n</div>'
 if(isxhtmltest)s=s.replace(/\</g,"<br />&lt;")
 if(listScripts)s="<pre>#"+document.location+"\n\n"+scriptList+"</pre>"
 if(listHeadings)s="<pre>#"+document.location+"\n\n"+headingList+"</pre>"
 return s
}


function usercallback(s){}

function showscript(i,j,script){
	if(!j)j=0
	var s=(script?script:i>=0?Scripts[i].split(" ~~ ")[j]:document.getElementById("cmd").value)
	if(s.indexOf("javascript:") == 0) {
		alert(eval(s.substring(11)))
		return
	}
	showmsg("user",s+"\n")
	thiscommand=s
	usercallback(s)
	S=(s+"#").split(s.indexOf("##") >= 0 ? "##" : "#")
	document.getElementById("cmd").value=s=S[0]
	jmolScript(s)
}


