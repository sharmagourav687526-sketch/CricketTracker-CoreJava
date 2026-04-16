# 🏏 Live Cricket Score Tracker (Core Java)
> A professional, terminal-based live dashboard built with Pure Core Java on Kali Linux.

[![Java Version](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Build](https://img.shields.io/badge/Build-Maven-red.svg)](https://maven.apache.org/)
[![OS](https://img.shields.io/badge/OS-Kali--Linux-blue.svg)](https://www.kali.org/)

## 📖 Project Overview
This project is a high-performance terminal utility that tracks live cricket scores globally. It was built to demonstrate mastery of **Asynchronous Programming**, **Stream API**, and **Network I/O** without the use of high-level frameworks like Spring Boot.

## 🛠️ Technical Architecture
The application follows a modular architecture to handle real-time data flow:

* **Multithreading**: Uses `ScheduledExecutorService` to run a background refresh loop every 30 seconds without blocking user input.
* **Data Filtering**: Implements the **Java Stream API** to filter live matches from completed ones using string predicate logic.
* **Networking**: Utilizes Java 11+ `HttpClient` for asynchronous API requests.
* **Persistence**: A custom `FileManager` handles match history archiving with a `HashSet` to ensure zero data duplication.



## 🚀 Features
- [x] **Live Match Discovery**: Automatically fetches and lists active matches.
- [x] **Smart Filtering**: Distinguishes between "Live", "Drawn", and "Finished" games.
- [x] **Dedicated Tracking**: Select one match to follow in a clean, focused dashboard.
- [x] **ANSI UI**: Color-coded terminal output (Blue/Green/Yellow) for better readability.
- [x] **Persistent History**: Automatically archives results to `match_history.txt`.

## 📦 Installation & Usage

### Prerequisites
* Java 21 (LTS)
* Maven 3.6+

### Setup
1. **Clone the Repo:**
   ```bash
   git clone [https://github.com/sharmagourav687526-sketch/CricketTracker-CoreJava.git](https://github.com/sharmagourav687526-sketch/CricketTracker-CoreJava.git)
   cd CricketTracker-CoreJava