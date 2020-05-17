package application.model.Memento;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ToolBarState {

    public ToolBarState(String str) {
        if(!str.isEmpty()) {
            // ImageManager.class.getResource("/images/undo.png").toString()
            File file = new File(ToolBarState.class.getResource("/save/toolbarState.txt").toString());
            try {
                FileWriter output = new FileWriter(this.getClass().getResource("/save/toolbarState.txt").getPath());
                output.write(str);
                output.close();
            } catch (IOException e) {
                // File not found
                e.printStackTrace();
            }
        }
    }

    public String getState() {
        System.out.println("Toolbar getstate, path -> " + ToolBarState.class.getResource("/save/toolbarState.txt").getPath());
        File file = new File(ToolBarState.class.getResource("/save/toolbarState.txt").getPath());
        System.out.println("In get state, file is -> " + file);

        if(file.length() == 9) return "/save/toolbarFirst.txt";
       return "/save/toolbarState.txt";
    }
}
