package dev.hared.emi.api;

import java.util.ArrayList;
import java.util.List;

public interface EMIGuiAPI {

    static ArrayList<IEMIListener> listener = new ArrayList<IEMIListener>();

    //public <T extends HandledScreen> T getGuiObj();

    public int getX();

    public int getY();

    public int getWidth();

    public int getHeight();

    public int getInventoryX();

    public int getInventoryY();

    public void drawSquare(EMIMatrix matrices, int x1, int y1, int x2, int y2, int color);

    public void getRenderTooltip(EMIMatrix matrices, EMIStack stack, int x, int y);

    public void getRenderTooltipString(EMIMatrix matrices, List<String> tip, int x, int y);

    public void getDrawItem(EMIStack stack, int x, int y, String amountText);

    public static void addListener(IEMIListener emilistener){
        listener.add(emilistener);
    }

    public  void drawSlotHighlight(EMIMatrix matrices, int x, int y);

    public EMIStack[] getAllItems();

}
