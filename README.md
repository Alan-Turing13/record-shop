#### RECORD SHOP

![landing page!](src/main/resources/assets/landing-page.png)


A Spring Boot CRUD app for persisting your favourite albums, keeping track of them over time and (optionally) making collaborative lists to share with friends
through GitHub login. This includes with a simple landing page for displaying all the covers.

With this being a full-stack application, I broke it down into the following stages:
- testing every method with Mockito
- implementing the view page with Thymeleaf
- implementing input validation with jakarta.persistence and hibernate
- catching all possible exceptions with custom handlers
- adding login protection to the put, patch, post and delete methods.

I didn't overcomplicate the database structure, because I was intent on meeting the goal of the app as stated above.
By organising the packages and classes so that each separate component only had to focus on its particular responsibilities,
I created an app that's easy to understand, use and maintain. Through making extensive use of annotations I
kept my code free of unnecessary bloat. 

I committed regularly throughout the development process, using different branches when I wanted
to try out experimental features like caching. Taking this approach ensured that I never got stuck for too long on any one problem.


