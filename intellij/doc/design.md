# Sequence Diagram

```plantuml
@startuml
hide footbox
skin rose

actor User as user
participant " : TextUI" as ui
participant " : Controller" as controller
participant " : RestaurantLibrary" as lib
participant " : Restaurant" as restaurant

ui -> user : Display search prompt
user -> ui : Enter search term
user -> ui : Indicate desired sorting and filters
user -> ui : Click search 

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
    -rating: number
    -review_list: ArrayList<String>
    -description: String
    -cuisines: Array<String>
    --
    getRestaurantInfo(): RestaurantInfo
}

class Location{
    -address: String
    -city: String
    -state: String
    -country: String
    -postalCode: String
}

class Review{
    -reviewId: String
    -rating: float
    -reviewInfo: ReviewInfo
    -reviewText: String
    -username: String
    -restaurantId: String
    -date: DateTime
}

class User{
    -userName: String
    -joinedDateTime: DateTime
}

class RestaurantLibrary{
    -allRestaurants: ArrayList<Restaurant>
}

class Navigator{
    -currentUser: User
    --
    search(criteria: String): List<Restaurant>
    postReview(user: User, restaurant: Restaurant, rating: float, reviewText: string): Review
}

' associations
Navigator "1" -- "1" RestaurantLibrary : Accesses\t
RestaurantLibrary "1" - "1..*" Restaurant : \tIs-information-expert-of\t\t
Restaurant "1" - "1" Location : \tContains\t\t
Review "*" -- "1" Restaurant : \tIs-part-of\t\t
User "1" - "*" Review : \tCreates\t\t
@enduml
```
