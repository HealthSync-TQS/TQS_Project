FROM node:20.11.1-alpine

WORKDIR /app

COPY package*.json ./

RUN npm cache clean --force

RUN npm install

COPY . .

EXPOSE 3002

CMD ["npm", "start"]