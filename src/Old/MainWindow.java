/*
import org.apache.ftpserver.ftplet.FtpException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainWindow extends JFrame implements ListSelectionListener {
    private int width;
    private int height;
    private Old.Controller controller;

    private JList userList;
    private JLabel usernameLabel;
    private JLabel directoryLabel;

    public MainWindow(Old.Controller controller) {
        super("Gangsta FTPServer");
        this.controller = controller;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupContent();
        width = 800;
        height = 600;

        start();
    }

    private void start() {
        setJMenuBar(new Old.FTPMenuBar());
        pack();
        setSize(width, height);
        setVisible(true);
    }

    private void setupContent() {
        Container contentPane = getContentPane();
        JPanel panel = new JPanel(new BorderLayout());
        contentPane.add(panel);
//        TODO fix leftPanel layout
        JPanel leftPanel = new JPanel(new FlowLayout());
        JPanel middPanel = new JPanel(new BorderLayout());
        JPanel rightPanel = new JPanel(new FlowLayout());

        JPanel middLeftPanel = new JPanel(new FlowLayout());
        JPanel middRightPanel = new JPanel(new FlowLayout());


        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(middPanel, BorderLayout.CENTER);
        panel.add(rightPanel, BorderLayout.EAST);

        leftPanel.setBorder(BorderFactory.createTitledBorder("Users"));
        middLeftPanel.setBorder(BorderFactory.createTitledBorder("Config"));
        middRightPanel.setBorder(BorderFactory.createTitledBorder("Server State"));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Misc"));

        middPanel.add(middLeftPanel,BorderLayout.WEST);
        middPanel.add(middRightPanel,BorderLayout.CENTER);


        JButton startDefaultServer = new JButton("Start Default Server");

        startDefaultServer.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        try {
                            controller.getServerManager().startServer();
                            JOptionPane.showMessageDialog(null, "Service started. \n" + controller.getServerManager().getServerInformation(), "Succes", JOptionPane.PLAIN_MESSAGE);
                        } catch (FtpException e) {
                            JOptionPane.showMessageDialog(null, "Service failed to start. \n" + e.getMessage(), "Failure", JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace();
                        }
                    }
                }
        );
        rightPanel.add(startDefaultServer);
        middLeftPanel.add(new JLabel("List of current users"));
        String[] stringsOfDoom = {"Jannik","Theis","Asbjørn"};


        ArrayList<MyUser> testArray = controller.getServerManager().getMasterUserManager().getMyUsers();

        DefaultListModel testModel = new DefaultListModel();
        controller.getServerManager().getMasterUserManager().createBaseUser("Jannik", "1234", "/tmp/test");
        controller.getServerManager().getMasterUserManager().createBaseUser("Theis", "1234", "/tmp/test");

        for(MyUser user: testArray){
            testModel.addElement(user);
        }
        userList = new JList(testModel);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userList.setVisibleRowCount(10);
        userList.addListSelectionListener(this);
        JScrollPane listScrollPane = new JScrollPane(userList);
        leftPanel.add(listScrollPane);
//        leftPanel.add()
        usernameLabel = new JLabel();
        directoryLabel = new JLabel();

        leftPanel.add(usernameLabel);
        leftPanel.add(directoryLabel);


    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        int listIndex = userList.getSelectedIndex();
        usernameLabel.setText(controller.getServerManager().getMasterUserManager().getMyUser(listIndex).getName());
        directoryLabel.setText(controller.getServerManager().getMasterUserManager().getMyUser(listIndex).getHomeDirectory());
    }
}*/
