package App;

import Game.Game;
import Server.Server;
import netscape.javascript.JSObject;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {


    ButtonGroup enemies;
    JCheckBox enemyRed;
    JCheckBox enemyBlue;
    JComboBox<Integer> gameId;
    JButton addEnemy;
    JButton addFruit;
    JButton deleteFruit;
    JComboBox<Integer> liana;
    ButtonGroup fruits;
    JCheckBox banana;
    JCheckBox apple;




    public Window(){


        JFrame frame = new JFrame("Console");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450,300);

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



        fruits = new ButtonGroup();
        banana = new JCheckBox("Banana");
        banana.setSelected(true);
        banana.setBounds(185, 125, 100, 25);
        fruits.add(banana);
        frame.getContentPane().add(banana);

        apple = new JCheckBox("Apple");
        apple.setBounds(290, 125, 75, 25);
        fruits.add(apple);
        frame.getContentPane().add(apple);

        liana = new JComboBox<Integer>();
        liana.setBounds(250, 50, 50, 30);
        for(int i=0; i<10; i++){ liana.addItem(i);}
        frame.getContentPane().add(liana);

        gameId = new JComboBox<Integer>();
        gameId.addItem(1);
        gameId.addItem(2);
        gameId.setBounds(125, 50, 50, 30);
        frame.getContentPane().add(gameId);


        frame.setVisible(true);

    }

    class AddEnemyAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {


            for (Game game : Server.games) {

                if(game.getId() == (int) gameId.getSelectedItem()){
                    if(enemyRed.isSelected()){
                        game.putEnemy("red", (int)liana.getSelectedItem());
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

        }
    }

    class DeleteFruit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

        }
    }
}
