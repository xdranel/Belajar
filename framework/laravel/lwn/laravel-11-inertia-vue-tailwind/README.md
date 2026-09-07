# LARAVEL Simple Starter Kit
## Using:
- Laravel 11
- Inertia JS
- Vue JS
- Tailwind CSS
- ZiggyVue

## Installation

Installation Steps:
1. Clone the repository
2. Run composer install
3. Run npm install
4. cp .env.example .env
5. php artisan key:generate
- For SQLite from root folder :
```bash
touch database/database.sqlite
php artisan migrate
```
- Or configure your .env for MySQL/PostgreSQL and run:
```bash
php artisan migrate
```
And then:
- Terminal 1:
```bash
php artisan serve
```
- Terminal 2:
```bash
npm run dev
```



