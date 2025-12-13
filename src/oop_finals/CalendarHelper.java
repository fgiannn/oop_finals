/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop_finals;

import java.sql.*;
import java.util.Calendar;

public class CalendarHelper {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/guidance_appointment_system";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";
    
    public static boolean isDateAvailable(int counselorId, java.util.Date date) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            
            // Get day of week
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            String[] days = {"", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
            String dayName = days[cal.get(Calendar.DAY_OF_WEEK)];
            
            // Check if counselor works on this day
            String scheduleQuery = "SELECT COUNT(*) FROM counselor_schedules " +
                                  "WHERE counselor_id = ? AND day_of_week = ? AND is_available = TRUE";
            PreparedStatement schedPst = conn.prepareStatement(scheduleQuery);
            schedPst.setInt(1, counselorId);
            schedPst.setString(2, dayName);
            ResultSet schedRs = schedPst.executeQuery();
            
            boolean worksOnDay = false;
            if (schedRs.next()) {
                worksOnDay = schedRs.getInt(1) > 0;
            }
            schedRs.close();
            schedPst.close();
            
            if (!worksOnDay) {
                conn.close();
                return false;
            }
            
            // Check if date is specifically blocked
            String blockedQuery = "SELECT COUNT(*) FROM counselor_blocked_dates " +
                                 "WHERE counselor_id = ? AND blocked_date = ?";
            PreparedStatement blockedPst = conn.prepareStatement(blockedQuery);
            blockedPst.setInt(1, counselorId);
            blockedPst.setDate(2, new java.sql.Date(date.getTime()));
            ResultSet blockedRs = blockedPst.executeQuery();
            
            boolean isBlocked = false;
            if (blockedRs.next()) {
                isBlocked = blockedRs.getInt(1) > 0;
            }
            blockedRs.close();
            blockedPst.close();
            conn.close();
            
            return !isBlocked;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}