package dev.hared.emi.gui;

import dev.hared.emi.api.EMIGuiAPI;
import dev.hared.emi.api.EMIGuiComponent;
import dev.hared.emi.api.EMIMatrix;
import dev.hared.emi.api.EMIStack;

public class EMISlot implements EMIGuiComponent {

    private EMIStack stack;

    private int x;

    private int y;

    private boolean hovered = false;

    private boolean set = false;

    public EMISlot(EMIStack stack, int x, int y){
        this.stack = stack;
        this.x = x;
        this.y = y;
    }

    public EMISlot(){
        this(null, 0, 0);
    }

    public void draw(EMIMatrix matrix, int mouseX, int mouseY, float delta){
        int i = this.x;
        int j = this.y;
        this.hovered = (mouseX >= i) && (mouseY >= j) && (mouseX < i + 16) && (mouseY < j + 16);
    }

    public void isSet(boolean set) {
       this.set = set;
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

    @Override
    public void onKey(int keyCode, int scanCode, int modifiers) {
        //Unused
    }

    @Override
    public void onMouse(double mouseX, double mouseY, int button) {
        //Unused
    }
}
