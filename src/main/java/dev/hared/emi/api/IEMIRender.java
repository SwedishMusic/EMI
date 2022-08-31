package dev.hared.emi.api;

import net.minecraft.client.util.math.MatrixStack;

public interface IEMIRender {

    void render(MatrixStack matrices, int mouseX, int mouseY, float delta);

}
