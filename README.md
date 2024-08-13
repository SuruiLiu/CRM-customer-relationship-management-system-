# Customer Relationship Managment System(CRM)

![home](https://github.com/user-attachments/assets/306d28c9-be03-493a-be0b-71e911ef97f4)

## Description

This projet is a Customer Relationship Managment System(CRM), which was designed to manage complex customer interactions and data across multiple channels. 
It is a seperate system. And it embeds a XGboost model to predict the win-rate(the odds of a transaction), which help to optimize operational efficiency and resource allocation rates, and help enterprises achieve sustained business growth and development.

## Technologies Used

- **Frontend**: Vue.js, Element-ui, Echarts.
- **Backend**: Spring-boot, Mybatis-plus, Flask.
- **Database**: MySQL, Redis.
- **Other Tools**: Git, XGBoost, Random Forest, GBR, LightGBM, and Support Vector Machine.

## Installation

### Prerequisites

Make sure you have the following installed on your system:

- [Vue.js](https://vuejs.org/v2/guide/installation.html) (for frontend installation)
- [Java JDK 8](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html) (for backend installation)
- [Maven](https://maven.apache.org/install.html) (optional, if using Maven for the backend)
- [MySQL](https://dev.mysql.com/downloads/mysql/) (or your preferred database)

### Frontend Installation

1. **Navigate to the `front` directory:**

    ```bash
    cd front
    ```

2. **Install dependencies:**

    ```bash
    npm install
    ```

3. **Start the frontend development server:**

    ```bash
    npm start
    ```

4. **Open your browser and navigate to:**

    ```
    http://localhost:3000
    ```

### Backend Installation

1. **Navigate to the `back` directory:**

    ```bash
    cd back
    ```

2. **Set up the database:**

    - Create a new database in MySQL (or your preferred database).
    - Update the database configuration in the application’s properties file, typically located at `src/main/resources/application.properties`. Replace the placeholders with your database information.

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

3. **Build the project:**

    ```bash
    mvn clean install
    ```

4. **Run the backend server:**

    ```bash
    mvn spring-boot:run
    ```

5. **The backend server should now be running at:**

    ```
    http://localhost:7000
    ```

### Note

- Ensure the frontend and backend servers are running simultaneously to ensure seamless integration.
- Refer to the documentation for any additional setup or configuration options specific to your project.

## Author

- **Surui Liu** - [GitHub](https://github.com/SuruiLiu) - [Email](742901100@qq.com)

## Contact Information

If you have any questions, suggestions, or feedback, feel free to reach out to me at:

- **GitHub Issues:** [CRM-customer-relationship-management-system-/issues](https://github.com/SuruiLiu/CRM-customer-relationship-management-system-/issues)
- **LinkedIn:** [SuruiLiu](https://www.linkedin.com/in/SuruiLiu)

### Contributing

Welcome contributions from the community! 

