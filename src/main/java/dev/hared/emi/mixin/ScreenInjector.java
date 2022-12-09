package dev.hared.emi.mixin;

import com.google.common.collect.Lists;
import dev.hared.emi.api.EMIGuiAPI;
import dev.hared.emi.api.EMIGuiComponent;
import dev.hared.emi.api.EMITextBox;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Screen.class)
public abstract class ScreenInjector implements EMIGuiAPI {

    @Shadow @Final
    private List<Element> children;

    @Shadow @Final
    private List<Selectable> selectables;

    @Invoker("renderTooltipFromComponents")
    public abstract void getScreenRenderTooltip(MatrixStack matrices, List<TooltipComponent> components, int x, int y);

    @Invoker("renderTooltip")
    public abstract void getScreenRenderTooltipStack(MatrixStack matrices, ItemStack stack, int x, int y);

    public void addEMIElement(EMIGuiComponent component){
        ClickableWidget cw = (ClickableWidget)(Object)component;
        this.children.add(cw);
        this.selectables.add(cw);
    }

    @Inject(method = "render", at = @At("RETURN"))
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo info){
        if(selectables.size() < 2)
            return;

        for(Selectable sel : selectables) {
            TextFieldWidget tw = null;
            for (Selectable selObj : selectables) {
                if(tw != null)
                    break;
                if (selObj instanceof TextFieldWidget && !(selObj instanceof EMITextBox) && ((TextFieldWidget) selObj).isFocused()) {
                  tw = (TextFieldWidget)selObj;
                }
            }
            if(tw == null){
                break;
            } else if(!tw.equals(sel) && sel instanceof TextFieldWidget){
                ((TextFieldWidget)sel).setTextFieldFocused(false);
            }
        }
    }

    @Override
    public void updateFocusedTextBox(EMITextBox textBox) {
        for(Selectable sel : selectables){
            if(sel instanceof TextFieldWidget){
                TextFieldWidget tw = (TextFieldWidget)sel;
                if(tw.equals(textBox))
                    tw.setTextFieldFocused(true);
                else
                    tw.setTextFieldFocused(false);
            }
        }
    }
}
