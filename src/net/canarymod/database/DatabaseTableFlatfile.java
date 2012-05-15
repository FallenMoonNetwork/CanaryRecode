package net.canarymod.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * @author Jos Kuijpers
 */
public class DatabaseTableFlatfile implements IDatabaseTable {

	private Logger log = Logger.getLogger("Minecraft");
	private File tableFile;
	private String name;
	private String description;
	public ArrayList<String> columnNames; // Public for access by Row
	public ArrayList<String> columnTypes; // Public for access by Row
	private ArrayList<DatabaseRowFlatfile> rows;
	private DatabaseFlatfile database;
	
	public DatabaseTableFlatfile(DatabaseFlatfile database, String file) throws IOException {
		
		this.database = database;
		this.tableFile = new File("db/"+file);
		this.name = file.substring(0, file.indexOf(".")).toLowerCase();
		this.description = "";
		this.columnNames = new ArrayList<String>();
		this.columnTypes = new ArrayList<String>();
		this.rows = new ArrayList<DatabaseRowFlatfile>();
		
		this.load();
	}
	
	private void load() throws IOException {
		BufferedReader in = null;
		
		try {
			in = new BufferedReader(new FileReader(this.tableFile));
			
			String inLine;
			int parseState = 0; // 0 = desc, 1 = columnname, 2 = column types, 3 = content
			
			while((inLine = in.readLine()) != null) {
				if(inLine.startsWith("##")) {
					// Description is optional
					if(parseState == 0)
						parseState = 1;

					if(parseState == 1) { // column names
						// Remove ##
						inLine = inLine.substring(2);
						
						// Split at column-separator
						String[] items = inLine.split("::");
						
						// Handle all items
						for(String i : items)
						{
							if(this.columnNames.contains(i.toUpperCase())) {
								throw new IOException("Duplicate column names in "+this.tableFile.getPath());
							}
							this.columnNames.add(i.toUpperCase());
						}
						parseState = 2;
					}
					else if(parseState == 2) { // column types
						// Remove ##
						inLine = inLine.substring(2);
						
						// Split at column-separator
						String[] items = inLine.split("::");
						
						if(items.length != this.columnNames.size())
							throw new IOException("Column type definitions do not cover all columns");
						
						// Handle all items
						for(int i = 0; i < items.length; i++) {
							this.columnTypes.add(items[i].toUpperCase());
						}
						
						parseState = 3;
					}
					
					continue;
				}
				if(inLine.startsWith("#")) {
					if(parseState != 0)
						throw new IOException("Invalid format");
					
					String appDesc = inLine.substring(1).trim();
					this.description += ((this.description == "")?"":" ")+appDesc;
					
					continue;
				}
				
				// Get the cells
				String[] cells = inLine.split("::");
				
				// Verify number of cells
				if(cells.length != this.columnNames.size())
					throw new IOException("Numbers of cells does not match number of columns");
				
				DatabaseRowFlatfile row = new DatabaseRowFlatfile(this, cells);
				this.rows.add(row);
			}
		}
		catch(IOException e) {
			if(in != null)
				in.close();
			throw e;
		}
		if(in != null)
			in.close();
	}
	
	public void reload() {
		// TODO implement
	}
	
	public void save() {
		// TODO implement
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.tableFile.renameTo(new File("db/"+name+".txt"));
		this.name = name;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
		this.save();
	}

	@Override
	public int getNumRows() {
		return this.rows.size();
	}

	@Override
	public IDatabaseRow getRow(int row) {
		return this.rows.get(row-1);
	}

	@Override
	public IDatabaseRow[] getAllRows() {
		IDatabaseRow[] t = {};
		return this.rows.toArray(t);
	}

	@Override
	public IDatabaseRow[] getFilteredRows(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDatabaseRow addRow() {
		DatabaseRowFlatfile newRow = new DatabaseRowFlatfile(this, null);
		this.rows.add(newRow);
		return newRow;
	}

	@Override
	public void removeRow(IDatabaseRow row) {
		if(this.rows.remove(row))
			this.save();
	}

	@Override
	public void removeRow(int row) {
		// TODO Auto-generated method stub
		if(row >= this.rows.size())
			return;
		this.rows.remove(row);
		this.save();
	}

	public int getColumnPosition(String column) {
		return this.columnNames.indexOf(column.toUpperCase());
	}
	
	@Override
	public int getNumColumns() {
		return this.columnNames.size();
	}

	@Override
	public void appendColumn(String name, ColumnType type) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeColumn(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertColumn(String name, ColumnType type, String after) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public String[] getAllColumns() {
		String[] ar = {};
		return this.columnNames.toArray(ar);
	}

	@Override
	public void truncateTable() {
		this.rows.clear();
		this.save();
	}

}
