<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

  <xsl:output method="text" indent="no"/>
  
  <xsl:template match="/">
    <xsl:apply-templates/>
  </xsl:template>

  <xsl:template match="fah_projects">
    <xsl:text><![CDATA[function createAllProjects(showList) {]]>
</xsl:text>
    <xsl:for-each select="fah_proj" >
      <xsl:value-of select="." />
      <xsl:if test="@number">
        <xsl:if test="@name">
          <xsl:text>addProject('</xsl:text>
          <!-- Project number -->
          <xsl:value-of select="@number" />
          <xsl:text>',</xsl:text>
          <!-- Project file name -->
          <xsl:choose>
            <xsl:when test="contains(@file, 'xyz')">
              <xsl:text>'p</xsl:text>
              <xsl:value-of select="@number" />
              <xsl:text>'</xsl:text>
            </xsl:when>
            <xsl:otherwise>
              <xsl:text>null</xsl:text>
            </xsl:otherwise>
          </xsl:choose>
          <xsl:text>,'</xsl:text>
          <!-- Project name -->
          <xsl:value-of select="@name" />
          <xsl:text>','</xsl:text>
          <!-- Credit -->
          <xsl:choose>
            <xsl:when test="@credit">
              <xsl:value-of select="@credit" />
            </xsl:when>
          </xsl:choose>
          <xsl:text>','</xsl:text>
          <!-- Atoms -->
          <xsl:choose>
            <xsl:when test="@atoms">
              <xsl:value-of select="@atoms" />
            </xsl:when>
          </xsl:choose>
          <xsl:text>','</xsl:text>
          <!-- Preferred -->
          <xsl:choose>
            <xsl:when test="@preferred">
              <xsl:value-of select="@preferred" />
            </xsl:when>
          </xsl:choose>
          <xsl:text>','</xsl:text>
          <!-- Deadline -->
          <xsl:choose>
            <xsl:when test="@deadline">
              <xsl:value-of select="@deadline" />
            </xsl:when>
          </xsl:choose>
          <xsl:text>','</xsl:text>
          <!-- Frames -->
          <xsl:choose>
            <xsl:when test="@frames">
              <xsl:value-of select="@frames" />
            </xsl:when>
          </xsl:choose>
          <xsl:text>','</xsl:text>
          <!-- Code -->
          <xsl:choose>
            <xsl:when test="@code">
              <xsl:value-of select="@code" />
            </xsl:when>
          </xsl:choose>
          <xsl:text>','</xsl:text>
          <!-- Public -->
          <xsl:choose>
            <xsl:when test="@public">
              <xsl:value-of select="@public" />
            </xsl:when>
          </xsl:choose>
          <xsl:text>','</xsl:text>
          <!-- Contrib -->
          <xsl:choose>
            <xsl:when test="@contrib">
              <xsl:value-of select="@contrib" />
            </xsl:when>
          </xsl:choose>
          <xsl:text>',showList);
</xsl:text>
        </xsl:if>
      </xsl:if>
    </xsl:for-each>
    <xsl:text><![CDATA[}]]>
</xsl:text>
    <xsl:text><![CDATA[function createMissingProjects() {]]>
</xsl:text>
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
                  <xsl:text>addMissingProject('</xsl:text>
                  <xsl:value-of select="@number" />
                  <xsl:text>');
</xsl:text>
                </xsl:otherwise>
              </xsl:choose>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:if>
      </xsl:if>
    </xsl:for-each>
    <xsl:text><![CDATA[}]]>
</xsl:text>
  </xsl:template>

</xsl:stylesheet>
