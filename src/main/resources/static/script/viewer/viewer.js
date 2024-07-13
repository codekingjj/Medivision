import * as cornerstone from '@cornerstonejs/core';
import * as cornerstoneDICOMImageLoader from '@cornerstonejs/dicom-image-loader';
import * as dicomParser from 'dicom-parser';
// import {init as csToolsInit, SegmentationDisplayTool} from "@cornerstonejs/tools";
// import * as cornerstoneTools from "@cornerstonejs/tools";
import Fetch from "../chat/utils/Fetch";
import StompManager from "../chat/chatroom/StompManager";

import {setTools} from "./tools";

export let viewportIds = [];
export let renderingEngineIds = [];

// 뷰 포트 생성
const content = $(".content");
// let imageListContainer = null;
// window.onload = () => {
//     imageListContainer = $("<div>", {
//         class : 'imageListContainer'
//     });
//    content.appendChild(imageListContainer);
// }

// 툴 정의
// const {
//     //도구 -> 돋보기
//     MagnifyTool,
//     TrackballRotateTool,
//     //도구 -> 확대, 축소
//     ZoomTool,
//     ToolGroupManager,
//     Enums: csToolsEnums,
//     //사진 이동
//     PanTool,
//     WindowLevelTool,
// } = cornerstoneTools;
//
// const { MouseBindings } = csToolsEnums;

window.onload = () => {
    loadData();
};

async function loadData() {
    viewportIds = [];
    renderingEngineIds = [];
    let num = 0;
    const studyKey = $("#studyKey").val();
   let seriesKeys = await fetchSeriesKeys(studyKey);
   console.log(seriesKeys);
   for (let seriesKey of seriesKeys) {

       let images = await fetchImages(seriesKey, studyKey);

       const imageIds = [];
       console.log(images)
       images.forEach((base64) => {
           const binary = atob(base64);
           const arraybuffer = Uint8Array.from(binary, c => c.charCodeAt(0));
           const imageId = `dicomweb:${URL.createObjectURL(new Blob([arraybuffer], {type: 'application/dicom'}))}`;
           imageIds.push(imageId);
       })
       // let byteCharacters = new Array(images.length);
       // for(let i = 0; i <images.length; i++) {
       //     byteCharacters[i] = atob(images[i])
       // }
       //
       // let byteCharacters = images.map(image => {
       //     return atob(image)
       // })


       // console.log(byteCharacters);
       // let byteNumbers = new Array(byteCharacters.length);
       // for(let i = 0 ; i < byteCharacters.length; i++) {
       //     byteNumbers[i] = byteCharacters.charCodeAt(i);
       // }
       // let byteArray = new Uint8Array(byteCharacters);
       // let blob = new Blob([byteArray], { type: byteCharacters });
       // console.log(blob);
       // const url = URL.createObjectURL(blob);
       // const imageId = `dicomimage:${url}`;
       // console.log(imageId);
       render(imageIds, num);
       num ++;

   }
    // error: function(error) {
    //     console.error('파일을 가져오는 중 오류 발생', error);
    // }
}


 const render = (imageIds, index) => {
    const element = document.createElement('dicomImage');
    element.style.width = '100%';
    element.style.height = '100%';
    element.style.marginBottom = '10px';
    element.classList.add("dicomImage");
    //element.id = `dicomImage${index}`;


    content.append(element);

    const renderingEngineId = `myRenderingEngine${index}`;
    const viewportId = `CT_AXIAL_STACK${index}`;


    try {
        setTools(viewportId, renderingEngineId);
    }catch (exception) {
        console.log(exception)
    }

    element.id = `${renderingEngineId}_${viewportId}`;

    renderingEngineIds.push(renderingEngineId);
    viewportIds.push(viewportId);
    const renderingEngine = new cornerstone.RenderingEngine(renderingEngineId);

    const viewportInput ={
        viewportId,
        element,
        type: cornerstone.Enums.ViewportType.STACK,
    }
    console.log(viewportInput);
    renderingEngine.enableElement(viewportInput);

    const viewport = renderingEngine.getViewport(viewportInput.viewportId);

    viewport.setStack(imageIds, 0);
    viewport.render();
}



async function fetchSeriesKeys(studyKey) {
    console.log(studyKey);
    return await fetch(`/viewer/get/${studyKey}`, {
                method: "GET",
            })
                .then(response => {
                    return response.json();
                })
                .then(data => {
                    return data;
                })
                .catch(err => {
                    window.location.href = "/auth/sign-in";
                });



}

async function fetchImages(seriesKey, studyKey) {

    return await fetch(`/viewer/get/${studyKey}/${seriesKey}`, {
        method: "GET",
    })
        .then(response => {
            return response.json();
        })
        .then(data => {
            return data;
        })
        .catch(err => {
            window.location.href = "/auth/sign-in";
        });



}

// element.oncontextmenu = (e) => e.preventDefault();
// element.style.width = '500px';
// element.style.height = '500px';
// content.appendChild(element);

// 파일 리딩
// const input = document.getElementById("file");
//
// input.addEventListener("change", (e) => {
//     console.log("진짜뷰어");
//     const files = e.target.files;
//
//     const reader = new FileReader();
//     reader.onload = (file) => {
//         const data = file.target.result;
//         render(data);
//     };
//     reader.readAsArrayBuffer(files[0]);
// });
//
// const render = async (arrayBuffer) => {
//     const imageId = `dicomweb:${URL.createObjectURL(new Blob([arrayBuffer], { type: 'application/dicom' }))}`;
//
//     const imageIds = [imageId];
//
//     const renderingEngineId = 'myRenderingEngine';
//     const viewportId = 'CT_AXIAL_STACK';

    // 1. 툴을 먼저 셋
//     try {
//         setTools(viewportId, renderingEngineId);
//     }catch (exception) {
//         console.log(exception)
//     }
//
//     const renderingEngine = new cornerstone.RenderingEngine(renderingEngineId);
//
//     const viewportInput = {
//         viewportId,
//         element,
//         type: cornerstone.Enums.ViewportType.STACK,
//     };
//
//     renderingEngine.enableElement(viewportInput);
//     const viewport = renderingEngine.getViewport(viewportInput.viewportId);
//
//     await viewport.setStack(imageIds, 0);
//
//     // 2.이미지가 로드된 후에 툴 설정을 수행
//     // setTools(viewportId, renderingEngineId);
//
//     // 뷰포트 리랜더링
//     viewport.render();
// };

// const setTools = (viewportId, renderingEngineId) => {
//     // 툴 추가
//     csToolsInit();
//
//     const toolGroupId = 'NAVIGATION_TOOL_GROUP_ID';
//
//     cornerstoneTools.addTool(MagnifyTool);
//     cornerstoneTools.addTool(TrackballRotateTool);
//     cornerstoneTools.addTool(ZoomTool);
//     cornerstoneTools.addTool(PanTool);
//     cornerstoneTools.addTool(WindowLevelTool);
//     // cornerstoneTools.addTool(SegmentationDisplayTool);
//
//     const toolGroup = ToolGroupManager.createToolGroup(toolGroupId);
//
//     toolGroup.addTool(MagnifyTool.toolName, { cursor: '' });
//     toolGroup.addTool(TrackballRotateTool.toolName, { cursor: 'crosshair' });
//     toolGroup.addTool(ZoomTool.toolName, { cursor: 'zoom-in' });
//     // toolGroup.addTool(SegmentationDisplayTool.toolName, {cursor: 'abc'});
//     // toolGroup.addTool(AnnotationDisplayTool.toolName, {cursor:'asd'});
//     toolGroup.addTool(PanTool.toolName, {cursor:'move'});
//     toolGroup.addTool(WindowLevelTool.toolName, {cursor:'light'});
//
//     // 툴 활성화
//     toolGroup.setToolActive(WindowLevelTool.toolName, {
//         bindings: [{ mouseButton: MouseBindings.Primary }],
//     });
//
//     toolGroup.setToolActive(TrackballRotateTool.toolName, {
//         bindings: [{ mouseButton: MouseBindings.Auxiliary }],
//     });
//
//     toolGroup.setToolActive(ZoomTool.toolName, {
//         bindings: [{ mouseButton: MouseBindings.Secondary }],
//     });
//
//     toolGroup.addViewport(viewportId, renderingEngineId);
// };

const init = async () => {
    await cornerstone.init();
    cornerstoneDICOMImageLoader.external.cornerstone = cornerstone;
    cornerstoneDICOMImageLoader.external.dicomParser = dicomParser;

    var config = {
        maxWebWorkers: navigator.hardwareConcurrency || 1,
        startWebWorkersOnDemand: true,
        taskConfiguration: {
            decodeTask: {
                initializeCodecsOnStartup: false,
            },
            sleepTask: {
                sleepTime: 3000,
            },
        },
    };

    cornerstoneDICOMImageLoader.webWorkerManager.initialize(config);
};

init();