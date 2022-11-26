package dev.hared.emi.mixin;

import dev.hared.emi.api.EMIGuiAPI;
import dev.hared.emi.api.EMIStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(ItemStack.class)
public class StackMixin implements EMIStack {

    private List<String> tip = null;
    @Override
    public void setToolTip(List<String> tip) {
        this.tip = tip;
    }

    @Override
    public List<String> getToolTip() {
        return tip;
    }

    @Override
    public int getWidth(EMIGuiAPI api) {
        int i = 0;
        if(tip != null){
            for(String text : tip)
                i = api.getTextWidth(text) > i ? api.getTextWidth(text) : i;
        }
        return i;
    }
}
