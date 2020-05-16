package application.model.Memento;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ToolBarState {

    public ToolBarState(String str) {
        if(!str.isEmpty()) {
            File file = new File("src/main/java/application/model/Memento/toolbarState.txt");
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
        File file = new File("src/main/java/application/model/Memento/toolbarState.txt");
        if(file.length() == 9) return "src/main/java/application/model/Memento/toolbarFirst.txt";
       return "src/main/java/application/model/Memento/toolbarState.txt";
    }
}
