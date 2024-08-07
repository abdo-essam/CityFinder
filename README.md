# City Finder

City Finder is an Android application that allows users to search for cities and view their locations on Google Maps. This project is designed to evaluate problem-solving skills, UX judgment, and code quality.

## Features

- Load a list of cities from a JSON file.
- Filter results by a given prefix string.
- Display cities in a scrollable list, in alphabetical order.
- Show city and country code as the title.
- Show coordinates as a subtitle.
- Show the location of the city on Google Maps when tapped.

## Data Structure and Search Algorithm
### Trie Data Structure

The Trie (prefix tree) data structure is used to optimize the search functionality. This allows for efficient prefix-based searches, making the application responsive even with a large dataset of cities.

Why Trie?
- Efficient Searches: The search time complexity is O(m), where m is the length of the search prefix.
- Memory Usage: The Trie is space-efficient as common prefixes are stored once.
