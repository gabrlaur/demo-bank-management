## Bank management demo application

### About
Spring boot application to manage bank transactions, using JPA, Lombok, Flyway, MySQL.

### Running the application
<ul>
<li>Update the application.properties file with DB connection details</li>
<li>Then run the DemoBankManagementApplication.java</li>
</ul>

### Examples:
#### Uploading the bank statements csv:
Send a POST request to: /statement/upload, body example:

> account,comment,amount,currency,date </br>
LT123123123,another transaction,-500,USD,2021-08-04T10:11:30 </br>
LT123123123,,-600,USD,2021-08-04T10:11:30

#### Exporting the bank statements csv:
Get bank statements. Send GET request to: /statement/filter?startDateTime=2015-08-04T10:11:30
Other available parameters, "id" - beneficiary id, "endDateTime" - where date < "endDateTime"

Response body:
> account,amount,comment,currency,date <br>
LT123123123,12,"this is a first transaction",USD,2022-06-08T02:18:04 <br>
LT123123123,55,"This is a second transaction",USD,2022-06-12T21:53:32 <br>
LT123123123,9000,"This is a third transaction",USD,2022-06-12T21:53:44 <br>
LT123123123,123,,USD,2025-08-04T10:11:30 <br>
LT123123123,123,"another transaction",USD,2025-08-04T10:11:30 <br>
LT123123123,-123,"another transaction",USD,2025-08-04T10:11:30 <br>
LT123123123,-500,"another transaction",USD,2021-08-04T10:11:30 <br>
LT123123123,-600,,USD,2021-08-04T10:11:30 <br>

### Getting account total:
Get account total. Send GET request to: /statement/total?id=1
Other available parameters, "startDateTime" - where date > "startDateTime", "endDateTime" - where date < "endDateTime"

Response body:
> account,total <br>
LT123123123,8090


#### Additional APIs are also available. Full postman collection can be found: Demo bank.postman_collection