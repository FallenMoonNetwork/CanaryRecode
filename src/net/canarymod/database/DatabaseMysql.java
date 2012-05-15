package net.canarymod.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseMysql implements IDatabase {

    private MysqlConnectionPool connectionPool;
    
    public DatabaseMysql() {
        connectionPool = MysqlConnectionPool.getInstance();
    }
    @Override
    public int getNumTables() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String[] getAllTables() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IDatabaseTable getTable(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addTable(IDatabaseTable table) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeTable(String name) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getStringValue(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String[] getStringValues(String path) {
        // TODO Auto-generated method stub
        return null;
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
    
    private PreparedStatement convertPathToSelectQuery(String path) throws SQLException {
        //XXX convert any column name to uppercase and tablename to lowercase
        String[] elements = path.split(".");
        //Syntaxexceptio here
        PreparedStatement ps = connectionPool.getConnection().prepareStatement("SELECT ? FROM ?");
        ps.setString(2, elements[0].toLowerCase());
        ps.setString(1, elements[1].toUpperCase());
        return ps;
        
    }

}
