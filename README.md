# Board

**Get Contents**
----
  Returns a list of contents

* **URL**

  /contents

* **Method:**

  `GET`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 
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

  /content/{id}

* **Method:**

  `GET`
  
*  **URL Params**

   id[required]

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 
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

  /content

* **Method:**

  `PUT`

* **Success Response:**

  * **Code:** 200 <br /> 
    **Content:** 
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
  
  
  * **Error Response:**

  * **Content:** 
    ```javascript
     {
      "code": 10000,
      "message": "Bad Request"
    }
    ```
