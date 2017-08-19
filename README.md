# LWJGL3D
A 3D Game Framework Build with LWJGL 2.9.1

## Getting this Repo Working on Your Computer
This project is built upon LWJGL 2.9.1 which you can download [here](https://sourceforge.net/projects/java-game-lib/files/Official%20Releases/LWJGL%202.9.1/). You only need the lwjgl-2.9.1.zip, but you can download the others if you want. If you're using Intellij IDEA, here's how you can set up the dependency properly:
1) Open up File->Project Structure
2) In Project Settings->Modules, choose your module.
3) Click the green plus sign on the far right and choose "JARs or Directories"
4) Navigate to your extracted lwjgl folder and inside the jars folder, select both lwjgl.jar and lwjgl-util.jar
5) Click apply and close out of the window.

There's one more thing you'll have to do to get it to run. Create a new folder in your project folder called "libs". Then, from your lwjgl folder, go to native/your_operating_system/ (where your_operating_system is the system you're on) and copy all the contents of that folder into the libs folder you just made. When this is complete, if you're on Windows for example, you should have a libs folder with a bunch of .dll files inside. Now, in IDEA, open your run configurations. If you haven't already created one, go ahead and do so. The main class should be com.anzanama.lwjgl3d.Main. In the line that says "VM Options:" add "-Djava.library.path=libs/" (without the quotes of course).

Now, you should be good to run the program. I'm not sure how this process compares in other IDEs, but I imagine it's similar. Maybe at some point, I'll go through Eclipse and NetBeans and make guides for them too.

