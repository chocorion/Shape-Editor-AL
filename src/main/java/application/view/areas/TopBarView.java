package application.view.areas;

import application.model.shape.Rectangle;
import application.utils.Color;
import application.utils.ImageManager;
import application.utils.ModelObservable;
import application.view.*;
import javafx.util.Pair;

import java.util.ArrayList;


/**
 * Represent the view of the top bar.
 */
public class TopBarView extends ViewBridge {
    private ArrayList<Pair<Rectangle, String>> buttons;
    private Rectangle area;

    private static int BUTTON_WIDTH;
    private static int BUTTON_MARGIN;
    private static int BUTTON_HEIGHT;

    /**
     * Parameterized constructor.
     * @param concreteView Implementation to use for the draw.
     */
    public TopBarView(IConcreteView concreteView) {
        super(concreteView);

        area = Layout.getTopBar();
        BUTTON_MARGIN = (int) (area.getHeight()/6);
        BUTTON_HEIGHT  = (int) area.getHeight() - 2 * BUTTON_MARGIN;
        BUTTON_WIDTH = (int) area.getWidth()/10;

        buttons = new ArrayList<>();

        String[] buttonNames = {
                "undo",
                "redo",
                "export",
                "import"
        };

        for (int i = 0; i < 4; i++) {
            buttons.add(
                    new Pair<>(
                            createButton(area.getMinX(), area.getMinY(), BUTTON_WIDTH, BUTTON_HEIGHT, BUTTON_MARGIN, i),
                            buttonNames[i]
                    )
            );
        }
    }


    /**
     * Draw the topBar.
     */
    public void draw() {
        drawRoundedRectShadow((int)area.getMinX(),
                             (int)area.getMinY(),
                       (int)area.getWidth()-2*Layout.BORDER,
                      (int) area.getHeight()-2*Layout.BORDER,
                     5,
                    2*Layout.BORDER,
                              new Color(77,104,119)
        );

        drawButtons();
    }


    /**
     * Update the topBar view.
     */
    public void update() {
        area = Layout.getTopBar();
        this.draw();
    }


    /**
     * Create a rectangle representing a basic button.
     * @param topBarX X coords of the topBar.
     * @param topBarY Y coords of the topBar.
     * @param width Width of the topBar.
     * @param height Height of the topBar.
     * @param margin Value for the marge.
     * @param buttonId Button index.
     * @return Rectangle representing the button.
     */
    private Rectangle createButton(double topBarX, double topBarY, int width, int height, int margin, int buttonId) {
        return new Rectangle(
                topBarX + buttonId * (width + margin/2) + margin,
                topBarY + margin,
                width,
                height
        );
    }


    /**
     * Draws all the buttons.
     */
    private void drawButtons() {
        for (Pair<Rectangle, String> pair : buttons) {
            drawImage(
                    ImageManager.getImage(pair.getValue()),
                    pair.getKey()
            );
        }
    }


    /**
     * Computes the index of the button for the given position.
     * @param x X coords of the point.
     * @param y Y coords of the point.
     * @return The index of the button if exists, else -1.
     */
    public int getButtonId(int x, int y) {
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).getKey().isIn(x, y)) {
                return i;
            }
        }

        return -1;
    }
}
