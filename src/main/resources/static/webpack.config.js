const path = require("path");

module.exports = {
    entry: ["./script/viewer/viewer.js",
    "./script/viewer/tools.js"], //  시작 파일의 경로
    output: {
        filename: "bundle.js",                  // 출력 파일 이름
        path: path.resolve(__dirname, "dist")   // 출력 디렉토리
    },
    resolve: {
        modules: ["node_modules"],      // 모듈 검색 경로
        extensions: [".ts", ".js", ".json", ".wasm"]
    },
    mode: "development"
}