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
 * BerkeleyDB.java - Berkeley DB class for SpadeFS.
 * Implements SpadeDB interface.
 */

package spade;

import java.io.File;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.StoreConfig;

public class BerkeleyDB implements SpadeDB, DBconstants
{
    private String envName;
    private Environment dbEnv;
    private EntityStore dbStore;
    private DataAccessor da;

    public BerkeleyDB(String envName)
    {
        this.envName = envName;
        setup();
    }

    public void setup()
    {
        // Open the environment and entity store. Allow them to be created if they do not already exist.
        try {
            EnvironmentConfig envConfig = new EnvironmentConfig();
            envConfig.setAllowCreate(true);
            envConfig.setSharedCache(true);

            StoreConfig storeConfig = new StoreConfig();
            storeConfig.setAllowCreate(true);

            dbEnv = new Environment(new File(envName), envConfig);
            dbStore = new EntityStore(dbEnv, "EntityStore", storeConfig);
            da = new DataAccessor(dbStore);
        }
        catch (DatabaseException dbe) {
            System.err.println("Error creating BerkeleyDB environment/store" + dbe.toString());
            dbe.printStackTrace();
            //System.exit(-1);
        }
    }

    public void close()
    {
        if (dbStore != null) {
            try {
                dbStore.close();
            }
            catch(DatabaseException dbe) {
                System.err.println("Error closing BerkeleyDB store: " + dbe.toString());
                dbe.printStackTrace();
                //System.exit(-1);
            }
        }

        if (dbEnv != null) {
            try {
                // Clean the log before closing
                dbEnv.cleanLog();
                dbEnv.close();
            } 
            catch (DatabaseException dbe) {
                System.err.println("Error closing BerkeleyDB environment" + dbe.toString());
                dbe.printStackTrace();
                //System.exit(-1);
            }
        }
    }

    public void put_process(ProcessEntity entry)
    {
        try {
            da.processIdx.put(entry);
        } 
        catch (DatabaseException dbe) {
            System.err.println("Error putting process entity into DB" + dbe.toString());
            dbe.printStackTrace();
            //System.exit(-1);
        }
    }

    public ProcessEntity get_process(int key)
    {
        ProcessEntity entry = null;

        try {
            entry = da.processIdx.get(key); 
        } 
        catch (DatabaseException dbe) {
            System.err.println("Error getting process entity from DB" + dbe.toString());
            dbe.printStackTrace();
            //System.exit(-1);
        }

        return entry;
    }

    public void put_file(FileEntity entry)
    {
        try {
            da.fileIdx.put(entry);
        } 
        catch (DatabaseException dbe) {
            System.err.println("Error putting file entity into DB" + dbe.toString());
            dbe.printStackTrace();
            //System.exit(-1);
        }
    }

    public FileEntity get_file(String key)
    {
        FileEntity entry = null;

        try {
            entry = da.fileIdx.get(key);
        } 
        catch (DatabaseException dbe) {
            System.err.println("Error getting file entity from DB" + dbe.toString());
            dbe.printStackTrace();
            //System.exit(-1);
        }

        return entry;
    }

    public Environment getEnv()
    {
        return dbEnv;
    }

    public EntityStore getEntityStore() {
        return dbStore;
    }

    protected void finalize()
    {
    }
}
