package dev.hared.emi;

import dev.hared.emi.api.EMIGuiAPI;
import dev.hared.emi.gui.EMIAbstractGui;
import net.fabricmc.api.ClientModInitializer;

import java.util.HashMap;

public class EMI implements ClientModInitializer {

    private HashMap<String, EMIAbstractGui> guiRegistry = new HashMap<String, EMIAbstractGui>();

    private static EMI modInstance;

    @Override
    public void onInitializeClient(){
        modInstance = this;
    }

    public static EMI getInstance(){
        return modInstance;
    }

    public <T extends EMIAbstractGui> boolean addEMIGUI(Class<T> clazz){
        return false;
    }

    public void onGUITick(EMIGuiAPI api) {

    }

}
