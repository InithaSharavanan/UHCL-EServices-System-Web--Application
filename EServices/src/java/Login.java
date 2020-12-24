/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author asini
 */
@ManagedBean
@SessionScoped
public class Login {
    private  String id;
    private String password;
    private String type;
    private Course theCourse;
    private Eservices theEservices;
    DataStorage data=new SQL_Database();
    public Login() {
    }

   

    public Login(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public Login(String id, String password, String type) {
        this.id = id;
        this.password = password;
        this.type = type;
    }
     public Eservices getTheEservices() {
        return theEservices;
    }

    public void setTheEservices(Eservices theEservices) {
        this.theEservices = theEservices;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPassword(String password) {
        this.password = password;
    }
      public String login()
    {
        //load the Driver
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //return to internalError.xhtml
            return ("internalError");
        }
        String fileName ="";
        if(type.equals("Student")){
             fileName= data.studentLogin(id, password);
             if(fileName.equals("homePage"))
            {
                theEservices=new Eservices(id,password);
                theEservices.displayCourses();
                return "homePage";
            }
            else
            {
                return fileName;
            }
        }
        else{
             fileName= data.facultyLogin(id, password);
              if(fileName.equals("facultyHomePage"))
            {
                theEservices=new Eservices(id,password);
                theEservices.displayFacultySchedule();
                return "facultyHomePage";
            }
            else
            {
                return fileName;
            }
        }     
         
    }

}
