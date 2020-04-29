package Habbib.view;

import Habbib.controller.SessionController;
import Habbib.model.Institution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame{

    private JLabel headerLabel;
    private JButton loginButton, registerButton;
    private SessionController sessionController;
    private Container containerMenu;
    private Container containerRegister;


    public LoginView() {
        super("Habbib beds");
        showWindow();

    }
    public void showWindow(){
        setSize(620,520);
        setLocationRelativeTo(null); //apresentando centralizada

        setContentPane(initComponents());
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private Container initComponents(){
        Container initContainer = getContentPane();
        initContainer.setLayout(null);

        headerLabel = new JLabel("Habbib beds");
        headerLabel.setBounds(230,50,140,40);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI Historic", 1, 20));

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setFont(new Font("Segoe UI Historic", 0, 16));
        loginLabel.setBounds(280,125,50,30);

        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setBounds(155,180,60,20);
        userLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userLabel.setVerticalAlignment(SwingConstants.CENTER);
        userLabel.setFont(new Font("Segoe UI Historic", 0, 14));

        JTextField  userInput = new JTextField();
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

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initContainer.setVisible(false);
                setContentPane(initRegister());
            }
        });



        return initContainer;
    }
    private Container initMenu(){
        containerMenu = new Container();
        containerMenu.setLayout(null);


        headerLabel = new JLabel("Menu");
        headerLabel.setBounds(230,50,140,40);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI Historic", 1, 24));

        JButton seila = new JButton();
        seila.setBounds(219,232,200,20);

        containerMenu.add(headerLabel);
        containerMenu.add(seila);
        setVisible(true);

        return containerMenu;
    }
    private Container initRegister(){
        containerRegister = new Container();
        containerRegister.setLayout(null);

        headerLabel = new JLabel("Cadastre sua instituição");
        headerLabel.setBounds(169,10, 251, 32 );
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI Historic", 0, 24));

        JLabel nameLabel = new JLabel("Nome:");
        nameLabel.setBounds(10,59,40,20);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        nameLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JTextField  nameInput = new JTextField();
        nameInput.setBounds(60,60,530,20);

        JLabel cnpjLabel = new JLabel("CNPJ:");
        cnpjLabel.setBounds(10,99,40,20);
        cnpjLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cnpjLabel.setVerticalAlignment(SwingConstants.CENTER);
        cnpjLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JTextField  cpnjInput = new JTextField();
        cpnjInput.setBounds(60,100,350,20);

        JLabel typeLabel = new JLabel("Tipo:");
        typeLabel.setBounds(408,99,40,20);
        typeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        typeLabel.setVerticalAlignment(SwingConstants.CENTER);
        typeLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JComboBox typeCB= new JComboBox();
        typeCB.setModel(new DefaultComboBoxModel<>(new String[] { "Selecionar","Privado", "Público"}));
        typeCB.setBounds(448,100,142,20);


        JLabel adressLabel = new JLabel("Endereço:");
        adressLabel.setBounds(10,139,59,20);
        adressLabel.setHorizontalAlignment(SwingConstants.CENTER);
        adressLabel.setVerticalAlignment(SwingConstants.CENTER);
        adressLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JTextField  adressInput = new JTextField();
        adressInput.setBounds(79,139,511,20);



        JLabel zipCodeLabel = new JLabel("CEP:");
        zipCodeLabel.setBounds(10,179,27,20);
        zipCodeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        zipCodeLabel.setVerticalAlignment(SwingConstants.CENTER);
        zipCodeLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JTextField  zipCodeInput = new JTextField();
        zipCodeInput.setBounds(47,180,124,20);

        JLabel neighborhoodLabel = new JLabel("Bairro:");
        neighborhoodLabel.setBounds(175,179,39,20);
        neighborhoodLabel.setHorizontalAlignment(SwingConstants.CENTER);
        neighborhoodLabel.setVerticalAlignment(SwingConstants.CENTER);
        neighborhoodLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JTextField  neighborhoodInput = new JTextField();
        neighborhoodInput.setBounds(224,180,198,20);

        JLabel numberLabel = new JLabel("Número:");
        numberLabel.setBounds(426,179,53,20);
        numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numberLabel.setVerticalAlignment(SwingConstants.CENTER);
        numberLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JTextField  numberInput = new JTextField();
        numberInput.setBounds(489,180,101,20);

        JLabel complementLabel = new JLabel("Complemento:");
        complementLabel.setBounds(10,219,90,20);
        complementLabel.setHorizontalAlignment(SwingConstants.CENTER);
        complementLabel.setVerticalAlignment(SwingConstants.CENTER);
        complementLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JTextField  complementInput = new JTextField();
        complementInput.setBounds(110,220,128,20);

        JLabel cityLabel = new JLabel("Cidade:");
        cityLabel.setBounds(242,219,45,20);
        cityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cityLabel.setVerticalAlignment(SwingConstants.CENTER);
        cityLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JTextField  cityInput = new JTextField();
        cityInput.setBounds(297,220,187,20);

        JLabel UFLabel = new JLabel("UF:");
        UFLabel.setBounds(488,219,20,20);
        UFLabel.setHorizontalAlignment(SwingConstants.CENTER);
        UFLabel.setVerticalAlignment(SwingConstants.CENTER);
        UFLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JComboBox UFCB= new JComboBox();
        UFCB.setModel(new DefaultComboBoxModel<>(new String[] { "Selecionar","SP", "RJ"}));
        UFCB.setBounds(517,220,75,20);

        JLabel phoneLabel = new JLabel("Telefone:");
        phoneLabel.setBounds(10,259,54,20);
        phoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
        phoneLabel.setVerticalAlignment(SwingConstants.CENTER);
        phoneLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JTextField  phoneInput = new JTextField();
        phoneInput.setBounds(74,260,158,20);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setBounds(10,299,40,20);
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setVerticalAlignment(SwingConstants.CENTER);
        passwordLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JPasswordField  passwordInput = new JPasswordField();
        passwordInput.setBounds(60,300,204,20);



        JLabel cPasswordLabel = new JLabel("Confirmar senha:");
        cPasswordLabel.setBounds(268,299,104,20);
        cPasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cPasswordLabel.setVerticalAlignment(SwingConstants.CENTER);
        cPasswordLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JPasswordField  cPasswordInput = new JPasswordField();
        cPasswordInput.setBounds(382,300,208,20);



        JButton backButton = new JButton("Voltar");
        backButton.setBounds(188,428,109,31);
        backButton.setFont(new Font("Segoe UI Historic", 0, 16));
        JButton registerButton = new JButton("Cadastrar");
        registerButton.setBounds(303,428,109,31);
        registerButton.setFont(new Font("Segoe UI Historic", 1, 16));

        containerRegister.add(headerLabel);
        containerRegister.add(nameLabel);
        containerRegister.add(nameInput );
        containerRegister.add(cnpjLabel);
        containerRegister.add(cpnjInput);
        containerRegister.add(typeLabel);
        containerRegister.add(typeCB);
        containerRegister.add(adressLabel);
        containerRegister.add(adressInput);
        containerRegister.add(zipCodeLabel);
        containerRegister.add(zipCodeInput);
        containerRegister.add(neighborhoodLabel);
        containerRegister.add(neighborhoodInput);
        containerRegister.add(numberLabel);
        containerRegister.add(numberInput);
        containerRegister.add(complementLabel);
        containerRegister.add(complementInput);
        containerRegister.add(cityLabel);
        containerRegister.add(cityInput);
        containerRegister.add(UFLabel);
        containerRegister.add(UFCB);
        containerRegister.add(phoneLabel);
        containerRegister.add(phoneInput);
        containerRegister.add(passwordLabel);
        containerRegister.add(passwordInput);
        containerRegister.add(cPasswordLabel );
        containerRegister.add(cPasswordInput);
        containerRegister.add(backButton);
        containerRegister.add(registerButton);

        setVisible(true);
        return containerRegister;
    }

}
