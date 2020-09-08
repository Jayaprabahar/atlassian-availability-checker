# Introduction

Welcome to Atlassian availability checker tool !!

## Problem Statement

Bamboo and BitBucket are the important tools used by various teams for code commit, build and deployment purpose. It is used more often by the developers to commit and deploy their work.

Sometimes these applications goes down for any reason such as for backup, resting mode, maintenance or server down. 

During this time, developers cannot initiate any build process, deployment process and cannot even commit the code. If teams plan their change during this time or if they are planning to work on Saturdays, then it is a huge obstacle. 

Developers have to manually check these applications, to check their availability.

This forced me to automate this work and this tool is a result of it.

## Solution

This Atlassian availability checker tool is created to automate the process of monitoring and mailing the concerned persons/team who want to know when the Bamboo/Bitbucket is going down/UP.

It is a spring boot application enabled with Quartz schedulers and Selenium to perform this task. 

As Executables cannot be placed inside the SpringBoot jars (exe inside a jar), bonigarcia webdriver is used which will be downloaded during springboot jar running time.

The tool is added with various configurations such as mail id to be notified, email when the server is UP or DOWN or both, frequency to check and many more. If the tool is started, it will monitor the status of Bamboo and bitbucket and trigger emails once the expected situation is arrived


## Technology

1.	Spring-boot-starter-parent	2.3.1.RELEASE
2.	OpenJDK 11
3.	Bonigarcia 4.0.0

## Configurations

You can edit following configurations in the application.properties file

1.	Mail Server configurations - Dont edit this, unless if there is an issue with mail server
2.	Selenium configuration - Adjust according to your needs
3.	Application URL configuration - Dont edit this, unless if there is a change
4.	Scheduler configuration - Adjust according to your needs. 
5.	Headless Run configuration - Default true. If you want to see the browser while running, then change it to false
6.	Email configuration - Change it according to your needs.

## Prerequisites

1.	Java 11 (Can be java 8, But you need to change the java version in pom)
2.	Maven 3 and above tool
3.	Shell command tools
4.	Any Java IDE (Optional, Only if you want to play with code)

## Build

Use the following command to create the package. "mvn clean install"

You should able to see the application building, test execution and successful creation of atlassianavailabilitychecker jar file

```
$ mvn clean install

x087934@JayaprabaharPc MINGW64 /c/Jayaprabahar/git/atlassianavailabilitychecker
$ mvn clean install
[INFO] Scanning for projects...
[INFO]
[INFO] --< com.jayaprabahar.atlassian.availabilitychecker:atlassianavailabilitychecker >--
[INFO] Building atlassianavailabilitychecker 1.0.0
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-clean-plugin:3.1.0:clean (default-clean) @ atlassianavailabilitychecker ---
[INFO] Deleting C:\Jayaprabahar\git\atlassianavailabilitychecker\target
[INFO]
[INFO] --- maven-resources-plugin:3.1.0:resources (default-resources) @ atlassianavailabilitychecker ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 0 resource
[INFO]
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ atlassianavailabilitychecker ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 9 source files to C:\Jayaprabahar\git\atlassianavailabilitychecker\target\classes
[INFO]
[INFO] --- maven-resources-plugin:3.1.0:testResources (default-testResources) @ atlassianavailabilitychecker ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 0 resource
[INFO]
[INFO] --- maven-compiler-plugin:3.8.1:testCompile (default-testCompile) @ atlassianavailabilitychecker ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to C:\Jayaprabahar\git\atlassianavailabilitychecker\target\test-classes
[INFO]
[INFO] --- maven-surefire-plugin:2.22.2:test (default-test) @ atlassianavailabilitychecker ---
[INFO]
[INFO] -------------------------------------------------------

[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.jayaprabahar.atlassian.availabilitychecker.AtlassianAvailabilityCheckerApplicationTests
.
.
.
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.3.1.RELEASE)

2020-09-08 14:21:39.148  INFO 17024 --- [           main] ssianAvailabilityCheckerApplicationTests : Starting AtlassianAvailabilityCheckerApplicationTests on JayaprabaharPc with PID 17024 (started by x087934 in C:\Jayaprabahar\git\atlassianavailabilitychecker)
2020-09-08 14:21:39.149  INFO 17024 --- [           main] ssianAvailabilityCheckerApplicationTests : No active profile set, falling back to default profiles: default
2020-09-08 14:21:39.760  INFO 17024 --- [           main] i.g.bonigarcia.wdm.WebDriverManager      : Please answer the following questionnaire based on your experience with WebDriverManager. Thanks a lot!
2020-09-08 14:21:39.760  INFO 17024 --- [           main] i.g.bonigarcia.wdm.WebDriverManager      : ====> http://tiny.cc/wdm-survey <====
2020-09-08 14:21:40.387  INFO 17024 --- [           main] i.g.bonigarcia.wdm.WebDriverManager      : Using chromedriver 85.0.4183.87 (since Chrome 85 is installed in your machine)
2020-09-08 14:21:40.401  INFO 17024 --- [           main] i.g.bonigarcia.wdm.WebDriverManager      : Exporting webdriver.chrome.driver as C:\Users\x087934\.m2\repository\webdriver\chromedriver\win32\85.0.4183.87\chromedriver.exe
2020-09-08 14:21:40.403  INFO 17024 --- [           main] c.j.a.a.c.BambooAvailabilityChecker      : BambooAvailabilityChecker is loaded
2020-09-08 14:21:40.406  INFO 17024 --- [           main] c.j.a.a.c.BitBucketAvailabilityChecker   : BitBucketAvailabilityChecker is loaded
2020-09-08 14:21:40.569  INFO 17024 --- [           main] org.quartz.impl.StdSchedulerFactory      : Using default implementation for ThreadExecutor
2020-09-08 14:21:40.586  INFO 17024 --- [           main] org.quartz.core.SchedulerSignalerImpl    : Initialized Scheduler Signaller of type: class org.quartz.core.SchedulerSignalerImpl
2020-09-08 14:21:40.586  INFO 17024 --- [           main] org.quartz.core.QuartzScheduler          : Quartz Scheduler v.2.3.2 created.
2020-09-08 14:21:40.587  INFO 17024 --- [           main] org.quartz.simpl.RAMJobStore             : RAMJobStore initialized.
2020-09-08 14:21:40.588  INFO 17024 --- [           main] org.quartz.core.QuartzScheduler          : Scheduler meta-data: Quartz Scheduler (v2.3.2) 'quartzScheduler' with instanceId 'NON_CLUSTERED'
  Scheduler class: 'org.quartz.core.QuartzScheduler' - running locally.
  NOT STARTED.
  Currently in standby mode.
  Number of jobs executed: 0
  Using thread pool 'org.quartz.simpl.SimpleThreadPool' - with 10 threads.
  Using job-store 'org.quartz.simpl.RAMJobStore' - which does not support persistence. and is not clustered.

2020-09-08 14:21:40.588  INFO 17024 --- [           main] org.quartz.impl.StdSchedulerFactory      : Quartz scheduler 'quartzScheduler' initialized from an externally provided properties instance.
2020-09-08 14:21:40.588  INFO 17024 --- [           main] org.quartz.impl.StdSchedulerFactory      : Quartz scheduler version: 2.3.2
2020-09-08 14:21:40.588  INFO 17024 --- [           main] org.quartz.core.QuartzScheduler          : JobFactory set to: org.springframework.scheduling.quartz.SpringBeanJobFactory@3bc4ef12
2020-09-08 14:21:40.622  INFO 17024 --- [           main] o.s.s.c.ThreadPoolTaskScheduler          : Initializing ExecutorService 'taskScheduler'
2020-09-08 14:21:40.651  INFO 17024 --- [           main] o.s.s.quartz.SchedulerFactoryBean        : Starting Quartz Scheduler now
2020-09-08 14:21:40.652  INFO 17024 --- [           main] org.quartz.core.QuartzScheduler          : Scheduler quartzScheduler_$_NON_CLUSTERED started.
2020-09-08 14:21:40.662  INFO 17024 --- [   scheduling-1] c.j.a.a.s.AvailabilityCheckScheduler     : -----------------Scheduler Started --------------
2020-09-08 14:21:40.668  INFO 17024 --- [   scheduling-1] i.g.bonigarcia.wdm.WebDriverManager      : Please answer the following questionnaire based on your experience with WebDriverManager. Thanks a lot!
2020-09-08 14:21:40.668  INFO 17024 --- [   scheduling-1] i.g.bonigarcia.wdm.WebDriverManager      : ====> http://tiny.cc/wdm-survey <====
2020-09-08 14:21:40.669  INFO 17024 --- [           main] ssianAvailabilityCheckerApplicationTests : Started AtlassianAvailabilityCheckerApplicationTests in 1.766 seconds (JVM running for 2.743)
2020-09-08 14:21:40.684  INFO 17024 --- [   scheduling-1] i.g.bonigarcia.wdm.WebDriverManager      : Using chromedriver 85.0.4183.87 (since Chrome 85 is installed in your machine)
2020-09-08 14:21:40.690  INFO 17024 --- [   scheduling-1] i.g.bonigarcia.wdm.WebDriverManager      : Exporting webdriver.chrome.driver as C:\Users\x087934\.m2\repository\webdriver\chromedriver\win32\85.0.4183.87\chromedriver.exe
Starting ChromeDriver 85.0.4183.87 (cd6713ebf92fa1cacc0f1a598df280093af0c5d7-refs/branch-heads/4183@{#1689}) on port 18053
Only local connections are allowed.
Please see https://chromedriver.chromium.org/security-considerations for suggestions on keeping ChromeDriver safe.
ChromeDriver was started successfully.
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.629 s - in com.jayaprabahar.atlassian.availabilitychecker.AtlassianAvailabilityCheckerApplicationTests
2020-09-08 14:21:41.184  INFO 17024 --- [extShutdownHook] org.quartz.core.QuartzScheduler          : Scheduler quartzScheduler_$_NON_CLUSTERED paused.
2020-09-08 14:21:41.186  INFO 17024 --- [extShutdownHook] o.s.s.c.ThreadPoolTaskScheduler          : Shutting down ExecutorService 'taskScheduler'
2020-09-08 14:21:41.186  INFO 17024 --- [extShutdownHook] o.s.s.quartz.SchedulerFactoryBean        : Shutting down Quartz Scheduler
2020-09-08 14:21:41.187  INFO 17024 --- [extShutdownHook] org.quartz.core.QuartzScheduler          : Scheduler quartzScheduler_$_NON_CLUSTERED shutting down.
2020-09-08 14:21:41.187  INFO 17024 --- [extShutdownHook] org.quartz.core.QuartzScheduler          : Scheduler quartzScheduler_$_NON_CLUSTERED paused.
2020-09-08 14:21:41.188  INFO 17024 --- [extShutdownHook] org.quartz.core.QuartzScheduler          : Scheduler quartzScheduler_$_NON_CLUSTERED shutdown complete.
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- maven-jar-plugin:3.2.0:jar (default-jar) @ atlassianavailabilitychecker ---
[INFO] Building jar: C:\Jayaprabahar\git\atlassianavailabilitychecker\target\atlassianavailabilitychecker-1.0.0.jar
[INFO]
[INFO] --- spring-boot-maven-plugin:2.3.1.RELEASE:repackage (repackage) @ atlassianavailabilitychecker ---
[INFO] Replacing main artifact with repackaged archive
[INFO]
[INFO] --- maven-install-plugin:2.5.2:install (default-install) @ atlassianavailabilitychecker ---
[INFO] Installing C:\Jayaprabahar\git\atlassianavailabilitychecker\target\atlassianavailabilitychecker-1.0.0.jar to C:\Jayaprabahar\m3\com\jayaprabahar\atlassian\availabilitychecker\atlassianavailabilitychecker\1.0.0\atlassianavailabilitychecker-1.0.0.jar
[INFO] Installing C:\Jayaprabahar\git\atlassianavailabilitychecker\pom.xml to C:\Jayaprabahar\m3\com\jayaprabahar\atlassian\availabilitychecker\atlassianavailabilitychecker\1.0.0\atlassianavailabilitychecker-1.0.0.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  9.876 s
[INFO] Finished at: 2020-09-08T14:21:42+02:00
[INFO] ------------------------------------------------------------------------


````

## Execute

Use the following command to run the application. First go to target directory "java -jar atlassianavailabilitychecker-1.0.0.jar"

````
x087934@JayaprabaharPc MINGW64 /c/Jayaprabahar/git/atlassianavailabilitychecker
$

x087934@JayaprabaharPc MINGW64 /c/Jayaprabahar/git/atlassianavailabilitychecker
$ cd target/

x087934@JayaprabaharPc MINGW64 /c/Jayaprabahar/git/atlassianavailabilitychecker/target
$ ls
atlassianavailabilitychecker-1.0.0.jar  atlassianavailabilitychecker-1.0.0.jar.original  classes/  generated-sources/  generated-test-sources/  maven-archiver/  maven-status/  surefire-reports/  test-classes/

x087934@JayaprabaharPc MINGW64 /c/Jayaprabahar/git/atlassianavailabilitychecker/target
$ java -jar atlassianavailabilitychecker-1.0.0.jar

```

*Have fun!*
Best Regards