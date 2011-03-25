
Demo = new Array()
title = ""


function newDemo(i,image,caption,script) {
 Demo[i]=new Array()
 Demo[i].image=image
 Demo[i].caption=caption
 Demo[i].script=script
 Demo[i].title = title
 title = ""
}

function newTitle(caption) {
 title = caption
}


/////////////////


n = 0

newTitle("Molecular and Solvent Surfaces")

newDemo(++n,"demo_sasurface.jpg","A simple solvent-accessible surface. These surfaces are very quick to calculate and display. They represent the trace of the center of a spherical solvent probe as it rolls around the molecule.",
	"load 2ptn.cif;moveto 1.0 245 959 140 66.2; isosurface ignore(solvent) sasurface 1.2"
	)
newDemo(++n,"demo_molecular.jpg","A simple solvent-excluded or \"molecular\" surface. These surfaces take considerably more time to calculate, since they involve the creation of rounded troughs.",
	"load 2ptn.cif;moveto 1.0 245 959 140 66.2; select resno <= 25; isosurface ignore(solvent) molecular"
	)

newDemo(++n,"demo_cavity.jpg","Jmol can depict the cavities around a ligand or substrate. ",
	"load 2by9.pdb;moveto 1.0 { -876 -280 393 130.71} 210.14 -31.4 14.8 {23.699003 21.285 22.801003} 29.576609 {0.0 0.0 0.0} -25.99526 106.25879 50.0; select BAM; color white;refresh; select within(5.0, BAM);moveto 1.0 { -876 -280 393 130.71} 210.14 -31.4 14.8 {23.699003 21.285 22.801003} 29.576609 {0.0 0.0 0.0} -25.99526 106.25879 50.0; select within(5.0, BAM); isosurface cav1 ignore(bam or solvent) cavity molecular colorscheme sets translucent 0.3"
	)





newTitle("Planar Slices Through Molecular Surfaces")

newDemo(++n,"demo_molecularslice.jpg","Jmol uses a unique algorithm to calculate surfaces. Each position in space is given a value that is its distance from the nearest surface point. What this means is that a \"surface\" is really an \"isosurface with cutoff 0\". And what that means is that a surface is no different in terms of visualization from a molecular orbital or other atom-based spacial property.",
	"load 2ptn.cif; isosurface plane {1 0 10} {0 1 10} {1 1 10} ignore(solvent) sasurface 0.5; moveto 1.0 { -822 -490 -290 168.25};"
	)
newDemo(++n,"demo_jvxlplanarslice.jpg","A molecular \"slice\" generated from a JVXL file.",
	"load 2ptn.cif; isosurface &quot;2ptn-molecular_slice1.jvxl&quot;; moveto 3.0 -308 401 863 108.1;"
	)

newTitle("Crystallographic Planes")

newDemo(++n,"demo_millerplane1.jpg",'The "hkl" keyword indicates that the next three numbers in braces are Miller indices. Note that all coordinates are implicitly fractional. A faster alternative is to use "solvent 0" instead of "molecular".',
	"load nacl.cif {2 2 2}; unitcell off;axes 0.2;boundbox on; select *; isosurface hkl {1 1 1} resolution 4; moveto 1.0 268 -953 -141 88.0;"
	)
newDemo(++n,"demo_millerplane.jpg",'While both "molecular" and "solvent 0" give an electron-density-like depiction, "solvent 0" is faster.',
	"load nacl.cif {2 2 2}; unitcell off;axes 0.2;boundbox on; select *; isosurface hkl {1 1 1} resolution 4 solvent 0; moveto 1.0 268 -953 -141 88.0; delay 3;restrict none;"
	)





newTitle("Molecular Electrostatic Potentials")


newDemo(++n,"demo_c6h6mepmap.jpg","Molecular electrostatic potentials are calculated from partial charge data present in a file; Jmol cannot calculate these charges. Generally MEP data would be mapped onto a molecular surface, as shown here.",
	"load C6H6.smol;moveto 1.0 { -962 -235 -141 56.81}; isosurface resolution 6 molecular map mep"
	)

newDemo(++n,"demo_c6h6mep.jpg","MEP data can also be rendered directly...",
	"load C6H6.smol;moveto 1.0 { -962 -235 -141 56.81}; isosurface resolution 6 mep"
	)


newDemo(++n,"demo_c6h6mepplane.jpg","...or mapped onto a plane.",
	"load C6H6.smol;moveto 1.0 { -962 -235 -141 56.81}; isosurface resolution 6 contour 41 plane xy map mep"
	)




newTitle("Atomic and Molecular Orbitals")

newDemo(++n,"demo_atomicorbitals.jpg","Simple atomic orbitals can be placed on any atom or, for that matter, anywhere in space. One simply specifies the values of <i>n</i>, <i>l</i>, and <i>m</i>.",
	"isosurface phase atomicOrbital 3 2 1; set axesMolecular;set axesScale 0.5;axes on; moveto 1.0 { 462 -868 -180 47.18} 141"
	)


newDemo(++n,"demo_mo.jpg","Jmol can render many standard calculated molecular orbitals without the need for CUBE files. Most ab initio (STO-3G, 3-21G*, 6-31++G**, etc.) and semi-empirical (AM1, PM3) basis sets can be rendered. For GAUSSIAN, use keywords <b>pop=full gfprint</b>; the orbitals will be in frame 2.",
	"load ch2o_homo.mo;wireframe 0.05;spacefill off;moveto 1.0 940 -340 -34 67.0; mo 1" 
	)

newDemo(++n,"demo_mohomo.jpg",'For files specifying orbital occupancy, the keywords "homo" and "lumo" can be used. Several other options are available with the MO command.',
	"load C6H6.smol;moveto 1.0 -821 -377 -430 69.2; mo cutoff 0.008; mo resolution 8; mo homo -1;"
	)

newDemo(++n,"demo_isomo.jpg","The isosurface command can also be used with molecular orbitals in order to create special effects.",
	"load ch2o_homo.mo;wireframe 0.05;spacefill off;moveto 1.0 940 -340 -34 67.0; isosurface color yellow purple mo 1 translucent"
	)

newDemo(++n,"demo_moslice.jpg","Planar slices through molecular orbitals can be made using the isosurface command (Jmol 11.3.2).",
	"load ch2o_homo.mo;wireframe 0.05;spacefill off;moveto 1.0 940 -340 -34 67.0; isosurface color absolute -0.1 0.1 plane xz mo 1"
	)


newTitle("Other Surface Objects")


newDemo(++n,"demo_sphere.jpg","Spheres and ellipsoids can be created using the isosurface command.",
	"load caffeine.xyz;moveto { -960 -278 -23 85.64}; isosurface i1 center {substructure( \"[C]1[C][C][N][C][N]1\")} sphere 1.0 translucent blue; co_xyz = ({atomno=11}.xyz - {atomno=5}.xyz) * 0.7; x = co_xyz.x;y = co_xyz.y;z = co_xyz.z; isosurface i2 center {atomno=11} ellipsoid {@x @y @z 0.5} translucent yellow;"
	)


newDemo(++n,"demo_plane.jpg","Planes can be created using the draw command. ",
	"load caffeine.xyz;moveto { -960 -278 -23 85.64}; draw d1 PERP 150 PLANE (atomno=3) (atomno=5) \"red\" opaque red; draw t2 (atomno=9) (atomno=10) (atomno=11) scale 1.5 translucent; moveto 1.0 { -996 -84 -15 61.39}"
	)


/////////////////

var spt = false

if(location.search.length>1)eval(location.search.substring(1,1000))


function getTitles() {
  if (spt) return
  var s = "<ul>"
  for (var i = 1; i <= n; i++) if (Demo[i].title)
    s += "<li><a href=#"+Demo[i].title.replace(/ /g,"")+">"+Demo[i].title+"</a></li>"
  s += "</ul>"
  document.write(s)
}

function getDemos() {  
 for (var i = 0; i < n;)
   if (spt)
     getSpt(++i, 250)
   else
     getDemoCell(++i)
}

function getSpt(i, wh) {
  if (!Demo[i] || !Demo[i].caption)return
  var s = "# "+i+" "+Demo[i].caption+";\n"
  s +="theimg = \"../img/"+Demo[i].image+"\";"
  s += Demo[i].script+";"
  s += "write image "+wh+" "+wh+" @theimg;\n \n"
  document.write("<pre>"+s+"</pre>")
}


function getDemoCell(i,width){
  if (!Demo[i] || !Demo[i].caption)return;
  if(!width) width=250
  var cap = (Demo[i].title ? "<a name=\""+Demo[i].title.replace(/ /g,"")+"\">"+Demo[i].title+"</a>": "")
  document.write ("<h2>"+cap+"</h2>")
  var s="<a name=demo"+i+" target=_blank href=surfacedemo.htm?scripting=1;demo="+i+"><img src=img/"+Demo[i].image
	+" title=\"click to load the Jmol applet in a pop-up window\" width="+width+" height="+width+"/></a>"
  s="<table cellpadding=5 cellspacing=0 class=demotable3 width=800px><tr><td width=10>"+i+"</td>"
	+"<td class=democaptioncell width=250>"+Demo[i].caption+"</td>"
	+"<td class=demoimagecell width=250>"+s+"</td>"
	+"<td class=democodecell width=290><code>"+Demo[i].script.split("; ").join("<br /><br />")+"</code></td>"
	+"</tr></table>"
  document.write("<table border = 1 style='background-color:#E0E0E0'><tr><td><table cellpadding=0 cellspacing=0><tr><td class=demoframe width=800"+" valign=top>"+s+"</td></tr></table></td></tr></table>")
}

function getDemoCell3(i1,i2,i3,width){
  if(!width) width=200
  document.write("<table cellspacing=0 cellpadding=0><tr><td valign=top>")
  getDemoCell(i1,width)
  document.write("</td><td valign=top>")
  getDemoCell(i2,width)
  document.write("</td><td valign=top>")
  getDemoCell(i3,width)
  document.write("</td></tr></table>")
}

