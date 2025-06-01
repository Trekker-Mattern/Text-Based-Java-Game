del myprogram.jar
cd %~dp0\..
set project_root="%cd%"
rmdir output
javac -source 1.8 -target 1.8  *.java -d output
cd output
jar cfe %project_root%/build/myprogram.jar runTime .
cd %project_root%/build
java -jar myprogram.jar 