package App;

import Game.Game;
import Server.Server;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static Server.Server.games;

public class Window {


    private ButtonGroup enemies;
    private JCheckBox enemyRed;
    private JCheckBox enemyBlue;
    private JComboBox<Integer> gameId;
    private JButton addEnemy;
    private JButton addFruit;
    private JButton deleteFruit;
    private JComboBox<Integer> row;
    private JComboBox<Integer> column;
    private ButtonGroup fruits;
    private JCheckBox banana;
    private JCheckBox apple;
    public static JTextArea console;
    private JScrollPane scrollPane;


    /**
     * @author Sebastian Mora
     * @brief Class Constructor
     */
    public Window() throws IOException {


        JFrame frame = new JFrame("Console");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,650);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        frame.setContentPane(contentPane);

        enemies = new ButtonGroup();
        enemyRed = new JCheckBox("Red");
        enemyRed.setSelected(true);
        enemyRed.setBounds(10, 125, 75, 25);
        enemies.add(enemyRed);
        frame.getContentPane().add(enemyRed);

        enemyBlue = new JCheckBox("Blue");
        enemyBlue.setBounds(85, 125, 75, 25);
        enemies.add(enemyBlue);
        frame.getContentPane().add(enemyBlue);

        addEnemy = new JButton("Add enemy");
        addEnemy.setBounds(10, 150, 125, 25);
        addEnemy.addActionListener(new AddEnemyAction());
        frame.getContentPane().add(addEnemy);


        addFruit = new JButton("Add Fruit");
        addFruit.setBounds(150, 150, 125, 25);
        addFruit.addActionListener(new AddFruit());
        frame.getContentPane().add(addFruit);

        deleteFruit = new JButton("Delete Fruit");
        deleteFruit.addActionListener(new DeleteFruit());
        deleteFruit.setBounds(280, 150, 125, 25);
        frame.getContentPane().add(deleteFruit);



        console = new JTextArea("");
        console.setBounds(5, 5, 275 , 110);
        console.setEditable(false);
        frame.getContentPane().add(console);

        scrollPane = new JScrollPane(console);
        scrollPane.setBounds(5,5, 275, 110);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frame.getContentPane().add(scrollPane);

        fruits = new ButtonGroup();
        banana = new JCheckBox("Banana");
        banana.setActionCommand("banana");
        banana.setSelected(true);
        banana.setBounds(185, 125, 100, 25);
        fruits.add(banana);
        frame.getContentPane().add(banana);

        apple = new JCheckBox("Apple");
        apple.setActionCommand("apple");
        apple.setBounds(290, 125, 75, 25);
        fruits.add(apple);
        frame.getContentPane().add(apple);


        JLabel row_label = new JLabel("Row");
        row_label.setBounds(380, 10, 50, 15);
        frame.getContentPane().add(row_label);
        row = new JComboBox<Integer>();
        row.setBounds(380, 30, 50, 30);
        for(int i=1; i<10; i++){ row.addItem(i);}
        frame.getContentPane().add(row);


        JLabel column_label = new JLabel("Column");
        column_label.setBounds(380, 70, 70, 15);
        frame.getContentPane().add(column_label);
        column = new JComboBox<Integer>();
        column.setBounds(380, 90, 50, 30);
        for(int i=1; i<10; i++){ column.addItem(i);}
        frame.getContentPane().add(column);



        JLabel gameId_label = new JLabel("Game");
        gameId_label.setBounds(310, 10, 50, 15);
        frame.getContentPane().add(gameId_label);
        gameId = new JComboBox<Integer>();
        gameId.addItem(1);
        gameId.addItem(2);
        gameId.setBounds(310, 30, 50, 30);
        frame.getContentPane().add(gameId);



        BufferedImage image = ImageIO.read(getClass().getResource("matrixGame.png"));
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(460, 20, 550,600);
        frame.getContentPane().add(imageLabel);


        frame.setVisible(true);

    }


    /**
     * @author Sebastian Mora
     * @brief Updates console content
     * @param text new text
     */
    public static void updateConsole(String text){
        console.setText(console.getText() + "\n" + text);
    }


    class AddEnemyAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {


            for (Game game : games) {

                if(game.getId() == (int) gameId.getSelectedItem()){
                    if(enemyRed.isSelected()){
                        game.putEnemy("red", (int)row.getSelectedItem(), (int)column.getSelectedItem());
                    }
                    if(enemyBlue.isSelected()){
                        game.putEnemy("blue", (int) row.getSelectedItem(), (int)column.getSelectedItem());
                    }
                }

            }




        }
    }

    class AddFruit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String type = fruits.getSelection().getActionCommand();
            for(Game game : Server.games){
                if(game.getId() == (int)(gameId.getSelectedItem())){
                    game.putFruit(type, (int) row.getSelectedItem(), (int) column.getSelectedItem());
                }

            }

        }
    }

    class DeleteFruit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            String type = fruits.getSelection().getActionCommand();
            for(Game game : Server.games){
                if(game.getId() == (int)(gameId).getSelectedItem()){
                    game.deleteFruit(type, (int) row.getSelectedItem(), (int) column.getSelectedItem() );
                }
            }

        }
    }
}
