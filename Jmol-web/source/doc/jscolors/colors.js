/*

This Javascript app was written by Tony Awtrey (tony@awtrey.com).

This application is licensed under the GNU Public License.
For more information see: http://www.gnu.org/copyleft/gpl.html

(c) 1998, Anthony Awtrey Consulting

*/

function makeArray0() { for (i = 0; i<makeArray0.arguments.length; i++) this[i] = makeArray0.arguments[i];}

var T = new makeArray0("00","10","20","30","40","50","60","70","80","90","A0","B0","C0","D0","E0","F0","FF");
var S = new makeArray0("00","33","66","99","CC","FF");
var SR = new makeArray0("00","33","66","99","CC","FF");
var SG = new makeArray0("00","33","66","99","CC","FF");
var SB = new makeArray0("00","33","66","99","CC","FF");
var R = "00"
var G = "00"
var B = "00"
var RGB = R + G + B


document.write ("<TABLE WIDTH=\"100%\" HEIGTH=\"100%\" BORDER=\"1\" CELLPADDING=\"0\" CELLSPACING=\"0\">");

document.write ("<TD WIDTH=\"16%\" VALIGN=\"top\" HEIGHT=\"30\" BGCOLOR=\"#FFFFFF\"><P CLASS=\"t\" ALIGN=\"center\">Web Color Chart<BR>By Tony Awtrey</TD>");

for (var i = 0; i < 5; i++)
{
	R = T[i]
	G = T[i]
	B = T[i]
	RGB = R + G + B
	document.write ("<TD VALIGN=\"top\" HEIGHT=\"30\" BGCOLOR=\"#" + RGB + "\"><P CLASS=\"t\" ALIGN=\"center\">#" + RGB +"</TD>");
}

document.write ("<TR>");

for (var i = 5; i < 11; i++)
{
	R = T[i]
	G = T[i]
	B = T[i]
	RGB = R + G + B
	document.write ("<TD VALIGN=\"top\" HEIGHT=\"30\" BGCOLOR=\"#" + RGB + "\"><P CLASS=\"t\" ALIGN=\"center\">#" + RGB +"</TD>");
}

document.write ("<TR>");

for (var i = 11; i < 17; i++)
{
	R = T[i]
	G = T[i]
	B = T[i]
	RGB = R + G + B
	document.write ("<TD VALIGN=\"top\" HEIGHT=\"30\" BGCOLOR=\"#" + RGB + "\"><P CLASS=\"t\" ALIGN=\"center\">#" + RGB +"</TD>");
}

document.write ("<TR>");

for (var ir = 0; ir < 6; ir++)
{
for (var ib = 0; ib < 6; ib++)
{
for (var ig = 0; ig < 6; ig++)
{
	R = SR[ir]
	G = SG[ig]
	B = SB[ib]
	RGB = R + G + B
	document.write ("<TD VALIGN=\"top\" HEIGHT=\"30\" BGCOLOR=\"#" + RGB + "\"><P CLASS=\"t\" ALIGN=\"center\">#" + RGB +"</TD>");
}
document.write ("<TR>");
}
document.write ("<TR>");
}

document.write ("</TABLE>");
