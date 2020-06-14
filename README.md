<h1 align="center">Welcome to the Coronavirus tracker 👋</h1>
<p>
  <img alt="Version" src="https://img.shields.io/badge/version-1.0.0-blue.svg?cacheSeconds=2592000" />
  <a href="https://github.com/mikenussbaumer/coronavirus_tracker#readme" target="_blank">
    <img alt="Documentation" src="https://img.shields.io/badge/documentation-yes-brightgreen.svg" />
  </a>
  <a href="https://github.com/mikenussbaumer/coronavirus_tracker/graphs/commit-activity" target="_blank">
    <img alt="Maintenance" src="https://img.shields.io/badge/Maintained%3F-yes-green.svg" />
  </a>
  <a href="https://github.com/mikenussbaumer/coronavirus_tracker/blob/master/LICENSE" target="_blank">
    <img alt="License: MIT" src="https://img.shields.io/github/license/mikenussbaumer/coronavirus_tracker" />
  </a>
</p>

> Used for crawling and storing coronavirus data into a database and moreover to process that data and visualize it.

## Structure

* `configmanager module` - manage json configs easily
* `databasemanager module` - manager for accessing databases
* `client module` - user interface to visualize corona data
* `server module` - for fetching and saving corona data every day

## Install

1. Install maven dependencies
2. Start the `server` module for the first time to create the database configs
3. Configure `MySQLConfig.json` with your mysql database connection data
4. Run the `ddl_mysql.sql` script in your mysql database to create the appropriate tables needed to get the application to work
5. Run the initial setup function from the `CSVDataSaveManager` to initialize the database with country + historical corona data

## Usage

* Run the `server` module in the background to fetch and save current corona data once a day
* Run the `client` module to get a visualization of the data in the database through a interface

## Author

👤 **Mike Nussbaumer**

* E-Mail: dev@mike-nussbaumer.com
* Github: [@mikenussbaumer](https://github.com/mikenussbaumer)
* LinkedIn: [@mike-nußbaumer](https://linkedin.com/in/mike-nußbaumer)

## 🤝 Contributing

Contributions, issues and feature requests are welcome!<br />Feel free to check [issues page](https://github.com/mikenussbaumer/coronavirus_tracker/issues).

## Show your support

Give a ⭐️ if this project helped you!

## 📝 License

Copyright © 2020 [Mike Nussbaumer](https://github.com/mikenussbaumer).<br />
This project is [MIT](https://github.com/mikenussbaumer/coronavirus_tracker/blob/master/LICENSE) licensed.