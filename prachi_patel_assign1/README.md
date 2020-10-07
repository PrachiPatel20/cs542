# CSX42: Assignment 1
## Name: Prachi S Patel

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in coursesRegistration/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile coursesRegistration/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile coursesRegistration/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile coursesRegistration/src/build.xml run -Darg0="courseInfo.txt" -Darg1="student_coursePrefs.txt" -Darg2="registration_results.txt" 

Note: Arguments accept the absolute path of the files.


-----------------------------------------------------------------------
## Description:
First input should always be the CourseInfo and second input should always be the student_coursePrefs.
Formatting for <3 courses is:
111:G,I, ::SatisfationRating=12

Cited:-
1.https://www.tutorialspoint.com/javaexamples/file_read.htm
2.https://www.javatpoint.com/java-filewriter-class
3.https://forum.processing.org/two/discussion/11883/how-to-append-a-text-to-a-file
-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 09-19-2019


