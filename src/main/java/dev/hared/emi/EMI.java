package dev.hared.emi;

import dev.hared.emi.api.EMIGuiAPI;
import dev.hared.emi.api.EMIMatrix;
import dev.hared.emi.api.IEMIListener;
import dev.hared.emi.gui.EMIAbstractGui;
import dev.hared.emi.gui.EMIBasicGui;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.util.HashMap;

public class EMI implements ClientModInitializer, IEMIListener {

    private HashMap<String, EMIAbstractGui> guiRegistry = new HashMap<String, EMIAbstractGui>();
    private EMIAbstractGui currentGUI;

    private static EMI modInstance;

    @Override
    public void onInitializeClient(){
        EMIGuiAPI.addListener(this);
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
            return true;
        }
        return false;
    }

    private <T extends EMIAbstractGui> void handleRenderer(EMIMatrix matrices, int mouseX, int mouseY, float delta, T emiGui) {
        if(this.currentGUI.api != null)
            emiGui.render(matrices, mouseX, mouseY, delta);
    }

    public boolean setGUI(String clazz){
        if(this.guiRegistry.containsKey(clazz) && !this.currentGUI.getClass().getName().equals(clazz)){
            this.currentGUI = this.guiRegistry.get(clazz);
            this.currentGUI.initGUI();
            return true;
        }
        return false;
    }

    @Override
    public void render(EMIMatrix matrices, int mouseX, int mouseY, float delta) {
            this.currentGUI.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        this.currentGUI.keyboardInput(keyCode, scanCode, modifiers);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        this.currentGUI.mouseInput(mouseX, mouseY, button);
    }

    @Override
    public void getAPI(EMIGuiAPI api) {
        this.currentGUI.api = api;
    }
}
