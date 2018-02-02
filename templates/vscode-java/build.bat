@echo off

set DIR_SRC=src
set DIR_OUT=out
set DIR_LIB=libs

if not exist %DIR_SRC% (
    set err=Source dir '%DIR_SRC%' does not exists.
    set code=1
    goto end
)

where javac >nul 2>nul

if %errorlevel% neq 0 (
    set err=Can not find the Java compiler.
    set code=2
    goto end
)

if not exist %DIR_OUT% (
    mkdir %DIR_OUT%
)

dir /s /B %DIR_SRC%\*.java > sourceFiles.txt

javac -J-Duser.language=en -cp %DIR_LIB%\* -d %DIR_OUT% -encoding utf8 @sourceFiles.txt
set code=%errorlevel%
set err=
del sourceFiles.txt

:end
    if not "%err%"=="" echo %err%
    exit %code%
    