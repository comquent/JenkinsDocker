FROM jenkins/jenkins:latest

USER root

RUN apt-get update -qq && apt-get install -qqy apt-utils

RUN apt-get install -qqy \
    apt-transport-https \
    ca-certificates \
    curl \
    lxc \
    iptables \
    sudo

RUN curl -sSL https://get.docker.com/ | sh

RUN adduser jenkins sudo
RUN echo "jenkins ALL = NOPASSWD: /usr/bin/docker" >> /etc/sudoers
RUN cat /etc/sudoers

RUN mkdir /var/log/jenkins
RUN chown -R jenkins:jenkins /var/log/jenkins

USER jenkins

ENV JENKINS_USER admin
ENV JENKINS_PASS cq

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

COPY security.groovy /usr/share/jenkins/ref/init.groovy.d/security.groovy
COPY locale.groovy /usr/share/jenkins/ref/init.groovy.d/locale.groovy
COPY theme.groovy /usr/share/jenkins/ref/init.groovy.d/theme.goovy
COPY docker.groovy /usr/share/jenkins/ref/init.groovy.d/docker.goovy

#COPY plugins.txt /usr/share/jenkins/plugins.txt
#RUN /usr/local/bin/plugins.sh /usr/share/jenkins/plugins.txt

RUN /usr/local/bin/install-plugins.sh git docker matrix-auth simple-theme-plugin workflow-aggregator
