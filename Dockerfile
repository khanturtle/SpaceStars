FROM node:18-alpine as builder
WORKDIR /usr/src/app

# 종속성 파일 복사
COPY ./package*.json ./.yarnrc.yml ./yarn.lock ./
COPY ./.yarn ./.yarn

RUN yarn cache clean && yarn

COPY . .
RUN yarn build 

EXPOSE 3000

CMD [ "yarn", "start"]
