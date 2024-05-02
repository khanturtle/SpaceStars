FROM node:20-alpine as builder
WORKDIR /app

COPY yarn.lock package.json ./
RUN yarn install
COPY . .
RUN yarn build 

FROM node:20-alpine as runner
WORKDIR /app
COPY --from=builder /app/package.json .
COPY --from=builder /app/yarn.lock .
COPY --from=builder /app/next.config.mjs .
COPY --from=builder /app/public ./public
COPY --from=builder /app/.next ./.next

EXPOSE 3000

CMD [ "yarn", "start"]
