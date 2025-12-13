/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop_finals;

public class UserSession {
    private static int studentId;
    private static String studentName;
    private static String studentEmail;
    private static String course;
    private static String studentNumber;
    
    public static void setStudentSession(int id, String name, String email, String courseVal, String studNum) {
        studentId = id;
        studentName = name;
        studentEmail = email;
        course = courseVal;
        studentNumber = studNum;
    }
    
    public static int getStudentId() {
        return studentId;
    }
    
    public static String getStudentName() {
        return studentName;
    }
    
    public static String getStudentEmail() {
        return studentEmail;
    }
    
    public static String getCourse() {
        return course;
    }
    
    public static String getStudentNumber() {
        return studentNumber;
    }
    
    public static void clearSession() {
        studentId = 0;
        studentName = null;
        studentEmail = null;
        course = null;
        studentNumber = null;
    }
}
