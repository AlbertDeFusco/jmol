var doc = document;
function writeScriptControl(script) {
  jmolButton(script, "X");
  doc.writeln(script);
  doc.writeln("<br />");
}
function writeControls() {
  writeScriptControl('select *; cpk off; wireframe; select backbone; cpk 100%');
  writeScriptControl('select *; cpk off; wireframe 0.3');
  writeScriptControl('select *; restrict backbone; cpk off; wireframe 0.3');
  writeScriptControl('select *; restrict backbone; cpk 100%');
  writeScriptControl('select *; wireframe 0.3; select backbone; cpk 100%');
  writeScriptControl('select *; cpk off; wireframe; select backbone; wireframe 0.3');
  writeScriptControl('select *; cpk off; wireframe');
  doc.writeln('<hr />');
  writeScriptControl('trace 0.5; color trace orange');
  writeScriptControl('trace off');
  doc.writeln('<hr />');
  writeScriptControl('select alpha; label %a; color label white');
  writeScriptControl('select alpha; label %n; color label white');
  writeScriptControl('select *; label off');
  doc.writeln('<hr />');
  writeScriptControl('color atoms cpk');
  writeScriptControl('color atoms amino');
  doc.writeln('<hr />');
  writeScriptControl('backbone 0.3; color backbone yellowgreen');
  writeScriptControl('backbone off');
}
