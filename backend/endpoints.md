# Order API Endpoints

### 1. **Get All Orders**

* **Endpoint:** `GET /orders`
* **Description:** Retrieves all orders.
* **Response:** 200 OK with a list of `OrderDTO`.

### 2. **Get Order by ID**

* **Endpoint:** `GET /orders/{id}`
* **Description:** Retrieves a specific order by its ID.
* **Response:** 200 OK with an `OrderDTO`.

### 3. **Get Order by Payment ID**

* **Endpoint:** `GET /orders/payment/{id}`
* **Description:** Retrieves an order by payment ID.
* **Response:** 200 OK with an `OrderDTO`.

### 4. **Create a New Order**

* **Endpoint:** `POST /orders`
* **Description:** Creates a new order.
* **Request:** `OrderDTO`
* **Response:** 201 Created with the created `OrderDTO`.

### 5. **Get Products by Order ID**

* **Endpoint:** `GET /orders/{id}/products`
* **Description:** Retrieves products of a specific order.
* **Response:** 200 OK with a list of `ProductDTO`.

### 6. **Get Orders by User ID**

* **Endpoint:** `GET /orders/user/{userId}`
* **Description:** Retrieves all orders for a user.
* **Response:** 200 OK with a list of `OrderDTO`.

---
