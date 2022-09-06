package dev.hared.emi.gui;

import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

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
        Color color = new Color(57, 63, 71, 100);
        DrawableHelper.fill(matrices, this.api.getInventoryX()+2, this.api.getY()+2, this.api.getX()+this.api.getGuiObj().width-2, this.api.getY() + this.api.getGuiObj().height-2, color.hashCode());
    }
}
