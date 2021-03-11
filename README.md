# systems-architecture-client

first draft of requests

GET /category/ - list of available categories (JSON - list of CategoryDto) - крч на руссиче - лист тех категорий, файлы которых сейчас храняться на сервере и их можно запросить  
GET /data/{id}/ - get file by category  
POST /data/ - пост запрос отправляем JSONом DataDto (на сколько я понимаю, сервак должен сам определить по mime тайпу категорию)  

Class CategoryDto

- Long id;
- String title;
