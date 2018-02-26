import jenkins.model.*
import hudson.security.*
 
def env = System.getenv()
 
def jenkins = Jenkins.getInstance()
jenkins.setSecurityRealm(new HudsonPrivateSecurityRealm(false))
jenkins.setAuthorizationStrategy(new GlobalMatrixAuthorizationStrategy())
 
def user = jenkins.getSecurityRealm().createAccount(env.JENKINS_USER, env.JENKINS_PASS)
user.save()
 
jenkins.getAuthorizationStrategy().add(Jenkins.ADMINISTER, env.JENKINS_USER)
jenkins.save()

def resetEnvironmentVariable(String name, String value) {
        def processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
        def theEnvironmentField = processEnvironmentClass.getDeclaredField("theEnvironment");
        theEnvironmentField.setAccessible(true);
        def env = (Map<String, String>) theEnvironmentField.get(null);
        env.put(name, value);
        def theCaseInsensitiveEnvironmentField = processEnvironmentClass
                .getDeclaredField("theCaseInsensitiveEnvironment");
        theCaseInsensitiveEnvironmentField.setAccessible(true);
        def cienv = (Map<String, String>) theCaseInsensitiveEnvironmentField.get(null);
        cienv.put(name, value);
    }

resetEnvironmentVariable('JENKINS_PASS', '-----')
