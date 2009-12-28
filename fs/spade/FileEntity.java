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
 * FileEntity.java - Entity class for Berkeley DB table that stores
 * lineage concerned with a file.
 */

package spade;

import java.util.ArrayList;
import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;

@Entity
public class FileEntity {

    // Primary key is filename.
    @PrimaryKey
        private String filename;

    // Processes that read this file.
    private ArrayList<Integer> procsread;

    // Processes that wrote this file.
    private ArrayList<Integer> procswrote;

    public FileEntity(String name)
    {
        filename = name;
        procsread = new ArrayList<Integer>();
        procswrote = new ArrayList<Integer>();
    }

    public void setFilename(String data) {
        filename = data;
    }

    public String getFilename() {
        return filename;
    }

    public void setProcread(int pid) {
        procsread.add(pid);
    }

    public ArrayList<Integer> getProcread() {
        return procsread;
    }

    public void setProcwrote(int pid) {
        procswrote.add(pid);
    }

    public ArrayList<Integer> getProcwrote() {
        return procswrote;
    }
}
