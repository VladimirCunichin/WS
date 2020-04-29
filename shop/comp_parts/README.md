# PC Parts List

## Launching app
App runs on port 5000, to run:
``` docker-compose up ```

## Usage

### GET

**Get all parts**

```http://localhost:5000/api/parts ```

**Get specific part by id**

```http://localhost:5000/api/parts/<id> ```

**Response**
- On success - returns part/parts and status `200`
- On failure - status `404`

### POST

**Posting new part**

``` 
{
"manufacturer": "X",
"name": "Y",
"price": "Z",
"type": "D"
}
```
It will create new part:
```
{'id': max_id+1,
'manufacturer': "X",
'name': "Y",
'type': "Z",
'price': "D"
}
```
if put request dosent have one of manufaturer, name, price or type, web service will respond with error.

***Example***
```
{
"manufacturer": "AMD",
 "name": "Ryzen 3 2200G",
 "price": "96.19",
 "type": "CPU"
}
```

**Response**
- On success - Address to new part ant status `201`
- On failure - status `400`

### DELETE

**Removing part by part id**

```http://localhost:5000/api/parts/<id> ```

**Response**
- On success - status `204`
- On failure - status `404`

### PUT

**Changing values of specific part**


```http://localhost:5000/api/parts/<id>```

You can change any value, by sending it as json, for example, to change <id> parts name, you need to send put request with:

```{"name": "new_name"}```

This will change <id> parts name to "new_name"

Same goes with manufacturer, price and type. You can change two or more fields at the same time. For example, chaging name and type of part, you need to send:

```
{ 
"name": "new_name", 
"type": "new_type"
}
```

**Response**
- On success - status `201`
- On failure - status `400`