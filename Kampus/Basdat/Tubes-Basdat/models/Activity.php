<?php
require_once dirname(__DIR__) . '/config/path.php';
require_once CONFIG_PATH . '/Database.php';

class Activity
{
    private $conn;
    private $table = 'activities';

    public $id;
    public $user_id;
    public $action;
    public $description;
    public $status;
    public $created_at;

    public function __construct()
    {
        $database = new Database();
        $this->conn = $database->getConnection();
    }

    public function getRecentActivities($limit = 10)
    {
        $query = "SELECT a.id, a.action, a.description, a.status, a.created_at,
                         u.name as user_name
                  FROM " . $this->table . " a
                  LEFT JOIN users u ON a.user_id = u.id
                  ORDER BY a.created_at DESC
                  LIMIT :limit";

        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(':limit', $limit, PDO::PARAM_INT);
        $stmt->execute();

        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function create()
    {
        $query = "INSERT INTO " . $this->table . " 
                  (user_id, action, description, status) 
                  VALUES (:user_id, :action, :description, :status)";

        $stmt = $this->conn->prepare($query);

        $stmt->bindParam(':user_id', $this->user_id);
        $stmt->bindParam(':action', $this->action);
        $stmt->bindParam(':description', $this->description);
        $stmt->bindParam(':status', $this->status);

        return $stmt->execute();
    }

    public static function log($user_id, $action, $description = '', $status = 'success')
    {
        $activity = new Activity();
        $activity->user_id = $user_id;
        $activity->action = $action;
        $activity->description = $description;
        $activity->status = $status;

        return $activity->create();
    }
}
