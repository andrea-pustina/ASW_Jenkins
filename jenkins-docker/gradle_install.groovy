#!groovy

import jenkins.model.*
import hudson.plugins.gradle.GradleInstallation

def instance = Jenkins.getInstance()
def gradle_inst_exists = false

def gradle4 = new hudson.plugins.gradle.GradleInstallation("gradle4", "/opt/gradle/gradle-4.4.1", [])
def gradleInstallationDescriptor = instance.getDescriptorByType(hudson.plugins.gradle.GradleInstallation.DescriptorImpl)

def installations = gradleInstallationDescriptor.getInstallations().toList()
installations.each {
  installation = (hudson.plugins.gradle.GradleInstallation) it
  if (gradle4.getName() == installation.getName()) {
    gradle_inst_exists = true
    println("found installation")
  }
}

if (!gradle_inst_exists) {
  installations.add(gradle4)
  gradleInstallationDescriptor.setInstallations((GradleInstallation[]) installations)
  gradleInstallationDescriptor.save()
}
