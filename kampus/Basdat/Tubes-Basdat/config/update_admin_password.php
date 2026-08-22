<?php
require_once dirname(__DIR__) . '/config/path.php';
require_once dirname(__DIR__) . '/config/Database.php';

class PasswordUpdater {
    private $conn;
    private $table = 'users';

    public function __construct() {
        $database = new Database();
        $this->conn = $database->getConnection();
    }

    public function updateAdminPassword($username, $newPassword) {
        try {
            // First verify this is an admin account
            $query = "SELECT id FROM " . $this->table . " 
                     WHERE username = :username 
                     AND role = 'admin' 
                     AND status = 'active'";

            $stmt = $this->conn->prepare($query);
            $stmt->bindParam(':username', $username);
            $stmt->execute();

            if ($stmt->rowCount() != 1) {
                return [
                    'success' => false,
                    'message' => 'Invalid admin account or account not active'
                ];
            }

            $hashedPassword = password_hash($newPassword, PASSWORD_DEFAULT);

            $updateQuery = "UPDATE " . $this->table . " 
                          SET password = :password 
                          WHERE username = :username 
                          AND role = 'admin'";

            $updateStmt = $this->conn->prepare($updateQuery);
            $updateStmt->bindParam(':password', $hashedPassword);
            $updateStmt->bindParam(':username', $username);

            if ($updateStmt->execute()) {
                return [
                    'success' => true,
                    'message' => 'Password updated successfully'
                ];
            } else {
                return [
                    'success' => false,
                    'message' => 'Failed to update password'
                ];
            }

        } catch (PDOException $e) {
            return [
                'success' => false,
                'message' => 'Database error: ' . $e->getMessage()
            ];
        }
    }
}

$updater = new PasswordUpdater();
$result = $updater->updateAdminPassword('admin', 'admin123');
print_r($result);
