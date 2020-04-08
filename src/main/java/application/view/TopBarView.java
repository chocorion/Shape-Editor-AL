package application.view;

import application.model.areas.TopBar;
import application.model.shape.Rectangle;
import application.utils.Color;
import application.utils.ModelObserver;

public class TopBarView extends ViewDecorator implements ModelObserver {
    private TopBar topBar;

    public TopBarView(View view, TopBar topBar) {
        super(view);
        this.topBar = topBar;
    }

    @Override
    public void draw() {
        super.draw();
        int borderSize = 1;
        // draw topBar
        super.drawRectangle(new Rectangle(topBar.getX(), topBar.getY(), topBar.getWidth(), topBar.getHeight(), Color.BLACK));
        super.drawRectangle(new Rectangle(topBar.getX() + borderSize, topBar.getY() + borderSize, topBar.getWidth() - 2 * borderSize, topBar.getHeight() - 2 * borderSize, Color.WHITE));

        super.drawRectangle(new Rectangle(4, 4, topBar.getButtonWidth(), topBar.getButtonWidth()));
        super.drawRectangle(new Rectangle(topBar.getButtonWidth() + 6, 4, topBar.getButtonWidth(), topBar.getButtonWidth()));
        super.drawRectangle(new Rectangle(2 * (topBar.getButtonWidth() + 2) + 4, 4, topBar.getButtonWidth(), topBar.getButtonWidth()));

    }

    @Override
    public void update() {
        this.draw();
    }
}
