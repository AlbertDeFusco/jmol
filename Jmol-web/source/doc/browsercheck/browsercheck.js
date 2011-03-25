/* Copyright (C) 2004-2011  The Jmol Development Team
 *
 * Contact: jmol-developers@lists.sf.net
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
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 *  02111-1307  USA.
 */
function firstUpperCase(x) {
	return x.charAt(0).toUpperCase() + x.substring(1);
}
function testBrowser(candidate) {	// similar to _jmolTestUA() in Jmol.js
	var ua = _jmol.ua;
	var index = ua.indexOf(candidate);
	if (index < 0) { return false; }
	browserName = candidate;
	var t = ua.substring(index+candidate.length+1);
	browserNameVersion = t.substring(0,t.indexOf(" ")); //this is a string, not a number; no comparisons with it!
	return true;
}

var msg = msgAcceptable;
var mozillaVersion = "";
var javaVersion = 0;
var state = "yellow";
	
with (_jmol) {
	if (browser == "mozilla" && browserVersion >= 5) {
		var indexRV = ua.indexOf("rv:");
		if (indexRV > 0) {
			var rv = ua.substring(indexRV + 3);
			var indexEnd = rv.indexOf(")");
			var indexSemi = rv.indexOf(";");
			if (indexEnd < 0 || (indexSemi > 0 && indexSemi < indexEnd))
				indexEnd = indexSemi;
			if (indexEnd > 0)
				mozillaVersion = " " + rv.substring(0, indexEnd); 
		}
	}
	do {
		// Non-compatible or troublesome browsers:
		if (os == "mac" && browser == "mozilla" && browserVersion >= 5) {
			eval("try{javaVersion=" +
			 "java.lang.System.getProperty('java.version');" +
			 "} catch (e) { }");
			if (javaVersion < 1.4) {
				msg = msgMacMozillaOldJava + msgNoLiveConnectMac +
				msgMacMozillaAlternative;
				break;
			}
		}
		if (! isJavaEnabled) { msg = msgGenericNoJava; state = "red"; break; }
		if (! hasGetElementById) { msg = msgNoGetElementById; state = "red"; break; }
		if (os == "mac" && browser == "msie") { msg = msgMacIE + msgNoLiveConnectMac; break; }
		if (browser == "webkit" && browserVersion < 125.1) { msg = msgMacOldSafari + msgNoLiveConnectMac; break; }
		if (os == "mac" && browser == "opera" && browserVersion <= 7.54) { msg = msgMacOpera754OrLess; state = "red"; break; }
		if ((os == "linux" || os == "unix") &&
					browser == "konqueror" && browserVersion <= 3.3) {
			msg = msgLinuxKonqueror331OrLess; state = "red"; break; 
		}
		// Fully compliant browsers:
		if ( (os == "win" && browser == "msie") ||
					(browser == "webkit" && browserVersion >= 412.2) ||
					(browser == "mozilla" && browserVersion >= 5) ||
					(browser == "opera" && browserVersion >= 7.54) ) {
			msg = msgOK; state = "green"; break; 
		}
		// Other cases default to acceptable, yellow
	} while (false); // this 'loop' is here only to allow use of 'break'

	var osString = os;	/* for a more meaningful display */
	if (osString=="win") { osString = "Windows"; }
	else if (osString=="linux") { osString = "Linux"; }
	else if (osString=="unix") { osString = "Unix"; }
	else if (osString=="mac") { osString = "MacOS"; }
	else { osString = firstUpperCase(osString); }
	
	var browserName = browser, browserNameVersion = browserVersion; /* for a more meaningful display */
	if ( browserName=="mozilla" && browserVersion >= 5 ) { 
			/* Mozillas are always Mozilla/5; the browserVersion=5 is hence irrelevant as version nr. 
			All details are shown in UA anyway, so I'll put here the most user-friendly string */
		testBrowser("firefox") || testBrowser("seamonkey"); //instead of Mozilla data
		if (browserName=="mozilla") { browserNameVersion = mozillaVersion; }
	} 
	else if ( browserName=="webkit" ) { testBrowser("chrome") || testBrowser("epiphany") || testBrowser("safari"); }
	else if ( browserName=="msie") { testBrowser("msie"); browserName = "Internet Explorer"; }
	if ( browserName=="safari" && testBrowser("version") ) { browserName="safari"; } //display Safari version instead of WebKit version
	var browserString = firstUpperCase(browserName) + " " + browserNameVersion;
		}
