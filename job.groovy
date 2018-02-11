import jenkins.model.*
import hudson.model.*
import org.jenkinsci.plugins.workflow.multibranch.WorkflowMultiBranchProject
import hudson.plugins.git.GitSCM
import org.jenkinsci.plugins.pipeline.modeldefinition.config.FolderConfig
import javaposse.jobdsl.plugin.ExecuteDslScripts
import org.jvnet.hudson.plugins.triggers.startup.HudsonStartupTrigger

def job = Jenkins.get().createProject(FreeStyleProject, 'seed-job')

def scm = new GitSCM('${SCM_URL}')
job.scm = scm

def builder = new ExecuteDslScripts([targets: 'jobs.seed'])
job.buildersList.add(builder)

def trigger = new HudsonStartupTrigger('master', '0', '', '')
job.addTrigger(trigger)

job.save()
Jenkins.get().reload()

//Jenkins.get().queue.schedule(job)
