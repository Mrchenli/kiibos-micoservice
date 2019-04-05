#!/usr/bin/env bash

##查询所有的类别列表
curl http://localhost:8080/category

##插入品类
#22
curl -H "Content-type: application/json" -X PUT -d '{"parentCategoryId":0,"categoryName":"服装"}' http://localhost:8080/category
#23
curl -H "Content-type: application/json" -X PUT -d '{"parentCategoryId":22,"categoryName":"衣服"}' http://localhost:8080/category
#24
curl -H "Content-type: application/json" -X PUT -d '{"parentCategoryId":22,"categoryName":"鞋子"}' http://localhost:8080/category
#26
curl -H "Content-type: application/json" -X PUT -d '{"parentCategoryId":24,"categoryName":"鞋带"}' http://localhost:8080/category
#25
curl -H "Content-type: application/json" -X PUT -d '{"parentCategoryId":22,"categoryName":"裤子"}' http://localhost:8080/category

#27
curl -H "Content-type: application/json" -X PUT -d '{"parentCategoryId":0,"categoryName":"家电"}' http://localhost:8080/category
#28
curl -H "Content-type: application/json" -X PUT -d '{"parentCategoryId":0,"categoryName":"五金"}' http://localhost:8080/category
#29
curl -H "Content-type: application/json" -X PUT -d '{"parentCategoryId":0,"categoryName":"婴儿"}' http://localhost:8080/category


##查询品类树



##删除品类
curl -X DELETE http://localhost:8080/category/18