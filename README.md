#### RECORD SHOP

![landing page!](src/main/resources/assets/get-all-albums.png)

A Spring Boot CRUD app for persisting your favourite albums, keeping track of them over time and (optionally) making collaborative lists to share with friends
through GitHub login. This includes a 'front window'-style landing page display.

This app has an [Android Frontend](https://github.com/Alan-Turing13/record-shop-android) as well. 

With this being a full-stack application, I broke it down into the following stages:
- designing the album model
- adding methods for filtering albums by various parameters (genre, name, artist etc) 
- testing each method with Mockito
- implementing the view page with Thymeleaf and Apache Commons
- implementing input validation with jakarta.persistence and Hibernate
- catching all possible exceptions with custom handlers
- adding login protection to the put, patch, post and delete methods.

![landing page!](src/main/resources/assets/api-endpoints.png)

I didn't overcomplicate the database structure, because I was intent on meeting the goal of the app as stated above.
By organising the packages and classes so that each separate component only had to focus on its particular responsibilities,
I created an app that's easy to understand, use and maintain. Through making extensive use of annotations I
kept my code free of unnecessary bloat. 

I committed regularly throughout the development process, using different branches when I wanted
to try out experimental features like caching. Taking this approach ensured that I never spent too long on any one problem.

Update (2026): I’ve added a SeedData class which posts 30 albums to the database in case it’s empty. These are read from a text file in the resources folder. That way when you run the app from scratch you’ll be able to track the view page at http://localhost:8080/api/v1/records/albums 

