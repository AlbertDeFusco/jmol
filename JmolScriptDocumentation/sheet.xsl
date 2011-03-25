<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="xml" omit-xml-declaration="yes"/>

    <xsl:template match="document">
        <xsl:comment><xsl:text> Last updated: </xsl:text> <xsl:value-of select="./@lastupdated"/><xsl:text> </xsl:text></xsl:comment>
        <xsl:text>
</xsl:text>
        <xsl:comment><xsl:text> Automatically created from documentation on http://www.stolaf.edu/people/hansonr/jmol/docs/?xml
     as described in the README in the JmolScriptDocumentation module in CVS. </xsl:text></xsl:comment>
        <xsl:apply-templates select="cmdlist"/>
    </xsl:template>

    <xsl:template match="jmolcmd">
        <xsl:element name="section">
            <xsl:attribute name="id"><xsl:value-of select="cmdname/a/@id"/></xsl:attribute>
            <xsl:attribute name="xreflabel"><xsl:value-of select="cmdname/a"/></xsl:attribute>
            <title>
                <xsl:value-of select="cmdname/a"/>
            </title>
            <para>
                <xsl:value-of select="cmddescription"/>
            </para>
            <xsl:apply-templates select="cmdexamples"/>
            <xsl:apply-templates select="cmddefinitions"/>
            <xsl:apply-templates select="cmdxref"/>
            <xsl:apply-templates select="cmdscriptlist"/>
        </xsl:element>
    </xsl:template>
						
    <xsl:template match="cmdexamples">
        <xsl:if test="cmdexample">
            <variablelist>
                <title>Syntax</title>
                <xsl:for-each select="cmdexample">
                    <varlistentry>
                        <term>
                            <command><xsl:value-of select="cmdoption"/></command>
                        </term>
                        <listitem>
                            <para>
                                <xsl:value-of select="cmdlistidescription"/>
                            </para>
                            <xsl:for-each select="cmdscript">
                                <example>
                                  <title><xsl:value-of select="../cmdoption"/></title>
                                  <programlisting>
                                  <xsl:apply-templates select="."/>
                                  </programlisting>
                                </example>
                            </xsl:for-each>
                        </listitem>
                    </varlistentry>
                </xsl:for-each>
            </variablelist>
        </xsl:if>
    </xsl:template>
						
    <xsl:template match="cmddefinitions">
        <variablelist>
            <title>Definitions</title>
            <xsl:for-each select="cmddef">
                <varlistentry>
                    <term>
                        <option><xsl:value-of select="defkey"/></option>
                    </term>
                    <listitem>
                        <para>
                            <xsl:value-of select="defdata"/>
                        </para>
                    </listitem>
                </varlistentry>
            </xsl:for-each>
        </variablelist>
    </xsl:template>

    <xsl:template match="cmdxref">
      <xsl:text>See also: </xsl:text>
      <xsl:for-each select="seealso/a">
        <xsl:element name="xref">
          <xsl:attribute name="linkend"><xsl:value-of select="substring(@href,2)"/></xsl:attribute>
        </xsl:element>
        <xsl:text>, </xsl:text>
      </xsl:for-each>
    </xsl:template>

    <xsl:template match="cmdscriptlist">
        <para>Examples:</para>
        <programlisting>
          <xsl:for-each select="cmdscript">
            <xsl:value-of select="normalize-space(.)"/><xsl:text>
</xsl:text>
          </xsl:for-each>
        </programlisting>
    </xsl:template>

</xsl:stylesheet>


