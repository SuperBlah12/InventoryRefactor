@echo on
setlocal EnableDelayedExpansion

set "params=!cmdcmdline!"
set "params=!params:~0,-1!"
set "params=!params:*" =!"
set count=0

for %%G IN (!params!) do (
  set /a count+=1
  set "item_!count!=%%~G"
)

for /L %%n in (1,1,!count!) DO (
  set "list=!list!"!item_%%n!" "
)

java Refactor !list!
