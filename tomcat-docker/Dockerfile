FROM tomcat:8.0-alpine

# Maintainer 
MAINTAINER aswgroup 

# Set username and password to access Tomcat admin page.
COPY ./tomcat-conf/tomcat-users.xml /usr/local/tomcat/conf/

# Permit remote access to Tomcat manager page
COPY ./tomcat-conf/manager-context.xml /usr/local/tomcat/webapps/manager/META-INF/

