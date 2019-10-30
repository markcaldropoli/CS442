# CSX42: Assignment 1
## Name: Mark Caldropoli

Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in coursesRegistration/src folder.

## Instruction to clean:

Command: ant -buildfile coursesRegistration/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instruction to compile:

Command: ant -buildfile coursesRegistration/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

## Instruction to run:

Command: ant -buildfile coursesRegistration/src/build.xml run -Darg0="student_coursePrefs.txt" -Darg1="courseInfo.txt" -Darg2="registration_results.txt" 

Note: Arguments accept the absolute path of the files.

## Description:

For the scheduler algorithm, I used an ArrayList of Student to hold the students, an array of Course to hold the courses, and an ArrayList of ArrayLists of Student to hold the students assigned to each course. I chose to use these data structures because they have O(1) access time which is the most used operation for this assignment. Additionally, insertion into arrays and arraylists is O(n) time. I used ArrayList as opposed to an array for the students since we don't have a fixed number for each input file. On the other hand, I used an array for courses since we know there will always be 9 courses. I chose to use an ArrayList of ArrayLists of students for the matching portion of the algorithm since it made the most sense to me to map the course names to indexes and have a list of enrolled students for each course.

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken."

Date: [9/19/2019]
Mark Caldropoli



