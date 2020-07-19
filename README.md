# Organisational News Portal
Organisational News Portal is a REST API querying and retrieving scoped news and information.It retreives alll employees in a given department and news for them.It also gives general 
news that aren't for a specific department

## Author
Linda Tonui

## Setup/Installation Requirements
- Fork this repo
- Clone this repo
- Open your terminal
- Navigate to appropriate directory using the cd command
- Type in the command git clone and paste the url of clone and then press enter
- Setup Requirements for Database
- Enter the command `psql` in your terminal
- Then type `\i create.sql`


- In the App.java class in main/java folder, change the database details
- Go through all the tests in test/java folder and guided by the comments make necessary changes

# API documentation
#### Always Replace :id with the actual id

## User

- Creating user https://organization/herokuapp.com/user/new 

`{` 
`"name":"Linda Tonui",`
`"email":"tonui@too.com",`
`"pos":"CEO",`
`"role":"Boss"`
`}`
- Viewing User https://organization/herokuapp.com/user
- Viewing Specific User https://organization/herokuapp.com/user/:id


### Departments

- Creating Departments https://organization/herokuapp.com/department/new
 `
{ 
"departmentName":"Editing",
"description":"Editing of books"
}`
- Viewing Departments https://organization/herokuapp.com/departments
- Viewing Specific Department https://organization/herokuapp.com/department/:id 
- Viewing all users in a department https://organization/herokuapp.com/departments/:id/users 
- Viewing Specific User in Departments https://organization/herokuapp.com/departments/:id/users/:id 

## News

- Creating General News https://organization/herokuapp.com/news/new/general 
`{ 
"title":"Meeting",
"description":"Discussion about new head",
"user_id":1 
}`
- Creating Department News https://organization/herokuapp.com/news/new/department 
`{ 
"title":"Meeting",
"description":"Discussion about expanding",
"department_id":1, 
"user_id":1 
}`
- Viewing general news https://organization/herokuapp.com/news/general
- Viewing department news https://organization/herokuapp.com/news/department/:id


##Technologies Used
- HTML
- CSS
- Terminal
- Git
- Vs Code
- Intelli-J
- Spark
- Gradle
- Maven
- Heroku
- Java
