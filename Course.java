import java.util.ArrayList;

public class Course {
    //Stores a course's data from CICSCourseCatalog.xlsx, where each row represents one Course.
    private String id = ""; //Gained from corresponding Column A. Ex: "M455"
    private String name = ""; //Gained from corresponding Column B. Ex: "Linear Algebra"
    private int credit = 0; //Gained from corresponding Column C. Ex: 4
    private String freq = ""; //Gained from corresponding Column D. Ex: "Fall and Spring"
    private String reqDetails = ""; //Gained from corresponding Column E. Ex: "CS Elective"
    
    //possiblePreres is gained from corresponding Column F, G, H, etc (if applicable). 
    //Ex: [ ["CS210", "CS250"], ["CS210", "M455"] ] :  where index 0 element is from Column F and index 1 element is from Column G.
    private ArrayList<ArrayList<String>> possiblePrereq = new ArrayList<ArrayList<String>>(); 


    /* Method: printDetails()
     * Prints a Course's attributes through System.out.print
     * No parameters
     * Returns void
     */
    public void printDetails(){
        System.out.println("\n\nCourse details: ");
        System.out.println("ID = " + id);
        System.out.println("Name = " + name);
        System.out.println("Credits = " + credit);
        System.out.println("Frequency = Occurs every " + freq);
        System.out.println("Requirement Details = " + reqDetails);
        System.out.print("Course prerequisites");

        for(ArrayList<String> prereq: possiblePrereq){
            System.out.print(" = [");
            for(String identity: prereq){
                System.out.print(" "+ identity+ " ");
            }
            System.out.print("]");
        }
        if(possiblePrereq.isEmpty()){
            System.out.print(" = None");
        }
    }

    /* Method: addPreReqList(ArrayList<String> pr)
     * Adds an ArrayList<String> pr to ArrayList<ArrayList<String>> possiblePrereq. 
     * 
     * Parameters: ArrayList<String> pr
     * pr should be a list of Strings that represent class IDs, that a student needs surpass in order fufill the prerequisites for the Course
     * Each ArrayList<String> in possiblePrereq represents the different combinations of classes a student can surpass to fufill the prerequisites for the Course
     * 
     * Returns void
     */
    public void addPreReqList(ArrayList<String> pr){
        possiblePrereq.add(pr);
    }




    
    ///////////////      Setters        //////////////
    //Note: there is no setter for the attribute, ArrayList<ArrayList<String>> possiblePrereq, instead there is addPreReqList(ArrayList<String> pr)

    public void setID(String i){
        id = i;
    }
    public void setName(String n){
        name = n;
    }
    public void setCredit(int c){
        credit = c;
    }
    public void setFreq(String f){
        freq = f;
    }
    public void setReqDetails(String r){
        reqDetails = r;
    }



    //////////////      Getters       //////////////

    public String getID(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getCredit(){
        return credit;
    }
    public String getFreq(){
        return freq;
    }
    public String getReqDetails(){
        return reqDetails;
    }
    public ArrayList<ArrayList<String>> getPrereq(){
        return possiblePrereq; 
    }

}
