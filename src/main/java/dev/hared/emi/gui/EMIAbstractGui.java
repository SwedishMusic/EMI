package dev.hared.emi.gui;

import dev.hared.emi.api.EMIGuiAPI;
import net.minecraft.client.util.math.MatrixStack;

public abstract class EMIAbstractGui {

    public EMIGuiAPI api;

    public abstract void initGUI();

    public abstract void keyboardInput(int keyCode, int scanCode, int modifiers);

    public abstract void mouseInput(double mouseX, double mouseY, int button);

    public abstract void render(MatrixStack matrices, int mouseX, int mouseY, float delta);

}
