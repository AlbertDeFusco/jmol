
function createControls(
	textWireframeBonds, textSilicon, textPhosphorus, textOxygen,
	textCarbon, textNitrogen, textSulfur, textHydrogen) {

  jmolMenu([
	["load diffGear.pdb.gz; cpk off;", "diffGear"],
	["load fineMotion970116.pdb.gz; cpk off;", "fineMotion", "selected"],
	["load finalPump96.09.06.pdb.gz; cpk off;", "finalPump"]
	], -1);

  jmolHtml("<hr />");

  jmolCheckbox(
	"select *; wireframe",
	"select *; wireframe off",
	textWireframeBonds,
	"checked");
  jmolHtml("<hr />");

  jmolCheckbox(
	"select silicon; cpk on",
	"select silicon; cpk off",
	textSilicon);
  jmolBr();

  jmolCheckbox(
	"select phosphorus; cpk on",
	"select phosphorus; cpk off",
	textPhosphorus);
  jmolBr();

  jmolCheckbox(
	"select oxygen; cpk on",
	"select oxygen; cpk off",
	textOxygen);
  jmolBr();

  jmolCheckbox(
	"select carbon; cpk on",
	"select carbon; cpk off",
	textCarbon);
  jmolBr();

  jmolCheckbox(
	"select nitrogen; cpk on",
	"select nitrogen; cpk off",
	textNitrogen);
  jmolBr();

  jmolCheckbox(
	"select sulfur; cpk on",
	"select sulfur; cpk off",
	textSulfur);
  jmolBr();

  jmolCheckbox(
	"select hydrogen; cpk on",
	"select hydrogen; cpk off",
	textHydrogen);
  jmolBr();
}
