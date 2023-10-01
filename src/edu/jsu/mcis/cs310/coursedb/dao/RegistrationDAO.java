package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject.*;

public class RegistrationDAO {
   
    
    private final DAOFactory daoFactory;
    
    RegistrationDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public boolean create(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
             String sql = "INSERT INTO registration (studentid, termid, crn) VALUES (?, ?, ?)";
             ps = conn.prepareStatement(sql);
             ps.setInt(1,studentid);
             ps.setInt(2, termid);
             ps.setInt(3, crn);
                
             int rowsInserted = ps.executeUpdate();
             result = rowsInserted > 0;
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public boolean delete(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
             String sql = "DELETE FROM registration WHERE studentid = ? AND termid = ? AND crn = ?";
             ps = conn.prepareStatement(sql);
             ps.setInt(1,studentid);
             ps.setInt(2, termid);
             ps.setInt(3, crn);
                
             int rowsDeleted = ps.executeUpdate();
             result = rowsDeleted > 0;
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
    public boolean delete(int studentid, int termid) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
             String sql = "DELETE FROM registration WHERE studentid = ? AND termid = ?";
             ps = conn.prepareStatement(sql);
             ps.setInt(1,studentid);
             ps.setInt(2, termid);
             
                
             int rowsDeleted = ps.executeUpdate();
             result = rowsDeleted > 0;
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public String list(int studentid, int termid) {
        JsonArray jsonArray = new JsonArray();
        
        String result = "[]";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
             String sql = "SELECT * FROM registration WHERE studentid = ? AND termid = ?";
             ps = conn.prepareStatement(sql);
             ps.setInt(1,studentid);
             ps.setInt(2, termid);
             
                
             rs = ps.executeQuery();
            jsonArray = DAOUtility.getResultSetAsJson(rs);
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return jsonArray.toString();
        
    }
    
}
