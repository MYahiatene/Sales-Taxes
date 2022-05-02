FROM node:16-alpine

WORKDIR /usr/app

COPY ./package.json .

COPY ./package-lock.json .

RUN npm install

COPY . .

ENTRYPOINT ["npm","run","dev"]
