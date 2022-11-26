package dev.hared.emi.mixin;

import dev.hared.emi.api.EMIMatrix;
import dev.hared.emi.api.EMITextBox;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TextFieldWidget.class)
public abstract class TextBoxMixin implements EMITextBox {


    @Override @Invoker("getText")
    public abstract String getText();

    @Override @Invoker("setText")
    public abstract void setText(String text);

    @Override
    public void onKey(int keyCode, int scanCode, int modifiers){
        this.getObj().keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void onMouse(double mouseX, double mouseY, int button){
        this.getObj().mouseClicked(mouseX, mouseY, button);
    }

    public TextFieldWidget getObj(){
        return (TextFieldWidget)(Object)this;
    }

    public MatrixStack getMatrix(EMIMatrix matrix){
        return (MatrixStack)(Object)matrix;
    }

    @Override
    public void drawBox(EMIMatrix matrices, int mouseX, int mouseY, float delta) {
        this.getObj().render(this.getMatrix(matrices), mouseX, mouseY, delta);
    }

    @Override
    public void setX(int x) {
        this.getObj().x = x;
    }

    @Override
    public void setY(int y) {
        this.getObj().y = y;
    }

    @Override
    public void setWidth(int width) {
        this.getObj().setWidth(width);
    }

}
