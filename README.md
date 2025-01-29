# sr-scoreboard

## Requirements
The scoreboard supports the following operations:
1. Start a new match, assuming initial score 0 – 0 and adding it the scoreboard.
   This should capture following parameters:
   a. Home team
   b. Away team
2. Update score. This should receive a pair of absolute scores: home team score and away
   team score.
3. Finish match currently in progress. This removes a match from the scoreboard.
4. Get a summary of matches in progress ordered by their total score. The matches with the
   same total score will be returned ordered by the most recently started match in the
   scoreboard. 


I used several assumptions mainly about the name of the teams, handling the edge cases where the team name is null or 
an empty string.   

I created 4 test classes, each containing tests during a TDD development of the particular feature.

BaseTest class is used as a superclass for each test so that code duplication is avoided.

I used Factory pattern to create an instance of the Scoreboard, where I used dependency injection to use in-memory
repository implementation of the scoreboard list of matches.

I used the SOLID principles to encapsulate the functionality in separate methods and classes. For example, the 
Scoreboard class only implements code logic that is related to the scoreboard functionalities. The Match
repository only implements the logic of saving and reading the data about matches.The MatchRepository interface, 
where this API is closed for modification, is open for extending its implementation. The implementation can be easily
changed by another repository implementation which implements the methods defined by the interface.

The implementation can be extended to support thread safety, for example, if the public methods of the Scoreboard class
are marked as synchronized. That would mean that if one thread calls a method from this class, a lock on the object 
will be acquired, and another thread would not be able to execute a synchronized method on this object until the previous
method finishes its execution and releases the lock.

From the aspect of fault tolerance, this implementation throws exceptions when faulty parameters are used when its
methods are called. Fault tolerance can be implemented in the code using this implementation by silently handling these
exceptions. If explicitly specified, the code can be changed so that it handles these faults itself (for example, 
instead of throwing an exception when finishing a match that has not been started, this occurrence can be just logged and
the method implementation would return as if it was a successful operation).

I would like to mention, because of trying to keep the implementation simple (KISS), I did not implement any 
logging strategy. Also, to keep it simple, when the match is finished, it is deleted from the list.
In production (depending on the requirements), a no deletion strategy can be used 
(ex: add status to the Match model, and mark the matched with finished status instead 
of deleting them)

Also, I tried to write the code clean, and self-explanatory, and kept the need of java docs
at minimum.



