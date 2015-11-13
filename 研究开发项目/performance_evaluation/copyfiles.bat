@echo off
title 复制文件到服务器
echo 复制文件到服务器
:select
set /p slt=立即复制?(Y/N):
if "%slt%"=="Y" goto docopy
if "%slt%"=="y" goto docopy
if "%slt%"=="N" goto cancelcopy
if "%slt%"=="n" goto cancelcopy
goto select
:docopy
net use \\10.2.3.113\tomcat
rem net use //192.168.1.2/d$/files 你的共享密码 /user:你的共享用户名
echo 复制开始时间: %date% %time%
xcopy /s /v /f /y /d F:\develop\xampp\tomcat\webapps\pfe\*.* \\10.2.3.113\tomcat\webapps\pfe
echo 复制完成时间: %date% %time%
net use \\10.2.3.113\tomcat /delete /y
rem net use //192.168.1.2/files 你的共享密码 /user:你的共享用户名 /delete /y
pause
exit
:cancelcopy
echo 您放弃了复制请求,按任意键退出!
pause
exit
rem echo. & pause