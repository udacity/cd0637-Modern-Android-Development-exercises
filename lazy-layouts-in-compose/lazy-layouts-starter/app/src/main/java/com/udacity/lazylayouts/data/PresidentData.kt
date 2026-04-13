package com.udacity.lazylayouts.data

import java.util.UUID

data class President(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val yearsInOffice: String,
    val party: String,
    val description: String
)

object PresidentData {
    val presidents = listOf(
        President(
            name = "George Washington",
            yearsInOffice = "1789-1797",
            party = "Independent",
            description = "The first U.S. President and commander of the Continental Army during the Revolutionary War."
        ),
        President(
            name = "John Adams",
            yearsInOffice = "1797-1801",
            party = "Federalist",
            description = "A Founding Father who served as the first Vice President and second President."
        ),
        President(
            name = "Thomas Jefferson",
            yearsInOffice = "1801-1809",
            party = "Democratic-Republican",
            description = "Principal author of the Declaration of Independence."
        ),
        President(
            name = "James Madison",
            yearsInOffice = "1809-1817",
            party = "Democratic-Republican",
            description = "The 'Father of the Constitution' and fourth President."
        ),
        President(
            name = "James Monroe",
            yearsInOffice = "1817-1825",
            party = "Democratic-Republican",
            description = "Fifth President, known for the Monroe Doctrine."
        ),
        President(
            name = "Andrew Jackson",
            yearsInOffice = "1829-1837",
            party = "Democratic",
            description = "Army general and seventh President."
        ),
        President(
            name = "Abraham Lincoln",
            yearsInOffice = "1861-1865",
            party = "Republican",
            description = "Led the nation through the Civil War and abolished slavery."
        ),
        President(
            name = "Theodore Roosevelt",
            yearsInOffice = "1901-1909",
            party = "Republican",
            description = "A leader of the Progressive movement and conservationist."
        ),
        President(
            name = "Franklin D. Roosevelt",
            yearsInOffice = "1933-1945",
            party = "Democratic",
            description = "Led the U.S. through the Great Depression and World War II."
        ),
        President(
            name = "John F. Kennedy",
            yearsInOffice = "1961-1963",
            party = "Democratic",
            description = "Youngest elected President; navigated the Cold War."
        ),
        President(
            name = "Ronald Reagan",
            yearsInOffice = "1981-1989",
            party = "Republican",
            description = "Former actor who implemented 'Reaganomics'."
        ),
        President(
            name = "Barack Obama",
            yearsInOffice = "2009-2017",
            party = "Democratic",
            description = "First African American President."
        )
    )
}
