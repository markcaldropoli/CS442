# CSX42: Assignment 3
## Name: Mark Caldropoli

Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in dynamicLoadBalancer/src folder.

## Instruction to clean:

Command: ant -buildfile dynamicLoadBalancer/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instruction to compile:

Command: ant -buildfile dynamicLoadBalancer/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

## Instruction to run:

Command: ant -buildfile dynamicLoadBalancer/src/build.xml run -Darg0="input.txt" -Darg1="output.txt"

Note: Arguments accept the absolute path of the files.

## Description:

I followed the data structure layout given in the code snippets provided in the assignment.
For the load balancer internal data structure, I used a Map (HashMap) of String to ServiceManager.
I used all 4 slack days for this assignment and have 0 slack days remaining.

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken."

Date: [11/6/2019]
Mark Caldropoli

