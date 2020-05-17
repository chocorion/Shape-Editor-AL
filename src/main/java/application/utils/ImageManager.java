package application.utils;

import java.util.HashMap;

public class ImageManager {
    private static HashMap<String, String> images;

    static {
        images = new HashMap<>();

        images.put("undo", "resources/images/undo.png");
        images.put("redo", "resources/images/redo.png");
        images.put("import", "resources/images/import.png");
        images.put("export", "resources/images/export.png");
        images.put("trash", "resources/images/trash.png");
    }

    public static String getImage(String name) {
        if (images.containsKey(name)) {
            return images.get(name);
        }

        return null;
    }
}
