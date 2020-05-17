package application.utils;

import java.util.HashMap;

public class ImageManager {
    private static HashMap<String, String> images;

    static {
        images = new HashMap<>();

        images.put("undo", "src/main/resources/images/undo.png");
        images.put("redo", "src/main/resources/images/redo.png");
        images.put("import", "src/main/resources/images/import.png");
        images.put("export", "src/main/resources/images/export.png");
        images.put("trash", "src/main/resources/images/trash.png");
    }

    public static String getImage(String name) {
        if (images.containsKey(name)) {
            return images.get(name);
        }

        return null;
    }
}
