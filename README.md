# avtobusi.mk

A barebones Gradle app, which can easily be deployed to Heroku.

If you're going to use a database, ensure you have a local `.env` file that reads something like this:

```
DATABASE_URL=postgres://localhost:5432/gradle_database_name
```

## Deploying to Heroku

$ git add .
$ git commit -m "Added a Procfile."
$ heroku login
$ heroku create avtobusi
$ heroku deploy:war build/libs/avtobusi.war --app avtobusi
$ heroku ps:scale web=1 --app avtobusi
$ heroku open --app avtobusi
#postgresql-corrugated-20593
