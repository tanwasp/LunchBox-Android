# 1st Iteration Plan

| Rank   | Feature                                              | Comments                                                                                                                                                     |
|--------|------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------|
| High   | Review Restaurant <br/> Check out Restaurant         | Core feature for app to work <br/> Central to users getting information on restaurants <br/> Risky because rely on restaurant database and location tracking |
| Medium | Create account <br/> Create list <br/> Follow others | Essential to a personalized user experience and starting point for social aspect <br/> Core feature of personalized user experience                          |
| Low    | Manage account                                       | Not essential to app functionality & only for user preferences                                                                                               |

In the next stage of the project, we will begin by focusing on the "Review Restaurant" and 
"Check Out Restaurant Profile" use cases, as they are fundamental to very 
concept of the app. The ability to review restaurants enriches the community-driven 
aspect, while checking out restaurant profiles provides essential information to 
users, aiding their decision-making process. 

The "Create Account" and "Create List" use cases are deemed 
medium priority as they form the basis for personalized user experience. 
Creating an account is a gateway for users to access personalized features, 
while creating a list aids in better organization and personalization of user 
preferences. However, their priority is secondary to reviewing and accessing 
restaurant profiles which directly influence user engagement and decision-making.
Nonetheless, these use cases are integral for expanding user interaction and
enhancing the overall functionality of the platform. Other features that 
further customize the user experience and increase ease of use but are not 
necessary for app core functionality will come last.

# 2nd Iteration Plan

For the second iteration of the app, we will resolve some of the simplifications we made in the last iteration.
In particular, we will be addressing the situation of the user's desired restaurant not having
an existing profile on the app. To fulfill this, we will be implementing the Add Restaurant use case.
This is important so that users can manually enter new restaurants, utilizing crowd-sourcing to produce 
a more complete and up-to-date data set.

We will also implement a simplified version of the Create Account use case which will start the personalization aspect of the app.
This will allow the users to set a username that will appear when they leave a review, and view their own account profile with 
all their reviews.

Lastly, we will improve some aspects from the previous iteration, such as incorporating price range as part of users'
reviews and using a restaurant's reviews to assign them a price category on their profile. We will also display reviews'
creation dates, which will be collected automatically when the review is made.

# 3rd Iteration Plan

Since we have established the base function of reading restaurant profiles, adding restaurants, and leaving reviews, 
we will now move onto the social aspect of the app. This includes the send
follow request and accept follow request use cases. After this iteration is complete, users
will be able to create a personal account, log into it, connect with their friends on the app, 
and filter their restaurant search to display only restaurants that have been reviewed by their friends.

These elements are central to the social component of the app, and should be in place before other use
cases that rely on it, like manage account and manage following, or less critical ones, like create list 
that provide an additional feature but are not essential to the core functionality of the app.
