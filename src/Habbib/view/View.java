package Habbib.view;

import Habbib.controller.BedController;
import Habbib.controller.InstitutionController;
import Habbib.controller.RequisitionController;
import Habbib.controller.SessionController;
import Habbib.dao.InstitutionDAO;
import Habbib.model.Address;
import Habbib.model.Bed;
import Habbib.model.Institution;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class View extends BaseView{

    public View() {
        super("Habbib beds");
        super.showWindow(loginContainer(), 620,520);
    }

    private Container loginContainer() {

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
                setContentPane(registerContainer());
            }
        });

        JButton loginButton = super.createButton("Entrar", 219,232,200,20);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    SessionController sessionController = new SessionController();

                    Institution institution = sessionController.login(userInput.getText(), passInput.getText());
                    loginContainer.setVisible(false);
                    setContentPane(menuContainer(institution));
                } catch (Exception ex) {
                    if(userInput.getText().equals("") || passInput.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Seu usuário ou senha está vazio", "WARNING",JOptionPane.WARNING_MESSAGE);

                    } else{
                        JOptionPane.showMessageDialog(null,"Seu usuário ou senha está incorreto.", "WARNING",JOptionPane.WARNING_MESSAGE);
                    }

                }
            }
        });

        loginContainer.add(loginButton);
        loginContainer.add(registerButton);

        return loginContainer;
    }

    private Container registerContainer() {
        JPanel registerContainer = new JPanel();
        registerContainer.setLayout(null);

        registerContainer.add(super.createHeaderLabel("Cadastre sua instituição", 169,10, 251, 32));
        registerContainer.add(super.createInputLabel("Nome:",10,59,40,20));
        registerContainer.add(super.createInputLabel("CNPJ:",10,99,40,20));
        registerContainer.add(super.createInputLabel("Tipo:",415,99,60,20));
        registerContainer.add(super.createInputLabel("Endereco:",10,139,59,20));
        registerContainer.add(super.createInputLabel("CEP:",10,179,27,20));
        registerContainer.add(super.createInputLabel("Bairro:",212,179,39,20));
        registerContainer.add(super.createInputLabel("Número:",415,139,59,20));
        registerContainer.add(super.createInputLabel("Compl:",415,179,100,20));
        registerContainer.add(super.createInputLabel("Cidade:",10,219,45,20));
        registerContainer.add(super.createInputLabel("UF:",300,219,20,20));
        registerContainer.add(super.createInputLabel("Telefone:",415,219,59,20));
        registerContainer.add(super.createInputLabel("Senha:",10,259,40,20));
        registerContainer.add(super.createInputLabel("Confirmar senha:",276,259,104,20));
        registerContainer.add(super.createInputLabel("Voltar",86,428,109,311));
        registerContainer.add(super.createInputLabel("Confirmar",106,428,109,311));

        JTextField nameInput = super.createTextField(79,60,512,22);
        JTextField cnpjInput = super.createTextField(79,100,330,22);
        JTextField addressInput = super.createTextField(79,139,330,22);
        JTextField zipCodeInput = super.createTextField(80,180,130,22);
        JTextField neighborhoodInput = super.createTextField(254,180,155,22);
        JTextField numberInput = super.createTextField(489,139,101,22);
        JTextField complementInput = super.createTextField(489,180,101,22);
        JTextField cityInput = super.createTextField(79,220,220,22);
        JTextField phoneInput = super.createTextField(489,220,101,22);
        JPasswordField passwordInput = super.createPasswordField(78,260,192,22);
        JPasswordField cPasswordInput = super.createPasswordField(390,260,200,22);
        JButton backButton = super.createButton("Voltar",82,428,109,22);
        JButton registerButton = super.createButton("Cadastra",200,428,109,22);
        JComboBox typeCB = super.createComboBox(new String[]{"Selecionar","Privado","Publico"},490,100,100,20);
        JComboBox uFCB = super.createComboBox(new String[]{"Selecionar","AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"},322,220,86,22);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    if(uFCB.getSelectedItem().equals("Selecionar") || typeCB.getSelectedItem().equals("Selecionar")) {
                        JOptionPane.showMessageDialog(null, "Dados inválidos, verifique se os campos estão preenchidos corretamente", "WARNING", JOptionPane.WARNING_MESSAGE);
                    } else if (!(cPasswordInput).getText().equals(passwordInput.getText())) {
                        JOptionPane.showMessageDialog(null, "As senhas digítadas não conferem !", "WARNING", JOptionPane.WARNING_MESSAGE);
                    } else {

                        Institution institution = new Institution();
                        Address address = new Address();
                        InstitutionController registerInstitution = new InstitutionController();

                        address.setZipCode(zipCodeInput.getText());
                        address.setAddress(addressInput.getText());
                        address.setNumber(Integer.parseInt(numberInput.getText()));
                        address.setComplement(complementInput.getText());
                        address.setNeighborhood(neighborhoodInput.getText());
                        address.setUf(uFCB.getSelectedItem().toString());
                        address.setCity(cityInput.getText());
                        address.setId(registerInstitution.registerAddress(address).getId());

                        institution.setCnpj(cnpjInput.getText());
                        institution.setName(nameInput.getText());
                        institution.setPassword(passwordInput.getText());
                        institution.setType(typeCB.getSelectedItem().toString());
                        institution.setContactNumber(phoneInput.getText());
                        institution.setAddress(address);
                        registerInstitution.registerInstitution(institution);

                        JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
                        registerContainer.setVisible(false);
                        setContentPane(loginContainer());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Dados inválidos, verifique se os campos estão preenchidos corretamente", "WARNING",JOptionPane.WARNING_MESSAGE);
                    System.out.println(ex.getMessage());
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int result = JOptionPane.showConfirmDialog(null,
                        "Deseja realmente voltar?",null, JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION) {
                    registerContainer.setVisible(false);
                    setContentPane(loginContainer());
                }
            }
        });

        registerContainer.add(nameInput);
        registerContainer.add(cnpjInput);
        registerContainer.add(typeCB);
        registerContainer.add(addressInput);
        registerContainer.add(zipCodeInput);
        registerContainer.add(neighborhoodInput);
        registerContainer.add(numberInput);
        registerContainer.add(complementInput);
        registerContainer.add(cityInput);
        registerContainer.add(uFCB);
        registerContainer.add(phoneInput);
        registerContainer.add(passwordInput);
        registerContainer.add(cPasswordInput);
        registerContainer.add(backButton);
        registerContainer.add(registerButton);

        setVisible(true);
        return registerContainer;

    }

    private Container menuContainer(Institution institution) {

        JPanel menuContainer = new JPanel();
        menuContainer.setLayout(null);

        menuContainer.add(super.createHeaderLabel("Bem - vindo",178,10,251,32));

        JButton provider = super.createDashboardButton("Fornecedor",84,166,167,61);
        provider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuContainer.setVisible(false);
                setContentPane(providerContainer(institution));
            }
        });

        JButton requester = super.createDashboardButton("Solicitante",351,166,167,61);
        requester.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuContainer.setVisible(false);
                setContentPane(requesterContainer(institution));
            }
        });

        JButton registerBed = super.createDashboardButton("Cadastrar Leito",84,287,167,61);
        registerBed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuContainer.setVisible(false);
                JFrame frameRegisterBed = new JFrame();
                frameRegisterBed.setSize(500,200);
                frameRegisterBed.setResizable(false);
                frameRegisterBed.setLocationRelativeTo(null);
                frameRegisterBed.setContentPane(registerBedContainer(institution));
                frameRegisterBed.setVisible(true);
                frameRegisterBed.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameRegisterBed.addWindowListener(new WindowListener() {
                    @Override
                    public void windowOpened(WindowEvent e) {

                    }

                    @Override
                    public void windowClosing(WindowEvent e) {

                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                        menuContainer.setVisible(true);
                    }

                    @Override
                    public void windowIconified(WindowEvent e) {

                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {

                    }

                    @Override
                    public void windowActivated(WindowEvent e) {

                    }

                    @Override
                    public void windowDeactivated(WindowEvent e) {

                    }
                });

            }

        });

        JButton requestBed = super.createDashboardButton("Solicitar Leito",351,287,167,61);
        requestBed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuContainer.setVisible(false);
                setContentPane(requestBedContainer(institution));
            }
        });

        JButton exit = super.createButton("Sair",512,427,78,30);
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

        menuContainer.add(provider);
        menuContainer.add(requester);
        menuContainer.add(registerBed);
        menuContainer.add(requestBed);
        menuContainer.add(exit);

        setVisible(true);

        return menuContainer;
    }

    private Container providerContainer(Institution institution) {

        JPanel providerContainer = new JPanel();
        providerContainer.setLayout(null);
        providerContainer.add(super.createHeaderLabel("Fornecedor", 178,10,251,32));
        providerContainer.add(super.createInputLabel("Instituição:",13,52,70,30));
        providerContainer.add(super.createInputLabel(institution.getName(),83,52,70,30));
        providerContainer.add(super.createInputLabel("Status:",13,82,60,30));
        JComboBox statusCB = super.createComboBox(new String[]{"Selecionar","Em análise","Recusado","Aprovado"},10,110,110,30);
        JTextField searchInput = super.createTextField(120,110,393,30);
        JButton searchButton = super.createButton("Consultar",512, 108, 80, 32);

        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };
        RequisitionController rc = new RequisitionController();
        model.addColumn("Instituição");
        model.addColumn("Paciente");
        model.addColumn("Leito");
        model.addColumn("Status");

        JTable providerTable = super.createTable(model);
        providerTable.getColumnModel().getColumn(0).setPreferredWidth(180);
        providerTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        providerTable.getColumnModel().getColumn(2).setPreferredWidth(125);
        providerTable.getColumnModel().getColumn(3).setPreferredWidth(75);
       for(int i = 0; i <10 ; i++ ){
           model.addRow(new Object[]{"NotreDame Intermédica Itaquera", "Roberto Augusto Alvares Cabral", "Baixa-Complexidade","Aprovado"});
           model.addRow(new Object[]{"NotreDame Intermédica Itaquera", "Roberto Augusto Alvares Cabral", "Baixa-Complexidade","Em análise"});
           model.addRow(new Object[]{"NotreDame Intermédica Itaquera", "Roberto Augusto Alvares Cabral", "Baixa-Complexidade","Recusado"});
       }
        providerTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table =(JTable) e.getSource();
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    providerContainer.setVisible(false);
                   String institutionName = providerTable.getValueAt(providerTable.getSelectedRow(), 0).toString();
                   String patientName = providerTable.getValueAt(providerTable.getSelectedRow(), 1).toString();
                   String bed =  providerTable.getValueAt(providerTable.getSelectedRow(), 2).toString();
                   String status = providerTable.getValueAt(providerTable.getSelectedRow(), 3).toString();

                    setContentPane(providerStatus(institution,institutionName,patientName,bed,status));
/*
                    try {
                        InstitutionDAO  ad = new InstitutionDAO();
                        Institution inst = ad.getInstitutionByName(name);
                        String address = inst.getAddress().getAddress();
                        String city  =  inst.getAddress().getCity();
                        String numberAddress = Integer.toString(inst.getAddress().getNumber());
                        setContentPane(initRequestBed(institution, name,type, uf, bed, phone, address, numberAddress , city));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

*/

                }

            }
        });

/*
        try {
            for(Bed beds : rc.searchAvailableBeds()){

                model.addRow(new Object[]{ beds.getInstitution().getName(),beds.getInstitution().getType(),beds.getInstitution().getAddress().getNeighborhood(),beds.getType(),5,beds.getInstitution().getContactNumber()});




            }
        } catch (Exception e) {
            e.printStackTrace();
        }


*/
        JScrollPane scroll = new JScrollPane(providerTable);
        scroll.setBounds(10,150,580,272);





        JButton backButton = super.createButton("Cancelar",510, 427, 80, 30);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //TODO Criar JTable para exibir os resultados
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                providerContainer.setVisible(false);
                setContentPane(menuContainer(institution));
            }
        });
        providerContainer.add(statusCB);
        providerContainer.add(searchInput);
        providerContainer.add(searchButton);
        providerContainer.add(backButton);
        providerContainer.add(scroll);

        return providerContainer;
    }

    private Container providerStatus(Institution institution, String institutionName, String patientName, String bed, String status){
        JPanel providerStatusContainer = new JPanel();
        providerStatusContainer.setLayout(null);


        providerStatusContainer.add(super.createHeaderLabel("Solicitação", 160,10,251,32));

        providerStatusContainer.add(super.createTitleLabel("Instituição solicitante:", 10 ,57, 155,28 ));
        providerStatusContainer.add(super.createTextLabelLeft(institutionName, 10 ,80,250,32 ));
        providerStatusContainer.add(super.createTextLabelLeftBold("Público", 260 ,80,52,25));
       providerStatusContainer.add(super.createTextLabelLeft("Tel.: (11)4002-8922" ,456 ,80,117,20 ));
        providerStatusContainer.add(super.createTextLabelLeft("R.: Domigues Figueredos Anhares da Silva,2506",10 ,110,440,20 ));
        providerStatusContainer.add(super.createTextLabelLeft("Ferraz de vasconcelos,SP",456 ,110,150,20 ));

        providerStatusContainer.add(super.createInputLabel("Paciente:",10,130,140,30));
        providerStatusContainer.add(super.createTextLabelLeft("Leito Solicitado: " + bed, 10 ,160,250,20 ));
        providerStatusContainer.add(super.createTextLabelLeft("Status: " + status, 456 ,160,180,20 ));
        providerStatusContainer.add(super.createTextLabelLeft("Nome: " + patientName, 10 ,185,250,20 ));
        providerStatusContainer.add(super.createTextLabelLeft("Idade: 42", 456 ,185,70,20 ));
        providerStatusContainer.add(super.createTextLabelLeft("CPF: 437091978-55", 10 ,210,250,20 ));
        providerStatusContainer.add(super.createTextLabelLeft("Sexo: Feminino",260,210,100,20 ));
        providerStatusContainer.add(super.createTextLabelLeft("CID: COVID-19", 456 ,210,100,20 ));
        providerStatusContainer.add(super.createTitleLabel("Observações sobre o paciente:", 10 ,235,300 ,28 ));
        JTextArea obsText = super.createJTextArea(10,262,580,160);
        obsText.setText("Paciente tem alergia a xereca");
        obsText.setEditable(false);
        JScrollPane scroll = new JScrollPane(obsText);
        scroll.setBounds(10,262,580,160);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        providerStatusContainer.add(scroll);

        if(status.equals("Aprovado")|| status.equals("Recusado")){

            JButton comeBack = super.createButton("Voltar",330,427,80, 30 );
            comeBack.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    providerStatusContainer.setVisible(false);
                    setContentPane(providerContainer(institution));
                }
            });
            providerStatusContainer.add(comeBack);

            JButton refuse = super.createButton("Recusar",420,427,80, 30 );
            refuse.setEnabled(false);
            providerStatusContainer.add(refuse);

            JButton approved = super.createButton("Aprovar",510,427,80, 30 );
            approved.setEnabled(false);
            providerStatusContainer.add(approved);
        }else{
            JButton comeBack = super.createButton("Voltar",330,427,80, 30 );
            comeBack.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    providerStatusContainer.setVisible(false);
                    setContentPane(providerContainer(institution));
                }
            });
            providerStatusContainer.add(comeBack);

            JButton refuse = super.createButton("Recusar",420,427,80, 30 );
            providerStatusContainer.add(refuse);

            JButton approved = super.createButton("Aprovar",510,427,80, 30 );
            providerStatusContainer.add(approved);
        }


        return  providerStatusContainer;
    }

    private Container requesterContainer(Institution institution) {
        JPanel requesterContainer = new JPanel();
        requesterContainer.setLayout(null);

        requesterContainer.add(super.createHeaderLabel("Solicitante", 178,10,251,32));
        requesterContainer.add(super.createInputLabel("Status:",13,82,60,30));
        JComboBox statusCB = super.createComboBox(new String[]{"Selecionar","Em análise","Recusado","Aprovado"},10,110,110,30);
        JTextField searchInput = super.createTextField(120,110,393,30);
        JButton searchButton = super.createButton("Consultar",512, 108, 80, 32);
        JButton backButton = super.createButton("Cancelar",512, 427, 78, 30);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Criar JTable
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                requesterContainer.setVisible(false);
                setContentPane(menuContainer(institution));
            }
        });

        requesterContainer.add(statusCB);
        requesterContainer.add(searchInput);
        requesterContainer.add(searchButton);
        requesterContainer.add(backButton);

        return requesterContainer;
    }

    private Container registerBedContainer(Institution institution){
        JPanel registerBedContainer = new JPanel();
        registerBedContainer.setLayout(null);

        registerBedContainer.add(super.createHeaderLabel("Cadastrar leito", 80,10,300,32));
        registerBedContainer.add(super.createInputLabel("Tipo:",10,60,100,20));
        registerBedContainer.add(super.createInputLabel("Quantidade:",10,100,100,20));
        JComboBox typeInput = super.createComboBox(new String[]{"Selecionar","UTI", "Semi-intensivo","Baixa Complexidade"},170,60,120,20);
        JTextField amountInput = super.createTextField(170,100,25,22);
        JButton registerButton = super.createButton("Cadastrar",400,120,78, 30 );
        JButton backButton = super.createButton("Voltar",315,120 , 78, 30);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    Bed bed = new Bed();
                    BedController bedController = new BedController();

                    bed.setType(typeInput.getSelectedItem().toString());
                    bed.setInstitution(institution);

                    bedController.registerBeds(bed,Integer.parseInt(amountInput.getText()));

                    JOptionPane.showMessageDialog(null,"Leito(s) cadastrado(s) com sucesso !", "WARNING",JOptionPane.WARNING_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Erro ao cadastrar leito(s).", "WARNING",JOptionPane.WARNING_MESSAGE);
                    System.out.println(ex.getMessage());
                }

            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                registerBedContainer.setVisible(false);
                setContentPane(menuContainer(institution));
            }

        });

        registerBedContainer.add(typeInput);
        registerBedContainer.add(amountInput);
        registerBedContainer.add(registerButton);
        registerBedContainer.add(backButton);

        return registerBedContainer;
    }

    private Container requestBedContainer(Institution institution){
        JPanel requestBedContainer= new JPanel();
        requestBedContainer.setLayout(null);



        requestBedContainer.add(super.createHeaderLabel("Solicitar leito", 160,10,251,32));

        requestBedContainer.add(super.createInputLabel("Tipo:",10,37,70,30));

        requestBedContainer.add(super.createInputLabel("UF:",157,37,70,30));

        requestBedContainer.add(super.createInputLabel("Leito:",372,37,70,30));

        JComboBox status = super.createComboBox(new String[]{"Privado","Público"},10,62,89,26);

        JComboBox neighborhood = super.createComboBox(new String[]{"Selecionar","AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"},157,62,86,22);

        JComboBox type = super.createComboBox(new String[]{"UTI","Semi-intensivo","Baixa complexidade"},372,62,98,26);


        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };
        BedController bc = new BedController();
        model.addColumn("Instituição");
        model.addColumn("Tipo");
        model.addColumn("UF");
        model.addColumn("Leito");
        model.addColumn("QTD");
        model.addColumn("Telefone");

        JTable requestBedTable = super.createTable(model);
        requestBedTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        requestBedTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        requestBedTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        requestBedTable.getColumnModel().getColumn(3).setPreferredWidth(95);
        requestBedTable.getColumnModel().getColumn(4).setPreferredWidth(30);
        requestBedTable.getColumnModel().getColumn(5).setPreferredWidth(100);


        requestBedTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table =(JTable) e.getSource();
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {

                    requestBedContainer.setVisible(false);

                    String name = requestBedTable.getValueAt(requestBedTable.getSelectedRow(), 0).toString();
                    String type = requestBedTable.getValueAt(requestBedTable.getSelectedRow(), 1).toString();
                    String uf =  requestBedTable.getValueAt(requestBedTable.getSelectedRow(), 2).toString();
                    String bed = requestBedTable.getValueAt(requestBedTable.getSelectedRow(), 3).toString();
                    String phone = requestBedTable.getValueAt(requestBedTable.getSelectedRow(), 5).toString();


                    try {
                        InstitutionDAO  ad = new InstitutionDAO();
                        Institution inst = ad.getInstitutionByName(name);
                        String address = inst.getAddress().getAddress();
                        String city  =  inst.getAddress().getCity();
                        String numberAddress = Integer.toString(inst.getAddress().getNumber());
                        setContentPane(initRequestBed(institution, name,type, uf, bed, phone, address, numberAddress , city));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }




                }

            }
        });


        try {
            for(Bed beds : bc.searchAvailableBeds()){

                model.addRow(new Object[]{ beds.getInstitution().getName(),beds.getInstitution().getType(),beds.getInstitution().getAddress().getNeighborhood(),beds.getType(),5,beds.getInstitution().getContactNumber()});




            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        JScrollPane scroll = new JScrollPane(requestBedTable);
        scroll.setBounds(10,110,580,300);






        JButton consult = super.createButton("Consultar",512, 62, 78, 26);
        consult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /*providerContainer.setVisible(false);
                setContentPane(initMenu(institution));*/
            }
        });




        JButton exit = super.createButton("Cancelar",512, 427, 78, 30);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                requestBedContainer.setVisible(false);
                setContentPane(menuContainer(institution));
            }
        });
        requestBedContainer.add(status);
        requestBedContainer.add(neighborhood);
        requestBedContainer.add(type);
        requestBedContainer.add(consult);
        requestBedContainer.add(exit);
        requestBedContainer.add(scroll);

        return  requestBedContainer;
    }

    private Container initRequestBed(Institution institution, String name, String type, String uf,String bed, String phone, String address,String numberAddress ,String city){

        JPanel requestContainer = new JPanel();
        requestContainer.setLayout(null);
        requestContainer.add(super.createHeaderLabel("Solicitação", 251,10,103,27));
        requestContainer.add(super.createTitleLabel("Instituição solicitante:", 10 ,57, 155,28 ));
        requestContainer.add(super.createTextLabelLeft(name, 10 ,80,250,32 ));
        requestContainer.add(super.createTextLabelLeftBold(type, 260 ,80,52,25));
        requestContainer.add(super.createTextLabelLeft("Tel.:"+ phone ,458 ,80,117,20 ));
        requestContainer.add(super.createTextLabelLeft("R.:"+address+","+numberAddress,10 ,110,440,20 ));
        requestContainer.add(super.createTextLabelLeft(city+","+uf,458 ,110,150,20 ));


        requestContainer.add(super.createTitleLabel("Paciente:", 10 ,135, 80,28 ));
        requestContainer.add(super.createTextLabelLeft("Leito solicitado:",10 ,162,85,20 ));
        requestContainer.add(super.createTextLabelLeftBold(bed,95,162,200,20 ));

        requestContainer.add(super.createTextLabelLeft("Nome:",10,192,40,20 ));
        JTextField firstNameInput = createTextField(55,192,224,20);
        requestContainer.add(firstNameInput);

        requestContainer.add(super.createTextLabelLeft("Sobrenome:",283,192,64,20 ));
        JTextField lastNameInput = super.createTextField(368,192,224,20);
        requestContainer.add(lastNameInput);

        requestContainer.add(super.createTextLabelLeft("CPF:",10,217,40,20 ));
        JTextField cpfInput = super.createTextField(55,217,224,20);
        requestContainer.add(cpfInput);
        requestContainer.add(super.createTextLabelLeft("Data de Nasc.:",283,217,75,20 ));
        JTextField dob = super.createTextField(368,217,75,20);
        requestContainer.add(dob);
        requestContainer.add(super.createTextLabelLeft("Sexo:",447,217,41,20 ));
        JComboBox generCB = super.createComboBox(new String[]{"Selecionar","Masculino","Feminino"},488,217,104,20);
        requestContainer.add(generCB);

        requestContainer.add(super.createTextLabelLeft("CID:",10,242,40,20 ));
        JTextField cidInput = super.createTextField(55,242,224,20);
        requestContainer.add(cidInput);

        requestContainer.add(super.createTitleLabel("Obs.:", 10 ,267, 50,28 ));
        JTextArea obsText = super.createJTextArea(10,295,490,150);
        JScrollPane scroll = new JScrollPane(obsText);
        scroll.setBounds(10,295,580,140);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        requestContainer.add(scroll);

        JButton comeBack = super.createButton("Voltar",333,444,78, 30 );
         comeBack.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 requestContainer.setVisible(false);
                 setContentPane(requestBedContainer(institution));
             }
         });
        requestContainer.add(comeBack);

        JButton cancel = super.createButton("Cancelar",421,444,78, 30 );
        requestContainer.add(cancel);

        JButton request = super.createButton("Solicitar",509,444,78, 30 );
        requestContainer.add(request);


        return  requestContainer;
    }
}