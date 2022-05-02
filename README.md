
# Sales-Taxes
## Sales-Taxes is full stack web app with Nuxt2 frontend + Spring Boot backend with a H2 In Memory Database. The app was tested with junit5 + assertj. 

## _Getting started with Sales-Taxes_

### With docker and docker compose:

### Prerequisites: docker, docker compose

### To run the App follow the following steps:

1. Navigate into the root directory of the project and issue following command:
```

docker-compose --build

```
#### Please navigate to localhost:3000 in your preferred browser to open the app.

### With maven and nodejs:

### Prerequisites: Java 11 , Node 16 , Nuxt 2 , Spring Boot ,Maven

### To run the App follow the following steps:

1. Start the spring boot backend server:
```
mvn spring-boot:run 

```
2. Install the frontend dependencies and start the frontend server:
Change into the directory src/nuxt and issue following commands: 
 ```
npm install

```

 ```
npm run dev

```
#### The backend server runs on localhost:8088 and the frontend server runs on localhost:3000. Please navigate to localhost:3000 in your preferred browser to open the app.

Optional but not mandatory improvements: Add frontend testing to the project, add more tests for backend.

## SALES TAXES problem description:
Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical
products that are exempt. Import duty is an additional sales tax
applicable on all imported goods at a rate of 5%, with no exemptions.
The rounding rules for sales tax are that for a tax
rate of n%, a shelf price of p contains (np/100 rounded up to the nearest 0.05) amount of
sales tax.

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

### OUTPUT:
#### Output 1:
- 1 book: 12.49
- 1 music CD: 16.49
- 1 chocolate bar: 0.85
- Sales Taxes: 1.50
- Total: 29.83
#### Output 2:
- 1 imported box of chocolates: 10.50
- 1 imported bottle of perfume: 54.65
- Sales Taxes: 7.65
- Total: 65.15
#### Output 3:
- 1 imported bottle of perfume: 32.19
- 1 bottle of perfume: 20.89
- 1 packet of headache pills: 9.75
- 1 imported box of chocolates: 11.85
- Sales Taxes: 6.70
- Total: 74.68


