# Vision Document

## Introduction

Our restaurant reviewing app allows users to record, rate, and share their dining experiences while discovering new restaurants approved by other users. 
They can connect with their friends and make lists to categorize restaurants, save their favorites, and make wish lists.

## Target Audience

This app is intended for people 13+ years old in the United States with Android devices.
It is is ideal for food enthusiasts, travelers, and individuals looking to explore new dining experiences.

## Value Proposition
Our app addresses user needs that other reviewing platforms do not:
* Ability to connect with friends and follow their activity
  * Can filter to only reviews of people you know
* Ability to make shareable lists of associated restaurants
* Isolates restaurant reviews from other sorts of establishments, unlike platforms like Yelp and Google Reviews

## Main Features
* Rate and review restaurants
* Connect with friends and view their ratings
* Create and share curated restaurant lists
* Search restaurants using filters

## Constraints

* Dependency on robust, accurate restaurant data
* Ideal functionality relies on user location and connecting to Maps
* Maintaining user privacy and data security

## Stakeholder Goals

* For Diners: Find trusted restaurant recommendations, log experiences, and connect with friends.
* For Restaurant Owners: Gain visibility, receive feedback, and attract more patrons.

## UML Use Case Diagram

```plantuml
@startuml

skin rose

' human actors
actor "User" as user

' system actors
actor "GPS" <<system>> as gps
actor "Camera Roll" <<system>> as photos

' list all use cases in package 
package "Restaurant App" {
usecase "Create Account" as createAcc
usecase "Manage Account" as manageAcc
usecase "Check Restaurant Reviews" as checkReview
usecase "Check User Profile" as checkPerson
usecase "Review Restaurant" as review
usecase "Manage Reviews" as manageReview
usecase "Create List" as makeList
usecase "Manage Lists" as manageList
usecase "Assign Restaurant to List" as assignList
usecase "Send Follow Request" as sendFollow
usecase "Accept Follow Request" as acceptFollow
usecase "Respond to Review" as replyReview
}

' list relationships between actors and use cases 
user --> createAcc
user --> manageAcc
user --> checkReview
user --> checkPerson
user --> review
user --> manageReview
user --> makeList
user --> manageList
user --> assignList
user --> sendFollow
user --> acceptFollow
user --> replyReview
makeList --> gps
manageList --> gps
assignList --> gps
createAcc --> gps
checkReview --> gps
review --> photos
manageAcc --> photos
manageReview --> photos
@enduml
```