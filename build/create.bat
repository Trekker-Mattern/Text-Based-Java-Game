@echo off
IF EXIST "myprogram.jar" ( del "myprogram.jar"
) ELSE ( echo "No such jar exists")


cd "%~dp0\.."
set project_root="%cd%"

echo %project_root% 
PAUSE 
IF EXIST "output" ( del "output") ELSE (echo "No such file exist")


FOR /r %%a IN (*.java) DO (
    echo %%a
    "%java_home%\javac" "%%a" -d output 2> errors.txt
    
)
cd output
"%java_home%\jar" cfe %project_root%/build/myprogram.jar runTime .
cd %project_root%/build
"%java_home%\java" -jar myprogram.jar 2> errors.txt

