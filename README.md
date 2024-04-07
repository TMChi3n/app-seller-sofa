# Database Mysql
Config connection Java Spring boot and Mysql
spring.application.name=Backend
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/sofa_new_version
spring.datasource.username=root
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Java Spring boot
Build CRUD Products
Login and Register account (Not Spring Security)
Search Product

# Android studio
Use retrofit for call api from to Java Spring boot
Gson is library read Java Object to JSON String
> implementation("com.squareup.retrofit2:retrofit:2.1.0")
> implementation("com.squareup.retrofit2:converter-gson:2.1.0")
> implementation("com.google.code.gson:gson:2.8.9")

Use picasso for draw. If draw use link is String
> implementation("com.squareup.picasso:picasso:2.71828")

Use okhttp for call url to Spring boot
> implementation("com.squareup.okhttp3:logging-interceptor:4.0.1")
> implementation("com.squareup.okhttp3:okhttp:4.9.2")

Use bumbtech, because this load image framework, that's so useful
Document https://github.com/bumptech/glide
> implementation("com.github.bumptech.glide:glide:4.16.0")

# React
Use React for management Product and User account
> npm install axios cors react react-dom react-router-dom nodemon

