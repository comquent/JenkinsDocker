import jenkins.*
import jenkins.model.*
import hudson.model.*
import jenkins.model.Jenkins
import org.jenkinsci.plugins.authorizeproject.*
import org.jenkinsci.plugins.authorizeproject.strategy.*
import jenkins.security.QueueItemAuthenticatorConfiguration

def instance = Jenkins.getInstance()

// Define which strategies you want to allow to be set per project
def strategyMap = [
  (instance.getDescriptor(AnonymousAuthorizationStrategy.class).getId()): true, 
  (instance.getDescriptor(TriggeringUsersAuthorizationStrategy.class).getId()): true,
  (instance.getDescriptor(SpecificUsersAuthorizationStrategy.class).getId()): true,
  (instance.getDescriptor(SystemAuthorizationStrategy.class).getId()): false
]

def authenticators = QueueItemAuthenticatorConfiguration.get().getAuthenticators()
def configureProjectAuthenticator = true
for(authenticator in authenticators) {
  if(authenticator instanceof ProjectQueueItemAuthenticator) {
    // only add if it does not already exist
    configureProjectAuthenticator = false
  }
}

if(configureProjectAuthenticator) {
  authenticators.add(new ProjectQueueItemAuthenticator(strategyMap))
}

instance.save()
