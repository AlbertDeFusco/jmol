/*

ChimeToJmol.js
hansonr@stolaf.edu 8:07 AM 4/21/2010

A set of functions that will (when fully developed) 
allow the following on just about any Chime page to 
convert the Chime EMBED tags to Jmol applets and buttons.

Also changes older Jmol "applet" code to use Jmol.js

1) add these lines to your page's <HEAD> section:

 <script language="javascript" SRC="Jmol/Jmol.js">  </script>
 <script language="javascript" SRC="Jmol/ChimeToJmol.js">  </script>

2) Set jmolDirectory (below) to the desired directory and load it with
the standard Jmol files (Jmol.js, JmolApplet*.jar) and ChimeToJmol.js

3) If you are using a BODY onload="funcName()" attribute already, 
then add 

  checkJmol()

to it.

4) If you want to test your page, add NOJMOL to the page URL

TODO: more script conversions needed for Chime.

*/

// set this as desired
var jmolDirectory = "./Jmol"


/////////Jmol section //////////

function checkJmol() {
	if (document.location.search.indexOf("NOJMOL") >= 0)return
	var body = document.body.innerHTML
	if (body.indexOf("JmolApplet") >= 0) {
		if(body.indexOf("JmolAppletControl") >= 0)__fixJmol()
		return
	}
	if (body.indexOf("<EMBED") >= 0 || body.indexOf("<embed") >= 0) __fixChime()
}

window.onload = checkJmol

function __fixJmol() {
	var body = document.body.innerHTML
	var ptAPPLET = -1
	var Text = body.split("<applet")
	if (Text.length == 1)return
	for (var i = 1; i < Text.length; i++) {
		var text = Text[i]
		var pt = text.indexOf("</applet>")
		if (text.indexOf("JmolAppletControl") >= 0) {
			Text[i] = __jmolFixJmolAppletControl(text.substring(0, pt)) + text.substring(pt + 9)
		} else if (text.indexOf("JmolApplet") >= 0) {
			Text[i] = __jmolFixJmolApplet(text.substring(0, pt)) + text.substring(pt + 9)
		} else {
			Text[i] = "<applet" + text
			continue
		}
	}
	document.body.innerHTML = Text.join("")
}

function __jmolFixJmolApplet(tag) {
	var A = __jmolGetAttributes(tag) 
	if (!A.width)A.width = 300
	if (!A.height)A.height = 300
	if (!A.script)A.script = ""
	if (!A.codebase)A.codebase = jmolDirectory
	if (A.archive == "JmolApplet.jar") A.archive = "JmolApplet0.jar"
	if (document.location.protocol == "file:" && A.archive.indexOf("Signed") < 0) A.archive = "JmolAppletSigned0.jar"
	if (A.load)A.script = "load \"" + A.load + "\";" + A.script
	jmolInitialize(A.codebase, A.archive)
	jmolSetDocument(0)
	return jmolApplet([A.width,A.height],A.script, A.name)	
}

function __jmolFixJmolAppletControl(tag) {
	var A = __jmolGetAttributes(tag) 
	if (!A.width)A.width = 12
	if (!A.height)A.height = 12
	_jmol.buttonCssText = "style=\"width:" + A.width + "\""
	if (A.altscript)
		return jmolCheckbox(A.script, A.altscript, "", false, A.target)
	return "<span width=\"" + A.width + "\" height=\"" + A.height + "\">" + jmolButton(A.script,"X",A.target) + "</span>"
}

function __fixChime() {
	jmolInitialize(jmolDirectory, 
		(document.location.protocol=="file:" ? "JmolAppletSigned0.jar" : "JmolApplet0.jar"))
	jmolSetDocument(0)

	var body = document.body.innerHTML
	var Text = body.split("<EMBED")
	if (Text.length == 1)Text = body.split("<embed")
	if (Text.length == 1)return
	for (var i = 1; i < Text.length; i++) {
		var text = Text[i]
		var pt = text.indexOf(">")
		if (text.indexOf("src=") >= 0) {
			Text[i] = __jmolFixChimeApplet(text.substring(0, pt)) + text.substring(pt + 1)
		} else if (text.indexOf("target=") >= 0) {
			Text[i] = __jmolFixChimeButton(text.substring(0, pt)) + text.substring(pt + 1)
		}
	}
	document.body.innerHTML = Text.join("")
}

function __jmolFixChimeApplet(tag) {
	var A = __jmolGetAttributes(tag) 
	A.width = parseInt(A.width)
	A.height = parseInt(A.height)
	A.script = (A.script ? __jmolFixChimeScript(A.script) : "")
	if (A.animmode) A.script += ";animation mode " + A.animmode + ";"
	if (A.animfps) A.script += ";animation fps " + A.animfps + ";"
	if (A.startanim && A.startanim.toLowerCase() == "true") A.script += ";animation on;"
	return jmolApplet([A.width,A.height],"load = \"" + A.src + "\";" + A.script, A.name)	
}

function __jmolFixChimeButton(tag) {
	var A = __jmolGetAttributes(tag) 
	if (!A.width)A.width = 12
	if (!A.height)A.height = 12
	A.script = __jmolFixChimeScript(A.script)
	return "<span width=\"" + A.width + "\" height=\"" + A.height + "\">" + jmolLink(A.script,"[x]") + "</span>"
}

function __jmolFixChimeScript(script) {
	script = script.replace(/\*\./g,"_")
			.replace(/stick on/g,"wireframe 0.15;")
			.replace(/ball\&amp\;stick off/g,"wireframe off;spacefill off")
			.replace(/select startanim\=false/g,"animation off")
			.replace(/select startanim\=true/g,"animation play")
	return script
}

function __jmolGetAttributes(tag) {
  var S = {}
  var name = ""
  var value = ""
  var inName = false
  var inValue = false
  if (tag.indexOf("<param") >= 0)
	tag = tag.replace(/\<param name\=/g," ")
		.replace(/value\=/g,"=")
		.replace(/\/\>/g, " ")
		.replace(/\>/g, " ")
		.replace(/[\r|\n|\t]/g, " ")
  if (tag.substring(tag.length-1, tag.length) == "/")
	tag = tag.substring(0, tag.length-1)
  tag = tag.replace(/\<br\>/g, " ")
  tag = tag.replace(/\<br \/\>/g, " ")
  tag = tag.replace(/\s+\=/g, "=")
  tag = tag.replace(/\=\s+/g, "=")
  tag += " ="
  for (var i = 0; i < tag.length - 2; i++) {
    switch(tag.charAt(i)) {
    case " ":
    case "\t":
    case "\n":
    case "\r":
	continue
    }
    var pts = tag.indexOf(" ", i)
    var pte = tag.indexOf("=", i)
    var ptq = tag.indexOf("'", pte + 1)
    var ptqq = tag.indexOf("\"", pte + 1)
    if (ptqq == pte + 1)ptq = ptqq
    var ptq2 = tag.indexOf((ptq == ptqq ? "\"" : "'"), ptq + 1)

    // <EMBED  xxxx ...
    if (pts < pte) {
	S[tag.substring(i, pts).toLowerCase().replace(/[\'\"]/g,"")] = ""
	i = pts
	continue
    }
    
    // <EMBED  xxxx=yyy ...
    if (pts < ptq) {
	S[tag.substring(i, pte).toLowerCase().replace(/[\'\"]/g,"")] = tag.substring(pte + 1, pts)
	i = pts
	continue
    }

    // <EMBED  xxxx="yyy" ...
   
    S[tag.substring(i, pte).toLowerCase().replace(/[\'\"]/g,"")] = tag.substring(ptq + 1, ptq2)
    i = ptq2    
  }
  return S
}


