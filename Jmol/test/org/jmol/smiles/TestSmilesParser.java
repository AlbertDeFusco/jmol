/*
 * JUnit TestCase for the SmilesParser
 */

package org.jmol.smiles;

import junit.framework.TestCase;

public class TestSmilesParser extends TestCase {

  public TestSmilesParser(String arg0) {
    super(arg0);
  }

  /*
   * Test methods for 'org.jmol.smiles.SmilesParser'
   * Using examples from Chapter 1 of Smiles tutorial
   */
  public void testChapter1_01() {    // Test [H+]
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomH = molecule.addAtom();
    atomH.setCharge(1);
    atomH.setSymbol("H");
    checkMolecule("[H+]", molecule);
  }
  public void testChapter1_02() {    // Test C
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC = molecule.addAtom();
    atomC.setSymbol("C");
    addHydrogen(molecule, atomC);
    addHydrogen(molecule, atomC);
    addHydrogen(molecule, atomC);
    addHydrogen(molecule, atomC);
    checkMolecule("C", molecule);
  }
  public void testChapter1_03() {    // Test O
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomO = molecule.addAtom();
    atomO.setSymbol("O");
    addHydrogen(molecule, atomO);
    addHydrogen(molecule, atomO);
    checkMolecule("O", molecule);
  }
  public void testChapter1_04() {    // Test [OH3+]
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomO = molecule.addAtom();
    atomO.setCharge(1);
    atomO.setSymbol("O");
    addHydrogen(molecule, atomO);
    addHydrogen(molecule, atomO);
    addHydrogen(molecule, atomO);
    checkMolecule("[OH3+]", molecule);
  }
  public void testChapter1_05() {    // Test [2H]O[2H]
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomH1 = molecule.addAtom();
    atomH1.setAtomicMass(2);
    atomH1.setSymbol("H");
    SmilesAtom atomO = molecule.addAtom();
    atomO.setSymbol("O");
    createBond(atomH1, atomO, SmilesBond.TYPE_SINGLE, false);
    SmilesAtom atomH2 = molecule.addAtom();
    atomH2.setAtomicMass(2);
    atomH2.setSymbol("H");
    createBond(atomO, atomH2, SmilesBond.TYPE_SINGLE, false);
    checkMolecule("[2H]O[2H]", molecule);
  }
  public void testChapter1_06() {    // Test [Au]
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomAu = molecule.addAtom();
    atomAu.setSymbol("Au");
    checkMolecule("[Au]", molecule);
  }
  public void testChapter1_07() {    // Test CCO
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    createBond(atomC1, atomC2, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomO = molecule.addAtom();
    atomO.setSymbol("O");
    createBond(atomC2, atomO, SmilesBond.TYPE_SINGLE, true);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomO);
    checkMolecule("CCO", molecule);
  }
  public void testChapter1_08() {    // Test O=C=O
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomO1 = molecule.addAtom();
    atomO1.setSymbol("O");
    SmilesAtom atomC = molecule.addAtom();
    atomC.setSymbol("C");
    createBond(atomO1, atomC, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomO2 = molecule.addAtom();
    atomO2.setSymbol("O");
    createBond(atomC, atomO2, SmilesBond.TYPE_DOUBLE, true);
    checkMolecule("O=C=O", molecule);
  }
  public void testChapter1_09() {    // Test C#N
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC = molecule.addAtom();
    atomC.setSymbol("C");
    SmilesAtom atomN = molecule.addAtom();
    atomN.setSymbol("N");
    createBond(atomC, atomN, SmilesBond.TYPE_TRIPLE, true);
    addHydrogen(molecule, atomC);
    checkMolecule("C#N", molecule);
  }
  public void testChapter1_10() {    // Test CC(=O)O
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    createBond(atomC1, atomC2, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomO1 = molecule.addAtom();
    atomO1.setSymbol("O");
    createBond(atomC2, atomO1, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomO2 = molecule.addAtom();
    atomO2.setSymbol("O");
    createBond(atomC2, atomO2, SmilesBond.TYPE_SINGLE, true);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomO2);
    checkMolecule("CC(=O)O", molecule);
  }
  public void testChapter1_11() {    // Test C1CCCCC1
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    SmilesAtom atomC3 = molecule.addAtom();
    atomC3.setSymbol("C");
    SmilesAtom atomC4 = molecule.addAtom();
    atomC4.setSymbol("C");
    SmilesAtom atomC5 = molecule.addAtom();
    atomC5.setSymbol("C");
    SmilesAtom atomC6 = molecule.addAtom();
    atomC6.setSymbol("C");
    createBond(atomC1, atomC2, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC2, atomC3, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC3, atomC4, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC4, atomC5, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC5, atomC6, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC1, atomC6, SmilesBond.TYPE_SINGLE, true);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC3);
    addHydrogen(molecule, atomC3);
    addHydrogen(molecule, atomC4);
    addHydrogen(molecule, atomC4);
    addHydrogen(molecule, atomC5);
    addHydrogen(molecule, atomC5);
    addHydrogen(molecule, atomC6);
    addHydrogen(molecule, atomC6);
    checkMolecule("C1CCCCC1", molecule);
  }
  public void testChapter1_12() {    // Test C1CC2CCCCC2CC1
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    SmilesAtom atomC3 = molecule.addAtom();
    atomC3.setSymbol("C");
    SmilesAtom atomC4 = molecule.addAtom();
    atomC4.setSymbol("C");
    SmilesAtom atomC5 = molecule.addAtom();
    atomC5.setSymbol("C");
    SmilesAtom atomC6 = molecule.addAtom();
    atomC6.setSymbol("C");
    SmilesAtom atomC7 = molecule.addAtom();
    atomC7.setSymbol("C");
    SmilesAtom atomC8 = molecule.addAtom();
    atomC8.setSymbol("C");
    SmilesAtom atomC9 = molecule.addAtom();
    atomC9.setSymbol("C");
    SmilesAtom atomC0 = molecule.addAtom();
    atomC0.setSymbol("C");
    createBond(atomC1, atomC0, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC1, atomC2, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC2, atomC3, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC3, atomC8, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC3, atomC4, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC4, atomC5, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC5, atomC6, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC6, atomC7, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC7, atomC8, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC8, atomC9, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC9, atomC0, SmilesBond.TYPE_SINGLE, true);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC3);
    addHydrogen(molecule, atomC4);
    addHydrogen(molecule, atomC4);
    addHydrogen(molecule, atomC5);
    addHydrogen(molecule, atomC5);
    addHydrogen(molecule, atomC6);
    addHydrogen(molecule, atomC6);
    addHydrogen(molecule, atomC7);
    addHydrogen(molecule, atomC7);
    addHydrogen(molecule, atomC8);
    addHydrogen(molecule, atomC9);
    addHydrogen(molecule, atomC9);
    addHydrogen(molecule, atomC0);
    addHydrogen(molecule, atomC0);
    checkMolecule("C1CC2CCCCC2CC1", molecule);
  }
  public void testChapter1_13() {    // Test c1ccccc1
    // Not implemented
  }
  public void testChapter1_14() {    // Test [Na+].[O-]c1ccccc1
    // Not implemented
  }
  public void testChapter1_15() {    // Test C/C=C/C
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    createBond(atomC1, atomC2, SmilesBond.TYPE_DIRECTIONAL_1, true);
    SmilesAtom atomC3 = molecule.addAtom();
    atomC3.setSymbol("C");
    createBond(atomC2, atomC3, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomC4 = molecule.addAtom();
    atomC4.setSymbol("C");
    createBond(atomC3, atomC4, SmilesBond.TYPE_DIRECTIONAL_1, true);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC3);
    addHydrogen(molecule, atomC4);
    addHydrogen(molecule, atomC4);
    addHydrogen(molecule, atomC4);
    checkMolecule("C/C=C/C", molecule);
  }
  public void testChapter1_16() {    // Test N[C@@H](C)C(=O)O
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomN = molecule.addAtom();
    atomN.setSymbol("N");
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setChiralClass(SmilesAtom.STEREOCHEMISTRY_TETRAHEDRAL);
    atomC1.setChiralOrder(2);
    atomC1.setSymbol("C");
    createBond(atomN, atomC1, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    createBond(atomC1, atomC2, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomC3 = molecule.addAtom();
    atomC3.setSymbol("C");
    createBond(atomC1, atomC3, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomO1 = molecule.addAtom();
    atomO1.setSymbol("O");
    createBond(atomC3, atomO1, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomO2 = molecule.addAtom();
    atomO2.setSymbol("O");
    createBond(atomC3, atomO2, SmilesBond.TYPE_SINGLE, true);
    addHydrogen(molecule, atomN);
    addHydrogen(molecule, atomN);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomO2);
    checkMolecule("N[C@@H](C)C(=O)O", molecule);
  }
  public void testChapter1_17() {    // Test O[C@H]1CCCC[C@H]1O
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomO1 = molecule.addAtom();
    atomO1.setSymbol("O");
    addHydrogen(molecule, atomO1);
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setChiralClass(SmilesAtom.STEREOCHEMISTRY_TETRAHEDRAL);
    atomC1.setChiralOrder(1);
    atomC1.setSymbol("C");
    addHydrogen(molecule, atomC1);
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC2);
    SmilesAtom atomC3 = molecule.addAtom();
    atomC3.setSymbol("C");
    addHydrogen(molecule, atomC3);
    addHydrogen(molecule, atomC3);
    SmilesAtom atomC4 = molecule.addAtom();
    atomC4.setSymbol("C");
    addHydrogen(molecule, atomC4);
    addHydrogen(molecule, atomC4);
    SmilesAtom atomC5 = molecule.addAtom();
    atomC5.setSymbol("C");
    addHydrogen(molecule, atomC5);
    addHydrogen(molecule, atomC5);
    SmilesAtom atomC6 = molecule.addAtom();
    atomC6.setChiralClass(SmilesAtom.STEREOCHEMISTRY_TETRAHEDRAL);
    atomC6.setChiralOrder(1);
    atomC6.setSymbol("C");
    addHydrogen(molecule, atomC6);
    SmilesAtom atomO2 = molecule.addAtom();
    atomO2.setSymbol("O");
    addHydrogen(molecule, atomO2);
    createBond(atomO1, atomC1, SmilesBond.TYPE_SINGLE, true);
    SmilesBond b = createBond(atomC1, null, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC1, atomC2, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC2, atomC3, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC3, atomC4, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC4, atomC5, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC5, atomC6, SmilesBond.TYPE_SINGLE, true);
    b.setAtom2(atomC6);
    createBond(atomC6, atomO2, SmilesBond.TYPE_SINGLE, true);
    checkMolecule("O[C@H]1CCCC[C@H]1O", molecule);
  }
  
  /*
   * Test methods for 'org.jmol.smiles.SmilesParser'
   * Using examples from Chapter 2 of Smiles tutorial
   */
  public void testChapter2_01() {    // Test [S]
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomS = molecule.addAtom();
    atomS.setSymbol("S");
    checkMolecule("[S]", molecule);
  }
  public void testChapter2_02() {    // Test [Au]
    testChapter1_06();
  }
  public void testChapter2_03() {    // Test C
    testChapter1_02();
  }
  public void testChapter2_04() {    // Test P
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomP = molecule.addAtom();
    atomP.setSymbol("P");
    addHydrogen(molecule, atomP);
    addHydrogen(molecule, atomP);
    addHydrogen(molecule, atomP);
    checkMolecule("P", molecule);
  }
  public void testChapter2_05() {    // Test S
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomS = molecule.addAtom();
    atomS.setSymbol("S");
    addHydrogen(molecule, atomS);
    addHydrogen(molecule, atomS);
    checkMolecule("S", molecule);
  }
  public void testChapter2_06() {    // Test Cl
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomCl = molecule.addAtom();
    atomCl.setSymbol("Cl");
    addHydrogen(molecule, atomCl);
    checkMolecule("Cl", molecule);
  }
  public void testChapter2_07() {    // Test [OH-]
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomO = molecule.addAtom();
    atomO.setCharge(-1);
    atomO.setSymbol("O");
    addHydrogen(molecule, atomO);
    checkMolecule("[OH-]", molecule);
  }
  public void testChapter2_08() {    // Test [OH-1]
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomO = molecule.addAtom();
    atomO.setCharge(-1);
    atomO.setSymbol("O");
    addHydrogen(molecule, atomO);
    checkMolecule("[OH-1]", molecule);
  }
  public void testChapter2_09() {    // Test [Fe+2]
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomFe = molecule.addAtom();
    atomFe.setCharge(2);
    atomFe.setSymbol("Fe");
    checkMolecule("[Fe+2]", molecule);
  }
  public void testChapter2_10() {    // Test [Fe++]
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomFe = molecule.addAtom();
    atomFe.setCharge(2);
    atomFe.setSymbol("Fe");
    checkMolecule("[Fe++]", molecule);
  }
  public void testChapter2_11() {    // Test [235U]
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomU = molecule.addAtom();
    atomU.setAtomicMass(235);
    atomU.setSymbol("U");
    checkMolecule("[235U]", molecule);
  }
  public void testChapter2_12() {    // Test [*+2]
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atom = molecule.addAtom();
    atom.setCharge(2);
    atom.setSymbol("*");
    checkMolecule("[*+2]", molecule);
  }
  
  /*
   * Test methods for 'org.jmol.smiles.SmilesParser'
   * Using examples from Chapter 3 of Smiles tutorial
   */
  public void testChapter3_01() {    // Test CC
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    createBond(atomC1, atomC2, SmilesBond.TYPE_SINGLE, true);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC2);
    checkMolecule("CC", molecule);
  }
  public void testChapter3_02() {    // Test C-C
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    createBond(atomC1, atomC2, SmilesBond.TYPE_SINGLE, true);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC2);
    checkMolecule("C-C", molecule);
  }
  public void testChapter3_03() {    // Test [CH3]-[CH3]
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    createBond(atomC1, atomC2, SmilesBond.TYPE_SINGLE, true);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC2);
    checkMolecule("[CH3]-[CH3]", molecule);
  }
  public void testChapter3_04() {    // Test C=O
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC = molecule.addAtom();
    atomC.setSymbol("C");
    SmilesAtom atomO = molecule.addAtom();
    atomO.setSymbol("O");
    createBond(atomC, atomO, SmilesBond.TYPE_DOUBLE, true);
    addHydrogen(molecule, atomC);
    addHydrogen(molecule, atomC);
    checkMolecule("C=O", molecule);
  }
  public void testChapter3_05() {    // Test C#N
    testChapter1_09();
  }
  public void testChapter3_06() {    // Test C=C
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    createBond(atomC1, atomC2, SmilesBond.TYPE_DOUBLE, true);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC2);
    checkMolecule("C=C", molecule);
  }
  public void testChapter3_07() {    // Test cc
    // Not implemented
  }
  public void testChapter3_08() {    // Test C=CC=C
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    createBond(atomC1, atomC2, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomC3 = molecule.addAtom();
    atomC3.setSymbol("C");
    createBond(atomC2, atomC3, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomC4 = molecule.addAtom();
    atomC4.setSymbol("C");
    createBond(atomC3, atomC4, SmilesBond.TYPE_DOUBLE, true);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC3);
    addHydrogen(molecule, atomC4);
    addHydrogen(molecule, atomC4);
    checkMolecule("C=CC=C", molecule);
  }
  public void testChapter3_09() {    // Test cccc
    // Not implemented
  }
  
  /*
   * Test methods for 'org.jmol.smiles.SmilesParser'
   * Using examples from Chapter 4 of Smiles tutorial
   */
  public void testChapter4_01() {    // Test CC(C)C(=O)O
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    createBond(atomC1, atomC2, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomC3 = molecule.addAtom();
    atomC3.setSymbol("C");
    createBond(atomC2, atomC3, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomC4 = molecule.addAtom();
    atomC4.setSymbol("C");
    createBond(atomC2, atomC4, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomO1 = molecule.addAtom();
    atomO1.setSymbol("O");
    createBond(atomC4, atomO1, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomO2 = molecule.addAtom();
    atomO2.setSymbol("O");
    createBond(atomC4, atomO2, SmilesBond.TYPE_SINGLE, true);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC3);
    addHydrogen(molecule, atomC3);
    addHydrogen(molecule, atomC3);
    addHydrogen(molecule, atomO2);
    checkMolecule("CC(C)C(=O)O", molecule);
  }
  public void testChapter4_02() {    // Test FC(F)F
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomF1 = molecule.addAtom();
    atomF1.setSymbol("F");
    SmilesAtom atomC = molecule.addAtom();
    atomC.setSymbol("C");
    createBond(atomF1, atomC, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomF2 = molecule.addAtom();
    atomF2.setSymbol("F");
    createBond(atomC, atomF2, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomF3 = molecule.addAtom();
    atomF3.setSymbol("F");
    createBond(atomC, atomF3, SmilesBond.TYPE_SINGLE, true);
    addHydrogen(molecule, atomC);
    checkMolecule("FC(F)F", molecule);
  }
  public void testChapter4_03() {    // Test C(F)(F)F
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC = molecule.addAtom();
    atomC.setSymbol("C");
    SmilesAtom atomF1 = molecule.addAtom();
    atomF1.setSymbol("F");
    createBond(atomC, atomF1, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomF2 = molecule.addAtom();
    atomF2.setSymbol("F");
    createBond(atomC, atomF2, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomF3 = molecule.addAtom();
    atomF3.setSymbol("F");
    createBond(atomC, atomF3, SmilesBond.TYPE_SINGLE, true);
    addHydrogen(molecule, atomC);
    checkMolecule("C(F)(F)F", molecule);
  }
  public void testChapter4_04() {    // Test O=Cl(=O)(=O)[O-]
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomO1 = molecule.addAtom();
    atomO1.setSymbol("O");
    SmilesAtom atomCl = molecule.addAtom();
    atomCl.setSymbol("Cl");
    createBond(atomO1, atomCl, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomO2 = molecule.addAtom();
    atomO2.setSymbol("O");
    createBond(atomCl, atomO2, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomO3 = molecule.addAtom();
    atomO3.setSymbol("O");
    createBond(atomCl, atomO3, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomO4 = molecule.addAtom();
    atomO4.setCharge(-1);
    atomO4.setSymbol("O");
    createBond(atomCl, atomO4, SmilesBond.TYPE_SINGLE, true);
    checkMolecule("O=Cl(=O)(=O)[O-]", molecule);
  }
  public void testChapter4_05() {    // Test Cl(=O)(=O)(=O)[O-]
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomCl = molecule.addAtom();
    atomCl.setSymbol("Cl");
    SmilesAtom atomO1 = molecule.addAtom();
    atomO1.setSymbol("O");
    createBond(atomCl, atomO1, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomO2 = molecule.addAtom();
    atomO2.setSymbol("O");
    createBond(atomCl, atomO2, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomO3 = molecule.addAtom();
    atomO3.setSymbol("O");
    createBond(atomCl, atomO3, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomO4 = molecule.addAtom();
    atomO4.setCharge(-1);
    atomO4.setSymbol("O");
    createBond(atomCl, atomO4, SmilesBond.TYPE_SINGLE, true);
    checkMolecule("Cl(=O)(=O)(=O)[O-]", molecule);
  }
  public void testChapter4_06() {    // Test CCCC(C(=O)O)CCC
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    createBond(atomC1, atomC2, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomC3 = molecule.addAtom();
    atomC3.setSymbol("C");
    createBond(atomC2, atomC3, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomC4 = molecule.addAtom();
    atomC4.setSymbol("C");
    createBond(atomC3, atomC4, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomC5 = molecule.addAtom();
    atomC5.setSymbol("C");
    createBond(atomC4, atomC5, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomO1 = molecule.addAtom();
    atomO1.setSymbol("O");
    createBond(atomC5, atomO1, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomO2 = molecule.addAtom();
    atomO2.setSymbol("O");
    createBond(atomC5, atomO2, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomC6 = molecule.addAtom();
    atomC6.setSymbol("C");
    createBond(atomC4, atomC6, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomC7 = molecule.addAtom();
    atomC7.setSymbol("C");
    createBond(atomC6, atomC7, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomC8 = molecule.addAtom();
    atomC8.setSymbol("C");
    createBond(atomC7, atomC8, SmilesBond.TYPE_SINGLE, true);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC3);
    addHydrogen(molecule, atomC3);
    addHydrogen(molecule, atomC4);
    addHydrogen(molecule, atomO2);
    addHydrogen(molecule, atomC6);
    addHydrogen(molecule, atomC6);
    addHydrogen(molecule, atomC7);
    addHydrogen(molecule, atomC7);
    addHydrogen(molecule, atomC8);
    addHydrogen(molecule, atomC8);
    addHydrogen(molecule, atomC8);
    checkMolecule("CCCC(C(=O)O)CCC", molecule);
  }
  
  /*
   * Test methods for 'org.jmol.smiles.SmilesParser'
   * Using examples from Chapter 5 of Smiles tutorial
   */
  public void testChapter5_01() {    // Test C1CCCCC1
    testChapter1_11();
  }
  public void testChapter5_02() {    // Test C1=CCCCC1
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    SmilesAtom atomC3 = molecule.addAtom();
    atomC3.setSymbol("C");
    SmilesAtom atomC4 = molecule.addAtom();
    atomC4.setSymbol("C");
    SmilesAtom atomC5 = molecule.addAtom();
    atomC5.setSymbol("C");
    SmilesAtom atomC6 = molecule.addAtom();
    atomC6.setSymbol("C");
    createBond(atomC1, atomC6, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC1, atomC2, SmilesBond.TYPE_DOUBLE, true);
    createBond(atomC2, atomC3, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC3, atomC4, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC4, atomC5, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC5, atomC6, SmilesBond.TYPE_SINGLE, true);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC3);
    addHydrogen(molecule, atomC3);
    addHydrogen(molecule, atomC4);
    addHydrogen(molecule, atomC4);
    addHydrogen(molecule, atomC5);
    addHydrogen(molecule, atomC5);
    addHydrogen(molecule, atomC6);
    addHydrogen(molecule, atomC6);
    checkMolecule("C1=CCCCC1", molecule);
  }
  public void testChapter5_03() {    // Test C=1CCCCC1
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    SmilesAtom atomC3 = molecule.addAtom();
    atomC3.setSymbol("C");
    SmilesAtom atomC4 = molecule.addAtom();
    atomC4.setSymbol("C");
    SmilesAtom atomC5 = molecule.addAtom();
    atomC5.setSymbol("C");
    SmilesAtom atomC6 = molecule.addAtom();
    atomC6.setSymbol("C");
    createBond(atomC1, atomC6, SmilesBond.TYPE_DOUBLE, true);
    createBond(atomC1, atomC2, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC2, atomC3, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC3, atomC4, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC4, atomC5, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC5, atomC6, SmilesBond.TYPE_SINGLE, true);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC3);
    addHydrogen(molecule, atomC3);
    addHydrogen(molecule, atomC4);
    addHydrogen(molecule, atomC4);
    addHydrogen(molecule, atomC5);
    addHydrogen(molecule, atomC5);
    addHydrogen(molecule, atomC6);
    checkMolecule("C=1CCCCC1", molecule);
  }
  public void testChapter5_04() {    // Test C1CCCCC=1
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    SmilesAtom atomC3 = molecule.addAtom();
    atomC3.setSymbol("C");
    SmilesAtom atomC4 = molecule.addAtom();
    atomC4.setSymbol("C");
    SmilesAtom atomC5 = molecule.addAtom();
    atomC5.setSymbol("C");
    SmilesAtom atomC6 = molecule.addAtom();
    atomC6.setSymbol("C");
    createBond(atomC1, atomC6, SmilesBond.TYPE_DOUBLE, true);
    createBond(atomC1, atomC2, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC2, atomC3, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC3, atomC4, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC4, atomC5, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC5, atomC6, SmilesBond.TYPE_SINGLE, true);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC3);
    addHydrogen(molecule, atomC3);
    addHydrogen(molecule, atomC4);
    addHydrogen(molecule, atomC4);
    addHydrogen(molecule, atomC5);
    addHydrogen(molecule, atomC5);
    addHydrogen(molecule, atomC6);
    checkMolecule("C1CCCCC=1", molecule);
  }
  public void testChapter5_05() {    // Test C=1CCCCC=1
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    SmilesAtom atomC3 = molecule.addAtom();
    atomC3.setSymbol("C");
    SmilesAtom atomC4 = molecule.addAtom();
    atomC4.setSymbol("C");
    SmilesAtom atomC5 = molecule.addAtom();
    atomC5.setSymbol("C");
    SmilesAtom atomC6 = molecule.addAtom();
    atomC6.setSymbol("C");
    createBond(atomC1, atomC6, SmilesBond.TYPE_DOUBLE, true);
    createBond(atomC1, atomC2, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC2, atomC3, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC3, atomC4, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC4, atomC5, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC5, atomC6, SmilesBond.TYPE_SINGLE, true);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC3);
    addHydrogen(molecule, atomC3);
    addHydrogen(molecule, atomC4);
    addHydrogen(molecule, atomC4);
    addHydrogen(molecule, atomC5);
    addHydrogen(molecule, atomC5);
    addHydrogen(molecule, atomC6);
    checkMolecule("C=1CCCCC=1", molecule);
  }
  public void testChapter5_06() {    // Test c12c(cccc1)cccc2
    // Not implemented
  }
  public void testChapter5_07() {    // Test c1cc2ccccc2cc1
    // Not implemented
  }
  public void testChapter5_08() {    // Test c1ccccc1c2ccccc2
    // Not implemented
  }
  public void testChapter5_09() {    // Test c1ccccc1c1ccccc1
    // Not implemented
  }
  
  /*
   * Test methods for 'org.jmol.smiles.SmilesParser'
   * Using examples from Chapter 6 of Smiles tutorial
   */
  public void testChapter6_01() {    // Test [Na+].[Cl-]
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomNa = molecule.addAtom();
    atomNa.setCharge(1);
    atomNa.setSymbol("Na");
    SmilesAtom atomCl = molecule.addAtom();
    atomCl.setCharge(-1);
    atomCl.setSymbol("Cl");
  // checkMolecule("[Na+].[Cl-]", molecule);  // Jmol does not support '.'
  }
  public void testChapter6_02() {    // Test [Na+].[O-]c1ccccc1
    // Not implemented
  }
  public void testChapter6_03() {    // Test c1cc([O-].[Na+])ccc1
    // Not implemented
  }
  public void testChapter6_04() {    // Test C1.O2.C12
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    SmilesAtom atomO = molecule.addAtom();
    atomO.setSymbol("O");
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    createBond(atomC1, atomC2, SmilesBond.TYPE_SINGLE, true);
    createBond(atomO, atomC2, SmilesBond.TYPE_SINGLE, true);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomO);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC2);
//    checkMolecule("C1.O2.C12", molecule); // Jmol does not support '.'
  }
  public void testChapter6_05() {    // Test CCO
    testChapter1_07();
  }
  
  /*
   * Test methods for 'org.jmol.smiles.SmilesParser'
   * Using examples from Chapter 7 of Smiles tutorial
   */
  public void testChapter7_01() {    // Test C
    testChapter1_02();
  }
  public void testChapter7_02() {    // Test [C]
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC = molecule.addAtom();
    atomC.setSymbol("C");
    checkMolecule("[C]", molecule);
  }
  public void testChapter7_03() {    // Test [12C]
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC = molecule.addAtom();
    atomC.setAtomicMass(12);
    atomC.setSymbol("C");
    checkMolecule("[12C]", molecule);
  }
  public void testChapter7_04() {    // Test [13C]
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC = molecule.addAtom();
    atomC.setAtomicMass(13);
    atomC.setSymbol("C");
    checkMolecule("[13C]", molecule);
  }
  public void testChapter7_05() {    // Test [13CH4]
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC = molecule.addAtom();
    atomC.setAtomicMass(13);
    atomC.setSymbol("C");
    addHydrogen(molecule, atomC);
    addHydrogen(molecule, atomC);
    addHydrogen(molecule, atomC);
    addHydrogen(molecule, atomC);
    checkMolecule("[13CH4]", molecule);
  }
  public void testChapter7_06() {    // Test F/C=C/F
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomF1 = molecule.addAtom();
    atomF1.setSymbol("F");
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    createBond(atomF1, atomC1, SmilesBond.TYPE_DIRECTIONAL_1, true);
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    createBond(atomC1, atomC2, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomF2 = molecule.addAtom();
    atomF2.setSymbol("F");
    createBond(atomC2, atomF2, SmilesBond.TYPE_DIRECTIONAL_1, true);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC2);
    checkMolecule("F/C=C/F", molecule);
  }
  public void testChapter7_07() {    // Test F\C=C\F
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomF1 = molecule.addAtom();
    atomF1.setSymbol("F");
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    createBond(atomF1, atomC1, SmilesBond.TYPE_DIRECTIONAL_2, true);
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    createBond(atomC1, atomC2, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomF2 = molecule.addAtom();
    atomF2.setSymbol("F");
    createBond(atomC2, atomF2, SmilesBond.TYPE_DIRECTIONAL_2, true);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC2);
    checkMolecule("F\\C=C\\F", molecule);
  }
  public void testChapter7_08() {    // Test F/C=C\F
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomF1 = molecule.addAtom();
    atomF1.setSymbol("F");
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    createBond(atomF1, atomC1, SmilesBond.TYPE_DIRECTIONAL_1, true);
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    createBond(atomC1, atomC2, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomF2 = molecule.addAtom();
    atomF2.setSymbol("F");
    createBond(atomC2, atomF2, SmilesBond.TYPE_DIRECTIONAL_2, true);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC2);
    checkMolecule("F/C=C\\F", molecule);
  }
  public void testChapter7_09() {    // Test F\C=C/F
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomF1 = molecule.addAtom();
    atomF1.setSymbol("F");
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    createBond(atomF1, atomC1, SmilesBond.TYPE_DIRECTIONAL_2, true);
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    createBond(atomC1, atomC2, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomF2 = molecule.addAtom();
    atomF2.setSymbol("F");
    createBond(atomC2, atomF2, SmilesBond.TYPE_DIRECTIONAL_1, true);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC2);
    checkMolecule("F\\C=C/F", molecule);
  }
  public void testChapter7_10() {    // Test F/C=C/C=C/C
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomF = molecule.addAtom();
    atomF.setSymbol("F");
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    createBond(atomF, atomC1, SmilesBond.TYPE_DIRECTIONAL_1, true);
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    createBond(atomC1, atomC2, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomC3 = molecule.addAtom();
    atomC3.setSymbol("C");
    createBond(atomC2, atomC3, SmilesBond.TYPE_DIRECTIONAL_1, true);
    SmilesAtom atomC4 = molecule.addAtom();
    atomC4.setSymbol("C");
    createBond(atomC3, atomC4, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomC5 = molecule.addAtom();
    atomC5.setSymbol("C");
    createBond(atomC4, atomC5, SmilesBond.TYPE_DIRECTIONAL_1, true);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC3);
    addHydrogen(molecule, atomC4);
    addHydrogen(molecule, atomC5);
    addHydrogen(molecule, atomC5);
    addHydrogen(molecule, atomC5);
    checkMolecule("F/C=C/C=C/C", molecule);
  }
  public void testChapter7_11() {    // Test F/C=C/C=CC
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomF = molecule.addAtom();
    atomF.setSymbol("F");
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    createBond(atomF, atomC1, SmilesBond.TYPE_DIRECTIONAL_1, true);
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    createBond(atomC1, atomC2, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomC3 = molecule.addAtom();
    atomC3.setSymbol("C");
    createBond(atomC2, atomC3, SmilesBond.TYPE_DIRECTIONAL_1, true);
    SmilesAtom atomC4 = molecule.addAtom();
    atomC4.setSymbol("C");
    createBond(atomC3, atomC4, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomC5 = molecule.addAtom();
    atomC5.setSymbol("C");
    createBond(atomC4, atomC5, SmilesBond.TYPE_SINGLE, true);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC3);
    addHydrogen(molecule, atomC4);
    addHydrogen(molecule, atomC5);
    addHydrogen(molecule, atomC5);
    addHydrogen(molecule, atomC5);
    checkMolecule("F/C=C/C=CC", molecule);
  }
  public void testChapter7_12() {    // Test N[C@@H](C)C(=O)O
    testChapter1_16();
  }
  public void testChapter7_13() {    // Test N[C@H](C)C(=O)O
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomN = molecule.addAtom();
    atomN.setSymbol("N");
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setChiralClass(SmilesAtom.STEREOCHEMISTRY_TETRAHEDRAL);
    atomC1.setChiralOrder(1);
    atomC1.setSymbol("C");
    createBond(atomN, atomC1, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    createBond(atomC1, atomC2, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomC3 = molecule.addAtom();
    atomC3.setSymbol("C");
    createBond(atomC1, atomC3, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomO1 = molecule.addAtom();
    atomO1.setSymbol("O");
    createBond(atomC3, atomO1, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomO2 = molecule.addAtom();
    atomO2.setSymbol("O");
    createBond(atomC3, atomO2, SmilesBond.TYPE_SINGLE, true);
    addHydrogen(molecule, atomN);
    addHydrogen(molecule, atomN);
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomO2);
    checkMolecule("N[C@H](C)C(=O)O", molecule);
  }
  public void testChapter7_14() {    // Test O[C@H]1CCCC[C@H]1O
    testChapter1_17();
  }
  public void testChapter7_15() {    // Test C1C[C@H]2CCCC[C@H]2CC1
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    addHydrogen(molecule, atomC1);
    addHydrogen(molecule, atomC1);
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setSymbol("C");
    addHydrogen(molecule, atomC2);
    addHydrogen(molecule, atomC2);
    SmilesAtom atomC3 = molecule.addAtom();
    atomC3.setChiralClass(SmilesAtom.STEREOCHEMISTRY_TETRAHEDRAL);
    atomC3.setChiralOrder(1);
    atomC3.setSymbol("C");
    addHydrogen(molecule, atomC3);
    SmilesAtom atomC4 = molecule.addAtom();
    atomC4.setSymbol("C");
    addHydrogen(molecule, atomC4);
    addHydrogen(molecule, atomC4);
    SmilesAtom atomC5 = molecule.addAtom();
    atomC5.setSymbol("C");
    addHydrogen(molecule, atomC5);
    addHydrogen(molecule, atomC5);
    SmilesAtom atomC6 = molecule.addAtom();
    atomC6.setSymbol("C");
    addHydrogen(molecule, atomC6);
    addHydrogen(molecule, atomC6);
    SmilesAtom atomC7 = molecule.addAtom();
    atomC7.setSymbol("C");
    addHydrogen(molecule, atomC7);
    addHydrogen(molecule, atomC7);
    SmilesAtom atomC8 = molecule.addAtom();
    atomC8.setChiralClass(SmilesAtom.STEREOCHEMISTRY_TETRAHEDRAL);
    atomC8.setChiralOrder(1);
    addHydrogen(molecule, atomC8);
    atomC8.setSymbol("C");
    SmilesAtom atomC9 = molecule.addAtom();
    atomC9.setSymbol("C");
    addHydrogen(molecule, atomC9);
    addHydrogen(molecule, atomC9);
    SmilesAtom atomC0 = molecule.addAtom();
    atomC0.setSymbol("C");
    addHydrogen(molecule, atomC0);
    addHydrogen(molecule, atomC0);
    createBond(atomC1, atomC0, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC1, atomC2, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC2, atomC3, SmilesBond.TYPE_SINGLE, true);
    SmilesBond b = createBond(atomC3, null, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC3, atomC4, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC4, atomC5, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC5, atomC6, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC6, atomC7, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC7, atomC8, SmilesBond.TYPE_SINGLE, true);
    b.setAtom2(atomC8);
    createBond(atomC8, atomC9, SmilesBond.TYPE_SINGLE, true);
    createBond(atomC9, atomC0, SmilesBond.TYPE_SINGLE, true);
    checkMolecule("C1C[C@H]2CCCC[C@H]2CC1", molecule);
  }
  public void testChapter7_16() {    // Test OC(Cl)=[C@]=C(C)F
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomO = molecule.addAtom();
    atomO.setSymbol("O");
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    createBond(atomO, atomC1, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomCl = molecule.addAtom();
    atomCl.setSymbol("Cl");
    createBond(atomC1, atomCl, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setChiralClass(SmilesAtom.STEREOCHEMISTRY_ALLENE);
    atomC2.setChiralOrder(1);
    atomC2.setSymbol("C");
    createBond(atomC1, atomC2, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomC3 = molecule.addAtom();
    atomC3.setSymbol("C");
    createBond(atomC2, atomC3, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomC4 = molecule.addAtom();
    atomC4.setSymbol("C");
    createBond(atomC3, atomC4, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomF = molecule.addAtom();
    atomF.setSymbol("F");
    createBond(atomC3, atomF, SmilesBond.TYPE_SINGLE, true);
    addHydrogen(molecule, atomO);
    addHydrogen(molecule, atomC4);
    addHydrogen(molecule, atomC4);
    addHydrogen(molecule, atomC4);
    checkMolecule("OC(Cl)=[C@]=C(C)F", molecule);
  }
  public void testChapter7_17() {    // Test OC(Cl)=[C@AL1]=C(C)F
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomO = molecule.addAtom();
    atomO.setSymbol("O");
    SmilesAtom atomC1 = molecule.addAtom();
    atomC1.setSymbol("C");
    createBond(atomO, atomC1, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomCl = molecule.addAtom();
    atomCl.setSymbol("Cl");
    createBond(atomC1, atomCl, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomC2 = molecule.addAtom();
    atomC2.setChiralClass(SmilesAtom.STEREOCHEMISTRY_ALLENE);
    atomC2.setChiralOrder(1);
    atomC2.setSymbol("C");
    createBond(atomC1, atomC2, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomC3 = molecule.addAtom();
    atomC3.setSymbol("C");
    createBond(atomC2, atomC3, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomC4 = molecule.addAtom();
    atomC4.setSymbol("C");
    createBond(atomC3, atomC4, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomF = molecule.addAtom();
    atomF.setSymbol("F");
    createBond(atomC3, atomF, SmilesBond.TYPE_SINGLE, true);
    addHydrogen(molecule, atomO);
    addHydrogen(molecule, atomC4);
    addHydrogen(molecule, atomC4);
    addHydrogen(molecule, atomC4);
    checkMolecule("OC(Cl)=[C@AL1]=C(C)F", molecule);
  }
  public void testChapter7_18() {    // Test F[Po@SP1](Cl)(Br)I
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomF = molecule.addAtom();
    atomF.setSymbol("F");
    SmilesAtom atomPo = molecule.addAtom();
    atomPo.setChiralClass(SmilesAtom.STEREOCHEMISTRY_SQUARE_PLANAR);
    atomPo.setChiralOrder(1);
    atomPo.setSymbol("Po");
    createBond(atomF, atomPo, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomCl = molecule.addAtom();
    atomCl.setSymbol("Cl");
    createBond(atomPo, atomCl, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomBr = molecule.addAtom();
    atomBr.setSymbol("Br");
    createBond(atomPo, atomBr, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomI = molecule.addAtom();
    atomI.setSymbol("I");
    createBond(atomPo, atomI, SmilesBond.TYPE_SINGLE, true);
    checkMolecule("F[Po@SP1](Cl)(Br)I", molecule);
  }
  public void testChapter7_19() {    // Test O=C[As@](F)(Cl)(Br)S
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomO = molecule.addAtom();
    atomO.setSymbol("O");
    SmilesAtom atomC = molecule.addAtom();
    atomC.setSymbol("C");
    createBond(atomO, atomC, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomAs = molecule.addAtom();
    atomAs.setChiralClass(SmilesAtom.STEREOCHEMISTRY_TRIGONAL_BIPYRAMIDAL);
    atomAs.setChiralOrder(1);
    atomAs.setSymbol("As");
    createBond(atomC, atomAs, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomF = molecule.addAtom();
    atomF.setSymbol("F");
    createBond(atomAs, atomF, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomCl = molecule.addAtom();
    atomCl.setSymbol("Cl");
    createBond(atomAs, atomCl, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomBr = molecule.addAtom();
    atomBr.setSymbol("Br");
    createBond(atomAs, atomBr, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomS = molecule.addAtom();
    atomS.setSymbol("S");
    createBond(atomAs, atomS, SmilesBond.TYPE_SINGLE, true);
    addHydrogen(molecule, atomC);
    addHydrogen(molecule, atomS);
    checkMolecule("O=C[As@](F)(Cl)(Br)S", molecule);
  }
  public void testChapter7_20() {    // Test O=C[Co@](F)(Cl)(Br)(I)S
    SmilesSearch molecule = new SmilesSearch();
    SmilesAtom atomO = molecule.addAtom();
    atomO.setSymbol("O");
    SmilesAtom atomC = molecule.addAtom();
    atomC.setSymbol("C");
    createBond(atomO, atomC, SmilesBond.TYPE_DOUBLE, true);
    SmilesAtom atomCo = molecule.addAtom();
    atomCo.setChiralClass(SmilesAtom.STEREOCHEMISTRY_OCTAHEDRAL);
    atomCo.setChiralOrder(1);
    atomCo.setSymbol("Co");
    createBond(atomC, atomCo, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomF = molecule.addAtom();
    atomF.setSymbol("F");
    createBond(atomCo, atomF, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomCl = molecule.addAtom();
    atomCl.setSymbol("Cl");
    createBond(atomCo, atomCl, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomBr = molecule.addAtom();
    atomBr.setSymbol("Br");
    createBond(atomCo, atomBr, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomI = molecule.addAtom();
    atomI.setSymbol("I");
    createBond(atomCo, atomI, SmilesBond.TYPE_SINGLE, true);
    SmilesAtom atomS = molecule.addAtom();
    atomS.setSymbol("S");
    createBond(atomCo, atomS, SmilesBond.TYPE_SINGLE, true);
    addHydrogen(molecule, atomC);
    addHydrogen(molecule, atomS);
    checkMolecule("O=C[Co@](F)(Cl)(Br)(I)S", molecule);
  }
  
  /**
   * Check that the SMILES parsing is correct
   * 
   * @param smiles SMILES string
   * @param expected SMILES molecule
   */
  private static void checkMolecule(String smiles, SmilesSearch expected) {
    assertTrue(new SmilesMatcher().areEqual(smiles, expected));
  }
  
  public SmilesBond createBond(SmilesAtom atom1, SmilesAtom atom2, int bondType, boolean isExplicitH) {
    SmilesBond b = new SmilesBond(atom1, atom2, bondType, false);
    if (!isExplicitH || atom2 == null)
      return b;
    if (atom1.getElementNumber() == 1)
      addExplicitH(atom2);
    if (atom2 !=  null && atom2.getElementNumber() == 1)
      addExplicitH(atom1);
    return b;
  }
  

  private void addExplicitH(SmilesAtom bonded) {
    int n = bonded.getExplicitHydrogenCount();
    if (n < 0)
      n = 0;
    bonded.setExplicitHydrogenCount(n + 1);
  }

  /**
   * Adds an hydrogen
   * 
   * @param molecule Molecule in which the hydrogen is added
   * @param bonded Other atom to bond to
   */
  private void addHydrogen(SmilesSearch molecule, SmilesAtom bonded) {
    // no longer testable this way -- SmilesParser does not add H atoms
    SmilesAtom atomH = molecule.addAtom();
    atomH.setSymbol("H");
    if (bonded != null) {
      createBond(bonded, atomH, SmilesBond.TYPE_SINGLE, false);
    }
  }
  
/*  
 * 
 * abandoned -- this just tested to see if the to molecules were
 * identically formed, not if they would match by a search.
 * 
  private static boolean areMoleculesEqual(
          SmilesSearch molecule1,
          SmilesSearch molecule2) {
    if ((molecule1 == null) || (molecule2 == null)) {
      Logger.error("Molecule null");
      return false;
    }
    if (molecule1.getPatternAtomCount() != molecule2.getPatternAtomCount()) {
      Logger.error(
          "Atoms count (" +
          molecule1.getPatternAtomCount() + "," +
          molecule2.getPatternAtomCount() + ")");
      return false;
    }
    for (int i = molecule1.getPatternAtomCount(); --i >= 0; ) {
      SmilesAtom atom1 = molecule1.getAtom(i);
      SmilesAtom atom2 = molecule2.getAtom(i);
      if ((atom1 == null) || (atom2 == null)) {
        Logger.error("Atom " + i + " null");
        return false;
      }
      if (atom1.getAtomicMass() != atom2.getAtomicMass()) {
        Logger.error(
            "Atom " + i + " atomic mass (" +
            atom1.getAtomicMass() + "," +
            atom2.getAtomicMass() + ")");
        return false;
      }
      if (atom1.getCovalentBondCount() != atom2.getCovalentBondCount()) {
        Logger.error(
            "Atom " + i + " bonds count (" +
            atom1.getCovalentBondCount() + "," +
            atom2.getCovalentBondCount() + ")");
        return false;
      }
      for (int j = 0; j < atom1.getCovalentBondCount(); j++) {
        SmilesBond bond1 = atom1.getBond(j);
        SmilesBond bond2 = atom2.getBond(j);
        if ((bond1 == null) || (bond2 == null)) {
          Logger.error(
              "Atom " + i + ", bond " + j + " null (" +
              bond1 + "," + bond2 + ")");
          return false;
        }
        if (bond1.getBondType() != bond2.getBondType()) {
          Logger.error(
              "Atom " + i + ", bond " + j + " bond type (" +
              bond1.getBondType() + "," +
              bond2.getBondType() + ")");
          return false;
        }
        if (bond1.getAtom1().getIndex() != bond2.getAtom1().getIndex()) {
          Logger.error(
              "Atom " + i + ", bond " + j + " atom1 number (" +
              bond1.getAtom1().getIndex() + "," +
              bond2.getAtom1().getIndex() + ")");
          return false;
        }
        if (bond1.getAtom2().getIndex() != bond2.getAtom2().getIndex()) {
          Logger.error(
              "Atom " + i + ", bond " + j + " atom2 number (" +
              bond1.getAtom2().getIndex() + "," +
              bond2.getAtom2().getIndex() + ")");
          return false;
        }
      }
      if (atom1.getCharge() != atom2.getCharge()) {
        Logger.error(
            "Atom " + i + " charge (" +
            atom1.getCharge() + "," +
            atom2.getCharge() + ")");
        return false;
      }
      if (atom1.getChiralClass() != atom2.getChiralClass()) {
        Logger.error(
            "Atom " + i + " chiral class (" +
            atom1.getChiralClass() + "," +
            atom2.getChiralClass() + ")");
        return false;
      }
      if (atom1.getChiralOrder() != atom2.getChiralOrder()) {
        Logger.error(
            "Atom " + i + " chiral order (" +
            atom1.getChiralOrder() + "," +
            atom2.getChiralOrder() + ")");
        return false;
      }
      if (atom1.getElementNumber() != atom2.getElementNumber()) {
        Logger.error(
            "Atom " + i + " atomicNumber (" +
            atom1.getElementNumber() + "," +
            atom2.getElementNumber() + ")");
        return false;
      }
      if (atom1.isAromatic() != atom2.isAromatic()) {
        Logger.error(
            "Atom " + i + " isAromatic (" +
            atom1.isAromatic() + "," +
            atom2.isAromatic() + ")");
        return false;
      }
    }
    return true;
  }
*/  
}
