FROM webdevops/php-nginx:8.1

ENV WEB_DOCUMENT_ROOT=/app/public

RUN docker-php-ext-install pdo pdo_mysql

WORKDIR /app

COPY . /app

RUN chown -R www-data:www-data /app