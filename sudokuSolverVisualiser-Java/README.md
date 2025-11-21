# 🎯 Sudoku Solver Visualiser

> **Watch backtracking in action!** An interactive Sudoku solver that visualizes the backtracking algorithm step-by-step, making it easy to understand how constraint satisfaction problems are solved.

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=java&logoColor=white)
![Python](https://img.shields.io/badge/Python-3776AB?style=flat&logo=python&logoColor=white)

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Algorithm](#-algorithm)
- [Getting Started](#-getting-started)
  - [Java Implementation](#java-implementation)
  - [Python Implementation](#python-implementation)
- [Usage](#-usage)
- [How It Works](#-how-it-works)
- [Files](#-files)
- [Screenshots](#-screenshots)

---

## 🌟 Overview

This Sudoku Solver Visualiser demonstrates the **backtracking algorithm** in action. It solves 9x9 Sudoku puzzles while providing real-time visual feedback of the algorithm's decision-making process, including:

- **Placement Visualization** - Watch numbers being placed in cells
- **Backtracking Visualization** - See when the algorithm removes incorrect numbers
- **Step-by-Step Execution** - Understand each decision the algorithm makes

---

## ✨ Features

### Core Features
- 🎯 **Interactive GUI** - User-friendly interface with a 9x9 grid
- ⏱️ **Step-by-Step Animation** - Visualize the solving process in real-time
- 🔍 **Backtracking Visualization** - See how the algorithm explores and backtracks
- 📥 **Load Puzzles** - Load predefined Sudoku puzzles
- 🎲 **Generate Puzzles** - Generate random Sudoku puzzles (in `instantSolver.java`)
- 🧹 **Clear Board** - Reset the board to start fresh

### Algorithm Features
- **Constraint Checking** - Validates rows, columns, and 3x3 subgrids
- **Optimized Data Structures** - Uses HashSets for O(1) lookup operations
- **Thread-Safe GUI Updates** - Smooth visualization without freezing

---

## 🧠 Algorithm

This implementation uses the **Backtracking Algorithm** to solve Sudoku:

1. **Find Empty Cell** - Locate the first empty cell (represented as 0)
2. **Try Numbers** - Attempt to place numbers 1-9 in the empty cell
3. **Check Validity** - Verify if the number is valid in:
   - The current row
   - The current column
   - The current 3x3 subgrid
4. **Recurse** - If valid, recursively solve the rest of the puzzle
5. **Backtrack** - If no valid number works, backtrack to the previous cell

### Time Complexity
- **Worst Case**: O(9^(n)) where n is the number of empty cells
- **Average Case**: Much better due to early constraint checking

### Space Complexity
- O(n) where n is the number of empty cells (recursion stack)

---

## 🚀 Getting Started

### Prerequisites

**For Java:**
- Java Development Kit (JDK) 8 or higher
- Any Java IDE or command-line compiler

**For Python:**
- Python 3.7 or higher
- Required libraries (see `requirements.txt`)

---

## 📦 Installation & Running

### Java Implementation

#### Option 1: Visualiser with Step-by-Step Animation
```bash
# Navigate to the folder
cd sudokuSolverVisualiser

# Compile the visualiser
javac visualiserCode.java

# Run the application
java SudokuSolverGUI
```

#### Option 2: Instant Solver with Puzzle Generator
```bash
# Compile the instant solver
javac instantSolver.java

# Run the application
java SudokuGUI
```

### Python Implementation

```bash
# Install dependencies
pip install -r requirements.txt

# Run the Python visualiser (when implemented)
python sudoku_visualiser.py
```

---

## 🎮 Usage

### Using the Visualiser (`visualiserCode.java`)

1. **Load a Puzzle**
   - Click the **"Load Puzzle"** button to load a predefined puzzle
   - Or manually enter numbers in the grid

2. **Solve**
   - Click the **"Solve"** button to start the visualization
   - Watch as numbers are placed and removed (backtracking) in real-time
   - A success message will appear when solved

3. **Clear**
   - Click the **"Clear"** button to reset the board

### Using the Instant Solver (`instantSolver.java`)

1. **Generate Puzzle**
   - Click **"Generate"** to create a random Sudoku puzzle
   
2. **Solve**
   - Click **"Solve"** to instantly solve the puzzle
   - The solution appears immediately (no animation)

3. **Manual Input**
   - Enter your own puzzle numbers directly in the grid
   - Click **"Solve"** to find the solution

---

## 🔧 How It Works

### Data Structures

The visualiser uses efficient data structures for constraint checking:

```java
HashSet<Integer>[] rows      // Track numbers in each row
HashSet<Integer>[] cols      // Track numbers in each column
HashSet<Integer>[] subgrids  // Track numbers in each 3x3 subgrid
```

### Visualization Technique

- **Threading**: Solving runs in a separate thread to prevent GUI freezing
- **Delays**: 50ms delays between steps for clear visualization
- **GUI Updates**: Uses `SwingUtilities.invokeLater()` for thread-safe GUI updates

### Solving Process

```
1. Find empty cell (0)
2. Try placing 1-9
3. Check constraints (row, column, subgrid)
4. If valid:
   - Place number
   - Update GUI
   - Recursively solve rest
5. If solution found: return true
6. If no valid number: backtrack (remove number, try next)
```

---

## 📁 Files

| File | Description |
|------|-------------|
| `visualiserCode.java` | Main visualiser with step-by-step animation and backtracking visualization |
| `instantSolver.java` | Alternative solver with puzzle generator (solves instantly without animation) |
| `samplePuzzle.png` | Sample Sudoku puzzle image |
| `samplePuzzleSolved(1).png` | Solved puzzle screenshot 1 |
| `samplePuzzleSolved(2).png` | Solved puzzle screenshot 2 |
| `sudokuSolverReport.pdf` | Detailed report/documentation |
| `requirements.txt` | Python dependencies (for future Python implementation) |

---

## 🖼️ Screenshots

Sample puzzles and solutions are available in the folder:
- `samplePuzzle.png` - Example puzzle
- `samplePuzzleSolved(1).png` - Solution example 1
- `samplePuzzleSolved(2).png` - Solution example 2

---

## 💡 Key Concepts Demonstrated

### 1. **Backtracking Algorithm**
   - Systematic exploration of solution space
   - Undoing decisions when they don't lead to a solution

### 2. **Constraint Satisfaction**
   - Row constraints
   - Column constraints
   - Subgrid constraints

### 3. **Optimization Techniques**
   - Early constraint checking
   - Efficient data structures (HashSets)
   - Pruning invalid branches

### 4. **GUI Programming**
   - Event-driven programming
   - Threading for responsive UI
   - Real-time updates

---

## 🔬 Educational Value

This visualiser is perfect for:
- 👨‍🎓 **Students** learning backtracking algorithms
- 🔧 **Developers** preparing for technical interviews
- 📚 **Educators** teaching constraint satisfaction problems
- 🧠 **Anyone** curious about how Sudoku solving works

---

## 🤝 Contributing

Feel free to:
- Add more puzzle presets
- Improve the visualization speed controls
- Add Python implementation
- Enhance the GUI design
- Add puzzle difficulty levels

---

## 📝 Notes

- The animation delay is set to 50ms. You can adjust it in the `delay()` method for faster/slower visualization
- The visualiser uses HashSet lookups for O(1) constraint checking, making it more efficient than naive implementations
- Both implementations solve valid Sudoku puzzles; the visualiser provides educational value through animation

---

## 🐛 Troubleshooting

**Problem**: GUI freezes when solving
- **Solution**: Ensure the solving runs in a separate thread (already implemented)

**Problem**: Numbers don't appear during solving
- **Solution**: Check that `SwingUtilities.invokeLater()` is used for GUI updates

**Problem**: Puzzle not solving
- **Solution**: Verify the input puzzle is valid (no duplicate numbers in rows/columns/subgrids)

---

<div align="center">

**Made with ❤️ for learning algorithms**

*Understanding backtracking, one cell at a time* 🎯

[⬆ Back to Main Repository](../README.md)

</div>

