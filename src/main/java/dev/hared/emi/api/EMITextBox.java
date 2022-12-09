package dev.hared.emi.api;

public interface EMITextBox extends EMIGuiComponent{

    public String getTextContent();

    public void setTextContent(String text);

    public void setX(int x);

    public void setY(int y);

    public boolean isInUse();

    public void setBoxWidth(int width);

    public void writeChar(char chr, int modifiers);

}
