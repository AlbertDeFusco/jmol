/*
 * Copyright (C) 2006-2009 Nicolas Vervelle,  The Jmol Development Team
 *
 * Contact: nico@jmol.org, jmol-developers@lists.sf.net
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

// (version notes moved to Jmol.php)

function jmolWikiPopupWindow(windowTitle, windowSize, windowLeft, windowTop, windowCode) {
  var windowWidth = parseInt(windowSize) + 15;
  var windowHeight = parseInt(windowSize) + 15;
  var opt = "width=" + windowWidth + "," +
            "height=" + windowHeight + "," +
            "resizable," +
            "left=" + windowLeft + ",top=" + windowTop ;
  var s =
    "<html><head>\n" +
    "<style type='text/css'>body, html { height:100%; margin:0; padding:0; }</style>" +
    "<title>" + windowTitle + "</title>\n" +
    "</head><body>\n";
	
  var ap = jmolParseWindowCode(windowCode);
  // make the popup applet resizable:
  ap[1] = '"100%"';	// overwrites the former size
  ap[2] = ap[2].charAt(0) + "set zoomLarge off; " + ap[2].substring(1);	// skips the quote and inserts command
  
  s += eval( ap[0] + "(" + ap[1] + ", " + ap[2] + ")" );	// put into page the code resulting from jmolApplet
  ap[3] = ap[3].replace(/^\s+/, "").replace(/\s+$/, ""); //trim spaces
  if (!!ap[3]) {
    s += eval(ap[3]);	// put into page the code resulting from whatever is after jmolApplet (was jmolBr)
  }

  s += "\n</body></html>";
  
	// window name in IE cannot contain spaces or parentheses (and windowTitle may have anything)
	// Therefore, avoid "non-word" characters (i.e. other than A-Z, numbers and underscore)
  var purgedTitle = windowTitle.replace(/\W/g, "_");
    /* Chrome (at least, 9.0.587) has a weird behaviour in inserting applet code 
        into popup window document: the applet is not displayed.
        The only workaround found is to open a named webpage (even if it does not exist) 
        AND either not to close its document or to delay its closing.
        This hack might be removed in future, if no longer needed for newer Chrome versions.
    */
  var wN = "about:blank"; //blank string is not good, it prevents setting the window title in Safari.
  var isChrome = !(navigator.userAgent.search("Chrome")==-1);
  if (isChrome) { wN = "dummy.html"; }  //nonexistent file, but makes it work.
  var w = open(wN, purgedTitle, opt);
  w.document.open();
  w.document.write(s); 
  var z = setTimeout("w.document.close();", 200);  //Chrome bug
  w.focus();
}

function jmolParseWindowCode(wC) {
  var ap=[],t1,t2,j1,j2;
  var j1 = wC.indexOf("jmolApplet");
  eval(wC.substring(0,j1));	// execute the windowCode before "jmolApplet" (jmolInitialize, jmolSetAppletColor etc.)
  jmolSetDocument(false);		// execute this
  t1 = wC.substring(j1);		// part of windowCode including "jmolApplet" and whatever follows
  j1 = t1.indexOf("\(");
  ap[0] = t1.substring(0,j1);		// "jmolApplet"  or  "jmolAppletInline"
  t2 = t1.substring(j1+1);
  j1 = t2.indexOf(",");
  ap[1] = t2.substring(0,j1);		// applet size (single number, square applet)
  j2 = t2.indexOf("\);");				// end of jmolApplet part
  ap[2] = t2.substring(j1+2,j2);	// script
  ap[2] = ap[2].replace(/\n/g, "|");		// protect newlines in inline data
  ap[3] = t2.substring(j2+2);		// whatever is after jmolApplet (was only jmolBr, now empty)
  return ap;
}

function jmolWikiPopInline(divID, windowCode) {
	var jDiv = document.getElementById("JmolInlineEnv"+divID);
	if ( jDiv.innerHTML!="" )
	{	// the applet exists already (was created before and then hidden); display it!
		//alert("exists")
	  jDiv.style.display="inline";
	}
	else
	{	// the applet does not exist; create it!
		//alert("does not exist")
	  var ap = jmolParseWindowCode(windowCode);
	  var s = "";
	  s += eval( ap[0] + "(" + ap[1] + ", " + ap[2] + ")" );	// put into page the code resulting from jmolApplet
	  if (ap[3]) {
		s += eval(ap[3]);	// put into page the code resulting from whatever is after jmolApplet (was jmolBr)
	  }
	  jDiv.innerHTML = s;
	}
  document.getElementById("JmolInlineLink"+divID).style.display="none";
  document.getElementById("JmolInlineHide"+divID).style.display="inline";
}

function jmolWikiPopInlineHide(divID) {
  document.getElementById("JmolInlineLink"+divID).style.display="inline";
  document.getElementById("JmolInlineEnv"+divID).style.display="none"; 
  document.getElementById("JmolInlineHide"+divID).style.display="none";
}

function setupCheckboxShiftClick() {
	return;
	/* prevent the function in wikibits.js from acting	
		so that checkboxes work
	*/
}
