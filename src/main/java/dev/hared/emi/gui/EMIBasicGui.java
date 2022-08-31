package dev.hared.emi.gui;

import net.minecraft.client.util.math.MatrixStack;

public class EMIBasicGui extends EMIAbstractGui{

    @Override
    public void initGUI() {
        System.out.println("On init");
    }

    @Override
    public void keyboardInput(int keyCode, int scanCode, int modifiers) {
        System.out.println("On key");
    }

    @Override
    public void mouseInput(double mouseX, double mouseY, int button) {
        System.out.println("On mouse");
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        System.out.println("On render");
    }
}
