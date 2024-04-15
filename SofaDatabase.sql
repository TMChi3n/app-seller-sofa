-- Create new database named 'sofa_orders'
CREATE DATABASE sofa_new_version;
-- Switch to the newly created database
USE sofa_new_version;
-- Create tables users, sofas, description, images, carts
CREATE TABLE users (
    id_user int primary key auto_increment,
    username nvarchar(255) not null,
    email nvarchar(255) not null,
    password nvarchar(255) not null
);
CREATE TABLE sofas (
    id_sofa int primary key auto_increment,
    name_sofa longtext not null,
    price decimal not null,
    descriptions longtext not null,
    img_url longblob not null
);
CREATE TABLE carts (
    id_cart int primary key auto_increment,
    quantity int not null,
    product_name nvarchar(255),
    amount decimal(20,2),
    id_user int not null,
    foreign key (id_user) references users(id_user)
);
-- Insert data into users, sofas, description, images, carts tables
INSERT INTO users (
        id_user,
        username,
        email,
        password
    )
VALUES (
        1,
        'Nguyễn Văn A',
        'a@gmail.com',
        '123'
    ),
    (
        2,
        'Nguyễn Văn B',
        'b@gmail.com',
        '123'
    ),
    (
        3,
        'Nguyễn Thị C',
        'c@gmail.com',
        '123'
    ),
    (
        4,
        'Admin',
        'admin@gmail.com',
        '111'
    );
INSERT INTO sofas (name_sofa, price, descriptions, img_url)
VALUES (
        'Ghế Sofa da bò LHG-899',
        48000000,
        'Size: 2m8 ( ~ có thể thay đổi )
      Material: Da bò thật Ý bề mặt tiếp xúc 50% da bò, 50% similiKhung Gỗ Sồi Mỹ đã xử lý chống mối mọt Nệm mút cao su non kết hợp lò xo đàn hồi tốt, mang lại cảm giác êm ái dễ chịu
      Guarantee: 5 năm
      Brand: Linh Hoàng Gia
      Origin: Việt Nam',
        'https://zsofa.vn/wp-content/uploads/2023/12/sofa-da-bo-898.jpeg'
    ),
    (
        'Ghế Sofa thư giãn da bò ZT201S',
        13500000,
        'Size: 85 ~ 95cm
      Material: Da Bò mặt tiếp xúc 50%
      Guarantee: 2 năm
      Brand: Linh Hoàng Gia
      Origin: Việt Nam',
        'https://zsofa.vn/wp-content/uploads/2024/03/z5268077674902_0ea2040a15195064025ad39037de0e89.jpg'
    ),
    (
        'Ghế Sofa cao cấp văn phòng ZP148',
        25500000,
        'Size: ghế băng ~2m, ghế đơn ~1m ( có thể tùy chỉnh )
      Material: da PU Hàn Quốc
      Guarantee: 4 năm
      Brand: Anh Anh
      Origin: Việt Nam',
        'https://zsofa.vn/wp-content/uploads/2024/03/sofa-kfv.jpg'
    );
INSERT INTO carts (id_cart, quantity, product_name, amount, id_user)
VALUES (1, 5, "Ghế Sofa da bò LHG-899", 200000000, 1),
    (2, 10, "Ghế Sofa thư giãn da bò ZT201S", 1230000000, 2),
    (3, 7, "Ghế Sofa cao cấp GL 1909", 2420000000, 3);
-- Create orders table
CREATE TABLE orders (
    id_order int primary key auto_increment,
    description_order longtext not null,
    id_user int not null,
    foreign key (id_user) references users(id_user)
);
-- Insert data into orders table
insert into orders
values (1, "Hóa đơn mua hàng gồm có sản phẩm: ghế sofa cao cấp, với mức tiền chỉ có 12500000 đồng", 1),
    (2, "Hóa đơn mua hàng gồm có sản phẩm: ghế sofa cao cấp, với mức tiền chỉ có 12500000 đồng", 2),
    (3, "Hóa đơn mua hàng gồm có sản phẩm: ghế sofa cao cấp, với mức tiền chỉ có 12500000 đồng", 3);
-- Add your INSERT statements for the orders table here if needed
-- Create token table
