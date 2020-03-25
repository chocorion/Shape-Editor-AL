package application.view;

public class WhiteBoard extends ViewDecorator {
    public WhiteBoard(View view) {
        super(view);
    }

    @Override
    public void draw() {
        super.draw();

        // Draw big white rectangle
    }
}