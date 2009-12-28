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

import fuse.FuseMount;
import fuse.staticfs.DirectoryNode;
import fuse.staticfs.MountpointNode;
import fuse.staticfs.StaticFilesystem;

import java.io.File;
import java.io.IOException;


public class MultiZipFilesystem extends StaticFilesystem
{
   public MultiZipFilesystem(String args[], int offset) throws IOException
   {
      super(new DirectoryNode("$ROOT"));

      DirectoryNode rootNode = getRootNode();
      for (int i = offset; i < args.length; i++)
      {
         File zipFile = new File(args[i]);
         rootNode.addChild(new MountpointNode(zipFile.getName(), new ZipFilesystem(zipFile)));
      }
   }


   public static void main(String[] args)
   {
      if (args.length < 2)
      {
         System.out.println("Usage: MultiZipFilesystem mountpoint zipfile1 [zipfile2 ...]");
         System.exit(-1);
      }

      String fuseArgs[] = new String[] { args[0] };

      try
      {
         FuseMount.mount(fuseArgs, new MultiZipFilesystem(args, 1));
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
}
