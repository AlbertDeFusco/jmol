
function displayStatus(projectname) {
  window.status = "Project " + projectname;
}

function clearStatus() {
  window.status = " ";
}

function showProjectDescription() {
  window.open("http://fah-web.stanford.edu/cgi-bin/fahproject?p=" + document.fahForm.infoNumber.value);
}

function hideSolvent() {
  var varScript;
  varScript =
    "restrict (not substructure(\"[O][C](N)N\")) or substructure(\"[O]([C](N)N)[*]\")\n" +
    "restrict (not substructure(\"[N][C]C\")) or substructure(\"[N]([C]C)[*]\")\n" +
    "restrict (not substructure(\"C(Cl)(Cl)Cl\"))\n" +
    "restrict (not substructure(\"CO\"))\n" +
    "restrict (not substructure(\"O\"))\n" +
    "center selected\n" +
    "select none";
  jmolScript(varScript, "Fah");
}

function showXMLFile() {
  window.open("./fah-projects.xml");
}

function getParameter(paramName, defaultVal) {
  var paramValue = defaultVal;
  var params = location.search.substring(1).split("&");
  for (i = 0; i < params.length; i++) {
    var detail = params[i].split("=");
    if ((detail[0] != undefined) && (detail[1] != undefined) && (detail[0] == paramName)) {
      paramValue = detail[1];
    }
  }
  return paramValue;
}

function createFahPage(select,
                       project, name, credit, atoms, preferred, deadline, frames, code,
                       contrib,
                       projectDescription, hideSolvent, availableFiles,
                       findMissing, missingProjects, supportJmol) {
  document.writeln("<table border='0' cellpadding='0' cellspacing='0' width='100%'>");
  var paramDetailLocation = getParameter("detailLocation", "right");
  var paramShowList = getParameter("showList", "dropbox");
  if (paramShowList != "button") {
    document.writeln(" <tr>");
    document.writeln("  <td>");
    document.writeln(    select + " : ");
    document.writeln("   <select onchange='showProjectInfo(this.value)'>");
    document.writeln("    <option value=''></option>");
    createAllProjects("dropbox");
    document.writeln("   </select>");
    document.writeln("   <br/>");
    document.writeln("  </td>");
    document.writeln(" </tr>");
  }
  if (paramDetailLocation == "top") {
    document.writeln("<tr><td valign='top'>");
    createDetails(project, name, credit, atoms, preferred, deadline, frames, code, contrib, supportJmol);
    document.writeln("</td></tr>");
  }
  document.writeln(" <tr>");
  document.writeln("  <td>");
  document.writeln("   <table border='0' cellpadding='0' cellspacing='0' width='100%'>");
  document.writeln("    <tr>");
  if (paramDetailLocation == "left") {
    document.writeln("<td valign='top'>");
    createDetails(project, name, credit, atoms, preferred, deadline, frames, code, contrib, supportJmol);
    document.writeln("</td>");
  }
  document.writeln("     <td>");
  var scriptStart = "";
  var paramStereo = getParameter("stereo", "");
  if (paramStereo != "") {
    scriptStart = scriptStart + "stereo " + paramStereo + ";";
  }
  var paramProject = getParameter("project", "");
  if (paramProject != "") {
    scriptStart = scriptStart + "load ../fah/projects/p" + paramProject + ".xyz.gz;";
  }
  var paramAppletSize = getParameter("appletSize", "350");
  var paramAppletWidth = getParameter("appletWidth", "");
  if (paramAppletWidth == "") {
    jmolApplet(paramAppletSize, scriptStart, "Fah");
  } else {
    jmolApplet([ paramAppletWidth, paramAppletSize ], scriptStart, "Fah");
  }
  document.writeln("     </td>");
  if (paramDetailLocation == "right") {
    document.writeln("<td valign='top'>");
    createDetails(project, name, credit, atoms, preferred, deadline, frames, code, contrib, supportJmol);
    document.writeln("</td>");
  }
  document.writeln("    </tr>");
  document.writeln("   </table>");
  document.writeln("  </td>");
  document.writeln(" </tr>");
  document.writeln(" <tr>");
  document.writeln("  <td align='left'>");
  document.writeln("   <input type='button' value='" + projectDescription + "'");
  document.writeln("          onclick='showProjectDescription()'");
  document.writeln("          onMouseOver='window.status=\"http://fah-web.stanford.edu/cgi-bin/fahproject\"'");
  document.writeln("          onMouseout='window.status=\" \"'/>");
  document.writeln("   <input type='button' value='" + hideSolvent + "'");
  document.writeln("          onclick='hideSolvent()'/>");
  document.writeln("   <input type='button' value='XML'");
  document.writeln("          onclick='showXMLFile()'/>");
  document.writeln("   <small>" + availableFiles + "</small>");
  document.writeln("  </td>");
  document.writeln(" </tr>");
  if (paramDetailLocation == "bottom") {
    document.writeln("<tr><td valign='top'>");
    createDetails(project, name, credit, atoms, preferred, deadline, frames, code, contrib, supportJmol);
    document.writeln("</td></tr>");
  }
  document.writeln(" <tr>");
  document.writeln("  <td align='center'>");
  document.writeln("   <br/>");
  document.writeln("   <small><i>" + findMissing + "</i></small>");
  document.writeln("  </td>");
  document.writeln(" </tr>");
  if ((paramShowList == "button") || (paramShowList == "both")) {
    document.writeln(" <tr>");
    document.writeln("  <td class='btfah'>");
    document.writeln("   <br/>");
    createAllProjects("button");
    document.writeln("  </td");
    document.writeln(" </tr");
    document.writeln(" <br/>");
  }
  document.writeln(" <tr>");
  document.writeln("  <td>");
  document.writeln("   <br/>");
  document.writeln("   <small>");
  document.writeln("    <i><u><a href='./missingProjects.txt'>" + missingProjects + "</a> :</u></i>");
  createMissingProjects();
  document.writeln("   </small>");
  document.writeln("  </td>");
  document.writeln(" </tr>");
  document.writeln("</table>");
}

function createDetails(project, name, credit, atoms, preferred, deadline, frames, code, contrib, supportJmol) {
  document.writeln("<table border='0' cellpadding='0' cellspacing='0' width='100%'>");
  document.writeln(" <tr>");
  document.writeln("  <td align='center' colspan='2'>");
  document.writeln("   <a href='http://folding.stanford.edu'>");
  document.writeln("    <img src='FAHlogoButton.jpg' alt='fah-logo' border='0'/>");
  document.writeln("   </a>");
  document.writeln("  </td>");
  document.writeln(" </tr>");
  document.writeln(" <tr>");
  document.writeln("  <td><label>" + project + " :</label></td>");
  document.writeln("  <td><input type='text' name='infoNumber' id='infoNumber'");
  document.writeln("            size='45' readonly='readonly'/></td>");
  document.writeln(" </tr>");
  document.writeln(" <tr>");
  document.writeln("  <td><label>" + name + " :</label></td>");
  document.writeln("  <td><input type='text' name='infoName' id='infoName'");
  document.writeln("             size='45' readonly='readonly'/></td>");
  document.writeln(" </tr>");
  document.writeln(" <tr>");
  document.writeln("  <td><label>" + credit + " :</label></td>");
  document.writeln("  <td><input type='text' name='infoCredit' id='infoCredit'");
  document.writeln("             size='45' readonly='readonly'/></td>");
  document.writeln(" </tr>");
  document.writeln(" <tr>");
  document.writeln("  <td><label>" + atoms + " :</label></td>");
  document.writeln("  <td><input type='text' name='infoAtoms' id='infoAtoms'");
  document.writeln("             size='45' readonly='readonly'/></td>");
  document.writeln(" </tr>");
  document.writeln(" <tr>");
  document.writeln("  <td><label>" + preferred + " :</label></td>");
  document.writeln("  <td><input type='text' name='infoPreferred' id='infoPreferred'");
  document.writeln("             size='45' readonly='readonly'/></td>");
  document.writeln(" </tr>");
  document.writeln(" <tr>");
  document.writeln("  <td><label>" + deadline + " :</label></td>");
  document.writeln("  <td><input type='text' name='infoDeadline' id='infoDeadline'");
  document.writeln("             size='45' readonly='readonly'/></td>");
  document.writeln(" </tr>");
  document.writeln(" <tr>");
  document.writeln("  <td><label>" + frames + " :</label></td>");
  document.writeln("  <td><input type='text' name='infoFrames' id='infoFrames'");
  document.writeln("             size='45' readonly='readonly'/></td>");
  document.writeln(" </tr>");
  document.writeln(" <tr>");
  document.writeln("  <td><label>" + code + " :</label></td>");
  document.writeln("  <td><input type='text' name='infoCode' id='infoCode'");
  document.writeln("             size='45' readonly='readonly'/></td>");
  document.writeln(" </tr>");
  document.writeln(" <tr>");
  document.writeln("  <td><label>" + contrib + " :</label></td>");
  document.writeln("  <td><input type='text' name='infoContrib' id='infoContrib'");
  document.writeln("             size='45' readonly='readonly'/></td>");
  document.writeln(" </tr>");
  document.writeln(" <tr>");
  document.writeln("  <td align='center' colspan='2'><br/><small><i>" + supportJmol + "</i></small></td>");
  document.writeln(" </tr>");
  document.writeln("</table>");
}

function addProject(project, filename, projectname,
                    credit, atoms, preferred, deadline, frames, code, p, contrib,
                    showList) {
  if ((projectname != undefined) &&
      (projectname !== null) &&
      (filename != undefined) &&
      (filename != null) &&
      ((p == "y") || (getParameter("view", "p") == "b"))) {
    var varValue = "" + project + ";" + filename + ";" + projectname + ";";
    if (credit != undefined && credit != null) {
      varValue = varValue + credit;
    }
    varValue = varValue + ";";
    if (atoms != undefined && atoms != null) {
      varValue = varValue + atoms;
    }
    varValue = varValue + ";";
    if (preferred != undefined && preferred != null) {
      varValue = varValue + preferred;
    }
    varValue = varValue + ";";
    if (deadline != undefined && deadline != null) {
      varValue = varValue + deadline;
    }
    varValue = varValue + ";";
    if (frames != undefined && frames != null) {
      varValue = varValue + frames;
    }
    varValue = varValue + ";";
    if (code != undefined && code != null) {
      varValue = varValue + code;
    }
    varValue = varValue + ";";
    if (contrib != undefined && contrib != null) {
      varValue = varValue + contrib;
    }
    if (showList == "button") {
      varButton =
        "<input " +
          "type='button' " +
          "value='" + project + "' " +
          "onClick='showProjectInfo(\"" + varValue + "\")' " +
          "onMouseOver='displayStatus(\"" + projectname + "\");return true' " +
          "onMouseout='clearStatus();return true' " +
          "/>";
      document.writeln(varButton);
    } else {
      varOption = "<option value='" + varValue + "'>" + project + " - " + projectname + "</option>";
      document.writeln(varOption);
    }
  }
}

function addMissingProject(name) {
  document.writeln(" " + name + ",");
}

function showProjectInfo(value) {
  var varArray = value.split(";");
  jmolScript("set perspectiveModel 11;load ../fah/projects/" + varArray[1] + ".xyz.gz", "Fah");
  document.fahForm.infoNumber.value = varArray[0];
  document.fahForm.infoName.value = varArray[2];
  document.fahForm.infoCredit.value = varArray[3];
  document.fahForm.infoAtoms.value = varArray[4];
  document.fahForm.infoPreferred.value = varArray[5];
  document.fahForm.infoDeadline.value = varArray[6];
  document.fahForm.infoFrames.value = varArray[7];
  var code = varArray[8];
  if (code == "A") {
    document.fahForm.infoCode.value = "Amber";
  } else if (code == "AD") {
    document.fahForm.infoCode.value = "ATI-DEV";
  } else if (code == "A0") {
    document.fahForm.infoCode.value = "Gromacs 33";
  } else if (code == "A3") {
    document.fahForm.infoCode.value = "Gromacs A3";
  } else if (code == "A4") {
    document.fahForm.infoCode.value = "Gromacs A4";
  } else if (code == "DB") {
    document.fahForm.infoCode.value = "Double Gromacs B";
  } else if (code == "DC") {
    document.fahForm.infoCode.value = "Double Gromacs C";
  } else if (code == "DG") {
    document.fahForm.infoCode.value = "Double Gromacs";
  } else if (code == "G") {
    document.fahForm.infoCode.value = "Gromacs";
  } else if (code == "G3") {
    document.fahForm.infoCode.value = "Gromacs PS3";
  } else if (code == "GB") {
    document.fahForm.infoCode.value = "GB Gromacs";
  } else if (code == "GC") {
    document.fahForm.infoCode.value = "GroCVS";
  } else if (code == "GG") {
    document.fahForm.infoCode.value = "Gromacs GPU";
  } else if (code == "GG2") {
    document.fahForm.infoCode.value = "Gromacs GPU 2";
  } else if (code == "GG2MT") {
    document.fahForm.infoCode.value = "Gromacs GPU 2 MT";
  } else if (code == "GS") {
    document.fahForm.infoCode.value = "Gromacs SMP";
  } else if (code == "GST") {
    document.fahForm.infoCode.value = "Gromacs SimT";
  } else if (code == "GT") {
    document.fahForm.infoCode.value = "Gromacs ST";
  } else if (code == "ND") {
    document.fahForm.infoCode.value = "NVIDIA-DEV";
  } else if (code == "OF") {
    document.fahForm.infoCode.value = "OpenMMFermi";
  } else if (code == "OG") {
    document.fahForm.infoCode.value = "OpenMMGPU";
  } else if (code == "PM") {
    document.fahForm.infoCode.value = "ProtoMol";
  } else if (code == "Q") {
    document.fahForm.infoCode.value = "QMD";
  } else if (code == "SP") {
    document.fahForm.infoCode.value = "Sharpen";
  } else if (code == "T") {
    document.fahForm.infoCode.value = "Tinker";
  } else {
    document.fahForm.infoCode.value = code;
  }
  document.fahForm.infoContrib.value = varArray[9];
}
