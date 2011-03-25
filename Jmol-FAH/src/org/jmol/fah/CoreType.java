/* $RCSfile$
 * $Author$
 * $Date$
 * $Revision$
 *
 * Copyright (C) 2005  The Jmol Development Team
 *
 * Contact: jmol-developers@lists.sf.net
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 *  02111-1307  USA.
 */

package org.jmol.fah;

/**
 * Typesafe enum class for core types
 */
public class CoreType {

  /**
   * Get CoreType from code
   * 
   * @param code Code of the core
   * @return CoreType
   */
  static public CoreType getFromCode(String code) {
    if (code != null) {
      if (code.equalsIgnoreCase(A0GROMACS._code)) {
        return A0GROMACS;
      }
      if (code.equalsIgnoreCase(A3GROMACS._code)) {
        return A3GROMACS;
      }
      if (code.equalsIgnoreCase(A4GROMACS._code)) {
        return A4GROMACS;
      }
      if (code.equalsIgnoreCase(A5GROMACS._code)) {
        return A5GROMACS;
      }
      if (code.equalsIgnoreCase(AMBER._code)) {
       return AMBER;
      }
      if (code.equalsIgnoreCase(ATI_DEV._code)) {
        return ATI_DEV;
       }
      if (code.equalsIgnoreCase(DGROMACS._code)) {
        return DGROMACS;
      }
      if (code.equalsIgnoreCase(DGROMACSB._code)) {
        return DGROMACSB;
      }
      if (code.equalsIgnoreCase(DGROMACSC._code)) {
        return DGROMACSC;
      }
      if (code.equalsIgnoreCase(GBGROMACS._code)) {
        return GBGROMACS;
      }
      if (code.equalsIgnoreCase(GROCVS._code)) {
        return GROCVS;
      }
      if (code.equalsIgnoreCase(GROGPU._code)) {
        return GROGPU;
      }
      if (code.equalsIgnoreCase(GROGPU2._code)) {
        return GROGPU2;
      }
      if (code.equalsIgnoreCase(GROGPU2_MT._code)) {
        return GROGPU2_MT;
      }
      if (code.equalsIgnoreCase(GROMACS._code)) {
        return GROMACS;
      }
      if (code.equalsIgnoreCase(GROMACS_PS3._code)) {
        return GROMACS_PS3;
      }
      if (code.equalsIgnoreCase(GROMACS_SIMT._code)) {
        return GROMACS_SIMT;
      }
      if (code.equalsIgnoreCase(GROMACS_SMP._code)) {
        return GROMACS_SMP;
      }
      if (code.equalsIgnoreCase(GROMACS_ST._code)) {
      	return GROMACS_ST;
      }
      if (code.equalsIgnoreCase(NVIDIA_DEV._code)) {
        return NVIDIA_DEV;
      }
      if (code.equalsIgnoreCase(OPEN_MM_CL._code)) {
        return OPEN_MM_CL;
      }
      if (code.equalsIgnoreCase(OPEN_MM_FERMI._code)) {
        return OPEN_MM_FERMI;
      }
      if (code.equalsIgnoreCase(OPEN_MM_GPU._code)) {
        return OPEN_MM_GPU;
      }
      if (code.equalsIgnoreCase(PROTO_MOL._code)) {
        return PROTO_MOL;
      }
      if (code.equalsIgnoreCase(QMD._code)) {
        return QMD;
      }
      if (code.equalsIgnoreCase(SHARPEN._code)) {
        return SHARPEN;
      }
      if (code.equalsIgnoreCase(TINKER._code)) {
        return TINKER;
      }
      if (code.equalsIgnoreCase(UNLISTED._code)) {
        return UNLISTED;
      }
    }
    return UNKNOWN;
  }

  /**
   * Get CoreType from name
   * 
   * @param name Name of the core
   * @return CoreType
   */
  static public CoreType getFromName(String name) {
    if (name != null) {
      if (name.equalsIgnoreCase(A0GROMACS._name)) {
        return A0GROMACS;
      }
      if (name.equalsIgnoreCase(A3GROMACS._name)) {
        return A3GROMACS;
      }
      if (name.equalsIgnoreCase(A4GROMACS._name)) {
        return A4GROMACS;
      }
      if (name.equalsIgnoreCase(A5GROMACS._name)) {
        return A5GROMACS;
      }
      if (name.equalsIgnoreCase(AMBER._name)) {
        return AMBER;
      }
      if (name.equalsIgnoreCase(ATI_DEV._name)) {
        return ATI_DEV;
      }
      if (name.equalsIgnoreCase(DGROMACS._name)) {
        return DGROMACS;
      }
      if (name.equalsIgnoreCase(DGROMACSB._name)) {
        return DGROMACSB;
      }
      if (name.equalsIgnoreCase(DGROMACSC._name)) {
        return DGROMACSC;
      }
      if (name.equalsIgnoreCase(GBGROMACS._name)) {
        return GBGROMACS;
      }
      if (name.equalsIgnoreCase(GROCVS._name)) {
        return GROCVS;
      }
      if (name.equalsIgnoreCase(GROGPU._name)) {
        return GROGPU;
      }
      if (name.equalsIgnoreCase(GROGPU2._name)) {
        return GROGPU2;
      }
      if (name.equalsIgnoreCase(GROGPU2_MT._name)) {
        return GROGPU2_MT;
      }
      if (name.equalsIgnoreCase(GROMACS._name)) {
        return GROMACS;
      }
      if (name.equalsIgnoreCase(GROMACS_PS3._name)) {
        return GROMACS_PS3;
      }
      if (name.equalsIgnoreCase(GROMACS_SIMT._name)) {
        return GROMACS_SIMT;
      }
      if (name.equalsIgnoreCase(GROMACS_SMP._name)) {
        return GROMACS_SMP;
      }
      if (name.equalsIgnoreCase(GROMACS_ST._name)) {
      	return GROMACS_ST;
      }
      if (name.equalsIgnoreCase(NVIDIA_DEV._name)) {
        return NVIDIA_DEV;
      }
      if (name.equalsIgnoreCase(OPEN_MM_CL._name)) {
        return OPEN_MM_CL;
      }
      if (name.equalsIgnoreCase(OPEN_MM_FERMI._name)) {
        return OPEN_MM_FERMI;
      }
      if (name.equalsIgnoreCase(OPEN_MM_GPU._name)) {
        return OPEN_MM_GPU;
      }
      if (name.equalsIgnoreCase(PROTO_MOL._name)) {
        return PROTO_MOL;
      }
      if (name.equalsIgnoreCase(QMD._name)) {
        return QMD;
      }
      if (name.equalsIgnoreCase(SHARPEN._name)) {
        return SHARPEN;
      }
      if (name.equalsIgnoreCase(TINKER._name)) {
        return TINKER;
      }
      if (name.equalsIgnoreCase(UNLISTED._name)) {
        return UNLISTED;
      }
    }
    return UNKNOWN;
  }

  /**
   * @return Returns the code.
   */
  public String getCode() {
    return this._code;
  }

  /**
   * @return Returns the name.
   */
  public String getName() {
    return this._name;
  }

  /**
   * @return Flag indicating if the core is producing a current.xyz file
   */
  public boolean hasFile() {
    return this._hasFile;
  }

  // Cores
  static public final CoreType UNKNOWN      = new CoreType(null, null, true);
  static public final CoreType A0GROMACS    = new CoreType("Gromacs33", "A0", true); //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType A3GROMACS    = new CoreType("GRO-A3", "A3", false); //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType A4GROMACS    = new CoreType("GRO-A4", "A4", false); //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType A5GROMACS    = new CoreType("GRO-A5", "A5", false); //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType AMBER        = new CoreType("Amber", "A", true); //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType ATI_DEV      = new CoreType("ATI-DEV", "AD", false); //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType DGROMACS     = new CoreType("DGromacs", "DG", true); //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType DGROMACSB    = new CoreType("DGromacsB", "DB", true); //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType DGROMACSC    = new CoreType("DGromacsC", "DC", true); //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType GBGROMACS    = new CoreType("GBGromacs", "GB", true); //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType GROCVS       = new CoreType("GroCVS", "GC", true);  //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType GROGPU       = new CoreType("GroGPU", "GG", false);  //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType GROGPU2      = new CoreType("GroGPU2", "GG2", false);  //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType GROGPU2_MT   = new CoreType("GroGPU2-MT", "GG2MT", false);  //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType GROMACS      = new CoreType("Gromacs", "G", true);  //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType GROMACS_PS3  = new CoreType("Gro-PS3", "G3", false);  //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType GROMACS_SIMT = new CoreType("GroSimT", "GST", true);  //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType GROMACS_SMP  = new CoreType("Gro-SMP", "GS", true);  //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType GROMACS_ST   = new CoreType("GroST", "GT", true);  //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType NVIDIA_DEV   = new CoreType("NVIDIA-DEV", "ND", false); //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType OPEN_MM_CL   = new CoreType("OpenMM_OpenCL", "OC", true); //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType OPEN_MM_FERMI= new CoreType("OpenMMFermi", "OF", true); //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType OPEN_MM_GPU  = new CoreType("OpenMMGPU", "OG", true); //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType PROTO_MOL    = new CoreType("ProtoMol", "PM", true); //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType QMD          = new CoreType("QMD", "Q", true); //$NON-NLS-1$ //$NON-NLS-2$
  static public final CoreType SHARPEN      = new CoreType("Sharpen", "SP", true);   //$NON-NLS-1$//$NON-NLS-2$
  static public final CoreType TINKER       = new CoreType("Tinker", "T", true);   //$NON-NLS-1$//$NON-NLS-2$
  static public final CoreType UNLISTED     = new CoreType("Unlisted", "UL", true);   //$NON-NLS-1$//$NON-NLS-2$

  // Attributes
  private final String _name;
  private final String _code;
  private final boolean _hasFile;

  /**
   * Constructor for CoreType
   * 
   * @param name Name of core
   * @param code Letter code of core
   * @param hasFile Flag indicating the present of a current.xyz file
   */
  private CoreType(String name, String code, boolean hasFile) {
    this._name = name;
    this._code = code;
    this._hasFile = hasFile;
  }
}
