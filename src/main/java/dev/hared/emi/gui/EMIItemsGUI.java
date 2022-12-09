package dev.hared.emi.gui;

import dev.hared.emi.api.EMIGuiAPI;
import dev.hared.emi.api.EMIMatrix;
import dev.hared.emi.api.EMITextBox;

public class EMIItemsGUI extends EMIBasicGui{

    private EMITextBox textBox;

    @Override
    public void initGUI(EMIGuiAPI api) {
        super.initGUI(api);
        this.textBox = api.createNewTextBox(0, 0, 0);
        this.components.add(textBox);
    }

    @Override
    public void render(EMIMatrix matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);

        this.textBox.setX(this.leftX + 2);
        this.textBox.setY(this.barBottomY + 2);
        this.textBox.setBoxWidth(this.rightX - this.leftX - 4);

        this.drawComponents(matrices, mouseX, mouseY, delta);
    }
}
