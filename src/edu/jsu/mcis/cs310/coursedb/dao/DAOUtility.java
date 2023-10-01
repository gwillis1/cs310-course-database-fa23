package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

public class DAOUtility {
    
    public static final int TERMID_FA23 = 1;
    
    public static JsonArray getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();
        
        try {
        
            if (rs != null) {

                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                
                    while (rs.next()){
                        JsonObject record = new JsonObject();
                        
                        for (int i = 1; i <= columnCount; i++){
                            String columnName = metaData.getColumnName(i);
                            Object columnValue = rs.getObject(i);
                            record.put(columnName, columnValue);
                        }
                        records.add(record);
                    }

            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return records;
        
    }
    
}
