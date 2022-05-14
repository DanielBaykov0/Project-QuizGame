package baykov.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quiz implements ActionListener {

    private final String[] questions = {
            "Which company created Java ?",
            "Which year was Java created ?",
            "What was Java's original name ?",
            "What is Java's logo ?",
            "Where does Java's name come from ?"
    };

    private final String[][] options = {
            {"Microsoft", "Alphabet", "Sun Microsystems", "SAP"},
            {"2000", "1996", "1963", "1982"},
            {"Apple", "Latte", "Oak", "Butternut"},
            {"Snake", "Tree", "Diamond", "Coffee"},
            {"Indonesia", "Australia", "Bulgaria", "USA"},
    };

    private final char[] answers = {
            'C',
            'B',
            'C',
            'D',
            'A'
    };

    private char answer;
    private int index;
    private int correctGuesses = 0;
    private final int totalQuestions = questions.length;
    private int seconds = 10;

    private final JFrame frame = new JFrame();
    private final JTextField textField = new JTextField();
    private final JTextArea textArea = new JTextArea();
    private final JButton buttonA = new JButton();
    private final JButton buttonB = new JButton();
    private final JButton buttonC = new JButton();
    private final JButton buttonD = new JButton();
    private final JLabel answerLabelA = new JLabel();
    private final JLabel answerLabelB = new JLabel();
    private final JLabel answerLabelC = new JLabel();
    private final JLabel answerLabelD = new JLabel();
    private final JLabel timeLabel = new JLabel();
    private final JLabel secondsLeft = new JLabel();
    private final JTextField numberRight = new JTextField();
    private final JTextField percentage = new JTextField();

    Timer timer = new Timer(1000, e -> {
        seconds--;
        // after each method we are subtracting 1 from our seconds(10)
        secondsLeft.setText(String.valueOf(seconds));
        if (seconds <= 0) {
            displayAnswer();
            // if second are 0 or less, call the displayAnswer() method
        }
    });

    public Quiz() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(650, 650);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setLayout(null);
        // no layout, place everything where you choose
        frame.setResizable(false);
        // can't resize

        textField.setBounds(10, 10, 630, 50);
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.WHITE);
        textField.setFont(new Font("Calibri", Font.BOLD, 30));
        textField.setBorder(BorderFactory.createBevelBorder(1));
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setEditable(false);

        textArea.setBounds(25, 70, 600, 50);
        textArea.setLineWrap(true);
        // if the text goes off the screen, it's going to wrap around the next line
        textArea.setWrapStyleWord(true);
        // the lines will be wrapped at word boundaries (whitespace) if they are too long
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font("Calibri", Font.BOLD, 25));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false);

        buttonA.setBounds(25, 140, 100, 100);
        buttonA.setFont(new Font("Calibri", Font.BOLD, 35));
        buttonA.setFocusable(false);
        // button won't be highlighted if it's clicked
        buttonA.addActionListener(this);
        buttonA.setText("A");
        buttonA.setBackground(Color.WHITE);
        buttonA.setForeground(Color.BLACK);

        buttonB.setBounds(25, 250, 100, 100);
        buttonB.setFont(new Font("Calibri", Font.BOLD, 35));
        buttonB.setFocusable(false);
        // button won't be highlighted if it's clicked
        buttonB.addActionListener(this);
        buttonB.setText("B");
        buttonB.setBackground(Color.WHITE);
        buttonB.setForeground(Color.BLACK);

        buttonC.setBounds(25, 360, 100, 100);
        buttonC.setFont(new Font("Calibri", Font.BOLD, 35));
        buttonC.setFocusable(false);
        // button won't be highlighted if it's clicked
        buttonC.addActionListener(this);
        buttonC.setText("C");
        buttonC.setBackground(Color.WHITE);
        buttonC.setForeground(Color.BLACK);

        buttonD.setBounds(25, 470, 100, 100);
        buttonD.setFont(new Font("Calibri", Font.BOLD, 35));
        buttonD.setFocusable(false);
        // button won't be highlighted if it's clicked
        buttonD.addActionListener(this);
        buttonD.setText("D");
        buttonD.setBackground(Color.WHITE);
        buttonD.setForeground(Color.BLACK);

        answerLabelA.setBounds(130, 140, 500, 100);
        answerLabelA.setBackground(Color.DARK_GRAY);
        answerLabelA.setForeground(Color.WHITE);
        answerLabelA.setFont(new Font("Calibri", Font.BOLD, 35));

        answerLabelB.setBounds(130, 250, 500, 100);
        answerLabelB.setBackground(Color.DARK_GRAY);
        answerLabelB.setForeground(Color.WHITE);
        answerLabelB.setFont(new Font("Calibri", Font.BOLD, 35));

        answerLabelC.setBounds(130, 360, 500, 100);
        answerLabelC.setBackground(Color.DARK_GRAY);
        answerLabelC.setForeground(Color.WHITE);
        answerLabelC.setFont(new Font("Calibri", Font.BOLD, 35));

        answerLabelD.setBounds(130, 470, 500, 100);
        answerLabelD.setBackground(Color.DARK_GRAY);
        answerLabelD.setForeground(Color.WHITE);
        answerLabelD.setFont(new Font("Calibri", Font.BOLD, 35));

        secondsLeft.setBounds(535, 510, 100, 100);
        secondsLeft.setBackground(Color.BLACK);
        secondsLeft.setForeground(Color.RED);
        secondsLeft.setFont(new Font("Calibri", Font.BOLD, 60));
        secondsLeft.setBorder(BorderFactory.createBevelBorder(1));
        secondsLeft.setOpaque(true);
        secondsLeft.setHorizontalAlignment(SwingConstants.CENTER);
        secondsLeft.setText(String.valueOf(seconds));

        timeLabel.setBounds(535, 490, 100, 25);
        timeLabel.setBackground(Color.DARK_GRAY);
        timeLabel.setForeground(Color.RED);
        timeLabel.setFont(new Font("Calibri", Font.BOLD, 15));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setText("timer");

        numberRight.setBounds(225, 225, 200, 100);
        numberRight.setBackground(Color.BLACK);
        numberRight.setForeground(Color.GREEN);
        numberRight.setFont(new Font("Calibri", Font.PLAIN, 50));
        numberRight.setBorder(BorderFactory.createBevelBorder(1));
        numberRight.setHorizontalAlignment(SwingConstants.CENTER);
        numberRight.setEditable(false);

        percentage.setBounds(225, 325, 200, 100);
        percentage.setBackground(Color.BLACK);
        percentage.setForeground(Color.GREEN);
        percentage.setFont(new Font("Calibri", Font.PLAIN, 50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(SwingConstants.CENTER);
        percentage.setEditable(false);

        frame.add(timeLabel);
        frame.add(secondsLeft);
        frame.add(answerLabelA);
        frame.add(answerLabelB);
        frame.add(answerLabelC);
        frame.add(answerLabelD);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textArea);
        frame.add(textField);
        frame.setVisible(true);

        nextQuestion();
    }

    public void nextQuestion() {
        if (index >= totalQuestions) {
            results();
        } else {
            textField.setText("Question " + (index + 1));
            // question + ( index = 0 + 1)
            textArea.setText(questions[index]);
            // questions[0], incrementing index increases the value
            answerLabelA.setText(options[index][0]);
            // options [row index = 0][0 -> first option in the array]
            answerLabelB.setText(options[index][1]);
            answerLabelC.setText(options[index][2]);
            answerLabelD.setText(options[index][3]);
            timer.start();
            // timer start
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if (e.getSource() == buttonA) {
            answer = 'A';
            if (answer == answers[index]) {
                // if answer 'A' equals an answer in our array of answers at a particular index
                correctGuesses++;
                // increasing correct answers by 1
            }
        }

        if (e.getSource() == buttonB) {
            answer = 'B';
            if (answer == answers[index]) {
                    correctGuesses++;
            }
        }

        if (e.getSource() == buttonC) {
            answer = 'C';
            if (answer == answers[index]) {
                correctGuesses++;
            }
        }

        if (e.getSource() == buttonD) {
            answer = 'D';
            if (answer == answers[index]) {
                correctGuesses++;
            }
        }

        displayAnswer();
    }

    public void displayAnswer() {

        timer.stop();

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if (answers[index] != 'A') {
            // if the answer from the array is not 'A'
            answerLabelA.setForeground(Color.RED);
            // sets the color of the text to red
        } else {
            answerLabelA.setForeground(Color.GREEN);
            // if the answer is correct set the text color to green
        }

        if (answers[index] != 'B') {
            answerLabelB.setForeground(Color.RED);
        } else {
            answerLabelB.setForeground(Color.GREEN);
        }

        if (answers[index] != 'C') {
            answerLabelC.setForeground(Color.RED);
        } else {
            answerLabelC.setForeground(Color.GREEN);
        }

        if (answers[index] != 'D') {
            answerLabelD.setForeground(Color.RED);
        } else {
            answerLabelD.setForeground(Color.GREEN);
        }

        Timer pause = new Timer(2000, e -> {
            answerLabelA.setForeground(Color.WHITE);
            // buttons are reversed to their original color
            answerLabelB.setForeground(Color.WHITE);
            answerLabelC.setForeground(Color.WHITE);
            answerLabelD.setForeground(Color.WHITE);

            answer = ' ';
            // sets the answer to a blank space
            seconds = 10;
            // reset seconds back to 10
            secondsLeft.setText(String.valueOf(seconds));
            // reset seconds left
            buttonA.setEnabled(true);
            // buttons are enabled for pressing again
            buttonB.setEnabled(true);
            buttonC.setEnabled(true);
            buttonD.setEnabled(true);
            index++;
            // increment the index
            nextQuestion();
            // call the next question method
        });
        pause.setRepeats(false);
        // Timer won't repeat, it will execute once
        pause.start();
        // starting the Timer
    }

    public void results() {

        buttonA.setEnabled(false);
        // button can't be pressed repeatedly after giving an answer
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        int result = (int) ((correctGuesses / (double) totalQuestions) * 100);

        textField.setText("Result");
        textArea.setText("");
        answerLabelA.setText("");
        answerLabelB.setText("");
        answerLabelC.setText("");
        answerLabelD.setText("");

        numberRight.setText(correctGuesses + " / " + totalQuestions);
        percentage.setText(result + "%");

        frame.add(numberRight);
        frame.add(percentage);
    }
}
