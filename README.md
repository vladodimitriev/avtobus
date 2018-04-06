# Avtobusi.mk
A barebones Gradle app, which can easily be deployed to Heroku.

### Deploying to Heroku
```
$ heroku login
$ (if not created already)heroku create avtobusi
$ heroku deploy:war build/libs/*.war --app avtobusi
$ heroku ps:scale web=1 --app avtobusi
$ heroku open --app avtobusi
```
### Database
postgresql-corrugated-20593

### jetty-runner
java -jar libs/jetty-runner.jar build/libs/*.war
