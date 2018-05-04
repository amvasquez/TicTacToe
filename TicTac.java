//Andrea Vasquez; HW3; amv445

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class TicTac{

    public static void main(String[] args){
        
        JFrame jf = new JFrame("Tic Tac Toe!!");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(500,500);
        jf.setResizable(true);

        JPanel jp = new JPanel();
        jp.setBackground(Color.black);
        jp.setLayout(new GridLayout(3,5,5,3));

        JButton btn = null;
        for (int i = 0; i < 9 ; i++){
            btn = new JButton(""+i);
            btn.addActionListener(new buttonAction());
            jp.add(btn);
        }

        jf.add(jp);
        jf.setVisible(true);
    }
}

class Player {  // parent player class 
    public char getPlayer(JButton btn){ //get what's inside the button
        return btn.getText().charAt(0);
    }
}

class X extends Player {
    public void setPlayer(JButton btn){
        btn.setText("X");
    }
}

class O extends Player {
    public void setPlayer(JButton btn){
        btn.setText("O");
    }
}

class buttonAction implements ActionListener{
    
    public int getCurrPlayer(JButton btn){
        int currPlayer = new Player(){ 
            public char getPlayer(JButton btn){
                return btn.getText().charAt(0);
            }
        }.getPlayer(btn); //gets the char and turn it into the ASCI value
        return currPlayer;
    }

    public int getTurn(Component[] cmp){
        int turn = 9; //spaces 
        int currPlayer;
        for (int i = 0; i < cmp.length; i++){
            currPlayer = getCurrPlayer((JButton)cmp[i]);
            if (currPlayer < 57){
                turn -=1; //decrements filled spaced
            }
        }
        return turn;
    }

    public int checkForWin(Component[] cmp){
        int [] boardAVals = new int[9];
        int win = 0;
        for (int i = 0; i < cmp.length; i++){
            boardAVals[i] = getCurrPlayer((JButton)cmp[i]);
        }

        if((boardAVals[0] == boardAVals[1]) && (boardAVals[1] == boardAVals[2])){ win = 1;} 
        else if ((boardAVals[3] == boardAVals[4]) && (boardAVals[4] == boardAVals[5])){ win = 1;}
        else if ((boardAVals[6] == boardAVals[7]) && (boardAVals[7] == boardAVals[8])){ win = 1;}
        else if ((boardAVals[0] == boardAVals[3]) && (boardAVals[3] == boardAVals[6])){ win = 1;}
        else if ((boardAVals[1] == boardAVals[4]) && (boardAVals[4] == boardAVals[7])){ win = 1;}
        else if ((boardAVals[2] == boardAVals[5]) && (boardAVals[5] == boardAVals[8])){ win = 1;}
        else if ((boardAVals[0] == boardAVals[4]) && (boardAVals[4] == boardAVals[8])){ win = 1;}
        else if ((boardAVals[2] == boardAVals[4]) && (boardAVals[4] == boardAVals[6])){ win = 1;}
        else{
            win = 0;
        }

        return win;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){

        JButton btn = (JButton) e.getSource();
        Component[] components = btn.getParent().getComponents();

        int turns = getTurn(components);
        int win = checkForWin(components);

        if ((turns != 9) && (win != 1)){
            int currPlayer = getCurrPlayer(btn);

            if (currPlayer < 57){ //checks the ASCII value to see if there's a letter inside
                if (turns % 2 == 0){ //plays x
                    new X(){}.setPlayer(btn);
                }
                else { //plays O
                    new O(){}.setPlayer(btn);
                }
            }
        }
        win =checkForWin(components);
        if (win == 1){System.out.println("YOU WIN!");}
        else if (turns >= 8){
            System.out.println("Its a tie!");
        }
    }
}