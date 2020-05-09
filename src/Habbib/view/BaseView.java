package Habbib.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BaseView extends JFrame {

    private String font = "Segoe UI Historic";

    private Font titleFont = new Font(font, Font.PLAIN, 16);
    private Font headerFont = new Font(font, Font.PLAIN, 20);
    private Font inputLabelFont = new Font(font, Font.PLAIN, 14);
    private Font textLabelFont = new Font(font, Font.PLAIN, 12);
    private Font textLabelFontBold = new Font(font, Font.BOLD, 12);
    private Font buttonFont = new Font(font, Font.PLAIN, 10);
    private Font buttonFontBig = new Font(font, Font.PLAIN, 16);
    private Font textTable = new Font(font, Font.PLAIN,11);

    protected Container currentContainer;

    public BaseView(String title) {
        super(title);
    }

    public void showWindow(Container contentPane, int width, int height){
        super.setSize(width,height);
        super.setResizable(false);
        super.setLocationRelativeTo(null);
        setContentPane(contentPane);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setContentPane(Container contentPane){
        currentContainer = contentPane;
        super.setContentPane(contentPane);
    }

    public JLabel createTitleLabel(String title, int x, int y, int width, int height){
        JLabel tittleLabel = new JLabel(title);
        tittleLabel.setFont(titleFont);
        tittleLabel.setBounds(x,y,width,height);
        return tittleLabel;
    }

    public JLabel createHeaderLabel(String title, int x, int y, int width, int height) {
        JLabel headerLabel = new JLabel(title);
        headerLabel.setFont(headerFont);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBounds(x,y,width,height);
        return headerLabel;
    }

    public JLabel createInputLabel(String title, int x, int y, int width, int height) {
        JLabel inputLabel = new JLabel(title);
        inputLabel.setBounds(x,y,width,height);
        inputLabel.setHorizontalAlignment(SwingConstants.LEFT);
        inputLabel.setVerticalAlignment(SwingConstants.CENTER);
        inputLabel.setFont(inputLabelFont);
        return inputLabel;
    }

    public JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x,y,width,height);
        return  textField;
    }

    public JPasswordField createPasswordField(int x, int y, int width, int height) {
        JPasswordField passInput = new JPasswordField();
        passInput.setBounds(x,y,width,height);
        return passInput;
    }

    public JLabel createTextLabel(String title, int x, int y, int width, int height){
        JLabel textLabel = new JLabel(title);
        textLabel.setFont(textLabelFont);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setVerticalAlignment(SwingConstants.CENTER);
        textLabel.setBounds(x,y,width,height);
        return textLabel;
    }

    public JButton createButton(String title, int x, int y, int width, int height){
        JButton button = new JButton();
        button.setText(title);
        button.setBounds(x,y,width,height);
        button.setFont(buttonFont);
        return button;
    }

    public JButton createDashboardButton(String title, int x, int y, int width, int height){
        JButton button = new JButton();
        button.setText(title);
        button.setBounds(x,y,width,height);
        button.setFont(buttonFontBig);
        return button;
    }

    public JComboBox createComboBox(Object[] options, int x, int y, int width, int height ) {
        JComboBox comboBox= new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel<>(options));
        comboBox.setBounds(x,y,width,height);
        return comboBox;
    }
    public JTable createTable(DefaultTableModel model){
        JTable table = new JTable(model);
        table.setFont(textTable);


        return  table;
    }

    protected JLabel createTextLabelLeft(String name, int x, int y, int width, int height) {
        JLabel textLabel = new JLabel(name);
        textLabel.setFont(textLabelFont);
        textLabel.setHorizontalAlignment(SwingConstants.LEFT);
        textLabel.setVerticalAlignment(SwingConstants.CENTER);
        textLabel.setBounds(x,y,width,height);
        return textLabel;

    }

    protected JLabel createTextLabelLeftBold(String name, int x, int y, int width, int height) {
        JLabel textLabel = new JLabel(name);
        textLabel.setFont(textLabelFontBold);
        textLabel.setHorizontalAlignment(SwingConstants.LEFT);
        textLabel.setVerticalAlignment(SwingConstants.CENTER);
        textLabel.setBounds(x,y,width,height);
        return textLabel;
    }

    protected JTextArea createJTextArea(int x, int y, int width, int height) {
        JTextArea  t = new JTextArea();
        t.setFont(textLabelFont);
        t.setBounds(x,y,width,height);

        return t;

    }


}
