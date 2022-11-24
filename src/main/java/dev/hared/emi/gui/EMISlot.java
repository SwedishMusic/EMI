package dev.hared.emi.gui;

import dev.hared.emi.api.EMIStack;

public class EMISlot {

    private EMIStack stack;

    private int x;

    private int y;

    private boolean hovered = false;

    public EMISlot(EMIStack stack, int x, int y){
        this.stack = stack;
        this.x = x;
        this.y = y;
    }

    public EMISlot(){
        this(null, 0, 0);
    }

    public void updateSlot(int mouseX, int mouseY){
        this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + 16 && mouseY < this.y + 16;
    }

    public boolean isHovered() {
        return hovered;
    }

    public EMIStack getStack() {
        return stack;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setStack(EMIStack stack) {
        this.stack = stack;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
