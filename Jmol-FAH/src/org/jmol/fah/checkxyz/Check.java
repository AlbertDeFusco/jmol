/* $RCSfile$
 * $Author: nicove $
 * $Date: 2005-08-27 11:16:47 +0200 $
 * $Revision: 3966 $
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
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 *  02111-1307  USA.
 */

package org.jmol.fah.checkxyz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

/**
 * Checking for missing XYZ files for http://www.jmol.org/fah
 *
 */
public class Check implements ActionListener {

  private boolean forceConfig = false;
  private boolean availableProjectsDownloaded = false;
  private boolean availableAmbersDownloaded = false;
  private Configuration configuration = new Configuration();

  private FileFilter projectFilter = null;
  private File availableProjects = null;
  private File availableAmbers = null;
  private Vector existingProjects = new Vector();
  private Vector existingAmbers = new Vector();
  private boolean showSentProjects = false;
  private Vector sentProjects = new Vector();
  private Vector sentAmbers = new Vector();

  /**
   * Constructor. 
   */
  public Check() {
    projectFilter = new FileFilter() {
      public boolean accept(File file) {
        if (file == null) {
          return false;
        }
        if (file.isDirectory()) {
          return true;
        }
        if (file.isFile()) {
          return file.getName().endsWith(".xyz");
        }
        return false;
      }
    };
    File configDirectory = new File(new File(System.getProperty("user.home")), ".jmol");
    try {
      FileUtils.forceMkdir(configDirectory);
    } catch (IOException e) {
      System.out.println("Error creating directory " + configDirectory.toString());
    }
    availableProjects = new File(configDirectory, "availableProjects");
    availableAmbers = new File(configDirectory, "availableAmbers");
  }

  /**
   * Check for missing files.
   * 
   * @param args Command line arguments
   */
  public void process(String[] args) {
    // Process command line arguments
    if (args != null) {
      for (int i = 0; i < args.length; i++) {
        if ("-config".equalsIgnoreCase(args[i])) {
          forceConfig = true;
        }
      }
    }
    
    // Once configured, process for the checking
    process();
  }

  /**
   * Check for missing files.
   */
  private void process() {
    configuration.loadConfiguration();
    if (forceConfig || !configuration.isConfigured()) {
      showSentProjects = true;
      configure();
    } else {
      processDirectories();
    }
  }

  /* (non-Javadoc)
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  public void actionPerformed(ActionEvent e) {
    processDirectories();
  }

  /**
   * Process all directories for files. 
   */
  public void processDirectories() {
    if (!configuration.getLoop()) {
      processDirectoriesOnce();
      System.exit(0);
    } else {
      do {
        availableProjectsDownloaded = false;
        availableAmbersDownloaded = false;
        System.out.println("==> Starting processing: " + DateFormat.getDateTimeInstance().format(new Date()));
        boolean specialInterval = processDirectoriesOnce();
        System.out.println("==> Processing done: " + DateFormat.getDateTimeInstance().format(new Date()));
        System.out.println("");
        int interval = (specialInterval ? configuration.getSpecialInterval() : configuration.getBasicInterval());
        try {
          Thread.sleep(interval * 60 * 1000);
        } catch (InterruptedException e) {
          //
        }
      } while (true);
    }
  }
  
  /**
   * Process all directories for files.
   * 
   * @return Flag indicating if the special interval should be used
   */
  public boolean processDirectoriesOnce() {
    boolean specialInterval = false;
    if (sentProjects != null) {
      sentProjects.clear();
    }
    if (sentAmbers != null) {
      sentAmbers.clear();
    }
    if (configuration.getDirectories() != null) {
      Iterator iter = configuration.getDirectories().iterator();
      while (iter.hasNext()) {
        specialInterval |= processDirectory(iter.next().toString());
      }
    }
    StringBuffer message = new StringBuffer();
    if ((sentProjects != null) && (!sentProjects.isEmpty())) {
      message.append(sentProjects.size());
      message.append(" .xyz files sent (");
      for (int i = 0; i < sentProjects.size(); i++) {
        if (i != 0) {
          message.append(", ");
        }
        message.append(sentProjects.get(i).toString());
      }
      message.append(")");
    } else {
      message.append("No new .xyz files found");
    }
    message.append("\n");
    if ((sentAmbers != null) && (!sentAmbers.isEmpty())) {
      message.append(sentAmbers.size());
      message.append(" .top/.trj files sent (");
      for (int i = 0; i < sentAmbers.size(); i++) {
        if (i != 0) {
          message.append(", ");
        }
        message.append(sentAmbers.get(i).toString());
      }
      message.append(")");
    } else {
      message.append("No new .top/.trj files found");
    }
    if (showSentProjects) {
      JOptionPane.showMessageDialog(
          null, message.toString(), "Result", JOptionPane.INFORMATION_MESSAGE);
      showSentProjects = false;
    } else {
      if (configuration.getDetailedOutput()) {
        System.out.println(message);
      }
    }
    return specialInterval;
  }

  /**
   * Process a directory for files.
   * 
   * @param directory Directory to process.
   * @return Flag indicating if the special interval should be used
   */
  private boolean processDirectory(String directory) {
    if (directory == null) {
      return false;
    }
    return processDirectory(new File(directory));
  }
  private boolean processDirectory(File directory) {
    if ((directory == null) || (!directory.isDirectory())) {
      return false;
    }
    File[] files = directory.listFiles(projectFilter);
    if (files == null) {
      return false;
    }
    boolean specialInterval = false;
    if (configuration.getDetailedOutput()) {
      System.out.println("Checking directory " + directory.getAbsolutePath());
    }
    for (int i = 0; i < files.length; i++) {
      if (files[i].isFile()) {
        specialInterval |= processFile(files[i]);
      }
    }
    for (int i = 0; i < files.length; i++) {
      if (files[i].isDirectory()) {
        specialInterval |= processDirectory(files[i]);
      }
    }
    return specialInterval;
  }

  /**
   * Check a XYZ file.
   * 
   * @param file XYZ file.
   * @return Flag indicating if the special interval should be used
   */
  private boolean processFile(File file) {
    if ((file == null) || (!file.isFile())) {
      return false;
    }
    boolean specialInterval = false;
    if (configuration.getDetailedOutput()) {
      System.out.print("    File " + file.getName() + " : ");
    }
    String project = extractProjectNumber(file);
    if (project == null) {
      if (configuration.getDetailedOutput()) {
        System.out.print("Unable to find project number");
      }
    } else {
      if (configuration.getDetailedOutput()) {
        System.out.print("Project n" + project + " -> ");
      }
      specialInterval = processProjectNumber(file, project);
    }
    if (configuration.getDetailedOutput()) {
      System.out.println();
    }
    return specialInterval;
  }

  /**
   * Analyze the <code>file</code> to find the project number.
   * 
   * @param file File.
   * @return Project number.
   */
  private String extractProjectNumber(File file) {
    String project;
    project = extractProjectNumberFromContent(file);
    if (project != null) {
      return project;
    }
    project = extractProjectNumberFromName(file);
    if (project != null) {
      return project;
    }
    return null;
  }

  /**
   * Analyze the <code>file</code> name to find the project number.
   * 
   * @param file File.
   * @return Project number.
   */
  private String extractProjectNumberFromName(File file) {
    String fileName = file.getName();

    // Removing 'p'
    if ((fileName.length() > 0) && (fileName.substring(0, 1).equalsIgnoreCase("p"))) {
      fileName = fileName.substring(1);
    }

    // Extracting the project number
    int index = 0;
    while ((index < fileName.length()) && (Character.isDigit(fileName.charAt(index)))) {
      index++;
    }
    if ((index == 0) || (index >= fileName.length())) {
      return null;
    }
    if ((fileName.charAt(index) != '_') && (fileName.charAt(index) != '.')) {
      return null;
    }
    return fileName.substring(0, index);
  }

  /**
   * Analyze the <code>file</code> content to find the project number.
   * 
   * @param file File.
   * @return Project number.
   */
  private String extractProjectNumberFromContent(File file) {
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(file));
      String line = reader.readLine();
      if (line == null) {
        return null;
      }
      line = line.trim();

      // Removing the number of atoms
      int index = 0;
      while ((index < line.length()) && (Character.isDigit(line.charAt(index)))) {
        index++;
      }
      if ((index == 0) || (index >= line.length())) {
        return null;
      }
      line = line.substring(index);
      
      // Removing the space characters
      index = 0;
      while ((index < line.length()) && (Character.isWhitespace(line.charAt(index)))) {
        index++;
      }
      if ((index == 0) || (index >= line.length())) {
        return null;
      }
      line = line.substring(index);
      
      // Removing the "p"
      if ((line.length() == 0) || (!line.substring(0, 1).equalsIgnoreCase("p"))) {
        return null;
      }
      line = line.substring(1);

      // Extracting the project number
      index = 0;
      while ((index < line.length()) && (Character.isDigit(line.charAt(index)))) {
        index++;
      }
      if ((index == 0) || (index >= line.length())) {
        return null;
      }
      if (line.charAt(index) != '_') {
        return null;
      }
      return line.substring(0, index);
    } catch (FileNotFoundException e) {
      //
    } catch (IOException e) {
      //
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          //
        }
      }
    }
    return null;
  }

  /**
   * Check a XYZ file.
   * 
   * @param file XYZ file.
   * @param project Project identifier.
   * @return Flag indicating if the special interval should be used
   */
  private boolean processProjectNumber(File file, String project) {
    if ((file == null) || (project == null)) {
      return false;
    }
    
    // Checking for Amber files
    File topologyFile = null;
    File trajectoryFile = null;
    File logFile = null;
    boolean pourcentEnough = false;
    boolean specialInterval = false;
    if (file.isFile() && "current.xyz".equalsIgnoreCase(file.getName())) {
      for (int i = 0; (i < 10) && !pourcentEnough; i++) {
        Object[] objects = new Object[] { Integer.valueOf(i) };
        File infoFile = new File(file.getParentFile(), String.format("wuinfo_%1$02d.dat", objects));
        if (infoFile.exists()) {
          topologyFile = new File(file.getParentFile(), String.format("wudata_%1$02d.top", objects));
          trajectoryFile = new File(file.getParentFile(), String.format("wudata_%1$02d.trj", objects));
          logFile = new File(file.getParentFile(), String.format("logfile_%1$02d.txt", objects));
          if (topologyFile.exists() && trajectoryFile.exists() && logFile.exists()) {
            try {
              BufferedReader reader = new BufferedReader(new FileReader(logFile));
              try {
                String line = null;
                while (!pourcentEnough && (line = reader.readLine()) != null) {
                  if (line != null) {
                    for (int percent = configuration.getThreshold(); percent <= 100; percent++) {
                      if (line.contains(Integer.toString(percent) + "%")) {
                        specialInterval = true;
                      }
                    }
                    if (line.contains("98%") ||
                        line.contains("99%")) {
                      pourcentEnough = true;
                    }
                  }
                }
              } catch (IOException e) {
                //
              } finally {
                if (reader != null) {
                  try {
                    reader.close();
                  } catch (IOException e) {
                    //
                  }
                }
              }
              if (!pourcentEnough) {
                topologyFile = null;
                trajectoryFile = null;
                logFile = null;
              }
            } catch (FileNotFoundException e) {
              //
            }
          } else {
            topologyFile = null;
            trajectoryFile = null;
            logFile = null;
          }
        }
      }
    }
    
    // Update existing projects
    if ((!availableProjects.exists()) || (!availableAmbers.exists())) {
      downloadAvailableFiles();
    }
    updateExistingProjects();

    // Check if something needs to be sent
    if (existingProjects.contains(project) &&
        (existingAmbers.contains(project) || (topologyFile == null) || (trajectoryFile == null))) {
      if (configuration.getDetailedOutput()) {
        System.out.print("Project available on Jmol website");
      }
      return (specialInterval && !existingAmbers.contains(project) && !configuration.hasBeenSent("A_" + project));
    }
    if (configuration.hasBeenSent(project) &&
        ((topologyFile == null) || (trajectoryFile == null) || configuration.hasBeenSent("A_" + project))) {
      if (configuration.getDetailedOutput()) {
        System.out.print("Already sent by you");
      }
      return false;
    }
    
    // Check again after updating files
    if ((!availableProjectsDownloaded) || (!availableAmbersDownloaded)) {
      downloadAvailableFiles();
      updateExistingProjects();
      if (existingProjects.contains(project) &&
          (existingAmbers.contains(project) || (topologyFile == null) || (trajectoryFile == null))) {
        if (configuration.getDetailedOutput()) {
          System.out.print("Project available on Jmol website");
        }
        return (specialInterval && !existingAmbers.contains(project));
      }
    }
    try {
      System.out.println("Found new project :) " + project);
      File[] files = new File[4];
      files[0] = file;
      if ((topologyFile != null) && (trajectoryFile != null)) {
        files[1] = topologyFile;
        files[2] = trajectoryFile;
        if (logFile != null) {
          files[3] = logFile;
        }
      }
      if ((configuration.getMailServer() != null) &&
          (!"".equals(configuration.getMailServer())) &&
          (configuration.getUserMail() != null) &&
          (!"".equals(configuration.getUserMail())) &&
          (configuration.getUserName() != null) &&
          (!"".equals(configuration.getUserName()))) {
        MailSender sender = new MailSender(configuration, project, files, false);
        sender.sendMail();
      }
      if ((configuration.getSaveDirectory() != null) &&
          (!"".equals(configuration.getSaveDirectory()))) {
        File saveDir = new File(configuration.getSaveDirectory());
        try {
          FileUtils.forceMkdir(saveDir);
        } catch (IOException e) {
          System.out.println("Error creating directory " + saveDir.getAbsolutePath());
        }
        for (int i = 0; i < files.length; i++) {
          File saveProjectDir = new File(saveDir, "p" + project);
          if (files[i] != null) {
            File destFile = new File(saveProjectDir, files[i].getName());
            try {
              FileUtils.copyFile(files[i], destFile);
            } catch (IOException e) {
              System.out.println("Error saving file " + files[i]);
            }
          }
        }
      }
      if (!existingProjects.contains(project)) {
        if (!configuration.hasBeenSent(project)) {
          configuration.addSentFile(project);
        }
        sentProjects.add(project);
      }
      if (!existingAmbers.contains(project) && (topologyFile != null) && (trajectoryFile != null)) {
        if (!configuration.hasBeenSent("A_" + project)) {
          configuration.addSentFile("A_" + project);
        }
        sentAmbers.add(project);
      }
    } catch (Throwable e) {
      outputError("Sending new file", e);
    }
    return false;
  }

  /**
   * Update the list of existing projects in memory.
   */
  private void updateExistingProjects() {
    if (availableProjects.exists() && existingProjects.isEmpty()) {
      BufferedReader reader = null;
      try {
        reader = new BufferedReader(new FileReader(availableProjects));
        String line;
        while ((line = reader.readLine()) != null) {
          line = line.trim();
          existingProjects.add(line);
        }
      } catch (FileNotFoundException e) {
        outputError("Reading local available projects", e);
      } catch (IOException e) {
        outputError("Reading local available projects", e);
      } finally {
        if (reader != null) {
          try {
            reader.close();
          } catch (IOException e) {
            // Nothing
          }
        }
      }
    }
    if (availableAmbers.exists() && existingAmbers.isEmpty()) {
      BufferedReader reader = null;
      try {
        reader = new BufferedReader(new FileReader(availableAmbers));
        String line;
        while ((line = reader.readLine()) != null) {
          line = line.trim();
          existingAmbers.add(line);
        }
      } catch (FileNotFoundException e) {
        outputError("Reading local available ambers", e);
      } catch (IOException e) {
        outputError("Reading local available ambers", e);
      } finally {
        if (reader != null) {
          try {
            reader.close();
          } catch (IOException e) {
            // Nothing
          }
        }
      }
    }
  }

  /**
   * Download the list of available files from http://www.jmol.org/
   */
  private void downloadAvailableFiles() {
    try {
      FileUtils.copyURLToFile(
          new URL("http://www.jmol.org/fah/availableProjects.txt"),
          availableProjects);
    } catch (MalformedURLException e) {
      //
    } catch (IOException e) {
      System.out.println("Error retrieving availableProjects.txt from Jmol website");
    }
    try {
      FileUtils.copyURLToFile(
          new URL("http://www.jmol.org/fah/availableAmber.txt"),
          availableAmbers);
    } catch (MalformedURLException e) {
      //
    } catch (IOException e) {
      System.out.println("Error retrieving availableAmber.txt from Jmol website");
    }
  }

  /**
   * Trace errors.
   * 
   * @param msg Message.
   * @param e Exception.
   */
  private void outputError(String msg, Throwable e) {
    if ((msg == null) && (e == null)) {
      return;
    }
    String text =
      ((msg != null) ? (msg + ": ") : "") +
      ((e != null) ? (e.getClass().getName() + " - " + e.getMessage()) : "");
    System.err.println(text);
  }

  /**
   * Update configuration. 
   */
  private void configure() {
    ConfigurationWindow window = new ConfigurationWindow(configuration, this);
    window.setVisible(true);
  }

  /**
   * @param args Command line arguments.
   */
  public static void main(String[] args) {
    Check check = new Check();
    check.process(args);
  }

}
