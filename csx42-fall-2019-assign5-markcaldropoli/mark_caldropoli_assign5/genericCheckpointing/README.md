# CSX42: Assignment 5
This assignment's submission is my own work and I did not discuss with any other past or current student, nor did I have access to a previous submission of this assignment by another student.

## Name: Mark Caldropoli

Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in genericCheckpointing/src folder.

## Instruction to clean:

Command: ant -buildfile genericCheckpointing/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instruction to compile:

Command: ant -buildfile genericCheckpointing/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

## Instruction to run:

Command: ant -buildfile genericCheckpointing/src/build.xml run -Darg0="deserser" -Darg1="checkpoint.txt" -Darg2="checkpoint-verify.txt" -Darg3="0"

Note: Arguments accept the absolute path of the files. checkpoint.txt is the file to be deserialized, checkpoint-verify.txt is the file resulting from serializing the objects obtained from the deserialized file.

## Description:

Logger Values:
Defaults to 0 if not equal to 0 or 1.
[0:ERROR] - Errors only, prints nothing unless error detected 
[1:DATA] - Data and errors, prints data and any detected errors to console

I used a List (ArrayList) of SerializableObject as my data structure to hold the deserialized objects.

I used 0 slack days for this assignment and have 0 slack days remaining.

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken."

Date: [12/7/2019]
Mark Caldropoli




