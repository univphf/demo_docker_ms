@echo off

rem pousser l'image vers le dockerhub
set DOMAINE=%1
set PASSWORD=%2

set JAVA_HOME=C:\Program Files\Java\jdk-11.0.9

IF "%PASSWORD%"=="" goto NOPASSWORD
IF "%DOMAINE%"=="" goto NOPASSWORD
cd .\ws_services_template\DockerHub
cmd /c buildN.cmd %DOMAINE% %PASSWORD%
cd ..
cd ..

cd .\ws_services_logs\DockerHub
cmd /c buildN.cmd %DOMAINE% %PASSWORD%
cd ..
cd ..

cd .\pg-insa\DockerHub
cmd /c buildN.cmd %DOMAINE% %PASSWORD%
cd ..
cd ..

goto FIN

:NOPASSWORD
cls
echo syntaxe build.cmd domaineDH passwordDH
echo exemple : build.cmd domaineDH passwordDocker
goto FIN

:FIN
