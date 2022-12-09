package dev.hared.emi.gui;

import dev.hared.emi.api.EMIGuiAPI;
import dev.hared.emi.api.EMIMatrix;
import dev.hared.emi.api.EMIStack;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EMIBasicGui extends EMIAbstractGui{
    protected final int base = 16;

    protected int barLeft;

    protected int barRight;

    private final EMISlot grass = new EMISlot();
    private final EMISlot star = new EMISlot();
    private final EMISlot table = new EMISlot();
    private final EMISlot command = new EMISlot();
    private final EMISlot save = new EMISlot();
    protected int barBottomY;

    public EMIBasicGui(){

    }

    private List<String> createSimpleTip(String tip){
        List<String> tipList = new ArrayList<String>();
        tipList.add(tip);
        return tipList;
    }

    @Override
    public void initGUI(EMIGuiAPI api) {
        super.initGUI(api);
        System.out.println("On init");
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

        this.components.add(grass);
        this.components.add(star);
        this.components.add(table);
        this.components.add(command);
        this.components.add(save);
    }

    @Override
    public void keyboardInput(int keyCode, int scanCode, int modifiers) {
        System.out.println("On key");
        super.keyboardInput(keyCode, scanCode, modifiers);
    }

    @Override
    public void mouseInput(double mouseX, double mouseY, int button) {
        System.out.println("On mouse");
        super.mouseInput(mouseX, mouseY, button);
    }

    @Override
    public void render(EMIMatrix matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);
        Color color = new Color(57, 63, 71, 100);
        this.api.drawSquare(matrices, this.leftX, this.topY, this.rightX, this.bottomY, color.hashCode());
        color = new Color(57, 63, 71, 120);
        barLeft = this.leftX + 2;
        barRight = this.rightX - 2;
        this.barBottomY = this.topY+17;
        this.api.drawSquare(matrices, barLeft, this.topY+1, barRight, barBottomY, color.hashCode());
        this.updateEMINavBar(barLeft, barRight);
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

    protected HashMap<Integer, List<EMIStack>> getItemMap(int rightX, int leftX, int topY, int bottomY, List<EMIStack> items){

        int row = leftX-rightX;
        row -= 2;
        row /= 18;

        int column = topY-bottomY;
        column -= 2;
        column /= 18;

        int slotsPerSide = row*column;

        int totalSides = items.size()/slotsPerSide;
        totalSides = (totalSides*slotsPerSide) < items.size() ? totalSides+1 : totalSides;

        HashMap<Integer, List<EMIStack>> map = new HashMap<Integer, List<EMIStack>>();

        int index = 0;

        for(int i = 0; i < totalSides; i++){
            ArrayList<EMIStack> stacks = new ArrayList<EMIStack>();
            for(int j = 0; j < slotsPerSide; j++){
                stacks.add(items.get(index));
                index++;
            }
            map.put(i, stacks);
        }

        return map;
    }
}
