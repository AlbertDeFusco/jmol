  <xsl:template match="fah_projects">
    <!-- <xsl:text>createAllProjects();</xsl:text> -->
  </xsl:template>
  
  <xsl:template match="fah_count_projects">
    <xsl:value-of select="count(//fah_projects/fah_proj[@name])"/>
  </xsl:template>
  
  <xsl:template match="fah_count_files">
    <xsl:value-of select="count(//fah_projects/fah_proj[@file])"/>
  </xsl:template>
