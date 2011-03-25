
/////////////////////

isprototype = false

usejmoljs=1
jardir="."
//nstart=(location.search+"?").split("?")[1]
td2width=250
ncolsfortextarea=48
ntd=2
thecaption=""
showappcode=false&&true
docbase="./index.htm"
codebase="."
messagecallback="showmsg"
thiscommand=""
animcallback="showmsg"
pickcallback="showmsg"
loadstructcallback="showmsg"
echoformat="font echo 14"
echoformat2="font echo 16"
title="example form"
height=400
width=400
ref=""
remark=""
FTREF250="<a class=\"ftnote\" href=\"javascript:showref(250)\"><sup>*</sup></a>"
woptions="menubar=yes,scrollbars,alwaysRaised,width=700,height=600,left=50"
loadscript=";"

title="testing";model="ZNQUKROD.MOL"
delayms = 200; delayms0 = 1000000
pollingTimer=0


Scripts=new Array(" "," ")
autoload=-1
archive="X"
demo=0
polling=0
scripting=-1
small=0

if(location.search.length>1)eval(location.search.substring(1,1000))
if(demo){
  if(autoload<0)autoload=Demo[demo].autoload
  if(archive=="X")archive=Demo[demo].archive
  Scripts=Demo[demo].Scripts
  if(autoload){
	model=""
	var S=Scripts.join("\n").split("\n")
	S[1]=""
	loadscript=S.join("\n")
  } else {
	loadscript=Scripts[2]
  }
  if(archive!=""){
	polling=1
  }
}else if(scripting){
  Scripts=NewScript
}
if(autoload < 0)autoload=0
if(archive=="X")archive=""

if(polling){
 messagecallback=""
 animcallback=""
 pickcallback=""
 loadstructcallback=""
 delayms0=1000
}

if(small)
 height=width=250



function doPolling(TF){
  //document.title="Polling Jmol applet:"+TF
  if(!polling) return
  if(!TF) {
	if(pollingTimer == 0) return
	clearTimeout(pollingTimer)
  }
  pollingTimer=setTimeout('dotest(11)',(TF==-1?delayms0:delayms))
}


//based on new.htm



remarks=""

what="visible"
whatp="centerInfo"
whatm=""

function showoutput(s){ if(!s) s=document.getElementById("output").value; dowritenew("<pre>"+s+"</pre>") };function oldshowoutput(){
 dowritenew("<pre>"+document.getElementById("output").value+"</pre>")
}

//based on examples.js by Bob Hanson hansonr@stolaf.edu 6:17 AM 6/14/2004
isxhtmltest=0/1
isinitialized=0
MAXMSG=100000
msglog=""

JMOLDOCS="http://www.stolaf.edu/academics/chemapps/jmol"
force_useHtml4Object=0
force_useIEObject=0

TitleInfo=new Array()

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


function fixabbrevs(s){
 s=s.replace(/JMOLDOCS/g,JMOLDOCS)
 return s
}


function showref(n){
 if(n==250)alert("Integer distances in Jmol indicate Rasmol units (0.004 Angstrom), now deprecated.")
}


function dowritenew(s){
 var sm=""+Math.random()
 sm=sm.substring(2,10)
 var newwin=open("","jmol_"+sm,woptions)
 newwin.document.write(s)
 newwin.document.close()
}

function getapplet(name, model, codebase, height, width, script, msgcallback,animcallback,pickcallback,loadstructcallback) {

  if (!isinitialized)jmolInitialize(jardir,(archive!=""?archive:0))
  if(force_useHtml4Object)_jmol.useHtml4Object=1
  if(force_useIEObject)_jmol.useIEObject=1
  isinitialized = 1
  jmolSetDocument(0)
  if (model)script = "load " + model + ";" + script
  var s = jmolApplet([width,height], script)
  var sext = ""
  if (msgcallback) {
    sext = "\n<param name='MessageCallback' value='" + msgcallback + "' />"
    s = s.replace(/\<param/, sext + "\n<param")
  }
  if (animcallback) {
    sext = "\n<param name='AnimFrameCallback' value='" + animcallback + "' />"
    s = s.replace(/\<param/, sext + "\n<param")
  }
  if (pickcallback) {
    sext = "\n<param name='PickCallback' value='" + pickcallback + "' />"
    s = s.replace(/\<param/, sext + "\n<param")
  }
  if (loadstructcallback) {
    sext = "\n<param name='LoadStructCallback' value='" + loadstructcallback + "' />"
    s = s.replace(/\<param/, sext + "\n<param")
  }
  return s
}

function getinfo(){
 var script=getscriptlink(0,false)
 var s="Click on a link below to see what it does. You can also type a command in the box below the model to see its effect. <a target=_blank href=JMOLDOCS>[Jmol documentation/examples]</a> "
 s=fixabbrevs(s)
// if(model)s+=" The model used in this case is "+(model.length==8 && model.indexOf(".pdb")==4?"<a rel=\"_blank\" href=\"http://pdbbeta.rcsb.org/pdb/explore.do?structureId="+model.substring(0,4)+"\">["+model.substring(0,4)+"]</a>":model)+"."
 return "<p>"+s+"</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>"
}

function getremark(){
 return (remark?"<blockquote><p>"+remark+"</p></blockquote>":"")
}

function getscriptlink(i,isul){
 if(!Scripts[i])return ""
 var S=fixabbrevs(Scripts[i]).split(" ~~ ")
 var s=(S.length>1?(isul?"</ul>":"")+"<table><tbody><tr>":"")
 //2> means colspan=2
 for(var j=0;j<S.length;j++){
	if(S.length>1)s+="\n<td"+(td2width && S.length<=ntd?" width='"+td2width+"'":"")+" valign='top'"+(isNaN(parseInt(S[j]))?">":" colspan='"+parseInt(S[j])+"'")+(isul?"<ul>":"")
	s+=(isul?"\n<li>":"")
	+(S[j].indexOf("<span")>=0||S[j].indexOf("<a ")>=0?S[j]:"<a href=\"javascript:showscript("+i+","+j+")\">"
		+(S[j].indexOf("load")==0?"<font color=red>":"")
		+(S[j].indexOf("###")==0?"<font color=black class=header>":S[j].indexOf("#")==0?"<font color=black>":"")
		+S[j].replace(/\</g,isxhtmltest?"&amp;lt;":"&lt;")
		+(S[j].indexOf("#")==0||S[j].indexOf("load")>=0?"</font>":"")
		+"</a>")
	+(isul?"</li>":"")
	if(S.length>1)s+=(isul?"\n</ul>":"")+"\n</td>"
 }
 if(S.length>1)s+="\n</tr></tbody></table>"+(isul?"\n<ul>":"")
 return s
}

function getscripts(){
 var s=""
 var isul=true
 for (var i=1;i<Scripts.length;i++){
	if(Scripts[i].charAt(0)==" "){
		s+="</ul><p>"+Scripts[i]+"</p><ul>"
	}else if(Scripts[i].charAt(0)=="*"){
		if(Scripts[i]=="*NOUL")isul=false
		if(Scripts[i]=="*UL")isul=true
	}else{
		s+=getscriptlink(i,isul)
	}
 }
 s=s.replace(/\<\/tbody\>\<\/table\>\<\/ul\>\<ul\>\<table\>\<tbody\>/g,"")
 if(s)s+="</ul>"
 if(polling)s+=protoActions
 return s.substring(5,s.length)
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
 document.getElementById("msg").value=window[i].toString()
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
 if(document.getElementById("msg").value.length>MAXMSG)document.getElementById("msg").value=document.getElementById("msg").value.substring(0,MAXMSG/2)
 showscript(-1)
}

INFO=""
function showmsg(n,objwhat,moreinfo){
 var what=objwhat+(moreinfo?" :: "+moreinfo:"")
 msglog+="\n"+what
 var s=document.getElementById("msg").value
 if(s.length>MAXMSG) s=s.substring(0,MAXMSG/2)
 document.getElementById("msg").value=n+": "+what+"\n"+s
 if(what.indexOf("executing script")>=0)return
 if(what.indexOf("Symmetry Information")>=0){
	INFO=what.replace(/\|/g,"\t\n")
	setTimeout("alert(INFO)",100)
 }
}

function showfile(s){
 if(s.length<100)return
 thiscommand=""
 var s="<pre>"+s.replace(/\</g,"&lt;")+"</pre>"
 dowritenew(s)
}

function showmsgbox(doNotInvert){
 var s=document.getElementById("msg").value+""
 if (!doNotInvert)S=s.split("\n").reverse().join("\n")
 s="<pre>"+s.replace(/\</g,"&lt;")+"</pre>"
 dowritenew(s)
}

function showthefile(){
 thiscommand="show file"
 jmolScript(thiscommand)
}

function showpage(TF){
 if(TF)isprototype = true
 if(isprototype) {
   if(archive=="")archive = "JmolAppletProto.jar"
 }
 var s=getpage()
 document.write(s)
 doPolling(-1)
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

function doResize() {
 var myHeight=winHeight()
 document.getElementById("aframe").style.height = myHeight
 document.getElementById("bframe").style.height = myHeight
}


function getpage(){

 var s=gettitle()+getremark()+getinfo()

 s+='\n<div id="aframe" style = "position:absolute;top:50px;left:10px;width:530px;overflow:auto;height:'+(winHeight()-75)+'px">'
+'<table id="atable"><tbody><tr><td>'
+getscripts()
+'\n</td></tr></tbody></table></div>'
+'\n<div id="bframe" style = "position:absolute;top:50px;left:550px;height:500px;width:450px;overflow:auto;height:'+(winHeight()-75)+'px">'
+'\n<span id="jmolApplet">'
+getapplet("jmol",model,codebase,height,width,loadscript+";"+Scripts[0],messagecallback,animcallback,pickcallback,loadstructcallback)
+'\n</span>'
+'\n<br />'+thecaption
+'\n<form action="javascript:showcmd()"><p>'
+'\ncmd: <input id="cmd" type="text" size="50" value="" />'
+'\n<a href="javascript:showmsgbox()">popup</a>'
 +'\n<br /><textarea id="msg" cols="'+ncolsfortextarea+'" rows="6" wrap="off">'
+'\n</textarea>'
+'\n<br />'+getfunctions()
+'\n</p></form>'
+'\n</div>'
 if(isxhtmltest)s=s.replace(/\</g,"<br />&lt;")
 return s
}



function usercallback(s){}

function showscript(i,j,script){
	if(!j)j=0
	var s=(script?script:i>=0?Scripts[i].split(" ~~ ")[j]:document.getElementById("cmd").value)
	showmsg("user",s+"\n")
	thiscommand=s
	usercallback(s)
	var S=(s+"#").split("#")
	document.getElementById("cmd").value=s=S[0]
	if(s.indexOf("getProperty ") == 0) {
		var sinfo=s.split(" ")[1]
		var ans=jmolGetPropertyAsArray(sinfo,s.split(" ")[2])
		ans=_jmolEnumerateObject(ans,sinfo)
		document.getElementById("msg").value=ans
		setTimeout('showmsgbox(1)',1000)
	} else {
		jmolScript(s)
	}
}

////////////////////prototype functions//////////

Callbacks=new Array("-atomPicked","fileLoaded","fileLoadError","-frameChanged","-measurePending","-measureCompleted","-measurePicked","-scriptStarted","-scriptEcho","scriptStatus","scriptError","scriptMessage", "scriptTerminated","-userAction","-viewerRefreshed","-newOrientation")

function loadModel(value){
 if (arguments.length==0) {
   d = document.getElementById("modellist")
   if(!d)return
   value = d[d.selectedIndex].value
 }
 var i = parseInt(""+value)
 if (i<1)return
 showscript(0,0,"load "+Models[i]+"; polyhedra bonds (not carbon) collapsed edges") 
}


function setOptions(all_or_none){
 for(var i=0;i<Callbacks.length;i++)document.getElementById(Callbacks[i]).checked = all_or_none
 getCallbackOptions()
}

icheckboxes=false

function getCheckList(){
 var s=""
 var skey=""
 var ischecked=0
 for(var i=0;i<Callbacks.length;i++){
	skey=Callbacks[i]
	ischecked=(skey.charAt(0)!="-")
	Callbacks[i]=skey=skey.replace(/\-/,"")
	s+=" <label><input onchange=getCallbackOptions() type=checkbox id='"+skey+"'"+(icheckboxes && ischecked?" checked=1":"")+">"+skey+"</label>"
	if((i+1)%4==0)s+="<br>"
 }
 s+=" <a href=javascript:setOptions(1)>all</a> <a href=javascript:setOptions(0)>none</a> "
 return s
}

function getCallbackOptions(){
 var s=""
 for(var i=0;i<Callbacks.length;i++)if(document.getElementById(Callbacks[i]).checked)s+=Callbacks[i]
 waspolling=polling
 polling=(s!="")
 var s="monitoring:" + s
 if(polling && !waspolling)doPolling(true)
 return s
}


function setOptions(all_or_none){
 for(var i=0;i<Callbacks.length;i++)document.getElementById(Callbacks[i]).checked = all_or_none
}

protoActions = ' <h3>THIS PAGE DEMONSTRATES PROTOTYPE FUNCTIONALITY<br> NOT AVAILABLE IN JMOL 10.2</h3><a href=javascript:dotest(-1)>[appletInfo]</a>'
 +' <a href=javascript:dotest(-2)>[animationInfo]</a>'
 +' <a href=javascript:dotest(0)>[fileName]</a>'
 +' <a href=javascript:dotest(1)>[fileHeader]</a>'
 +' <a href=javascript:dotest(2)>[fileContents]</a>'
 +'<br><a href=javascript:dotest(8.1)>[atomList]</a>'
 +' <a href=javascript:dotest(8)>[atomInfo]</a>'
 +' <a href=javascript:dotest(7)>[bondInfo]</a>'
 +' <a href=javascript:dotest(7.5)>[moleculeInfo]</a>'
 +" <a href=javascript:dotest(10)>[extractModel]</a>"
 +'<br><a href=javascript:dotest(3.1)>[auxiliaryInfo]</a>'
 +' <a href=javascript:dotest(3)>[orientationInfo]</a>'
 +' <a href=javascript:dotest(3.2)>[transformInfo]</a>'
 +' <a href=javascript:dotest(3.3)>[centerInfo]</a>'
 +' <a href=javascript:dotest(3.4)>[boundboxInfo]</a>'
 +"<br><a href=javascript:dotest(4)>[modelInfo]</a>"
 +" <a href=javascript:dotest(4.2)>[polymerInfo]</a>"
 +" <a href=javascript:dotest(4.3)>[chainInfo]</a>"
 +" <a href=javascript:dotest(4.4)>[shapeInfo]</a>"
 +" <a href=javascript:dotest(4.5)>[stateInfo]</a>"
 +" <a href=javascript:dotest(9)>[reload THIS orientation]</a>"
 +"<br><br>"
// +" <a href=javascript:dotest(11)>[jmolStatus]</a>"
 +" monitor: "
 +getCheckList()
//," JSON data returned:<br><textarea name=outputJSON id=outputJSON rows=5 cols=50></textarea>"
 +"<br> <input type=checkbox name=idecode id=idecode checked=1>Decoded: <a href=javascript:showoutput()>new window</a><br><textarea name=output id=output rows=5 cols=60 wrap=off></textarea>"


function dotest(iwhat){
 if(iwhat!=3)monitoron=false
 var s=""
 var sJSON=""
 var info=""
 var sinfo=""
 var sparam=""
 var str=""
 if(iwhat==-1){
   sinfo="appletInfo"
 }else if(iwhat==-2){
   sinfo="animationInfo"
 }else if(iwhat==0){
   str="fileName"
 }else if(iwhat==1){
   str="fileHeader"
   info = jmolGetPropertyAsString(sinfo)
 }else if(iwhat==2){
   str="fileContents"
 }else if(iwhat==3){
   sinfo="orientationInfo"
 }else if(iwhat==3.1){
   sinfo="auxiliaryInfo"
 }else if(iwhat==3.2){
   sinfo="transformInfo"
 }else if(iwhat==3.3){
   sinfo="centerInfo"
 }else if(iwhat==3.4){
   sinfo="boundboxInfo"
 }else if(iwhat==3.5){
   sinfo="zoomInfo"
 }else if(iwhat==4){
   sinfo="modelInfo"
 }else if(iwhat==4.2){
   sparam="PROMPT"
   sinfo="polymerInfo"
 }else if(iwhat==4.3){
   sparam="PROMPT"
   sinfo="chainInfo"
 }else if(iwhat==4.4){
   sinfo="shapeInfo"
 }else if(iwhat==4.5){
   sparam="visible"
   sinfo="stateInfo"
 }else if(iwhat==7){
   sparam="PROMPT"
   sinfo="bondInfo"
 }else if(iwhat==7.5){
   sparam="PROMPT"
   sinfo="moleculeInfo"
 }else if(iwhat==8){
   sparam="PROMPT"
   sinfo="atomInfo"
 }else if(iwhat==8.1){
   sparam="PROMPT"
   sinfo="atomList"
 }else if(iwhat==9){
   info = jmolGetPropertyAsArray("orientationInfo")
   s="load "+jmolGetPropertyAsString("fileName")+";"+info.moveTo
   document.getElementById("cmd").value=s
   jmolScript(s)
 }else if(iwhat==10){
   s=prompt("enter an atom expression",what)
   if(!s)return
   what=s
   s = jmolGetPropertyAsString("extractModel",what)
   var A=jmolGetPropertyAsArray("orientationInfo")
   showoutput(s);   jmolLoadInlineScript(s,A.moveTo)
 }else if(iwhat==11){
   whatm=getCallbackOptions()
   var A=jmolGetStatus(whatm)
   parseMessages(A)
   doPolling(polling)
   return
 }
 if(str!=""){
   s=jmolGetPropertyAsString(str)
 }else if(sinfo!=""){
   if(sparam == "PROMPT"){
     s=prompt("enter an atom expression",what)
     if(!s)return
     what=s
     sparam=what
   }
   if(document.getElementById("idecode").checked){
	s=jmolGetPropertyAsArray(sinfo,sparam)
	s=_jmolEnumerateObject(s,sinfo)
   }else{
	s=jmolGetPropertyAsJSON(sinfo,sparam)
   }
 }
 if(s!="")document.getElementById("output").value=s
}

function parseMessages(A){
 if(!A)return
 var s=""
 for(var i=A.length-1;i>=0;i--){
	s=A[i][3]+"\n"+s
	showmsg("msg",A[i])
 }
 document.getElementById("output").value=s
}

