package dev.hared.emi.api;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public interface EMIGuiAPI {

    static ArrayList<IKeyboardInput> ki = new ArrayList<IKeyboardInput>();
    static ArrayList<IMouseInput> mi = new ArrayList<IMouseInput>();

    public <T extends HandledScreen> T getGuiObj();

    public int getX();

    public int getY();

    public int getTitleX();

    public int getTitleY();

    public void getRenderTooltip(MatrixStack matrices, ItemStack stack, int x, int y);

    public void getDrawItem(ItemStack stack, int x, int y, String amountText);

    public void onRender(MatrixStack matrices, int mouseX, int mouseY, float delta);

    public static void addMouseListener(IMouseInput mouseInput){
        mi.add(mouseInput);
    }

    public static void addKeyboardListener(IKeyboardInput keyboardInput){
        ki.add(keyboardInput);
    }

    default public void drawSlotHighlight(MatrixStack matrices, int x, int y, int z){
        HandledScreen.drawSlotHighlight(matrices, x, y, z);
    }

}
