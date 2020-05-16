package application.view.element.interaction;

import application.view.element.TextInput;

public class TextInputInteraction extends Interaction {
    private TextInput textInput;

    public TextInputInteraction(TextInput textInput) {
        this.textInput = textInput;
    }

    public boolean onLeftClickPressed(int x, int y) {
        return textInput.isIn(x, y);
    }

    public boolean onKeyPressed(String keyCode, int mouseX, int mouseY) {
        if (keyCode.equals("ENTER")) {
            return false;
        }

        else if (keyCode.equals("BACK_SPACE")) {
            textInput.remove();
        }

        else if (keyCode.length() == 1) {
            textInput.append(keyCode);
        }
        return true;
    }
}
