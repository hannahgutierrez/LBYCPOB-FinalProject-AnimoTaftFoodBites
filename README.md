PROJECT TITLE:
Animo Taft Food Bites: A Directory to The Food Places Around De La Salle University Manila Campus

TEAM MEMBERS:

BUNGAY, Alexia Gabrielle - alexiabungay

GUTIERREZ, Hannah Grace C. - hannahgutierrez

Valencia, John Lance B. - John Lance Valencia

PROBLEM STATEMENT & GOALS:

Eateries surrounding the De La Salle University (DLSU) Manila campus are filled with a variety of options and choosing a place to eat has always been a common question among a group of peers or even a question for your own self. Every student, faculty, and staff  may have a limited time or simply does not have the energy to roam and choose where to eat. With this project, all the dining options, opening and closing time, locations, and the list of food will be provided in one program.

TARGET USER:

The target beneficiaries of this project are all students, faculty, and staff of De La Salle University Manila Campus. The program will be beneficial in order to save time to walk around the area to choose where to eat. With all the features, planning where to eat and what to eat could lessen the hassle and stress of an individual. The users could also filter the food category based on their cravings and easily locate their destination with directions and a visual of the target location.

BRIEF DESCRIPTION:

If users will be able to check all the restaurants or cafes digitally, this would save their time and energy compared to physically looking for a place to eat. They would also be able to check all the necessary information about the food places such as visual of the location, operation hours, contact number, exact address, menu list, and direction option that will direct them to a google map where they follow the way to the target location. Often cafes and dining restaurants are object-oriented. Such that each establishment shared core characteristics (name, address, menu, and operating hours). Using OOP allows these models to have discrete objects. Which allows the design to be applicable, allowing the programmers to add multiple establishments, have updates, and more.

CORE OOP CONCEPTS:
- Encapsulation: Bundling the attributes and the methods that can be used for the data, with the direct access to the state.
- Inheritance: FoodPlaces will serve as the parent class where all the restaurants will serve as the subclass that are required to have the field and methods such as having address, menu, directions, image, category, contact number, and operating hours.
- Polymorphism: Each food place has the same features to be displayed, but different specific details as an output.
- Abstraction: Hiding the process of sorting the category of food places and will simply display the final results.

INITIAL CLASS IDEAS:

Class
- DirectionMap: directs to the google map link using getGoogleMapLink().
- MenuItem: The MenuItem class represents an individual food or beverage sold by a restaurant. Each menu item contains information such as its name, price, description, and availability. Restaurant owners can add, edit, or remove menu items through this class.
- Announcement: The Announcement class stores updates posted by restaurant owners. These announcements inform students about newly opened restaurants, operating hour changes, special promotions, new menu items, or temporary closures.
- RestaurantManager: The RestaurantManager class is responsible for managing all restaurant establishments registered in the system. It organizes restaurant records, performs searching and filtering, and updates the list of available establishments.
- Review: submitReview(), editReview(), displayReview() The Review class allows students to provide ratings and comments about restaurants they have visited. Reviews help other users make informed dining decisions based on previous customer experiences.

Abstract class
- FoodPlace: OpeningStatus() to know the opening hours, contactInformation to contact the establishment for inquiries, loadLogo() to show the logo, establishmentName to know the restaurant name, displayMenu() to notify the user what available foods are in the said establishment, and Address to know its specific location.

Inheritance or subclass
- Establishment (The Establishment name differs on the restaurant or cafe name): an extension of FoodPlace.
- Student : The Student class represents the regular users of the application. Students can browse restaurants, search food places by category, view menus, check announcements, and access directions to the selected establishment.

Interface
- Navigatable: getGoogleMapLink() provides the live directions through google map integration.
- RestaurantsCategorable: foodType() to show off the variation (chicken, fish, etc.) of food available.
- CafeCategorable: internetAvailability() to show if the place has available internet connection.
- Manageable: add(), edit(), delete() The Manageable interface defines the standard operations for managing system records. Classes that implement this interface can perform create, update, and delete operations.
- Postable: postAnnouncement() The Postable interface allows restaurant owners to publish announcements and promotional posts that will be displayed to users of the application. 

Resources
- image folder that contains the restaurant logo image, the menu images, and the location front view.
- direction folder that contains text files that hold the link directed to google maps to provide the directions

USER STORIES (Recommended):
- As a student, I  want to know all the food places around my campus so that I can satisfy my cravings and save the effort of walking under the heat.
- Across social media platforms, I have noticed that there are always students who would ask the same questions of “where is the best place to eat”, “good cafes”, and more. This project aims to aid old and new students to find and navigate their way through the food establishments around De La Salle Manila.
-As a restaurant owner, I want to manage my restaurant's information, menus, operating hours, photos, and announcements so that students can always access accurate and up-to-date information about my establishment.
-As a system administrator, I want to manage restaurant owner accounts and verify registered establishments so that the information displayed in the application remains accurate, secure, and trustworthy.

CORE FEATURES (Recommended):
- Sample Main Screen
![Test Image](https://github.com/hannahgutierrez/LBYCPOB-FinalProject-AnimoTaftFoodBites/blob/master/projectvisuals/MainScreen.png)
- Sample Details of Restaurant Chosen
![Test Image](https://github.com/hannahgutierrez/LBYCPOB-FinalProject-AnimoTaftFoodBites/blob/master/projectvisuals/SampleRestaurant.png)
- Sample Menu Option Screen
![Test Image](https://github.com/hannahgutierrez/LBYCPOB-FinalProject-AnimoTaftFoodBites/blob/master/projectvisuals/SampleMenu.png)
- Sample Direction Option Screen
![Alt text](https://i.imgur.com/8gccG7b.jpeg)
-Additional Features 
<img width="1536" height="1024" alt="AdditionalFeatures" src="https://github.com/user-attachments/assets/a7ac1efe-13f9-4b63-ab19-d7a8e9686dd7" />
