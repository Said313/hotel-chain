const { src, dest, series } = require("gulp");
const del = require('del');

function copyFrontendFiles(){
    return src('./client/build/**/*')
    .pipe(dest('./backend/src/main/webapp'));
}

function cleanWebAppFolder(){
    return del(['./backend/src/main/webapp/**/*',
                '!./backend/src/main/webapp/META-INF',
                '!./backend/src/main/webapp/WEB-INF']);
}

exports.clean = cleanWebAppFolder;
exports.copy = copyFrontendFiles;

exports.default = series(cleanWebAppFolder, copyFrontendFiles);