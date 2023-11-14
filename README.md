# README
**Assumption of the program**
1. Every room can access to its neighbours.
2. If one room can be seen by someone standing in the other, this means that they are neighbour and there is no pet in the room
4. The pet moves follow the DFS

**Limitations**
1. The game can only played in the terminal.

**Design changes**  
No

**Citations**
1. CS5010 Milestone 3 - GamePlay
2. Java tutorial https://docs.oracle.com/javase/tutorial/

**Run explanation**  
Two example running is in the /res. They are runningExample1.txt and runningExample2.txt.  
And res/example.png shows the layout of the world.  
Here is the run explanation: 

Adding arguments or not adding arguments are both OK
> java -jar TheWorld.jar  
> java -jar TheWorld.jar [filePath] [MaxTurnNumber]
> 
If you choose to run without arguments, It will show the following output at first:

>Please enter the path of the world file:  


Then after entering the filepath "mansion.txt", it will ask you to enter the max turn number:
>Please enter the turn number:

Then, the gaming world will get initialized, and the room name and index will be printed.
for example, the information of first room is:
> 0. Armory


Then, you will need to add the player, the process is shown as follows:
>Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game  
1  
Please enter the name of the player:  
player1  
Please enter the capacity:  
2  
Please enter the index of the room that the player is in:  
0  
Add player successfully, the current player information:  
Target name: Doctor Lucky, Current room: Armory  
Character name: player1, Current room: Armory, items: [], player type: human  

So, the player1 is added.
We could also add a computer player by pressing 2.  
To finish the adding player process, just enter 3 to start the game.

The prompt will show the information of the player which is in the turn, 
the room where the player is in, and the information of the target and pet.

Then, the player could choose to move to neighbour, pick up an item, look around, move the pet, or attempt to kill the target.
```
Now we are in the 1 turn
The player in current turn is: p1

The items of the player are:


The information of the room that the player is in:
Room name:Drawing Room
Characters: p1,
Items: Letter Opener,
Neighbours: [Dining Hall, ], [Armory, ], [Foyer, ], [Wine Cellar, ],

The status of target and pet are:
Target room: Armory, health: 5
Pet room: Armory

Enter 1 to move to neighbour. Then enter the direction and index of the neighbour
Enter 2 to pick up an item in the room. Then enter the index of the item
Enter 3 to look around
Enter 4 to move the pet. Then enter the direction and index of the neighbour
Enter 5 to attempt to kill the target. Then enter the index of item you want to use(enter 0 if you do not have any item)

```
To quit the game, just enter quit or q to finish it.
>q  
Game quit!

To see an example running of the game, please see the runningExample1.txt and runningExample2.txt in the res folder.

