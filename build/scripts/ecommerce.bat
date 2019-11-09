@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  ecommerce startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and ECOMMERCE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\ecommerce-1.0-SNAPSHOT.jar;%APP_HOME%\lib\dropwizard-swagger-1.0.6-1.jar;%APP_HOME%\lib\dropwizard-assets-1.3.5.jar;%APP_HOME%\lib\dropwizard-auth-1.3.5.jar;%APP_HOME%\lib\dropwizard-hibernate-1.3.5.jar;%APP_HOME%\lib\dropwizard-flyway-1.0.0-1.jar;%APP_HOME%\lib\dropwizard-db-1.3.5.jar;%APP_HOME%\lib\dropwizard-views-freemarker-1.0.6.jar;%APP_HOME%\lib\dropwizard-views-1.0.6.jar;%APP_HOME%\lib\dropwizard-core-1.3.5.jar;%APP_HOME%\lib\postgresql-9.4.1212.jre7.jar;%APP_HOME%\lib\jjwt-0.7.0.jar;%APP_HOME%\lib\hibernate-java8-5.3.7.Final.jar;%APP_HOME%\lib\aws-java-sdk-s3-1.11.271.jar;%APP_HOME%\lib\aws-java-sdk-lambda-1.11.271.jar;%APP_HOME%\lib\aws-java-sdk-ssm-1.11.271.jar;%APP_HOME%\lib\aws-java-sdk-ses-1.11.271.jar;%APP_HOME%\lib\aws-java-sdk-sqs-1.11.271.jar;%APP_HOME%\lib\lambda-logging-1.0.1.jar;%APP_HOME%\lib\aws-lambda-java-core-1.2.0.jar;%APP_HOME%\lib\google-api-services-sheets-v4-rev108-1.22.0.jar;%APP_HOME%\lib\google-api-services-drive-v2-rev264-1.22.0.jar;%APP_HOME%\lib\firebase-admin-6.10.0.jar;%APP_HOME%\lib\google-api-client-gson-1.30.1.jar;%APP_HOME%\lib\google-cloud-storage-1.79.0.jar;%APP_HOME%\lib\google-cloud-core-http-1.79.0.jar;%APP_HOME%\lib\google-api-services-storage-v1-rev20190523-1.28.0.jar;%APP_HOME%\lib\google-api-client-1.30.1.jar;%APP_HOME%\lib\plivo-java-3.0.9.jar;%APP_HOME%\lib\commons-validator-1.6.jar;%APP_HOME%\lib\jsoup-1.10.2.jar;%APP_HOME%\lib\poi-ooxml-3.16.jar;%APP_HOME%\lib\joda-money-0.12.jar;%APP_HOME%\lib\HikariCP-3.3.1.jar;%APP_HOME%\lib\okhttp-3.2.0.jar;%APP_HOME%\lib\commons-io-2.5.jar;%APP_HOME%\lib\javax.json-1.1.jar;%APP_HOME%\lib\qrgen-1.4.jar;%APP_HOME%\lib\mail-1.5.0-b01.jar;%APP_HOME%\lib\commons-exec-1.3.jar;%APP_HOME%\lib\jaxb-api-2.3.0.jar;%APP_HOME%\lib\dropwizard-configuration-1.3.5.jar;%APP_HOME%\lib\dropwizard-jersey-1.3.5.jar;%APP_HOME%\lib\dropwizard-request-logging-1.3.5.jar;%APP_HOME%\lib\dropwizard-jetty-1.3.5.jar;%APP_HOME%\lib\dropwizard-logging-1.3.5.jar;%APP_HOME%\lib\dropwizard-metrics-1.3.5.jar;%APP_HOME%\lib\dropwizard-jackson-1.3.5.jar;%APP_HOME%\lib\dropwizard-validation-1.3.5.jar;%APP_HOME%\lib\dropwizard-servlets-1.3.5.jar;%APP_HOME%\lib\dropwizard-lifecycle-1.3.5.jar;%APP_HOME%\lib\dropwizard-util-1.3.5.jar;%APP_HOME%\lib\metrics-servlets-4.0.2.jar;%APP_HOME%\lib\metrics-jvm-4.0.2.jar;%APP_HOME%\lib\metrics-jmx-4.0.2.jar;%APP_HOME%\lib\metrics-logback-4.0.2.jar;%APP_HOME%\lib\metrics-jersey2-4.0.2.jar;%APP_HOME%\lib\metrics-jetty9-4.0.2.jar;%APP_HOME%\lib\metrics-json-4.0.2.jar;%APP_HOME%\lib\metrics-core-4.0.2.jar;%APP_HOME%\lib\metrics-healthchecks-4.0.2.jar;%APP_HOME%\lib\argparse4j-0.8.1.jar;%APP_HOME%\lib\jetty-setuid-java-1.0.3.jar;%APP_HOME%\lib\jackson-datatype-hibernate5-2.9.6.jar;%APP_HOME%\lib\usertype.core-7.0.0.CR1.jar;%APP_HOME%\lib\hibernate-core-5.3.7.Final.jar;%APP_HOME%\lib\flyway-core-4.0.3.jar;%APP_HOME%\lib\aws-java-sdk-kms-1.11.271.jar;%APP_HOME%\lib\aws-java-sdk-core-1.11.271.jar;%APP_HOME%\lib\jmespath-java-1.11.271.jar;%APP_HOME%\lib\jackson-datatype-guava-2.9.6.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.9.6.jar;%APP_HOME%\lib\jackson-datatype-jdk8-2.9.6.jar;%APP_HOME%\lib\jackson-module-parameter-names-2.9.6.jar;%APP_HOME%\lib\jackson-module-afterburner-2.9.6.jar;%APP_HOME%\lib\jackson-datatype-joda-2.9.6.jar;%APP_HOME%\lib\swagger-jersey2-jaxrs-1.5.12.jar;%APP_HOME%\lib\swagger-jaxrs-1.5.12.jar;%APP_HOME%\lib\jackson-jaxrs-json-provider-2.9.6.jar;%APP_HOME%\lib\jackson-jaxrs-base-2.9.6.jar;%APP_HOME%\lib\jackson-module-jaxb-annotations-2.9.6.jar;%APP_HOME%\lib\swagger-core-1.5.12.jar;%APP_HOME%\lib\jackson-databind-2.9.6.jar;%APP_HOME%\lib\jersey-media-multipart-2.23.2.jar;%APP_HOME%\lib\jersey-bean-validation-2.25.1.jar;%APP_HOME%\lib\hibernate-validator-5.4.2.Final.jar;%APP_HOME%\lib\hibernate-commons-annotations-5.0.4.Final.jar;%APP_HOME%\lib\jboss-logging-3.3.2.Final.jar;%APP_HOME%\lib\log4j-over-slf4j-1.7.25.jar;%APP_HOME%\lib\logback-classic-1.2.3.jar;%APP_HOME%\lib\google-oauth-client-1.30.1.jar;%APP_HOME%\lib\google-cloud-firestore-1.9.0.jar;%APP_HOME%\lib\google-cloud-core-grpc-1.79.0.jar;%APP_HOME%\lib\gax-grpc-1.46.1.jar;%APP_HOME%\lib\google-cloud-core-1.79.0.jar;%APP_HOME%\lib\gax-httpjson-0.63.1.jar;%APP_HOME%\lib\gax-1.46.1.jar;%APP_HOME%\lib\grpc-alts-1.21.0.jar;%APP_HOME%\lib\google-auth-library-oauth2-http-0.16.1.jar;%APP_HOME%\lib\google-http-client-jackson2-1.30.1.jar;%APP_HOME%\lib\google-http-client-gson-1.30.1.jar;%APP_HOME%\lib\google-http-client-appengine-1.30.1.jar;%APP_HOME%\lib\google-http-client-1.30.1.jar;%APP_HOME%\lib\httpclient-4.5.8.jar;%APP_HOME%\lib\grpc-grpclb-1.21.0.jar;%APP_HOME%\lib\protobuf-java-util-3.7.1.jar;%APP_HOME%\lib\grpc-netty-shaded-1.21.0.jar;%APP_HOME%\lib\opencensus-contrib-grpc-util-0.21.0.jar;%APP_HOME%\lib\grpc-core-1.21.0.jar;%APP_HOME%\lib\gson-2.8.5.jar;%APP_HOME%\lib\commons-beanutils-1.9.2.jar;%APP_HOME%\lib\commons-digester-1.8.1.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\commons-collections-3.2.2.jar;%APP_HOME%\lib\poi-3.16.jar;%APP_HOME%\lib\poi-ooxml-schemas-3.16.jar;%APP_HOME%\lib\curvesapi-1.04.jar;%APP_HOME%\lib\jul-to-slf4j-1.7.25.jar;%APP_HOME%\lib\jcl-over-slf4j-1.7.25.jar;%APP_HOME%\lib\metrics-annotation-4.0.2.jar;%APP_HOME%\lib\swagger-models-1.5.12.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar;%APP_HOME%\lib\okio-1.6.0.jar;%APP_HOME%\lib\javase-3.0.0.jar;%APP_HOME%\lib\activation-1.1.jar;%APP_HOME%\lib\proto-google-cloud-firestore-admin-v1-1.9.0.jar;%APP_HOME%\lib\proto-google-cloud-firestore-v1-1.9.0.jar;%APP_HOME%\lib\proto-google-cloud-firestore-v1beta1-0.62.0.jar;%APP_HOME%\lib\proto-google-iam-v1-0.12.0.jar;%APP_HOME%\lib\api-common-1.8.1.jar;%APP_HOME%\lib\opencensus-contrib-http-util-0.21.0.jar;%APP_HOME%\lib\reflections-0.9.10.jar;%APP_HOME%\lib\grpc-protobuf-1.21.0.jar;%APP_HOME%\lib\grpc-stub-1.21.0.jar;%APP_HOME%\lib\grpc-auth-1.21.0.jar;%APP_HOME%\lib\grpc-protobuf-lite-1.21.0.jar;%APP_HOME%\lib\grpc-api-1.21.0.jar;%APP_HOME%\lib\guava-27.1-android.jar;%APP_HOME%\lib\netty-codec-http-4.1.34.Final.jar;%APP_HOME%\lib\netty-handler-4.1.34.Final.jar;%APP_HOME%\lib\netty-codec-4.1.34.Final.jar;%APP_HOME%\lib\netty-transport-4.1.34.Final.jar;%APP_HOME%\lib\jackson-annotations-2.9.0.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\profiler-1.0.2.jar;%APP_HOME%\lib\joda-time-2.9.9.jar;%APP_HOME%\lib\jackson-dataformat-yaml-2.9.6.jar;%APP_HOME%\lib\jackson-dataformat-cbor-2.6.7.jar;%APP_HOME%\lib\jackson-core-2.9.9.jar;%APP_HOME%\lib\javax.el-3.0.0.jar;%APP_HOME%\lib\jersey-container-servlet-2.25.1.jar;%APP_HOME%\lib\jersey-container-servlet-core-2.25.1.jar;%APP_HOME%\lib\jersey-server-2.25.1.jar;%APP_HOME%\lib\jersey-metainf-services-2.25.1.jar;%APP_HOME%\lib\jersey-client-2.25.1.jar;%APP_HOME%\lib\jersey-media-jaxb-2.25.1.jar;%APP_HOME%\lib\jersey-common-2.25.1.jar;%APP_HOME%\lib\hk2-locator-2.5.0-b32.jar;%APP_HOME%\lib\javassist-3.23.1-GA.jar;%APP_HOME%\lib\commons-text-1.2.jar;%APP_HOME%\lib\commons-lang3-3.7.jar;%APP_HOME%\lib\logback-access-1.2.3.jar;%APP_HOME%\lib\logback-core-1.2.3.jar;%APP_HOME%\lib\jetty-servlets-9.4.11.v20180605.jar;%APP_HOME%\lib\jetty-webapp-9.4.11.v20180605.jar;%APP_HOME%\lib\jetty-servlet-9.4.11.v20180605.jar;%APP_HOME%\lib\jetty-security-9.4.11.v20180605.jar;%APP_HOME%\lib\jetty-server-9.4.11.v20180605.jar;%APP_HOME%\lib\jetty-http-9.4.11.v20180605.jar;%APP_HOME%\lib\jetty-io-9.4.11.v20180605.jar;%APP_HOME%\lib\jetty-xml-9.4.11.v20180605.jar;%APP_HOME%\lib\jetty-util-9.4.11.v20180605.jar;%APP_HOME%\lib\jetty-continuation-9.4.11.v20180605.jar;%APP_HOME%\lib\tomcat-jdbc-9.0.5.jar;%APP_HOME%\lib\usertype.spi-7.0.0.CR1.jar;%APP_HOME%\lib\freemarker-2.3.23.jar;%APP_HOME%\lib\mimepull-1.9.6.jar;%APP_HOME%\lib\javax.persistence-api-2.2.jar;%APP_HOME%\lib\byte-buddy-1.8.17.jar;%APP_HOME%\lib\antlr-2.7.7.jar;%APP_HOME%\lib\jboss-transaction-api_1.2_spec-1.1.1.Final.jar;%APP_HOME%\lib\jandex-2.0.5.Final.jar;%APP_HOME%\lib\classmate-1.3.4.jar;%APP_HOME%\lib\javax.activation-api-1.2.0.jar;%APP_HOME%\lib\dom4j-2.1.1.jar;%APP_HOME%\lib\ion-java-1.0.2.jar;%APP_HOME%\lib\commons-codec-1.11.jar;%APP_HOME%\lib\commons-collections4-4.1.jar;%APP_HOME%\lib\xmlbeans-2.6.0.jar;%APP_HOME%\lib\core-3.0.0.jar;%APP_HOME%\lib\j2objc-annotations-1.3.jar;%APP_HOME%\lib\opencensus-contrib-grpc-metrics-0.21.0.jar;%APP_HOME%\lib\opencensus-api-0.21.0.jar;%APP_HOME%\lib\javax.annotation-api-1.3.2.jar;%APP_HOME%\lib\google-auth-library-credentials-0.16.1.jar;%APP_HOME%\lib\netty-buffer-4.1.34.Final.jar;%APP_HOME%\lib\netty-resolver-4.1.34.Final.jar;%APP_HOME%\lib\netty-common-4.1.34.Final.jar;%APP_HOME%\lib\validation-api-1.1.0.Final.jar;%APP_HOME%\lib\snakeyaml-1.18.jar;%APP_HOME%\lib\javax.ws.rs-api-2.0.1.jar;%APP_HOME%\lib\hk2-api-2.5.0-b32.jar;%APP_HOME%\lib\javax.inject-2.5.0-b32.jar;%APP_HOME%\lib\javax.servlet-api-3.1.0.jar;%APP_HOME%\lib\tomcat-juli-9.0.5.jar;%APP_HOME%\lib\stax-api-1.0.1.jar;%APP_HOME%\lib\httpcore-4.4.11.jar;%APP_HOME%\lib\grpc-context-1.21.0.jar;%APP_HOME%\lib\failureaccess-1.0.1.jar;%APP_HOME%\lib\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;%APP_HOME%\lib\checker-compat-qual-2.5.2.jar;%APP_HOME%\lib\error_prone_annotations-2.3.2.jar;%APP_HOME%\lib\animal-sniffer-annotations-1.17.jar;%APP_HOME%\lib\proto-google-common-protos-1.16.0.jar;%APP_HOME%\lib\protobuf-java-3.7.1.jar;%APP_HOME%\lib\threetenbp-1.3.3.jar;%APP_HOME%\lib\jersey-guava-2.25.1.jar;%APP_HOME%\lib\osgi-resource-locator-1.0.1.jar;%APP_HOME%\lib\hk2-utils-2.5.0-b32.jar;%APP_HOME%\lib\aopalliance-repackaged-2.5.0-b32.jar;%APP_HOME%\lib\annotations-2.0.1.jar;%APP_HOME%\lib\annotations-4.1.1.4.jar;%APP_HOME%\lib\javax.inject-1.jar;%APP_HOME%\lib\swagger-annotations-1.5.12.jar

@rem Execute ecommerce
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %ECOMMERCE_OPTS%  -classpath "%CLASSPATH%" prakhar.ECommerceApplication %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable ECOMMERCE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%ECOMMERCE_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
