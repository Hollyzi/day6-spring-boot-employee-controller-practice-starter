API of company
GET #obtain a certain specific company with response of id, name
            method:GET
            url:/company/1
            Responsecode:200
            ResponseBody:
                        {
                        "id":1,
                        "name":"Company1"
                        }

GET #obtain list of all employee under a certain specific company
            method:GET
            url:/company/employees/1
            Responsecode:200
            ResponseBody:
            {
            "id":1,
            "name":"Company1"
            "employees":
            [
                        {
                        "id": 1,
                        "name": "name1",
                        "age": 15,
                        "gender": "FEMALE",
                        "salary": 18.0
                        },
                        {
                        "id": 2,
                        "name": "name2",
                        "age": 15,
                        "gender": "FEMALE",
                        "salary": 18.0
                        }
            ]
            }

GET #Page query, page equals 1, size equals 5, it will return the data in company list from index 0 to
index 4.
            method:GET
            url:/company?page=1&size=5
            Responsecode:200
            ResponseBody:
                        [{
                        "id":1,
                        "name":"Company1"
                        },
                        {
                        "id":2,
                        "name":"Company1"
                        },
                        {
                        "id":3,
                        "name":"Company1"
                        },
                        {
                        "id":4,
                        "name":"Company1"
                        },
                        {
                        "id":5,
                        "name":"Company1"
                        }
                        ]

PUT # update an employee with company
            method:PUT
            url:/company/employee/1/1
            requestBody:
            {
            "name": "changename",
            "age": 15,
            "gender": "FEMALE",
            "salary": 18.0
            }
            Responsecode:200
            ResponseBody:
                        {
                        "id":1,
                        "name":"Company1"
                        "employees":
                        [
                        {
                        "id": 1,
                        "name": "changename",
                        "age": 15,
                        "gender": "FEMALE",
                        "salary": 18.0
                        },
                        {
                        "id": 2,
                        "name": "name2",
                        "age": 15,
                        "gender": "FEMALE",
                        "salary": 18.0
                        }
                        ]
                        }

POST #add a company
            method:POST
            url:/company
            requestBody:
            {
            "id":1,
            "name":"Company1"
            }
            Responsecode:200
            ResponseBody:
            {
            "id":1,
            "name":"Company1"
            }

DELETE # delete a company
            method:DELETE
            url:/company/1
            Responsecode:204