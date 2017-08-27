# LWJGL3D
A Work-in-progress 3D Game Framework Build with LWJGL 2.9.1 and the Slick-Util library in Java. Currently, there isn't much here, but I am constantly working on it and updating, adding new things, as well as improvements to old ones.

If you find any issues with anything here, make sure to post them in the issues tab (unless there's already a post there for your issue). I greatly appreciate any feedback/criticism/help I can get, so don't be afraid to dish it out.

## Getting this Repo Working on Your Computer
This project is built upon LWJGL 2.9.1 which you can download [here](https://sourceforge.net/projects/java-game-lib/files/Official%20Releases/LWJGL%202.9.1/) and SlickUtil which can be found [here](http://slick.ninjacave.com/slick-util/). You only need the lwjgl-2.9.1.zip, but you can download the others if you want. (TODO: make a script/gradle task to handle this). 

If you're using Intellij IDEA, here's how you can set up the dependency properly:
1) Open up File->Project Structure
2) In Project Settings->Modules, choose your module.
3) Click the green plus sign on the far right and choose "JARs or Directories"
4) Navigate to your extracted lwjgl folder and inside the jars folder, select both lwjgl.jar and lwjgl-util.jar
5) Repeat steps 3 & 4 to add slick-util.jar from wherever you put it.
6) Click apply and close out of the window.

There's one more thing you'll have to do to get it to run. 
1) Create a new folder in your project folder called "libs". 
2) Then, from your lwjgl folder, go to native/your_operating_system/ (where your_operating_system is the system you're on) and copy all the contents of that folder into the libs folder you just made. When this is complete, if you're on Windows for example, you should have a libs folder with a bunch of .dll files inside. 
3) Now, go into IDEA and open your run configurations. If you haven't already created one, go ahead and do so. 
4) The main class should be com.anzanama.lwjgl3d.Main. 
5) In the line that says "VM Options:" add "-Djava.library.path=libs/" (without the quotes of course).

Now, you should be good to run the program. I'm not sure how this process compares in other IDEs, but I imagine it's similar. Maybe at some point, I'll go through Eclipse and NetBeans and make guides for them too.

## Documentation
Once I get a bit further along in working on the larger parts of this and closer to being able to push out a first major release, I'll begin working on documentation. This will probably come in the form of javadocs, as well as a wiki on this repo.

## Helping Out
If you'd like to help out with the project, feel free to submit a pull request. This is no guarantee that I'll accept it, but so long as your changes help the project along, I'll probably accept them. If you want to work with me directly and help out with code/documentation/etc., you can contact me via Discord (AnZaNaMa \#3242) or email me at recovery22306@gmail.com
