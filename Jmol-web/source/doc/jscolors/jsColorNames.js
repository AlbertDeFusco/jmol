/*

This Javascript app was written by Tony Awtrey (tony@awtrey.com).

This application is licensed under the GNU Public License.
For more information see: http://www.gnu.org/copyleft/gpl.html

(c) 1998, Anthony Awtrey Consulting

*/

function makeArray0() { for (i = 0; i<makeArray0.arguments.length; i++) this[i] = makeArray0.arguments[i];}

var N = new makeArray0(
"aliceblue","F0","F8","FF",
"antiquewhite","FA","EB","D7",
"aqua","00","FF","FF",
"aquamarine","7F","FF","D4",
"azure","F0","FF","FF",
"beige","F5","F5","DC",
"bisque","FF","E4","C4",
"black","00","00","00",
"blanchedalmond","FF","EB","CD",
"blue","00","00","FF",
"blueviolet","8A","2B","E2",
"brown","A5","2A","2A",
"burlywood","DE","B8","87",
"cadetblue","5F","9E","A0",
"chartreuse","7F","FF","00",
"chocolate","D2","69","1E",
"coral","FF","7F","50",
"cornflowerblue","64","95","ED",
"cornsilk","FF","F8","DC",
"crimson","DC","14","3C",
"cyan","00","FF","FF",
"darkblue","00","00","8B",
"darkcyan","00","8B","8B",
"darkgoldenrod","B8","86","0B",
"darkgray","A9","A9","A9",
"darkgreen","00","64","00",
"darkkhaki","BD","B7","6B",
"darkmagenta","8B","00","8B",
"darkolivegreen","55","6B","2F",
"darkorange","FF","8C","00",
"darkorchid","99","32","CC",
"darkred","8B","00","00",
"darksalmon","E9","96","7A",
"darkseagreen","8F","BC","8F",
"darkslateblue","48","3D","8B",
"darkslategray","2F","4F","4F",
"darkturquoise","00","CE","D1",
"darkviolet","94","00","D3",
"deeppink","FF","14","93",
"deepskyblue","00","BF","FF",
"dimgray","69","69","69",
"dodgerblue","1E","90","FF",
"firebrick","B2","22","22",
"floralwhite","FF","FA","F0",
"forestgreen","22","8B","22",
"fuchsia","FF","00","FF",
"gainsboro","DC","DC","DC",
"ghostwhite","F8","F8","FF",
"gold","FF","D7","00",
"goldenrod","DA","A5","20",
"gray","80","80","80",
"green","00","80","00",
"greenyellow","AD","FF","2F",
"honeydew","F0","FF","F0",
"hotpink","FF","69","B4",
"indianred","CD","5C","5C",
"indigo","4B","00","82",
"ivory","FF","FF","F0",
"khaki","F0","E6","8C",
"lavender","E6","E6","FA",
"lavenderblush","FF","F0","F5",
"lawngreen","7C","FC","00",
"lemonchiffon","FF","FA","CD",
"lightblue","AD","D8","E6",
"lightcoral","F0","80","80",
"lightcyan","E0","FF","FF",
"lightgoldenrodyellow","FA","FA","D2",
"lightgreen","90","EE","90",
"lightgrey","D3","D3","D3",
"lightpink","FF","B6","C1",
"lightsalmon","FF","A0","7A",
"lightseagreen","20","B2","AA",
"lightskyblue","87","CE","FA",
"lightslategray","77","88","99",
"lightsteelblue","B0","C4","DE",
"lightyellow","FF","FF","E0",
"lime","00","FF","00",
"limegreen","32","CD","32",
"linen","FA","F0","E6",
"magenta","FF","00","FF",
"maroon","80","00","00",
"mediumaquamarine","66","CD","AA",
"mediumblue","00","00","CD",
"mediumorchid","BA","55","D3",
"mediumpurple","93","70","DB",
"mediumseagreen","3C","B3","71",
"mediumslateblue","7B","68","EE",
"mediumspringgreen","00","FA","9A",
"mediumturquoise","48","D1","CC",
"mediumvioletred","C7","15","85",
"midnightblue","19","19","70",
"mintcream","F5","FF","FA",
"mistyrose","FF","E4","E1",
"moccasin","FF","E4","B5",
"navajowhite","FF","DE","AD",
"navy","00","00","80",
"oldlace","FD","F5","E6",
"olive","80","80","00",
"olivedrab","6B","8E","23",
"orange","FF","A5","00",
"orangered","FF","45","00",
"orchid","DA","70","D6",
"palegoldenrod","EE","E8","AA",
"palegreen","98","FB","98",
"paleturquoise","AF","EE","EE",
"palevioletred","DB","70","93",
"papayawhip","FF","EF","D5",
"peachpuff","FF","DA","B9",
"peru","CD","85","3F",
"pink","FF","C0","CB",
"plum","DD","A0","DD",
"powderblue","B0","E0","E6",
"purple","80","00","80",
"red","FF","00","00",
"rosybrown","BC","8F","8F",
"royalblue","41","69","E1",
"saddlebrown","8B","45","13",
"salmon","FA","80","72",
"sandybrown","F4","A4","60",
"seagreen","2E","8B","57",
"seashell","FF","F5","EE",
"sienna","A0","52","2D",
"silver","C0","C0","C0",
"skyblue","87","CE","EB",
"slateblue","6A","5A","CD",
"slategray","70","80","90",
"snow","FF","FA","FA",
"springgreen","00","FF","7F",
"steelblue","46","82","B4",
"tan","D2","B4","8C",
"teal","00","80","80",
"thistle","D8","BF","D8",
"tomato","FF","63","47",
"turquoise","40","E0","D0",
"violet","EE","82","EE",
"wheat","F5","DE","B3",
"white","FF","FF","FF",
"whitesmoke","F5","F5","F5",
"yellow","FF","FF","00",
"yellowgreen","9A","CD","32"
);


var total = 559;
var upto = 0;
var R1 = "00";
var G1 = "00";
var B1 = "00";
var RGB1 = "0";
var NAME1 = "0";
var R2 = "00";
var G2 = "00";
var B2 = "00";
var RGB2 = "0";
var NAME2 = "0";
var R3 = "00";
var G3 = "00";
var B3 = "00";
var RGB3 = "0";
var NAME3 = "0";
var R4 = "00";
var G4 = "00";
var B4 = "00";
var RGB4 = "0";
var NAME4 = "0";
var R5 = "00";
var G5 = "00";
var B5 = "00";
var RGB5 = "0";
var NAME5 = "0";

document.write ("<TABLE WIDTH=\"100%\" HEIGTH=\"100%\" BORDER=\"1\" CELLPADDING=\"0\" CELLSPACING=\"0\">");

upto = 0;

for (var i = 0; i < 28; i++)
{
	NAME1 = N[upto]
	upto++
	R1 = N[upto]
	upto++
	G1 = N[upto]
	upto++
	B1 = N[upto]
	upto++
	RGB1 = R1 + G1 + B1

	NAME2 = N[upto]
	upto++
	R2 = N[upto]
	upto++
	G2 = N[upto]
	upto++
	B2 = N[upto]
	upto++
	RGB2 = R2 + G2 + B2

	NAME3 = N[upto]
	upto++
	R3 = N[upto]
	upto++
	G3 = N[upto]
	upto++
	B3 = N[upto]
	upto++
	RGB3 = R3 + G3 + B3

	NAME4 = N[upto]
	upto++
	R4 = N[upto]
	upto++
	G4 = N[upto]
	upto++
	B4 = N[upto]
	upto++
	RGB4 = R4 + G4 + B4

	NAME5 = N[upto]
	upto++
	R5 = N[upto]
	upto++
	G5 = N[upto]
	upto++
	B5 = N[upto]
	upto++
	RGB5 = R5 + G5 + B5

document.write ("<TD WIDTH=\"16%\" COLSPAN=\"2\" VALIGN=\"top\" HEIGHT=\"15\" BGCOLOR=\"#FFFFFF\"><P CLASS=\"t\" ALIGN=\"center\">" + NAME1 + " #" + RGB1 +"</TD>");
document.write ("<TD WIDTH=\"16%\" COLSPAN=\"2\" VALIGN=\"top\" HEIGHT=\"15\" BGCOLOR=\"#FFFFFF\"><P CLASS=\"t\" ALIGN=\"center\">" + NAME2 + " #" + RGB2 +"</TD>");
document.write ("<TD WIDTH=\"16%\" COLSPAN=\"2\" VALIGN=\"top\" HEIGHT=\"15\" BGCOLOR=\"#FFFFFF\"><P CLASS=\"t\" ALIGN=\"center\">" + NAME3 + " #" + RGB3 +"</TD>");
document.write ("<TD WIDTH=\"16%\" COLSPAN=\"2\" VALIGN=\"top\" HEIGHT=\"15\" BGCOLOR=\"#FFFFFF\"><P CLASS=\"t\" ALIGN=\"center\">" + NAME4 + " #" + RGB4 +"</TD>");
document.write ("<TD WIDTH=\"16%\" COLSPAN=\"2\" VALIGN=\"top\" HEIGHT=\"15\" BGCOLOR=\"#FFFFFF\"><P CLASS=\"t\" ALIGN=\"center\">" + NAME5 + " #" + RGB5 +"</TD>");

document.write ("<TR>");

document.write ("<TD WIDTH=\"8%\" VALIGN=\"top\" HEIGHT=\"15\" BGCOLOR=\"" + NAME1 + "\">&nbsp;</TD>");
document.write ("<TD WIDTH=\"8%\" VALIGN=\"top\" HEIGHT=\"15\" BGCOLOR=\"#" + RGB1 + "\">&nbsp;</TD>");
document.write ("<TD WIDTH=\"8%\" VALIGN=\"top\" HEIGHT=\"15\" BGCOLOR=\"" + NAME2 + "\">&nbsp;</TD>");
document.write ("<TD WIDTH=\"8%\" VALIGN=\"top\" HEIGHT=\"15\" BGCOLOR=\"#" + RGB2 + "\">&nbsp;</TD>");
document.write ("<TD WIDTH=\"8%\" VALIGN=\"top\" HEIGHT=\"15\" BGCOLOR=\"" + NAME3 + "\">&nbsp;</TD>");
document.write ("<TD WIDTH=\"8%\" VALIGN=\"top\" HEIGHT=\"15\" BGCOLOR=\"#" + RGB3 + "\">&nbsp;</TD>");
document.write ("<TD WIDTH=\"8%\" VALIGN=\"top\" HEIGHT=\"15\" BGCOLOR=\"" + NAME4 + "\">&nbsp;</TD>");
document.write ("<TD WIDTH=\"8%\" VALIGN=\"top\" HEIGHT=\"15\" BGCOLOR=\"#" + RGB4 + "\">&nbsp;</TD>");
document.write ("<TD WIDTH=\"8%\" VALIGN=\"top\" HEIGHT=\"15\" BGCOLOR=\"" + NAME5 + "\">&nbsp;</TD>");
document.write ("<TD WIDTH=\"8%\" VALIGN=\"top\" HEIGHT=\"15\" BGCOLOR=\"#" + RGB5 + "\">&nbsp;</TD>");

document.write ("<TR>");
}

document.write ("</TABLE>");
