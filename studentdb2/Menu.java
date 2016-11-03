package studentdb2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Menu {

    public static void main(String[] args){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<Student> students = new ArrayList<Student>();

        BufferedReader br = null;
 
        try{
            File fin = new File("db.txt");
            if(fin.length() == 0) {
                System.err.println("FILE IS EMPTY");
                System.exit(-1);
            }
            
            fis = new FileInputStream(fin);
            ois = new ObjectInputStream(fis);

            String sCurrentLine;

            br = new BufferedReader(new FileReader("db.txt"));
            int i = 9;
            String sNum = "";
            String fName = "";
            String mName = "";
            String lName = "";
            String course = "";
            String year = "";
            String crushName = "";
            Course x = new Course(null, null);
            while ((sCurrentLine = br.readLine()) != null) {
                if (i % 9 == 0){
                    sNum = sCurrentLine;
                }else if (i % 9 == 1){
                    fName = sCurrentLine;
                }else if (i % 9 == 2){
                    mName = sCurrentLine;
                }else if(i % 9 == 3){
                    lName = sCurrentLine;
                }else if(i % 9 == 4){
                    course = sCurrentLine;
                }else if(i % 9 == 5){
                    year = sCurrentLine;
                }
                else if(i % 9 == 6){
                  crushName = sCurrentLine;
                }
                else if(i % 9 == 7){
                  x.setCourseCode(sCurrentLine);
                }
                else if(i % 9 == 8){
                  x.setCourseDescription(sCurrentLine);
                Student a = new Student(sNum,fName, mName.charAt(0), lName, course,Integer.parseInt(year), crushName, x);
                students.add(a);
                }
                i++;
            }
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }   finally {
            try {
                
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        System.out.println("\t\t\tMENU");
        System.out.println("1. Add a student\n2. Remove a student\n3. View data\n4. Save data\n5. Exit");
        Scanner s = new Scanner(System.in);
        Scanner x = new Scanner(System.in);
        int choice = 0;
        //s.nextLine();

        int flag = 1;

     do {
       choice = x.nextInt();
             if (choice == 1) {
             System.out.print("Enter Student Number: ");
             String sn = s.nextLine();

                 for (Student toCheck : students) {
                     if ((toCheck.getStudentNumber()).equals(sn)) {
                         System.out.println("A Student with the same student number already exist");
                         flag = 0;
                     }
                 }
             if(flag == 0){ break; }
             System.out.print("Enter First Name: ");
             String fn = s.nextLine();
             System.out.print("Enter Middle Initial: ");
             String mi = s.nextLine();
             System.out.print("Enter Last Name: ");
             String ln = s.nextLine();
             System.out.print("Enter course: ");
             String course = s.nextLine();
             System.out.print("Enter your crush's name:");
             String crushName = s.nextLine();
             System.out.print("Enter favorite subject:");
             String fSub = s.nextLine();
             System.out.print("Enter favorite subject's description:");
             String fSubD = s.nextLine();
             System.out.print("Enter year level: ");
             int yl = s.nextInt();


             Course y = new Course(fSub, fSubD);

             Student bago = new Student(sn, fn, mi.charAt(0), ln, course, yl, crushName, y);
             students.add(bago);
             System.out.println("Done");
         } else if (choice == 2) {
             System.out.println("Enter Student Number of the Student you want to remove: ");
             String snRemoved = s.next();
             for (Student toBeRemoved : students) {
                 if (toBeRemoved.getStudentNumber().equals(snRemoved)) {
                     students.remove(toBeRemoved);
                     break;
                 }
             }
             System.out.println("Added Student");
         } else if (choice == 3) {
             int f = 1;
             System.out.println("Enter Student Number of the Student you want to view: ");
             String snToView = s.next();
               for (Student s1 : students) {
                 if ((s1.getStudentNumber()).equals(snToView)) {
                     System.out.println(String.format("Student Number: %s\nFirst Name: %s\nMiddle Initial: %c\nLast Name: %s\ncourse: %s\nYear Level: %d\nCrush Name: %s\nFavorite Subject: %s",s1.getStudentNumber(),s1.getFirstName(),s1.getMiddleInitial(),s1.getLastName(),s1.getCourse(),s1.getYearLevel(),s1.getCrushName(),s1.getfaveSubject()));
                     f = 0;
                 }
             }
             if(f == 1){
                 System.out.println("Student is not enrolled or has been removed");
             }
             System.out.println("Success!");
         
         } else if (choice == 4) {
           try{
                 
            File fout = new File("db.txt");
            fos = new FileOutputStream(fout);
            oos = new ObjectOutputStream(fos);
               
            oos.writeObject(students);
            System.out.println("Saved Students");
            oos.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }

         System.out.println("Would you like to choose again?\n1. Add a student\n2. Remove a student\n3. View data\n4. Save data\n5. Exit");
        }while(choice != 5);

    }

}