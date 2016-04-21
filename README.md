# ExpenseApp
small project to keep track of house expenses
Few classes and packages are redundant
package myapp.users (redundant)
and in package myapp.gui, myPanel is redundant.

Basically there is just MainWindow which contains all the components,
which are  hierarchically constructed and all the top level containers are JPanels,
which contains textfields and buttons and scrollbars and jcombobox.

Main aobjective is to store all the expenses paid be the individuals in a file.
AT the end of the month we calculate who has paid how much and who owes who.

The front end is in myapp.gui package and a few constants in myapp.constants 
and the main calculation is in myapp.helper, it only has one class AppHelper.

