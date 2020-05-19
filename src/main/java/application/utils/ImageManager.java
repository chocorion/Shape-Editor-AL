package application.utils;

import java.util.HashMap;

/**
 * Manage the path for image used in the application.
 */
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


    /**
     * Get the path for the given image.
     * @param name Name of the image;
     * @return The path of the image if exists, else null.
     */
    public static String getImage(String name) {
        if (images.containsKey(name)) {
            return images.get(name);
        }

        return null;
    }
}
