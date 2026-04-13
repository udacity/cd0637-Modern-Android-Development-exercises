# Exercise 02: Content Card - Building Compose Layouts

## Learning Objective
Build complex layouts using Row, Column, Box, and modifiers

## Duration
15-25 minutes

## Overview
In this exercise, you'll build a content card that demonstrates nested layouts and proper use of modifiers. This hands-on practice will help you understand how to combine different layout composables to create complex UI structures.

## What You'll Build
A content card that includes:
- A header section with an icon and title (using Row)
- A content area with multiple text elements (using Column)
- Proper spacing using Spacer and padding modifiers
- Background colors and styling
- Preview function to see your work

## Skills Practiced
- Row and Column layouts
- Box for stacking elements
- Modifier basics (size, padding, background)
- Arrangement and alignment
- Spacer composable
- Combining layouts
- Nested layouts
- Custom composables

## Instructions

### Step 1: Review the Project Structure
Open `MainActivity.kt` and familiarize yourself with the basic structure. Notice the Card composable which provides the container.

### Step 2: Build the ContentCard Composable
The ContentCard has a basic Column layout. Find the TODO comments and add the missing UI elements:

1. **Build the header section** using a Row composable
   - Use `modifier = Modifier.fillMaxWidth().background(Color(0xFF6200EE)).padding(16.dp)`
   - Set `verticalAlignment = Alignment.CenterVertically`
   - Add an Icon with `Icons.Default.Notifications`
   - Add a Spacer with `Modifier.width(12.dp)`
   - Add a Text displaying the `title` parameter with white color

2. **Add the content section** using a Column composable
   - Use `modifier = Modifier.fillMaxWidth().padding(16.dp)`
   - Add a Text displaying the `content` parameter
   - Add a Spacer with `Modifier.height(8.dp)`
   - Add another Text for the `timestamp` parameter with smaller, secondary styling

3. **Test the arrangement and alignment**
   - Experiment with different arrangements in Row and Column
   - Test different padding values
   - Try different background colors

### Step 3: Test Your Implementation
1. Use the preview function `ContentCardPreview()` to see your work
2. Run the app on an emulator or device
3. Verify the header and content sections display correctly

## Expected Result
When complete, you should see a content card with:
- A purple header bar with a notification icon and title
- Content text in the body section
- A timestamp below the content
- Proper spacing between all elements

## Tips
- Use `Modifier.fillMaxWidth()` to make elements span the full width
- The `Spacer` composable is useful for adding fixed spacing
- Combine multiple modifiers using the dot notation (e.g., `Modifier.fillMaxWidth().padding(16.dp)`)
- Use `Arrangement` and `Alignment` to control how children are positioned
- Background colors can be added with the `background()` modifier

## Resources
- [Jetpack Compose Layouts](https://developer.android.com/jetpack/compose/layouts)
- [Layout Basics](https://developer.android.com/jetpack/compose/layouts/basics)
- [Modifiers](https://developer.android.com/jetpack/compose/modifiers)

## Need Help?
If you get stuck:
1. Check the TODO comments for specific guidance
2. Review the preview function to understand the expected parameters
3. Remember that Row arranges children horizontally and Column vertically
4. Make sure you're chaining modifiers correctly

Good luck! 🚀
