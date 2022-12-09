package dev.hared.emi.gui;

import dev.hared.emi.api.EMIGuiAPI;
import dev.hared.emi.api.EMIGuiComponent;
import dev.hared.emi.api.EMIMatrix;
import dev.hared.emi.api.EMITextBox;

import java.util.ArrayList;

public abstract class EMIAbstractGui {

    protected EMIGuiAPI api;
    protected ArrayList<EMIGuiComponent> components = new ArrayList<EMIGuiComponent>();
    protected int bottomY;
    protected int topY;
    protected int rightX;
    protected int leftX;

    public void initGUI(EMIGuiAPI api){
        this.api = api;
        this.components.clear();
    }

    public EMIGuiAPI getApi(){
        return api;
    }

    public boolean charInput(char chr, int modifiers){
        for(EMIGuiComponent comp : components){
            if(comp instanceof EMITextBox && ((EMITextBox)comp).isInUse()){
                ((EMITextBox)comp).writeChar(chr, modifiers);
                return true;
            }
        }
        return false;
    }

    public void keyboardInput(int keyCode, int scanCode, int modifiers){
        components.forEach(component -> component.onKey(keyCode, scanCode, modifiers));
    }

    public void mouseInput(double mouseX, double mouseY, int button){
        components.forEach(component -> component.onMouse(mouseX, mouseY, button));
    }

    public void render(EMIMatrix matrices, int mouseX, int mouseY, float delta){
        leftX = this.api.getInventoryX() + 2;
        rightX = this.api.getX() + this.api.getWidth() - 2;
        topY = this.api.getY() + 2;
        bottomY = this.api.getY() + this.api.getHeight() - 2;
    }

    public void drawComponents(EMIMatrix matrices, int mouseX, int mouseY, float delta){
        components.forEach(component -> {
            component.draw(matrices, mouseX, mouseY, delta);
            if(component instanceof EMISlot)
                drawEMISlot(matrices, mouseX, mouseY, (EMISlot) component);
        });
    }

    private void drawEMISlot(EMIMatrix matrices, int mouseX, int mouseY, EMISlot slot){;

        api.getDrawItem(slot.getStack(), slot.getX(), slot.getY(), "");
        if(slot.isHovered()) {
            api.drawSlotHighlight(matrices, slot.getX(), slot.getY());
            int y = (slot.getY() - api.getY()) < 16 ? mouseY + 24 : mouseY;
            if(slot.getStack().getToolTip() == null){
                api.getRenderTooltip(matrices, slot.getStack(), mouseX + api.getX(), y);
            }
            else{
                int x = mouseX + slot.getStack().getWidth(api) > rightX ? mouseX - slot.getStack().getWidth(api) - 16 : mouseX;
                api.getRenderTooltipString(matrices, slot.getStack().getToolTip(), x, y);
            }
        }

    }

}
