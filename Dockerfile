# Use a base image with JDK and Gradle
FROM gradle:7.6.1-jdk17 as builder

# Set the working directory inside the container
WORKDIR /app

# Copy the project files to the container
COPY . .

# Install dependencies and Google Chrome version 125
RUN apt-get update && apt-get install -y wget unzip gnupg curl \
    && curl -sSL https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update && apt-get install -y \
    google-chrome-stable=125.0.6422.76-1 \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

# Download and install Chromedriver version 125 for Linux
RUN wget -O /tmp/chromedriver.zip https://storage.googleapis.com/chrome-for-testing-public/125.0.6422.76/linux64/chromedriver-linux64.zip \
    && unzip /tmp/chromedriver.zip -d /tmp/ \
    && mv /tmp/chromedriver-linux64/chromedriver /usr/local/bin/chromedriver \
    && chmod +x /usr/local/bin/chromedriver \
    && rm -rf /tmp/chromedriver.zip /tmp/chromedriver-linux64

# Set environment variables for Chrome and Chromedriver
ENV CHROME_BIN=/usr/bin/google-chrome
ENV CHROMEDRIVER=/usr/local/bin/chromedriver

# Create reports directory
RUN mkdir -p /app/reports

# Run tests and generate report
CMD ["gradle", "clean", "test"]
