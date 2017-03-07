Food Bank program
by Preston Haynes
prestonfrommaine@gmail.com

The MIT License (MIT)

Copyright (c) 2017 Preston Haynes

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

This program is intended for a food pantry to decide how many food items
to be purchased. The program revieces a text file and produces a text file
with the results of how many food items to purchase.


TO RUN:
========================
BUILD OUTPUT DESCRIPTION
========================

When you build an Java application project that has a main class, the IDE
automatically copies all of the JAR
files on the projects classpath to your projects dist/lib folder. The IDE
also adds each of the JAR files to the Class-Path element in the application
JAR files manifest file (MANIFEST.MF).

To run the project from the command line, go to the dist folder and
type the following:

java -jar "FoodBank.jar" 

To distribute this project, zip up the dist folder (including the lib folder)
and distribute the ZIP file.

Notes:

* If two JAR files on the project classpath have the same name, only the first
JAR file is copied to the lib folder.
* Only JAR files are copied to the lib folder.
If the classpath contains other types of files or folders, these files (folders)
are not copied.
* If a library on the projects classpath also has a Class-Path element
specified in the manifest,the content of the Class-Path element has to be on
the projects runtime path.
* To set a main class in a standard Java project, right-click the project node
in the Projects window and choose Properties. Then click Run and enter the
class name in the Main Class field. Alternatively, you can manually type the
class name in the manifest Main-Class element.

INPUT INSTRUCTIONS:

To modify the output, change the text file called "input.txt" located in the same
folder as the "FoodBank.jar" file. 

<task-to-solve>, <budget>, <cart-weight-limit>, <cart-volume-limit>;
<item-name>, <inventory-quantity>, <item-price>, <item-weight>, <itemvolume>,<item-food-group>;


Task To Solve:
There are four tasks that calculate the number of food items to purchase differently:

Task 1 - 
Returns a the most amount of food items possible to purchase given the budget.
Weight and volume are not factored.
Task 2-
Returns a the most amount of food items possible to purchase given the budget and cart weight limit.
Volume is not factored.
Task 3-
Returns a the most amount of food items possible to purchase given the budget and cart weight limit,
and balances the number of food groups so there is a + or - 5% margin of error.
Volume is not factored.
Task 4-
Returns a the most amount of food items possible to purchase given the budget,cart weight limit, and cart volume limit.

Budget:
Amount of money in US Dollars to spend on food items

Cart Weight Limit:
"Weight" limit of cart in kilograms on earth's surface gravity

Cart Volume Limit:
Volume limit of cart in Liters

Item Name:
Name of item. CamelCasePrefered. Spaces will be autmatically deleted.

Inventory Quanity:
Whole number of food item that is available to purchase

Item Price:
Price of item in US Dollars

Item Weight:
"Weight" of individual food item in Kilograms on earth's surface gravity

Item Volume:
Volume of individual food item in Liters

Item Food Group:
Food group of food item. Examples: Grains, dairy, veggies, meat...


The program produces a text file called "output.txt" that will have the number of items to purchase
based on parameters of the task number given. 

In this format:
<item-name>, <quantity-to-buy>;



