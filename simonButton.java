package Simon_Game;

import javax.swing.*;
import java.awt.*;

public class simonButton extends JButton {
    private int index;

    public simonButton(int index){
        this.index = index;
        setOpaque(true);
        setBorderPainted(false);
        setBackground(getButtonColor());
    }
    public int getIndex(){
        return index;

    }
    private Color getButtonColor(){
        switch (index){
            case 0 : return Color.RED;
            case 1 : return Color.BLUE;
            case 2 : return Color.GREEN;
            case 3 : return Color.YELLOW;
            default: return Color.BLACK;
        }
    }

}
