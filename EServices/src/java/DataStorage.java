
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asini
 */
public interface DataStorage {
    public String StudentSignUp(String id,String password);
    public String FacultySignUp(String id,String password);
    public String studentLogin(String id, String password);
    public String facultyLogin(String id, String password);
    public ArrayList<Course> getOpenCourses();
    public ArrayList<Course> getCourseDetail(String courseId);
    public String registerCourse(String courseId,String id);
    public boolean isRegistered(String courseId,String id);
    public boolean isOpen(String courseId);
    public void increaseEnrollCount(String courseId);
    public void closeCourse(String courseId);
    public int enrollcount(String courseId);
    public String isCourseExists(String courseId);
    public ArrayList<Course> getCourseSchedule(String id);
    public void updateStatusToOpen(String courseId);
    public void decreaseEnrollCount(String courseId);
    public String dropCourse(String id, String courseId);
    public ArrayList<Course> getFacultySchedule(String id);
    public ArrayList<String> getStudents(String id,String courseId);
}

