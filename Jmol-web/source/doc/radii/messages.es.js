// Written by  Angel Herr�ez
var tx_Reload =		"Cargar de nuevo"
var tx_ResetOr =	"Restaurar orientaci�n"
var tx_Bkg =		"Fondo oscuro / claro"
var tx_Labels =		"Mostrar s�mbolos at�micos"
var tx_Vdw =		"Van der Waals"
var tx_AtR =		"at�micos (sin carga)"
var tx_SglR =		"Un solo radio"
var tx_AllR =		"Todos los radios"
var tx_Uncharged = 	"sin carga"
var tx_Choose =		"elige"
var tx_PeriodName =	["Periodo 1", "Periodo 2", "Periodo 3", "Periodo 4", "Periodo 5", "Periodo 6", "Periodo 7", "Lant�nidos", "Act�nidos"]
var tx_GroupName =	["Grupo I", "Grupo II", "Grupo III", "Grupo IV", "Grupo V", "Grupo VI", "Grupo VII", "Grupo VIII"]
var tx_Metal =		["metal", "no metal", "metaloide", "metal del grupo principal", "metal de transici�n", "no metal del grupo principal", "metaloide", "metal de transici�n interno"]
var tx_ColorAs = 	"Colorear como"
var tx_ColorAsCPK = "Colores predeterminados CPK"

var tx_2 =	"I�nico con carga:"
var tx_3 =	"(gira el modelo usando el rat�n)"
var tx_4 =	"s�lo para los elementos del periodo"
var tx_5 =	"s�lo para los elementos del grupo"
var tx_6 =	"Mostrar los �tomos con tama�o proporcional a su abundancia relativa en los humanos (peso seco)."
var tx_6a =	"abundancia"
var tx_7a =	"para"
var tx_7b =	"todos los elementos"
var tx_9 =	"Reconocimientos"
var tx_10 =	"Patrones de coloreado"
var tx_11 =	"Vistas peculiares e instructivas"
var tx_12 =	"Patr�n de ionizaci�n a lo largo de los grupos."
var tx_13 =	"S�lo elementos incluidos en PDB o NDB."
var tx_13a = "elementos en PDB o NDB"

if (cfgUsed==0) 
{	var tx_0 =	"Tabla peri�dica de radios en 3D"
	var tx_1 =	"Inicialmente se muestran radios de Van der Waals y colores predeterminados (CPK)"
	var tx_8 =	"Esta p�gina usa los valores de radios y colores de Wherland"
}
else if (cfgUsed==1) 
{	var tx_0 =	"Radios at�micos e i�nicos en Jmol"
	var tx_1 =	"Inicialmente se muestran radios de Van der Waals y colores predeterminados de Jmol"
	var tx_8 =	"Esta p�gina usa los valores de radios y colores de Jmol"
}

/* Los acentos en botones funcionan bien --sin necesidad de funci�n acentos()-- en 
	- Win2000 (Firefox 1.0.4, MSIE 6.0, etc.)
	- Linux, kernel 2.6.10, Molinux 1.2 (Firefox 1.0.2)
	- Mac (OSX; Safari 2.0.1, Mozilla 1.7.7)  --simulados en http://www2.uah.es/biomodel/jmol/radii/beta1/jmol_pertable.es.html

 La p�gina no sale en IE 5.2.3 MacOSX

*/

function acentos(x) {
	// version 040622
	// Spanish - Espa�ol
	// Portuguese - Portugu�s - Portugu�s
	if (navigator.appVersion.toLowerCase().indexOf("windows") != -1) {return x}
	x = x.replace(/�/g,"\xE1")
	x = x.replace(/�/g,"\xC1")
	x = x.replace(/�/g,"\xE3")
	x = x.replace(/�/g,"\xC3")
	x = x.replace(/�/g,"\xE7")
	x = x.replace(/�/g,"\xC7")
	x = x.replace(/�/g,"\xE9")
	x = x.replace(/�/g,"\xC9")
	x = x.replace(/�/g,"\xEA")
	x = x.replace(/�/g,"\xCA")
	x = x.replace(/�/g,"\xED")
	x = x.replace(/�/g,"\xCD")
	x = x.replace(/�/g,"\xF3")
	x = x.replace(/�/g,"\xD3")
	x = x.replace(/�/g,"\xF5")
	x = x.replace(/�/g,"\xD5")
	x = x.replace(/�/g,"\xFA")
	x = x.replace(/�/g,"\xDA")
	x = x.replace(/�/g,"\xF1")
	x = x.replace(/�/g,"\xD1")
	x = x.replace(/�/g,"\xBF")
	x = x.replace(/�/g,"\xA1")
	
	
	return x
}

