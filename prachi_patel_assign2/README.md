# CSX42: Assignment 2
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

####Command: ant -buildfile coursesRegistration/src/build.xml run -Darg0="input_file.txt" -Darg1="output_file.txt" 

Note: Arguments accept the absolute path of the files.


-----------------------------------------------------------------------
## Description:

The preferences are checked at first. If it can not be added in the course list then the courses are added in waitlist. When new semester starts the waitlist is checked first and then the preference.

Waitlist is obsolete for Group5 as no prerequisite is required.

After visiting all preferences the remaining courses will remain in the waitlist.

They are selected based on which group has got less then 2courses. If so then we find that course in waitlist if it exist we assign that subject to student.
Loop continues till waitlist is empty or after loop it remains unchanged.

https://howtodoinjava.com/design-patterns/behavioral/state-design-pattern/
https://stackoverflow.com/questions/38861354/removing-a-character-from-an-arraylist-of-characters
https://www.tutorialspoint.com/java/java_documentation.htm

I have used 1 slack day for this assignment
-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 10-08-2019


