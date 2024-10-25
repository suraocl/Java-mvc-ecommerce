SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `product`;
DROP TABLE IF EXISTS `cart`;
DROP TABLE IF EXISTS `cart_product`;
DROP TABLE IF EXISTS `category`;
DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS `order_product`;
DROP TABLE IF EXISTS `paymentmethod`;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `user` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    `role` VARCHAR(255) DEFAULT 'USER' NOT NULL,
    `cartId` INTEGER NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `product` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `price` DOUBLE NOT NULL,
    `imageUrl` VARCHAR(255) NOT NULL,
    `categoryId` INTEGER NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `cart` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`id`)
);

CREATE TABLE `cart_product` (
    `cartId` INTEGER NOT NULL,
    `productId` INTEGER NOT NULL,
    `quantity` INTEGER NOT NULL,
    PRIMARY KEY (`cartId`, `productId`)
);

CREATE TABLE `category` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `order` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `createdAt` DATETIME NOT NULL,
    `userId` INTEGER NOT NULL,
    `paymentMethodId` INTEGER NOT NULL,
    `address` VARCHAR(255) NOT NULL,
    `totalPrice` DOUBLE NOT NULL,
    `status` VARCHAR(255) DEFAULT "BEKLIYOR",
    PRIMARY KEY (`id`)
);

CREATE TABLE `order_product` (
    `orderId` INTEGER NOT NULL,
    `productId` INTEGER NOT NULL,
    `quantity` INTEGER NOT NULL,
    PRIMARY KEY (`orderId`, `productId`)
);

CREATE TABLE `paymentmethod` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `methodName` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

ALTER TABLE `user` 
    ADD FOREIGN KEY (`cartId`) REFERENCES `cart`(`id`) ON DELETE CASCADE;
ALTER TABLE `product` 
    ADD FOREIGN KEY (`categoryId`) REFERENCES `category`(`id`);
ALTER TABLE `cart_product` 
    ADD FOREIGN KEY (`cartId`) REFERENCES `cart`(`id`) ON DELETE CASCADE,
    ADD FOREIGN KEY (`productId`) REFERENCES `product`(`id`) ON DELETE CASCADE;
ALTER TABLE `order` 
    ADD FOREIGN KEY (`userId`) REFERENCES `user`(`id`) ON DELETE CASCADE,
    ADD FOREIGN KEY (`paymentMethodId`) REFERENCES `paymentmethod`(`id`);
ALTER TABLE `order_product` 
    ADD FOREIGN KEY (`orderId`) REFERENCES `order`(`id`) ON DELETE CASCADE,
    ADD FOREIGN KEY (`productId`) REFERENCES `product`(`id`) ON DELETE CASCADE;

-- Disable foreign key checks
SET FOREIGN_KEY_CHECKS = 0;
-- Kullanıcı verilerini ekleyin
INSERT INTO `user` (`name`, `email`, `password`, `role`, `cartId`) VALUES
('Ahmet Yılmaz', 'ahmet@example.com', 'sifre123', 'USER', 1),
('Ayşe Kaya', 'ayse@example.com', 'sifre456', 'USER', 2),
('Fatma Öztürk', 'fatma@example.com', 'sifre789', 'USER', 3),
('Mehmet Demir', 'mehmet@example.com', 'sifre101112', 'USER', 4),
('Zeynep Kocaman', 'zeynep@example.com', 'sifre131415', 'USER', 5),
('Emirhan Tekin', 'emirhan@example.com', 'sifre161718', 'USER', 6),
('İrem Yıldırım', 'irem@example.com', 'sifre192021', 'USER', 7),
('Can Özdemir', 'can@example.com', 'sifre222324', 'USER', 8),
('Yusuf Şahin', 'yusuf@example.com', 'sifre252627', 'USER', 9),
('Gizem Aslan', 'gizem@example.com', 'sifre282930', 'USER', 10),
('Admin', 'admin@admin.com', '123456', 'ADMIN', 11);

-- Sepet verilerini ekleyin
INSERT INTO `cart` VALUES (NULL);
INSERT INTO `cart` VALUES (NULL);
INSERT INTO `cart` VALUES (NULL);
INSERT INTO `cart` VALUES (NULL);
INSERT INTO `cart` VALUES (NULL);
INSERT INTO `cart` VALUES (NULL);
INSERT INTO `cart` VALUES (NULL);
INSERT INTO `cart` VALUES (NULL);
INSERT INTO `cart` VALUES (NULL);
INSERT INTO `cart` VALUES (NULL);
INSERT INTO `cart` VALUES (NULL);

-- Kategori verilerini ekleyin
INSERT INTO `category` (`name`) VALUES
('Elektronik'),
('Moda'),
('Ev'),
('Spor'),
('Kozmetik'),
('Süpermarket');

-- Ürün verilerini ekleyin
INSERT INTO `product` (`name`, `price`, `imageUrl`, `categoryId`, `description`) VALUES
('Dizüstü Bilgisayar', 999.99, 'https://productimages.hepsiburada.net/s/777/222-222/110000664868344.jpg/format:webp', 1, 'Yüksek performanslı bir dizüstü bilgisayar.'),
('Akıllı Telefon', 499.99, 'https://productimages.hepsiburada.net/s/476/222-222/110000518914645.jpg/format:webp', 1, 'En son model bir akıllı telefon.'),
('Roman', 19.99, 'https://productimages.hepsiburada.net/s/196/222-222/110000164557160.jpg/format:webp', 2, 'En çok satan bir roman.'),
('T-shirt', 14.99, 'https://productimages.hepsiburada.net/s/777/300-400/110000690728565.jpg/format:webp', 3, 'Rahat bir pamuklu T-shirt.'),
('Spor Ayakkabı', 79.99, 'https://productimages.hepsiburada.net/s/19/300-400/9828188848178.jpg/format:webp', 4, 'Yüksek kaliteli koşu ayakkabısı.'),
('Ruj', 9.99, 'https://productimages.hepsiburada.net/s/196/222-222/110000165426235.jpg/format:webp', 5, 'Uzun süre kalıcı bir ruj.'),
('Kahve Makinesi', 49.99, 'https://productimages.hepsiburada.net/s/42/222-222/10735746908210.jpg/format:webp', 3, 'Programlanabilir bir kahve makinesi.'),
('Akıllı Saat', 199.99, 'https://productimages.hepsiburada.net/s/442/222-222/110000475798462.jpg/format:webp', 1, 'Fitness izleme özellikli bir akıllı saat.'),
('Sırt Çantası', 29.99, 'https://productimages.hepsiburada.net/s/166/222-222/110000128845736.jpg/format:webp', 4, 'Dayanıklı ve geniş bir sırt çantası.'),
('Kulaklık', 89.99, 'https://productimages.hepsiburada.net/s/777/222-222/110000679538914.jpg/format:webp', 1, 'Gürültü engelleme özellikli kulaklık.'),
('Bluetooth Hoparlör', 49.99, 'https://productimages.hepsiburada.net/s/503/222-222/110000557527979.jpg/format:webp', 1, 'Taşınabilir ve kablosuz bir Bluetooth hoparlör.'),
('Gaming Mouse', 29.99, 'https://productimages.hepsiburada.net/s/449/222-222/110000483500287.jpg/format:webp', 1, 'Yüksek hassasiyetli bir oyun faresi.'),
('Akıllı Termostat', 99.99, 'https://productimages.hepsiburada.net/s/777/222-222/110000688963974.jpg/format:webp', 3, 'Evinizi uzaktan kontrol edebilen bir akıllı termostat.'),
('Seyahat Valizi', 79.99, 'https://productimages.hepsiburada.net/s/31/222-222/10318944731186.jpg/format:webp', 3, 'Dayanıklı ve hafif bir seyahat valizi.'),
('Spor Sutyen', 19.99, 'https://productimages.hepsiburada.net/s/402/300-400/110000428108101.jpg/format:webp', 4, 'Yüksek destekli bir spor sutyeni.'),
('Kablosuz Kulaklık', 39.99, 'https://productimages.hepsiburada.net/s/512/222-222/110000567316682.jpg/format:webp', 1, 'Rahat ve yüksek kaliteli kablosuz kulaklık.'),
('Yoga Matı', 24.99, 'https://productimages.hepsiburada.net/s/43/222-222/10758076268594.jpg/format:webp', 4, 'Kaymaz ve ekstra kalın bir yoga matı.'),
('Diz Altı Çorap', 9.99, 'https://productimages.hepsiburada.net/s/399/300-400/110000424302926.jpg/format:webp', 2, 'Rahat ve dayanıklı diz altı çoraplar.'),
('Bisiklet Kaskı', 34.99, 'https://productimages.hepsiburada.net/s/26/222-222/10140337111090.jpg/format:webp', 4, 'Güvenliğiniz için yüksek kaliteli bir bisiklet kaskı.'),
('Mutfak Bıçağı Seti', 49.99, 'https://productimages.hepsiburada.net/s/39/222-222/10615345545266.jpg/format:webp', 3, 'Keskin ve dayanıklı mutfak bıçakları seti.'),
('Bluetooth Oyuncu Kulaklığı', 69.99, 'https://productimages.hepsiburada.net/s/777/222-222/110000679538914.jpg/format:webp', 1, 'Profesyonel oyuncular için yüksek performanslı bir Bluetooth kulaklık.'),
('Spor Eldiven', 14.99, 'https://productimages.hepsiburada.net/s/562/222-222/110000625607219.jpg/format:webp', 4, 'Antrenman sırasında ellerinizi koruyan ve destekleyen spor eldivenleri.'),
('Güneş Gözlüğü', 29.99, 'https://productimages.hepsiburada.net/s/63/222-222/110000004599231.jpg/format:webp', 2, 'Modaya uygun ve güneş ışınlarını engelleyen güneş gözlüğü.'),
('Bardak Seti', 19.99, 'https://productimages.hepsiburada.net/s/129/222-222/110000079491111.jpg/format:webp', 3, 'Şık ve dayanıklı bir bardak seti.'),
('Spor Çanta', 39.99, 'https://productimages.hepsiburada.net/s/777/300-400/110000669839135.jpg/format:webp', 4, 'Spor ekipmanlarınızı taşımak için dayanıklı ve şık bir spor çantası.');

-- Sepet_ürün verilerini ekleyin
INSERT INTO `cart_product` (`cartId`, `productId`, `quantity`) VALUES
(1, 1, 1),
(1, 3, 2),
(2, 5, 1),
(3, 7, 1),
(4, 2, 3),
(5, 6, 2),
(6, 9, 1),
(7, 8, 1),
(8, 4, 2),
(9, 10, 1),
(10, 3, 3);

-- Sipariş verilerini ekleyin
INSERT INTO `order` (`createdAt`, `userId`, `paymentMethodId`, `address`, `totalPrice`) VALUES
('2023-01-01 10:00:00', 1, 1, '123 Ana Cadde', 360),
('2023-02-01 12:00:00', 2, 2, '456 Çınar Sokak', 360),
('2023-03-01 15:00:00', 3, 1, '789 Çam Caddesi', 180),
('2023-04-01 18:00:00', 4, 2, '101 Akasya Sokak', 240),
('2023-05-01 20:00:00', 5, 1, '202 Ladin Sokak', 120);

-- Sipariş_ürün verilerini ekleyin
INSERT INTO `order_product` (`orderId`, `productId`, `quantity`) VALUES
(1, 1, 5),
(1, 3, 4),
(2, 2, 1),
(3, 5, 2),
(4, 7, 1),
(5, 6, 1);

-- Ödeme yöntemi verilerini ekleyin
INSERT INTO `paymentmethod` (`methodName`) VALUES
('Kredi Kartı'),
('PayPal');


-- Enable foreign key checks
SET FOREIGN_KEY_CHECKS = 1;
