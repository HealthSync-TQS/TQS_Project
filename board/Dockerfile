FROM node:20.11.1-alpine AS build

WORKDIR /app

COPY package.json .

RUN npm install

COPY . .

EXPOSE 8083

CMD [ "npm", "run", "dev" ]
