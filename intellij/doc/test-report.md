# Test Report

## System Test #1

The goal of the first test run is to evaluate the functionality of the price filter and location sorting of search results.
It should show how these components respond when you give them valid and invalid input. When given invalid input, the program
should alert the user and prompt them to try again. Once valid data is entered, the user should be able to select a restaurant 
using its labeled number. The desired outcome is that the user will be able to view matching restaurants based on their search,
and select a restaurant in order to see its details and reviews.

**TRANSCRIPT :**

Welcome to Lunch Box!

Please enter a search term to find a restaurant by name.

sushi

Would you like to filter by price? (y,n)

yass

Invalid input. Please try again.

Would you like to filter by price? (y,n)

y

Please enter a valid price category: $, $$, or $$$

super cheap

Invalid input. Please try again.

Would you like to filter by price? (y,n)

y

Please enter a valid price category: $, $$, or $$$

$

Would you like to filter by distance? (y,n)

nooo

Invalid input. Please try again.

Would you like to filter by distance? (y,n)

n

Would you like to sort your results? (y,n)

yasssss

Invalid input. Please try again.

Would you like to sort your results? (y,n)

y

How would you like to sort? (proximity, rating)

whatever

Invalid input. Please try again.

Would you like to sort your results? (y,n)

y

How would you like to sort? (proximity, rating)

proximity

=====================================

RESTAURANT LIST           

=====================================

Restaurant 1

Name: Sushi Train

Rating: 3.5

Price: $

Address: 94 White Bridge Rd

City: Nashville

State: TN

Distance: 143 miles

-------------------------------------

Restaurant 2

Name: WIN Bubble Tea and Sushi

Rating: 4.0

Price: $

Address: 99 White Bridge Pike

City: Nashville

State: TN

Distance: 217 miles

-------------------------------------


What would you like to do next? (Enter 1 to search again search, Enter 2 to select restaurant)

2

Please enter the number of the restaurant you would like to select.

2

Name: WIN Bubble Tea and Sushi

Rating: 4.0

Price: $

Address: 99 White Bridge Pike

City: Nashville

State: TN

Distance: 217 miles


=====================================

REVIEWS           

=====================================

Username: melissa99

Rating: 4.5

Review: Single position risk economic likely result pattern. Need cultural property. Spring discussion yet left. Create choose spring party leave magazine shoulder. There goal student power ability security.

-------------------------------------


Would you like to post a review? (y,n)

n

=====================================

RESTAURANT LIST           

=====================================

Restaurant 1

Name: Sushi Train

Rating: 3.5

Price: $

Address: 94 White Bridge Rd

City: Nashville

State: TN

Distance: 143 miles

-------------------------------------

Restaurant 2

Name: WIN Bubble Tea and Sushi

Rating: 4.0

Price: $

Address: 99 White Bridge Pike

City: Nashville

State: TN

Distance: 217 miles

-------------------------------------

What would you like to do next? (Enter 1 to search again search, Enter 2 to select restaurant)

1

Please enter a search term to find a restaurant by name.

**END OF TRANSCRIPT**

These results make sense because there are 3 restaurants with sushi in the name loaded into the data,
and 2 of them have a price range of $ (the $$$ one was filtered out). And the results are displayed
in descending closeness (143 before 217 miles), showing the proximity sorting.


## System Test #2

The goal of the second test run is to evaluate the functionality of the location filter and rating sorting of search results.
The desired outcome is that the user will be able to view restaurants matching their search parameters.

**TRANSCRIPT :**

Welcome to Lunch Box!

Please enter a search term to find a restaurant by name.

cafe

Would you like to filter by price? (y,n)

n

Would you like to filter by distance? (y,n)

y

Please enter the max distance (in miles) the restaurant can be from you.

50

Would you like to sort your results? (y,n)

y

How would you like to sort? (proximity, rating)

rating

=====================================

RESTAURANT LIST           

=====================================

Restaurant 1

Name: The Clubhouse Cafe

Rating: 3.8

Price: $

Address: 9420 Gravois Rd

City: Affton

State: MO

Distance: 22 miles

-------------------------------------

Restaurant 2

Name: Dingho Cafe

Rating: 1.7

Price: $$

Address: 7900 Watson Rd

City: Saint Louis

State: MO

Distance: 23 miles

-------------------------------------

What would you like to do next? (Enter 1 to search again search, Enter 2 to select restaurant)

1

Please enter a search term to find a restaurant by name.

**END OF TRANSCRIPT**

These results make sense because all results contain the word cafe, are within 50 miles, and are ordered by rating.

## System Test #3

The goal of the third test run is to make a search with no filters and with default sorting. The user should be able to select a restaurant and
leave a review, and the review will be visible when they go back to the restaurant profile.

**TRANSCRIPT :**

Welcome to Lunch Box!

Please enter a search term to find a restaurant by name.

bake

Would you like to filter by price? (y,n)

n

Would you like to filter by distance? (y,n)

n

Would you like to sort your results? (y,n)

n

=====================================

RESTAURANT LIST           

=====================================

Restaurant 1

Name: Moreno Bakery

Rating: 3.3

Price: $$

Address: 737 W Brandon Blvd

City: Brandon

State: FL

Distance: 532 miles

-------------------------------------

Restaurant 2

Name: Britt’s Bakehouse

Rating: 3.0

Price: $

Address: 137 W Jefferson Ave

City: Kirkwood

State: MO

Distance: 28 miles

-------------------------------------

What would you like to do next? (Enter 1 to search again search, Enter 2 to select restaurant)

2

Please enter the number of the restaurant you would like to select.

2

Name: Britt’s Bakehouse

Rating: 3.0

Price: $

Address: 137 W Jefferson Ave

City: Kirkwood

State: MO

Distance: 28 miles

=====================================

REVIEWS           

=====================================


Username: ymcmahon

Rating: 1.5

Review: White arrive little off available. Would easy agency full garden. Low stand free sister than which finally.

-------------------------------------

Username: vcarter

Rating: 3.5

Review: Every example prevent still himself light page. Require agreement apply business factor road bit. Hear girl another southern smile one picture fine. School between us forget. Lawyer behind type cold.

-------------------------------------

Username: donaldcampbell

Rating: 3.0

Review: Particular perform choose story suffer full popular why. Film by whatever realize food. Boy live tree field model than teacher. Moment might eye wait economy. Style if truth enter gas sign past.

-------------------------------------

Username: cheryl70

Rating: 4.0

Review: Pattern line seven involve probably go suggest. Fish parent reveal name film chair. Response step seem shake service. Professor especially want position. Single rather group factor happen.

-------------------------------------

Would you like to post a review? (y,n)

y

Please enter a rating for this restaurant (1-5 only integers).

0

Invalid rating. Please try again.

1

Please enter a review for this restaurant.

Terrible!

=====================================

RESTAURANT LIST           

=====================================

Restaurant 1

Name: Moreno Bakery

Rating: 3.3

Price: $$

Address: 737 W Brandon Blvd

City: Brandon

State: FL

Distance: 532 miles

-------------------------------------

Restaurant 2

Name: Britt’s Bakehouse

Rating: 2.6

Price: $

Address: 137 W Jefferson Ave

City: Kirkwood

State: MO

Distance: 28 miles

-------------------------------------

What would you like to do next? (Enter 1 to search again search, Enter 2 to select restaurant)

2

Please enter the number of the restaurant you would like to select.

2

Name: Britt’s Bakehouse

Rating: 2.6

Price: $

Address: 137 W Jefferson Ave

City: Kirkwood

State: MO

Distance: 28 miles

=====================================

REVIEWS           

=====================================

Username: ymcmahon

Rating: 1.5

Review: White arrive little off available. Would easy agency full garden. Low stand free sister than which finally.

-------------------------------------

Username: vcarter

Rating: 3.5

Review: Every example prevent still himself light page. Require agreement apply business factor road bit. Hear girl another southern smile one picture fine. School between us forget. Lawyer behind type cold.

-------------------------------------

Username: donaldcampbell

Rating: 3.0

Review: Particular perform choose story suffer full popular why. Film by whatever realize food. Boy live tree field model than teacher. Moment might eye wait economy. Style if truth enter gas sign past.

-------------------------------------

Username: cheryl70

Rating: 4.0

Review: Pattern line seven involve probably go suggest. Fish parent reveal name film chair. Response step seem shake service. Professor especially want position. Single rather group factor happen.

-------------------------------------

Username: Me

Rating: 1.0

Review: Terrible!

-------------------------------------


Would you like to post a review? (y,n)

n

=====================================

RESTAURANT LIST           

=====================================

Restaurant 1

Name: Moreno Bakery

Rating: 3.3

Price: $$

Address: 737 W Brandon Blvd

City: Brandon

State: FL

Distance: 532 miles

-------------------------------------

Restaurant 2

Name: Britt’s Bakehouse

Rating: 2.6

Price: $

Address: 137 W Jefferson Ave

City: Kirkwood

State: MO

Distance: 28 miles

-------------------------------------

What would you like to do next? (Enter 1 to search again search, Enter 2 to select restaurant)

**END OF TRANSCRIPT**

This output makes sense because no sorting algorithm is indicated, so the results are given the default sorting (by rating).
When a user leaves a review, their rating influences the overall rating of the restaurant and is visible when they go back to the restaurant profile.

## System Test #4

The goal of this test run is to evaluate what happens when you enter a term that does not match any of the restaurant's names. 
The desired outcome is that a message will appear indicating no results, and the user will be prompted to search again.

**TRANSCRIPT :**

Welcome to Lunch Box!

Please enter a search term to find a restaurant by name.

yum

Would you like to filter by price? (y,n)

n

Would you like to filter by distance? (y,n)

y

Please enter the max distance (in miles) the restaurant can be from you.

50

Would you like to sort your results? (y,n)

n

No restaurants match the given criteria.

Please enter a search term to find a restaurant by name.

**END OF TRANSCRIPT**

These results are expected because no restaurants match the given parameters. 
The program loops so the user is able to try again with a different search.