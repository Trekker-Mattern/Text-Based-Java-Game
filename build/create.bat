@echo on
IF EXIST "myprogram.jar" ( del "myprogram.jar"
) ELSE ( echo "No such jar exists")


cd "%~dp0\.."
set project_root="%cd%"

echo %project_root% 
IF EXIST "output" ( del "output") ELSE (echo "No such file exist")

setlocal enabledelayedexpansion

set "fileList="
FOR /r %%a IN (*.java) DO (
    set fileList=%%a !fileList!
    echo %%a
    
)
ECHO %fileList%
"%java_home%\javac" %fileList% -d output 2> javacError.txt
cd output
"%java_home%\jar" cfe %project_root%/build/myprogram.jar runTime . 2> createJar.txt
cd %project_root%/build
"%java_home%\java" -jar myprogram.jar 2> runErrors.txt

PAUSE

