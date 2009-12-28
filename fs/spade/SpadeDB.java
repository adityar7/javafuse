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

/*
 * SpadeDB.java - Database class for SpadeFS.
 */

package spade;

public interface SpadeDB
{
    // Initialize a database.
    public void setup();

    // Close a database.
    public void close();

    // Insert into a table a process entity.
    public void put_process(ProcessEntity entry) throws Exception;

    // Get from a table a process entity.
    public ProcessEntity get_process(int key) throws Exception;

    // Insert into a table a file entity.
    public void put_file(FileEntity entry) throws Exception;

    // Get from a table a file entity.
    public FileEntity get_file(String key) throws Exception;
}
