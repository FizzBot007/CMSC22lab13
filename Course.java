import java.io.Serializable;

public class Course implements Serializable {
  private String courseCode;
  private String courseDescription;
  
  public Course(String courseCode, String courseDescription){
   this.courseCode = courseCode;
   this.courseDescription = courseDescription;
  }
  
  public void setCourseCode(String courseCode){
    this.courseCode = courseCode; 
  }
   
  public void setCourseDescription(String courseDescription){
    this.courseDescription = courseDescription; 
  }
  
  public String getCourseCode(){
    return courseCode; 
  }
   
  public String getCourseDescription(){
    return courseDescription; 
  }
  
      public String toString() {
        return  String.format("%s\n%s", courseCode, courseDescription);
    }
    
  
  
  
  
  
  
  
}