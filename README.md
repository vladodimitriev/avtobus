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
$ heroku deploy:war build/libs/*.war --app avtobusi
$ heroku ps:scale web=1 --app avtobusi
$ heroku open --app avtobusi
```
#postgresql-corrugated-20593



web:    java $JAVA_OPTS -jar libs/webapp-runner.jar --port $PORT build/libs/*.war
web:    java $JAVA_OPTS -jar libs/jetty-runner.jar --port $PORT build/libs/*.war
java -jar libs/webapp-runner.jar build/libs/*.war
java -jar libs/jetty-runner.jar build/libs/*.war