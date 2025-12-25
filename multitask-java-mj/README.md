# MultiTask Management System

A comprehensive application for managing notes, customer support issues, and resident information. Built with Spring Boot (Java 17) backend and React (JavaScript) frontend with CSS.

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
- **JavaScript** - Script
- **Tailwind CSS** - Styling
- **React Router** - Navigation
- **Axios** - HTTP client

## Database Setup

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
       password: M.Jahnavi@123            # Change to your MySQL password
   ```

   **Note:** The database tables will be created automatically by Hibernate on first run (`ddl-auto: update`).

##  Backend Setup

#### Build Project

1. Open the **Maven** tool window (View → Tool Windows → Maven)
2. Expand **notes-application → Lifecycle**
3. Double-click **clean** and then **install**
   - Or use the terminal: Right-click `pom.xml` → **Maven → Reload Project**

#### Run Configuration

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

## Frontend Setup

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

##  Running the Application

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


##  Troubleshooting

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

