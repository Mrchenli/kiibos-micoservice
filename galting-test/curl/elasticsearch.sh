#!/usr/bin/env bash

curl -H "Content-type: application/json" -X PUT -d '{
"title":"New version of Elasticsearch released!",
"content":"Version 1.0 released today!",
"tags":["announce","elasticsearch","release"]
}' http://192.168.2.177:9200/blog/article/1