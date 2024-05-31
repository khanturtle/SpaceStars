# 1단계: 빌더 이미지
FROM node:18-alpine AS builder

WORKDIR /app

COPY .yarn .yarn
COPY .yarnrc.yml .yarnrc.yml
COPY package.json package.json
COPY packages/ui/package.json packages/ui/package.json
COPY packages/web/package.json packages/web/package.json

RUN yarn cache clean
RUN yarn install
COPY .pnp.loader.mjs .pnp.loader.mjs

COPY . .

RUN yarn ui build

# 2단계: 실제 애플리케이션 이미지
FROM node:18-alpine

WORKDIR /app

# 빌더 단계에서 필요한 파일만 복사
COPY --from=builder /app/packages/ui/package.json packages/ui/package.json
COPY --from=builder /app/packages/web/package.json packages/web/package.json
COPY --from=builder /app/.yarn .yarn
COPY --from=builder /app/.yarnrc.yml .yarnrc.yml
COPY --from=builder /app/yarn.lock yarn.lock
COPY --from=builder /app/package.json package.json
COPY --from=builder /app/.pnp.loader.mjs .pnp.loader.mjs

COPY --from=builder /app/packages/ui/dist packages/ui/dist

# 웹 패키지 빌드
RUN yarn cache clean
RUN yarn install

COPY . . 

RUN yarn web build

# 3단계: 실제 애플리케이션 이미지
FROM node:18-alpine

WORKDIR /app

COPY --from=builder2 /app/packages/ui/package.json packages/ui/package.json
COPY --from=builder2 /app/packages/web/package.json packages/web/package.json
COPY --from=builder2 /app/.yarn .yarn
COPY --from=builder2 /app/.yarnrc.yml .yarnrc.yml
COPY --from=builder2 /app/yarn.lock yarn.lock
COPY --from=builder2 /app/package.json package.json
COPY --from=builder2 /app/.pnp.loader.mjs .pnp.loader.mjs

COPY --from=builder2 /app/packages/web/.next packages/web/.next 
COPY --from=builder2 /app/packages/web/public packages/web/public
COPY --from=builder2 /app/packages/ui/dist packages/ui/dist

RUN yarn install

# 웹 서버를 위한 포트 열기
EXPOSE 3000

# 웹 서버 시작 명령어
CMD ["yarn", "web", "start"]
