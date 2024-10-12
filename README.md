# ViCoupon Global Product and Price Crawler

**ViCoupon Global Product and Price Crawler** is a Java-based system designed to collect and track product information and pricing from e-commerce platforms worldwide. The data is aggregated and used to provide users of [ViCoupon.com](https://vicoupon.com) with the best deals, coupons, and offers.

## Features

- **Global Coverage**: Crawls product information from e-commerce platforms worldwide.
- **Price Tracking**: Monitors product price changes in real-time for accurate and timely information.
- **Multi-format Support**: Exports data to multiple formats (CSV, JSON) for further use and integration.
- **Customizable**: Easily configure crawl frequency, store preferences, and product filters.
- **Scalable**: Built on a scalable architecture to handle large volumes of product data.


## How It Works

1. **Crawler Initialization**: Each crawler is customized to extract product details such as name, price, description, and availability from global e-commerce stores.
2. **Data Processing**: The extracted data is processed, cleaned, and normalized before storage.
3. **Data Export**: The processed data is stored in a database and can be exported in various formats (CSV, JSON) for use in analytics or the ViCoupon website.

## Technologies Used

- **Java 21**: Core programming language for the crawler system.
- **Jsoup**: For HTML parsing and web scraping.
- **Selenium**: For headless browser automation for websites requiring JavaScript execution.
- **JDBC**: For interaction with relational databases (e.g., MySQL).
- **MongoDB**: For storing product and pricing data (alternative to MySQL).
- **Maven**: For project dependency management and build automation.
- **AWS Services**: For deployment and scaling (e.g., S3, EC2, Lambda).

## Setup and Installation

### Prerequisites

- Java 21 or higher
- Maven 3.x or higher
- MySQL or MongoDB (depending on your database setup)

## Contact

For more information or support, contact us at:

- **Email**: [contact@vicoupon.com](mailto:contact@vicoupon.com)
- **Website**: [ViCoupon.com](https://vicoupon.com)
