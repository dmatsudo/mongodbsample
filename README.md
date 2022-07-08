# mongodbsample

This is a sample project using MongoDB and exposing Springboot CRUD enpoints to an entity.

It uses Docker to setup the DB so Docker needs to be installed as PreReq.

### Working with DB
Containers creation:
```bash
docker compose -f docker-compose.yaml up -d
```
Useful commands:
```bash
# Enter to mongodb container
docker exec -it mongodb bash
# Entering mongo client
mongo mongodb://localhost:27017 -u rootuser -p rootpass
```
Some mongo useful commands:
```bash
db.help()

show dbs
use <db_name> (creates db and/or switches to the db)
db.createCollection("<collection_name>")
show collections
db.<collection_name>.drop()

db.<collection_name>.insert(<json>) / insertMany(<json_array>)
db.<collection_name>.count()
db.<collection_name>.find()
db.users.find(			---> Collection
	{age: {$gt: 18}},	---> query criteria
	{name: 1, address: 1}	---> Projection
).limit(5)			---> cursor modifier

db.<collection_name>.update(<criteria>, <update>) / updateMany()

db.<collection_name>.deleteOne(<criteria>) / deleteMany()

db.dropDatabase()
```
### Application:
The SpringBoot component is a basic three layer application which exposes CRUD endpoints in a Controller. It uses a MongoRepository for the persistence of one Entity.