# Quick Start Guide

Get the MultiTask Management System up and running in 5 minutes!

## Prerequisites Check

```bash
# Check Java version (should be 17+)
java -version

# Check Maven (optional - project includes wrapper)
mvn -version

# Check Node.js (should be 16+)
node -v
npm -v

# Check MySQL
mysql --version
```

## Quick Setup Steps

### 1. Database Setup (2 minutes)

```sql
-- Connect to MySQL
mysql -u root -p

-- Create database
CREATE DATABASE multitask_management;

-- Exit MySQL
exit;
```

Update `notes-application/src/main/resources/application.yaml`:
```yaml
spring:
  datasource:
    username: root        # Your MySQL username
    password:             # Your MySQL password
```

### 2. Backend Setup (1 minute)

**Option A: Using IntelliJ IDEA**
1. Open IntelliJ IDEA
2. File → Open → Select `notes-application` folder
3. Wait for Maven import
4. Run `NotesApplication.java` (green play button)

**Option B: Using Command Line**
```bash
cd notes-application
./mvnw spring-boot:run
```

Backend runs on: http://localhost:8080

### 3. Frontend Setup (2 minutes)

```bash
cd frontend
npm install
npm start
```

Frontend runs on: http://localhost:3000

## Verify Installation

1. Open http://localhost:3000
2. Navigate to "Notes" tab
3. Enter any email (e.g., `test@example.com`)
4. Click "Login"
5. Create a test note

If you see the note created successfully, everything is working! 🎉

## Common Issues

**Backend won't start:**
- Check MySQL is running
- Verify database credentials
- Check port 8080 is available

**Frontend won't start:**
- Run `npm install` again
- Check port 3000 is available
- Ensure backend is running first

**Database connection error:**
- Verify MySQL is running: `mysql -u root -p`
- Check database exists: `SHOW DATABASES;`
- Verify credentials in `application.yaml`

For detailed instructions, see [README.md](README.md)

