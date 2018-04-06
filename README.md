# Avtobusi.mk
=============================================================

```
A barebones Gradle app, which can easily be deployed to Heroku.
```
## Deploying to Heroku

### Deploy to Heroku using old-fashioned deploy:war
```
$ heroku login
$ (if not created already)heroku create avtobusi
$ heroku deploy:war build/libs/*.war --app avtobusi
$ heroku ps:scale web=1 --app avtobusi
$ heroku open --app avtobusi
```
### Deploying to Heroku using Procfile
```
web:    java $JAVA_OPTS -jar libs/webapp-runner.jar --port $PORT build/libs/*.war
web:    java $JAVA_OPTS -jar libs/jetty-runner.jar --port $PORT build/libs/*.war
```
### Deploy locally before deploying to Heroku
```
java -jar libs/webapp-runner.jar build/libs/*.war
java -jar libs/jetty-runner.jar build/libs/*.war
```
## Remote database
```
postgresql-corrugated-20593
```


