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
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package org.jmol.util;

public class IntInt2ObjHash {
  int entryCount;
  Entry[] entries;
  

  public IntInt2ObjHash(int initialCapacity) {
    entries = new Entry[initialCapacity];
  }

  public IntInt2ObjHash() {
    this(256);
  }

  public synchronized Object get(int key1, int key2) {
    Entry[] entries = this.entries;
    int k = (key1 ^ (key2 >> 1)) & 0x7FFFFFFF;
    int hash = k % entries.length;
    for (Entry e = entries[hash]; e != null; e = e.next)
      if (e.key1 == key1 && e.key2 == key2)
        return e.value;
    return null;
  }

  public synchronized void put(int key1, int key2, Object value) {
    Entry[] entries = this.entries;
    int k = (key1 ^ (key2 >> 1)) & 0x7FFFFFFF;
    int hash = k % entries.length;
    for (Entry e = entries[hash]; e != null; e = e.next)
      if (e.key1 == key1 && e.key2 == key2) {
        e.value = value;
        return;
      }
    if (entryCount > entries.length)
      rehash();
    entries = this.entries;
    hash = k % entries.length;
    entries[hash] = new Entry(key1, key2, value, entries[hash]);
    ++entryCount;
  }

  private void rehash() {
    Entry[] oldEntries = entries;
    int oldSize = oldEntries.length;
    int newSize = oldSize * 2 + 1;
    Entry[] newEntries = entries = new Entry[newSize];

    for (int i = oldSize; --i >= 0; ) {
      for (Entry e = oldEntries[i]; e != null; ) {
        Entry t = e;
        e = e.next;

        int k = (t.key1 ^ (t.key2 >> 1)) & 0x7FFFFFFF;
        int hash = k % newSize;
        t.next = newEntries[hash];
        newEntries[hash] = t;
      }
    }
  }

  static class Entry {
    int key1;
    int key2;
    Object value;
    Entry next;
    
    Entry(int key1, int key2, Object value, Entry next) {
      this.key1 = key1;
      this.key2 = key2;
      this.value = value;
      this.next = next;
    }
  }
}


