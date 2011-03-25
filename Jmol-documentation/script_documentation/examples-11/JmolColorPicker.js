/* Jmol Simple JavaScript Color Picker
 by Jonathan Gutow
December 28, 2009

requires
   JmolColorPicker.css
   Jmol.js
*/

//globals and their defaults

var JmolColorPickerStatus = {
    lastPicked: '', //last picked color...not used at present
    funcName: '', //where to pass to next after pickedColor()
    passThrough: '', //name of the global variable or structure containing information to be passed
    }

var JmolColorPickerBoxes=new Array();//array of boxInfo

function boxInfo(boxID, appletID, scriptStr){//used when using a predefined colorPickerBox
    this.boxID=boxID;
    this.appletID=appletID; //applet ID
    this.scriptStr=scriptStr; //script with $COLOR$ where the color should be placed.
    }

//Jmol set up functions to allow local testing easily
function addJavaScript(path, file) {
 document.write("<"+"script src=\"" + path + "/" + file + "\" type=\"text/javascript\"><" + "/script>"); 
}

function changePathIfLocal(){
 var protocol = window.location.protocol.toLowerCase();
  if (protocol == "file:") { 
    jmoljarpath = jmollocaljarpath;
    jmoljspath = jmollocaljspath;
  }
}


//Build the ColorPicker Div.
function makeColorPicker(){
    var pi = Math.PI;
    var red = 255;
    var green = 255;
    var blue = 255;
    colorPickerDiv = document.getElementById("colorPickerDiv");
    if(! colorPickerDiv){
        colorPickerDiv = document.createElement("div");
        colorPickerDiv.setAttribute("id", "colorPickerDiv");
        colorPickerDiv.setAttribute("class","hidden");
        }
var rgbs=[[255,0,0]
,[255,128,0]
,[255,255,0]
,[128,255,0]
,[0,255,0]
,[0,255,128]
,[0,255,255]
,[0,128,255]
,[0,0,255]
,[128,0,255]
,[255,0,255]
,[255,255,255]
]
var hues=[100,90,80,70,60,50,40]

    htmlStr = '<table cellspacing="0" cellpadding="0" border="0"><tbody>';
    for (j = 0; j <= hues.length;j++){
	htmlStr += '<tr>'
	var f = hues[j]/100.0;
        for (k = 0; k < rgbs.length; k++){
		r = Math.round(rgbs[k][0] * f) 
		g = Math.round(rgbs[k][1] * f) 
		b = Math.round(rgbs[k][2] * f) 
           htmlStr +='<td style="background-color: rgb(' + r + "," + g + "," + b + ');">';
           htmlStr +='<div style="width: 6px; height: 6px;" onclick=\'pickedColor("rgb('+r+','+g+','+b+')");\'></div>';
           htmlStr +='</td>';
        }//for k
	htmlStr +='</tr>';
    }//for j
    htmlStr += '</tbody></table>'; 
    test = htmlStr.replace(/\</g,"&lt;");
    document.getElementById("testdiv").innerHTML = test
    content = document.createTextNode("loading color picker...");
    colorPickerDiv.appendChild(content);
    colorPickerDiv.innerHTML = htmlStr;
    return(colorPickerDiv);   
}

function pickedColor(colorStr){
    document.getElementById('colorPickerDiv').setAttribute("class", "hidden");
    var evalStr = ''+ JmolColorPickerStatus.funcName+'("'+colorStr+'",'+ JmolColorPickerStatus.passThrough+');';
    eval(evalStr);
}

function popUpPicker(whereID, funcName, passThrough){
    var pickerDiv = document.getElementById("colorPickerDiv");
    if (!pickerDiv) {//make a new picker
        colorPickerDiv =  makeColorPicker();
        document.body.appendChild(colorPickerDiv);
        pickerDiv = document.getElementById("colorPickerDiv");
        }
    JmolColorPickerStatus.funcName = funcName;
    JmolColorPickerStatus.passThrough = passThrough;
    var where = document.getElementById(whereID);
    where.appendChild(pickerDiv);
    pickerDiv.setAttribute("class","colorPicker_vis");
    }

function JmolColorPickerBox(boxID, presentColor, appletID, scriptStr){
    var boxNum = JmolColorPickerBoxes.length;
    JmolColorPickerBoxes[boxNum]= new boxInfo(boxID, appletID, scriptStr);  
    var boxDiv = document.createElement("div");
    boxDiv.setAttribute("id",boxID);
    content = document.createTextNode("building color box...");
    boxDiv.appendChild(content);
    boxDiv.style.background=presentColor;
    boxDiv.style.height='14px';
    boxDiv.style.width='28px';
    htmlStr = '<table cellspacing="0" cellpadding="0" border="1" onclick=\'popUpPicker(';
    htmlStr += '"'+boxID+'","colorBoxUpdate",'+boxNum+');\' ';
    htmlStr += '><tbody>';
    htmlStr += '<tr><td><div style="height: 12px; width: 12px;"></div></td><td><table ';
    htmlStr += 'cellspacing="0" cellpadding="0" border="0" style="background-color: white;"><tbody>';
    htmlStr += '<tr><td style="width: 6px; height: 6px; background-color: rgb(255,0,0);"></td>';
    htmlStr += '<td style="width: 6px; height: 6px; background-color: rgb(0,255,0);"></td></tr>';
    htmlStr += '<tr><td style="width: 6px; height: 6px; background-color: rgb(0,0,255);"></td>';
    htmlStr += '<td style="width: 6px; height: 6px; background-color: rgb(255,255,0);"></td></tr>';
    htmlStr += '</tbody></table></td></tr></tbody></table>';
    boxDiv.innerHTML = htmlStr;
    scripts = document.getElementsByTagName("script");
    scriptNode = scripts.item(scripts.length-1);
    parentNode = scriptNode.parentNode;
    parentNode.appendChild(boxDiv);
    }

function colorBoxUpdate(pickedColor, boxNum){
    document.getElementById(JmolColorPickerBoxes[boxNum].boxID).style.background = pickedColor;
    document.getElementById('colorPickerDiv').setAttribute("class", "hidden");
    var rgbCodes = pickedColor.replace(/rgb/i,'').replace('(','[').replace(')',']');
    var scriptStr = JmolColorPickerBoxes[boxNum].scriptStr.replace('$COLOR$', rgbCodes);
    jmolScript(scriptStr,JmolColorPickerBoxes[boxNum].appletID);
    }


