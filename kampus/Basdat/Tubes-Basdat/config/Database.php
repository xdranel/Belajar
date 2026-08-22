<?php
class Database {
    private $host = 'db';
    private $db_name = 'basdat_app_test';
    private $username = 'root';
    private $password = 'plm987!';
    private $conn;

    public function getConnection() {
        $this->conn = null;

        try {
            $this->conn = new PDO(
                "mysql:host=" . $this->host . ";dbname=" . $this->db_name,
                $this->username,
                $this->password
            );
            $this->conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            error_log("Database connection successful");
        } catch(PDOException $e) {
            error_log("Connection Error: " . $e->getMessage());
            echo "Connection Error: " . $e->getMessage();
        }

        return $this->conn;
    }

}
