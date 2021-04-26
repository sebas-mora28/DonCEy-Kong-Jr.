package App;

import Game.Game;
import Server.Server;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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
    private JComboBox<Integer> liana;
    private JComboBox<Integer> column;
    private ButtonGroup fruits;
    private JCheckBox banana;
    private JCheckBox apple;
    private JCheckBox mango;
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
        frame.setSize(800,550);
        JPanel contentPane = new JPanel();
        //contentPane.setBackground(Color.BLACK);
        contentPane.setLayout(null);
        frame.setContentPane(contentPane);

        enemies = new ButtonGroup();
        enemyRed = new JCheckBox("Red");
        enemyRed.setSelected(true);
        enemyRed.setBounds(75, 300, 75, 25);
        enemies.add(enemyRed);
        frame.getContentPane().add(enemyRed);

        enemyBlue = new JCheckBox("Blue");
        enemyBlue.setBounds(160, 300, 75, 25);
        enemies.add(enemyBlue);
        frame.getContentPane().add(enemyBlue);

        addEnemy = new JButton("Add enemy");
        addEnemy.setBounds(80, 325, 125, 25);
        addEnemy.addActionListener(new AddEnemyAction());
        frame.getContentPane().add(addEnemy);


        addFruit = new JButton("Add Fruit");
        addFruit.setBounds(10, 225, 125, 25);
        addFruit.addActionListener(new AddFruit());
        frame.getContentPane().add(addFruit);

        deleteFruit = new JButton("Delete Fruit");
        deleteFruit.addActionListener(new DeleteFruit());
        deleteFruit.setBounds(150, 225, 125, 25);
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
        banana.setBounds(10, 200, 80, 25);
        fruits.add(banana);
        frame.getContentPane().add(banana);

        apple = new JCheckBox("Apple");
        apple.setActionCommand("apple");
        apple.setBounds(100, 200, 75, 25);
        fruits.add(apple);
        frame.getContentPane().add(apple);

        mango = new JCheckBox("Mango");
        mango.setActionCommand("mango");
        mango.setBounds(190, 200, 75, 25);
        fruits.add(mango);
        frame.getContentPane().add(mango);

        JLabel liana_label = new JLabel("Liana");
        liana_label.setBounds(180, 130, 50, 15);
        frame.getContentPane().add(liana_label);
        liana = new JComboBox<Integer>();
        liana.setBounds(180, 150, 50, 30);
        for(int i=1; i<=10; i++){ liana.addItem(i);}
        frame.getContentPane().add(liana);




        JLabel gameId_label = new JLabel("Game");
        gameId_label.setBounds(50, 130, 50, 15);
        frame.getContentPane().add(gameId_label);
        gameId = new JComboBox<Integer>();
        gameId.addItem(1);
        gameId.addItem(2);
        gameId.setBounds(50, 150, 50, 30);
        frame.getContentPane().add(gameId);



        BufferedImage image = ImageIO.read(getClass().getResource("background.png"));
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(290, 5, 500,500);
        frame.getContentPane().add(imageLabel);

        //BufferedImage logo = ImageIO.read(getClass().getResource("logo.png"));
        //JLabel logoLabel = new JLabel(new ImageIcon(logo));
        //logoLabel.setBounds(1, 150, 300,300);
        //frame.getContentPane().add(logoLabel);

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
                        game.putEnemy("red", (int) liana.getSelectedItem());
                    }
                    if(enemyBlue.isSelected()){
                        game.putEnemy("blue", (int) liana.getSelectedItem());
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
                    game.putFruit(type, (int) liana.getSelectedItem());
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
                    game.deleteFruit(type, (int) liana.getSelectedItem() );
                }
            }

        }
    }
}
