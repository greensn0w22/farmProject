sudo apt install npm
npm init
echo '{
  "name": "farm-api",
  "version": "1.0.0",
  "description": "",
  "main": "index.js",
  "scripts": {
    "start": "node index",
    "dev": "nodemon index"
},
  "author": "",
  "license": "ISC"
}
' > package.json

npm install --save-dev nodemon
npm install mysql