package application.model.Memento;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Represent class that save the state of the toolbar.
 */
public class ToolBarState {

    /**
     * Parameterized constructor.
     * @param str Representation of the toolbar state.
     */
    public ToolBarState(String str) {
        if(!str.isEmpty()) {
            File file = new File("resources/save/toolbarState.txt");
            try {
                FileWriter output = new FileWriter(file.getAbsoluteFile());
                output.write(str);
                output.close();
            } catch (IOException e) {
                // File not found
                e.printStackTrace();
            }
        }
    }


    /**
     * Return the path to the current toolbar state.
     * @return Path to the current state.
     */
    public String getState() {
        File file = new File("resources/save/toolbarState.txt");
        if(file.length() == 9) return "resources/save/toolbarState.txt";
       return "resources/save/toolbarState.txt";
    }
}
