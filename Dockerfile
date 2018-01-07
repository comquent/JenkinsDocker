FROM jenkins:latest

ENV JENKINS_USER admin
ENV JENKINS_PASS cq

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

COPY security.groovy /usr/share/jenkins/ref/init.groovy.d/security.groovy
COPY theme.groovy /usr/share/jenkins/ref/init.groovy.d/theme.groovy

RUN /usr/local/bin/install-plugins.sh git docker matrix-auth simple-theme-plugin workflow-aggregator

