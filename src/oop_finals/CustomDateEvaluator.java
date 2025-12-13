package oop_finals;

import com.toedter.calendar.IDateEvaluator;
import java.awt.Color;
import java.util.Date;

/**
 * Custom date evaluator for JCalendar that highlights unavailable dates in red
 * Note: Some JCalendar versions have a typo: getInvalidBackroundColor (missing 'g')
 */
public class CustomDateEvaluator implements IDateEvaluator {
    
    private final int counselorId;
    private static final Color UNAVAILABLE_COLOR = new Color(255, 200, 200); // Light red
    
    public CustomDateEvaluator(int counselorId) {
        this.counselorId = counselorId;
    }
    
    @Override
    public boolean isSpecial(Date date) {
        // Return true for dates we want to highlight (unavailable dates)
        Date today = new Date();
        today.setHours(0);
        today.setMinutes(0);
        today.setSeconds(0);
        
        // Don't highlight past dates
        if (date.before(today)) {
            return false;
        }
        
        // Highlight unavailable dates
        return !CalendarHelper.isDateAvailable(counselorId, date);
    }
    
    @Override
    public Color getSpecialForegroundColor() {
        return Color.DARK_GRAY;
    }
    
    public Color getSpecialBackgroundColor() {
        return UNAVAILABLE_COLOR;
    }
    
    @Override
    public String getSpecialTooltip() {
        return "Counselor not available";
    }
    
    @Override
    public boolean isInvalid(Date date) {
        // Mark past dates and unavailable dates as invalid
        Date today = new Date();
        today.setHours(0);
        today.setMinutes(0);
        today.setSeconds(0);
        
        if (date.before(today)) {
            return true;
        }
        
        return !CalendarHelper.isDateAvailable(counselorId, date);
    }
    
    @Override
    public Color getInvalidForegroundColor() {
        return Color.GRAY;
    }
    
    // Note: This method has a typo in some JCalendar versions (Backround instead of Background)
    @Override
    public Color getInvalidBackroundColor() {
        return UNAVAILABLE_COLOR;
    }
    
    @Override
    public String getInvalidTooltip() {
        return "Not available";
    }

    @Override
    public Color getSpecialBackroundColor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}