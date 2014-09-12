get-paper-id
============

get paper ids from microsoft-academic-search

- conference ids
- journal ids

Usage
-----

```sh
$ cp src/main/resources/application.conf.example src/main/resources/application.conf
$ $EDITOR src/main/resources/application.conf
$ sbt compile
$ sbt "run siggraph.txt"
$ cat siggraph.txt | pbcopy
```
