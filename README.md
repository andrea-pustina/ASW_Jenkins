# ASW Jenkins
The aim of this project is to test the functionality of Jenkins.
In particular, we install and configure Jenkins in a Docker Container using only configuration files (infrastructure-as-code approach).


Using the configuration files it is possible to:
* create/configure Jenkins job (XML syntax)
* create/configure Jenkins pipelines (Groovie syntax)
* create a Jenkins user administrator
* install Jenkins plugins
* configure a Jenkins global tool (e.g. Gradle)
* create Jenkins credentials (e.g. for deployment)

Configure a Jenkins global tools is necessary, otherwise Jenkins will not recognize the tool (even if the tool is installed in the container)

In order to test this project 
* download the project
* open the terminal in the project folder
* sudo chmod +r ./jenkins-docker/credentials.groovy ./jenkins-docker/gradle_install.groovy ./jenkins-docker/security.groovy ./jenkins-docker/plugins.txt
* run "run.sh"
* two containers will be build and run. One with Jenkins and one with Tomcat
* open the browser
* Jenkins will be at localhost:8080 (user:admin, pass:admin)
* Tomcat will be at localhost:8081 (user:admin, pass:admin)
* Login into Jenkins
* Launch the pipeline and it will build, test and deploy a [github project](https://github.com/victormax94/asw_webapp) into the Tomcat container
* Go to http://localhost:8081/helloworld/ and you will find the new application just deployed


Backlog:
* create a VM with Jenkins installed
* test Jenkins functionality
* create a container with Jenkins installed
* disable Jenkins setup wizard
* install plugins from configuration file (plugins.txt)
* create a volume (shared directory) to configure Jenkins job and pipelines
* install docker-compose and create docker-compose.yml
* create Jenkins user "admin" with password "admin" (in security.groovy)
* fixed security warnings (in security.groovy)
  * fixed security issue "disable CLI over Remoting"
  * fixed security issue "CSRF issuer not configured"
  * fixed security issue "Agent to master security subsystem is currently off"
* install Gradle in the container
* configure Gradle as a Jenkins Global Tool
* create build job "build_hello_world" with two Gradle tasks
  * test task: execute JUnit tests
  * war task: create the .war file (for deployment in Tomcat container)
* create a container with Tomcat installed
* enable remote access to Tomcat manager page (manager-context.xml)
* create administrator Tomcat user "admin" with password "admin"	(tomcat-users.xml)
* create manager-script Tomcat user "jenkins" with password "jenkins" for deploy (tomcat-users.xml)
* create Jenkins global credentials "user:jenkins, password:jenkins" for deploy (in credentials.groovy)
* create deploy job "deploy_hello_world" 
  * import .war file from the latest build of "build_hello_world" job (Copy Artifact plugin)
  * deploy on Tomcat using the default network provided by Docker Compose (Deploy to container plugin)
* create pipeline "hello_world_pipeline" consisting of two stages
  * Build - "build_hello_world" job
  * Deploy - "deploy_hello_world" job
