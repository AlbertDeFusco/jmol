/* $RCSfile$
 * $Author$
 * $Date$
 * $Revision$
 *
 * Copyright (C) 2005  Miguel, Jmol Development, www.jmol.org
 *
 * Contact: miguel@jmol.org
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

package org.jmol.util;


public class TestIntInt2ObjHash extends junit.framework.TestCase {

  public TestIntInt2ObjHash() {
  }

  @Override
  public void setUp() {
  }

  @Override
  public void tearDown() {
  }

  public void testOne() {
    IntInt2ObjHash h = new IntInt2ObjHash(10);
    for (int i = 0; i < 10; ++i)
      h.put(i, i, Integer.valueOf(i));
    for (int i = 0; i < 10; ++i)
      assertEquals(((Integer)h.get(i, i)).intValue(), i);
  }

  public void test256() {
    IntInt2ObjHash h = new IntInt2ObjHash(256);
    for (int i = 0; i < 256; ++i)
      h.put(i, i, Integer.valueOf(i));
    for (int i = 0; i < 256; ++i)
      assertEquals(((Integer)h.get(i, i)).intValue(), i);
  }

  public void test257() {
    IntInt2ObjHash h = new IntInt2ObjHash(256);
    for (int i = 0; i < 257; ++i)
      h.put(i, i, Integer.valueOf(i));
    for (int i = 0; i < 257; ++i)
      assertEquals(((Integer)h.get(i, i)).intValue(), i);
  }

  public void testUpTo1000() {
    for (int i = 1; i < 1000; i += 100)
      tryOne(i);
  }

  void tryOne(int count) {
    IntInt2ObjHash h = new IntInt2ObjHash(4);
    for (int i = 0; i < count; ++i)
      h.put(i, i, Integer.valueOf(i));
    //    dumpHash(h);
    for (int i = 0; i < count; ++i)
      assertEquals(((Integer)h.get(i, i)).intValue(), i);
  }

  void dumpHash(IntInt2ObjHash h) {
    Logger.info("dumping hash:" + h);
    Logger.info("h.entryCount=" + h.entryCount);
    IntInt2ObjHash.Entry[] entries = h.entries;
    for (int i = 0; i < entries.length; ++i) {
      StringBuffer log = new StringBuffer();
      log.append(i).append(": ");
      for (IntInt2ObjHash.Entry e = entries[i]; e != null; e = e.next) {
        log.append(e.key1).append(",").append(e.key2).append(" ");
      }
      Logger.info(log.toString());
    }
  }

  public void test1000() {
    IntInt2ObjHash h = new IntInt2ObjHash();
    for (int i = 0; i < 1000; ++i)
      h.put(i, -i, Integer.valueOf(i));
    for (int i = 0; i < 1000; ++i)
      assertEquals(((Integer)h.get(i, -i)).intValue(), i);
  }

}
