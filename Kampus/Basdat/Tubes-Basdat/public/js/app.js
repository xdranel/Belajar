class AdminApp {
    constructor() {
        this.currentPage = 'dashboard';
        this.initializeEventListeners();
    }

    initializeEventListeners() {
        // Navigation links
        document.querySelectorAll('[data-page]').forEach(link => {
            link.addEventListener('click', (e) => {
                e.preventDefault();
                this.showPage(e.target.dataset.page);
            });
        });
    }

    showPage(page) {
        // Update navigation
        document.querySelectorAll('.nav-link').forEach(link => {
            link.classList.remove('active');
        });
        document.querySelector(`[data-page="${page}"]`).classList.add('active');

        // Hide all content
        document.getElementById('dashboardContent').classList.add('d-none');
        document.getElementById('usersContent').classList.add('d-none');
        document.getElementById('productsContent').classList.add('d-none');

        // Show selected content and update title
        switch (page) {
            case 'dashboard':
                document.getElementById('pageTitle').textContent = 'Dashboard Overview';
                document.getElementById('dashboardContent').classList.remove('d-none');
                break;
            case 'users':
                document.getElementById('pageTitle').textContent = 'Data Users';
                document.getElementById('usersContent').classList.remove('d-none');
                this.loadUsersData();
                break;
            case 'products':
                document.getElementById('pageTitle').textContent = 'Data Products';
                document.getElementById('productsContent').classList.remove('d-none');
                this.loadProductsData();
                break;
        }

        this.currentPage = page;
    }

    async loadUsersData() {
        const tbody = document.getElementById('usersTable');
        tbody.innerHTML = '<tr><td colspan="7" class="text-center"><div class="loading"></div> Loading...</td></tr>';

        try {
            const response = await fetch('api/dashboard.php?action=users');
            const users = await response.json();

            if (!users || users.error) {
                throw new Error(users.error || 'Failed to load users data');
            }

            tbody.innerHTML = users.map(user => `
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>
                        <span class="badge ${user.role === 'admin' ? 'bg-danger' : 'bg-primary'}">
                            ${user.role}
                        </span>
                    </td>
                    <td>
                        <span class="badge ${user.status === 'active' ? 'bg-success' : 'bg-secondary'}">
                            ${user.status}
                        </span>
                    </td>
                    <td>${this.formatDate(user.created_at)}</td>
                </tr>
            `).join('');
        } catch (error) {
            console.error('Error loading users:', error);
            tbody.innerHTML = '<tr><td colspan="7" class="text-center text-danger">Error loading data</td></tr>';
        }
    }



    async loadProductsData() {
        const tbody = document.getElementById('productsTable');
        tbody.innerHTML = '<tr><td colspan="7" class="text-center"><div class="loading"></div> Loading...</td></tr>';

        try {
            const response = await fetch('api/dashboard.php?action=products');
            const products = await response.json();

            if (!products || products.error) {
                throw new Error(products.error || 'Failed to load products data');
            }

            tbody.innerHTML = products.map(product => `
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.category}</td>
                    <td>Rp ${this.formatNumber(product.price)}</td>
                    <td>${product.stock}</td>
                    <td>
                        <span class="badge ${product.status === 'active' ? 'bg-success' : 'bg-secondary'}">
                            ${product.status}
                        </span>
                    </td>
                    <td>${this.formatDate(product.created_at)}</td>
                </tr>
            `).join('');
        } catch (error) {
            console.error('Error loading products:', error);
            tbody.innerHTML = '<tr><td colspan="7" class="text-center text-danger">Error loading data</td></tr>';
        }
    }



    formatDate(dateString) {
        const date = new Date(dateString);
        return date.toLocaleDateString('id-ID') + ' ' + date.toLocaleTimeString('id-ID', {
            hour: '2-digit',
            minute: '2-digit'
        });
    }

    formatNumber(number) {
        return new Intl.NumberFormat('id-ID').format(number);
    }
}

document.addEventListener('DOMContentLoaded', () => {
    new AdminApp();
});