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
user -> addRestFrag : Enter location info
addRestFrag -> controller : addRestaurant(name, address, city, state, country, postalCode, lat, lon){
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

package view{

}

' associations
IFilter <|.. PriceFilter
IFilter <|.. LocFilter
PriceFilter "0..1" -- "1" RestaurantLibrary: Helps-filter
LocFilter "0..1" -- "1" RestaurantLibrary
RestaurantLibrary "1" - "1..*" Restaurant : \tIs-information-expert-of\t\t
ReviewsLibrary "1" -- "*" Review : \tManages-all\t\t
Review "*" -- "1" Restaurant : \tIs-part-of\t\t
User "1" -left- "*" Review : \tIs created by\t\t
Restaurant "1" - "*" Location : \tHas a \t
User "1" -down- "*" Location : \tHas a \t
@enduml
```
