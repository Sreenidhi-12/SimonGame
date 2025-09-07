package Simon_Game;

import javax.sound.midi.Sequence;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimonGame extends JFrame implements ActionListener {
    private List<Integer> sequence;
    private List<JButton> button;
    private int CurrentStep;
    private boolean playerturn;
    private Timer timer;
    private int currentFlash;

    public SimonGame(){
        setTitle("Simon game by Sreenidhi");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
//        setLayout(new BorderLayout(2,2));

        sequence = new ArrayList<>();
        button = new ArrayList<>();
        CurrentStep = 0;
        playerturn = false;

        for(int i =0;i < 4;i++){
            JButton buttons = new simonButton(i);
            buttons.addActionListener(this);
            button.add(buttons);
            buttonPanel.add(buttons);

        }
        add(buttonPanel, BorderLayout.CENTER);
        newRound();
        setVisible(true);
        

    }

    private void newRound() {
        Random random = new Random();
        sequence.add(random.nextInt(4));
        CurrentStep = 0;
        playerturn = false;
        playSequence();

    }

    private void playSequence() {
        currentFlash = 0;
        timer = new Timer(700, null);  // 700ms interval between flashes
        timer.addActionListener(new ActionListener() {
//        timer = new Timer(700, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentFlash < sequence.size()){
                    button.get(sequence.get(currentFlash)).setBackground((Color.WHITE));
                    Timer pause = new Timer(500, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                           button.get(sequence.get(currentFlash)).setBackground(getButtonColor(sequence.get(currentFlash)));
                           currentFlash++;

                        }
                    });
                    pause.setRepeats(false);
                    pause.start();
                }
                else{
                    timer.stop();
                    playerturn = true;


                }
            }
        });
        currentFlash = 0;
        timer.setRepeats(true);
        timer.start();

    }
    private Color getButtonColor(int index){
        switch (index){
            case 0: return Color.RED;
            case 1: return Color.BLUE;
            case 2: return Color.GREEN;
            case 3: return Color.YELLOW;
            default: return Color.BLACK;

        }
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(!playerturn) return;

        simonButton buttons = (simonButton) e.getSource();
        if(buttons.getIndex()==sequence.get(CurrentStep)){
            CurrentStep++;
            if(CurrentStep == sequence.size()){
                playerturn = false;
                newRound();
            }
            else{
                JOptionPane.showMessageDialog(this,"Game Over! Your Score: "+(sequence.size()-1));
                sequence.clear();
                newRound();
            }
        }
    }

}
