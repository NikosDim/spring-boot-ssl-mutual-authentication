# Generate PKCS12 keystore, certificate and truststore for client and server for mutual authentication

1. **Generating the Server Keystore:**
  `keytool -genkeypair -alias secure-server -keyalg RSA -dname "CN=localhost,OU=interlink,O=interlink,L=Manchester,S=UK,C=uk" -keypass secret -keystore server-keystore.p12 -storepass secret -storetype PKCS12 -ext SAN=dns:localhost,ip:127.0.0.1`

2. **Generating the Client Keystore:** 
  `keytool -genkeypair -alias secure-client -keyalg RSA -dname "CN=TheClient,OU=interlink,O=interlink,L=Manchester,S=UK,C=uk" -keypass secret -keystore client-keystore.p12 -storepass secret -storetype PKCS12 -ext SAN=dns:localhost,ip:127.0.0.1`

3. **Import the supported client's public certificates intro the server truststore:**
    - **Export the client public certificate:** 
  `keytool -exportcert -alias secure-client -file client-public.cer -keystore client-keystore.p12 -storepass secret`
    - **Import it in the server truststore**: 
  `keytool -importcert -keystore server-truststore.p12 -alias clientcert -file client-public.cer -storepass secret -storetype PKCS12`

4. **Import the server's public certificate into the client truststores:**
    - **Export the server public certificate:** 
  `keytool -exportcert -alias secure-server -file server-public.cer -keystore server-keystore.p12 -storepass secret`
    - **Import it in the client truststore**:
  `keytool -importcert -keystore client-truststore.p12 -alias servercert -file server-public.cer -storepass secret -storetype PKCS12`

