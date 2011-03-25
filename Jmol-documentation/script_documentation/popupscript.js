// popupscript.js for Jmol interactive script documentation 
// 11:41 PM 8/17/2006

useGetElement = true // may be over-ridden

/// for getting info from the command line and entering it into divs


function getField(what,defaultInfo) {
 var tag = "&" + what + "="
 var s = unescape (("&"+document.location.search.substring(1)+tag +"&").split(tag)[1].split("&")[0])
 if (s == "" && defaultInfo)s = defaultInfo
 //alert(what+" "+s)
 return s
}


function loadDiv(where,what){
 document.getElementById(where).innerHTML=what
}

function loadTitle(title){
 document.title=title
 loadDiv("title",title)
}


List=new Array()

function loadList(list) {
 List=list.split("||")
 var s="\n<ul>"
 for(i=0;i<List.length;i++){
	var ref=(List[i].indexOf("#")==0?"</ul>"+List[i].substring(1)+"<ul>":"<li><a href=\"javascript:loadScript("+i+")\">"+List[i]+"</a></li>")
	s+="\n"+ref+""
 }
 s+="\n<ul>"
 loadDiv("theList",s)
}

function sortscript(a,b) {
 return (a[0] < b[0] ? -1 : a[0] > b[0] ? 1 : 0)
}

function loadScript(n, scr){
 scr = (n < 0 ? scr : List[n])
 var s=jmolScriptWaitAsArray(scr,"X")
 if (s=="")s = [[0,"<font color=red>Jmol script compiler error</font>"]]
 var A = new Array()
 for (var i = 0; i < s.length; i++)for(var j=0;j < s[i].length;j++)A[A.length] = s[i][j]
 if (A.length)A = A.sort(sortscript)
 for (var i =0; i < A.length; i++)A[i] = A[i][3].replace(/\|/g,"<br />")
 A[0] = "<b>"+A[0]+"</b>"
 loadDiv("theVersion",A.join("<br />"))
}

function loadText(theText) {
 var s='\n<textarea name="cmds" id="cmds" rows="5" cols="45">\n'+theText+'\n</textarea>'
 loadDiv("theText",s)
}

appversion = ""

function jmolScriptInfo() {
 var s=document.info.cmds.value
 if(s.indexOf("js:")==0)
	alert(eval(s.substring(3)))
 else {
	s=loadScript(-1,s)
 }
}

function fileLoadedCallback(x,msg) {
        if (appversion == "") {
	 appversion = jmolDecodeJSON(jmolGetPropertyAsJSON("appletInfo","","X"))
	 var S = appversion.split("\n")
	 appversion = "<table width=800 cellpadding=10><tr><td>"+S[1]+"<br />"+S[2]+"<br />"+S[3]+"<br />"+S[4]+"<br />"+S[5]+"<br />"+S[6]+"</td></tr></table>"
	 loadDiv("theVersion",appversion.replace(/\n/g,"<br />"))
	}
}

/* <pre>

loaddata.js Bob Hanson hansonr@stolaf.edu 1:35 PM 09/28/2005

new RCSB call 6:02 AM 5/6/2006
---------

allows loading of ANY model from ANY server into ANY applet on ANY page.

Bob Hanson St. Olaf College hansonr@stolaf.edu 4:19 PM 9/19/2005

---------

user functions include: 

getModel()        starts the process with user input 

retrieveModel()   starts the process without user input 

loadModel()       called BY the server ON the client

showModelText()   opens new window with model text

openWindowText()  writes text to a new window

---------

to use:


just add a script with src="http://fusion.stolaf.edu/chemistry/jmol/loaddata.js"
to your web page. Include an applet on your page along with links such as the following:

  <a href="javascript:getModel()">load a molecule</a>
  <a href="javascript:retrieveModel('1crn')">1crn</a>
  <a href="javascript:loadModel()">(reload)</a>
  <a href="javascript:showModelText()">(text)</a>
  
You can replace any or all of the functions shown below to suit.

In addition, you will need your own Jmol jar files and Jmol.js.

---------

how it works:

a) getModel() requests user input and then calls retrieveModel(), which adds a 
SCRIPT "node" to the DOM (Document Object Model) tree at the end of the HEAD section. 
(The DOM tree is the internal representation of the web page within the browser.) 
This action sends a message to the fusion.stolaf.edu server, passing to a ColdFusion
file the URL of the model. The server gets the model from wherever it might be on the
web and returns it AS A JAVASCRIPT ARRAY along with the command "loadModel()". 
The loadModel() function runs IN THE BROWSER, combines the array back into a string, and 
fires the applet's loadInline() function with the model data as a parameter.

The return is asynchronous and is initiated with the line in retrieveModel():

 d.appendChild(o)

As a bonus, the model is cached in the browser as a JavaScript array. So additional 
calls to load this particular model never need to go to the server. 

You do not need to use http://fusion.stolaf.edu/chemistry/jmol/loaddata.js -- you can copy this file
to your system and modify it any way you like. What is fixed is that the server will return:

A=new Array()         // contents of the requested file, line by line
Cache["#urlname#"]=A  // a browser-based cache of the file in array format. 
defaultloadscript=""  // signaling that a file has been returned. 

In serverURL you can set "alert=0" instead of "alert=1" in your version of this code
to turn off the server message indicating how many lines were returned.


*/


A=new Array()
Cache=new Array()

serverURL="http://fusion.stolaf.edu/chemistry/jmol/loaddata.cfm?alert=1&url="
defaultRetrievedir="http://www.stolaf.edu/academics/chemapps/jmol/docs/"
defaultURL="http://www.rcsb.org/pdb/displayFile.do?fileFormat=PDB&structureId=1CRN"
thismodel="1crn"
thistarget="0"
defaultloadscript=""

function loadModel(targetSuffix,Adata){
//alert(Adata)
 if(!targetSuffix)targetSuffix=thistarget
 if(!Adata)Adata=A
 thistarget=targetSuffix
 //called by server, but in client
 var app=document.getElementById("jmolApplet"+targetSuffix)
 if(defaultloadscript){
	app.script(defaultloadscript)
 }else{
	app.loadInline(Adata.join("\n"))
 }
}

function getModel(targetSuffix){
 if(!targetSuffix)targetSuffix="0"
 var s=prompt("Enter a 4-digit PDB ID or the URL of a model file.",thismodel)
 if(!s)return
 thismodel=s
 retrieveModel(s,targetSuffix,1)
}

function retrieveModel(url,targetSuffix,ialert){
 if(!targetSuffix)targetSuffix="0"
 var pdbid=""
 if(url.indexOf(":")<0){
	if(url.length==4){
		url=url.toUpperCase()
		pdbid="&pdbid="+url
		url=defaultURL.replace(/1CRN/g,url)
	}else{
		url=defaultRetrievedir+url
	}
 }
 url=url.replace(/\+/g,"%2B")
 if(Cache[url]){
	A=Cache[url]
	loadModel(targetSuffix,Cache[url])
	return
 }

 if(ialert)alert("Please stand by while the following URL is retrieved:\n\n"+url)

 var d=document.getElementsByTagName("HEAD")[0]
 var o=document.createElement("script")
 o.setAttribute("type","text/javascript")
 var s=serverURL+escape(url)+"&app="+targetSuffix+pdbid
 o.setAttribute("src",s)
 d.appendChild(o)
}

function showModelText(){
	if(A.length==0){
		alert("Load some data from the server; then you can look at the file.")
		return
	}
	openWindowText(A.join("\n"))
}

function openWindowText(s){
	s=""+s  //stringifies it
	s="<html><head></head><body><pre>"+s.replace(/\</g,"&lt;")+"</pre></body></html>"
 	var opt="menubar,scrollbars,width=900,height=600,left=120,top=50"
	var sm=""+Math.random()
	sm=sm.substring(3,10)
	var w=open("","DT_"+sm,opt)
	w.document.open()
	w.document.write(s)
	w.document.close()
}

function loadAlert(s){
	alert(s)
}