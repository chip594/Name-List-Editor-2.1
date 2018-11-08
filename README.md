# Name List Editor
## Version: 2.1
This is a remake of my first "real" program, the original version of *Name List Editor*. I wanted to expand on the functionality of the initial version and improve the GUI. 

Almost completely rebuilt using JavaFX

### Added Features for v.2.0:
- Split window, to compare both the original and modified lists.
- Tabs, for the expandability to add new (and future) functions.
- Messages display at the bottom frame, instead of in a pop-up window.

**Top Menu:**
- File
    - Open
    - Save
    - Close

- Help
    - About

**Tabs:**
- Space Invaders: performs the same functions as version 1
    - Analyze Spaces - Searches any content of the text area and notifies the user if any extra spaces are present. The program will tell the user how many extra spaces are found and asks the user if he / she would like to remove them. 
    - Remove Spaces - Will skip the first step and simply remove any extra spaces.

- Tabby Tab: will produce a tab delimited output based on the button used. 
> (ex. FirstName [TAB] LastName [TAB] Title1 [TAB] Title2 [TAB])

> Make sure that you're doing a badge setup of only one type (ex. only four tabs, or only five types), as you will get an error if you attempt to tab out a five tab list and if one of the names only has four lines. 

Background: My wife works for a company that makes name badges, "Hello my name is...". Customers would send in a name list for their badge orders and any extra spaces, either between first & last name, or at the end of the last name, would throw off any of the names following. This could be really bad for instance a list 1,000+ names which was either very difficult, or very time consuming, to find manually. 

The inspiration behind Name List Editor was that of a Notepad format, one that a list, or multiple lists, could be simply copy / pasted into the text area and corrected. 
