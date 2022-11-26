package dev.hared.emi.gui;

import dev.hared.emi.api.EMIMatrix;
import dev.hared.emi.api.EMIStack;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EMIBasicGui extends EMIAbstractGui{
    protected final int base = 16;

    private final EMISlot grass = new EMISlot();
    private final EMISlot star = new EMISlot();
    private final EMISlot table = new EMISlot();
    private final EMISlot command = new EMISlot();
    private final EMISlot save = new EMISlot();

    public EMIBasicGui(){
        EMIStack grassBlock = api.getItem("grass_block");
        grassBlock.setToolTip(this.createSimpleTip("Items"));
        grass.setStack(grassBlock);
        grass.isSet(true);

        EMIStack starItem = api.getItem("nether_star");
        starItem.setToolTip(this.createSimpleTip("Favorites"));
        star.setStack(starItem);

        EMIStack tableBlock = api.getItem("crafting_table");
        tableBlock.setToolTip(this.createSimpleTip("Customize Items"));
        table.setStack(tableBlock);

        EMIStack comBlock = api.getItem("command_block");
        comBlock.setToolTip(this.createSimpleTip("Commands"));
        command.setStack(comBlock);

        EMIStack bookItem = api.getItem("writable_book");
        bookItem.setToolTip(this.createSimpleTip("Saved inventories"));
        save.setStack(bookItem);
    }

    private List<String> createSimpleTip(String tip){
        List<String> tipList = new ArrayList<String>();
        tipList.add(tip);
        return tipList;
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
        super.render(matrices, mouseX, mouseY, delta);
        Color color = new Color(57, 63, 71, 100);
        this.api.drawSquare(matrices, this.leftX, this.topY, this.rightX, this.bottomY, color.hashCode());
        color = new Color(57, 63, 71, 120);
        int barLeft = this.leftX + 2;
        int barRight = this.rightX - 2;
        this.api.drawSquare(matrices, barLeft, this.topY+1, barRight, this.topY+17, color.hashCode());
        this.updateEMINavBar(barLeft, barRight);
        this.drawEMINavBar(matrices, mouseX, mouseY);
    }

    private void drawEMINavBar(EMIMatrix matrices, int mouseX, int mouseY){
        this.drawEMISlot(matrices, mouseX, mouseY, grass);
        this.drawEMISlot(matrices, mouseX, mouseY, star);
        this.drawEMISlot(matrices, mouseX, mouseY, table);
        this.drawEMISlot(matrices, mouseX, mouseY, command);
        this.drawEMISlot(matrices, mouseX, mouseY, save);
    }

    private void updateEMINavBar(int xL, int xR){
        int i = xR-xL;
        i-= 4 + (base*5);
        i /= 4;

        int x = xL+2;
        grass.setX(x);
        grass.setY(this.topY+1);

        x += i + base;
        star.setX(x);
        star.setY(this.topY+1);

        x += i + base;
        table.setX(x);
        table.setY(this.topY+1);

        x += i + base;
        command.setX(x);
        command.setY(this.topY+1);

        x += i + base;
        save.setX(x);
        save.setY(this.topY+1);
    }
}
