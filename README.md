# LunchBox Prototype #2

The final iteration of the project focuses on users being able to create and access personal account info.
In addition to searching restaurants, viewing a restaurant's profile, leaving reviews, and adding restaurants, users can now create their own account, 
log into it, view their profile (with their reviews), and log out. We also improved some aspects from the previous iteration, such as 
collecting and displaying review dates and adding distance to the search results.
We also implemented data persistence and account authentication in Firebase.

## Limitations and Simplifications 
One limitation is that some functionality doesn't work if you click the buttons too fast. 
For example, if you click the search button too fast after enter the page, it says no results are found because the restaurants aren't loaded yet.
This makes some of the tests finicky because they rely on the system sleep pauses to pass.
We cache-sort ahead of time to speed up later actions, but this means it takes a while to load up. 
Additionally, the accuracy of the restaurant data is limited because it relies on unverified user input. 
The Add Restaurant form itself is also not very practical because it is rather extensive and requires information (like coordinates) that the user that the user would not have easy access to.

Some simplifications we made are the built-in inclusion of very limited set of existing restaurants and fake reviews. We use only a section of the restaurants in Firestore to avoid the
risk of surpassing the daily call limit.

## Running the App
* Clone the repository
* Start Android Studio
* Open /astudio
* Build and run in android studio
* Enjoy!

The app can be navigated using the screen-specific buttons and Android's back arrow button.

