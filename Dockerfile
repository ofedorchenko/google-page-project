# Use a base image with JDK and Gradle
FROM gradle:7.6.1-jdk17 as builder

# Install dependencies and Google Chrome last version
RUN apt-get update && apt-get install -y wget unzip gnupg curl xvfb \
    && curl -sSL https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update && apt-get install -y --no-install-recommends \
    google-chrome-stable \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

# Download and install Chromedriver for the correct version for Linux
RUN wget -O /tmp/chromedriver.zip https://storage.googleapis.com/chrome-for-testing-public/125.0.6422.141/linux64/chromedriver-linux64.zip \
    && unzip /tmp/chromedriver.zip -d /tmp/ \
    && mv /tmp/chromedriver-linux64/chromedriver /usr/local/bin/chromedriver \
    && chmod +x /usr/local/bin/chromedriver \
    && rm -rf /tmp/chromedriver.zip /tmp/chromedriver-linux64

# Set environment variables for Chromedriver
ENV CHROMEDRIVER_PATH=/usr/local/bin/chromedriver

# Set the working directory inside the container
WORKDIR /app

# Create reports directory
RUN mkdir -p /app/reports

# Copy the project files to the container
COPY . .

# Run tests and generate report
CMD ["gradle", "clean", "test"]