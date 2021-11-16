# Board

https://www.youtube.com/watch?v=PpE7avnB4wo 

**Get Contents**
----
  Returns a list of contents

* **URL**

  /contents

* **Method:**

  `GET`

* **Success Response: Code 200**
    ```javascript
      {
        "code": 0,
        "message": "Success",
        "data": [
          {
            "id": 9,
            "name": "Elsa",
            "title": "Test title by Elsa",
            "updatedAt": "2021-11-15T21:42:49"
          },
          {
            "id": 12,
            "name": "Anna",
            "title": "Test title by Anna",
            "updatedAt": "2021-11-15T21:48:45"
          }
        ]
      }
    ```
    
 * **Sample Call:**

    ```javascript
      $.ajax({
        url: "/contents",
        dataType: "json",
        type : "GET",
        success : function(r) {
          console.log(r);
        }
      });
    ```

**Get Content Detail**
----
  Returns a content detail

* **URL**

  /contents/{id}

* **Method:**

  `GET`
  
*  **URL Params**

   id[required]

* **Success Response: Code 200**
    ```javascript
    {
      "code": 0,
      "message": "Success",
      "data": {
        "id": 9,
        "name": "Elsa",
        "title": "Test title by Elsa",
        "content": "Test content2 by Elsa",
        "updatedAt": "2021-11-15T21:42:49"
      }
    }
    ```
 
* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/contents/9",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```

**Create Content**
----
  Create content and return the content.

* **URL**

  /contents

* **Method:**

  `POST`
  
*  **Data Params**
    ```javascript
    {
      "name": "TestName",
      "title": "TestTitle",
      "content": "TestContent"
    }
    
* **Success Response: Code 200**
    ```javascript
    {
      "code": 0,
      "message": "Success",
      "data": {
        "name": "TestName",
        "title": "TestTitle",
        "content": "TestContent"
      }
    }
    ```
 
* **Sample Call:**
  ```javascript
    $.ajax({
      url: "/contents/9",
      dataType: "json",
      data: {
        name: "TestName",
        title: "TestTitle",
        content: "TestContent"
      }
      type : "POST",
      success : function(r) {
        console.log(r);
      }
    });
  ```
 **Modify Content**
----
  Modify content and return the content.

* **URL**

  /contents/{id}

* **Method:**

  `PUT`
  
*  **URL Params**

   id[required]
   
*  **Data Params**
    ```javascript
    {
      "title": "modifiedTitle",
      "content": "modifiedContent"
    }
    ```

* **Success Response: Code 200**
    ```javascript
    {
      "code": 0,
      "message": "Success",
      "data": {
        "id": 9,
        "name": "Elsa",
        "title": "modifiedTitle",
        "content": "modifiedContent",
        "updatedAt": "2021-11-15T21:42:49"
      }
    }
    ```
 
* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/contents/9",
      dataType: "json",
      data: {
        title: "modifiedTitle",
        content: "modifiedContent"
      }
      type : "PUT",
      success : function(r) {
        console.log(r);
      }
    });
  ```

**Delete Content**
----
  Delete a content

* **URL**

  /contents/{id}

* **Method:**

  `DELETE`
  
*  **URL Params**

   id[required]

* **Success Response: Code 200**
    ```javascript
    {
      "code": 0,
      "message": "Success",
      "data": {
        "id": 9,
        "name": "Elsa",
        "title": "test2",
        "content": "test1",
        "updatedAt": "2021-11-15T21:42:49"
      }
    }
    ```
 
* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/contents/9",
      dataType: "json",
      type : "DELETE",
      success : function(r) {
        console.log(r);
      }
    });
  ```
  
**Error Response**
----
* **Sample Error:**
  ```javascript
   {
    "code": 10000,
    "message": "Bad Request"
  }
  ```
