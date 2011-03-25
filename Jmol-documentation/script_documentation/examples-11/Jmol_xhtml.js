function _jmolDocumentWrite(text) 
{	// Override Jmol.js
  if (_jmol.currentDocument)
  {  //_jmol.currentDocument.write(text); //this is what Jmol.js uses
     /* This is the new code, slightly adapted (A.H.) from 
	 	the addElement() function at  https://developer.mozilla.org/en/document.createElement
	 	LICENSE: Code samples in MDC wikis are available under the terms of 
		the MIT License, http://www.ibiblio.org/pub/Linux/LICENSES/mit.license
	 */
	// 1. Create a new div element and give it some content:
	var newDivForJmolJs = document.createElement("span");
	newDivForJmolJs.innerHTML = text;
	// 2. Add the newly created element and it's content into the DOM:
	document.body.insertBefore(newDivForJmolJs, null);
	// (Firefox and Opera require the 2nd argument, even if null)
  }
  return text;
}

/*
function jmolButton(script, label, id, title) {
  _jmolInitCheck();
  if (id == undefined || id == null)
    id = "jmolButton" + _jmol.buttonCount;
  if (label == undefined || label == null)
    label = script.substring(0, 32);
  ++_jmol.buttonCount;
  var scriptIndex = _jmolAddScript(script);
  var t = "<span id=\"span_"+id+"\""+(title ? " title =\"" + title + "\"":"")+"><input type='button' name='" + id + "' id='" + id +
          "' value='" + label +
          "' onclick='_jmolClick(" + scriptIndex + _jmol.targetText +
          ")' onmouseover='_jmolMouseOver(" + scriptIndex +
          ");return true' onmouseout='_jmolMouseOut()' " +
          _jmol.buttonCssText + "/></span>";
  if (_jmol.debugAlert)
    alert(t);
  return _jmolDocumentWrite(t);
}

function jmolCheckbox(scriptWhenChecked, scriptWhenUnchecked,
                      labelHtml, isChecked, id, title) {
  _jmolInitCheck();
  if (id == undefined || id == null)
    id = "jmolCheckbox" + _jmol.checkboxCount;
  ++_jmol.checkboxCount;
  if (scriptWhenChecked == undefined || scriptWhenChecked == null ||
      scriptWhenUnchecked == undefined || scriptWhenUnchecked == null) {
    alert("jmolCheckbox requires two scripts");
    return;
  }
  if (labelHtml == undefined || labelHtml == null) {
    alert("jmolCheckbox requires a label");
    return;
  }
  var indexChecked = _jmolAddScript(scriptWhenChecked);
  var indexUnchecked = _jmolAddScript(scriptWhenUnchecked);
  var eospan = "</span>"
  var t = "<span id=\"span_"+id+"\""+(title ? " title =\"" + title + "\"":"")+"><input type='checkbox' name='" + id + "' id='" + id +
          "' onclick='_jmolCbClick(this," +
          indexChecked + "," + indexUnchecked + _jmol.targetText +
          ")' onmouseover='_jmolCbOver(this," + indexChecked + "," +
          indexUnchecked +
          ");return true' onmouseout='_jmolMouseOut()' " +
	  (isChecked ? "checked " : "") + _jmol.checkboxCssText + "/>" 
  if (labelHtml.toLowerCase().indexOf("<td>")>=0) {
	t += eospan
	eospan = "";
  }
  t += "<label for=\"" + id + "\">" + labelHtml + "</label>" +eospan;
  if (_jmol.debugAlert)
    alert(t);
  return _jmolDocumentWrite(t);
}

function _jmolRadio(script, labelHtml, isChecked, separatorHtml, groupName, id, title) {
  ++_jmol.radioCount;
  if (groupName == undefined || groupName == null)
    groupName = "jmolRadioGroup" + (_jmol.radioGroupCount - 1);
  if (!script)
    return "";
  if (labelHtml == undefined || labelHtml == null)
    labelHtml = script.substring(0, 32);
  if (! separatorHtml)
    separatorHtml = "";
  var scriptIndex = _jmolAddScript(script);
  var eospan = "</span>"
  var t = "<span id=\"span_"+id+"\""+(title ? " title =\"" + title + "\"":"")+"><input name='" 
	+ groupName + "' id='"+id+"' type='radio' onclick='_jmolClick(" +
         scriptIndex + _jmol.targetText + ");return true;' onmouseover='_jmolMouseOver(" +
         scriptIndex + ");return true;' onmouseout='_jmolMouseOut()' " +
	 (isChecked ? "checked " : "") + _jmol.radioCssText + "/>"
  if (labelHtml.toLowerCase().indexOf("<td>")>=0) {
	t += eospan
	eospan = "";
  }
  t += "<label for=\"" + id + "\">" + labelHtml + "</label>" +eospan + separatorHtml;

  return t;
}

function jmolMenu(arrayOfMenuItems, size, id, title) {
  _jmolInitCheck();
  if (id == undefined || id == null)
    id = "jmolMenu" + _jmol.menuCount;
  ++_jmol.menuCount;
  var type = typeof arrayOfMenuItems;
  if (type != null && type == "object" && arrayOfMenuItems.length) {
    var len = arrayOfMenuItems.length;
    if (typeof size != "number" || size == 1)
      size = null;
    else if (size < 0)
      size = len;
    var sizeText = size ? " size='" + size + "' " : "";
    var t = "<span id=\"span_"+id+"\""+(title ? " title =\"" + title + "\"":"")+"><select name='" + id + "' id='" + id +
            "' onchange='_jmolMenuSelected(this" + _jmol.targetText + ")'" +
            sizeText + _jmol.menuCssText + ">";
    for (var i = 0; i < len; ++i) {
      var menuItem = arrayOfMenuItems[i];
      type = typeof menuItem;
      var script, text;
      var isSelected = undefined;
      if (type == "object" && menuItem != null) {
        script = menuItem[0];
        text = menuItem[1];
        isSelected = menuItem[2];
      } else {
        script = text = menuItem;
      }
      if (text == undefined || text == null)
        text = script;		
	  if (script=="#optgroup") {
        t += "<optgroup label='" + text + "'>";	  
	  } else if (script=="#optgroupEnd") {
        t += "</optgroup>";	  
	  } else {		
        var scriptIndex = _jmolAddScript(script);
        var selectedText = isSelected ? "' selected>" : "'>";
        t += "<option value='" + scriptIndex + selectedText + text + "</option>";
	  }
    }
    t += "</select></span>";
    if (_jmol.debugAlert)
      alert(t);
    return _jmolDocumentWrite(t);
  }
}
*/