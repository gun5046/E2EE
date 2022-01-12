@echo off
echo Microsotf Visual Studio Community 2019 x64 C/C++ Environment of 
cd C:\
mkdir key
cd key
openssl genrsa -out private.key 1024
openssl rsa -in private.key -out public_key.pem -pubout

set path=%path%;C:\Strawberry\perl\bin;C:\nasm
call "C:\Program Files (x86)\Microsoft Visual Studio\2019\Community\VC\Auxiliary\Build\vcvars64.bat"