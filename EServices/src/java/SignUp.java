/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author asini
 */
@ManagedBean
@RequestScoped
public class SignUp {
    private String id;
    private String password;
    private String type;
    DataStorage data=new SQL_Database();
    public SignUp(){
        
    }
    public SignUp(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public SignUp(String id, String password, String type) {
        this.id = id;
        this.password = password;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String SignUp(){
        String fileName="";
         //load the driver
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            return ("Internal Error! Please try again later.");
        }
       
        
        if(type.equals("Student")){
            fileName=data.StudentSignUp(id,password);
        }
        else{
            fileName=data.FacultySignUp(id,password);
        }
         if(fileName.equals("ConfirmSignUp"))
        {
            return "ConfirmSignUp";
        }
        else
        {
            return fileName;
        }
    }
}
