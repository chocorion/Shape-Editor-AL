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

        // draw topBar
        super.drawRectangle(new Rectangle(1, 1, topBar.getWidth() - 2, topBar.getHeight() - 2, Color.BLACK));
        super.drawRectangle(new Rectangle(2, 2, topBar.getWidth() - 4, topBar.getHeight() - 4, Color.WHITE));

        super.drawRectangle(new Rectangle(4, 4, topBar.getButtonWidth(), topBar.getButtonWidth()));
        super.drawRectangle(new Rectangle(topBar.getButtonWidth() + 6, 4, topBar.getButtonWidth(), topBar.getButtonWidth()));
        super.drawRectangle(new Rectangle(2 * (topBar.getButtonWidth() + 2) + 4, 4, topBar.getButtonWidth(), topBar.getButtonWidth()));

    }

    @Override
    public void update() {
        this.draw();
    }
}
