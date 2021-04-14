package App;

import netscape.javascript.JSObject;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {


    public Window(){


        JFrame frame = new JFrame("Console");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450,300);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        frame.setContentPane(contentPane);


        ButtonGroup enemies = new ButtonGroup();
        JRadioButton enemyRed = new JRadioButton("Red");
        enemyRed.setSelected(true);
        enemyRed.setBounds(20, 25, 75, 25);
        enemies.add(enemyRed);
        frame.getContentPane().add(enemyRed);


        JRadioButton enemyBlue = new JRadioButton("Blue");
        enemyBlue.setBounds(95, 25, 75, 25);
        enemies.add(enemyRed);
        frame.getContentPane().add(enemyBlue);

        JButton addEnemy = new JButton("Add enemy");
        addEnemy.setBounds(10, 50, 125, 25);
        addEnemy.addActionListener(new AddEnemyAction());
        frame.getContentPane().add(addEnemy);


        JButton addFruit = new JButton("Add Fruit");
        addFruit.setBounds(10, 100, 125, 25);
        addFruit.addActionListener(new AddFruit());
        frame.getContentPane().add(addFruit);

        JButton deleteFruit = new JButton("Delete Fruit");
        deleteFruit.addActionListener(new DeleteFruit());
        deleteFruit.setBounds(10, 150, 125, 25);
        frame.getContentPane().add(deleteFruit);


        JComboBox<Integer> liana = new JComboBox<Integer>();
        liana.setBounds(200, 80, 50, 30);
        for(int i=0; i<10; i++){ liana.addItem(i);}
        frame.getContentPane().add(liana);


        JComboBox<Integer> numberGame = new JComboBox<Integer>();
        numberGame.addItem(1);
        numberGame.addItem(2);
        numberGame.setBounds(275, 80, 50, 30);
        frame.getContentPane().add(numberGame);

        frame.setVisible(true);

    }

    static class AddEnemyAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

        }
    }

    static class AddFruit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

        }
    }

    static class DeleteFruit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

        }
    }
}
