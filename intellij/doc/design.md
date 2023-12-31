# Sequence Diagrams

## Create Account

```plantuml
@startuml
hide footbox
skin rose

actor User as user
participant " : SignupFragment" as signupFrag
participant " : MainView" as mainView
participant " : LandingFragment" as landFrag
participant " : Controller" as controller
participant " : LoginActivity" as loginAct
participant " : FireStore" as fs

landFrag -> controller : onNavigateToSignup()
controller -> loginAct : launch(signupIntent)
loginAct -> mainView : displayFragment(signupFragment)
mainView -> user : Display Signup page
user -> signupFrag : Enter new account info
signupFrag -> loginAct : onSignup(username,email,password)
loginAct -> fs : Save user
loginAct -> mainView : updateCurrentUser(user)
mainView -> user : Display Home page

@enduml
```

## Log In

```plantuml
@startuml
hide footbox
skin rose

actor User as user
participant " : LoginFragment" as loginFrag
participant " : MainView" as mainView
participant " : LandingFragment" as landFrag
participant " : Controller" as controller
participant " : LoginActivity" as loginAct
participant " : FireStore" as fs

landFrag -> controller : onNavigateToLogin()
controller -> loginAct : launch(loginIntent)
loginAct -> mainView : displayFragment(loginFragment)
mainView -> user : Display Login page
user -> loginFrag : Enter log in info
loginFrag -> loginAct : onLogin(email,password)
loginAct -> fs : Verify user
loginAct -> mainView : updateCurrentUser(user)
mainView -> user : Display Home page

@enduml
```

## View Profile

```plantuml
@startuml
hide footbox
skin rose

actor User as user
participant " : NavigationDrawer" as navDrawer
participant " : MainView" as mainView
participant " : Controller" as controller
participant "revLib : ReviewsLibrary" as revLib

user -> navDrawer : Select Profile page
navDrawer -> controller : onNavigateToMyProfile()
controller -> revLib : getReviewsByUser(User)
controller -> mainView: displayFragment(profileFragment)
mainView -> user : Display User's profile

@enduml
```

## Check Out Restaurant

```plantuml
@startuml
hide footbox
skin rose

actor User as user
participant " : SearchFragment" as searchFrag
participant " : HomeFragment" as homeFrag
participant " : MainView" as mainView
participant " : Controller" as controller
participant "lib : RestaurantLibrary" as lib
participant "revLib : ReviewsLibrary" as revLib

homeFrag -> controller : onNavigateToSearch()
controller -> mainView : displayFragment(searchFragment)
mainView -> user : Display search page
user -> searchFrag : Enter search parameters
searchFrag -> controller : onPerformSearch(term, priceFilter, distanceFilter, sort)
controller -> lib : matches = search(term, filters, sort, curUser)
controller -> mainView : displaySearchResults(matches)
mainView -> user : Display restaurants matching criteria
user -> searchFrag : Select desired restaurant
searchFrag -> controller : onNavigateToRestaurant(desiredRestaurant)
controller -> revLib : getReviews(selectedResult)
controller -> mainView : displayFragment(restaurantFragment)
mainView -> user : Display selected restaurant info
@enduml
```

## Review Restaurant
Picks up directly after Check Out Restaurant:

```plantuml
@startuml
hide footbox
skin rose

actor User as user
participant " : AddReviewFragment" as addRevFrag
participant " : RestaurantFragment" as restFrag
participant " : MainView" as mainView
participant " : Controller" as controller
participant "revLib : ReviewsLibrary" as revLib
participant "lib : RestaurantLibrary" as lib
participant " : Restaurant" as restaurant

user -> restFrag : Select Add Review
restFrag -> controller : onNavigateToPostReview(restaurant)
controller -> mainView : displayFragment(addRevFragment)
mainView -> user : Display add review form
user ->  addRevFrag : Enter review information
addRevFrag -> controller : onAddReview(rating, comment, id, price)
controller -> revLib : newReviewId = addReview(curUser, restaurantId, rating, reviewText)
controller -> lib : addReviewToRest(restaurantId, newReviewId)
controller -> restaurant: computeRating(revLib)
controller -> restaurant: computePriceRange(revLib)
controller -> mainView : displayFragment(restaurantFragment)
mainView -> user : Display selected restaurant info

@enduml
```

## Add Restaurant
Picks up directly after "Display restaurants matching criteria" step in Check Out Restaurant:

```plantuml
@startuml
hide footbox
skin rose

actor User as user
participant " : SearchFragment" as searchFrag
participant " : AddRestaurantFragment" as addRestFrag
participant " : MainView" as mainView
participant " : Controller" as controller
participant "lib : RestaurantLibrary" as lib

user -> searchFrag : Select Add Restaurant
searchFrag -> controller : onNavigateToAddRestaurant()
controller -> mainView : displayFragment(addRestaurantFragment)
mainView -> user : Display add restaurant form
user -> addRestFrag : Enter restaurant info
addRestFrag -> controller : addRestaurant(name, address, city, state, country, postalCode, lat, lon)
controller -> lib : addRestaurant(name,address,city,state,lat,lon)
controller -> mainView : displayFragment(restaurantFragment)
mainView -> user : Display new restaurant profile

@enduml
```

# Design Class Diagram

```plantuml
@startuml
skin rose
'skinparam classAttributeIconSize 0

class Restaurant{
    -restaurantId: String
    -name: String
    -location: Location
    -rating: float
    -address: String
    -city: String
    -state: String
    -country: String
    -postalCode: String
    -loc: Location
    -review_list: ArrayList<String>
    +distanceToUser: float
    +priceRange: int
    --
    getDistDisplay(): String
    addressDisplay(): String
    getPriceRangeDisplay(): int
    getRatingDisplay(): String
    setDistToUser(u: User): void
    computeRating(revLib: ReviewsLibrary): void
    computePriceRange(revLib: ReviewsLibrary): void
    getDollarSigns(priceRange: int): String
}

class Review{
    -reviewId: String
    -rating: float
    -body: String
    -username: String
    -restaurantId: String
    -date: DateTime
    --
    toString(): String
}

class User{
    -userName: String
    -joinedDateTime: Date
    -loc: Location
    --
    getUsername(): String
    getJoinedDate(): Date
    getLat(): float
    getLon(): float
}

class RestaurantLibrary{
    -data: HashMap<String, Restaurant>
    --
    getRestaurant(id: String): Restaurant
    loadRestaurants(restaurants: List<Restaurant>): void
    search(term: String, filters: Set<IFilter>, sort: String, curUser: User): ArrayList<Restaurant>
    addRestaurant(name: String, address: String, city: String, state: String, country: String, postalCode: String, lat: float, lon: float): Restaurant
}

class ReviewsLibrary{
    -data: HashMap<String, Review>
    --
    addReview(curUser: User, restaurantId: String, rating: float, reviewText: String): String
    addReviewToReviewsLibrary(review: Review): void
    getReviewsByRestaurant(r: Restaurant): ArrayList<Review>
    getReviewsByUser(u: User): ArrayList<Review>
    loadReviews(reviews: List<Review>): void
}

interface IFilter { 
    --
    filter(restaurants: Collection<Restaurant>): Collection<Restaurant>
}

class PriceFilter{
    -price: int
}

class LocFilter{
    -distance: int
    -u: User
}

class Location{
    -lat: float
    -lon: float
    --
    haversine(loc: Location) : float
}

class LoginActivity{
    -mainView: IMainView
    -signupFragment: SignupFragment
    -loginFragment: LoginFragment
    --
    onSignup(username: String, email: String, password: String): void
    checkUsernameExists(username: String, email: String, password: String): void
    onLogin(email: String, password: String): void
}

class ControllerActivity{
    {static} -lib: RestaurantLibrary
    {static} -revLib: ReviewsLibrary
    -curUser: User
    -mainView: IMainView
    --
    onNavigateToSearch(): void
    onPerformSearch(searchTerm: String, priceFilter: String, distanceFilter: String, sortOption: String): void
    onNavigateToRestaurant(restaurant: Restaurant, reversible: Boolean): void
    onNavigateToPostReview(restaurantId:String): void
    onAddReview(rating: float, comment: String, restaurantId: String, priceSymbol: int): void
    onNavigateToAddRestaurant(): void
    addRestaurant(name: String, address: String, city: String, state: String, country: String, postalCode: String, lat: String, lon: String): void
}

package view{

}

' associations
IFilter <|.. PriceFilter
IFilter <|.. LocFilter
PriceFilter "0..1" .. "1" RestaurantLibrary: Helps filter
LocFilter "0..1" .. "1" RestaurantLibrary
RestaurantLibrary "1" - "1..*" Restaurant : \tIs-information-expert-of\t\t
Review "*" -down- "1" ReviewsLibrary: \tIs managed by\t\t
Restaurant "1" -right- "*" Review: Can have\t\t
Location "1" -down- "1" Restaurant : Is an attribute of\t
RestaurantLibrary "1" -down- "1" ControllerActivity: Provides information to\t
ReviewsLibrary "1" -down- "1" ControllerActivity: \tProvides information to\t
User "1" -right- "1" ControllerActivity : \tIs an attribute of\t\t
ControllerActivity "1" - "1" LoginActivity: \tDisplays screens for\t
ControllerActivity "1" -down- "1" view: \tCommunicates with user using

@enduml
```


View Package:
```plantuml
@startuml
skin rose
'skinparam classAttributeIconSize 0

interface IMainView { 
    --
    View getRootView()
    void displayFragment(Fragment fragment, boolean reversible, String name, int popCount)
    void displaySearchResults(ArrayList<Restaurant> searchResults)
    void clearBackStack()
    void setupNavigationDrawer(MainActivity mainActivity)
    AppBarConfiguration getAppBarConfiguration()
    DrawerLayout getDrawerLayout()
    void showAppBar()
    void hideAppBar()
    NavController getNavController()
    NavigationView getNavigationView()
}

interface IAddRestaurantView { 
}

interface IAddRestaurantView.Listener {
    --
    void addRestaurant(String name, String address, String city, String state, String country, String postalCode, String lat, String lon)
}

interface IAddReviewView { 
}

interface IAddReviewView.Listener { 
    --
    void onAddReview(float rating, String comment, String id, int priceSymbol)
}

interface IHomeView { 
}

interface IHomeView.Listener { 
    --
    void onNavigateToSearch()
    void getUserReviewsNavToProfile()
    void onLogout()
}

interface IRestaurantView { 
}

interface IRestaurantView.Listener { 
    --
    void onNavigateToPostReview(String restaurantId)
}

class MainView{
    +binding: MainBinding
    +fmanager: FragmentManager
    -appBarConfiguration: AppBarConfiguration
    -navController: NavController
    +navigationView: NavigationView
}

class AddRestaurantFragment{
    -binding: FragmentAddRestaurantBinding 
    -listener: Listener
}

class AddReviewFragment{
    -binding: FragmentAddReviewBinding
    -listener: Listener
    -restId: String
    -restaurantName: String
}

class HomeFragment{
    -binding: FragmentHomeBinding 
    -listener: Listener
}

class RestaurantFragment{
    -binding: FragmentRestaurantBinding 
    -listener: Listener
    -restaurant: Restaurant
    -reviewsRecyclerView: RecyclerView
    -reviewAdapter: ReviewAdapter
    -reviewsList: List<Review>
}


IMainView <|.. MainView
IRestaurantView <|.. RestaurantFragment
IRestaurantView +-- IRestaurantView.Listener
IHomeView <|.. HomeFragment
IHomeView +-- IHomeView.Listener
IAddRestaurantView <|.. AddRestaurantFragment
IAddRestaurantView +-- IAddRestaurantView.Listener
IAddReviewView <|.. AddReviewFragment
IAddReviewView +-- IAddReviewView.Listener

@enduml
```

```plantuml
@startuml
skin rose
'skinparam classAttributeIconSize 0

interface ISearchView { 
    --
    void updateSearchResults(List<Restaurant> searchResults)
    void showNoResultsMessage(boolean show)
    void showNoResultsMessage(boolean show)
}

interface ISearchView.Listener { 
    --
    void onPerformSearch(String searchTerm, String priceFilter, String distanceFilter, String sortOption)
    void onNavigateToRestaurant(Restaurant restaurant, boolean reversible, int popCount)
    void onNavigateToAddRestaurant()
}

class SearchFragment{
    -binding: FragmentSearchBinding 
    -listener: Listener
    -searchResultsRecyclerView: RecyclerView
    -restaurantAdapter: RestaurantAdapter
    --
    void onNavigateToRestaurant(Restaurant restaurant)
}

abstract class AuthFragment{
    #EMAIL_PATTERN: String
    #emailPattern: Pattern
    --
    boolean isValidEmail(String email)
    boolean isValidPassword(String password)
    boolean isValidUsername(String username)
}

interface ILandingView { 
}

interface ILandingView.Listener { 
    --
    void onNavigateToLogin()
    void onNavigateToSignup()
}

class LandingFragment{
    -binding: FragmentLandingBinding
    -listener: ILandingView.Listener
}

interface ILoginView { 
}

interface ILoginView.Listener { 
    --
    void onLogin(String username, String password)
}

class LoginFragment{
    -binding: FragmentLoginBinding
    -listener: Listener
}

interface ISignupView { 
}

interface ISignupView.Listener { 
    --
    void onSignup(String username, String email, String password)
    void checkUsernameExists(String username, String email, String password)
}

class SignupFragment{
    -binding: FragmentSignupBinding
    -listener: Listener
    --
    TextWatcher createTextWatcher()
    void validateInput()
    void onSignupResult(boolean isSuccess, String message)
    void onUsernameExistsResult(boolean exists, String message)
    void hideKeyboard(View view)
}

interface IUserProfileView { 
}

interface IUserProfileView.Listener { 
    --
    void onNavigateToMyFriends()
}

class UserProfileFragment{
    -binding: FragmentUserProfileBinding
    -user: User
    -reviewsRecyclerView: RecyclerView
    -reviewAdapter: ReviewAdapterUserProf
    -reviewsList: List<Review>
    -listener: Listener
    --
    void setupRecyclerView(View view)
    void onAttach(Context context)
    void onDetach()
}

ISearchView <|.. SearchFragment
ISearchView +-- ISearchView.Listener
ILandingView <|.. LandingFragment
ILandingView +-- ILandingView.Listener
IUserProfileView <|.. UserProfileFragment
IUserProfileView +-- IUserProfileView.Listener
ILoginView <|.. LoginFragment
AuthFragment <|- LoginFragment
ILoginView +-- ILoginView.Listener
ISignupView <|.. SignupFragment
AuthFragment <|- SignupFragment
ISignupView +-- ISignupView.Listener

@enduml
```