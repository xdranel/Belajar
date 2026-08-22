<?php
require_once dirname(__DIR__) . '/config/path.php';
require_once CONFIG_PATH . '/Database.php';

class User
{
    private $conn;
    private $table = 'users';

    public $id;
    public $username;
    public $password;
    public $name;
    public $email;
    public $role;
    public $status;
    public $created_at;

    public function __construct()
    {
        $database = new Database();
        $this->conn = $database->getConnection();
    }

    public function login($username, $password)
    {
        $query = "SELECT id, username, password, name, email, role, status 
                  FROM " . $this->table . " 
                  WHERE username = :username AND role = 'admin' AND status = 'active'";

        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(':username', $username);
        $stmt->execute();

        if ($stmt->rowCount() == 1) {
            $row = $stmt->fetch(PDO::FETCH_ASSOC);

            if (password_verify($password, $row['password'])) {
                $this->id = $row['id'];
                $this->username = $row['username'];
                $this->name = $row['name'];
                $this->email = $row['email'];
                $this->role = $row['role'];
                $this->status = $row['status'];
                return true;
            }
        }
        return false;
    }

    public function getAllUsers()
    {
        $query = "SELECT id, username, name, email, role, status, created_at 
                  FROM " . $this->table . " 
                  ORDER BY created_at DESC";

        $stmt = $this->conn->prepare($query);
        $stmt->execute();

        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function getUserStats()
    {
        $query = "SELECT 
                    COUNT(*) as total,
                    SUM(CASE WHEN status = 'active' THEN 1 ELSE 0 END) as active,
                    SUM(CASE WHEN role = 'admin' THEN 1 ELSE 0 END) as admins
                  FROM " . $this->table;

        $stmt = $this->conn->prepare($query);
        $stmt->execute();

        return $stmt->fetch(PDO::FETCH_ASSOC);
    }

    public function findById($id)
    {
        $query = "SELECT * FROM " . $this->table . " WHERE id = :id";
        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(':id', $id);
        $stmt->execute();

        return $stmt->fetch(PDO::FETCH_ASSOC);
    }
}