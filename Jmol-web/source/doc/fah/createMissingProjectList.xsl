<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

  <xsl:output method="text" indent="no"/>
  
  <xsl:template match="/">
    <xsl:apply-templates/>
  </xsl:template>

  <xsl:template match="fah_projects">
    <xsl:for-each select="fah_proj" >
      <xsl:value-of select="." />
      <xsl:if test="@number">
        <xsl:if test="@name">
          <!-- Project file name -->
          <xsl:choose>
            <xsl:when test="contains(@file, 'xyz')">
            </xsl:when>
            <xsl:otherwise>
              <xsl:choose>
                <xsl:when test="@code = 'AD'">
                </xsl:when>
                <xsl:when test="@code = 'G3'">
                </xsl:when>
                <xsl:when test="@code = 'GG'">
                </xsl:when>
                <xsl:when test="@code = 'GG2'">
                </xsl:when>
                <xsl:when test="@code = 'ND'">
                </xsl:when>
                <xsl:otherwise>
                  <xsl:value-of select="@number" />
                  <xsl:text>
</xsl:text>
                </xsl:otherwise>
              </xsl:choose>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:if>
      </xsl:if>
    </xsl:for-each>
  </xsl:template>

</xsl:stylesheet>
