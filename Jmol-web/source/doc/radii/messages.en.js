// Written by  Angel Herráez
var tx_Reload =		"Reload"
var tx_ResetOr =	"Reset orientation"
var tx_Bkg =		"Dark / Light background"
var tx_Labels =		"Show element symbols"
var tx_Vdw =		"Van der Waals"
var tx_AtR =		"Atomic (uncharged)"
var tx_SglR =		"Single radius"
var tx_AllR =		"All radii"
var tx_Uncharged = 	"uncharged"
var tx_Choose =		"choose"
var tx_PeriodName =	["Period 1", "Period 2", "Period 3", "Period 4", "Period 5", "Period 6", "Period 7", "Lanthanides", "Actinides"]
var tx_GroupName =	["Group I", "Group II", "Group III", "Group IV", "Group V", "Group VI", "Group VII", "Group VIII"]
var tx_Metal =		["metal", "nonmetal", "metalloid", "main group metal", "transition metal", "main group nonmetal", "metalloid", "inner transition metal"]
var tx_ColorAs = 	"Color as"
var tx_ColorAsCPK = "Color as default CPK"

var tx_2 =	"Ionic with charge"
var tx_3 =	"(rotate the model using the mouse)"
var tx_4 =	"only for elements in period"
var tx_5 =	"only for elements in group"
var tx_6 =	"Display atom size proportional to relative abundance in humans (dry weight)."
var tx_6a =	"Abundance"
var tx_7a =	"for"
var tx_7b =	"all elements"
var tx_9 =	"Acknowledgements"
var tx_10 =	"Coloring schemes"
var tx_11 =	"Singular, instructive views"
var tx_12 =	"Pattern of ionization along the groups."
var tx_13 =	"Only elements included in PDB or NDB."
var tx_13a = "Elements in PDB or NDB"

if (cfgUsed==0) 
{	var tx_0 =	"3D Periodic Table of Radii"
	var tx_1 =	"Initially shown: Van der Waals radius and default color scheme (CPK)"
	var tx_8 =	"This page uses Wherland's values of radii and colors"
}
else if (cfgUsed==1) 
{	var tx_0 =	"Atomic and ionic radii in Jmol"
	var tx_1 =	"Initially shown: Jmol's default Van der Waals radius and color scheme"
	var tx_8 =	"This page uses Jmol's values of radii and colors"
}
