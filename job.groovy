import jenkins.model.*
import org.jenkinsci.plugins.workflow.multibranch.WorkflowMultiBranchProject
import jenkins.plugins.git.*

def mb_pipeline = new WorkflowMultiBranchProject(Jenkins.get(), 'hallo')
//def scm = new GitSCMSource('https://github.com/comquent/spring-petclinic.git')
//mb_pipeline.getSCMSources().add(scm)

Jenkins.get().reload()