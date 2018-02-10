import jenkins.model.*
import hudson.model.*
import org.jenkinsci.plugins.workflow.multibranch.WorkflowMultiBranchProject
import hudson.plugins.git.GitSCM
import org.jenkinsci.plugins.pipeline.modeldefinition.config.FolderConfig
import javaposse.jobdsl.plugin.ExecuteDslScripts

def job = Jenkins.get().createProject(FreeStyleProject, 'seed-job')
def builder = new ExecuteDslScripts([targets: 'jobs.seed'])
def scm = new GitSCM('xxx')
job.scm = scm
job.buildersList.add(builder)

job.save()
Jenkins.get().reload()

job.build()
