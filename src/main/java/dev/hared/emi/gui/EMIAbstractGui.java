package dev.hared.emi.gui;

import dev.hared.emi.api.EMIGuiAPI;
import dev.hared.emi.api.EMIMatrix;

public abstract class EMIAbstractGui {

    public EMIGuiAPI api;

    public abstract void initGUI();

    public abstract void keyboardInput(int keyCode, int scanCode, int modifiers);

    public abstract void mouseInput(double mouseX, double mouseY, int button);

    public abstract void render(EMIMatrix matrices, int mouseX, int mouseY, float delta);

    public void drawEMIStack(EMIMatrix matrices, int mouseX, int mouseY, EMISlot slot){
        slot.updateSlot(mouseX, mouseY);

        api.getDrawItem(slot.getStack(), slot.getX(), slot.getY(), "");

        if(slot.isHovered()) {
            api.drawSlotHighlight(matrices, slot.getX(), slot.getY());
            if(slot.getStack().getToolTip() == null)
                api.getRenderTooltip(matrices, slot.getStack(), slot.getX(), slot.getY());
            else
                api.getRenderTooltipString(matrices, slot.getStack().getToolTip(), slot.getX(), slot.getY());
        }

    }

}
