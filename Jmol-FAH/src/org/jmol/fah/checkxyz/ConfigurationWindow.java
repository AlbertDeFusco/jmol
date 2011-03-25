/* $RCSfile$
 * $Author$
 * $Date$
 * $Revision$
 *
 * Copyright (C) 2007  The Jmol Development Team
 *
 * Contact: jmol-developers@lists.sf.net
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 *  02110-1301, USA.
 */

package org.jmol.fah.checkxyz;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;


/**
 * Configuration Window.
 */
public class ConfigurationWindow extends JFrame implements ActionListener {

  private Configuration configuration = null;
  private ActionListener actionValidate = null;

  private JTextField textUser = null;
  private JTextField textMailServer = null;
  private JFormattedTextField textMailPort = null;
  private JFormattedTextField textMailSslPort = null;
  private JCheckBox chkUseSsl = null;
  private JTextField textUserMail = null;
  private JTextField textUserLogin = null;
  private JTextField textUserPassword = null;
  private JTextField textSaveDir = null;
  private JCheckBox chkLoop = null;
  private JFormattedTextField textBasicInterval = null;
  private JFormattedTextField textThreshold = null;
  private JFormattedTextField textSpecialInterval = null;
  private JList listDirectories = null;
  private DefaultListModel listModel = null;
  private JButton buttonAddDirectories = null;
  private JButton buttonRemoveDirectories = null;
  private JButton buttonTestMail = null;

  private static final String actionAddDirectory = "AddDirectory";
  private static final String actionRemoveDirectory = "RemoveDirectory";
  private static final String actionOk = "Ok";
  private static final String actionCancel = "Cancel";
  private static final String actionTestMail = "TestMail";

  /**
   * Constructor.
   * 
   * @param config Configuration.
   * @param action What to do when OK is pressed.
   */
  public ConfigurationWindow(Configuration config, ActionListener action) {
    super("Current.xyz files for Jmol");
    configuration = config;
    actionValidate = action;
    createWindowContent();
  }

  /**
   * Create the window components.
   */
  private void createWindowContent() {

    // Various settings
    setDefaultCloseOperation(HIDE_ON_CLOSE);
    setTitle("Current.xyz files for Jmol");

    // Layout
    BoxLayout layout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
    getContentPane().setLayout(layout);

    // Preferences component
    JComponent preferences = createPreferencesComponent();
    getContentPane().add(preferences);

    // Contributions
    if ((configuration.getSentFiles() != null) &&
        (!configuration.getSentFiles().isEmpty())) {
      StringBuffer buffer = new StringBuffer("You have already sent the following files:");
      Iterator iter = configuration.getSentFiles().iterator();
      int num = 0;
      while (iter.hasNext()) {
        if (num % 20 == 0) {
          buffer.append("\n" + iter.next().toString());
        } else {
          buffer.append(" " + iter.next().toString());
        }
        num++;
      }
      JTextArea textContributions = new JTextArea(buffer.toString());
      textContributions.setEditable(false);
      textContributions.setBackground(getBackground());
      JScrollPane scrollPane = new JScrollPane(
          textContributions,
          ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
          ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      scrollPane.setBorder(null);
      getContentPane().add(scrollPane);
    }
    
    // Commands component
    JComponent commands = createCommandsComponent();
    getContentPane().add(commands);

    pack();
  }

  /**
   * @return Component for the Preferences.
   */
  private JComponent createPreferencesComponent() {
    JPanel panel = new JPanel();
    BoxLayout layout = new BoxLayout(panel, BoxLayout.X_AXIS);
    panel.setLayout(layout);

    // Fields
    JComponent fieldsComponent = createFieldsComponent();
    panel.add(fieldsComponent);

    // Directories
    JComponent directoriesComponent = createDirectoriesComponent();
    panel.add(directoriesComponent);

    return panel;
  }

  /**
   * @return Component for the Fields.
   */
  private JComponent createFieldsComponent() {
    JPanel panel = new JPanel();
    GridBagLayout layout = new GridBagLayout();
    panel.setLayout(layout);
    panel.setBorder(new TitledBorder("Settings"));
    panel.setMinimumSize(new Dimension(150, 200));
    panel.setMaximumSize(new Dimension(200, 800));

    // Constraints
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.gridheight = 1;
    constraints.gridwidth = 1;
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.insets = new Insets(1, 1, 1, 1);
    constraints.ipadx = 1;
    constraints.ipady = 1;
    constraints.weightx = 1;
    constraints.weighty = 0;

    // User name
    JLabel labelUser = new JLabel("User name :", SwingConstants.RIGHT);
    constraints.gridx = 0;
    constraints.weightx = 0;
    constraints.anchor = GridBagConstraints.EAST;
    panel.add(labelUser, constraints);

    textUser = new JTextField(configuration.getUserName(), 15);
    textUser.setToolTipText("The User Name that will be credited for the .xyz files");
    constraints.gridx++;
    constraints.weightx = 1;
    constraints.anchor = GridBagConstraints.WEST;
    panel.add(textUser, constraints);

    constraints.gridy++;

    // Mail server
    JLabel labelMailServer = new JLabel("Mail server :", SwingConstants.RIGHT);
    constraints.gridx = 0;
    constraints.weightx = 0;
    constraints.anchor = GridBagConstraints.EAST;
    panel.add(labelMailServer, constraints);

    textMailServer = new JTextField(configuration.getMailServer(), 15);
    textMailServer.setToolTipText("Your mail server, maybe something like smtp....");
    constraints.gridx++;
    constraints.weightx = 1;
    constraints.anchor = GridBagConstraints.WEST;
    panel.add(textMailServer, constraints);

    constraints.gridy++;

    // Mail port
    JLabel labelMailPort = new JLabel("Mail port : ", SwingConstants.RIGHT);
    constraints.gridx = 0;
    constraints.weightx = 0;
    constraints.anchor = GridBagConstraints.EAST;
    panel.add(labelMailPort, constraints);
    
    NumberFormat portFormat = NumberFormat.getIntegerInstance();
    portFormat.setMaximumFractionDigits(0);
    portFormat.setMaximumIntegerDigits(5);
    textMailPort = new JFormattedTextField(portFormat);
    textMailPort.setColumns(15);
    textMailPort.setToolTipText("Your mail server port, usually 25");
    textMailPort.setValue(Integer.valueOf(configuration.getMailPort()));
    constraints.gridx++;
    constraints.weightx = 1;
    constraints.anchor = GridBagConstraints.WEST;
    panel.add(textMailPort, constraints);
    
    constraints.gridy++;

    // Mail SSL port
    JLabel labelMailSslPort = new JLabel("Mail SSL port : ", SwingConstants.RIGHT);
    constraints.gridx = 0;
    constraints.weightx = 0;
    constraints.anchor = GridBagConstraints.EAST;
    panel.add(labelMailSslPort, constraints);
    
    NumberFormat portSslFormat = NumberFormat.getIntegerInstance();
    portSslFormat.setMaximumFractionDigits(0);
    portSslFormat.setMaximumIntegerDigits(5);
    textMailSslPort = new JFormattedTextField(portSslFormat);
    textMailSslPort.setColumns(15);
    textMailSslPort.setToolTipText("Your mail server SSL port, usually 465");
    textMailSslPort.setValue(Integer.valueOf(configuration.getMailSslPort()));
    constraints.gridx++;
    constraints.weightx = 1;
    constraints.anchor = GridBagConstraints.WEST;
    panel.add(textMailSslPort, constraints);
    
    constraints.gridy++;

    // Use SSL
    chkUseSsl = new JCheckBox("Use SSL", configuration.getUseSsl());
    chkUseSsl.setToolTipText("Check this if you want to connect to your mail server on a secured connection using SSL");
    constraints.gridx = 0;
    constraints.gridwidth = 2;
    constraints.weightx = 1;
    constraints.anchor = GridBagConstraints.CENTER;
    panel.add(chkUseSsl, constraints);
    constraints.gridwidth = 1;
    
    constraints.gridy++;
    
    // User mail
    JLabel labelUserMail = new JLabel("Mail address :", SwingConstants.RIGHT);
    constraints.gridx = 0;
    constraints.weightx = 0;
    constraints.anchor = GridBagConstraints.EAST;
    panel.add(labelUserMail, constraints);

    textUserMail = new JTextField(configuration.getUserMail(), 15);
    textUserMail.setToolTipText("Your email address");
    constraints.gridx++;
    constraints.weightx = 1;
    constraints.anchor = GridBagConstraints.WEST;
    panel.add(textUserMail, constraints);

    constraints.gridy++;

    // User login
    JLabel labelUserLogin = new JLabel("Login :", SwingConstants.RIGHT);
    constraints.gridx = 0;
    constraints.weightx = 0;
    constraints.anchor = GridBagConstraints.EAST;
    panel.add(labelUserLogin, constraints);

    textUserLogin = new JTextField(configuration.getLogin(), 15);
    textUserLogin.setToolTipText("You only need to fill this field if your mail server requires authentication.");
    constraints.gridx++;
    constraints.weightx = 1;
    constraints.anchor = GridBagConstraints.WEST;
    panel.add(textUserLogin, constraints);

    constraints.gridy++;

    // User password
    JLabel labelUserPassword = new JLabel("Password :", SwingConstants.RIGHT);
    constraints.gridx = 0;
    constraints.weightx = 0;
    constraints.anchor = GridBagConstraints.EAST;
    panel.add(labelUserPassword, constraints);

    textUserPassword = new JPasswordField(configuration.getPassword(), 15);
    textUserPassword.setToolTipText("You only need to fill this field if your mail server requires authentication.");
    constraints.gridx++;
    constraints.weightx = 1;
    constraints.anchor = GridBagConstraints.WEST;
    panel.add(textUserPassword, constraints);

    constraints.gridy++;

    // Test mail
    buttonTestMail = new JButton("Test mail ...");
    buttonTestMail.setActionCommand(actionTestMail);
    buttonTestMail.addActionListener(this);
    constraints.gridx = 0;
    constraints.weightx = 1;
    constraints.gridwidth = 2;
    constraints.anchor = GridBagConstraints.CENTER;
    panel.add(buttonTestMail, constraints);
    constraints.gridwidth = 1;

    constraints.gridy++;

    // Save directory
    JLabel labelSaveDir = new JLabel("Save directory :", SwingConstants.RIGHT);
    constraints.gridx = 0;
    constraints.weightx = 0;
    constraints.anchor = GridBagConstraints.EAST;
    panel.add(labelSaveDir, constraints);

    textSaveDir = new JTextField(configuration.getSaveDirectory(), 15);
    textSaveDir.setToolTipText("The directory in which sent files are also copied locally.");
    constraints.gridx++;
    constraints.weightx = 1;
    constraints.anchor = GridBagConstraints.WEST;
    panel.add(textSaveDir, constraints);

    constraints.gridy++;

    // Loop
    chkLoop = new JCheckBox("Loop", configuration.getLoop());
    chkLoop.setToolTipText("Check this if you want the tool to check regularly for missing files");
    constraints.gridx = 0;
    constraints.gridwidth = 2;
    constraints.weightx = 1;
    constraints.anchor = GridBagConstraints.CENTER;
    panel.add(chkLoop, constraints);
    constraints.gridwidth = 1;
    
    constraints.gridy++;
    
    // Basic interval
    JLabel labelBasicInterval = new JLabel("Default interval : ", SwingConstants.RIGHT);
    constraints.gridx = 0;
    constraints.weightx = 0;
    constraints.anchor = GridBagConstraints.EAST;
    panel.add(labelBasicInterval, constraints);
    
    NumberFormat basicIntervalFormat = NumberFormat.getIntegerInstance();
    basicIntervalFormat.setMaximumFractionDigits(0);
    basicIntervalFormat.setMaximumIntegerDigits(4);
    textBasicInterval = new JFormattedTextField(basicIntervalFormat);
    textBasicInterval.setColumns(15);
    textBasicInterval.setToolTipText("The interval in minutes between two checks when 'Loop' is checked");
    textBasicInterval.setValue(Integer.valueOf(configuration.getBasicInterval()));
    constraints.gridx++;
    constraints.weightx = 1;
    constraints.anchor = GridBagConstraints.WEST;
    panel.add(textBasicInterval, constraints);
    
    constraints.gridy++;

    // Threshold
    JLabel labelThreshold = new JLabel("Threshold : ", SwingConstants.RIGHT);
    constraints.gridx = 0;
    constraints.weightx = 0;
    constraints.anchor = GridBagConstraints.EAST;
    panel.add(labelThreshold, constraints);
    
    NumberFormat thresholdFormat = NumberFormat.getIntegerInstance();
    thresholdFormat.setMaximumFractionDigits(0);
    thresholdFormat.setMaximumIntegerDigits(2);
    textThreshold = new JFormattedTextField(thresholdFormat);
    textThreshold.setColumns(15);
    textThreshold.setToolTipText("The threshold in % after which the interval is reduced");
    textThreshold.setValue(Integer.valueOf(configuration.getThreshold()));
    constraints.gridx++;
    constraints.weightx = 1;
    constraints.anchor = GridBagConstraints.WEST;
    panel.add(textThreshold, constraints);
    
    constraints.gridy++;

    // Special interval
    JLabel labelSpecialInterval = new JLabel("Interval after threshold : ", SwingConstants.RIGHT);
    constraints.gridx = 0;
    constraints.weightx = 0;
    constraints.anchor = GridBagConstraints.EAST;
    panel.add(labelSpecialInterval, constraints);
    
    NumberFormat specialIntervalFormat = NumberFormat.getIntegerInstance();
    specialIntervalFormat.setMaximumFractionDigits(0);
    specialIntervalFormat.setMaximumIntegerDigits(2);
    textSpecialInterval = new JFormattedTextField(specialIntervalFormat);
    textSpecialInterval.setColumns(15);
    textSpecialInterval.setToolTipText("The interval in minutes between two checks when 'Loop' is checked and the 'Threshold' is reached");
    textSpecialInterval.setValue(Integer.valueOf(configuration.getSpecialInterval()));
    constraints.gridx++;
    constraints.weightx = 1;
    constraints.anchor = GridBagConstraints.WEST;
    panel.add(textSpecialInterval, constraints);
    
    constraints.gridy++;

    // Filler
    JLabel labelFiller = new JLabel();
    constraints.gridx = 0;
    constraints.gridwidth = 2;
    constraints.weighty = 1;
    panel.add(labelFiller, constraints);

    return panel;
  }


  /**
   * @return Component for the Directories
   */
  private JComponent createDirectoriesComponent() {
    JPanel panel = new JPanel();
    BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
    panel.setLayout(layout);
    panel.setBorder(new TitledBorder("Directories"));

    // List
    listModel = new DefaultListModel();
    for (int i = 0; i < configuration.getDirectories().size(); i++) {
      listModel.addElement(configuration.getDirectories().get(i));
    }
    listDirectories = new JList(listModel);
    if (configuration.getDirectories().size() > 0) {
      listDirectories.setSelectedIndex(0);
    }
    listDirectories.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    listDirectories.setLayoutOrientation(JList.VERTICAL);
    //listDirectories.setMinimumSize(new Dimension(200, 100));
    JScrollPane listScroller = new JScrollPane(listDirectories);
    listScroller.setPreferredSize(new Dimension(200, 200));
    listScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    listScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    panel.add(listScroller);

    // Panel for commands
    panel.add(createDirectoriesCommands());

    return panel;
  }


  /**
   * @return Component for the commands on the directories
   */
  private JComponent createDirectoriesCommands() {
    JPanel panel = new JPanel();
    BoxLayout layout = new BoxLayout(panel, BoxLayout.X_AXIS);
    panel.setLayout(layout);

    // Add
    buttonAddDirectories = new JButton("Add");
    buttonAddDirectories.setActionCommand(actionAddDirectory);
    buttonAddDirectories.addActionListener(this);
    panel.add(buttonAddDirectories);

    // Remove
    buttonRemoveDirectories = new JButton("Remove");
    buttonRemoveDirectories.setActionCommand(actionRemoveDirectory);
    buttonRemoveDirectories.addActionListener(this);
    if (configuration.getDirectories().size() == 0) {
      buttonRemoveDirectories.setEnabled(false);
    }
    panel.add(buttonRemoveDirectories);

    return panel;
  }


  /**
   * @return Component for the Commands.
   */
  private JComponent createCommandsComponent() {
    JPanel panel = new JPanel();
    BoxLayout layout = new BoxLayout(panel, BoxLayout.X_AXIS);
    panel.setLayout(layout);

    // OK
    JButton buttonOk = new JButton("OK");
    buttonOk.setActionCommand(actionOk);
    buttonOk.addActionListener(this);
    panel.add(buttonOk);

    // Cancel
    JButton buttonCancel = new JButton("Cancel");
    buttonCancel.setActionCommand(actionCancel);
    buttonCancel.addActionListener(this);
    panel.add(buttonCancel);

    return panel;
  }


  /* (non-Javadoc)
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  public void actionPerformed(ActionEvent e) {
    if (actionOk.equals(e.getActionCommand())) {

      // Check values
      try {
        textMailPort.commitEdit();
      } catch (ParseException ex) {
        JOptionPane.showMessageDialog(
            this, "The mail port value is incorrect.", "Incorrect value", JOptionPane.ERROR_MESSAGE);
        return;
      }
      try {
        textMailSslPort.commitEdit();
      } catch (ParseException ex) {
        JOptionPane.showMessageDialog(
            this, "The mail SSL port value is incorrect.", "Incorrect value", JOptionPane.ERROR_MESSAGE);
        return;
      }
      try {
        textBasicInterval.commitEdit();
      } catch (ParseException ex) {
        JOptionPane.showMessageDialog(
            this, "The default interval value is incorrect.", "Incorrect value", JOptionPane.ERROR_MESSAGE);
        return;
      }
      try {
        textThreshold.commitEdit();
      } catch (ParseException ex) {
        JOptionPane.showMessageDialog(
            this, "The threshold value is incorrect.", "Incorrect value", JOptionPane.ERROR_MESSAGE);
        return;
      }
      try {
        textSpecialInterval.commitEdit();
      } catch (ParseException ex) {
        JOptionPane.showMessageDialog(
            this, "The second interval value is incorrect.", "Incorrect value", JOptionPane.ERROR_MESSAGE);
        return;
      }
      
      // Validate configuration
      Object tmpValue = null;
      configuration.setUserName(textUser.getText());
      configuration.setMailServer(textMailServer.getText());
      tmpValue = textMailPort.getValue();
      if (tmpValue instanceof Long) {
        Long port = (Long) tmpValue;
        configuration.setMailPort(port.intValue());
      }
      tmpValue = textMailSslPort.getValue();
      if (tmpValue instanceof Long) {
        Long port = (Long) tmpValue;
        configuration.setMailSslPort(port.intValue());
      }
      configuration.setUseSsl(chkUseSsl.isSelected());
      configuration.setUserMail(textUserMail.getText());
      configuration.setLogin(textUserLogin.getText());
      configuration.setPassword(textUserPassword.getText());
      configuration.setSaveDirectory(textSaveDir.getText());
      configuration.setLoop(chkLoop.isSelected());
      tmpValue = textBasicInterval.getValue();
      if (tmpValue instanceof Long) {
        Long interval = (Long) tmpValue;
        configuration.setBasicInterval(interval.intValue());
      }
      tmpValue = textThreshold.getValue();
      if (tmpValue instanceof Long) {
        Long threshold = (Long) tmpValue;
        configuration.setThreshold(threshold.intValue());
      }
      tmpValue = textSpecialInterval.getValue();
      if (tmpValue instanceof Long) {
        Long interval = (Long) tmpValue;
        configuration.setSpecialInterval(interval.intValue());
      }
      Vector directories = new Vector(listModel.size());
      for (int i = 0; i < listModel.size(); i++) {
        directories.add(listModel.get(i));
      }
      configuration.setDirectories(directories);
      configuration.saveConfiguration();

      this.setVisible(false);

      if (actionValidate != null) {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        actionValidate.actionPerformed(e);
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
      }

    } else if (actionCancel.equals(e.getActionCommand())) {

      // Cancel
      this.dispose();

    } else if (actionAddDirectory.equals(e.getActionCommand())) {

      // Add a directory
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      fileChooser.setApproveButtonText("Add directory");
      fileChooser.setApproveButtonToolTipText("Add this directory to the list of directories containing Folding@Home XYZ files");
      int returnVal = fileChooser.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File directory = fileChooser.getSelectedFile();
        if ((directory != null) && (directory.isDirectory())) {
          listModel.addElement(directory.getAbsolutePath());
          listDirectories.setSelectedIndex(listModel.size() - 1);
          buttonRemoveDirectories.setEnabled(true);
        }
      }

    } else if (actionRemoveDirectory.equals(e.getActionCommand())) {

      // Remove a directory
      int index = listDirectories.getSelectedIndex();
      if (index >= 0) {
        listModel.remove(index);
        int size = listModel.getSize();
        if (size == 0) {
          buttonRemoveDirectories.setEnabled(false);
        } else {
          if (index == listModel.getSize()) {
            index--;
          }
          listDirectories.setSelectedIndex(index);
          listDirectories.ensureIndexIsVisible(index);
        }
      }

    } else if (actionTestMail.equals(e.getActionCommand())) {

      // Check Port value
      try {
        textMailPort.commitEdit();
      } catch (ParseException ex) {
        JOptionPane.showMessageDialog(
            this, "The mail port value is incorrect.", "Incorrect value", JOptionPane.ERROR_MESSAGE);
        return;
      }
      try {
        textMailSslPort.commitEdit();
      } catch (ParseException ex) {
        JOptionPane.showMessageDialog(
            this, "The mail SSL port value is incorrect.", "Incorrect value", JOptionPane.ERROR_MESSAGE);
        return;
      }
      
      // Test mail configuration
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      fileChooser.setApproveButtonText("Use this file for testing");
      fileChooser.setApproveButtonToolTipText("Use this file in the testing mail sent to yourself");
      fileChooser.setDialogTitle("Select file for testing");
      int returnVal = fileChooser.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File testFile = fileChooser.getSelectedFile();
        if ((testFile != null) && (testFile.isFile())) {
          Configuration config = new Configuration();
          config.setLogin(textUserLogin.getText());
          config.setMailServer(textMailServer.getText());
          Object portValue = textMailPort.getValue();
          if (portValue instanceof Long) {
            Long port = (Long) portValue;
            config.setMailPort(port.intValue());
          }
          Object portSslValue = textMailSslPort.getValue();
          if (portSslValue instanceof Long) {
            Long port = (Long) portSslValue;
            config.setMailSslPort(port.intValue());
          }
          config.setUseSsl(chkUseSsl.isSelected());
          config.setPassword(textUserPassword.getText());
          config.setUserMail(textUserMail.getText());
          config.setUserName(textUser.getText());
          File[] files = new File[1];
          files[0] = testFile;
          MailSender sender = new MailSender(config, "XXXX", files, true);
          setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
          try {
            sender.sendMail();
            JOptionPane.showMessageDialog(
                this, "The test mail has been sent.", "Message sent", JOptionPane.INFORMATION_MESSAGE);
          } catch (Throwable ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                this,
                "The following error was encountered when sending the mail:\n" +
                ex.getClass().getName() + ": " + ex.getMessage() +
                ((ex.getCause() != null) ? "\n" + ex.getCause().getMessage() : ""),
                "Error while sending mail",
                JOptionPane.ERROR_MESSAGE);
          }
          setCursor(Cursor.getDefaultCursor());
        }
      }
    }
  }
}
