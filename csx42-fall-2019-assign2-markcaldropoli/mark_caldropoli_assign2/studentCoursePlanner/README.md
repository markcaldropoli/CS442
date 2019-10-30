# CSX42: Assignment 2
## Name: Mark Caldropoli

Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in studentCoursePlanner/src folder.

## Instruction to clean:

Command: ant -buildfile studentCoursePlanner/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instruction to compile:

Command: ant -buildfile studentCoursePlanner/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

## Instruction to run:

Command: ant -buildfile studentCoursePlanner/src/build.xml run -Darg0="input.txt" -Darg1="output.txt"

Note: Arguments accept the absolute path of the files.

## Description:

My program uses ArrayList<Character> for the waitlist which is in the Student class.
When the Student object is created, it populates the list of courses (waitlist) with all of
the courses from the file input and checks if there are any duplicates. If there are no
duplicates, the Student is successfully created. Later, when the Student's processCourse
method is called from the current state, it will check if there are courses left to process
and if there are, it will get the course at the front of the list and check if the course's
prereqs are satisfied. If the prereqs are satisfied, it will get removed from the waitlist
and the course will be processed as taken during the current semester. Courses are not
added to the waitlist after the initial Student creation, instead they are removed when
processed and processed in the order that they were provided in the input file as specified.

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken."

Date: [10/8/2019]
Mark Caldropoli

