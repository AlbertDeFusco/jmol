// Written by  Angel Herr?ez

var tx, scr_sfill, scr_load
var qRangeIni, qRangeEnd

if (cfgUsed==0) 	//Wherland
{	scr_load = "load pertbl3d.pdb; "	//more later
	// a different pdb is needed because this has some ions that Jmol has not.
	scr_sfill = "spacefill temperature; hover %i %e, r =%b; "
	qRangeIni = -2
	qRangeEnd = 3
}
else if (cfgUsed==1) 	//Jmol 
{	scr_load = "load pertbl3d_j.pdb.gz; "	//more later
	scr_sfill = "spacefill ionic; hover %i %e; "
	qRangeIni = -4
	qRangeEnd = 7
}

var scr_reset = "reset; zoom 100; "
var scr_setEcho = "set echo bottom center; font echo 22 serif; color echo [255,0,255]; "

scr_load += "set perspectivedepth off; wireframe off; set labeloffset 0 1; " + scr_reset

var scr_vdw = "select all; label off; set echo off; restrict 13; color cpk; " + scr_sfill 
if (cfgUsed==1) scr_vdw += "spacefill; "	//supresses 'ionic', but not 'temperature'
scr_vdw += scr_setEcho + "echo " + tx_Vdw + "; "

function scr_charge(n)
{	var i = "select all; label off; set echo off; restrict " + eval(n+5) + "; color cpk; label %e; " + scr_sfill
	if (n==0) i += "label off; "
	if (n>0) n = "+" + n
	i += scr_setEcho + "echo charge " + n + "; "
	return i
}

var scr_per = new Array(9)
//scr_per[0] = "select *.H, *.He; "
scr_per[0] = "select elemno>=1 and elemno<=2; "
//scr_per[1] = "select *.Li, *.Be, *.B, *.C, *.N, *.O, *.F, *.Ne; "
scr_per[1] = "select elemno>=3 and elemno<=10; "
//scr_per[2] = "select *.Na, *.Mg, *.Al, *.Si, *.P, *.S, *.Cl, *.Ar; "
scr_per[2] = "select elemno>=11 and elemno<=18; "
//scr_per[3] = "select *.K, *.Ca, *.Sc, *.Ti, *.V, *.Cr, *.Mn, *.Fe, *.Co, *.Ni, *.Cu, *.Zn, *.Ga, *.Ge, *.As, *.Se, *.Br, *.Kr; "
scr_per[3] = "select elemno>=19 and elemno<=36; "
//scr_per[4] = "select *.Rb, *.Sr, elemno=39, *.Zr, *.Nb, *.Mo, *.Tc, *.Ru, *.Rh, *.Pd, *.Ag, *.Cd, *.In, *.Sn, *.Sb, *.Te, *.I, *.Xe; "
scr_per[4] = "select elemno>=37 and elemno<=54; "
//scr_per[5] = "select *.Cs, *.Ba, *.La, *.Hf, *.Ta, *.W, *.Re, *.Os, *.Ir, *.Pt, *.Au, *.Hg, *.Tl, *.Pb, *.Bi, *.Po, *.At, *.Rn; "
scr_per[5] = "select (elemno>=55 and elemno<=57) or (elemno>=72 and elemno<=86); "
//scr_per[6] = "select *.Fr, *.Ra, *.Ac, *.Rf, *.Db, *.Sg, *.Bh, *.Hs, *.Mt; "
scr_per[6] = "select (elemno>=87 and elemno<=89) or (elemno>=104 and elemno<=118); "
//scr_per[7] = "select *.Ce, *.Pr, *.Nd, *.Pm, *.Sm, *.Eu, *.Gd, *.Tb, *.Dy, *.Ho, *.Er, *.Tm, *.Yb, *.Lu; "
scr_per[7] = "select elemno>=58 and elemno<=71; "
//scr_per[8] = "select *.Th, *.Pa, *.U, *.Np, *.Pu, *.Am, *.Cm, *.Bk, *.Cf, *.Es, *.Fm, *.Md, *.No, *.Lr; "
scr_per[8] = "select elemno>=90 and elemno<=103; "

var scr_grp = new Array(8)
scr_grp[0] = "select *.H, *.Li, *.Na, *.K, *.Rb, *.Cs; "
scr_grp[1] = "select *.Be, *.Mg, *.Ca, *.Sr, *.Ba, *.Ra; "
scr_grp[2] = "select *.B, *.Al, *.Ga, *.In, *.Tl; "
scr_grp[3] = "select *.C, *.Si, *.Ge, *.Sn, *.Pb; "
scr_grp[4] = "select *.N, *.P, *.As, *.Sb, *.Bi; "
scr_grp[5] = "select *.O, *.S, *.Se, *.Te, *.Po; "
scr_grp[6] = "select *.F, *.Cl, *.Br, *.I; "
scr_grp[7] = "select *.He, *.Ne, *.Ar, *.Kr, *.Xe, *.Rn; "
/*	Shows only main groups 1 to 8 (p-block).
	Other choices would be:
	- "older IUPAC" / "European" convention: groups IA...VIIA, VIIIA(x3), IB...VIIIB
	- "American" convention: groups IA, IIA, IIIB...VIIB, VIIIB(x3), IB, IIB, IIIA...VIIIA
	- current IUPAC convention: groups 1...7, 8(x3), 9...18
*/


var scr_1per = "set echo off; "
var scr_2per = "define temp selected; select temp and 5; label %e; select temp; " + scr_setEcho

function showPer(n) 
{	tx = scr_1per + setResColors() + "spacefill 0; " + scr_per[n-1] + scr_sfill + scr_2per + "echo " + tx_PeriodName[n-1] 
	jmolScript(tx) 
}

function showGroup(n) 
{	tx = scr_1per + setResColors() + "spacefill 0; " + scr_grp[n-1] + scr_sfill + scr_2per + "echo " + tx_GroupName[n-1] 
	jmolScript(tx) 
}

function toggleBkgColor()
{	var c = document.body.style.backgroundColor
	var paleColorH = "#F8F8B8", paleColorD = "[248,248,184]"	//alternative to white for background
	if (c=="rgb(0, 0, 0)" || c=="#000000") 		//formats returned by Firefox and IE respectively
	{	document.body.style.backgroundColor=paleColorH;
		document.body.style.color="#000000";
		jmolScript("background " + paleColorD + "; ");
	}
	else 
	{	document.body.style.backgroundColor="#000000";
		document.body.style.color="#ffffff";
		jmolScript("background black; ");
	}
	document.getElementById("header").style.backgroundColor=document.body.style.backgroundColor;
}

function setBkgColor()
{	document.body.style.backgroundColor="#000000";
	document.body.style.color="#ffffff";
	document.getElementById("header").style.backgroundColor="#000000";
}

function hexColorToDecColor(x)
{	return "[" + parseInt(x.substring(0,2),16) + "," + parseInt(x.substring(2,4),16) + "," + parseInt(x.substring(4,6),16) + "]"
}

var resColor = new Array(2)
// Order is: charge -4  to 0 to +7, VdW
resColor[0] = ["cccccc", "cccccc", "FFFFFF", "00FF00", "00FFFF", "FFFF00", "FFA500", "FF0000", "cccccc", "cccccc", "cccccc", "cccccc", "A020F0"]	
/*colors used in Scot Wherland's "Chime Radii" page: white, green, cyan, yellow, orange, red, purple  */

resColor[1] = ["FF0000", "FF4040", "FF8080", "FFC0C0", "FFFFFF", "D8D8FF", "B4B4FF", "9090FF", "6C6CFF", "4848FF", "2424FF", "0000FF", "CFF740"]
/* Jmol's red to blue gradient as in 'color charge'; plus yellow-green for VdW  */

function buildChargeLegend()
{	var tx1 = ''
	for (i=qRangeIni; i<0; i++) { tx1 += '<span id="charge' + Math.abs(i) + 'n">' + i + '</span>, ' }
	tx1 += '<span id="charge0">' + tx_Uncharged + '</span>, '
	for (i=1; i<=qRangeEnd; i++) { tx1 += '<span id="charge' + i + 'p">+' + i + '</span>, ' }
	tx1 += '<span id="vdw">' + tx_Vdw + '</span>'
	wr(tx1)
}

function allRadii()
{	jmolScript( setResColors() + scr_sfill )
}

function setResColors()
{	var scr_resColors = "label off; set echo off; "
	for (i=qRangeIni+4; i<=qRangeEnd+4; i++)
	{	scr_resColors += "select " + (i+1) + "; color " + hexColorToDecColor( resColor[cfgUsed][i] ) + "; "
	}
	scr_resColors += "select " + (12+1) + "; color " + hexColorToDecColor( resColor[cfgUsed][12] ) + "; "  //VdW
	scr_resColors += "select all; "
	return scr_resColors
}

function setResColorStyles()
{	var resColorStyle = ["charge4n", "charge3n", "charge2n", "charge1n", "charge0", "charge1p", "charge2p", "charge3p", "charge4p", "charge5p", "charge6p", "charge7p", "vdw"]
	for (i=qRangeIni+4; i<=qRangeEnd+4; i++)
	{	document.getElementById(resColorStyle[i]).style.color = "#"+resColor[cfgUsed][i]
	}
	document.getElementById(resColorStyle[12]).style.color = "#"+resColor[cfgUsed][12]	//VdW
}


function reApplyLabels(theForm)
{	// due to weird behaviour, now it is really "setLabelsToggleOff"
	var ctl = theForm.labelToggle;
/* Ideal function:
	alert("re-applying labels"); //any alert here makes the function work; otherwise, it does not ??!!
	ctl.click();ctl.click();
One that works:
*/
	toggleLabelsCtl(0,theForm)
}
function toggleLabelsCtl(c,theForm)
{	if ( c=="off" || c=="false" || c==0 || !c ) theForm.labelToggle.checked=false
	else theForm.labelToggle.checked=true
}

/* Abundance of elements in human body, % dry weight
	Taken from Will McClure, www.bio.cmu.edu/Courses/BiochemMols/Periodic//ELEMMain.htm
*/
var scr_Abund = "select all; spacefill off; color cpk; label off; set echo off; "
+ "define abund atomno=1 or (atomno>=6 and atomno<=8) or atomno=11 or atomno=12 or (atomno>=15 and atomno<=17) or atomno=19 or atomno=20 or (atomno>=25 and atomno<=30); "
+ "restrict abund and 5; label %a; "
+ "select atomno=1;spacefill 247; "
+ "select atomno=6;spacefill 745; "
+ "select atomno=7;spacefill 316; "
+ "select atomno=8;spacefill 402; "
+ "select atomno=11;spacefill 77; "
+ "select atomno=12;spacefill 55; "
+ "select atomno=15;spacefill 173; "
+ "select atomno=16;spacefill 100; "
+ "select atomno=17;spacefill 77; "
+ "select atomno=19;spacefill 110; "
+ "select atomno=20;spacefill 212; "
+ "select (atomno>=25) and (atomno<=30);spacefill 30; "
+ "restrict 5; " + scr_setEcho + "echo " + tx_6a + "; "

var scr_pattIon = scr_reset + "set echo off; select all; label off; rotate x 90; restrict not 13; spacefill ionic; define temp selected; select [LBL]90; label %a; set labeloffset 1 0; select temp; "

var elemsNotInPdb = [2,10,22,32,41,43,50,61,66,68,69,83,84,85,86,87,88,89,90,93,94,95,97,99]	//from last to the end
/* Source: http://www.imb-jena.de/ImgLibPDB/pages/hetDir/PSE2HET.shtml   Last accessed: 7 sep 05 */
var scr_inPdb = "restrict not (" 
for (i=0; i<elemsNotInPdb.length-1 ; i++)
{	scr_inPdb += "elemno=" + elemsNotInPdb[i] + " , "
}
scr_inPdb += "elemno>=" + elemsNotInPdb[elemsNotInPdb.length-1] + "); "
scr_inPdb += scr_setEcho + "echo " + tx_13a + "; "

// Colors in these scripts must match styles ".colorMetal#":
var scr_colorMetal = "define temp selected"
+ "; select *T.* or *I.* or *M.*; color " + hexColorToDecColor('6699CC')
+ "; select *N.*; color " + hexColorToDecColor('FFCC00')
+ "; select *S.*; color " + hexColorToDecColor('33CC00')
+ "; select temp; "
var scr_colorMetal2 = "define temp selected"
+ "; select *M.*; color " + hexColorToDecColor('8899AA')
+ "; select *T.*; color " + hexColorToDecColor('99FFFF')
+ "; select *N.*; color " + hexColorToDecColor('FFCC00')
+ "; select *S.*; color " + hexColorToDecColor('33CC00')
+ "; select *I.*; color " + hexColorToDecColor('FF6666')
+ "; select temp; "
var scr_colorCPK = "define temp selected; select all; color cpk; select temp; "

function wr(tx) { document.write(tx) }
