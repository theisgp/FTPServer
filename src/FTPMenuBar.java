import com.sun.xml.internal.bind.v2.TODO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MenuBar for the FTP application
 */
public class FTPMenuBar extends JMenuBar {

    public FTPMenuBar() {
        createFileMenu();
        createHelpMenu();
    }

    private void createFileMenu() {
        JMenu fileMenu = new JMenu("File");

//        Quit - quits on click
        JMenuItem quitItem = new JMenuItem("Quit");
        fileMenu.add(quitItem);
        quitItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                            System.exit(0);
                    }
                });


        add(fileMenu);
    }

    private void createHelpMenu() {
        JMenu helpMenu = new JMenu("Help");

//        About - shows information about program
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);
        aboutItem.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        JOptionPane.showMessageDialog(null, "The most gangsta FTPServer ever seen! \n Created by Theis " +
                                        "and Jannik", "About", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
        add(helpMenu);
    }
}