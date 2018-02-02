import org.jenkinsci.plugins.docker.commons.tools.DockerTool

def dockerInstallation = new DockerTool("docker", "/usr/bin", null)
dockerDescriptor.setInstallations(dockerInstallation)