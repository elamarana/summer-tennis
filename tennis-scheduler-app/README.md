# Tennis Scheduler - Spring Boot Application

A Spring Boot web application for managing tennis match schedules and scoring with persistent storage.

## Features

- 🎾 Tennis match schedule management
- 📊 Interactive scoring system with sets and tiebreakers
- 🏆 Automatic standings calculation
- 💾 Persistent score storage (JSON file)
- 📱 Responsive web interface
- 🔄 REST API for score management
- 📂 Import/Export scores functionality

## Technology Stack

- **Backend**: Spring Boot 3.2.0
- **Frontend**: HTML, CSS, JavaScript
- **Template Engine**: Thymeleaf
- **Data Storage**: JSON file (flat file storage)
- **Build Tool**: Maven
- **Java Version**: 17

## Project Structure

```
tennis-scheduler-app/
├── src/
│   └── main/
│       ├── java/com/tennis/scheduler/
│       │   ├── TennisSchedulerApplication.java
│       │   ├── controller/
│       │   │   └── TennisController.java
│       │   ├── model/
│       │   │   └── Score.java
│       │   └── service/
│       │       └── ScoreService.java
│       └── resources/
│           ├── application.properties
│           ├── templates/
│           │   └── index.html
│           └── scores.json (created automatically)
├── pom.xml
└── README.md
```

## Quick Start

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Running Locally

1. **Clone or download the project**
2. **Navigate to project directory**:
   ```bash
   cd tennis-scheduler-app
   ```

3. **Build and run the application**:
   ```bash
   # Using Maven
   mvn spring-boot:run
   
   # Or build first then run
   mvn clean package
   java -jar target/tennis-scheduler-0.0.1-SNAPSHOT.jar
   ```

4. **Access the application**:
   Open your browser and go to: `http://localhost:8080`

## API Endpoints

### Score Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/scores` | Get all scores |
| GET | `/api/scores/{matchId}` | Get score for specific match |
| POST | `/api/scores/{matchId}` | Save/update score for match |
| DELETE | `/api/scores/{matchId}` | Delete score for match |
| DELETE | `/api/scores` | Clear all scores |

### Web Pages

| Endpoint | Description |
|----------|-------------|
| `/` | Main tennis scheduler interface |

## Data Storage

Scores are stored in a JSON file located at:
```
src/main/resources/scores.json
```

The file is automatically created when the application starts if it doesn't exist.

## Deploy to GitHub Pages

### Option 1: Using GitHub Actions (Recommended)

1. **Create a GitHub repository**
2. **Push your code** to the repository
3. **Create GitHub Actions workflow**:

Create `.github/workflows/deploy.yml`:
```yaml
name: Deploy to GitHub Pages

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  build-and-deploy:
    runs-on: ubuntu-latest
    
    steps:
    - uses: actions/checkout@v3
    
    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'temurin'
        
    - name: Build with Maven
      run: mvn clean package -DskipTests
      
    - name: Deploy to GitHub Pages
      uses: peaceiris/actions-gh-pages@v3
      with:
        github_token: ${{ secrets.GITHUB_TOKEN }}
        publish_dir: ./src/main/resources/static
        publish_branch: gh-pages
```

4. **Enable GitHub Pages** in repository settings
5. **Deploy** - Your site will be available at `https://username.github.io/repository-name`

### Option 2: Manual Deployment

1. **Build the application**:
   ```bash
   mvn clean package
   ```

2. **Create a GitHub repository**
3. **Create gh-pages branch**:
   ```bash
   git checkout --orphan gh-pages
   git rm -rf .
   ```

4. **Copy static files**:
   ```bash
   cp -r src/main/resources/static/* .
   cp -r src/main/resources/templates/* .
   git add .
   git commit -m "Deploy to GitHub Pages"
   git push origin gh-pages
   ```

## Deploy to Heroku

1. **Install Heroku CLI**
2. **Login to Heroku**:
   ```bash
   heroku login
   ```

3. **Create Heroku app**:
   ```bash
   heroku create your-app-name
   ```

4. **Deploy**:
   ```bash
   git subtree push --prefix tennis-scheduler-app heroku main
   ```

## Deploy to Vercel

1. **Install Vercel CLI**:
   ```bash
   npm i -g vercel
   ```

2. **Deploy**:
   ```bash
   cd tennis-scheduler-app
   vercel
   ```

## Configuration

### Application Properties

Edit `src/main/resources/application.properties`:

```properties
server.port=8080
spring.application.name=tennis-scheduler
spring.thymeleaf.cache=false
spring.thymeleaf.mode=HTML
spring.jackson.serialization.indent-output=true
```

### Port Configuration

Change the server port by modifying:
```properties
server.port=YOUR_PORT_NUMBER
```

## Development

### Adding New Features

1. **Add new REST endpoints** in `TennisController.java`
2. **Create new services** in the `service` package
3. **Update the frontend** in `templates/index.html`
4. **Add new models** in the `model` package

### Testing

Run tests with Maven:
```bash
mvn test
```

## Troubleshooting

### Common Issues

1. **Port already in use**: Change `server.port` in `application.properties`
2. **File permission errors**: Ensure the application can write to the resources folder
3. **JSON file corrupted**: Delete `scores.json` and restart the application

### Logs

Check application logs for debugging. Logs are printed to console by default.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## License

This project is open source and available under the MIT License.

## Support

For issues and questions:
- Create an issue in the GitHub repository
- Check the troubleshooting section above
