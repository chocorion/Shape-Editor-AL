package application.view.areas;

import application.model.shape.Rectangle;
import application.utils.Color;
import application.utils.ImageManager;
import application.utils.ModelObservable;
import application.view.*;
import javafx.util.Pair;

import java.util.ArrayList;


public class TopBarView {
    private ArrayList<Pair<Rectangle, String>> buttons;
    private Rectangle area;
    private ViewBridge view;

    private static int BUTTON_WIDTH;
    private static int BUTTON_MARGIN;
    private static int BUTTON_HEIGHT;

    public TopBarView(ViewBridge view) {
        this.view = view;

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
                            createButton(area.getX(), area.getY(), BUTTON_WIDTH, BUTTON_HEIGHT, BUTTON_MARGIN, i),
                            buttonNames[i]
                    )
            );
        }

    }

    public void draw() {
        // draw topBar
        view.drawRectangle(area);

        view.drawRectangle(
                new Rectangle(
                        area.getX() + Layout.BORDER,
                        area.getY() + Layout.BORDER,
                        area.getWidth() - 2 * Layout.BORDER,
                        area.getHeight() - 2 * Layout.BORDER,
                        Color.WHITE
                )
        );

        drawButtons();
    }

    public void update() {
        area = Layout.getTopBar();
        this.draw();
    }

    private Rectangle createButton(double topBarX, double topBarY, int width, int height, int margin, int buttonId) {
        return new Rectangle(
                topBarX + buttonId * (width + margin/2) + margin,
                topBarY + margin,
                width,
                height
        );
    }

    private void drawButtons() {
        for (Pair<Rectangle, String> pair : buttons) {
            view.drawImage(
                    ImageManager.getImage(pair.getValue()),
                    pair.getKey()
            );
        }
    }

    public int getButtonId(int x, int y) {
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).getKey().isIn(x, y)) {
                return i;
            }
        }

        return -1;
    }
}
