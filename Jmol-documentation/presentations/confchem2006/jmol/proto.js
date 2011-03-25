
title="ATOMS";model="caffeine.xyz;set testflag1; set solvent on;dots on"

messagecallback=""
animcallback=""
pickcallback=""
loadstructcallback=""

delayms = 100; delayms0 = 1000
height=400;width=400

Callbacks=new Array("-atomPicked","fileLoaded","fileLoadError","-frameChanged","-measurePending","-measureCompleted","-measurePicked","-scriptStarted","-scriptEcho","scriptStatus","scriptError","scriptMessage", "scriptTerminated","-userAction","-viewerRefreshed","-newOrientation")

function getCheckList(){
 var s=""
 var skey=""
 var ischecked=0
 for(var i=0;i<Callbacks.length;i++){
	skey=Callbacks[i]
	ischecked=(skey.charAt(0)!="-")
	Callbacks[i]=skey=skey.replace(/\-/,"")
	s+=" <label><input type=checkbox id='"+skey+"'"+(ischecked?" checked=1":"")+">"+skey+"</label>"
	if((i+1)%4==0)s+="<br>"
 }
 s+=" <a href=javascript:setOptions(1)>all</a> <a href=javascript:setOptions(0)>none</a> "
 return s
}

function getCallbackOptions(){
 var s="monitoring:"
 for(var i=0;i<Callbacks.length;i++)if(document.getElementById(Callbacks[i]).checked)s+=Callbacks[i]
 return s
}


function setOptions(all_or_none){
 for(var i=0;i<Callbacks.length;i++)document.getElementById(Callbacks[i]).checked = all_or_none
}

Scripts=new Array(" "
,' This prototype page illustrates the proposed frame/animation enhancements and reading data from a Spartan file. Be sure to click on [modelInfo].'
	,'load caffeine.xyz;set solvent on;dots on;moveto 1.0 -561 -26 -827 71.3 124;'
	,'load t.xyz;set solvent on;dots on;moveto 1.0 -561 -26 -827 71.3 124;'
	,'load C6H6.smol;select */1;set solvent on;dots on;moveto 1.0 -561 -26 -827 71.3 124;'
	,'load tt.xyz;set solvent on;dots on;moveto 1.0 -561 -26 -827 71.3 124;'
	,'load 1crn.pdb;set solvent on;dots on;moveto 1.0 853 285 437 144.4 317;'
	,'set testflag1 on;set radius 1.2001;set solvent on;dots on; #bob\'s calc'
	,'set testflag1 off;set radius 1.2000;set solvent on;dots on; #Jmol 10.2'
	,'depth 50;slab 100;slab on;'
	,'slab off;'
	,'wireframe off;spacefill off;'
	,'wireframe 0.05;spacefill 0.15;'
	,'set testflag2 on; # show contact lines'
	,'set testflag2 off;'
	,'set testflag3 on; # very crude solid surface'
	,'set testflag3 off;'
	,'set testflag4 on; # trace solvent centers'
	,'set testflag4 off;'
	,'load 1crn.pdb; set testflag1 on; set solvent on; dots on; slab 50; slab on;moveto 1.0 244 -141 -960 108.1 1260;'


 ,' <a href=javascript:dotest(-1)>[appletInfo]</a>'
 +' <a href=javascript:dotest(-2)>[animationInfo]</a>'
 +' <a href=javascript:dotest(0)>[fileName]</a>'
 +' <a href=javascript:dotest(1)>[fileHeader]</a>'
 +' <a href=javascript:dotest(2)>[fileContents]</a>'
 +'<br><a href=javascript:dotest(8.1)>[atomList]</a>'
 +' <a href=javascript:dotest(8)>[atomInfo]</a>'
 +' <a href=javascript:dotest(7)>[bondInfo]</a>'
 +" <a href=javascript:dotest(10)>[extractModel]</a>"
 +'<br><a href=javascript:dotest(3)>[orientationInfo]</a>'
 +' <a href=javascript:dotest(3.2)>[transformInfo]</a>'
 +' <a href=javascript:dotest(3.3)>[centerInfo]</a>'
 +' <a href=javascript:dotest(3.4)>[boundboxInfo]</a>'
 +' <a href=javascript:dotest(3.5)>[zoomInfo]</a>'
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
," <input type=checkbox name=idecode id=idecode checked=1>Decoded: <a href=javascript:showoutput()>new window</a><br><textarea name=output id=output rows=5 cols=60 wrap=off></textarea>"
)
remarks=""
//]]>

what="visible"
whatp="centerInfo"
whatm=""

function showoutput(s){ if(!s) s=document.getElementById("output").value; dowritenew("<pre>"+s+"</pre>") };function oldshowoutput(){
 dowritenew("<pre>"+document.getElementById("output").value+"</pre>")
}

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
   setTimeout("dotest(11)",delayms)
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

monitoron=0
function domonitor(isiter){
  if(!isiter)monitoron=!monitoron
  if(!monitoron)return
  dotest(3)
  setTimeout("domonitor(1)",delayms)  
}


