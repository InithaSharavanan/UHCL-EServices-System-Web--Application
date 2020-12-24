
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class SQL_Database implements DataStorage{
       final String DATABASE_URL="jdbc:mysql://127.0.0.1:3306/annamalaishai70?useSSL=false";
        final String db_id="root";
        final String db_pwd="root123";
        
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    @Override
    public String StudentSignUp(String id, String password) {
        try
        {

            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                    db_id, db_pwd);
            //crate the statement
            statement = connection.createStatement();

            //do a query
            resultSet = statement.executeQuery("Select * from student where id = '" + id + "'");

            if(resultSet.next())
            {
                return("SignUpNotOk");
            }
            else
            {
                //insert a record into useraccount
            int r = statement.executeUpdate("insert into student values ('" + id + "','" + password + "')");
                return ("ConfirmSignUp");

            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return("SignUpNotOk");
        }
        finally
        {
             //close the database
             try
             {
                 resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
        }

    }

    @Override
    public String FacultySignUp(String id, String password) {
        try
        {

            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                    db_id, db_pwd);
            //crate the statement
            statement = connection.createStatement();

            //do a query
            resultSet = statement.executeQuery("Select * from faculty where id = '" + id + "'");

            if(resultSet.next())
            {
                return("SignUpNotOk");
            }
            else
            {
                //insert a record into useraccount
            int r = statement.executeUpdate("insert into faculty values ('" + id + "','" + password + "')");
                return ("ConfirmSignUp");

            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return("SignUpNotOk");
        }
        finally
        {
             //close the database
             try
             {
                 resultSet.close();
                 statement.close();
                 connection.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
        }
    }

    @Override
    public String studentLogin(String id, String password) {
        try
        {

            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                  db_id, db_pwd);
            //create statement
            statement = connection.createStatement();
            //search the accountID in the onlineAccount table
            resultSet = statement.executeQuery("Select * from student where id = '" + id + "'");

            if(resultSet.next())
            {
                //the id is found, check the password
                if(password.equals(resultSet.getString(2)))
                {
                    //password is good
                    return "homePage";
                    //go to the welcome page
                }
                else
                {
                    //password is not correct
                    return "loginNotOK";

                }
            }
            else
            {
                return "loginNotOK";
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "internalError";
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public String facultyLogin(String id, String password) {
        try
        {

            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                  db_id, db_pwd);
            //create statement
            statement = connection.createStatement();
            //search the accountID in the onlineAccount table
            resultSet = statement.executeQuery("Select * from faculty where id = '" + id + "'");

            if(resultSet.next())
            {
                //the id is found, check the password
                if(password.equals(resultSet.getString(2)))
                {
                    //password is good
                    return "facultyHomePage";
                    //go to the welcome page
                }
                else
                {
                    //password is not correct
                    return "loginNotOK";

                }
            }
            else
            {
                return "loginNotOK";
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "internalError";
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public ArrayList<Course> getOpenCourses() {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                  db_id, db_pwd);

            statement = connection.createStatement();
            //Get all the threads to display in thread page
              ArrayList<Course> aList = new ArrayList<Course>();

            resultSet = statement.executeQuery("Select * from course where status='Open'");
            while(resultSet.next())
            {
                Course course = new Course(resultSet.getString(1),
                    resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getString(7),resultSet.getDouble(8));
                aList.add(course);
            }
            return aList;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public ArrayList<Course> getCourseDetail(String courseId) {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                  db_id, db_pwd);

            statement = connection.createStatement();
            //Get all the threads to display in thread page
              ArrayList<Course> aList = new ArrayList<Course>();

            resultSet = statement.executeQuery("Select * from course where course_id='" + courseId + "'");
            while(resultSet.next())
            {
                Course course = new Course(resultSet.getString(1),
                    resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getString(7),resultSet.getDouble(8));
                aList.add(course);
            }
            return aList;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public String registerCourse(String courseId, String id) {
         try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("insert into studentregistration values('" + id + "','" + courseId + "')");
            connection.commit();
            connection.setAutoCommit(true);
            return "confirmRegistration";

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "internalError";
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean isRegistered(String courseId, String id) {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                  db_id, db_pwd);

            statement = connection.createStatement();

            resultSet = statement.executeQuery("Select * from studentregistration where student_id='" + id + "' and  course_id='" + courseId + "'");
            return resultSet.next();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean isOpen(String courseId) {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                  db_id, db_pwd);

            statement = connection.createStatement();

            resultSet = statement.executeQuery("Select students_enrolled,class_capacity from course where course_id='" + courseId + "'");
            if(resultSet.next()){
                if(resultSet.getInt(1)<resultSet.getInt(2)){
                    return true;
                }
            }
            return false;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void increaseEnrollCount(String courseId) {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("update course set students_enrolled=students_enrolled+1 where course_id='" + courseId + "'");
            connection.commit();
            connection.setAutoCommit(true);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void closeCourse(String courseId) {
         try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("update course set status='Closed' where course_id='" + courseId + "'");
            connection.commit();
            connection.setAutoCommit(true);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }

    }

    @Override
    public int enrollcount(String courseId) {
      try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                  db_id, db_pwd);

            statement = connection.createStatement();

            resultSet = statement.executeQuery("Select students_enrolled from course where course_id='" + courseId + "'");
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
            return 0;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public String isCourseExists(String courseId) {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                  db_id, db_pwd);

            statement = connection.createStatement();

            resultSet = statement.executeQuery("Select * from course where course_id='" + courseId + "'");
            if(resultSet.next()){
                return "courseDetail.xhtml";
            }
            return "courseNotfound";

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "internalError";
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public ArrayList<Course> getCourseSchedule(String id) {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                  db_id, db_pwd);

            statement = connection.createStatement();
            //Get all the threads to display in thread page
              ArrayList<Course> aList = new ArrayList<Course>();

            resultSet = statement.executeQuery("Select c.course_id,c.course_title,c.instructor_info,c.class_time,c.class_capacity,c.students_enrolled,c.status,c.amount from course c,studentregistration s where c.course_id=s.course_id and s.student_id='" + id + "' ");
            while(resultSet.next())
            {
                Course course = new Course(resultSet.getString(1),
                    resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getString(7),resultSet.getDouble(8));
                aList.add(course);
            }
            return aList;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void updateStatusToOpen(String courseId) {
         try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("update course set status='Open' where course_id='" + courseId + "'");
            connection.commit();
            connection.setAutoCommit(true);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void decreaseEnrollCount(String courseId) {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("update course set students_enrolled=students_enrolled-1 where course_id='" + courseId + "'");
            connection.commit();
            connection.setAutoCommit(true);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public String dropCourse(String id, String courseId) {
         try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("delete from studentregistration where student_id='" + id + "' and course_id='" + courseId + "'");
            connection.commit();
            connection.setAutoCommit(true);
            return "confirmCourseDrop";

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "internalError";
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public ArrayList<Course> getFacultySchedule(String id) {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                  db_id, db_pwd);

            statement = connection.createStatement();
            //Get all the threads to display in thread page
              ArrayList<Course> aList = new ArrayList<Course>();

            resultSet = statement.executeQuery("Select c.course_id,c.course_title,c.instructor_info,c.class_time,c.class_capacity,c.students_enrolled,c.status,c.amount from course c,faculty f where c.instructor_info=f.id and f.id='" + id + "' ");
            while(resultSet.next())
            {
                Course course = new Course(resultSet.getString(1),
                    resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getString(7),resultSet.getDouble(8));
                aList.add(course);
            }
            return aList;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public ArrayList<String> getStudents(String id,String courseId) {        
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                  db_id, db_pwd);

            statement = connection.createStatement();
            //Get all the threads to display in thread page
              ArrayList<String> aList = new ArrayList<String>();

            resultSet = statement.executeQuery("Select student_id from studentregistration s,course c where c.course_id=s.course_id and c.instructor_info='"+ id +"' and c.course_id='"+ courseId +"' ");
            while(resultSet.next())
            {
                aList.add(resultSet.getString(1));
            }
            return aList;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }
    

}
