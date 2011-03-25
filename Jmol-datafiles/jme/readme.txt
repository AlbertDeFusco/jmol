Angel Herraez, 2009 04 19

Examples of "JME format" from JME Molecular Editor,
http://www.molinspiration.com/jme/

Jmol already had a basic reader for this, so let's have some sample files too.
I created them using
http://www.molinspiration.com/jme/doc/jme_ex1.html

The format is a single line of text
(http://www.molinspiration.com/jme/doc/jme_functions.html):

1. 
The format of the JME String is as follows
natoms nbonds (atomic_symbol x_coord y_coord) for all atoms (atom1 atom2 bond_order) for all bonds
==> this is read correctly by Jmol (even admits isotopes if they are entered into the drawing using the 'X' tool, see example)

2.
For stereo bonds the bond order is -1 for up and -2 for down from the first to the second atom
==> this is interpreted by Jmol leading to unusual wedge-shaped bonds, but the parser seems not to differentiate up/down (see AMP_noH.jme example)

3.
JME tries to guess valence model (number of hydrogens) on the atom, you can force your model by enclosing the atom symbol in square brackets (i.e. [NH2+])
==> not supported by Jmol

4.
Molecules in multipart system are separated by the | character. 
==> supported by Jmol (both molecules in the same frame)

5.
Components of the reaction are separated by the > character. The JME string for the reaction is thus "reactant1 | reactant 2 ... > modulator(s) > product(s)"
==> not supported by Jmol


Comments apply to Jmol 11.7.32
