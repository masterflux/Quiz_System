# Quiz System  

## Overview  
This project is part of the **CSC8404 Advanced Programming in Java** coursework. It implements a **Quiz System** designed to assess students by providing structured quizzes, tracking performance, and generating insightful statistics.  

The system follows **object-oriented design principles**, including interface-based hierarchies, late binding, immutability, collections, and unit testing. The goal is to ensure a modular, extensible, and maintainable approach to quiz management.  

## Features  
✅ **Quiz Management** – Generates both **regular** and **revision** quizzes.  
✅ **Student Tracking** – Monitors quiz attempts, scores, and overall performance.  
✅ **Flexible Question Types** – Supports **multiple-choice** and **free-response** questions.  
✅ **Scoring System** – Automatically determines **pass/fail** results based on quiz scores.  
✅ **Performance Insights** – Generates statistics to help analyze student progress.  
✅ **Unit Testing** – Ensures system reliability using **JUnit**.  

## System Design  

### 🔹 Core Components  

#### **Question Interface & Implementations**  
- `Question` – Base interface for quiz questions.  
- `FreeResponseQuestion` – Represents open-ended questions.  
- `MultipleChoiceQuestion` – Handles multiple-choice questions.
- `QuestionFactory` – Differentiate between MCQ and Free Response Question.
- `QuestionPool` - Contains random pre-defined pool of Questions.

#### **Quiz System**  
- `QuizManager` – Handles quiz creation logic, processes answers, calculates scores, tracks progress and handles quizes (Regular and Revision Quiz). 

#### **Student & Statistics**  
- `Student` – Maintains student details and quiz history.  
- `StudentStatistics` – Tracks and analyzes student performance metrics.  

## Getting Started  
To run the project, ensure you have **Java 11+** installed. Clone the repository and build the project using your preferred IDE or via the command line.  

```sh
git clone https://github.com/your-repo/quiz-system.git
cd quiz-system
javac Main.java
java Main
