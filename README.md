# Name List Editor
## Version: 2.1
Retains the same look as v.2.0, but has many new features to expand the functionality. 

Built using JavaFX

### Added Features for v.2.1:
- "Add to Current List" under the "File" menu, which will append a new list to the currently open list.
- Fixed the code on "Space Invaders" to allow for names of different lengths (ex. John Smith or John C. Smith Sr.)
- "Basket Case" tab, which allows the customization of the wording case.
- Changed the button names on "Tabby Tab" to be more self explanatory.
- "Blank Tab Title" on "Tabby Tab", which allows the insertion of a blank space before the title.
- "Badger Mode" tab, which allows the setup for an entire job on a single line printing.
- Badger Mode gives the user the option of an infinite line, or specific amount of badges per line. 

**Top Menu:**
- File
    - Open
    - Add to Current List - appends a new list to the currently open list
    - Save
    - Close

- Tools
    - Reset Original List - will undo any changes and revert back to original list

- Help
    - About

**Tabs:**
- Space Invaders: performs the same functions as version 1
    - Analyze Spaces - Searches any content of the text area and notifies the user if any extra spaces are present. The program will tell the user how many extra spaces are found and asks the user if he / she would like to remove them. 
    - Remove Spaces - Will skip the first step and simply remove any extra spaces.

- Basket Case: allows the customization of the wording case. The customizations can be applied to the full list, or only specific lines. 

- Tabby Tab: will produce a tab delimited output based on the button used. 
    - A seperate button depending on how many lines the job is.
    - "Blank Tab Title", is to add a [TAB] (blank line) between name and title.
> (ex. FirstName [TAB] LastName [TAB] Title1 [TAB] Title2 [TAB])

- Badger Mode: A custom setup for when the full list of names are needed on a single line, or a specific number of names per line. 

> Make sure that you're doing a badge setup of only one type (ex. only four tabs, or only five types), as you will get an error if you attempt to tab out a five tab list and if one of the names only has four lines. 

### Added Features for v.2.0:
- Split window, to compare both the original and modified lists.
- Tabs, for the expandability to add new (and future) functions.
- Messages display at the bottom frame, instead of in a pop-up window.

Background: My wife works for a company that makes name badges, "Hello my name is...". Customers would send in a name list for their badge orders and any extra spaces, either between first & last name, or at the end of the last name, would throw off any of the names following. This could be really bad for instance a list 1,000+ names which was either very difficult, or very time consuming, to find manually. 

The inspiration behind Name List Editor was that of a Notepad format, one that a list, or multiple lists, could be simply copy / pasted into the text area and corrected. 
