# LWJGL3D
An abandoned from-scratch 3D Game Framework I built with LWJGL 2.9.1 and the Slick-Util library in Java. The idea behind this framework was to create a game development platform like Unity or Godot for coders. I am interested in game development, but I find myself frustrated with the amount of time I spend fiddling around with GUIs in those programs, when I really want to be writing code.

This engine was designed to be a solid launching off point for developing games without having to deal with GUIs. I eventually plan to go back and start from scratch to create a framework that can handle multiple graphics engines (OpenGL, Vulkan, maybe DirectX) in C++, but I haven't had time for that yet.

Features that were implemented before the project was abandoned:
* Model System
  * Supports models from .obj files, as well as raw models created through a passed VAO (vertex and other model data)
  * Model system is robust and hierarchical, so support for other model types can easily be added
  * Supports textured models
* Custom rendering system built from scratch using the LWJGL OpenGL bindings
* Lighting system
  * Works in tandem with model and terrain systems to provide realistic reflections, lighting, and shadows
  * Supports colored lights
* Configuration System
  * Custom configuration system which allows games to add options for their players to customize
  * Supports boolean, integer, and float values
  * Will automatically generate a default configuration file if one does not exist or is missing
* GameObject System
  * The bread and butter of the framework. Everything in the world is a GameObject of some kind
  * The main game loops allow every game object to hook into the update() and render() functions so they can control themselves.
  * Built in support for attaching Models to GameObjects and rendering is automatically handled
  * Camera system that allows for free cameras the player can control directly, or follow cameras that will follow a GameObject
* World System
  * The world is automatically broken up into "chunks" which contain GameObjects
  * The engine's main game loop runs an update on every chunk that is loaded, which in turn causes GameObjects in the chunk to update
  * I ran into an issue here where GameObjects that move often need to be transitioned between chunks during an update.
  * To combat the above, I added the WorldChange system, which allows GameObjects to schedule WorldChanges during their update phase to move between chunks
  * This somewhat complicates logic, as GameObjects have to keep in mind that they cannot technically belong to a new Chunk until the next update phase
  * I plan to rework this a bit when I recreate the engine
* Terrain System
  * Custom terrain system that generates terrain based on provided heightmaps
  * Heightmaps are black and white and use the greyscale to distinguish heights
  * Supports textured terrain through TerrainTexturePacks
  * Terrain Texture Packs consist of up to 4 textures that are applied to terrain based on a given texture mask
  * The mask is a .png file which uses the r, g, b, a channels to distinguish between textures. This allows for transitions between textures and overlaying textures.
* Input System
  * Custom modular input system that allows games to add "Axes" which allow for additional inputs
  * Built-in support for Keyboard and Mouse, as well as DualShock 4 controllers
  * System allows for implementation of Input to any device the dev desires
* Full shader support that allows for custom shaders
* Inlcuded Gradle setup that can handle dependencies
  * Added task to automatically set up native files for LWJGL based on user's operating system

All of the systems in this engine/framework were designed to be modular and extensible. Every part of this project implements MVC principles, so the same Input mechanics can be used for AI, networking won't require a full rewrite if implemented, etc.

The engine is not quite functional to the point you could create a full game, as it doesn't have a physics engine. However, it is in a good enough state that you can put together a world of arbitrary size and tour it.

If you find any issues with anything here, you can post them in the Issue tracker. However, I make no guarantees on whether I'll see them, as I am no longer actively working on this project and plan to rebuild it from scratch in the future.

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

### Edit 12/14/2023
I was just looking through my repositories and noticed I never put a License in this one. This project is completely free and open-source, and falls under the MIT License as stated below

##License
Copyright 2023 Andrew C. Graber

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

## Helping Out
If you'd like to help out with the project, feel free to submit a pull request. This is no guarantee that I'll accept it, but so long as your changes help the project along, I'll probably accept them. If you want to work with me directly and help out with code/documentation/etc., you can contact me via Discord (AnZaNaMa \#3242) or email me at recovery22306@gmail.com
