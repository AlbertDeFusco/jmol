/* $RCSfile$
 * $Author$
 * $Date$
 * $Revision$
 *
 * Copyright (C) 2005 Miguel, Jmol Development
 *
 * Contact: miguel@jmol.org,jmol-developers@lists.sourceforge.net
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SqueezeCube {
  public static void main(String[] argv) {
    System.err.println("SqueezeCube");
    new SqueezeCube(new BufferedReader(new InputStreamReader(System.in)),
                    new BufferedWriter(new OutputStreamWriter(System.out)));
  }

  BufferedReader br;
  BufferedWriter bw;

  String titleLine1;
  String titleLine2;
  String atomCountAndOriginLine;
  String[] volumetricVectorLines = new String[3];
  String[] atomLines;
  String optionalMolecularOrbitalLine;
  float[][][] volumetricData;

  int atomCount;
  boolean negativeAtomCount;
  int[] volumetricCounts = new int[3];
  int countX, countY, countZ;

  int count0, count1, countNeg1;

  SqueezeCube(BufferedReader br, BufferedWriter bw) {
    this.br = br;
    this.bw = bw;

    try {
      readTitleLines();
      readAtomCountAndOriginLine();
      readVolumetricVectorLines();
      readAtomLines();
      readOptionalMolecularOrbitalLine();
      readVolumetricData();
      squeezeVolumetricData(0.001f);

      writeHeader();
      writeVolumetricData();
      bw.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  void readTitleLines() throws Exception {
    System.err.println("readTitleLines");
    titleLine1 = br.readLine();
    titleLine2 = br.readLine();
    System.err.println(titleLine1 + "\n" + titleLine2);
  }

  void readAtomCountAndOriginLine() throws Exception {
    System.err.println("readAtomCountAndOriginLine");
    atomCountAndOriginLine = br.readLine();
    System.err.println(atomCountAndOriginLine);
    StringTokenizer st = new StringTokenizer(atomCountAndOriginLine);
    atomCount = Integer.parseInt(st.nextToken());
    if (atomCount < 0) {
      atomCount = -atomCount;
      negativeAtomCount = true;
    }
  }

  void readVolumetricVectorLines() throws Exception {
    System.err.println("readVolumetricVectorLines");
    for (int i = 0; i < 3; ++i) {
      String vv = br.readLine();
      volumetricVectorLines[i] = vv;
      System.err.println(vv);
      StringTokenizer st = new StringTokenizer(vv);
      volumetricCounts[i] = Integer.parseInt(st.nextToken());
    }
    countX = volumetricCounts[0];
    countY = volumetricCounts[1];
    countZ = volumetricCounts[2];
  }

  void readAtomLines() throws Exception {
    System.err.println("readAtomLines");
    atomLines = new String[atomCount];
    for (int i = 0; i < atomCount; ++i) {
      atomLines[i] = br.readLine();
      System.err.println(atomLines[i]);
    }
  }

  void readOptionalMolecularOrbitalLine() throws Exception {
    if (negativeAtomCount) {
      System.err.println("readOptionalMolecularOrbitalLine");
      optionalMolecularOrbitalLine = br.readLine();
      System.err.println(optionalMolecularOrbitalLine);
    }
  }

  void readVolumetricData() throws Exception {
    System.err.println("readVolumetricData");
    StringTokenizer st = new StringTokenizer("");
    volumetricData = new float[countX][][];
    for (int x = 0; x < countX; ++x) {
      float[][] plane = new float[countY][];
      volumetricData[x] = plane;
      for (int y = 0; y < countY; ++y) {
        float[] strip = new float[countZ];
        plane[y] = strip;
        for (int z = 0; z < countZ; ++z) {
          if (! st.hasMoreTokens()) {
            String line = br.readLine();
            if (line == null) {
              System.err.println("end of file in SqueezeCube?");
              System.err.println("x=" + x + " y=" + y + " z=" + z);
              throw new NullPointerException();
            }
            st = new StringTokenizer(line);
          }
          strip[z] = Float.parseFloat(st.nextToken());
        }
      }
    }
    System.err.println("Successfully read " + countX +
                       " x " + countY +
                       " x " + countZ + " data points");
  }

  int[] scaleFactors = { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000 };

  void squeezeVolumetricData(float minCutoff) {
    for (int x = countX; --x >= 0; )
      for (int y = countY; --y >= 0; )
        for (int z = countZ; --z >= 0; )
          squeezePoint(x, y, z, minCutoff);
    int totalPoints = countX * countY * countZ;
    System.err.println("total points   = " + totalPoints);
    System.err.println("squeezed to  1 = " + count1 + " " +
                       ((count1 * 100) / totalPoints) + "%");
    System.err.println("squeezed to  0 = " + count0 + " " +
                       ((count0 * 100) / totalPoints) + "%");
    System.err.println("squeezed to -1 = " + countNeg1 + " " +
                       ((countNeg1 * 100) / totalPoints) + "%");
    int totalSqueezed = count1 + count0 + countNeg1;
    System.err.println("total squeezed = " + totalSqueezed + " " +
                       ((totalSqueezed * 100) / totalPoints) + "%");
  }

  int[][] offsetsNeighbors =
  {
    { 1, 0, 0},
    {-1, 0, 0},
    { 0, 1, 0},
    { 0,-1, 0},
    { 0, 0, 1},
    { 0, 0,-1}
  };

  void squeezePoint(int x, int y, int z, float minCutoff) {
    if (meAndNeighborsBelowCutoff(x, y, z, minCutoff)) {
      volumetricData[x][y][z] = 0;
      ++count0;
      return;
    }
  }
  
  boolean meAndNeighborsBelowCutoff(int x, int y, int z, float minCutoff) {
    if (! isBelowCutoff(x, y, z, minCutoff))
      return false;
    for (int i = offsetsNeighbors.length; --i >= 0; ) {
      int[] offsetsNeighbor = offsetsNeighbors[i];
      int nX = x + offsetsNeighbor[0];
      int nY = y + offsetsNeighbor[1];
      int nZ = z + offsetsNeighbor[2];
      if (! isInBounds(nX, nY, nZ))
        continue;
      if (! isBelowCutoff(nX, nY, nZ, minCutoff))
        return false;
    }
    return true;
  }

  boolean isInBounds(int x, int y, int z) {
    if (x < 0 || x == countX)
      return false;
    if (y < 0 || y == countY)
      return false;
    if (z < 0 || z == countZ)
      return false;
    return true;
  }

  boolean isBelowCutoff(int x, int y, int z, float cutoff) {
    float d = volumetricData[x][y][z];
    if (d >= 0 && d < cutoff)
      return true;
    if (d < 0 && -d < cutoff)
      return true;
    return false;
  }

  void writeHeader() throws Exception {
    bw.write(titleLine1); bw.newLine();
    bw.write(titleLine2); bw.newLine();
    bw.write(atomCountAndOriginLine); bw.newLine();
    for (int i = 0; i < 3; ++i) {
      bw.write(volumetricVectorLines[i]); bw.newLine();
    }
    for (int i = 0; i < atomCount; ++i) {
      bw.write(atomLines[i]); bw.newLine();
    }
    if (negativeAtomCount) {
      bw.write(optionalMolecularOrbitalLine); bw.newLine();
    }
  }

  void writeVolumetricData() throws Exception {
    for (int x = 0; x < countX; ++x)
      for (int y = 0; y < countY; ++y)
        for (int z = 0; z < countZ; ++z)
          writeOneDataPoint(format(volumetricData[x][y][z]));
    if (outputLineCount != 0)
      bw.newLine();
    System.err.println("total points output=" + totalPointsOutput);
  }

  String format(float d) {
    if (d == 0)
      return "0";
    if (d == 1)
      return "1";
    if (d == -1)
      return "-1";
    return "" + d;
  }

  int outputLineCount;
  int totalPointsOutput;

  void writeOneDataPoint(String formattedData) throws Exception {
    if (outputLineCount == 5) {
      bw.write(formattedData);
      bw.newLine();
      outputLineCount = 0;
      ++totalPointsOutput;
    } else {
      bw.write(formattedData + " ");
      ++outputLineCount;
      ++totalPointsOutput;
    }
  }
}
