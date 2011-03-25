//Jmol predefined set information adapted from JmolConstants.java
//Bob Hanson hansonr@stolaf.edu 3:54 PM 12/23/2004

MyDefs=new Array()
style=";"
nimages=0
_a = specialAtomNames = new Array(
	
    ////////////////////////////////////////////////////////////////
    // The ordering of these entries can be changed ... BUT ...
    // the offsets must be kept consistent with the ATOMID definitions
    // below.
    //
    // null is entry 0
    // The first 32 entries are reserved for null + 31 'distinguishing atoms'
    // see definitions below. 32 is magical because bits are used in an
    // int to distinguish groups. If we need more then we can go to 64
    // bits by using a long ... but code must change.
    //
    // All entries less than 64 are backbone entries
    // But the number 64 is not magical and could be easily changed
    ////////////////////////////////////////////////////////////////
    null, // 0
    
    // protein backbone
    //
    "N",   //  1 - amino nitrogen
    "CA",  //  2 - alpha carbon
    "C",   //  3 - carbonyl carbon
    null, // used to be carbonyl oxygen, now can be O or O1

    // nucleic acid backbone sugar
    //
    "O5'", //  5 - sugar 5' oxygen
    "C5'", //  6 - sugar 5' carbon
    "C4'", //  7 - sugar ring 4' carbon
    "C3'", //  8 - sugar ring 3' carbon
    "O3'", //  9 - sugar 3' oxygen
    "C2'", // 10 - sugar ring 2' carbon
    "C1'", // 11 - sugar ring 1' carbon
    // Phosphorus is not required for a nucleic group because
    // at the terminus it could have H5T or O5T ...
    "P",   // 12 - phosphate phosphorus

    // ... But we need to distinguish phosphorus separately because
    // it could be found in phosphorus-only nucleic polymers

    // reserved for future expansion ... lipids & carbohydrates
    null, null, null,       // 13 - 15
    null, null, null, null, // 16 - 19
    null, null, null, null, // 20 - 23
    null, null, null, null, // 24 - 27
    null, null, null, null, // 28 - 31

    
    // anything that could be considered part of the 'backbone'
    // goes in this next group
    
    // protein backbone
    //
    "OXT", // 32 - second carbonyl oxygen, C-terminus only

    // protein backbone hydrogens
    //
    "H",   // 33 - amino hydrogen
    // these appear on the N-terminus end of 1ALE & 1LCD
    "1H",  // 34 - N-terminus hydrogen
    "2H",  // 35 - second N-terminus Hydrogen
    "3H",  // 36 - third N-terminus Hydrogen
    "HA",  // 37 - H on alpha carbon
    "1HA", // 38 - H on alpha carbon in Gly only
    "2HA", // 39 - 1ALE calls the two GLY hdrogens 1HA & 2HA

    "O",   // 40 - carbonyl oxygen
    "O1",  // 41 - carbonyl oxygen in some protein residues (4THN)
    null,  // 42
    null,  // 43
    null,  // 44

    // Terminal nuclic acid
    "H5T", // 45 - 5' terminus hydrogen which replaces P + O1P + O2P
    "O5T", // 46 - 5' terminus oxygen which replaces P + O1P + O2P
    "O1P", // 47 - first equivalent oxygen on phosphorus of phosphate
    "O2P", // 48 - second equivalent oxygen on phosphorus of phosphate
    "O4'", // 49 - sugar ring 4' oxygen ... not present in +T ... maybe others
    "O2'", // 50 - sugar 2' oxygen, unique to RNA

    // nucleic acid backbone hydrogens
    //
    "1H5'", // 51 - first  equivalent H on sugar 5' carbon
    "2H5'", // 52 - second  equivalent H on sugar 5' carbon 
    "H4'",  // 53 - H on sugar ring 4' carbon
    "H3'",  // 54 - H on sugar ring 3' carbon
    "1H2'", // 55 - first equivalent H on sugar ring 2' carbon
    "2H2'", // 56 - second equivalent H on sugar ring 2' carbon
    "2HO'", // 57 - H on sugar 2' oxygen, unique to RNA 
    "H1'",  // 58 - H on sugar ring 1' carbon 
    //
    "H3T",  // 59 - 3' terminus hydrogen
    //
    null,   // 60
    null,   // 61
    null,   // 62
    null,   // 63
   
    // everything before this (1 - 63, but not 0) is backbone

    // nucleic acid bases
    //
    "N1",   // 64
    "C2",   // 65
    "N3",   // 66
    "C4",   // 67
    "C5",   // 68
    "C6",   // 69 -- currently defined as the nucleotide wing
            // this determines the vector for the sheet
            // could be changed if necessary

    // pyrimidine O2
    //
    "O2",   // 70

    // purine stuff
    //
    "N7",   // 71
    "C8",   // 72
    "N9",   // 73

    // nucleic acid base ring functional groups
    //
    "N4",  // 74 - base ring N4, unique to C
    "N2",  // 75 - base amino N2, unique to G
    "N6",  // 76 - base amino N6, unique to A
    "C5M", // 77 - base methyl carbon, unique to T

    "O6",  // 78 - base carbonyl O6, only in G and I
    "O4",  // 79 - base carbonyl O4, only in T and U
    "S4",  // 80 - base thiol sulfur, unique to thio-U
"")

GROUPID_PROLINE = 15;
GROUPID_PURINE_MIN = 24;
GROUPID_PURINE_LAST = 29;
GROUPID_PYRIMIDINE_MIN = 30;
GROUPID_PYRIMIDINE_LAST = 35;
GROUPID_GUANINE = 26;
GROUPID_PLUS_GUANINE = 27;
GROUPID_GUANINE_1_MIN = 40;
GROUPID_GUANINE_1_LAST = 46;
GROUPID_GUANINE_2_MIN = 55;
GROUPID_GUANINE_2_LAST = 57;

GROUPID_AMINO_MAX = 23;
GROUPID_SHAPELY_MAX = 36;


_g = predefinedGroup3Names = new Array(
    "", //  0 this is the null group
    
    "ALA", // 1
    "ARG",
    "ASN",
    "ASP",
    "CYS",
    "GLN",
    "GLU",
    "GLY",
    "HIS",
    "ILE",
    "LEU",
    "LYS",
    "MET",
    "PHE",
    "PRO", // 15 Proline
    "SER",
    "THR",
    "TRP",
    "TYR",
    "VAL",
    "ASX", // 21 ASP/ASN ambiguous
    "GLX", // 22 GLU/GLN ambiguous
    "UNK", // 23 unknown -- 23

    // if you change these numbers you *must* update
    // the predefined sets in script.Token.java

    "A", // 24 the purines
    "+A",
    "G", // 26
    "+G",
    "I", // 28
    "+I",
    "C", // 30 the pyrimidines
    "+C",
    "T", // 32
    "+T",
    "U", // 34
    "+U",

    "1MA", // 36
    "AMO",
    "5MC",
    "OMC",
    "1MG", // 40
    "2MG",
    "M2G",
    "7MG",
    "G7M",
    "OMG", // 45
    "YG",
    "QUO",
    "H2U",
    "5MU",
    "4SU", // 50
    "PSU",
    
    "AMP",
    "ADP",
    "ATP",
    
    "GMP", // 55
    "GDP",
    "GTP",
    
    "IMP",
    "IDP",
    "ITP", // 60
    
    "CMP",
    "CDP",
    "CTP",
    
    "TMP",
    "TDP", // 65
    "TTP",
    
    "UMP",
    "UDP",
    "UTP", // 69

    // water && solvent
    "HOH", // 70
    "DOD", // 71
    "WAT", // 72
    // ions && solvent
    "PO4", // 73 phosphate ions
    "SO4", // 74 sulphate ions

"")


PreDefinedSets=new Array(
    "// protein related",

    "protein hardwired",

    "@amino _g>0 & _g<=23",
    "@acidic asp,glu",
    "@basic arg,his,lys",
    "@charged acidic,basic",
    "@negative acidic",
    "@positive basic",
    "@neutral amino & !(acidic,basic)",
    "@polar amino & !hydrophobic",
    "@cyclic his,phe,pro,trp,tyr",
    "@acyclic amino & !cyclic",
    "@aliphatic ala,gly,ile,leu,val",
    "@aromatic his,phe,trp,tyr",
    "@buried ala,cys,ile,leu,met,phe,trp,val",
    "@surface !buried",
    "@hydrophobic ala,gly,ile,leu,met,phe,pro,trp,tyr,val",
    "@ligand hetero & !solvent",
    "@mainchain backbone",
    "@small ala,gly,ser",
    "@medium asn,asp,cys,pro,thr,val",
    "@large arg,glu,gln,his,ile,leu,lys,met,phe,trp,tyr",

    "// nucleic acid related",

    "nucleic hardwired",
    "dna hardwired",
    "rna hardwired",
    "purine hardwired",
    "pyrimidine hardwired",

    "@c nucleic & within(group,_a=74)",
    "@g nucleic & within(group,_a=75)",
    "@cg c,g",
    "@a nucleic & within(group,_a=76)",
    "@t nucleic & within(group,_a=77)",
    "@at a,t",
    "@i nucleic & within(group,_a=78) & !g",
    "@u nucleic & within(group,_a=79) & !t",
    "@tu nucleic & within(group,_a=80)",

    "// solvent related",

    "@solvent _g>=70 & _g<=74", // water or ions
    "@hoh water",
    "@water _g>=70&_g<=72",
    "@ions _g=70,_g=71",

    "// structure related",

    "@alpha _a=2",
    "@backbone (protein,nucleic) & _a>0 & _a<=63",
    "@sidechain (protein,nucleic) & !backbone",
    "@base nucleic & !backbone",
    "@turn _structure=1",
    "@sheet _structure=2",
    "@helix _structure=3",

    "@bonded _bondedcount>0",

    "hetero hardwired",

"")

function adddef(skey,sdef,itype){
	var i=MyDefs.length
	MyDefs[i]=new Array()
	MyDefs[i].skey=skey
	MyDefs[i].sdef=sdef
	MyDefs[i].itype=itype
}


function getPreDefined(){
 var sout=""
 var S=new Array()
 var sinfo=""
 var skey=""
 var sdef=""
 var pt=0

 adddef("groups","",0)
 for(var i=0;i<_g.length;i++){
	sinfo=_g[i]
	if(sinfo){
		adddef(sinfo,sinfo,2)
	}
 }


 for(var i=0;i<PreDefinedSets.length;i++){
	sinfo=PreDefinedSets[i]
	if(sinfo){
		pt=sinfo.indexOf(" ")
		skey=sinfo.substring(0,pt)
		sdef=sinfo.substring(pt+1,sinfo.length)
		if(sinfo.indexOf("//")==0){
			adddef(sinfo.substring(3,sinfo.length),"",0)
		}else if(sinfo.indexOf("@")==0){
			skey=skey.substring(1,skey.length)
			if(sdef.indexOf("_")>=0)sdef=skey
			adddef(skey,sdef,1)
		}else if(sdef=="hardwired"){
			adddef(skey,skey,2)
		}
	}
 }
}getPreDefined()

function showdefs(){
 var sout=""
 nimages=0
 for(var i=0;i<MyDefs.length;i++){
	if(MyDefs[i].itype==0){
		if(sout)sout+="\n</select><\p>"
		sout+="<p>"+MyDefs[i].skey+"\n<br><img name=\"img"+nimages+"\" src=\"blank.gif\"><select name=\"select"+nimages+"\" onchange=\"showthedef("+nimages+",value)\" onkeypress=\"showthedef("+nimages+",value,1)\">"
		sout+="\n<option selected=\"selected\" value=\"-99\">---------------------------</option>"
		nimages++
	}else{
		sout+="\n<option value=\""+i+"\">"+MyDefs[i].skey+"</option>"
	}
 }
 if(sout)sout="<p>Select...<\p>"+sout+"\n</select></p>"
 return sout
}

function showthedef(n,si,delay){
 if(delay==2){
	delay=0
	var d=document.getElementById("select"+n)
	si=d[d.selectedIndex].value
 }
 if(delay){
	setTimeout("showthedef("+n+","+si+",2)",100)
	return
 }
 var ithis=parseInt(si+"")
 if(ithis<0)n=-1
 for(var i=0;i<nimages;i++)document["img"+i].src=(n==i?"arrow.gif":"blank.gif") 
 showscript(0,0,(ithis>=0?"rockets off;select "+MyDefs[ithis].skey+style+"#"+MyDefs[ithis].sdef:""))
}
