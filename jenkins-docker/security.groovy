#!groovy

import jenkins.model.*
import hudson.security.*
import jenkins.security.s2m.AdminWhitelistRule

import hudson.security.csrf.DefaultCrumbIssuer

def instance = Jenkins.getInstance()

def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount("admin", "admin")
instance.setSecurityRealm(hudsonRealm)

// defines behaviour with logged user
def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)



// Disable remoting
instance.getDescriptor("jenkins.CLI").get().setEnabled(false)

// Enable Agent to master security subsystem
instance.injector.getInstance(AdminWhitelistRule.class).setMasterKillSwitch(false);

// Disable jnlp
instance.setSlaveAgentPort(-1);

//  CSRF Protection
instance.setCrumbIssuer(new DefaultCrumbIssuer(true))

// Disable old Non-Encrypted protocols
HashSet<String> newProtocols = new HashSet<>(instance.getAgentProtocols());
newProtocols.removeAll(Arrays.asList(
        "JNLP3-connect", "JNLP2-connect", "JNLP-connect", "CLI-connect"
));
instance.setAgentProtocols(newProtocols);



instance.save()
instance.getInjector().getInstance(AdminWhitelistRule.class).setMasterKillSwitch(false)


