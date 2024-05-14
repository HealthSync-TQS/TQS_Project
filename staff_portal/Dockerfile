FROM node:20.11.1-alpine AS build

WORKDIR /app

COPY package.json .

RUN npm install

COPY . .

EXPOSE 8082

CMD [ "npm", "start"]