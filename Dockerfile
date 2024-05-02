FROM node:20-alpine as builder
WORKDIR /user/src/app

# 종속성 파일 복사
COPY ./package*.json ./.yarnrc.yml ./.pnp.cjs ./.pnp.loader.mjs ./yarn.lock ./
COPY ./.yarn ./.yarn

RUN yarn install

# FROM node:20-alpine as runner
# WORKDIR /app
# COPY --from=builder /app/package.json .
# COPY --from=builder /app/yarn.lock .
# COPY --from=builder /app/next.config.mjs .
# COPY --from=builder /app/public ./public
# COPY --from=builder /app/.next ./.next

COPY ./tsconfig.json ./.prettierrc.json ./.env.* ./next-env.d.ts ./.eslintrc.json ./next.config.mjs ./
COPY ./src ./src
# COPY . .
RUN yarn build 

EXPOSE 3000

CMD [ "yarn", "start"]
