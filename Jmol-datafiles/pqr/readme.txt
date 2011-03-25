Angel Herraez, 2009 04 19

Example of PQR format, generated from PDB file using PDB2PQR.

PQR format is a format based on pdb, where the occupancy is replaced with the atomic charge and the temperature (or B factor) is replaced with atomic radius (however, the column positions in many pqr files do not match those of pdb files). This gives the acronym: P for pdb, Q for charge, R for radius. Jmol interprets the charge values (property partialcharge) and the radii (property vanderwaals), and can hence use them e.g. in color atoms partialCharge and spacefill.

The PQR format has somewhat uncertain origins, but is used by several computational biology packages, including MEAD, AutoDock and APBS[1], for which it is the primary input format.

PQR format description[2] within APBS documentation. Note that APBS reads PQR loosely, based only on white space delimiters, but Jmol may be more strict about column positions.

PDB files can be converted to PQR by the PDB2PQR software[3], which adds missing hydrogen atoms and calculates the charge and radius parameters from a variety of force fields. 


1.- http://apbs.sourceforge.net/
2.- http://apbs.sourceforge.net/doc/user-guide/index.html#pqr-format
3.- http://pdb2pqr.sourceforge.net/
4.- http://cardon.wustl.edu/MediaWiki/index.php/PQR_format