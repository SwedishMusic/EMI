package dev.hared.emi.gui;

import dev.hared.emi.api.EMIGuiAPI;
import dev.hared.emi.api.EMIGuiComponent;
import dev.hared.emi.api.EMIMatrix;

import java.util.ArrayList;

public abstract class EMIAbstractGui {

    public EMIGuiAPI api;
    protected ArrayList<EMIGuiComponent> components = new ArrayList<EMIGuiComponent>();
    protected int bottomY;
    protected int topY;
    protected int rightX;
    protected int leftX;

    public abstract void initGUI();

    public void keyboardInput(int keyCode, int scanCode, int modifiers){
        components.forEach(component -> component.onKey(keyCode, scanCode, modifiers));
    }

    public void mouseInput(double mouseX, double mouseY, int button){
        components.forEach(component -> component.onMouse(mouseX, mouseY, button));
    }

    public void render(EMIMatrix matrices, int mouseX, int mouseY, float delta){
        leftX = this.api.getInventoryX()+2;
        rightX = this.api.getX() + this.api.getWidth()-2;
        topY = this.api.getY() + 2;
        bottomY = this.api.getY() + this.api.getHeight()-2;
    }

    public void drawComponents(EMIMatrix matrices, int mouseX, int mouseY, float delta){
        components.forEach(component -> component.draw(matrices, mouseX, mouseY, delta));
    }

    public void drawEMISlot(EMIMatrix matrices, int mouseX, int mouseY, EMISlot slot){
        slot.updateSlot(mouseX, mouseY, api);

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
