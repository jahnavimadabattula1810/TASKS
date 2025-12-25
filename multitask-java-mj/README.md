# MultiTask Management System

A comprehensive monorepo application for managing notes, customer support issues, and resident information. Built with Spring Boot (Java 17) backend and React (TypeScript) frontend with Tailwind CSS.

## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Database Setup](#database-setup)
- [Backend Setup](#backend-setup)
  - [Using IntelliJ IDEA](#using-intellij-idea)
  - [Using Command Line](#using-command-line)
- [Frontend Setup](#frontend-setup)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Architecture](#architecture)
- [Contributing](#contributing)

## ✨ Features

### 📝 Notes Management
- Email-based authentication/login
- Create, edit, and delete notes
- Responsive tile layout with modern UI
- Auto-updating timestamps
- Real-time note management

### 🎫 Customer Support Reporting
- Report support issues with category selection
- Image URL support for issue attachments
- Advanced table view with pagination
- Column-specific search filters (email, category, title)
- Issue tracking and management

### 🏠 Resident Management
- Complete CRUD operations for residents
- Table view with pagination
- Column-specific search filters (name, state, gender, flat number, rent status)
- Click row to edit functionality
- Rent status tracking with visual indicators

## 🛠 Tech Stack

### Backend
- **Java 17+** - Programming language
- **Spring Boot 4.0.1** - Framework
- **Spring Data JPA** - Data persistence
- **MySQL** - Database
- **Maven** - Build tool
- **Lombok** - Boilerplate reduction
- **Logback** - Logging framework

### Frontend
- **React 18** - UI library
- **TypeScript** - Type safety
- **Tailwind CSS** - Styling
- **React Router** - Navigation
- **Axios** - HTTP client

## 📦 Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK) 17 or higher**
  - Check installation: `java -version`
  - Download: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)

- **Maven 3.6+**
  - Check installation: `mvn -version`
  - Download: [Maven](https://maven.apache.org/download.cgi)
  - Note: The project includes Maven wrapper (`mvnw`), so Maven installation is optional

- **Node.js 16+ and npm**
  - Check installation: `node -v` and `npm -v`
  - Download: [Node.js](https://nodejs.org/)

- **MySQL 8.0+**
  - Check installation: `mysql --version`
  - Download: [MySQL](https://dev.mysql.com/downloads/mysql/)

- **IntelliJ IDEA** (Recommended for Java development)
  - Download: [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)

## 📁 Project Structure

```
MultiTaskManagementSystem/
├── notes-application/          # Spring Boot Backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/task/notes_application/
│   │   │   │       ├── controller/     # REST Controllers
│   │   │   │       ├── entity/         # JPA Entities
│   │   │   │       ├── repository/     # Data Repositories
│   │   │   │       ├── service/        # Business Logic
│   │   │   │       ├── exception/      # Exception Handlers
│   │   │   │       └── NotesApplication.java
│   │   │   └── resources/
│   │   │       ├── application.yaml    # Application config
│   │   │       └── logback-spring.xml  # Logging config
│   │   └── test/                        # Test files
│   ├── pom.xml                          # Maven dependencies
│   └── mvnw                            # Maven wrapper
│
└── frontend/                    # React Frontend
    ├── src/
    │   ├── components/          # Reusable components
    │   ├── pages/               # Page components
    │   ├── services/            # API services
    │   ├── App.tsx              # Main app component
    │   └── index.tsx            # Entry point
    ├── public/                  # Static files
    ├── package.json             # npm dependencies
    └── tailwind.config.js       # Tailwind config
```

## 🗄 Database Setup

1. **Start MySQL Server**
   - Ensure MySQL is running on your system
   - Default port: `3306`

2. **Create Database**
   ```sql
   CREATE DATABASE multitask_management;
   ```

3. **Update Database Credentials**
   
   Open `notes-application/src/main/resources/application.yaml` and update:
   
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/multitask_management
       username: root          # Change to your MySQL username
       password:               # Change to your MySQL password
   ```

   **Note:** The database tables will be created automatically by Hibernate on first run (`ddl-auto: update`).

## 🚀 Backend Setup

### Using IntelliJ IDEA

#### Step 1: Import Project

1. **Open IntelliJ IDEA**
2. **File → Open** (or `Cmd+O` on Mac / `Ctrl+O` on Windows)
3. Navigate to the `notes-application` folder
4. Select the folder and click **Open**
5. IntelliJ will detect it as a Maven project and start importing

#### Step 2: Configure JDK

1. **File → Project Structure** (or `Cmd+;` on Mac / `Ctrl+Alt+Shift+S` on Windows)
2. Under **Project Settings → Project**:
   - Set **Project SDK** to Java 17 or higher
   - Set **Project language level** to 17
3. Click **Apply** and **OK**

#### Step 3: Maven Configuration

1. IntelliJ should automatically detect Maven
2. If not, go to **File → Settings** (or `Cmd+,` on Mac / `Ctrl+Alt+S` on Windows)
3. Navigate to **Build, Execution, Deployment → Build Tools → Maven**
4. Ensure Maven home directory is set (or use bundled Maven)
5. Click **Apply**

#### Step 4: Enable Annotation Processing (for Lombok)

1. **File → Settings** → **Build, Execution, Deployment → Compiler → Annotation Processors**
2. Check **Enable annotation processing**
3. Click **Apply** and **OK**

#### Step 5: Install Lombok Plugin (Optional but Recommended)

1. **File → Settings** → **Plugins**
2. Search for "Lombok"
3. Install the Lombok plugin
4. Restart IntelliJ when prompted

#### Step 6: Build Project

1. Open the **Maven** tool window (View → Tool Windows → Maven)
2. Expand **notes-application → Lifecycle**
3. Double-click **clean** and then **install**
   - Or use the terminal: Right-click `pom.xml` → **Maven → Reload Project**

#### Step 7: Run Configuration

1. Navigate to `src/main/java/com/task/notes_application/NotesApplication.java`
2. Right-click on the file
3. Select **Run 'NotesApplication.main()'**
   - Or click the green play button next to the `main` method
4. The application will start on `http://localhost:8080`

**Alternative: Create Run Configuration**

1. **Run → Edit Configurations**
2. Click **+** → **Application**
3. Configure:
   - **Name:** NotesApplication
   - **Main class:** `com.task.notes_application.NotesApplication`
   - **Working directory:** `$MODULE_DIR$`
4. Click **OK** and run

### Using Command Line

1. **Navigate to backend directory:**
   ```bash
   cd notes-application
   ```

2. **Build the project:**
   ```bash
   ./mvnw clean install
   ```
   (On Windows: `mvnw.cmd clean install`)

3. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```
   (On Windows: `mvnw.cmd spring-boot:run`)

4. **Verify backend is running:**
   - Open browser: `http://localhost:8080`
   - You should see a Whitelabel Error Page (this is normal - the API endpoints are at `/api/*`)

## 🎨 Frontend Setup

1. **Navigate to frontend directory:**
   ```bash
   cd frontend
   ```

2. **Install dependencies:**
   ```bash
   npm install
   ```
   (This may take a few minutes)

3. **Start the development server:**
   ```bash
   npm start
   ```

4. **Verify frontend is running:**
   - Browser should automatically open to `http://localhost:3000`
   - If not, manually navigate to `http://localhost:3000`

## 🏃 Running the Application

### Start Order

1. **Start MySQL Server** (if not already running)
2. **Start Backend** (Spring Boot on port 8080)
3. **Start Frontend** (React on port 3000)

### Access the Application

- **Frontend:** http://localhost:3000
- **Backend API:** http://localhost:8080
- **API Base URL:** http://localhost:8080/api

### Testing the Application

1. **Notes Page:**
   - Enter any email address (e.g., `user@example.com`)
   - Click "Login"
   - Create, edit, and delete notes

2. **Support Page:**
   - Click "Report Issue"
   - Fill in the form and submit
   - Use filters to search issues

3. **Residents Page:**
   - Click "Add Resident"
   - Fill in resident details
   - Click on a row to edit
   - Use filters to search residents

## 📚 API Documentation

### Notes Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/notes/{email}` | Get all notes for a user email |
| POST | `/api/notes` | Create a new note |
| PUT | `/api/notes/{id}` | Update an existing note |
| DELETE | `/api/notes/{id}` | Delete a note |

**Example Request (Create Note):**
```json
POST /api/notes
{
  "userEmail": "user@example.com",
  "title": "My Note",
  "description": "Note description"
}
```

### Issues Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/issues` | Get issues with pagination and filters |
| POST | `/api/issues` | Create a new issue |
| GET | `/api/issues/{id}` | Get issue by ID |
| DELETE | `/api/issues/{id}` | Delete an issue |

**Query Parameters for GET /api/issues:**
- `page` (default: 0) - Page number
- `size` (default: 10) - Page size
- `sortBy` (default: createdDate) - Sort field
- `sortDir` (default: DESC) - Sort direction
- `email` - Filter by email
- `category` - Filter by category (TECHNICAL, BILLING, GENERAL, MAINTENANCE, OTHER)
- `title` - Filter by title

**Example Request (Create Issue):**
```json
POST /api/issues
{
  "userEmail": "user@example.com",
  "title": "Login Issue",
  "category": "TECHNICAL",
  "description": "Cannot login to the system",
  "imageUrl": "https://example.com/screenshot.png"
}
```

### Residents Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/residents` | Get residents with pagination and filters |
| POST | `/api/residents` | Create a new resident |
| GET | `/api/residents/{id}` | Get resident by ID |
| PUT | `/api/residents/{id}` | Update a resident |
| DELETE | `/api/residents/{id}` | Delete a resident |

**Query Parameters for GET /api/residents:**
- `page` (default: 0) - Page number
- `size` (default: 10) - Page size
- `sortBy` (default: id) - Sort field
- `sortDir` (default: ASC) - Sort direction
- `name` - Filter by name
- `state` - Filter by state
- `gender` - Filter by gender
- `flatNumber` - Filter by flat number
- `rentStatus` - Filter by rent status (Paid, Pending, Overdue)

**Example Request (Create Resident):**
```json
POST /api/residents
{
  "name": "John Doe",
  "state": "California",
  "gender": "Male",
  "flatNumber": "A-101",
  "rentStatus": "Paid"
}
```

## 🏗 Architecture

### Backend Architecture

- **Controller Layer**: Handles HTTP requests and responses
- **Service Layer**: Contains business logic
- **Repository Layer**: Data access layer using Spring Data JPA
- **Entity Layer**: JPA entities representing database tables
- **Exception Handler**: Global exception handling with `@ControllerAdvice`

### Frontend Architecture

- **Pages**: Main page components (Notes, Support, Residents)
- **Components**: Reusable UI components (Navigation)
- **Services**: API service layer for backend communication
- **Routing**: React Router for navigation

### Database Schema

- **notes**: Stores user notes
- **support_issues**: Stores customer support issues
- **residents**: Stores resident information

## 🔧 Troubleshooting

### Backend Issues

**Port 8080 already in use:**
- Change port in `application.yaml`: `server.port: 8081`
- Or stop the process using port 8080

**Database connection error:**
- Verify MySQL is running
- Check database credentials in `application.yaml`
- Ensure database `multitask_management` exists

**Lombok errors in IntelliJ:**
- Enable annotation processing (see IntelliJ setup)
- Install Lombok plugin
- Invalidate caches: File → Invalidate Caches / Restart

### Frontend Issues

**Port 3000 already in use:**
- React will prompt to use another port
- Or set port: `PORT=3001 npm start`

**API connection errors:**
- Ensure backend is running on port 8080
- Check CORS configuration in controllers
- Verify API base URL in `frontend/src/services/api.ts`

**npm install fails:**
- Clear npm cache: `npm cache clean --force`
- Delete `node_modules` and `package-lock.json`
- Run `npm install` again

## 📝 Logging

Logs are configured to write to:
- **Console**: All log levels
- **File**: `notes-application/logs/application.log`
- **Log Rotation**: Daily rotation with 10MB file size limit
- **Retention**: 30 days of history, 1GB total size cap

Logback configuration: `notes-application/src/main/resources/logback-spring.xml`

## 🐛 Error Handling

Global exception handling is implemented using `@ControllerAdvice`:
- **404 Not Found**: Resource not found errors
- **400 Bad Request**: Validation and client errors
- **500 Internal Server Error**: Server-side errors

All errors return JSON responses with status codes and error messages.

## 📄 License

This project is open source and available for educational purposes.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 👤 Author

Created as part of a MultiTask Management System project.

---

**Happy Coding! 🚀**
