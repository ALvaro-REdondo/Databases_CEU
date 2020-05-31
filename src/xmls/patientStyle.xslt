<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
	<html>
	<head><title>Patient</title></head>
	<body>
		<p><b>Name:<xsl:value-of select="/patient/@name"/></b></p>
		<p>Gender<xsl:value-of select="/patient/@gender"/></p>
		<p>State:<xsl:value-of select="/patient/@state"/></p>
		<p>Date of birth:<xsl:value-of select="/patient/@dob"/></p>
        <p>Pathology Id:<xsl:value-of select="/patient/@pathology_id"/></p>
        <p>Clinical history Id:<xsl:value-of select="/patient/@clinicalHistory_id"/></p>
	</body>
	
	</html>


</xsl:template>
</xsl:stylesheet>