# Worksheet Four 

## The Reflection API

This worksheet considers *Java Reflection*. 

- You must commit your changes regularly. 
- Where the questions make incremental changes to the code, you do not need to keep 
separate versions of your code, as your commits will deal with that situation. 

You should create your code under the directory `src/reflection`.

## Learning Goals

Before the next session, you should try to achieve the following learning goals:

+ Use the Reflection API for inspecting class metadata.
+ Use reflection for creating instances of classes.
+ Use reflection for accessing fields and invoking methods.


## The Exercises

The *Reflection API* allows a Java program to inspect and manipulate itself; 
it comprises the `java.lang.Class` class and the `java.lang.reflect` package, 
which represents the members of a class with `Method`, `Constructor`, and `Field` objects.

1. Write a Java program, `DescribeClass`, that reads the name of a class from the command 
   line and emits the interface of the class in Java syntax (interface or class, modifiers, 
   constructors, methods, fields; no method bodies).
   
   **Hint**: You can load a class whose name you know with `java.lang.Class.forName()`. 
   The `java.lang.Class` class offers a rich interface that enables you to inspect the 
   interface of any class.
   
   Apply this program to a set of classes and interfaces as test input. 
   You may also use the program on itself.
   
2. Write a program, `InstantiateClass`, that reads a class name and a list of arguments and 
   creates an object of that class where the read arguments are passed to the constructor.
   
   **Hint**: Treat arguments as strings. A `java.lang.Class` can enumerate its constructors. 
   Choose a constructor with the appropriate parameter count, then find the parameter types. 
   To create typed argument objects, call the proper constructors that take a string as their 
   only argument. You can call dynamic constructors using.
   
   ```
   java.lang.reflect.Constructor.newInstance()
   ```
   
3. Write a `JUnit` test, `Autograder` to help grade the internal correctness of a student’s 
   submitted program for a hypothetical assignment.
  
   Make the tests fail if the class under test has any of the following:
   + more than four fields,
   + any non-private fields,
   + any fields of type `ArrayList`,
   + fewer than two private *helper* methods, 
   + any method that has a `throws` clause, 
   + any method that returns an `int`,
   + missing a zero-argument constructor.
   
4. Usually, it is up to the programmer to write a `toString()` method for each class one creates. 
   This exercise is about writing a general `toString` method that gives a printed representation 
   of any object so that all fields are printed, and references to other things are also handled. 
   As part of the reflection API for java, it is possible to find out which fields exist for a given 
   object and to get their values. To solve this exercise, you must examine the `java.lang.reflect` 
   API in detail.  You will (probably) end up with around 50–60 lines of code, including that necessary 
   for testing your solution.

------
#Redo Q2 for constructor params of other types
##Notes
*1hr*
Java implicitly creates a constructor if none is provided. 
Therefore, all classes have 1 constructor at minimum.

When extending from a super class: Java implicitly calls super if a super() call is missing.

If a parent class has the same variable name and it's parent also does too, then when the child class
makes a call to super, only the immediate parent variable name can be inherited. 
The "grandparent" variable is not accessible.

Don't call methods from constructors apart from a few such as:
+ final methods [which cannot be overridden]
+ static methods
+ private methods

###For coursework: use Q2 and String params to help out

For testing, loops might not be good.
This is because a failure in one part of the loop doesn't check the rest of the loop.
Use parameterized tests.

@BeforeEach and @BeforeAll for JUnit tests

----
###Design Patterns (Week 5)
In a Singleton constructor, would there be an issue if the constructor 
had to do something resource-intensive?
Issues with multi-threading
Can the reflection API affect/break this pattern?

