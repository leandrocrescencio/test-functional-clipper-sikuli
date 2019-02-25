# test-functional-clipper-sikuli
Some ideas for automating the UI of a Clipper app using Java + Sikuli


## Project configuration ##
- File **config.properties** 
* located on **src\main\test\resources**
* **Environment** points to the path within the project and name of the legacy execution .bat (in the model YOURAPP.BAT)


## Sikuli Images ##
* The images are used only to check where is the app and to save the location to navegate through the app, without other images.
* located on **src\main\test\resources\objects**
* There may be variations in the different machines due to resolution, which causes the application screen not to be found even if it opens, in case this happens it is necessary to redo the screen prints. 
* The name of the files should be the same as the code: **src\main\java\appobjects\SikuliAppObject.java**
  * '**app_win7.png**' 
  * '**app_win10.png**' 
  * '**app_2016.png**'
