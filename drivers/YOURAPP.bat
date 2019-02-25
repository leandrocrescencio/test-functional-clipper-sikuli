@ECHO OFF
color 1A
:MENU
R:
cd util
CALL setbanco coredbhom old
CLS
ECHO.
ECHO.
ECHO. -------------------------------------------------------------------
ECHO.            HELLO WORLD!! THIS IS YOUR CLIPPER APP
ECHO. -------------------------------------------------------------------
ECHO. SELECT THE ACTION BELOW
ECHO.
ECHO. 1 - GO
ECHO. 2 - TEST
ECHO. 3 - YOUR
ECHO. 4 - APP
ECHO. 5 - EXIT
ECHO. -------------------------------------------------------------------
ECHO.
SET /P M=Choose an option and press ENTER:
IF %M%==1 GOTO GO 
IF %M%==2 GOTO TEST
IF %M%==3 GOTO YOUR
IF %M%==4 GOTO APP
IF %M%==5 GOTO EOF
IF %M%=='' GOTO MENU

:GO
ECHO Option 1!
ECHO.
@Pause
GOTO MENU

:TEST
ECHO Option 2!
ECHO.
@Pause
GOTO MENU

:YOUR
ECHO Option 3!
ECHO.
@Pause
GOTO MENU

:APP
ECHO Option 4!
ECHO.
@Pause
GOTO MENU
