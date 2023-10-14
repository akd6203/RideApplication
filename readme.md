# VIT Ride Application

## Table of Contents
1. [Introduction](#1-introduction)
2. [Project Overview](#2-project-overview)
3. [Technical Details](#3-technical-details)
   - 3.1. [Programming Language](#31-programming-language)
   - 3.2. [Object-Oriented Design](#32-object-oriented-design)
   - 3.3. [Class Structure](#33-class-structure)
4. [User Roles and Features](#4-user-roles-and-features)
   - 4.1. [Passenger](#41-passenger)
   - 4.2. [Driver](#42-driver)
   - 4.3. [Admin](#43-admin)
5. [Implementation Details](#5-implementation-details)
   - 5.1. [User Class](#51-user-class)
   - 5.2. [Passenger Class](#52-passenger-class)
   - 5.3. [Driver Class](#53-driver-class)
   - 5.4. [Admin Class](#54-admin-class)
6. [Running the Project](#6-running-the-project)
7. [Conclusion](#7-conclusion)

## 1. Introduction
The VIT Ride Application is a user management system developed in Java. This documentation provides a comprehensive understanding of the application, its objectives, and the technical aspects of its implementation.

## 2. Project Overview
The VIT Ride Application addresses the need for an efficient ride-sharing platform for the VIT (Vellore Institute of Technology) community. It enables users to register, log in, book rides, provide ratings, and manage their user profiles.

## 3. Technical Details

### 3.1. Programming Language
The application is implemented in Java, a versatile and widely-used programming language. Java is chosen for its platform independence, robust libraries, and strong object-oriented capabilities.

### 3.2. Object-Oriented Design
The project heavily utilizes object-oriented programming (OOP) principles, enhancing maintainability, reusability, and organization. Key OOP concepts include:
- Encapsulation
- Inheritance
- Polymorphism
- Abstraction

### 3.3. Class Structure
The application follows a structured class hierarchy, consisting of:
- User Class: The base class for all users, encompassing common attributes and methods.
- Passenger Class: Inheriting from the User Class, it adds passenger-specific attributes and methods.
- Driver Class: Inheriting from the User Class, it includes driver-specific attributes and methods.
- Admin Class: Extending the User Class, it provides admin-specific features.

## 4. User Roles and Features

### 4.1. Passenger
- **Booking Rides**: Passengers can request and book rides to their desired destinations.
- **Ride History**: Passengers can view their ride history.
- **Rating Drivers**: Passengers can rate drivers after a ride.
- **Update Profile**: Passengers can modify their name, email, and password.

### 4.2. Driver
- **Accepting Rides**: Drivers can accept ride requests from passengers.
- **Ride History**: Drivers can access their ride history.
- **Viewing Ratings**: Drivers can view their average ratings provided by passengers.
- **Update Profile**: Drivers can change their name, email, and password.

### 4.3. Admin
- **Adding/Deleting Users**: Admins can add new users to the system and remove existing users.
- **Viewing All Users**: Admins can view a list of all users registered in the application.

## 5. Implementation Details

### 5.1. User Class
The User class serves as the base class for all users. It includes attributes like name, email, password, and methods for registration and login.

### 5.2. Passenger Class
The Passenger class inherits from the User class and introduces attributes like customer_id, wallet_id, and methods for booking rides and providing ratings.

### 5.3. Driver Class
The Driver class inherits from the User class and includes attributes like driver_id, license_no, earnings, and methods for accepting rides, viewing earnings, and checking ratings.

### 5.4. Admin Class
The Admin class extends the User class and has attributes for admin_id and methods to manage users, including adding and deleting users.

## 6. Running the Project
To run the VIT Ride Application:

1. Open NetBeans or your preferred Java IDE.
2. Load the project into the IDE.
3. Build and run the project from the IDE.

## 7. Conclusion
The VIT Ride Application exemplifies the application of object-oriented programming principles in creating an efficient user management system. This documentation offers insights into its technical details and features, ensuring a clear understanding of the project's architecture and functionality.

This comprehensive documentation provides a deep dive into the VIT Ride Application, making it easier for developers and users to understand and use the system effectively
