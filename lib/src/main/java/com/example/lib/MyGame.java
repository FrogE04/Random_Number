package com.example.lib;
//import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JFormattedTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTextField;

public class MyGame extends JFrame{
    private static final long serialVersionUID = 1L;

    private JPanel jContentPane = null;

    private JFormattedTextField input = null;

    private JButton guessAns = null;

    private JScrollPane jScrollPane = null;

    private JTextArea ansArea = null;

    private JButton start = null;

    private int ans = 0;

    private final int maxInt = 99;

    private JButton aboutMe = null;

    private JButton Ans = null;

    /**
     * This is the default constructor
     */
    public MyGame() {
        super();
        initialize();
        reStart();
    }

    public void reStart() {
        this.input.setText("");
        this.ansArea.setText("");
        java.util.Random r = new java.util.Random();
        this.ans = r.nextInt(maxInt) + 1;
        this.getInput().requestFocus();
    }

    public void doGuest() {
        int input_ans = 0;
        try {
            input_ans = Integer.parseInt(this.getInput().getText());
        } catch (Exception ex) {
            return;
        }
        if (input_ans > this.ans)
            this.getAnsArea().append("答案比 "+ input_ans + " 小 !\n" );
        else if (input_ans < this.ans)
            this.getAnsArea().append("答案比 "+ input_ans + " 大 !\n" );
        else {
            this.getAnsArea().append(input_ans + " 你答對了!\n");
            this.getAnsArea().append("請案重新開始\n或者關閉程式\n");
        }

        // 再把答案欄清空
        this.getInput().setText("");
        this.getInput().requestFocus();

    }

    public void doAns() {

        this.getAnsArea().append("答案是:" + this.ans + "\n");
        this.getInput().setText("");
        this.getInput().requestFocus();
    }

    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {
        this.setSize(500, 300); //整個介面大小  長寬
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(screenSize.width / 2 - this.getWidth() / 2,   //介面螢幕位子.X軸
                screenSize.height / 2 - this.getHeight() / 2);        //介面螢幕位子.y軸
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setContentPane(getJContentPane());
        this.setTitle("猜數字~太好玩啦!");
        this.setVisible(true);
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getInput(), null);
            jContentPane.add(getGuessAns(), null);
            jContentPane.add(getJScrollPane(), null);
            jContentPane.add(getStart(), null);
            jContentPane.add(getAns(), null);

        }
        return jContentPane;
    }

    /**
     * This method initializes input
     *
     * @return javax.swing.JFormattedTextField
     */
    private JFormattedTextField getInput() {  //輸入答案的格子
        if (input == null) {
            javax.swing.text.NumberFormatter mf2 = null;
            mf2 = new javax.swing.text.NumberFormatter();
            mf2.setMaximum(new Integer(maxInt));
            mf2.setMinimum(new Integer(1));
            input = new JFormattedTextField(mf2);
            input.setBounds(new Rectangle(150, 1, 150, 150));  //猜數字格子 前2位置 後2長寬
            input.setFont(new Font("Dialog", Font.PLAIN, 80));         //猜數字的字大小
            input.setHorizontalAlignment(JTextField.CENTER);
            input.setText("20");
        }
        return input;
    }

    /**
     * This method initializes guessAns
     *
     * @return javax.swing.JButton
     */
    private JButton getGuessAns() {    //來猜看看的按鍵
        if (guessAns == null) {
            guessAns = new JButton();
            guessAns.setBounds(new Rectangle(1, 151, 300, 120)); //前2位子  後2長寬
            guessAns.setBackground(Color.cyan);   //按鍵顏色
            guessAns.setText("來猜看看");
            guessAns.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    MyGame frame = ((MyGame) ((JButton) e.getSource())
                            .getTopLevelAncestor());
                    frame.doGuest();
                }
            });
        }
        return guessAns;
    }

    /**
     * This method initializes jScrollPane
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane() {     //提示欄
        if (jScrollPane == null) {
            jScrollPane = new JScrollPane();
            jScrollPane.setBounds(new Rectangle(300, 1, 200, 300)); //位子  長寬
            jScrollPane.setViewportView(getAnsArea());
        }
        return jScrollPane;
    }

    /**
     * This method initializes ansArea
     *
     * @return javax.swing.JTextArea
     */
    private JTextArea getAnsArea() {
        if (ansArea == null) {
            ansArea = new JTextArea();
        }
        return ansArea;
    }

    /**
     * This method initializes start
     *
     * @return javax.swing.JButton
     */
    private JButton getStart() {  //重新開始
        if (start == null) {
            start = new JButton();
            start.setBounds(new Rectangle(1, 1, 150, 70));  //按鍵位子  長寬
            start.setBackground(Color.cyan);                              //按鍵顏色
            start.setActionCommand("重新開始");
            start.setText("重新聞始");
            start.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    MyGame frame = ((MyGame) ((JButton) e.getSource())
                            .getTopLevelAncestor());
                    frame.reStart();
                }
            });
        }
        return start;
    }

    /**
     * This method initializes Ans
     *
     * @return javax.swing.JButton
     */
    private JButton getAns() {       //查看答案
        if (Ans == null) {
            Ans = new JButton();
            Ans.setBounds(new Rectangle(1, 70, 150, 80));
            Ans.setText("查看答案");
            Ans.setBackground(Color.yellow);
            Ans.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    MyGame frame = ((MyGame) ((JButton) e.getSource())
                            .getTopLevelAncestor());
                    frame.doAns();
                }
            });
        }
        return Ans;
    }

    /**
     * This method initializes aboutMe
     *
     * @return javax.swing.JButton
     */

    public static void main(String args[]) {
        new MyGame();
    }
}