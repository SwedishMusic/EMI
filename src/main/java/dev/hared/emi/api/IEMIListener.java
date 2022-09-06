package dev.hared.emi.api;

import net.minecraft.client.util.math.MatrixStack;

public interface IEMIListener {

    void render(MatrixStack matrices, int mouseX, int mouseY, float delta);

    void keyPressed(int keyCode, int scanCode, int modifiers);

    void mouseClicked(double mouseX, double mouseY, int button);

    void tick(EMIGuiAPI api);

}
