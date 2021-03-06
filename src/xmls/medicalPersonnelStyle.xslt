<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />
<xsl:template match="/">
	<html>
	<head><title>Medical Personnel</title></head>
	<body>
		<p><b>Name:<xsl:value-of select="/medicalPersonnel/@name"/></b></p>
		<p>Department:<xsl:value-of select="/medicalPersonnel/@department"/></p>
		<p>Position:<xsl:value-of select="/medicalPersonnel/@position"/></p>
		<p>PatholgyId:<xsl:value-of select="/medicalPersonnel/@pathologyId"/></p>
	</body>	
	</html>
</xsl:template>
</xsl:stylesheet>
