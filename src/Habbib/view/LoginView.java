package Habbib.view;

import Habbib.controller.SessionController;
import Habbib.model.Institution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame{
    private JFrame frame;
    private JLabel headerLabel,loginLabel;
    private JButton loginButton, registerButton;
    private SessionController sessionController;
    private Container containerMenu;


    public LoginView() {
        super("Habbib beds");
        showWindow();

    }
    public void showWindow(){

        setSize(600,500);
        setLocationRelativeTo(null); //apresentando centralizada
        Rectangle rc = getBounds();
        rc.width = 700; //fixando tamanho
        rc.height = 500;
        setContentPane(initComponents());
        setMaximizedBounds(rc);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private Container initComponents(){
        Container initContainer = getContentPane();
        initContainer.setLayout(null);
        initContainer.setFont(new Font("Segoe UI Historic", 0, 14));
        headerLabel = new JLabel("Habbib beds");
        headerLabel.setBounds(230,50,140,40);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI Historic", 1, 20));

        loginLabel = new JLabel("Login");
        loginLabel.setFont(new Font("Segoe UI Historic", 0, 16));
        loginLabel.setBounds(280,125,50,30);

        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setBounds(155,180,60,20);
        userLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userLabel.setVerticalAlignment(SwingConstants.CENTER);
        userLabel.setFont(new Font("Segoe UI Historic", 0, 14));

        JTextField  userInput = new JTextField(10);
        userInput.setBounds(219,180,200,20);

        JLabel passLabel = new JLabel("Senha:");
        passLabel.setBounds(155,206,60,20);
        passLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passLabel.setVerticalAlignment(SwingConstants.CENTER);
        passLabel.setFont(new Font("Segoe UI Historic", 0, 14));

        JPasswordField passInput = new JPasswordField();
        passInput.setBounds(219,206,200,20);

        loginButton = new JButton();
        loginButton.setText("Entrar");
        loginButton.setBounds(219,232,200,20);

        JLabel registerLabel = new JLabel("Não tem uma conta?");
        registerLabel.setBounds(219,400,120,20);
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setVerticalAlignment(SwingConstants.CENTER);
        registerLabel.setFont(new Font("Segoe UI Historic", 0, 12));

        registerButton = new JButton();
        registerButton.setText("Crie uma");
        registerButton.setBounds(340,400,81,20);
        registerButton.setFont(new Font("Segoe UI Historic", 0, 10));

        initContainer.add(headerLabel);
        initContainer.add(loginLabel);
        initContainer.add(userLabel);
        initContainer.add(userInput);
        initContainer.add(passLabel);
        initContainer.add(passInput);
        initContainer.add(loginButton);
        initContainer.add(registerLabel);
        initContainer.add(registerButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    sessionController = new SessionController();
                    String user = (String) userInput.getText();
                    String pass = (String) passInput.getText();

                    System.out.println(user +" "+ pass);
                    Institution institution = sessionController.login(user, pass);

                    if(institution != null){
                        initContainer.setVisible(false);
                        setContentPane(initMenu());
                    }
                }
                catch (Exception ex)
                {

                }


            }
        });
        return initContainer;
    }
    private Container initMenu(){
        containerMenu = new Container();
        containerMenu.setLayout(null);
        containerMenu.setFont(new Font("Segoe UI Historic", 0, 14));

        headerLabel = new JLabel("LOGADO!!!!");
        headerLabel.setBounds(230,50,140,40);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI Historic", 1, 20));
        containerMenu.add(headerLabel);
        setVisible(true);
        return containerMenu;
    }

}
