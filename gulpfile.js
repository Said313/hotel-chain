
const {src, dest} = require('gulp');

function copy(){
    return src('client/build/*')
        .pipe(dest('hotel-chain-app/src/main/webapp'));
}

exports.default = copy;