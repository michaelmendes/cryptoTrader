# cryptoTrader
2212B Group Project 

## Project Summary
For this project our group specified, designed, and implemented a system that supports 
a) defining trading brokers, 
b) allowing each trading broker to declare interest for a list of cryptocoins they would like to obtain prices for,
c) attaching a trading strategy to each trading broker, and
d) displaying on the UI the trading log and trading activity.

## Design 
1. We wanted to use design patterns and architectural styles that allowed for high cohesion and low coupling.
2. This project combines a number of design patterns that were each chosen for their particular usefulness and applicability to the task at hand. The design patterns used were the Façade, Strategy, Factory Method, Proxy, Singleton, and Observer design patterns.  
  a) The Façade design pattern was used for coordinating activities between the UI and the backend services (i.e. fetch data, notify, display). 
  b) The Strategy design pattern was used to determine which type of analysis would be performed. 
  c) The Factory Method was used to create strategy instances as required by the type of analysis selected by the user. 
  d) The Proxy design pattern was used for the login service. 
  e) The Singleton design pattern was used for creating and accessing a configuration object that holds all user selections. 
  f) The Observer design pattern was used to associate a result object with viewers which are notified.

## Run Locally 
1. Run this command to download code: git clone https://github.com/michaelmendes/cryptoTrader.git
2. Run program from the MainUI class.
