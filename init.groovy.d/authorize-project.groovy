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
  (instance.getDescriptor(AnonymousAuthorizationStrategy.class).getId()): false, 
  (instance.getDescriptor(TriggeringUsersAuthorizationStrategy.class).getId()): true,
  (instance.getDescriptor(SpecificUsersAuthorizationStrategy.class).getId()): true,
  (instance.getDescriptor(SystemAuthorizationStrategy.class).getId()): true
]

def authenticators = QueueItemAuthenticatorConfiguration.get().getAuthenticators()
def configureProjectAuthenticator = true
def configureGlobalQueueItemAuthenticator = true
for(authenticator in authenticators) {
  if(authenticator instanceof ProjectQueueItemAuthenticator) {
    // only add if it does not already exist
    configureProjectAuthenticator = false
  }
  if(authenticator instanceof GlobalQueueItemAuthenticator) {
    // only add if it does not already exist
    configureGlobalQueueItemAuthenticator = false
  }
}

if(configureProjectAuthenticator) {
  authenticators.add(new ProjectQueueItemAuthenticator(strategyMap))
}
if(configureGlobalQueueItemAuthenticator) {
  authenticators.add(new GlobalQueueItemAuthenticator(strategyMap))
}

instance.save()
