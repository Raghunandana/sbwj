# sbwj
Spring Boot Application for add and list Suburb and Postcodes



Post Code API
	URL :  http://192.168.1.10:9080/
1.	List all the Suburb
Service list the all the Suburb
GET	   sbwj/action/getAllSuburb

Accept : application/json
Content-Type : application/json

●	Request :  NA

●	Response : 

[{
		id: 3,
		suburb: "City C",
		postcode: "ABCC"
	},
	{
		id: 2,
		suburb: "City AB",
		postcode: "AB"
	},
	{
		id: 1,
		suburb: "City AE",
		postcode: "ABC"
	},
	{
		id: 4,
		suburb: "City AAD",
		postcode: "ABD"
	}
]		


2.	Get the List of Suburb based on the postcode
Service used list all the Suburb belongings given postcode
GET	   sbwj/action /getSuburbList/{postcode}

Accept : application/json
Content-Type : application/json

●	Request :  
abc
●	Response : 

[{
		"id": 1,
		"suburb": "City AE",
		"postcode": "ABC"
	},
	{
		"id": 3,
		"suburb": "City C",
		"postcode": "ABCC"
	}
]
3.	Get the List of Suburb based on the range of postcode
Service used list the Suburb based on the range of postcode provided
GET	   sbwj/action /getPostcodeRange/{data}

Accept : application/json
Content-Type : application/json
Request :  
ab-abccddd

●	Response : 
[{
		id: 2,
		suburb: "City AB",
		postcode: "AB"
	},
	{
		id: 1,
		suburb: "City AE",
		postcode: "ABC"
	},
	{
		id: 3,
		suburb: "City C",
		postcode: "ABCC"
	}
]
4.	Insert Suburb Information
Service used insert list of Suburb and postcode information
POST	   sbwj/action /insertSubUrb

Accept : application/json
Content-Type : application/json

●	Request :  
{
	"data": [{
			"suburb": "City E",
			"postcode": "ABCEE"
		},
		{
			"suburb": "City F",
			"postcode": "ABFF"
		}
	]

}

●	Response : 

{
	"code": 200,
	"issuccess": true
}

