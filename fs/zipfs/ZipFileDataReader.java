/*
 * JavaFuse - Java bindings for using FUSE.
 * Copyright (C) 2009 SRI International
 * 
 * This program is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free Software 
 * Foundation, either version 3 of the License, or (at your option) any later 
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with 
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

/**
 *   FUSE-J: Java bindings for FUSE (Filesystem in Userspace by Miklos Szeredi (mszeredi@inf.bme.hu))
 *
 *   Copyright (C) 2003 Peter Levart (peter@select-tech.si)
 *
 *   This program can be distributed under the terms of the GNU LGPL.
 *   See the file COPYING.LIB
 */

package fuse.zipfs;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class ZipFileDataReader
{
   private ZipFile zipFile;
   private Map zipEntry2dataReader;

   public ZipFileDataReader(ZipFile zipFile)
   {
      this.zipFile = zipFile;
      zipEntry2dataReader = new HashMap();
   }


   public synchronized ZipEntryDataReader getZipEntryDataReader(ZipEntry zipEntry, long offset, int size)
   {
      ZipEntryDataReader entryReader = (ZipEntryDataReader)zipEntry2dataReader.get(zipEntry.getName());

      if (entryReader == null)
      {
         entryReader = new ZipEntryDataReader(zipFile, zipEntry);
         zipEntry2dataReader.put(zipEntry.getName(), entryReader);
      }

      return entryReader;
   }

   public synchronized void releaseZipEntryDataReader(ZipEntry zipEntry)
   {
      zipEntry2dataReader.remove(zipEntry.getName());
   }
}
