# Sequence Diagrams

## Check Out Restaurant

```plantuml
@startuml
hide footbox
skin rose

actor User as user
participant " : SearchFragment" as searchFrag
participant " : MainFragment" as homeFrag
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
user -> ui : Select desired restaurant
controller -> revLib : getReviews(selectedResult)
controller -> ui : onNavigateToRestaurant(selectedResult)
ui -> user : Display selected restaurant info
@enduml
```

some fragment is calling on onDoThis. That points to controller. Controller points to mainView using displayFragment

## Review Restaurant
Picks up directly after Check Out Restaurant:

```plantuml
@startuml
hide footbox
skin rose

actor User as user
participant "ui : AppUI" as ui
participant " : Controller" as controller
participant "revLib : ReviewsLibrary" as revLib
participant "lib : RestaurantLibrary" as lib
participant " : Restaurant" as restaurant

user -> ui : Select Add Review
ui -> controller : onNavigateToPostReview
ui -> user : Display add review form
user -> ui : Enter review information
ui -> controller :onAddReview (reviewInfo)
controller -> revLib : newReviewId = addReview(curUser, restaurantId, rating, reviewText)
controller -> lib : addReviewToRest(restaurantId, newReviewId)
controller -> restaurant: computeRating(revLib)

@enduml
```

## Add Restaurant
Picks up directly after "Display restaurants matching criteria":

```plantuml
@startuml
hide footbox
skin rose

actor User as user
participant "ui : AppUI" as ui
participant " : Controller" as controller
participant "lib : RestaurantLibrary" as lib

user -> ui : Select Add Restaurant
controller -> ui : displayAddForm()
ui -> user : Asks for restaurant name
user -> ui : Enter name
ui -> user : Asks for location info
user -> ui : Enter location indo
controller -> lib : addRestaurant(name,address,city,state,lat,lon);

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
