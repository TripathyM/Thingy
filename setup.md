## Setup Instructions

1. Install Java 11  
   If using brew follow below instructions - 
   ```shell
   brew tap AdoptOpenJDK/openjdk
   brew cask install adoptopenjdk11
   ```
2. In case you have multiple JDKs installed, you can use jEnv to manage your Java environment
   ```shell
   brew install jenv
   jenv add /Library/Java/JavaVirtualMachines/adoptopenjdk-11.jdk/Contents/Home/
   jenv versions #List out available(added) Java versions in the machine
   
   # Run below command from inside the project location in local machine
   jenv local 11.0
   ```

3. Run the application using 
    ```shell
    ./gradlew bootRun
    ```
4. Verify by accessing http://localhost:8080/hello
 


