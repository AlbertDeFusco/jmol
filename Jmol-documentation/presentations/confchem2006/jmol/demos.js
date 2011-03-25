function newDemo(i,image,caption,script,morescript,etc,aload,arch) {
 var pt=arguments.length-1
 archive=arguments[pt]+""
 if (archive == "" || archive.indexOf(".jar") >= 0) {
	--pt
 }else{
	archive = ""
 }
 autoload = arguments[pt]
 if (autoload == 0 || autoload == 1) {
	--pt
 }else{
	autoload = 0
 }
 Demo[i]=new Array()
 Demo[i].image=image
 Demo[i].caption=caption
 Demo[i].Scripts=(" | # "+caption +"|"+script).split("|")
 Demo[i].autoload=(autoload?autoload:0)
 Demo[i].archive=(archive?archive:"")
 for(var j=4;j<=pt;j++){
	Demo[i].Scripts[Demo[i].Scripts.length]=arguments[j]
 }
 for(var j=0;j<Demo[i].Scripts.length;j++) {
	Demo[i].Scripts[j]=Demo[i].Scripts[j].replace(/mol\//g,"")
	if (Demo[i].Scripts[j].indexOf("#")<0 && Demo[i].Scripts[j].lastIndexOf(";") != Demo[i].Scripts[j].length-1)
		Demo[i].Scripts[j]+=";"
 }
}


Demo = new Array()

newDemo(1,"sf4.jpg","SF<sub>4</sub>, with symmetry elements indicated",
	"load SF4.mol;|"
	+"set perspectivedepth off;|"
	+"draw plane1 250 plane (atomno=5) (atomno=4)  (atomno=1);|color $plane1 translucent;|"
	+"draw plane2 220 plane (atomno=2) (atomno=3)  (atomno=4 or atomno=5);|color $plane2 translucent yellow;|"
	+"draw axis1 300 (atomno=1) (atomno=4 or atomno=5);|"
	+"moveTo 3 -6 -960 -282 102.3 89|"
	+"# click on the end of the axis to spin the model around this axis"
	,1,"JmolAppletProto.jar")

newDemo(2,"1p84.jpg","ubiquinol-cytochrome C reductase core dimer (1p84)",
	"load 1p84b.pdb;wireframe off;spacefill off;cartoon on;|frame 0;|"
	+"select */1;color violet;|select */2;color structure;|moveto 4 48 -994 103 46.2"
	,1)

newDemo(3,"taxol.jpg","taxol, highlighting functional groups",
	"load taxol.pdb;|"
	+"select *;color cpk;select ~CH3;color yellow;|"
	+"select *;color cpk;select ~CH2;color yellow;|"
	+"select *;color cpk;select ~CH;color yellow;|"
	+"select *;color cpk;select ~CR4;color yellow;|"
	+"select *;color cpk;select ~OH;color yellow;|"
	+"select *;color cpk;select ~CO;color yellow;|"
	+"select *;color cpk;select ~R2CO;color yellow;|"
	+"select *;color cpk;select ~RCO2R;color yellow;|"
	+"spin OFF;|spin ON;|"
	+"# this uses the script file <a target=_blank href=~groups.txt>~groups.txt</a> (available in Jmol 10.2), which is called by an embedded #jmolscript: command (available in Jmol 10.x) in <a href=\"javascript:alert(jmolGetPropertyAsString('fileContents'))\">taxol.pdb</a>."
	,0,"JmolAppletProto.jar")


newDemo(4,"cyclohexane_movie.jpg","chair-chair conformational change in cyclohexane",
	"load cyclohexane_movie.xyz;|"
	+"moveto 3 828 -458 322 123.6;|delay 2;"
	+"animation fps 10; animation on;|"
	+"frame 1;|"
	+"frame next;|frame play;|anim off;|anim on;"
	,1)

newDemo(5,"h2o-vib.jpg","vibrational modes of water",
	"load h2o-vib.csf;wireframe 0.05;spacefill 0.15;|moveto 1 959 270 -90 160.1;|"
	+"frame 1;|"
	+"frame 3;|color vectors yellow;|vectors 0.08;vector scale -1.0;vibration scale 0.1;vibration on;|"
	+"frame next;"
	,1)


newDemo(6,"ethene-MO.jpg","highest-occupied molecular orbital in ethylene",
	"load ethene-MO.pdb;"
	+'isosurface pos05 0.05 "ethene-HOMO.cub.gz";isosurface neg05 -0.05 "ethene-HOMO.cub.gz";'
	+'moveto 3 -923 -290 -254 80.6;|'
	+'isosurface pos01 0.01 "ethene-HOMO.cub.gz";|isosurface neg01 -0.01 "ethene-HOMO.cub.gz";color isosurface translucent;|'
	+'isosurface pos01 nofill mesh;color isosurface translucent;|'
	+'isosurface neg01 dots;isosurface neg01 nofill;|'
	,0,"")

newDemo(7,"caffeine.jpg","caffeine",
	"load caffeine.xyz;"
	,1)

newDemo(8,"newdots.jpg","solvent-accessible dot surface with trace of solvent centers",
	"load caffeine.xyz;set perspectivedepth off;set solvent on;dots on;|"
	+'load caffeine.xyz;set solvent on;dots on;moveto 1.0 -561 -26 -827 71.3 124;|'
	+'load C6H6.smol;select */1;set solvent on;dots on;moveto 1.0 -561 -26 -827 71.3 124;|'
	+'load 1crn.pdb;set solvent on;dots on;moveto 1.0 853 285 437 144.4 317;|'
	+'set testflag1 off;set radius 1.2000;set solvent on;dots on; #experimental|'
	+'set testflag1 on;set radius 1.2001;set solvent on;dots on; #Jmol 10.2|'
	+'depth 50;slab 100;slab on;|'
	+'slab 50;depth 0;slab on;|'
	+'slab off;|'
	+'wireframe off;spacefill off;|'
	+'wireframe 0.05;spacefill 0.15;|'
	+'set radius 0.3;set solvent on; dots on|'
	+'set radius 0.7;set solvent on; dots on|'
	+'set radius 1.2;set solvent on; dots on # the default|'
	+'set testflag2 on; # show contact lines|'
	+'set testflag2 off;|'
	+'set testflag3 on; # "clay model" solid surface|'
	+'set testflag3 off;|'
	+'set testflag4 on; # trace solvent centers|'
	+'set testflag4 off;|'
	+'load 1crn.pdb; set testflag1 on; set solvent on; dots on; slab 50; slab on;moveto 1.0 244 -141 -960 108.1 1260;'
	,0,"JmolAppletProto.jar")


newDemo(9,"polyhedra.jpg","polyhedra, showing quartz chirality"
	,'load quartz.xyz;'
	,'delay 1'
	,'polyhedra 4 BONDS; color polyhedra red'
	,'delay 1'
	,'polyhedra 4 BONDS EDGES'
	,'delay 1'
	,'polyhedra 4 BONDS (silicon) TO (oxygen) COLLAPSED EDGES'
	,'delay 2'
	,'moveto 3 985 -165 41 92.5;delay 6'
	,'moveto 3 1 0 0 0 100 # a different view'
	,1)

newDemo(10,"connect.jpg","modifying and creating bonds (caffeine)"
	,'load caffeine.xyz'
	,'delay 1'
	,'connect 1.2 1.26 (carbon) (oxygen) DOUBLE'
	,'delay 1'
	,'connect 1.5 (carbon) (connected(2,nitrogen)) DOUBLE'
	,'delay 1'
	,'connect 1.5 (nitrogen and not connected(3)) (connected(1,hydrogen)) DOUBLE'
	,'delay 1'
	,'moveto 1 -463 -39 -885 59.9'
	,1)


newDemo(11,"smiles.jpg","SMILES substructure identification"
	,'load caffeine.xyz;set display selected;select none'
	,'select substructure("[C][O]") #the carbonyl groups'
	,'select substructure("[C][O]") and carbon #the carbonyl carbons'
	,'select substructure("[C][O]") and oxygen #the carbonyl oxygens'
	,'select substructure("[C](H)(H)H") #the CH3 groups'
	,'select substructure("[O][C][N]C") and (oxygen or hydrogen) # oxygens and nearby methyl'
	,'select substructure("C[N][C]H") and (hydrogen) # methyl and methine attached to the same nitrogen atom'
	,'select substructure("[N][CH][N]") and (hydrogen) # the H on the carbon between two nitrogen atoms'
	,'select substructure("[C][C]") # all adjacent carbon atoms'
	,'select substructure("[C][C]") and not substructure("[C][O]") # carbon atoms alpha and beta to the carbonyl'
	,'select substructure("[C][C][O]") and not substructure("[C][O]") # the carbon atom alpha to the carbonyl'
	,'select substructure("[C][C][C][O]") and not substructure("[C][C][O]") # the carbon atom beta to the carbonyl'
	,'select none'
	,0)

newDemo(12,"unitcells.jpg","unit cell symmetry generation"
	,'load quartz.cif;set showUnitCell on'
	,'# standard load reads only the file data -- no symmetry applied'
	,'load "quartz.cif" {2 2 2};set showUnitCell on'
	,'# note that quotation marks REQUIRED when the number of unit cells is given'
	,'load calcite.cif;set showUnitCell on'
	,'load "calcite.cif" {3 2 1};set showUnitCell on'
	,'load maleic.cif;set showUnitCell on'
	,'load "maleic.cif" {1,1,1};set showUnitCell on'
	,'# note that this file has 18 models, each with a different unit cell'
	,'set perspectiveDepth off'
	,'select symmetry;color green # select only those atoms generated by symmetry operations'
	,'select not symmetry;color yellow # select only the original atoms prior to symmetry operations'
	,'frame 1'
	,'frame next'
	,'frame prev'
	,'# an optional integer parameter right after the file name indicates WHICH model to load'
	,'load "maleic.cif" 3 {2,2,2};set showUnitCell on'
	,'show symmetry #list the space group and symmetry operators'
	,'load "maleic.cif" 6 {1,1,1};set showUnitCell on'
	,'set showUnitCell off'
	,'set perspectiveDepth on'
	,0,"JmolAppletProto.jar")


newDemo(13,"dipoles.jpg","dipole moments"
	,'load water.xyz;rotate y 20;set perspectiveDepth off'
	,'dipole (atomno=1) (atomno=3)'
	,'dipole (atomno=2) (atomno=3)'
	,'load CH3F.smol;rotate y 40; rotate x 30'
	,'dipole molecular offset 0.0'
	,'dipole bonds'
	,0,"JmolAppletProto.jar")


newDemo(14,"getproperty.jpg","applet.getProperty()"
	,'load CH3F.smol'
	,'getProperty appletinfo  '
	,'getProperty atominfo  '
	,'getProperty bondinfo  '
	,'getProperty modelinfo  '
	,'load taxol.pdb'
	,'set measure pm; measures off;measure 1.2 1.5 ALL (carbon) (oxygen)'
	,'getProperty measurementinfo  '
	,'set measure pm; measures off;measure 1.2 1.3 ALL (carbon) (oxygen)'
	,'getProperty measurementinfo  '
	,0,"JmolAppletProto.jar")

newDemo(15,"status.jpg","jmolGetProperty()"
	,'load CH3F.smol'
	,0,"JmolAppletProto.jar")

newDemo(16,"frames.jpg","jmolGetProperty()"
	,'load C6H6.smol;rotate x -60'
	,'frame 2;'
	,'vectors .08;'
	,'vector scale 2'
	,'vector scale 10'
	,'vibration on;'
	,'vibration off;'
	,'frame next'
	,'frame prev'
	,'frame play'
	,'frame playrev'
	,'animation off'
	,'load SF6.smol'
	,'frame 2;'
	,'vectors 0.1;'
	,'vector scale 2'
	,'vector scale 10'
	,'vibration on;'
	,'vibration off;'
	,'animation mode loop'
	,'animation off'
	,'frame next'
	,'frame prev'
	,'frame play'
	,'frame playrev'
	,'animation on'
	,'animation off'
	,0,"JmolAppletProto.jar")

newDemo(17,"draw.jpg","drawing points, lines, and planes"
	,'load water.xyz;rotate y 20'
	,'draw line1 250 (atomno=1) (atomno=2);color draw yellow'
	,'draw line2 350 $line1 (atomno=3);color draw white'

	,'load caffeine.xyz;set picking SPIN 10; #now pick two atoms'
	,'draw line1 300 (oxygen) (nitrogen);'
	,'draw line2 300 (atomno=9) (atomno=11);'
	,'draw line2 {-2. 0. 0.} {0. 2. 0.};'

	,'load caffeine2.xyz;draw triangle1 150 (atomno=1) (atomno=14) (atomno=15);'
	,'draw line1 200 PERP $triangle1'
	,'draw line2 PERP 400 (atomno=1) (atomno=14) (atomno=15);'
	,'draw plane2 PLANE 100 (atomno=11) (atomno=9) (atomno=21);'
	,'draw plane2 150'
	,'draw plane2 200'
	,'draw triangle1;color draw red;draw line2 ;color draw blue'
	,'load cyclohexane_movie.xyz;draw line1 250 (atomno=2) (atomno=5);draw line2 200 (atomno=4 or atomno=3) (atomno=1 or atomno=6);'
	,'animation on'
	,'spin off'
	,'animation off'
	,'frame next'
	,'frame prev'
	,'frame play'
	,'frame playrev'
	,0,"JmolAppletProto.jar")

newDemo(18,"spin.jpg","rotation and spinning around internal axes"
	,'load water.xyz;rotate y 20'
	,'draw line1 250 (atomno=1) (atomno=2);color draw yellow'
	,'draw line2 350 $line1 (atomno=3);color draw white'
	,'spin INTERNAL $line1 30'
	,'spin INTERNAL $line2 30'
	,'spin off;rotate INTERNAL $line1 10;'
	,'spin off;rotate INTERNAL $line2 10;'
	,'#Note that clicking the end of an axis starts it rotating counterclockwise (shift-left click for clockwise)'
	,'rotate INTERNAL $line2 10;'

	,'load caffeine.xyz;set picking SPIN 10; #now pick two atoms'
	,'set picking spin 30'
	,'draw line1 300 (oxygen) (nitrogen);'
	,'spin INTERNAL $line1 30'
	,'draw line2 300 (atomno=9) (atomno=11);'
	,'draw line2 {-2. 0. 0.} {0. 2. 0.};'
	,'spin INTERNAL $line2 30'
	,'spin off'

	,'load caffeine.xyz;set axes 3;select atomno=4;dots on #several new rotate options'
	,'rotate'
	,'rotate -x'
	,'rotate y 10 (atomno=4)'
	,'rotate y 10 INTERNAL (atomno=4)'
	,'rotate z 10 INTERNAL (atomno=4)'
	,'rotate 20 (atomno=8) (atomno=4)'
	,'draw line1 300 (oxygen) (nitrogen)'
	,'rotate 10 INTERNAL $line1'
	,'draw line1 {0 0 0} {3 0 0}'
	,'rotate 30 {0 0 0} {3 0 0}'
	,'rotate 20 INTERNAL '
        ,'set windowCentered off'
	,'center atomno=23'
	,'center atomno=9'
	,'center $line1'
	,'center {0 1 2}'
        ,'set windowCentered on'
        ,'set perspectiveDepth off'
        ,'set perspectiveDepth on'
	,'set windowCentered on;center visible;refresh;set windowcentered off;center atomno=10'
	,'set windowCentered on;center visible;refresh;set windowcentered off;center atomno=20'
	,'load caffeine2.xyz;draw triangle1 150 (atomno=1) (atomno=14) (atomno=15);'
	,'draw line1 200 PERP $triangle1'
	,'draw line2 PERP 400 (atomno=1) (atomno=14) (atomno=15);'
	,'draw plane2 PLANE 400 (atomno=1) (atomno=14) (atomno=15);'
	,'draw triangle1 400'
	,'draw triangle1;color draw red;draw line2 ;color draw blue'
	,'spin INTERNAL $line2 30'
	,'spin off'
	,'load cyclohexane_movie.xyz;draw line1 250 (atomno=2) (atomno=5);draw line2 200 (atomno=4 or atomno=3) (atomno=1 or atomno=6);'
	,'animation on'
	,'animation off;spin INTERNAL $line1 30'
	,'animation off;spin INTERNAL $line2 30'
	,'spin off'
	,'animation off'
	,'frame next'
	,'frame prev'
	,'frame play'
	,'frame playrev'
	,0,"JmolAppletProto.jar")


newDemo(19,"cyclohexane_movie.jpg","chair-chair conformational change in cyclohexane -- with running measurement",
	"load cyclohexane_movie.xyz;|"
	+"measure ALL (atomno=7) (atomno=3) (atomno=4) (atomno=13) # (only in 10.x);|color measure yellow;|"
	+"moveto 3 828 -458 322 123.6;|delay 2;"
	+"animation fps 10; animation on;|"
	+"frame 1;|"
	+"frame next;|frame play;|anim off;|anim on;"
	,1,"JmolAppletProto.jar")
