"%JAVA_HOME%\jre\bin\keytool" -genkeypair -alias mochi -dname "CN=localhost,OU=Hakan,O=Hakan,C=NL" -ext "SAN:c=DNS:localhost,IP:127.0.0.1" -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore ..\be\src\main\resources\mochi.p12 -validity 3650
:: "%JAVA_HOME%\jre\bin\keytool" -list -v -storetype pkcs12 -keystore ..\be\src\main\resources\mochi.p12
