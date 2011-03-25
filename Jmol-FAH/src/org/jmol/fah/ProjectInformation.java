/* $RCSfile$
 * $Author$
 * $Date$
 * $Revision$
 *
 * Copyright (C) 2005  The Jmol Development Team
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

package org.jmol.fah;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.zip.ZipInputStream;

import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jmol.fah.utils.XMLValue;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * Manage project information
 */
public class ProjectInformation {

  // Projects informations
  Vector _projectInfo;

  // Flags to enable / disable parts of the code
  private final static boolean _outputEmprotz = false;
  private final static boolean _checkCurrentXyzFiles = true;
  private final static boolean _checkActiveMissing = true;

  // Indicates if informations are stored locally
  private static boolean _local = true;

  // Project information (Singleton)
  private static ProjectInformation _info = null;

  // Texts for the different sources of information
  private final static String       _txtEM  = "(EM) "; //$NON-NLS-1$
  private final static String       _txtFCI = "(FCI) "; //$NON-NLS-1$
  private final static String       _txtPS  = "(PS) "; //$NON-NLS-1$
  private final static String       _txtQD  = "(QD) "; //$NON-NLS-1$
  private final static String       _txtS   = "(S) "; //$NON-NLS-1$

  // Informations about EM (emprotz.dat)
  private long                      _emDate;
  private long                      _emFileDate;

  // Informations about PSummary (psummaryC.html)
  private long                      _psDate;
  private long                      _psFileDate;

  // Informations about QD (qdinfo.dat)
  private long                      _qdDate;
  private long                      _qdFileDate;

  // Informations about static data
  private long                      _staticDate;

  /**
   * @return Project information
   */
  public static ProjectInformation getInstance() {
    //Retrieve instance
    if (_info == null) {
      _info = new ProjectInformation();
    }
    
    return _info;
  }
  
  /**
   * @param projectNum Project number
   * @return Informations for project
   */
  Information getInfo(int projectNum) {

    //Retrieve project information
    Information result = null;
    Vector infos = this._projectInfo;
    if ((projectNum >= 0) && (projectNum < infos.size())) {
      Object obj = this._projectInfo.elementAt(projectNum);
      if (obj instanceof Information) {
        result = (Information) obj;
      }
  	}
    return result;
  }

  /**
   * @param projectNum Project number
   * @return Informations for project
   */
  Information createInfo(int projectNum) {
    Information info = new Information();
    if (projectNum != 9999) { // To avoid test projects
      if (this._projectInfo.size() <= projectNum) {
        this._projectInfo.setSize(projectNum + 1);
      }
      this._projectInfo.set(projectNum, info);
    }
    return info;
  }
  
  /**
   * Get points value for a project
   * 
   * @param projectNum Project number
   * @return Points value for project
   */
  public Double getProjectValue(int projectNum) {
    Double value = null;

    //Retrieve project information
    Information info = getInfo(projectNum);
    if (info != null) {
      //Initialize with static value
      value = info._staticValue;
      long date = _info._staticDate;

      //Replace eventually with EM value
      if ((info._emValue != null) &&
          ((_info._emDate > date) || (value == null))) {
        value = info._emValue;
        date = _info._emDate;
      }

      //Replace eventually with PS value
      if ((info._psValue != null) &&
          ((_info._psDate > date) || (value == null))) {
        value = info._psValue;
        date = _info._psDate;
      }

      //Replace eventually with QD value
      if ((info._qdValue != null) &&
          ((_info._qdDate > date) || (value == null))) {
        value = info._qdValue;
        date = _info._qdDate;
      }
    }

    return value;
  }

  /**
   * Get name for a project
   * 
   * @param projectNum Project number
   * @return Name of the project
   */
  public String getProjectName(int projectNum) {
    String name = null;

    //Retrieve project information
    Information info = getInfo(projectNum);
    if (info != null) {
      //Initialize with static name
      name = info._staticName;
      long date = _info._staticDate;

      //Replace eventually with PS name
      if ((info._psName != null) &&
          ((_info._psDate > date) || (name == null))) {
        name = info._psName;
        //date = _info._psDate;
      }
    }

    return name;
  }

  /**
   * Constructor for ProjectInformation
   */
  private ProjectInformation() {
    this._projectInfo = new Vector(2000);
    this._emDate = -1;
    this._emFileDate = -1;
    this._psDate = -1;
    this._psFileDate = -1;
    this._qdDate = -1;
    this._qdFileDate = -1;
    this._staticDate = new GregorianCalendar(2004, Calendar.JULY, 10).getTimeInMillis();
    addStaticInformation();
    addEMInformation();
    addPSCInformation();
    addPSInformation();
    addQDInformation();
    addFCIInformation();
  }

  /**
   * Update a local file from an URL.
   * 
   * @param file Local file.
   * @param url URL.
   */
  private void updateFileFromUrl(File file, URL url) {
    if ((file == null) || (url == null)) {
      return;
    }
    OutputStream output = null;
    InputStream input = null;
    try {

      System.out.print("Updating " + file.getName() + " from " + url.toExternalForm() + ": ");
      // Get last-modified times of the URL
      URLConnection connection = url.openConnection();
      if (connection == null) {
        System.out.println("Unable to access URL");
        return;
      }
      long urlTime = connection.getLastModified();
      if (urlTime == 0) {
        System.out.println("Unable to retrieve last-modified information");
        return;
      }

      // Get last-modified time of the file
      long fileTime = file.lastModified();
      if (fileTime >= urlTime) {
        System.out.println("File already up to date");
        return;
      }

      // Retrieve the file
      File tmpFile = new File(file.getParentFile(), file.getName() + ".tmp");
      output = new BufferedOutputStream(new FileOutputStream(tmpFile));
      input = new BufferedInputStream(connection.getInputStream());
      int data;
      while ((data = input.read()) != -1) {
        output.write(data);
      }
      input.close();
      output.close();

      // Manage local file
      if (!file.delete()) {
        System.out.println("Error deleting file " + file.toString());
      }
      if (!tmpFile.renameTo(file)) {
        System.out.println("Error renaming file " + tmpFile.toString() + " to " + file.toString());
      }
      System.out.println("File updated");
    } catch (IOException e) {
      System.out.println("Error " + e.getMessage());
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          //
        }
      }
      if (output != null) {
        try {
          output.close();
        } catch (IOException e) {
          //
        }
      }
    }
  }

  /**
   * Add information from emprotz.dat
   */
  private void addEMInformation() {
    try {
      //Check file existence and time
      long emDate = System.currentTimeMillis();
      if (_local == true) {
        File emFile = new File("emprotz.dat"); //$NON-NLS-1$
        if (!emFile.exists()) {
          return;
        }
        emDate = emFile.lastModified();
      }
      if (emDate > this._emFileDate) {
        //Update time information
        this._emFileDate = emDate;
        this._emDate = emDate;

        //Clear old information
        for (int ii = 0; ii < this._projectInfo.size(); ii++) {
          Information info = getInfo(ii);
          if (info != null) {
            info._emDeadline = null;
            info._emFrames = null;
            info._emValue = null;
          }
        }

        //Load new information
        Reader reader = null;
        if (_local == true) {
          reader = new FileReader("emprotz.dat"); //$NON-NLS-1$
        } else {
          StringBuffer urlName = new StringBuffer();
          urlName.append("http://home.comcast.net/"); //$NON-NLS-1$
          urlName.append("~wxdude1/emsite/download/"); //$NON-NLS-1$
          urlName.append("emprotz.zip"); //$NON-NLS-1$
          try {
            URL url = new URL(urlName.toString());
            InputStream stream = url.openStream();
            ZipInputStream zip = new ZipInputStream(stream);
            zip.getNextEntry();
            reader = new InputStreamReader(zip);
          } catch (MalformedURLException mue) {
            mue.printStackTrace();
          }
        }
        BufferedReader file = new BufferedReader(reader);
        try {
          String line1 = null;
          int count = 0;
          while ((line1 = file.readLine()) != null) {
            String line2 = file.readLine();
            String line3 = (line2 != null) ? file.readLine() : null;
            String line4 = (line3 != null) ? file.readLine() : null;
            count++;
            if ((count > 1) &&
                (line1 != null) && (line2 != null) &&
                (line3 != null) && (line4 != null)) {
              if (line1.length() > 2) {
                int posBegin = line1.indexOf("\"", 0); //$NON-NLS-1$
                int posEnd = line1.indexOf("\"", posBegin + 1); //$NON-NLS-1$
                if ((posBegin >= 0) && (posEnd >= 0)) {
                  String project = line1.substring(posBegin + 1, posEnd - posBegin);
                  int projectNum = Integer.parseInt(project);
                  Integer deadline = Integer.valueOf(line2.trim());
                  Double value = Double.valueOf(line3.trim());
                  Integer frames = Integer.valueOf(line4.trim());

                  //Retrieve element
                  Information info = getInfo(projectNum);
                  if (info == null) {
                  	info = createInfo(projectNum);
                  }

                  //Set value
                  if (info._emValue == null) {
                    info._emDeadline = deadline;
                    info._emFrames = frames;
                    info._emValue = value;
                  }
                }
              }
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          file.close();
        }
      }
    } catch (FileNotFoundException e) {
      //Empty
    } catch (IOException e) {
      //Empty
    }
  }

  /**
   * Add information from psummaryC.html
   */
  private void addPSCInformation() {
    Reader reader = null;
    try {
      //Check file existence and time
      long psDate = System.currentTimeMillis();
      if (_local == true) {
        File psFile = new File("psummary.html"); //$NON-NLS-1$
        if (!psFile.exists()) {
          return;
        }
        psDate = psFile.lastModified();
      }
      if (psDate > this._psFileDate) {
        //Update time information
        this._psFileDate = psDate;
        this._psDate = psDate;

        //Clear old information
        for (int ii = 0; ii < this._projectInfo.size(); ii++) {
          Information info = getInfo(ii);
          if (info != null) {
            info._psAtoms = null;
            info._psContact = null;
            info._psCore = null;
            info._psDeadline = null;
            info._psFrames = null;
            info._psKfactor = null;
            info._psName = null;
            info._psPreferred = null;
            info._psServer = null;
            info._psValue = null;
          }
        }

        //Load new information
        if (_local == true) {
          reader = new FileReader("psummary.html"); //$NON-NLS-1$
        } else {
          StringBuffer urlName = new StringBuffer();
          urlName.append("http://fah-web.stanford.edu/"); //$NON-NLS-1$
          urlName.append("psummaryC.html"); //$NON-NLS-1$
          try {
            URL url = new URL(urlName.toString());
            InputStream stream = url.openStream();
            reader = new InputStreamReader(stream);
          } catch (MalformedURLException mue) {
            mue.printStackTrace();
          }
        }
        HTMLDocument htmlDoc = new HTMLDocumentPSummaryC();
        HTMLEditorKit htmlEditor = new HTMLEditorKit();
        htmlEditor.read(reader, htmlDoc, 0);
      }
    } catch (FileNotFoundException e) {
      //Empty
    } catch (IOException e) {
      //Empty
    } catch (BadLocationException e) {
      //Empty
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          //
        }
      }
    }
  }

  /**
   * Add information from psummary.html
   */
  private void addPSInformation() {
    Reader reader = null;
    try {
      //Check file existence and time
      //long psDate = System.currentTimeMillis();
      if (_local == true) {
        return;
      }

      //Load new information
      if (_local == true) {
        reader = new FileReader("psummary.html"); //$NON-NLS-1$
      } else {
        StringBuffer urlName = new StringBuffer();
        urlName.append("http://fah-web.stanford.edu/"); //$NON-NLS-1$
        urlName.append("psummary.html"); //$NON-NLS-1$
        try {
          URL url = new URL(urlName.toString());
          InputStream stream = url.openStream();
          reader = new InputStreamReader(stream);
        } catch (MalformedURLException mue) {
          mue.printStackTrace();
        }
      }
      HTMLDocument htmlDoc = new HTMLDocumentPSummary();
      HTMLEditorKit htmlEditor = new HTMLEditorKit();
      htmlEditor.read(reader, htmlDoc, 0);
    } catch (FileNotFoundException e) {
      //Empty
    } catch (IOException e) {
      //Empty
    } catch (BadLocationException e) {
      //Empty
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          //
        }
      }
    }
  }

  /**
   * Add information from qdinfo.dat
   */
  private void addQDInformation() {
    try {
      //Check file existence and time
      long qdDate = System.currentTimeMillis();
      if (_local == true) {
        File qdFile = new File("qdinfo.dat"); //$NON-NLS-1$
        if (!qdFile.exists()) {
          return;
        }
        qdDate = qdFile.lastModified();
      }
      if (qdDate > this._qdFileDate) {
        //Update time information
        this._qdFileDate = qdDate;

        //Clear old information
        for (int ii = 0; ii < this._projectInfo.size(); ii++) {
          Information info = getInfo(ii);
          if (info != null) {
            info._qdValue = null;
          }
        }

        //Load new information
        Reader reader = null;
        if (_local == true) {
          reader = new FileReader("qdinfo.dat"); //$NON-NLS-1$
        } else {
          StringBuffer urlName = new StringBuffer();
          urlName.append("http://boston.quik.com/rph/"); //$NON-NLS-1$
          urlName.append("qdinfo.dat"); //$NON-NLS-1$
          try {
            URL url = new URL(urlName.toString());
            InputStream stream = url.openStream();
            reader = new InputStreamReader(stream);
          } catch (MalformedURLException mue) {
            mue.printStackTrace();
          }
        }
        BufferedReader file = new BufferedReader(reader);
        try {
          String line = null;
          while ((line = file.readLine()) != null) {
            if (line.startsWith("pg ")) { //$NON-NLS-1$
              this._qdDate = Long.parseLong(line.substring(3), 16);
              this._qdDate = (this._qdDate + 946684800) * 1000;
            } else if (line.startsWith("pt ")) { //$NON-NLS-1$
              line = line.substring(3).trim();
              int pos = -1;
              while ((line.length() > 0) &&
                     ((pos = line.indexOf(' ')) > 0)) {
                int projectNum = 0;
                Double value = null;
                if (pos > 0) {
                  projectNum = Integer.parseInt(line.substring(0, pos));
                  line = line.substring(pos).trim();
                }
                pos = line.indexOf(' ');
                if (pos > 0) {
                  value = new Double((double) Integer.parseInt(line.substring(0, pos)) / 100);
                  line = line.substring(pos).trim();
                }

                //Retrieve element
                Information info = getInfo(projectNum);
                if (info == null) {
                  info = createInfo(projectNum);
                }

                //Set value
                if (info._qdValue == null) {
                  info._qdValue = value;
                }
              }
            }
          }
        } finally {
          file.close();
        }
      }
    } catch (FileNotFoundException e) {
      //Empty
    } catch (IOException e) {
      //Empty
    }
  }

  /**
   * Add information from FCI
   */
  private void addFCIInformation() {
    if (_local == true) {
      return;
    }

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);

    InputStream stream = null;
    try {
      // Retrieve distant file
      File localFile = new File("fci-data.xml");
      URL distantUrl = new URL("http://fci.fatalerrorgroup.com/xml-data/project-summary.xml");
      if (_local == false) {
        updateFileFromUrl(localFile, distantUrl);
      }

      //Load document
      stream = new FileInputStream(localFile);
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(stream);

      //Parse document
      Node node = document.getFirstChild();
      while (node != null) {
        if (node.getLocalName().equalsIgnoreCase("project_summary")) { //$NON-NLS-1$
          Node child = node.getFirstChild();
          while (child != null) {

            //Get project information
            if (child.getNodeName().equalsIgnoreCase("project")) { //$NON-NLS-1$
              addFCINode(child);
            }

            child = child.getNextSibling();
          }
        }
        node = node.getNextSibling();
      }
    } catch (SAXException e) {
      //Empty
    } catch (ParserConfigurationException e) {
      //Empty
    } catch (IOException e) {
      //Empty
    } finally {
      if (stream != null) {
        try {
          stream.close();
        } catch (IOException e) {
          //
        }
      }
    }
  }

  /**
   * Add information about a project from FCI
   * 
   * @param node Node for project
   */
  private void addFCINode(Node node) {

    //Project number
    Integer project = XMLValue.getInteger(node, "number"); //$NON-NLS-1$
    if (project == null) {
      return;
    }

    //Retrieve element
    Information info = getInfo(project.intValue());
    if (info == null) {
      info = createInfo(project.intValue());
    }

    if (info != null) {
      info._fciAtoms = XMLValue.getInteger(node, "atoms"); //$NON-NLS-1$
      String core = XMLValue.getString(node, "code", null); //$NON-NLS-1$
      if (core != null) {
        if ("AMBER".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.AMBER;
        } else if ("ATI-DEV".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.ATI_DEV;
        } else if ("DC".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.DGROMACSC;
        } else if ("DGROMACS".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.DGROMACS;
        } else if ("DGROMACSB".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.DGROMACSB;
        } else if ("DGROMACSC".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.DGROMACSC;
        } else if ("GBGROMACS".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.GBGROMACS;
        } else if ("GC".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.GROCVS;
        } else if ("GG2".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.GROGPU2;
        } else if ("GROCVS".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.GROCVS;
        } else if ("GROGPU".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.GROGPU;
        } else if ("GROGPU2".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.GROGPU2;
        } else if ("GROGPU2-MT".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.GROGPU2_MT;
        } else if ("GROMACS".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.GROMACS;
        } else if ("GROMACS33".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.A0GROMACS;
        } else if ("GROMACS-SMP".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.GROMACS_SMP;
        } else if ("GRO-A3".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.A3GROMACS;
        } else if ("GRO-A4".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.A4GROMACS;
        } else if ("GRO-A5".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.A5GROMACS;
        } else if ("GRO-PS3".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.GROMACS_PS3;
        } else if ("GRO-SMP".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.GROMACS_SMP;
        } else if ("GROST".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.GROMACS_ST;
        } else if ("GROSIMT".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.GROMACS_SIMT;
        } else if ("NVIDIA-DEV".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.NVIDIA_DEV;
        } else if ("OPENMMFERMI".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.OPEN_MM_FERMI;
        } else if ("OPENMMGPU".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.OPEN_MM_GPU;
        } else if ("OPENMM_OPENCL".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.OPEN_MM_CL;
        } else if ("ProtoMol".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.PROTO_MOL;
        } else if ("QMD".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.QMD;
        } else if ("SHARPEN".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.SHARPEN;
        } else if ("TINKER".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.TINKER;
        } else if ("UNLISTED".equalsIgnoreCase(core)) {
          info._fciCore = CoreType.UNLISTED;
        } else {
          outputTextLn("FCI Core unknown: " + core);
        }
      }
      info._fciContact = XMLValue.getString(node, "contact", null); //$NON-NLS-1$
      info._fciValue = XMLValue.getDouble(node, "credit"); //$NON-NLS-1$
      info._fciDeadline = XMLValue.getInteger(node, "deadline", 86400); //$NON-NLS-1$
      info._fciFrames = XMLValue.getInteger(node, "frames"); //$NON-NLS-1$
      info._fciKfactor = XMLValue.getDouble(node, "kfactor"); //$NON-NLS-1$
      info._fciName = XMLValue.getString(node, "name", null); //$NON-NLS-1$
      info._fciPreferred = XMLValue.getInteger(node, "preferred", 86400); //$NON-NLS-1$
      info._fciServer = XMLValue.getString(node, "server", null); //$NON-NLS-1$
    }
  }

  /**
   * Add static information (hard coded)
   */
  private void addStaticInformation() {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);

    try {
      //Load document
      DocumentBuilder builder = factory.newDocumentBuilder();
      File file = null;
      if (_local == true) {
        file = new File("fah-projects.xml"); //$NON-NLS-1$
      } else {
        file = new File("../Jmol-web/source/doc/fah/fah-projects.xml"); //$NON-NLS-1$
      }
      Document document = builder.parse(file);

      //Parse document
      Node node = document.getFirstChild();
      while (node != null) {
        if (node.getLocalName().equalsIgnoreCase("fah_projects")) { //$NON-NLS-1$
          Node child = node.getFirstChild();
          while (child != null) {
            //Get Date
            /*if (child.getNodeName().equalsIgnoreCase("date")) { //$NON-NLS-1$
              //TODO
            }*/

            //Get project information
            if (child.getNodeName().equalsIgnoreCase("fah_proj")) { //$NON-NLS-1$
              addStaticNode(child);
            }

            child = child.getNextSibling();
          }
        }
        node = node.getNextSibling();
      }
    } catch (SAXException e) {
      //Empty
    } catch (ParserConfigurationException e) {
      //Empty
    } catch (IOException e) {
      //Empty
    }
  }

  /**
   * Add static information about a project
   * 
   * @param node Node for project
   */
  private void addStaticNode(Node node) {
    NamedNodeMap att = node.getAttributes();

    //Project number
    Integer project = XMLValue.getInteger(att, "number"); //$NON-NLS-1$
    if (project == null) {
      return;
    }

    //Retrieve element
    Information info = getInfo(project.intValue());
    if (info == null) {
      info = createInfo(project.intValue());
    }

    if (info != null) {
      info._staticAtoms = XMLValue.getInteger(att, "atoms"); //$NON-NLS-1$
      info._staticContact = XMLValue.getString(att, "contact", null); //$NON-NLS-1$
      info._staticCore = CoreType.getFromCode(XMLValue.getString(att, "code", null)); //$NON-NLS-1$
      info._staticValue = XMLValue.getDouble(att, "credit"); //$NON-NLS-1$
      info._staticDeadline = XMLValue.getInteger(att, "deadline", 86400); //$NON-NLS-1$
      info._staticFrames = XMLValue.getInteger(att, "frames"); //$NON-NLS-1$
      info._staticName = XMLValue.getString(att, "name", null); //$NON-NLS-1$
      info._staticPreferred = XMLValue.getInteger(att, "preferred", 86400); //$NON-NLS-1$
      info._staticServer = XMLValue.getString(att, "server", null); //$NON-NLS-1$
      info._staticFile = XMLValue.getString(att, "file", null); //$NON-NLS-1$
      info._staticPublic = XMLValue.getYesNo(att, "public"); //$NON-NLS-1$
      info._staticKfactor = XMLValue.getDouble(att, "kfactor"); //$NON_NLS-1$
    }
  }

  /**
   * Information for a project
   */
  private static class Information {
    /**
     * Constructor for Information
     */
    Information() {
      this._emDeadline = null;
      this._emFrames = null;
      this._emValue = null;
      this._fciAtoms = null;
      this._fciCore = null;
      this._fciDeadline = null;
      this._fciFrames = null;
      this._fciKfactor = null;
      this._fciName = null;
      this._fciPreferred = null;
      this._fciServer = null;
      this._fciValue = null;
      this._psAtoms = null;
      this._psCore = null;
      this._psDeadline = null;
      this._psFrames = null;
      this._psKfactor = null;
      this._psName = null;
      this._psPreferred = null;
      this._psServer = null;
      this._psValue = null;
      this._psPublic = null;
      this._qdValue = null;
      this._staticAtoms = null;
      this._staticCore = null;
      this._staticDeadline = null;
      this._staticFrames = null;
      this._staticKfactor = null;
      this._staticName = null;
      this._staticPreferred = null;
      this._staticServer = null;
      this._staticValue = null;
      this._staticFile = null;
      this._staticPublic = null;
    }

    // EM informations
    Integer  _emDeadline;
    Integer  _emFrames;
    Double   _emValue;

    // FCI informations
    Integer  _fciAtoms;
    String   _fciContact;
    CoreType _fciCore;
    Integer  _fciDeadline;
    Integer  _fciFrames;
    Double   _fciKfactor;
    String   _fciName;
    Integer  _fciPreferred;
    String   _fciServer;
    Double   _fciValue;

    // PSummary informations
    Integer  _psAtoms;
    String   _psContact;
    CoreType _psCore;
    Integer  _psDeadline;
    Integer  _psFrames;
    Double   _psKfactor;
    String   _psName;
    Integer  _psPreferred;
    String   _psServer;
    Double   _psValue;
    Boolean  _psPublic;

    // QD informations
    Double   _qdValue;

    // Static informations
    Integer  _staticAtoms;
    String   _staticContact;
    CoreType _staticCore;
    Integer  _staticDeadline;
    Integer  _staticFrames;
    Double   _staticKfactor;
    String   _staticName;
    Integer  _staticPreferred;
    String   _staticServer;
    Double   _staticValue;
    String   _staticFile;
    Boolean  _staticPublic;
  }

  /**
   * HTML Document for PSummaryC
   */
  class HTMLDocumentPSummaryC extends HTMLDocument {

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.text.html.HTMLDocument#getReader(int)
     */
    public HTMLEditorKit.ParserCallback getReader(int pos) {
      return new PSummaryReaderC();
    }
  }

  /**
   * Reader for PSummaryC
   */
  class PSummaryReaderC extends HTMLEditorKit.ParserCallback {

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.text.html.HTMLEditorKit.ParserCallback#handleStartTag(
     *      javax.swing.text.html.HTML.Tag,
     *      javax.swing.text.MutableAttributeSet,
     *      int)
     */
    public void handleStartTag(HTML.Tag tag, MutableAttributeSet att, int pos) {
      if (tag.equals(HTML.Tag.TABLE)) {
        this._table++;
        this._column = 0;
      }
      if (tag.equals(HTML.Tag.TR)) {
        this._row++;
        this._column = 0;
      }
      if (tag.equals(HTML.Tag.TD)) {
        this._column++;
      }
      if ((this._table > 0) && (this._row == 1)) {
        //System.out.println(tag);
      }
      super.handleStartTag(tag, att, pos);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.text.html.HTMLEditorKit.ParserCallback#handleText(char[], int)
     */
    public void handleText(char[] data, int pos) {
      if ((this._table > 0) && (this._row > 1)) {
        switch (this._column) {
        case 1: //Project
          this._project = Integer.parseInt(new String(data));
          break;

        case 2: //Server IP
          this._server = new String(data);
          break;

        case 3: //Work unit name
          this._name = new String(data);
          break;

        case 4: //Number of atoms
          this._atoms = null;
          try {
            this._atoms = Integer.valueOf(new String(data));
          } catch (NumberFormatException e) {
            //
          }
          break;

        case 5: //Preferred days
          this._preferred = null;
          try {
            this._preferred = new Integer((int) (86400 * Double.parseDouble(new String(data))));
          } catch (NumberFormatException e) {
            //Empty
          }
          break;

        case 6: //Final deadline
          this._deadline = null;
          try {
            this._deadline = new Integer((int) (86400 * Double.parseDouble(new String(data))));
          } catch (NumberFormatException e) {
            //Empty
          }
          break;

        case 7: //Credits
          this._value = null;
          try {
            this._value = Double.valueOf(new String(data));
          } catch (NumberFormatException e) {
            //Empty
          }
          break;

        case 8: //Frames
          this._frames = null;
          try {
            this._frames = Integer.valueOf(new String(data));
          } catch (NumberFormatException e) {
            //
          }
          break;

        case 9: //Code
          this._core = CoreType.getFromName(new String(data));
          break;

        case 10: //Description
          break;

        case 11: //Contact
          this._contact = new String(data);
          break;
          
        case 12: //Kfactor
          this._kfactor = null;
          try {
            this._kfactor = Double.valueOf(new String(data));
          } catch (NumberFormatException e) {
            //
          }
        }
      }
      super.handleText(data, pos);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.text.html.HTMLEditorKit.ParserCallback#handleEndTag(
     *      javax.swing.text.html.HTML.Tag,
     *      int)
     */
    public void handleEndTag(HTML.Tag tag, int pos) {
      if ((this._table > 0) && (this._row == 1)) {
        //System.out.println("/" + tag); //$NON-NLS-1$
      }
      if (tag.equals(HTML.Tag.TABLE)) {
        this._table--;
        if (this._table == 0) {
          this._row = 0;
        }
      }
      if (tag.equals(HTML.Tag.TR)) {
        this._column = 0;
        if (this._project > 0) {
          //Retrieve element
          Information info = null;
          if (ProjectInformation.this._projectInfo.size() > this._project) {
            info = ProjectInformation.this.getInfo(this._project);
          }
          if (info == null) {
          	info = ProjectInformation.this.createInfo(this._project);
          }

          //Set value
          if (info._psValue == null) {
            info._psAtoms = this._atoms;
            info._psContact = this._contact;
            info._psCore = this._core;
            info._psDeadline = this._deadline;
            info._psFrames = this._frames;
            info._psKfactor = this._kfactor;
            info._psName = this._name;
            info._psPreferred = this._preferred;
            info._psServer = this._server;
            info._psValue = this._value;
          }
        }
        this._atoms = null;
        this._contact = null;
        this._core = null;
        this._deadline = null;
        this._frames = null;
        this._kfactor = null;
        this._name = null;
        this._preferred = null;
        this._project = -1;
        this._server = null;
        this._value = null;
      }
      super.handleEndTag(tag, pos);
    }

    // Informations on the current position in the HTML file
    private int      _column    = 0;
    private int      _row       = 0;
    private int      _table     = 0;

    // Informations on the current project
    private Integer  _atoms     = null;
    private String   _contact   = null;
    private CoreType _core      = null;
    private Integer  _deadline  = null;
    private Integer  _frames    = null;
    private Double   _kfactor   = null;
    private String   _name      = null;
    private Integer  _preferred = null;
    private int      _project   = -1;
    private String   _server    = null;
    private Double   _value     = null;
  }

  /**
   * HTML Document for PSummary
   */
  class HTMLDocumentPSummary extends HTMLDocument {

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.text.html.HTMLDocument#getReader(int)
     */
    public HTMLEditorKit.ParserCallback getReader(int pos) {
      return new PSummaryReader();
    }
  }

  /**
   * Reader for PSummary
   */
  class PSummaryReader extends HTMLEditorKit.ParserCallback {

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.text.html.HTMLEditorKit.ParserCallback#handleStartTag(
     *      javax.swing.text.html.HTML.Tag,
     *      javax.swing.text.MutableAttributeSet,
     *      int)
     */
    public void handleStartTag(HTML.Tag tag, MutableAttributeSet att, int pos) {
      if (tag.equals(HTML.Tag.TABLE)) {
        this._table++;
        this._column = 0;
      }
      if (tag.equals(HTML.Tag.TR)) {
        this._row++;
        this._column = 0;
      }
      if (tag.equals(HTML.Tag.TD)) {
        this._column++;
      }
      if ((this._table > 0) && (this._row == 1)) {
        //System.out.println(tag);
      }
      super.handleStartTag(tag, att, pos);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.text.html.HTMLEditorKit.ParserCallback#handleText(char[], int)
     */
    public void handleText(char[] data, int pos) {
      if ((this._table > 0) && (this._row > 1)) {
        switch (this._column) {
        case 1: //Project
          this._project = Integer.parseInt(new String(data));
          break;
        }
      }
      super.handleText(data, pos);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.text.html.HTMLEditorKit.ParserCallback#handleEndTag(
     *      javax.swing.text.html.HTML.Tag,
     *      int)
     */
    public void handleEndTag(HTML.Tag tag, int pos) {
      if ((this._table > 0) && (this._row == 1)) {
        //System.out.println("/" + tag); //$NON-NLS-1$
      }
      if (tag.equals(HTML.Tag.TABLE)) {
        this._table--;
        if (this._table == 0) {
          this._row = 0;
        }
      }
      if (tag.equals(HTML.Tag.TR)) {
        this._column = 0;
        if (this._project > 0) {
          //Retrieve element
          Information info = null;
          if (ProjectInformation.this._projectInfo.size() > this._project) {
            info = ProjectInformation.this.getInfo(this._project);
          }
          if (info != null) {
            info._psPublic = Boolean.TRUE;
          }
        }
        this._project = -1;
      }
      super.handleEndTag(tag, pos);
    }

    // Informations on the current position in the HTML file
    private int      _column    = 0;
    private int      _row       = 0;
    private int      _table     = 0;

    // Informations on the current project
    private int      _project   = -1;
  }

  /**
   * Output data in the same format as emprotz.dat file 
   */
  private void outputEmprotzDatFile() {
    int unknownProjects = 0;
    for (int ii = 0; ii < _projectInfo.size(); ii++) {
      Information info = getInfo(ii);
      if (info != null) {
        if ((info._staticPreferred != null) &&
            (info._staticValue != null) &&
            (info._staticFrames != null)) {
          System.out.println("\"" + ii + "\""); //$NON-NLS-1$ //$NON-NLS-2$
          System.out.println(" " + info._staticPreferred); //$NON-NLS-1$
          System.out.println(" " + info._staticValue); //$NON-NLS-1$
          System.out.println(" " + info._staticFrames); //$NON-NLS-1$
        } else {
          unknownProjects++;
        }
      }
    }
    System.out.println("Unknown: " + unknownProjects); //$NON-NLS-1$
  }

  private void checkActiveMissing() {
    // Beta projects
    boolean separator = false;
    for (int ii = 0; ii < _projectInfo.size(); ii++) {
      Information info = getInfo(ii);
      if (info != null) {
        if ((info._psName != null) &&
            ((info._staticFile == null) || (!info._staticFile.contains("xyz")))) {
          if ((info._staticCore == null) || (info._staticCore.hasFile())) {
            if (!separator) {
              outputText("Active missing beta projects (.xyz): ");
            }
            outputInfo("", "p" + ii, separator);
            separator = true;
          }
        }
      }
    }
    if (separator) {
      outputNewLine();
    }
    separator = false;
    for (int ii = 0; ii < _projectInfo.size(); ii++) {
      Information info = getInfo(ii);
      if (info != null) {
        if ((info._psName != null) &&
            ((info._staticFile == null) || (!info._staticFile.contains("top")))) {
          if (info._staticCore == CoreType.AMBER) {
            if (!separator) {
              outputText("Active missing amber beta projects (.top + .trj): ");
            }
            outputInfo("", "p" + ii, separator);
            separator = true;
          }
        }
      }
    }
    if (separator) {
      outputNewLine();
    }

    // Public projects
    separator = false;
    for (int ii = 0; ii < _projectInfo.size(); ii++) {
      Information info = getInfo(ii);
      if (info != null) {
        if ((info._psName != null) &&
            ((info._staticFile == null) || (!info._staticFile.contains("xyz"))) &&
            (Boolean.TRUE.equals(info._staticPublic))) {
          if ((info._staticCore == null) || (info._staticCore.hasFile())) {
            if (!separator) {
              outputText("Active missing public projects (.xyz): ");
            }
            outputInfo("", "p" + ii, separator);
            separator = true;
          }
        }
      }
    }
    if (separator) {
      outputNewLine();
    }
    separator = false;
    for (int ii = 0; ii < _projectInfo.size(); ii++) {
      Information info = getInfo(ii);
      if (info != null) {
        if ((info._psName != null) &&
            ((info._staticFile == null) || (!info._staticFile.contains("top"))) &&
            (Boolean.TRUE.equals(info._staticPublic))) {
          if (info._staticCore == CoreType.AMBER) {
            if (!separator) {
              outputText("Active missing amber public projects (.top + .trj): ");
            }
            outputInfo("", "p" + ii, separator);
            separator = true;
          }
        }
      }
    }
    if (separator) {
      outputNewLine();
    }
  }
  
  /**
   * Output a list of problems between fah-projects.xml and existing files
   * 
   * @param projectNumber Project number 
   */
  private void outputCurrentXyzProblems(int projectNumber) {
    // Get project informations
    Information info = getInfo(projectNumber);

    // Check for xyz file
    StringBuffer filePath = new StringBuffer();
    filePath.append("./projects/p"); //$NON-NLS-1$
    filePath.append(projectNumber);
    filePath.append(".xyz.gz"); //$NON-NLS-1$
    File file = new File(filePath.toString());
        
    if (file.exists()) {
      if ((info == null) || ((info._staticFile == null) || (!info._staticFile.contains("xyz")))) {
        System.out.println(
            "Missing xyz file in XML file for project " + //$NON-NLS-1$
            projectNumber);
      }
    } else {
      if ((info != null) && ((info._staticFile != null) && (info._staticFile.contains("xyz")))) {
        System.out.println(
            "Missing current.xyz file for project " + //$NON-NLS-1$
            projectNumber);
      }
    }

    // Check for amber file
    filePath.setLength(0);
    filePath.append("./projects/p"); //$NON-NLS-1$
    filePath.append(projectNumber);
    filePath.append(".top.gz"); //$NON-NLS-1$
    file = new File(filePath.toString());
        
    if (file.exists()) {
      if ((info == null) || ((info._staticFile == null) || (!info._staticFile.contains("top")))) {
        System.out.println(
            "Missing top file in XML file for project " + //$NON-NLS-1$
            projectNumber);
      }
    } else {
      if ((info != null) && ((info._staticFile != null) && (info._staticFile.contains("top")))) {
        System.out.println(
            "Missing top file for project " + //$NON-NLS-1$
            projectNumber);
      }
    }
  }
    
  /**
   * @param projectNumber Project number
   * @return Tells if there is missing static information for the project 
   */
  private boolean missingStaticInformation(int projectNumber) {
    boolean different = false;

    // Get project informations
    Information info = getInfo(projectNumber);
    if (info == null) {
      return false;
    }

    //Check for differences with EM
    if ((info._psPreferred == null) &&
        (info._staticPreferred == null) &&
        (info._emDeadline != null)) {
      different = true;
    }
    if ((info._psFrames == null) &&
        (info._staticFrames == null) &&
        (info._emFrames != null)) {
      different = true;
    }
    if ((info._psValue == null) &&
        (info._staticValue == null) &&
        (info._emValue != null)) {
      different = true;
    }

    //Check for differences with FCI
    if ((info._psAtoms == null) &&
        (info._staticAtoms == null) &&
        (info._fciAtoms != null) &&
        (info._fciAtoms.intValue() > 0)) {
      different = true;
    }
    if ((info._psContact == null) &&
        (info._staticContact == null) &&
        (info._fciContact != null) &&
        (!info._fciContact.equals("NA")) && //$NON-NLS-1$
        (!info._fciContact.equals("n/a"))) { //$NON-NLS-1$
      different = true;
    }
    if ((info._psCore == null) &&
        (info._staticCore == null) &&
        (info._fciCore != null)) {
      different = true;
    }
    if ((info._psDeadline == null) &&
        (info._staticDeadline == null) &&
        (info._fciDeadline != null)) {
      different = true;
    }
    if ((info._psFrames == null) &&
        (info._staticFrames == null) &&
        (info._fciFrames != null)) {
      different = true;
    }
    if ((info._psKfactor == null) &&
        (info._staticKfactor == null) &&
        (info._fciKfactor != null) &&
        (info._fciKfactor.doubleValue() != 0.0)) {
      different = true;
    }
    if ((info._psName == null) &&
        (info._staticName == null) &&
        (info._fciName != null)) {
      different = true;
    }
    if ((info._psPreferred == null) &&
        (info._staticPreferred == null) &&
        (info._fciPreferred != null)) {
      different = true;
    }
    if ((info._psServer == null) &&
        (info._staticServer == null) &&
        (info._fciServer != null)) {
      different = true;
    }
    if ((info._psValue == null) &&
        (info._staticValue == null) &&
        (info._fciValue != null)) {
      different = true;
    }

    //Check for differences with PS
    if ((info._psAtoms != null) && (!info._psAtoms.equals(info._staticAtoms))) {
      different = true;
    }
    if ((info._psContact != null) &&
        (!info._psContact.equals("NA")) && //$NON-NLS-1$
        (!info._psContact.equals(info._staticContact))) {
      different = true;
    }
    if ((info._psCore != null) && (info._psCore != info._staticCore)) {
      different = true;
    }
    if ((info._psDeadline != null) && (!info._psDeadline.equals(info._staticDeadline))) {
      different = true;
    }
    if ((info._psFrames != null) && (!info._psFrames.equals(info._staticFrames))) {
      different = true;
    }
    if ((info._psName != null) &&
        (!info._psName.equals(info._staticName))) {
      int sepPosition = info._psName.indexOf('_');
      if (((sepPosition == info._psName.length() - 1) || (sepPosition == -1)) &&
          (info._staticName != null) &&
          (info._staticName.startsWith(info._psName))) {
        //
      } else {
        different = true;
      }
    }
    if ((info._psPreferred != null) && (!info._psPreferred.equals(info._staticPreferred))) {
      different = true;
    }
    if ((info._psServer != null) && (!info._psServer.equals(info._staticServer))) {
      different = true;
    }
    if ((info._psValue != null) && (!info._psValue.equals(info._staticValue))) {
      different = true;
    }
    if (Boolean.TRUE.equals(info._psPublic) && !Boolean.TRUE.equals(info._staticPublic)) {
      different = true;
    }
    if ((info._psKfactor != null) && (!info._psKfactor.equals(info._staticKfactor))) {
      if ((info._psKfactor.doubleValue() != 0.0) || (info._staticKfactor != null)) {
        different = true;
      }
    }

    //Check for differences with QD
    if ((info._psValue == null) &&
        (info._staticValue == null) &&
        (info._qdValue != null)) {
      different = true;
    }

    return different;
  }

  /**
   * Output text
   * 
   * @param text Text
   */
  private void outputText(String text) {
    System.out.print(text);
  }

  /**
   * Output text
   * 
   * @param text Text
   */
  private void outputTextLn(String text) {
    System.out.println(text);
  }

  /**
   * Output a new line
   */
  private void outputNewLine() {
    System.out.println();
  }

  /**
   * Output information
   * 
   * @param type Type of information
   * @param object Object to output
   * @param separator Indicate if a separator is to be outputed before
   */
  private void outputInfo(String type, Object object, boolean separator) {
    if (object == null) {
      return;
    }
    if (separator) {
      outputText(", "); //$NON-NLS-1$
    }
    outputText(type);
    outputText(object.toString());
  }

  /**
   * Output static information
   * 
   * @param object Object to output
   * @param separator Indicate if a separator is to be outputed before
   */
  private void outputInfoS(Object object, boolean separator) {
    outputInfo(_txtS, object, separator);
  }

  /**
   * Output psummary information
   * 
   * @param object Object to output
   * @param separator Indicate if a separator is to be outputed before
   */
  private void outputInfoPS(Object object, boolean separator) {
    outputInfo(_txtPS, object, separator);
  }

  /**
   * Output EM information
   * 
   * @param object Object to output
   * @param separator Indicate if a separator is to be outputed before
   */
  private void outputInfoEM(Object object, boolean separator) {
    outputInfo(_txtEM, object, separator);
  }

  /**
   * Output FCI information
   * 
   * @param object Object to output
   * @param separator Indicate if a separator is to be outputed before
   */
  private void outputInfoFCI(Object object, boolean separator) {
    outputInfo(_txtFCI, object, separator);
  }

  /**
   * Output QD information
   * 
   * @param object Object to output
   * @param separator Indicate if a separator is to be outputed before
   */
  private void outputInfoQD(Object object, boolean separator) {
    outputInfo(_txtQD, object, separator);
  }

  /**
   * Outputs missing static informations
   * 
   * @param projectNumber Project number
   */
  private void outputMissingStaticInformation(int projectNumber) {
    // Get project informations
    Information info = getInfo(projectNumber);
    if (info == null) {
      return;
    }

    outputText("Differences for project "); //$NON-NLS-1$
    outputTextLn(Integer.toString(projectNumber));

    // Check for unknown project
    if ((info._staticAtoms == null) &&
        (info._staticContact == null) &&
        (info._staticCore == null) &&
        (info._staticDeadline == null) &&
        (info._staticFile == null) &&
        (info._staticFrames == null) &&
        (info._staticKfactor == null) &&
        (info._staticName == null) &&
        (info._staticPreferred == null) &&
        (info._staticPublic == null) &&
        (info._staticServer == null) &&
        (info._staticValue == null)) {
      outputText("  <fah_proj number=\"" + Integer.toString(projectNumber) + "\"");
      if (info._psName != null) {
        outputText(" name=\"" + info._psName + "\"");
      } else if (info._fciName != null) {
        outputText(" name=\"" + info._fciName + "\"");
      }
      if (info._psPublic != null) {
        outputText(" public=\"" + (Boolean.TRUE.equals(info._psPublic) ? "y" : "n") + "\"");
      } else {
        outputText(" public=\"n\"");
      }
      outputTextLn("");
      outputText("   ");
      if (info._psServer != null) {
        outputText(" server=\"" + info._psServer + "\"");
      } else if (info._fciServer != null) {
        outputText(" server=\"" + info._fciServer + "\"");
      }
      if (info._psAtoms != null) {
        outputText(" atoms=\"" + info._psAtoms + "\"");
      } else if (info._fciAtoms != null) {
        outputText(" atoms=\"" + info._fciAtoms + "\"");
      }
      if (info._psPreferred != null) {
        if (info._psPreferred.intValue() % 86400 == 0) {
          outputText(" preferred=\"" + (info._psPreferred.intValue() / 86400) + "\"");
        } else {
          outputText(" preferred=\"" + (info._psPreferred.doubleValue() / 86400) + "\"");
        }
      } else if (info._fciPreferred != null) {
        if (info._fciPreferred.intValue() % 86400 == 0) {
          outputText(" preferred=\"" + (info._fciPreferred.intValue() / 86400) + "\"");
        } else {
          outputText(" preferred=\"" + (info._fciPreferred.doubleValue() / 86400) + "\"");
        }
      }
      if (info._psDeadline != null) {
        if (info._psDeadline.intValue() % 86400 == 0) {
          outputText(" deadline=\"" + (info._psDeadline.intValue() / 86400) + "\"");
        } else {
          outputText(" deadline=\"" + (info._psDeadline.doubleValue() / 86400) + "\"");
        }
      } else if (info._fciDeadline != null) {
        if (info._fciDeadline.intValue() % 86400 == 0) {
          outputText(" deadline=\"" + (info._fciDeadline.intValue() / 86400) + "\"");
        } else {
          outputText(" deadline=\"" + (info._fciDeadline.doubleValue() / 86400) + "\"");
        }
      }
      outputTextLn("");
      outputText("   ");
      if (info._psValue != null) {
        outputText(" credit=\"" + info._psValue + "\"");
      } else if (info._fciValue != null) {
        outputText(" credit=\"" + info._fciValue + "\"");
      }
      if (info._psFrames != null) {
        outputText(" frames=\"" + info._psFrames + "\"");
      } else if (info._fciFrames != null) {
        outputText(" frames=\"" + info._fciFrames + "\"");
      }
      if (info._psCore != null) {
        outputText(" code=\"" + info._psCore.getCode() + "\"");
      } else if (info._fciCore != null) {
        outputText(" code=\"" + info._fciCore.getCode() + "\"");
      }
      if (info._psContact != null) {
        outputText(" contact=\"" + info._psContact + "\"");
      } else if (info._fciContact != null) {
        outputText(" contact=\"" + info._fciContact + "\"");
      }
      if (info._psKfactor != null) {
        outputText(" kfactor=\"" + info._psKfactor + "\"");
      } else if (info._fciKfactor != null) {
        outputText(" kfactor=\"" + info._fciKfactor + "\"");
      }
      outputTextLn("/>");
      return;
    }

    //Print names difference
    boolean nameDifferent = false;
    if (info._psName != null) {
      if (!info._psName.equals(info._staticName)) {
        nameDifferent = true;
      }
    } else if (info._staticName == null) {
      if (info._fciName != null) {
        nameDifferent = true;
      }
    }
    if (nameDifferent) {
      outputText("  Name: "); //$NON-NLS-1$
      boolean separator = false;
      if (info._staticName != null) {
        outputInfoS(info._staticName, separator);
        separator = true;
      }
      if (info._fciName != null) {
        outputInfoFCI(info._fciName, separator);
        separator = true;
      }
      if (info._psName != null) {
        outputInfoPS(info._psName, separator); //$NON-NLS-1$
        separator = true;
      }
      outputNewLine();
    }

    //Print server difference
    boolean serverDifferent = false;
    if (info._psServer != null) {
      if (!info._psServer.equals(info._staticServer)) {
        serverDifferent = true;
      }
    } else if (info._staticServer == null) {
      if (info._fciServer != null) {
        serverDifferent = true;
      }
    }
    if (serverDifferent) {
      outputText("  Server: "); //$NON-NLS-1$
      boolean separator = false;
      if (info._staticServer != null) {
        outputInfoS(info._staticServer, separator);
        separator = true;
      }
      if (info._fciServer != null) {
        outputInfoFCI(info._fciServer, separator);
        separator = true;
      }
      if (info._psServer != null) {
        outputInfoPS(info._psServer, separator);
        separator = true;
      }
      outputNewLine();
    }

    //Print atoms difference
    boolean atomsDifferent = false;
    if (info._psAtoms != null) {
      if (!info._psAtoms.equals(info._staticAtoms)) {
        atomsDifferent = true;
      }
    } else if (info._staticAtoms == null) {
      if (info._fciAtoms != null) {
        atomsDifferent = true;
      }
    }
    if (atomsDifferent) {
      outputText("  Atoms: "); //$NON-NLS-1$
      boolean separator = false;
      if (info._staticAtoms != null) {
        outputInfoS(info._staticAtoms, separator);
        separator = true;
      }
      if (info._fciAtoms != null) {
        outputInfoFCI(info._fciAtoms, separator);
        separator = true;
      }
      if (info._psAtoms != null) {
        outputInfoPS(info._psAtoms, separator);
        separator = true;
      }
      outputNewLine();
    }

    //Print preferred difference
    boolean preferredDifferent = false;
    if (info._psPreferred != null) {
      if (!info._psPreferred.equals(info._staticPreferred)) {
        preferredDifferent = true;
      }
    } else if (info._staticPreferred == null) {
      if ((info._emDeadline != null) ||
          (info._fciPreferred != null)) {
        preferredDifferent = true;
      }
    }
    if (preferredDifferent) {
      outputText("  Preferred: "); //$NON-NLS-1$
      boolean separator = false;
      if (info._staticPreferred != null) {
        outputInfoS(info._staticPreferred, separator);
        separator = true;
      }
      if (info._emDeadline != null) {
        outputInfoEM(info._emDeadline, separator);
        separator = true;
      }
      if (info._fciPreferred != null) {
        outputInfoFCI(info._fciPreferred, separator);
        outputText(" "); //$NON-NLS-1$
        outputText(Double.toString(((double) (info._fciPreferred.intValue() / 864)) / 100));
        separator = true;
      }
      if (info._psPreferred != null) {
        outputInfoPS(info._psPreferred, separator);
        outputText(" "); //$NON-NLS-1$
        outputText(Double.toString(((double) (info._psPreferred.intValue() / 864)) / 100));
        separator = true;
      }
      outputNewLine();
    }

    //Print deadline difference
    boolean deadlineDifferent = false;
    if (info._psDeadline != null) {
      if (!info._psDeadline.equals(info._staticDeadline)) {
        deadlineDifferent = true;
      }
    } else if (info._staticDeadline == null) {
      if (info._fciDeadline != null) {
        deadlineDifferent = true;
      }
    }
    if (deadlineDifferent) {
      outputText("  Deadline: "); //$NON-NLS-1$
      boolean separator = false;
      if (info._staticDeadline != null) {
        outputInfoS(info._staticDeadline, separator);
        separator = true;
      }
      if (info._fciDeadline != null) {
        outputInfoFCI(info._fciDeadline, separator);
        outputText(" "); //$NON-NLS-1$
        outputText(Double.toString(((double) (info._fciDeadline.intValue() / 864)) / 100));
        separator = true;
      }
      if (info._psDeadline != null) {
        outputInfoPS(info._psDeadline, separator);
        outputText(" "); //$NON-NLS-1$
        outputText(Double.toString(((double) (info._psDeadline.intValue() / 864)) / 100));
        separator = true;
      }
      outputNewLine();
    }

    //Print points difference
    boolean pointsDifferent = false;
    if (info._psValue != null) {
      if (!info._psValue.equals(info._staticValue)) {
        pointsDifferent = true;
      }
    } else if (info._staticValue == null) {
      if ((info._emValue != null) ||
          (info._fciValue != null) ||
          (info._qdValue != null)) {
        pointsDifferent = true;
      }
    }
    if (pointsDifferent) {
      outputText("  Points: "); //$NON-NLS-1$
      boolean separator = false;
      if (info._staticValue != null) {
        outputInfoS(info._staticValue, separator);
        separator = true;
      }
      if (info._emValue != null) {
        outputInfoEM(info._emValue, separator);
        separator = true;
      }
      if (info._fciValue != null) {
        outputInfoFCI(info._fciValue, separator);
        separator = true;
      }
      if (info._psValue != null) {
        outputInfoPS(info._psValue, separator);
        separator = true;
      }
      if (info._qdValue != null) {
        outputInfoQD(info._qdValue, separator);
        separator = true;
      }
      outputNewLine();
    }

    //Print frames difference
    boolean framesDifferent = false;
    if (info._psFrames != null) {
      if (!info._psFrames.equals(info._staticFrames)) {
        framesDifferent = true;
      }
    } else if (info._staticFrames == null) {
      if ((info._emFrames != null) ||
          (info._fciFrames != null)) {
        framesDifferent = true;
      }
    }
    if (framesDifferent) {
      outputText("  Frames: "); //$NON-NLS-1$
      boolean separator = false;
      if (info._staticFrames != null) {
        outputInfoS(info._staticFrames, separator);
        separator = true;
      }
      if (info._emFrames != null) {
        outputInfoEM(info._emFrames, separator);
        separator = true;
      }
      if (info._fciFrames != null) {
        outputInfoFCI(info._fciFrames, separator);
        separator = true;
      }
      if (info._psFrames != null) {
        outputInfoPS(info._psFrames, separator);
        separator = true;
      }
      outputNewLine();
    }

    //Print core difference
    boolean coreDifferent = false;
    if (info._psCore != null) {
      if (!info._psCore.equals(info._staticCore)) {
        coreDifferent = true;
      }
    } else if (info._staticCore == null) {
      if (info._fciCore != null) {
        coreDifferent = true;
      }
    }
    if (coreDifferent) {
      outputText("  Core: "); //$NON-NLS-1$
      boolean separator = false;
      if ((info._staticCore != null) &&
          (info._staticCore != CoreType.UNKNOWN)) {
        outputInfoS(info._staticCore.getName(), separator);
        separator = true;
      }
      if (info._fciCore != null) {
        outputInfoFCI(info._fciCore.getName(), separator);
        separator = true;
      }
      if (info._psCore != null) {
        outputInfoPS(info._psCore.getName(), separator);
        separator = true;
      }
      outputNewLine();
    }

    //Print contact difference
    boolean contactDifferent = false;
    if (info._psContact != null) {
      if ((!info._psContact.equals("NA")) && //$NON-NLS-1$
          (!info._psContact.equals(info._staticContact))) {
        contactDifferent = true;
      }
    } else if (info._staticContact == null) {
      if ((info._fciContact != null) &&
          (!info._fciContact.equals("NA")) && //$NON-NLS-1$
          (!info._fciContact.equals("n/a"))) { //$NON-NLS-1$
        contactDifferent = true;
      }
    }
    if (contactDifferent) {
      outputText("  Contact: "); //$NON-NLS-1$
      boolean separator = false;
      if (info._staticContact != null) {
        outputInfoS(info._staticContact, separator);
        separator = true;
      }
      if (info._fciContact != null) {
        outputInfoFCI(info._fciContact, separator);
        separator = true;
      }
      if (info._psContact != null) {
        outputInfoPS(info._psContact, separator);
        separator = true;
      }
      outputNewLine();
    }
    
    //Print kfactor difference
    boolean kfactorDifferent = false;
    if (info._psKfactor != null) {
      if ((!info._psKfactor.equals(info._staticKfactor)) &&
          ((info._psKfactor.doubleValue() != 0.0) || (info._staticKfactor != null))) {
        kfactorDifferent = true;
      }
    } else if (info._staticKfactor == null) {
      if ((info._fciKfactor != null) && (info._fciKfactor.doubleValue() != 0.0)) {
        kfactorDifferent = true;
      }
    }
    if (kfactorDifferent) {
      outputText("  Kfactor: "); // $NON-NLS-1$
      boolean separator = false;
      if (info._staticKfactor != null) {
        outputInfoS(info._staticKfactor, separator);
        separator = true;
      }
      if (info._fciKfactor != null) {
        outputInfoFCI(info._fciKfactor, separator);
        separator = true;
      }
      if (info._psKfactor != null) {
        outputInfoPS(info._psKfactor, separator);
        separator = true;
      }
      outputNewLine();
    }
    //Print public difference
    if (Boolean.TRUE.equals(info._psPublic) && !Boolean.TRUE.equals(info._staticPublic)) {
        outputText("  Public");
        outputNewLine();
      }
  }

  /**
   * Main enabling checking between sources of information
   * 
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    //Retrieve instance
    _local = false;
    ProjectInformation projectInfo = getInstance();

    //Print current date / time
    /*
    projectInfo.outputTextLn("Dates"); //$NON-NLS-1$
    projectInfo.outputTextLn("  EM    :" + Long.toString(_info._emDate)); //$NON-NLS-1$
    projectInfo.outputTextLn("  PS    :" + Long.toString(_info._psDate)); //$NON-NLS-1$
    projectInfo.outputTextLn("  QD    :" + Long.toString(_info._qdDate)); //$NON-NLS-1$
    projectInfo.outputTextLn("  Static:" + Long.toString(_info._staticDate)); //$NON-NLS-1$
    projectInfo.outputTextLn("  Now   :" + Long.toString(new Date().getTime())); //$NON-NLS-1$
    */

    //Ouput emprotz.dat format
    if (_outputEmprotz) {
      projectInfo.outputEmprotzDatFile();
    }

    //Check for differences in static data
    for (int ii = 0; ii < projectInfo._projectInfo.size(); ii++) {
      if (projectInfo.missingStaticInformation(ii)) {
        projectInfo.outputMissingStaticInformation(ii);
      }
    }

    // Check for problems in current.xyz files
    if (_checkCurrentXyzFiles) {
      for (int ii = 0; ii < projectInfo._projectInfo.size(); ii++) {
        projectInfo.outputCurrentXyzProblems(ii);
      }
    }
    
    // Check for missing current.xyz files for active projects
    if (_checkActiveMissing) {
      projectInfo.checkActiveMissing();
    }
  }
}
