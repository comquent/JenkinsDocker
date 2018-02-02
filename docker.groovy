import jenkins.model.*
import org.jenkinsci.plugins.docker.commons.tools.DockerTool

def dockerInstallation = new DockerTool("docker", "/usr/bin", null)
def dockerDescriptor = Jenkins.instance.getDescriptorByName("org.jenkinsci.plugins.docker.commons.tools.DockerTool")
dockerDescriptor.setInstallations(dockerInstallation)