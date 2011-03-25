<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE somethingOrAnything [
<!ENTITY style_fah SYSTEM "style_fah.xsl" >
]>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

  <xsl:output method="html" indent="yes"/>
  <xsl:param name="lang" select="'en'"/>
  <xsl:param name="project_xml" select="'project.xml'"/>
  
  <xsl:template match="document">
    <xsl:apply-templates/>
  </xsl:template>
  
  <xsl:template match="document/body">
    <xsl:if test="/document/properties/title">
      <br/>
      <h1><xsl:value-of select="/document/properties/title"/></h1>
    </xsl:if>
    <xsl:if test="header">
      <xsl:apply-templates select="header"/>
    </xsl:if>
    <xsl:variable name="numberOfSections" select="count(.//section)"/>
    <xsl:if test='$numberOfSections &gt; 1'>
    <xsl:for-each select=".//section">
      <small>
      <xsl:if test="@title">
        <xsl:variable name="level" select="count(ancestor::*)"/>
        <xsl:choose>
          <xsl:when test='$level=2'>
            <a href="#{@title}"><xsl:value-of select="@title"/></a><br/>
          </xsl:when>
          <xsl:when test='$level=3'>
            &#xA0;&#xA0;&#xA0;&#xA0;
            <a href="#{@title}"><xsl:value-of select="@title"/></a><br/>
          </xsl:when>
        </xsl:choose>
      </xsl:if>
      </small>
    </xsl:for-each>
    <br/>
    </xsl:if>
    <xsl:apply-templates select="section"/>
    <xsl:if test="footer">
      <br/>
      <xsl:apply-templates select="footer"/>
    </xsl:if>
  </xsl:template>

  <xsl:template match="header">
    <xsl:apply-templates/>
  </xsl:template>

  <!-- Process a section in the document. Nested sections are supported -->
  <xsl:template match="document//section">
    <xsl:variable name="level" select="count(ancestor::*)"/>
    <xsl:if test="@title">
      <xsl:choose>
        <xsl:when test='$level=2'>
          <h2><a name="{@title}"><xsl:value-of select="@title"/></a></h2>
        </xsl:when>
        <xsl:when test='$level=3'>
          <h3><a name="{@title}"><xsl:value-of select="@title"/></a></h3>
        </xsl:when>
        <xsl:when test='$level=4'>
          <h4><a name="{@title}"><xsl:value-of select="@title"/></a></h4>
        </xsl:when>
        <xsl:when test='$level>=5'>
          <h5><xsl:copy-of select="@title"/></h5>
        </xsl:when>
      </xsl:choose>
    </xsl:if>
    <blockquote>
      <xsl:apply-templates/>
    </blockquote>
  </xsl:template>

  <!-- Paragraphs are separated with one empty line -->
  <xsl:template match="p">
    <p><xsl:apply-templates/><br/></p>
  </xsl:template>

  <xsl:template match="url">
    <a href="{.}"><xsl:copy-of select="text()"/></a>
  </xsl:template>

  <xsl:template match="email">
    <a href="mailto:{.}"><xsl:copy-of select="."/></a>
  </xsl:template>

  <xsl:template match="/">
    <xsl:variable name="project" select="document($project_xml)/project"/>
    <xsl:text disable-output-escaping="yes">&lt;!DOCTYPE HTML PUBLIC </xsl:text>
    <xsl:text disable-output-escaping="yes">"-//W3C//DTD HTML 4.01 Transitional//EN" </xsl:text>
    <xsl:text disable-output-escaping="yes">"http://www.w3.org/TR/html4/loose.dtd"&gt;</xsl:text>
    <xsl:text disable-output-escaping="yes">&lt;html lang="</xsl:text>
    <xsl:value-of select="$lang" disable-output-escaping="yes"/>
    <xsl:text disable-output-escaping="yes">"&gt;</xsl:text>
    <head>
      <xsl:choose>
        <xsl:when test="/document/properties/title"><title><xsl:value-of select="/document/body/title"/></title></xsl:when>
        <xsl:when test="/document/body/title"><title><xsl:value-of select="/document/body/title"/></title></xsl:when>
        <xsl:otherwise><title><xsl:value-of select="$project/title"/></title></xsl:otherwise>
      </xsl:choose>
      <link rel="stylesheet" type="text/css" href="[root]/default.css"/>
      <script src="[root]/jmol/Jmol.js" type="text/javascript"></script>
    </head>

    <body>
      <script type="text/javascript">jmolInitialize("[root]/jmol");</script>
      <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
          <td valign="top">
            <xsl:apply-templates select="$project/links"/>
            <hr/>
          </td>
        </tr>

        <tr>
          <td>
            <h1>
              <xsl:choose>
                <xsl:when test="/document/body/title"><xsl:value-of select="/document/body/title"/></xsl:when>
                <xsl:otherwise><xsl:value-of select="$project/title"/></xsl:otherwise>
              </xsl:choose>
            </h1>
           </td>
        </tr>

        <tr>
          <td>
             <xsl:apply-templates select="document/body"/>
             <br/>
             <hr/>
          </td>
        </tr>

        <xsl:if test="$project/notice">
          <tr>
            <td align="center">
              <xsl:for-each select="$project/notice">
                <xsl:copy-of select="./*"/>
              </xsl:for-each>
            </td>
          </tr>
        </xsl:if>

      </table>

    </body>
	<xsl:text disable-output-escaping="yes">&lt;/html&gt;</xsl:text>
  </xsl:template>

  <xsl:template match="links">
    <xsl:apply-templates/>
  </xsl:template>

  <!-- UL is processed into a table using graphical bullets -->
<!--
  <xsl:template match="ul">
    <table border="0" cellpadding="2" cellspacing="2">
      <tr><td colspan="2" height="5"></td></tr>
      <xsl:apply-templates/>
    </table>
  </xsl:template>
-->

<!--
  <xsl:template match="ul/li">
    <tr>
      <td align="left" valign="top">
        <img src="images/blueball.gif" alt="*"/>
     </td>
      <td align="left" valign="top"><xsl:apply-templates/></td>
    </tr>
  </xsl:template>
-->

  <xsl:template match="screenshots">
    <table border="0" cellpadding="2" cellspacing="2">
      <tr><td colspan="2" height="5"></td></tr>
      <xsl:apply-templates/>
    </table>
  </xsl:template>
  
  <xsl:template match="screenshot">
    <tr>
      <td align="left" valign="middle">
        <a href="{@image}">
        <img src="{@image}" width="200" alt="Jmol screenshot"/>
        </a>
     </td>
      <td align="left" valign="middle"><xsl:apply-templates/></td>
    </tr>
  </xsl:template>

  <xsl:template match="appletParameterList">
    <table border="1" cellpadding="2" cellspacing="2">
      <tr>
        <th align="center" valign="middle">
        NAME=
       </th>
        <th align="center" valign="middle">VALUE=</th>
      </tr>
      <xsl:apply-templates/>
    </table>
  </xsl:template>
  
  <xsl:template match="appletParameterList/param">
    <tr>
      <td align="center" valign="top">
      <xsl:value-of select="@name"/>
     </td>
      <td align="left" valign="top"><xsl:apply-templates/></td>
    </tr>
  </xsl:template>

  <xsl:template match="section">
    <xsl:if test="@title">
      <br />
    </xsl:if>
  </xsl:template>

  <xsl:template match="br">
    <br />
  </xsl:template>

  <xsl:template match='@* | node()'>
    <xsl:copy>
      <xsl:apply-templates select='@* | node()'/>
    </xsl:copy>
  </xsl:template>

  <xsl:template match="appletExample">
    <pre>
    <xsl:value-of select='@* | node()'/>
    </pre>
    <xsl:value-of disable-output-escaping="yes" select='@* | node()'/>
  </xsl:template>


  <xsl:template match="contributors">
    <blockquote><xsl:text>
    </xsl:text>
    <xsl:for-each select="contributor" >
    <xsl:value-of select="name" />
    (<a href="mailto:{email}"><xsl:value-of select="email" /></a>)<br/><xsl:text>
    </xsl:text>
    </xsl:for-each>
    </blockquote>
  </xsl:template>

  <xsl:template match="changes">
    <h3><xsl:value-of select="@version" /></h3>
    <ul>
    <xsl:for-each select="change" >
      <li>
      <xsl:value-of select="." /><xsl:if test="@contributor"> (<xsl:value-of select="@
      contributor" />)</xsl:if>
      </li>
    </xsl:for-each>
    </ul>
  </xsl:template>

  &style_fah;

</xsl:stylesheet>
