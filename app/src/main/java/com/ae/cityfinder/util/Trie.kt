package com.ae.cityfinder.util

import com.ae.cityfinder.model.City

class Trie {
    /**
     * Internal class representing a node in the Trie structure.
     */
    private class TrieNode {
        // Maps each character to its corresponding child node
        val children: MutableMap<Char, TrieNode> = mutableMapOf()
        // Flag to indicate if the node represents the end of a word
        var isEndOfWord: Boolean = false
        // Reference to the City object associated with the word
        var city: City? = null
    }

    // The root node of the Trie
    private val root = TrieNode()

    /**
     * Inserts a word and its corresponding city information into the Trie.
     *
     * @param word The word to be inserted.
     * @param city The City object containing information about the associated city.
     */
    fun insert(word: String, city: City) {

        var node = root
        // Insert each character of the word into the Trie

        for (char in word) {
            // Uses getOrPut to retrieve an existing child node for the character or create a new one.
            node = node.children.getOrPut(char) { TrieNode() }
        }
        // Marks the last node as the end of a word and sets the associated city
        node.isEndOfWord = true
        // Set the city associated with the last node
        node.city = city
    }

    /**
     * Searches for all cities associated with a given prefix.
     *
     * @param prefix The prefix to search for.
     * @return A list of City objects associated with the prefix, or an empty list if no matches are found.
     */
    fun search(prefix: String): List<City> {
        var node = root
        // Traverse the Trie to find the last node corresponding to the prefix
        for (char in prefix) {
            // If the character is not found in the Trie, return an empty list
            node = node.children[char] ?: return emptyList()
        }
        // Collect all cities associated with the last node and its descendants
        return collectAllWords(node)
    }


    /**
     * Helper function to collect all cities associated with a node and its descendants.
     *
     * @param node The Trie node to start the search from.
     * @return A list of City objects associated with the node and its descendants.
     */
    private fun collectAllWords(node: TrieNode): List<City> {
        val cities = mutableListOf<City>()
        // If the node is a word, add it to the list
        if (node.isEndOfWord) {
            // Add the city associated with the word
            node.city?.let { cities.add(it) }
        }
        // Recursively collect cities from child nodes
        for (child in node.children.values) {
            cities.addAll(collectAllWords(child))
        }
        return cities
    }

}