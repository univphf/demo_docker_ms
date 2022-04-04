@echo off

rem pousser l'image vers le dockerhub
set DOMAINE=%1
set PASSWORD=%2

set NOMAPPLI=pg-insa

IF "%PASSWORD%"=="" goto NOPASSWORD
IF "%DOMAINE%"=="" goto NOPASSWORD

cd ..


rem retourner dans dockerhub
cd DockerHub


echo Construct %NOMAPPLI% and push to %DOMAINE% using password %PASSWORD%
echo

docker login -u %DOMAINE% -p %PASSWORD%
docker build -t %NOMAPPLI% .
docker tag %NOMAPPLI% %DOMAINE%/%NOMAPPLI%
docker push %DOMAINE%/%NOMAPPLI%:latest
goto SUPP

:NOPASSWORD
cls
echo syntaxe build.cmd domaineDH passwordDH
echo exemple : build.cmd domaineDH passwordDocker
goto FIN

:SUPP


:FIN
