// Written by  Angel Herráez
var tx_Reload =		"Cargar de nuevo"
var tx_ResetOr =	"Restaurar orientación"
var tx_Bkg =		"Fondo oscuro / claro"
var tx_Labels =		"Mostrar símbolos atómicos"
var tx_Vdw =		"Van der Waals"
var tx_AtR =		"atómicos (sin carga)"
var tx_SglR =		"Un solo radio"
var tx_AllR =		"Todos los radios"
var tx_Uncharged = 	"sin carga"
var tx_Choose =		"elige"
var tx_PeriodName =	["Periodo 1", "Periodo 2", "Periodo 3", "Periodo 4", "Periodo 5", "Periodo 6", "Periodo 7", "Lantánidos", "Actínidos"]
var tx_GroupName =	["Grupo I", "Grupo II", "Grupo III", "Grupo IV", "Grupo V", "Grupo VI", "Grupo VII", "Grupo VIII"]
var tx_Metal =		["metal", "no metal", "metaloide", "metal del grupo principal", "metal de transición", "no metal del grupo principal", "metaloide", "metal de transición interno"]
var tx_ColorAs = 	"Colorear como"
var tx_ColorAsCPK = "Colores predeterminados CPK"

var tx_2 =	"Iónico con carga:"
var tx_3 =	"(gira el modelo usando el ratón)"
var tx_4 =	"sólo para los elementos del periodo"
var tx_5 =	"sólo para los elementos del grupo"
var tx_6 =	"Mostrar los átomos con tamaño proporcional a su abundancia relativa en los humanos (peso seco)."
var tx_6a =	"abundancia"
var tx_7a =	"para"
var tx_7b =	"todos los elementos"
var tx_9 =	"Reconocimientos"
var tx_10 =	"Patrones de coloreado"
var tx_11 =	"Vistas peculiares e instructivas"
var tx_12 =	"Patrón de ionización a lo largo de los grupos."
var tx_13 =	"Sólo elementos incluidos en PDB o NDB."
var tx_13a = "elementos en PDB o NDB"

if (cfgUsed==0) 
{	var tx_0 =	"Tabla periódica de radios en 3D"
	var tx_1 =	"Inicialmente se muestran radios de Van der Waals y colores predeterminados (CPK)"
	var tx_8 =	"Esta página usa los valores de radios y colores de Wherland"
}
else if (cfgUsed==1) 
{	var tx_0 =	"Radios atómicos e iónicos en Jmol"
	var tx_1 =	"Inicialmente se muestran radios de Van der Waals y colores predeterminados de Jmol"
	var tx_8 =	"Esta página usa los valores de radios y colores de Jmol"
}

/* Los acentos en botones funcionan bien --sin necesidad de función acentos()-- en 
	- Win2000 (Firefox 1.0.4, MSIE 6.0, etc.)
	- Linux, kernel 2.6.10, Molinux 1.2 (Firefox 1.0.2)
	- Mac (OSX; Safari 2.0.1, Mozilla 1.7.7)  --simulados en http://www2.uah.es/biomodel/jmol/radii/beta1/jmol_pertable.es.html

 La página no sale en IE 5.2.3 MacOSX

*/

function acentos(x) {
	// version 040622
	// Spanish - Español
	// Portuguese - Portugués - Português
	if (navigator.appVersion.toLowerCase().indexOf("windows") != -1) {return x}
	x = x.replace(/á/g,"\xE1")
	x = x.replace(/Á/g,"\xC1")
	x = x.replace(/ã/g,"\xE3")
	x = x.replace(/Ã/g,"\xC3")
	x = x.replace(/ç/g,"\xE7")
	x = x.replace(/Ç/g,"\xC7")
	x = x.replace(/é/g,"\xE9")
	x = x.replace(/É/g,"\xC9")
	x = x.replace(/ê/g,"\xEA")
	x = x.replace(/Ê/g,"\xCA")
	x = x.replace(/í/g,"\xED")
	x = x.replace(/Í/g,"\xCD")
	x = x.replace(/ó/g,"\xF3")
	x = x.replace(/Ó/g,"\xD3")
	x = x.replace(/õ/g,"\xF5")
	x = x.replace(/Õ/g,"\xD5")
	x = x.replace(/ú/g,"\xFA")
	x = x.replace(/Ú/g,"\xDA")
	x = x.replace(/ñ/g,"\xF1")
	x = x.replace(/Ñ/g,"\xD1")
	x = x.replace(/¿/g,"\xBF")
	x = x.replace(/¡/g,"\xA1")
	
	
	return x
}

