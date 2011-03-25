//examples.js by Bob Hanson hansonr@stolaf.edu 7:53 AM 8/18/2006
isxhtmltest=0/1
isinitialized=0
MAXMSG=100000
msglog=""
height=350
width=350

force_useHtml4Object=0
force_useIEObject=0

TitleInfo=new Array()
TitleInfo["lighting"]="Adjusting the Lighting Using the Set Command"
TitleInfo["animation"]="Animations and Frames"
TitleInfo["atoms"]="Atom-Related Scripting"
TitleInfo["bonds"]="Bond-Related Scripting"
TitleInfo["structure"]="Displaying Protein Secondary Structure"
TitleInfo["select"]="Options For Selecting Atoms and Groups of Atoms"
TitleInfo["callback"]="Using Call-Back Functions"
TitleInfo["scaletest"]="Using Scale3d To Set the Exact Scale"
TitleInfo["labels"]="Using the Label Command To Highlight Atoms"
TitleInfo["moveto"]="Setting the orientation with MoveTo"
//TitleInfo["resize"]="Resize an Applet using 'show orientation' and 'span'"
TitleInfo["predefinedsets"]="Selecting Predefined Sets"

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
docbase="../index.htm"
codebase=".."
archive="JmolApplet.jar"
messagecallback="showmsg"
thiscommand=""
animcallback=""
pickcallback="showmsg"
loadstructcallback=""
echoformat="font echo 14"
echoformat2="font echo 16"
title="example form"
Scripts=new Array("reset")
ref=""
remark=""
FTREF250="<a class=\"ftnote\" href=\"javascript:showref(250)\"><sup>*</sup></a>"
woptions="menubar=yes,scrollbars,alwaysRaised,width=700,height=600,left=50"
loadscript=";"

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

function getapplet(name, model, codebase, height, width, script, msgcallback) {

  if (!isinitialized)jmolInitialize(codebase,archive)
  if(force_useHtml4Object)_jmol.useHtml4Object=1
  if(force_useIEObject)_jmol.useIEObject=1
  isinitialized = 1
  jmolSetDocument(0)
  if (model)script = "load " + model + ";" + script
  var s = jmolApplet([width,height], script, "X")
  var sext = ""
  if (msgcallback) {
    sext = "\n<param name='MessageCallback' value='" + msgcallback + "' />"
    s = s.replace(/\<param/, sext + "\n<param")
  }
  if (pickcallback) {
    sext = "\n<param name='PickCallback' value='" + pickcallback + "' />"
    s = s.replace(/\<param/, sext + "\n<param")
  }

  return s
}

function getinfo(){
 var script=getscriptlink(0,false)
 var s="Click on a link below to see what it does. You can also type a command in the box below the model to see its effect."
 if(model)s+=" The model used in this case is "+(model.length==8 && model.indexOf(".pdb")==4?"<a rel=\"_blank\" href=\"http://pdbbeta.rcsb.org/pdb/explore.do?structureId="+model.substring(0,4)+"\">["+model.substring(0,4)+"]</a>":model)+"."
 if(script)s+=" In each case, the following default script is run first: "+script
 return "<p>"+s+"</p>"
}

function getremark(){
 return (remark?"<blockquote><p>"+remark+"</p></blockquote>":"")
}

function getscriptlink(i,isul){
 if(!Scripts[i])return ""
 var S=Scripts[i].split(" ~~ ")
 var s=(S.length>1?(isul?"</ul>":"")+"<table><tbody><tr>":"")
 //2> means colspan=2
 for(var j=0;j<S.length;j++){
	if(S.length>1)s+="\n<td"+(td2width && S.length<=ntd?" width='"+td2width+"'":"")+" valign='top'"+(isNaN(parseInt(S[j]))?">":" colspan='"+parseInt(S[j])+"'")+(isul?"<ul>":"")
	s+=(isul?"\n<li>":"")+(S[j].indexOf("<span")>=0||S[j].indexOf("<a href")>=0?S[j]:"<a href=\"javascript:showscript("+i+","+j+")\">"+S[j].replace(/\</g,isxhtmltest?"&amp;lt;":"&lt;")+"</a>")+(isul?"</li>":"")
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
 var S=new Array()
 for(var i in window){
	try {
		if(typeof(window[i])=="function" && window[i].toString().indexOf("native")<0)S[S.length]="<option value=\""+i+"\">"+i+"()</option>"
	}
	catch(e) {
		// ignore
	}
 }
 if(!S)return ""
 return "functions on this page:<select name=\"myfunctions\" onchange=\"showfunction(this.value)\"><option value=\"0\"></option>"+S.sort().join("")+"</select>"
}

function gettitle(){
 var s="<h3><a rel=\"_blank\" href=\"http://www.jmol.org\">Jmol</a> Examples: <a rel=\"_blank\" href=\""+docbase+(ref.charAt(0)=="?"?"":"#")+idof(ref)+"\">"+title+"</a></h3>"
 s="<form action=\"\"><table width=\"800\"><tbody><tr><td>"+s+"</td><td>"+gettitleinfo()+"</td></tr></tbody></table></form>"
 return s
}

function showcmd(){
 if(document.getElementById("msg").value.length>MAXMSG)document.getElementById("msg").value=""
 showscript(-1)
}

function showmsg(n,objwhat){
 var what=objwhat+""
 msglog+="\n"+what
 var s=document.getElementById("msg").value
 if(s.length>1000)s=""
 document.getElementById("msg").value=n+": "+what+"\n"+s
 if(what.indexOf("executing script")>=0)return
 if(thiscommand.indexOf("show file")==0)showfile(what)
}

function showfile(s){
 if(s.length<100)return
 thiscommand=""
 var s="<pre>"+s.replace(/\</g,"&lt;")+"</pre>"
 dowritenew(s)
}

function showmsgbox(){
 var s=document.getElementById("msg").value
 s="<pre>"+s.replace(/\</g,"&lt;")+"</pre>"
 dowritenew(s)
}

function showthefile(){
 thiscommand="show file"
 jmolScript(thiscommand,"X")
}

function doinit(){
 showpageDOM()
 fixlinks()
}

function showpage(){
 var s=getpage()
 document.write(s)
}

function showpageDOM(){
 var s=getpage()
 var d=document.getElementsByTagName("BODY")[0]
 //isDOM=0
 if(isDOM){
	_jmolWriteNodeHtml(d,s)
 }else{ //fallback test:
	d.innerHTML=s
 }
}

function getpage(){

 var s=gettitle()+getremark()+getinfo()
 s+='\n<div id="aframe">'
	+'<table id="atable"><tbody><tr><td>'
	+getscripts()
	+'\n</td></tr></tbody></table></div>'
	+'\n<div id="bframe">'
	+'\n<span id="jmolApplet">'
	+getapplet("jmol",model,codebase,height,width,loadscript+";"+Scripts[0],messagecallback,animcallback,pickcallback,loadstructcallback)
	+'\n</span>'
	+'\n<p><br />'+thecaption
	+'\n</p><form action="javascript:showcmd()"><p>'
	+'\n<br /><a href="javascript:showthefile()">file</a> cmd: <input id="cmd" type="text" size="50" value="" />'
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
	s="set echo top left;"+echoformat+";echo \" "+s.replace(/\"/g,"")+"\";"+(s.indexOf("load ")<0?Scripts[0]+";":"")+s
	s+=";set echo bottom left;"+echoformat2+";echo \" "+S[1]+"\""
	jmolScript(s,"X")
}

if(typeof(_jmol)=="undefined"){
	document.write('<link rel="stylesheet" type="text/css" href="styles.css" />')
	document.write('<script type="text/javascript" src="Jmol.js"></script>')
	isDOM=0
	window.onload=fixlinks
}else{
	isDOM=1
	window.onload=doinit
}

