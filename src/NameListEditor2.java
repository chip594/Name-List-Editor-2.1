package fbpi;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.text.WordUtils;

public class NameListEditor2 extends Application {

    Image icon;

    public NameListEditor2() {
        icon = new Image(NameListEditor2.class.getResource("icon.png").toExternalForm());
    }

    Stage stage;
    Scene scene;
    TextArea leftTextArea, rightTextArea, bottomBarDisplay;
    TextField badgerModeTextField;
    String originalList, rightContents, line1, line2, line3, line4, line5, line6;
    CheckBox capsBox1, capsBox2, capsBox3, capsBox4, capsBox5, capsBox6;

    public static void main(String[] args) { launch(args);}

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("Name List Editor 2.1");

        //Create the Top Menu
        VBox topMenu = new VBox();

        //Create the Menu Bar
        MenuBar menuBar = new MenuBar();

        //Create toolbar separators
        Separator verticalSeparatorTab1 = new Separator();
        Separator verticalSeparatorTab2 = new Separator();
        Separator verticalSeparatorTab3 = new Separator();
        Separator verticalSeparatorTab4 = new Separator();

        //Create the File Menu
        Menu fileMenu = new Menu("File");
        //Adds an open option to file menu
        MenuItem openOption = new MenuItem("Open");
        openOption.setOnAction(e -> openFile());
        //Adds an add to file option to file menu
        MenuItem addToFile = new MenuItem("Add To Current List");
        addToFile.setOnAction(e -> addToFile());
        //Adds a save option to file menu
        MenuItem saveOption = new MenuItem("Save");
        saveOption.setOnAction(e -> saveFile());
        //Adds a close option to file menu
        MenuItem closeOption = new MenuItem("Close");
        closeOption.setOnAction(e -> stage.close());
        //Adds the File Menu's options
        fileMenu.getItems().addAll(openOption, addToFile, saveOption, closeOption);

        //Create the Tools Menu
        Menu toolsMenu = new Menu("Tools");
        MenuItem resetOriginalList = new MenuItem("Reset Original List");
        resetOriginalList.setOnAction(e -> resetOriginalList());
        toolsMenu.getItems().addAll(resetOriginalList);

        //Create the Help Menu
        Menu helpMenu = new Menu("Help");
        MenuItem aboutOption = new MenuItem("About");
        //Sets the action for the About button
        aboutOption.setOnAction(e -> aboutWindow());
        helpMenu.getItems().addAll(aboutOption);

        //Adds the Menus to the Menu Bar
        menuBar.getMenus().addAll(fileMenu, toolsMenu, helpMenu);

        //Create the Tab Pane
        TabPane tabPane = new TabPane();

        //Create the Space Invaders Tab
        Tab tab1 = new Tab("Space Invaders");
        tab1.setClosable(false);
        //Creates Tab 1's Tool Bar
        ToolBar tab1ToolBar = new ToolBar();
        Button analyzespacesButton = new Button("Analyze Spaces");
        //Sets the action for the Analyze Spaces button
        analyzespacesButton.setOnAction(e -> analyzeSpaces());
        Button removeSpacesButton = new Button("Remove Spaces");
        //Sets the action for the Remove Spaces button
        removeSpacesButton.setOnAction(e -> removeSpaces());
        //Adds the buttons to the Space Invaders Tool Bar
        tab1ToolBar.getItems().addAll(analyzespacesButton, removeSpacesButton);
        tab1.setContent(tab1ToolBar);

        //Create Tabby Tab Tab
        Tab tab2 = new Tab("Tabby Tab");
        tab2.setClosable(false);
        //Creates Tab 2's Tool Bar
        ToolBar tab2ToolBar = new ToolBar();
        //Creates the Tabby Tab's Buttons & assigns their action
        Button twoLinerButton = new Button("2 Liner");
        twoLinerButton.setOnAction(e -> twoLinerTab());
        Button threeLinerButton = new Button("3 Liner");
        threeLinerButton.setOnAction(e -> threeLinerTab());
        Button fourLinerButton = new Button("4 Liner");
        fourLinerButton.setOnAction(e -> fourLinerTab());
        Button fiveLinerButton = new Button("5 Liner");
        fiveLinerButton.setOnAction(e -> fiveLinerTab());
        Button sixLinerButton = new Button("6 Liner");
        sixLinerButton.setOnAction(e -> sixLinerTab());
        Button blankTabTitleButton = new Button("Blank Tab Title");
        blankTabTitleButton.setOnAction(e -> twoLinerFirstBlank());
        //Adds the buttons to the Tabby Tab Tool Bar
        tab2ToolBar.getItems().addAll(twoLinerButton, threeLinerButton, fourLinerButton, fiveLinerButton, sixLinerButton, verticalSeparatorTab2, blankTabTitleButton);
        tab2.setContent(tab2ToolBar);

        //Create Fix Case Tab
        Tab tab3 = new Tab("Basket Case");
        tab3.setClosable(false);
        ToolBar tab3ToolBar = new ToolBar();
        Button normalCase = new Button("All Normal Case");
        normalCase.setOnAction(e -> fixCase("normal"));
        Button lowerCase = new Button("All Lower Case");
        lowerCase.setOnAction(e -> fixCase("lower"));
        Button upperCase = new Button("All Upper Case");
        upperCase.setOnAction(e -> fixCase("upper"));
        Label whichBlankLineLabel = new Label("Or Pick -->:");
        capsBox1 = new CheckBox("Line 1");
        capsBox2 = new CheckBox("Line 2");
        capsBox3 = new CheckBox("Line 3");
        capsBox4 = new CheckBox("Line 4");
        capsBox5 = new CheckBox("Line 5");
        capsBox6 = new CheckBox("Line 6");
        tab3ToolBar.getItems().addAll(normalCase, lowerCase, upperCase, verticalSeparatorTab3, whichBlankLineLabel, capsBox1, capsBox2, capsBox3, capsBox4, capsBox5, capsBox6);
        tab3.setContent(tab3ToolBar);

        //Create Badger Mode
        Tab tab4 = new Tab("Badger Mode");
        tab4.setClosable(false);
        ToolBar tab4ToolBar = new ToolBar();
        Button moveTextButton = new Button("Shift Text Left");
        moveTextButton.setOnAction(e -> moveTextLeft());
        badgerModeTextField = new TextField();
        badgerModeTextField.setMaxWidth(45);
        Button howManyTabsButton = new Button("<-- This Many Tabs");
        howManyTabsButton.setOnAction(e -> nonInfiniteTabsBadgerMode());
        Button infiniteTabs = new Button("Infinite Tabs");
        infiniteTabs.setOnAction(e -> infiniteTabsBadgerMode());
        Label howManyTabsLabel = new Label("Or Pick -->:");
        tab4ToolBar.getItems().addAll(moveTextButton, verticalSeparatorTab4, infiniteTabs, howManyTabsLabel, badgerModeTextField, howManyTabsButton);
        tab4.setContent(tab4ToolBar);

        //Adds the Tabs to the Tab Pane
        tabPane.getTabs().addAll(tab1, tab3, tab2, tab4);

        //Create a Label over each Text Area
        GridPane labelGrid = new GridPane();
        labelGrid.getStyleClass().add("text-area-label");
        labelGrid.setPrefHeight(20);
        Label originalLabel = new Label("Original Name List");
        Label modifiedLabel = new Label("Modified Name List");
        labelGrid.setConstraints(originalLabel, 0, 1);
        labelGrid.setConstraints(modifiedLabel, 1, 1);
        labelGrid.setHgrow(originalLabel, Priority.SOMETIMES);
        labelGrid.setHgrow(modifiedLabel, Priority.SOMETIMES);
        labelGrid.setHalignment(originalLabel, HPos.CENTER);
        labelGrid.setHalignment(modifiedLabel, HPos.CENTER);
        labelGrid.getChildren().addAll(originalLabel, modifiedLabel);

        //Create the Side-by-Side Text Area
        HBox centerText = new HBox();
        leftTextArea = new TextArea();
        leftTextArea.setPromptText("Original Name List");
        rightTextArea = new TextArea();
        rightTextArea.setPromptText("Modified Name List");
        centerText.setHgrow(leftTextArea, Priority.ALWAYS);
        centerText.setHgrow(rightTextArea, Priority.ALWAYS);

        //Create bottom panel / display bar
        bottomBarDisplay = new TextArea("");
        bottomBarDisplay.getStyleClass().add("bottom-bar");
        bottomBarDisplay.setPrefHeight(10);
        bottomBarDisplay.setEditable(false);

        //Create Main Frame & Attaches the Elements
        centerText.getChildren().addAll(leftTextArea, rightTextArea);
        topMenu.getChildren().addAll(menuBar, tabPane, labelGrid);
        BorderPane layout = new BorderPane();
        layout.setTop(topMenu);
        layout.setCenter(centerText);
        layout.setBottom(bottomBarDisplay);

        //Creates the main scene
        scene = new Scene(layout, 1000, 800);
        stage.setScene(scene);
        scene.getStylesheets().setAll(getClass().getResource("FBPI.css").toExternalForm());
        stage.getIcons().add(icon);
        stage.show();
    }

    public void aboutWindow() {
        Pane aboutPane = new Pane();
        WebView browser = new WebView();
        Scene aboutScene = new Scene(aboutPane, 620, 600);
        Stage aboutStage = new Stage();
        aboutStage.setTitle("About Window HTML");
        aboutStage.setScene(aboutScene);
        aboutPane.getChildren().add(browser);
        URL url = getClass().getResource("about.html");
        browser.getEngine().load(url.toExternalForm());
        aboutStage.show();
    }

    public void resetOriginalList() {
        leftTextArea.setText("");
        leftTextArea.setText(originalList);
    }

    public void openFile() {

        FileChooser fc = new FileChooser();
        fc.setTitle("Open File");
        File openFile = fc.showOpenDialog(stage);
        if (openFile != null) {
            try {
                leftTextArea.setText("");
                Scanner scan = new Scanner(new FileReader(openFile.getPath()));
                while (scan.hasNext())
                    leftTextArea.appendText(scan.nextLine() + "\r\n");
           }
           catch (IOException e) {
                errorMessage();
           }
        }
    }

    public void addToFile() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Add To File");
        File addToFile = fc.showOpenDialog(stage);
        if (addToFile != null) {
            try {
                Scanner scan = new Scanner(new FileReader(addToFile.getPath()));
                while (scan.hasNext())
                    leftTextArea.appendText(scan.nextLine() + "\r\n");
            }
            catch (IOException e) {
                errorMessage();
            }
        }
    }

    public void saveFile() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save File");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT Files", "*.txt"));
        File saveFile = fc.showSaveDialog(stage);
        if (saveFile != null) {
            try {
                FileWriter fw = new FileWriter(saveFile);
                fw.write(rightContents);
                fw.close();
            }
            catch (IOException e) {
                e.printStackTrace();
                bottomBarDisplay.setText("An ERROR occured while saving the file!");
                return;
            }
            bottomBarDisplay.setText("File Saved: " + saveFile.toString());
        }
        else {
            bottomBarDisplay.setText("File save cancelled.");
        }
    }

    public void actionComplete() {
        bottomBarDisplay.setStyle("-fx-text-fill: BLACK;");
        bottomBarDisplay.setText("Modification completed, don't forget to save your new list!");
    }

    public void errorMessage() {
        bottomBarDisplay.setStyle("-fx-text-fill: RED;");
        bottomBarDisplay.setText("Oops, there was a problem. Check the Original Name List for errors");
    }

    public void analyzeSpaces() {
        String contents = leftTextArea.getText();
        Integer spaceCounter = 0;
        String hemi = "Hemi";
        String it = "it";
        Pattern p = Pattern.compile("\\s+[ ]");
        Matcher m = p.matcher(contents);
        while (m.find()) {
            spaceCounter++;
        }
        if (spaceCounter != 1) {
            hemi = "Hemi's";
            it = "Them";
        }
        bottomBarDisplay.setText("Found " + spaceCounter + " " + hemi + " , Click Remove Spaces if You'd Like to Remove " + it);
    }

    public void removeSpaces() {
        //Grabs the contents of the left side text area
        String contents = leftTextArea.getText();
        originalList = leftTextArea.getText();
        //Splits those contents and adds them to a String Array, return is the delimiter
        String[] contentsToSplit = contents.split("\\s*\\r?\\n\\s*");
        //Creates a StringBuilder to build the layout
        StringBuilder sb = new StringBuilder();
        //iterates through the Array to concatenate the names the way I want them
        try {
            for (int i = 0; i < contentsToSplit.length; i++) {
                String line = contentsToSplit[i];
                String[] contentsLine = line.split("\\s+".trim());
                for (int a = 0; a < contentsLine.length; a++) {
                    if (a > 0) {
                        sb.append(" ");
                    }
                    sb.append(contentsLine[a]);
                }
                sb.append("\r\n");
            }
            //Create a String Variable with the value of each line formed by the StringBuilder
            rightContents = sb.toString();
            //Displays that new String out to the right side text area
            rightTextArea.setText(rightContents);
            leftTextArea.setText(rightContents);
            actionComplete();
        }
        catch (Exception e) {
            errorMessage();
        }
    }

    public void twoLinerTab() {
        String contents = leftTextArea.getText();
        String[] contentsToSplit = contents.split("\\s*\\r?\\n\\s*");
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < contentsToSplit.length; i+=2) {
                line1 = contentsToSplit[i];
                line2 = contentsToSplit[i + 1];

                capitalizeLines(capsBox1, capsBox2, capsBox3, capsBox4, capsBox5, capsBox6);

                sb.append(line1 + "\t" + line2 + "\r\n");
            }
            rightContents = sb.toString();
            rightTextArea.setText(rightContents);
            actionComplete();
        }
        catch (Exception e) {
            errorMessage();
        }
    }

    public void twoLinerFirstBlank() {
        String contents = leftTextArea.getText();
        String[] contentsToSplit = contents.split("\\s*\\r?\\n\\s*");
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < contentsToSplit.length; i++) {
                line1 = contentsToSplit[i];

                capitalizeLines(capsBox1, capsBox2, capsBox3, capsBox4, capsBox5, capsBox6);

                sb.append(" " + "\t" + line1 + "\r\n");
            }
            rightContents = sb.toString();
            rightTextArea.setText(rightContents);
        }
        catch (Exception e) {
            errorMessage();
        }
    }

    public void threeLinerTab() {
        String contents = leftTextArea.getText();
        String[] contentsToSplit = contents.split("\\s*\\r?\\n\\s*");
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < contentsToSplit.length; i+=3) {
                line1 = contentsToSplit[i];
                line2 = contentsToSplit[i+1];
                line3 = contentsToSplit[i+2];

                capitalizeLines(capsBox1, capsBox2, capsBox3, capsBox4, capsBox5, capsBox6);

                sb.append(line1 + "\t" + line2 + "\t" + line3 + "\r\n");
            }
                rightContents = sb.toString();
                rightTextArea.setText(rightContents);
                actionComplete();
        }
        catch (Exception e) {
            errorMessage();
        }
    }

    public void fourLinerTab() {
        String contents = leftTextArea.getText();
        String[] contentsToSplit = contents.split("\\s*\\r?\\n\\s*");
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < contentsToSplit.length; i+=4) {
                line1 = contentsToSplit[i];
                line2 = contentsToSplit[i + 1];
                line3 = contentsToSplit[i + 2];
                line4 = contentsToSplit[i + 3];

                capitalizeLines(capsBox1, capsBox2, capsBox3, capsBox4, capsBox5, capsBox6);

                sb.append(line1 + "\t" + line2 + "\t" + line3 + "\t" + line4 + "\r\n");
            }
            rightContents = sb.toString();
            rightTextArea.setText(rightContents);
            actionComplete();
        }
        catch (Exception e) {
            errorMessage();
        }
    }

    public void fiveLinerTab() {
        String contents = leftTextArea.getText();
        String[] contentsToSplit = contents.split("\\s*\\r?\\n\\s*");
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < contentsToSplit.length; i += 5) {
                line1 = contentsToSplit[i];
                line2 = contentsToSplit[i + 1];
                line3 = contentsToSplit[i + 2];
                line4 = contentsToSplit[i + 3];
                line5 = contentsToSplit[i + 4];

                capitalizeLines(capsBox1, capsBox2, capsBox3, capsBox4, capsBox5, capsBox6);

                sb.append(line1 + "\t" + line2 + "\t" + line3 + "\t" + line4 + "\t" + line5 + "\r\n");
            }
            rightContents = sb.toString();
            rightTextArea.setText(rightContents);
            actionComplete();
        }
        catch (Exception e) {
            errorMessage();
        }
    }

    public void sixLinerTab() {
        String contents = leftTextArea.getText();
        String[] contentsToSplit = contents.split("\\s*\\r?\\n\\s*");
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < contentsToSplit.length; i+=6) {
                line1 = contentsToSplit[i];
                line2 = contentsToSplit[i + 1];
                line3 = contentsToSplit[i + 2];
                line4 = contentsToSplit[i + 3];
                line5 = contentsToSplit[i + 4];
                line6 = contentsToSplit[i + 5];

                capitalizeLines(capsBox1, capsBox2, capsBox3, capsBox4, capsBox5, capsBox6);

                sb.append(line1 + "\t" + line2 + "\t" + line3 + "\t" + line4 + "\t" + line5 + "\t" + line6 + "\r\n");
            }
            rightContents = sb.toString();
            rightTextArea.setText(rightContents);
            actionComplete();
        }
        catch (Exception e) {
            errorMessage();
        }
    }

    public void fixCase(String fixCase) {
        String contentsRight, contentsLeft;
        if (fixCase == "lower") {
            contentsLeft = leftTextArea.getText();
            leftTextArea.setText(contentsLeft.toLowerCase());
            contentsRight = rightTextArea.getText();
            rightTextArea.setText(contentsRight.toLowerCase());
        }
        if (fixCase == "upper") {
            contentsLeft = leftTextArea.getText();
            leftTextArea.setText(contentsLeft.toUpperCase());
            contentsRight = rightTextArea.getText();
            rightTextArea.setText(contentsRight.toUpperCase());
        }
        if (fixCase == "normal") {
            contentsLeft = leftTextArea.getText();
            leftTextArea.setText(WordUtils.capitalizeFully(contentsLeft));
            contentsRight = rightTextArea.getText();
            rightTextArea.setText(WordUtils.capitalizeFully(contentsRight));
        }
        actionComplete();
    }

    public void capitalizeLines(CheckBox capsBox1, CheckBox capsBox2, CheckBox capsBox3, CheckBox capsBox4, CheckBox capsBox5, CheckBox capsBox6) {
        if(capsBox1.isSelected())
            line1 = line1.toUpperCase();
        if(capsBox2.isSelected())
            line2 = line2.toUpperCase();
        if(capsBox3.isSelected())
            line3 = line3.toUpperCase();
        if(capsBox4.isSelected())
            line4 = line4.toUpperCase();
        if(capsBox5.isSelected())
            line5 = line5.toUpperCase();
        if(capsBox6.isSelected())
            line6 = line6.toUpperCase();
    }

    public void moveTextLeft() {
        String contents = rightTextArea.getText();
        leftTextArea.setText(contents);

    }

    public void infiniteTabsBadgerMode() {
        String contents = leftTextArea.getText();
        String[] contentsToSplit = contents.split("\\s*\\r?\\n\\s*");
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < contentsToSplit.length; i++) {
                String line = contentsToSplit[i];
                if (i > 0)  {
                    sb.append("\t");
                }
                sb.append(line);
            }
            sb.append("\r\n");
            rightContents = sb.toString();
            rightTextArea.setText(rightContents);
            actionComplete();
        }
        catch (Exception e) {
            errorMessage();
        }
    }

    public void nonInfiniteTabsBadgerMode() {
        String tabCountBMText = badgerModeTextField.getText();
        int tabCountBM = Integer.parseInt(tabCountBMText);
        String contents = leftTextArea.getText();
        String[] contentsToSplit = contents.split("\\s*\\r?\\n\\s*");
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < contentsToSplit.length; i++) {
                    if (i > 0) {
                        sb.append("\t");
                    }
                    if (i % tabCountBM == 0 && i != 0) {
                        sb.append("\r\n");
                    }
                    sb.append(contentsToSplit[i]);
            }
            sb.append("\r\n");

            rightContents = sb.toString();
            rightTextArea.setText(rightContents);
            actionComplete();
        }
        catch (Exception e) {
            errorMessage();
        }
    }
}

