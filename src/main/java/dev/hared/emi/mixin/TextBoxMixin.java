package dev.hared.emi.mixin;

import dev.hared.emi.api.EMIMatrix;
import dev.hared.emi.api.EMITextBox;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TextFieldWidget.class)
public abstract class TextBoxMixin implements EMITextBox {

    @Override @Invoker("getText")
    public abstract String getTextContent();

    @Override @Invoker("setText")
    public abstract void setTextContent(String text);

    @Override
    public void onKey(int keyCode, int scanCode, int modifiers){
        this.getObj().keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void onMouse(double mouseX, double mouseY, int button){
        this.getObj().mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean isInUse() {
        return this.getObj().isActive();
    }

    public TextFieldWidget getObj(){
        return (TextFieldWidget)(Object)this;
    }

    public MatrixStack getMatrix(EMIMatrix matrix){
        return (MatrixStack)(Object)matrix;
    }

    @Override
    public void draw(EMIMatrix matrices, int mouseX, int mouseY, float delta) {
        this.getObj().render(this.getMatrix(matrices), mouseX, mouseY, delta);
    }

    @Override
    public void writeChar(char chr, int modifiers) {
        this.getObj().charTyped(chr,  modifiers);
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
    public void setBoxWidth(int width) {
        this.getObj().setWidth(width);
    }

}
