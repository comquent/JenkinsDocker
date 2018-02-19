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

COPY init.groovy.d/* /usr/share/jenkins/ref/init.groovy.d/

#COPY plugins.txt /usr/share/jenkins/plugins.txt
#RUN /usr/local/bin/plugins.sh /usr/share/jenkins/plugins.txt

RUN /usr/local/bin/install-plugins.sh\
 git\
 docker\
 matrix-auth\
 simple-theme-plugin:0.3\
 workflow-aggregator\
 job-dsl\
 startup-trigger-plugin\
 authorize-project
