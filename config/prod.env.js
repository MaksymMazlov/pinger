'use strict';

module.exports = {
  NODE_ENV: '"production"',
  SERVER_API_URL: '""',
  BUILD_TIMESTAMP: `'${new Date().getTime()}'`,
  VERSION: `'${process.env.hasOwnProperty('APP_VERSION') ? process.env.APP_VERSION : 'UNKNOWN'}'`,
};
