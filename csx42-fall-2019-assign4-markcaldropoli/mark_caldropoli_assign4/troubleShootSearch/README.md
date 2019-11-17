# CSX42: Assignment 4
## Name: Mark Caldropoli

Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in troubleShootSearch/src folder.

## Instruction to clean:

Command: ant -buildfile troubleShootSearch/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instruction to compile:

Command: ant -buildfile troubleShootSearch/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

## Instruction to run:

Command: ant -buildfile troubleShootSearch/src/build.xml run -Darg0="technicalInfo.txt" -Darg1="synonyms.txt" -Darg2="userInput.txt" -Darg3="output.txt"

Note: Arguments accept the absolute path of the files. The order of the files are as follows:
technicalInfo.txt, synonyms.txt, userInput.txt, output.txt

## Description:

MyArrayList uses an ArrayList of String for the technical info lines and a HashMap of String to String for the synonyms. MyTree uses a Binary Search Tree implementation to store the words given in the technical info.

File Input Order:
1. technicalInfo.txt
2. synonyms.txt
3. userInput.txt
4. output.txt

MyLogger Values:
[0:ERROR] - Errors only, prints nothing unless error detected
[1:SEARCH_RESULTS] - Search results, prints output to console
[2:FILE_PROCESSOR] - File processor, prints all input lines processed
[3:MYARRAYLIST] - MyArrayList element, prints all operations performed on MyArrayList
[4:MYTREE] - MyTree element, prints all operations performed on MyTree

I set the value in the driver of the submitted code to be 1 so the search results are printed to both the output file and the console.
This can be modified using one of the above levels that I specified.
I made the assumption that since the assignment only takes in the 4 file arguments, we shouldn't take in a debug level as an argument.

I used 0 slack days for this assignment and have 0 slack days remaining.

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken."

Date: [11/17/2019]
Mark Caldropoli


