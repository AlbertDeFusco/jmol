/* Element parameters used by Jmol - mainly colors
	as defined in   Jmol-HEAD/src/org/jmol/viewer/JmolConstants.java
	( rev.14469, Jmol 12.1.15 )
	Support file for building color tables in jscolors/index.html (Jmol's website)
	-- Do not translate anything in this file --
	Angel Herráez, 29 july 2005
	Contributions by Nicolas Vervelle
	Updated: 11 august 2005
	Updated: 21 april 2007 - A.H. - Isotopes
	Last update: 16 oct. 2010 - A.H. - new DSSP secondary structure types
*/

var elemParams = new Array(110)
for (i=0;i<elemParams.length;i++) { elemParams[i] = new Array(3) }
/*	first is element symbol,
	 second is Jmol's CPK color, third is Rasmol's (2.6 to 2.7.3, should be Chime too)
*/
elemParams[1]  = ['H','FFFFFF','FFFFFF']
elemParams[2]  = ['He','D9FFFF','FFC0CB']
elemParams[3]  = ['Li','CC80FF','B22222']
elemParams[4]  = ['Be','C2FF00','FF1493']
elemParams[5]  = ['B','FFB5B5','00FF00']
elemParams[6]  = ['C','909090','C8C8C8']
elemParams[7]  = ['N','3050F8','8F8FFF']
elemParams[8]  = ['O','FF0D0D','F00000']
elemParams[9]  = ['F','90E050','DAA520']
elemParams[10] = ['Ne','B3E3F5','FF1493']
elemParams[11] = ['Na','AB5CF2','0000FF']
elemParams[12] = ['Mg','8AFF00','228B22']
elemParams[13] = ['Al','BFA6A6','808090']
elemParams[14] = ['Si','F0C8A0','DAA520']
elemParams[15] = ['P','FF8000','FFA500']
elemParams[16] = ['S','FFFF30','FFC832']
elemParams[17] = ['Cl','1FF01F','00FF00']
elemParams[18] = ['Ar','80D1E3','FF1493']
elemParams[19] = ['K','8F40D4','FF1493']
elemParams[20] = ['Ca','3DFF00','808090']
elemParams[21] = ['Sc','E6E6E6','FF1493']
elemParams[22] = ['Ti','BFC2C7','808090']
elemParams[23] = ['V','A6A6AB','FF1493']
elemParams[24] = ['Cr','8A99C7','808090']
elemParams[25] = ['Mn','9C7AC7','808090']
elemParams[26] = ['Fe','E06633','FFA500']
elemParams[27] = ['Co','F090A0','FF1493']
elemParams[28] = ['Ni','50D050','A52A2A']
elemParams[29] = ['Cu','C88033','A52A2A']
elemParams[30] = ['Zn','7D80B0','A52A2A']
elemParams[31] = ['Ga','C28F8F','FF1493']
elemParams[32] = ['Ge','668F8F','FF1493']
elemParams[33] = ['As','BD80E3','FF1493']
elemParams[34] = ['Se','FFA100','FF1493']
elemParams[35] = ['Br','A62929','A52A2A']
elemParams[36] = ['Kr','5CB8D1','FF1493']
elemParams[37] = ['Rb','702EB0','FF1493']
elemParams[38] = ['Sr','00FF00','FF1493']
elemParams[39] = ['Y','94FFFF','FF1493']
elemParams[40] = ['Zr','94E0E0','FF1493']
elemParams[41] = ['Nb','73C2C9','FF1493']
elemParams[42] = ['Mo','54B5B5','FF1493']
elemParams[43] = ['Tc','3B9E9E','FF1493']
elemParams[44] = ['Ru','248F8F','FF1493']
elemParams[45] = ['Rh','0A7D8C','FF1493']
elemParams[46] = ['Pd','006985','FF1493']
elemParams[47] = ['Ag','C0C0C0','808090']
elemParams[48] = ['Cd','FFD98F','FF1493']
elemParams[49] = ['In','A67573','FF1493']
elemParams[50] = ['Sn','668080','FF1493']
elemParams[51] = ['Sb','9E63B5','FF1493']
elemParams[52] = ['Te','D47A00','FF1493']
elemParams[53] = ['I','940094','A020F0']
elemParams[54] = ['Xe','429EB0','FF1493']
elemParams[55] = ['Cs','57178F','FF1493']
elemParams[56] = ['Ba','00C900','FFA500']
elemParams[57] = ['La','70D4FF','FF1493']
elemParams[58] = ['Ce','FFFFC7','FF1493']
elemParams[59] = ['Pr','D9FFC7','FF1493']
elemParams[60] = ['Nd','C7FFC7','FF1493']
elemParams[61] = ['Pm','A3FFC7','FF1493']
elemParams[62] = ['Sm','8FFFC7','FF1493']
elemParams[63] = ['Eu','61FFC7','FF1493']
elemParams[64] = ['Gd','45FFC7','FF1493']
elemParams[65] = ['Tb','30FFC7','FF1493']
elemParams[66] = ['Dy','1FFFC7','FF1493']
elemParams[67] = ['Ho','00FF9C','FF1493']
elemParams[68] = ['Er','00E675','FF1493']
elemParams[69] = ['Tm','00D452','FF1493']
elemParams[70] = ['Yb','00BF38','FF1493']
elemParams[71] = ['Lu','00AB24','FF1493']
elemParams[72] = ['Hf','4DC2FF','FF1493']
elemParams[73] = ['Ta','4DA6FF','FF1493']
elemParams[74] = ['W','2194D6','FF1493']
elemParams[75] = ['Re','267DAB','FF1493']
elemParams[76] = ['Os','266696','FF1493']
elemParams[77] = ['Ir','175487','FF1493']
elemParams[78] = ['Pt','D0D0E0','FF1493']
elemParams[79] = ['Au','FFD123','DAA520']
elemParams[80] = ['Hg','B8B8D0','FF1493']
elemParams[81] = ['Tl','A6544D','FF1493']
elemParams[82] = ['Pb','575961','FF1493']
elemParams[83] = ['Bi','9E4FB5','FF1493']
elemParams[84] = ['Po','AB5C00','FF1493']
elemParams[85] = ['At','754F45','FF1493']
elemParams[86] = ['Rn','428296','FF1493']
elemParams[87] = ['Fr','420066','FF1493']
elemParams[88] = ['Ra','007D00','FF1493']
elemParams[89] = ['Ac','70ABFA','FF1493']
elemParams[90] = ['Th','00BAFF','FF1493']
elemParams[91] = ['Pa','00A1FF','FF1493']
elemParams[92] = ['U','008FFF','FF1493']
elemParams[93] = ['Np','0080FF','FF1493']
elemParams[94] = ['Pu','006BFF','FF1493']
elemParams[95] = ['Am','545CF2','FF1493']
elemParams[96] = ['Cm','785CE3','FF1493']
elemParams[97] = ['Bk','8A4FE3','FF1493']
elemParams[98] = ['Cf','A136D4','FF1493']
elemParams[99] = ['Es','B31FD4','FF1493']
elemParams[100] = ['Fm','B31FBA','FF1493']
elemParams[101] = ['Md','B30DA6','FF1493']
elemParams[102] = ['No','BD0D87','FF1493']
elemParams[103] = ['Lr','C70066','FF1493']
elemParams[104] = ['Rf','CC0059','FF1493']
elemParams[105] = ['Db','D1004F','FF1493']
elemParams[106] = ['Sg','D90045','FF1493']
elemParams[107] = ['Bh','E00038','FF1493']
elemParams[108] = ['Hs','E6002E','FF1493']
elemParams[109] = ['Mt','EB0026','FF1493']

var isotParams = new Array(5)
for (i=0;i<isotParams.length;i++) { isotParams[i] = new Array(2) }
/*	first is element symbol,
	 second is Jmol's CPK color
	 third is atomic nr.
*/
isotParams[1]  = ['D,<sup>2</sup>H','FFFFC0', 1]
isotParams[2]  = ['T,<sup>3</sup>H','FFFFA0', 1]
isotParams[3]  = ['<sup>13</sup>C','505050', 6]
isotParams[4]  = ['<sup>14</sup>C','404040', 6]
isotParams[5]  = ['<sup>15</sup>N','105050', 7]


/*	CPKnew from Rasmol2.7.3
	documented in http://openrasmol.org/doc/rasmol.html#cpkcolours
*/
var elemCPKnew = new Array(elemParams.length)
for ( i=0;i<elemParams.length;i++ )
{ 
	if (elemParams[i][2]=='C8C8C8') elemCPKnew[i]='D3D3D3'
	if (elemParams[i][2]=='8F8FFF') elemCPKnew[i]='87CEE6'
	if (elemParams[i][2]=='F00000') elemCPKnew[i]='FF0000'
	if (elemParams[i][2]=='FFC832') elemCPKnew[i]='FFFF00'
	if (elemParams[i][2]=='FFA500') elemCPKnew[i]='FFAA00'
	if (elemParams[i][2]=='808090') elemCPKnew[i]='696969'
	if (elemParams[i][2]=='A52A2A') elemCPKnew[i]='802828'
	if (elemParams[i][2]=='B22222') elemCPKnew[i]='B22121'
	/* omitted for clarity in the table
	if (elemParams[i][2]=='FF1493') elemCPKnew[i]='FA1691'
	*/
}


function hexColorToDecColor(x)
{	return "[" + parseInt(x.substring(0,2),16) + "," + parseInt(x.substring(2,4),16) + "," + parseInt(x.substring(4,6),16) + "]"
}


var structProtParams = ['FF0080', 'A00080', '600080', 'FFC800', '6080FF', 'FFFFFF']
// alphaHelix, 3(10)helix, piHelix, sheet, turn, other

var structNucParams = ['AE00FE', 'FD0162']
// DNA, RNA

var structOtherParams = ['A6A6FA', '808080']
// carbohydrate, other


var aaParams = new Array(23)
for (i=0;i<aaParams.length;i++) { aaParams[i] = new Array(3) }
/*	Protein coloring patterns.
	First is amino acid symbol,	second is "amino" color pattern, third is "shapely" color pattern
*/
aaParams[0] = ['Ala','C8C8C8','8CFF8C']
aaParams[1] = ['Arg','145AFF','00007C']
aaParams[2] = ['Asn','00DCDC','FF7C70']
aaParams[3] = ['Asp','E60A0A','A00042']
aaParams[4] = ['Cys','E6E600','FFFF70']
aaParams[5] = ['Gln','00DCDC','FF4C4C']
aaParams[6] = ['Glu','E60A0A','660000']
aaParams[7] = ['Gly','EBEBEB','FFFFFF']
aaParams[8] = ['His','8282D2','7070FF']
aaParams[9] = ['Ile','0F820F','004C00']
aaParams[10] = ['Leu','0F820F','455E45']
aaParams[11] = ['Lys','145AFF','4747B8']
aaParams[12] = ['Met','E6E600','B8A042']
aaParams[13] = ['Phe','3232AA','534C52']
aaParams[14] = ['Pro','DC9682','525252']
aaParams[15] = ['Ser','FA9600','FF7042']
aaParams[16] = ['Thr','FA9600','B84C00']
aaParams[17] = ['Trp','B45AB4','4F4600']
aaParams[18] = ['Tyr','3232AA','8C704C']
aaParams[19] = ['Val','0F820F','FF8CFF']
aaParams[20] = ['Asx','FF69B4','FF00FF']
aaParams[21] = ['Glx','FF69B4','FF00FF']
aaParams[22] = ['other','BEA06E','FF00FF']

function assignLastAa(t)
{	// allows other language word for last aa (instead of 'other')
	if (t) aaParams[aaParams.length-1][0] = t
}

var ntParams = new Array(6)
for (i=0;i<ntParams.length;i++) { ntParams[i] = new Array(2) }
/*	Nucleic acid coloring patterns.
	First is nucleotide (or base) symbol, second is "shapely" color pattern
	"Amino" color pattern does not recognize nucleic acids, renders them as "other" = [190,160,110]	= BEA06E
*/
ntParams[0] = ['A','A0A0FF']
ntParams[1] = ['G','FF7070']
ntParams[2] = ['I','80FFFF']
ntParams[3] = ['C','FF8C4B']
ntParams[4] = ['T','A0FFA0']
ntParams[5] = ['U','FF8080']


var chainParams = new Array(27)
for (i=0;i<chainParams.length;i++) { chainParams[i] = new Array(3) }
/*	Default chain coloring pattern
	First is chain ID, second is color for "atom"s, third for "hetero"s
*/
chainParams[0]  = ['A, a','C0D0FF','90A0CF']
chainParams[1]  = ['B, b','B0FFB0','80CF98']
chainParams[2]  = ['C, c','FFC0C8','CF90B0']
chainParams[3]  = ['D, d','FFFF80','CFCF70']
chainParams[4]  = ['E, e','FFC0FF','CF90CF']
chainParams[5]  = ['F, f','B0F0F0','80C0C0']
chainParams[6]  = ['G, g','FFD070','CFA060']
chainParams[7]  = ['H, h','F08080','C05070']
chainParams[8]  = ['I, h','F5DEB3','C5AE83']
chainParams[9]  = ['J, j','00BFFF','00A7CF']
chainParams[10] = ['K, k','CD5C5C','B54C4C']
chainParams[11] = ['L, l','66CDAA','56B592']
chainParams[12] = ['M, m','9ACD32','8AB52A']
chainParams[13] = ['N, n','EE82EE','BE72BE']
chainParams[14] = ['O, o','00CED1','00B6A1']
chainParams[15] = ['P, p, 0','00FF7F','00CF6F']
chainParams[16] = ['Q, q, 1','3CB371','349B61']
chainParams[17] = ['R, r, 2','00008B','0000BB']
chainParams[18] = ['S, s, 3','BDB76B','A59F5B']
chainParams[19] = ['T, t, 4','006400','009400']
chainParams[20] = ['U, u, 5','800000','B00000']
chainParams[21] = ['V, v, 6','808000','B0B000']
chainParams[22] = ['W, w, 7','800080','B000B0']
chainParams[23] = ['X, x, 8','008080','00B0B0']
chainParams[24] = ['Y, y, 9','B8860B','E8B613']
chainParams[25] = ['Z, z','B22222','C23232']
chainParams[26] = ['none','FFFFFF','FFFFFF']

function assignLastChain(t)
{	// allows other language word for last chain description (instead of 'none')
	if (t) chainParams[chainParams.length-1][0] = t
}

var chargeParams = new Array(12)
for (i=0;i<chargeParams.length;i++) { chargeParams[i] = new Array(2) }
//	First is charge, second is color
chargeParams[0] = ['-4','FF0000']
chargeParams[1] = ['-3','FF4040']
chargeParams[2] = ['-2','FF8080']
chargeParams[3] = ['-1','FFC0C0']
chargeParams[4] = ['0','FFFFFF']
chargeParams[5] = ['1','D8D8FF']
chargeParams[6] = ['2','B4B4FF']
chargeParams[7] = ['3','9090FF']
chargeParams[8] = ['4','6C6CFF']
chargeParams[9] = ['5','4848FF']
chargeParams[10] = ['6','2424FF']
chargeParams[11] = ['7','0000FF']


var hBondsParams = ['FFFFFF','FF00FF','FF0000','FFA500','00FFFF','00FF00','FFFF00','FF8080']
//	Explained at http://openrasmol.org/doc/rasmol.html#hbondtypecolours


var rwbParams = ['FF0000','FF1010','FF2020','FF3030','FF4040','FF5050','FF6060','FF7070','FF8080','FF9090','FFA0A0','FFB0B0','FFC0C0','FFD0D0','FFE0E0','FFFFFF','E0E0FF','D0D0FF','C0C0FF','B0B0FF','A0A0FF','9090FF','8080FF','7070FF','6060FF','5050FF','4040FF','3030FF','2020FF','1010FF','0000FF']

var rainbowParams = ['0000FF','0020FF','0040FF','0060FF','0080FF','00A0FF','00C0FF','00E0FF','00FFFF','00FFE0','00FFC0','00FFA0','00FF80','00FF60','00FF40','00FF20','00FF00','20FF00','40FF00','60FF00','80FF00','A0FF00','C0FF00','E0FF00','FFFF00','FFE000','FFC000','FFA000','FF8000','FF6000','FF4000','FF2000','FF0000']

var isoSurfParams = ['5020A0','7040C0','9060E0','B080FF','A02050','C04070','E06090','FF80B0']

