# URL Shortener Project

## Overview
This project is a URL Shortener application that allows users to shorten long URLs into compact, shareable links. It is built using **Spring Boot** for the backend, **React** for the frontend, and **AWS DynamoDB** for database storage, leveraging **TTL (Time-to-Live)** for automatic expiration of short URLs.

---

## Features
- **URL Shortening**: Convert long URLs into short, unique links.
- **Redirection**: Redirect users to the original URL when the short link is accessed.
- **Expiration with TTL**: Automatically delete expired short URLs using DynamoDB's TTL feature.
- **User Interface**: A React-based frontend for a seamless user experience.
- **Scalable Storage**: DynamoDB ensures high availability and scalability for URL storage.

---

## Architecture
1. **Frontend**: React application to create and manage short URLs.
2. **Backend**: Spring Boot REST API to handle URL shortening and redirection.
3. **Database**: AWS DynamoDB for storing short URLs and their metadata.

---

## Tech Stack
### Backend:
- **Language**: Java (Spring Boot)
- **Frameworks**: Spring Web, Spring Data DynamoDB
- **Other**: Maven, Java JDK 17+

### Frontend:
- **Language**: JavaScript/TypeScript
- **Framework**: React
- **Package Manager**: npm

### Database:
- **AWS DynamoDB**
- **TTL (Time-to-Live)** for automatic record expiration

---

## Prerequisites
1. **Backend**:
   - JDK 17 or higher
   - Maven
   - AWS credentials configured for DynamoDB access

2. **Frontend**:
   - Node.js (v14+ recommended)
   - npm or yarn

3. **AWS Services**:
   - DynamoDB table with a TTL attribute configured

---

## Future Enhancements
- Add user authentication for managing personal URLs.
- Implement analytics to track click rates for short URLs.
- Support custom short URLs.
- Add CI/CD pipelines for automated deployment.


