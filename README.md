# README
**Run TheWorld.jar to run the main function.**  
Adding arguments or not adding arguments are both OK
> java -jar TheWorld.jar  
> java -jar TheWorld.jar [filePath] [MaxTurnNumber]
> 
If you choose to run without arguments, It will show the following output at first:

>Please enter the path of the world file:  


Then after entering the filepath "mansion.txt", it will ask you to enter the max turn number:
>Please enter the turn number:

Then, the gaming world will get initialized, and the room information will be printed,
for example, the information of first room is:
>SpecifiedRoom{name='Armory', index=0,
leftCorner=19, rightCorner=26, upperCorner=22, lowerCorner=23,  
items in the room =[Revolver],  
characters=[Target name: Doctor Lucky, Current room: Armory]},  
neighbours = [Dining Hall, Billiard Room, ][][][Drawing Room, ],  
visibleRooms = [Dining Hall, Billiard Room, Trophy Room, Library, Nursery, Tennessee Room, Lilac Room, Master Suite, Drawing Room, Wine Cellar, ]  


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
and the room where the player is in. 
>Game start! The players are listed as follow:    
Target name: Doctor Lucky, Current room: Armory    
Character name: player1, Current room: Armory, items: [], player type: human   
Now we are in the 1 turn  
The player in current turn is: player1  
The information of the room that the player is in:  
---------------------------------
>SpecifiedRoom{name='Armory', index=0,  
leftCorner=19, rightCorner=26, upperCorner=22, lowerCorner=23,  
items in the room =[Revolver],  
characters=[Target name: Doctor Lucky, Current room: Armory, Character name: 3player1, Current room: Armory, items: [], player type: human]},  
neighbours = [Dining Hall, Billiard Room, ][][][Drawing Room, ],  
visibleRooms = [Dining Hall, Billiard Room, Trophy Room, Library, Nursery, Tennessee Room, Lilac Room, Master Suite, Drawing Room, Wine Cellar, ]  
---------------------------------
>Enter 1 to move to neighbour. Then enter the direction and index of the neighbour  
Enter 2 to pick up an item in the room. Then enter the index of the item  
Enter 3 to look around  

To quit the game, just enter quit or q to finish it.
>q  
Game quit!

Two example running is in the /res. They are runningExample1.txt and runningExample2.txt.


