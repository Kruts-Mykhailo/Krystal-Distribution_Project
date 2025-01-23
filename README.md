# Krystal Distribution Group

### Author: Mykhailo Kruts

<b>Short project description:</b>   

Krystal Distribution Group (KdG) is a project for an imaginary company that specializes in the efficient and seamless distribution of essential
raw materials, including gypsum, iron ore, cement, petcoke, and slag.

![image](./assets/Снимок%20экрана%202025-01-22%20в%2023.45.18.png)

The new logistics system was baptized as KdG MineralFlow, and it aims to optimize the flow of
materials from arrival scheduling to final warehousing.
Currently, KdG handles the following materials, but their state-of-the-art warehouses are capable of
adapting easily to all sorts of raw materials, so the system needs to be capable of handling these
kinds of evolutions.

<b>Backend system description:</b>  

Project that incorporated DDD (Domain Driven Design) principles with implementation of Hexagonal architecture.
  
System focuses on distinct bounded contexts like:

- Landside
- Waterside
- Warehouse
- Invoicing.

Each bounded context works as separate Java application. Communication between them is done by using RabbitMQ

Project incorporates these technologies and practices:

1. Hexagonal architecture of each Context
2. Domain driven design
3. Hibernate JPA to store data in MySQL DB
4. Different kinds of testing (e.g. Integration, Unit, Mock, etc.)
5. Messaging using RabbitMQ
6. Keycloak resource server authentication
7. API's written by following REST conventions
8. Snapshotting and Event sourcing


## Context architecture  overview: 

### Landside Context

![landside-domain-model](./assets/Снимок%20экрана%202025-01-23%20в%2019.49.02.png)

Purpose: 
* Manage warehouse information, including storage capacity and raw material stock levels. 
* Maintain records of mineral types and quantities stored.

### Waterside Context

![waterside-domain](./assets/Снимок%20экрана%202025-01-23%20в%2019.51.51.png)

Purpose:
* Track outgoing shipments.
* Track Inspection Operations, Bunkering Operation
* Match Shipping Orders with Purchase Orders


### Warehouse Context

![warehouse-domain](./assets/Снимок%20экрана%202025-01-23%20в%2019.51.13.png)

Purpose: 
* Schedule truck arrivals to ensure efficient processing and avoid congestion.
* Maintain a log of scheduled and actual arrival times. 
* Maintain a log of departures. 
* Record the weight of trucks arriving and leaving the warehouse. 
* Calculate the net weight of minerals delivered.


### Invoicing Context

![invoicing-domain](./assets/Снимок%20экрана%202025-01-23%20в%2019.52.25.png)

Purpose:
* Calculate storage fee each day per customer
* Calculate commission fee each day per customer
* At request invoicing to customers (invoice outstanding credit)
