package application.view.areas;

import application.model.areas.TopBar;
import application.model.shape.Rectangle;
import application.utils.Color;
import application.utils.ImageManager;
import application.utils.ModelObservable;
import application.utils.ModelObserver;
import application.view.ObserverDecoration;
import application.view.View;
import application.view.ViewDecorator;
import javafx.util.Pair;

import java.util.ArrayList;


public class TopBarView extends ViewDecorator implements ObserverDecoration {
    private TopBar topBar;
    private static int BUTTON_MARGIN = 4;

    private ArrayList<Pair<Rectangle, String>> buttons;

    public TopBarView(View view, TopBar topBar) {
        super(view);
        this.topBar = topBar;

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
                            createButton(topBar.getX(), topBar.getY(), topBar.getButtonWidth(), BUTTON_MARGIN, i),
                            buttonNames[i]
                    )
            );
        }

    }

    @Override
    public void draw() {
        super.draw();
        int borderSize = 1;
        // draw topBar
        super.drawRectangle(new Rectangle(topBar.getX(), topBar.getY(), topBar.getWidth(), topBar.getHeight(), Color.BLACK));
        super.drawRectangle(new Rectangle(topBar.getX() + borderSize, topBar.getY() + borderSize, topBar.getWidth() - 2 * borderSize, topBar.getHeight() - 2 * borderSize, Color.WHITE));

        drawButtons();

    }

    @Override
    public void update() {
        this.draw();
    }

    private Rectangle createButton(int topBarX, int topBarY, int size, int margin, int buttonId) {
        return new Rectangle(
                topBarX + buttonId * (size + margin/2) + margin,
                topBarY + margin,
                size,
                size
        );
    }

    private void drawButtons() {
        for (Pair<Rectangle, String> pair : buttons) {
            super.drawImage(
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

    @Override
    public ModelObservable getSubject() {
        return this.topBar;
    }
}
