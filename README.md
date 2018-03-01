# Copycat

- Download video/subtitle files automatically from rss feeds and manually using admin(download support file type: common file, url, torrent, magnet)
- Display video list and play video with subtitle into frontend web

## Develop Environment
- Language: Java 8 (change to java 9 soon)
- Framework: Spring boot 1.5 (change to version 2 soon)
- DB: MySQL

## Run(develop)
### server
```$xslt
# server
$ SPRING_PROFILES_ACTIVE=local ./gradlew bootrun
```
### frontend
```
# frontend
# move to copycat-frontend
$ npm run dev
```
### admin
```
# admin
# move to copycat-admin
$ npm run dev
```