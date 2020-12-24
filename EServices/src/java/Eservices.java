
import java.util.ArrayList;
import javax.faces.context.FacesContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asini
 */
public class Eservices {
    private String id;
    private String password;
    DataStorage data=new SQL_Database();
    ArrayList<Course> openCourse=new ArrayList<>();
    private String selectedCourseToView;
    ArrayList<Course> courseDetail=new ArrayList<>();
    ArrayList<Course> facultyCourseDetail=new ArrayList<>();
   ArrayList<String> students=new ArrayList<>(); 
    public Eservices(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Course> getOpenCourse() {
        return openCourse;
    }

    public String getSelectedCourseToView() {
        return selectedCourseToView;
    }

    public void setSelectedCourseToView(String selectedCourseToView) {
        this.selectedCourseToView = selectedCourseToView;
    }

    public void setOpenCourse(ArrayList<Course> openCourse) {
        this.openCourse = openCourse;
    }
    
    public ArrayList<Course> displayCourses(){
        openCourse=data.getOpenCourses();
        return openCourse;
    }
    public String updateSelectedCourseToView(String courseId){
        selectedCourseToView=courseId;
        return "courseDetail.xhtml";
    }
    public String updateSelectedCourseScheduleToView(String courseId){
        selectedCourseToView=courseId;
        return "courseDetailFromSchedule.xhtml";
    }
     public String CheckSearchedCourse(String courseId){
        selectedCourseToView=courseId;
        String filename=data.isCourseExists(courseId);
        return filename;
    }
    public ArrayList<Course> displayCourseDetails(){
        courseDetail=data.getCourseDetail(selectedCourseToView);
        return courseDetail;
    }
    
    public String registerCourse(String courseId){
        if(data.isRegistered(courseId, id)){
            return "alreadyRegistered";
        }
        if(data.isOpen(courseId)){
            data.increaseEnrollCount(courseId);
            int enrollcnt=data.enrollcount(courseId);
            if(enrollcnt==2){
                data.closeCourse(courseId);
            }
            return data.registerCourse(courseId, id);
        }
        else{
            return "couseClosed";
        }
        
    }
    public ArrayList<Course> displaySchedule(){
        courseDetail=data.getCourseSchedule(id);
        return courseDetail;
    }
   
    public String dropCourse(String courseId){
        String filename="";
        selectedCourseToView=courseId;
        if(data.isRegistered(courseId, id)){
            int enrollcnt=data.enrollcount(courseId);
            if(enrollcnt==2){
                data.updateStatusToOpen(courseId);
            }
            data.decreaseEnrollCount(courseId);
            
            filename=data.dropCourse(id,courseId);
            
        }
        return filename;
    }
      public ArrayList<Course> displayFacultySchedule(){
        facultyCourseDetail=data.getFacultySchedule(id);
        return facultyCourseDetail;
    }
       public String facultyCourseView(String courseId){
        selectedCourseToView=courseId;
        return "facultyCourseDetail.xhtml";
    }
    public ArrayList<String> displayStudents(){
        String courseId=selectedCourseToView;
        students=data.getStudents(id,courseId);
        return students;
    }
      public String signOut()
    {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml"; 
    }

   
}
