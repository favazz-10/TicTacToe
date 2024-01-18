import java.awt.*;
import java.util.Objects;
import javax.swing.*;



public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    boolean gameOver = false;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textLabel, BorderLayout.NORTH);

        boardPanel.setBackground(Color.darkGray);
        boardPanel.setLayout(new GridLayout(3, 3));
        frame.add(boardPanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(e -> {
                    if (gameOver) return;
                    JButton tile1 = (JButton) e.getSource();
                    if (Objects.equals(tile1.getText(), "")) {
                        tile1.setText(currentPlayer);
                        checkWinner();
                        if (!gameOver) {
                            currentPlayer = Objects.equals(currentPlayer, playerX) ? playerO : playerX;
                            textLabel.setText(currentPlayer + "'s turn");
                        }

                    }


                });

            }
        }

    }

void checkWinner(){

        //horizontal check
        for(int r=0;r<3;r++){
            if(Objects.equals(board[r][0].getText(), ""))continue;
            if (Objects.equals(board[r][0].getText(), board[r][1].getText()) &&
                    Objects.equals(board[r][1].getText(), board[r][2].getText())) {
                for(int i=0;i<3;i++){
                    setWinner(board[r][i]);
                }
                gameOver=true;
                return;
            }


        }

        //vertical check
    for(int c=0;c<3;c++){
        if(Objects.equals(board[0][c].getText(), ""))continue;
        if (Objects.equals(board[0][c].getText(), board[1][c].getText()) &&
                Objects.equals(board[1][c].getText(), board[2][c].getText())) {
            for(int i=0;i<3;i++){
                setWinner(board[i][c]);
            }
            gameOver=true;
            return;
        }

    }

    //diagonal check
    if(!Objects.equals(board[0][0].getText(), "") &&
            Objects.equals(board[0][0].getText(), board[1][1].getText()) &&
            Objects.equals(board[1][1].getText(), board[2][2].getText())){
        for (int i=0;i<3;i++){
            setWinner(board[i][i]);
        }
        gameOver=true;
        return;
    }


    //anti-diagonal check
    if(!Objects.equals(board[2][0].getText(), "") &&
            Objects.equals(board[2][0].getText(), board[1][1].getText()) &&
            Objects.equals(board[1][1].getText(), board[0][2].getText())){
        setWinner(board[2][0]);
        setWinner(board[1][1]);
        setWinner(board[0][2]);
        gameOver=true;

    }
}

void setWinner(JButton tile){
        tile.setBackground(Color.green);
        tile.setForeground(Color.black);
        frame.setResizable(false);
        textLabel.setFont(new Font("Arial",Font.BOLD,30));
        textLabel.setText("Hurray, player "+currentPlayer+" is the winner");



}
}
