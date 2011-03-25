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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

/**
 * Manage contributions informations
 */
public class Contribution implements Serializable {

  // Contributions
  private Vector _infos = null;

  private final static int PAGES_COUNT = 6;
  private final static int PROJECTS_BY_PAGE = 1000;

  /**
   * @return Contribution singleton
   */
  public static Contribution getContribution() {
    if (_contrib == null) {
      _contrib = new Contribution();
    }
    return _contrib;
  }

  /**
   * Constructor for Contribution
   */
  private Contribution() {
    _infos = new Vector(PAGES_COUNT * PROJECTS_BY_PAGE);
  }

  /**
   * Add information from stanford stats site
   * 
   * @param userName User name
   * @param teamNum Team number
   */
  public void addInformation(String userName, int teamNum) {
    for (int i = 0; i < PAGES_COUNT; i++) {
      addInformation(userName, teamNum, i * PROJECTS_BY_PAGE);
    }
  }

  /**
   * Add information from stanford stats site for a range
   * 
   * @param userName User name
   * @param teamNum Team number
   * @param range Range
   */
  private void addInformation(String userName, int teamNum, int range) {
    //Load new information
    StringBuffer urlName = new StringBuffer();
    urlName.append("http://fah-web.stanford.edu/"); //$NON-NLS-1$
    urlName.append("cgi-bin/main.py?qtype=userpagedet"); //$NON-NLS-1$
    urlName.append("&username="); //$NON-NLS-1$
    urlName.append(userName);
    urlName.append("&teamnum="); //$NON-NLS-1$
    urlName.append(teamNum);
    urlName.append("&prange="); //$NON-NLS-1$
    urlName.append(range);
    try {
      URL url = new URL(urlName.toString());
      InputStream stream = url.openStream();
      InputStreamReader reader = new InputStreamReader(stream);
      HTMLDocument htmlDoc = new HTMLDocumentContribution(this);
      HTMLEditorKit htmlEditor = new HTMLEditorKit() {
        protected HTMLEditorKit.Parser getParser() {
          return new ParserDelegator() {
            public void parse(Reader r,
                              HTMLEditorKit.ParserCallback cb,
                              boolean ignoreCharSet) throws IOException {
              super.parse(r, cb, true);
            }
          };
        }
      };
      htmlEditor.read(reader, htmlDoc, 0);
    } catch (MalformedURLException mue) {
      mue.printStackTrace();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } catch (BadLocationException ble) {
      ble.printStackTrace();
    }
  }

  /**
   * Adds contribution informations for a project
   * 
   * @param project Project number
   * @param count Contributions
   */
  void addProjectInformation(String project, int count) {
    if ((project == null) || (project.length() == 0)) {
      System.out.println("Incorrect project: " + project);
      return;
    }
    int projectNum = 0;
    try {
      projectNum = Integer.parseInt(project.substring(1));
    } catch (NumberFormatException e) {
      System.out.println("Incorrect project: " + project);
    }
    Integer currentCount = null;
    if (projectNum < _infos.size()) {
      try {
        currentCount = (Integer) _infos.get(projectNum);
      } catch (ClassCastException e) {
        System.out.println("Error in infos: " + _infos.get(projectNum));
      }
    }
    Integer newCount = new Integer(count + (currentCount != null ? currentCount.intValue() : 0));
    if (projectNum >= _infos.size()) {
      _infos.setSize(projectNum + 1);
    }
    _infos.set(projectNum, newCount);
  }

  /**
   * Displays contribution informations 
   */
  public void displayContributions() {
    for (int i = 0; i < _infos.size(); i++) {
      try {
        Integer count = (Integer) _infos.get(i);
        if (count != null) {
          System.out.println("P" + i + "\t" + count);
        }
      } catch (ClassCastException e) {
        //
      }
    }
  }

  // Contribution (Singleton)
  private static Contribution _contrib;

  /**
   * HTML Document for Contribution page
   */
  private static class HTMLDocumentContribution extends HTMLDocument {

    private Contribution _contrib1 = null;

    public HTMLDocumentContribution(Contribution contrib) {
      super();
      _contrib1 = contrib;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.text.html.HTMLDocument#getReader(int)
     */
    public HTMLEditorKit.ParserCallback getReader(int pos) {
      return new ContributionReader(pos, _contrib1);
    }

    /**
     * Reader for Contribution
     */
    private class ContributionReader extends HTMLDocument.HTMLReader {

      private Contribution _contrib2;

      /**
       * @param offset
       * @param contrib
       */
      public ContributionReader(int offset, Contribution contrib) {
        super(offset);
        _contrib2 = contrib;
      }

      /*
       * @see javax.swing.text.html.HTMLEditorKit.ParserCallback#handleStartTag(
       *      javax.swing.text.html.HTML.Tag,
       *      javax.swing.text.MutableAttributeSet, int)
       */
      public void handleStartTag(HTML.Tag tag, MutableAttributeSet att, int pos) {
        if (tag.equals(HTML.Tag.TABLE)) {
          this._table++;
          this._tableNum++;
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
       * @see javax.swing.text.html.HTMLEditorKit.ParserCallback#handleText(
       *      char[],
       *      int)
       */
      public void handleText(char[] data, int pos) {
        if ((this._table > 0) && (this._row > 1) && (this._tableNum == 6)) {
          switch (this._column) {
          case 1: // Project
            this._project = new String(data);
            break;
          case 2: // Count
            this._count = Integer.parseInt(new String(data));
            break;
          }
        }
        super.handleText(data, pos);
      }

      /*
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
          if ((this._project != null) && (this._count > 0) && (_contrib2 != null)) {
            _contrib2.addProjectInformation(this._project, this._count);
          }
          this._column = 0;
          this._project = null;
          this._count = 0;
        }
        super.handleEndTag(tag, pos);
      }
	
      // Position
      private int    _column   = 0;
      private int    _row      = 0;
      private int    _table    = 0;
      private int    _tableNum = 0;

      // Current informations
      private String _project  = null;
      private int    _count    = 0;
    }
  }

  /**
   * Main enabling checking getting contribution informations
   * 
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    String userName = System.getProperty("org.jmol.fah.user");
    if (userName == null) {
      System.err.println("You must define org.jmol.fah.user");
      return;
    }
    String team = System.getProperty("org.jmol.fah.team");
    if (team == null) {
      System.err.println("You must define org.jmol.fah.team");
      return;
    }
    String[] teams = team.split(",");
    Contribution contrib = getContribution();
    for (int i = 0; i < teams.length; i++) {
      try {
        int teamNumber = Integer.parseInt(teams[i]);
        contrib.addInformation(userName, teamNumber); //$NON-NLS-1$
      } catch (NumberFormatException e) {
        System.err.println("org.jmol.fah.team must be a comma separated list of integers");
      }
    }
    contrib.displayContributions();
  }
}
