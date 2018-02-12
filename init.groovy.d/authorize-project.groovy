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
  (instance.getDescriptor(TriggeringUsersAuthorizationStrategy.class).getId()): false,
  (instance.getDescriptor(SpecificUsersAuthorizationStrategy.class).getId()): false,
  (instance.getDescriptor(SystemAuthorizationStrategy.class).getId()): true
]

def authenticators = QueueItemAuthenticatorConfiguration.get().getAuthenticators()
def configureProjectAuthenticator = true
for(authenticator in authenticators) {
  if(authenticator instanceof ProjectQueueItemAuthenticator) {
    // only add if it does not already exist
    configureProjectAuthenticator = false
  }
}