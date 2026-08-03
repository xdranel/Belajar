from locust import HttpUser, task, between

class AdminUser(HttpUser):
    wait_time = between(1, 3) #Wait 1 -> 3

    def on_start(self):
        """Login when the test starts"""
        self.login()

    def login(self):
        """Perform login"""
        response = self.client.post("/", {
            "username": "admin",
            "password": "admin123",
            "login": "true"
        })

        if response.status_code != 200:
            print(f"Login failed with status code: {response.status_code}")

    @task(3)
    def get_dashboard_data(self):
        """Get dashboard data"""
        self.client.get("/api/dashboard.php?action=dashboard")

    @task(2)
    def get_users_data(self):
        """Get users data"""
        self.client.get("/api/dashboard.php?action=users")

    @task(2)
    def get_products_data(self):
        """Get products data"""
        self.client.get("/api/dashboard.php?action=products")

    @task(1)
    def logout(self):
        """Perform logout"""
        self.client.get("/?logout=1")
        self.login()

#locust --host=http://localhost:8080
