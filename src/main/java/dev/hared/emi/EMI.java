package dev.hared.emi;

import dev.hared.emi.api.EMIGuiAPI;
import dev.hared.emi.api.IKeyboardInput;
import dev.hared.emi.api.IMouseInput;
import dev.hared.emi.gui.EMIAbstractGui;
import net.fabricmc.api.ClientModInitializer;

import java.util.HashMap;

public class EMI implements ClientModInitializer {

    private HashMap<String, EMIAbstractGui> guiRegistry = new HashMap<String, EMIAbstractGui>();
    private EMIAbstractGui currentGUI;

    private static EMI modInstance;

    @Override
    public void onInitializeClient(){
        modInstance = this;
    }

    public static EMI getInstance(){
        return modInstance;
    }

    public <T extends EMIAbstractGui> boolean addEMIGUI(T emiGui){
        if(!this.guiRegistry.containsKey(emiGui.getClass().getName())){
            this.guiRegistry.put(emiGui.getClass().getName(), emiGui);
            EMIGuiAPI.addKeyboardListener((keyCode, scanCode, modifiers) -> emiGui.keyboardInput(keyCode, scanCode, modifiers));
            EMIGuiAPI.addMouseListener(((mouseX, mouseY, button) -> emiGui.mouseInput(mouseX, mouseY, button)));
            return true;
        }
        return false;
    }

    public void onGUITick(EMIGuiAPI api) {
        if(this.currentGUI != null)
            this.currentGUI.api = api;
    }

}
