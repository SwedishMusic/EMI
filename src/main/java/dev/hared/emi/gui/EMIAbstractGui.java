package dev.hared.emi.gui;

import dev.hared.emi.api.EMIGuiAPI;
import dev.hared.emi.api.EMIMatrix;
import net.minecraft.client.util.math.MatrixStack;

public abstract class EMIAbstractGui {

    public EMIGuiAPI api;

    public abstract void initGUI();

    public abstract void keyboardInput(int keyCode, int scanCode, int modifiers);

    public abstract void mouseInput(double mouseX, double mouseY, int button);

    public abstract void render(EMIMatrix matrices, int mouseX, int mouseY, float delta);

}
