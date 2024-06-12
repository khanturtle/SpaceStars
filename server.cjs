const fs = require('fs');
const path = require('path');
const https = require('https');
const express = require('express');
const next = require('next');

const dev = process.env.NODE_ENV !== 'production';
const app = next({ dev });
const handle = app.getRequestHandler();

const httpsOptions = {
  key: fs.readFileSync(path.resolve(__dirname, 'certs/10.10.10.43+3-key.pem')),
  cert: fs.readFileSync(path.resolve(__dirname, 'certs/10.10.10.43+3.pem')),
};

app.prepare().then(() => {  
  const server = express();

  server.all('*', (req, res) => {
    return handle(req, res);
  });

  https.createServer(httpsOptions, server).listen(3000, '0.0.0.0', err => {
    if (err) throw err;
    console.log('> Ready on https://localhost:3000 and https://10.10.10.43:3000');
  });
});
