# Test Report

## System Test #1

The goal of the first test run is to evaluate the functionality of the price filter and location sorting of search results.
It should show how these components respond when you give them valid and invalid input. When given invalid input, the program
should alert the user and prompt them to try again. Once valid data is entered, the user should be able to select a restaurant 
using its labeled number. The desired outcome is that the user will be able to view a restaurant profile (complete with restaurant
info and reviews) based on their search.

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

Name: WIN Bubble Tea and Sushi

Rating: 4.0

Price: $

Address: 99 White Bridge Pike

City: Nashville

State: TN

Distance: 217 miles

-------------------------------------

Restaurant 2

Name: Sushi Train

Rating: 3.5

Price: $

Address: 94 White Bridge Rd

City: Nashville

State: TN

Distance: 217 miles

-------------------------------------


What would you like to do next? (Enter 1 to search again search, Enter 2 to select restaurant)

2

Please enter the number of the restaurant you would like to select.

1

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
and 2 of them have a price range of $ (the not $ one was filtered out). And the results are displayed
in descending closeness (143 before 217 miles), showing the proximity sorting.


## System Test #2

The goal of the second test run is to evaluate the functionality of the location filter and rating sorting of search results.


## System Test #3

The goal of this test run is to make a search with no filters and default sorting. The user should be able to select a restaurant and
leave a review, and the review will be visible when they go back to the restaurant profile.

## System Test #4

The goal of this test run is to evaluate what happens when you enter a term that does not match any of the restaurant's names. 
The desired outcome is that a message will appear indicating no results, and the user will be prompted to search again.

