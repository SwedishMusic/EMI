package dev.hared.emi.gui;

import dev.hared.emi.EMI;
import dev.hared.emi.api.EMIMatrix;
import dev.hared.emi.api.EMIStack;

import java.awt.*;

public class EMIBasicGui extends EMIAbstractGui{

    private int bottomY;
    private int topY;
    private int rightX;
    private int leftX;

    protected final int base = 16;

    private final EMIStack grass;
    private final EMIStack star;
    private final EMIStack table;
    private final EMIStack command;
    private final EMIStack save;

    public EMIBasicGui(){
        grass = EMI.getInstance().getItem("grass_block");
        star = EMI.getInstance().getItem("nether_star");
        table = EMI.getInstance().getItem("crafting_table");
        command = EMI.getInstance().getItem("command_block");
        save = EMI.getInstance().getItem("writable_book");
    }

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
        int barLeft = this.leftX + 2;
        int barRight = this.rightX - 2;
        this.api.drawSquare(matrices, barLeft, this.topY+1, barRight, this.topY+17, color.hashCode());

        this.drawEMINavBar(mouseX, mouseY, barLeft, barRight);

    }

    private void drawEMINavBar(int mouseX, int mouseY, int xL, int xR){
        int i = xR-xL;
        System.out.println(i);
        i-= 4 + (base*5);
        i /= 4;
        int x = xL+2;
        this.api.getDrawItem(grass, x, this.topY+1, "");
        x += i + base;
        this.api.getDrawItem(star, x, this.topY+1, "");
        x += i + base;
        this.api.getDrawItem(table, x, this.topY+1, "");
        x += i + base;
        this.api.getDrawItem(command,x, this.topY+1, "");
        x += i + base;
        this.api.getDrawItem(save, x, this.topY+1, "");
    }
}
