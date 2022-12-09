package dev.hared.emi;

import dev.hared.emi.api.EMIGuiAPI;
import dev.hared.emi.api.EMIMatrix;
import dev.hared.emi.api.EMIStack;
import dev.hared.emi.api.IEMIListener;
import dev.hared.emi.gui.EMIAbstractGui;
import dev.hared.emi.gui.EMIBasicGui;
import dev.hared.emi.gui.EMIItemsGUI;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

public class EMI implements ClientModInitializer, IEMIListener {

    private HashMap<String, EMIAbstractGui> guiRegistry = new HashMap<String, EMIAbstractGui>();
    private EMIAbstractGui currentGUI;

    private EMIStack[] emiItems;

    private static EMI modInstance;

    @Override
    public void onInitializeClient(){
        EMIGuiAPI.addListener(this);
        modInstance = this;
        this.addEMIGUI(new EMIItemsGUI());
        this.currentGUI = this.guiRegistry.get(EMIItemsGUI.class.getName());
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

    public boolean setGUI(String clazz){
        if(this.guiRegistry.containsKey(clazz) && !this.currentGUI.getClass().getName().equals(clazz)){
            this.currentGUI = this.guiRegistry.get(clazz);
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
    public boolean charInput(char chr, int modifiers) {
        return this.currentGUI.charInput(chr,  modifiers);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        this.currentGUI.mouseInput(mouseX, mouseY, button);
    }

    @Override
    public void getAPI(EMIGuiAPI api) {
        if(this.currentGUI.getApi() == null || this.currentGUI.getApi() != api){
            this.currentGUI.initGUI(api);
        }
    }

    public EMIStack[] EMIItemList() {
        if(emiItems == null){
            emiItems = new EMIStack[Registry.ITEM.size()];
            for(int i = 0; i < Registry.ITEM.size(); i++){
                emiItems[i] = (EMIStack)(Object)new ItemStack(Registry.ITEM.get(i));
            }
        }
        return emiItems;
    }

    public EMIStack getItem(String id){
        return (EMIStack)(Object)new ItemStack(Registry.ITEM.get(new Identifier(id)));
    }

}
