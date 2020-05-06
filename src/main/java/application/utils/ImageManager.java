package application.utils;

import java.util.HashMap;

public class ImageManager {
    private static HashMap<String, String> images;

    static {
        images = new HashMap<>();

        images.put("undo", ImageManager.class.getResource("/images/undo.png").toString());
        images.put("redo", ImageManager.class.getResource("/images/redo.png").toString());
        images.put("import", ImageManager.class.getResource("/images/import.png").toString());
        images.put("export", ImageManager.class.getResource("/images/export.png").toString());
        images.put("trash", ImageManager.class.getResource("/images/trash.png").toString());
    }

    public static String getImage(String name) {
        if (images.containsKey(name)) {
            return images.get(name);
        }

        return null;
    }
}
