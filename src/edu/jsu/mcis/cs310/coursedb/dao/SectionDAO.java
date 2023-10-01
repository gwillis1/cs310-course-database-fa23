package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;


public class SectionDAO {
    
 
    private final DAOFactory daoFactory;
    
    SectionDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public String find(int termid, String subjectid, String num) {
        JsonArray jsonArray = new JsonArray();
        
        String result = "[]";
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        Connection conn = null;
        try {
            
             conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                String sql = "SELECT * FROM section WHERE termid = ? AND subjectid = ? AND NUM = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, termid);
                ps.setString(2, subjectid);
                ps.setString(3, num);
                
                rs = ps.executeQuery();
                
                jsonArray = DAOUtility.getResultSetAsJson(rs);
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (conn != null){ try { conn.close(); } catch (Exception e) { e.printStackTrace(); } }
        }
        
        return jsonArray.toString();
        
    }
    
}