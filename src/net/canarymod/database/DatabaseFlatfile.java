
package net.canarymod.database;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * @author Jos Kuijpers
 */
public class DatabaseFlatfile implements IDatabase {

	private Logger log = Logger.getLogger("Minecraft");
	private HashMap<String, DatabaseTableFlatfile> tables;
	private File dbDirectory;
	
	/**
	 * Sets up the flatfile database
	 */
	public DatabaseFlatfile() {

		// Find the database table files
		this.dbDirectory = new File("db/");
		this.tables = new HashMap<String, DatabaseTableFlatfile>();
		String[] dbFiles = this.dbDirectory.list();
		
		// Extract the tablename and verify the extension
		for(String file : dbFiles) {
			if(!file.endsWith(".txt")) {
				log.warning("Invalid file '"+file+"' found in db/");
				continue;
			}

			DatabaseTableFlatfile table;
			try {
				table = new DatabaseTableFlatfile(this,file);
			}
			catch(IOException e) {
				log.severe("Failed to load flatfile database table in "+file);
				continue;
			}
			
			// Store the table object
			tables.put(table.getName(), table);
		}
	}
	
	public void reload() {
		
	}
	
	public void save() {
		
	}
	
	/**
	 * Called by DatabaseTableFlatfile to synchronize the database info structure. Do not use!
	 * @param oldName
	 * @param newName
	 */
	public void tableRenamed(String oldName, String newName) {
		this.tables.put(newName,this.tables.get(oldName));
		this.tables.remove(oldName);
	}
	
	@Override
 	public int getNumTables() {
		return this.tables.size();
	}

	@Override
	public String[] getAllTables() {
		String[] ar = {};
		return this.tables.keySet().toArray(ar);
	}

	@Override
	public IDatabaseTable getTable(String name) {
		if(!this.tables.containsKey(name.toLowerCase()))
			return null;
		return this.tables.get(name.toLowerCase());
	}

	@Override
	public void addTable(IDatabaseTable table) {
		// TODO wut?
		this.tables.put(table.getName().toLowerCase(), (DatabaseTableFlatfile)table);
	}

	@Override
	public void removeTable(String name) {
		if(!this.tables.containsKey(name.toLowerCase()))
			return;
		this.tables.remove(name.toLowerCase());
		File f = new File("db/"+name.toLowerCase()+".txt");
		f.delete();
	}

	private String[] resolvePath(String[] path) {
		String tableName = path[0].toLowerCase();
		String columnName = path[1].toUpperCase();
		ArrayList<String> data = new ArrayList<String>();
		String[] ret = {};
		DatabaseTableFlatfile table;

		if(!this.tables.containsKey(tableName))
			return null;

		table = this.tables.get(tableName);
		
		// Find the column index
		int index = table.getColumnPosition(columnName);
		if(index == -1)
			return null;

		for(IDatabaseRow row : table.getAllRows()) {
			data.add(row.getStringCell(columnName));
		}
		
		
		ret = data.toArray(ret);
		
		return ret;
	}
	
	@Override
	public String getStringValue(String path) {
		String[] components = path.split(".");
		if(components.length != 3)
			return null;
		
		String[] values = this.resolvePath(components);
		int index;
		try {
			index = Integer.parseInt(components[2]);
		}
		catch(NumberFormatException e) {
			log.warning("A NumberFormatException occurred in Table: '"+components[0]+"' @ Column: "+components[1]);
			return null;
		}
		
		if(index >= values.length)
			return null;
		
		return values[index];
	}

	@Override
	public String[] getStringValues(String path) {
		String[] components = path.split("\\.");
		if(components.length != 2)
			return null;

		return this.resolvePath(components);
	}

	@Override
	public int getIntValue(String path) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] getIntValues(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getFloatValue(String path) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float[] getFloatValues(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getDoubleValue(String path) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getDoubleValues(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getBooleanValue(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean[] getBooleanValues(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getLongValue(String path) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long[] getLongValues(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Character getCharacterValue(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Character[] getCharacterValues(String path) {
		// TODO Auto-generated method stub
		return null;
	}

}
