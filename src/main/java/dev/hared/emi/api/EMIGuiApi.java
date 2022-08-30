package dev.hared.emi.api;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;

public interface EMIGuiApi {

    public <T extends HandledScreen> T getGuiObj();

    public int getX();

    public int getY();

    public int getTitleX();

    public int getTitleY();

    public void getRenderTooltip(MatrixStack matrices, ItemStack stack, int x, int y);

    public void getDrawItem(ItemStack stack, int x, int y, String amountText);

    public boolean keyPressed(int keyCode, int scanCode, int modifiers);

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta);

    public boolean mouseClicked(double mouseX, double mouseY, int button);

    default public void drawSlotHighlight(MatrixStack matrices, int x, int y, int z){
        HandledScreen.drawSlotHighlight(matrices, x, y, z);
    }

}
