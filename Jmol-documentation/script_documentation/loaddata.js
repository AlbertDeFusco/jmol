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
defaultdir="http://www.stolaf.edu/people/hansonr/jmol/docs/"
defaultURL="http://www.rcsb.org/pdb/displayFile.do?fileFormat=PDB&structureId=1CRN"
thismodel="1crn"
thistarget="0"
defaultloadscript=""

function loadModel(targetSuffix,Adata){
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
		url=defaultdir+url
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
 o.setAttribute("src",serverURL+escape(url)+"&app="+targetSuffix+pdbid)
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