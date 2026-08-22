<?php
require_once '../controllers/AuthController.php';
require_once '../controllers/DashboardController.php';

$auth = new AuthController();
$auth->requireAdmin();

$dashboard = new DashboardController();
$dashboardData = $dashboard->getDashboardData();
?>

<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - Management System</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<div class="row g-0">
    <div class="col-md-2 dashboard-sidebar">
        <div class="sidebar-brand">
            <i class="fas fa-tachometer-alt"></i> Admin Panel
        </div>
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link active" href="#" data-page="dashboard">
                    <i class="fas fa-chart-bar"></i> Dashboard
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#" data-page="users">
                    <i class="fas fa-users"></i> Data Users
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#" data-page="products">
                    <i class="fas fa-box"></i> Data Products
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="index.php?logout=1">
                    <i class="fas fa-sign-out-alt"></i> Logout
                </a>
            </li>
        </ul>
    </div>

    <div class="col-md-10 main-content">
        <div class="bg-white border-bottom p-3">
            <div class="d-flex justify-content-between align-items-center">
                <h5 id="pageTitle">Dashboard Overview</h5>
                <div>
                    <span class="badge bg-success">Online</span>
                    <span class="ms-2">
                            <i class="fas fa-user"></i> <?php echo $_SESSION['name']; ?>
                        </span>
                </div>
            </div>
        </div>

        <div id="dashboardContent" class="p-4">
            <div class="row mb-4">
                <div class="col-md-3">
                    <div class="stats-card">
                        <h3><?php echo number_format($dashboardData['stats']['users']); ?></h3>
                        <p><i class="fas fa-users"></i> Total Users</p>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="stats-card">
                        <h3><?php echo number_format($dashboardData['stats']['products']); ?></h3>
                        <p><i class="fas fa-box"></i> Total Products</p>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="stats-card">
                        <h3><?php echo number_format($dashboardData['stats']['orders']); ?></h3>
                        <p><i class="fas fa-shopping-cart"></i> Total Orders</p>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="stats-card">
                        <h3>Rp <?php echo number_format($dashboardData['stats']['revenue']); ?></h3>
                        <p><i class="fas fa-dollar-sign"></i> Revenue</p>
                    </div>
                </div>
            </div>

            <div class="data-table">
                <div class="table-header">
                    <h6><i class="fas fa-clock"></i> Recent Activity</h6>
                </div>
                <div class="table-responsive">
                    <table class="table table-hover mb-0">
                        <thead>
                        <tr>
                            <th>Time</th>
                            <th>User</th>
                            <th>Action</th>
                            <th>Status</th>
                        </tr>
                        </thead>
                        <tbody>
                        <?php if (!empty($dashboardData['activities'])): ?>
                            <?php foreach ($dashboardData['activities'] as $activity): ?>
                                <tr>
                                    <td><?php echo date('d/m/Y H:i', strtotime($activity['created_at'])); ?></td>
                                    <td><?php echo isset($activity['user_name']) ? $activity['user_name'] : 'System'; ?></td>
                                    <td><?php echo $activity['action']; ?></td>
                                    <td>
                                        <?php if ($activity['status'] == 'success'): ?>
                                            <span class="badge bg-success">Success</span>
                                        <?php else: ?>
                                            <span class="badge bg-danger">Failed</span>
                                        <?php endif; ?>
                                    </td>
                                </tr>
                            <?php endforeach; ?>
                        <?php else: ?>
                            <tr>
                                <td colspan="4" class="text-center">No recent activities</td>
                            </tr>
                        <?php endif; ?>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div id="usersContent" class="p-4 d-none">
            <div class="data-table">
                <div class="table-header">
                    <h6><i class="fas fa-users"></i> Data Users</h6>
                </div>
                <div class="table-responsive">
                    <table class="table table-hover mb-0">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Username</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>Status</th>
                            <th>Created At</th>
                        </tr>
                        </thead>
                        <tbody id="usersTable">

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div id="productsContent" class="p-4 d-none">
            <div class="data-table">
                <div class="table-header">
                    <h6><i class="fas fa-box"></i> Data Products</h6>
                </div>
                <div class="table-responsive">
                    <table class="table table-hover mb-0">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Category</th>
                            <th>Price</th>
                            <th>Stock</th>
                            <th>Status</th>
                            <th>Created At</th>
                        </tr>
                        </thead>
                        <tbody id="productsTable">

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<script src="js/app.js"></script>
</body>
</html>