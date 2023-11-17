# Sequence Diagrams

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

Model and Controller Package:

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
    toString(): String
    getRating(): float
    getName(): String
    getRestaurantId(): String
    getReviewList(): ArrayList<String>
    setDistToUser(u: User): void
    computeRating(revLib: ReviewsLibrary): void
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
    addReviewToRest(restaurantId: String, reviewId: String): void
    search(term: String, filters: Set<IFilter>, sort: String, curUser: User): ArrayList<Restaurant>
}

class ReviewsLibrary{
    -data: HashMap<String, Review>
    --
    addReview(curUser: User, restaurantId: String, rating: float, reviewText: String): String
    displayReviews(reviews : ArrayList<String>): String
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
PriceFilter "0..1" -- "1" RestaurantLibrary: Helps-filter
LocFilter "0..1" -- "1" RestaurantLibrary
RestaurantLibrary "1" - "1..*" Restaurant : \tIs-information-expert-of\t\t
Review "*" -down- "1" ReviewsLibrary: \tIs managed by\t\t
Restaurant "1" -right- "*" Review: Can have\t\t
User "1" -down- "*" Review : Creates\t\t
Location "1" - "1" Restaurant : Is an attribute of\t
Location "1" - "1" User: \tIs an attribute of\t
RestaurantLibrary "1" -down- "1" ControllerActivity: Provides information to\t
ReviewsLibrary "1" -down- "1" ControllerActivity: \tProvides information to\t
ControllerActivity "1" -down- "1" view: \tCommunicates with user using

@enduml
```


View Package:
```plantuml
@startuml
skin rose
'skinparam classAttributeIconSize 0

interface IAddRestaurantView { 
    --
    void addRestaurant(String name, String address, String city, String state, String country, String postalCode, String lat, String lon) in Listener Interface
}

interface IAddReviewView { 
    --
    void onAddReview(float rating, String comment, String id, int priceSymbol) in Listener Interface
}

interface IHomeView { 
    --
    void onNavigateToSearch() in Listener Interface
}

interface IMainView { 
    --
    View getRootView()
    void displayFragment(Fragment fragment, boolean reversible, String name, int popCount)
    void displaySearchResults(ArrayList<Restaurant> searchResults)
    
}

interface IRestaurantView { 
    --
    void onNavigateToPostReview(String restaurantId) in Listener Interface
}

interface ISearchView { 
    --
    void onPerformSearch(String searchTerm, String priceFilter, String distanceFilter, String sortOption) in Listener Interface
    void onNavigateToRestaurant(Restaurant restaurant, boolean reversible, int popCount) in Listener Interface
    void onNavigateToAddRestaurant() in Listener Interface
    void updateSearchResults(List<Restaurant> searchResults)
    void showNoResultsMessage(boolean show)
    void showNoResultsMessage(boolean show)
}

class AddRestaurantFragment{
    -binding: FragmentAddRestaurantBinding 
    -listener: Listener
}

class AddReviewFragment{
    -binding: FragmentAddReviewBinding
    -listener: Listener
    -restId: String
    -ratingBar: RatingBar
    -commentEditText: EditText
    -priceSpinner: Spinner
    -addReviewButton: Button
}

class HomeFragment{
    -binding: FragmentHomeBinding 
    -listener: Listener
}

class RestaurantFragment{
    -binding: FragmentRestaurantBinding 
    -listener: Listener
    -restaurant: Resstaurant
    -reviewsRecyclerView: RecyclerView
    -reviewAdapter: ReviewAdapter
    -reviewsList: List<Review>
}

class SearchFragment{
    -binding: FragmentSearchBinding 
    -listener: Listener
    -rootView: View
    -searchEditText: EditText
    -distanceFilterEditText: EditText
    -searchResultsRecyclerView: RecyclerView
    -priceFilterSpinner: Spinner
    -sortRadioGroup: RadioGroup
    -searchButton: Button
    -restaurantAdapter: RestaurantAdapter
    --
    void onNavigateToRestaurant(Restaurant restaurant)
    void onNavigateToAddRestaurant()
}

class MainView{
    -binding: MainBinding
    -fmanager: FragmentManager
}

package ModelAndController {

}

ISearchView <|.. SearchFragment
IRestaurantView <|.. RestaurantFragment
IHomeView <|.. HomeFragment
IAddRestaurantView <|.. AddRestaurantFragment
IAddReviewView <|.. AddReviewFragment
IMainView <|.. MainView

SearchFragment "1" -down- "1" MainView : \tIs displayed by\t\t
RestaurantFragment "1" -down- "1" MainView : \tIs displayed by\t\t
HomeFragment "1" -down- "1" MainView : \tIs displayed by\t\t
AddRestaurantFragment "1" -down- "1" MainView : \tIs displayed by\t\t
AddReviewFragment "1" -down- "1" MainView : \tIs displayed by\t\t
MainView "1" -down- "1" ModelAndController : \tReceives commands from\t\t

@enduml
```