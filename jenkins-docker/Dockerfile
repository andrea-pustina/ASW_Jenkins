FROM jenkins/jenkins:lts

USER root

# Install gradle
RUN apt-get update && \ 
    apt-get -y install apt-utils openjdk-8-jdk wget unzip && \
    wget https://services.gradle.org/distributions/gradle-4.4.1-bin.zip && \
    mkdir /opt/gradle && \
    unzip -d /opt/gradle gradle-4.4.1-bin.zip

USER jenkins

# Complete gradle installation
RUN export PATH=$PATH:/opt/gradle/gradle-4.2.1/bin

# Create a jenkins user and fix security issues
COPY security.groovy /usr/share/jenkins/ref/init.groovy.d/security.groovy

# Install plugins
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

# Config credentials
COPY credentials.groovy /usr/share/jenkins/ref/init.groovy.d/credentials.groovy

# Gradle configuration in jenkins
COPY gradle_install.groovy /usr/share/jenkins/ref/init.groovy.d/gradle_install.groovy






