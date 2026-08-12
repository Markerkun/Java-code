package org.example;

import org.example.entities.OrderItemEntity;
import org.example.entities.OrderyEntity;
import org.example.entities.ReviewEntity;
import net.datafaker.Faker;
import org.example.entities.CategoryEntity;
import org.example.entities.ProductEntity;
import org.example.entities.ProductPhotoEntity;
import org.example.entities.UserEntity;
import org.example.utils.HibernateHelper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;

public class Main {
    // Папка для зображень всередині проєкту
    private static final Path IMAGES_DIR = Paths.get("images", "products");
    public static void main(String[] args) {
//        System.out.println("OOP Java 3");
        try {
            System.out.println("Підлкючення до БД");
            //Самі прості таблиці -
            // 1.Категорія, 2.Товар, 3.Фото товарів, 4.Користувачі,
            // 5.Відгуки товарів
            // 6.Замовлення користувачів і 7.товари в замовлені.

            var session = HibernateHelper.getSession();

            seedCategories(session);
            seedProductsWithPhotos(session);
            seedUsers(session);
            seedReviews(session);
            seedOrders(session);

            // ....
            HibernateHelper.shutDown();

        }catch (Exception e) {
            System.out.println("Щось пішло не так"+e.getMessage());
        }
    }

    private static void seedCategories(Session session) {
        Random random = new Random(12345);
        Faker faker = new Faker(random);

        List<String> categoryNames = List.of(
                "Електроніка", "Одяг та взуття", "Дім і сад",
                "Спорт та відпочинок", "Краса та здоров'я",
                "Книги", "Іграшки", "Автотовари", "Продукти харчування"
        );

        Transaction transaction = session.beginTransaction();

        for (String name : categoryNames) {
            Long count = session.createQuery(
                            "select count(c) from CategoryEntity c where c.name = :name", Long.class)
                    .setParameter("name", name)
                    .uniqueResult();

            if (count == 0) {
                CategoryEntity category = new CategoryEntity();
                category.setName(name);
                category.setDescription(faker.lorem().sentence(10));
                session.persist(category);
            }
        }

        transaction.commit();
        System.out.println("Категорії перевірені/додані");
    }

    private static void seedProductsWithPhotos(Session session) throws IOException {
        Random random = new Random(54321); // окремий seed для продуктів
        Faker faker = new Faker(random);

        // Створюємо папку для зображень, якщо її немає
        Files.createDirectories(IMAGES_DIR);

        // Забираємо всі наявні категорії з БД
        Query<CategoryEntity> query = session.createQuery(
                "from CategoryEntity", CategoryEntity.class);
        List<CategoryEntity> categories = query.list();

        if (categories.isEmpty()) {
            System.out.println("Немає категорій у БД, продукти не будуть створені");
            return;
        }

        Transaction transaction = session.beginTransaction();

        int productCounter = 1;

        for (CategoryEntity category : categories) {
            int productsPerCategory = 5; // скільки товарів на категорію

            for (int i = 0; i < productsPerCategory; i++) {
                ProductEntity product = new ProductEntity();
                product.setName(faker.commerce().productName());
                product.setDescription(faker.lorem().sentence(15));
                product.setPrice(BigDecimal.valueOf(
                                faker.number().randomDouble(2, 50, 5000))
                        .setScale(2, RoundingMode.HALF_UP));
                product.setQuantityInStock(faker.number().numberBetween(0, 200));
                product.setCategory(category);

                session.persist(product); // потрібно для отримання id перед фото

                // Генеруємо 1-3 фото на товар
                int photosCount = faker.number().numberBetween(1, 4);
                for (int p = 0; p < photosCount; p++) {
                    String fileName = "product_" + productCounter + "_" + (p + 1) + ".jpg";
                    Path localPath = IMAGES_DIR.resolve(fileName);

                    // Використовуємо picsum.photos із фіксованим seed -> завжди однакові картинки
                    String seedValue = "product" + productCounter + "-" + p;
                    String imageUrl = "https://picsum.photos/seed/" + seedValue + "/400/400";

                    downloadImage(imageUrl, localPath);

                    ProductPhotoEntity photo = new ProductPhotoEntity();
                    photo.setUrl(localPath.toString().replace("\\", "/")); // зберігаємо шлях у БД
                    photo.setIsMain(p == 0);
                    photo.setProduct(product);

                    session.persist(photo);
                }

                productCounter++;
            }
        }

        transaction.commit();
        System.out.println("Продукти та фото успішно згенеровані: " + (productCounter - 1));
    }

    /**
     * Скачує зображення за URL і зберігає локально
     */
    private static void downloadImage(String imageUrl, Path destination) {
        try (InputStream in = URI.create(imageUrl).toURL().openStream()) {
            Files.copy(in, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Скачано: " + destination.getFileName());
        } catch (IOException e) {
            System.out.println("Не вдалося скачати " + imageUrl + ": " + e.getMessage());
        }
    }

    private static void seedUsers(Session session) {
        Random random = new Random(99999); // окремий seed для користувачів
        Faker faker = new Faker(random);

        int usersToCreate = 20;
        int createdCount = 0;

        Transaction transaction = session.beginTransaction();

        for (int i = 0; i < usersToCreate; i++) {
            String username = faker.name().username(); // напр. john.doe
            String email = faker.internet().emailAddress();

            // Перевірка на унікальність у БД (username і email мають бути unique)
            Long existing = session.createQuery(
                            "select count(u) from UserEntity u where u.username = :username or u.email = :email",
                            Long.class)
                    .setParameter("username", username)
                    .setParameter("email", email)
                    .uniqueResult();

            if (existing > 0) {
                continue; // пропускаємо дублікат, не рахуємо у createdCount
            }

            UserEntity user = new UserEntity();
            user.setUsername(username);
            user.setEmail(email);
            // У реальному проєкті пароль треба хешувати (BCrypt тощо),
            // тут для seed-даних просто фейкове значення
            user.setPassword("123456");
            user.setPhone(faker.phoneNumber().phoneNumber());

            // Випадкова дата реєстрації за останні 2 роки, детермінована seed'ом
            LocalDateTime registrationDate = faker.timeAndDate()
                    .past(730, java.util.concurrent.TimeUnit.DAYS)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            user.setRegistrationDate(registrationDate);

            session.persist(user);
            createdCount++;
        }

        transaction.commit();
        System.out.println("Користувачі успішно згенеровані: " + createdCount);
    }

    private static void seedReviews(Session session) {
        Random random = new Random(77777);

        List<UserEntity> users = session.createQuery(
                "from UserEntity", UserEntity.class
        ).list();

        List<ProductEntity> products = session.createQuery(
                "from ProductEntity", ProductEntity.class
        ).list();

        if (users.isEmpty()) {
            System.out.println("Немає користувачів. Спочатку запустіть seedUsers()");
            return;
        }

        if (products.isEmpty()) {
            System.out.println("Немає товарів. Спочатку запустіть seedProductsWithPhotos()");
            return;
        }

        String[] comments = {
                "Дуже хороший товар, повністю задоволений покупкою.",
                "Товар відповідає опису, все чудово.",
                "Якість на високому рівні, рекомендую.",
                "Досить непоганий товар за свою ціну.",
                "Швидка доставка, товар прийшов без пошкоджень.",
                "Користуюся вже декілька днів, поки все добре.",
                "Замовлення отримав вчасно. Якість сподобалась.",
                "Очікував трохи кращої якості.",
                "Товар нормальний, але є невеликі недоліки.",
                "Все сподобалося, обов'язково замовлю ще.",
                "Ціна відповідає якості.",
                "Дуже задоволений покупкою.",
                "Товар чудовий, рекомендую цей магазин.",
                "Не зовсім відповідає моїм очікуванням.",
                "Працює добре, проблем не виникло."
        };

        Transaction transaction = session.beginTransaction();

        int createdCount = 0;

        for (int i = 0; i < 50; i++) {

            UserEntity user = users.get(
                    random.nextInt(users.size())
            );

            ProductEntity product = products.get(
                    random.nextInt(products.size())
            );

            ReviewEntity review = new ReviewEntity();

            review.setRating(
                    random.nextInt(5) + 1
            );

            review.setComment(
                    comments[random.nextInt(comments.length)]
            );

            review.setCreatedAt(
                    LocalDateTime.now().minusDays(
                            random.nextInt(730)
                    )
            );

            review.setUser(user);
            review.setProduct(product);

            session.persist(review);
            createdCount++;
        }

        transaction.commit();

        System.out.println(
                "Відгуки успішно згенеровані: " + createdCount
        );
    }

    private static void seedOrders(Session session) {

        Random random = new Random(88888);

        List<UserEntity> users = session.createQuery(
                "from UserEntity", UserEntity.class
        ).list();

        List<ProductEntity> products = session.createQuery(
                "from ProductEntity", ProductEntity.class
        ).list();

        if (users.isEmpty()) {
            System.out.println(
                    "Немає користувачів. Спочатку запустіть seedUsers()"
            );
            return;
        }

        if (products.isEmpty()) {
            System.out.println(
                    "Немає товарів. Спочатку запустіть seedProductsWithPhotos()"
            );
            return;
        }

        String[] addresses = {
                "м. Рівне, вул. Соборна, 10",
                "м. Київ, вул. Хрещатик, 25",
                "м. Львів, вул. Городоцька, 15",
                "м. Луцьк, вул. Лесі Українки, 20",
                "м. Тернопіль, вул. Руська, 12",
                "м. Одеса, вул. Дерибасівська, 30",
                "м. Харків, вул. Сумська, 18",
                "м. Дніпро, просп. Дмитра Яворницького, 45",
                "м. Вінниця, вул. Соборна, 32",
                "м. Житомир, вул. Київська, 17"
        };

        OrderyEntity.OrderStatus[] statuses =
                OrderyEntity.OrderStatus.values();

        Transaction transaction = session.beginTransaction();

        int ordersCreated = 0;
        int itemsCreated = 0;

        for (int i = 0; i < 20; i++) {

            UserEntity user = users.get(
                    random.nextInt(users.size())
            );

            OrderyEntity order = new OrderyEntity();

            order.setUser(user);

            order.setOrderDate(
                    LocalDateTime.now().minusDays(
                            random.nextInt(365)
                    )
            );

            order.setDeliveryAddress(
                    addresses[random.nextInt(addresses.length)]
            );

            order.setStatus(
                    statuses[random.nextInt(statuses.length)]
            );

            BigDecimal totalPrice = BigDecimal.ZERO;

            List<ProductEntity> shuffledProducts =
                    new ArrayList<>(products);

            Collections.shuffle(shuffledProducts, random);

            // Від 1 до 4 різних товарів у замовленні
            int itemsCount = Math.min(
                    random.nextInt(4) + 1,
                    shuffledProducts.size()
            );

            session.persist(order);

            for (int j = 0; j < itemsCount; j++) {

                ProductEntity product =
                        shuffledProducts.get(j);

                int quantity = random.nextInt(5) + 1;

                BigDecimal price = product.getPrice();

                BigDecimal itemTotal = price.multiply(
                        BigDecimal.valueOf(quantity)
                );

                totalPrice = totalPrice.add(itemTotal);

                OrderItemEntity item = new OrderItemEntity();

                item.setOrder(order);
                item.setProduct(product);
                item.setQuantity(quantity);
                item.setPricePerUnit(price);

                session.persist(item);

                itemsCreated++;
            }

            order.setTotalPrice(
                    totalPrice.setScale(
                            2,
                            RoundingMode.HALF_UP
                    )
            );

            session.merge(order);

            ordersCreated++;
        }

        transaction.commit();

        System.out.println(
                "Замовлення успішно згенеровані: "
                        + ordersCreated
        );

        System.out.println(
                "Товари в замовленнях: "
                        + itemsCreated
        );
    }
}