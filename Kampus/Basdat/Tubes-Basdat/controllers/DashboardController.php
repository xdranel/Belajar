<?php
require_once dirname(__DIR__) . '/config/path.php';
require_once MODELS_PATH . '/User.php';
require_once MODELS_PATH . '/Product.php';
require_once MODELS_PATH . '/Activity.php';


class DashboardController
{
    private $user;
    private $product;
    private $activity;

    public function __construct()
    {
        $this->user = new User();
        $this->product = new Product();
        $this->activity = new Activity();
    }

    public function getDashboardData()
    {
        $userStats = $this->user->getUserStats();
        $productStats = $this->product->getProductStats();
        $activities = $this->activity->getRecentActivities(10);

        $totalOrders = rand(100, 1000);
        $revenue = $productStats['total_value'] ? intval($productStats['total_value'] / 100) : rand(50000, 500000);

        return [
            'stats' => [
                'users' => $userStats['total'],
                'products' => $productStats['total'],
                'orders' => $totalOrders,
                'revenue' => $revenue
            ],
            'activities' => $activities
        ];
    }

    public function getUsersData()
    {
        return $this->user->getAllUsers();
    }

    public function getProductsData()
    {
        return $this->product->getAllProducts();
    }

    public function getActivitiesData()
    {
        return $this->activity->getRecentActivities(50);
    }

    public function ajaxGetDashboardData()
    {
        header('Content-Type: application/json');
        echo json_encode($this->getDashboardData());
        exit();
    }

    public function ajaxGetUsersData()
    {
        header('Content-Type: application/json');
        echo json_encode($this->getUsersData());
        exit();
    }

    public function ajaxGetProductsData()
    {
        header('Content-Type: application/json');
        echo json_encode($this->getProductsData());
        exit();
    }
}

