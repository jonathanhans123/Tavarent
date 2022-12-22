# Taverent-Kotlin-

-- Untuk Android Studio
Cara membuka program

Secara Local
1. Clone ke PC dulu
2. Buka Tavarent-Tavarent-/Tavarent
3. XAMPP nyalakan lalu php artisan serve
4. php artisan migrate
5. php artisan db:seed
6. Buka Project Android Studio, terus ke app->res->values->strings.xml
7. Comment line 4 dan Uncomment line 3
P.S Project Android Studio baru bisa jalan setelah laravel di serve dan di migrate

Secara Webhosting
1. Buka Project Android Studio, terus ke app->res->values->strings.xml
2. Uncomment line 4 dan Comment line 3


--Untuk Laravel
Apa yang perlu dilakukan supaya laravel bisa jalan
Secara Local
1. Copas file .env.example rename menjadi .env
2. Lalu pada file .env ganti menjadi ini
    APP_KEY=base64:OyR4/WkzmkQVJtEatV3ZJfFsrrswdkJ0BGliV6T6z0Y=
    DB_DATABASE=db_tavarent
    MAIL_MAILER=mailtrap
    MAIL_HOST=smtp.mailtrap.io
    MAIL_PORT=2525
    MAIL_USERNAME=43c4f4a9791ec2
    MAIL_PASSWORD=f81b217467fe21
    MAIL_ENCRYPTION=tls
    MAIL_FROM_ADDRESS="hello@example.com"
    MAIL_FROM_NAME="${APP_NAME}"
3. Composer install
4. php artisan migrate --seed
5. php artisan storage:link
6. php artisan serve

Secara Website
1. Pergi ke tavarent.000webhost.com

