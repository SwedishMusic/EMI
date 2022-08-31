package dev.hared.emi;

import dev.hared.emi.api.EMIGuiAPI;
import dev.hared.emi.api.IKeyboardInput;
import dev.hared.emi.api.IMouseInput;
import dev.hared.emi.gui.EMIAbstractGui;
import dev.hared.emi.gui.EMIBasicGui;
import net.fabricmc.api.ClientModInitializer;

import java.util.HashMap;

public class EMI implements ClientModInitializer {

    private HashMap<String, EMIAbstractGui> guiRegistry = new HashMap<String, EMIAbstractGui>();
    private EMIAbstractGui currentGUI;

    private static EMI modInstance;

    @Override
    public void onInitializeClient(){
        modInstance = this;
        this.addEMIGUI(new EMIBasicGui());
        this.currentGUI = this.guiRegistry.get(EMIBasicGui.class.getName());
    }

    public static EMI getInstance(){
        return modInstance;
    }

    public <T extends EMIAbstractGui> boolean addEMIGUI(T emiGui){
        if(!this.guiRegistry.containsKey(emiGui.getClass().getName())){
            this.guiRegistry.put(emiGui.getClass().getName(), emiGui);
            EMIGuiAPI.addIEMIRenderer(((matrices, mouseX, mouseY, delta) -> emiGui.render(matrices, mouseX, mouseY, delta)));
            EMIGuiAPI.addKeyboardListener((keyCode, scanCode, modifiers) -> emiGui.keyboardInput(keyCode, scanCode, modifiers));
            EMIGuiAPI.addMouseListener(((mouseX, mouseY, button) -> emiGui.mouseInput(mouseX, mouseY, button)));
            return true;
        }
        return false;
    }

    public boolean setGUI(String clazz){
        if(this.guiRegistry.containsKey(clazz) && !this.currentGUI.getClass().getName().equals(clazz)){
            this.currentGUI = this.guiRegistry.get(clazz);
            this.currentGUI.initGUI();
            return true;
        }
        return false;
    }

    public void onGUITick(EMIGuiAPI api) {
        this.currentGUI.api = api;
    }

}
