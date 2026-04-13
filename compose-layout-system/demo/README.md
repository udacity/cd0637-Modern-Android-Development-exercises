# Demo: Compose Layout System

## Project Overview
This project, `Demo-Compose-Layout-System`, is a deep dive into the Jetpack Compose layout system. It demonstrates how to create complex, responsive, and beautifully arranged UIs by combining basic layout building blocks.

## Layout Concepts Demonstrated

### 1. Rows and Columns
- **Rows**: Used for horizontal placement (e.g., Toolbar icons, Action buttons).
- **Columns**: Used for vertical stacking (e.g., Task lists, Dashboard sections, Form layouts).

### 2. Box for Stacking
- Used for layering elements on top of each other.
- Demonstrated in the **Status Banner**, where background decorative icons and text overlays are stacked.

### 3. Modifier Mastery
- **Size and Padding**: Controlling the footprint and internal spacing of elements.
- **Background and Clip**: Applying colors, gradients, and rounded corner shapes.
- **Weight**: Using `Modifier.weight()` to distribute space proportionally in Rows and Columns.

### 4. Arrangement and Alignment
- **Arrangement**: Controlling the distribution of children (e.g., `Arrangement.SpaceBetween`, `Arrangement.spacedBy(8.dp)`).
- **Alignment**: Controlling the position of children within their containers (e.g., `Alignment.CenterVertically`, `Alignment.CenterEnd`).

### 5. Spacers and Helpers
- Using `Spacer` to create flexible or fixed gaps between elements without hardcoding margins everywhere.

## UI Components

1. **Custom Toolbar**: Header section with navigation and profile actions.
2. **Status Banner**: A high-impact visual card with stacked content.
3. **Quick Action Grid**: A perfectly sized 2x2 grid using nested layouts.
4. **Dynamic Task List**: A list that fills the available vertical space.
5. **Vertical Form**: A simple layout for adding new tasks.

## How to Run
1. Open this project in Android Studio.
2. Sync the Gradle files.
3. Run the application on an emulator or a physical device.
4. You will see a functional Dashboard UI exploring all the layout features mentioned.

## Learning Objectives
- Master the use of `Column`, `Row`, and `Box`.
- Understand the power of `Modifier.weight`.
- Learn to nest layouts to build complex grid structures.
- Apply consistent spacing using `Arrangement` and `Spacer`.
