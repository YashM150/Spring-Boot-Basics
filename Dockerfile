version: '3.8'
services:
  backend:
    build: 
      context: ./ecom
      dockerfile: Dockerfile
    ports:
      - "8080:8080"
    networks:
      - app-network

  frontend:
    build:
      context: ./ecom-frontend-1
      dockerfile: Dockerfile
    ports:
      - "5173:5173"
    networks:
      - app-network

networks:
  app-network:
    driver: bridge