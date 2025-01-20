import java.util.ArrayList;
import java.util.HashSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Iterator;

import java.io.FileInputStream; 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 

/*
BS in CS Students need to do all mandatory classes, IE req, JYW Req, CNS Lab Science Req, 3 300+ CS Elective, 3 400+ CS Elective in order to graduate.
 */
// NOTE: could create a new class called StudentProgress - in which stores an hashset of courses, and the degree requirment progress. 

public class mainActivity {
    
    /*
     * Main method:
     * Creates a menu that 1. Prints the college's course catalog or 2. Prints the courses the user can take based on the prerequisites.
     */
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        ArrayList<Course> catalog = readCourseCatalogExcel();
        HashSet<String> userCourses = readUserCoursesFile();  //HashSet is used to reduce time complextity for method potentialCourses()
        int choice;
        do{
            System.out.println("\n\n------------------------------------------------------------------------------------\nMenu:\n0. Quit\n1. Print UMass CICS course catalog\n2. Potential Classes the student can take: ");
            choice = input.nextInt();
            System.out.println("\n------------------------------------------------------------------------------------");
        
            switch(choice){
                case 1:
                    for(Course x: catalog){
                        x.printDetails();
                    }
                    break;

                case 2: 
                    ArrayList<Course> potential = potentialCourses(catalog, userCourses);
                    System.out.println("The student can take the following classes because the prereqs are met: ");
                    for(Course c: potential){
                        c.printDetails();
                    }
                    break;
            } 
        

        } while(choice!=0);
        
        input.close(); 
    }

    
    /* Method: readCourseCatalogExcel()
     * Using Apache POI, reads and stores the college's course catalog metadata in the excel sheet, CICSCourseCatalog.xlsx, into 'Courses' and returns a list of all the 'Courses' (courseCatalog).
     * If an Exception thrown, prints the Exception's stack trace instead.
     * 
     * No parameters
     * Returns an ArrayList of Courses.
     */
    public static ArrayList<Course> readCourseCatalogExcel(){
        ArrayList<Course> courseCatalog = new ArrayList<Course>();

        try{
            FileInputStream file = new FileInputStream(new File("CICSCourseCatalog.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator(); 
            Row row = rowIterator.next(); //used to skip the first row - which is filled of headers

            while (rowIterator.hasNext()) {  //each row
                row = rowIterator.next();
                Course course = new Course(); 

                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) { //each cell in a row
                    Cell cell = cellIterator.next(); 
                    
                    
                    if(cell.getCellType() != CellType.BLANK){
                       
                        if(cell.getColumnIndex() >= 5){
                            ArrayList<String> prereq = new ArrayList<String>();
                            String regex = " ";
                            String[] prArray = cell.getStringCellValue().split(regex);
                            for (String pr: prArray){
                                prereq.add(pr);
                            }
                            course.addPreReqList(prereq);
                        }
                        else{
                            switch(cell.getColumnIndex()){
                                case 0:
                                    course.setID(cell.getStringCellValue());
                                break;
                                case 1:
                                    course.setName(cell.getStringCellValue());
                                break;
                                case 2:
                                    course.setCredit((int)cell.getNumericCellValue());
                                break;
                                case 3:
                                    course.setFreq(cell.getStringCellValue());
                                break;
                                case 4:
                                    course.setReqDetails(cell.getStringCellValue());
                                break;
                            }
                        }
                    }
                }
                //course.printDetails();
                courseCatalog.add(course);
            }
            workbook.close();
            file.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return courseCatalog;
    }
 
    /* Method: readUserCoursesFile()
     * Using java.io.File, reads and stores userCourses.txt into a Hashset of Strings where each string represents the course ID of a course the user has completed. 
     * If an FileNotFoundException thrown, prints the "An error occurred." along with the stack trace instead.
     * 
     * No parameters
     * Returns an HashSet of Strings.
     */
    public static HashSet<String> readUserCoursesFile(){
        HashSet<String> userCourses = new HashSet<>(); 
        try {
            File myObj = new File("userCourses.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              userCourses.add(data);
            }
            myReader.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return userCourses;
    }

    /* Method: potentialCourses()
     * Goes through an ArrayList of Courses that represent the college offered courses and goes through a Hashset of Strings of the user completed courses
     * and returns an ArrayList of courses that represent all the courses the user has fufilled the prerequisites for. 
     * If an FileNotFoundException thrown, prints the "An error occurred." along with the stack trace instead.
     * 
     * Parameters: ArrayList<Course> catalog, HashSet<String> userCourses.
     * Returns an ArrayList of Courses.
     */
    public static ArrayList<Course> potentialCourses(ArrayList<Course> catalog, HashSet<String> userCourses){
        //HashSet is used because .contains has a time complexity of O(1) while ArrayList .contains has a time complexity of O(N); 
        ArrayList<Course> potential = new ArrayList<Course>(); 
        boolean add = true; 

        for(Course c: catalog){ //O(N)
            for(ArrayList<String> pr: c.getPrereq()){ //O(M)
                for(String p: pr){ //O(P)
                    if(!userCourses.contains(p)){ //O(1)
                        add = false; //O(1)
                    }
                }
                if( (!userCourses.contains(c.getID())) && add){ //O(1)
                    potential.add(c); //O(1)
                    break;
                }
                add = true;
            }
        }

        //Time complexity: O(N * M * P) where N = size of 'catalog' and M =  average size of possiblePrereq and P = average size of a course's prereqs

        return potential;
    }

}



