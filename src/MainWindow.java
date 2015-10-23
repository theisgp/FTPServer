import com.sun.corba.se.spi.activation.Server;
import org.apache.ftpserver.ftplet.FtpException;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private int width;
    private int height;
    private ServerManager serverManager;

    public MainWindow() {
        super("Gangsta FTPServer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupContent();
        width = 800;
        height = 600;

        start();
    }

    private void start() {
        setJMenuBar(new FTPMenuBar());
        pack();
        setSize(width, height);
        setVisible(true);
    }

    private void setupContent() {
        Container contentPane = getContentPane();
        JButton startDefaultServer = new JButton("Start Default Server");

        startDefaultServer.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        serverManager = new ServerManager();
                        try {
                            serverManager.startServer();
                            JOptionPane.showMessageDialog(null, "Service started. \n" + serverManager.getServerInformation(), "Succes", JOptionPane.PLAIN_MESSAGE);
                        } catch (FtpException e) {
                            JOptionPane.showMessageDialog(null, "Service failed to start. \n" + e.getMessage(), "Failure", JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace();
                        }
                    }
                }
        );

        JPanel northPanel = new JPanel(new BorderLayout());
//        JPanel eastPanel = new JPanel(new BorderLayout());
//        JPanel southPanel = new JPanel(new BorderLayout());
//        JPanel westPanel = new JPanel(new BorderLayout());

        northPanel.add(startDefaultServer, BorderLayout.NORTH);
//        startDefaultServer.setSize(1,1);
        contentPane.add(northPanel);
//        contentPane.add(eastPanel);
//        contentPane.add(southPanel);
//        contentPane.add(westPanel);
    }


}
