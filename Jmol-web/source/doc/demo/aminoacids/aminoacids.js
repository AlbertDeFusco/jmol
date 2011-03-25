var doc = document;

function writeAminoControl(basename, label) {
  doc.open();
  doc.writeln("<td>");
  doc.close();

  jmolButton("load " + basename + ".pdb.gz", "X");
  doc.open();
  doc.writeln(" ");
  doc.writeln(label);
  doc.writeln("</td>");
  doc.close();
}

function writeScriptControl(script) {
  jmolButton(script);
  jmolHtml(" ");
}

function writeScriptControls() {
  doc.writeln("<br /><hr />");

  writeScriptControl('select *');
  writeScriptControl('select mainchain');
  writeScriptControl('select sidechain');
  doc.writeln("<br /><hr />");

  writeScriptControl('wireframe on');
  writeScriptControl('wireframe 0.1');
  writeScriptControl('wireframe 0.2');
  doc.writeln("<br /><hr />");

  writeScriptControl('cpk off');
  writeScriptControl('cpk 20%');
  writeScriptControl('cpk on');
  doc.writeln("<br /><hr />");

  writeScriptControl('label %a');
  writeScriptControl('label %n');
  writeScriptControl('label off');
  doc.writeln("<br /><hr />");
  writeScriptControl('color label white');
  writeScriptControl('color label none');
  doc.writeln("<br /><hr />");

  writeScriptControl('color atoms cpk');
  writeScriptControl('color atoms amino');
  doc.writeln("<br />");
}
