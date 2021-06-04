ECHO default password is "changeit"
"%JAVA_HOME%\jre\bin\keytool" -importcert -file "..\be\src\main\resources\myCertificate.crt" -alias mochi -keystore "%JAVA_HOME%\jre\lib\security\cacerts"
