package application.model.Memento;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ToolBarState {

    public ToolBarState(String str) {
        if(!str.isEmpty()) {
            File file = new File("src/main/resources/save/toolbarState.txt");
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
        File file = new File(ToolBarState.class.getResource("/save/toolbarState.txt").getPath());
        if(file.length() == 9) return "/save/toolbarFirst.txt";
       return "/save/toolbarState.txt";
    }
}
