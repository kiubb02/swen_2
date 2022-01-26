# Monster Trading Cards Game

This Game is built to be a platform for trading and battling with and
against each other in a magical card-game world. 

## Technical Desicions and Steps

### Designs

Before starting to implement this project I have created a first draft of how I will build this software programm. At the beginning of the Semester there wasnt a lot to work with but luckily I had the course Software Architecture which helped to figure out different Design Possibilities for a software project.

My first Design however was more C++ oriented and focused on working with classes.
This looked somewhat like that:

![image](https://user-images.githubusercontent.com/71399699/151027423-4c57913e-c131-4cf9-bb7f-d3a311fa83c4.png)

In this image one can see that I have created a class for each needed path that is mentioned in our http requests and a single database class which was supposed to handle and host all database requests/functions (sql statements). 
Therefore each class was responsible for everything concerning one path, which in the longer run became way too much too quickly.

I thought that 

### Failure

After diving a little bit deeper into the project I have noticed that my first Design should be earsed completely. It was not flexible enough, not encapsulated, not independant enough and was overall a way too shallow and trivial approach of a solution. Parting everything into classes wasnt the problem, but the fact that I still tried to solve it in a way I first learned to code was.

- Why did the first design fail?

I was not working with interfaces at all, which excluded working with multiple inheritance, which I needed to simplify the functions like Handling Requests. That way I could inherit that into each component and avoid/bypass several if-statements and switch-cases in one hanlde Request Function. 

I overcomplicated the use of the databaseclass by making it carry all the weight, instead of parting it into a CardsHanlder , BattleHandler and so on - Class.

The components/classes had more than one "gateway", which made it even harder to encapsulate and part each class from each other and therefore see them as one entity.


### Selected Solutions

In my project I have included various design patterns, depending on what the classes and or components are needed for. 

**1. Singleton**

One Design pattern I have chosen is the Singleton Pattern. It is actually an anti pattern, due to the inflexibility of that pattern, but is still used if the programmer only wants to use one object/instance of a class, which is needed across the entire system.
In my case such an object would be the Logger I am using to log actions in battles.
- **But why am I using a Singleton then?**
- I want my Logger instance to be running and logging information at any one time.

**2. Components**

Components are the main part of my project. Since we are using a curl script with designated paths/repositories (e.g users/<username>) I have decided to work upon this task by creating one component per path.
- **Why did I choose to work with components?**
- Components make it possible to achieve the single responsiblity principle: Each component gets the input from Client (in this case the http request) - works on that input through functions and later returns a response back to the client. So the only responsibility of my components is to work through the request of their designated paths.
  
![image](https://user-images.githubusercontent.com/71399699/151037421-01e03211-871a-45db-8ad8-b484b16e325a.png)

Here you can see my new architectural design. In that approach it clearly can be seen that I parted everything into components by creating packages and making sure that each component includes all functions, which makes it able to reuse the package in a new environment since I reduced the dependancies between the classes outside of each component. 

## Unit Tests

### Chosen Unit Tests

> Before you can test an entire software program, make sure the individual
> parts work properly on their own. Unit testing validates the function of a unit,
> ensuring that the inputs (one to a few) result in the lone desired output. 
  
The units I was testing where my carrying components. Components which are important for the functionality and are responsible for the outcome of the Responses to the Clients.
Therefore I check different scenarios/inputs on their return values. All of my tests are assertion unit tests.
  
  > Assert the exact desired behavior; avoid overly precise or overly loose conditions.
  
To check on those values I 
  
### Why did I test those code parts?
  
The code parts I have tested (Battle, Card, Package, Trading and User Functions) are all carrying components of my project. All of those functions have important return          values  for the Client and therefore I wanted to test these return values which are important for the entire application to work correctly and give the Client the correct        Response.

## Time Spent on the Project
  
Before starting to implement my project I created a "Semester Roadmap". In that roadmap I checked when I would work on what and how long the designated time span for each       task will be. Sadly I went down the wron path all along by working to long on a perfect database (for days) , which at the end I didnt use anymore. 
At the end I actually threw the Roadmap out of the Window and started working with a different approach.
(The exact timespans of when I worked on what exactly can be seen in my git history)

My new approach consisted of creating 5 phases. In each phase I d work on a big task:
  
  1. Phase: Setting up a Server
  2. Phase: Working on the Requests (and parallely work on the database)
  3. Phase: Creating Tests
  4. Phase: Cleaning up my Code
  5. Phase: Writing the Protocol
  
I planned on taking a few days on each phase and therefore each phase took me around 2/3 days in a sum.
In total I have worked on this Project for 3 months (including those timespans, in which I have only created database designs and more). The final implementation of my new solution after noticing that my first solution would never work out took me three weeks.

7. 1 => Setting up a new Server (5 hours)
7. 1 => Finished handling the first 13 Requests (12 Hours)
8. 1 => Correcting mistakes of Requests 10-13 and implementing the Trading Requests (The Response included a wrong HTTP Code) (12 Hours)
9. 1 => First go through , cleaning up code, creating more generalized Interfaces (6 hours)
10. 1 => User Authentication changed into an authentication class, which controlls token and more based on the Request (5 hours)
10. 1 => Started setting up the first idea of a Battle logic (6 hours) => **The Battle Logic took me the longest**
11. 1 => Changed the Names of Files to fit the Java Namingconventions (3 hours)
15. 1 - 17.1 => Trying to solve issues in my battle logic (7 hours each day)
22. 1 => Setting up and everything for the Score Requests (2 hours)
23. 1 => Implementing everything for the Score and Stats Requests, Battle has also been implemented but the damage and elo calculation has to be updated (14 hours)
24. 1 => Battle fully works, Tests have been implemented as well as extra Features are added to the Game (12 hours)
