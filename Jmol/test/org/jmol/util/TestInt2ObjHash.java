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


public class TestInt2ObjHash extends junit.framework.TestCase {

  public TestInt2ObjHash() {
  }

  @Override
  public void setUp() {
  }

  @Override
  public void tearDown() {
  }

  public void testOne() {
    Int2ObjHash h = new Int2ObjHash(10);
    for (int i = 0; i < 10; ++i)
      h.put(i, Integer.valueOf(i));
    for (int i = 0; i < 10; ++i)
      assertEquals(((Integer)h.get(i)).intValue(), i);
  }

  public void test256() {
    Int2ObjHash h = new Int2ObjHash(256);
    for (int i = 0; i < 256; ++i)
      h.put(i, Integer.valueOf(i));
    for (int i = 0; i < 256; ++i)
      assertEquals(((Integer)h.get(i)).intValue(), i);
  }

  public void test257() {
    Int2ObjHash h = new Int2ObjHash(256);
    for (int i = 0; i < 257; ++i)
      h.put(i, Integer.valueOf(i));
    for (int i = 0; i < 257; ++i)
      assertEquals(((Integer)h.get(i)).intValue(), i);
  }

  public void testUpTo1000() {
    for (int i = 1; i < 1000; i += 100)
      tryOne(i);
  }

  void tryOne(int count) {
    Int2ObjHash h = new Int2ObjHash(4);
    for (int i = 0; i < count; ++i)
      h.put(i, Integer.valueOf(i));
    //    dumpHash(h);
    for (int i = 0; i < count; ++i)
      assertEquals(((Integer)h.get(i)).intValue(), i);
  }

  void dumpHash(Int2ObjHash h) {
    Logger.info("dumping hash:" + h);
    Logger.info("h.entryCount=" + h.entryCount);
    Int2ObjHash.Entry[] entries = h.entries;
    for (int i = 0; i < entries.length; ++i) {
      StringBuffer log = new StringBuffer();
      log.append(i).append(": ");
      for (Int2ObjHash.Entry e = entries[i]; e != null; e = e.next) {
        log.append(e.key).append(" ");
      }
      Logger.info(log.toString());
    }
  }

  public void test1000() {
    Int2ObjHash h = new Int2ObjHash();
    for (int i = 0; i < 1000; ++i)
      h.put(-i, Integer.valueOf(i));
    for (int i = 0; i < 1000; ++i)
      assertEquals(((Integer)h.get(-i)).intValue(), i);
  }

}
