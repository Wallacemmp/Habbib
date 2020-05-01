package Habbib.view;

import Habbib.controller.InstitutionController;
import Habbib.controller.SessionController;
import Habbib.model.Address;
import Habbib.model.Institution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends BaseView{

    public View() {
        super("Habbib beds");
        super.showWindow(loginContainer(), 620,520);
    }

    private Container loginContainer(){
        JPanel loginContainer = new JPanel();
        loginContainer.setLayout(null);

        JTextField userInput = super.createTextField(219,180,200,20);
        JPasswordField passInput = super.createPasswordField(219,206,200,20);

        loginContainer.add(super.createHeaderLabel("Habbib Beds", 230,50,140,40));
        loginContainer.add(super.createTitleLabel("Login", 280,125,50,30));
        loginContainer.add(super.createInputLabel("Usuário:", 155,180,60,20));
        loginContainer.add(super.createInputLabel("Senha:", 155,206,60,20));
        loginContainer.add(super.createTextLabel("Não tem uma conta?",219,400,120,20 ));

        loginContainer.add(userInput);
        loginContainer.add(passInput);
        loginContainer.setVisible(true);

        JButton registerButton = super.createButton("Crie uma", 340,400,81,20);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginContainer.setVisible(false);
                setContentPane(initRegister());
            }
        });


        JButton loginButton = super.createButton("Entrar", 219,232,200,20);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    SessionController sessionController = new SessionController();

                    Institution institution = sessionController.login(userInput.getText(), passInput.getText());
                    loginContainer.setVisible(false);
                    setContentPane(initMenu(institution));
                }
                catch (Exception ex)
                {
                    if(userInput.getText().equals("") || passInput.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Seu usuário ou senha está vazio", "WARNING",JOptionPane.WARNING_MESSAGE);

                    }else{
                        JOptionPane.showMessageDialog(null,"Seu usuário ou senha está incorreto.", "WARNING",JOptionPane.WARNING_MESSAGE);
                    }

                }
            }
        });

        loginContainer.add(loginButton);
        loginContainer.add(registerButton);
        return loginContainer;
    }

    private Container initRegister(){
        JPanel registerContainer = new JPanel();
        registerContainer.setLayout(null);



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
                setContentPane(loginContainer());

            }
        });

        JComboBox typeCB = super.createComboBox(new String[] { "Selecionar","Privado", "Público"}, 448,100,142,22);

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
                        registerInstitution.Register(institution);
                        JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
                        registerContainer.setVisible(false);
                        setContentPane(loginContainer());
                    }
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Dados inválidos, verifique se os campos estão preenchidos corretamente", "WARNING",JOptionPane.WARNING_MESSAGE);
                    System.out.println(ex);
                }
            }
        });

        registerContainer.add(super.createHeaderLabel("Cadastre sua instituição", 169,10, 251, 32));
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

    private Container initMenu(Institution institution){
        JPanel menuContainer = new JPanel();
        menuContainer.setLayout(null);

        //Bem - Vindo
        menuContainer.add(super.createHeaderLabel("Bem - vindo",178,10,251,32));

        //Fornecedor
        JButton provider = super.createButton2("Fornecedor",84,166,167,61);
        provider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuContainer.setVisible(false);
                setContentPane(initProvider(institution));
            }
        });

        //Solicitante
        JButton requester = super.createButton2("Solicitante",351,166,167,61);
        requester.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuContainer.setVisible(false);
                setContentPane(initRequester(institution));
            }
        });

        //Cadastrar Leito
        JButton registerBed = super.createButton2("Cadastrar Leito",84,287,167,61);
        registerBed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuContainer.setVisible(false);
                setContentPane(initRegisterBed(institution));
            }
        });

        //Solicitar Leito
        JButton requestBed = super.createButton2("Solicitar",351,287,167,61);
        requestBed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuContainer.setVisible(false);
                setContentPane(initRequestBed(institution));
            }
        });

        //Sair
        JButton exit = super.createButton("Sair",10,427,78,30);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int result = JOptionPane.showConfirmDialog(null,
                        "Deseja realmente sair?",null, JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION) {
                    menuContainer.setVisible(false);
                    setContentPane(loginContainer());
                }
            }
        });

        //Adicionando no container:
        menuContainer.add(provider);
        menuContainer.add(requester);
        menuContainer.add(registerBed);
        menuContainer.add(requestBed);
        menuContainer.add(exit);

        setVisible(true);


        return menuContainer;
    }

    //Tela de Fornecedor
    private Container initProvider(Institution institution){
        JPanel providerContainer = new JPanel();
        providerContainer.setLayout(null);

        //Tela de Fornecedor
        providerContainer.add(super.createHeaderLabel("Tela de fornecedor", 178,10,251,32));

        //Instituição
        providerContainer.add(super.createInputLabel("Instituição:",10,52,70,30));

        //Hospital1
        //TODO: (Puxar a instituição que está logado conforme Habbib_Latest 5/23)

        //Status
        providerContainer.add(super.createInputLabelLeft("Status:",10,82,60,30));

        //Pendente
        JComboBox pending = super.createComboBox(new String[]{"Pendente","Recusado","Aprovado"},10,122,89,26);

        //Hospital X
        // TODO:(Realizar busca)
        JTextField userInput = super.createTextField(109,122,393,30);

        //Consultar
        // TODO: (Realizar busca)
        JButton consult = super.createButton("Consultar",512, 122, 78, 30);
        consult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /*providerContainer.setVisible(false);
                setContentPane(initMenu(institution));*/
            }
        });

        //Sair
        JButton exit = super.createButton("Sair",512, 427, 78, 30);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                providerContainer.setVisible(false);
                setContentPane(initMenu(institution));
            }
        });

        //Dashboard - Fornecedor
        providerContainer.add(pending);
        providerContainer.add(userInput);
        providerContainer.add(consult);
        providerContainer.add(exit);

        return providerContainer;
    }//Final de Fornecedor


    //Tela Solicitante:
    private Container initRequester(Institution institution){
        JPanel requesterContainer = new JPanel();
        requesterContainer.setLayout(null);

        //Solicitante
        requesterContainer.add(super.createHeaderLabel("Tela do solicitante", 178,10,251,32));

        //Status
        requesterContainer.add(super.createInputLabelLeft("Status:",10,82,60,30));

        //Pendente
        JComboBox pending = super.createComboBox(new String[]{"Todos","Recusado","Aprovado"},10,122,89,26);

        //Hospital X
        // TODO:(Realizar busca)
        JTextField userInput = super.createTextField(109,122,393,30);

        //Consultar
        // TODO: (Realizar busca)
        JButton consult = super.createButton("Consultar",512, 122, 78, 30);
        consult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /*providerContainer.setVisible(false);
                setContentPane(initMenu(institution));*/
            }
        });

        //Sair
        JButton exit = super.createButton("Sair",512, 427, 78, 30);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                requesterContainer.setVisible(false);
                setContentPane(initMenu(institution));
            }
        });

        //Dashboard - Solicitante:
        requesterContainer.add(userInput);
        requesterContainer.add(pending);
        requesterContainer.add(consult);
        requesterContainer.add(exit);

        return requesterContainer;
    }//final de Solicitante


    //Tela: Cadastrar Leito
    private Container initRegisterBed(Institution institution){
        JPanel registerBedContainer= new JPanel();
        registerBedContainer.setLayout(null);

        registerBedContainer.add(super.createHeaderLabel("Tela do cadastrar leito", 150,10,300,32));


        JButton exit = new JButton("Sair");
        exit.setBounds(10, 427,78, 30 );
        exit.setFont(new Font("Segoe UI Historic", 0, 16));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                registerBedContainer.setVisible(false);
                setContentPane(initMenu(institution));
            }
        });


        registerBedContainer.add(exit);

        return registerBedContainer;
    }//Final Cadastrar Leito

    //Solicitar Leito
    private Container initRequestBed(Institution institution){
        JPanel requestBedContainer= new JPanel();
        requestBedContainer.setLayout(null);

        //Solicitar Leito
        requestBedContainer.add(super.createHeaderLabel("Solicitar leito", 160,10,251,32));

        //Tipo:
        requestBedContainer.add(super.createInputLabel("Tipo:",10,52,70,30));

        //Bairro
        requestBedContainer.add(super.createInputLabel("Bairro:",157,52,70,30));

        //Leito
        requestBedContainer.add(super.createInputLabel("Leito:",372,52,70,30));

        //Privado
        JComboBox status = super.createComboBox(new String[]{"Privado","Público"},10,92,89,26);

        //Mooca
        JComboBox neighborhood = super.createComboBox(new String[]{"Mooca","Tatuapé","..."},157,92,89,26);

        //UTI
        JComboBox type = super.createComboBox(new String[]{"UTI","Semi-intensivo","Baixa complexidade"},372,92,89,26);

        //Consultar
        // TODO: (Realizar busca)
        JButton consult = super.createButton("Consultar",512, 92, 78, 30);
        consult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /*providerContainer.setVisible(false);
                setContentPane(initMenu(institution));*/
            }
        });

        //Hospital X
        //Hospital X
        // TODO:(Realizar busca)
        JTextField userInput = super.createTextField(10,139,580,30);


        JButton exit = super.createButton("Sair",512, 427, 78, 30);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                requestBedContainer.setVisible(false);
                setContentPane(initMenu(institution));
            }
        });

        requestBedContainer.add(status);
        requestBedContainer.add(neighborhood);
        requestBedContainer.add(type);
        requestBedContainer.add(consult);
        requestBedContainer.add(userInput);
        requestBedContainer.add(exit);

        return  requestBedContainer;
    }//Final Solicitar leito

}
