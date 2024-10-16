# E-Commerce Application

## Table of Contents

- [Technologies Used](#technologies-used)
- [Backend](#backend)
  - [Structure](#backend-structure)
  - [Features](#backend-features)
- [Frontend](#frontend)
  - [Structure](#frontend-structure)
  - [Features](#frontend-features)
- [Setup](#setup)
  - [Backend Setup](#backend-setup)
  - [Frontend Setup](#frontend-setup)
- [Authentication and Roles](#authentication-and-roles)
- [Endpoints](#endpoints)

---

## Technologies Used

- **Backend:**
  - Java
  - Spring Boot
  - MySQL
  - Spring Security with JWT
- **Frontend:**
  - React.js (with Vite)
  - Axios for API calls
  - Bootstrap for styling

---

## Backend

### Structure

The backend is built with Spring Boot and is structured as follows:

- `config/`: Contains configuration files like security settings and MongoDB configuration.
- `controller/`: Holds the controllers for handling HTTP requests.
- `model/`: Defines the data models used throughout the application.
- `repository/`: Contains repository interfaces for data access operations.
- `service/`: Contains the business logic and services.
- `utility/`: Any helper classes or utilities.

### Features

- **Authentication**: JWT-based authentication.
- **Role Management**: Different roles for users and admins.
- **Product Management**: Allows admins to add, update, delete, and fetch products.
- **Search**: Users can search for products using keywords.
- **Security**: Role-based access control and user authentication.
  
---

## Frontend

### Structure

The frontend is built with React.js (Vite) and is organized as:

- `components/`: Contains all the components used in the application.
  - `DarkMode.jsx`: Handles theme switching between light and dark modes.
  - `AddProduct.jsx`, `UpdateProduct.jsx`: Admin pages for managing products.
  - `Cart.jsx`: Manages the user's cart and checkout process.
  - `Login.jsx`, `Register.jsx`: Handles user authentication and registration.
  - `Navbar.jsx`: Displays navigation, login/logout, and search functionality.
  - `CheckoutPopup.jsx`: Checkout page for users.
  
### Features

- **Product Browsing**: Users can browse products and add them to the cart.
- **Search**: Search functionality for products.
- **Cart Management**: Add and remove items from the cart, proceed to checkout.
- **Authentication**: Login and register pages for users.
- **Dark Mode**: Users can switch between light and dark themes.
- **Role-based Views**: Admin and user views are separated by role.

---

## Setup

### Backend Setup

1. Clone the repository.
2. Ensure that you have Java and Maven installed.
3. Run the application with the following commands:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. The application will run on `http://localhost:8080`.

### Frontend Setup

1. Navigate to the frontend directory.
2. Install the dependencies:
   ```bash
   npm install
   ```
3. Start the frontend server:
   ```bash
   npm run dev
   ```
4. The frontend will run on `http://localhost:5173`.

---

## Authentication and Roles

The application has two types of users:

- **Admin**: Can add, update, and delete products. Admins must use a secret key during registration.
- **User**: Can browse and add products to the cart.

---

## Endpoints

- **Authentication**
  - `POST /api/auth/login`: Logs in a user and returns a JWT token.
  - `POST /api/auth/register`: Registers a new user (User or Admin).
  
- **Product Management (Admin)**
  - `GET /api/products`: Fetches all products.
  - `POST /api/products`: Adds a new product (Admin only).
  - `PUT /api/products/{id}`: Updates a product (Admin only).
  - `DELETE /api/products/{id}`: Deletes a product (Admin only).

- **Cart and Checkout**
  - `GET /api/cart`: Fetches the user's cart.
  - `POST /api/cart`: Adds items to the cart.
  - `POST /api/cart/checkout`: Proceeds to checkout.

