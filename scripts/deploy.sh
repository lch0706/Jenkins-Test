#!/bin/bash
# deploy.sh

# Exit immediately if a command exits with a non-zero status
set -e

echo "Starting deployment..."

# Define variables
APP_NAME="MyApplication"
DEPLOY_DIR="/home/juhan/myApp"
REPO_URL="https://github.com/lch0706/Jenkins-Test.git"
BRANCH="main"

# Ensure the deployment directory exists
if [ ! -d "$DEPLOY_DIR" ]; then
    echo "Creating deployment directory..."
    mkdir -p "$DEPLOY_DIR"
fi

# Clone or pull the latest code
if [ -d "$DEPLOY_DIR/.git" ]; then
    echo "Updating existing repository..."
    cd "$DEPLOY_DIR"
    git pull origin "$BRANCH"
else
    echo "Cloning repository..."
    git clone --branch "$BRANCH" "$REPO_URL" "$DEPLOY_DIR"
    cd "$DEPLOY_DIR"
fi

# Optional: Install dependencies
echo "Installing dependencies..."
# Example for Node.js:
# npm install

# Example for Python:
# pip install -r requirements.txt

# Start or restart your application
echo "Starting application using PM2..."
# Example for Node.js with PM2
# pm2 startOrRestart ecosystem.config.js --only "$APP_NAME"

echo "Deployment completed successfully."
