# systems-architecture-client

first draft of requests

GET /category/ - list of available categories (JSON - list of CategoryDto) - крч на руссиче - лист тех категорий, файлы которых сейчас храняться на сервере и их можно запросить
GET /category/{id}/ - get title of category by id - return JSON - CategoryDTO
GET /data/{id}/ - get file by category
POST /data/ - пост запрос отправляем JSONом DataDto (на сколько я понимаю, сервак должен сам определить по mime тайпу категорию)

Class DocumentDto

- String category;
- String data;

Class CategoryDto

- Long id;
- String title;

Class DataDto
- string data; - мб не строка, посмотрю как лучше хранить большие объекты в джавке
