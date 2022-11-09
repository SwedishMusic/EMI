package dev.hared.emi.gui;

import dev.hared.emi.api.EMIMatrix;
import dev.hared.emi.api.EMIStack;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.awt.*;
import java.util.ArrayList;

public class EMIBasicGui extends EMIAbstractGui{

    private int bottomY;
    private int topY;
    private int rightX;
    private int leftX;

    protected final int base = 16;

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
    public void render(EMIMatrix matrices, int mouseX, int mouseY, float delta) {
        leftX = this.api.getInventoryX()+2;
        rightX = this.api.getX() + this.api.getWidth()-2;
        topY = this.api.getY() + 2;
        bottomY = this.api.getY() + this.api.getHeight()-2;
        Color color = new Color(57, 63, 71, 100);
        this.api.drawSquare(matrices, this.leftX, this.topY, this.rightX, this.bottomY, color.hashCode());
        color = new Color(57, 63, 71, 120);
        this.api.drawSquare(matrices, this.leftX+2, this.topY+2, this.rightX-2, this.topY+17, color.hashCode());

        //Chest icon
        ItemStack chest = new ItemStack(Blocks.CHEST);
        this.api.getDrawItem((EMIStack)(Object)chest, this.leftX + 2, this.topY+1, "");
        ArrayList<String> s = new ArrayList<String>();
        s.add("Test");
        s.add("Tip");
        this.api.getRenderTooltipString(matrices, s,this.leftX + 2, this.topY+1);
        topY = this.api.getY()+18;
    }
}
