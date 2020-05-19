package application.view;


/**
 * Simple interface for drawable objects.
 */
public interface IDrawable {
    /**
     * Draw the object at the giver position.
     * @param x X coords for the draw.
     * @param y Y coords for the draw.
     */
    void draw(int x, int y);
}
