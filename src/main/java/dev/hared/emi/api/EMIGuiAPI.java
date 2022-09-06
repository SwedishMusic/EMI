package dev.hared.emi.api;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.ArrayList;

public interface EMIGuiAPI {

    static ArrayList<IEMIListener> listener = new ArrayList<IEMIListener>();

    public <T extends HandledScreen> T getGuiObj();

    public int getX();

    public int getY();

    public int getInventoryX();

    public int getInventoryY();

    public void getRenderTooltip(MatrixStack matrices, ItemStack stack, int x, int y);

    public void getDrawItem(ItemStack stack, int x, int y, String amountText);

    public void onRender(MatrixStack matrices, int mouseX, int mouseY, float delta);

    public static void addListener(IEMIListener emilistener){
        listener.add(emilistener);
    }

    default public void drawSlotHighlight(MatrixStack matrices, int x, int y){
        HandledScreen.drawSlotHighlight(matrices, x, y, this.getGuiObj().getZOffset());
    }

}
