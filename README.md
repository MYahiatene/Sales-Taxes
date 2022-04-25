# Sales-Taxes-Itemis

## SALES TAXES
Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical
products that are exempt. Import duty is an additional sales tax
applicable on all imported goods at a rate of 5%, with no exemptions. When I purchase items
I receive a receipt which lists the name of all the items and their price (including tax),
finishing with the total cost of the items,
and the total amounts of sales taxes paid. The rounding rules for sales tax are that for a tax
rate of n%, a shelf price of p contains (np/100 rounded up to the nearest 0.05) amount of
sales tax.
Write an application that prints out the receipt details for these shopping baskets…
### INPUT:
#### Input 1:
- 1 book at 12.49
- 1 music CD at 14.99
- 1 chocolate bar at 0.85
#### Input 2:
- 1 imported box of chocolates at 10.00
- 1 imported bottle of perfume at 47.50
#### Input 3:
- 1 imported bottle of perfume at 27.99
- 1 bottle of perfume at 18.99
- 1 packet of headache pills at 9.75
- 1 box of imported chocolates at 11.25



# Sales-Taxes
## Sales-Taxes is full stack web app with Nuxt2 frontend + Spring Boot backend with a H2 In Memory Database. The app was tested with junit5 + assertj. 

## Getting started with Sales-Taxes

### Prerequisites: Linux, Java 11 , Node 16 , Nuxt 2 , Spring Boot 
### Note: Maven should install most dependencies and libraries into the project folder so that you dont have to download above prerequisites manually.

To run the App follow the following steps:

1. Download all frontend and backend dependencies/files(may take some time, be patient ;)) and start the backend server.
In the Project root folder type the following command in the terminal:
```
./mvnw spring-boot:run 

```
2. Start the frontend server in another terminal(in the root directory of the project folder) as soon as the backend server is started:
 ```
npm run dev

```

### Improvements: Add frontend testing to the project, add some integration tests for backend, add docker + docker compose for easier deployment.
