# Ecommerce microservices
## Introduction:
- This project is a scalable and modular Job Application Microservices System designed using Java Spring Boot. It leverages a microservices architecture to ensure flexibility, maintainability, and ease of scaling as business requirements evolve. The system is powered by PostgreSQL and Mongodb for robust and efficient data storage and retrieval.
- Discovery Server: Built with Spring Cloud Netflix Eureka, this component enables seamless service registration and discovery, ensuring smooth communication between microservices.
- API Gateway: Developed using Spring Cloud Gateway, this component acts as a single entry point for all client requests, providing routing, load balancing, and security.

## Features:
1. Customer Service
- Customer Management: Efficiently manage customer profiles, including account creation, updates, and retrieval of customer details.
- Authentication and Authorization: Implement secure login and role-based access control for enhanced data security.
- Customer Preferences: Allow customers to manage their preferences for personalized shopping experiences.
2. Notification Service:
- Multi-Channel Notifications: Support for email, SMS, and push notifications to keep customers informed.
- Event-Driven Messaging: Notify customers about order updates, payment confirmations, and promotional offers.
- Customizable Templates: Use dynamic templates for personalized and professional communication.
3. Order Service:
- Order Processing: Streamlined workflows for order placement, tracking, and management.
- Order History: Provide customers with detailed order histories and statuses.
- Stock Validation: Ensure real-time inventory checks during order placement to prevent overselling.
4. Product Service:
- Product Catalog: Manage a comprehensive catalog with categories, pricing, and availability.
- Search and Filters: Enable customers to search and filter products by attributes like size, color, and price.
- Media Management: Allow image and video uploads for a rich product display experience.
5. 
