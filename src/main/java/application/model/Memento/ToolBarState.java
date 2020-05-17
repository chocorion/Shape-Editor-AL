package application.model.Memento;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ToolBarState {

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

    public String getState() {
        File file = new File("resources/save/toolbarState.txt");
        if(file.length() == 9) return "resources/save/toolbarState.txt";
       return "resources/save/toolbarState.txt";
    }
}
