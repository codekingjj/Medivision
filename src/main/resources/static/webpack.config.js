const path = require('path');

module.exports = {
    entry: {
        viewer: './script/viewer/viewer.js',     // 기존 엔트리 포인트
        search: './script/search/search.js'      // 새로운 엔트리 포인트
    },
    output: {
        filename: '[name].bundle.js',
        path: path.resolve(__dirname, 'dist'),
    },
    resolve: {
        modules: ['node_modules'],
        extensions: ['.ts', '.js', '.json', '.wasm'],
        alias: {
            'a': path.resolve(__dirname, 'node_modules/@icr/polyseg-wasm/dist')
        }
    },
    cache: {
        type: 'filesystem',
        cacheDirectory: path.resolve(__dirname, '.webpack_cache'),
    },
    experiments: {
        asyncWebAssembly: true,
    },
    module: {
        rules: [
            {
                test: /\.wasm$/,
                type: 'webassembly/async',
            },
            {
                test: /\.js$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                },
            },
        ],
    },
    stats: {
        errorDetails: true,
    },
    mode: 'development'
};

// const path = require("path");
// const NodePolyfillPlugin = require("node-polyfill-webpack-plugin");
//
// module.exports = {
//     entry: {
//         viewer: './script/viewer/viewer.js',     // 기존 엔트리 포인트
//         search: './script/search/search.js'      // 새로운 엔트리 포인트
//     },
//     output: {
//         filename: '[name].bundle.js',            // 각각의 번들에 대해 파일 이름 지정
//         path: path.resolve(__dirname, 'dist')    // 출력 디렉토리
//     },
//     resolve: {
//         modules: ['node_modules'],               // 모듈 검색 경로
//         extensions: ['.ts', '.js', 'json', '.wasm'],
//         fallback: {
//             "fs": false,
//             "path": require.resolve("path-browserify"),
//             "os": require.resolve("os-browserify/browser"),
//             "module": false
//         }
//     },
//     cache: {
//         type: 'filesystem',
//         cacheDirectory: path.resolve(__dirname, '.webpack_cache'),
//     },
//     mode: 'development',                         // 개발 모드
//     module: {
//         rules: [
//             {
//                 test: /\.wasm$/,
//                 type: 'webassembly/async'
//             }
//         ]
//     },
//     experiments: {
//         asyncWebAssembly: true,
//         syncWebAssembly: true
//     },
//     plugins: [
//         new NodePolyfillPlugin()
//     ]
// };
