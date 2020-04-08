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
        super.drawRectangle(new Rectangle(0, 0, topBar.getWidth(), topBar.getHeight(), Color.LIGHT_GREY));
        super.drawRectangle(new Rectangle(2, 2, topBar.getButtonWidth(), topBar.getButtonWidth()));
        super.drawRectangle(new Rectangle(topBar.getButtonWidth() + 4, 2, topBar.getButtonWidth(), topBar.getButtonWidth()));
        super.drawRectangle(new Rectangle(2 * (topBar.getButtonWidth() + 2) + 2, 2, topBar.getButtonWidth(), topBar.getButtonWidth()));

    }

    @Override
    public void update() {
        this.draw();
    }
}
