README for HW8
Name: Yilei (Jerry) Bao
Bao.yil@northeastern.edu

************************ Project Introduction ***********************

This project builds an program that can read in commands from an input file and create either a graphical or a web view of various snapshots (of shapes) contained in an album. The user can select the input command file, type of view, and potentially the output file name, and the maximum window size via command line arguments. 

************************* Program Structure *************************

The program now has three packages: model(shapesmodel), controller(shapescontroller), and view(shapesview). Additionally, the project also has a Main class called PhotoAlbumMain.

1. shapesmodel
This package is the same as the shapesalbum package in HW7, with slight modifications and fixes specified below in the section "Updates From HW7". 
It contains: classes to model a rectangle or an oval, the utility class containing methods used to modify shapes, the class that represent a snapshot, and the class that represent an album of snapshots. Relevant interfaces are also included. Additionally, it also has a class ("InputFileReader") to read commands from a txt file and execute accordingly (add shapes, modify shapes, take screenshots, etc.). This class will produce the Album object which can be rendered by the views. 

2. shapescontroller
This package contains a single class ("ShapesController") that reads in command line arguments and create the view asked by the command line arguments from the designated input command file. It acts as the bridge between the model and the view.

3. shapesview
This package is responsible for visualizing the album output from shapescontroller into either a graphical or a web view, upon the user's request. 
It contains: the MainFrame (i.e., display window) for the graphical view, the body panel(the actual snapshots) and header panel (snapshots' IDs and descriptions) for the MainFrame, and the web view. Relevant interfaces are also included. 

4. PhotoAlbumMain
This is the central control of the entire program. It reads in various command line arguments and present the user with the desired view of the album. 
The command line arguments include 3 mandatory arguments: -in for input command file name, -view or -v for the desired view (either "graphical" or "web"), and -out for output html file name (only relevant to the web view). There are also 2 optional arguments: the maximum width and maximum height of the window size for graphical view, default to 1000 by 1000. 

Additionally, in the "resources" directory, users can find a JAR file for the program ("hw8.jar"), and an example web view. ("buildingsOut.html") There's also an Application run configuration ("hw8.run.xml") that chooses PhotoAlbumMain as its main class and has several command line arguments as examples. 

************************** Updates From HW7 *************************

1. Deep copy. 
In HW7, I thought I achieved deep copy but I didn't. When we modify the list of shapes, snapshots that have already been taken will also change accordingly. In HW8, I added a copySelf() method. Now, when we modify the shapes, the past snapshots will remain unchanged. 

2. ShapesAction method updates. 
I updated various static methods in the ShapesAction class. The application model now supports modification to shapes based on shape's name.

