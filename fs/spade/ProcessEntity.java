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
 * ProcessEntity.java - Entity class for Berkeley DB table that stores
 * lineage concerned with a process.
 */

package spade;

import java.util.ArrayList;
import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;

@Entity
public class ProcessEntity {

    // Primary key is process ID.
    @PrimaryKey
        private int pid;

    // Command line arguments.
    private String cmdline;

    // Environment variables.
    private String environ;

    // UID, GID, Parent PID
    private int uid;
    private int gid;
    private int ppid;

    // Files this process read from.
    private ArrayList<String> filesread;

    // Files this process wrote to.
    private ArrayList<String> fileswritten;

    public ProcessEntity(int id) {
        pid = id;
    }

    public void setPid(int id) {
        pid = id;
    }

    public int getPid() {
        return pid;
    }

    public void setCmdline(String str) {
        cmdline = str;
    }

    public String getCmdline() {
        return cmdline;
    }

    public void setEnviron(String str) {
        environ = str;
    }

    public String getEnviron() {
        return environ;
    }

    public void setUid(int id) {
        uid = id;
    }

    public int getUid() {
        return uid;
    }

    public void setGid(int id) {
        gid = id;
    }

    public int getGid() {
        return gid;
    }

    public void setPpid(int id) {
        ppid = id;
    }

    public int getPpid() {
        return ppid;
    }

    public void setFilesread(String name) {
        filesread.add(name);
    }

    public ArrayList<String> getFilesread() {
        return filesread;
    }

    public void setFileswritten(String name) {
        fileswritten.add(name);
    }

    public ArrayList<String> getFileswritten() {
        return fileswritten;
    }
}
