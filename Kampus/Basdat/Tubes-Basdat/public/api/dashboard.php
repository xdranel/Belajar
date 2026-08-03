<?php
require_once '../../config/path.php';
require_once CONTROLLERS_PATH . '/DashboardController.php';

header('Content-Type: application/json');

$controller = new DashboardController();

if (isset($_GET['action'])) {
    switch ($_GET['action']) {
        case 'dashboard':
            echo json_encode($controller->getDashboardData());
            break;
        case 'users':
            echo json_encode($controller->getUsersData());
            break;
        case 'products':
            echo json_encode($controller->getProductsData());
            break;
        default:
            http_response_code(400);
            echo json_encode(['error' => 'Invalid action']);
    }
} else {
    http_response_code(400);
    echo json_encode(['error' => 'No action specified']);
}
