# Thingy
Todo for life

### Start the application
Run below commands 
```shell
./gradlew clean docker
docker-compose up
```

### Known issues
- If the `gradle docker` task fails with the error message - 
`"Cannot run program "docker": error=2, No such file or directory"`, 
then kill the gradle daemon.

SOURCE - https://github.com/palantir/gradle-docker/issues/162#issuecomment-396704199 