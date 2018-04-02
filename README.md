# avtobusi.mk

A barebones Gradle app, which can easily be deployed to Heroku.

If you're going to use a database, ensure you have a local `.env` file that reads something like this:

```
DATABASE_URL=postgres://localhost:5432/gradle_database_name
```

## Deploying to Heroku

```
$ heroku login
$ (if not created already)heroku create avtobusi
$ heroku config:set WEBAPP_RUNNER_OPTS="--uri-encoding=UTF-8" --app avtobusi
$ heroku deploy:war build/libs/avtobusi-1.0.x.war --webapp-runner 8.5.11.3 --app avtobusi

$ heroku deploy:war build/libs/avtobusi-1.0.8.war --jetty-runner 9.4.2.v20170220 --app avtobusi
$ heroku ps:scale web=1 --app avtobusi
$ heroku open --app avtobusi
```
#postgresql-corrugated-20593



web:    java $JAVA_OPTS -jar target/dependency/webapp-runner.jar --port $PORT target/*.war
java -jar libs/jar_files/webapp-runner-8.5.29.0.jar build/libs/avtobusi-1.0.8.war
java -jar libs/jetty-runner.jar build/libs/avtobusi*.war