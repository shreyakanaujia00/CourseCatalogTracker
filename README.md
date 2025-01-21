# CourseCatalogTracker
A tool that reads that metadata of a college's (UMass Amherst's Manning College of Information & Computer Sciences) course catalog and enables users (students who are getting their BS in Computer Science) to plan their degree.

- Reads data from an excel sheet that contains UMass Amherst CICS course catalog metadata
- Represents the data through the defined class 'Course'
- Includes several methods aimed for undergraduate students pursuing a BS in Computer Science to search through the catalog for course selection.

## Files:
### CICSCourseCatalog.xlsx
This is an Excel sheet that includes the metadata of the UMass Amherst CICS course catalog.
This data is manually collected from the [UMass Amherst CICS course webpage](https://www.cics.umass.edu/academics/courses).

Though the specific metadata can change, the format and set up of this sheet needs to stay consistent in order for method, readCourseCatalogExcel() in mainActivity.java, to read and store the data using Apache POI. 

Table set up:
Row 1 is reserved for headers and is skipped by readCourseCatalogExcel(). 
Following rows represent a course that is offered from 
Column A consists of course IDs. 
Column B consists of course names. 
Column C consists of the courses' number of credits. 
Column D consists of the courses' frequency: whether they're offered during the fall, spring, or both semesters. 
Column E consists of the courses' requirement details that are aimed for students who are getting their BS in Computer Science. This is aligned with the degree requirements that BS in CS Students need to complete the mandatory classes, the Integrative Expierence requirement, the Junior Year Writing requirement, the College of Natural Science Lab Science requirement, 3 CS300+ electives, and 3 CS400+ electives in order to graduate.
Column F and following non-empty columns consist of the courses' prerequisites courses. Each column contains a possible list of courses that a student must have already completed in order to attend the row's course. For example, Course CS348 prerequisites, according to the [UMass Amherst CICS course webpage](https://www.cics.umass.edu/academics/courses), includes CS210 and CS240 and (CS250 or M455). This data is represented in two columns because a student can attend the Course CS348 if they have completed the courses: CS210, CS240, and CS250, or have completed the courses: CS210, CS240, and M455.

### Course.java
This is a class structure named Course, that's used to represent a course in the CICS course catalog. It includes attributes for each column of metadata in CICSCourseCatalog.xlsx. It also includes setters, getters, a print all attributes method: printDetails(), and an appending to the prereq attribute: addPreReqList(ArrayList<String> pr).
For more details on the methods, see comments within the file. 

### userCourses.txt
This is a simple text file that has all the courses the user (student) has completed. This data is represented in course IDs and each ID is seperated by a new line.
This file is read in the method readUserCoursesFile() in mainActivity.java.

### mainActivity.java
The main method automatically runs methods readUserCoursesFile() and readUserCoursesFile(), which collectively read all the data and store some of it through the class structure 'Course'. 
Then it allows users to see a menu and choose what method to utilize. 
At this moment, users can see course catalog metadata, and find what classes they are elligible to take based on the prerequisites. 
For more details on the methods, see comments within the file. 

*Note: more methods and features will likely be added in the soon future. 

### lib
Includes Apache pacakge poi-bin-5.2.3 and log4j-core.jar version 2.18.0


