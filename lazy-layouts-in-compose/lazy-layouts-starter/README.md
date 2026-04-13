# Exercise 08: Lazy Layouts in Compose

## Learning Objective
Create efficient scrolling lists using LazyColumn and LazyGrid

## Duration
20-25 minutes

## Overview
In this exercise, you will build a "Presidents List" application using Jetpack Compose. You will display a list of US Presidents, but instead of a simple flat list, you will group them by their Political Party using `stickyHeader`. This ensures that as you scroll, the party name "sticks" to the top until the next group appears.

## What You'll Build
- A scrollable list of Presidents using `LazyColumn`
- Sticky Headers to group Presidents by Party
- Efficient list rendering with keys

## Skills Practiced
- `LazyColumn`
- `stickyHeader` inside `LazyColumn`
- Grouping data (`groupBy`)
- `items` with keys for performance

## Instructions

### Step 1: Review Data Source
Open `PresidentData.kt`. You'll see a list of `President` objects, each with a name, party, years in office, and description.

### Step 2: Design the List Item
In `MainActivity.kt`:
1.  Locate `PresidentListItem`.
2.  Use a `Card` or `Column` to display the `president.name` (bold/large), `president.yearsInOffice`, and `president.description`.

### Step 3: Implement LazyColumn
1.  Inside `PresidentsApp`, replace the placeholder with a `LazyColumn`.
2.  Pass a `contentPadding` (e.g., `16.dp`) to give the list some breathing room.

### Step 4: Add Sticky Headers
1.  Group the presidents list by party: `val grouped = presidents.groupBy { it.party }`
2.  Iterate through the `grouped` map inside your `LazyColumn`.
3.  Use `stickyHeader { }` to display a Header for the party name. (Hint: Create a simple `Text` composable with a background color).
4.  Use `items(items = ...)` to display the list of presidents for that party.

### Step 5: Optimize
1.  Add the `key` parameter to your `items` call: `key = { it.id }`. This helps Compose uniquely identify items for animations and updates.

## Expected Result
- A list of presidents sectioned by their party.
- Scrolling down separates "Independent", "Federalist", "Democratic-Republican", etc.
- The Party header sticks to the top of the screen while you scroll through its members.
