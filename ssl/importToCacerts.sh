ECHO default password is "changeit"
"/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/bin/keytool" -importcert -file "../be/src/main/resources/myCertificate.crt" -alias mochi -keystore "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/security/cacerts"
