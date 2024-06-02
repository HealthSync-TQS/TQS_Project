FROM node:20.11.1-alpine AS build

WORKDIR /app

COPY package.json .

RUN npm cache clean --force

RUN npm install

COPY . .

EXPOSE 3003

CMD [ "npm", "start"]