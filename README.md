# csc560_finalproject
Urban Traffic Optimization and Simulation using Graph Algorithms

## Overview
This project is a traffic simulation system implemented in Java that computes shortest travel paths using Dijkstra's algorithm. The system incorporates real-world constraints such as road closures, toll avoidance, emergency routing, and time-based congestion. An interactive JavaFX user interface is included to visualize the graph and dynamically update routes.


## Features
### Core Algorithm
- Dijkstra's shortest path algorithm
- Edge weights based on:
  - Distance
  - Congestion factor (dynamic)
  - Toll Cost

### Traffic Simulation
- Time of day congestion (i.e., rush hour)
- Road closures
- Emergency mode (ignores closures)
- Optional toll avoidance

### Interactive UI (JavaFX)
- Visual graph representation (nodes and edges)
- Color-coded edges based on traffic conditions
- User controls:
  - Toggle avoid tolls
  - Toggle emergency mode
  - Toggle road closure (A-B)
  - Adjust time of day
  - Recalculate shortest paths
 

## Project Structure
```
csc560_finalproject/
├── src/
│   ├── algorithm/
│   │   ├── Dijkstra.java
│   │   ├── TrafficManager.java
│   ├── graph/
│   │   ├── Graph.java
│   │   ├── Node.java
│   │   ├── Edge.java
│   ├── Main.java
│   ├── UIApp.java
├── javafx/
│   └── lib/
├── out/
├── .vscode/
│   ├── launch.json
│   ├── settings.json
```

## Requirements
- Java JDK 17
- JavaFX SDK 17 (included in this repository under `javafx/lib`


## How to Run Locally
1. Clone the repository
``` bash
git clone https://github.com/mclark-99/csc560_finalproject.git
```
``` bash
cd C:\Users\YOUR_USERNAME\csc560_finalproject
```

2. Compile
``` bash
javac --module-path "javafx/lib" --add-modules javafx.controls,javafx.fxml -d out src/algorithm/*.java src/graph/*.java src/Main.java src/UIApp.java
```
3. Run
``` bash
java --module-path "javafx/lib" --add-modules javafx.controls,javafx.fxml -cp out UIApp
```

## How to Use the Application
- Ajust the time-of-day slider to simulate traffic congestion
- Toggle "Avoid Tolls" to exclude toll roads from routing
- Toggle "Emergency Mode" to ignore road closures
- Toffle "Close A-B" to simulate a blocked road
- Click "recalculate" to update the shortest path results

## Example Output
```
From A:
B : 10.0
D : 16.0
C : 20.0
A : 0.0
```

## Author
Maiti Clark
West Chester University - Computer Science
