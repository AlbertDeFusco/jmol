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

package org.jmol.fah.utils;

import javax.swing.SwingConstants;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Utility class to parse XML Values
 */
public class XMLValue {

  /**
   * Get a String value from a group of attributes
   * 
   * @param map Group of attributes
   * @param name Name of attribute
   * @param value Default value
   * @return String value of the attribute
   */
  public static String getString(NamedNodeMap map, String name, String value) {
    if ((map != null) && (name != null)) {
      Node node = map.getNamedItem(name);
      if (node != null) {
        value = node.getNodeValue();
      }
    }
    return value;
  }

  /**
   * Get a String value from a child of a node
   * 
   * @param parent Parent node
   * @param name Name of child
   * @param value Default value
   * @return String value of the attribute
   */
  public static String getString(Node parent, String name, String value) {
    if ((parent != null) && (name != null)) {
      value = getChildNodeValue(parent, name);
    }
    return value;
  }

  /**
   * Get an Integer value from a group of attributes
   * 
   * @param map Group of attributes
   * @param name Name of attribute
   * @return Integer value of the attribute
   */
  public static Integer getInteger(NamedNodeMap map, String name) {
    if ((map != null) && (name != null)) {
      Node node = map.getNamedItem(name);
      if (node != null) {
        try {
          return Integer.valueOf(node.getNodeValue());
        } catch (NumberFormatException e) {
          //
        }
      }
    }
    return null;
  }

  /**
   * Get an Integer value from a child of a node
   * 
   * @param parent Parent node
   * @param name Name of child
   * @return Integer value of the attribute
   */
  public static Integer getInteger(Node parent, String name) {
    if ((parent != null) && (name != null)) {
      String value = getChildNodeValue(parent, name);
      if (value != null) {
        try {
          return Integer.valueOf(value);
        } catch (NumberFormatException e) {
          //
        }
      }
    }
    return null;
  }

  /**
   * Get an Integer value from a group of attributes
   * 
   * @param map Group of attributes
   * @param name Name of attribute
   * @param mult Multiplicative factor
   * @return Integer value of the attribute
   */
  public static Integer getInteger(NamedNodeMap map, String name, int mult) {
    if ((map != null) && (name != null)) {
      Node node = map.getNamedItem(name);
      if (node != null) {
        try {
          double value = Double.parseDouble(node.getNodeValue());
          return new Integer((int)(value * mult));
        } catch (NumberFormatException e) {
          //
        }
      }
    }
    return null;
  }

  /**
   * Get an Integer value from a child of a node
   * 
   * @param parent Parent node
   * @param name Name of child
   * @param mult Multiplicative factor
   * @return Integer value of the attribute
   */
  public static Integer getInteger(Node parent, String name, int mult) {
    if ((parent != null) && (name != null)) {
      String value = getChildNodeValue(parent, name);
      if (value != null) {
        try {
          double dValue = Double.parseDouble(value);
          return new Integer((int)(dValue * mult));
        } catch (NumberFormatException e) {
          //
        }
      }
    }
    return null;
  }

  /**
   * Get a double value from a group of attributes
   * 
   * @param map Group of attributes
   * @param name Name of attribute
   * @return Double value of the attribute
   */
  public static Double getDouble(NamedNodeMap map, String name) {
    if ((map != null) && (name != null)) {
      Node node = map.getNamedItem(name);
      if (node != null) {
        try {
          return Double.valueOf(node.getNodeValue());
        } catch (NumberFormatException e) {
          //
        }
      }
    }
    return null;
  }

  /**
   * Get a double value from a child of a node
   * 
   * @param parent Parent node
   * @param name Name of child
   * @return Integer value of the attribute
   */
  public static Double getDouble(Node parent, String name) {
    if ((parent != null) && (name != null)) {
      String value = getChildNodeValue(parent, name);
      if (value != null) {
        try {
          return Double.valueOf(value);
        } catch (NumberFormatException e) {
          //
        }
      }
    }
    return null;
  }

  /**
   * Get a boolean value from a group of attributes
   * 
   * @param map Group of attributes
   * @param name Name of attribute
   * @return Boolean value of the attribute
   */
  public static Boolean getBoolean(NamedNodeMap map, String name) {
    if ((map != null) && (name != null)) {
      Node node = map.getNamedItem(name);
      if (node != null) {
        return getBoolean(node);
      }
    }
    return null;
  }

  /**
   * Get a boolean value (y/n) from a group of attributs
   * 
   * @param map Group of attributes
   * @param name Name of attributes
   * @return Boolean value of the attribute
   */
  public static Boolean getYesNo(NamedNodeMap map, String name) {
    if ((map != null) && (name != null)) {
      Node node = map.getNamedItem(name);
      if (node != null) {
        return getYesNo(node);
      }
    }
    return null;
  }

  /**
   * Get a SwingConstants value from a group of attributes
   * 
   * @param map Group of attributes
   * @param name Name of attribute
   * @param value Default value
   * @return SwingConstants value of the attribute
   */
  public static int getSwingConstants(NamedNodeMap map, String name, int value) {
    if ((map != null) && (name != null)) {
      Node node = map.getNamedItem(name);
      if (node != null) {
        value = getSwingConstants(node, value);
      }
    }
    return value;
  }

  /**
   * Transform a node into a boolean value
   * 
   * @param node Node which value is a boolean
   * @return Boolean value of the node
   */
  public static Boolean getBoolean(Node node) {
    if ((node != null) && (node.getNodeValue() != null)) {
      String text = node.getNodeValue().toLowerCase();
      if (text.equals("true")) { //$NON-NLS-1$
        return Boolean.TRUE;
      } else if (text.equals("false")) { //$NON-NLS-1$
        return Boolean.FALSE;
      }
    }
    return null;
  }

  /**
   * Transform a node into a boolean value (test for y/n)
   * 
   * @param node Node which value is a y/n
   * @return Boolean value of the node
   */
  public static Boolean getYesNo(Node node) {
    if ((node != null) && (node.getNodeValue() != null)) {
      String text = node.getNodeValue().toLowerCase();
      if (text.equals("y")) { //$NON-NLS-1$
        return Boolean.TRUE;
      } else if (text.equals("n")) { //$NON-NLS-1$
        return Boolean.FALSE;
      }
    }
    return null;
  }

  /**
   * Find a child node of a node
   * 
   * @param node Parent node
   * @param name Name of the child
   * @return Child
   */
  public static Node getChildNode(Node node, String name) {
    if ((node != null) && (name != null)) {
      Node child = node.getFirstChild();
      while (child != null) {
        if ((child.getLocalName() != null) &&
            (child.getLocalName().equalsIgnoreCase(name))) {
          return child;
        }
        child = child.getNextSibling();
      }
    }
    return null;
  }

  /**
   * Find a child node value of a node
   * 
   * @param node Parent node
   * @param name Name of the child
   * @return Value
   */
  public static String getChildNodeValue(Node node, String name) {
    Node child = getChildNode(node, name);
    if (child != null) {
      return getNodeValue(child);
    }
    return null;
  }

  /**
   * Find the value of a node
   * 
   * @param node Node
   * @return Value
   */
  public static String getNodeValue(Node node) {
    if (node != null) {
      switch (node.getNodeType()) {
      case Node.TEXT_NODE:
    	return node.getNodeValue();
      case Node.ELEMENT_NODE:
        return getNodeValue(node.getFirstChild());
      }
    }
    return null;
  }

  /**
   * Transform a node into a SwingConstants value
   * 
   * @param node Node which value is a SwingConstants
   * @param value Default value
   * @return SwingConstants value of the node
   */
  public static int getSwingConstants(Node node, int value) {
    if ((node != null) && (node.getNodeValue() != null)) {
      String text = node.getNodeValue().toLowerCase();
      if (text.equals("bottom")) { //$NON-NLS-1$
        value = SwingConstants.BOTTOM;
      } else if (text.equals("center")) { //$NON-NLS-1$
        value = SwingConstants.CENTER;
      } else if (text.equals("east")) { //$NON-NLS-1$
        value = SwingConstants.EAST;
      } else if (text.equals("horizontal")) { //$NON-NLS-1$
        value = SwingConstants.HORIZONTAL;
      } else if (text.equals("leading")) { //$NON-NLS-1$
        value = SwingConstants.LEADING;
      } else if (text.equals("left")) { //$NON-NLS-1$
        value = SwingConstants.LEFT;
      } else if (text.equals("next")) { //$NON-NLS-1$
        value = SwingConstants.NEXT;
      } else if (text.equals("north")) { //$NON-NLS-1$
        value = SwingConstants.NORTH;
      } else if (text.equals("northeast")) { //$NON-NLS-1$
        value = SwingConstants.NORTH_EAST;
      } else if (text.equals("northwest")) { //$NON-NLS-1$
        value = SwingConstants.NORTH_WEST;
      } else if (text.equals("previous")) { //$NON-NLS-1$
        value = SwingConstants.PREVIOUS;
      } else if (text.equals("right")) { //$NON-NLS-1$
        value = SwingConstants.RIGHT;
      } else if (text.equals("south")) { //$NON-NLS-1$
        value = SwingConstants.SOUTH;
      } else if (text.equals("southeast")) { //$NON-NLS-1$
        value = SwingConstants.SOUTH_EAST;
      } else if (text.equals("southwest")) { //$NON-NLS-1$
        value = SwingConstants.SOUTH_WEST;
      } else if (text.equals("top")) { //$NON-NLS-1$
        value = SwingConstants.TOP;
      } else if (text.equals("trailing")) { //$NON-NLS-1$
        value = SwingConstants.TRAILING;
      } else if (text.equals("vertical")) { //$NON-NLS-1$
        value = SwingConstants.VERTICAL;
      } else if (text.equals("west")) { //$NON-NLS-1$
        value = SwingConstants.WEST;
      }
    }
    return value;
  }
}
