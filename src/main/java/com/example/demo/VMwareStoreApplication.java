package com.example.demo;

import com.example.demo.jpa.entities.CategoryEntity;
import com.example.demo.jpa.entities.ProductEntity;
import com.example.demo.jpa.entities.ProfileEntity;
import com.example.demo.jpa.entities.SellerEntity;
import com.example.demo.jpa.repositories.CategoryJpaRepository;
import com.example.demo.jpa.repositories.ProductJpaRepository;
import com.example.demo.jpa.repositories.SellerJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;
import java.util.HashSet;


@EnableJpaRepositories(basePackages = "com.example.demo.jpa.repositories")
@SpringBootApplication
public class VMwareStoreApplication implements CommandLineRunner {

	private final CategoryJpaRepository _categoryJpaRepository;
	private final ProductJpaRepository _productJpaRepository;
	private final SellerJpaRepository _sellerJpaRepository;

	public VMwareStoreApplication(CategoryJpaRepository _categoryJpaRepository, ProductJpaRepository _productJpaRepository, SellerJpaRepository _sellerJpaRepository) {
		this._categoryJpaRepository = _categoryJpaRepository;
		this._productJpaRepository = _productJpaRepository;
		this._sellerJpaRepository = _sellerJpaRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(VMwareStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		_categoryJpaRepository.deleteAll();
		_productJpaRepository.deleteAll();
		_sellerJpaRepository.deleteAll();

		//--------------Create two sellers-----------------------------------------
		SellerEntity judy = new SellerEntity("Judy's account id = 879");
		ProfileEntity judyProfile = new ProfileEntity(judy, "Judy", "Adams");
//		judyProfile.setBirthday(new SimpleDateFormat("MM/dd/yyyy").parse(("4/12/2010")));
		judy.setProfile(judyProfile);
		judy = _sellerJpaRepository.save(judy);
		SellerEntity michael = new SellerEntity("Micheal's account id = 023");
		ProfileEntity michaelProfile = new ProfileEntity(michael, "Michael", "Martin");
		michael.setProfile(michaelProfile);
		michael = _sellerJpaRepository.save(michael);


		//--------------Create 4 different categories and save them--------------------
		CategoryEntity artCategory = new CategoryEntity("Art");
		CategoryEntity wallDecorCategory = new CategoryEntity("Wall Decor");
		CategoryEntity babyCategory = new CategoryEntity("Baby");
		CategoryEntity toysCategory = new CategoryEntity("Toys");
		artCategory = _categoryJpaRepository.save(artCategory);
		wallDecorCategory = _categoryJpaRepository.save(wallDecorCategory);
		babyCategory = _categoryJpaRepository.save(babyCategory);
		toysCategory = _categoryJpaRepository.save(toysCategory);


		//--------------Create a product in wall decor and art categories--------------
//		List<String> imageUrls = new ArrayList<>();
//		imageUrls.add("https://c.pxhere.com/photos/b1/ab/fantastic_purple_trees_beautiful_jacaranda_trees_pretoria_johannesburg_south_africa-1049314.jpg!d");
//		imageUrls.add("https://c.pxhere.com/photos/90/da/jacaranda_trees_flowering_purple_stand_blossom_spring_plant-922332.jpg!d");
		ProductEntity pictureProductEntity = new ProductEntity("Framed Canvas Wall Art",
								42.34f, michael, new HashSet<>(Arrays.asList(artCategory, wallDecorCategory)));
		pictureProductEntity = _productJpaRepository.save(pictureProductEntity);



		//--------------Create a product in toys and baby categories------------------
//		imageUrls.add("https://c.pxhere.com/photos/ba/a9/still_life_teddy_white_read_book_background_blue-844147.jpg!d");
//		imageUrls.add("https://c.pxhere.com/photos/56/ab/still_life_teddy_white_read_book_background_blue-844152.jpg!d");
		ProductEntity dollProductEntity = new ProductEntity("Teddy Bear", 24.25f,
				judy, new HashSet<>(Arrays.asList(babyCategory, toysCategory)));
		dollProductEntity = _productJpaRepository.save(dollProductEntity);


		////////////////////////Test MongoDB///////////////////////////////////////////////////

//		//--------------Create a seller-----------------------------------------------
//		Profile profile = new Profile("Peter", "Smith", Gender.Male);
//		Seller seller = new Seller("Peter's account id = 391", profile);
//		_sellerMongoRepository.save(seller);
//
//		System.out.println("__________________________________________________________________");
//		System.out.println("Test MongoDB repository");
//		System.out.println("Find seller(s) by first name");
//		_sellerMongoRepository.findByFirstName("Peter").forEach(System.out::println);
//		System.out.println("__________________________________________________________________");
//
//
//		//--------------Create four different categories in MongoDB-------------------
//		Category furnitureCategory = new Category("Furniture");
//		Category handmadeCategory = new Category("Handmade");
//		furnitureCategory = _categoryMongoRepository.save(furnitureCategory);
//		handmadeCategory = _categoryMongoRepository.save(handmadeCategory);
//		Category kitchenCategory = new Category("Kitchen");
//		kitchenCategory = _categoryMongoRepository.save(kitchenCategory);
//		Category woodCategory = new Category();
//		woodCategory.setName("Wood");
//		woodCategory = _categoryMongoRepository.save(woodCategory);
//
//
//		//--------------Create a product in two different categories------------------
//		EmbeddedCategory woodEmbedded = new EmbeddedCategory(woodCategory.getId(), woodCategory.getName());
//		EmbeddedCategory handmadeEmbedded = new EmbeddedCategory(handmadeCategory.getId(), handmadeCategory.getName());
//		HashSet<EmbeddedCategory> categoryList = new HashSet<>(Arrays.asList(woodEmbedded, handmadeEmbedded));
//		Product desk = new Product("A Wooden Desk", "Made with thick solid reclaimed wood, Easy to Assemble", 249.99f, seller, categoryList);
//		desk = _productMongoRepository.save(desk);
//
//		Update update = new Update();
//		update.addToSet("productsOfCategory", desk.getId());
//		List<String> ids = desk.getFallIntoCategories().stream().map(EmbeddedCategory::getId).collect(Collectors.toList());
//		Query myUpdateQuery = new Query();
//		myUpdateQuery.addCriteria(Criteria.where("_id").in(ids));
//		UpdateResult updateResult = mongoOperation.updateMulti(myUpdateQuery, update, Category.class);
//		System.out.println("__________________________________________________________________");
//		System.out.println("The count of categories which updated after saving the desk is:  " + String.valueOf(updateResult.getMatchedCount()));
//
//
//		//--------------Create a product in one category------------------------------
//		EmbeddedCategory furnitureEmbedded = new EmbeddedCategory(furnitureCategory.getId(), furnitureCategory.getName());
//		categoryList = new HashSet<>(Arrays.asList(furnitureEmbedded));
//		Product diningChair = new Product("Antique Dining Chair",
//				"This mid-century fashionable chair is quite comfortable and attractive.", 234.20f, seller, categoryList);
//		diningChair = _productMongoRepository.save(diningChair);
//
//		update = new Update();
//		update.addToSet("productsOfCategory", diningChair.getId());
//		ids = diningChair.getFallIntoCategories().stream().map(EmbeddedCategory::getId).collect(Collectors.toList());
//		myUpdateQuery = new Query();
//		myUpdateQuery.addCriteria(Criteria.where("_id").in(ids));
//		updateResult = mongoOperation.updateMulti(myUpdateQuery, update, Category.class);
//		System.out.println("__________________________________________________________________");
//		System.out.println("The count of categories which updated after saving the dining chair is:  " + String.valueOf(updateResult.getMatchedCount()));
//
//
//		//--------------Create a product in three different categories------------------
//		EmbeddedCategory kitchenEmbedded = new EmbeddedCategory(kitchenCategory.getId(), kitchenCategory.getName());
//		categoryList = new HashSet<>(Arrays.asList(handmadeEmbedded, woodEmbedded, kitchenEmbedded));
//		Product spoon = new Product("Bamboo Spoon", "This is more durable than traditional hardwood spoon, safe to use any cookware.", 13.11f, seller, categoryList);
//		spoon = _productMongoRepository.save(spoon);
//
//		update = new Update();
//		update.addToSet("productsOfCategory", spoon.getId());
//		ids = spoon.getFallIntoCategories().stream().map(EmbeddedCategory::getId).collect(Collectors.toList());
//		myUpdateQuery = new Query();
//		myUpdateQuery.addCriteria(Criteria.where("_id").in(ids));
//		updateResult = mongoOperation.updateMulti(myUpdateQuery, update, Category.class);
//		System.out.println("__________________________________________________________________");
//		System.out.println("The count of categories which updated after saving wooden spoon is:  " + String.valueOf(updateResult.getMatchedCount()));
	}
}
