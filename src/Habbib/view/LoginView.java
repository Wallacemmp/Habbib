package Habbib.view;

import Habbib.controller.InstitutionController;
import Habbib.controller.SessionController;
import Habbib.model.Address;
import Habbib.model.Institution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame{

    private JLabel headerLabel;
    private JButton loginButton, registerButton;
    private SessionController sessionController;
    private Container initContainer;
    private Container menuContainer;
    private Container registerContainer;



    public LoginView() {
        super("Habbib beds");
        showWindow();

    }
    public void showWindow(){
        setSize(620,520);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(initComponents());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private Container initComponents(){
        initContainer = new JPanel();
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
        initContainer.setVisible(true);
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
    private Container initRegister(){
        registerContainer = new JPanel();
        registerContainer.setLayout(null);

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
        nameInput.setBounds(79,60,512,22);

        JLabel cnpjLabel = new JLabel("CNPJ:");
        cnpjLabel.setBounds(10,99,40,20);
        cnpjLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cnpjLabel.setVerticalAlignment(SwingConstants.CENTER);
        cnpjLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JTextField  cpnjInput = new JTextField();
        cpnjInput.setBounds(79,100,330,22);

        JLabel typeLabel = new JLabel("Tipo:");
        typeLabel.setBounds(408,99,40,20);
        typeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        typeLabel.setVerticalAlignment(SwingConstants.CENTER);
        typeLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JComboBox typeCB= new JComboBox();
        //TODO não permitiri selecionar a opção "Selecionar" no combobox Type
        typeCB.setModel(new DefaultComboBoxModel<>(new String[] { "Selecionar","Privado", "Público"}));
        typeCB.setBounds(448,100,142,22);


        JLabel addressLabel = new JLabel("Endereço:");
        addressLabel.setBounds(10,139,59,20);
        addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addressLabel.setVerticalAlignment(SwingConstants.CENTER);
        addressLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JTextField  addressInput = new JTextField();
        addressInput.setBounds(79,139,330,22);

        JLabel zipCodeLabel = new JLabel("CEP:");
        zipCodeLabel.setBounds(10,179,27,20);
        zipCodeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        zipCodeLabel.setVerticalAlignment(SwingConstants.CENTER);
        zipCodeLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JTextField  zipCodeInput = new JTextField();
        zipCodeInput.setBounds(79,180,114,22);

        JLabel neighborhoodLabel = new JLabel("Bairro:");
        neighborhoodLabel.setBounds(195,179,39,20);
        neighborhoodLabel.setHorizontalAlignment(SwingConstants.CENTER);
        neighborhoodLabel.setVerticalAlignment(SwingConstants.CENTER);
        neighborhoodLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JTextField  neighborhoodInput = new JTextField();
        neighborhoodInput.setBounds(236,180,174,22);

        JLabel numberLabel = new JLabel("Número:");
        numberLabel.setBounds(426,139,53,20);
        numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numberLabel.setVerticalAlignment(SwingConstants.CENTER);
        numberLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JTextField  numberInput = new JTextField();
        numberInput.setBounds(489,139,101,22);

        JLabel complementLabel = new JLabel("Compl:");
        complementLabel.setBounds(404,179,90,20);
        complementLabel.setHorizontalAlignment(SwingConstants.CENTER);
        complementLabel.setVerticalAlignment(SwingConstants.CENTER);
        complementLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JTextField  complementInput = new JTextField();
        complementInput.setBounds(489,180,101,22);

        JLabel cityLabel = new JLabel("Cidade:");
        cityLabel.setBounds(10,219,45,20);
        cityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cityLabel.setVerticalAlignment(SwingConstants.CENTER);
        cityLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JTextField  cityInput = new JTextField();
        cityInput.setBounds(79,220,220,22);

        JLabel UFLabel = new JLabel("UF:");
        UFLabel.setBounds(305,219,20,20);
        UFLabel.setHorizontalAlignment(SwingConstants.CENTER);
        UFLabel.setVerticalAlignment(SwingConstants.CENTER);
        UFLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JComboBox UFCB= new JComboBox();
        //TODO não permitiri selecionar a opção "Selecionar" no combobox UF
        UFCB.setModel(new DefaultComboBoxModel<>(new String[] { "Selecionar","SP", "RJ"}));
        UFCB.setBounds(335,220,75,22);

        JLabel phoneLabel = new JLabel("Telefone:");
        phoneLabel.setBounds(426,219,54,20);
        phoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
        phoneLabel.setVerticalAlignment(SwingConstants.CENTER);
        phoneLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JTextField  phoneInput = new JTextField();
        phoneInput.setBounds(489,220,101,22);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setBounds(12,259,40,20);
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setVerticalAlignment(SwingConstants.CENTER);
        passwordLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JPasswordField  passwordInput = new JPasswordField();
        passwordInput.setBounds(78,260,192,22);

        JLabel cPasswordLabel = new JLabel("Confirmar senha:");
        cPasswordLabel.setBounds(276,259,104,20);
        cPasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cPasswordLabel.setVerticalAlignment(SwingConstants.CENTER);
        cPasswordLabel.setFont(new Font("Segoe UI Historic", 0, 14));
        JPasswordField  cPasswordInput = new JPasswordField();
        cPasswordInput.setBounds(390,260,200,22);

        JButton backButton = new JButton("Voltar");
        backButton.setBounds(188,428,109,31);
        backButton.setFont(new Font("Segoe UI Historic", 0, 16));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerContainer.setVisible(false);

                setContentPane(initComponents());

            }
        });

        JButton registerButton = new JButton("Cadastrar");
        registerButton.setBounds(303,428,109,31);
        registerButton.setFont(new Font("Segoe UI Historic", 1, 16));
        registerButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    if(UFCB.getSelectedItem().equals("Selecionar") || typeCB.getSelectedItem().equals("Selecionar")){
                        JOptionPane.showMessageDialog(null,"Dados inválidos, verifique se os campos estão preenchidos corretamente", "WARNING",JOptionPane.WARNING_MESSAGE);

                    }else {
                        //TODO Testar objetos
                        Institution institution = new Institution();
                        Address address = new Address();
                        InstitutionController registerInstitution = new InstitutionController();

                        //Adiciona as informações no objeto address
                        address.setZipCode((String) zipCodeInput.getText());
                        address.setAddress((String) addressInput.getText());
                        address.setNumber(Integer.parseInt((String) numberInput.getText()));
                        address.setComplement((String) complementInput.getText());
                        address.setNeighborhood((String) neighborhoodInput.getText());
                        address.setUf((String) UFCB.getSelectedItem());
                        address.setCity((String) cityInput.getText());
                        address.setId(registerInstitution.addAddress(address));

                        //Adiciona as informações no objeto institution
                        institution.setCnpj((String) cpnjInput.getText());
                        institution.setNome((String) nameInput.getText());
                        institution.setPassword((String) passwordInput.getText());
                        institution.setType((String) typeCB.getSelectedItem());
                        institution.setContactNumber((String) phoneInput.getText());
                        institution.setAddress(address);
                        registerInstitution.register(institution);
                        JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
                        registerContainer.setVisible(false);
                        setContentPane(initComponents());
                    }
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Dados inválidos, verifique se os campos estão preenchidos corretamente", "WARNING",JOptionPane.WARNING_MESSAGE);
                    System.out.println(ex);
                }
            }
        });

        registerContainer.add(headerLabel);
        registerContainer.add(nameLabel);
        registerContainer.add(nameInput );
        registerContainer.add(cnpjLabel);
        registerContainer.add(cpnjInput);
        registerContainer.add(typeLabel);
        registerContainer.add(typeCB);
        registerContainer.add(addressLabel);
        registerContainer.add(addressInput);
        registerContainer.add(zipCodeLabel);
        registerContainer.add(zipCodeInput);
        registerContainer.add(neighborhoodLabel);
        registerContainer.add(neighborhoodInput);
        registerContainer.add(numberLabel);
        registerContainer.add(numberInput);
        registerContainer.add(complementLabel);
        registerContainer.add(complementInput);
        registerContainer.add(cityLabel);
        registerContainer.add(cityInput);
        registerContainer.add(UFLabel);
        registerContainer.add(UFCB);
        registerContainer.add(phoneLabel);
        registerContainer.add(phoneInput);
        registerContainer.add(passwordLabel);
        registerContainer.add(passwordInput);
        registerContainer.add(cPasswordLabel );
        registerContainer.add(cPasswordInput);
        registerContainer.add(backButton);
        registerContainer.add(registerButton);

        setVisible(true);
        return registerContainer;
    }
    private Container initMenu(){
        menuContainer = new JPanel();
        menuContainer.setLayout(null);


        headerLabel = new JLabel("Bem - vindo");
        headerLabel.setBounds(178,10,251,32);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI Historic", 1, 24));

        //Fornecedor
        JButton provider = new JButton("Fornecedor");
        provider.setBounds(84,166,167,61);
        provider.setFont(new Font("Segoe UI Historic", 0, 16));


        //Solicitante
        JButton requester = new JButton("Solicitante");
        requester.setBounds(351,166,167,61);
        requester.setFont(new Font("Segoe UI Historic", 0, 16));

        //Cadastrar Leito
        JButton registerBed = new JButton("Cadastrar Leito");
        registerBed.setBounds(84,287,167,61);
        registerBed.setFont(new Font("Segoe UI Historic", 0, 16));

        //Solicitar Leito
        JButton requestBed = new JButton("Solicitar Leito");
        requestBed.setBounds(351,287,167,61);
        requestBed.setFont(new Font("Segoe UI Historic", 0, 16));

        //Sair
        JButton exit = new JButton("Sair");
        exit.setBounds(10, 427,78, 30 );
        exit.setFont(new Font("Segoe UI Historic", 0, 16));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int result = JOptionPane.showConfirmDialog(null,
                        "Deseja realmente sair?",null, JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION) {
                    menuContainer.setVisible(false);
                    setContentPane(initComponents());
                }
            }
        });

        //Adicionando no container:
        menuContainer.add(headerLabel);
        menuContainer.add(provider);
        menuContainer.add(requester);
        menuContainer.add(registerBed);
        menuContainer.add(requestBed);
        menuContainer.add(exit);

        setVisible(true);


        return menuContainer;
    }


}
